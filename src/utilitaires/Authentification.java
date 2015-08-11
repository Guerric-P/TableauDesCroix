package utilitaires;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import services.PersonneService;
import domaine.Personne;

public class Authentification implements LoginModule {

  private CallbackHandler handler;
  private Subject subject;
  private Personne userPrincipal;
  private Role rolePrincipal;
  private String login;
  private List<String> userGroups;

  @Override
  public void initialize(Subject subject,
      CallbackHandler callbackHandler,
      Map<String, ?> sharedState,
      Map<String, ?> options) {

    handler = callbackHandler;
    this.subject = subject;
  }

  @Override
  public boolean login() throws LoginException {

    Callback[] callbacks = new Callback[2];
    callbacks[0] = new NameCallback("login");
    callbacks[1] = new PasswordCallback("password", true);
    
      try {
		handler.handle(callbacks);
	} catch (IOException e) {
		throw new LoginException("Authentication failed");
	} catch (UnsupportedCallbackException e) {
		throw new LoginException("Authentication failed");
	}
      String name = ((NameCallback) callbacks[0]).getName();
      String password = String.valueOf(((PasswordCallback) callbacks[1])
          .getPassword());
      
      userPrincipal = PersonneService.GetInstance().ChargerPersonneParLogin(name);
      
      if(userPrincipal != null){
	
	      if(userPrincipal.getLogin().equals(userPrincipal.getMotDePasse()))
	      {
	    	  try {
				userPrincipal.setMotDePasse(Cryptage.encrypt(password));
			} catch (Exception e) {
				throw new LoginException("Authentication failed");
			}
	    	  PersonneService.GetInstance().MettreAJour(userPrincipal);
	    	  CreerRoles(userPrincipal);
	    	  login = name;
	    	  return true;
	      } else
			try {
				if(userPrincipal.getMotDePasse().equals(Cryptage.encrypt(password)))
				  {
					  CreerRoles(userPrincipal);
					  login = name;
					  return true;
				  }
			} catch (Exception e) {
				throw new LoginException("Authentication failed");
			}
      }

      // If credentials are NOT OK we throw a LoginException
     throw new LoginException("Authentication failed");
  }

  @Override
  public boolean commit() throws LoginException {

    subject.getPrincipals().add(userPrincipal);

    if (userGroups != null && userGroups.size() > 0) {
      for (String groupName : userGroups) {
        rolePrincipal = new Role(groupName);
        subject.getPrincipals().add(rolePrincipal);
      }
    }

    return true;
  }

  @Override
  public boolean abort() throws LoginException {
    return true;
  }

  @Override
  public boolean logout() throws LoginException {
    subject.getPrincipals().remove(userPrincipal);
    subject.getPrincipals().remove(rolePrincipal);
    return true;
  }
  
  private void CreerRoles(Personne userPrincipal){
	  userGroups = new ArrayList<String>();
      if(userPrincipal.isEstAdmin()){
          userGroups.add("admin");
      }
      userGroups.add("user");
  }

}
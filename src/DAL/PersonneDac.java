package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utilitaires.ConfigManager;
import domaine.Ilot;
import domaine.Personne;

public class PersonneDac extends DacBase {

	  private Connection connect = null;
	  private PreparedStatement preparedStatement = null;
	  public List<Personne> ChargerTous() throws Exception{

	      // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
			  List<Personne> liste = new ArrayList<Personne>();
		      connect.createStatement();
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      CallableStatement cStmt = connect.prepareCall("{call psS_Personne()}");
		      boolean hasResults = cStmt.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = cStmt.getResultSet();
		          while(resultSet.next()){
		        	  liste.add(ConvertirPourJava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getString(9), resultSet.getString(10), resultSet.getBoolean(11)));
		          }
		      }
		      return liste;
	      }
	      catch(Exception e)
	      {
	    	  TraiterErreur(e);
	    	  throw e;
	      }
	      finally
	      {
	    	  try{
		    	  connect.close();
	    	  }
	    	  catch(Exception e){
	    		  
	    	  }
	    	  finally{
	    		  
	    	  }
	      }	      
	  }
	  
	  public Personne ChargerParId(int id) {

	      // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
		      // Statements allow to issue SQL queries to the database
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      preparedStatement = connect.prepareStatement("{call ps_ChargerPersonneParId(?)}");
		      preparedStatement.setInt(1, id);
		      boolean hasResults = preparedStatement.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = preparedStatement.executeQuery();
		          if (resultSet.next())
		        	  return ConvertirPourJava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getString(9), resultSet.getString(10), resultSet.getBoolean(11));
		      }
		      return null;
	      }
	      catch(Exception e)
	      {
	    	  TraiterErreur(e);
	    	  return null;
	      }
	      finally
	      {
	    	  try{
		    	  connect.close();
	    	  }
	    	  catch(Exception e){
	    		  
	    	  }
	    	  finally{
	    		  
	    	  }
	      }	      
	  }
	  
	  private Personne ConvertirPourJava(int id, String trigramme, String nom, String prenom, int idIlot, boolean aMisSaCroix, int nombreCroix, Date dateCroissants, String login, String motDePasse, boolean estAdmin){
		  Ilot ilot = IlotDac.GetInstance().Charger(idIlot);
		  return new Personne(id, trigramme, nom, prenom, ilot, aMisSaCroix, nombreCroix, dateCroissants, login, motDePasse, estAdmin);
	  }
	  
	  public static PersonneDac GetInstance(){
		  return new PersonneDac();
	  }

	public void MettreAJour(Personne personne) {
		try{
		Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
	      // Statements allow to issue SQL queries to the database
	      // Result set get the result of the SQL query
	      //resultSet = statement.executeQuery("select * from test.personne");
	      preparedStatement = connect.prepareStatement("{call psU_Personne(?,?,?,?,?,?,?,?,?,?,?)}");
	      preparedStatement.setInt(1, personne.getId());
	      preparedStatement.setString(2, personne.getTrigramme());
	      preparedStatement.setString(3, personne.getNom());
	      preparedStatement.setString(4, personne.getPrenom());
	      preparedStatement.setInt(5, personne.getIlot().getId());
	      preparedStatement.setBoolean(6, personne.isaMisSaCroix());
	      preparedStatement.setInt(7, personne.getNombreCroix());
	      preparedStatement.setDate(8, personne.getDateCroissants());
	      preparedStatement.setString(9, personne.getLogin());
	      preparedStatement.setString(10, personne.getMotDePasse());
	      preparedStatement.setBoolean(11, personne.isEstAdmin());
	      preparedStatement.execute();
		}
	      catch(Exception e)
	      {
	    	  TraiterErreur(e);
	      }
	      finally
	      {
	    	  try{
		    	  connect.close();
	    	  }
	    	  catch(Exception e){
	    		  
	    	  }
	    	  finally{
	    		  
	    	  }
	      }	     
		
	}

	public Personne ChargerPersonneParLogin(String login) {
		// Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
		      // Statements allow to issue SQL queries to the database
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      preparedStatement = connect.prepareStatement("{call ps_ChargerPersonneParLogin(?)}");
		      preparedStatement.setString(1, login);
		      boolean hasResults = preparedStatement.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = preparedStatement.executeQuery();
		          if (resultSet.next())
		        	  return ConvertirPourJava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getString(9), resultSet.getString(10), resultSet.getBoolean(11));
		      }
		      return null;
	      }
	      catch(Exception e)
	      {
	    	  TraiterErreur(e);
	    	  return null;
	      }
	      finally
	      {
	    	  try{
		    	  connect.close();
	    	  }
	    	  catch(Exception e){
	    		  
	    	  }
	    	  finally{
	    		  
	    	  }
	      }	      
	}

	public Personne GetPersonneAyantMisSaDateLeMemeJour(java.sql.Date dateSQL) {
		 try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
		      // Statements allow to issue SQL queries to the database
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      preparedStatement = connect.prepareStatement("{call ps_GetPersonneAyantMisSaDateLeMemeJour(?)}");
		      preparedStatement.setDate(1, dateSQL);
		      boolean hasResults = preparedStatement.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = preparedStatement.executeQuery();
		          if (resultSet.next())
		        	  return ConvertirPourJava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getString(9), resultSet.getString(10), resultSet.getBoolean(11));
		      }
		      return null;
	      }
	      catch(Exception e)
	      {
	    	  TraiterErreur(e);
	    	  return null;
	      }
	      finally
	      {
	    	  try{
		    	  connect.close();
	    	  }
	    	  catch(Exception e){
	    		  
	    	  }
	    	  finally{
	    		  
	    	  }
	      }	      
	}
}

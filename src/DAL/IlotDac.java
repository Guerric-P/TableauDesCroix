package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilitaires.ConfigManager;
import domaine.Ilot;

public class IlotDac extends DacBase {
	  private Connection connect = null;
	  private PreparedStatement preparedStatement = null;
	  public Ilot Charger(int id){
	      // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
		      // Statements allow to issue SQL queries to the database
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      preparedStatement = connect.prepareStatement("{call psS_Ilot(?)}");
		      preparedStatement.setInt(1, id);
		      boolean hasResults = preparedStatement.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = preparedStatement.executeQuery();
		          resultSet.next();
		          return ConvertirPourJava(resultSet.getInt(1), resultSet.getString(2));
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
	
	  private Ilot ConvertirPourJava(int id, String libelle){
		  
		  return new Ilot(id, libelle);
	  }
	  
	  public static IlotDac GetInstance(){
		  return new IlotDac();
	  }
}

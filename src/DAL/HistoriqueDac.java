package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import utilitaires.ConfigManager;
import domaine.Historique;
import domaine.Personne;
import domaine.TypeCroix;

public class HistoriqueDac extends DacBase {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	public List<Historique> ChargerTous() throws Exception{

	      // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
			  List<Historique> liste = new ArrayList<Historique>();
		      connect.createStatement();
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      CallableStatement cStmt = connect.prepareCall("{call psS_Historique()}");
		      boolean hasResults = cStmt.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = cStmt.getResultSet();
		          while(resultSet.next()){
		        	  liste.add(ConvertirPourJava(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getTimestamp(6)));
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
	
	public List<Historique> ChargerDixDernieresSecondes() throws Exception{

	      // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
			  List<Historique> liste = new ArrayList<Historique>();
		      connect.createStatement();
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      CallableStatement cStmt = connect.prepareCall("{call ps_ChargerHistoriqueDixDernieresSecondes()}");
		      boolean hasResults = cStmt.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = cStmt.getResultSet();
		          while(resultSet.next()){
		        	  liste.add(ConvertirPourJava(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getTimestamp(6)));
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
	
	public void Creer(Personne personneEmettrice, Personne personneReceptrice, String motif, TypeCroix typeCroix, Timestamp date){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
		      // Statements allow to issue SQL queries to the database
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      preparedStatement = connect.prepareStatement("{call psI_Historique(?,?,?,?,?)}");
		      if(personneEmettrice == null){
		    	  preparedStatement.setNull(1, Types.INTEGER);
	    	  }
		      else{
		    	  preparedStatement.setInt(1,  personneEmettrice.getId());
		      }
		      if(personneReceptrice == null){
		    	  preparedStatement.setNull(2, Types.INTEGER);
	    	  }
		      else{
		    	  preparedStatement.setInt(2,  personneReceptrice.getId());
		      }
		      preparedStatement.setString(3, motif);
		      preparedStatement.setInt(4, typeCroix.ordinal()+1);
		      preparedStatement.setTimestamp(5, date);
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
	
	private Historique ConvertirPourJava(int id, int idPersonneEmettrice, int idPersonneReceptrice, String motif,  int idTypeCroix, Timestamp date ){
		Personne personneEmettrice = PersonneDac.GetInstance().ChargerParId(idPersonneEmettrice);
		Personne personneReceptrice = PersonneDac.GetInstance().ChargerParId(idPersonneReceptrice);
		TypeCroix typeCroix = TypeCroix.values()[idTypeCroix-1];
		
		return new Historique(personneEmettrice, personneReceptrice, motif, typeCroix, id, date);
	}
	
	public static HistoriqueDac GetInstance(){
		return new HistoriqueDac();		
	}

	public void Purger() {
		try{
		Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
	      // Statements allow to issue SQL queries to the database
	      // Result set get the result of the SQL query
	      //resultSet = statement.executeQuery("select * from test.personne");
	      preparedStatement = connect.prepareStatement("{call ps_PurgerHistorique}");
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

	public List<Historique> ChargerSemaineCourante() throws Exception {
		 // Setup the connection with the DB
	      try
	      {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(new ConfigManager().getParameter("URL"));
			  List<Historique> liste = new ArrayList<Historique>();
		      connect.createStatement();
		      // Result set get the result of the SQL query
		      //resultSet = statement.executeQuery("select * from test.personne");
		      CallableStatement cStmt = connect.prepareCall("{call ps_ChargerHistoriqueSemaineCourante()}");
		      boolean hasResults = cStmt.execute();
		      ResultSet resultSet = null;
		      if (hasResults) {
		          resultSet = cStmt.getResultSet();
		          while(resultSet.next()){
		        	  liste.add(ConvertirPourJava(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getTimestamp(6)));
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
}

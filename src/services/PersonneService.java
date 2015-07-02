package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import DAL.PersonneDac;
import domaine.Personne;
import domaine.TypeCroix;

public class PersonneService {
	public List<Personne> ChargerTous() throws Exception{
		List<Personne> liste = PersonneDac.GetInstance().ChargerTous();
		Collections.sort(liste, new Comparator<Personne>() {
	        @Override
	        public int compare(Personne  personne1, Personne  personne2)
	        {
	            return  personne1.getIlot().getLibelle().compareTo(personne2.getIlot().getLibelle());
	        }
	    });

		return liste;
	}
	
	public static PersonneService GetInstance(){
		return new PersonneService();
	}

	
	public boolean Mettrecroix(int idPersonneDonnante, int idPersonnePrenante, String motif){
		PersonneDac personneDac = PersonneDac.GetInstance();
		Personne personneDonnante = personneDac.ChargerParId(idPersonneDonnante);
		if (personneDonnante.isaMisSaCroix()){
			return false;
		}
		else{
			personneDonnante.setaMisSaCroix(true);
			personneDac.MettreAJour(personneDonnante);
			Personne personnePrenante = personneDac.ChargerParId(idPersonnePrenante);
			personnePrenante.setNombreCroix(personnePrenante.getNombreCroix() + 1);
			personneDac.MettreAJour(personnePrenante);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			HistoriqueService.GetInstance().Creer(personneDonnante, personnePrenante, motif, TypeCroix.Personnelle, date);
			return true;
		}
	}

	public boolean ResetCroix(int id) {
		PersonneDac personneDac = PersonneDac.GetInstance();
		Personne personneAReset = personneDac.ChargerParId(id);
		personneAReset.setNombreCroix(0);
		personneDac.MettreAJour(personneAReset);
		return true;
	}

	public boolean ResetIndicateurs() {
		
		try{List<Personne> liste = PersonneDac.GetInstance().ChargerTous();
		PersonneDac personneDac = PersonneDac.GetInstance();
		for(Personne personne : liste){
			personne.setaMisSaCroix(false);
			personneDac.MettreAJour(personne);
		}
		return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public boolean MettreCroixSpeciale(int idPersonnePrenante, int idTypeCroix, String motif) {
		PersonneDac personneDac = PersonneDac.GetInstance();
		Personne personnePrenante = personneDac.ChargerParId(idPersonnePrenante);
		personnePrenante.setNombreCroix(personnePrenante.getNombreCroix() + 1);
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		HistoriqueService.GetInstance().Creer(null, personnePrenante, motif, TypeCroix.values()[idTypeCroix], date);
		PersonneService.GetInstance().MettreAJour(personnePrenante);
		return true;
	}
	
	public Personne ChargerPersonneParLogin(String login){
		PersonneDac personneDac = PersonneDac.GetInstance();
		return personneDac.ChargerPersonneParLogin(login);
	}
	
	public void MettreAJour(Personne personne){
		PersonneDac personneDac = PersonneDac.GetInstance();
		personneDac.MettreAJour(personne);
	}

	public void MettreDate(String date, Personne personne) throws ParseException {
		java.sql.Date dateSQL;
		if(date.isEmpty()){
			dateSQL = null;
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateSQL = new java.sql.Date(sdf.parse(date).getTime());
		}	
		personne.setDateCroissants(dateSQL);
		PersonneService.GetInstance().MettreAJour(personne);
	}
}

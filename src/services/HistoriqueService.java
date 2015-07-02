package services;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DAL.HistoriqueDac;

import com.google.gson.Gson;

import domaine.Historique;
import domaine.Personne;
import domaine.TypeCroix;

public class HistoriqueService {
	public List<Historique> ChargerTous() throws Exception{
		List<Historique> liste = HistoriqueDac.GetInstance().ChargerTous();
		Collections.sort(liste, new Comparator<Historique>() {
	        @Override
	        public int compare(Historique  historique1, Historique  historique2)
	        {
	            return  historique2.getDate().compareTo(historique1.getDate());
	        }
	    });

		return liste;
	}
	
	public static HistoriqueService GetInstance(){
		return new HistoriqueService();
	}
	
	public void Creer(Personne personneEmettrice, Personne personneReceptrice, String motif, TypeCroix typeCroix, Timestamp date){
		HistoriqueDac.GetInstance().Creer(personneEmettrice, personneReceptrice, motif, typeCroix, date);
	}

	public void Purger() {
		HistoriqueDac.GetInstance().Purger();
		
	}

	public String ChargerNotifications() throws Exception {
		Gson gson = new Gson();
		List<Historique> liste = HistoriqueDac.GetInstance().ChargerDixDernieresSecondes();
		return gson.toJson(liste);
	}
	
	public List<Historique> ChargerSemaineCourante() throws Exception{
		List<Historique> liste = HistoriqueDac.GetInstance().ChargerSemaineCourante();
		Collections.sort(liste, new Comparator<Historique>() {
	        @Override
	        public int compare(Historique  historique1, Historique  historique2)
	        {
	            return  historique2.getDate().compareTo(historique1.getDate());
	        }
	    });

		return liste;
	}
}

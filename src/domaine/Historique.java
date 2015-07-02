package domaine;

import java.sql.Timestamp;

public class Historique {
	private Personne personneEmettrice;
	private Personne personneReceptrice;
	private String motif;
	private TypeCroix typeCroix;
	private int id;
	private Timestamp date;
	
	public Historique(Personne personneEmettrice, Personne personneReceptrice, String motif, TypeCroix typeCroix,
			int id, Timestamp date) {
		super();
		this.personneEmettrice = personneEmettrice;
		this.personneReceptrice = personneReceptrice;
		this.motif = motif;
		this.typeCroix = typeCroix;
		this.id = id;
		this.date = date;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Personne getPersonneEmettrice() {
		return personneEmettrice;
	}
	public void setPersonneEmettrice(Personne personneEmettrice) {
		this.personneEmettrice = personneEmettrice;
	}
	public Personne getPersonneReceptrice() {
		return personneReceptrice;
	}
	public void setPersonneReceptrice(Personne personneReceptrice) {
		this.personneReceptrice = personneReceptrice;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public TypeCroix getTypeCroix() {
		return typeCroix;
	}
	public void setTypeCroix(TypeCroix typeCroix) {
		this.typeCroix = typeCroix;
	}
}

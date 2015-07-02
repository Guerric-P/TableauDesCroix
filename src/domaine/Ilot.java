package domaine;

public class Ilot {
	int id;
	String libelle;
	
	public Ilot(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}

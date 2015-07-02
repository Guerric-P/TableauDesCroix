package domaine;

import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Personne implements Principal {
	private int id;
	private String trigramme;
	private String nom;
	private String prenom;
	private Ilot ilot;
	private boolean aMisSaCroix;
	private int nombreCroix;
	private Date dateCroissants;
	private String login;
	private String motDePasse;
	private boolean estAdmin;
	
	public Personne(int id, String trigramme, String nom, String prenom, Ilot ilot, boolean aMisSaCroix,
			int nombreCroix, Date dateCroissants, String login, String motDePasse, boolean estAdmin) {
		super();
		this.id = id;
		this.trigramme = trigramme;
		this.nom = nom;
		this.prenom = prenom;
		this.ilot = ilot;
		this.aMisSaCroix = aMisSaCroix;
		this.nombreCroix = nombreCroix;
		this.dateCroissants = dateCroissants;
		this.login = login;
		this.motDePasse = motDePasse;
		this.estAdmin = estAdmin;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isEstAdmin() {
		return estAdmin;
	}

	public void setEstAdmin(boolean estAdmin) {
		this.estAdmin = estAdmin;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrigramme() {
		return trigramme;
	}
	public void setTrigramme(String trigramme) {
		this.trigramme = trigramme;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Ilot getIlot() {
		return ilot;
	}
	public void setIlot(Ilot ilot) {
		this.ilot = ilot;
	}
	public boolean isaMisSaCroix() {
		return aMisSaCroix;
	}
	public void setaMisSaCroix(boolean aMisSaCroix) {
		this.aMisSaCroix = aMisSaCroix;
	}
	public int getNombreCroix() {
		return nombreCroix;
	}
	public void setNombreCroix(int nombreCroix) {
		this.nombreCroix = nombreCroix;
	}
	public Date getDateCroissants() {
		return dateCroissants;
	}
	public void setDateCroissants(Date dateCroissants) {
		this.dateCroissants = dateCroissants;
	}

	public String getDateCroissantsPourIHM() throws ParseException {
		if (dateCroissants == null)
			return null;
		String date = new SimpleDateFormat("dd/MM/yyyy").format(dateCroissants);
		return date;
	}
	
	@Override
	public String getName() {
		return login;
	}
}

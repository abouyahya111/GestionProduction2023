package dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="EMPLOYE_BLOQUE")
@NamedQuery(name="EmployeBloque.findAll", query="SELECT e FROM EmployeBloque e")
public class EmployeBloque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String matricule;

	private String nom;
	
	private String adresse;
	
	@Column(name="NUM_TEL")
	private String numTel;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@Column(name="DATE_NAISSANCE")
	private Date dateNaissance;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;
	

	public EmployeBloque() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Utilisateur getUtilCreation() {
		return utilCreation;
	}

	public void setUtilCreation(Utilisateur utilCreation) {
		this.utilCreation = utilCreation;
	}


	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
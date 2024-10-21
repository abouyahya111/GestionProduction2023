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


/**
 * The persistent class for the fournisseur database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT f FROM Client f")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	private String adresse;
	
	private String code ;

	private String email;

	private String nom;

	private String numTel;
	
	@Column(name="CODE_DEPOT")
	private String codeDepot;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;

	@JoinColumn(name="TYPE_CLIENT")
	private String typeClient;

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Utilisateur getUtilCreation() {
		return utilCreation;
	}

	public void setUtilCreation(Utilisateur utilCreation) {
		this.utilCreation = utilCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}



	

}
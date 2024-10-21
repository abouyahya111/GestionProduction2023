package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@NamedQuery(name="EmployeRepos.findAll", query="SELECT e FROM EmployeRepos e")
public class EmployeRepos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;



	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_SITUATION")
	private Date dateSituation;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;

	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	private String equipe;
	
	
	
	public EmployeRepos() {
	}

	public int getId() {
		return this.id;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
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

	public Date getDateSituation() {
		return dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	

	
}
package dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the detail_production database table.
 * 
 */

@Entity
@Table(name="COMPTEUR_ABSENCE_EMPLOYE")
@NamedQuery(name="CompteurAbsenceEmploye.findAll", query="SELECT d FROM CompteurAbsenceEmploye d")
public class CompteurAbsenceEmploye implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name="CODE")
	private String code;
	
	@JoinColumn(name="DATE_ABSENCE")
	@Temporal(TemporalType.DATE)
	private Date dateAbsence;
	
	@ManyToOne
	@JoinColumn(name="ID_EMPLOYE")
	private Employe employe;
	
	@JoinColumn(name="COMPTEUR")
	private int compteur;
	
	


	public CompteurAbsenceEmploye() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	

}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="ManqueDechetFournisseur.findAll", query="SELECT f FROM ManqueDechetFournisseur f")
public class ManqueDechetFournisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="NUM_OF")
	private String numOF;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateDechet;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation ;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification ;
	@Column(name="ETAT")
	private String etat;
	
	@Column(name="TYPE")
	private String type;

	//bi-directional many-to-one association to RipFournisseur
	@OneToMany(cascade = CascadeType.ALL, mappedBy="manquedechetfournisseur" )
	private List<DetailManqueDechetFournisseur> detailManqueDechetFournisseur=new ArrayList<>();




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getNumOF() {
		return numOF;
	}




	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}




	public Date getDateDechet() {
		return dateDechet;
	}




	public void setDateDechet(Date dateDechet) {
		this.dateDechet = dateDechet;
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







	public List<DetailManqueDechetFournisseur> getDetailManqueDechetFournisseur() {
		return detailManqueDechetFournisseur;
	}




	public void setDetailManqueDechetFournisseur(List<DetailManqueDechetFournisseur> detailManqueDechetFournisseur) {
		this.detailManqueDechetFournisseur = detailManqueDechetFournisseur;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getEtat() {
		return etat;
	}




	public void setEtat(String etat) {
		this.etat = etat;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}

	

	
}
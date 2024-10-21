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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="COMPTEUR_PRODUCTION")
@NamedQuery(name="CompteurProduction.findAll", query="SELECT d FROM CompteurProduction d")
public class CompteurProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;


	//bi-directional many-to-one association to Production

	@JoinColumn(name="DATE_PROD")
	@Temporal(TemporalType.DATE)
	private Date dateProd;
	
	@JoinColumn(name="PERIODE")
	private String periode;
	
	@JoinColumn(name="COMPTEUR")
	private int compteur;


	public CompteurProduction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateProd() {
		return dateProd;
	}

	public void setDateProd(Date dateProd) {
		this.dateProd = dateProd;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	
	

}
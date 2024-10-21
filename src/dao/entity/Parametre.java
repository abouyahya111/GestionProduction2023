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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="Parametre")
@NamedQuery(name="Parametre.findAll", query="SELECT e FROM Parametre e")
public class Parametre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE")
	private String code;

	@Column(name="LIBELLE")
	private String  libelle;
	
	@Column(name="VALEUR")
	private BigDecimal valeur;
	
	@Temporal(TemporalType.DATE)
	private Date date;	

	public Parametre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public BigDecimal getValeur() {
		return valeur;
	}

	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
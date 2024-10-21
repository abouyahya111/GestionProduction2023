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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="PrimeAnciennte.findAll", query="SELECT f FROM PrimeAnciennte f")
public class PrimeAnciennte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="TRANCHE")
	private String tranche;
	
	@Column(name="ANNEE_MIN")
	private BigDecimal anneMin ;
	
	@Column(name="ANNEE_MAX")
	private BigDecimal anneMax ;
	
	@Column(name="TAUX")
	private BigDecimal taux ;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_prime")
	private Date datePrime;
	
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTranche() {
		return tranche;
	}

	public void setTranche(String tranche) {
		this.tranche = tranche;
	}

	public BigDecimal getAnneMin() {
		return anneMin;
	}

	public void setAnneMin(BigDecimal anneMin) {
		this.anneMin = anneMin;
	}

	public BigDecimal getAnneMax() {
		return anneMax;
	}

	public void setAnneMax(BigDecimal anneMax) {
		this.anneMax = anneMax;
	}

	public BigDecimal getTaux() {
		return taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	public Date getDatePrime() {
		return datePrime;
	}

	public void setDatePrime(Date datePrime) {
		this.datePrime = datePrime;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	 
	
	

}
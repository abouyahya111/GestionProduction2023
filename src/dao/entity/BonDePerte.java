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
import javax.persistence.Transient;


/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="BonDePerte.findAll", query="SELECT f FROM BonDePerte f")
public class BonDePerte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	

	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;
	
	
	@ManyToOne
	@JoinColumn(name="id_Magasin")
	private Magasin magasin;
	
	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
	
	
	@ManyToOne
	@JoinColumn( name="id_fournisseurMP")
	private FournisseurMP fournisseurMP;
	
	private BigDecimal quantite;
	private BigDecimal prix;
	private BigDecimal montant;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateoperation() {
		return dateoperation;
	}
	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public Magasin getMagasin() {
		return magasin;
	}
	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
	public MatierePremier getMatierePremier() {
		return matierePremier;
	}
	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}
	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}
	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}
	public BigDecimal getQuantite() {
		return quantite;
	}
	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



	

}
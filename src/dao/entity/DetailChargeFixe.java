package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;




/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="Detail_Charge_Fixe")
@NamedQuery(name="DetailChargeFixe.findAll", query="SELECT p FROM DetailChargeFixe p")
public class DetailChargeFixe implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;

	@ManyToOne
	@JoinColumn(name="id_magasin")
	private Magasin magasin;
	
	
	@Column(name="Quantite")
	private BigDecimal quantite;
	
	@Column(name="Prix_Unitaire")
	private BigDecimal PrixUnitaire;
	
	@Column(name="MONTANT")
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn(name="id_charge_fixe")
	private ChargeFixe ChargeFixe;
	
	
	

	public DetailChargeFixe() {
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public MatierePremier getMatierePremier() {
		return matierePremier;
	}




	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
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




	public BigDecimal getQuantite() {
		return quantite;
	}




	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}




	public BigDecimal getPrixUnitaire() {
		return PrixUnitaire;
	}




	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		PrixUnitaire = prixUnitaire;
	}




	public BigDecimal getMontant() {
		return montant;
	}




	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}




	public ChargeFixe getChargeFixe() {
		return ChargeFixe;
	}




	public void setChargeFixe(ChargeFixe chargeFixe) {
		ChargeFixe = chargeFixe;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	

}
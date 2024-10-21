package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;




/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="detail_chargevariable")
@NamedQuery(name=" DetailChargeVariable.findAll", query="SELECT p FROM  DetailChargeVariable p")
public class  DetailChargeVariable implements Serializable {
	

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
	@JoinColumn(name="id_charge_variable")
	private ChargeVariable ChargeVariable;
	

	public  DetailChargeVariable() {
		
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


	public ChargeVariable getChargeVariable() {
		return ChargeVariable;
	}


	public void setChargeVariable(ChargeVariable chargeVariable) {
		ChargeVariable = chargeVariable;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
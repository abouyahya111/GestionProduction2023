package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;




/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="detail_frais_depot")
@NamedQuery(name="DetailFraisDepot.findAll", query="SELECT p FROM DetailFraisDepot p")
public class DetailFraisDepot implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@Column(name="QUANTITE")
	private BigDecimal quantite;
	
	@Column(name="prix_unitaire")
	private BigDecimal prix;
	
	@Column(name="MONTANT")
	private BigDecimal montant;
	
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="id_Charge_Depot")
	private FraisDepot FraisDepot;
	
	
	

	public DetailFraisDepot() {
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



	public FraisDepot getFraisDepot() {
		return FraisDepot;
	}



	public void setFraisDepot(FraisDepot fraisDepot) {
		FraisDepot = fraisDepot;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	
	

	

}
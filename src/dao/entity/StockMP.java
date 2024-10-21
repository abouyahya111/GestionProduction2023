package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@Table(name="stock")
@NamedQuery(name="StockMP.findAll", query="SELECT s FROM StockMP s")
public class StockMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="quantite_commande")
	private BigDecimal quantiteCommande;

	private BigDecimal stock;

	@Column(name="stock_min")
	private BigDecimal stockMin;
	
	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	//bi-directional many-to-one association to MatierePremier
		@ManyToOne
		@JoinColumn(name="id_magasin")
		private Magasin magasin;
		
		@ManyToOne
		@JoinColumn(name="id_fournisseurMP")
		private FournisseurMP fournisseurMP;	
		

	public StockMP() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getQuantiteCommande() {
		return this.quantiteCommande;
	}

	public void setQuantiteCommande(BigDecimal quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getStockMin() {
		return this.stockMin;
	}

	public void setStockMin(BigDecimal stockMin) {
		this.stockMin = stockMin;
	}

	public MatierePremier getMatierePremier() {
		return this.matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}
	
	
	
	

}
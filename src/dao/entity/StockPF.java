package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity

@NamedQuery(name="StockPF.findAll", query="SELECT s FROM StockPF s")
public class StockPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;


	private BigDecimal stock;

	@Column(name="stock_min")
	private BigDecimal stockMin;
	
	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_Article")
	private Articles articles;
	
	//bi-directional many-to-one association to MatierePremier
		@ManyToOne
		@JoinColumn(name="id_magasin")
		private Magasin magasin;

	public StockPF() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

}
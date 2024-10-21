package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@NamedQuery(name="PourcentageproductionArticle.findAll", query="SELECT p FROM PourcentageproductionArticle p")
public class PourcentageProductionArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;



	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles articles;

	
	@Column(name="QUANTITE")
	private BigDecimal quantite;
	

	@Column(name="POURCENTAGE_QUANTITE")
	private BigDecimal pourcentageQuantite;

	public PourcentageProductionArticle() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPourcentageQuantite() {
		return pourcentageQuantite;
	}

	public void setPourcentageQuantite(BigDecimal pourcentageQuantite) {
		this.pourcentageQuantite = pourcentageQuantite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
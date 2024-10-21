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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="SituationProductionTotalParArticlePF.findAll", query="SELECT f FROM SituationProductionTotalParArticlePF f")
public class SituationProductionTotalParArticlePFClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	


	
	@Column(name="ARTICLE")
	private Articles articles ;
	
	@Column(name="PRODUCTION")
	private int idProduction ;
	
	@Column(name="CATEGORIE")
	private CategorieMp categorie ;
	
	@Column(name="QuantiteConsomme")
	private BigDecimal quantiteConsomme ;
	
	@Column(name="POURCENTAGE")
	private BigDecimal pourcentage ;

	public Articles getArticles() {
		return articles;
	}
	
	@Column(name="POURCENTAGE_categorie")
	private BigDecimal pourcentage_categorie ;
	
	@Column(name="POURCENTAGE_article")
	private BigDecimal pourcentage_article;

	public int getIdProduction() {
		return idProduction;
	}

	public void setIdProduction(int idProduction) {
		this.idProduction = idProduction;
	}

	public BigDecimal getQuantiteConsomme() {
		return quantiteConsomme;
	}

	public void setQuantiteConsomme(BigDecimal quantiteConsomme) {
		this.quantiteConsomme = quantiteConsomme;
	}

	public BigDecimal getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(BigDecimal pourcentage) {
		this.pourcentage = pourcentage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public CategorieMp getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieMp categorie) {
		this.categorie = categorie;
	}

	public BigDecimal getPourcentage_categorie() {
		return pourcentage_categorie;
	}

	public void setPourcentage_categorie(BigDecimal pourcentage_categorie) {
		this.pourcentage_categorie = pourcentage_categorie;
	}

	public BigDecimal getPourcentage_article() {
		return pourcentage_article;
	}

	public void setPourcentage_article(BigDecimal pourcentage_article) {
		this.pourcentage_article = pourcentage_article;
	}

	 
	
	

}
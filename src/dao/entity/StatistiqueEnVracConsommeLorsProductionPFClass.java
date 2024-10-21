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
@NamedQuery(name="StatistiqueEnVracConsommeLorsProductionPF.findAll", query="SELECT f FROM StatistiqueEnVracConsommeLorsProductionPF f")
public class StatistiqueEnVracConsommeLorsProductionPFClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	


	
	@Column(name="ARTICLE")
	private Articles articles ;
	
	@Column(name="MATIEREPREMIERE")
	private MatierePremier matierePremier ;
	
	@Column(name="CATEGORIE")
	private String categorie ;
	
	@Column(name="QuantiteConsomme")
	private BigDecimal quantiteConsomme ;
	
	@Column(name="NOMBRE_ENVRAC_CONSOMME")
	private  Integer nombreEnvracConsomme ;
	
	@Column(name="POURCENTAGE")
	private BigDecimal pourcentage ;
	
	@Column(name="POURCENTAGE_categorie")
	private BigDecimal pourcentage_categorie ;
	
	@Column(name="POURCENTAGE_mp")
	private BigDecimal pourcentage_mp;

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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

	public Integer getNombreEnvracConsomme() {
		return nombreEnvracConsomme;
	}

	public void setNombreEnvracConsomme(Integer nombreEnvracConsomme) {
		this.nombreEnvracConsomme = nombreEnvracConsomme;
	}

	public BigDecimal getPourcentage_categorie() {
		return pourcentage_categorie;
	}

	public void setPourcentage_categorie(BigDecimal pourcentage_categorie) {
		this.pourcentage_categorie = pourcentage_categorie;
	}

	public BigDecimal getPourcentage_mp() {
		return pourcentage_mp;
	}

	public void setPourcentage_mp(BigDecimal pourcentage_mp) {
		this.pourcentage_mp = pourcentage_mp;
	}

	

	

}
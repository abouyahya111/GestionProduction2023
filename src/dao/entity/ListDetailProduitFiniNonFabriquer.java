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
@NamedQuery(name="listDetailproduitfininonfabriquer.findAll", query="SELECT f FROM listDetailproduitfininonfabriquer f")
public class ListDetailProduitFiniNonFabriquer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	MatierePremier matierePremier;
	BigDecimal Quantite ;
	BigDecimal prix;
	BigDecimal Montant ;
	Articles articles;
	
	
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
		return Quantite;
	}
	public void setQuantite(BigDecimal quantite) {
		Quantite = quantite;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	public BigDecimal getMontant() {
		return Montant;
	}
	public void setMontant(BigDecimal montant) {
		Montant = montant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Articles getArticles() {
		return articles;
	}
	public void setArticles(Articles articles) {
		this.articles = articles;
	}
	
	
	
	
	
}
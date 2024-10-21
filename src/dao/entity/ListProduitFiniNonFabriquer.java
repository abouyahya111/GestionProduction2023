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
@NamedQuery(name="listproduitfininonfabriquer.findAll", query="SELECT f FROM listproduitfininonfabriquer f")
public class ListProduitFiniNonFabriquer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	Articles articles;
	BigDecimal quantiteBox;
	BigDecimal quantiteCarton;
	BigDecimal montantBox;
	BigDecimal montantCarton;
	BigDecimal montantTotal;
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
	public BigDecimal getQuantiteBox() {
		return quantiteBox;
	}
	public void setQuantiteBox(BigDecimal quantiteBox) {
		this.quantiteBox = quantiteBox;
	}
	public BigDecimal getQuantiteCarton() {
		return quantiteCarton;
	}
	public void setQuantiteCarton(BigDecimal quantiteCarton) {
		this.quantiteCarton = quantiteCarton;
	}
	public BigDecimal getMontantBox() {
		return montantBox;
	}
	public void setMontantBox(BigDecimal montantBox) {
		this.montantBox = montantBox;
	}
	public BigDecimal getMontantCarton() {
		return montantCarton;
	}
	public void setMontantCarton(BigDecimal montantCarton) {
		this.montantCarton = montantCarton;
	}
	public BigDecimal getMontantTotal() {
		return montantTotal;
	}
	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
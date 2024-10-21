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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */

public class EtatCoutProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	 private Date dateProduction ;
	 private	String numOF;
	 private	Articles article;
	 
	 private Production production;
	 private BigDecimal coutMP;
	 private BigDecimal coutEmployeGenerique;
	 private BigDecimal coutEmployeProduction;
	 private BigDecimal coutEmployeEmballage;
	 private BigDecimal total;
	 private BigDecimal quantiteRealiser;
	 private BigDecimal cout;
	public Date getDateProduction() {
		return dateProduction;
	}
	public void setDateProduction(Date dateProduction) {
		this.dateProduction = dateProduction;
	}
	public String getNumOF() {
		return numOF;
	}
	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}
	public Articles getArticle() {
		return article;
	}
	public void setArticle(Articles article) {
		this.article = article;
	}
	public BigDecimal getCoutMP() {
		return coutMP;
	}
	public void setCoutMP(BigDecimal coutMP) {
		this.coutMP = coutMP;
	}
	public BigDecimal getCoutEmployeGenerique() {
		return coutEmployeGenerique;
	}
	public void setCoutEmployeGenerique(BigDecimal coutEmployeGenerique) {
		this.coutEmployeGenerique = coutEmployeGenerique;
	}
	public BigDecimal getCoutEmployeProduction() {
		return coutEmployeProduction;
	}
	public void setCoutEmployeProduction(BigDecimal coutEmployeProduction) {
		this.coutEmployeProduction = coutEmployeProduction;
	}
	public BigDecimal getCoutEmployeEmballage() {
		return coutEmployeEmballage;
	}
	public void setCoutEmployeEmballage(BigDecimal coutEmployeEmballage) {
		this.coutEmployeEmballage = coutEmployeEmballage;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getQuantiteRealiser() {
		return quantiteRealiser;
	}
	public void setQuantiteRealiser(BigDecimal quantiteRealiser) {
		this.quantiteRealiser = quantiteRealiser;
	}
	public BigDecimal getCout() {
		return cout;
	}
	public void setCout(BigDecimal cout) {
		this.cout = cout;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Production getProduction() {
		return production;
	}
	public void setProduction(Production production) {
		this.production = production;
	}
	 
	
	
	 
}
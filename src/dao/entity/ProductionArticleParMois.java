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
@NamedQuery(name="Articles.findAll", query="SELECT f FROM Articles f")
public class ProductionArticleParMois implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@Column(name="matierpremier")
	private MatierePremier mp;
	
	@Column(name="article")
	private Articles article;
	
	@Column(name="Quantiet1")
	private BigDecimal qtteMois1 ;
	@Column(name="Quantiet2")
	private BigDecimal qtteMois2 ;
	
	@Column(name="Quantiet3")
	private BigDecimal qtteMois3 ;
	@Column(name="Quantiet4")
	private BigDecimal qtteMois4 ;
	@Column(name="Quantiet5")
	private BigDecimal qtteMois5 ;
	
	@Column(name="Quantiet6")
	private BigDecimal qtteMois6 ;
	
	@Column(name="Quantiet7")
	private BigDecimal qtteMois7 ;
	@Column(name="Quantiet8")
	private BigDecimal qtteMois8 ;
	@Column(name="Quantiet9")
	private BigDecimal qtteMois9 ;
	@Column(name="Quantiet10")
	private BigDecimal qtteMois10 ;
	@Column(name="Quantiet11")
	private BigDecimal qtteMois11 ;
	
	@Column(name="Quantiet12")
	private BigDecimal qtteMois12 ;
	
	@Column(name="QuantiteTotal")
	private BigDecimal qtteTotal ;
	
	@Column(name="initial")
	private BigDecimal qtteInitial ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}








	public BigDecimal getQtteTotal() {
		return qtteTotal;
	}

	public void setQtteTotal(BigDecimal qtteTotal) {
		this.qtteTotal = qtteTotal;
	}

	public BigDecimal getQtteInitial() {
		return qtteInitial;
	}

	public void setQtteInitial(BigDecimal qtteInitial) {
		this.qtteInitial = qtteInitial;
	}

	public MatierePremier getMp() {
		return mp;
	}

	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}

	public BigDecimal getQtteMois1() {
		return qtteMois1;
	}

	public void setQtteMois1(BigDecimal qtteMois1) {
		this.qtteMois1 = qtteMois1;
	}

	public BigDecimal getQtteMois2() {
		return qtteMois2;
	}

	public void setQtteMois2(BigDecimal qtteMois2) {
		this.qtteMois2 = qtteMois2;
	}

	public BigDecimal getQtteMois3() {
		return qtteMois3;
	}

	public void setQtteMois3(BigDecimal qtteMois3) {
		this.qtteMois3 = qtteMois3;
	}

	public BigDecimal getQtteMois4() {
		return qtteMois4;
	}

	public void setQtteMois4(BigDecimal qtteMois4) {
		this.qtteMois4 = qtteMois4;
	}

	public BigDecimal getQtteMois5() {
		return qtteMois5;
	}

	public void setQtteMois5(BigDecimal qtteMois5) {
		this.qtteMois5 = qtteMois5;
	}

	public BigDecimal getQtteMois6() {
		return qtteMois6;
	}

	public void setQtteMois6(BigDecimal qtteMois6) {
		this.qtteMois6 = qtteMois6;
	}

	public BigDecimal getQtteMois7() {
		return qtteMois7;
	}

	public void setQtteMois7(BigDecimal qtteMois7) {
		this.qtteMois7 = qtteMois7;
	}

	public BigDecimal getQtteMois8() {
		return qtteMois8;
	}

	public void setQtteMois8(BigDecimal qtteMois8) {
		this.qtteMois8 = qtteMois8;
	}

	public BigDecimal getQtteMois9() {
		return qtteMois9;
	}

	public void setQtteMois9(BigDecimal qtteMois9) {
		this.qtteMois9 = qtteMois9;
	}

	public BigDecimal getQtteMois10() {
		return qtteMois10;
	}

	public void setQtteMois10(BigDecimal qtteMois10) {
		this.qtteMois10 = qtteMois10;
	}

	public BigDecimal getQtteMois11() {
		return qtteMois11;
	}

	public void setQtteMois11(BigDecimal qtteMois11) {
		this.qtteMois11 = qtteMois11;
	}

	public BigDecimal getQtteMois12() {
		return qtteMois12;
	}

	public void setQtteMois12(BigDecimal qtteMois12) {
		this.qtteMois12 = qtteMois12;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}



}
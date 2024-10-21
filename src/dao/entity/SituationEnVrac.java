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
@NamedQuery(name="SituationEnVrac.findAll", query="SELECT f FROM SituationEnVrac f")
public class SituationEnVrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@Column(name="MP")
	private MatierePremier mp ;
	
	@Column(name="MATIERE_PREMIERE")
	private String matierepremiere ;
	
	@Column(name="CODE_MATIERE_PREMIERE")
	private String codematierepremiere ;
	
	@Column(name="ENATTENT")
	private BigDecimal enattent ;
	
	@Column(name="RECEPTION_ENATTENT")
	private BigDecimal receptionenattent ;

	@Column(name="TANTAN")
	private BigDecimal tantan ;
	
	@Column(name="LAAYOUN")
	private BigDecimal laayoun ;
	
	@Column(name="ESSMARA")
	private BigDecimal essmara ;
	
	@Column(name="AGADIR")
	private BigDecimal agadir ;
	
	@Column(name="TOTAL")
	private BigDecimal total ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getMatierepremiere() {
		return matierepremiere;
	}

	public void setMatierepremiere(String matierepremiere) {
		this.matierepremiere = matierepremiere;
	}

	public String getCodematierepremiere() {
		return codematierepremiere;
	}

	public void setCodematierepremiere(String codematierepremiere) {
		this.codematierepremiere = codematierepremiere;
	}

	public BigDecimal getEnattent() {
		return enattent;
	}

	public void setEnattent(BigDecimal enattent) {
		this.enattent = enattent;
	}

	public BigDecimal getTantan() {
		return tantan;
	}

	public void setTantan(BigDecimal tantan) {
		this.tantan = tantan;
	}

	public BigDecimal getLaayoun() {
		return laayoun;
	}

	public void setLaayoun(BigDecimal laayoun) {
		this.laayoun = laayoun;
	}

	public BigDecimal getEssmara() {
		return essmara;
	}

	public void setEssmara(BigDecimal essmara) {
		this.essmara = essmara;
	}

	public BigDecimal getAgadir() {
		return agadir;
	}

	public void setAgadir(BigDecimal agadir) {
		this.agadir = agadir;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MatierePremier getMp() {
		return mp;
	}

	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}

	public BigDecimal getReceptionenattent() {
		return receptionenattent;
	}

	public void setReceptionenattent(BigDecimal receptionenattent) {
		this.receptionenattent = receptionenattent;
	}
	
	



}
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="SituationPFParAnne.findAll", query="SELECT f FROM SituationPFParAnne f")
public class SituationPFParAnneeClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="annedu")
	private Integer annedu ;
	
	@Column(name="anneAu")
	private Integer anneAu ;
	
	@Column(name="magasin")
	private Magasin magasin ;
	
	@Column(name="MoisAnneDu")
	private Integer moisAnneDu ;
	
	@Column(name="MoisAnneAu")
	private String moisAnneAu ;
	
	@Column(name="quantiteFabriquerAnneDu")
	private BigDecimal quantiteFabriquerAnneDu ;
	
	@Column(name="quantiteFabriquerAnneAu")
	private BigDecimal quantiteFabriquerAnneAu ;
	
	@Column(name="pourcentageAnneDu")
	private BigDecimal pourcentageAnneDu ;
	
	@Column(name="pourcentageAnneAu")
	private BigDecimal pourcentageAnneAu ;
	
	@Column(name="difference")
	private BigDecimal difference ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	

	public Integer getAnnedu() {
		return annedu;
	}

	public void setAnnedu(Integer annedu) {
		this.annedu = annedu;
	}

	public Integer getAnneAu() {
		return anneAu;
	}

	public void setAnneAu(Integer anneAu) {
		this.anneAu = anneAu;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	 

	public Integer getMoisAnneDu() {
		return moisAnneDu;
	}

	public void setMoisAnneDu(Integer moisAnneDu) {
		this.moisAnneDu = moisAnneDu;
	}

	public String getMoisAnneAu() {
		return moisAnneAu;
	}

	public void setMoisAnneAu(String moisAnneAu) {
		this.moisAnneAu = moisAnneAu;
	}

	public BigDecimal getQuantiteFabriquerAnneDu() {
		return quantiteFabriquerAnneDu;
	}

	public void setQuantiteFabriquerAnneDu(BigDecimal quantiteFabriquerAnneDu) {
		this.quantiteFabriquerAnneDu = quantiteFabriquerAnneDu;
	}

	public BigDecimal getQuantiteFabriquerAnneAu() {
		return quantiteFabriquerAnneAu;
	}

	public void setQuantiteFabriquerAnneAu(BigDecimal quantiteFabriquerAnneAu) {
		this.quantiteFabriquerAnneAu = quantiteFabriquerAnneAu;
	}

	public BigDecimal getPourcentageAnneDu() {
		return pourcentageAnneDu;
	}

	public void setPourcentageAnneDu(BigDecimal pourcentageAnneDu) {
		this.pourcentageAnneDu = pourcentageAnneDu;
	}

	public BigDecimal getPourcentageAnneAu() {
		return pourcentageAnneAu;
	}

	public void setPourcentageAnneAu(BigDecimal pourcentageAnneAu) {
		this.pourcentageAnneAu = pourcentageAnneAu;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	
	
}
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
@NamedQuery(name="StatistiqueEnVracConsomme.findAll", query="SELECT f FROM StatistiqueEnVracConsomme f")
public class StatistiqueEnVracConsommeClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	


	
	@Column(name="MP")
	private MatierePremier mp ;
	
	
	@Column(name="QuantiteConsomme")
	private BigDecimal quantiteConsomme ;
	
	@Column(name="QuantiteConsommeToal")
	private BigDecimal quantiteConsommeTotal ;
	
	@Column(name="QuantitePlus")
	private BigDecimal quantitePlus ;
	
	@Column(name="QuantiteOffre")
	private BigDecimal quantiteOffre ;
	
	@Column(name="QuantiteManque")
	private BigDecimal quantiteManque ;
	
	@Column(name="QuantiteDechet")
	private BigDecimal quantiteDechet ;
	
	@Column(name="QuantiteDechetFournisseur")
	private BigDecimal quantiteDechetFournisseur ;
	
	@Column(name="POURCENTAGE")
	private BigDecimal pourcentage ;
	
	@Column(name="CATEGORIE")
	private String categorie ;
	
	@Column(name="POURCENTAGE_categorie")
	private BigDecimal pourcentage_categorie ;
	
	@Column(name="POURCENTAGE_mp")
	private BigDecimal pourcentage_mp;
	
	@Column(name="montant2022")
	private BigDecimal montant2022;
	
	@Column(name="montant2023")
	private BigDecimal montant2023;
	
	@Column(name="difference")
	private BigDecimal difference;

	public MatierePremier getMp() {
		return mp;
	}

	public void setMp(MatierePremier mp) {
		this.mp = mp;
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

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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

	public BigDecimal getMontant2022() {
		return montant2022;
	}

	public void setMontant2022(BigDecimal montant2022) {
		this.montant2022 = montant2022;
	}

	public BigDecimal getMontant2023() {
		return montant2023;
	}

	public void setMontant2023(BigDecimal montant2023) {
		this.montant2023 = montant2023;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getQuantiteConsommeTotal() {
		return quantiteConsommeTotal;
	}

	public void setQuantiteConsommeTotal(BigDecimal quantiteConsommeTotal) {
		this.quantiteConsommeTotal = quantiteConsommeTotal;
	}

	public BigDecimal getQuantitePlus() {
		return quantitePlus;
	}

	public void setQuantitePlus(BigDecimal quantitePlus) {
		this.quantitePlus = quantitePlus;
	}

	public BigDecimal getQuantiteOffre() {
		return quantiteOffre;
	}

	public void setQuantiteOffre(BigDecimal quantiteOffre) {
		this.quantiteOffre = quantiteOffre;
	}

	public BigDecimal getQuantiteManque() {
		return quantiteManque;
	}

	public void setQuantiteManque(BigDecimal quantiteManque) {
		this.quantiteManque = quantiteManque;
	}

	public BigDecimal getQuantiteDechet() {
		return quantiteDechet;
	}

	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		this.quantiteDechet = quantiteDechet;
	}

	public BigDecimal getQuantiteDechetFournisseur() {
		return quantiteDechetFournisseur;
	}

	public void setQuantiteDechetFournisseur(BigDecimal quantiteDechetFournisseur) {
		this.quantiteDechetFournisseur = quantiteDechetFournisseur;
	}

	


}
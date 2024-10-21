package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="Cout_ProdMP")
@NamedQuery(name="CoutProdMP.findAll", query="SELECT d FROM CoutProdMP d")
public class CoutProdMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal quantite;
	
	@Column(name="prix_unitaire")
	private  BigDecimal prixUnitaire;
	
	@Column(name="prix_total")
	private  BigDecimal prixTotal;
	
	@Column(name="COUT_DECHET")
	private  BigDecimal coutDechet;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn( name="id_production")
	private ProductionMP prodcutionCM;
	
	
	@Column(name="quantite_charge_supp")
	private  BigDecimal quantChargeSupp=BigDecimal.ZERO;
	
	@Column(name="quantite_consomme")
	private  BigDecimal quantConsomme;
	
	@Column(name="quantite_existante")
	private  BigDecimal quantExistante;
	
	@Column(name="quantite_CHARGE")
	private  BigDecimal quantCharge;
	
	@Column(name="quantite_ESTIME")
	private  BigDecimal quantEstime;
	
	@Column(name="quantite_dechet")
	private  BigDecimal quantDechet=BigDecimal.ZERO;
	
	@Column(name="QUANTITE_RESTE")
	private  BigDecimal quantReste=BigDecimal.ZERO;
	
	
	
	@Column(name="QUANTITE_DECHET_FOURNISSEUR")
	private  BigDecimal quantDechetFournisseur=BigDecimal.ZERO;
	
	@Column(name="COUT_DECHET_FOURNISSEUR")
	private  BigDecimal coutDechetFournisseur;
	
	@Column(name="QUANTITE_OFFRE")
	private  BigDecimal quantiteOffre=BigDecimal.ZERO;
	
	@Column(name="COUT_OFFRE")
	private  BigDecimal coutOffre;
	
	@Column(name="QUANTITE_MANQUANTE")
	private  BigDecimal quantiteManquante=BigDecimal.ZERO;
	
	@Column(name="COUT_MANQUANTE")
	private  BigDecimal coutManquante;
	
	@Column(name="CODE_FOURNISSEUR")
	private String codeFournisseur;
	
	@Column(name="MANQUANE_FR_PLUS")
	private  BigDecimal quantiteManquanteFrPlus=BigDecimal.ZERO;


	public CoutProdMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}
	

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}

	public BigDecimal getQuantChargeSupp() {
		return quantChargeSupp;
	}

	public void setQuantChargeSupp(BigDecimal quantChargeSupp) {
		this.quantChargeSupp = quantChargeSupp;
	}

	public BigDecimal getQuantConsomme() {
		return quantConsomme;
	}

	public void setQuantConsomme(BigDecimal quantConsomme) {
		this.quantConsomme = quantConsomme;
	}

	public BigDecimal getQuantDechet() {
		return quantDechet;
	}

	public void setQuantDechet(BigDecimal quantDechet) {
		this.quantDechet = quantDechet;
	}

	public BigDecimal getQuantExistante() {
		return quantExistante;
	}

	public void setQuantExistante(BigDecimal quantExistante) {
		this.quantExistante = quantExistante;
	}

	public ProductionMP getProdcutionCM() {
		return prodcutionCM;
	}

	public void setProdcutionCM(ProductionMP prodcutionCM) {
		this.prodcutionCM = prodcutionCM;
	}

	public BigDecimal getQuantCharge() {
		return quantCharge;
	}

	public void setQuantCharge(BigDecimal quantCharge) {
		this.quantCharge = quantCharge;
	}

	public BigDecimal getQuantReste() {
		return quantReste;
	}

	public void setQuantReste(BigDecimal quantReste) {
		this.quantReste = quantReste;
	}

	public BigDecimal getCoutDechet() {
		return coutDechet;
	}

	public void setCoutDechet(BigDecimal coutDechet) {
		this.coutDechet = coutDechet;
	}

	public BigDecimal getQuantEstime() {
		return quantEstime;
	}

	public void setQuantEstime(BigDecimal quantEstime) {
		this.quantEstime = quantEstime;
	}

	public BigDecimal getQuantDechetFournisseur() {
		return quantDechetFournisseur;
	}

	public void setQuantDechetFournisseur(BigDecimal quantDechetFournisseur) {
		this.quantDechetFournisseur = quantDechetFournisseur;
	}

	public BigDecimal getCoutDechetFournisseur() {
		return coutDechetFournisseur;
	}

	public void setCoutDechetFournisseur(BigDecimal coutDechetFournisseur) {
		this.coutDechetFournisseur = coutDechetFournisseur;
	}

	public BigDecimal getQuantiteOffre() {
		return quantiteOffre;
	}

	public void setQuantiteOffre(BigDecimal quantiteOffre) {
		this.quantiteOffre = quantiteOffre;
	}

	public BigDecimal getCoutOffre() {
		return coutOffre;
	}

	public void setCoutOffre(BigDecimal coutOffre) {
		this.coutOffre = coutOffre;
	}

	public BigDecimal getQuantiteManquante() {
		return quantiteManquante;
	}

	public void setQuantiteManquante(BigDecimal quantiteManquante) {
		this.quantiteManquante = quantiteManquante;
	}

	public BigDecimal getCoutManquante() {
		return coutManquante;
	}

	public void setCoutManquante(BigDecimal coutManquante) {
		this.coutManquante = coutManquante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public BigDecimal getQuantiteManquanteFrPlus() {
		return quantiteManquanteFrPlus;
	}

	public void setQuantiteManquanteFrPlus(BigDecimal quantiteManquanteFrPlus) {
		this.quantiteManquanteFrPlus = quantiteManquanteFrPlus;
	}

	
	

}
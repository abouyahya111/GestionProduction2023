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
import javax.persistence.Transient;


/**
 * The persistent class for the detail_commande database table.
 * 
 */

@Entity
@Table(name="SituationManquePlusEnVrac")
@NamedQuery(name="CoutMP.findAll", query="SELECT d FROM SituationManquePlusEnVrac d")
public class SituationManquePlusEnVrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal quantite;
	
	@Column(name="prix_unitaire")
	private  BigDecimal prixUnitaire;
	
	@Column(name="prix_total")
	private  BigDecimal prixTotal=BigDecimal.ZERO;
	
	@Column(name="COUT_DECHET")
	private  BigDecimal coutDechet=BigDecimal.ZERO;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn( name="id_production")
	private Production prodcutionCM;
	
	
	@Column(name="quantite_charge_supp")
	private  BigDecimal quantChargeSupp=BigDecimal.ZERO;
	
	@Column(name="quantite_consomme")
	private  BigDecimal quantConsomme=BigDecimal.ZERO;
	
	@Column(name="quantite_existante")
	private  BigDecimal quantExistante=BigDecimal.ZERO;
	
	@Column(name="quantite_CHARGE")
	private  BigDecimal quantCharge=BigDecimal.ZERO;
	
	@Column(name="quantite_ESTIME")
	private  BigDecimal quantEstime=BigDecimal.ZERO;
	
	@Column(name="quantite_dechet")
	private  BigDecimal quantDechet=BigDecimal.ZERO;
	
	@Column(name="QUANTITE_RESTE")
	private  BigDecimal quantReste=BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP;
	
	@Column(name="QUANTITE_DECHET_FOURNISSEUR")
	private  BigDecimal quantDechetFournisseur=BigDecimal.ZERO;
	
	@Column(name="COUT_DECHET_FOURNISSEUR")
	private  BigDecimal coutDechetFournisseur=BigDecimal.ZERO;
	
	@Column(name="QUANTITE_OFFRE")
	private  BigDecimal quantiteOffre=BigDecimal.ZERO;
	
	@Column(name="TOTAL_SAISI_DECHET")
	private  BigDecimal dechetTotal=BigDecimal.ZERO;
	
	@Column(name="TOTAL_SAISI_MANQUE")
	private  BigDecimal manqueTotal=BigDecimal.ZERO;
	
	@Column(name="COUT_OFFRE")
	private  BigDecimal coutOffre=BigDecimal.ZERO;
	
	@Column(name="QUANTITE_MANQUANTE")
	private  BigDecimal quantiteManquante=BigDecimal.ZERO;
	
	@Column(name="MANQUANE_FR_PLUS")
	private  BigDecimal quantiteManquanteFrPlus=BigDecimal.ZERO;
	
	@Column(name="COUT_MANQUANTE")
	private  BigDecimal coutManquante=BigDecimal.ZERO;
	
	@Column(name="CODE_FOURNISSEUR")
	private String codeFournisseur;
	
	@Column(name="CODE_FOURNISSEURDECHET")
	private String codeFournisseurdechet;
	
	@Column(name="MOINS_PLUS")
	private String moinsPlus;
	
	@Column(name="estimation")
	private  BigDecimal estimation=BigDecimal.ZERO;
	
	@Transient
	private String nomMP;
	
	@Transient
	private BigDecimal montantPlus;
	
	@Transient
	private BigDecimal montantMoins;
	
	@Transient
	private BigDecimal montantDechetUsine;

	@Transient
	private BigDecimal montantDechetFournisseur;
	
	public SituationManquePlusEnVrac() {
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

	public Production getProdcutionCM() {
		return prodcutionCM;
	}

	public void setProdcutionCM(Production prodcutionCM) {
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
	@Transient
	public String getNomMP() {
		return nomMP;
	}
	@Transient
	public void setNomMP(String nomMP) {
		this.nomMP = nomMP;
	}

	public String getCodeFournisseurdechet() {
		return codeFournisseurdechet;
	}

	public void setCodeFournisseurdechet(String codeFournisseurdechet) {
		this.codeFournisseurdechet = codeFournisseurdechet;
	}

	public BigDecimal getDechetTotal() {
		return dechetTotal;
	}

	public void setDechetTotal(BigDecimal dechetTotal) {
		this.dechetTotal = dechetTotal;
	}

	public BigDecimal getManqueTotal() {
		return manqueTotal;
	}

	public void setManqueTotal(BigDecimal manqueTotal) {
		this.manqueTotal = manqueTotal;
	}

	public BigDecimal getMontantPlus() {
		return getQuantiteManquanteFrPlus().multiply(getPrixUnitaire());
	}

	public void setMontantPlus(BigDecimal montantPlus) {
		this.montantPlus = montantPlus;
	}

	public BigDecimal getMontantMoins() {
		return getQuantiteManquante().multiply(getPrixUnitaire());
	}

	public void setMontantMoins(BigDecimal montantMoins) {
		this.montantMoins = montantMoins;
	}

	public BigDecimal getMontantDechetUsine() {
		return getQuantDechet().multiply(getPrixUnitaire());
	}

	public void setMontantDechetUsine(BigDecimal montantDechetUsine) {
		this.montantDechetUsine = montantDechetUsine;
	}

	public BigDecimal getMontantDechetFournisseur() {
		return getQuantDechetFournisseur().multiply(getPrixUnitaire());
	}

	public void setMontantDechetFournisseur(BigDecimal montantDechetFournisseur) {
		this.montantDechetFournisseur = montantDechetFournisseur;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public BigDecimal getEstimation() {
		return estimation;
	}

	public void setEstimation(BigDecimal estimation) {
		this.estimation = estimation;
	}

	public String getMoinsPlus() {
		return moinsPlus;
	}

	public void setMoinsPlus(String moinsPlus) {
		this.moinsPlus = moinsPlus;
	}

	

	
	
	

}
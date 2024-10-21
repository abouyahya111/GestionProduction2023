package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="DETAIL_FACTURE_VENTE_MP")
@NamedQuery(name="DetailFactureVenteMP.findAll", query="SELECT d FROM DetailFactureVenteMP d")
public class DetailFactureVenteMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="PRIX_UNITAIRE")
	private BigDecimal prixUnitaire;

	@Column(name="QUANTITE")
	private BigDecimal quantite;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC;
	
	@Column(name="TVA")
	private BigDecimal tva;
	
	
	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR_MP")
	private FournisseurMP fournisseurMP;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_MAT_PREM")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_FACTURE_VENTE")
	private FactureVenteMP factureVenteMP;

	public DetailFactureVenteMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(BigDecimal montantHT) {
		this.montantHT = montantHT;
	}

	public BigDecimal getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(BigDecimal montantTVA) {
		this.montantTVA = montantTVA;
	}

	public BigDecimal getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(BigDecimal montantTTC) {
		this.montantTTC = montantTTC;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public FactureVenteMP getFactureVenteMP() {
		return factureVenteMP;
	}

	public void setFactureVenteMP(FactureVenteMP factureVenteMP) {
		this.factureVenteMP = factureVenteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public BigDecimal getTva() {
		return tva;
	}

	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}

	
	
	
}
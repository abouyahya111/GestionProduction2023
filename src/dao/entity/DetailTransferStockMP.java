package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="Detail_Transfer_StockMP")
@NamedQuery(name="DetailTransferStockMP.findAll", query="SELECT d FROM DetailTransferStockMP d")
public class DetailTransferStockMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	private BigDecimal quantite;
	
	private BigDecimal quantiteReceptionFacture;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn( name="id_Magasin_destination")
	private Magasin magasinDestination;
	
	
	@ManyToOne
	@JoinColumn( name="id_Magasin_entrer")
	private Magasin magasinEntrer;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_transfer")
	private TransferStockMP transferStockMP;
	
	@Column(name="Quantite_Dechet")
	private BigDecimal QuantiteDechet;
	
	@Column(name="Quantite_Manque")
	private BigDecimal quantiteManque;
	
	@Column(name="Quantite_Plus")
	private BigDecimal quantitePlus;
	
	@Column(name="Quantite_Offre")
	private BigDecimal QuantiteOffre;
	
	@Column(name="Stock_Source")
	private String StockSource;
	
	@Column(name="etat")
	private String etat;
	
	@Column(name="Num_BL_Reception")
	private String numBLReception;
	
	@Column(name="Quantite_ancien")
	private BigDecimal QuantiteAncien;

	
	private String action;
	private String service;
	private String commentaire;
	
	
	@Column(name="Quantite_retour")
	private BigDecimal QuantiteRetour;
	
	
	@Column(name="Quantite_existante")
	private BigDecimal QuantiteExistante;
	
	@ManyToOne
	@JoinColumn(name="id_Fournisseur" )
	private FournisseurMP fournisseur;
	
	@ManyToOne
	@JoinColumn(name="id_Fournisseu_NonTher" )
	private FournisseurMP fournisseurNonThe;
	
	@ManyToOne
	@JoinColumn(name="id_Fournisseur_adhesif" )
	private FournisseurAdhesive fournisseurAdhesive;
	

	public DetailTransferStockMP() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getQuantite() {
		return this.quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public MatierePremier getMatierePremier() {
		return this.matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public Magasin getMagasinSouce() {
		return magasinSouce;
	}

	public void setMagasinSouce(Magasin magasinSouce) {
		this.magasinSouce = magasinSouce;
	}

	public Magasin getMagasinDestination() {
		return magasinDestination;
	}

	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
	}

	public TransferStockMP getTransferStockMP() {
		return transferStockMP;
	}

	public void setTransferStockMP(TransferStockMP transferStockMP) {
		this.transferStockMP = transferStockMP;
	}

	public BigDecimal getQuantiteDechet() {
		return QuantiteDechet;
	}

	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		QuantiteDechet = quantiteDechet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantiteOffre() {
		return QuantiteOffre;
	}

	public void setQuantiteOffre(BigDecimal quantiteOffre) {
		QuantiteOffre = quantiteOffre;
	}

	public String getStockSource() {
		return StockSource;
	}

	public void setStockSource(String stockSource) {
		StockSource = stockSource;
	}

	public BigDecimal getQuantiteRetour() {
		return QuantiteRetour;
	}

	public void setQuantiteRetour(BigDecimal quantiteRetour) {
		QuantiteRetour = quantiteRetour;
	}

	public FournisseurMP getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurMP fournisseur) {
		this.fournisseur = fournisseur;
	}

	public BigDecimal getQuantiteManque() {
		return quantiteManque;
	}

	public void setQuantiteManque(BigDecimal quantiteManque) {
		this.quantiteManque = quantiteManque;
	}

	public BigDecimal getQuantiteExistante() {
		return QuantiteExistante;
	}

	public void setQuantiteExistante(BigDecimal quantiteExistante) {
		QuantiteExistante = quantiteExistante;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public BigDecimal getQuantiteAncien() {
		return QuantiteAncien;
	}

	public void setQuantiteAncien(BigDecimal quantiteAncien) {
		QuantiteAncien = quantiteAncien;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public BigDecimal getQuantitePlus() {
		return quantitePlus;
	}

	public void setQuantitePlus(BigDecimal quantitePlus) {
		this.quantitePlus = quantitePlus;
	}

	public Magasin getMagasinEntrer() {
		return magasinEntrer;
	}

	public void setMagasinEntrer(Magasin magasinEntrer) {
		this.magasinEntrer = magasinEntrer;
	}

	public String getNumBLReception() {
		return numBLReception;
	}

	public void setNumBLReception(String numBLReception) {
		this.numBLReception = numBLReception;
	}

	public FournisseurAdhesive getFournisseurAdhesive() {
		return fournisseurAdhesive;
	}

	public void setFournisseurAdhesive(FournisseurAdhesive fournisseurAdhesive) {
		this.fournisseurAdhesive = fournisseurAdhesive;
	}

	public FournisseurMP getFournisseurNonThe() {
		return fournisseurNonThe;
	}

	public void setFournisseurNonThe(FournisseurMP fournisseurNonThe) {
		this.fournisseurNonThe = fournisseurNonThe;
	}

	public BigDecimal getQuantiteReceptionFacture() {
		return quantiteReceptionFacture;
	}

	public void setQuantiteReceptionFacture(BigDecimal quantiteReceptionFacture) {
		this.quantiteReceptionFacture = quantiteReceptionFacture;
	}

	 


	

}
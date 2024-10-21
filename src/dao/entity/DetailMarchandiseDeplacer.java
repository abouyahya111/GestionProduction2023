package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detailmarchandisedeplacer")
@NamedQuery(name="detailmarchandisedeplacer.findAll", query="SELECT d FROM detailmarchandisedeplacer d")
public class DetailMarchandiseDeplacer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	 

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
 
	
	@Column(name="Quantite_entre")
	private BigDecimal QuantiteEntrer;
	
	@Column(name="Quantite_Sortie")
	private BigDecimal QuantiteSortie;
	
	@Column(name="Quantite_valider")
	private BigDecimal QuantiteValider;
	
	
	@Column(name="Quantite_manque")
	private BigDecimal quantiteManque;
	
	
	@ManyToOne
	@JoinColumn(name="id_detail_transfertMP" )
	private DetailTransferStockMP detailTransferStockMP;
	
	@ManyToOne
	@JoinColumn(name="id_Fournisseur" )
	private FournisseurMP fournisseur;
	
	@Column(name="action")
	private String action;
	
	@Column(name="service")
	private String service;
	
	@Column(name="commentaire")
	private String commentaire;
	
	@Column(name="etat")
	private String etat;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn( name="id_Magasin_destination")
	private Magasin magasinDestination;

	@Column(name="code_transfert")
	private String codeTransfert;
	
	
	@Column(name="date_entre")
	private Date dateEntrer;
	
	@Column(name="date_sortie")
	private Date dateSortie;
	
	@Column(name="transfertmp")
	private TransferStockMP transferStockMP;
	
	@Column(name="etat_manque")
	private String etatManque;
	
	@Column(name="montant")
	private BigDecimal montant;
	
	@Column(name="manqueExiste")
	private String manqueExiste;

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


	public MatierePremier getMatierePremier() {
		return matierePremier;
	}


	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}


	public BigDecimal getQuantiteEntrer() {
		return QuantiteEntrer;
	}


	public void setQuantiteEntrer(BigDecimal quantiteEntrer) {
		QuantiteEntrer = quantiteEntrer;
	}


	public BigDecimal getQuantiteSortie() {
		return QuantiteSortie;
	}


	public void setQuantiteSortie(BigDecimal quantiteSortie) {
		QuantiteSortie = quantiteSortie;
	}


	public FournisseurMP getFournisseur() {
		return fournisseur;
	}


	public void setFournisseur(FournisseurMP fournisseur) {
		this.fournisseur = fournisseur;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public DetailTransferStockMP getDetailTransferStockMP() {
		return detailTransferStockMP;
	}


	public void setDetailTransferStockMP(DetailTransferStockMP detailTransferStockMP) {
		this.detailTransferStockMP = detailTransferStockMP;
	}


	public BigDecimal getQuantiteValider() {
		return QuantiteValider;
	}


	public void setQuantiteValider(BigDecimal quantiteValider) {
		QuantiteValider = quantiteValider;
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


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
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


	public String getCodeTransfert() {
		return codeTransfert;
	}


	public void setCodeTransfert(String codeTransfert) {
		this.codeTransfert = codeTransfert;
	}


	public Date getDateEntrer() {
		return dateEntrer;
	}


	public void setDateEntrer(Date dateEntrer) {
		this.dateEntrer = dateEntrer;
	}


	public Date getDateSortie() {
		return dateSortie;
	}


	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}


	public TransferStockMP getTransferStockMP() {
		return transferStockMP;
	}


	public void setTransferStockMP(TransferStockMP transferStockMP) {
		this.transferStockMP = transferStockMP;
	}


	public String getEtatManque() {
		return etatManque;
	}


	public void setEtatManque(String etatManque) {
		this.etatManque = etatManque;
	}


	public BigDecimal getMontant() {
		return montant;
	}


	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}


	public BigDecimal getQuantiteManque() {
		return quantiteManque;
	}


	public void setQuantiteManque(BigDecimal quantiteManque) {
		this.quantiteManque = quantiteManque;
	}


	public String getManqueExiste() {
		return manqueExiste;
	}


	public void setManqueExiste(String manqueExiste) {
		this.manqueExiste = manqueExiste;
	}




}
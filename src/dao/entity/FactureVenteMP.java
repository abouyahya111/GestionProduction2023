package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name="FACTURE_VENTE_MP")
@NamedQuery(name="FactureVenteMP.findAll", query="SELECT c FROM FactureVenteMP c")
public class FactureVenteMP implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;

	@Column(name="NUM_FACTURE")
	private String numFacture;

	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA;

	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC;

	@Column(name="ETAT")
	private String etat;
	
	@Column(name="MODE_REGLEMENT")
	private String modeReglement;
	
	@Column(name="NUM_PIECE")
	private String numPiece;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="MODIFIER_PAR")
	private Utilisateur modifierPar;

	//bi-directional many-to-one association to Fournisseur
	@ManyToOne
	@JoinColumn(name="ID_CLIENT_FOUR")
	private Client fournisseur;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin;
	
	@Column(name="CODE_TRANSFER")
	private String codeTransfer;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="CREER_PAR")
	private Utilisateur creerPar;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ANNULER_PAR")
	private Utilisateur annulerPar;
	
	@Column(name="DATE_CREER")
	private Date dateCreer;

	@Column(name="DATE_MODIFIER")
	private Date dateModifier;
	
	//bi-directional many-to-one association to DetailCommande
	@OneToMany(cascade = CascadeType.ALL,mappedBy="factureVenteMP")
	private List<DetailFactureVenteMP> detailFactureVenteMP;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public FactureVenteMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}





	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public Utilisateur getAnnulerPar() {
		return annulerPar;
	}

	public void setAnnulerPar(Utilisateur annulerPar) {
		this.annulerPar = annulerPar;
	}

	public Client getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Client fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<DetailFactureVenteMP> getDetailFactureVenteMP() {
		return detailFactureVenteMP;
	}

	public void setDetailFactureVenteMP(List<DetailFactureVenteMP> detailFactureVenteMP) {
		this.detailFactureVenteMP = detailFactureVenteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public String getCodeTransfer() {
		return codeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		this.codeTransfer = codeTransfer;
	}

	public Date getDateCreer() {
		return dateCreer;
	}

	public void setDateCreer(Date dateCreer) {
		this.dateCreer = dateCreer;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}






	
	
}
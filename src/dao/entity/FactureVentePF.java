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
@Table(name="FACTURE_VENTE_PF")
@NamedQuery(name="FactureVentePF.findAll", query="SELECT c FROM FactureVentePF c")
public class FactureVentePF implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;

	@Column(name="NUM_FACTURE")
	private String NumFacture;


	@Column(name="MONTANT_TOTAL")
	private BigDecimal montantTotal;

	@Column(name="ETAT")
	private String etat;

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

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="CREER_PAR")
	private Utilisateur creerPar;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ANNULER_PAR")
	private Utilisateur annulerPar;

	//bi-directional many-to-one association to DetailCommande
	@OneToMany(cascade = CascadeType.ALL,mappedBy="factureVentePF")
	private List<DetailFactureVentePF> detailFactureVentePF;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public FactureVentePF() {
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
		return NumFacture;
	}

	public void setNumFacture(String numFacture) {
		NumFacture = numFacture;
	}

	public BigDecimal getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
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

	public List<DetailFactureVentePF> getDetailFactureVentePF() {
		return detailFactureVentePF;
	}

	public void setDetailFactureVentePF(
			List<DetailFactureVentePF> detailFactureVentePF) {
		this.detailFactureVentePF = detailFactureVentePF;
	}



	
	
}
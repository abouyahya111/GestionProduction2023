package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the detail_commande database table.
 * 
 */

@Entity
@Table(name="Manque_Importation")
@NamedQuery(name="ManqueImportation.findAll", query="SELECT d FROM ManqueImportation d")
public class ManqueImportation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal quantite;
	
	private BigDecimal quantiteFacture;
	
	private BigDecimal quantiteReceptione;
	
	@Column(name="prix_unitaire")
	private  BigDecimal prixUnitaire;
	
	@Column(name="Num_Reception")
	private String numReception;
	
	@Column(name="etat")
	private String etat;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	@ManyToOne
	@JoinColumn(name="id_Magasin")
	private Magasin magasin ;
	 
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP; 
	
	@ManyToOne
	@JoinColumn(name="id_fournisseurAdhesif")
	private FournisseurAdhesive fournisseurAdhesif;
	
	
	@Temporal(TemporalType.DATE)
	private Date date;
	 
	@ManyToOne
	@JoinColumn(name="ajouterPar")
	private Utilisateur ajouterPar;
	
	@ManyToOne
	@JoinColumn(name="modifierPar")
	private Utilisateur modifierPar;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREER")
	private Date dateCreer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIER")
	private Date dateModifier;

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

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public FournisseurAdhesive getFournisseurAdhesif() {
		return fournisseurAdhesif;
	}

	public void setFournisseurAdhesif(FournisseurAdhesive fournisseurAdhesif) {
		this.fournisseurAdhesif = fournisseurAdhesif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Utilisateur getAjouterPar() {
		return ajouterPar;
	}

	public void setAjouterPar(Utilisateur ajouterPar) {
		this.ajouterPar = ajouterPar;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNumReception() {
		return numReception;
	}

	public void setNumReception(String numReception) {
		this.numReception = numReception;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public BigDecimal getQuantiteFacture() {
		return quantiteFacture;
	}

	public void setQuantiteFacture(BigDecimal quantiteFacture) {
		this.quantiteFacture = quantiteFacture;
	}

	public BigDecimal getQuantiteReceptione() {
		return quantiteReceptione;
	}

	public void setQuantiteReceptione(BigDecimal quantiteReceptione) {
		this.quantiteReceptione = quantiteReceptione;
	}

	 
	
	
	

}
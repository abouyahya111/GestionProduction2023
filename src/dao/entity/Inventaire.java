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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="Inventaire.findAll", query="SELECT f FROM Inventaire f")
public class Inventaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	

	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;
	
	
	@ManyToOne
	@JoinColumn(name="id_Magasin")
	private Magasin magasin;
	
	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSaisir;
	
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;
	
	@ManyToOne
	@JoinColumn( name="id_fournisseurMP")
	private FournisseurMP fournisseurMP;
	
	@ManyToOne
	@JoinColumn( name="id_compteMagasinier")
	private CompteMagasinier compteMagasinier;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_Dechet")
	private Magasin magasindechet;
	
	private BigDecimal quantite;
	
	private BigDecimal quantitePerte;
	
	private String codeInventaire;
	
	
	private String etat;
	
	private String motif;
	
	private boolean plus;
	private boolean moins;
	
	
	
	private BigDecimal quantiteStock;
	
	private BigDecimal quantiteStockApresDeuxiemInventaire;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateoperation() {
		return dateoperation;
	}

	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(BigDecimal quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public String getCodeInventaire() {
		return codeInventaire;
	}

	public void setCodeInventaire(String codeInventaire) {
		this.codeInventaire = codeInventaire;
	}



	public boolean isPlus() {
		return plus;
	}

	public void setPlus(boolean plus) {
		this.plus = plus;
	}

	public boolean isMoins() {
		return moins;
	}

	public void setMoins(boolean moins) {
		this.moins = moins;
	}

	public BigDecimal getQuantitePerte() {
		return quantitePerte;
	}

	public void setQuantitePerte(BigDecimal quantitePerte) {
		this.quantitePerte = quantitePerte;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public CompteMagasinier getCompteMagasinier() {
		return compteMagasinier;
	}

	public void setCompteMagasinier(CompteMagasinier compteMagasinier) {
		this.compteMagasinier = compteMagasinier;
	}

	public Magasin getMagasindechet() {
		return magasindechet;
	}

	public void setMagasindechet(Magasin magasindechet) {
		this.magasindechet = magasindechet;
	}



	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public Date getDateSaisir() {
		return dateSaisir;
	}

	public void setDateSaisir(Date dateSaisir) {
		this.dateSaisir = dateSaisir;
	}

	public BigDecimal getQuantiteStockApresDeuxiemInventaire() {
		return quantiteStockApresDeuxiemInventaire;
	}

	public void setQuantiteStockApresDeuxiemInventaire(BigDecimal quantiteStockApresDeuxiemInventaire) {
		this.quantiteStockApresDeuxiemInventaire = quantiteStockApresDeuxiemInventaire;
	}
	
	
	

	

}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;




/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="detail_compte_magasinier")
@NamedQuery(name="DetailCompteMagasinier.findAll", query="SELECT p FROM DetailCompteMagasinier p")
public class DetailCompteMagasinier implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	
	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@Column(name="QUANTITE")
	private BigDecimal quantite;
	
	@Column(name="prix_unitaire")
	private BigDecimal prix;
	
	@Column(name="MONTANT")
	private BigDecimal montant;
	
	
	@ManyToOne
	@JoinColumn(name="id_magasin")
	private Magasin magasin;
	
	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Compte_magasinier")
	private CompteMagasinier compteMagasinier;
	
	
	

	public DetailCompteMagasinier() {
	}



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






	public String getDesignation() {
		return designation;
	}






	public void setDesignation(String designation) {
		this.designation = designation;
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






	public BigDecimal getPrix() {
		return prix;
	}






	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}






	public BigDecimal getMontant() {
		return montant;
	}






	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}





	public Magasin getMagasin() {
		return magasin;
	}



	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}



	public Depot getDepot() {
		return depot;
	}



	public void setDepot(Depot depot) {
		this.depot = depot;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	public CompteMagasinier getCompteMagasinier() {
		return compteMagasinier;
	}



	public void setCompteMagasinier(CompteMagasinier compteMagasinier) {
		this.compteMagasinier = compteMagasinier;
	}



	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}



	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}






	
	

	

}
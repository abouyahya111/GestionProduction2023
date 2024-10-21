package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@NamedQuery(name="ProductionMP.findAll", query="SELECT p FROM ProductionMP p")
public class ProductionMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	

	
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_production")
	private Date dateProduction;

	
	@Column(name="cout_mp")
	private BigDecimal coutTotalMP;
	
	@Column(name="COUT_DECHET")
	private BigDecimal coutDechet;	
	
	@Column(name="cout_employe")
	private BigDecimal coutTotalEmploye;
	
	@Column(name="cout_total")
	private BigDecimal coutTotal;

	@Column(name="quantite_reel")
	private BigDecimal quantiteReel;
	
	@Column(name="CODE_DEPOT")
	private String codeDepot;
	
	@Column(name="NUM_OFMP")
	private String numOFMP;	
	
	@Column(name="NOMBRE_HEURE")
	private BigDecimal nbreHeure;
	
	private String statut;

	//bi-directional many-to-one association to DetailProduction
	@OneToMany(cascade = CascadeType.ALL,mappedBy="productionMP")
	private List<DetailProductionMP> detailProductionsMP=new ArrayList<DetailProductionMP>();
	
	
	//bi-directional many-to-one association to DetailProduction
		@OneToMany(cascade = CascadeType.ALL,mappedBy="prodcutionCM")
		private List<CoutProdMP> listCoutProdMP=new ArrayList<CoutProdMP>();
		
		

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateurMAJ;

	
	@ManyToOne
	@JoinColumn(name="id_magasin_stock")
	private Magasin magasinStockage;
	
	@ManyToOne
	@JoinColumn(name="id_magasin")
	private Magasin magasinProd;
	

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateurCreation;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="annuler_par")
	private Utilisateur utilisateurAnnulation;
	
	//bi-directional many-to-one association to Produit
		@ManyToOne
		@JoinColumn(name="id_article")
		private ArticlesMP articlesMP;
	

	public ProductionMP() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDateProduction() {
		return dateProduction;
	}



	public void setDateProduction(Date dateProduction) {
		this.dateProduction = dateProduction;
	}



	public BigDecimal getCoutTotalMP() {
		return coutTotalMP;
	}



	public void setCoutTotalMP(BigDecimal coutTotalMP) {
		this.coutTotalMP = coutTotalMP;
	}



	public BigDecimal getCoutDechet() {
		return coutDechet;
	}



	public void setCoutDechet(BigDecimal coutDechet) {
		this.coutDechet = coutDechet;
	}



	public BigDecimal getCoutTotalEmploye() {
		return coutTotalEmploye;
	}



	public void setCoutTotalEmploye(BigDecimal coutTotalEmploye) {
		this.coutTotalEmploye = coutTotalEmploye;
	}



	public BigDecimal getCoutTotal() {
		return coutTotal;
	}



	public void setCoutTotal(BigDecimal coutTotal) {
		this.coutTotal = coutTotal;
	}



	public BigDecimal getQuantiteReel() {
		return quantiteReel;
	}



	public void setQuantiteReel(BigDecimal quantiteReel) {
		this.quantiteReel = quantiteReel;
	}



	public String getCodeDepot() {
		return codeDepot;
	}



	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}



	public String getNumOFMP() {
		return numOFMP;
	}



	public void setNumOFMP(String numOFMP) {
		this.numOFMP = numOFMP;
	}



	public BigDecimal getNbreHeure() {
		return nbreHeure;
	}



	public void setNbreHeure(BigDecimal nbreHeure) {
		this.nbreHeure = nbreHeure;
	}



	public List<DetailProductionMP> getDetailProductionsMP() {
		return detailProductionsMP;
	}



	public void setDetailProductionsMP(List<DetailProductionMP> detailProductionsMP) {
		this.detailProductionsMP = detailProductionsMP;
	}



	public List<CoutProdMP> getListCoutProdMP() {
		return listCoutProdMP;
	}



	public void setListCoutProdMP(List<CoutProdMP> listCoutProdMP) {
		this.listCoutProdMP = listCoutProdMP;
	}



	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}



	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}



	public Magasin getMagasinStockage() {
		return magasinStockage;
	}



	public void setMagasinStockage(Magasin magasinStockage) {
		this.magasinStockage = magasinStockage;
	}



	public Magasin getMagasinProd() {
		return magasinProd;
	}



	public void setMagasinProd(Magasin magasinProd) {
		this.magasinProd = magasinProd;
	}



	public Utilisateur getUtilisateurCreation() {
		return utilisateurCreation;
	}



	public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
		this.utilisateurCreation = utilisateurCreation;
	}



	public Utilisateur getUtilisateurAnnulation() {
		return utilisateurAnnulation;
	}



	public void setUtilisateurAnnulation(Utilisateur utilisateurAnnulation) {
		this.utilisateurAnnulation = utilisateurAnnulation;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ArticlesMP getArticlesMP() {
		return articlesMP;
	}



	public void setArticlesMP(ArticlesMP articlesMP) {
		this.articlesMP = articlesMP;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	

	
}
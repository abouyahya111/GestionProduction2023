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
@NamedQuery(name="Production.findAll", query="SELECT p FROM Production p")
public class Production implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_deb_fab_ree")
	private Date dateDebFabRee;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin_fab_pre")
	private Date dateFinFabPre;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin_fab_ree")
	private Date dateFinFabRee;

	@Temporal(TemporalType.DATE)
	@Column(name="`date_deb_fab_pre`")
	private Date date_debFabPre;

	@Column(name="cout_mp")
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	@Column(name="COUT_DECHET")
	private BigDecimal coutDechet=BigDecimal.ZERO;
	
	@Column(name="COUT_EMPLOYE_GENE")
	private BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	
	@Column(name="COUT_EMPLOYE_EMABALGE")
	private BigDecimal coutTotalEmployeEmbalage=BigDecimal.ZERO;
	
	@Column(name="COUT_HORS_PRODUCTION_EN_ATTENT")
	private BigDecimal coutTotalHorsProductionEnAttent=BigDecimal.ZERO;

	@Column(name="cout_employe")
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	
	@Column(name="cout_total")
	private BigDecimal coutTotal=BigDecimal.ZERO;

	@Column(name="quantite_estime")
	private BigDecimal quantiteEstime;

	@Column(name="quantite_reel")
	private BigDecimal quantiteReel=BigDecimal.ZERO;;
	
	@Column(name="CODE_DEPOT")
	private String codeDepot;

	private String statut;
	
	@Column(name="NUM_OF")
	private String numOF;
	
	@Column(name="offre_variant")
	private String offreVariant;
	
	@Column(name="DESCRIPTION_OF")
	private String description;
	
	@Column(name="OFFRE")
	private String offre;
	
	@Column(name="PERIODE")
	private String periode;
	
	@Column(name="NOMBRE_HEURE")
	private BigDecimal nbreHeure;
	
	@Column(name="ARTICLE_MIXTE")
	private boolean articleMixte;
	
	@Column(name="quantite_offre")
	private BigDecimal quantiteOffre;
	
	@Column(name="quantite_plus")
	private BigDecimal quantitePlus;
	
	@Column(name="quantite_moins")
	private BigDecimal quantiteMoins;
	

	public boolean isArticleMixte() {
		return articleMixte;
	}

	public void setArticleMixte(boolean articleMixte) {
		this.articleMixte = articleMixte;
	}

	//bi-directional many-to-one association to DetailProduction
	@OneToMany(cascade = CascadeType.ALL,mappedBy="production")
	private List<DetailProduction> detailProductions=new ArrayList<DetailProduction>();
	
	//bi-directional many-to-one association to DetailProduction
	@OneToMany(cascade = CascadeType.ALL,mappedBy="productionGen")
	private List<DetailProdGen> listDetailProdGen=new ArrayList<DetailProdGen>();
	
	//bi-directional many-to-one association to DetailProduction
		@OneToMany(cascade = CascadeType.ALL,mappedBy="prodcutionCM")
		private List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
		
		//bi-directional many-to-one association to DetailProduction
		@OneToMany(cascade = CascadeType.ALL,mappedBy="productionDRP")
		private List<DetailResponsableProd> listDetailResponsableProd=new ArrayList<DetailResponsableProd>();
				
		

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateurMAJ;
	
	@ManyToOne
	@JoinColumn(name="ID_EQUIPE")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name="ID_EQUIPE_GEN")
	private Equipe equipeGen;


	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles articles;

	//bi-directional many-to-one association to Machine
	@ManyToOne
	@JoinColumn(name="id_ligne")
	private LigneMachine ligneMachine;
	
	@ManyToOne
	@JoinColumn(name="id_magasin_stock")
	private Magasin magasinStockage;
	
	@ManyToOne
	@JoinColumn(name="id_magasin")
	private Magasin magasinProd;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN_PF")
	private Magasin magasinPF;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateurCreation;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="annuler_par")
	private Utilisateur utilisateurAnnulation;
	
	@ManyToOne
	@JoinColumn(name="id_magasin_dechet")
	private Magasin magasinDechet;
	
	@ManyToOne
	@JoinColumn(name="TYPE_OFFRE")
	private TypeOffre tyoeOffre;
	
	@ManyToOne
	@JoinColumn(name="CONDITION_OFFRE")
	private ConditionOffre conditionOffre;
	
	
	@ManyToOne
	@JoinColumn(name="FORME_BOX")
	private FormeParBox formeBox;

	public Production() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateDebFabRee() {
		return this.dateDebFabRee;
	}

	public void setDateDebFabRee(Date dateDebFabRee) {
		this.dateDebFabRee = dateDebFabRee;
	}

	public Date getDateFinFabPre() {
		return this.dateFinFabPre;
	}

	public void setDateFinFabPre(Date dateFinFabPre) {
		this.dateFinFabPre = dateFinFabPre;
	}

	public Date getDateFinFabRee() {
		return this.dateFinFabRee;
	}

	public void setDateFinFabRee(Date dateFinFabRee) {
		this.dateFinFabRee = dateFinFabRee;
	}

	public Date getDate_debFabPre() {
		return this.date_debFabPre;
	}

	public void setDate_debFabPre(Date date_debFabPre) {
		this.date_debFabPre = date_debFabPre;
	}

	

	public BigDecimal getQuantiteEstime() {
		return this.quantiteEstime;
	}

	public void setQuantiteEstime(BigDecimal quantiteEstime) {
		this.quantiteEstime = quantiteEstime;
	}

	public BigDecimal getQuantiteReel() {
		return this.quantiteReel;
	}

	public void setQuantiteReel(BigDecimal quantiteReel) {
		this.quantiteReel = quantiteReel;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public List<DetailProduction> getDetailProductions() {
		return this.detailProductions;
	}

	public void setDetailProductions(List<DetailProduction> detailProductions) {
		this.detailProductions = detailProductions;
	}

	public DetailProduction addDetailProduction(DetailProduction detailProduction) {
		getDetailProductions().add(detailProduction);
		detailProduction.setProduction(this);

		return detailProduction;
	}

	public DetailProduction removeDetailProduction(DetailProduction detailProduction) {
		getDetailProductions().remove(detailProduction);
		detailProduction.setProduction(null);

		return detailProduction;
	}

	public CoutMP addProduction(CoutMP coutMP) {
		getListCoutMP().add(coutMP);
		coutMP.setProdcutionCM(this);
		return coutMP;
	}

	public CoutMP removeProduction(CoutMP coutMP) {
		getListCoutMP().remove(coutMP);
		coutMP.setProdcutionCM(null);

		return coutMP;
	}
	

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
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

	public List<CoutMP> getListCoutMP() {
		return listCoutMP;
	}

	public void setListCoutMP(List<CoutMP> listCoutMP) {
		this.listCoutMP = listCoutMP;
	}

	public LigneMachine getLigneMachine() {
		return ligneMachine;
	}

	public void setLigneMachine(LigneMachine ligneMachine) {
		this.ligneMachine = ligneMachine;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public BigDecimal getCoutTotalMP() {
		return coutTotalMP;
	}

	public void setCoutTotalMP(BigDecimal coutTotalMP) {
		this.coutTotalMP = coutTotalMP;
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

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public Equipe getEquipeGen() {
		return equipeGen;
	}

	public void setEquipeGen(Equipe equipeGen) {
		this.equipeGen = equipeGen;
	}

	public BigDecimal getCoutTotalEmployeGen() {
		return coutTotalEmployeGen;
	}

	public void setCoutTotalEmployeGen(BigDecimal coutTotalEmployeGen) {
		this.coutTotalEmployeGen = coutTotalEmployeGen;
	}

	public BigDecimal getNbreHeure() {
		return nbreHeure;
	}

	public void setNbreHeure(BigDecimal nbreHeure) {
		this.nbreHeure = nbreHeure;
	}

	public Magasin getMagasinPF() {
		return magasinPF;
	}

	public void setMagasinPF(Magasin magasinPF) {
		this.magasinPF = magasinPF;
	}

	
	public List<DetailResponsableProd> getListDetailResponsableProd() {
		return listDetailResponsableProd;
	}

	public void setListDetailResponsableProd(
			List<DetailResponsableProd> listDetailResponsableProd) {
		this.listDetailResponsableProd = listDetailResponsableProd;
	}

	public List<DetailProdGen> getListDetailProdGen() {
		return listDetailProdGen;
	}

	public void setListDetailProdGen(List<DetailProdGen> listDetailProdGen) {
		this.listDetailProdGen = listDetailProdGen;
	}

	public BigDecimal getCoutTotalEmployeEmbalage() {
		return coutTotalEmployeEmbalage;
	}

	public void setCoutTotalEmployeEmbalage(BigDecimal coutTotalEmployeEmbalage) {
		this.coutTotalEmployeEmbalage = coutTotalEmployeEmbalage;
	}

	public BigDecimal getCoutDechet() {
		return coutDechet;
	}

	public void setCoutDechet(BigDecimal coutDechet) {
		this.coutDechet = coutDechet;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}

	public String getOffre() {
		return offre;
	}

	public void setOffre(String offre) {
		this.offre = offre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Magasin getMagasinDechet() {
		return magasinDechet;
	}

	public void setMagasinDechet(Magasin magasinDechet) {
		this.magasinDechet = magasinDechet;
	}

	public BigDecimal getCoutTotalHorsProductionEnAttent() {
		return coutTotalHorsProductionEnAttent;
	}

	public void setCoutTotalHorsProductionEnAttent(BigDecimal coutTotalHorsProductionEnAttent) {
		this.coutTotalHorsProductionEnAttent = coutTotalHorsProductionEnAttent;
	}

	public BigDecimal getQuantiteOffre() {
		return quantiteOffre;
	}

	public void setQuantiteOffre(BigDecimal quantiteOffre) {
		this.quantiteOffre = quantiteOffre;
	}

	public BigDecimal getQuantitePlus() {
		return quantitePlus;
	}

	public void setQuantitePlus(BigDecimal quantitePlus) {
		this.quantitePlus = quantitePlus;
	}

	public BigDecimal getQuantiteMoins() {
		return quantiteMoins;
	}

	public void setQuantiteMoins(BigDecimal quantiteMoins) {
		this.quantiteMoins = quantiteMoins;
	}

	public TypeOffre getTyoeOffre() {
		return tyoeOffre;
	}

	public void setTyoeOffre(TypeOffre tyoeOffre) {
		this.tyoeOffre = tyoeOffre;
	}

	public ConditionOffre getConditionOffre() {
		return conditionOffre;
	}

	public void setConditionOffre(ConditionOffre conditionOffre) {
		this.conditionOffre = conditionOffre;
	}

	public String getOffreVariant() {
		return offreVariant;
	}

	public void setOffreVariant(String offreVariant) {
		this.offreVariant = offreVariant;
	}

	public FormeParBox getFormeBox() {
		return formeBox;
	}

	public void setFormeBox(FormeParBox formeBox) {
		this.formeBox = formeBox;
	}


	
	
	
	
}
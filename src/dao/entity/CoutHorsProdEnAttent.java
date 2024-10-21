package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="COUT_HORS_PROD_EN_ATTENT")
@NamedQuery(name="CoutHorsProdEnAttent.findAll", query="SELECT d FROM CoutHorsProdEnAttent d")
public class CoutHorsProdEnAttent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String code;
	
	private String typeTravail;
	
	private String commentaire;

	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_SITUTAION")
	private Date dateSituation;
	
	 
	
	@Column(name="HEURE_25")
	private BigDecimal heure25;
	
	@Column(name="HEURE_50")
	private BigDecimal heure50;
	
	@Column(name="COUT_TOTAL")
	private BigDecimal coutTotal;
	
	@Column(name="DELAI_EMPLOYE")
	private BigDecimal delaiEmploye;
	
	@Column(name="COUT_HORAIRE")
	private BigDecimal coutHoraire;
	
	@Column(name="COUT_HORAIRE25")
	private BigDecimal coutHoraire25;
	
	@Column(name="COUT_HORAIRE50")
	private BigDecimal coutHoraire50;
	
	
	@Column(name="COUT_SUPP25")
	private BigDecimal coutSupp25;
	
	@Column(name="COUT_SUPP50")
	private BigDecimal coutSupp50;
	
	@Column(name="COUT_CONSOMMER")
	private BigDecimal coutConsommer;
	
	@Column(name="RESTE")
	private BigDecimal reste;

	@Column(name="ETAT")
	private String etat;
	
	@JoinColumn(name="DATE_CREATION")
	private Date dateCreation;
	
	@ManyToOne
	@JoinColumn(name="id_production")
	private Production production;
	
	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;
	
	@JoinColumn(name="DATE_MAJ")
	private Date dateMAJ;
	
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateurMAJ;
	
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateurCreation;
	
	@ManyToOne
	@JoinColumn(name="depot")
	private Depot depot;
	
	
	@ManyToOne
	@JoinColumn(name="article")
	private Articles articles;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateSituation() {
		return dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	 

	public BigDecimal getHeure25() {
		return heure25;
	}

	public void setHeure25(BigDecimal heure25) {
		this.heure25 = heure25;
	}

	public BigDecimal getHeure50() {
		return heure50;
	}

	public void setHeure50(BigDecimal heure50) {
		this.heure50 = heure50;
	}

	public BigDecimal getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(BigDecimal coutTotal) {
		this.coutTotal = coutTotal;
	}

	public BigDecimal getCoutHoraire() {
		return coutHoraire;
	}

	public void setCoutHoraire(BigDecimal coutHoraire) {
		this.coutHoraire = coutHoraire;
	}

	public BigDecimal getCoutHoraire25() {
		return coutHoraire25;
	}

	public void setCoutHoraire25(BigDecimal coutHoraire25) {
		this.coutHoraire25 = coutHoraire25;
	}

	public BigDecimal getCoutHoraire50() {
		return coutHoraire50;
	}

	public void setCoutHoraire50(BigDecimal coutHoraire50) {
		this.coutHoraire50 = coutHoraire50;
	}

	public BigDecimal getCoutConsommer() {
		return coutConsommer;
	}

	public void setCoutConsommer(BigDecimal coutConsommer) {
		this.coutConsommer = coutConsommer;
	}

	public BigDecimal getReste() {
		return reste;
	}

	public void setReste(BigDecimal reste) {
		this.reste = reste;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Date getDateMAJ() {
		return dateMAJ;
	}

	public void setDateMAJ(Date dateMAJ) {
		this.dateMAJ = dateMAJ;
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

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TypeResEmploye getTypeResEmploye() {
		return typeResEmploye;
	}

	public void setTypeResEmploye(TypeResEmploye typeResEmploye) {
		this.typeResEmploye = typeResEmploye;
	}

	public BigDecimal getCoutSupp25() {
		return coutSupp25;
	}

	public void setCoutSupp25(BigDecimal coutSupp25) {
		this.coutSupp25 = coutSupp25;
	}

	public BigDecimal getCoutSupp50() {
		return coutSupp50;
	}

	public void setCoutSupp50(BigDecimal coutSupp50) {
		this.coutSupp50 = coutSupp50;
	}

	public BigDecimal getDelaiEmploye() {
		return delaiEmploye;
	}

	public void setDelaiEmploye(BigDecimal delaiEmploye) {
		this.delaiEmploye = delaiEmploye;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public String getTypeTravail() {
		return typeTravail;
	}

	public void setTypeTravail(String typeTravail) {
		this.typeTravail = typeTravail;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	

	
	

}
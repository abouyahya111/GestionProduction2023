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
@Table(name="TRAVAIL_HORS_PROD")
@NamedQuery(name="TravailHorsProd.findAll", query="SELECT d FROM TravailHorsProd d")
public class TravailHorsProd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String code;
	
	private String designation;
	
	private String commentaire;
	
	private String typeTravail;

	//bi-directional many-to-one association to Employe
	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;

	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_SITUTAION")
	private Date dateSituation;
	
	@Column(name="HEURE_SUPP25")
	private BigDecimal heureSupp25;
	
	@Column(name="HEURE_SUPP50")
	private BigDecimal heureSupp50;

	@Column(name="DELAI_TRAVAIL")
	private BigDecimal delai;
	
	@JoinColumn(name="DATE_CREATION")
	private Date dateCreation;
	
	@JoinColumn(name="DATE_MAJ")
	private Date dateMAJ;
	
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateurMAJ;
	
	@ManyToOne
	@JoinColumn(name="depot")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="article")
	private Articles articles;
	
	
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateurCreation;
	
	@Column(name="Heures_En_Attente")
	private boolean heuresEnAttente;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;
	
	@Column(name="cout_horaire")
	private BigDecimal coutHoraire;

	public TravailHorsProd() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Date getDateSituation() {
		return dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	public BigDecimal getHeureSupp25() {
		return heureSupp25;
	}

	public void setHeureSupp25(BigDecimal heureSupp25) {
		this.heureSupp25 = heureSupp25;
	}

	public BigDecimal getHeureSupp50() {
		return heureSupp50;
	}

	public void setHeureSupp50(BigDecimal heureSupp50) {
		this.heureSupp50 = heureSupp50;
	}

	public BigDecimal getDelai() {
		return delai;
	}

	public void setDelai(BigDecimal delai) {
		this.delai = delai;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isHeuresEnAttente() {
		return heuresEnAttente;
	}

	public void setHeuresEnAttente(boolean heuresEnAttente) {
		this.heuresEnAttente = heuresEnAttente;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public TypeResEmploye getTypeResEmploye() {
		return typeResEmploye;
	}

	public void setTypeResEmploye(TypeResEmploye typeResEmploye) {
		this.typeResEmploye = typeResEmploye;
	}

	public BigDecimal getCoutHoraire() {
		return coutHoraire;
	}

	public void setCoutHoraire(BigDecimal coutHoraire) {
		this.coutHoraire = coutHoraire;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getTypeTravail() {
		return typeTravail;
	}

	public void setTypeTravail(String typeTravail) {
		this.typeTravail = typeTravail;
	}

	

}
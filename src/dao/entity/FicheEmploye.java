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
import javax.persistence.Transient;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="FICHE_EMPLOYE")
@NamedQuery(name="FicheEmploye.findAll", query="SELECT d FROM FicheEmploye d")
public class FicheEmploye implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="delai_employe")
	private BigDecimal delaiEmploye=BigDecimal.ZERO;;
	
	
	
	@Column(name="remise")
	private BigDecimal remise;
	
	@Column(name="AVANCE")
	private BigDecimal avance;

	//bi-directional many-to-one association to Employe
	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;

	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_SITUTAION")
	private Date dateSituation;
	
	@Column(name="NUM_OF")
	private String numOF;
	
	@Column(name="HEURE_SUPP25")
	private BigDecimal heureSupp25;
	
	@Column(name="HEURE_SUPP50")
	private BigDecimal heureSupp50;
	
	@Column(name="COUT_HORAIRE")
	private BigDecimal coutHoraire;
	
	
	
	
	
	/*@Column(name="COUT_SUPP25")
	private BigDecimal coutSupp25;
	
	@Column(name="COUT_SUPP50")
	private BigDecimal coutSupp50;
	
	@Column(name="cout_horaire")
	private BigDecimal coutTotal;*/
	
	@Transient
	private BigDecimal heurProduction;
	
	@Column(name="DELAI_PROD")
	private BigDecimal delaiProd=BigDecimal.ZERO;
	
	@Column(name="SORTIE")
	private boolean sortie;
	
	@Column(name="equipe")
	private String equipe;
	
	@Column(name="ABSENT")
	private boolean absent;

	@Column(name="RETARD")
	private boolean retard;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;

	public boolean isSortie() {
		return sortie;
	}

	public void setSortie(boolean sortie) {
		this.sortie = sortie;
	}

	public FicheEmploye() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getDelaiEmploye() {
		return delaiEmploye;
	}

	public void setDelaiEmploye(BigDecimal delaiEmploye) {
		this.delaiEmploye = delaiEmploye;
	}

/*	public BigDecimal getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(BigDecimal coutTotal) {
		this.coutTotal = coutTotal;
	}*/

	public BigDecimal getRemise() {
		return remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	public BigDecimal getAvance() {
		return avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
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

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
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

	/*public BigDecimal getCoutSupp25() {
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
	}*/
	public BigDecimal getDelaiProd() {
		return delaiProd;
	}

	public void setDelaiProd(BigDecimal delaiProd) {
		this.delaiProd = delaiProd;
	}

	public BigDecimal getHeurProduction() {
		return heurProduction;
	}

	public void setHeurProduction(BigDecimal heurProduction) {
		this.heurProduction = heurProduction;
	}

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	public boolean isRetard() {
		return retard;
	}

	public void setRetard(boolean retard) {
		this.retard = retard;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
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
	
	
	
	
	
	

}
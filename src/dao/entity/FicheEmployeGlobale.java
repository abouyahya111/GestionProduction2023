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

@Table(name="FICHE_GLOBALE")
public class FicheEmployeGlobale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="delai_employe")
	private BigDecimal delaiEmploye;
	
	@Column(name="cout_horaire")
	private BigDecimal coutTotal;
	
	@Column(name="remise")
	private BigDecimal remise;
	
	@Column(name="remiseApayer")
	private BigDecimal remiseApayer;
	
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
	
	@Column(name="COUT_SUPP25")
	private BigDecimal coutSupp25;
	
	@Column(name="COUT_SUPP50")
	private BigDecimal coutSupp50;
	
	@Column(name="COUT_TOTAL_BRUT")
	private BigDecimal coutTotalBrut;
	
	@Column(name="ANCIENNETE")
	private BigDecimal anciennete;
	
	@Column(name="CNSS")
	private BigDecimal cnss;
	
	@Column(name="COUT_TOTAL_NET")
	private BigDecimal coutTotalNet;
	
	@Column(name="COMPTEUR")
	private int compteur;
	
	@Column(name="REDUCTION")
	private BigDecimal reduction;

	@Column(name="NETAPAYER")
	private BigDecimal netapayer;
	
	@Column(name="NETAPAYERBULLETIN")
	private BigDecimal netapayerbulletin;
	
	@Column(name="TOTAL_RESTE")
	private BigDecimal totalReste;
	
	public FicheEmployeGlobale() {
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

	public BigDecimal getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(BigDecimal coutTotal) {
		this.coutTotal = coutTotal;
	}

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

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public BigDecimal getReduction() {
		return reduction;
	}

	public void setReduction(BigDecimal reduction) {
		this.reduction = reduction;
	}

	public BigDecimal getNetapayer() {
		return netapayer;
	}

	public void setNetapayer(BigDecimal netapayer) {
		this.netapayer = netapayer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getRemiseApayer() {
		return remiseApayer;
	}

	public void setRemiseApayer(BigDecimal remiseApayer) {
		this.remiseApayer = remiseApayer;
	}

	public BigDecimal getNetapayerbulletin() {
		return netapayerbulletin;
	}

	public void setNetapayerbulletin(BigDecimal netapayerbulletin) {
		this.netapayerbulletin = netapayerbulletin;
	}

	public BigDecimal getTotalReste() {
		return totalReste;
	}

	public void setTotalReste(BigDecimal totalReste) {
		this.totalReste = totalReste;
	}

	public BigDecimal getCoutTotalBrut() {
		return coutTotalBrut;
	}

	public void setCoutTotalBrut(BigDecimal coutTotalBrut) {
		this.coutTotalBrut = coutTotalBrut;
	}

	public BigDecimal getAnciennete() {
		return anciennete;
	}

	public void setAnciennete(BigDecimal anciennete) {
		this.anciennete = anciennete;
	}

	public BigDecimal getCnss() {
		return cnss;
	}

	public void setCnss(BigDecimal cnss) {
		this.cnss = cnss;
	}

	public BigDecimal getCoutTotalNet() {
		return coutTotalNet;
	}

	public void setCoutTotalNet(BigDecimal coutTotalNet) {
		this.coutTotalNet = coutTotalNet;
	}

	
	

}
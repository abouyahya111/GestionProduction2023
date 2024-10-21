package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="DETAIL_RESPONSABLE_PROD")
@NamedQuery(name="DetailResponsableProd.findAll", query="SELECT d FROM DetailResponsableProd d")
public class DetailResponsableProd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DELAI_EMPLOYE")
	private BigDecimal delaiEmploye;
	
	@Column(name="COUT_TOTAL")
	private BigDecimal coutTotal;
	
	@Column(name="REMISE")
	private BigDecimal remise;
	
	@Column(name="NOTE")
	private int note;
	
	@Column(name="MOTIF")
	private String motif;
	
	@Column(name="NBRE_TYPE_EMP")
	private BigDecimal nbreTypeEmploye;

	//bi-directional many-to-one association to Employe
	@ManyToOne
	@JoinColumn(name="ID_EMPLOYE")
	private Employe employe;
	
	@Column(name="HEURE_SUPP25")
	private BigDecimal heureSupp25;
	
	@Column(name="HEURE_SUPP50")
	private BigDecimal heureSupp50;
	
	@Column(name="COUT_SUPP25")
	private BigDecimal coutSupp25;
	
	@Column(name="COUT_SUPP50")
	private BigDecimal coutSupp50;
	
	@Column(name="ABSENT")
	private boolean absent;
	
	@Column(name="SORTIE")
	private boolean sortie;
	

	//bi-directional many-to-one association to Production
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTION")
	private Production productionDRP;

	public DetailResponsableProd() {
	}

	
	public boolean isAbsent() {
		return absent;
	}


	public void setAbsent(boolean absent) {
		this.absent = absent;
	}


	public Production getProductionDRP() {
		return productionDRP;
	}


	public void setProductionDRP(Production productionDRP) {
		this.productionDRP = productionDRP;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getDelaiEmploye() {
		return this.delaiEmploye;
	}

	public void setDelaiEmploye(BigDecimal delaiEmploye) {
		this.delaiEmploye = delaiEmploye;
	}

	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Production getProduction() {
		return this.productionDRP;
	}

	public void setProduction(Production production) {
		this.productionDRP = production;
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

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public BigDecimal getNbreTypeEmploye() {
		return nbreTypeEmploye;
	}

	public void setNbreTypeEmploye(BigDecimal nbreTypeEmploye) {
		this.nbreTypeEmploye = nbreTypeEmploye;
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


	public boolean isSortie() {
		return sortie;
	}


	public void setSortie(boolean sortie) {
		this.sortie = sortie;
	}

	
	

}
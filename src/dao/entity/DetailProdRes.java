package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="DETAIL_PROD_RES")
@NamedQuery(name="DetailProdRes.findAll", query="SELECT d FROM DetailProdRes d")
public class DetailProdRes implements Serializable {
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
	
	@Column(name="AUTORISATION")
	private boolean autorisation;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;

	
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
	
	@Column(name="cout_horaire")
	private BigDecimal coutHoraire;
	
	@Column(name="cout_horaire_supp25")
	private BigDecimal coutHoraireSupp25;
	
	@Column(name="cout_horaire_supp50")
	private BigDecimal coutHoraireSupp50;
	
	@Column(name="ABSENT")
	private boolean absent;
	
	@Column(name="SORTIE")
	private boolean sortie;
	
	@Column(name="RETARD")
	private boolean retard;
	
	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_PRODUCTION")
	private Date dateProduction;
	
	@Column(name="VALIDER")
	private String valider;
	
	@Column(name="NBR_PRODUCTION")
	private int nbrProduction;
	
	@Column(name="CODE_DEPOT")
	private String codeDepot;
	
	

	//bi-directional many-to-one association to Production
	

	public DetailProdRes() {
	}

	
	public boolean isAbsent() {
		return absent;
	}


	public void setAbsent(boolean absent) {
		this.absent = absent;
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


	public Date getDateProduction() {
		return dateProduction;
	}


	public void setDateProduction(Date dateProduction) {
		this.dateProduction = dateProduction;
	}


	public boolean isRetard() {
		return retard;
	}


	public void setRetard(boolean retard) {
		this.retard = retard;
	}


	public boolean isAutorisation() {
		return autorisation;
	}


	public void setAutorisation(boolean autorisation) {
		this.autorisation = autorisation;
	}


	public String getValider() {
		return valider;
	}


	public void setValider(String valider) {
		this.valider = valider;
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


	public BigDecimal getCoutHoraireSupp25() {
		return coutHoraireSupp25;
	}


	public void setCoutHoraireSupp25(BigDecimal coutHoraireSupp25) {
		this.coutHoraireSupp25 = coutHoraireSupp25;
	}


	public BigDecimal getCoutHoraireSupp50() {
		return coutHoraireSupp50;
	}


	public void setCoutHoraireSupp50(BigDecimal coutHoraireSupp50) {
		this.coutHoraireSupp50 = coutHoraireSupp50;
	}


	public int getNbrProduction() {
		return nbrProduction;
	}


	public void setNbrProduction(int nbrProduction) {
		this.nbrProduction = nbrProduction;
	}


	public String getCodeDepot() {
		return codeDepot;
	}


	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}

	
	
	
	
	

}
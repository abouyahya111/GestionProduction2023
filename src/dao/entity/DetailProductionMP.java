package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="detail_productionMP")
@NamedQuery(name="DetailProductionMP.findAll", query="SELECT d FROM DetailProductionMP d")
public class DetailProductionMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="delai_employe")
	private BigDecimal delaiEmploye;
	
	@Column(name="cout_total")
	private BigDecimal coutTotal;
	
	@Column(name="remise")
	private BigDecimal remise;
	
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
	
	@Column(name="AUTORISATION")
	private boolean autorisation;
	
	@Column(name="MOTIF")
	private String motif;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;

	//bi-directional many-to-one association to Employe
	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;

	//bi-directional many-to-one association to Production
	@ManyToOne
	@JoinColumn(name="id_production")
	private ProductionMP productionMP;
	
	@Column(name="VALIDER")
	private String valider;

	public DetailProductionMP() {
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

	public ProductionMP getProductionMP() {
		return this.productionMP;
	}

	public void setProductionMP(ProductionMP productionMP) {
		this.productionMP = productionMP;
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

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
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

	
	

}
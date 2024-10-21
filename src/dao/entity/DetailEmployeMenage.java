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
@Table(name="DETAIL_EMPLOYE_MENAGE")
@NamedQuery(name="DetailEmployeMenage.findAll", query="SELECT d FROM DetailEmployeMenage d")
public class DetailEmployeMenage implements Serializable {
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
	
	@Column(name="ABSENT")
	private boolean absent;
	
	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_TRAVAIL")
	private Date dateTravail;
	
	
	@Column(name="cout_horaire")
	private BigDecimal coutHoraire;

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

	public boolean isAutorisation() {
		return autorisation;
	}

	public void setAutorisation(boolean autorisation) {
		this.autorisation = autorisation;
	}

	public BigDecimal getNbreTypeEmploye() {
		return nbreTypeEmploye;
	}

	public void setNbreTypeEmploye(BigDecimal nbreTypeEmploye) {
		this.nbreTypeEmploye = nbreTypeEmploye;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	public Date getDateTravail() {
		return dateTravail;
	}

	public void setDateTravail(Date dateTravail) {
		this.dateTravail = dateTravail;
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

	public BigDecimal getCoutHoraire() {
		return coutHoraire;
	}

	public void setCoutHoraire(BigDecimal coutHoraire) {
		this.coutHoraire = coutHoraire;
	}
	


	//bi-directional many-to-one association to Production
	

	

}
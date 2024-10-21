package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="SituationProgrammeProduction.findAll", query="SELECT f FROM SituationProgrammeProduction f")
public class SituationProgrammeProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="ARTICLE")
	private Articles article ;
	
	@Column(name="TOTAL_EMPLOYEE")
	private BigDecimal totalEmploye ;
	
	@Column(name="NUM_EMPLOYEE")
	private BigDecimal numEmploye ;

	@Column(name="NOMBRE_EMPLOYEE")
	private BigDecimal nombreEmploye ;
	
	@Column(name="NOMBRE_OF")
	private BigDecimal nombreOF ;
	
	@Column(name="GENERIQUE")
	private BigDecimal generique ;
	
	@Column(name="ADHESIF")
	private BigDecimal adhesif ;
	
	@Column(name="REPOS")
	private BigDecimal repos ;
	
	@Column(name="ABSENT")
	private BigDecimal absent ;
	
	@Column(name="MACHINE")
	private Machine machine ;
	
	@Column(name="GAMMAGE")
	private String grammage ;
	
	@Column(name="CARTON")
	private String carton ;
	
	@Column(name="TOTAL_HEURES")
	private BigDecimal totalHeures ;
	
	@Column(name="PRIX")
	private BigDecimal prix ;
	
	@Column(name="QUANTITE")
	private BigDecimal quantite ;
	
	@Column(name="COUT")
	private BigDecimal cout ;
	
	@Column(name="COUTFIX")
	private BigDecimal cout_fix ;

	@Column(name="DIFFERENCE")
	private BigDecimal difference ;
	
	@Column(name="totalNumEmployeImprimer")
	private BigDecimal totalNumEmployeImprimer ;
	
	@Column(name="totalEmployeGeniriqueImprimer")
	private BigDecimal totalEmployeGeniriqueImprimer ;
	
	@Column(name="totalEmployeAdhesifImprimer")
	private BigDecimal totalEmployeAdhesifImprimer ;
	
	@Column(name="totalEmployeAbsentImprimer")
	private BigDecimal totalEmployeAbsentImprimer ;
	
	@Column(name="totalEmployeReposImprimer")
	private BigDecimal totalEmployeReposImprimer ;
	
	@Column(name="TOTAL_EMPLOYEEImprimer")
	private BigDecimal totalEmployeImprimer ;
	
	@Column(name="FORME_PAR_BOX")
	private FormeParBox formeParBox ;
	
	@Column(name="COUT_MACHINE")
	private CoutMachine coutMachine ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotalEmploye() {
		return totalEmploye;
	}

	public void setTotalEmploye(BigDecimal totalEmploye) {
		this.totalEmploye = totalEmploye;
	}

	public BigDecimal getNumEmploye() {
		return numEmploye;
	}

	public void setNumEmploye(BigDecimal numEmploye) {
		this.numEmploye = numEmploye;
	}

	public BigDecimal getNombreEmploye() {
		return nombreEmploye;
	}

	public void setNombreEmploye(BigDecimal nombreEmploye) {
		this.nombreEmploye = nombreEmploye;
	}

	public BigDecimal getGenerique() {
		return generique;
	}

	public void setGenerique(BigDecimal generique) {
		this.generique = generique;
	}

	public BigDecimal getAdhesif() {
		return adhesif;
	}

	public void setAdhesif(BigDecimal adhesif) {
		this.adhesif = adhesif;
	}

	public BigDecimal getAbsent() {
		return absent;
	}

	public void setAbsent(BigDecimal absent) {
		this.absent = absent;
	}

	
	public BigDecimal getTotalHeures() {
		return totalHeures;
	}

	public void setTotalHeures(BigDecimal totalHeures) {
		this.totalHeures = totalHeures;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
	
	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public BigDecimal getCout() {
		return cout;
	}

	public void setCout(BigDecimal cout) {
		this.cout = cout;
	}

	public String getGrammage() {
		return grammage;
	}

	public void setGrammage(String grammage) {
		this.grammage = grammage;
	}

	public String getCarton() {
		return carton;
	}

	public void setCarton(String carton) {
		this.carton = carton;
	}

	public BigDecimal getCout_fix() {
		return cout_fix;
	}

	public void setCout_fix(BigDecimal cout_fix) {
		this.cout_fix = cout_fix;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getNombreOF() {
		return nombreOF;
	}

	public void setNombreOF(BigDecimal nombreOF) {
		this.nombreOF = nombreOF;
	}

	public BigDecimal getTotalNumEmployeImprimer() {
		return totalNumEmployeImprimer;
	}

	public void setTotalNumEmployeImprimer(BigDecimal totalNumEmployeImprimer) {
		this.totalNumEmployeImprimer = totalNumEmployeImprimer;
	}

	public BigDecimal getTotalEmployeGeniriqueImprimer() {
		return totalEmployeGeniriqueImprimer;
	}

	public void setTotalEmployeGeniriqueImprimer(BigDecimal totalEmployeGeniriqueImprimer) {
		this.totalEmployeGeniriqueImprimer = totalEmployeGeniriqueImprimer;
	}

	public BigDecimal getTotalEmployeAdhesifImprimer() {
		return totalEmployeAdhesifImprimer;
	}

	public void setTotalEmployeAdhesifImprimer(BigDecimal totalEmployeAdhesifImprimer) {
		this.totalEmployeAdhesifImprimer = totalEmployeAdhesifImprimer;
	}

	public BigDecimal getTotalEmployeAbsentImprimer() {
		return totalEmployeAbsentImprimer;
	}

	public void setTotalEmployeAbsentImprimer(BigDecimal totalEmployeAbsentImprimer) {
		this.totalEmployeAbsentImprimer = totalEmployeAbsentImprimer;
	}

	public BigDecimal getTotalEmployeImprimer() {
		return totalEmployeImprimer;
	}

	public void setTotalEmployeImprimer(BigDecimal totalEmployeImprimer) {
		this.totalEmployeImprimer = totalEmployeImprimer;
	}

	public BigDecimal getRepos() {
		return repos;
	}

	public void setRepos(BigDecimal repos) {
		this.repos = repos;
	}

	public BigDecimal getTotalEmployeReposImprimer() {
		return totalEmployeReposImprimer;
	}

	public void setTotalEmployeReposImprimer(BigDecimal totalEmployeReposImprimer) {
		this.totalEmployeReposImprimer = totalEmployeReposImprimer;
	}

	public FormeParBox getFormeParBox() {
		return formeParBox;
	}

	public void setFormeParBox(FormeParBox formeParBox) {
		this.formeParBox = formeParBox;
	}

	public CoutMachine getCoutMachine() {
		return coutMachine;
	}

	public void setCoutMachine(CoutMachine coutMachine) {
		this.coutMachine = coutMachine;
	}


	
	
	
	
	
}
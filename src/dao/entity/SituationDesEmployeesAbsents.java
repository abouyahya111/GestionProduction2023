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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Constantes;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="Articles.findAll", query="SELECT f FROM Articles f")
public class SituationDesEmployeesAbsents implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;
	
	@Column(name="MOIS_1")
	private String mois1;
	
	@Column(name="MOIS_2")
	private String mois2;
	
	@Column(name="MOIS_3")
	private String mois3;
	
	@Column(name="MOIS_4")
	private String mois4;
	
	@Column(name="MOIS_5")
	private String mois5;

	@Column(name="MOIS_6")
	private String mois6;
	
	@Column(name="MOIS_7")
	private String mois7;
	
	@Column(name="MOIS_8")
	private String mois8;
	
	@Column(name="MOIS_9")
	private String mois9;
	
	@Column(name="MOIS_10")
	private String mois10;
	
	@Column(name="MOIS_11")
	private String mois11;
	
	@Column(name="MOIS_12")
	private String mois12;
	
	@Column(name="TOTAL")
	private String total;
	
	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_SITUTAION")
	private Date dateSituation;
	
	@Column(name="EQUIPE")
	private String equipe;
	
	@Column(name="MOTIF")
	private String motif;
	
	@Column(name="AUTORISAER")
	private boolean  autoriser;
	
	@Column(name="NBR_JOURS")
	private String nbrJours;
	
	@Column(name="NBR_REPOS")
	private String nbrRepos;
	
	@Column(name="NBR_Absent")
	private String nbrAbsent;
	
	@Column(name="NBR_Absent_Autoriser")
	private String nbrAbsentAutoriser;

	@Column(name="NBR_Absent_NonAutoriser")
	private String nbrAbsentNonAutoriser;


	public String getNbrJours() {
		return nbrJours;
	}

	public void setNbrJours(String nbrJours) {
		this.nbrJours = nbrJours;
	}

	public String getNbrRepos() {
		return nbrRepos;
	}

	public void setNbrRepos(String nbrRepos) {
		this.nbrRepos = nbrRepos;
	}

	public String getNbrAbsent() {
		return nbrAbsent;
	}

	public void setNbrAbsent(String nbrAbsent) {
		this.nbrAbsent = nbrAbsent;
	}

	public void setAutorisation(String autorisation) {
		this.autorisation = autorisation;
	}

	@Transient
	private String autorisation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	

	public String getMois1() {
		return mois1;
	}

	public void setMois1(String mois1) {
		this.mois1 = mois1;
	}

	public String getMois2() {
		return mois2;
	}

	public void setMois2(String mois2) {
		this.mois2 = mois2;
	}

	public String getMois3() {
		return mois3;
	}

	public void setMois3(String mois3) {
		this.mois3 = mois3;
	}

	public String getMois4() {
		return mois4;
	}

	public void setMois4(String mois4) {
		this.mois4 = mois4;
	}

	public String getMois5() {
		return mois5;
	}

	public void setMois5(String mois5) {
		this.mois5 = mois5;
	}

	public String getMois6() {
		return mois6;
	}

	public void setMois6(String mois6) {
		this.mois6 = mois6;
	}

	public String getMois7() {
		return mois7;
	}

	public void setMois7(String mois7) {
		this.mois7 = mois7;
	}

	public String getMois8() {
		return mois8;
	}

	public void setMois8(String mois8) {
		this.mois8 = mois8;
	}

	public String getMois9() {
		return mois9;
	}

	public void setMois9(String mois9) {
		this.mois9 = mois9;
	}

	public String getMois10() {
		return mois10;
	}

	public void setMois10(String mois10) {
		this.mois10 = mois10;
	}

	public String getMois11() {
		return mois11;
	}

	public void setMois11(String mois11) {
		this.mois11 = mois11;
	}

	public String getMois12() {
		return mois12;
	}

	public void setMois12(String mois12) {
		this.mois12 = mois12;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDateSituation() {
		return dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public boolean isAutoriser() {
		return autoriser;
	}

	public void setAutoriser(boolean autoriser) {
		this.autoriser = autoriser;
	}

	public String getAutorisation() {

		
		if(isAutoriser() ==true)
		{
			autorisation="OUI";
		}else
		{
			autorisation="NON";
		}
		
		
		return autorisation;
	
	}

	public String getNbrAbsentAutoriser() {
		return nbrAbsentAutoriser;
	}

	public void setNbrAbsentAutoriser(String nbrAbsentAutoriser) {
		this.nbrAbsentAutoriser = nbrAbsentAutoriser;
	}

	public String getNbrAbsentNonAutoriser() {
		return nbrAbsentNonAutoriser;
	}

	public void setNbrAbsentNonAutoriser(String nbrAbsentNonAutoriser) {
		this.nbrAbsentNonAutoriser = nbrAbsentNonAutoriser;
	}

	
	
	
	
	
	


	
}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="Charge_Production")
@NamedQuery(name="ChargeProduction.findAll", query="SELECT p FROM ChargeProduction p")
public class ChargeProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="CODE")
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_de_saisi")
	private Date datedesaisi;

	@Temporal(TemporalType.DATE)
	@Column(name="date_mise_a_jour")
	private Date dateMiseJours;

	@Column(name="CODE_DEPOT")
	private String codeDepot;

	@Column(name="TOTALE")
	private BigDecimal totale;

		//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateurMAJ;
	
		//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateurCreation;

	@Column(name="TYPE")
	private String type;

	@OneToMany(cascade = CascadeType.ALL,mappedBy="ChargeProduction")
	private List<DetailCoutProduction> ListdetailCoutProductions=new ArrayList<DetailCoutProduction>();
	

	public ChargeProduction() {
	}


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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Date getDatedesaisi() {
		return datedesaisi;
	}


	public void setDatedesaisi(Date datedesaisi) {
		this.datedesaisi = datedesaisi;
	}


	public Date getDateMiseJours() {
		return dateMiseJours;
	}


	public void setDateMiseJours(Date dateMiseJours) {
		this.dateMiseJours = dateMiseJours;
	}


	public String getCodeDepot() {
		return codeDepot;
	}


	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}


	public BigDecimal getTotale() {
		return totale;
	}


	public void setTotale(BigDecimal totale) {
		this.totale = totale;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<DetailCoutProduction> getListdetailCoutProductions() {
		return ListdetailCoutProductions;
	}


	public void setListdetailCoutProductions(
			List<DetailCoutProduction> listdetailCoutProductions) {
		ListdetailCoutProductions = listdetailCoutProductions;
	}



	
	

	
	
}
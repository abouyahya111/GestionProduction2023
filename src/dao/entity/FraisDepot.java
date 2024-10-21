package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CompteMagasinier database table.
 * 
 */
@Entity
@Table(name="Frais_Depot")
@NamedQuery(name="FraisDepot.findAll", query="SELECT m FROM FraisDepot m")
public class FraisDepot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@Column(name="CODE")
	private String code;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="montant_total")
	private BigDecimal montant;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	
	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;

	@ManyToOne
	@JoinColumn(name="id_magasin")
	private Magasin magasin;
	

	//bi-directional many-to-one association to DetailCompteMagasinier
	@OneToMany(cascade = CascadeType.ALL,mappedBy="FraisDepot")
	private List<DetailFraisDepot> DetailFraisDepot=new ArrayList<DetailFraisDepot>();



	public int getId() {
		return id;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public BigDecimal getMontant() {
		return montant;
	}



	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}



	public Date getDateoperation() {
		return dateoperation;
	}



	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}



	public Depot getDepot() {
		return depot;
	}



	public void setDepot(Depot depot) {
		this.depot = depot;
	}



	public Magasin getMagasin() {
		return magasin;
	}



	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}



	public List<DetailFraisDepot> getDetailFraisDepot() {
		return DetailFraisDepot;
	}



	public void setDetailFraisDepot(List<DetailFraisDepot> detailFraisDepot) {
		DetailFraisDepot = detailFraisDepot;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setId(int id) {
		this.id = id;
	}



	
	
	
}
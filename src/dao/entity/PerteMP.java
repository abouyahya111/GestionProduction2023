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


/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="PerteMP.findAll", query="SELECT f FROM PerteMP f")
public class PerteMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	

	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;
	
	
	@ManyToOne
	@JoinColumn(name="id_Magasin")
	private Magasin magasin;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSource;

	@Column(name="ETAT")
	private String etat;
	
	@Column(name="NUM_PERTE")
	private String numPerte;
	
	
	//bi-directional many-to-one association to DetailCommande
	@OneToMany(cascade = CascadeType.ALL,mappedBy="perteMP")
	private List<DetailPerteMP> detailPerteMP;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<DetailPerteMP> getDetailPerteMP() {
		return detailPerteMP;
	}

	public void setDetailPerteMP(List<DetailPerteMP> detailPerteMP) {
		this.detailPerteMP = detailPerteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getNumPerte() {
		return numPerte;
	}

	public void setNumPerte(String numPerte) {
		this.numPerte = numPerte;
	}

	public Magasin getMagasinSource() {
		return magasinSource;
	}

	public void setMagasinSource(Magasin magasinSource) {
		this.magasinSource = magasinSource;
	}

	
	
	
	
	
}
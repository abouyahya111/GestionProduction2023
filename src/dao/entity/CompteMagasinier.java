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
@Table(name="Compte_Magasinier")
@NamedQuery(name="CompteMagasinier.findAll", query="SELECT m FROM CompteMagasinier m")
public class CompteMagasinier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="montant_total")
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn(name="depot")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="magasin")
	private Magasin magasin;

	//bi-directional many-to-one association to DetailCompteMagasinier
	@OneToMany(cascade = CascadeType.ALL,mappedBy="compteMagasinier")
	private List<DetailCompteMagasinier> detailCompteMagasinier=new ArrayList<DetailCompteMagasinier>();



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



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public BigDecimal getMontant() {
		return montant;
	}



	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}



	public List<DetailCompteMagasinier> getDetailCompteMagasinier() {
		return detailCompteMagasinier;
	}



	public void setDetailCompteMagasinier(
			List<DetailCompteMagasinier> detailCompteMagasinier) {
		this.detailCompteMagasinier = detailCompteMagasinier;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
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

	
	
	
	
	
	
}
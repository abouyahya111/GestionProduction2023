package dao.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="MAGASIN_PRODUIT_FINI")
@NamedQuery(name="MagasinProduitFini.findAll", query="SELECT d FROM MagasinProduitFini d")
public class MagasinProduitFini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;
	
	private String libelle;


	

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depotPF;

	public MagasinProduitFini() {
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Depot getDepot() {
		return depotPF;
	}

	public void setDepot(Depot depot) {
		this.depotPF = depot;
	}

	
	

}
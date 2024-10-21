package dao.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="Magasin")
@NamedQuery(name="Magasin.findAll", query="SELECT d FROM Magasin d")
public class Magasin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;
	
	private String libelle;

	private String typeMagasin;
	
	@Column(name="CATEGORIE_MAGASIN") //Magasin de Stockage ou de production
	private String catMagasin;
	
	@Column(name="CODE_MACHINE")
	private String codeMachine;

	

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	// Client ou Interne
		private String type;

	public Magasin() {
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
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getTypeMagasin() {
		return typeMagasin;
	}

	public void setTypeMagasin(String typeMagasin) {
		this.typeMagasin = typeMagasin;
	}

	public String getCodeMachine() {
		return codeMachine;
	}

	public void setCodeMachine(String codeMachine) {
		this.codeMachine = codeMachine;
	}

	public String getCatMagasin() {
		return catMagasin;
	}

	public void setCatMagasin(String catMagasin) {
		this.catMagasin = catMagasin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

}
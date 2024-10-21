package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the machine database table.
 * 
 */
@Entity
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String matricule;

	private String nom;
	
	private String codeDepot;

	@Column(name="stock_solofane")
	private BigDecimal stockSolofane;
	
	

/*	//bi-directional many-to-one association to Production
	@OneToMany(mappedBy="machine")
	private List<Production> productions;*/
	
	//bi-directional many-to-one association to RipFournisseur
		@OneToMany(cascade = CascadeType.ALL, mappedBy="machine")
		private List<LigneMachine> ListLigneMachine;

	public Machine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getStockSolofane() {
		return this.stockSolofane;
	}

	public void setStockSolofane(BigDecimal stockSolofane) {
		this.stockSolofane = stockSolofane;
	}

	

	public LigneMachine addProduction(LigneMachine ligneMachine) {
		getListLigneMachine().add(ligneMachine);
		ligneMachine.setMachine(this);

		return ligneMachine;
	}

	public LigneMachine removeProduction(LigneMachine ligneMachine) {
		getListLigneMachine().remove(ligneMachine);
		ligneMachine.setMachine(null);

		return ligneMachine;
	}

	public List<LigneMachine> getListLigneMachine() {
		return ListLigneMachine;
	}

	public void setListLigneMachine(List<LigneMachine> listLigneMachine) {
		ListLigneMachine = listLigneMachine;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}



}
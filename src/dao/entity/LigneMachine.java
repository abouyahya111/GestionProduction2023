package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="ligne_machine")
@NamedQuery(name="LigneMachine.findAll", query="SELECT d FROM LigneMachine d")
public class LigneMachine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nom;
	
	private String matricule;
	
	@Column(name="stock_solofane")
	private BigDecimal stockSolofane;
	
	@Column(name="stock_centure")
	private BigDecimal stockCenture;
	
	@Column(name="stock_skoutch")
	private BigDecimal stockStoutch;

	

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_MACHINE")
	private Machine machine;

	public LigneMachine() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public BigDecimal getStockSolofane() {
		return stockSolofane;
	}

	public void setStockSolofane(BigDecimal stockSolofane) {
		this.stockSolofane = stockSolofane;
	}

	public BigDecimal getStockCenture() {
		return stockCenture;
	}

	public void setStockCenture(BigDecimal stockCenture) {
		this.stockCenture = stockCenture;
	}

	public BigDecimal getStockStoutch() {
		return stockStoutch;
	}

	public void setStockStoutch(BigDecimal stockStoutch) {
		this.stockStoutch = stockStoutch;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	

}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the machine database table.
 * 
 */
@Entity
@NamedQuery(name="Equipe.findAll", query="SELECT m FROM Equipe m")
public class Equipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="code_Equipe")
	private String codeEquipe;

	@Column(name="nom_Equipe")
	private String nomEquipe;
	
	@Column(name="REMISE")
	private BigDecimal remise;
	
	
	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="TYPE_EQUIPE")
	private TypeEquipe typeEquipe;
	
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	//bi-directional many-to-one association to RipFournisseur
		@OneToMany(cascade = CascadeType.ALL, mappedBy="equipe")
		private List<Employe> ListEmploye=new ArrayList<Employe>();

	public Equipe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeEquipe() {
		return codeEquipe;
	}

	public void setCodeEquipe(String codeEquipe) {
		this.codeEquipe = codeEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public List<Employe> getListEmploye() {
		return ListEmploye;
	}

	public void setListEmploye(List<Employe> listEmploye) {
		ListEmploye = listEmploye;
	}

	public Employe addEmploye(Employe employe) {
		getListEmploye().add(employe);
		employe.setEquipe(this);

		return employe;
	}

	public Employe removeEmploye(Employe employe) {
		getListEmploye().remove(employe);
		employe.setEquipe(null);

		return employe;
	}

	public TypeEquipe getTypeEquipe() {
		return typeEquipe;
	}

	public void setTypeEquipe(TypeEquipe typeEquipe) {
		this.typeEquipe = typeEquipe;
	}

	public BigDecimal getRemise() {
		return remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}


}
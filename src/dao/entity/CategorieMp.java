package dao.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the categorie_mp database table.
 * 
 */
@Entity
@Table(name="categorie_mp")
@NamedQuery(name="CategorieMp.findAll", query="SELECT c FROM CategorieMp c")
public class CategorieMp implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;

	//bi-directional many-to-one association to SubCategorieMp
	@ManyToOne
	@JoinColumn(name="id_sub_cat")
	private SubCategorieMp subCategorieMp;

	//bi-directional many-to-one association to MatierePremier
	@OneToMany(cascade = CascadeType.ALL,mappedBy="categorieMp")
	private List<MatierePremier> matierePremiers;

	public CategorieMp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public SubCategorieMp getSubCategorieMp() {
		return this.subCategorieMp;
	}

	public void setSubCategorieMp(SubCategorieMp subCategorieMp) {
		this.subCategorieMp = subCategorieMp;
	}

	public List<MatierePremier> getMatierePremiers() {
		return this.matierePremiers;
	}

	public void setMatierePremiers(List<MatierePremier> matierePremiers) {
		this.matierePremiers = matierePremiers;
	}

	public MatierePremier addMatierePremier(MatierePremier matierePremier) {
		getMatierePremiers().add(matierePremier);
		matierePremier.setCategorieMp(this);

		return matierePremier;
	}

	public MatierePremier removeMatierePremier(MatierePremier matierePremier) {
		getMatierePremiers().remove(matierePremier);
		matierePremier.setCategorieMp(null);

		return matierePremier;
	}

}
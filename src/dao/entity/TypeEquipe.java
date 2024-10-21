package dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@Table(name="TYPE_EQUIPE")
//@NamedQuery(name="TypeEquipe.findAll", query="SELECT f FROM TypeEquipe f")
public class TypeEquipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE")
	private String code;
	
	@Column(name="LIBELLE")
	private String libelle ;
	
	@Column(name="SEQUENCE")
	private int sequence ;
	
	//bi-directional many-to-one association to MatierePremier
			@OneToMany(cascade = CascadeType.ALL,mappedBy="typeEquipe")
			private List<Equipe> ListEquipe;

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public List<Equipe> getListEquipe() {
		return ListEquipe;
	}

	public void setListEquipe(List<Equipe> listEquipe) {
		ListEquipe = listEquipe;
	}

	public Equipe addMatierePremier(Equipe equipe) {
		getListEquipe().add(equipe);
		equipe.setTypeEquipe(this);

		return equipe;
	}

	public Equipe removeMatierePremier(Equipe equipe) {
		getListEquipe().remove(equipe);
		equipe.setTypeEquipe(null);

		return equipe;
	}

	

}
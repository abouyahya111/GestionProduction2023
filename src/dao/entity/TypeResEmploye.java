package dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the devise database table.
 * 
 */
@Entity
@Table(name="TYPE_RES_EMPLOYE")
public class TypeResEmploye implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;
	
	private String libelle;
	
	//bi-directional many-to-one association to MatierePremier
		@OneToMany(cascade = CascadeType.ALL,mappedBy="typeResEmploye")
		private List<Employe> ListEmploye;


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

	public List<Employe> getListEmploye() {
		return ListEmploye;
	}

	public void setListEmploye(List<Employe> listEmploye) {
		ListEmploye = listEmploye;
	}
}

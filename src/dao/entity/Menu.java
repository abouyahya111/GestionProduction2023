package dao.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the sub_categorie_mp database table.
 * 
 */
@Entity
@Table(name="Menu")
@NamedQuery(name="Menu.findAll", query="SELECT s FROM Menu s")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String libelle;
	
	private String type;

	@Column(name="CODE_PARENT")
	private String codeParent;


	public Menu() {
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	

}
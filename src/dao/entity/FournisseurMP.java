package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="FournisseurMP.findAll", query="SELECT f FROM FournisseurMP f")
public class FournisseurMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_FOURNISSEUR")
	private String codeFournisseur;
	
	@Column(name="NOM")
	private String nom ;
	
	@Column(name="ADRESSE")
	private String adresse ;
	
	@ManyToOne
	@JoinColumn(name="id_sub_cat")
	private SubCategorieMp subCategorieMp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SubCategorieMp getSubCategorieMp() {
		return subCategorieMp;
	}

	public void setSubCategorieMp(SubCategorieMp subCategorieMp) {
		this.subCategorieMp = subCategorieMp;
	}

	
	
	
}
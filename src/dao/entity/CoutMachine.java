package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the detail_commande database table.
 * 
 */

@Entity
@Table(name="Cout_Machine")
@NamedQuery(name="CoutMachine.findAll", query="SELECT d FROM CoutMachine d")
public class CoutMachine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;	
	
	@ManyToOne
	@JoinColumn(name="id_FormeParBox")
	private FormeParBox formeParBox;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private CategorieMp categorie;
	
	@Column(name="tonnage")
	private  BigDecimal tonnage;
	
	@Column(name="nombre_personne")
	private  BigDecimal nombrePersonne;
	
	@Column(name="COUT")
	private  BigDecimal cout;

	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;
	
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModifier;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FormeParBox getFormeParBox() {
		return formeParBox;
	}

	public void setFormeParBox(FormeParBox formeParBox) {
		this.formeParBox = formeParBox;
	}

	public BigDecimal getTonnage() {
		return tonnage;
	}

	public void setTonnage(BigDecimal tonnage) {
		this.tonnage = tonnage;
	}

	public BigDecimal getNombrePersonne() {
		return nombrePersonne;
	}

	public void setNombrePersonne(BigDecimal nombrePersonne) {
		this.nombrePersonne = nombrePersonne;
	}

	public BigDecimal getCout() {
		return cout;
	}

	public void setCout(BigDecimal cout) {
		this.cout = cout;
	}

	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
	}

	public Date getDateCreer() {
		return dateCreer;
	}

	public void setDateCreer(Date dateCreer) {
		this.dateCreer = dateCreer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CategorieMp getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieMp categorie) {
		this.categorie = categorie;
	}

	 
	
	
	
	
	

}
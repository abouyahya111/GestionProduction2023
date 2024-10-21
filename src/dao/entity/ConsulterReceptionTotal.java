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
@NamedQuery(name="ConsulterReceptionTotal.findAll", query="SELECT f FROM ConsulterReceptionTotal f")
public class ConsulterReceptionTotal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn(name="id_Fournisseur" )
	private FournisseurMP fournisseur;
	
	public FournisseurMP getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurMP fournisseur) {
		this.fournisseur = fournisseur;
	}

	private BigDecimal quantiteTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public BigDecimal getQuantiteTotal() {
		return quantiteTotal;
	}

	public void setQuantiteTotal(BigDecimal quantiteTotal) {
		this.quantiteTotal = quantiteTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 
	


}
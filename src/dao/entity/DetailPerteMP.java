package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="DETAIL_PERTE_MP")
@NamedQuery(name="DetailPerteMP.findAll", query="SELECT d FROM DetailPerteMP d")
public class DetailPerteMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn( name="id_motif_perte")
	private MotifPerteMP motifPerteMP;
	
	
	@ManyToOne
	@JoinColumn( name="id_fournisseurMP")
	private FournisseurMP fournisseurMP;
	
	private BigDecimal quantite;
	private BigDecimal prix;
	private BigDecimal montant;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_PERTE_MP")
	private PerteMP perteMP;

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

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public PerteMP getPerteMP() {
		return perteMP;
	}

	public void setPerteMP(PerteMP perteMP) {
		this.perteMP = perteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MotifPerteMP getMotifPerteMP() {
		return motifPerteMP;
	}

	public void setMotifPerteMP(MotifPerteMP motifPerteMP) {
		this.motifPerteMP = motifPerteMP;
	}

	

}
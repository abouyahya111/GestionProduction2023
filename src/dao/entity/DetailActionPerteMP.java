package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="DETAIL_ACTION_PERTE_MP")
@NamedQuery(name="DetailActionPerteMP.findAll", query="SELECT d FROM DetailActionPerteMP d")
public class DetailActionPerteMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn( name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn( name="id_action_perte")
	private ActionMP actionMP;
	
	
	@ManyToOne
	@JoinColumn( name="id_fournisseurMP")
	private FournisseurMP fournisseurMP;
	
	private BigDecimal quantite;
	private BigDecimal prix;
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn( name="id_compteMagasinier")
	private CompteMagasinier compteMagasinier;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_ACTION_PERTE_MP")
	private ActionPerteMP actionperteMP;
	
	
	@ManyToOne
	@JoinColumn(name="ID_DETAIL_PERTE_MP")
	private DetailPerteMP detailPerteMP;
	
	
	

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


	public ActionPerteMP getActionperteMP() {
		return actionperteMP;
	}

	public void setActionperteMP(ActionPerteMP actionperteMP) {
		this.actionperteMP = actionperteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ActionMP getActionMP() {
		return actionMP;
	}

	public void setActionMP(ActionMP actionMP) {
		this.actionMP = actionMP;
	}

	

	public CompteMagasinier getCompteMagasinier() {
		return compteMagasinier;
	}

	public void setCompteMagasinier(CompteMagasinier compteMagasinier) {
		this.compteMagasinier = compteMagasinier;
	}

	public DetailPerteMP getDetailPerteMP() {
		return detailPerteMP;
	}

	public void setDetailPerteMP(DetailPerteMP detailPerteMP) {
		this.detailPerteMP = detailPerteMP;
	}




	

}
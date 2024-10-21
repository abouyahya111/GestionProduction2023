package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detail_dechet_fournisseur")
@NamedQuery(name="DetailManqueDechetFournisseur.findAll", query="SELECT d FROM DetailManqueDechetFournisseur d")
public class DetailManqueDechetFournisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Commande
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_dechetfournisseur")
	private ManqueDechetFournisseur manquedechetfournisseur;
	
	@Column(name="ETAT")
	private String etat;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fourniseur;
	
    private BigDecimal quantiteManque;
	
	private BigDecimal quantiteDechet;
	private BigDecimal quantitePlus;
	
	private BigDecimal prix;
	
	@ManyToOne
	@JoinColumn(name="id_magasindechet")
	private Magasin magasinDechet;

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

	




	public ManqueDechetFournisseur getManquedechetfournisseur() {
		return manquedechetfournisseur;
	}

	public void setManquedechetfournisseur(ManqueDechetFournisseur manquedechetfournisseur) {
		this.manquedechetfournisseur = manquedechetfournisseur;
	}

	public BigDecimal getQuantiteManque() {
		return quantiteManque;
	}

	public void setQuantiteManque(BigDecimal quantiteManque) {
		this.quantiteManque = quantiteManque;
	}

	public BigDecimal getQuantiteDechet() {
		return quantiteDechet;
	}

	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		this.quantiteDechet = quantiteDechet;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FournisseurMP getFourniseur() {
		return fourniseur;
	}

	public void setFourniseur(FournisseurMP fourniseur) {
		this.fourniseur = fourniseur;
	}

	public Magasin getMagasinDechet() {
		return magasinDechet;
	}

	public void setMagasinDechet(Magasin magasinDechet) {
		this.magasinDechet = magasinDechet;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getQuantitePlus() {
		return quantitePlus;
	}

	public void setQuantitePlus(BigDecimal quantitePlus) {
		this.quantitePlus = quantitePlus;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	
	

}
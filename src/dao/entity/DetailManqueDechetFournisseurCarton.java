package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detail_dechet_fournisseur_carton")
@NamedQuery(name="DetailManqueDechetFournisseurCarton.findAll", query="SELECT d FROM DetailManqueDechetFournisseurCarton d")
public class DetailManqueDechetFournisseurCarton implements Serializable {
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
	private ManqueDechetFournisseurCarton manquedechetfournisseurcarton;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurAdhesive fourniseurAdhesive;
	
    private BigDecimal quantiteManque;
	
	private BigDecimal quantiteDechet;
	
	private BigDecimal quantitePlus;
	
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

	




	public ManqueDechetFournisseurCarton getManquedechetfournisseurcarton() {
		return manquedechetfournisseurcarton;
	}

	public void setManquedechetfournisseurcarton(ManqueDechetFournisseurCarton manquedechetfournisseurcarton) {
		this.manquedechetfournisseurcarton = manquedechetfournisseurcarton;
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

	public FournisseurAdhesive getFourniseurAdhesive() {
		return fourniseurAdhesive;
	}

	public void setFourniseurAdhesive(FournisseurAdhesive fourniseurAdhesive) {
		this.fourniseurAdhesive = fourniseurAdhesive;
	}

	public Magasin getMagasinDechet() {
		return magasinDechet;
	}

	public void setMagasinDechet(Magasin magasinDechet) {
		this.magasinDechet = magasinDechet;
	}

	public BigDecimal getQuantitePlus() {
		return quantitePlus;
	}

	public void setQuantitePlus(BigDecimal quantitePlus) {
		this.quantitePlus = quantitePlus;
	}


	
	
	

}
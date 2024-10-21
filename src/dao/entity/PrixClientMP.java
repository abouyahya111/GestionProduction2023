package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@Table(name="PRIX_CLIENT_MP")
@NamedQuery(name="PrixClientMP.findAll", query="SELECT s FROM PrixClientMP s")
public class PrixClientMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="PRIX_UNITAIRE")
	private BigDecimal prixUnitaire;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_MAT_PREM")
	private MatierePremier matierePremier;
	
	//bi-directional many-to-one association to MatierePremier
		@ManyToOne
		@JoinColumn(name="ID_CLIENT")
		private Client client;
		
		//bi-directional many-to-one association to MatierePremier
				@ManyToOne
				@JoinColumn(name="ID_FOURNISSEUR")
				private Client fournisseur;

	public PrixClientMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Client fournisseur) {
		this.fournisseur = fournisseur;
	}


}
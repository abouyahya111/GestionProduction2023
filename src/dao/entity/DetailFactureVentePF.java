package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="DETAIL_FACTURE_VENTE_PF")
@NamedQuery(name="DetailFactureVentePF.findAll", query="SELECT d FROM DetailFactureVentePF d")
public class DetailFactureVentePF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="PRIX_UNITAIRE")
	private BigDecimal prixUnitaire;

	@Column(name="QUANTITE")
	private BigDecimal quantite;
	
	@Column(name="MONTANT_TOTAL")
	private BigDecimal montantTotal;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_MAT_PREM")
	private Articles article;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="ID_FACTURE_VENTE")
	private FactureVentePF factureVentePF;

	public DetailFactureVentePF() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getQuantite() {
		return this.quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	

	public BigDecimal getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public FactureVentePF getFactureVentePF() {
		return factureVentePF;
	}

	public void setFactureVentePF(FactureVentePF factureVentePF) {
		this.factureVentePF = factureVentePF;
	}



	

}
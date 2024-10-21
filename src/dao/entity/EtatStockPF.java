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
@NamedQuery(name="EtatStockPF.findAll", query="SELECT f FROM EtatStockPF f")
public class EtatStockPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	
	// Stock Initial
	
	@Column(name="quantite_initial")
	private BigDecimal quantiteInitial;

	@Column(name="prix_initial")
	private BigDecimal prixInitial;
	
	@Column(name="montant_initial")
	private BigDecimal montantInitial;
	
	// Achat
	
	@Column(name="quantite_achat")
	private BigDecimal quantiteAchat;

	@Column(name="prix_achat")
	private BigDecimal prixAchat;
		
	@Column(name="montant_achat")
	private BigDecimal montantAchat;
	
	// Production
	
		@Column(name="quantite_production")
		private BigDecimal quantiteProduction;

		@Column(name="prix_production")
		private BigDecimal prixProduction;
			
		@Column(name="montant_production")
		private BigDecimal montantProduction;
		
		
		// Transfer Entrer
		
		@Column(name="quantite_entrer")
		private BigDecimal quantiteEntrer;

		@Column(name="prix_entrer")
		private BigDecimal prixEntrer;
				
		@Column(name="montant_entrer")
		private BigDecimal montantEntrer;
		
	
		
	// Sortie
		
	@Column(name="quantite_sortie")
	private BigDecimal quantiteSortie;

	@Column(name="prix_sortie")
	private BigDecimal prixSortie;
			
	@Column(name="montant_sortie")
	private BigDecimal montantSortie;
	
	// avoir (retour) Facture Avoire PF
	
			@Column(name="quantite_avoir")
			private BigDecimal quantiteAvoir;

			@Column(name="prix_avoire")
			private BigDecimal prixAvoir;
					
			@Column(name="montant_Avoir")
			private BigDecimal montantAvoir;
	
	// Stock Finale
		
	@Column(name="quantite_finale")
	private BigDecimal quantiteFinale;

	@Column(name="prix_Finale")
	private BigDecimal prixFinale;
	
	@Column(name="montant_Finale")
	private BigDecimal montantFinale;
	
	
	@Column(name="achat_revendu")
	private BigDecimal achatRevendu;
	
	
	@Column(name="la_marge")
	private BigDecimal marge;
	
	@Column(name="la_marge_pourcentage")
	private BigDecimal margePourcentage;

	public BigDecimal getMarge() {
		return marge;
	}

	public void setMarge(BigDecimal marge) {
		this.marge = marge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public BigDecimal getQuantiteInitial() {
		return quantiteInitial;
	}

	public void setQuantiteInitial(BigDecimal quantiteInitial) {
		this.quantiteInitial = quantiteInitial;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public BigDecimal getMontantInitial() {
		return montantInitial;
	}

	public void setMontantInitial(BigDecimal montantInitial) {
		this.montantInitial = montantInitial;
	}

	public BigDecimal getQuantiteAchat() {
		return quantiteAchat;
	}

	public void setQuantiteAchat(BigDecimal quantiteAchat) {
		this.quantiteAchat = quantiteAchat;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public BigDecimal getMontantAchat() {
		return montantAchat;
	}

	public void setMontantAchat(BigDecimal montantAchat) {
		this.montantAchat = montantAchat;
	}

	public BigDecimal getQuantiteSortie() {
		return quantiteSortie;
	}

	public void setQuantiteSortie(BigDecimal quantiteSortie) {
		this.quantiteSortie = quantiteSortie;
	}

	public BigDecimal getPrixSortie() {
		return prixSortie;
	}

	public void setPrixSortie(BigDecimal prixSortie) {
		this.prixSortie = prixSortie;
	}

	public BigDecimal getMontantSortie() {
		return montantSortie;
	}

	public void setMontantSortie(BigDecimal montantSortie) {
		this.montantSortie = montantSortie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantiteProduction() {
		return quantiteProduction;
	}

	public void setQuantiteProduction(BigDecimal quantiteProduction) {
		this.quantiteProduction = quantiteProduction;
	}

	public BigDecimal getPrixProduction() {
		return prixProduction;
	}

	public void setPrixProduction(BigDecimal prixProduction) {
		this.prixProduction = prixProduction;
	}

	public BigDecimal getMontantProduction() {
		return montantProduction;
	}

	public void setMontantProduction(BigDecimal montantProduction) {
		this.montantProduction = montantProduction;
	}

	public BigDecimal getQuantiteFinale() {
		return quantiteFinale;
	}

	public void setQuantiteFinale(BigDecimal quantiteSFinale) {
		this.quantiteFinale = quantiteSFinale;
	}

	public BigDecimal getPrixFinale() {
		return prixFinale;
	}

	public void setPrixFinale(BigDecimal prixFinale) {
		this.prixFinale = prixFinale;
	}

	public BigDecimal getMontantFinale() {
		return montantFinale;
	}

	public void setMontantFinale(BigDecimal montantFinale) {
		this.montantFinale = montantFinale;
	}

	public BigDecimal getQuantiteAvoir() {
		return quantiteAvoir;
	}

	public void setQuantiteAvoir(BigDecimal quantiteAvoir) {
		this.quantiteAvoir = quantiteAvoir;
	}

	public BigDecimal getPrixAvoir() {
		return prixAvoir;
	}

	public void setPrixAvoir(BigDecimal prixAvoir) {
		this.prixAvoir = prixAvoir;
	}

	public BigDecimal getMontantAvoir() {
		return montantAvoir;
	}

	public void setMontantAvoir(BigDecimal montantAvoir) {
		this.montantAvoir = montantAvoir;
	}

	

	public BigDecimal getQuantiteEntrer() {
		return quantiteEntrer;
	}

	public void setQuantiteEntrer(BigDecimal quantiteEntrer) {
		this.quantiteEntrer = quantiteEntrer;
	}

	public BigDecimal getPrixEntrer() {
		return prixEntrer;
	}

	public void setPrixEntrer(BigDecimal prixEntrer) {
		this.prixEntrer = prixEntrer;
	}

	public BigDecimal getMontantEntrer() {
		return montantEntrer;
	}

	public void setMontantEntrer(BigDecimal montantEntrer) {
		this.montantEntrer = montantEntrer;
	}

	

	public BigDecimal getAchatRevendu() {
		return achatRevendu;
	}

	public void setAchatRevendu(BigDecimal achatRevendu) {
		this.achatRevendu = achatRevendu;
	}

	public BigDecimal getMargePourcentage() {
		return margePourcentage;
	}

	public void setMargePourcentage(BigDecimal margePourcentage) {
		this.margePourcentage = margePourcentage;
	}

	

}
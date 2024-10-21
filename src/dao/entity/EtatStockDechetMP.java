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
@NamedQuery(name="EtatStocDechetkMP.findAll", query="SELECT f FROM EtatStockDechetMP f")
public class EtatStockDechetMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP;
	
	// Stock reception
	
	@Column(name="quantite_initial")
	private BigDecimal quantiteInitial;

	
	// Stock reception
	
	@Column(name="quantite_reception")
	private BigDecimal quantiteReception;

	// Dechet Production
	
	@Column(name="quantite_dechet")
	private BigDecimal quantiteDechet;
	
	// Dechet Fournisseur 
	@Column(name="quantite_dechet_fournisseur")
	private BigDecimal quantiteDechetFournisseur;
	
	
	// perte
	
	@Column(name="quantite_perte")
	private BigDecimal quantitePerte;
	
	
	// vente
	
	
	@Column(name="quantite_vente")
	private BigDecimal quantiteVente;
	
	
	// Dechet Fournisseur En Attent
		@Column(name="quantite_dechet_fournisseur_en_attent")
		private BigDecimal quantiteDechetFournisseurEnAttent;
		
		
		// Dechet Fournisseur Annulé
				@Column(name="quantite_dechet_fournisseur_annule")
				private BigDecimal quantiteDechetFournisseurAnnule;
				
				
				// Dechet Fournisseur Définitive
				@Column(name="quantite_dechet_fournisseur_definitive")
				private BigDecimal quantiteDechetFournisseurDefinitive;
	

		// Autres Sortie
			@Column(name="autres_sortie")
			private BigDecimal quantiteautresSortie;
	
	// Stock Finale
	@Column(name="quantite_finale")
	private BigDecimal quantiteFinale;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public MatierePremier getMp() {
		return mp;
	}



	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}



	public BigDecimal getQuantiteInitial() {
		return quantiteInitial;
	}



	public void setQuantiteInitial(BigDecimal quantiteInitial) {
		this.quantiteInitial = quantiteInitial;
	}



	public BigDecimal getQuantiteReception() {
		return quantiteReception;
	}



	public void setQuantiteReception(BigDecimal quantiteReception) {
		this.quantiteReception = quantiteReception;
	}



	public BigDecimal getQuantiteDechet() {
		return quantiteDechet;
	}



	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		this.quantiteDechet = quantiteDechet;
	}



	public BigDecimal getQuantiteDechetFournisseur() {
		return quantiteDechetFournisseur;
	}



	public void setQuantiteDechetFournisseur(BigDecimal quantiteDechetFournisseur) {
		this.quantiteDechetFournisseur = quantiteDechetFournisseur;
	}



	public BigDecimal getQuantitePerte() {
		return quantitePerte;
	}



	public void setQuantitePerte(BigDecimal quantitePerte) {
		this.quantitePerte = quantitePerte;
	}



	public BigDecimal getQuantiteVente() {
		return quantiteVente;
	}



	public void setQuantiteVente(BigDecimal quantiteVente) {
		this.quantiteVente = quantiteVente;
	}



	public BigDecimal getQuantiteFinale() {
		return quantiteFinale;
	}



	public void setQuantiteFinale(BigDecimal quantiteFinale) {
		this.quantiteFinale = quantiteFinale;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}



	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}



	public BigDecimal getQuantiteDechetFournisseurEnAttent() {
		return quantiteDechetFournisseurEnAttent;
	}



	public void setQuantiteDechetFournisseurEnAttent(BigDecimal quantiteDechetFournisseurEnAttent) {
		this.quantiteDechetFournisseurEnAttent = quantiteDechetFournisseurEnAttent;
	}



	public BigDecimal getQuantiteautresSortie() {
		return quantiteautresSortie;
	}



	public void setQuantiteautresSortie(BigDecimal quantiteautresSortie) {
		this.quantiteautresSortie = quantiteautresSortie;
	}



	public BigDecimal getQuantiteDechetFournisseurAnnule() {
		return quantiteDechetFournisseurAnnule;
	}



	public void setQuantiteDechetFournisseurAnnule(BigDecimal quantiteDechetFournisseurAnnule) {
		this.quantiteDechetFournisseurAnnule = quantiteDechetFournisseurAnnule;
	}



	public BigDecimal getQuantiteDechetFournisseurDefinitive() {
		return quantiteDechetFournisseurDefinitive;
	}



	public void setQuantiteDechetFournisseurDefinitive(BigDecimal quantiteDechetFournisseurDefinitive) {
		this.quantiteDechetFournisseurDefinitive = quantiteDechetFournisseurDefinitive;
	}



	
	
	
	
	
	
}
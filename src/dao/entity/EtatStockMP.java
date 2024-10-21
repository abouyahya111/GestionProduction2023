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
@NamedQuery(name="EtatStockMP.findAll", query="SELECT f FROM EtatStockMP f")
public class EtatStockMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseurMP")
	private FournisseurMP fournisseurMP;
	
	
	// Stock reception
	
	@Column(name="quantite_initial")
	private BigDecimal quantiteInitial;

	@Column(name="prix_initial")
	private BigDecimal prixInitial;
	
	
	// Stock reception
	
	@Column(name="quantite_reception")
	private BigDecimal quantiteReception;

	// Dechet Production
	
	@Column(name="quantite_dechet")
	private BigDecimal quantiteDechet;
	
	// Offre Production 
	@Column(name="quantite_offre_production")
	private BigDecimal quantiteOffreProduction;
	
	
	// Charge
	
	@Column(name="quantite_charger")
	private BigDecimal quantiteCharger;
	
	
	// Quantite Rester à la Machine
	
		@Column(name="quantite_charger")
		private BigDecimal quantiteResterMachine;
	
	
	// ChargeSupp
	
	
	@Column(name="quantite_chargerSupp")
	private BigDecimal quantiteChargerSupp;
	
	// Entrer
	
	@Column(name="quantite_entrer")
	private BigDecimal quantiteEntrer;
	
	// Fabrique
	
		@Column(name="quantite_fabriquer")
		private BigDecimal quantiteFabriquer;
	
	// Transfert Sortie
	@Column(name="transfer_sortie")
	private BigDecimal transferSortie;
	
	// Transfert Entrer
		@Column(name="transfer_entrer")
		private BigDecimal transferEntrer;

	// Sortie
	@Column(name="quantite_sortie")
	private BigDecimal quantiteSortie;
		
	// Retour
	@Column(name="quantite_retour")
	private BigDecimal quantiteRetour;
	
	// Existante
		@Column(name="quantite_existante")
		private BigDecimal quantiteExistante;
	
	// Autres Sortie
		@Column(name="quantite_Autre_sortie")
		private BigDecimal quantiteAutreSortie;
	
		@Column(name="anne")
		private int anne;
	
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

	public BigDecimal getQuantiteReception() {
		return quantiteReception;
	}

	public void setQuantiteReception(BigDecimal quantiteReception) {
		this.quantiteReception = quantiteReception;
	}

	public BigDecimal getQuantiteEntrer() {
		return quantiteEntrer;
	}

	public void setQuantiteEntrer(BigDecimal quantiteEntrer) {
		this.quantiteEntrer = quantiteEntrer;
	}

	public BigDecimal getQuantiteSortie() {
		return quantiteSortie;
	}

	public void setQuantiteSortie(BigDecimal quantiteSortie) {
		this.quantiteSortie = quantiteSortie;
	}

	public BigDecimal getQuantiteRetour() {
		return quantiteRetour;
	}

	public void setQuantiteRetour(BigDecimal quantiteRetour) {
		this.quantiteRetour = quantiteRetour;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantiteDechet() {
		return quantiteDechet;
	}

	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		this.quantiteDechet = quantiteDechet;
	}

	public BigDecimal getQuantiteOffreProduction() {
		return quantiteOffreProduction;
	}

	public void setQuantiteOffreProduction(BigDecimal quantiteOffreProduction) {
		this.quantiteOffreProduction = quantiteOffreProduction;
	}

	public BigDecimal getQuantiteFinale() {
		return quantiteFinale;
	}

	public void setQuantiteFinale(BigDecimal quantiteFinale) {
		this.quantiteFinale = quantiteFinale;
	}

	public BigDecimal getQuantiteCharger() {
		return quantiteCharger;
	}

	public void setQuantiteCharger(BigDecimal quantiteCharger) {
		this.quantiteCharger = quantiteCharger;
	}

	public BigDecimal getQuantiteChargerSupp() {
		return quantiteChargerSupp;
	}

	public void setQuantiteChargerSupp(BigDecimal quantiteChargerSupp) {
		this.quantiteChargerSupp = quantiteChargerSupp;
	}

	public BigDecimal getQuantiteInitial() {
		return quantiteInitial;
	}

	public void setQuantiteInitial(BigDecimal quantiteInitial) {
		this.quantiteInitial = quantiteInitial;
	}

	public BigDecimal getTransferSortie() {
		return transferSortie;
	}

	public void setTransferSortie(BigDecimal transferSortie) {
		this.transferSortie = transferSortie;
	}

	public BigDecimal getTransferEntrer() {
		return transferEntrer;
	}

	public void setTransferEntrer(BigDecimal transferEntrer) {
		this.transferEntrer = transferEntrer;
	}

	public BigDecimal getQuantiteAutreSortie() {
		return quantiteAutreSortie;
	}

	public void setQuantiteAutreSortie(BigDecimal quantiteAutreSortie) {
		this.quantiteAutreSortie = quantiteAutreSortie;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}

	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}

	public BigDecimal getQuantiteResterMachine() {
		return quantiteResterMachine;
	}

	public void setQuantiteResterMachine(BigDecimal quantiteResterMachine) {
		this.quantiteResterMachine = quantiteResterMachine;
	}

	public BigDecimal getQuantiteFabriquer() {
		return quantiteFabriquer;
	}

	public void setQuantiteFabriquer(BigDecimal quantiteFabriquer) {
		this.quantiteFabriquer = quantiteFabriquer;
	}

	public BigDecimal getQuantiteExistante() {
		return quantiteExistante;
	}

	public void setQuantiteExistante(BigDecimal quantiteExistante) {
		this.quantiteExistante = quantiteExistante;
	}

	public int getAnne() {
		return anne;
	}

	public void setAnne(int anne) {
		this.anne = anne;
	}

	
	

}
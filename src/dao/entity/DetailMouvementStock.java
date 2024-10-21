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
import javax.persistence.Table;
import javax.persistence.Temporal;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@Table(name ="Detail_Mouvement_Stock")
@NamedQuery(name = "DetailMouvementStock.findAll", query = "SELECT c FROM DetailMouvementStock c")
public class DetailMouvementStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ID_MATIER_PREMIERE")
    private MatierePremier matierePremier;
    
    @Column(name = "INITIAL")
    private BigDecimal initial;
        
    @Column(name = "PRIX_INITIAL")
    private BigDecimal prixInitial;
      
        @Column(name = "PRODUCTION")
    private BigDecimal production;
        
        @Column(name = "DECHET")
        private BigDecimal dechet;
        
        @Column(name = "OFFRE_PRODUCTION")
        private BigDecimal offreProduction;
        
        
        @Column(name = "Service")
        private BigDecimal service;
        
        @Column(name = "DECHET_SERVICE")
        private BigDecimal dechetService;
        
        @Column(name = "TRANSFERT")
        private BigDecimal transfert;
        
        @Column(name = "OFFRE_SERVICE")
        private BigDecimal offreService;
     
        @Column(name = "vente")
    private BigDecimal vente;
       
        @Column(name = "AVOIR")
        private BigDecimal avoir; 
        
        @Column(name = "STOCK_FINAL")
    private BigDecimal stockFinaldb; 
        
        @Column(name = "PRIX_FINAL")
        private BigDecimal prixFinaldb; 
        
     @Column(name = "ACHAT")
     private BigDecimal achat;
     
     @Column(name = "PRIX_ACHAT")
     private BigDecimal prixAchat;
      
     @Column(name = "Retour")
    private BigDecimal retour;
     
   
     @Column(name = "Num_OF")
     private String numOF;
        
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ID_MOUVEMENT_STOCK_GLOBAL")
    private MouvementStockGlobal mouvementStockGlobal;
     
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateMaj;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;

    
  /////////////////////////////////////////////////
    @Column(name = "reception")
    private BigDecimal reception;
        
  
    
    @Column(name = "entrer")
    private BigDecimal entrer;
        
    
    @Column(name = "sortie")
    private BigDecimal sortie;
    
    @Column(name = "magasin_destination")
    private Magasin magasinDestination;
    
    @Column(name = "magasin_source")
    private Magasin magasinSource;
    
    
    
    
    ////////////////////////////////////////////////
    
    
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

	public BigDecimal getInitial() {
		return initial;
	}

	public void setInitial(BigDecimal initial) {
		this.initial = initial;
	}



	public BigDecimal getVente() {
		return vente;
	}

	public void setVente(BigDecimal vente) {
		this.vente = vente;
	}

	public BigDecimal getAvoir() {
		return avoir;
	}

	public void setAvoir(BigDecimal avoir) {
		this.avoir = avoir;
	}

	public BigDecimal getStockFinaldb() {
		return stockFinaldb;
	}

	public void setStockFinaldb(BigDecimal stockFinaldb) {
		this.stockFinaldb = stockFinaldb;
	}

	public BigDecimal getAchat() {
		return achat;
	}

	public void setAchat(BigDecimal achat) {
		this.achat = achat;
	}

	public BigDecimal getRetour() {
		return retour;
	}

	public void setRetour(BigDecimal retour) {
		this.retour = retour;
	}

	public MouvementStockGlobal getMouvementStockGlobal() {
		return mouvementStockGlobal;
	}

	public void setMouvementStockGlobal(MouvementStockGlobal mouvementStockGlobal) {
		this.mouvementStockGlobal = mouvementStockGlobal;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateMaj() {
		return dateMaj;
	}

	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}

	public Utilisateur getUtilisateurCreation() {
		return utilisateurCreation;
	}

	public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
		this.utilisateurCreation = utilisateurCreation;
	}

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public BigDecimal getProduction() {
		return production;
	}

	public void setProduction(BigDecimal production) {
		this.production = production;
	}

	public BigDecimal getService() {
		return service;
	}

	public void setService(BigDecimal service) {
		this.service = service;
	}

	public BigDecimal getDechet() {
		return dechet;
	}

	public void setDechet(BigDecimal dechet) {
		this.dechet = dechet;
	}

	public BigDecimal getDechetService() {
		return dechetService;
	}

	public void setDechetService(BigDecimal dechetService) {
		this.dechetService = dechetService;
	}

	public BigDecimal getOffreProduction() {
		return offreProduction;
	}

	public void setOffreProduction(BigDecimal offreProduction) {
		this.offreProduction = offreProduction;
	}

	public BigDecimal getTransfert() {
		return transfert;
	}

	public void setTransfert(BigDecimal transfert) {
		this.transfert = transfert;
	}

	public BigDecimal getOffreService() {
		return offreService;
	}

	public void setOffreService(BigDecimal offreService) {
		this.offreService = offreService;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public BigDecimal getPrixFinaldb() {
		return prixFinaldb;
	}

	public void setPrixFinaldb(BigDecimal prixFinaldb) {
		this.prixFinaldb = prixFinaldb;
	}

	public BigDecimal getReception() {
		return reception;
	}

	public void setReception(BigDecimal reception) {
		this.reception = reception;
	}

	public BigDecimal getEntrer() {
		return entrer;
	}

	public void setEntrer(BigDecimal entrer) {
		this.entrer = entrer;
	}

	public BigDecimal getSortie() {
		return sortie;
	}

	public void setSortie(BigDecimal sortie) {
		this.sortie = sortie;
	}

	public Magasin getMagasinDestination() {
		return magasinDestination;
	}

	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
	}

	public Magasin getMagasinSource() {
		return magasinSource;
	}

	public void setMagasinSource(Magasin magasinSource) {
		this.magasinSource = magasinSource;
	}

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

   
    
    /*
    public BigDecimal getStockFinal() {
        BigDecimal result = (getInitial().add(getReception())).subtract(getCharge()).subtract(getChargeSupplementaire()).subtract(getTransfertSorties()).add(getTransfertEntrees()).subtract(getSorties());
         return result ;
    }
*/
	/*public BigDecimal getRetour() {
		return retour;
	}

	public void setRetour(BigDecimal retour) {
		this.retour = retour;
	}*/
	
	

}
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
@Table(name ="Mouvement_Stock_Global")
@NamedQuery(name = "MouvementStockGlobal.findAll", query = "SELECT c FROM MouvementStockGlobal c")
public class MouvementStockGlobal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name="DATE_MOUVEMENT")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateMouvement;
    

     @ManyToOne
    @JoinColumn(name = "ID_DEPOT")
    private Depot depot;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN")
    private Magasin magasin;

    @OneToMany(mappedBy="mouvementStockGlobal",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DetailMouvementStock> detailMouvementStock= new ArrayList<DetailMouvementStock>();
    
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
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

 

   

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public List<DetailMouvementStock> getDetailMouvementStock() {
        return detailMouvementStock;
    }

    public void setDetailMouvementStock(List<DetailMouvementStock> detailMouvementStock) {
        this.detailMouvementStock = detailMouvementStock;
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
    
	

}
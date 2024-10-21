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
import javax.persistence.Temporal;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="EtatTransfertMPEnPF.findAll", query="SELECT f FROM EtatTransfertMPEnPF f")
public class EtatTransfertMPEnPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	 @Column(name="DATE_MOUVEMENT")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateMouvement;
	
	
	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP;
	
	// Stock Transfert
	
	@Column(name="quantite_transfert")
	private BigDecimal quantiteTransfert;
	
	// Code Stock Transfert
	
	@Column(name="code_transfert")
	private String codeTransfert;
	
	
	// Magasin Source 
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	// Magasin Destination 
	@ManyToOne
	@JoinColumn( name="id_Magasin_destination")
	private Magasin magasinDestination;
	
	@ManyToOne
	@JoinColumn( name="id_article")
	private Articles article;


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


	public MatierePremier getMp() {
		return mp;
	}


	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}


	public FournisseurMP getFournisseurMP() {
		return fournisseurMP;
	}


	public void setFournisseurMP(FournisseurMP fournisseurMP) {
		this.fournisseurMP = fournisseurMP;
	}


	public BigDecimal getQuantiteTransfert() {
		return quantiteTransfert;
	}


	public void setQuantiteTransfert(BigDecimal quantiteTransfert) {
		this.quantiteTransfert = quantiteTransfert;
	}


	public String getCodeTransfert() {
		return codeTransfert;
	}


	public void setCodeTransfert(String codeTransfert) {
		this.codeTransfert = codeTransfert;
	}


	public Magasin getMagasinSouce() {
		return magasinSouce;
	}


	public void setMagasinSouce(Magasin magasinSouce) {
		this.magasinSouce = magasinSouce;
	}


	public Magasin getMagasinDestination() {
		return magasinDestination;
	}


	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
	} 


	public Articles getArticle() {
		return article;
	}


	public void setArticle(Articles article) {
		this.article = article;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

				



				
			
}
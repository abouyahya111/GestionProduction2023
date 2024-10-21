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
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="MouvementStockProduitsFini.findAll", query="SELECT f FROM MouvementStockProduitsFini f")
public class MouvementStockProduitsFini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;


	
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_STOCK")
	private Date dateStockPF;
	
	@Column(name="Prix_Initial")
	private BigDecimal prixInitial;
	
	@Column(name="initial")
	private BigDecimal initial;
	
	@Column(name="Prix_Achat")
	private BigDecimal prixAchat;
	
	@Column(name="Achat")
	private BigDecimal achat;
	
	@Column(name="vente")
	private BigDecimal vente;
	
	@Column(name="avoir")
	private BigDecimal avoir;
	
	@Column(name="Transfer_Entrer")
	private BigDecimal transferEntrer;
	
	@Column(name="Prix_Transfer")
	private BigDecimal prixTransferEntrer;
	
	@Column(name="Entrer_Production")
	private BigDecimal entrerProduction;
	
	@Column(name="Prix_Production")
	private BigDecimal prixProduction;
	
	@Column(name="Stock_Final")
	private BigDecimal stockFinal;
	
	@Column(name="Prix_Final")
	private BigDecimal prixFinal;
	
	@Column(name="Magasin_Destination")
	private Magasin magasinDestination;
	
	@Column(name="Client")
	private Client client;
	
	@Column(name="Depot")
	private Depot depot;

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

	public Date getDateStockPF() {
		return dateStockPF;
	}

	public void setDateStockPF(Date dateStockPF) {
		this.dateStockPF = dateStockPF;
	}

	public BigDecimal getInitial() {
		return initial;
	}

	public void setInitial(BigDecimal initial) {
		this.initial = initial;
	}

	public BigDecimal getAchat() {
		return achat;
	}

	public void setAchat(BigDecimal achat) {
		this.achat = achat;
	}

	public BigDecimal getVente() {
		return vente;
	}

	public void setVente(BigDecimal vente) {
		this.vente = vente;
	}

	public BigDecimal getEntrerProduction() {
		return entrerProduction;
	}

	public void setEntrerProduction(BigDecimal entrerProduction) {
		this.entrerProduction = entrerProduction;
	}

	public BigDecimal getStockFinal() {
		return stockFinal;
	}

	public void setStockFinal(BigDecimal stockFinal) {
		this.stockFinal = stockFinal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getAvoir() {
		return avoir;
	}

	public void setAvoir(BigDecimal avoir) {
		this.avoir = avoir;
	}



	public BigDecimal getTransferEntrer() {
		return transferEntrer;
	}

	public void setTransferEntrer(BigDecimal transferEntrer) {
		this.transferEntrer = transferEntrer;
	}

	public BigDecimal getPrixFinal() {
		return prixFinal;
	}

	public void setPrixFinal(BigDecimal prixFinal) {
		this.prixFinal = prixFinal;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public BigDecimal getPrixTransferEntrer() {
		return prixTransferEntrer;
	}

	public void setPrixTransferEntrer(BigDecimal prixTransferEntrer) {
		this.prixTransferEntrer = prixTransferEntrer;
	}

	public BigDecimal getPrixProduction() {
		return prixProduction;
	}

	public void setPrixProduction(BigDecimal prixProduction) {
		this.prixProduction = prixProduction;
	}

	public Magasin getMagasinDestination() {
		return magasinDestination;
	}

	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}



	
	

}
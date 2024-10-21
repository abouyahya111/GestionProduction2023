package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="Detail_Transfer_PF")
@NamedQuery(name="DetailTransferProduitFini.findAll", query="SELECT d FROM DetailTransferProduitFini d")
public class DetailTransferProduitFini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	private BigDecimal quantite;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_Article")
	private Articles article;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_destination")
	private Magasin magasinDestination;


	@JoinColumn(name="TYPE_TRANSFER")
	private String typeTransfer;
	
	@JoinColumn(name="DATE_TRANSFER")
	private Date dateTransfer;
	
	@ManyToOne
	@JoinColumn(name="id_Client")
	private Client client;
	
	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_transfer")
	private TransferStockPF transferStockPF;

	public DetailTransferProduitFini() {
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

	public String getTypeTransfer() {
		return typeTransfer;
	}

	public void setTypeTransfer(String typeTransfer) {
		this.typeTransfer = typeTransfer;
	}

	public Date getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(Date dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	public TransferStockPF getTransferStockPF() {
		return transferStockPF;
	}

	public void setTransferStockPF(TransferStockPF transferStockPF) {
		this.transferStockPF = transferStockPF;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
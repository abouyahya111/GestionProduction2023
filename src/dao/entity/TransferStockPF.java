package dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name="Transfer_StockPF")
@NamedQuery(name="TransferStockPF.findAll", query="SELECT c FROM TransferStockPF c")
public class TransferStockPF implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_transfer")
	private Date dateTransfer;
	
	@Column(name="Code_Transfer")
	private String CodeTransfer;

	private String statut;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;


	//bi-directional many-to-one association to DetailCommande
	@OneToMany(cascade = CascadeType.ALL,mappedBy="transferStockPF")
	private List<DetailTransferProduitFini> listDetailTransferProduitFini;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public TransferStockPF() {
	}
	
	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	
	
	public DetailTransferProduitFini addDetailTransferStockPF(DetailTransferProduitFini detailTransferStockPF) {
		getListDetailTransferProduitFini().add(detailTransferStockPF);
		detailTransferStockPF.setTransferStockPF(this);

		return detailTransferStockPF;
	}

	public DetailTransferProduitFini removeDetailTransferStockPF(DetailTransferProduitFini detailTransferStockPF) {
		getListDetailTransferProduitFini().remove(detailTransferStockPF);
		detailTransferStockPF.setTransferStockPF(null);

		return detailTransferStockPF;
	}
	
	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public Date getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(Date dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	public String getCodeTransfer() {
		return CodeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		CodeTransfer = codeTransfer;
	}

	public List<DetailTransferProduitFini> getListDetailTransferProduitFini() {
		return listDetailTransferProduitFini;
	}

	public void setListDetailTransferProduitFini(List<DetailTransferProduitFini> listDetailTransferProduitFini) {
		this.listDetailTransferProduitFini = listDetailTransferProduitFini;
	}

	



}
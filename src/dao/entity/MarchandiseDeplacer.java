package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="marchandisedeplacer")
@NamedQuery(name="marchandisedeplacer.findAll", query="SELECT d FROM marchandisedeplacer d")
public class MarchandiseDeplacer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn( name="id_Magasin_destination")
	private Magasin magasinDestination;

	@Column(name="code_transfert")
	private String codeTransfert;
	
	@Column(name="etat")
	private String etat;
	
	@Column(name="date_entre")
	private Date dateEntrer;
	
	@Column(name="date_sortie")
	private Date dateSortie;
	
	
	@Column(name="transfertmp")
	private TransferStockMP transferStockMP;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	

	public String getCodeTransfert() {
		return codeTransfert;
	}

	public void setCodeTransfert(String codeTransfert) {
		this.codeTransfert = codeTransfert;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TransferStockMP getTransferStockMP() {
		return transferStockMP;
	}

	public void setTransferStockMP(TransferStockMP transferStockMP) {
		this.transferStockMP = transferStockMP;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateEntrer() {
		return dateEntrer;
	}

	public void setDateEntrer(Date dateEntrer) {
		this.dateEntrer = dateEntrer;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	

	

}
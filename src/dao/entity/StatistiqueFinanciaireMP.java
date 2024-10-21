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

 
@Entity
@NamedQuery(name="StatistiqueFinanciaireMP.findAll", query="SELECT f FROM StatistiqueFinanciaireMP f")
public class StatistiqueFinanciaireMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="Code_Transfer")
	private String CodeTransfer;
	
	@Column(name="etat")
	private String etat;
	
	 
	@Column(name="ALIMENTATION", precision = 30, scale = 12)
	private BigDecimal alimentation ;
	
	@Column(name="STOCKEMBALLAGE", precision = 30, scale = 12)
	private BigDecimal stockEmballage ;
	
	@Column(name="STOCKENVRAC", precision = 30, scale = 12)
	private BigDecimal stockEnVrac ;
	
	
	@Column(name="COUTPRODUCTION", precision = 30, scale = 12)
	private BigDecimal coutProduction ;
	
	@Column(name="COUTEMPLOYEE", precision = 30, scale = 12)
	private BigDecimal coutEmployeeProduction;
	
	@Column(name="COUTPRODUCTIONCARTON", precision = 30, scale = 12)
	private BigDecimal coutProductionCarton ;
	
	@Column(name="COUTEMPLOYEECARTON", precision = 30, scale = 12)
	private BigDecimal coutEmployeeCarton;
	
	
	@Column(name="COUTFABRICATIONCARTON", precision = 30, scale = 12)
	private BigDecimal coutFabricationCarton ;
	
	@Column(name="COUTSORTIE", precision = 30, scale = 12)
	private BigDecimal coutSortie;
	
	@Column(name="COUTTRANSFERTMPPF", precision = 30, scale = 12)
	private BigDecimal coutTransfertMPPF;
	
	
	@Column(name="COUTRECEPTION", precision = 30, scale = 12)
	private BigDecimal coutReception;
	
	@Column(name="COUTENTRE", precision = 30, scale = 12)
	private BigDecimal COUTEntre;
	
	
	@Column(name="COUTVente", precision = 30, scale = 12)
	private BigDecimal coutVente;
	
	@Column(name="COUTRETOUR", precision = 30, scale = 12)
	private BigDecimal coutRetour;
	
	@Column(name="COUTPF", precision = 30, scale = 12)
	private BigDecimal COUTPF;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateOperation;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAlimentation() {
		return alimentation;
	}

	public void setAlimentation(BigDecimal alimentation) {
		this.alimentation = alimentation;
	}

	public BigDecimal getStockEmballage() {
		return stockEmballage;
	}

	public void setStockEmballage(BigDecimal stockEmballage) {
		this.stockEmballage = stockEmballage;
	}

	public BigDecimal getStockEnVrac() {
		return stockEnVrac;
	}

	public void setStockEnVrac(BigDecimal stockEnVrac) {
		this.stockEnVrac = stockEnVrac;
	}

	public BigDecimal getCoutProduction() {
		return coutProduction;
	}

	public void setCoutProduction(BigDecimal coutProduction) {
		this.coutProduction = coutProduction;
	}

	public BigDecimal getCoutEmployeeProduction() {
		return coutEmployeeProduction;
	}

	public void setCoutEmployeeProduction(BigDecimal coutEmployeeProduction) {
		this.coutEmployeeProduction = coutEmployeeProduction;
	}

	public BigDecimal getCoutProductionCarton() {
		return coutProductionCarton;
	}

	public void setCoutProductionCarton(BigDecimal coutProductionCarton) {
		this.coutProductionCarton = coutProductionCarton;
	}

	public BigDecimal getCoutEmployeeCarton() {
		return coutEmployeeCarton;
	}

	public void setCoutEmployeeCarton(BigDecimal coutEmployeeCarton) {
		this.coutEmployeeCarton = coutEmployeeCarton;
	}

	public BigDecimal getCoutFabricationCarton() {
		return coutFabricationCarton;
	}

	public void setCoutFabricationCarton(BigDecimal coutFabricationCarton) {
		this.coutFabricationCarton = coutFabricationCarton;
	}

	public BigDecimal getCoutSortie() {
		return coutSortie;
	}

	public void setCoutSortie(BigDecimal coutSortie) {
		this.coutSortie = coutSortie;
	}

	public BigDecimal getCoutTransfertMPPF() {
		return coutTransfertMPPF;
	}

	public void setCoutTransfertMPPF(BigDecimal coutTransfertMPPF) {
		this.coutTransfertMPPF = coutTransfertMPPF;
	}

	public BigDecimal getCoutReception() {
		return coutReception;
	}

	public void setCoutReception(BigDecimal coutReception) {
		this.coutReception = coutReception;
	}

	public BigDecimal getCOUTEntre() {
		return COUTEntre;
	}

	public void setCOUTEntre(BigDecimal cOUTEntre) {
		COUTEntre = cOUTEntre;
	}

	public BigDecimal getCoutVente() {
		return coutVente;
	}

	public void setCoutVente(BigDecimal coutVente) {
		this.coutVente = coutVente;
	}

	public BigDecimal getCOUTPF() {
		return COUTPF;
	}

	public void setCOUTPF(BigDecimal cOUTPF) {
		COUTPF = cOUTPF;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getCodeTransfer() {
		return CodeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		CodeTransfer = codeTransfer;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public BigDecimal getCoutRetour() {
		return coutRetour;
	}

	public void setCoutRetour(BigDecimal coutRetour) {
		this.coutRetour = coutRetour;
	}
	
	
	
	

}
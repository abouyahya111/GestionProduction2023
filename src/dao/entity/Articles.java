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
@NamedQuery(name="Articles.findAll", query="SELECT f FROM Articles f")
public class Articles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_ARTICLE")
	private String codeArticle;
	
	@Column(name="LIBELLE")
	private String liblle ;
	
	@Column(name="CENTRECOUT1")
	private BigDecimal centreCout1 ;

	@Column(name="CENTRECOUT2")
	private String centreCout2 ;
	
	@Column(name="CENTRECOUT3")
	private BigDecimal centreCout3 ;
	
	@Column(name="CENTRECOUT4")
	private BigDecimal centreCout4 ;
	
	@Column(name="CENTRECOUT5")
	private BigDecimal centreCout5 ;
	
	@Column(name="CODE_FONCTION")
	private String codeFonction ;
	
	@Column(name="PRIX_VENTE")
	private BigDecimal prixGros ;
	
	@Column(name="PRIX_RETOUR")
	private BigDecimal prixDetail ;
	
	@Column(name="TVA")
	private BigDecimal tva ;
	
	@Column(name="CONDITIONNEMENT")
	private BigDecimal conditionnement ;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation ;
	
	@Column(name="CLIENT")
	private boolean client ;
	
	
	@ManyToOne 
	@JoinColumn(name="id_Client")
	private Client articleClient;

	//bi-directional many-to-one association to RipFournisseur
	@OneToMany(cascade = CascadeType.ALL, mappedBy="articles" )
	private List<DetailEstimation> detailEstimation=new ArrayList<>();

	

	
	

	public DetailEstimation addDetailEstimation(DetailEstimation detailEstimation) {
		getDetailEstimation().add(detailEstimation);
		detailEstimation.setArticles(this);

		return detailEstimation;
	}

	public DetailEstimation removeDetailEstimation(DetailEstimation detailEstimation) {
		getDetailEstimation().remove(detailEstimation);
		detailEstimation.setArticles(null);

		return detailEstimation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getLiblle() {
		return liblle;
	}

	public void setLiblle(String liblle) {
		this.liblle = liblle;
	}

	public BigDecimal getCentreCout1() {
		return centreCout1;
	}

	public void setCentreCout1(BigDecimal centreCout1) {
		this.centreCout1 = centreCout1;
	}

	public String getCentreCout2() {
		return centreCout2;
	}

	public void setCentreCout2(String centreCout2) {
		this.centreCout2 = centreCout2;
	}

	public BigDecimal getCentreCout3() {
		return centreCout3;
	}

	public void setCentreCout3(BigDecimal centreCout3) {
		this.centreCout3 = centreCout3;
	}

	public BigDecimal getCentreCout4() {
		return centreCout4;
	}

	public void setCentreCout4(BigDecimal centreCout4) {
		this.centreCout4 = centreCout4;
	}

	public BigDecimal getCentreCout5() {
		return centreCout5;
	}

	public void setCentreCout5(BigDecimal centreCout5) {
		this.centreCout5 = centreCout5;
	}

	public String getCodeFonction() {
		return codeFonction;
	}

	public void setCodeFonction(String codeFonction) {
		this.codeFonction = codeFonction;
	}

	public BigDecimal getPrixGros() {
		return prixGros;
	}

	public void setPrixGros(BigDecimal prixGros) {
		this.prixGros = prixGros;
	}

	public BigDecimal getPrixDetail() {
		return prixDetail;
	}

	public void setPrixDetail(BigDecimal prixDetail) {
		this.prixDetail = prixDetail;
	}

	public BigDecimal getTva() {
		return tva;
	}

	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}

	public BigDecimal getConditionnement() {
		return conditionnement;
	}

	public void setConditionnement(BigDecimal conditionnement) {
		this.conditionnement = conditionnement;
	}

	public List<DetailEstimation> getDetailEstimation() {
		return detailEstimation;
	}

	public void setDetailEstimation(List<DetailEstimation> detailEstimation) {
		this.detailEstimation = detailEstimation;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}

	public Client getArticleClient() {
		return articleClient;
	}

	public void setArticleClient(Client articleClient) {
		this.articleClient = articleClient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
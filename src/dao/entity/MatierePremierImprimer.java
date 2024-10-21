package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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


/**
 * The persistent class for the matiere_premier database table.
 * 
 */
@Entity
@Table(name="matiere_premier_imprimer")
@NamedQuery(name="MatierePremierImprimer.findAll", query="SELECT m FROM MatierePremierImprimer m")
public class MatierePremierImprimer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;
	
	private boolean deleted ;
	@Column( precision = 19, scale = 12)
	private BigDecimal prix;
	
	
	private BigDecimal prixComparer;
	
	// Client ou Interne
	private String type;
	private String unite;
	private String client;


	/*//bi-directional many-to-one association to DetailCommande
	@OneToMany(mappedBy="matierePremier")
	private List<DetailCommande> detailCommandes;

	//bi-directional many-to-one association to DetailFacture
	@OneToMany(mappedBy="matierePremier")
	private List<DetailFacture> detailFactures;

	//bi-directional many-to-one association to DetailProduit
	@OneToMany(mappedBy="matierePremier")
	private List<DetailProduit> detailProduits;*/

	//bi-directional many-to-one association to CategorieMp
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_cat")
	private CategorieMp categorieMp;
	
	@ManyToOne 
	@JoinColumn(name="id_Type_Offre")
	private TypeOffre typeOffre;
	
	@JoinColumn(name="grammage_Offre")
	private BigDecimal grammageOffre;

	/*//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="matierePremier")
	private List<Stock> stocks;*/

	public MatierePremierImprimer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

/*	public List<DetailCommande> getDetailCommandes() {
		return this.detailCommandes;
	}

	public void setDetailCommandes(List<DetailCommande> detailCommandes) {
		this.detailCommandes = detailCommandes;
	}

	public DetailCommande addDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().add(detailCommande);
		detailCommande.setMatierePremier(this);

		return detailCommande;
	}

	public DetailCommande removeDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().remove(detailCommande);
		detailCommande.setMatierePremier(null);

		return detailCommande;
	}

	public List<DetailFacture> getDetailFactures() {
		return this.detailFactures;
	}

	public void setDetailFactures(List<DetailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}

	public DetailFacture addDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().add(detailFacture);
		detailFacture.setMatierePremier(this);

		return detailFacture;
	}

	public DetailFacture removeDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().remove(detailFacture);
		detailFacture.setMatierePremier(null);

		return detailFacture;
	}

	public List<DetailProduit> getDetailProduits() {
		return this.detailProduits;
	}

	public void setDetailProduits(List<DetailProduit> detailProduits) {
		this.detailProduits = detailProduits;
	}

	public DetailProduit addDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().add(detailProduit);
		detailProduit.setMatierePremier(this);

		return detailProduit;
	}

	public DetailProduit removeDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().remove(detailProduit);
		detailProduit.setMatierePremier(null);

		return detailProduit;
	}
*/
	public CategorieMp getCategorieMp() {
		return this.categorieMp;
	}

	public void setCategorieMp(CategorieMp categorieMp) {
		this.categorieMp = categorieMp;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrixComparer() {
		return prixComparer;
	}

	public void setPrixComparer(BigDecimal prixComparer) {
		this.prixComparer = prixComparer;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public TypeOffre getTypeOffre() {
		return typeOffre;
	}

	public void setTypeOffre(TypeOffre typeOffre) {
		this.typeOffre = typeOffre;
	}

	public BigDecimal getGrammageOffre() {
		return grammageOffre;
	}

	public void setGrammageOffre(BigDecimal grammageOffre) {
		this.grammageOffre = grammageOffre;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	

	
	
	

	/*public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setMatierePremier(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setMatierePremier(null);

		return stock;
	}*/

}
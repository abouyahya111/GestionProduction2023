package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the matiere_premier database table.
 * 
 */
@Entity
@Table(name="matiere_premier_Modifier")
@NamedQuery(name="MatierePremierModifier.findAll", query="SELECT m FROM MatierePremierModifier m")
public class MatierePremierModifier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;
	
	private boolean deleted ;
	@Column( precision = 19, scale = 12)
	private BigDecimal prix;
	
	@Column( precision = 19, scale = 12)
    private BigDecimal prix2024;
	
	@Column( precision = 19, scale = 12)
    private BigDecimal prixInitial2024;
	

	@Column( precision = 19, scale = 12)
	private BigDecimal prix2025;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2025;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prix2026;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2026;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prix2027;
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2027;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prix2028;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2028;
	
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prix2029;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2029;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prix2030;
	
	@Column( precision = 19, scale = 12)
	private BigDecimal prixInitial2030;
	
	private BigDecimal prixComparer;
	
	// Client ou Interne
	private String type;
	private String unite;

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
	
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIER")
	private Date dateModifier;
	@ManyToOne 
	@JoinColumn(name="id_Client")
	private Client client;

	/*//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="matierePremier")
	private List<Stock> stocks;*/

	public MatierePremierModifier() {
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

	public BigDecimal getPrix2024() {
		return prix2024;
	}

	public void setPrix2024(BigDecimal prix2024) {
		this.prix2024 = prix2024;
	}

	public BigDecimal getPrix2025() {
		return prix2025;
	}

	public void setPrix2025(BigDecimal prix2025) {
		this.prix2025 = prix2025;
	}

	public BigDecimal getPrix2026() {
		return prix2026;
	}

	public void setPrix2026(BigDecimal prix2026) {
		this.prix2026 = prix2026;
	}

	public BigDecimal getPrix2027() {
		return prix2027;
	}

	public void setPrix2027(BigDecimal prix2027) {
		this.prix2027 = prix2027;
	}

	public BigDecimal getPrix2028() {
		return prix2028;
	}

	public void setPrix2028(BigDecimal prix2028) {
		this.prix2028 = prix2028;
	}

	public BigDecimal getPrix2029() {
		return prix2029;
	}

	public void setPrix2029(BigDecimal prix2029) {
		this.prix2029 = prix2029;
	}

	public BigDecimal getPrix2030() {
		return prix2030;
	}

	public void setPrix2030(BigDecimal prix2030) {
		this.prix2030 = prix2030;
	}
	
	
	
	public BigDecimal getPrixInitial2024() {
		return prixInitial2024;
	}

	public void setPrixInitial2024(BigDecimal prixInitial2024) {
		this.prixInitial2024 = prixInitial2024;
	}

	public BigDecimal getPrixInitial2025() {
		return prixInitial2025;
	}

	public void setPrixInitial2025(BigDecimal prixInitial2025) {
		this.prixInitial2025 = prixInitial2025;
	}

	public BigDecimal getPrixInitial2026() {
		return prixInitial2026;
	}

	public void setPrixInitial2026(BigDecimal prixInitial2026) {
		this.prixInitial2026 = prixInitial2026;
	}

	public BigDecimal getPrixInitial2027() {
		return prixInitial2027;
	}

	public void setPrixInitial2027(BigDecimal prixInitial2027) {
		this.prixInitial2027 = prixInitial2027;
	}

	public BigDecimal getPrixInitial2028() {
		return prixInitial2028;
	}

	public void setPrixInitial2028(BigDecimal prixInitial2028) {
		this.prixInitial2028 = prixInitial2028;
	}

	public BigDecimal getPrixInitial2029() {
		return prixInitial2029;
	}

	public void setPrixInitial2029(BigDecimal prixInitial2029) {
		this.prixInitial2029 = prixInitial2029;
	}

	public BigDecimal getPrixInitial2030() {
		return prixInitial2030;
	}

	public void setPrixInitial2030(BigDecimal prixInitial2030) {
		this.prixInitial2030 = prixInitial2030;
	}

	public BigDecimal getPrixByYear(int year) {
        switch (year) {
        case 2023:
            return getPrix();
            case 2024:
                return getPrix2024();
            case 2025:
                return getPrix2025();
            case 2026:
                return getPrix2026();
            case 2027:
                return getPrix2027();
            case 2028:
                return getPrix2028();
            case 2029:
                return getPrix2029();
            case 2030:
                return getPrix2030();    
            default:
                // Handle the case when the year is not supported or return a default value
                return BigDecimal.ZERO; // You can change this to an appropriate default value
        }
    }
	
	public BigDecimal getPrixInitialByYear(int year) {
        switch (year) {
        case 2023:
            return getPrix();
            case 2024:
                return getPrixInitial2024();
            case 2025:
                return getPrixInitial2025();
            case 2026:
                return getPrixInitial2026();
            case 2027:
                return getPrixInitial2027();
            case 2028:
                return getPrixInitial2028();
            case 2029:
                return getPrixInitial2029();
            case 2030:
                return getPrixInitial2030();    
            default:
                // Handle the case when the year is not supported or return a default value
                return BigDecimal.ZERO; // You can change this to an appropriate default value
        }
    }
	
	public void setPrixInitialByYear(int year, BigDecimal prix) {
        switch (year) {
        case 2023:
            setPrix(prix);
            break;
        
            case 2024:
                setPrixInitial2024(prix);
                break;
            case 2025:
                setPrixInitial2025(prix);
                break;
            case 2026:
                setPrixInitial2026(prix);
                break;
            case 2027:
                setPrixInitial2027(prix);
                break;
            case 2028:
                setPrixInitial2028(prix);
                break;
            case 2029:
                setPrixInitial2029(prix);
                break;    
            case 2030:
                setPrixInitial2030(prix);
                break;    
            default:
                // Handle unsupported year or throw an exception
                throw new IllegalArgumentException("Unsupported year: " + year);
        }
    }
	
	public void setPrixByYear(int year, BigDecimal prix) {
        switch (year) {
        case 2023:
            setPrix(prix);
            break;
        
            case 2024:
                setPrix2024(prix);
                break;
            case 2025:
                setPrix2025(prix);
                break;
            case 2026:
                setPrix2026(prix);
                break;
            case 2027:
                setPrix2027(prix);
                break;
            case 2028:
                setPrix2028(prix);
                break;
            case 2029:
                setPrix2029(prix);
                break;    
            case 2030:
                setPrix2030(prix);
                break;    
            default:
                // Handle unsupported year or throw an exception
                throw new IllegalArgumentException("Unsupported year: " + year);
        }
    }

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
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
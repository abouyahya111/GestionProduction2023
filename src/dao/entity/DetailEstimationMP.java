package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detail_estimationMP")
@NamedQuery(name="DetailEstimationMP.findAll", query="SELECT d FROM DetailEstimationMP d")
public class DetailEstimationMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int priorite;

	private BigDecimal quantite;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_articleMP")
	private ArticlesMP articlesMP;
	
	@Column(name="ACTIF")
	private boolean actif;

	public DetailEstimationMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public ArticlesMP getArticles() {
		return articlesMP;
	}

	public void setArticlesMP(ArticlesMP articlesMP) {
		this.articlesMP = articlesMP;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	

}
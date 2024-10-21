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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="ArticlesMP.findAll", query="SELECT f FROM ArticlesMP f")
public class ArticlesMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_ARTICLE")
	private String codeArticle;
	
	@Column(name="LIBELLE")
	private String liblle ;
	
	
	//bi-directional many-to-one association to RipFournisseur
	@OneToMany(cascade = CascadeType.ALL, mappedBy="articlesMP")
	private List<DetailEstimationMP> detailEstimationMP;

	public DetailEstimationMP addDetailEstimationMP(DetailEstimationMP detailEstimationMP) {
		getDetailEstimationMP().add(detailEstimationMP);
		detailEstimationMP.setArticlesMP(this);

		return detailEstimationMP;
	}

	public DetailEstimationMP removeDetailEstimationMP(DetailEstimationMP detailEstimationMP) {
		getDetailEstimationMP().remove(detailEstimationMP);
		detailEstimationMP.setArticlesMP(null);

		return detailEstimationMP;
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

	

	public List<DetailEstimationMP> getDetailEstimationMP() {
		return detailEstimationMP;
	}

	public void setDetailEstimationMP(List<DetailEstimationMP> detailEstimationMP) {
		this.detailEstimationMP = detailEstimationMP;
	}

	
}
package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the detail_produit database table.
 * 
 */
@Entity
@Table(name="detail_produit")
@NamedQuery(name="DetailProduit.findAll", query="SELECT d FROM DetailProduit d")
public class DetailProduit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to DetailProduit
	@ManyToOne
	@JoinColumn(name="id_produit")
	private DetailProduit detailProduit;

	//bi-directional many-to-one association to DetailProduit
	@OneToMany(mappedBy="detailProduit")
	private List<DetailProduit> detailProduits;

	public DetailProduit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MatierePremier getMatierePremier() {
		return this.matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public DetailProduit getDetailProduit() {
		return this.detailProduit;
	}

	public void setDetailProduit(DetailProduit detailProduit) {
		this.detailProduit = detailProduit;
	}

	public List<DetailProduit> getDetailProduits() {
		return this.detailProduits;
	}

	public void setDetailProduits(List<DetailProduit> detailProduits) {
		this.detailProduits = detailProduits;
	}

	public DetailProduit addDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().add(detailProduit);
		detailProduit.setDetailProduit(this);

		return detailProduit;
	}

	public DetailProduit removeDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().remove(detailProduit);
		detailProduit.setDetailProduit(null);

		return detailProduit;
	}

}
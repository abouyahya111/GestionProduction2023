package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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





/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@Table(name="DETAIL_PROMOTION")
@NamedQuery(name="DetailPromotion.findAll", query="SELECT d FROM DetailPromotion d")
public class DetailPromotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_CATEGORIE")
	private CategorieMp categorie;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_promotion")
	private Promotion promotion=new Promotion();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategorieMp getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieMp categorie) {
		this.categorie = categorie;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	

}
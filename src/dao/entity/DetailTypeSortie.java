package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detail_typesortie")
@NamedQuery(name="DetailTypeSortie.findAll", query="SELECT d FROM DetailTypeSortie d")
public class DetailTypeSortie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String sousType;



	//bi-directional many-to-one association to Commande
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_typesortie")
	private TypeSortie typesortie;
	


	public DetailTypeSortie() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSousType() {
		return sousType;
	}

	public void setSousType(String sousType) {
		this.sousType = sousType;
	}

	public TypeSortie getTypesortie() {
		return typesortie;
	}

	public void setTypesortie(TypeSortie typesortie) {
		this.typesortie = typesortie;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="TypeSortie.findAll", query="SELECT f FROM TypeSortie f")
public class TypeSortie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="LIBELLE")
	private String liblle ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="typesortie" )
	private List<DetailTypeSortie> detailTypeSortie=new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLiblle() {
		return liblle;
	}

	public void setLiblle(String liblle) {
		this.liblle = liblle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DetailTypeSortie> getDetailTypeSortie() {
		return detailTypeSortie;
	}

	public void setDetailTypeSortie(List<DetailTypeSortie> detailTypeSortie) {
		this.detailTypeSortie = detailTypeSortie;
	}



	

}
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
@NamedQuery(name="TypeOffre.findAll", query="SELECT f FROM TypeOffre f")
public class TypeOffre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="TYPE_OFFRE")
	private String typeOffre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeOffre() {
		return typeOffre;
	}

	public void setTypeOffre(String typeOffre) {
		this.typeOffre = typeOffre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 
	
	
	

}
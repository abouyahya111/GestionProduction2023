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
@NamedQuery(name="FormeParBox.findAll", query="SELECT f FROM FormeParBox f")
public class FormeParBox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_sub_categorie")
	private SubCategorieMp subCategorieMp;
	
	@ManyToOne
	@JoinColumn(name="id_forme")
	private forme forme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SubCategorieMp getSubCategorieMp() {
		return subCategorieMp;
	}

	public void setSubCategorieMp(SubCategorieMp subCategorieMp) {
		this.subCategorieMp = subCategorieMp;
	}

	public forme getForme() {
		return forme;
	}

	public void setForme(forme forme) {
		this.forme = forme;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
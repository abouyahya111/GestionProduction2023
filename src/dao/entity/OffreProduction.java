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
@NamedQuery(name="OffreProduction.findAll", query="SELECT f FROM OffreProduction f")
public class OffreProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	@ManyToOne
	@JoinColumn( name="id_production")
	private Production prodcutionCM;
	
	@ManyToOne
	@JoinColumn( name="id_condition_offre")
	private ConditionOffre conditionOffre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Production getProdcutionCM() {
		return prodcutionCM;
	}

	public void setProdcutionCM(Production prodcutionCM) {
		this.prodcutionCM = prodcutionCM;
	}

	public ConditionOffre getConditionOffre() {
		return conditionOffre;
	}

	public void setConditionOffre(ConditionOffre conditionOffre) {
		this.conditionOffre = conditionOffre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	

}
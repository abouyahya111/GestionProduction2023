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
@NamedQuery(name="ConditionOffre.findAll", query="SELECT f FROM ConditionOffre f")
public class ConditionOffre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CONDITION_OFFRE")
	private String conditionOffre;
	
	@Column(name="VALEUR")
	private BigDecimal valeur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConditionOffre() {
		return conditionOffre;
	}

	public void setConditionOffre(String conditionOffre) {
		this.conditionOffre = conditionOffre;
	}

	public BigDecimal getValeur() {
		return valeur;
	}

	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 
	
	

}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@NamedQuery(name="regularisationPrixMP.findAll", query="SELECT p FROM regularisationPrixMP p")
public class RegularisationPrixMp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	 
	@Column(name="NUM_OF")
	private String numOF;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getNumOF() {
		return numOF;
	}


	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
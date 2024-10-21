package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CompteMagasinier database table.
 * 
 */
@Entity
@Table(name="Compte_Caissier")
@NamedQuery(name="CompteCaissier.findAll", query="SELECT m FROM CompteCaissier m")
public class CompteCaissier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="DEBIT")
	private BigDecimal debit;
	
	@Column(name="CREDIT")
	private BigDecimal credit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
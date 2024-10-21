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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="ChargeFixe.findAll", query="SELECT f FROM ChargeFixe f")
public class ChargeFixe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_CHARGE")
	private String code;
	
	@Column(name="Designation")
	private String designation;

	@Column(name="MONTANT")
	private BigDecimal montant;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_operation")
	private Date dateoperation;
	

	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="ChargeFixe" ,fetch = FetchType.EAGER)
	private List<DetailChargeFixe> listdetailChargeFixe=new ArrayList<DetailChargeFixe>();




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}




	public BigDecimal getMontant() {
		return montant;
	}




	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}




	public List<DetailChargeFixe> getListdetailChargeFixe() {
		return listdetailChargeFixe;
	}




	public void setListdetailChargeFixe(List<DetailChargeFixe> listdetailChargeFixe) {
		this.listdetailChargeFixe = listdetailChargeFixe;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Date getDateoperation() {
		return dateoperation;
	}




	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}
	

	

}
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;




/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@Table(name="Detail_Cout_Production")
@NamedQuery(name="DetailCoutProduction.findAll", query="SELECT p FROM DetailCoutProduction p")
public class DetailCoutProduction implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="MONTANT")
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn(name="id_dcharge")
	private Charges charges;
	
	@Column(name="COUTUNITAIRE")
	private BigDecimal coutunitaire=BigDecimal.ZERO;
	
	@Column(name="POURCENTAGE")
	private BigDecimal pourcentage=BigDecimal.ZERO;
	
	@Column(name="POURCENTAGEDH")
	private BigDecimal pourcentagedh=BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name="id_charge_production")
	private ChargeProduction ChargeProduction;
	

	public DetailCoutProduction() {
	}




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




	public BigDecimal getMontant() {
		return montant;
	}




	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}




	public Charges getCharges() {
		return charges;
	}




	public void setCharges(Charges charges) {
		this.charges = charges;
	}




	public BigDecimal getCoutunitaire() {
		return coutunitaire;
	}




	public void setCoutunitaire(BigDecimal coutunitaire) {
		this.coutunitaire = coutunitaire;
	}




	public BigDecimal getPourcentage() {
		return pourcentage;
	}




	public void setPourcentage(BigDecimal pourcentage) {
		this.pourcentage = pourcentage;
	}




	public BigDecimal getPourcentagedh() {
		return pourcentagedh;
	}




	public void setPourcentagedh(BigDecimal pourcentagedh) {
		this.pourcentagedh = pourcentagedh;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public ChargeProduction getChargeProduction() {
		return ChargeProduction;
	}




	public void setChargeProduction(ChargeProduction chargeProduction) {
		ChargeProduction = chargeProduction;
	}




	

}
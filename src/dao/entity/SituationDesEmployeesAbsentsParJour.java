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
@NamedQuery(name="Articles.findAll", query="SELECT f FROM Articles f")
public class SituationDesEmployeesAbsentsParJour implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;
	
	@Column(name="1")
	private String j_1;
	@Column(name="2")
	private String j_2;
	@Column(name="3")
	private String j_3;
	@Column(name="4")
	private String j_4;
	@Column(name="5")
	private String j_5;
	
	@Column(name="6")
	private String j_6;
	
	@Column(name="7")
	private String j_7;
	@Column(name="8")
	private String j_8;
	@Column(name="9")
	private String j_9;
	@Column(name="10")
	private String j_10;
	@Column(name="11")
	private String j_11;
	@Column(name="12")
	private String j_12;
	@Column(name="13")
	private String j_13;
	@Column(name="14")
	private String j_14;
	@Column(name="15")
	private String j_15;
	@Column(name="16")
	private String j_16;
	@Column(name="17")
	private String j_17;
	@Column(name="18")
	private String j_18;
	@Column(name="19")
	private String j_19;
	@Column(name="20")
	private String j_20;
	@Column(name="21")
	private String j_21;
	@Column(name="22")
	private String j_22;
	@Column(name="23")
	private String j_23;
	@Column(name="24")
	private String j_24;
	@Column(name="25")
	private String j_25;
	@Column(name="26")
	private String j_26;
	@Column(name="27")
	private String j_27;
	@Column(name="28")
	private String j_28;
	@Column(name="29")
	private String j_29;
	@Column(name="30")
	private String j_30;
	@Column(name="31")
	private String j_31;
	
	@Column(name="EQUIPE")
	private String equipe;
	
	@Column(name="TOTAL")
	private String total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public String getJ_1() {
		return j_1;
	}

	public void setJ_1(String j_1) {
		this.j_1 = j_1;
	}

	public String getJ_2() {
		return j_2;
	}

	public void setJ_2(String j_2) {
		this.j_2 = j_2;
	}

	public String getJ_3() {
		return j_3;
	}

	public void setJ_3(String j_3) {
		this.j_3 = j_3;
	}

	public String getJ_4() {
		return j_4;
	}

	public void setJ_4(String j_4) {
		this.j_4 = j_4;
	}

	public String getJ_5() {
		return j_5;
	}

	public void setJ_5(String j_5) {
		this.j_5 = j_5;
	}

	public String getJ_6() {
		return j_6;
	}

	public void setJ_6(String j_6) {
		this.j_6 = j_6;
	}

	public String getJ_7() {
		return j_7;
	}

	public void setJ_7(String j_7) {
		this.j_7 = j_7;
	}

	public String getJ_8() {
		return j_8;
	}

	public void setJ_8(String j_8) {
		this.j_8 = j_8;
	}

	public String getJ_9() {
		return j_9;
	}

	public void setJ_9(String j_9) {
		this.j_9 = j_9;
	}

	public String getJ_10() {
		return j_10;
	}

	public void setJ_10(String j_10) {
		this.j_10 = j_10;
	}

	public String getJ_11() {
		return j_11;
	}

	public void setJ_11(String j_11) {
		this.j_11 = j_11;
	}

	public String getJ_12() {
		return j_12;
	}

	public void setJ_12(String j_12) {
		this.j_12 = j_12;
	}

	public String getJ_13() {
		return j_13;
	}

	public void setJ_13(String j_13) {
		this.j_13 = j_13;
	}

	public String getJ_14() {
		return j_14;
	}

	public void setJ_14(String j_14) {
		this.j_14 = j_14;
	}

	public String getJ_15() {
		return j_15;
	}

	public void setJ_15(String j_15) {
		this.j_15 = j_15;
	}

	public String getJ_16() {
		return j_16;
	}

	public void setJ_16(String j_16) {
		this.j_16 = j_16;
	}

	public String getJ_17() {
		return j_17;
	}

	public void setJ_17(String j_17) {
		this.j_17 = j_17;
	}

	public String getJ_18() {
		return j_18;
	}

	public void setJ_18(String j_18) {
		this.j_18 = j_18;
	}

	public String getJ_19() {
		return j_19;
	}

	public void setJ_19(String j_19) {
		this.j_19 = j_19;
	}

	public String getJ_20() {
		return j_20;
	}

	public void setJ_20(String j_20) {
		this.j_20 = j_20;
	}

	public String getJ_21() {
		return j_21;
	}

	public void setJ_21(String j_21) {
		this.j_21 = j_21;
	}

	public String getJ_22() {
		return j_22;
	}

	public void setJ_22(String j_22) {
		this.j_22 = j_22;
	}

	public String getJ_23() {
		return j_23;
	}

	public void setJ_23(String j_23) {
		this.j_23 = j_23;
	}

	public String getJ_24() {
		return j_24;
	}

	public void setJ_24(String j_24) {
		this.j_24 = j_24;
	}

	public String getJ_25() {
		return j_25;
	}

	public void setJ_25(String j_25) {
		this.j_25 = j_25;
	}

	public String getJ_26() {
		return j_26;
	}

	public void setJ_26(String j_26) {
		this.j_26 = j_26;
	}

	public String getJ_27() {
		return j_27;
	}

	public void setJ_27(String j_27) {
		this.j_27 = j_27;
	}

	public String getJ_28() {
		return j_28;
	}

	public void setJ_28(String j_28) {
		this.j_28 = j_28;
	}

	public String getJ_29() {
		return j_29;
	}

	public void setJ_29(String j_29) {
		this.j_29 = j_29;
	}

	public String getJ_30() {
		return j_30;
	}

	public void setJ_30(String j_30) {
		this.j_30 = j_30;
	}

	public String getJ_31() {
		return j_31;
	}

	public void setJ_31(String j_31) {
		this.j_31 = j_31;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
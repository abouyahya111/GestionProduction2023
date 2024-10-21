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
@NamedQuery(name="GrammageCarton.findAll", query="SELECT f FROM GrammageCarton f")
public class GrammageCarton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_GRAMMAGE")
	private String codeGrammage;
	
	@Column(name="GRAMMAGE_CARTON")
	private BigDecimal grammageCarton ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeGrammage() {
		return codeGrammage;
	}

	public void setCodeGrammage(String codeGrammage) {
		this.codeGrammage = codeGrammage;
	}

	public BigDecimal getGrammageCarton() {
		return grammageCarton;
	}

	public void setGrammageCarton(BigDecimal grammageCarton) {
		this.grammageCarton = grammageCarton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	
	

}
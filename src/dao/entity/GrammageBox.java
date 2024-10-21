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
@NamedQuery(name="GrammageBox.findAll", query="SELECT f FROM GrammageBox f")
public class GrammageBox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE_GRAMMAGE")
	private String codeGrammage;
	
	@Column(name="GRAMMAGE_BOX")
	private BigDecimal grammageBox ;

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

	public BigDecimal getGrammageBox() {
		return grammageBox;
	}

	public void setGrammageBox(BigDecimal grammageBox) {
		this.grammageBox = grammageBox;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
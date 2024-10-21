package dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RecapFicheEmploye {
	
	private BigDecimal delaiEmploye;
	private BigDecimal coutTotal;
	private BigDecimal  remise;
	private BigDecimal avance;
	private Date dateSituation;
	public BigDecimal getDelaiEmploye() {
		return delaiEmploye;
	}
	public void setDelaiEmploye(BigDecimal delaiEmploye) {
		this.delaiEmploye = delaiEmploye;
	}
	public BigDecimal getCoutTotal() {
		return coutTotal;
	}
	public void setCoutTotal(BigDecimal coutTotal) {
		this.coutTotal = coutTotal;
	}
	public BigDecimal getRemise() {
		return remise;
	}
	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}
	public Date getDateSituation() {
		return dateSituation;
	}
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	public BigDecimal getAvance() {
		return avance;
	}
	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}
	
	

}

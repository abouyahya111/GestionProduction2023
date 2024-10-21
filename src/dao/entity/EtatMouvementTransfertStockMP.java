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
import javax.persistence.Temporal;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="EtatMouvementTransfertStockMP.findAll", query="SELECT f FROM EtatMouvementTransfertStockMP f")
public class EtatMouvementTransfertStockMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	 @Column(name="DATE_MOUVEMENT")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateMouvement;
	
	
	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private FournisseurMP fournisseurMP;
	
	// Stock Transfert
	
	@Column(name="quantite_transfert")
	private BigDecimal quantiteTransfert;

	
	// Stock Sortie En Attent
	
		@Column(name="quantite_sortie_enattent")
		private BigDecimal quantiteEnAttent;
	
		// Stock Sortie Retour
		
			@Column(name="quantite_retour")
			private BigDecimal quantiteRetour;
	
	
			// Stock Sortie PF
			
				@Column(name="quantite_sortie_pf")
				private BigDecimal quantiteSortiePF;


				public int getId() {
					return id;
				}


				public void setId(int id) {
					this.id = id;
				}


				public Date getDateMouvement() {
					return dateMouvement;
				}


				public void setDateMouvement(Date dateMouvement) {
					this.dateMouvement = dateMouvement;
				}


				public MatierePremier getMp() {
					return mp;
				}


				public void setMp(MatierePremier mp) {
					this.mp = mp;
				}


				public FournisseurMP getFournisseurMP() {
					return fournisseurMP;
				}


				public void setFournisseurMP(FournisseurMP fournisseurMP) {
					this.fournisseurMP = fournisseurMP;
				}


				public BigDecimal getQuantiteTransfert() {
					return quantiteTransfert;
				}


				public void setQuantiteTransfert(BigDecimal quantiteTransfert) {
					this.quantiteTransfert = quantiteTransfert;
				}


				public BigDecimal getQuantiteEnAttent() {
					return quantiteEnAttent;
				}


				public void setQuantiteEnAttent(BigDecimal quantiteEnAttent) {
					this.quantiteEnAttent = quantiteEnAttent;
				}


				public BigDecimal getQuantiteRetour() {
					return quantiteRetour;
				}


				public void setQuantiteRetour(BigDecimal quantiteRetour) {
					this.quantiteRetour = quantiteRetour;
				}


				public BigDecimal getQuantiteSortiePF() {
					return quantiteSortiePF;
				}


				public void setQuantiteSortiePF(BigDecimal quantiteSortiePF) {
					this.quantiteSortiePF = quantiteSortiePF;
				}


				public static long getSerialversionuid() {
					return serialVersionUID;
				}		
			
			
			
}
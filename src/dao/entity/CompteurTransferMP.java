package dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="COMPTEUR_TRANSFER_MP")
@NamedQuery(name="CompteurTransferMP.findAll", query="SELECT d FROM CompteurTransferMP d")
public class CompteurTransferMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name="CODE_DEPOT")
	private String codeDepot;
	
	@JoinColumn(name="COMPTEUR")
	private int compteur;


	public CompteurTransferMP() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}

	

	
	

}
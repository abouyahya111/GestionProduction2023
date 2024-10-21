package dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String login;

	private String nom;

	private String password;
	 
	private String codeDepot;

	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;

	public Utilisateur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}

/*	public List<Commande> getCommandes1() {
		return this.commandes1;
	}

	public void setCommandes1(List<Commande> commandes1) {
		this.commandes1 = commandes1;
	}
	public Commande addCommandes1(Commande commandes1) {
		getCommandes1().add(commandes1);
		commandes1.setUtilisateur1(this);

		return commandes1;
	}

	public Commande removeCommandes1(Commande commandes1) {
		getCommandes1().remove(commandes1);
		commandes1.setUtilisateur1(null);

		return commandes1;
	}

	public List<Commande> getCommandes2() {
		return this.commandes2;
	}

	public void setCommandes2(List<Commande> commandes2) {
		this.commandes2 = commandes2;
	}*/

	/*public Commande addCommandes2(Commande commandes2) {
		getCommandes2().add(commandes2);
		commandes2.setUtilisateur2(this);

		return commandes2;
	}

	public Commande removeCommandes2(Commande commandes2) {
		getCommandes2().remove(commandes2);
		commandes2.setUtilisateur2(null);

		return commandes2;
	}

	public List<Commande> getCommandes3() {
		return this.commandes3;
	}

	public void setCommandes3(List<Commande> commandes3) {
		this.commandes3 = commandes3;
	}

	public Commande addCommandes3(Commande commandes3) {
		getCommandes3().add(commandes3);
		commandes3.setUtilisateur3(this);

		return commandes3;
	}

	public Commande removeCommandes3(Commande commandes3) {
		getCommandes3().remove(commandes3);
		commandes3.setUtilisateur3(null);

		return commandes3;
	}

	public List<Facture> getFactures1() {
		return this.factures1;
	}

	public void setFactures1(List<Facture> factures1) {
		this.factures1 = factures1;
	}

	public Facture addFactures1(Facture factures1) {
		getFactures1().add(factures1);
		factures1.setUtilisateur1(this);

		return factures1;
	}

	public Facture removeFactures1(Facture factures1) {
		getFactures1().remove(factures1);
		factures1.setUtilisateur1(null);

		return factures1;
	}

	public List<Facture> getFactures2() {
		return this.factures2;
	}

	public void setFactures2(List<Facture> factures2) {
		this.factures2 = factures2;
	}

	public Facture addFactures2(Facture factures2) {
		getFactures2().add(factures2);
		factures2.setUtilisateur2(this);

		return factures2;
	}

	public Facture removeFactures2(Facture factures2) {
		getFactures2().remove(factures2);
		factures2.setUtilisateur2(null);

		return factures2;
	}

	public List<Facture> getFactures3() {
		return this.factures3;
	}

	public void setFactures3(List<Facture> factures3) {
		this.factures3 = factures3;
	}

	public Facture addFactures3(Facture factures3) {
		getFactures3().add(factures3);
		factures3.setUtilisateur3(this);

		return factures3;
	}

	public Facture removeFactures3(Facture factures3) {
		getFactures3().remove(factures3);
		factures3.setUtilisateur3(null);

		return factures3;
	}

	public List<Production> getProductions1() {
		return this.productions1;
	}

	public void setProductions1(List<Production> productions1) {
		this.productions1 = productions1;
	}

	public Production addProductions1(Production productions1) {
		getProductions1().add(productions1);
		productions1.setUtilisateur1(this);

		return productions1;
	}

	public Production removeProductions1(Production productions1) {
		getProductions1().remove(productions1);
		productions1.setUtilisateur1(null);

		return productions1;
	}

	public List<Production> getProductions2() {
		return this.productions2;
	}

	public void setProductions2(List<Production> productions2) {
		this.productions2 = productions2;
	}

	public Production addProductions2(Production productions2) {
		getProductions2().add(productions2);
		productions2.setUtilisateur2(this);

		return productions2;
	}

	public Production removeProductions2(Production productions2) {
		getProductions2().remove(productions2);
		productions2.setUtilisateur2(null);

		return productions2;
	}

	public List<Production> getProductions3() {
		return this.productions3;
	}

	public void setProductions3(List<Production> productions3) {
		this.productions3 = productions3;
	}

	public Production addProductions3(Production productions3) {
		getProductions3().add(productions3);
		productions3.setUtilisateur3(this);

		return productions3;
	}

	public Production removeProductions3(Production productions3) {
		getProductions3().remove(productions3);
		productions3.setUtilisateur3(null);

		return productions3;
	}

	public List<Reglement> getReglements1() {
		return this.reglements1;
	}

	public void setReglements1(List<Reglement> reglements1) {
		this.reglements1 = reglements1;
	}

	public Reglement addReglements1(Reglement reglements1) {
		getReglements1().add(reglements1);
		reglements1.setUtilisateur1(this);

		return reglements1;
	}

	public Reglement removeReglements1(Reglement reglements1) {
		getReglements1().remove(reglements1);
		reglements1.setUtilisateur1(null);

		return reglements1;
	}

	public List<Reglement> getReglements2() {
		return this.reglements2;
	}

	public void setReglements2(List<Reglement> reglements2) {
		this.reglements2 = reglements2;
	}

	public Reglement addReglements2(Reglement reglements2) {
		getReglements2().add(reglements2);
		reglements2.setUtilisateur2(this);

		return reglements2;
	}

	public Reglement removeReglements2(Reglement reglements2) {
		getReglements2().remove(reglements2);
		reglements2.setUtilisateur2(null);

		return reglements2;
	}

	public List<Reglement> getReglements3() {
		return this.reglements3;
	}

	public void setReglements3(List<Reglement> reglements3) {
		this.reglements3 = reglements3;
	}

	public Reglement addReglements3(Reglement reglements3) {
		getReglements3().add(reglements3);
		reglements3.setUtilisateur3(this);

		return reglements3;
	}

	public Reglement removeReglements3(Reglement reglements3) {
		getReglements3().remove(reglements3);
		reglements3.setUtilisateur3(null);

		return reglements3;
	}*/

}
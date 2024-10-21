package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@NamedQuery(name="Employe.findAll", query="SELECT e FROM Employe e")
public class Employe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="cout_horaire")
	private BigDecimal coutHoraire;

	private String matricule;

	private String nom;
	
	private String prenom;
	
	private String sex;
	
	private BigDecimal nombreEnfant;
	
	private String situation;
	
	private String cin;
	
	private String societe;
	
	private String numDossier;

	private BigDecimal remise;

	private String responsabilite;
	
	private String adresse;
	
	private String service;
	
	private String lieuNaissance;
	
	@Column(name="NUM_CNSS")
	private String NumCNSS;
	
	@Column(name="SALARIE")
	private boolean salarie;
	
	private boolean actif;
	
	@Column(name="NUM_TEL")
	private String numTel;
	
	@Column(name="BLOCAGE_EMPLOYE")
	private String blocageEmploye;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@Column(name="DATE_PREMIER_PRODUCTION")
	private Date datePremiereProduction;
	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@Column(name="DATE_NAISSANCE")
	private Date dateNaissance;
	
	@Column(name="DATE_ENTRE")
	private Date dateEntre;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_SORTIE")
	private Date dateSortie;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;

	@ManyToOne
	@JoinColumn(name="ID_EQUIPE")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	@Transient
	private String salarier;
	
	@Transient
	private String active;
	
	@Column(name="PRIME")
	private boolean prime;
	
	
	@Column(name="PRIME_ANCIENTE")
	private boolean primeAnciente;
	
	 
	
	@Transient
	private String bloque;
	
	@Transient
	private String nomafficher;
	
	
	
	public Employe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCoutHoraire() {
		return this.coutHoraire;
	}

	public void setCoutHoraire(BigDecimal coutHoraire) {
		this.coutHoraire = coutHoraire;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getRemise() {
		return this.remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	public String getResponsabilite() {
		return this.responsabilite;
	}

	public void setResponsabilite(String responsabilite) {
		this.responsabilite = responsabilite;
	}

	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getBlocageEmploye() {
		return blocageEmploye;
	}

	public void setBlocageEmploye(String blocageEmploye) {
		this.blocageEmploye = blocageEmploye;
	}



	public Utilisateur getUtilCreation() {
		return utilCreation;
	}

	public void setUtilCreation(Utilisateur utilCreation) {
		this.utilCreation = utilCreation;
	}


	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
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

	public TypeResEmploye getTypeResEmploye() {
		return typeResEmploye;
	}

	public void setTypeResEmploye(TypeResEmploye typeResEmploye) {
		this.typeResEmploye = typeResEmploye;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getNumCNSS() {
		return NumCNSS;
	}

	public void setNumCNSS(String numCNSS) {
		NumCNSS = numCNSS;
	}

	public Date getDateEntre() {
		return dateEntre;
	}

	public void setDateEntre(Date dateEntre) {
		this.dateEntre = dateEntre;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public boolean isSalarie() {
		return salarie;
	}

	public void setSalarie(boolean salarie) {
		this.salarie = salarie;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigDecimal getNombreEnfant() {
		return nombreEnfant;
	}

	public void setNombreEnfant(BigDecimal nombreEnfant) {
		this.nombreEnfant = nombreEnfant;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public Date getDateSortie() {
		
		
		
		
		return dateSortie;
		
		
		
		
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getActive() {
		
		if(isActif()==true)
		{
			active = "ACTIF";
			
		}else
		{
			if(getDateSortie()!=null)
			{
				active = "SORTIE";
			}else
			{
				active = "INACTIF";
			}
			
			
		}
		return active;
	}



	

	public String getSalarier() {
		
		if(isSalarie()==true)
		{
			salarier = "Salarie";
		}else
		{
			salarier = "Employé";
		}
		
		return salarier;
		
		
	}

	public String getNomafficher() {
		
		if(getNom()!=null)
		{
			nomafficher=getNom();
		}
		if(getPrenom()!=null)
		{
			nomafficher=getNom() +" "+getPrenom();
		}
		
		
		
		
		
		return nomafficher;
	}

	public boolean isPrime() {
		return prime;
	}

	public void setPrime(boolean prime) {
		this.prime = prime;
	}

	public boolean isPrimeAnciente() {
		return primeAnciente;
	}

	public void setPrimeAnciente(boolean primeAnciente) {
		this.primeAnciente = primeAnciente;
	}

	public Date getDatePremiereProduction() {
		return datePremiereProduction;
	}

	public void setDatePremiereProduction(Date datePremiereProduction) {
		this.datePremiereProduction = datePremiereProduction;
	}





	
	
	

	
}
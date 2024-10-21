package util;

import java.math.BigDecimal;

import main.AuthentificationView;

public interface Constantes {
	
	
	
	public static final int PASSWORD_LENGTH = 8;
	
	public static String CODE_OUI="OUI";
	public static String CODE_NON="NON";
	public static String TRAVAIL_HORS_PROD="TRAVAIL HORS PROD";
	/*ADMIN LOGIN AND MOT DE PASSE */
	
	public static String ADMIN_CONNEXION="admin";
	

	/* etats ordre de fabrication*/
	public static final String ETAT_OF_CREER = "Crée";
	public static final String ETAT_OF_LANCER = "Lancé";
	public static final String ETAT_OF_TERMINER = "Terminé";
	public static final String ETAT_OF_ANNULER = "Annulé";
	
	
	
	/*Etats commande */
	public static final String ETAT_COMMANDE_CREER = "Crée";
	public static final String ETAT_COMMANDE_LIVRER = "Livrée";
	public static final String ETAT_COMMANDE_RECU = "Reçu";
	
	/*Etat transfer transfert stock*/
	
	public static final String ETAT_TRANSFER_STOCK_TRANSFERE = "TRANSFER";/*Transfer d'un magasin à un autre dans le meme dépot */
	public static final String ETAT_TRANSFER_STOCK_ENTRE = "ENTRE";/*Stock entré d'un autre dépot */
	public static final String ETAT_TRANSFER_STOCK_SORTIE = "SORTIE";/*Stock sortie */
	public static final String ETAT_TRANSFER_STOCK_TRANSFERE_SORTIE = "SORTIE";/*Stock sortie vers un autre dépot */
	public static final String ETAT_TRANSFER_STOCK_AJOUT = "AJOUT";/*Stock sortie vers un autre dépot */
	public static final String ETAT_TRANSFER_STOCK_SORTIE_PF = "SORTIE_PF";/*Stock sortie vers un magasin produit fini */
	public static final String ETAT_TRANSFER_STOCK_ENTRER_MP = "ENTRER_MP";/*Stock entrer d'un magasin MP*/
	public static final String ETAT_TRANSFER_STOCK_CHARGE = "CHARGE";// Production
	public static final String ETAT_TRANSFER_STOCK_CHARGE_SUPP = "CHARGE_SUPP";// Charge Supp Production
	public static final String ETAT_TRANSFER_STOCK_INITIAL = "INITIAL";// Etat transfer Stock Type Initial
	public static final String ETAT_TRANSFER_RETOUR = "RETOUR";// retour usine
	public static final String ETAT_SORTIE_ENATTENT = "SORTIE_ENATTENT";// SORTIE EN ATTENT
	public static final String ETAT_SORTIE_ENATTENT_VALIDER="SORTIE_ENATTENT_VALIDER";
	public static final String ETAT_RECEPTION_ENATTENT = "RECEPTION_ENATTENT";// RECEPTION EN ATTENT
	public static final String ETAT_RECEPTION_VALIDER = "RECEPTION_VALIDER";// RECEPTION VALIDER
	public static final String ETAT_TRANSFER_STOCK_FABRIQUE = "FABRIQUE";// Production MP
	public static final String ETAT_TRANSFER_STOCK_ENTRE_ENATTENT = "ENTRE_ENATTENT";//STOCKS ENTRER EN ATTENTE CONTENT DES ECARTS ET ATTEND L'ACCEPTATION D'ADMINISTRATION
	
	/* DETAIL TYPE SORTIE FOURNISSEUR ENATTENT  */
	public static final String DETAIL_TYPE_SORTIE_FOURNISSEUR_ENATTENT= "SORTIE EN ATTENT";
	
	/* TYPE DE TRAVAIL pour hors production  */
	public static final String  TYPE_TRAVAIL_HORS_PRODUCTION_PRODUCTION= "PRODUCTION";
	public static final String  TYPE_TRAVAIL_HORS_PRODUCTION_AUTRES= "AUTRES";
	
	
	/*Responsabilité*/
	public static final String EMPLOYE_RESPONSABILITE_AIDE_TECHNITIEN = "ATEC";
	public static final String EMPLOYE_RESPONSABILITE_TECHNITIEN = "TEC";
	public static final String TYPE_EMPLOYE_RESPONSABLE_EQUIPE = "REE";
	public static final String TYPE_EMPLOYE_CHEF_PRODUCTION = "REP";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION = "MOP";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_EQUIPE = "MOE";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC = "MOV";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_MENAGE = "MEN";
	
	public static final String TYPE_EMPLOYE_MAIN_SALARIE = "SALARIE";
	
	/*type d'equipe */
	public static final String TYPE_EQUIPE_CODE_PRPDUCTION = "E_PROD";
	public static final String TYPE_EQUIPE_CODE_GENERIQUE = "E_EMB";
	public static final String TYPE_EQUIPE_CODE_RESPONSABLE = "E_GEN";
	public static final String TYPE_EQUIPE_CODE_PRPDUCTION_MP = "E_PROD_MP";
	public static final String TYPE_EQUIPE_CODE_MENAGE = "E_MEN";
	
	/*Periode*/
	
	public static final String PRPDUCTION_PERIODE_MATIN = "MATIN";
	public static final String PRPDUCTION_PERIODE_SOIR = "SOIR";
	
	
	/*TYPE TRANSFER PRODUIT FINI*/
	public static final String STATUT_TRANSFER_PRODUIT_FINI_ENTRE = "ENTRE";
	public static final String STATUT_TRANSFER_PRODUIT_FINI_SORTIE = "SORTIE";
	
	public static final String TYPE_TRANSFER_PRODUIT_FINI_ENTRE = "ENTRE PRODUCTION";
	public static final String TYPE_TRANSFER_PRODUIT_FINI_SORTIE = "SORTIE COMMERCIAL";
	
	/*CODE PARAMETRE */
	public static final String PARAMETRE_CODE_COUT_HEURE_SUPP_25 = "PARAM_1";
	public static final String PARAMETRE_CODE_COUT_HEURE_SUPP_50 = "PARAM_2";
	public static final String PARAMETRE_CODE_QUANTITE_TOUNAGE_500G = "PARAM_3";
	public static final String PARAMETRE_CODE_COUT_HORAIRE = "PARAM_4";
	public static final String PARAMETRE_CODE_COUT_HORAIRE_CNSS = "PARAM_5";
	public static final String PARAMETRE_CODE_TAUX_CNSS_226 = "PARAM_6";
	public static final String PARAMETRE_CODE_TAUX_CNSS_448 = "PARAM_7";
	public static final String PARAMETRE_CODE_REMISE_CHEF_PROD = "PARAM_8";
	public static final String PARAMETRE_CODE_REMISE_TECHNICIEN = "PARAM_9";
	public static final String PARAMETRE_CODE_REMISE_AIDE_TECHNICIEN = "PARAM_10";
	public static final String PARAMETRE_CODE_REMISE_CHEF_EQUIPE = "PARAM_11";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION = "PARAM_12";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE = "PARAM_13";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_GENERIQUE = "PARAM_14";
	public static final String PARAMETRE_CODE_STOCK_MINIMAL_FILM_NORMAL_100G = "PARAM_15";
	
	/*LIBELLE PARAMETRE */
	public static final String PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS = "COUT HORAIRE CNSS";
	
	public static final String PARAMETRE_LIBELLE_TAUX_CNSS_GLOBAL = "TAUX CNSS GLOBALE";	
	
	
	
	/*TYPE MAGASIN*/
	public static final String MAGASIN_CODE_TYPE_MP= "MP";
	public static final String MAGASIN_CODE_TYPE_DECHET_MP= "MP_DECHET";
	public static final String MAGASIN_LIBELLE_TYPE_MP= "Matière Première";
	public static final String MAGASIN_LIBELLE_TYPE_DECHET_MP= "Dechet Matière Première";
	public static final String MAGASIN_CODE_TYPE_PF= "PF";
	public static final String MAGASIN_LIBELLE_TYPE_PF= "Produit Fini";
	
	
	/*TYPE MAGASIN*/
	public static final String MAGASIN_CODE_CATEGORIE_STOCKAGE= "STK";
	public static final String MAGASIN_LIBELLE_CATEGORIE_STOCKAGE= "Magasin Stockage";
	public static final String MAGASIN_CODE_CATEGORIE_PRODUCTION= "PROD";
	public static final String MAGASIN_LIBELLE_CATEGORIE_PRODUCTION= "Magasin Production";
	public static final String MAGASIN_CODE_CATEGORIE_PRODUCTION_CARTON= "PRODCAR";
	public static final String MAGASIN_LIBELLE_CATEGORIE_PRODUCTION_CARTON= "Magasin Production CARTON";
	
	/*SEQUENCEUR LIBELLE CLASSE POUR GENERER LES CODES */
	
	public static final String PARAMETRE_LIBELLE ="Parametre";
	public static final String MAGASIN_LIBELLE ="Magasin";
	public static final String MATIERE_PREMIERE_LIBELLE ="MatierePremier";
	public static final String AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE ="AutresDechetMatierePremier";
	public static final String FOURNISSEUR_MATIERE_PREMIERE ="FournisseurMP";
	public static final String MACHINE_LIBELLE ="Machine";
	public static final String LIGNE_MACHINE_PREMIERE_LIBELLE ="LigneMachine";
	public static final String EQUIPE_PREMIERE_LIBELLE ="Equipe";
	public static final String EMPLOYE_NUM_DOSSIER_LIBELLE ="Employe";
	public static final String PRODUCTION_LIBELLE ="Production";
	public static final String CLIENT_LIBELLE ="Client";
	public static final String FACTURE_PRODUCTION_LIBELLE ="FactureProduction";
	
/*SEQUENCEUR CODE CLASSE POUR GENERER LES CODES */
	
	public static final String CODE_SERVICE_SEQUENCEUR ="Service_";
	
 
	
	public static final String CODE_DEPOT_SEQUENCEUR ="Depot_";
	
	public static final String CODE_MACHINE_SEQUENCEUR ="Machine_";
	
	public static final String CODE_COMPTE_MAGASINIER ="Magasinier_";
	
	/*Libelle  charges sequenseur*/
	public static final String CHARGES= "charges";
	
	
	/*code TYPE charges*/
	public static final String CODE_CHARGES_FIXE= "F";
	public static final String CODE_CHARGES_VARIABLE= "V";
	
	/*ETAT STATISTIQUE FINANCIERE MP*/
	public static final String ETAT_STATISTIQUE_FINANCIERE_INVENTAIRE= "INVENTAIRE";
	
	/*TYPE CHARGES*/
	public static final String CLIENT_LIBELLE_EXTERNE= "EXTERNE";
	public static final String CLIENT_CLIENT_INTERNE= "INTERNE";
	
	/* GENERER CODE CHARGES  */
	public static final String CHARGEST_FIX ="F";
	public static final String CHARGEST_VARIABLE ="V";
	
	
	
	/*FORMAT DATE */
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String DATE_FORMAT_YEAR = "yyyy";	
	
	/*CODE MENU*/
	public static final String MENU_DEPOT = "depotMenu";
	public static final String MENU_MATIERE_PREMIERE = "MatierePremierMenu";
	public static final String MENU_ARTICLE = "articleMenu";
	public static final String MENU_STOCK_MATIERE_PREMIERE = "stockMPMenu";
	public static final String MENU_MACHINE = "uniteFabMenu";
	public static final String MENU_EQUIPE = "equipeFabMenu";
	public static final String MENU_ORDRE_FABRICATION = "ordreFabMenu";
	public static final String MENU_STOCK_PRODUIT_FINI = "stockPFMenu";
	public static final String MENU_PARAMETRE = "parametreMenu";
	public static final String MENU_CLIENT = "clientMenu";
	public static final String MENU_FOURNISSEUR = "FournisseurMenu";
	public static final String MENU_BONENTRERSORTIEMP = "BonEntrerSortieMPMenu";
	public static final String MENU_PRODCARTON = "ProdCartonMenu";
	public static final String MENU_REFERENTIEL = "ReferentielMenu";
	
	public static final String SERVICE_EMPLOYE ="PRODUCTION";
	
	
	/*CODE MATIETRE PREMIERE*/
	public static final String MATIERE_PREMIERE_FILM_NORMAL_2OOG = "MP_57";
	public static final String MATIERE_PREMIERE_FILM_GOLD_2OOG = "MP_54";
	public static final String MATIERE_PREMIERE_SCOTCH_1000M = "MP_61";
	public static final String MATIERE_PREMIERE_SERVICE_PRODUCTION = "MP_SERV";
	
	/*CODE SOUS CATEGORIE MATIETRE PREMIERE*/
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_THE = "TH001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL = "FN001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD = "FG001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE = "CEN001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH = "SCO001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON = "CA001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE = "PAP001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_BOX = "BOX001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE = "BOXM001";

	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU = "CAD001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF = "ADH001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET = "SACH001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_SAC = "SAC001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE = "PIEC001";
	
	
	/*PRIORITE ESTIMATION MATIERE PREMIERE*/
	public static final int PRIORITE_ESTIMATION_0 = 0;
	public static final int PRIORITE_ESTIMATION_1 = 1;
	public static final int PRIORITE_ESTIMATION_2 = 2;
	
	/*CODE CATEGORIE MATIERE PREMIERE*/
	public static final String CATEGORIE_MATIERE_PREMIERE_SCOTCH  = "CAT001";
	public static final String CODE_CATEGORIE_BOX_100G = "100G";	
	public static final String CODE_CATEGORIE_BOX_125G = "125G";
	public static final String CODE_CATEGORIE_BOX_25G = "25G";
	public static final String CODE_CATEGORIE_BOX_160G = "160G";	
	public static final String CODE_CATEGORIE_BOX_200G ="200G";	
	public static final String CODE_CATEGORIE_BOX_250G ="250G";	
	public static final String CODE_CATEGORIE_BOX_400G = "400G";	
	public static final String CODE_CATEGORIE_BOX_450G = "450G";	
	public static final String CODE_CATEGORIE_BOX_500G = "500G";	
	public static final String CODE_CATEGORIE_BOX_1KG = "1KG";	
	public static final String CODE_CATEGORIE_BOX_2KG = "2000G";	
	public static final String CODE_CATEGORIE_BOX_5KG = "5KG";			
	public static final String CODE_CATEGORIE_BOX_METEL_500G = "ME500G";
	public static final String CODE_CATEGORIE_BOX_METEL_200G = "ME200G";
	
	
	
	
	/*CODE MAGASIN*/
	
	public static final String CODE_MAGASIN_FOURNISSEUR = "SIE_MAG_23";
	public static final String CODE_MAGASIN_STOCKAGE_EN_ATTENTE = "STO_MAG_18";
	
	
	/* UNITE PIECE */
	public static final String UNITE_PIECE = "Piece";
	
	/* UNITE KG */
	public static final String UNITE_KG= "KG";
	
	/* UNITE KG */
	public static final String UNITE_METTRE= "m";
	
	/* TYPE OFFRE AUTRES */
	public static final String TYPE_OFFRE_AUTRES = "AUTRES";
	
	/* TYPE OFFRE PF */
	public static final String TYPE_OFFRE_PF = "PF";
	
	/* TYPE OFFRE SPECIALE */
	public static final String TYPE_OFFRE_SPECIALE = "SPECIALE";
	
	/* TYPE OFFRE Autres PF */
	public static final String TYPE_OFFRE_AUTRES_PF = "AUTRES_PF";
	
	
	/* OFFRE VARIANT */
	public static final String  OFFRE_VARIANT = "VARIANT";
	public static final String  OFFRE_NORMAL = "NORMAL";
	
	public static final String TRANSFERE_COD_PRODUCTION_PR="SORTIE_PF";
	
	/* CODE BOX */
	public static final String CODE_BOX = "BOX001";
	
	/* CODE BOX */
	public static final String CODE_PIECE = "PIEC001";

	/* CODE CARTON */
	public static final String CODE_CARTON = "CA001";

	/* CODE TAMPON */
	public static final String CODE_TAMPON = "TMP001";

	/* CODE STICKERS */
	public static final String CODE_STICKERS = "STI001";

	/* CODE THERRES VERRES */
	public static final String CODE_THERRES_VERRES = "CAD001";
	
	/* CODE MP THERRES  */
	public static final String CODE_MP_THERRES = "MP_67";
	
	public static final String MP_CONTIENT_VERRE = "VERRE";
	
	
	
	/* CODE SACHET */
	public static final String CODE_SACHET = "SACH001";
	
	
	
	
	
	
	/*CODE DEPOT*/
	public static final String CODE_DEPOT_SIEGE="SIEGE";	
	public static final String CODE_DEPOT_STOCK_EN_ATTENTE="SIEGE";	
	
	public static final String	MAGASIN_CODE_TYPE_MP_ATT="MP-ATT";
	
	
	/*CODE MACHINE LIER AU MAGASIN STOCKAGE : c'est une machine vertuelle*/
	public static final String CODE_MACHINE_STOCKAGE = "M0";
	public static final String LIBELLE_MACHINE_STOCKAGE = "MAGASIN STOCKAGE";
	
	public static final BigDecimal COUT_HEURE_SUPPLEMENTAIRE_25 = new BigDecimal(16.825);
	
	public static final BigDecimal COUT_HEURE_SUPPLEMENTAIRE_50 = new BigDecimal(20.19);
	
	/*Désignation service pour la facturation des services production */

	/*CODE DEPOT PRODUCTION*/
	
	public static final String CODE_DEPOT_PRODUCTION_TANTAN="TANTAN";
	public static final String CODE_DEPOT_PRODUCTION_LAAYOUNE="LAAYOUNE";
	
	public static final String DEBUT_NUM_OF_PRODUCTION_TANTAN="T";
	public static final String DEBUT_NUM_OF_PRODUCTION_LAAYOUNE="L";
	
	public static final String CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_TANTAN="TAN_C1";
	public static final String CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_LAAYOUNE="LAA_C1";
	
	//pourcentage
		public static final String POURCENTAGE="%";
		
		//Pourcentage_DH
		
		public static final String POURCENTAGEDH="DH";
		
	// Promotion offre
		public static final String PROMOTION_OFFRE="P";
	
	// Code charge Depot variable à changer 
		public static final String CODE_FRAIS_DEPOT="V1";
	
		//Bon Fixe 
				public static final String BON_FIXE="BF";
				
				
		//Bon Variable 
		public static final String BON_VARIABLE="BV";
		
		// percentage Production 
		public static final String PERCENTAGE_PRODUCTION="PARAM_16";
		
		// Scotch 1000 M 
		public static final BigDecimal SCOTCHE_MULTIPLE_1000=new BigDecimal(1000);
		
		// Centure 5000
		public static final BigDecimal CENTURE_5000=new BigDecimal(5000);
		
		// PROMO POURCENTAGE PRODUCTION		
		public static final String POURCENTAGE_PROMO="PROMO";
		
		// TYPE SORTIE 
		
		public static final String TYPE_SORTIE_DECHET="DECHETS";
		public static final String TYPE_SORTIE_MANQUE="MANQUE";
		public static final String TYPE_SORTIE_RETOUR="RETOUR";
		public static final String SOUS_TYPE_SORTIE_ENATTENT="SORTIE EN ATTENT";
		public static final String SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT="RETOUR EN-ATTENTE";
		public static final String SOUS_TYPE_SORTIE_DEFINITIF="SORTIE DEFINITIF";
		public static final String SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER="RETOUR_FOURNISSEUR_ANNULER";
		public static final String SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_DEFINITIF="RETOUR DIFINITIVE ";
		public static final String TYPE_SORTIE_RETOUR_FOURNISSEUR="RETOUR FOURNISSEUR";
		
		// SEX 
		
		public static final String SEX_MASCULIN="M";
		public static final String SEX_FEMININ="F";
		
		// SITUATION
		public static final String SITUATION_MARIE="MARIÉ(E)";
		public static final String SITUATION_CELIBATAIRE="CÉLIBATAIRE";
		public static final String SITUATION_VEUF="VEUF(VE)";
		public static final String SITUATION_DIVORCER="DIVORCÉ";
		
		// CODE NUM RECEPTION
		public static final String CODE_RECEPTION="RCP ";
		
		// CODE NUM SORTIE PF
				public static final String CODE_NUM_SORTIE_PF="PRO_";
		
		// CODE MP
		
		public static final String CODE_MP="MP_";
		public static final String CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE ="DCH_";
		
		// CODE TRANSFER SORTIE
		public static final String STRUCTURE_CODE_TRANSFERT_SORTIE=	Constantes.ETAT_TRANSFER_STOCK_SORTIE+"_"+AuthentificationView.utilisateur.getCodeDepot().substring(0, 3)+"_"+"..."+"_...";
		
		// CODE TRANSFER ENTRE
		public static final String STRUCTURE_CODE_TRANSFERT_ENTRE=	Constantes.ETAT_TRANSFER_STOCK_ENTRE+"_"+AuthentificationView.utilisateur.getCodeDepot().substring(0, 3)+"_"+"...."+"_...";
				
		// ETAT MANQUEDECHETFOURNISSEUR
				
		
		        public static final String ETAT_VALIDER="VALIDER";
				public static final String ETAT_INVALIDER="INVALIDER";
		
				/*CODE CATEGORIE MATIETRE PREMIERE*/	
				public static final String CATEGORIE_MATIERE_PREMIERE_CHAARA = "CH001";			
				public static final String CATEGORIE_MATIERE_PREMIERE_MKARKAB = "HB001";	
				
				/*CODE MANQUE PLUS ET MOINS*/	
				public static final String MANQUE_PLUS = "PLUS";			
				public static final String MANQUE_MOINS = "MOINS";		
				
				/*CODE DECHET USINE ET FOURNISSEUR*/	
				public static final String DECHET_USINE = "USINE";			
				public static final String DECHET_FOURNISSEUR = "FOURNISSEUR";	
				
				
	// SOUS FAMILLE ARTICLE 
				
				public static final String SOUS_FAMILLE_CHAARA = "CHAARA";				
				public static final String SOUS_FAMILLE_BOIT = "BOIT";
				public static final String SOUS_FAMILLE_3505 = "3505";
				
				
	// CODE_COUT_PRODUCTION_ARTICLE_PAR BOX			
				
				public static final String COUT_BOX_100G = "COUT BOX 100G";	
				public static final String COUT_BOX_125G = "COUT BOX 125G";	
				public static final String COUT_BOX_160G = "COUT BOX 160G";
				public static final String COUT_BOX_200G = "COUT BOX 200G";
				public static final String COUT_BOX_250G = "COUT BOX 250G";
				public static final String COUT_BOX_400G = "COUT BOX 400G";
				public static final String COUT_BOX_450G = "COUT BOX 450G";
				public static final String COUT_BOX_500G = "COUT BOX 500G";
				public static final String COUT_BOX_1KG = "COUT BOX 1KG";
				public static final String COUT_BOX_2KG = "COUT BOX 2KG";
				public static final String COUT_BOX_5KG = "COUT BOX 5KG";
				
/////////////////////////////////////////////////////////////////////////////////	
				
// grammage BOX			
				
				public static final BigDecimal BOX_100G = new BigDecimal(100);	
				public static final BigDecimal BOX_125G = new BigDecimal(125);
				public static final BigDecimal BOX_25G = new BigDecimal(25);
				public static final BigDecimal BOX_160G = new BigDecimal(160);	
				public static final BigDecimal BOX_200G = new BigDecimal(200);	
				public static final BigDecimal BOX_250G = new BigDecimal(250);	
				public static final BigDecimal BOX_400G = new BigDecimal(400);	
				public static final BigDecimal BOX_450G = new BigDecimal(450);	
				public static final BigDecimal BOX_500G = new BigDecimal(500);	
				public static final BigDecimal BOX_1KG = new BigDecimal(1000);	
				public static final BigDecimal BOX_2KG = new BigDecimal(2000);	
				public static final BigDecimal BOX_5KG = new BigDecimal(5000);			

// Tous les employés absent		
				
				public static final String TOUT_EMPLOYEE_ABSENT = "TOUS";	
				
				
//  absent		
				
				public static final String EMPLOYEE_ABSENT = "ABSENT";	
				
/// PARAMETRE_PRIME_NBRHEURESTOTAL_EMPLOYE
				
				public static final String NBR_HEURES_TOTAL = "NBR_HEURES_TOTAL";					
				public static final String COUT_PRIME_PANIER = "COUT_PRIME_PANIER";
				public static final String COUT_PRIME_TRANSPORT = "COUT_PRIME_TRANSPORT";
				public static final String PRIME_PLAFONNE = "PRIME_PLAFONNE";
				
				
				
	/////// TYPE MP CLIENT OU INTERNE
				
				public static final String MP_CLIENT = "CLIENT";			
				public static final String MP_INTERNE = "INTERNE";
				
	////// CODE INVENTAIRE
				public static final String CODE_INVENTAIRE_1 = "INVENTAIRE_001";	
				public static final String CODE_INVENTAIRE_2 = "INVENTAIRE_002";
				public static final String ETAT_INVENTAIRE_1_NNONVALIDER="NONVALIDER";
				
	////// CODE TYPE DECHET
				public static final String TYPE_DECHET = "DECHET";	
				public static final String TYPE_PERTE = "PERTE";	
				public static final String TYPE_VENTE = "VENTE";	
				public static final String TYPE_DECHET_FOURNISSEUR = "DECHET_FOURNISSEUR";
				
				////// MANQUE INVENTAIRE
				public static final String MANQUE_INVENTAIRE = "MANQUE INVENTAIRE";
				
				// MODE REGLEMENT
				public static String MODE_REGLEMENT_CHEQUE = "Chèque";
				public static String MODE_REGLEMENT_ESPECE = "Espèces";
				public static String MODE_REGLEMENT_VIREMENT = "Virement";
				public static String MODE_REGLEMENT_VERSEMENT = "Versement";
				public static String MODE_REGLEMENT_TRAITE = "Traite";
				
				// TIMBER
				public static BigDecimal TIMBER = new BigDecimal(0.25);
				
				// TVA
				public static BigDecimal TVA = new BigDecimal(0.20);
				
				// Etat Facture MP
				public static String ETAT_NON_REGLE = "Non Reglé";
				public static String ETAT_REGLE = "Reglé";
				
				// MOTIF DE PERTE
				public static String RETOUR_PRODUCTION = "RETOUR_PRODUCTION";
				public static String TRANSFERT_MAGASIN_DECHET = "TRANSFERT_MAGASIN_DECHET";	
				public static String AVANCE_SUR_MAGASINIER = "AVANCE_SUR_MAGASINIER";
				
				/* code Facture TANTAN */

				public static final String CODE_FACTURE_VENTE_ETP = "FACTURE_VENTE_ETP";
				
				/* code BON */

				public static final String CODE_BON = "BON";
				
				/* code Facture LAAYOUNE */

				public static final String CODE_FACTURE_VENTE_LAA = "FACTURE_VENTE_LAA";
				
				/* TYPE DE TRANSFERT */

				public static final String TRANSFERT_MAGASIN_VERS_MAGASIN = "TRANSFERT MAG VERS MAG";
				public static final String TRANSFERT_MACHINE_VERS_MACHINE = "TRANSFERT MACH VERS MACH";
				public static final String RETOUR_MACHINE_VERS_MAGASIN = "TRANSFERT MACH VERS MAG";
				public static final String SORTIE_PF = "SORTIE PF";
				
				
				public static final String CODE_PERTE = "PERTE_";
				
				/*Etat Cout Hors production*/	
				
				public static final String COUT_HORS_PRODUCTION_EN_ATTENT = "ENATTENT";
				public static final String COUT_HORS_PRODUCTION_VALIDER = "VALIDER";
				
	/*CODE RECEPTION INVENTAIRE*/	
				
				public static final String CODE_RECEPTION_INVENTAIRE = "INVENTAIRE";
				
/*CODE RECEPTION INVENTAIRE*/	
				
				public static final String CODE_IMPORTER = "IMPORTER";
				
					
/*LES SERVICES*/	
				
			
				public static final String SERVICE_FRAIS= "FRAIS";
				public static final String SERVICE_PAIE= "PAIE";
				public static final String SERVICE_PRODUCTION= "PRODUCTION";
				
/*LES ACTIONS MARCHANDISE DESPLACER EN ATTENTE*/	
				
				public static final String ACTION_PERTE_DE_QUANTITE = "PERTE DE QUANTITE";
				public static final String ACTION_AVANCE_SUR_CHAUFFEUR = "AVANCE SUR CHAUFFEUR";
				public static final String ACTION_ERREUR_MAGASINIER = "ERREUR MAGASINIER";
				public static final String ACTION_QUANTITE_A_STOCKER = "QUANTITE A STOCKER";
				public static final String ACTION_ERREUR_DE_SAISIE_OPERATEUR = "ERREUR DE SAISIE OPERATEUR";
				
/*ETAT MARCHANDISE DESPLACER EN ATTENTE*/	
				
				public static final String ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER = "Valider";
				public static final String ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER = "Annulé";
				public static final String ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER = "SORTIE_Annulé";
				
/*ETAT MANQUE MARCHANDISE DESPLACER EN ATTENTE*/	
				
				public static final String ETAT_MANQUE_POSITIF = "POSITIF";
				public static final String ETAT_MANQUE_NEGATIF = "NEGATIF";
				
/*BON FRAIS POUR MANQUE MARCHANDISE DESPLACER EN ATTENTE */	
				
				public static final String BON_FRAIS = "BON_FRAIS";
				
				 
/*AFFICHER TRANSFERT ENTRER EN ATTENTE ADMIN OU USER */	
				
				public static final String AFFICHER_TRANSFERT_ENATTENTE_ADMIN = "ADMIN";
				public static final String AFFICHER_TRANSFERT_ENATTENTE_USER = "USER";
				 			
/*TYPE SORTIE PF */	
				
				public static final String SORTIE_PF_DEPOT = "DEPOT";
				public static final String SORTIE_PF_CLIENT = "CLIENT";	
				
				
/*Etat STATISTIQUE PRODUCTION PF ET CARTON */	
				
				public static final String PROD_CARTON = "PROD CARTON";
				public static final String PROD_PF = "PROD PF";				
				
}

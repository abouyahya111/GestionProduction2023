package util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.PrintException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurNumDossierDAOImpl;
import dao.daoImplManager.CompteurTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurNumDossierDAO;
import dao.daoManager.CompteurTransferMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MenuDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.CompteurNumDossier;
import dao.entity.CompteurProduction;
import dao.entity.CompteurTransferMP;

import dao.entity.DetailFactureVenteMP;
import dao.entity.Employe;
import dao.entity.FournisseurMP;
import dao.entity.Habilitation;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Menu;
import dao.entity.Parametre;
import dao.entity.PrixClientMP;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

public class Utils implements Constantes{
	
	private ResultSet rsetSqlServer;
	private Statement stxSqlServer;
	private String query;
	private Statement stx;   
	private static StockMPDAO stockMPDAO=new StockMPDAOImpl();
	private static StockPFDAO stockPFDAO=new StockPFDAOImpl();
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	private static CompteurNumDossierDAO compteurNumDossierDAO=new CompteurNumDossierDAOImpl();
	private static MenuDAO menuDAO =new MenuDAOImpl();
	private static HabilitationDAO habilitationDAO=new HabilitationDAOImpl();
	private static UtilisateurDAO utilisateurDAO=new UtilisateurDAOImpl();
	private static ParametreDAO parametreDAO= new ParametreDAOImpl();
	private static CompteurAbsenceEmployeDAO compteurAbsenceEmployeDAO=new CompteurAbsenceEmployeDAOImpl();
	private static CompteurTransferMPDAO compteurTransferMPDAO=new CompteurTransferMPDAOImpl();
	private static PrixClientMPDAO prixClientMPDAO=new PrixClientMPDAOImpl();
	private static final String LETTRE_PASSWORD = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
	private static final Random RANDOM = new SecureRandom();
	private static  FournisseurMPDAO fournisseurMPDAO;
	private static   DetailTransferMPDAO detailTransferStockMPDAO=   new DetailTransferMPDAOImpl();
	private static List <Object[]> listeObjectMontantTotalInitialMP=new ArrayList<Object[]>();
	
	
	// Verifier Si PoPup est ouvert
	

	
	//Méthode permet de miger la table article d'une base de données SQLSERVER vers une base de données MySql
	   	public void migerTableArticle() {
	   		System.out.println("debut");
	   	query="select * from ARTICLES where CONDITIONNEMENT in ('1','2','4','5','10') and CENTRECOUT5 in ('100 G','200 G','250 G','500 G','1 KG')";
	  System.out.println(query);
	try {
		rsetSqlServer=stxSqlServer.executeQuery(query);
		
		while(rsetSqlServer.next()){
			String query2 ="INSERT INTO ARTICLES VALUES (null,'"+rsetSqlServer.getString("CODE_ARTICLE")+"','"+rsetSqlServer.getString("LIBELLE")+"','"+rsetSqlServer.getString("CENTRECOUT1")+"','"+rsetSqlServer.getString("CENTRECOUT2")+
					"','"+rsetSqlServer.getString("CENTRECOUT3")+"','"+rsetSqlServer.getString("CENTRECOUT4")+"','"+rsetSqlServer.getString("CENTRECOUT5")+"','"+rsetSqlServer.getString("CODE_FONCTION")+
					"','"+rsetSqlServer.getFloat("PRIX_VENTE")+"','"+rsetSqlServer.getFloat("PRIX_RETOUR")+"','"+rsetSqlServer.getFloat("TVA")+"','"+rsetSqlServer.getString("CONDITIONNEMENT")+"')";
			stx.executeUpdate(query2);
			
			 
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	System.out.println("Fin");
	
	   		
	   		
	   	}
	   	
public static String creerCodeOF(String codeArticle,String codeDepot){
	String code="";
	Sequenceur sequenceur =sequenceurDAO.findByLibelle(codeDepot);
	
	code=codeDepot.substring(0, 1)+"_"+codeArticle+"_"+sequenceur.getValeur();
	
	return code;
}


public static String genererCodeFactureVente (String date){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		Utilisateur  utilisateur=AuthentificationView.utilisateur;
		  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_PRODUCTION_TANTAN)) {
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_FACTURE_VENTE_ETP); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code="0"+valeur+"/"+date; 
		  }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100) 
		  { code=valeur+"/"+date; } 
			  }else
			  {
		  
		  code="00"+1+"/"+date;
		  
		  }
		  
		  
		  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_PRODUCTION_LAAYOUNE)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_LAA); 
		  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
		  if(valeur<100 && valeur>=10)
		  {
		  code="0"+valeur+"/"+date; }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100)
		  { code=valeur+"/"+date; } }else {
		  
		  code="00"+1+"/"+date;
		  
		  } }
		  
		  
		  return code;
		 
}




public static String genererCodeService (String service){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		  
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCode(service); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code=service+"0"+valeur; 
		  }else if(valeur<10) 
		  { code=service+"00"+valeur;
		  }else if(valeur>=100) 
		  { code=service+valeur; } 
			  }else
			  {
		  
		  code=service+"00"+1;
		  
		  }
		  
		  

		  
		  
		  return code;
		 
}

 

public static String genererCodeDepot(String depot){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		  
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCode(depot); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code=depot+"0"+valeur; 
		  }else if(valeur<10) 
		  { code=depot+"00"+valeur;
		  }else if(valeur>=100) 
		  { code=depot+valeur; } 
			  }else
			  {
		  
		  code=depot+"00"+1;
		  
		  }
		  
		  

		  
		  
		  return code;
		 
}

public static String genererCodeMachine (String machine){	
	
 
		
		  String code=""; 
		  
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCode(machine); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code=machine+"0"+valeur; 
		  }else if(valeur<10) 
		  { code=machine+"00"+valeur;
		  }else if(valeur>=100) 
		  { code=machine+valeur; } 
			  }else
			  {
		  
		  code=machine+"00"+1;
		  
		  }
		  
		  

		  
		  
		  return code;
		 
}


public static String genererCodeCompteMagasinier (String compte){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		  
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCode(compte); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code=compte+"0"+valeur; 
		  }else if(valeur<10) 
		  { code=compte+"00"+valeur;
		  }else if(valeur>=100) 
		  { code=compte+valeur; } 
			  }else
			  {
		  
		  code=compte+"00"+1;
		  
		  }
		  
		  

		  
		  
		  return code;
		 
}


public static void incrementerValeurSequenceurService (String service){
	
	
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(service);	
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
	
	
}

public static void incrementerValeurSequenceurDepot (String depot){
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(depot);	
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
}

public static void incrementerValeurSequenceurMachine (String machine){
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(machine);	
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
}



public static void incrementerValeurSequenceurCompteMagasinier (String compte){
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(compte);	
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
}



public static boolean genererStockByMagasinMP(MatierePremier matierePremier,List<Magasin> listMagasin , List<FournisseurMP> listFournisseurMP , BigDecimal prix){ 
	StockMP stockMP = new StockMP();
	if(matierePremier!=null && listMagasin!=null){
	
		for(int j=0;j<listMagasin.size();j++){
			
			
			
			if(listFournisseurMP.size()!=0)
			{
				
					
				 for(int k=0;k<listFournisseurMP.size() ; k++)
				 {
					 FournisseurMP fournisseurMP=listFournisseurMP.get(k);
					
						Magasin magasin =listMagasin.get(j);
						
						if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
							stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
							
							
						
						if(stockMP==null){
							stockMP = new StockMP();
							stockMP.setMagasin(magasin);
							stockMP.setMatierePremier(matierePremier);
							stockMP.setQuantiteCommande(BigDecimal.ZERO);
							stockMP.setStock(BigDecimal.ZERO);
							stockMP.setStockMin(BigDecimal.ZERO);
							stockMP.setFournisseurMP(fournisseurMP);
							if(magasin.getType().equals(Constantes.MP_CLIENT))
							{
								stockMP.setPrixUnitaire(matierePremier.getPrix());	
							}else
							{
								stockMP.setPrixUnitaire(prix);
							}
							
							stockMPDAO.add(stockMP);
						} 
						
						
						
						
					 		 }
				
				
			}else
			{
				
				Magasin magasin =listMagasin.get(j);
				
				if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
				//	stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
					
					stockMP =stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
				
				if(stockMP==null){
					stockMP = new StockMP();
					stockMP.setMagasin(magasin);
					stockMP.setMatierePremier(matierePremier);
					stockMP.setQuantiteCommande(BigDecimal.ZERO);
					stockMP.setStock(BigDecimal.ZERO);
					stockMP.setStockMin(BigDecimal.ZERO);
					//stockMP.setFournisseurMP(fournisseurMP);
					if(magasin.getType().equals(Constantes.MP_CLIENT))
					{
						stockMP.setPrixUnitaire(matierePremier.getPrix());	
					}else
					{
						stockMP.setPrixUnitaire(prix);
					}
					stockMPDAO.add(stockMP);
				} 
				
				
			}
	
		}
	}
	return true ;
	
}


public static boolean genererStock(List<MatierePremier>  listmatierePremier,List<Magasin> listMagasin  ){ 
	StockMP stockMP = new StockMP();
    try{
    	
    	
    	fournisseurMPDAO=new FournisseurMPDAOImpl();
    	
    	

   }catch(Exception exp){exp.printStackTrace();		
   JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
	if( listMagasin!=null){
	
		for(int j=0;j<listMagasin.size();j++){
			
		for(int d=0;d<listmatierePremier.size();d++)	
		{
			
		MatierePremier matierePremier=listmatierePremier.get(d);	
		
		 if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  {
			 
			 
			 List<FournisseurMP> listFournisseurMP=fournisseurMPDAO.findByCategorie(matierePremier.getCategorieMp().getSubCategorieMp());  
			 
				
			 for(int k=0;k<listFournisseurMP.size() ; k++)
			 {
				 FournisseurMP fournisseurMP=listFournisseurMP.get(k);
				
					Magasin magasin =listMagasin.get(j);
					
					if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
						System.out.println("MP : "+matierePremier.getId() +" : "+magasin.getId());
						
						stockMP =stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
					if(stockMP!=null)	
					{
						stockMP.setFournisseurMP(fournisseurMP);
						stockMPDAO.edit(stockMP);
					}else
					{
						stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
					}
						
						
						
					
					if(stockMP==null){
						stockMP = new StockMP();
						stockMP.setMagasin(magasin);
						stockMP.setMatierePremier(matierePremier);
						stockMP.setQuantiteCommande(BigDecimal.ZERO);
						stockMP.setStock(BigDecimal.ZERO);
						stockMP.setStockMin(BigDecimal.ZERO);
						stockMP.setFournisseurMP(fournisseurMP);
						if(magasin.getType().equals(Constantes.MP_CLIENT))
						{
							stockMP.setPrixUnitaire(BigDecimal.ZERO);	
						}else
						{
							stockMP.setPrixUnitaire(matierePremier.getPrix());
						}
						stockMPDAO.add(stockMP);
					} 
					
					
					
					
			 }
			 
			 
			 
		  }else
		  {
			  
			  

				
				Magasin magasin =listMagasin.get(j);
				
				if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
				//	stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
					
					System.out.println("MP : "+matierePremier.getId() +" : "+magasin.getId());
					stockMP =stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
				
				
				
				if(stockMP==null){
					stockMP = new StockMP();
					stockMP.setMagasin(magasin);
					stockMP.setMatierePremier(matierePremier);
					stockMP.setQuantiteCommande(BigDecimal.ZERO);
					stockMP.setStock(BigDecimal.ZERO);
					stockMP.setStockMin(BigDecimal.ZERO);
					//stockMP.setFournisseurMP(fournisseurMP);
					if(magasin.getType().equals(Constantes.MP_CLIENT))
					{
						stockMP.setPrixUnitaire(BigDecimal.ZERO);	
					}else
					{
						stockMP.setPrixUnitaire(matierePremier.getPrix());
					}
					
					stockMPDAO.add(stockMP);
				} 
				
				
			  
			  
			  
		  }
			
			
			
		}
			
		
	
		}
	}
	return true ;
	
}



public static boolean genererStockProduitFiniByMagasin(List<Articles> listArticles,List<Magasin> listMagasin){ 
	StockPF stockPF = new StockPF();
	if(listArticles!=null && listMagasin!=null){
	for(int i=0;i<listArticles.size();i++)
		for(int j=0;j<listMagasin.size();j++){
			 
			Articles articles =listArticles.get(i);
			Magasin magasin =listMagasin.get(j);

			stockPF =stockPFDAO.findStockByMagasinPF(articles.getId(), magasin.getId());
			
			if(stockPF==null){
				stockPF = new StockPF();
				stockPF.setMagasin(magasin);
				stockPF.setArticles(articles);
				stockPF.setStock(BigDecimal.ZERO);
				stockPF.setStockMin(BigDecimal.ZERO);
				stockPF.setPrixUnitaire(BigDecimal.ZERO);
				stockPFDAO.add(stockPF);
			}
		}
	}
	return true ;
	
}
	

/*Générer mot de passe */

public static String genererPassword(){
	
	String pw ="";
	
	for(int i=0;i < Constantes.PASSWORD_LENGTH; i++){
		int index = (int) (RANDOM.nextDouble() * LETTRE_PASSWORD.length());
		pw += LETTRE_PASSWORD.substring(index, index + 1);
		
	}
	
	return pw;
}

public static String genererCode (String codeClasse){
	String code;
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);
	 code=sequenceur.getCode()+sequenceur.getValeur();
	
	return code;
	
}


public static String genererCodeAutresDechet (String codeClasse){
	String code = "";
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);
	if(sequenceur==null)
	{
		 Sequenceur  sequenceurTmp=new Sequenceur();  
		  sequenceurTmp.setLibelle(AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
		  sequenceurTmp.setCode(CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
		  sequenceurTmp.setValeur(1);
		  sequenceurDAO.add(sequenceurTmp); 
		  code=sequenceurTmp.getCode()+"00"+sequenceurTmp.getValeur();
	}else
	{
		 int  valeur=sequenceur.getValeur();
		  if(valeur<100 && valeur>=10) {
			  
	 code=sequenceur.getCode()+"0"+valeur; 
	 
	 } else if(valeur<10) 
	 { 
		 code=sequenceur.getCode()+"00"+valeur;
		 
	 }else if(valeur>=100) 
	 { 
		 code=sequenceur.getCode()+valeur; 
		 
	 } 
	}
	 
	
	
	return code;
	
}


public static String genererCode (String code,String libelleClasse){
	String codeGenerer;
		Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(code, libelleClasse);
int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
	codeGenerer=sequenceur.getCode()+valeur;
	
	return codeGenerer;
	
}

public static void incrementerValeurSequenceur (String codeClasse){
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);	
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
}	


public static void incrementerValeurSequenceurAutresDechet (String codeClasse){
	
	  String code=""; 
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);	
	
	  if(sequenceur!=null) { 
		  
		  int  valeur=sequenceur.getValeur()+1;
		sequenceur.setValeur(valeur);
			sequenceurDAO.edit(sequenceur);  
		  
	  }else
		  {
		  Sequenceur  sequenceurTmp=new Sequenceur();  
		  sequenceurTmp.setLibelle(AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
		  sequenceurTmp.setCode(CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
		  sequenceurTmp.setValeur(1);
		  sequenceurDAO.add(sequenceurTmp); 
	 
	  
	  } 
	
	
}	


public static String incrementerchargeVF (String codeClasse){
	String codeGenerer;
	Sequenceur  sequenceur=sequenceurDAO.findByCode(codeClasse);
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
	 codeGenerer=sequenceur.getCode()+valeur;
	return codeGenerer;
}


public static String  genererCodeMagasin(String codeClasse,String codeDepot){
	
	String code =codeDepot.substring(0, 3);
	return code+"_"+genererCode (codeClasse);
		
	
}

public static String  genererCodeMachine(String codeClasse,String codeDepot){
	
	String code =codeDepot.substring(0, 3);
	return code+"_"+genererCode (codeClasse);
		
	
}

public static String  genererCodeReferentiel(String codeClasse,String codeDepot){
	
	String code =codeDepot.substring(0, 3);
	return code+"_"+genererCode (codeClasse);
		
	
}

public static String  genererNumDossierEmploye(){
	 int compteur=0;
	 String numDossier;
	 Calendar cal = Calendar.getInstance();
     cal.setTime(new Date ());
     String  annee = cal.get(Calendar.YEAR)+"";
	 CompteurNumDossier compteurNumDossier=compteurNumDossierDAO.findNumByAnnee(annee);
	 
	 if(compteurNumDossier !=null){
		 compteur=compteurNumDossier.getCompteur()+1;
		 compteurNumDossier.setCompteur(compteur);	
		 compteurNumDossierDAO.edit(compteurNumDossier);
		 
	 }else{
		 compteur=1;
		 compteurNumDossier= new CompteurNumDossier();
		
		 compteurNumDossier.setCompteur(compteur);
		 compteurNumDossier.setAnnee(annee);;
		 compteurNumDossierDAO.add(compteurNumDossier);
		 
	 }
	 if(compteur<10)
	  numDossier=annee+"/00"+compteur;
	 else if (compteur<100)
		 numDossier=annee+"/0"+compteur;
	 else 
		 numDossier=annee+"/"+compteur;
	 
	 return numDossier;
}

public static String  retournerNumDossierEmploye(){
	int compteur=0;
	 String numDossier;
	 Calendar cal = Calendar.getInstance();
    cal.setTime(new Date ());
    String  annee = cal.get(Calendar.YEAR)+"";
	 CompteurNumDossier compteurNumDossier=compteurNumDossierDAO.findNumByAnnee(annee);
	 if(compteurNumDossier!=null){
			 compteur=compteurNumDossier.getCompteur();
	 if(compteur<10)
		  numDossier=annee+"/00"+compteur;
		 else if (compteur<100)
			 numDossier=annee+"/0"+compteur;
		 else 
			 numDossier=annee+"/"+compteur;
	 }else {
		  compteurNumDossier= new CompteurNumDossier();
		  compteurNumDossier.setAnnee(annee);
		  compteurNumDossier.setCompteur(1);
		  compteurNumDossierDAO.add(compteurNumDossier);
		  numDossier=annee+"/001";
		 
	 }
		 
		 return numDossier;
}


public static String  genererNumFactureProduction(String codeClasse){
	int compteur=0;
	 String numFacture="F-";
	 Calendar cal = Calendar.getInstance();
    cal.setTime(new Date ());
    String  annee = cal.get(Calendar.YEAR)+"";
	 Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(annee,codeClasse);
	 if(sequenceur!=null){
			 compteur=sequenceur.getValeur()+1;
			 sequenceur.setValeur(compteur);
			 sequenceurDAO.edit(sequenceur);
	
	 }else {
		 sequenceur= new Sequenceur();
		 sequenceur.setCode(annee);
		 sequenceur.setLibelle(codeClasse);
		 sequenceur.setValeur(1);
		 sequenceurDAO.add(sequenceur);
		 numFacture+=annee+"/001";
		 
	 }
	 
	 if(compteur!=0 && compteur<10)
		 numFacture+=annee+"/00"+compteur;
		 else if (compteur<100)
			 numFacture+=annee+"/0"+compteur;
		 else 
			 numFacture+=annee+"/"+compteur;
		 
		 return numFacture;
}



public static void genererMenuUtilisateur(){ 
	Habilitation habilitation = new Habilitation();
	List<Menu> listeMenu =menuDAO.findAll();
	List<Utilisateur> listUtilisateur = utilisateurDAO.findAll();
	
	
	if(listeMenu!=null && listUtilisateur!=null){
	for(int i=0;i<listeMenu.size();i++)
		for(int j=0;j<listUtilisateur.size();j++){
			 
			Menu menu =listeMenu.get(i);
			Utilisateur utilisateur =listUtilisateur.get(j);

			habilitation  =habilitationDAO.findByMenuUtilisateur(menu.getId(), utilisateur.getId());
			
			if(habilitation==null){
				habilitation = new Habilitation();
				habilitation.setMenu(menu);
				habilitation.setUtilisateur(utilisateur);
				habilitation.setAutorise(true);
				
				habilitationDAO.add(habilitation);
			}
		}
	}
  }


public static Map<String ,BigDecimal > listeParametre(){
	
	
	 Map<String, BigDecimal> mapParametre= new HashMap<>();
	List<Parametre>  listeParametre= parametreDAO.findAll();
	
	for(int i=0;i<listeParametre.size();i++){
		Parametre parametre=listeParametre.get(i) ;
		mapParametre.put(parametre.getCode(),parametre.getValeur());
		
	}
	 
	 
	 return mapParametre;
	
}


public static String  genererCodeTransfer(String codeDepot,String etatTransfer){
	 int compteur=0;
	 String codeTransfer;
	 
	 	Date date = new Date();	
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String dateJourString =dateFormat.format(date);

	 CompteurTransferMP compteurTransferMP=compteurTransferMPDAO.findByDepot(codeDepot);
	 
	 if(compteurTransferMP !=null){
		 compteur=compteurTransferMP.getCompteur()+1;
		 compteurTransferMP.setCompteur(compteur);	
		 compteurTransferMPDAO.edit(compteurTransferMP);
		 
	 }else{
		 compteur=1;
		 compteurTransferMP= new CompteurTransferMP();
		
		 compteurTransferMP.setCompteur(compteur);
		 compteurTransferMP.setCodeDepot(codeDepot);
		 compteurTransferMPDAO.add(compteurTransferMP);
	 }
	
	 codeTransfer =etatTransfer+"_"+codeDepot.substring(0, 3)+"_"+dateJourString+"_"+compteur; 
	 
	 return codeTransfer;
}


public static String  genererCodeSortiePF(String codeDepot,String date){
	 
	 String code=""; 
	 sequenceurDAO=new SequenceurDAOImpl();
 
	  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,ETAT_TRANSFER_STOCK_SORTIE_PF+"_"+codeDepot); 
	  if(sequenceur!=null) { 
		  int  valeur=sequenceur.getValeur()+1;
		  if(valeur<100 && valeur>=10) {
	  code="0"+valeur+"/"+date; 
	  }else if(valeur<10) 
	  { code="00"+valeur+"/"+date;
	  }else if(valeur>=100) 
	  { code=valeur+"/"+date; } 
		  }else
		  {
	  
	  code="00"+1+"/"+date;
		  }
	 return code;
}


public static String  genererCodeTransferInventaire(String codeDepot,String etatTransfer){
	 int compteur=0;
	 String codeTransfer;
	 
	 	Date date = new Date();	
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String dateJourString =dateFormat.format(date);

	 CompteurTransferMP compteurTransferMP=compteurTransferMPDAO.findByDepot(CODE_RECEPTION_INVENTAIRE+"_"+codeDepot);
	 
	 if(compteurTransferMP !=null){
		 compteur=compteurTransferMP.getCompteur()+1;
		 compteurTransferMP.setCompteur(compteur);	
		 compteurTransferMPDAO.edit(compteurTransferMP);
		 
	 }else{
		 compteur=1;
		 compteurTransferMP= new CompteurTransferMP();
		
		 compteurTransferMP.setCompteur(compteur);
		 compteurTransferMP.setCodeDepot(CODE_RECEPTION_INVENTAIRE+"_"+codeDepot);
		 compteurTransferMPDAO.add(compteurTransferMP);
	 }
	
	 codeTransfer =etatTransfer+"_"+CODE_RECEPTION_INVENTAIRE+"_"+codeDepot.substring(0, 3)+"_"+compteur; 
	 
	 return codeTransfer;
}



public static boolean genererPrixCientByMP(List<MatierePremier> listMP,List<Client> listClient,List<Client> listFournisseur){ 
	PrixClientMP prixClientMP = new PrixClientMP();
	if(listMP!=null && listClient!=null){
	for(int i=0;i<listMP.size();i++){
		MatierePremier matierePremiere =listMP.get(i);
		for(int j=0;j<listClient.size();j++){
				Client client =listClient.get(j);
			for(int k=0;k<listFournisseur.size();k++){
			
				Client fournisseur =listFournisseur.get(k);
				prixClientMP =prixClientMPDAO.findPrixByClientMP(matierePremiere.getCode(), client.getCode(),fournisseur.getCode());
			
			if(prixClientMP==null){
				prixClientMP = new PrixClientMP();
				prixClientMP.setClient(client);
				prixClientMP.setFournisseur(fournisseur);
				prixClientMP.setMatierePremier(matierePremiere);
				prixClientMP.setPrixUnitaire(BigDecimal.ZERO);
				prixClientMPDAO.add(prixClientMP);
			}
			}
		}
	}
	}
	return true ;
	
}



public static String genereCodeDateMoisAnnee(Date date) {
	String code="";
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat simpleFormat1 = new SimpleDateFormat("MM");
    SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd");
    
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    
    int jour = calendar.get(GregorianCalendar.DATE);
    
    if(jour<=15)
    	code="15"+simpleFormat1.format(date)+simpleFormat.format(date);
    else
    	code="30"+simpleFormat1.format(date)+simpleFormat.format(date);
  

   return code;
}


public static void compterAbsenceEmploye(String code, Employe employe, Date dateProd){
	 int compteur=0;
	 CompteurAbsenceEmploye compteurAbsenceEmploye= compteurAbsenceEmployeDAO.findByDateAbsencePeriode(code, employe.getId());
	 
	 if(compteurAbsenceEmploye !=null){
		 compteur=compteurAbsenceEmploye.getCompteur()+1;
		 compteurAbsenceEmploye.setCompteur(compteur);	
		 compteurAbsenceEmployeDAO.edit(compteurAbsenceEmploye);
		 
	 }else{
		 compteurAbsenceEmploye= new CompteurAbsenceEmploye();
		 compteurAbsenceEmploye.setCode(code);
		 compteurAbsenceEmploye.setDateAbsence(dateProd);
		 compteurAbsenceEmploye.setEmploye(employe);
		 compteurAbsenceEmploye.setCompteur(1);
		 compteurAbsenceEmployeDAO.add(compteurAbsenceEmploye);
		 
	 }
	 
}

//Fuction Cpy Cut Past JtextField



public static void copycoller(JTextField text){
	
	JPopupMenu popup = new JPopupMenu();
	JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	item.setText("Couper");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.CopyAction());
	item.setText("Copier");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.PasteAction());
	item.setText("Coller");
	popup.add(item);
	text.setComponentPopupMenu(popup);
	 
}

// fuction copy cut past cell jtable


public static void copycollercell(JTextComponent text){
	
	JPopupMenu popup = new JPopupMenu();
	JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	item.setText("Couper");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.CopyAction());
	item.setText("Copier");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.PasteAction());
	item.setText("Coller");
	popup.add(item);
	text.setComponentPopupMenu(popup);
	 
}


public static String genererCodeBon (String date){	
	
	 ///  Numerotation Bons
	 
		
		  String code="PROD "; 
		 
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code="0"+valeur+"-"+date; 
		  }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100) 
		  { code=valeur+"-"+date; } 
			  }else
			  {
		  
		  code="00"+1+"-"+date;
		  
		  } 
		   
		  
		  
		  return code;


}


public static BigDecimal SommeMontantInitial() {
    BigDecimal somme = BigDecimal.ZERO;

    listeObjectMontantTotalInitialMP=detailTransferStockMPDAO.MontantTotalInitialDesMP(Constantes.ETAT_TRANSFER_STOCK_INITIAL);
    for(int i=0;i<listeObjectMontantTotalInitialMP.size();i++)
    	
    {
    	
    	
    	 Object[] object=listeObjectMontantTotalInitialMP.get(i);
    	  if((BigDecimal)object[1] != null)
		  {
    		  somme=(BigDecimal)object[1];
    	 
		  }
    }
   

    return somme;
}
  
public static BigDecimal SommeMontantInitialEnVrac() {
	    BigDecimal somme = BigDecimal.ZERO;

	    listeObjectMontantTotalInitialMP=detailTransferStockMPDAO.MontantTotalInitialDesEnVrac(Constantes.ETAT_TRANSFER_STOCK_INITIAL);
	    for(int i=0;i<listeObjectMontantTotalInitialMP.size();i++)
	    	
	    {
	    	
	    	
	    	 Object[] object=listeObjectMontantTotalInitialMP.get(i);
	    	  if((BigDecimal)object[1] != null)
			  {
	    		  somme=(BigDecimal)object[1];
	    	 
			  }
	    }

	    return somme;
	}


public static  BigDecimal SommeMontantInitialEmballage() {
	    BigDecimal somme = BigDecimal.ZERO;

	    listeObjectMontantTotalInitialMP=detailTransferStockMPDAO.MontantTotalInitialDesEmballages (Constantes.ETAT_TRANSFER_STOCK_INITIAL);
	    for(int i=0;i<listeObjectMontantTotalInitialMP.size();i++)
	    	
	    {
	    	
	    	
	    	 Object[] object=listeObjectMontantTotalInitialMP.get(i);
	    	  if((BigDecimal)object[1] != null)
			  {
	    		  somme=(BigDecimal)object[1];
	    	 
			  }
	    }

	    return somme;
	}



}

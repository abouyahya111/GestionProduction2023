package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.print.PrintException;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import dao.entity.Articles;
import dao.entity.BonDePerte;
import dao.entity.ChargeProduction;
import dao.entity.CompteCaissier;
import dao.entity.ConsulterReceptionTotal;
import dao.entity.CoutMP;
import dao.entity.DetailActionPerteMP;
import dao.entity.DetailCompteMagasinier;

import dao.entity.DetailFactureProduction;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.DetailMarchandiseDeplacer;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EmployeRepos;
import dao.entity.EtatCoutProduction;
import dao.entity.EtatDetailMouvementTransfertStockMP;
import dao.entity.EtatMouvementTransfertStockMP;
import dao.entity.EtatStockDechetMP;
import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;
import dao.entity.Inventaire;
import dao.entity.ListDetailProduitFiniNonFabriquer;
import dao.entity.ListProduitFiniNonFabriquer;
import dao.entity.ManqueImportation;
import dao.entity.MatierePremier;
import dao.entity.MatierePremierImprimer;
import dao.entity.PourcentageProductionArticle;
import dao.entity.SituationDesEmployeesAbsents;
import dao.entity.SituationDesEmployeesAbsentsParJour;
import dao.entity.SituationEnVrac;
import dao.entity.SituationManquePlusEnVrac;
import dao.entity.SituationPFParAnneeClass;
import dao.entity.SituationProductionTotalParArticlePFClass;
import dao.entity.StatistiqueEnVracConsommeClass;
import dao.entity.StatistiqueEnVracConsommeLorsProductionPFClass;
import dao.entity.StockMP;


public class JasperUtils {
	
public static 	void imprimerListeEmploye( List<Employe> listEmploye, Map parameters,String nom){

		
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeEmploye.jasper");
		
		try {
			//JasperDesign jasperDesign = JRXmlLoader.load(str);
			//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
			
			JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
			//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\EquipeProducion\\ListeEmploye_"+nom+".pdf");
			JasperViewer.viewReport(JASPER_PRINT, false); 
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public static 	void imprimerFicheEquipeGen( List<Employe> listEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/fichePointageEquipeGen.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonPerteAvanceSurMagasinier( List<DetailActionPerteMP> listDetailActionPerteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonPerteAvanceSurMagasinier.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailActionPerteMP));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonPerteTransfertMagasinDechet( List<DetailActionPerteMP> listDetailActionPerteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonPerteTransfertMagasinDechet.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailActionPerteMP));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonPerteRetourProduction( List<DetailActionPerteMP> listDetailActionPerteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonPerteRetourProduction.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailActionPerteMP));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerListeMatierePremiere( List<MatierePremierImprimer> listMatierePremiere, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeMatierePremiere.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMatierePremiere.stream().collect(Collectors.toList())));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerFactureVenteMP( List<DetailFactureVenteMP> listDetailFactureVenteMP, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteMP.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureVenteMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void ExporterFactureVenteMPToExcel( List<DetailFactureVenteMP> listDetailFactureVenteMP, Map parameters  ) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteMP.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureVenteMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
	
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
	 
	/////////////////////////////////////////////// Exporter Jasper To Excel ///////////////////////////////////////////////////////// 
		//JasperViewer.viewReport(JASPER_PRINT, true);
	 
		
		
	 try{
		 
		//To generate and open the XLSheet

		 JRXlsxExporter exporterXLS = new JRXlsxExporter();
		 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
		 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureVenteMP.xlsx");
		 
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
		 
		 exporterXLS.exportReport();
		 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureVenteMP.xlsx");
		
		}
		catch(Exception exception)
		{
		exception.printStackTrace();
		}
	 
	 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	 
	 
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerCompteMagasinier( List<DetailCompteMagasinier> listDetailCompteMagasinier, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ConsulterCompteMagasinier.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailCompteMagasinier));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerFicheEmployeGlobale( List<FicheEmployeGlobale> listFicheEmployeGlobale, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheEmployeGlobale.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmployeGlobale));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerSituationEmployeAbsent( List<SituationDesEmployeesAbsents> listSituationEmployeAbsent, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationEmployeAbsent.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationEmployeAbsent));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerSituationEmployeProduction( List<SituationDesEmployeesAbsents> listSituationEmployeProduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationEmployeProduction.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationEmployeProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerSituationEmployeAbsentParJours( List<SituationDesEmployeesAbsentsParJour> listSituationEmployeAbsentparJours, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationEmployeAbsentParJours.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationEmployeAbsentparJours));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerCoutProduction( List<ChargeProduction> listchargeproduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/CoutProduction.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listchargeproduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerFicheEquipeGen2( List<Employe> listEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/fichePointageEquipeGen2.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static 	void imprimerDetailOrdreFabrication( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/detailOrdreFabrication1.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\OrdreFabrication\\DetailOrdreFabrication"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerBonSortieMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMatierePremiere.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonSortieMatierePremiereEmballage( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMatierePremiereEmballage.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerValiderBonSortieMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ValiderBonSortieMatierePremiere.jasper");
	
	try {
		
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		
		e.printStackTrace();
	}
}

public static 	void imprimerBonSortieMPChargeSupp( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMPChargeSupp.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieChargeSupp\\BonSortieMPChargeSupp_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerFicheCalculeMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheCalculeMPResteDechet.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheDechetMatierePremiere\\FicheCalculeMP_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerFicheEmploye( List<FicheEmploye> listFicheEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheEmploye.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerFicheEmployeAdmin( List<FicheEmploye> listFicheEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheEmployeAdmin.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerBulletinPaieEmploye( List<FicheEmploye> listFicheEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BulltinPaieEmploye.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\BulletinPaieEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
	
	
	
}

public static 	void imprimerBonSortieMPDeplace( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMPDeplace1.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonTransfereMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonTransfereMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonReceptionMP(List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonReceptionMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 
		
		JasperPrintManager.printReport(JASPER_PRINT, false);

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonDeclarationReceptionMagasinierMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDeclarationReceptionMagasinier.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonDePerteMP( List<BonDePerte> listBonDePerteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDePerteMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listBonDePerteMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerArticleNonFabrique( List<ListProduitFiniNonFabriquer> listProduitsfiniNonFabrique, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeArticleNonFabrique.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listProduitsfiniNonFabrique));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerDetailArticleNonFabrique( List<ListDetailProduitFiniNonFabriquer> listDetailProduitsfiniNonFabrique, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeDetailArticleNonFabrique.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailProduitsfiniNonFabrique));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonDeManque( List<DetailCompteMagasinier> listDetailCompteMagasinier, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDeManqueMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailCompteMagasinier));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonEntrerMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonEntrerMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonFrais( List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacer, Map parameters ){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonFrais.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailMarchandiseDeplacer));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void HistoriqueReceptionStockMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/HistoriqueReception.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void HistoriqueReceptionTotalStockMP( List<ConsulterReceptionTotal> listReceptionTotal, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/HistoriqueTotalReception.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listReceptionTotal));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerDetailManqueDechetFournisseurMP( List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailManqueDechetFournisseurMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailManqueDechetFournisseurMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerDetailManquePlusEnVrac( List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailManquePlusEnVrac.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailManqueDechetFournisseurMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerDetailManqueDechetFournisseurCarton( List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseurMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailManqueDechetFournisseurCarton.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailManqueDechetFournisseurMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerFicheEmployeBloque( List<Employe> listEmployes, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EmployeBloquant.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmployes));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerSituationDechetManque(Map parameters, TableModel model){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationDechetManquePlus.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerCoutProductionParArticle(Map parameters, List<EtatCoutProduction> listEtatCoutProduction){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/CoutProductionParArticle.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatCoutProduction));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerCoutProductionParProduction(Map parameters, List<EtatCoutProduction> listEtatCoutProduction){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/CoutProductionParProduction.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatCoutProduction));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerSituationGlobaleCoutProduction(Map parameters, TableModel model){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationGlobaleCoutProduction.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerDeclarationReceptionMagasinier(Map parameters, TableModel model){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DeclarationReceptionMagasinierTotal.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerDetailDeclarationReceptionMagasinier(Map parameters, TableModel model){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailDeclarationReceptionMagasinierTotal.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerConsulterCoutHorsProductionEnAttent(Map parameters, TableModel model){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ConsulterCoutsHorsproductionEnAttent.jasper");
	
	try {
		
		
	//	parameters.put("date", "TEST");
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str, parameters,con);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




public static 	void imprimerSituationManquePlusEtMoinsEnVrac( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationManquePlusEtMoinsEnVrac.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonFraisManquePlusEtMoinsEnVrac( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonFraisManquePlusManqueMoinsEnVrac.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonFraisManquePlusEtMoinsDechetEmballage( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonFraisManquePlusManqueMoinsDechetEmballage.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerSituationManquePlusEtMoinsEmballage( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationManquePlusEtMoinsEmballage.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerSituationProgrammeProduction( List<dao.entity.SituationProgrammeProduction> listSituationProduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationProgrammeProduction.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationProduction));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerSituationPFParAnnee( List<SituationPFParAnneeClass> listSituationPFParAnneeClass, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationPFParAnnee.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationPFParAnneeClass));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerSituationPFParAnneeGraphParQauntite(List<SituationPFParAnneeClass> listSituationPFParAnneeClass, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationPFParAnneeTmp.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationPFParAnneeClass));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerStatistiqueEnVracConsomme ( List<StatistiqueEnVracConsommeClass> listStatistiqueEnVracConsommeClasses, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/StatistiqueEnVracConsomme.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listStatistiqueEnVracConsommeClasses));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerSituationProductionTotalArticlePF( List<SituationProductionTotalParArticlePFClass> listSituationProductionTotalParArticlePFClass, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationProductionTotalArticlePF.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationProductionTotalParArticlePFClass));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerStatistiqueEnVracUtiliseLorsDeLaProductionPF( List<StatistiqueEnVracConsommeLorsProductionPFClass> listStatistiqueEnVracConsommeLorsProductionPFClass, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/StatistiqueEnVracUtiliserLorsDeLaProductionPF.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listStatistiqueEnVracConsommeLorsProductionPFClass));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerSituationManquePlusEtMoinsEmballageDechetUsine( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationManquePlusEtMoinsEmballageDechetUsine.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerSituationManquePlusEtMoinsEmballageDechetFournisseur( List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationManquePlusEtMoinsEmballageDechetFournisseur.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationManquePlusEtMoinsEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerBonTransfereMPProduitFini( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonTransferMPProduitFini.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonSortiePF( List<DetailTransferProduitFini> listDetailTransferStockPF, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortiePF.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockPF));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * 
 * @param listDetailTransferStockMP
 * @param parameters
 * @param nom
 * Méthode permet d'imprimer la situation de stock d'un magasin donné.
 */
public static 	void imprimerSitutationStock( List<EtatStockMP> listStockMP, Map parameters){

	 
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/situtationStockMP.jasper");
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport( str,parameters,new JRBeanCollectionDataSource(listStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	

	
}

 


public static 	void imprimerManqueImportation( List<ManqueImportation> listManqueImportation, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ManqueImportation.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listManqueImportation));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerInventaire( List<Inventaire> listInventaire, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/Inventaire.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listInventaire));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerSituationInventaire( List<Inventaire> listInventaire, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationInventaire.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listInventaire));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




public static 	void imprimerInventaire2( TableModel model , Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/Inventaire2.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerSitutationStockEnVrac( List<SituationEnVrac> listSituationStockEnVrac, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationStockEnVrac.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listSituationStockEnVrac));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




/*imprimer facture production */
public static 	void imprimerFacutreProduction( List<DetailFactureProduction> listDetailFactureProduction, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/factureProduction.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



/*####################################################################################*/

public static 	void imprimerInterne( ){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/test.jasper");
	CoutMP CoutMP =new CoutMP();
	CoutMP.setPrixUnitaire(new BigDecimal(77777) );
	
	List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	listCoutMP.add(CoutMP);
	Map parameters = new HashMap();
	parameters.put("date", "TEST");
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMPDéplacé\\BonSortieMPDeplace_"+nom+".pdf");
		//JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerExterne(List<CoutMP> listCoutMP, Map parameters ){

	
	InputStream str =JasperUtils.class.getResourceAsStream("C:/Edition/jasper/test.jasper");
	
	
	
}

public static 	void imprimerTestSubReport( List<Employe> listEmploye, Map parameters,String nom){
	
	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/report1.jrxml");
	InputStream strSubReport =JasperUtils.class.getResourceAsStream("/jasper/address_report_template.jrxml");
	
	try {
		
		String sqlPrint1="SELECT  name , english , maths , history , science from student where name='Thomas'";
        JasperDesign jasperDesign;
        jasperDesign = JRXmlLoader.load("C:/Program Files/TMS/reports/Master.jrxml");
        JasperDesign jasperDesignSub1 = JRXmlLoader.load("C:/Program Files/TMS/reports/Master_subreport1.jrxml");
        JasperDesign jasperDesignSub2 = JRXmlLoader.load("C:/Program Files/TMS/reports/Master_subreport2.jrxml");
       
        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
        JasperReport jasperReportSub1 = JasperCompileManager.compileReport(jasperDesignSub1);
        JasperReport jasperReportSub2 = JasperCompileManager.compileReport(jasperDesignSub2);
        Map perameters=new HashMap();
      /* JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,perameters,conn);
        JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException ex) {
        Logger.getLogger(Subr.class.getName()).log(Level.SEVERE, null, ex);
        }*/
		
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperDesign jasperDesignSub = JRXmlLoader.load(strSubReport);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		JasperReport JASPER_REP_SUB = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(JASPER_REP,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\EquipeProducion\\ListeEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void imprimerDetailOF( List<CoutMP> listcoutMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailOF.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listcoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static void imprimerDetailOF2( List<CoutMP> listcoutMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/detailOrdreFabrication2.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listcoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerEtatStock( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatStockParFournisseur( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockMPParFournisseur.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerCompteCaissier( List<CompteCaissier> listCompteCaissier, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatCompteCaissier.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCompteCaissier));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerEtatInitialStockMP( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatInitialMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatHistoriquePrisEtQuantiteInitialStockMP( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatPrixEtQuantiteInitialMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerEtatStockDechetMP( List<EtatStockDechetMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockDechetMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMouvementTransfertStockMP( List<EtatMouvementTransfertStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMouvementTransfertStockMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatDetailMouvementTransfertStockMP( List<EtatDetailMouvementTransfertStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatDetailMouvementTransfertStockMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatDetailStockDechetMP( List<EtatStockDechetMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatDetailStockDechetMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMouvementStockDechetManque( List<DetailTransferStockMP> listEtatMouvementStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMouvementStockMPDechetManque.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatMouvementStockMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}





public static 	void imprimerEtatEmployes( List<Employe> listEmploye, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatEmploye.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatAutorisationEmployesAbsent( List<SituationDesEmployeesAbsents> listEmploye, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationAutorisationDesAbsents.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerSituationEmployeRepos( List<EmployeRepos> listEmploye, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationEmployeRepos.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerSituationEmployeGlobal( List<SituationDesEmployeesAbsents> listEmploye, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/SituationEmployeGlobal.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerBonSortieMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonSortieMPSaisir( List<DetailPerteMP> listDetailPerteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDePerteMPSaisir.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailPerteMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void imprimerPercentageProduction( List<CoutMP> listCoutMP, Map parameters){
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/PercentageProduction.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	   //JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void imprimerProductionArticles( List<PourcentageProductionArticle> listPourcentageProductionArticle, Map parameters){
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ProductionArticles.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listPourcentageProductionArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	   //JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void imprimerPourcentageProductionArticles( List<PourcentageProductionArticle> listPourcentageProductionArticle, Map parameters){
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/PourcentageProductionArticles.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listPourcentageProductionArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	   //JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}

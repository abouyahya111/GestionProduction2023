package Production;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main.AuthentificationView;
import main.ProdLauncher;

import org.apache.commons.collections4.set.MapBackedSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Comparateur;
import util.ComparateurRegularisationPrixMP;
import util.Constantes;
import util.DateUtils;
import util.Utils;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.CoutMachineDAOImpl;
import dao.daoImplManager.CoutProdMPDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FormeDAOImpl;
import dao.daoImplManager.FormeParBoxDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.CoutMachineDAO;
import dao.daoManager.CoutProductionMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurCartonDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.FormeParBoxDAO;
import dao.daoManager.ManqueDechetFournisseurCartonDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CoutMP;
import dao.entity.CoutMachine;
import dao.entity.CoutProdMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FormeParBox;
import dao.entity.FournisseurMP;
import dao.entity.LigneMachine;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.ManqueDechetFournisseurCarton;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.RegularisationPrixMp;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;
import dao.entity.forme;

import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class RegularisationDesPrixMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  FormeParBoxDAO formeParBoxDAO ;
	
	
 	
	private List<forme> listForme =new ArrayList<forme>();
	private List<SubCategorieMp> listSousCategorie =new ArrayList<SubCategorieMp>();
private FormeDAO formeDAO;
private SubCategorieMPDAO subCategorieMPDAO;
private Map< String, SubCategorieMp> mapSubcategorie = new HashMap<>();
private Map< String, forme> mapForme = new HashMap<>();

	private List<Production> listProduction =new ArrayList<Production>();
	private List<ProductionMP> listProductionMP =new ArrayList<ProductionMP>();
	private List<CoutMP> listcoutMP =new ArrayList<CoutMP>();
	private List<CoutProdMP> listcoutProductionMP =new ArrayList<CoutProdMP>();
	private List<DetailTransferProduitFini> listDetailTransfertPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferStockMP> listDetailTransfertMP =new ArrayList<DetailTransferStockMP>();
	private List<TransferStockMP> listTransfertStockMP =new ArrayList<TransferStockMP>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur =new ArrayList<DetailManqueDechetFournisseur>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur =new ArrayList<ManqueDechetFournisseur>();
	private List<RegularisationPrixMp> listRegularisationPrixMp =new ArrayList<RegularisationPrixMp>();
	private List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseurCarton =new ArrayList<DetailManqueDechetFournisseurCarton>();
	private List<ManqueDechetFournisseurCarton> listManqueDechetFournisseurCarton =new ArrayList<ManqueDechetFournisseurCarton>();
	 
	private JButton btnAjouter;
	JComboBox comboSousCategorie = new JComboBox();
	JComboBox comboForme = new JComboBox();
	JDateChooser dateCoutMachine = new JDateChooser();
	private Map< String, FormeParBox> mapFormeParBox = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	CoutMachineDAO coutMachineDAO;
	JComboBox comboBoxMP = new JComboBox();
	
	
	
	MatierePremiereDAO matierePremiereDAO;
	ProductionDAO productionDAO;
	ProductionMPDAO productionMPDAO;
	CoutMPDAO coutMPDAO;
	DetailTransferMPDAO detailTransferMPDAO;
	DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	TransferStockMPDAO transferStockMPDAO;
	TransferStockPFDAO transferStockPFDAO;
	ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	CoutProductionMPDAO coutProductionMPDAO;
	ManqueDechetFournisseurCartonDAO manqueDechetFournisseurCartonDAO;
	DetailManqueDechetFournisseurCartonDAO detailManqueDechetFournisseurCartonDAO;
	
	
	/////////////////////////////////////////////////////////////////////// Listes des etats Stock Finale MP	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
	private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
	
	
	private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurReception=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
	
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
	private List <Object[]> Mindate=new ArrayList<Object[]>();
	private SubCategorieMPDAO subcategoriempdao;
 
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public RegularisationDesPrixMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	 matierePremiereDAO=new MatierePremierDAOImpl();
        	 productionDAO =new ProductionDAOImpl();
        	 coutMPDAO =new CoutMPDAOImpl();
        	 detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	 detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
        	 transferStockMPDAO=new TransferStockMPDAOImpl();
        	 transferStockPFDAO=new TransferStockPFDAOImpl();
        	 manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	 detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	 productionMPDAO=new ProductionMPDAOImpl();
        	 coutProductionMPDAO=new CoutProdMPDAOImpl();
        	 detailManqueDechetFournisseurCartonDAO=new DetailManqueDechetFournisseurCartonDAOImpl();
        	 manqueDechetFournisseurCartonDAO=new ManqueDechetFournisseurCartonDAOImpl();
        	 
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(31, 32, 1020, 78);
				  		   add(layeredPane);
				  		   			
				  		   			JLabel lblAnnee = new JLabel("Annee");
				  		   			lblAnnee.setBounds(236, 24, 77, 26);
				  		   			layeredPane.add(lblAnnee);
				  		   			
				  		   			 dateCoutMachine = new JDateChooser();
				  		   			dateCoutMachine.setLocale(Locale.FRANCE);
				  		   			dateCoutMachine.setDateFormatString("yyyy");
				  		   			dateCoutMachine.setBounds(370, 24, 259, 26);
				  		   			layeredPane.add(dateCoutMachine);
				  		   			
				  		   			btnAjouter = new JButton("Valider");
				  		   			btnAjouter.setBounds(439, 151, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					boolean existe=true;
				  		   					
				  		   					int annee=DateUtils.getAnnee(dateCoutMachine.getDate()) ;
				  		   				 BigDecimal coutTotalMP=BigDecimal.ZERO;
				  		   			BigDecimal	 coutTotaldechet=BigDecimal.ZERO;
 listRegularisationPrixMp.clear();
				  		   				 listProduction=productionDAO.LesProductionTerminerParAnnee(annee, Constantes.ETAT_OF_TERMINER);
				  		   				 listProductionMP=productionMPDAO.LesProductionTerminerParAnnee(annee, Constantes.ETAT_OF_TERMINER);
				  		   				 
				  		   				 for(int i=0;i<listProduction.size();i++)
				  		   				 {
				  		   					TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(listProduction.get(i).getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
				  		   					
				  		   					if(transferStockMP!=null)
				  		   					{
				  		   					RegularisationPrixMp regularisationPrixMp=new RegularisationPrixMp(); 
					  		   				regularisationPrixMp.setDate(transferStockMP.getDate());
					  		   			regularisationPrixMp.setNumOF(transferStockMP.getCodeTransfer());
					  		   		listRegularisationPrixMp.add(regularisationPrixMp);
				  		   					}
				  		   					
				  		   		
				  		   				 }
				  		   				 
				  		   				 
				  		   			 for(int i=0;i<listProductionMP.size();i++)
			  		   				 {
				  		   					TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(listProductionMP.get(i).getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
				  		   				if(transferStockMP!=null)
			  		   					{
				  		   					
				  		   				RegularisationPrixMp regularisationPrixMp=new RegularisationPrixMp(); 
				  		   				regularisationPrixMp.setDate(transferStockMP.getDate());
				  		   			regularisationPrixMp.setNumOF(transferStockMP.getCodeTransfer());
				  		   		listRegularisationPrixMp.add(regularisationPrixMp);
			  		   					}
			  		   				
			  		   				 }
				  		   			 
				  		   		Collections.sort(listRegularisationPrixMp, new ComparateurRegularisationPrixMP());
				  		   		
//////// ///////////////////////////////////////////////////////////////////////////////////////////  Traitement   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  		   			  
			  		   				 
				  		   				for(int i=0;i<listRegularisationPrixMp.size();i++)
				  		   				{
				  		   					 
				  		   				  
				  		   				
				  		   			
				  		   				Production production=productionDAO.findByNumOFStatut(listRegularisationPrixMp.get(i).getNumOF(), Constantes.ETAT_OF_TERMINER);	
				  		   			ProductionMP productionMP=productionMPDAO.findByNumOFStatut(listRegularisationPrixMp.get(i).getNumOF(), Constantes.ETAT_OF_TERMINER);	
				  		   				
				  		   				if(production!=null)
				  		   				{
				  		   					existe=true;
				  		   				coutTotalMP=BigDecimal.ZERO;
					  		   			coutTotaldechet=BigDecimal.ZERO;
				  		  		   			listcoutMP=productionDAO.listeCoutMP(production.getId());
					  		   				 for(int j=0;j<listcoutMP.size();j++)
					  		   				 {
					  		   					 
					  		   					CoutMP coutMP=listcoutMP.get(j);
					  		   				coutMP.setPrixUnitaire(coutMP.getMatierePremier().getPrixByYear(annee));
					  		   			coutMP.setPrixTotal(coutMP.getPrixUnitaire().multiply(coutMP.getQuantConsomme()));
					  		   		coutMP.setCoutDechet(coutMP.getPrixUnitaire().multiply(coutMP.getQuantDechet()));
					  		   		coutMP.setCoutDechetFournisseur(coutMP.getPrixUnitaire().multiply(coutMP.getQuantDechetFournisseur()));
					  		   		coutMP.setCoutManquante(coutMP.getPrixUnitaire().multiply(coutMP.getQuantiteManquante()));
					  		   	coutMP.setCoutOffre(coutMP.getPrixUnitaire().multiply(coutMP.getQuantiteOffre()));
					  		   		coutTotalMP=coutTotalMP.add(coutMP.getPrixTotal());
					  		   		coutTotaldechet=	coutTotaldechet.add(coutMP.getCoutDechet().add(coutMP.getCoutDechetFournisseur().add(coutMP.getCoutManquante())));
					  		   			
					  		   			coutMPDAO.edit(coutMP);
					  		   					 
					  		   					 
					  		   				 }
					  		   				
					  		   			production.setCoutTotalMP(coutTotalMP);
					  		   		production.setCoutDechet(coutTotaldechet);
					  		   	production.setCoutTotal(production.getCoutDechet().add(production.getCoutTotalEmploye().add(production.getCoutTotalEmployeEmbalage().add(production.getCoutTotalEmployeGen().add(production.getCoutTotalHorsProductionEnAttent().add(production.getCoutTotalMP()))))));
					  		  productionDAO.edit(production);
					  		  TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(production.getNumOF());
					  		  if(transferStockPF!=null)
					  		  {
					  			  listDetailTransfertPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
			  		   				for(int j=0;j<listDetailTransfertPF.size();j++)
			  		   				{
			  		   					
			  		   				if(listDetailTransfertPF.get(j).getArticle().getId()==production.getArticles().getId())
			  		   				{
			  		   					
			  		   					DetailTransferProduitFini detailTransferProduitFini=listDetailTransfertPF.get(j);
			  		   				detailTransferProduitFini.setPrixUnitaire(production.getCoutTotal().divide(production.getQuantiteReel(), 6, RoundingMode.HALF_UP));
			  		   					detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
			  		   				}
			  		   					
			  		   					
			  		   					
			  		   				}
					  		  }
					  		 
					  		   				
					  	   				
					  		   				listTransfertStockMP=transferStockMPDAO.findListeTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
					  		   				for(int d=0;d<listTransfertStockMP.size();d++)
					  		   				{
					  		   					
					  		   					listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(listTransfertStockMP.get(d).getId());
					  		   					
					  		   					for(int f=0;f<listDetailTransfertMP.size();f++)
					  		   					{
					  		   					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);	
					  		   				detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
					  		   				
					  		   					detailTransferMPDAO.edit(detailTransferStockMP)	;
					  		   					}
					  		   					
					  		   					
					  		   				}
					  		   				
					  		   				
					  		   			listTransfertStockMP=transferStockMPDAO.findListeTransferByDateByStatut(production.getDate(), Constantes.TYPE_SORTIE_RETOUR);
				  		   				for(int d=0;d<listTransfertStockMP.size();d++)
				  		   				{
				  		   					
				  		   					listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(listTransfertStockMP.get(d).getId());
				  		   					
				  		   					for(int f=0;f<listDetailTransfertMP.size();f++)
				  		   					{
				  		   					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);	
				  		   				detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
				  		   				
				  		   					detailTransferMPDAO.edit(detailTransferStockMP)	;
				  		   					}
				  		   					
				  		   					
				  		   				}
					  		   				
					  		   			listTransfertStockMP=transferStockMPDAO.findListeTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET);
				  		   				for(int d=0;d<listTransfertStockMP.size();d++)
				  		   				{
				  		   					
				  		   					listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(listTransfertStockMP.get(d).getId());
				  		   					
				  		   					for(int f=0;f<listDetailTransfertMP.size();f++)
				  		   					{
				  		   					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);	
				  		   				detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
				  		   				
				  		   					detailTransferMPDAO.edit(detailTransferStockMP)	;
				  		   					}
				  		   					
				  		   					
				  		   				}
					  		   				
				  		   			listTransfertStockMP=transferStockMPDAO.findListeTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET_FOURNISSEUR);
			  		   				for(int d=0;d<listTransfertStockMP.size();d++)
			  		   				{
			  		   					
			  		   					listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(listTransfertStockMP.get(d).getId());
			  		   					
			  		   					for(int f=0;f<listDetailTransfertMP.size();f++)
			  		   					{
			  		   					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);	
			  		   				detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
			  		   				
			  		   					detailTransferMPDAO.edit(detailTransferStockMP)	;
			  		   					}
			  		   					
			  		   					
			  		   				}
			  		   				
			  		   				
			  		   				
			  		   				listManqueDechetFournisseur=manqueDechetFournisseurDAO.listeManqueDechetFournisseurByCode(production.getNumOF());
			  		   				for(int d=0;d<listManqueDechetFournisseur.size();d++)
			  		   				{
			  		   					
			  		   				ManqueDechetFournisseur manqueDechetFournisseur=listManqueDechetFournisseur.get(d);
			  		   				listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
			  		   				
			  		   				for(int y=0;y<listDetailManqueDechetFournisseur.size();y++)
			  		   				{
			  		   					
			  		   					DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(y);
			  		   					
			  		   				    detailManqueDechetFournisseur.setPrix(detailManqueDechetFournisseur.getMatierePremier().getPrixByYear(annee));
			  		   					detailManqueDechetFournisseurDAO.edit(detailManqueDechetFournisseur);
			  		   					
			  		   					
			  		   					
			  		   				}
			  		   					
			  		   					
			  		   					
			  		   				}
				  		   					
				  		   				}
				  		   				
				  		   				if(productionMP!=null)
				  		   				{
				  		   				coutTotalMP=BigDecimal.ZERO;
					  		   			coutTotaldechet=BigDecimal.ZERO;
					  		   			
				  		   				listcoutProductionMP=productionMPDAO.listeCoutProdMP(productionMP.getId())	;
				  		   				
				  		   				for(int d=0;d<listcoutProductionMP.size();d++)
				  		   				{
				  		   					
				  		   				CoutProdMP coutProdMP=listcoutProductionMP.get(d);
				  		   				
				  		   			coutProdMP.setPrixUnitaire(coutProdMP.getMatierePremier().getPrixByYear(annee));
				  		   		coutProdMP.setPrixTotal(coutProdMP.getPrixUnitaire().multiply(coutProdMP.getQuantConsomme()));	
				  		   	coutProdMP.setCoutDechet(coutProdMP.getQuantDechet().multiply(coutProdMP.getPrixUnitaire()));	
				  		  coutProdMP.setCoutDechetFournisseur(coutProdMP.getQuantDechetFournisseur().multiply(coutProdMP.getPrixUnitaire()));	
				  		coutProdMP.setCoutManquante(coutProdMP.getQuantiteManquante().multiply(coutProdMP.getPrixUnitaire()));
				  		coutProdMP.setCoutOffre(coutProdMP.getQuantiteOffre().multiply(coutProdMP.getPrixUnitaire()));
				  		coutTotalMP=coutTotalMP.add(coutProdMP.getPrixTotal());
				  		coutTotaldechet=coutTotaldechet.add(coutProdMP.getCoutDechet().add(coutProdMP.getCoutDechetFournisseur().add(coutProdMP.getCoutManquante())));
				  		  coutProductionMPDAO.edit(coutProdMP);
				  		  
				  		   				}
				  		   					
				  		   			productionMP.setCoutTotalMP(coutTotalMP);	
				  		   		productionMP.setCoutDechet(coutTotaldechet);
				  		   	productionMP.setCoutTotal(productionMP.getCoutDechet().add(productionMP.getCoutTotalEmploye().add(productionMP.getCoutTotalMP())));
				  		   	productionMPDAO.edit(productionMP);
				  		   	
				  		   	
				  		   	TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
				  		   	if(transferStockMP!=null)
				  		   	{
				  		     	listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
					  		   	for(int f=0;f<listDetailTransfertMP.size();f++)
					  		   	{
					  		   		
					  		   		DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);
					  		   		
					  		   	detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
					  		   	detailTransferMPDAO.edit(detailTransferStockMP);
					  		   		
					  		   		
					  		   	}
				  		   		
				  		   	}
				  		
				  		   	
	TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.TYPE_DECHET_FOURNISSEUR);
				  		   	if(transferStockMPTmp!=null)
				  		   	{
				  		   		
				  		   	listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(transferStockMPTmp.getId());
				  		   	for(int f=0;f<listDetailTransfertMP.size();f++)
				  		   	{
				  		   		
				  		   		DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(f);
				  		   		
				  		   	detailTransferStockMP.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(annee));
				  		   	detailTransferMPDAO.edit(detailTransferStockMP);
				  		   		
				  		   		
				  		   	}
				  		   	}
				  		  
				  		   	
				  		   	
				  		  CalculerStockFinaleMP( productionMP);
				  		  
				  		  
	/////////////////////////////////////////////////////////////////////////////////////// Calculer Prix Moyen    /////////////////////////////////////////////////////////////////////////////////////////////////////////////			  		  
				  		  
				  		BigDecimal prixPF=BigDecimal.ZERO;
				  		BigDecimal nouveauprix=BigDecimal.ZERO;
				  		BigDecimal quantiteTotal=BigDecimal.ZERO ;
				  		BigDecimal prixStock=BigDecimal.ZERO;
				  		BigDecimal prixOld=BigDecimal.ZERO;
				  		BigDecimal QuantiteOld=BigDecimal.ZERO;
				  		BigDecimal prixNew=BigDecimal.ZERO;
				  		
				   
				  		
				  		
				  		
				  		//prixTotal=productionMP.getCoutTotalEmploye()+productionMP.getCoutTotalMP()+productionMP.getCoutDechet();
				  		
				  		prixPF=productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
				  		
				  		MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
				  		
				  		for(int j=0;j<listEtatStockMPAfficher.size();j++)
				  		{
				  			
				  			EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
				  			if(etatStockMP.getMp().getId()==matierePremier.getId())
				  			{
				  				if(etatStockMP.getMp().getPrixByYear(  DateUtils.getAnnee(productionMP.getDateProduction()) )!=null)
				  				{
				  					prixOld=etatStockMP.getMp().getPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()));
				  				}else
				  				{
				  					prixOld=BigDecimal.ZERO;
				  				}
				  				
				  				
				  				QuantiteOld=etatStockMP.getQuantiteFinale();
				  				
				  			}
				  			
				  			
				  			
				  		}
				  		
				  		
				  		
				  		 
				  	       
				  	       if(productionMP.getQuantiteReel().add(QuantiteOld).compareTo(BigDecimal.ZERO)!=0)
				  	       {
				  	           prixNew=  (((productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP)).multiply(productionMP.getQuantiteReel()) ).add(prixOld.multiply(QuantiteOld))).divide(productionMP.getQuantiteReel().add(QuantiteOld), 6, RoundingMode.HALF_UP);

				  	       }
				  	       
				  	       matierePremier.setPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()) , prixNew); 
				  	       matierePremiereDAO.edit(matierePremier);
				  		  
				  	     existe=true;
				  		   	
				  		   				
				  		   				}
				  		   				
				
		  		   					
				  		   					
				  		   			if(existe==true)
			  		   				{
			  		   					JOptionPane.showMessageDialog(null, "Prix Modifier Avec Succee");
			  		   				}}
				  		   				 
				  		   				 
				  		   				 
				  		   				
				  		   				}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			
				  		   			
	}
	
	
	

	
	void initialiser()
	{
}
	

	
/////////////////////////////////////////////////////////////////////   Calculer Stock Finale MP   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	public void CalculerStockFinaleMP(ProductionMP productionMP)
	{
		
		

		MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
			
		
			
			
				
				SubCategorieMp subCategorieMp=null;
					CategorieMp categorieMp=null;
					MatierePremier mp=null;
					Magasin magasin=productionMP.getMagasinStockage();
					
				  	FournisseurMP fournisseurMP=null;
				
				 
				  	
				  	
				  	
				
					
					
					
					Date mindate=null;
					
					Mindate=detailTransferMPDAO.MinDate(magasin);
					
					for(int i=0;i<Mindate.size();i++)
					{
						
						 Object[] object= Mindate.get(i);
						
						
						if(Mindate.get(i)!=null)
						{
							mindate=(Date)object[0];
						}
						
					}
					
					String patternYear = "yyyy";
					String patternDate = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
					SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

					
					
					
					if(mindate!=null)
					{
						
						String date = simpleDateFormatDate.format(mindate);
						
						
						try {
						mindate=simpleDateFormatDate.parse(date);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					}else
					{
						String year = simpleDateFormatyear.format(productionMP.getDateProduction());
						
						try {
						mindate=simpleDateFormatDate.parse(year+"-01-01");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					}
					
					
					
					SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
				// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
					
					
				  	 
	/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				  	  				  	
					
						listeObjectStockInitialGroupByMP=detailTransferMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

					
						
						
					
					listeObjectEtatStockGroupByMP=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteNonThe=detailTransferMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				
					
	//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

					
					
						listeObjectStockInitialGroupByMPByFournisseur=detailTransferMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
						
					

					//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteThe=detailTransferMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					
					listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);

					
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
			listEtatStockMP.clear();
			listEtatStockMPAfficher.clear();
			CalculerStockMPThe();	
			CalculerStockMPNonThe();		
				
				
			for(int j=0;j<listEtatStockMP.size();j++)
			{
				
			EtatStockMP etatStockMP=listEtatStockMP.get(j);	
				
			if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
				{
					
				listEtatStockMPAfficher.add(etatStockMP);
				}
				
				
				
				
			}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
			{
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
				{
					
					listEtatStockMPAfficher.add(etatStockMP);	
					
				}
				
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
				{
					
					listEtatStockMPAfficher.add(etatStockMP);	
					
				}
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficher.add(etatStockMP);	
					
				}
				
			}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficher.add(etatStockMP);	
					
				}
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficher.add(etatStockMP);	
					
				}
				
			}else
			{
				
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
				
				
			}
				
				
			
			
			
			
			
			
			
				
				
				
				
				
			}
			

			
			
			
			
				 
		
			
			

	  
		
		
		
		
		
	}



	public void CalculerStockMPNonThe()
	{
		
		
		
		
			
			for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
			{
				
				 Object[] object=listeObjectStockInitialGroupByMP.get(i);
				EtatStockMP etatStockMP=new EtatStockMP();
				MatierePremier mp= (MatierePremier)object[0];
				etatStockMP.setMp(mp);
										
				etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
				
			
				 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
				  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
				
				//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
				listEtatStockMP.add(etatStockMP);
				
				
			}
		
		
		boolean existe=false;
		
		  for(int j=0;j<listeObjectEtatStockGroupByMP.size() ; j++) {
		  
			  existe=false;
			  
		  Object[] object=listeObjectEtatStockGroupByMP.get(j);
		  MatierePremier mp=(MatierePremier)object[0];
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
			  if(listEtatStockMP.get(k).getFournisseurMP()==null)
			  {
				 
				  
				  
				  existe=true;
				  
				  EtatStockMP etatStockMP=listEtatStockMP.get(k);
				  if(etatStockMP.getMp().getCode().equals("MP_703"))
				  {
					  
					System.out.println(etatStockMP.getMp().getCode());  
					  
				  }
				 
				  if((BigDecimal)object[1] != null)
				  {
					  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
				  }else
				  {
					  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  }
				  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
				  {
					  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
				  }else
				  {
					  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
				  }
				 
				  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
				  {
					  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
				  }else
				  {
					  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  }
				 if((BigDecimal)object[4]!=null)
				 {
					 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
				 }else
				 {
					 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
				 }
				  
				 if((BigDecimal)object[5]!=null)
				 {
					 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
				 }else
				 {
					 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				 }
				 
				 if((BigDecimal)object[8]!=null)
				 {
					 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
				 }else
				 {
					 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				 }
				 
				 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
				 {
					  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

				 }else
				 {
					  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

				 }
				 if(((BigDecimal)object[10])!=null)
				 {
					  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
				 }else
				 {
					  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				 }
				
				 if((BigDecimal)object[11]!=null)
				 {
					  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
				 }else
				 {
					  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				 }
				if((BigDecimal)object[12]!=null)
				{
					 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
				}else
				{
					 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				}
				 
				  
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
				 
				  listEtatStockMP.set(k, etatStockMP);
			  }

		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(existe==false)
			{
				
				
				
				  EtatStockMP etatStockMP=new EtatStockMP();
				 
				  etatStockMP.setMp(mp);
				  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
				  
				  if((BigDecimal)object[1] != null)
				  {
					  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
				  }else
				  {
					  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  }
				  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
				  {
					  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
				  }else
				  {
					  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
				  }
				 
				  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
				  {
					  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
				  }else
				  {
					  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  }
				 if((BigDecimal)object[4]!=null)
				 {
					 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
				 }else
				 {
					 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
				 }
				  
				 if((BigDecimal)object[5]!=null)
				 {
					 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
				 }else
				 {
					 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				 }
				 
				 if((BigDecimal)object[8]!=null)
				 {
					 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
				 }else
				 {
					 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				 }
				 
				 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
				 {
					  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

				 }else
				 {
					  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

				 }
				 if(((BigDecimal)object[10])!=null)
				 {
					  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
				 }else
				 {
					  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				 }
				
				 if((BigDecimal)object[11]!=null)
				 {
					  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
				 }else
				 {
					  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				 }
				if((BigDecimal)object[12]!=null)
				{
					 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
				}else
				{
					 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				}
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
				 
				  listEtatStockMP.add(etatStockMP);	
				
				
				
			}
		  
		  
		  
		  
		  }
		 
		/////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		  
			boolean trouver=false;
			
			  for(int j=0;j<listeEtatStockTransfertEnAttenteNonThe.size() ; j++) {
			  
				  trouver=false;
				  
			 DetailTransferStockMP  detailTransferStockMP=listeEtatStockTransfertEnAttenteNonThe.get(j);
			
				  
			  for(int k=0;k<listEtatStockMP.size();k++) {
			  
			  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId()) {
				  if(listEtatStockMP.get(k).getFournisseurMP()==null)
				  {
					 if(detailTransferStockMP.getFournisseur()==null) 
					 {
						 
							if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
								
							{
							if(detailTransferStockMP.getMagasinSouce()!=null)	
							{
								
								if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
									
								{
									
									
									  trouver=true;
									  
									  EtatStockMP etatStockMP=listEtatStockMP.get(k);
									  
									
									  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
									 		  listEtatStockMP.set(k, etatStockMP);
									
									
									
									
								}
								
								
								
							}
								
								
								
								
								
							}
						 
						 
						 
					 }
					 
				  }

			  
			  }
			  

			  
			  
			  
			  
			  
			  }
			  
				if(trouver==false)
				{
					
					 if(detailTransferStockMP.getFournisseur()==null) 
					 {
						 
							if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
								
							{
							if(detailTransferStockMP.getMagasinSouce()!=null)	
							{
								
								if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
									
								{
									
									
									
									  EtatStockMP etatStockMP=new EtatStockMP();
										 
									  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
									  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
									  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
									  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
									  etatStockMP.setTransferSortie(BigDecimal.ZERO);
									  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
									  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
									  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
									  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
									  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
									  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
									  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
									  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
									 
									  listEtatStockMP.add(etatStockMP);	
									
									
									
									
									
									
									
								}
							}
							}
					 }
					
					
					
					
					
				}
			  
			  
			  
			  
			  }
		  
		  
		  
		  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  
		  
		  
		  
		  
		  for(int k=0;k<listEtatStockMP.size();k++) {
			  
			  EtatStockMP etatStockMP=listEtatStockMP.get(k);
			
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  listEtatStockMP.set(k, etatStockMP);
			  
			  
			  }

		
		
		
	}

	public void CalculerStockMPThe()
	{
		

		
		

		
		
			
			for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
			{
				
				 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
				EtatStockMP etatStockMP=new EtatStockMP();
				MatierePremier mp= (MatierePremier)object[0];
				
				FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
				
				etatStockMP.setMp(mp);
										
				etatStockMP.setQuantiteInitial((BigDecimal)object[2]);
				
				etatStockMP.setFournisseurMP(fournisseurMP);
				 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
				  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
				etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
				listEtatStockMP.add(etatStockMP);
				
				
			}
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		boolean existe=false;
		
		  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurReception.size() ; j++) {
		  
			  existe=false;
			  
		  Object[] object=listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
		  MatierePremier mp=(MatierePremier)object[0];
		  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
			  existe=true;
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		  
		 
		  if((BigDecimal)object[2]!=null)
		  {
			  etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add((BigDecimal)object[2]) ); 
		  }
		 
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(existe==false)
			{
				
				
				
				  EtatStockMP etatStockMP=new EtatStockMP();
				 
				  etatStockMP.setMp(mp);
				  etatStockMP.setFournisseurMP(fournisseurMP);
				  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
				  if((BigDecimal)object[2]!=null)
				  {
					  etatStockMP.setQuantiteReception((BigDecimal)object[2]);
				  }else
				  {
					  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  }
				 
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
				  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				  
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
				 
				  listEtatStockMP.add(etatStockMP);	
				
				
			}
		  
		  
		  
		  
		  }

	//////////////////////////////////////////////////////////////////////////////////////////////////////// Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			 existe=false;
			 
			 BigDecimal enter=BigDecimal.ZERO;
			 BigDecimal transfert=BigDecimal.ZERO;
			
			  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurEntrer.size() ; j++) {
			  
				  existe=false;
				  
			  Object[] object=listeObjectEtatStockGroupByMPByFournisseurEntrer.get(j);
			  MatierePremier mp=(MatierePremier)object[0];
			  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
			  
			  enter=BigDecimal.ZERO;
			  transfert=BigDecimal.ZERO;
				  
			  for(int k=0;k<listEtatStockMP.size();k++) {
				  enter=BigDecimal.ZERO;
				  transfert=BigDecimal.ZERO;
			  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
				  existe=true;
			  
			  EtatStockMP etatStockMP=listEtatStockMP.get(k);
			  
			 
			  if(((BigDecimal)object[2])!=null)
			  {
				  enter=(BigDecimal)object[2];
				 
			  }
			  if(((BigDecimal)object[3])!=null)
			  {
				  transfert=(BigDecimal)object[3];
				 
			  }
			  
			  
			  etatStockMP.setTransferEntrer (etatStockMP.getTransferEntrer().add(enter.add(transfert)));
			 
			
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
			  listEtatStockMP.set(k, etatStockMP);
			  
			  
			  
			  }
			  

			  
			  
			  
			  
			  
			  }
			  
				if(existe==false)
				{
					
					
					
					  EtatStockMP etatStockMP=new EtatStockMP();
					 
					  etatStockMP.setMp(mp);
					  etatStockMP.setFournisseurMP(fournisseurMP);
					  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
					  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
					  if(((BigDecimal)object[2])!=null)
					  {
						  enter=(BigDecimal)object[2];
						 
					  }
					  if(((BigDecimal)object[3])!=null)
					  {
						  transfert=(BigDecimal)object[3];
						 
					  }  
					  
					  
					 
					etatStockMP.setTransferEntrer (enter.add(transfert));
					 
					  etatStockMP.setTransferSortie(BigDecimal.ZERO);
					  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
					  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
					  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
					  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
					  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
					  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
					  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
					  
					  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
					 
					  listEtatStockMP.add(etatStockMP);	
					
					
				}
			  
			  
			  
			  
			  }
			   
		  
	//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;

	BigDecimal sortie=BigDecimal.ZERO;
	 transfert=BigDecimal.ZERO;

	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurSortie.size() ; j++) {

	existe=false;
	sortie=BigDecimal.ZERO;
	transfert=BigDecimal.ZERO;
	Object[] object=listeObjectEtatStockGroupByMPByFournisseurSortie.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		sortie=BigDecimal.ZERO;
		transfert=BigDecimal.ZERO;

	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);


	if(((BigDecimal)object[2])!=null)
	{
		sortie=(BigDecimal)object[2];
		 
	}
	if(((BigDecimal)object[3])!=null)
	{
		  transfert=(BigDecimal)object[3];
		 
	}  
	etatStockMP.setTransferSortie (etatStockMP.getTransferSortie(). add(sortie.add(transfert)));

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	if(((BigDecimal)object[2])!=null)
	{
		sortie=(BigDecimal)object[2];
		 
	}
	if(((BigDecimal)object[3])!=null)
	{
		  transfert=(BigDecimal)object[3];
		 
	} 


		etatStockMP.setTransferSortie(sortie.add(transfert));


	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////// Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal charge=BigDecimal.ZERO;
	BigDecimal chargesupp=BigDecimal.ZERO;
	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurCharger.size() ; j++) {
		 charge=BigDecimal.ZERO;
		 chargesupp=BigDecimal.ZERO;
	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurCharger.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		charge=BigDecimal.ZERO;
		 chargesupp=BigDecimal.ZERO;

	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);

	if((BigDecimal)object[2]!=null)
	{
		charge=(BigDecimal)object[2];
		
	}
	etatStockMP.setQuantiteCharger(etatStockMP.getQuantiteCharger().add(charge));
	if((BigDecimal)object[3]!=null)
	{
		chargesupp=(BigDecimal)object[3];
	}

	etatStockMP.setQuantiteChargerSupp(etatStockMP.getQuantiteChargerSupp().add(chargesupp));
	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);

	if((BigDecimal)object[2]!=null)
	{
		charge=(BigDecimal)object[2];
		
	}

	if((BigDecimal)object[3]!=null)
	{
	chargesupp=(BigDecimal)object[3];
	}

	etatStockMP.setQuantiteCharger(charge);

	etatStockMP.setQuantiteChargerSupp(chargesupp);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}
		  
	//////////////////////////////////////////////////////////////////////////////////////////////////////// Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal retour=BigDecimal.ZERO;
	BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
		retour=BigDecimal.ZERO;
		retourFournisseurAnnule=BigDecimal.ZERO;
	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurRetour.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		retour=BigDecimal.ZERO;
	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);


	if((BigDecimal)object[2]!=null)
	{
		retour=(BigDecimal)object[2];
		
	}
	etatStockMP.setQuantiteRetour (etatStockMP.getQuantiteRetour().add(retour));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	if((BigDecimal)object[2]!=null)
	{
		retour=(BigDecimal)object[2];
		
	}

		etatStockMP.setQuantiteRetour(retour);


	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}
		  
	//////////////////////////////////////////////////////////////////////////////////////////////////////// Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	 sortie=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size() ; j++) {
		 sortie=BigDecimal.ZERO;
		

	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		 sortie=BigDecimal.ZERO;
		

	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
	sortie=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortie));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{
		
		System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
	sortie=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((sortie));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   SortiePF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;

	BigDecimal sortiePF=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size() ; j++) {

	sortiePF=BigDecimal.ZERO;


	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {

	sortiePF=BigDecimal.ZERO;


	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
		sortiePF=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortiePF));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{

	System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
		sortiePF=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((sortiePF));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal sortieEnAttente=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size() ; j++) {
		sortieEnAttente=BigDecimal.ZERO;


	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		sortieEnAttente=BigDecimal.ZERO;


	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
		sortieEnAttente=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortieEnAttente));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{

	System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
		sortieEnAttente=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((sortieEnAttente));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Perte////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal perte=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size() ; j++) {
		perte=BigDecimal.ZERO;


	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		perte=BigDecimal.ZERO;


	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
		perte=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(perte));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{

	System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
		perte=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((perte));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	 retour=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size() ; j++) {
		retour=BigDecimal.ZERO;


	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		retour=BigDecimal.ZERO;


	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
		retour=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retour));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{

	System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
		retour=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((retour));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour Fournisseur Annule////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	retourFournisseurAnnule=BigDecimal.ZERO;


	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.size() ; j++) {
	retourFournisseurAnnule=BigDecimal.ZERO;


	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
	retourFournisseurAnnule=BigDecimal.ZERO;


	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;


	EtatStockMP etatStockMP=listEtatStockMP.get(k);



	if(((BigDecimal)object[2])!=null)
	{
	retourFournisseurAnnule=((BigDecimal)object[2]);
	}


	etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retourFournisseurAnnule));


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
	{

	System.out.println(etatStockMP.getMp().getCode());
	}
	if(((BigDecimal)object[2])!=null)
	{
	retourFournisseurAnnule=((BigDecimal)object[2]);
	}

	etatStockMP.setQuantiteAutreSortie((retourFournisseurAnnule));

	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}





	//////////////////////////////////////////////////////////////////////////////////////////////////////// Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	existe=false;
	BigDecimal resteMachine=BigDecimal.ZERO;
	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurResterMachine.size() ; j++) {
		resteMachine=BigDecimal.ZERO;
	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		resteMachine=BigDecimal.ZERO;
	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);

	if(((BigDecimal)object[2])!=null)
	{
		resteMachine=((BigDecimal)object[2]);
		
	}
	etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(resteMachine));

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	if(((BigDecimal)object[2])!=null)
	{
		resteMachine=((BigDecimal)object[2]);
		
	}
		etatStockMP.setQuantiteResterMachine(resteMachine);


	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}	  

	////////////////////////////////////////////////////////////////////////////////////////////////////////   Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal fabriquer=BigDecimal.ZERO;
	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurFabrique.size() ; j++) {
		fabriquer=BigDecimal.ZERO;
	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		fabriquer=BigDecimal.ZERO;
	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);

	if(((BigDecimal)object[2])!=null)
	{
		fabriquer=((BigDecimal)object[2]);
		
	}
	etatStockMP.setQuantiteFabriquer (etatStockMP.getQuantiteFabriquer().add(fabriquer));

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	if(((BigDecimal)object[2])!=null)
	{
		fabriquer=((BigDecimal)object[2]);
		
	}
		etatStockMP.setQuantiteFabriquer(fabriquer);


	etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}	

	//////////////////////////////////////////////////////////////////////////////////////////////////////// Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	existe=false;
	BigDecimal existante=BigDecimal.ZERO;
	for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurExistante.size() ; j++) {
		existante=BigDecimal.ZERO;
	existe=false;

	Object[] object=listeObjectEtatStockGroupByMPByFournisseurExistante.get(j);
	MatierePremier mp=(MatierePremier)object[0];
	FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

	for(int k=0;k<listEtatStockMP.size();k++) {
		existante=BigDecimal.ZERO;
	if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	existe=true;

	EtatStockMP etatStockMP=listEtatStockMP.get(k);

	if(((BigDecimal)object[2])!=null)
	{
		existante=((BigDecimal)object[2]);
		
	}

	etatStockMP.setQuantiteExistante(etatStockMP.getQuantiteExistante().add(existante));
	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	listEtatStockMP.set(k, etatStockMP);



	}







	}

	if(existe==false)
	{



	EtatStockMP etatStockMP=new EtatStockMP();

	etatStockMP.setMp(mp);
	etatStockMP.setFournisseurMP(fournisseurMP);
	etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
	etatStockMP.setQuantiteReception(BigDecimal.ZERO);
	etatStockMP.setTransferEntrer(BigDecimal.ZERO);
	etatStockMP.setTransferSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
	etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
	etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
	etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
	etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
	etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
	if(((BigDecimal)object[2])!=null)
	{
	existante=((BigDecimal)object[2]);
		
	}
	etatStockMP.setQuantiteExistante(existante);


	etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

	listEtatStockMP.add(etatStockMP);	


	}




	}
		  
	//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
			boolean trouver=false;
			
			  for(int j=0;j<listeEtatStockTransfertEnAttenteThe.size() ; j++) {
			  
				  trouver=false;
				  
			  DetailTransferStockMP detailTransferStockMP=listeEtatStockTransfertEnAttenteThe.get(j);
			 
				  
			  for(int k=0;k<listEtatStockMP.size();k++) {
			  
			  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==detailTransferStockMP.getFournisseur().getId()) {
				
				if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
					
					
				{
				if(detailTransferStockMP.getMagasinSouce()!=null)	
				{
					
					if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
					{
						
						
						  trouver=true;
						  
						  EtatStockMP etatStockMP=listEtatStockMP.get(k);
						  
						 
						  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
						 		  listEtatStockMP.set(k, etatStockMP);
						
						
						
						
					}
					
					
					
				}
					
					
					
					
					
				}
				  
			
			  
			  
			  
			  }
			  

			  
			  
			  
			  
			  
			  }
			  
				if(trouver==false)
				{
					
					 if(detailTransferStockMP.getFournisseur()!=null) 
					 {
						 
							if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
								
							{
							if(detailTransferStockMP.getMagasinSouce()!=null)	
							{
								
								if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
									
								{
									
									  EtatStockMP etatStockMP=new EtatStockMP();
										 
									  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
									  etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
									  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
									  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
									  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
									  etatStockMP.setTransferSortie(BigDecimal.ZERO);
									  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
									  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
									  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
									  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
									  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
									  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
									  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
									  
									  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
									 
									  listEtatStockMP.add(etatStockMP);	
									
								}
									
								}
							}
							}
					 
				
					
					
				}
			  
			  
			  
			  
			  }
		  
		  
		  
		  
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		  
		  
		  
		  
		  
		  
		 
		  
		  for(int k=0;k<listEtatStockMP.size();k++) {
			  
			  EtatStockMP etatStockMP=listEtatStockMP.get(k);
			
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  if(etatStockMP.getFournisseurMP()!=null)
			  {
				  System.out.println(etatStockMP.getMp().getCode() +" *** "+etatStockMP.getFournisseurMP().getCodeFournisseur() + "****"+etatStockMP.getQuantiteInitial()+" *** "+etatStockMP.getQuantiteReception()+" *** "+ etatStockMP.getQuantiteRetour()+" *** "+etatStockMP.getTransferEntrer()+" *** "+etatStockMP.getQuantiteResterMachine()+" *** "+etatStockMP.getQuantiteFabriquer() +" ---- "+ etatStockMP.getQuantiteCharger() +" *****" + etatStockMP.getQuantiteChargerSupp()+" ***** "+etatStockMP.getQuantiteAutreSortie() +" ***** "+ etatStockMP.getTransferSortie() +" ***** "+ etatStockMP.getQuantiteExistante());

			  }
			  
			  
			  listEtatStockMP.set(k, etatStockMP);
			  
			  
			  }

			
		
	}
	
	
	
}

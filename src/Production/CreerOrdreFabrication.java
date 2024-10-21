package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;
import matierePremiere.ListeMatierePremiere;
import presentation.article.ActiverDesactiverMPEstimation;
import presentation.article.AjouterEstimationThe;

import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CheckBoxCellRenderer;
import util.CheckBoxComboBoxRenderer;
import util.CheckBoxItem;
import util.CheckableItem;
import util.CheckedComboBox;
import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.ConditionOffreDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FormeParBoxDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.OffreProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TypeOffreDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.ConditionOffreDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FormeParBoxDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TypeOffreDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CompteurProduction;
import dao.entity.ConditionOffre;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.EtatStockMP;
import dao.entity.FormeParBox;
import dao.entity.FournisseurMP;
import dao.entity.LigneMachine;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.OffreProduction;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TypeOffre;
import dao.entity.Utilisateur;
import javafx.scene.control.ComboBox;

import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class CreerOrdreFabrication extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleMPCAT;
	private DefaultTableModel	 modeleMPMixte;
	private DefaultTableModel	 modeleMPOffre;
	private DefaultTableModel	 modeleMPOffreVariant;

	private JXTable   table = new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnCalculeMP;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField quantite;
	private JTextField codeArticle;
	private boolean offreVarianteValider=false;
	
	private JButton btnAjouterFournisseurEnVrac= new JButton("Ajouter Fournisseur En Vrac Mixte");
	
	private Map< String, LigneMachine> ligneMachineMap = new HashMap<>();
	private Map< String, Magasin> magasinMap = new HashMap<>();
	private Map< String, Magasin> magasinPordMap = new HashMap<>();
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private Map< String, Magasin> magasinStockagePF = new HashMap<>();
	private Map< String, Equipe> mapEquipeProd = new HashMap<>();
	private Map< String, Equipe> mapEquipeGen = new HashMap<>();
	private Map< String, Articles> mapAricle = new HashMap<>();
	private Map< String, String> mapCodeArticle= new HashMap<>();
	private Map< String, String> mapLibelleAricle = new HashMap<>();
	private Map< String, Machine> machineMap = new HashMap<>();
	private Map< String, Depot> depotMap = new HashMap<>();
//	private Map< String, CategorieMp> mapCategorieMP = new HashMap<>();
	private Map< Integer, StockMP> mapQauntiteMatierePremier = new HashMap<>();
	
	private Map< String, Employe> mapEmployeTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeAideTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeChefProd = new HashMap<>();
	private Map< String, Employe> mapEmployeChefEquipe = new HashMap<>();
	private Map< String, Employe> mapListEmployeResponsable = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremierImp = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierDetailEstimation = new HashMap<>();
	private Map< String, BigDecimal> mapQuantiteRestereMatierePremier= new HashMap<>();
	private Map< String, Integer> mapPrioriteMatierePremier= new HashMap<>();
	private Map< String, BigDecimal> mapQuantiteMP = new HashMap<>();
	private Map< String, BigDecimal> mapQuantiteMPExistante = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierEnVrac = new HashMap<>();
	private Map< String, Promotion> mapPromotion = new HashMap<>();
	private Map< String, TypeOffre> mapTypeOffre = new HashMap<>();
	private Map< String, ConditionOffre> mapConditionOffre = new HashMap<>();
	
	private Map< String, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private Map< String, MatierePremier> mapMPOffre = new HashMap<>();
 
	private Map< String, EtatStockMP> mapStockFinaleMagasinStiockage = new HashMap<>();
	private Map< String, EtatStockMP> mapStockFinaleMagasinProduction = new HashMap<>();
	
	private MachineDAO machineDAO;
	private EquipeDAO equipeDAO;
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	//private DetailProdGenDAO detailProdGenDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private EmployeDAO employeDAO;
	private static SequenceurDAO sequenceurDAO= new SequenceurDAOImpl();
	private JComboBox categorie;
	private JComboBox comboMachine;
	private JComboBox comboLigneMachine;
	private JComboBox comboMagasin;
	private JComboBox comboDepot  = new JComboBox();
	private JComboBox comboPeriode  = new JComboBox();
	private JComboBox comboMagasinPF = new JComboBox();
	private JComboBox comboDepotPF = new JComboBox();
	private JComboBox comboDepotProd= new JComboBox();
	private JComboBox comboMagasinProd= new JComboBox();
	private JButton btnajouterEstimation = new JButton("Ajouter Estimation");
	private JButton btnvaliderEstimation;
	private String nomMP;
	private DetailEstimationDAO detailestimationDAO;
	private DetailEstimationPromoDAO detailEstimationPromoDAO;
	private ArticlesDAO articleDAO;
	private DepotDAO depotDAO;
	
	private CompteurProductionDAO compteurProductionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private StockPFDAO stockPFDAO;
	
	
	private List<MatierePremier> listMPOffre =new ArrayList<MatierePremier>();
	private List<DetailEstimationPromo> listDetailEstimationOffreMP =new ArrayList<DetailEstimationPromo>();
	private List<DetailEstimationPromo> listDetailEstimationOffreMPSelectionner =new ArrayList<DetailEstimationPromo>();
	private List<BigDecimal> listGrammageOffreSelectionner =new ArrayList<BigDecimal>();
	private List<Machine> listMachine =new ArrayList<Machine>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Equipe> listEquipeProd =new ArrayList<Equipe>();
	private List<Equipe> listEquipeGen =new ArrayList<Equipe>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPEnVrac =new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPEmballage =new ArrayList<CoutMP>();
	private List<Employe> listEmploye =new ArrayList<Employe>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	
	private List<Articles> listArticles =new ArrayList<Articles>();
	private List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>() ;
	private List<DetailEstimation> lisDetailEstimationTmp = new ArrayList<DetailEstimation>() ;
	private List<DetailEstimation> lisDetailEstimationAfficherEnvVacMixte = new ArrayList<DetailEstimation>() ;
	private List<FormeParBox>listeformeParBox = new ArrayList<FormeParBox>() ;
	private List<DetailEstimation> lisDetailEstimationbycategorie = new ArrayList<DetailEstimation>() ;
	private List<DetailResponsableProd> listDetailResponsableProd=new ArrayList<DetailResponsableProd>();
	private List<Promotion> listPromotion =new ArrayList<Promotion>();
	private List<TypeOffre> listTypeOffre =new ArrayList<TypeOffre>();
	private List<ConditionOffre> listCondition =new ArrayList<ConditionOffre>();
	private Articles article = new Articles();
	private PromotionDAO promotiondao=new PromotionDAOImpl();
	
	private static Production production = new Production();
	private Utilisateur utilisateur;
	private FournisseurMPDAO fournisseurMPDAO;
	private JTextField txtNumOF;
	private JLabel lblDescriptionOf;
	private JTextField txtDescription;
	private JScrollPane scrollPaneMP = new JScrollPane((Component) null);
	private JDateChooser dateFinChooser ;
	private JDateChooser dateDebutChooser;
	private JLabel label;
	private JLabel lblMagasinProd;
	private JLabel lblPriode;
	private JComboBox comboScotch;
	private  JComboBox comboBoxThe;
	private JCheckBox checkPromotion;
	JCheckBox chckbxArticleMixte ;
	boolean creerOF=true;
	private JTable tableMP;
	private JButton btnAjouterEstimation;
	private Promotion PromotionTMP;
	private JButton btnValiderBonSortie;
	
	boolean EstimationEnVracCalculer=false;
	boolean OffreValider=false;
	
	SubCategorieMPDAO SubCategorieMPDAO;
	private JTable tablempfournisseurmixte;
	JScrollPane scrollPane_1 = new JScrollPane();
	 JComboBox comboMagasinDechetMP = new JComboBox();
	 private List <Object[]> Mindate=new ArrayList<Object[]>();
	 private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficherMagasinStockage=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficherMagasinProduction=new ArrayList<EtatStockMP>();
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
		
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
		
		JButton btnValiderConditionMixte = new JButton("Valider Condition Mixt");
		private DetailTransferMPDAO detailTransferStockMPDAO;
		private JTable tableOffre;
		 JComboBox comboTypeOffre = new JComboBox();
		 JLabel lblTypeOffre = new JLabel("Type Offre :");
		 JLabel lblCondition = new JLabel("Condition :");
		 JComboBox comboCondition = new JComboBox();
		 JComboBox<CheckBoxItem> comboConditionChecked = new JComboBox<>();
		  
		 private TypeOffreDAO TypeOffreDAO;
		 private ConditionOffreDAO conditionOffreDAO;
		 boolean afficherOffre=false;
		 private JTable tableOffreVariant;
		 JComboBox comboVariant = new JComboBox();
		 JScrollPane scrollPane_TableOffreVariant = new JScrollPane();
		 JScrollPane scrollPane_TableOffre = new JScrollPane();
		 private Map< String, BigDecimal> mapQuantiteOffreVariant = new HashMap<>();
			private dao.daoManager.OffreProductionDAO OffreProductionDAO;
			 JComboBox comboSousCategorie = new JComboBox();
			 private JLabel lblForme;
			 private JComboBox comboForme;
			 private List<FormeParBox> listFormeParBox =new ArrayList<FormeParBox>();
				private List<SubCategorieMp> listSousCategorie =new ArrayList<SubCategorieMp>();
				private  FormeParBoxDAO formeParBoxDAO ;
				private Map< String, SubCategorieMp> mapSubcategorie = new HashMap<>();
				private Map< String, FormeParBox> mapFormeParBox = new HashMap<>();
				private Map< String, MatierePremier> mapMPCadeauAutrePF = new HashMap<>();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public CreerOrdreFabrication(Promotion promotion,Articles articleTmp ,String quantiteTmp,String ScotchTmp,String DepotTmp,String MagasinTmp,String PeriodeTmp,String envracTmp,String NumOFTmp, Date dateDebutTmp,Date dateFinTmp,String MachineTmp,String DepotProdTmp,String LigneMachineTmp,String MagasinProdTmp,String DepotPFTmp,String MagasinPFTmp , boolean mixte) {
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 2001, 767);
        try{
        	
        	 
        	production = new Production();
        	articleDAO=new ArticlesDAOImpl();
        	machineDAO= new MachineDAOImpl();
        	equipeDAO= new EquipeDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	detailestimationDAO= new DetailEstimationDAOImpl();
        	detailEstimationPromoDAO=new DetailEstimationPromoDAOImpl();
        	  //	detailProdGenDAO=ProdLauncher.detailProdGenDAO;	
        //	detailProdGenDAO=ProdLauncher.detailProdGenDAO;
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	compteurResponsableProdDAO= new CompteurResponsableProdDAOImpl();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	employeDAO= new EmployeDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	SubCategorieMPDAO=new SubCategorieMPAOImpl();	
        	TypeOffreDAO=new TypeOffreDAOImpl();
        	conditionOffreDAO=new ConditionOffreDAOImpl();
        	OffreProductionDAO=new OffreProductionDAOImpl();
        	formeParBoxDAO=new FormeParBoxDAOImpl();
        	nomMP="";
        	mapQauntiteMatierePremier = new HashMap<>();
        	chckbxArticleMixte = new JCheckBox("Article Mixte");
        	listTypeOffre=TypeOffreDAO.findAll();
        	listCondition=conditionOffreDAO.findAll();
       	 scrollPane_1 = new JScrollPane();
      	scrollPane_1.setBounds(1504, 52, 429, 290);
      	add(scrollPane_1);
      	scrollPane_1.setVisible(false);
      	tablempfournisseurmixte = new JTable();
      	scrollPane_1.setViewportView(tablempfournisseurmixte);
      	tablempfournisseurmixte.setModel(new DefaultTableModel(
      		new Object[][] {
      		},
      		new String[] {
      			"Code MP", "MP", "Fournisseur","ESTIMATION"
      		}
      	));
      	tablempfournisseurmixte.setFillsViewportHeight(true); 
        	
        	
        	
        	
        	comboBoxThe = new JComboBox();
        	comboBoxThe.addItemListener(new ItemListener() {
        		public void itemStateChanged(ItemEvent arg0) {
        			
        			if(comboBoxThe.getSelectedIndex()!=-1)
        			{
        				if(!comboBoxThe.getSelectedItem().equals(""))
            			{
        					intialiserTableauFournisseurMPMixte();
            			}
        				
        			}
        			
        			
        			
        			
        		}
        	});
        	if(promotion!=null)
        	{
        		 PromotionTMP=promotion;
        	}
        	chckbxArticleMixte.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			if(chckbxArticleMixte.isSelected()==true)
	        		{
        				btnAjouterEstimation.setVisible(true);
        				intialiserTableauFournisseurMPMixte();
        				intialiserTableauMP();
        				comboBoxThe.setSelectedIndex(-1);
        				comboBoxThe.setEnabled(false);
        				
        				
        				
        				
        				
	        		}else
	        		{
	        			
	        			
	        			btnAjouterEstimation.setVisible(false);
	        			intialiserTableauFournisseurMPMixte();
	        			hide();
	        			comboBoxThe.removeAllItems();
		     	 		comboBoxThe.addItem("");
	        			comboBoxThe.setEnabled(true);
	        			
	        			int idArticle=mapAricle.get(categorie.getSelectedItem()).getId();
		     	 		
		     	 		List<DetailEstimation> listeDetailEstimationCatThe=articleDAO.listeMatierePremierByArticleByCategorie (idArticle, SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		     			
		     			for(int i=0;i<listeDetailEstimationCatThe.size();i++)
		     	  		{	
		     				DetailEstimation detailEstimation=listeDetailEstimationCatThe.get(i);
		     				mapMatierePremier.put(detailEstimation.getMatierePremier().getNom(), detailEstimation.getMatierePremier());
		     	  			comboBoxThe.addItem(detailEstimation.getMatierePremier().getNom());
		     	  			
		     	  		}
	        		}
        			
        		}
        	});
        	chckbxArticleMixte.setOpaque(false);

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion a la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        
     
        
        
        
        
        
    	JLayeredPane layeredPane = new JLayeredPane();
        
        comboScotch = new JComboBox();
       
        comboPeriode.addItem("");
    	comboPeriode.addItem(PRPDUCTION_PERIODE_MATIN);
    	comboPeriode.addItem(PRPDUCTION_PERIODE_SOIR);
    	comboDepotProd.addItem("");
  		comboMagasinProd.addItem("");
  		comboDepot.addItem("");
  		comboScotch.addItem("");
  		String Codedepot = utilisateur.getCodeDepot();
  		
        try{
            	imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            	imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            	imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            	imgSupp1 = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             
          } catch (Exception exp){exp.printStackTrace();}
        
        intialiserTableau();
        
        final String codeDepot=AuthentificationView.utilisateur.getCodeDepot();	
     
        codeArticle = new JTextField();
        util.Utils.copycoller(codeArticle);
        categorie = new JComboBox();
        categorie.addItem(""); 
        listArticles=articleDAO.findAll();
        int i=0;
	      	while(i<listArticles.size())
	      		{	
	      			Articles article=listArticles.get(i);
	      			mapCodeArticle.put(article.getLiblle(), article.getCodeArticle());
	      			mapLibelleAricle.put( article.getCodeArticle(),article.getLiblle());
	      			mapAricle.put(article.getLiblle(), article);
	      			categorie.addItem(article.getLiblle());
	      			i++;
	      		}
		
		categorie.addItemListener(new ItemListener() {
	     	 	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		comboBoxThe.removeAllItems();
	     	 		comboBoxThe.addItem("");
	     	 		checkPromotion.setSelected(false);
		  			 
	     	
	     	 		codeArticle.setText(mapCodeArticle.get(categorie.getSelectedItem()));
	     	 		
	     	 		int idArticle=mapAricle.get(categorie.getSelectedItem()).getId();
	     	 		
	     	 		List<DetailEstimation> listeDetailEstimationCatThe=articleDAO.listeMatierePremierByArticleByCategorie(idArticle, SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
	     			
	     			for(int i=0;i<listeDetailEstimationCatThe.size();i++)
	     	  		{	
	     				DetailEstimation detailEstimation=listeDetailEstimationCatThe.get(i);
	     				mapMatierePremier.put(detailEstimation.getMatierePremier().getNom(), detailEstimation.getMatierePremier());
	     	  			comboBoxThe.addItem(detailEstimation.getMatierePremier().getNom());
	     	  			
	     	  		}
                  
	     	 	 	}
	     	 	}
	     	 });
		
		
		List<MatierePremier> listeMatierePremierCatScotch=matierePremiereDAO.listeMatierePremierByCategorie(CATEGORIE_MATIERE_PREMIERE_SCOTCH);
		
		for( i=0;i<listeMatierePremierCatScotch.size();i++)
  		{	
			MatierePremier matierePremier=listeMatierePremierCatScotch.get(i);
			mapMatierePremier.put(matierePremier.getNom(), matierePremier);
  			comboScotch.addItem(matierePremier.getNom());
  			
  		}
	  
			
		
	
	      
		codeArticle.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e)
		  	{
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			
		  			categorie.setSelectedItem(mapLibelleAricle.get(codeArticle.getText()));
		  			checkPromotion.setSelected(false);
		  			 
		  		}}});
		
		
				  		  btnImprimer = new JButton("Bon Sortie En Vrac");
				  		  btnImprimer.setIcon(imgImprimer);
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	if(lisDetailEstimation.size()<=0)
		  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la matière Première avant d'imprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  	else {
				  		  		listCoutMPEnVrac.clear();
				  		  		for(int i=0;i<listCoutMP.size();i++)
				  		  		{
				  		  			
				  		  			CoutMP coutMP=listCoutMP.get(i);
				  		  			if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		  			{
				  		  				
				  		  				listCoutMPEnVrac.add(coutMP);
				  		  			}
				  		  			
				  		  			
				  		  			
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  		
				  		  		if(listCoutMPEnVrac.size()!=0)
				  		  		{
				  		  			if(dateDebutChooser.getDate()==null)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la date SVP !!!");
				  		  			return;
				  		  			}else if(txtNumOF.getText().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez calculer la Matiere premiere SVP !!!");
				  		  			return;
				  		  			}else if(comboMachine.getSelectedIndex()==-1)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la machine SVP !!!");
				  		  			return;	
				  		  			}else if(comboMachine.getSelectedItem().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la machine SVP !!!");
				  		  			return;	
				  		  			}else if(comboMagasin.getSelectedIndex()==-1)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner Magasin SVP !!!");
				  		  			return;	
				  		  			}else if(comboMagasin.getSelectedItem().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner Magasin SVP !!!");
				  		  			return;	
				  		  			}
				  		  			
						
						/*
						 * for(int j=0;j<listCoutMPEnVrac.size();j++) {
						 * 
						 * for(int i=0;i<table.getRowCount();i++) {
						 * 
						 * if(listCoutMPEnVrac.get(j).getMatierePremier().getCode().equals(table.
						 * getValueAt(i, 0).toString()) &&
						 * listCoutMPEnVrac.get(j).getFournisseurMP().getCodeFournisseur().equals(table.
						 * getValueAt(i, 2).toString())) {
						 * 
						 * if(table.getValueAt(i, 7)!=null) {
						 * 
						 * if(!table.getValueAt(i, 7).equals("") ) {
						 * 
						 * listCoutMPEnVrac.get(j).setMoinsPlus(table.getValueAt(i, 7).toString());
						 * 
						 * }
						 * 
						 * 
						 * }
						 * 
						 * 
						 * 
						 * }
						 * 
						 * 
						 * }
						 * 
						 * }
						 */
						 
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			
				  		  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	String date=dateFormat.format(dateDebutChooser.getDate());
									 
									Map parameters = new HashMap();
									parameters.put("titre", "Bon de Sortie En Vrac");
									parameters.put("numOF", txtNumOF.getText());
									parameters.put("machine", comboMachine.getSelectedItem());
									parameters.put("equipe", "");
									parameters.put("magasin", comboMagasin.getSelectedItem());
									parameters.put("dateProd", date);
									parameters.put("article", article.getLiblle());
									JasperUtils.imprimerBonSortieMatierePremiere(listCoutMPEnVrac,parameters,production.getNumOF());
									
									
				  		  		}else
				  		  		{
				  		  			JOptionPane.showMessageDialog(null, "veuillez Calculer la matier premiere SVP !!!");
				  		  			return;
				  		  		}
				  		  
				
				  		  	}
								
								}
				  		  });
				  		
				  		
				  		 btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnImprimer.setBounds(20, 637, 142, 23);
				  		 add(btnImprimer);
				  		 comboMagasin = new JComboBox();		
				  		 comboMagasin.addItem("");
				  		  btnCalculeMP = new JButton("Calculer Mati\u00E8re ");
				  		  btnCalculeMP.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnCalculeMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	afficherOffre=false;
				  		  		matierePremiereDAO.ViderSession();
				  		  		productionDAO.ViderSession();
				  		  		detailestimationDAO.ViderSession();
				  		  	detailestimationDAO=new DetailEstimationDAOImpl();
				  		  EstimationEnVracCalculer=false;
				  		  		nomMP="";
				  		  		int modulo=0;
				  		  //	intialiserTableau();
				  		  	BigDecimal valueBD = new BigDecimal(quantite.getText());
				  	    	int euro = valueBD.intValue();	
				  		  	BigDecimal decimalPartBD = valueBD.subtract(BigDecimal.valueOf(euro)).setScale(2, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(10));
				  		  	int decimalPart=decimalPartBD.intValue();
				  		  	
				  		  		if(categorie.getSelectedItem().equals("")){
				  		  			JOptionPane.showMessageDialog(null, "Il faut choisir l'article!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		} else if(quantite.getText().equals("")){
				  		  		JOptionPane.showMessageDialog(null, "Il faut remplir la quantité!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		}  else if(comboMagasin.getSelectedItem().equals("")){
					  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Source!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
					  		  		} else if(comboMagasinProd.getSelectedItem().equals("")){
						  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Production!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		  			
						  		  		} else  if(comboScotch.getSelectedItem().equals("")){
							  		  		JOptionPane.showMessageDialog(null, "Il faut choisir Type de SCOTCH!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		  			
							  		  		} else{
							  		  			
							  		  		  if(chckbxArticleMixte.isSelected()==false && comboBoxThe.getSelectedItem().equals(""))
							  		  			
							  		  				JOptionPane.showMessageDialog(null, "Il faut choisir Type du THE VRAC!", "Attention", JOptionPane.INFORMATION_MESSAGE);
							  		  			else {
							  		  				
							  		  			 
							  		  			
							  		  				Magasin magasinDechetMP=MapmagasinDechetMP.get(comboMagasinDechetMP.getSelectedItem());
							  		  				if(magasinDechetMP==null)
							  		  				{
							  		  				JOptionPane.showMessageDialog(null, "veuillez selectionner le magasin dechet MP si n'existe pas alors veuiilez l'ajouter SVP");
						  		  					return;
							  		  				}
							  		  				
	/////////////////////////////////////////////////////////////////////////// Verification Estimation En Vrac ///////////////////////////////////////////////////////////////////////////////////////						  		  				
							  		  					boolean trouve=false;
							  		  					BigDecimal quantiteEnvrac=BigDecimal.ZERO;
							  		  					
							  		  					int nbrFournisseurEnVrac=0;
							  		  				for(int i=0;i<tablempfournisseurmixte.getRowCount();i++)
							  		  				{
							  		  					
							  		  					
							  		  				if(!tablempfournisseurmixte.getValueAt(i, 2).toString().equals(""))
					  		  						{
							  		  					
							  		  					
							  		  				nbrFournisseurEnVrac=nbrFournisseurEnVrac+1;
							  		  				
							  		  				
					  		  						}
							  		  					
							  		  					
							  		  					
							  		  					
							  		  					int k=0;
							  		  					
							  		  					System.out.println("Fournisseur :"+tablempfournisseurmixte.getValueAt(i, 2).toString());
							  		  					
							  		  					for(int j=0;j<tablempfournisseurmixte.getRowCount();j++)
							  		  					{
							  		  						
							  		  						if(!tablempfournisseurmixte.getValueAt(i, 2).toString().equals(""))
							  		  						{
							  		  							if(!tablempfournisseurmixte.getValueAt(j, 2).toString().equals(""))
							  		  							{
							  		  								if(tablempfournisseurmixte.getValueAt(i, 2).toString().equals(tablempfournisseurmixte.getValueAt(j, 2).toString()))
							  		  								{
							  		  									
							  		  									if(tablempfournisseurmixte.getValueAt(i, 0).toString().equals(tablempfournisseurmixte.getValueAt(j, 0).toString()))
							  		  									{
							  		  									k=k+1;
							  		  									}
							  		  									
							  		  							
							  		  									
							  		  								}
							  		  								
							  		  								
							  		  								
							  		  								
							  		  							}
							  		  							
							  		  							
							  		  							
							  		  							
							  		  						}
							  		  						
							  		  						
							  		  						
							  		  						
							  		  						
							  		  						
							  		  					}
							  		  					
							  		  					
							  		  					if(k>1)
							  		  					{
							  		  					trouve=true;
							  		  					}
							  		  					
							  		  					
							  		  				}
							  		  				
							  		  				if(trouve==true)
							  		  				{
							  		  					
							  		  					JOptionPane.showMessageDialog(null, "veuillez entrer les Fournisseur des En Vrac different SVP");
							  		  					return;
							  		  					
							  		  				}
							  		  					
							  		  			 	if(chckbxArticleMixte.isSelected()==false)
							  		  			 	{
							  		  			 		
							  		  			 		if(nbrFournisseurEnVrac>1)
							  		  			 		{
							  		  			 		JOptionPane.showMessageDialog(null, "Pour Utiliser Plusieur Fournisseurs Veuillez Cocher Article Mixte SVP");
							  		  					return;
							  		  			 		} 
							  		  			 		
							  		  			 	}else
							  		  			 	{
							  		  			 	if(nbrFournisseurEnVrac==1)
						  		  			 		{
						  		  			 		JOptionPane.showMessageDialog(null, "Veuillez Selectionner Plusieur Fournisseurs Pour Un Article Mixte SVP");
						  		  					return;
						  		  			 		}
							  		  			 	}
							  		  					
							  		  			 
							  		  			 	
							  		  			 	
							  		  			if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
							    				{
							  		  				if(comboVariant.getSelectedItem().toString().equals(Constantes.OFFRE_VARIANT)) 
							  		  				{
							  		  					if(offreVarianteValider==false)
							  		  					{
							  		  					JOptionPane.showMessageDialog(null, "Veuillez Valider offre Variant  SVP");
							  		  					return;
							  		  					}
							  		  				}
							    				}
							  		  				
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  		  				
							  		  				
				  		
								  		  				article =mapAricle.get(categorie.getSelectedItem());
								  		  				
								  		  				if(article.getConditionnement().compareTo(new BigDecimal(5))==0){
								  		  					modulo=decimalPart%2;
								  		  				}else if(article.getConditionnement().compareTo(new BigDecimal(2))==0){
								  		  					modulo=decimalPart%5;
								  		  				}else if(article.getConditionnement().compareTo(new BigDecimal(75))==0){
								  		  					modulo=decimalPart%16;
								  		  				}
								  		  			modulo=0;
								  		  				if(modulo==0){
								  		  					
								  		  				lisDetailEstimation =detailestimationDAO.findDetilestimationActifByArticle(article.getId());
											  		  	
								  		  				txtNumOF.setText(Utils.creerCodeOF(article.getCodeArticle(),magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode()));
								  		  				
			//////////////////////////////////////////////////////////////////////////////////////////////////////  Vérifier Les prix Des MP Si Null ou égale à Zero     /////////////////////////////////////////////////////////////////////////////////////////////
								  		  				
								  		  				
								  		  			boolean PrixMPZeroOuNull=false;
								  		  		String Message="Veuillez Entrer les Prix Des MP Suivantes SVP :"+"\n";
								  		  		
								  		  		for(int k=0;k<lisDetailEstimation.size();k++)
								  		  		{
								  		  			DetailEstimation detailEstimation=lisDetailEstimation.get(k);
								  		  			
								  		  			
								  		  		if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
												{
													
												//	mapMatierePremierDetailEstimation.put(detailEstimation.getMatierePremier().getCode()+" "+detailEstimation.getFournisseur().getCodeFournisseur(), detailEstimation.getMatierePremier());
												//	mapPrioriteMatierePremier.put(detailEstimation.getMatierePremier().getCode()+" "+detailEstimation.getFournisseur().getCodeFournisseur(), detailEstimation.getPriorite());
												
												}else
												{
													
													mapMatierePremierDetailEstimation.put(detailEstimation.getMatierePremier().getCode(), detailEstimation.getMatierePremier());
													mapPrioriteMatierePremier.put(detailEstimation.getMatierePremier().getCode(), detailEstimation.getPriorite());
													
													
												}
								  		  			
								  		  			
								  		  			
								  		  			if(detailEstimation.getMatierePremier().getType().equals(Constantes.MP_INTERNE))
								  		  			{
								  		  				
								  		  			
								  		  			
								  	if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
								  	{
								  		
								  		if(!chckbxArticleMixte.isSelected())
								  		{
								  			
								  			
								  				
								  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
							  		  			{
							  		  				
							  		  				PrixMPZeroOuNull=true;
							  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
							  		  				
							  		  			}else
							  		  			{
							  		  				
							  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
							  		  				{
							  		  					
							  		  					PrixMPZeroOuNull=true;
							  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
							  		  					
							  		  				}
							  		  				
							  		  				
							  		  				
							  		  			}
								  			
								  			
								  			
								  		}else if(chckbxArticleMixte.isSelected())
								  		{
								  			
								  			
								  				
								  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
							  		  			{
							  		  				
							  		  				PrixMPZeroOuNull=true;
							  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
							  		  				
							  		  			}else
							  		  			{
							  		  				
							  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
							  		  				{
							  		  					
							  		  					PrixMPZeroOuNull=true;
							  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
							  		  					
							  		  				}
							  		  				
							  		  				
							  		  				
							  		  			}
								  				
								  			
								  		}
								  		
								  		
								  	}else
								  	{
								  		
								  		if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
					  		  			{
					  		  				
					  		  				PrixMPZeroOuNull=true;
					  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
					  		  				
					  		  			}else
					  		  			{
					  		  				
					  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
					  		  				{
					  		  					
					  		  					PrixMPZeroOuNull=true;
					  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
					  		  					
					  		  				}
					  		  				
					  		  				
					  		  				
					  		  			}
								  	}
								  		  			
								  		  		
								  		  			
								  		  			}else if(detailEstimation.getMatierePremier().getType().equals(Constantes.MP_CLIENT))
								  		  			{
								  		  			
								  		  				if(!detailEstimation.getMatierePremier().getCode().contains("C"))
								  		  				{
								  		  				 
								  		  				MatierePremier matierePremier=matierePremiereDAO.findByCode(detailEstimation.getMatierePremier().getCode()+"C");
								  		  					
								  		  					if(matierePremier!=null)
								  		  					{
								  		  						
								  			  		  			
															  	if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
															  	{
															  		
															  		if(!chckbxArticleMixte.isSelected())
															  		{
															  			
															  			
															  				
															  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
														  		  			{
														  		  				
														  		  				PrixMPZeroOuNull=true;
														  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
														  		  				
														  		  			}else
														  		  			{
														  		  				
														  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
														  		  				{
														  		  					
														  		  					PrixMPZeroOuNull=true;
														  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
														  		  					
														  		  				}
														  		  				
														  		  				
														  		  				
														  		  			}
															  			
															  			
															  			
															  		}else if(chckbxArticleMixte.isSelected())
															  		{
															  			
															  			
															  				
															  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
														  		  			{
														  		  				
														  		  				PrixMPZeroOuNull=true;
														  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
														  		  				
														  		  			}else
														  		  			{
														  		  				
														  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
														  		  				{
														  		  					
														  		  					PrixMPZeroOuNull=true;
														  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
														  		  					
														  		  				}
														  		  				
														  		  				
														  		  				
														  		  			}
															  				
															  			
															  		}
															  		
															  		
															  	}else
															  	{
															  		
															  		if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))==null)
												  		  			{
												  		  				
												  		  				PrixMPZeroOuNull=true;
												  		  				Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
												  		  				
												  		  			}else
												  		  			{
												  		  				
												  		  				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)==0)
												  		  				{
												  		  					
												  		  					PrixMPZeroOuNull=true;
												  		  					Message=Message+detailEstimation.getMatierePremier().getCode()+"\n";
												  		  					
												  		  				}
												  		  				
												  		  				
												  		  				
												  		  			}
															  	}
								  		  						
								  		  						
								  		  						
								  		  						
								  		  						
								  		  						
								  		  						
								  		  						
								  		  					}
								  		  					
								  		  				}
								  		  				
								  		  				
								  		  				
								  		  				
								  		  			}
								  		  			
								  		  			
								  		  		}
								  		  		
								  		  		
								  		  		if(PrixMPZeroOuNull==true)
								  		  		{
								  		  			
								  		  		lisDetailEstimation.clear();
								  		  			
								  		  			JOptionPane.showMessageDialog(null, Message, "Erreur Prix",JOptionPane.WARNING_MESSAGE);
								  		  			return;
								  		  			
								  		  		}
								  		  				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						  		  				
								  		  		
								  		  		
								  		  		
								  		  		
								  		  		
								  		  				
							
								  		  				if(!afficherTableMatierePremiere(lisDetailEstimation)){
		  		     			
								  		  					int reponse = JOptionPane.showConfirmDialog(null, "La quantité n'est pas suffaisante de la matière première:\n"+nomMP+"\n"
								  		  							+ "Voulez vous importer le Stock Suppélementaire ?", 
								  		  							"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
								  		  					if(reponse == JOptionPane.YES_OPTION )
								  		  					{
								  		  						JFrame popupJFrame = new MatierePremiere(mapQauntiteMatierePremier);
								  		  						popupJFrame.setVisible(true);
								
								  		  					} else {
								  		  						intialiserTableau();
								  		  					}
								  		  				}else
								  		  				{
								  		  					
								  		  				
								  		  				for(int j=0;j<lisDetailEstimation.size();j++)
								  		  				{
								  		  				listeformeParBox.clear();
								  		  					
								  		  			listeformeParBox=formeParBoxDAO.findBySubCategorie(lisDetailEstimation.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp());
								  		  				if(listeformeParBox.size()!=0)	
								  		  				{
								  		  					comboSousCategorie.setSelectedItem(lisDetailEstimation.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getNom()); 
								  		  				}
								  		  					
								  		  				}
								  		  					
								  		  					
								  		  					
								  		  				if(checkPromotion.isSelected()==true)
								  						{
								  							ValiderOffreMP();
								  							
								  							
								  							if(OffreValider==true)
								  							{
								  								remplirQuantiteOffreMP(listCoutMP);
								  							
								  								//EstimationEnVracCalculer=true;
								  								
								  								
								  							}
								  							
								  							afficherOffre=true;
								  							
								  							afficher_tableCouMP(listCoutMP);
								  							
								  							
								  							
								  							
								  							
								  						}else
								  						{
								  							//EstimationEnVracCalculer=true;
								  							afficher_tableCouMP(listCoutMP);
								  						}
								  		  				}
								  		  					
								  		  					
								  		  				}else {
								  		  					
								  		  				JOptionPane.showMessageDialog(null, "Il faut vérifier la quantité saisie !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								  		  					
								  		  				}
								  		  				
							  		  				}
								  		  		}
							
				  		  	}
				  		  });
				  		  btnCalculeMP.setIcon(new ImageIcon(CreerOrdreFabrication.class.getResource("/img/chercher.png")));
				  		  btnCalculeMP.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  btnCalculeMP.setBounds(20, 596, 133, 23);
				  		  add(btnCalculeMP);
				  		   
				  		   categorie.setBounds(313, 12, 259, 26);
				  		   add(categorie);
				  		   
				  		   JLabel lblArticle = new JLabel("Article:");
				  		   lblArticle.setBounds(265, 11, 102, 26);
				  		   add(lblArticle);
				  		   lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   quantite = new JTextField();
				  		   util.Utils.copycoller(quantite);
				  		   quantite.setBounds(640, 11, 157, 26);
				  		   add(quantite);
				  		   quantite.setColumns(10);
				  		   
				  		   JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
				  		   lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblQuantite.setBounds(582, 10, 68, 26);
				  		   add(lblQuantite);
				  		   comboLigneMachine = new JComboBox();
				  		   comboLigneMachine.addItem("");
				  		   codeArticle.setBounds(83, 11, 166, 26);
				  		   add(codeArticle);
				  		   codeArticle.setColumns(10);
				  		 
				  		   JLabel lblCodeArticle = new JLabel("Code Article");
				  		   lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeArticle.setBounds(10, 11, 82, 26);
				  		   add(lblCodeArticle);
				  		   dateDebutChooser = new JDateChooser();
				  		   dateFinChooser = new JDateChooser();
				  		    
				  		    btnAjouter = new JButton("Cr\u00E9er OF");
				  		    btnAjouter.setBounds(342, 596, 153, 23);
				  		    add(btnAjouter);
				  		    btnAjouter.setIcon(imgAjouter);
				  		    btnAjouter.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		if(EstimationEnVracCalculer==false)
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "Veuillez Calculer l'Estimation En Vrac SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     			return;
				  		     		}
				  		     		
				  		     		
				  		     		Promotion promotion=null;
				  		     		nomMP="";
				  		     		production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
				  		     		
				  		     		if(production==null){
				  		     			production=new Production();
				  		     		
				  		     		  if(comboForme.getSelectedItem()!=null)
				  		     		  {
				  		     			  

				  		     		  		if(!comboForme.getSelectedItem().toString().equals(""))
				  		     		  		{
				  		     		  			
				  		     		  			FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
				  		     		  			if(formeParBox!=null)
				  		     		  			{
				  		     		  				
				  		     		  			production.setFormeBox(formeParBox);
				  		     		  			
				  		     		  			}else
				  		     		  			{
				  		     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		  		return;
				  		     		  			}
				  		     		  		}else
				  		     		  		{
				  		     		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		  	return;
				  		     		  		}
				  		     		  }	else
				  		     		  {
				  		     			  
				  		     				JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
return;
				  		     			  
				  		     		  }
				  		     			
				  		     		
				  		     		if(lisDetailEstimation.size()<=0)
				  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la matière Première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboLigneMachine.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir une ligne de machine!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboPeriode.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir une période!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(dateDebutChooser.getDate()==null)
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir date début!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(dateFinChooser.getDate()==null)
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir date Fin!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboMagasinPF.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir magasin produit fini!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		else if(!afficherTableMatierePremiereCreerOF(listCoutMP)){
				  		     			if(nomMP.equals(""))
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre crée !Il faut remplir toutes quantités !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			else
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre crée !La quantité : "+nomMP+"n'est pas suffaisante", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}else {
				  		     			
				  		     			
				  		     			
				  		     		
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			Magasin magasinDechetMP=MapmagasinDechetMP.get(comboMagasinDechetMP.getSelectedItem());
				  		  				if(magasinDechetMP==null)
				  		  				{
				  		  				JOptionPane.showMessageDialog(null, "veuillez selectionner le magasin dechet MP si n'existe pas alors veuiilez l'ajouter SVP");
			  		  					return;
				  		  				}
			  		     		
			  		     				dateDebutChooser.setDateFormatString("dd/MM/yyyy");
			  		     				dateFinChooser.setDateFormatString("dd/MM/yyyy");
				  		     			Date dateDebutPrevue=dateDebutChooser.getDate();
				  		     			Date dateFinPrevue=dateFinChooser.getDate();
						  				
									
				  		     			LigneMachine ligneMachine = ligneMachineMap.get(comboLigneMachine.getSelectedItem());
				  		     			Magasin magasinProd=magasinPordMap.get(comboMagasinProd.getSelectedItem());
				  		     			Magasin magasinStock=magasinMap.get(comboMagasin.getSelectedItem());
				  		     			Magasin magasinPF=magasinStockagePF.get(comboMagasinPF.getSelectedItem());
				  		     			
				  		     			
				  		     			if(checkPromotion.isSelected()==true)
				  		     			{
				  		     				if(!comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
					  		     			{
				  		     					VerifierOffreExistant();
				  		     					
					  		     			}
				  		     				
				  		     				
				  		     					
				  		     				
				  		     			}
				  		     			
				  		     	
				  		     	TypeOffre typeOffre=mapTypeOffre.get(comboTypeOffre.getSelectedItem());
				  		     			
				  		     			
				  		     			production.setDate(dateDebutPrevue);
				  		     			Sequenceur sequenceur =sequenceurDAO.findByLibelle(magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			production.setStatut(ETAT_OF_CREER);
				  		     			production.setPeriode(comboPeriode.getSelectedItem().toString());
				  		     			production.setNumOF(txtNumOF.getText());
				  		     			production.setCodeDepot(codeDepot);
				  		     			production.setDescription(txtDescription.getText());
				  		     			production.setDate_debFabPre(dateDebutPrevue);
				  		     			production.setDateFinFabPre(dateFinPrevue);
				  		     			production.setQuantiteEstime(new BigDecimal(quantite.getText()));
				  		     			production.setUtilisateurCreation(AuthentificationView.utilisateur);
				  		     			production.setLigneMachine(ligneMachine);
				  		     			production.setMagasinProd(magasinProd);
				  		     			production.setMagasinStockage(magasinStock);
				  		     			production.setMagasinPF(magasinPF);
				  		     			production.setArticles(article);
				  		     			production.setListDetailResponsableProd(listDetailResponsableProd);
				  		     			production.setArticleMixte(chckbxArticleMixte.isSelected());
				  		     			production.setMagasinDechet(magasinDechetMP);
				  		     			production.setQuantiteOffre(BigDecimal.ZERO);
				  		     			production.setQuantitePlus(BigDecimal.ZERO);
				  		     			production.setQuantiteMoins(BigDecimal.ZERO);
				  		     			if(!comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
				  		     			{
				  		     				ConditionOffre	conditionOffre=	mapConditionOffre.get(comboCondition.getSelectedItem());
					  		     			production.setConditionOffre(conditionOffre);
				  		     			}
				  		     			
				  		     			production.setTyoeOffre(typeOffre);
				  		     			
				  		     			List<OffreProduction>listOffreProduction=new ArrayList<OffreProduction>();

				  		     			
				  		     			if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
				  		     			{
				  		     				
				  		     				production.setOffreVariant(Constantes.CODE_OUI);
				  		     				for(int t=0;t<comboConditionChecked.getItemCount();t++)
				  		     				{
				  		     					
				  		     					
			  		    							CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(t);
			  		    							if(checkBoxItem.isSelected()==true)
			  		    							{
			  		    								ConditionOffre conditionOffreTmp=	mapConditionOffre.get(checkBoxItem.getConditionOffre())		;  	
			  		    								if(conditionOffreTmp!=null)
			  		    								{
			  		    									OffreProduction offreProduction=new OffreProduction();
				  		    								offreProduction.setConditionOffre(conditionOffreTmp);
				  		    								offreProduction.setProdcutionCM(production);
				  		    								listOffreProduction.add(offreProduction);
			  		    								}
			  		    								
			  		    							}
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     			}else
				  		     			{
				  		     				production.setOffreVariant(Constantes.CODE_NON);
				  		     				
				  		     			}
				  		     			
				  		     			productionDAO.add(production);
				  		     			
				  		     			for(int t=0;t<listOffreProduction.size();t++)
				  		     			{
				  		     				OffreProductionDAO.add(listOffreProduction.get(t));
				  		     				
				  		     			}
				  		     			compterProduction(dateDebutPrevue,comboPeriode.getSelectedItem().toString());
				  		     			
				  		     			Utils.incrementerValeurSequenceur (magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			creerStockProduitFini();
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication Crée Avec Succès!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			InitialserTous();
				  		     			EstimationEnVracCalculer=false;
				  		     			
				  		     			/*PARAMETRAGE EMAIL
				  		     			try {
											EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
												"OF Crée avec succès",
												registerMailBody());
										} catch (AddressException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (MessagingException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}*/
				  		     			
				  		     			btnImprimer.setVisible(true);
			  		     			//	}	
				  		     		}
				  		     		

				  		     		}else {
					  		     		
					  		     		JOptionPane.showMessageDialog(null, "Cet Ordre de Fabrication est déjà crée !", "Attention", JOptionPane.INFORMATION_MESSAGE);	
					  		     	}
				  		     	}
				  		     });
				  		    btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		     btnInitialiser = new JButton("Initialiser");
				  		     btnInitialiser.setBounds(518, 596, 102, 23);
				  		     add(btnInitialiser);
				  		     btnInitialiser.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnInitialiser.setIcon(imgInit);
				  		     btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table.addInputMethodListener(new InputMethodListener() {
				  		     	public void caretPositionChanged(InputMethodEvent arg0) {
				  		     	}
				  		     	public void inputMethodTextChanged(InputMethodEvent arg0) {}
				  		     });
				  		     table.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyReleased(KeyEvent arg0) {}
				  		     });
				  		   
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		     table.getTableHeader().setReorderingAllowed(false);
				  		     DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 124, 1117, 226);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		     	
				  			   
				  		  		List<Machine> listMachine= machineDAO.findListMachineByCodeDepot(Codedepot);
				  		     comboMachine=new JComboBox ();
				  		     comboMachine.addItem("");
				  		       i=0;
				  		      	while(i<listMachine.size())
				  		      		{	
				  		      			Machine machine=listMachine.get(i);
				  		      			machineMap.put(machine.getNom(), machine);
				  		      			comboMachine.addItem(machine.getNom());
				  		      			i++;
				  		      		}
			  		     	 
			  		    
			  		     
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 97, 1117, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(26, 400, 1370, 161);
				  		     	add(layeredPane);
				  		     	
				  		     	txtNumOF = new JTextField();
				  		     	util.Utils.copycoller(txtNumOF);
				  		     	txtNumOF.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			
				  			  			production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
				  			  			comboDepotPF.setSelectedItem(production.getMagasinPF().getDepot().getLibelle());
				  			  		
				  			  		}}});
				  		     
				  		     	txtNumOF.setBounds(78, 11, 144, 26);
				  		     	layeredPane.add(txtNumOF);
				  		     	txtNumOF.setColumns(10);
				  		     	
				  		     	JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
				  		     	lblNumOF.setBounds(10, 12, 89, 24);
				  		     	layeredPane.add(lblNumOF);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Machine");
				  		     	lblMachine.setBounds(438, 49, 58, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 
				  		     	 comboMachine.setBounds(506, 50, 163, 24);
				  		     	 layeredPane.add(comboMachine);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Ligne Machine");
				  		     	 lblGroupe.setBounds(679, 49, 77, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboLigneMachine.setBounds(766, 49, 215, 24);
				  		     	 layeredPane.add(comboLigneMachine);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date D\u00E9but");
				  		  lblDatePrevue.setBounds(10, 48, 77, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  
				  		  
				  		  dateDebutChooser.setLocale(Locale.FRANCE);
				  		  dateDebutChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateDebutChooser.setBounds(78, 48, 144, 26);
				  		  layeredPane.add(dateDebutChooser);
				  		  
				  		  JLabel lblDateFin = new JLabel("Date Fin");
				  		  lblDateFin.setBounds(232, 49, 58, 26);
				  		  layeredPane.add(lblDateFin);
				  	
				  		  dateFinChooser.setLocale(Locale.FRANCE);
				  		  dateFinChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateFinChooser.setBounds(284, 47, 144, 26);
				  		  layeredPane.add(dateFinChooser);
				  		  
				  		  lblDescriptionOf = new JLabel("Description OF");
				  		  lblDescriptionOf.setBounds(232, 12, 89, 24);
				  		  layeredPane.add(lblDescriptionOf);
				  		  
				  		  txtDescription = new JTextField();
				  		util.Utils.copycoller(txtDescription);
				  		  txtDescription.setColumns(10);
				  		  txtDescription.setBounds(312, 14, 163, 26);
				  		  layeredPane.add(txtDescription);
				  		  
				  		  label = new JLabel("D\u00E9pot");
				  		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label.setBounds(10, 126, 68, 24);
				  		  layeredPane.add(label);
				  		  
				  		  comboDepotProd.setBounds(73, 127, 166, 24);
				  		  layeredPane.add(comboDepotProd);
				  		  
				  		  lblMagasinProd = new JLabel("Magasin Prod");
				  		  lblMagasinProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblMagasinProd.setBounds(251, 127, 89, 24);
				  		  layeredPane.add(lblMagasinProd);
				  		  
				  		
				  		  comboMagasinProd.setBounds(338, 128, 215, 24);
				  		  layeredPane.add(comboMagasinProd);
				  		  
				  		  JLabel lblDpotStockageProduit = new JLabel("D\u00E9pot Stockage Produit Fini");
				  		  lblDpotStockageProduit.setBounds(10, 90, 167, 24);
				  		  layeredPane.add(lblDpotStockageProduit);
				  		  lblDpotStockageProduit.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  comboDepotPF.setBounds(187, 91, 247, 24);
				  		  layeredPane.add(comboDepotPF);
				  		  comboDepotPF.addItem("");
				  		  
				  		  JLabel lblMagasinProduitFini = new JLabel("Magasin Produit Fini");
				  		  lblMagasinProduitFini.setBounds(444, 84, 124, 24);
				  		  layeredPane.add(lblMagasinProduitFini);
				  		  lblMagasinProduitFini.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  comboMagasinPF.setBounds(564, 87, 215, 24);
				  		  layeredPane.add(comboMagasinPF);
				  		  comboMagasinPF.addItem("");
				  		  
				  		   checkPromotion = new JCheckBox("Offre");
				  		   checkPromotion.setBounds(485, 13, 58, 23);
				  		   layeredPane.add(checkPromotion);
				  		   
				  		   JLabel lblMagasinDechetMp = new JLabel("Magasin Dechet MP");
				  		   lblMagasinDechetMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblMagasinDechetMp.setBounds(559, 122, 124, 24);
				  		   layeredPane.add(lblMagasinDechetMp);
				  		   
				  		    comboMagasinDechetMP = new JComboBox();
				  		   comboMagasinDechetMP.setBounds(679, 125, 249, 24);
				  		   layeredPane.add(comboMagasinDechetMP);
				  		   
				  		    lblTypeOffre = new JLabel("Type Offre :");
				  		   lblTypeOffre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblTypeOffre.setBounds(540, 13, 77, 24);
				  		   layeredPane.add(lblTypeOffre);
				  		   
				  		    comboTypeOffre = new JComboBox();
				  		    comboTypeOffre.addItemListener(new ItemListener() {
				  		    	public void itemStateChanged(ItemEvent e) {
				  		    		
				  		    		if(e.getStateChange() == ItemEvent.SELECTED) 
				  		    		
				  		    		
				  		    		{
				  		    			
				  		    			if(!comboTypeOffre.getSelectedItem().equals(""))
				  		    			{
				  		    				
				  		    				 OffreValider=false; 
				  		    				EstimationEnVracCalculer=false; 
				  		    			TypeOffre typeOffre=mapTypeOffre.get(comboTypeOffre.getSelectedItem());	
				  		    				
				  		    				if(typeOffre!=null)
				  		    				{
				  		    					
				  		    					if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES))
				  		    					{
				  		    						btnValiderConditionMixte.setVisible(false);
				  		    						lblCondition.setVisible(false);
				  		    						comboCondition.setVisible(false);
				  		    						comboCondition.setSelectedItem("");
				  		    						comboConditionChecked.setVisible(false);
				  		    						for(int i=0;i< comboConditionChecked.getItemCount();i++)
				  		    						{
				  		    							CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
				  		    							checkBoxItem.setSelected(false);
				  		    						}
				  		    						comboVariant.setVisible(false);
								  		   			comboVariant.setSelectedItem("");
				  		    						intialiserTableauOffre();  			
								  		   			intialiserTableauOffreVariant();
								  		   			
								  		   			tableOffre.setVisible(true);
								  		   		scrollPane_TableOffre.setVisible(true);
								  		  	scrollPane_TableOffreVariant.setVisible(false);
								  		   			tableOffreVariant.setVisible(false);
								  		   			
								  		   			SubCategorieMp subcategorie=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
								  		   			
								  		   			listMPOffre.clear();
								  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauAutres(subcategorie);
								  		   			
								  		   			
								  		   		listDetailEstimationOffreMP.clear();
							  		   			for(int i=0;i<listMPOffre.size();i++)
							  		   			{
							  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
							  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
							  		   			
							  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
							  		   		
							  		   		
							  		   			}
								  		   			
								  		   			
								  		   			afficher_tableOffreMP(listDetailEstimationOffreMP);
								  		   			
				  		    						
				  		    					}else if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
				  		    					{
				  		    						
				  		    						
				  		    						btnValiderConditionMixte.setVisible(false);
				  		    						lblCondition.setVisible(false);
				  		    						comboCondition.setVisible(false);
				  		    						comboCondition.setSelectedItem("");	
				  		    						comboConditionChecked.setVisible(false);
				  		    						for(int i=0;i< comboConditionChecked.getItemCount();i++)
				  		    						{
				  		    							CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
				  		    							checkBoxItem.setSelected(false);
				  		    						}
				  		    						comboVariant.setVisible(true);
								  		   			comboVariant.setSelectedItem("");
				  		    						intialiserTableauOffre();  			
								  		   			intialiserTableauOffreVariant();
								  		   			tableOffre.setVisible(false);
				  		    						tableOffreVariant.setVisible(false);
				  		    						scrollPane_TableOffre.setVisible(false);
				  		    						scrollPane_TableOffreVariant.setVisible(false);
				  		    					
				  		    						
				  		    						
				  		    						
				  		    					}else if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
				  		    					{
				  		    						
				  		    						
				  		    						btnValiderConditionMixte.setVisible(false);
				  		    						lblCondition.setVisible(true);
				  		    						comboCondition.setVisible(true);
				  		    						comboCondition.setSelectedItem("");	
				  		    						comboConditionChecked.setVisible(false);
				  		    						for(int i=0;i< comboConditionChecked.getItemCount();i++)
				  		    						{
				  		    							CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
				  		    							checkBoxItem.setSelected(false);
				  		    						}
				  		    						comboVariant.setVisible(false);
								  		   			comboVariant.setSelectedItem("");
				  		    						intialiserTableauOffre();  			
								  		   			intialiserTableauOffreVariant();
								  		   			tableOffre.setVisible(false);
				  		    						tableOffreVariant.setVisible(false);
				  		    						scrollPane_TableOffre.setVisible(false);
				  		    						scrollPane_TableOffreVariant.setVisible(false);
				  		    					
				  		    						
				  		    						
				  		    						
				  		    					}
				  		    					
				  		    					
				  		    					else
				  		    					{
				  		    						btnValiderConditionMixte.setVisible(false);
				  		    						lblCondition.setVisible(true);
				  		    						comboCondition.setVisible(true);
				  		    						comboCondition.setSelectedItem("");	
				  		    						comboConditionChecked.setVisible(false);
				  		    						for(int i=0;i< comboConditionChecked.getItemCount();i++)
				  		    						{
				  		    							CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
				  		    							checkBoxItem.setSelected(false);
				  		    						}
				  		    						comboVariant.setVisible(false);
								  		   			comboVariant.setSelectedItem("");
				  		    						intialiserTableauOffre();  			
								  		   			intialiserTableauOffreVariant();
								  		   			tableOffre.setVisible(false);
				  		    						tableOffreVariant.setVisible(false);
				  		    						scrollPane_TableOffre.setVisible(false);
				  		    						scrollPane_TableOffreVariant.setVisible(false);
				  		    					}
				  		    					 
				  		    				}
				  		    				
				  		    				
				  		    			}else
				  		    			{
				  		    				btnValiderConditionMixte.setVisible(false);
				  		    				 OffreValider=false; 
				  		    				EstimationEnVracCalculer=false; 
				  		    				lblCondition.setVisible(false);
		  		    						comboCondition.setSelectedItem("");
		  		    						
		  		    						intialiserTableauOffre();  			
						  		   			intialiserTableauOffreVariant();
						  		   			tableOffre.setVisible(false);
				  		    				tableOffreVariant.setVisible(false);
				  		    				scrollPane_TableOffre.setVisible(false);
				  		    				scrollPane_TableOffreVariant.setVisible(false);
				  		    				
				  		    			}
				  		    			
				  		    		}
				  		    		
				  		    		
				  		    	}
				  		    });
				  		   comboTypeOffre.setBounds(623, 13, 167, 24);
				  		   layeredPane.add(comboTypeOffre);
				  		   
				  		    lblCondition = new JLabel("Condition :");
				  		   lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCondition.setBounds(1030, 12, 77, 24);
				  		   layeredPane.add(lblCondition);
				  		   
				  		 
				  		comboConditionChecked = new JComboBox<CheckBoxItem>();
				  		comboConditionChecked = createCheckBoxComboBox(listCondition);
				  		
				  		comboConditionChecked.addPropertyChangeListener(new PropertyChangeListener() {
				  		    	public void propertyChange(PropertyChangeEvent arg0) {
}
				  		    });
				  		
				  		
				  		  comboConditionChecked.addItemListener(new ItemListener() {
				  		    	public void itemStateChanged(ItemEvent e) {
				  		    		
				  		    		  offreVarianteValider=false;
				  		    	 
				  		    		
				  		    	}
				  		    });
				  		  comboConditionChecked.setBounds(1095, 13, 154, 24);
				  		   layeredPane.add(comboConditionChecked);	
				  		   
				  		    comboCondition = new JComboBox();
				  		    comboCondition.addPropertyChangeListener(new PropertyChangeListener() {
				  		    	public void propertyChange(PropertyChangeEvent arg0) {
				  		    	}
				  		    });
				  		    comboCondition.addItemListener(new ItemListener() {
				  		    	public void itemStateChanged(ItemEvent e) {
				  		    		
	if(e.getStateChange() == ItemEvent.SELECTED) 
					  		    		
					  		    		
				  		    		{
				  		    			
				  		    		if(!comboCondition.getSelectedItem().equals(""))
				  		    		{
				  		    			 OffreValider=false; 
				  		    			EstimationEnVracCalculer=false; 
				  		    			ConditionOffre conditionOffre=mapConditionOffre.get(comboCondition.getSelectedItem());
				  		    			
				  		    			if(conditionOffre!=null)
				  		    			{
				  		    				BigDecimal unite=conditionOffre.getValeur();
				  		    				
				  		    				SubCategorieMp subcategorie=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
				  		    				
				  		    				if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
				  		    				{
				  		    					
				  		    					
				  		    					intialiserTableauOffre();  
				  		    					intialiserTableauOffreVariant();
							  		   			
							  		   			tableOffre.setVisible(true);
							  		   			tableOffreVariant.setVisible(false);
							  		   		scrollPane_TableOffre.setVisible(true);
							  		  	scrollPane_TableOffreVariant.setVisible(false);
							  		   			
							  		   			listMPOffre.clear();
							  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauPF(subcategorie, unite);
							  		   		 
							  		   
							  		   			listDetailEstimationOffreMP.clear();
							  		   			for(int i=0;i<listMPOffre.size();i++)
							  		   			{
							  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
							  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
							  		   			
							  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
							  		   			}
							  		   			
							  		   			
							  		   		afficher_tableOffreMP(listDetailEstimationOffreMP);
							  		   			
				  		    				}else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_SPECIALE))
				  		    				{
				  		    					comboVariant.setSelectedItem(""); 
								  		   		comboVariant.setVisible(false);
								  		   		intialiserTableauOffreVariant();
								  		     	tableOffreVariant.setVisible(false);
				  		    					listDetailEstimationOffreMP.clear();
				  		    					boolean fournisseurNonSelectionner=true;
				  		    					boolean existedeuxfois=false;
				  		    					
				  		    					for(int j=0;j<tablempfournisseurmixte.getRowCount();j++)
					  		  					{
					  		  						
					  		  						 
					  		  							if(!tablempfournisseurmixte.getValueAt(j, 2).toString().equals(""))
					  		  							{
					  		  								FournisseurMP fournisseurMP=mapFournisseurMP.get(tablempfournisseurmixte.getValueAt(j, 2).toString());
					  		  								MatierePremier matierePremier=mapMatierePremierEnVrac.get(tablempfournisseurmixte.getValueAt(j, 0).toString());
					  		  							    fournisseurNonSelectionner=false;
					  		  							    
					  		  							 for(int t=0;t<listDetailEstimationOffreMP.size();t++)
					  		  							 {
					  		  								DetailEstimationPromo detailEstimationPromo= listDetailEstimationOffreMP.get(t);
					  		  								if(detailEstimationPromo.getMatierePremiere().getId()==matierePremier.getId())
					  		  								{
					  		  									
					  		  								if(detailEstimationPromo.getFournisseur().getId()==fournisseurMP.getId())
					  		  								{
					  		  									
					  		  								existedeuxfois=true;
					  		  									
					  		  								}
					  		  									
					  		  									
					  		  								}
					  		  								 
					  		  								 
					  		  							 }
					  		  							 
					  		  							 if(existedeuxfois==false)
					  		  							 {
					  		  								 
					  		  								DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
					  		  							detailEstimationPromo.setFournisseur(fournisseurMP);
					  		  						detailEstimationPromo.setMatierePremiere(matierePremier);
					  		  					listDetailEstimationOffreMP.add(detailEstimationPromo);
					  		  								 
					  		  							 }
					  		  							 
					  		  							    
					  		  								
					  		  								
					  		  								
					  		  							}
					  		  							
					  		  							
					  		  					}
				  		    					
				  		    					if(existedeuxfois==true)
				  		    					{
				  		    						
				  		    						JOptionPane.showMessageDialog(null, "veuillez Selectionner Les Fournisseurs differents pour le meme Envrac   SVP !!!!");
				  		    						listDetailEstimationOffreMP.clear();
				  		    						intialiserTableauOffre();  			
								  		   			 tableOffre.setVisible(true);
								  		   		scrollPane_TableOffre.setVisible(true);
								  		  	scrollPane_TableOffreVariant.setVisible(false);
								  		  	tableOffreVariant.setVisible(false);
					  		    					listMPOffre.clear();
				  		    						return;
				  		    						
				  		    						
				  		    					}
				  		    					
				  		    					
				  		    					if(fournisseurNonSelectionner==true)
				  		    					{
				  		    						JOptionPane.showMessageDialog(null, "veuillez Selectionner Les Fournisseur d'envrac SVP !!!!");
				  		    						 intialiserTableauOffre();  			
								  		   			 tableOffre.setVisible(true);
								  		   		scrollPane_TableOffre.setVisible(true);
								  		  	scrollPane_TableOffreVariant.setVisible(false);
								  		  	tableOffreVariant.setVisible(false);
					  		    					listMPOffre.clear();
				  		    						return;
				  		    						
				  		    						
				  		    					}else
				  		    					{
				  		    						
				  		    					
				  		    						article =mapAricle.get(categorie.getSelectedItem());
							  		  				 
							  		  					
							  		  				lisDetailEstimation =detailestimationDAO.findDetilestimationActifByArticle(article.getId());
				  		    						
				  		    						for(int i=0;i<lisDetailEstimation.size();i++)
				  		    						{
				  		    						
				  		    							DetailEstimation detailEstimation=lisDetailEstimation.get(i);
				  		    							if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
				  		    							{
				  		    								
				  		    								DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
						  		  							 
						  		  						detailEstimationPromo.setMatierePremiere(detailEstimation.getMatierePremier());
						  		  					listDetailEstimationOffreMP.add(detailEstimationPromo);
				  		    								
				  		    							}
				  		    							
				  		    							
				  		    							
				  		    							
				  		    							
				  		    						}
				  		    						
				  		    						
				  		    						
				  		    					   intialiserTableauOffre();  
				  		    					   intialiserTableauOffreVariant();
								  		   			 tableOffre.setVisible(true);
								  		   			 tableOffreVariant.setVisible(false);
								  		   		scrollPane_TableOffre.setVisible(true);
								  		  	scrollPane_TableOffreVariant.setVisible(false);
					  		    					listMPOffre.clear();				  		    					
								  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauAutres(subcategorie);
								  		   			
								  		   		for(int i=0;i<listMPOffre.size();i++)
							  		   			{
							  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
							  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
							  		   			
							  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
							  		   			}
								  		   			
								  		   			
								  		   		    afficher_tableOffreMP(listDetailEstimationOffreMP);
								  		   		
								  		   		
				  		    					}
				  		    					
				  		    					
                                              
				  		    					
				  		    					
				  		    					
				  		    					
				  		    					
				  		    					
				  		    					
				  		    				}else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
				  		    				{
				  		    					
				  		    					mapMPCadeauAutrePF.clear();
				  		    					intialiserTableauOffre();  
				  		    					intialiserTableauOffreVariant();
							  		   			
							  		   			tableOffre.setVisible(true);
							  		   			tableOffreVariant.setVisible(false);
							  		   		scrollPane_TableOffre.setVisible(true);
							  		  	scrollPane_TableOffreVariant.setVisible(false);
							  		   			
							  		   			listMPOffre.clear();
							  		   		listDetailEstimationOffreMP.clear();
							  		   			
                                             SubCategorieMp subcategorieTmp=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
						  		   			
						  		   			
						  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauAutres(subcategorieTmp);
						  		   				
						  		   		for(int i=0;i<listMPOffre.size();i++)
					  		   			{
					  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
					  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
					  		   		mapMPCadeauAutrePF.put(listMPOffre.get(i).getCode(), listMPOffre.get(i));
					  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
					  		   			}
							  		   			
							  		   			
							  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauPF(subcategorie, unite);
							  		   		 
							  		   		for(int i=0;i<listMPOffre.size();i++)
						  		   			{
						  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
						  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
						  		   		mapMPCadeauAutrePF.put(listMPOffre.get(i).getCode(), listMPOffre.get(i));
						  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
						  		   			}
							  		   			
							  		   			
							  		   			
							  		   			
							  		   		afficher_tableOffreMP(listDetailEstimationOffreMP);
							  		   			
				  		    				}
				  		    				
				  		    				
				  		    				
				  		    				
				  		    				
				  		    				
				  		    			}
				  		    			
				  		    		
				  		    			
				  		    			
				  		    			
				  		    			
				  		    		}else
				  		    		{
				  		    			 OffreValider=false; 
				  		    			EstimationEnVracCalculer=false; 
				  		    			if(!comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES))
				  		    			{
				  		    				
				  		    				intialiserTableauOffre();  	
				  		    				intialiserTableauOffreVariant();
						  		   			tableOffreVariant.setVisible(false);
						  		   			tableOffre.setVisible(false);
						  		   			scrollPane_TableOffre.setVisible(false);
						  		   			scrollPane_TableOffreVariant.setVisible(false);
				  		    			}
				  		    			
				  		    			
				  		    		}
				  		    			
				  		    			
				  		    			
				  		    			
				  		    			
				  		    		}
				  		    		
				  		    		
				  		    		

				  		    	}
				  		    });
				  		   comboCondition.setBounds(1095, 13, 154, 24);
				  		   layeredPane.add(comboCondition);
				  				  		
				  		   
				  		   checkPromotion.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		
				  		   		if(checkPromotion.isSelected()==true)
				  		   		{
				  		   		btnValiderConditionMixte.setVisible(false);
				  		   			lblTypeOffre.setVisible(true);
				  		   			comboTypeOffre.setVisible(true);
				  		   		   comboTypeOffre.setSelectedItem("");
				  		   			comboVariant.setVisible(false);
				  		   			comboVariant.setSelectedItem("");
				  		   		EstimationEnVracCalculer=false; 
				  		   		 OffreValider=false; 
				  		   			
				  		   		}else
				  		   		{
				  		   		btnValiderConditionMixte.setVisible(false);
				  		   		lblTypeOffre.setVisible(false);
			  		   			comboTypeOffre.setVisible(false);
			  		   		   comboTypeOffre.setSelectedItem("");	
				  		   			lblCondition.setVisible(false);
				  		   			comboCondition.setVisible(false);
				  		   		comboCondition.setSelectedItem("");
				  		   	comboVariant.setVisible(false);
		  		   			comboVariant.setSelectedItem("");
				  		   		intialiserTableauOffre()	;
				  		   	tableOffre.setVisible(false);
				  			tableOffreVariant.setVisible(false);
				  			scrollPane_TableOffre.setVisible(false);
				  			scrollPane_TableOffreVariant.setVisible(false);
				  		   		 OffreValider=false; 
				  		   	EstimationEnVracCalculer=false; 
				  		   		}
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		  
				  		  comboDepotPF.addItemListener(new ItemListener() {
		  		     	 	public void itemStateChanged(ItemEvent e) {
		  		     	 	
		  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
			  		     	  	 // comboGroupe = new JComboBox();
		  		     	 	comboMagasinPF.removeAllItems();
		  		     	 	//comboGroupe.addItem("");
			  		     	  	   	Depot depot =depotMap.get(comboDepotPF.getSelectedItem());
			  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinPF.addItem(magasin.getLibelle());
						  		      			magasinStockagePF.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
		  		     	 	 }
		  		     	 	}
		  		     	 });
				  		
				  		  comboDepot.setBounds(83, 48, 166, 24);
				  		  add(comboDepot);
				  		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
				  			Depot	 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
					    		depotMap.put(depot.getLibelle(), depot);
		  		      			comboDepot.addItem(depot.getLibelle());
		  		      			comboDepotProd.addItem(depot.getLibelle());
		  		      			comboDepotPF.addItem(depot.getLibelle());
					    }else {
				  		  listDepot = depotDAO.findAll();	
				  		       i=0;
				  		      	while(i<listDepot.size())
				  		      		{	
				  		      			Depot depot=listDepot.get(i);
				  		      			depotMap.put(depot.getLibelle(), depot);
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      			comboDepotProd.addItem(depot.getLibelle());
				  		      			comboDepotPF.addItem(depot.getLibelle());
				  		      			i++;
				  		      		}
					    }
				  		  JLabel lblDpot = new JLabel("D\u00E9pot");
				  		  lblDpot.setBounds(10, 48, 58, 24);
				  		  add(lblDpot);
				  		  lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 
				  		   comboMagasin.setBounds(313, 49, 259, 24);
				  		   add(comboMagasin);
				  		   
				  		   JLabel lblMagasin = new JLabel("Magasin");
				  		   lblMagasin.setBounds(259, 49, 77, 24);
				  		   add(lblMagasin);
				  		   lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		  
				  		   comboPeriode.setBounds(642, 48, 157, 24);
				  		   add(comboPeriode);
				  		   
				  		   lblPriode = new JLabel("P\u00E9riode :");
				  		   lblPriode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblPriode.setBounds(582, 47, 77, 24);
				  		   add(lblPriode);
				  		   
				  		   JButton btnImprimerEquipe = new JButton("Fiche Equipe Prod");
				  		   btnImprimerEquipe.setIcon(imgImprimer);
				  		   btnImprimerEquipe.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		if(production.getId()>0){
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
								 List<Employe> listEmploye=production.getEquipe().getListEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine", production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", "");
								parameters.put("magasin", production.getMagasinProd().getLibelle());
								parameters.put("dateProd",date);
								JasperUtils.imprimerListeEmploye(listEmploye,parameters,production.getNumOF());
								//JasperUtils.imprimerListeEmployeTab(listEmploye);
							//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnImprimerEquipe.setBounds(20, 672, 142, 23);
				  		   add(btnImprimerEquipe);
				  		 btnImprimerEquipe.setVisible(false);
				  		   JButton btnImprimerFicherEquipeGen = new JButton("Fiche Equipe Emabalage");
				  		   btnImprimerFicherEquipeGen.setIcon(imgImprimer);
				  		   btnImprimerFicherEquipeGen.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   		if(production.getId()>0){
				  		   			
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
				  		   		List<Employe> listEmploye=production.getEquipeGen().getListEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine",production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", "");
								parameters.put("magasin",production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheEquipeGen(listEmploye,parameters,production.getNumOF());
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   		
				  		   	}
				  		   });
				  		   btnImprimerFicherEquipeGen.setBounds(172, 671, 167, 24);
				  		   add(btnImprimerFicherEquipeGen);
				  		 btnImprimerFicherEquipeGen.setVisible(false);
				  		   JButton btnImprimerGen = new JButton("imprimer Equipe Gen");
				  		   btnImprimerGen.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		//JasperUtils.imprimerInterne()
				  		   		
				  		  	if(production.getId()>0){
			  		   			
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
				  		   		List<Employe> listEmploye=employeDAO.findAutreEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine",production.getLigneMachine().getMachine().getNom());
								//parameters.put("equipe", production.getEquipeGen().getNomEquipe());
								parameters.put("magasin",production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheEquipeGen2(listEmploye,parameters,production.getNumOF());
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   	}
				  		   });
				  		   btnImprimerGen.setBounds(342, 670, 133, 26);
				  		   add(btnImprimerGen);
				  		 btnImprimerGen.setVisible(false);
				  		 
				  		   comboScotch.setBounds(896, 11, 157, 24);
				  		   add(comboScotch);
				  		   
				  		   JLabel lblScotche = new JLabel("Scotche :");
				  		   lblScotche.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblScotche.setBounds(815, 11, 77, 24);
				  		   add(lblScotche);
				  		   
				  		   JLabel lblThEnVrac = new JLabel("Th\u00E9 En Vrac :");
				  		   lblThEnVrac.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblThEnVrac.setBounds(815, 47, 77, 24);
				  		   add(lblThEnVrac);
				  		   
				  		    
				  		   comboBoxThe.setBounds(896, 47, 210, 24);
				  		   add(comboBoxThe);
				  		   
				  		   JButton btnValiderBonSortieMP = new JButton("Valider Bon Sortie En Vrac");
				  		   btnValiderBonSortieMP.setBounds(342, 636, 175, 24);
				  		   add(btnValiderBonSortieMP);

				  		   btnValiderBonSortieMP.setIcon(imgImprimer);
				  		
				  		
				  		chckbxArticleMixte.setBounds(1072, 13, 97, 23);
				  		add(chckbxArticleMixte);
				  		
				  		 scrollPaneMP = new JScrollPane((Component) null);
				  		scrollPaneMP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		scrollPaneMP.setBounds(1136, 48, 358, 300);
				  		scrollPaneMP.setVisible(false);
				  		add(scrollPaneMP);
				  		
				  		tableMP = new JTable();
				  		tableMP.setModel(new DefaultTableModel(
				  			new Object[][] {
				  			},
				  			new String[] {
				  				"Code", "Nom Matiere Premiere", "Quantit\u00E9 Estim\u00E9"
				  			})
				  			 {
						     	boolean[] columnEditables = new boolean[] {
						     			false,false,true
						     	};
						     	public boolean isCellEditable(int row, int column) {
						     		return columnEditables[column];
						     	}});
				  		
				  		tableMP.setFillsViewportHeight(true);
				  		tableMP.getTableHeader().setReorderingAllowed(false);
				  		scrollPaneMP.setViewportView(tableMP);
				  		
				  		 btnvaliderEstimation = new JButton("Valider Estimation");
				  		 btnvaliderEstimation.addActionListener(new ActionListener() {
				  		 	public void actionPerformed(ActionEvent arg0) {
				  		 		

								boolean trouve=false;
								
								BigDecimal number ; 
								BigDecimal somme = new BigDecimal("0");
								BigDecimal result = new BigDecimal("1");
							if(!remplirMapQuantiteEstimation())	{
								
								JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
							} else {
								List<DetailEstimation> listeDetailEstimationtmp= remplirDetailEstimation();
								
									for(int i=0;i<listeDetailEstimationtmp.size();i++)
									{
										if(listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE) >=0 || listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ZERO)  <=0)
										{
											trouve=true;
										}
									}
									
									if(trouve==true)
									{
										JOptionPane.showMessageDialog(null, "la Quantité Mixte doit etre entre 0 et 1", "Erreur", JOptionPane.ERROR_MESSAGE);
										return;
									}else
									{
										
										for(int i=0;i<listeDetailEstimationtmp.size();i++)
										{
											number =new BigDecimal(listeDetailEstimationtmp.get(i).getQuantite()+"");
											somme=somme.add(number);
										}
										
										if(somme.compareTo(result)==1)
										{
											JOptionPane.showMessageDialog(null, "La somme de la quantité Mixte doit etre egale 1 ", "Erreur", JOptionPane.ERROR_MESSAGE);
											return;
										}
										else
										{
											
											article=articleDAO.findByCode(codeArticle.getText());
											List<DetailEstimation> listeDetailEstimation= remplirDetailEstimation();
											article.setDetailEstimation(listeDetailEstimation);
												
											articleDAO.edit(article);
											JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
											intialiserTableauMP();
											hide();
											
										}
											
										
									}
							
								
							}
						  
				  		 		
				  		 		
				  		 	}
				  		 });
				  		btnvaliderEstimation.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnvaliderEstimation.setBounds(1162, 353, 158, 23);
				  		btnvaliderEstimation.setIcon(imgAjouter);
				  		btnvaliderEstimation.setVisible(false);
				  		add(btnvaliderEstimation);
				  		
				  		btnAjouterEstimation = new JButton("Ajouter Estimation");
				  		btnAjouterEstimation.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				

				        		
				        			
				        			if(!categorie.getSelectedItem().equals(""))
				        			{
				        				if(codeArticle.getText()!="")
				        				{
				        						show();
				        							Articles article=mapAricle.get(categorie.getSelectedItem());
				        							lisDetailEstimationbycategorie=detailestimationDAO.findDetilestimationByCategorie(article.getId());
				        						
				        							if(lisDetailEstimationbycategorie.size()!=0)
				        							{
				        								intialiserTableauMP();
				        							 	List<MatierePremier> listeMP = matierePremiereDAO.listeMatierePremierByCategorie(lisDetailEstimationbycategorie.get(0).getMatierePremier().getCategorieMp().getCode());	
				        					
				        								afficher_tableMatierPremier(listeMP);
				        							}else
				        							{
				        								intialiserTableauMP();
				        							}
				        						
				        				
				        				}else
				        				{
				        					
				        				JOptionPane.showMessageDialog(null, "Merci de Choisir l'article avant de continue SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				        				return;
				        				}
				        				
				        			}else
				        			{
				        				
				        				JOptionPane.showMessageDialog(null, "Merci de saisir code article avant de continue SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				        				return;
				        			}
				        			
				        			
				  				
				  				
				  			}
				  		});
				  		btnAjouterEstimation.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnAjouterEstimation.setBounds(1175, 13, 133, 23);
				  		btnAjouterEstimation.setVisible(false);
				  		add(btnAjouterEstimation);
				  		
				  		JButton btnExporterExcel = new JButton("Exporter Excel");
				  		btnExporterExcel.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				

					    		Magasin magasin=magasinMap.get(comboMagasin.getSelectedItem());
					    		if(magasin!=null)
					    		{
					    		
					    		String titre="La Création OF Numero  "+txtNumOF.getText()+ " Magasin "+magasin.getLibelle();
					    		String titrefeuille="La Création OF ";
					    		ExporterTableVersExcel.tabletoexcelCreerMP(table, titre,titrefeuille);
					    		
					    		}else
					    		{


					    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
					    			return;
					    		
					    		
					    		}
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  			}
				  		});
				  		btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnExporterExcel.setBounds(498, 671, 107, 23);
				  		add(btnExporterExcel);
				  		
				  		JButton btnActiverDesactiver = new JButton("Activer / Desactiver MP");
				  		btnActiverDesactiver.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				
				  			Articles article=	mapAricle.get(categorie.getSelectedItem());
				  				JFrame popupJFrame = new ActiverDesactiverMPEstimation(article);
				  		    	  popupJFrame.setVisible(true);
				  				
				  				
				  			}
				  		});
				  		btnActiverDesactiver.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnActiverDesactiver.setBounds(1318, 13, 145, 23);
				  		add(btnActiverDesactiver);
				  		
				  		JButton btnBonSortieEmballage = new JButton("Bon Sortie Emballage");
				  		btnBonSortieEmballage.setIcon(imgImprimer);
				  		btnBonSortieEmballage.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				

				  		  		
					  		  	if(lisDetailEstimation.size()<=0)
			  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la matière Première avant d'imprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		  	else {
					  		  		listCoutMPEmballage.clear();
					  		  		for(int i=0;i<listCoutMP.size();i++)
					  		  		{
					  		  			
					  		  			CoutMP coutMP=listCoutMP.get(i);
					  		  			if( !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					  		  			{
					  		  				
					  		  			listCoutMPEmballage.add(coutMP);
					  		  			
					  		  			
					  		  			}
					  		  			
					  		  			
					  		  			
					  		  			
					  		  		}
					  		  		
					  		  		
					  		  		
					  		  		
					  		  		if(listCoutMPEmballage.size()!=0)
					  		  		{
					  		  			
					  		  		if(dateDebutChooser.getDate()==null)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la date SVP !!!");
				  		  			return;
				  		  			}else if(txtNumOF.getText().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez calculer la Matiere premiere SVP !!!");
				  		  			return;
				  		  			}else if(comboMachine.getSelectedIndex()==-1)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la machine SVP !!!");
				  		  			return;	
				  		  			}else if(comboMachine.getSelectedItem().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner la machine SVP !!!");
				  		  			return;	
				  		  			}else if(comboMagasin.getSelectedIndex()==-1)
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner Magasin SVP !!!");
				  		  			return;	
				  		  			}else if(comboMagasin.getSelectedItem().equals(""))
				  		  			{
				  		  			JOptionPane.showMessageDialog(null, "veuillez selectionner Magasin SVP !!!");
				  		  			return;	
				  		  			}
					  		  		
					  		  		
						/*
						 * for(int j=0;j<listCoutMPEmballage.size();j++) {
						 * 
						 * for(int i=0;i<table.getRowCount();i++) {
						 * if(listCoutMPEmballage.get(j).getFournisseurMP()!=null) {
						 * if(listCoutMPEmballage.get(j).getMatierePremier().getCode().equals(table.
						 * getValueAt(i, 0).toString()) &&
						 * listCoutMPEmballage.get(j).getFournisseurMP().getCodeFournisseur().equals(
						 * table.getValueAt(i, 2).toString())) {
						 * 
						 * listCoutMPEmballage.get(j).setQuantCharge(new BigDecimal(table.getValueAt(i,
						 * 6).toString()));
						 * 
						 * } }else {
						 * 
						 * if(listCoutMPEmballage.get(j).getMatierePremier().getCode().equals(table.
						 * getValueAt(i, 0).toString()) ) {
						 * 
						 * listCoutMPEmballage.get(j).setQuantCharge(new BigDecimal(table.getValueAt(i,
						 * 6).toString()));
						 * 
						 * }
						 * 
						 * 
						 * }
						 * 
						 * 
						 * 
						 * 
						 * }
						 * 
						 * }
						 */
					  		  		
					  		  		
					  		  		
					  		  			
					  		  			
					  		  		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	String date=dateFormat.format(dateDebutChooser.getDate());
									 
									Map parameters = new HashMap();
									
									parameters.put("titre", "Bon de Sortie Emballage");
									parameters.put("numOF", txtNumOF.getText());
									parameters.put("machine", comboMachine.getSelectedItem());
									parameters.put("equipe", "");
									parameters.put("magasin", comboMagasin.getSelectedItem());
									parameters.put("dateProd", date);
									parameters.put("article",article.getLiblle());
									JasperUtils.imprimerBonSortieMatierePremiereEmballage(listCoutMPEmballage,parameters,production.getNumOF());
					  		  		}else
					  		  		{
					  		  			JOptionPane.showMessageDialog(null, "Veuillez Calculer la Matiere Premiere SVP !!!!");
					  		  			return;
					  		  			
					  		  		}
					  		  		
					  		  		
					  		  
						  		  	
					  		  	}
									
										
				  				
				  				
				  				
				  				
				  				
				  				
				  			}
				  		});
				  		btnBonSortieEmballage.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnBonSortieEmballage.setBounds(172, 637, 164, 23);
				  		add(btnBonSortieEmballage);
				  		
				  		btnValiderBonSortie = new JButton("Valider Bon Sortie Emballage");
				  		btnValiderBonSortie.setIcon(imgImprimer);
				  		btnValiderBonSortie.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				

					  		  	
					  		  	if(production.getId()>0){
					  		  	List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(production.getDate());
								listCoutMP=productionDAO.listeCoutMP(production.getId());
						  		listCoutMPEmballage.clear();
				  		  		for(int i=0;i<listCoutMP.size();i++)
				  		  		{
				  		  			
				  		  			CoutMP coutMP=listCoutMP.get(i);
				  		  			if( !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		  			{
				  		  				
				  		  			listCoutMPEmballage.add(coutMP);
				  		  			
				  		  			
				  		  			}
				  		  			
				  		  			
				  		  		}
								
								Map parameters = new HashMap();
								parameters.put("titre", "Bon de Sortie Emballage");
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine", production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", "");
								parameters.put("magasin", production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerValiderBonSortieMatierePremiere(listCoutMPEmballage,parameters,production.getNumOF());
								
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					  		  	}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
					  		  		
				  				
				  				
				  				
				  				
				  			}
				  		});
				  		btnValiderBonSortie.setBounds(528, 636, 201, 24);
				  		add(btnValiderBonSortie);
				  		
				  		
				  		btnValiderBonSortieMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	
				  		  	if(production.getId()>0){
				  		  	List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
				  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String date=dateFormat.format(production.getDate());
							listCoutMP=productionDAO.listeCoutMP(production.getId());
							
					  		listCoutMPEnVrac.clear();
			  		  		for(int i=0;i<listCoutMP.size();i++)
			  		  		{
			  		  			
			  		  			CoutMP coutMP=listCoutMP.get(i);
			  		  			if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		  			{
			  		  				
			  		  				listCoutMPEnVrac.add(coutMP);
			  		  			}
			  		  			
			  		  			
			  		  			
			  		  			
			  		  		}
			  		  		
			  		  		
			  			  for(int j=0;j<listCoutMPEnVrac.size();j++) {
							  
							  for(int i=0;i<table.getRowCount();i++) {
							  
							  if(listCoutMPEnVrac.get(j).getMatierePremier().getCode().equals(table.getValueAt(i, 0).toString()) &&  listCoutMPEnVrac.get(j).getFournisseurMP().getCodeFournisseur().equals(table.getValueAt(i, 2).toString())) {
							  
							 if(table.getValueAt(i, 7)!=null)
							 {
								 
								if(!table.getValueAt(i, 7).equals("") )
								{
									
									listCoutMPEnVrac.get(j).setMoinsPlus(table.getValueAt(i, 7).toString());
									
								}
								 
								 
							 }
							  
							  
							  
							  }
							  
							  
							  }
							  
							  }
							
			  		  		
			  		  		
							 
							Map parameters = new HashMap();
							parameters.put("titre", "Bon de Sortie En Vrac");
							parameters.put("numOF", production.getNumOF());
							parameters.put("machine", production.getLigneMachine().getMachine().getNom());
							parameters.put("equipe", "");
							parameters.put("magasin", production.getMagasinProd().getLibelle());
							parameters.put("dateProd", date);
							JasperUtils.imprimerValiderBonSortieMatierePremiere(listCoutMPEnVrac,parameters,production.getNumOF());
							
							//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		  	}else {
				  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
				  		  	}
				  		  	}
				  		  });
				  		   
				  		  
				  		   comboDepot.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				  		     	  	 // comboGroupe = new JComboBox();
			  		     	 	comboMagasin.removeAllItems();
			  		     	 	//comboGroupe.addItem("");
				  		     	  	   	Depot depot =depotMap.get(comboDepot.getSelectedItem());
						  		       
				  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,MAGASIN_CODE_CATEGORIE_STOCKAGE);
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasin.addItem(magasin.getLibelle());
							  		      			magasinMap.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
			  		     	 	 }
			  		     	 	}
			  		     	 });
				  		
				  		comboDepotProd.addItemListener(new ItemListener() {
		  		     	 	public void itemStateChanged(ItemEvent e) {
		  		     	 	
		  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		  		     	 		 if(comboMachine.getSelectedItem()!=null && !comboMachine.getSelectedItem().equals("")){
		  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
		  		     	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
			  		     	  	 // comboGroupe = new JComboBox();
		  		     	 	 		comboMagasinProd.removeAllItems();
		  		     	 	 	//comboGroupe.addItem("");
			  		     	  	   	Depot depot =depotMap.get(comboDepotProd.getSelectedItem());
			  		     	  	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
			  		     	  	
			  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,machine.getMatricule());
			  		     	 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinProd.addItem(magasin.getLibelle());
						  		      			magasinPordMap.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
					  		      
					  		      
      if(listMagasinDechetMP!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasinDechetMP.size())
						  		      		{	
						  		      			Magasin magasin=listMagasinDechetMP.get(j);
						  		      			comboMagasinDechetMP.addItem(magasin.getLibelle());
						  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
					  		      
					  		      
		  		     	 	 }
		  		     	 		else {
			  		     	 		JOptionPane.showMessageDialog(null, "Il faut Choisir une machine", "Erreur", JOptionPane.ERROR_MESSAGE);
			  		     	 	 }
		  		     	 	 }
		  		     	 	}
		  		     	 });
				  		  comboMachine.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 		 
			  		     	 		 if(!comboDepotProd.getSelectedItem().equals("")){

					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinProd.removeAllItems();
					  		     	 	//comboGroupe.addItem("");
						  		     	  	   	Depot depot =depotMap.get(comboDepotProd.getSelectedItem());
						  		     	  	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
						  		     	  	
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,machine.getMatricule());
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinProd.addItem(magasin.getLibelle());
									  		      			magasinPordMap.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 
			  		     	 			 
			  		     	 		 }
			  		     	 	 List<LigneMachine> listLigneMachine=new ArrayList<LigneMachine>();
			  		     	 	comboLigneMachine.removeAllItems();
				  		     	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
						  		       
						  		      listLigneMachine = machine.getListLigneMachine();
						  		      if(listLigneMachine!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listLigneMachine.size())
							  		      		{	
							  		      			LigneMachine ligneMachine=listLigneMachine.get(j);
							  		      			comboLigneMachine.addItem(ligneMachine.getNom());
							  		      			ligneMachineMap.put(ligneMachine.getNom(), ligneMachine);
							  		      			j++;
							  		      		}
						  		      }
			  		     	 	 }
			  		     	 	}
			  		     	 });
				  		 
	
	

	

	
	if(!quantiteTmp.equals(""))
	{
		quantite.setText(quantiteTmp);
	}
	if(!ScotchTmp.equals(""))
	{
		comboScotch.setSelectedItem(ScotchTmp);
	}
	
	if(!DepotTmp.equals(""))
	{
		comboDepot.setSelectedItem(DepotTmp);
	}
	if(!MagasinTmp.equals(""))
	{
		comboMagasin.setSelectedItem(MagasinTmp);
	}
	
	if(!PeriodeTmp.equals(""))
	{
		comboPeriode.setSelectedItem(PeriodeTmp);
	}
	
	
	if(!NumOFTmp.equals(""))
	{
		txtNumOF.setText(NumOFTmp);
	}
	
	if(dateDebutTmp!=null)
	{
		dateDebutChooser.setDate(dateDebutTmp);
	}
	
	
	if(dateFinTmp!=null)
	{
		dateFinChooser.setDate(dateFinTmp);
	}
	
	if(!MachineTmp.equals(""))
	{
		comboMachine.setSelectedItem(MachineTmp);
	}
	
	if(!DepotProdTmp.equals(""))
	{
		comboDepotProd.setSelectedItem(DepotProdTmp);
	}
	
	if(!LigneMachineTmp.equals(""))
	{
		comboLigneMachine.setSelectedItem(LigneMachineTmp);
	}
	
	if(!MagasinProdTmp.equals(""))
	{
		comboMagasinProd.setSelectedItem(MagasinProdTmp);
	}
	
	if(!DepotPFTmp.equals(""))
	{
		comboDepotPF.setSelectedItem(DepotPFTmp);
	}
	
	if(!MagasinPFTmp.equals(""))
	{
		comboMagasinPF.setSelectedItem(MagasinPFTmp);
	}
	
	
	
	if(articleTmp!=null)
	{
		codeArticle.setText(articleTmp.getCodeArticle());
		
		
		categorie.setSelectedItem(mapLibelleAricle.get(codeArticle.getText()));
		
		
		if(!envracTmp.equals(""))
		{
			comboBoxThe.setSelectedItem(envracTmp);
		}
		
		
		chckbxArticleMixte.setSelected(mixte);
		
		
		if(chckbxArticleMixte.isSelected()==true)
		{
			btnAjouterEstimation.setVisible(true);
			comboBoxThe.setSelectedIndex(-1);
			comboBoxThe.setEnabled(false);
			
			
		}
		
		
		
		
		
		
		
		if(PromotionTMP!=null)
		{
			
			checkPromotion.setSelected(true);

	   		if(checkPromotion.isSelected()==true)
		   		{
		   			//combopromotion.setVisible(true);
		   		 
		   		
		   			listPromotion.clear();
		   		 
		  		listPromotion=promotiondao.findAll();
		   		
	   			if(listPromotion.size()!=0)
	   			{
	   				for(int k=0;k<listPromotion.size();k++)
	   				{
	   					Promotion promotionTM=listPromotion.get(k);
	   				 
	   					mapPromotion.put(promotionTM.getCode(), promotionTM);
	   				}
	   			}
		   			
/*
 * if(categorie.getSelectedIndex()!=-1 &&
 * !categorie.getSelectedItem().equals("")) { Articles
 * article=mapAricle.get(categorie.getSelectedItem()); if(article!=null) {
 * listPromotion=promotiondao.findByArticleActif(article.getId());
 * 
 * if(listPromotion.size()!=0) { for(int i=0;i<listPromotion.size();i++) {
 * Promotion promotion=listPromotion.get(i);
 * combopromotion.addItem(promotion.getCode());
 * mapPromotion.put(promotion.getCode(), promotion); } } } }
 */
		   			
		   			
		   			
		   			
		   		}else
		   		{

		   		
		   		}
 			
 			
 			
			 
			
			
			
			
		}
		
		
		lisDetailEstimation =detailestimationDAO.findDetilestimationActifByArticle(articleTmp.getId());
		afficherTableMatierePremiere(lisDetailEstimation);
	
		
	}
	

	
	 btnAjouterFournisseurEnVrac = new JButton("Ajouter Fournisseur En Vrac");
	 btnAjouterFournisseurEnVrac.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent arg0) {
	 		lisDetailEstimationTmp.clear();
	 		lisDetailEstimationAfficherEnvVacMixte.clear();
	 		scrollPane_1.setVisible(true);
	 		mapMatierePremierEnVrac.clear();
	 		Articles articles=mapAricle.get(categorie.getSelectedItem());
	 		
	 		if(articles==null)
	 		{
	 			
	 			JOptionPane.showMessageDialog(null, "veuillez choisir l'article SVP");
	 			return;
	 		}else
	 		{
	 			
	 				
	 				lisDetailEstimationTmp =detailestimationDAO.findDetilestimationActifByArticle(articles.getId());
		 			
		 			for(int i=0;i<lisDetailEstimationTmp.size();i++)
		 			{
		 				
		 				
		 			DetailEstimation detailEstimation=lisDetailEstimationTmp.get(i);
		 			
		 				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		 				{
		 					mapMatierePremierEnVrac.put(detailEstimation.getMatierePremier().getCode(), detailEstimation.getMatierePremier());
		 					
		 					if(chckbxArticleMixte.isSelected()==true)
							{
		 						mapMatierePremier.put(detailEstimation.getMatierePremier().getNom(), detailEstimation.getMatierePremier());
		 					
		 						/*if(detailEstimation.getPriorite()==2)
			 					{*/
		 							
			 						for(int j=0;j<listFournisseurMP.size();j++)
			 						{
			 							FournisseurMP fournisseur=listFournisseurMP.get(j);
			 							detailEstimation.setFournisseur(fournisseur);
			 							lisDetailEstimationAfficherEnvVacMixte.add(detailEstimation);
			 							
			 						}
			 						
			 						
			 					/*	
			 					}
		 						*/
							}else
							{
								
									
									MatierePremier envrac=	mapMatierePremier.get(comboBoxThe.getSelectedItem());
				 					if(envrac.getId()==detailEstimation.getMatierePremier().getId())
				 					{
				 						
				 						for(int j=0;j<listFournisseurMP.size();j++)
										{
											FournisseurMP fournisseur=listFournisseurMP.get(j);
											detailEstimation.setFournisseur(fournisseur);
											lisDetailEstimationAfficherEnvVacMixte.add(detailEstimation);
											
										}
				 					}
			 					
								
		 			
								
							}
		 					
		 				}
		 				
		 				
		 			}
				
	 		
	 			
	 			if(lisDetailEstimationAfficherEnvVacMixte.size()!=0)
	 			{
	 				
	 				afficher_tableMPMixteFournisseur(lisDetailEstimationAfficherEnvVacMixte);
	 				
	 			}
	 			
	 			
	 			
	 			
	 			
	 		}
	 		
	 		
	 		
	 		
	 	}
	 });
	btnAjouterFournisseurEnVrac.setBounds(1523, 13, 229, 28);
	add(btnAjouterFournisseurEnVrac);
	
	JButton btnCalculerEstimationEn = new JButton("Calculer Estimation En Vrac");
	btnCalculerEstimationEn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(!remplirMapQuantiteCharge()){
				creerOF=false;
				JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantité Chargées Avant De Calculer Estimation", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
				
				
				 for(int t=0;t<table.getRowCount();t++)
				 {

	  		     		

	  		     		if(!table.getValueAt(t, 6).equals(""))
	  		     		{
	  		     			
	  		     			table.setValueAt(new BigDecimal(table.getValueAt(t, 6).toString()).add(new BigDecimal(table.getValueAt(t, 4).toString())), t, 7);
	  		     			
	  		     		}else
	  		     		{
	  		     			table.setValueAt(new BigDecimal(table.getValueAt(t, 4).toString()), t, 7);

	  		     		}
	  		     		
	  		     	
	  		     	
				 }
				
				 
				
				BigDecimal QuantiteTotalEnvracCharger=BigDecimal.ZERO;
				for(int i=0;i<listCoutMP.size();i++){
					
				CoutMP	 coutMP= listCoutMP.get(i);
					  
						
						if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							
							QuantiteTotalEnvracCharger=QuantiteTotalEnvracCharger.add(mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).add(mapQuantiteMPExistante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()))) ;
							 
							 
							
						} 
						
						 
						
						
						
				}
				
				BigDecimal QuantiteTotalestimation=BigDecimal.ZERO;
				int pos=-1;
				boolean existe=false;
				
				for(int i=0;i<listCoutMP.size();i++){
					
					CoutMP	 coutMP= listCoutMP.get(i);
						  
							
							if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								coutMP.setEstimation((mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).add(mapQuantiteMPExistante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()))).divide(QuantiteTotalEnvracCharger, 6, RoundingMode.HALF_UP)) ;
								QuantiteTotalestimation=QuantiteTotalestimation.add(coutMP.getEstimation());
		  		  						pos=i;
		  		  						existe=true;
								for(int j=0;j<tablempfournisseurmixte.getRowCount();j++)
	  		  					{
	  		  						
	  		  						if(!tablempfournisseurmixte.getValueAt(j, 2).toString().equals(""))
	  		  						{
	  		  							 
	  		  							if(coutMP.getMatierePremier().getCode().equals(tablempfournisseurmixte.getValueAt(j, 0).toString()))
	  		  							{
	  		  								
	  		  							if(coutMP.getFournisseurMP().getCodeFournisseur().equals(tablempfournisseurmixte.getValueAt(j, 2).toString()))
	  		  							{
	  		  								
	  		  								
	  		  							tablempfournisseurmixte.setValueAt(coutMP.getEstimation(), j, 3);
	  		  						    EstimationEnVracCalculer=true;
	  		  						
	  		  						    
	  		  							}
	  		  								
	  		  								
	  		  							}
	  		  							
	  		  						}
	  		  						
	  		  						
	  		  						
	  		  					}
	  		  					
	  		  					
	  		  					 
	  		  					
	  		  					
	  		  				}else
	  		  				{
	  		  					
	  		  					if(coutMP.getMatierePremier().getCode().contains("C"))
	  		  					{
	  		  						
	  		  						if(mapQuantiteMP.get(coutMP.getMatierePremier().getCode().replace("C", "").trim())!=null)
	  		  						{
	  		  							
	  		  						coutMP.setEstimation(mapQuantiteMP.get(coutMP.getMatierePremier().getCode()).divide(mapQuantiteMP.get(coutMP.getMatierePremier().getCode().replace("C", "").trim()).add(mapQuantiteMP.get(coutMP.getMatierePremier().getCode())),12,RoundingMode.HALF_UP).multiply(coutMP.getEstimation()).setScale(6, RoundingMode.HALF_UP) );
	  		  					 
	  		  					listCoutMP.set(i, coutMP);
	  		  						}
	  		  						
	  		  						
	  		  						
	  		  					}else
	  		  					{
	  		  						
	  		  					if(mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"C")!=null)
  		  						{
	  		  						
	  		  						coutMP.setEstimation(mapQuantiteMP.get(coutMP.getMatierePremier().getCode()).divide(mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"C").add(mapQuantiteMP.get(coutMP.getMatierePremier().getCode())),12,RoundingMode.HALF_UP).multiply(coutMP.getEstimation()).setScale(6, RoundingMode.HALF_UP) );
	  		  					 
	  		  						listCoutMP.set(i, coutMP);
  		  							
  		  							
  		  						}
	  		  						
	  		  						
	  		  					}
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  				}
								
								
								
								
								
								
							 
							
							
							
					}
				
				
				
				if(QuantiteTotalestimation.compareTo(BigDecimal.ONE)!=0)
				{
					
					CoutMP coutMP=listCoutMP.get(pos);
					coutMP.setEstimation(coutMP.getEstimation().add(BigDecimal.ONE.subtract(QuantiteTotalestimation)));
					listCoutMP.set(pos, coutMP);
					
					for(int j=0;j<tablempfournisseurmixte.getRowCount();j++)
	  					{
	  						
	  						if(!tablempfournisseurmixte.getValueAt(j, 2).toString().equals(""))
	  						{
	  							 
	  							if(coutMP.getMatierePremier().getCode().equals(tablempfournisseurmixte.getValueAt(j, 0).toString()))
	  							{
	  								
	  							if(coutMP.getFournisseurMP().getCodeFournisseur().equals(tablempfournisseurmixte.getValueAt(j, 2).toString()))
	  							{
	  								
	  								
	  							tablempfournisseurmixte.setValueAt(coutMP.getEstimation(), j, 3);
	  						    
	  						
	  						    
	  							}
	  								
	  								
	  							}
	  							
	  						}
	  						
	  						
	  						
	  					}
					
					
					
					
				}
				
				if(EstimationEnVracCalculer==true)
				{
					if(checkPromotion.isSelected()==true)
					{
						ValiderOffreMP();
						
						
						if(OffreValider==true)
						{
							//remplirQuantiteOffreMP(listCoutMP);
						
							 
							
							
						}
						
						afficherOffre=true;
						
						//afficher_tableCouMP(listCoutMP);
						
						
						
						
						
					}
				}
			 
				
				
				
				
				
				
				
				
			}
			
			
			
		}
	});
	btnCalculerEstimationEn.setFont(new Font("Tahoma", Font.PLAIN, 11));
	btnCalculerEstimationEn.setBounds(164, 596, 167, 23);
	add(btnCalculerEstimationEn);
	
	  scrollPane_TableOffre = new JScrollPane();
	scrollPane_TableOffre.setBounds(1406, 376, 553, 331);
	add(scrollPane_TableOffre);
	
	tableOffre = new JTable();
	tableOffre.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			
			 int column = tableOffre.columnAtPoint( e.getPoint() );
	     		int row =tableOffre.rowAtPoint(e.getPoint());
	     		
	     		int y=0;
	     		
	     		TypeOffre typeOffre=mapTypeOffre.get(comboTypeOffre.getSelectedItem());	
				
				if(typeOffre!=null)
				{
					
					if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
					{
						for(int t=0;t<tableOffre.getRowCount();t++)
			     		{
			     			
			     			if((boolean)(tableOffre.getValueAt(t, 4))==true)
			     			{
			     				y=y+1;
			     			}
			     			
			     			
			     			
			     			
			     		}
					}
					
					
				}
	     	
	     		
	     		if(column==4)	
  		     	{
	     			
	     			if(y>1)
	     			{
	     				for(int t=0;t<tableOffre.getRowCount();t++)
	    	     		{
	         				tableOffre.setValueAt("", t, 3);
	    	     		}
	     			}else if(y==1 && (boolean) tableOffre.getValueAt(row, 4)==true )
	     			{
	     				for(int t=0;t<tableOffre.getRowCount();t++)
	    	     		{
	         				tableOffre.setValueAt("", t, 3);
	    	     		}
	     			}else if(y==0 && (boolean) tableOffre.getValueAt(row, 4)==false)
	     			{
	     				 
		     				tableOffre.setValueAt("", row, 3);
		     			 
	     			}
	     		
	     		
	     		
	     		
			
	     		
	     			
	     			 	
	     			
  		     	}
			
		}
	});
	tableOffre.setFillsViewportHeight(true);
	tableOffre.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Code MP", "MP","Fournisseur", "Estimation","Select"
		}
	));
	scrollPane_TableOffre.setViewportView(tableOffre);
	
	  scrollPane_TableOffreVariant = new JScrollPane();
	scrollPane_TableOffreVariant.setBounds(1406, 376, 618, 331);
	add(scrollPane_TableOffreVariant);
	
	tableOffreVariant = new JTable();
	tableOffreVariant.setFillsViewportHeight(true);
	tableOffreVariant.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Code MP", "MP", "Fournisseur", "Quantite Offre" ,"Select"
		}
	));
	scrollPane_TableOffreVariant.setViewportView(tableOffreVariant);
	
	
	SubCategorieMp subCategorieMp=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
	
	listFournisseurMP=fournisseurMPDAO.findByCategorie(subCategorieMp);
	for(int j=0;j<listFournisseurMP.size();j++)
	{
		FournisseurMP fournisseurMP=listFournisseurMP.get(j);		
		mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
		
	}
	
	
	tableOffre.setVisible(false);
	
	comboTypeOffre.addItem("");
	comboCondition.addItem("");
	 
	for(int j=0;j<listTypeOffre.size();j++)
	{
		
		TypeOffre typeOffre=listTypeOffre.get(j);
		comboTypeOffre.addItem(typeOffre.getTypeOffre());
		mapTypeOffre.put(typeOffre.getTypeOffre(), typeOffre);
		
		
	}
	
	for(int j=0;j<listCondition.size();j++)
	{
		
		ConditionOffre conditionOffre=listCondition.get(j);
		comboCondition.addItem(conditionOffre.getConditionOffre());
		mapConditionOffre.put(conditionOffre.getConditionOffre(), conditionOffre);
		
		
	}
	

	 
	   
	
	comboTypeOffre.setSelectedItem("");
	comboCondition.setSelectedItem("");
	
	

	comboTypeOffre.setVisible(false);
	comboCondition.setVisible(false);
	lblTypeOffre.setVisible(false);
	lblCondition.setVisible(false);
	
	  btnValiderConditionMixte = new JButton("Valider");
	btnValiderConditionMixte.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
 boolean selected=false;
	    		 
 			 OffreValider=false; 
 			EstimationEnVracCalculer=false; 
 			
 			listDetailEstimationOffreMP.clear();
 			
 			for(int i=0;i<comboConditionChecked.getItemCount();i++)
 			{
 				
 				CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
 				
 				if(checkBoxItem.isSelected()==true)
 				{
 					selected=true;
 					
 					ConditionOffre conditionOffre=mapConditionOffre.get(checkBoxItem.getConditionOffre());
		    			
		    			if(conditionOffre!=null)
		    			{
		    				BigDecimal unite=conditionOffre.getValeur();
		    				
		    				SubCategorieMp subcategorie=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
		    				
		    				if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
		    				{
		    					
		    					
		    					intialiserTableauOffre();  
		    					intialiserTableauOffreVariant();
		  		   			
		  		   			tableOffre.setVisible(true);
		  		   			tableOffreVariant.setVisible(false);
		  		   		scrollPane_TableOffre.setVisible(true);
		  		  	scrollPane_TableOffreVariant.setVisible(false);
		  		   			
		  		   			listMPOffre.clear();
		  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauPF(subcategorie, unite);
		  		   		 
		  		   
		  		   			listDetailEstimationOffreMP.clear();
		  		   			for(int j=0;j<listMPOffre.size();j++)
		  		   			{
		  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
		  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(j));
		  		   			
		  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
		  		   			}
		  		   			
		  		   			
		  		   		afficher_tableOffreMP(listDetailEstimationOffreMP);
		  		   			
		    				}
		    				
		    				
		    				
		    				
		    				
		    				
		    			}
 					
 					
 				}
 				
 			}
 			
 		 if(selected==false)
 		 {
 			 JOptionPane.showMessageDialog(null, "Veillez Cocher un ou plusieurs Condition Pour un Offre PF Variante");
	
 			intialiserTableauOffre();  
			intialiserTableauOffreVariant();
 			
 			tableOffre.setVisible(true);
 			tableOffreVariant.setVisible(false);
 		scrollPane_TableOffre.setVisible(true);
	scrollPane_TableOffreVariant.setVisible(false);
 			 
 		 }else
 		 {
 			offreVarianteValider=true;
 		 }
 			
 			
 			
 			
 	 
 	
 		
 		
 		
 		
 		
 	
			
			
			
		}
	});
	btnValiderConditionMixte.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnValiderConditionMixte.setBounds(1277, 7, 83, 35);
	layeredPane.add(btnValiderConditionMixte);
	
	JLabel lblNewLabel = new JLabel("Offre Variant :");
	lblNewLabel.setBounds(800, 14, 83, 20);
	layeredPane.add(lblNewLabel);
	
	  comboVariant = new JComboBox();
	  comboVariant.setBounds(879, 11, 141, 26);
	  layeredPane.add(comboVariant);
	  comboVariant.addItemListener(new ItemListener() {
	  	public void itemStateChanged(ItemEvent e) {
	  		
	  		
	  		if(e.getStateChange() == ItemEvent.SELECTED) 
		    		
		    		
	    		{
	  			
	  			if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
		  		{
	  				btnValiderConditionMixte.setVisible(true);
	  				comboConditionChecked.setVisible(true);
	  				comboCondition.setVisible(false);
		  			tableOffreVariant.setVisible(true);
		  			tableOffre.setVisible(false);
		  			scrollPane_TableOffre.setVisible(false);
		  			scrollPane_TableOffreVariant.setVisible(true);
		  			 OffreValider=false; 
		    			EstimationEnVracCalculer=false; 
		    			for(int i=0;i<comboConditionChecked.getItemCount();i++)
		    			{
		    				
		    				 CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
		    				
		    				 checkBoxItem.setSelected(false);
		    			}
		    			
		    			/*
		    			ConditionOffre conditionOffre=mapConditionOffre.get(comboCondition.getSelectedItem());
		  			BigDecimal unite=conditionOffre.getValeur();
	  				
	  				SubCategorieMp subcategorie=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
	  				
	  				if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
	  				{
	  					
	  					
	  					intialiserTableauOffreVariant();  			
	  		   			intialiserTableauOffre();
	  		   			
	  		   			listMPOffre.clear();
	  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauPF(subcategorie, unite);
	  		   		 
	  		   
	  		   			listDetailEstimationOffreMP.clear();
	  		   			for(int i=0;i<listMPOffre.size();i++)
	  		   			{
	  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
	  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
	  		   			
	  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
	  		   		
	  		   			}
	  		   			
	  		   			
	  		   		afficher_tableOffreMPVariant (listDetailEstimationOffreMP);
	  		   			
	  				}
		  			
		  			*/
		  			
		  			
		  		}else if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_NORMAL))
		  		{
		  			
		  			btnValiderConditionMixte.setVisible(false);
		  			comboConditionChecked.setVisible(false);
		  			comboCondition.setVisible(true);
		  			for(int i=0;i<comboConditionChecked.getItemCount();i++)
	    			{
	    				
	    				 CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
	    				
	    				 checkBoxItem.setSelected(false);
	    			}
		  			tableOffreVariant.setVisible(false);
		  			tableOffre.setVisible(false);
		  			scrollPane_TableOffre.setVisible(false);
		  			scrollPane_TableOffreVariant.setVisible(false);
		  			 OffreValider=false; 
		    			EstimationEnVracCalculer=false; 
		    			
		    			
		    			
		    			/*
		    			ConditionOffre conditionOffre=mapConditionOffre.get(comboCondition.getSelectedItem());
		  			BigDecimal unite=conditionOffre.getValeur();
	  				
	  				SubCategorieMp subcategorie=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
	  				
	  				if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
	  				{
	  					
	  					
	  					intialiserTableauOffreVariant();  			
	  		   			intialiserTableauOffre();
	  		   			
	  		   			listMPOffre.clear();
	  		   			listMPOffre=matierePremiereDAO.listeMatiereCadeauPF(subcategorie, unite);
	  		   		 
	  		   
	  		   			listDetailEstimationOffreMP.clear();
	  		   			for(int i=0;i<listMPOffre.size();i++)
	  		   			{
	  		   				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
	  		   			detailEstimationPromo.setMatierePremiere(listMPOffre.get(i));
	  		   			
	  		   		listDetailEstimationOffreMP.add(detailEstimationPromo);
	  		   		
	  		   			}
	  		   			
	  		   			
	  		   		afficher_tableOffreMPVariant (listDetailEstimationOffreMP);
	  		   			
	  				}
		  			
		  			
		  			*/
		  			
		  		
		  			
		  			
		  			
		  		}else
		  		{
		  			btnValiderConditionMixte.setVisible(false);
		  			intialiserTableauOffreVariant();  			
  		   			intialiserTableauOffre();
		  			tableOffreVariant.setVisible(false);
		  			scrollPane_TableOffre.setVisible(true);
		  			scrollPane_TableOffreVariant.setVisible(false);
		  			tableOffre.setVisible(true);
		  			comboConditionChecked.setVisible(false);
		  			comboCondition.setVisible(false);
		  			for(int i=0;i<comboConditionChecked.getItemCount();i++)
	    			{
	    				
	    				 CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
	    				
	    				 checkBoxItem.setSelected(false);
	    			}
		  			
		  			
		  			
		  		}
	  			
	    		}
	  		
	  
	  		
	  		
	  		
	  	}
	  });
	  
	  comboVariant.addItem("");
	  comboVariant.addItem(Constantes.OFFRE_VARIANT);
	  comboVariant.addItem(Constantes.OFFRE_NORMAL);
	  comboVariant.setSelectedItem("");
	  
	  JLabel lblSousCategorie = new JLabel("Sous Categorie  :");
	  lblSousCategorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  lblSousCategorie.setBounds(789, 84, 105, 24);
	  layeredPane.add(lblSousCategorie);
	  
	   comboSousCategorie = new JComboBox();
	   comboSousCategorie.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent arg0) {
	   		if(!comboSousCategorie.getSelectedItem().equals(""))
	   		{
	   			listFormeParBox.clear();
	   			comboForme.removeAllItems();
	   			comboForme.addItem("");
	   			mapFormeParBox.clear();
	   			SubCategorieMp subCategorieMp=mapSubcategorie.get(comboSousCategorie.getSelectedItem().toString());
	   			if(subCategorieMp!=null) {
	   				listFormeParBox=formeParBoxDAO.findBySubCategorie(subCategorieMp);
	   				for(int i=0;i<listFormeParBox.size();i++)
	   				{
	   					FormeParBox formeParBox=listFormeParBox.get(i);
	   					comboForme.addItem(formeParBox.getForme().getLibelle());
	   					mapFormeParBox.put(formeParBox.getForme().getLibelle(), formeParBox);
	   					
	   				}
	   				
	   				comboForme.setSelectedItem("");
	   				
	   			}else
	   			{
	   				listFormeParBox.clear();
		   			comboForme.removeAllItems();
		   			comboForme.addItem("");
		   			mapFormeParBox.clear();
		   			comboForme.setSelectedItem("");
	   			}
	   			
	   		}else
	   		{
	   			listFormeParBox.clear();
	   			comboForme.removeAllItems();
	   			comboForme.addItem("");
	   			mapFormeParBox.clear();
	   			comboForme.setSelectedItem("");
		   		
	   			
	   		}
	   	}
	   });
	  comboSousCategorie.setBounds(895, 84, 196, 24);
	  layeredPane.add(comboSousCategorie);
	  comboSousCategorie.setEnabled(false);
	  lblForme = new JLabel("Forme  :");
	  lblForme.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  lblForme.setBounds(1101, 84, 68, 24);
	  layeredPane.add(lblForme);
	  
	  comboForme = new JComboBox();
	  comboForme.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  if(comboForme.getSelectedItem()!=null)
	  {
		  

	  		if(!comboForme.getSelectedItem().toString().equals(""))
	  		{
	  			
	  			FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
	  			if(formeParBox!=null)
	  			{
	  				
	  				System.out.println(formeParBox.getSubCategorieMp().getNom() + "----"+ formeParBox.getForme().getLibelle());
	  			}
	  		}
	  }
	  	}
	  });
	  comboForme.setBounds(1179, 84, 181, 24);
	  layeredPane.add(comboForme);
	comboVariant.setVisible(false);
	 
	 
	tableOffre.setVisible(false);
	tableOffreVariant.setVisible(false);
	scrollPane_TableOffre.setVisible(false);
	scrollPane_TableOffreVariant.setVisible(false);
	
	 ChargerSousCategorieBox();
	
	}
	
	
	
	void ChargerSousCategorieBox()
	{
		comboSousCategorie.removeAllItems();
		comboSousCategorie.addItem("");
		listSousCategorie=SubCategorieMPDAO.findAll();
		comboForme.addItem("");
		for(int t=0;t<listSousCategorie.size();t++)
		{
			
			if(listSousCategorie.get(t).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || listSousCategorie.get(t).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE) )
			{
			comboSousCategorie.addItem(listSousCategorie.get(t).getNom());	
			mapSubcategorie.put(listSousCategorie.get(t).getNom(), listSousCategorie.get(t));
				
			}
			
			
		}
		
		comboSousCategorie.setSelectedItem("");
		comboForme.setSelectedItem("");
		
	}
	
	
	
	
	void intialiser()
	{
		quantite.setText("");
		codeArticle.setText("");
		categorie.setSelectedItem("");
		
		
		
	}
void 	intialiserTableau(){
		
		modeleMP =new DefaultTableModel(
  		     	new Object[][] {
  		     	},
  		     	new String[] {
  		     			"Code","Nom Matière Première","fournisseur", "Quantité Calculée", "Quantité Existante", "Quantité Manquante", "Quantité A Chargée","Quantite Total Charge","Manque/Plus"
  		     	}
  		     ) {
  		     	boolean[] columnEditables = new boolean[] {
  		     			false,false,false, false,false,false,true,true,true
  		     	};
  		     	public boolean isCellEditable(int row, int column) {
  		     		return columnEditables[column];
  		     	}
  		     };
  		   table.setModel(modeleMP);
  		   table.getColumnModel().getColumn(0).setPreferredWidth(60);
  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
  		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
  		   table.getColumnModel().getColumn(4).setPreferredWidth(160);
  		   table.getColumnModel().getColumn(5).setPreferredWidth(160);
  		 table.getColumnModel().getColumn(6).setPreferredWidth(160);
  		 table.getColumnModel().getColumn(7).setPreferredWidth(160);
  		table.getColumnModel().getColumn(8).setPreferredWidth(160);
  		 table.getTableHeader().setReorderingAllowed(false);
  		 
  		  TableColumn MoisPlusColumn = table.getColumnModel().getColumn(8);
		   JComboBox comboBox = new JComboBox();
		   comboBox.addItem("");
		   comboBox.addItem(MANQUE_PLUS);
		   comboBox.addItem(MANQUE_MOINS);
		   
		   MoisPlusColumn.setCellEditor(new DefaultCellEditor(comboBox));  
	}



void 	intialiserTableauOffre(){
	
	modeleMPOffre =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP", "MP","Fournisseur", "Estimation","Select"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true,true,true
		     	};
		     	
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class,String.class,BigDecimal.class, Boolean.class
					};
		      	
			       public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
		     	
		     	public boolean isCellEditable(int row, int column) {
		     		
		     		int y=0;
		     		TypeOffre typeOffre=mapTypeOffre.get(comboTypeOffre.getSelectedItem());	
		     		boolean offreSpeciale=false;
		     		MatierePremier mp=matierePremiereDAO.findByCode(tableOffre.getValueAt(row, 0).toString());
    				
    				if(typeOffre!=null)
    				{
    					
    					if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
    					{
    						
    						for(int t=0;t<tableOffre.getRowCount();t++)
    			     		{
    			     			
    			     			if((boolean)(tableOffre.getValueAt(t, 4))==true)
    			     			{
    			     				y=y+1;
    			     			}
    			     			
    			     			
    			     			
    			     			
    			     		}
    						
    					}else if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_SPECIALE))
    					{
    						offreSpeciale=true;
    					}
	     		
    				}
		     	
		     		
		     	 
		     		
		     	
		     		
		     		if((column==3 && y>=1 ) || (column==3 && (boolean)(tableOffre.getValueAt(row, 4))==false) || (column==3 && offreSpeciale==true && (mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) || mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))))
		     		{
		     			 
		     			
		     			
		     			
		     			
		     			return false;
		     		  
		     		}else if(column==3 && typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES_PF) && (boolean)(tableOffre.getValueAt(row, 4))==true && mp.getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
		     		{
		     			return false;
		     		}else if(column==3 && typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES_PF) && (boolean)(tableOffre.getValueAt(row, 4))==false && (mp.getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF) || mp.getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES)))
		     		{
		     			return false;
		     		}
		     		
		     		
		     		else
		     		
		     		return columnEditables[column];
		     		
		     		
		     	}
		     };
		   tableOffre.setModel(modeleMPOffre);
		   tableOffre.getColumnModel().getColumn(0).setPreferredWidth(60);
		   tableOffre.getColumnModel().getColumn(1).setPreferredWidth(260);
		   tableOffre.getColumnModel().getColumn(2).setPreferredWidth(60);
		   tableOffre.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableOffre.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableOffre.getTableHeader().setReorderingAllowed(false);
		 
		    
}



void 	intialiserTableauOffreVariant(){
	
	modeleMPOffreVariant =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP", "MP", "Fournisseur", "Quantite Offre" ,"Select"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true,true,true 
		     	};
		     	 
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class,String.class,BigDecimal.class, Boolean.class
					};
		      	
			       public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
		     	
		     	public boolean isCellEditable(int row, int column) {
		     		
		     	 
		     		return columnEditables[column];
		     		
		     		
		     	}
		     };
		   tableOffreVariant.setModel(modeleMPOffreVariant);
		   tableOffreVariant.getColumnModel().getColumn(0).setPreferredWidth(60);
		   tableOffreVariant.getColumnModel().getColumn(1).setPreferredWidth(260);
		   tableOffreVariant.getColumnModel().getColumn(2).setPreferredWidth(60);
		   tableOffreVariant.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableOffreVariant.getColumnModel().getColumn(4).setPreferredWidth(60);
		 
		   tableOffreVariant.getTableHeader().setReorderingAllowed(false);
		 
		    
}



void 	intialiserTableauFournisseurMPMixte(){
	
	JComboBox jcombobox=new JComboBox();
	
	jcombobox.addItem("");
	  
	  for(int i=0;i<listFournisseurMP.size();i++) {
	  
	  FournisseurMP fournisseurMP= listFournisseurMP.get(i);
	  
	  jcombobox.addItem(fournisseurMP.getCodeFournisseur());
	  
	  }



jcombobox.setEditable(false);
final ListModel<CheckableItem> model=jcombobox.getModel();
	
	modeleMPMixte =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","MP", "Fournisseur","ESTIMATION"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, true,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   tablempfournisseurmixte.setModel(modeleMPMixte);
		   tablempfournisseurmixte.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jcombobox));
		   tablempfournisseurmixte.getColumnModel().getColumn(0).setPreferredWidth(60);
		   tablempfournisseurmixte.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tablempfournisseurmixte.getColumnModel().getColumn(2).setPreferredWidth(60);
		   tablempfournisseurmixte.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tablempfournisseurmixte.getTableHeader().setReorderingAllowed(false);
}



void afficher_tableCouMP(List<CoutMP> listCoutMP)
{
	intialiserTableau();

	mapMatierePremierDetailEstimation.clear();
	  for(int i=0;i<listCoutMP.size();i++){
		{	
			CoutMP coutMP=listCoutMP.get(i);
			String fournisseurMP="";
			if(coutMP.getFournisseurMP()!=null)
			{
				fournisseurMP=coutMP.getFournisseurMP().getCodeFournisseur();
			}
			
		 
				 
					
					Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),fournisseurMP,coutMP.getQuantite()+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);
					
				 
				 
			
		}
	  }	
}



void afficher_tableOffreMP(List<DetailEstimationPromo> listDetailestimationOffreMP)
{
	intialiserTableauOffre();;

	  String fournisseur="";
	  for(int i=0;i<listDetailestimationOffreMP.size();i++){
		{	
			fournisseur="";
			DetailEstimationPromo detailEstimationPromo=listDetailestimationOffreMP.get(i);
			if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) ) 
			{
				fournisseur=detailEstimationPromo.getFournisseur().getCodeFournisseur();;
			}
			
			
			
			
			if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) || detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) 
			{
				
				if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) ) 
				{
					mapMPOffre.put(detailEstimationPromo.getMatierePremiere().getCode()+" "+detailEstimationPromo.getFournisseur().getCodeFournisseur(), detailEstimationPromo.getMatierePremiere());
				}else
				{
					mapMPOffre.put(detailEstimationPromo.getMatierePremiere().getCode(), detailEstimationPromo.getMatierePremiere());
				}
				
				
				
				Object [] ligne={detailEstimationPromo.getMatierePremiere().getCode() , detailEstimationPromo.getMatierePremiere().getNom(),fournisseur ,"", true};
				modeleMPOffre.addRow(ligne);
				
			}else
				
			{
				
				mapMPOffre.put(detailEstimationPromo.getMatierePremiere().getCode(), detailEstimationPromo.getMatierePremiere());
				
				Object [] ligne={detailEstimationPromo.getMatierePremiere().getCode() , detailEstimationPromo.getMatierePremiere().getNom(),fournisseur ,"", false};
				modeleMPOffre.addRow(ligne);
				
				
			}
			
			
		}
		
		
		
		
	  }	
}


void afficher_tableOffreMPVariant(List<DetailEstimationPromo> listDetailestimationOffreMP)
{
	intialiserTableauOffreVariant(); 

	  String fournisseur="";
	  for(int i=0;i<listDetailestimationOffreMP.size();i++){
		{	
			fournisseur="";
			DetailEstimationPromo detailEstimationPromo=listDetailestimationOffreMP.get(i);
			if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) ) 
			{
				fournisseur=detailEstimationPromo.getFournisseur().getCodeFournisseur();;
			}
			
			
			
			
		 
				
				mapMPOffre.put(detailEstimationPromo.getMatierePremiere().getCode(), detailEstimationPromo.getMatierePremiere());
				
				Object [] ligne={detailEstimationPromo.getMatierePremiere().getCode() , detailEstimationPromo.getMatierePremiere().getNom(),fournisseur ,"", false};
				modeleMPOffreVariant.addRow(ligne);
				
				
			 
			
			
		}
		
		
		
		
	  }	
}




void afficher_tableMPMixteFournisseur(List<DetailEstimation> listDetailEstimation)
{
	intialiserTableauFournisseurMPMixte();;

	  
	  for(int i=0;i<listDetailEstimation.size();i++){
			
			DetailEstimation detailEstimation=listDetailEstimation.get(i);
			
			Object [] ligne={detailEstimation.getMatierePremier().getCode(),detailEstimation.getMatierePremier().getNom(),"",""};
			modeleMPMixte.addRow(ligne);
			
		
		
		
	  }	
	  
	  tablempfournisseurmixte.setModel(modeleMPMixte);
}



boolean afficherTableMatierePremiere(List<DetailEstimation> lisDetailEstimation){
	
	mapQuantiteRestereMatierePremier.clear();
	
	creerOF=true;
	BigDecimal prix_unitaire = BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	BigDecimal quantiteACharge=BigDecimal.ZERO;
	BigDecimal quantiteAChargeTHE=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO;
	BigDecimal quantiteManqaunte=BigDecimal.ZERO;
	BigDecimal quantiteCarton=BigDecimal.ZERO;
	BigDecimal quantiteStock=BigDecimal.ZERO;
	intialiserTableau();
	listCoutMP =new ArrayList<CoutMP>();
	StockMP stockMP=new StockMP();
	mapQauntiteMatierePremier = new HashMap<>();
	StockMP stockMPMagasinProd=new StockMP();
	
	StockMP stockMPQauantiteManquante=new StockMP();
	CoutMP coutMP=new CoutMP();
	Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
	Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
	intialiserTableau();
	int j=0;
	
	BigDecimal NombreBox=BigDecimal.ZERO;
	
	detailTransferStockMPDAO.ViderSession();
	listEtatStockMPAfficher.clear();
	listEtatStockMPAfficherMagasinStockage.clear();
	listEtatStockMPAfficherMagasinProduction.clear();
	Date dateDebutPrevue=dateDebutChooser.getDate();
	CalculerStockFinale(magasinProd, dateDebutPrevue);
	listEtatStockMPAfficherMagasinProduction.addAll(listEtatStockMPAfficher);
	boolean BoxClientExiste=false;
	CalculerStockFinale(magasinStockage, dateDebutPrevue);
	listEtatStockMPAfficherMagasinStockage.addAll(listEtatStockMPAfficher);
	
	
	for(int k=0;k<lisDetailEstimation.size();k++)
	{
		DetailEstimation detailEstimation=lisDetailEstimation.get(k);
		if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
				{
			
			quantiteCarton=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite() , 0,RoundingMode.FLOOR);
			
			
				}
		
		if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
		{
			
		NombreBox=NombreBox.add(BigDecimal.ONE)	;
		
		
		if(detailEstimation.getMatierePremier().getType()!=null) 
		{
		
		if(detailEstimation.getMatierePremier().getType().equals(Constantes.MP_CLIENT))
		{
			BoxClientExiste=true;
		}
		
		}
		
			
		}
		
		
		
		
		
		
		
	}
	
	

BigDecimal rest=BigDecimal.ZERO;
	
	for(int i=0;i<lisDetailEstimation.size();i++){
		
		rest=BigDecimal.ZERO;
		
		quantiteExistante=BigDecimal.ZERO;
		
		 stockMPQauantiteManquante=new StockMP();
			DetailEstimation detailEstimation=lisDetailEstimation.get(i);
			if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF))
			{
				if(detailEstimation.getMatierePremier().getCode().contains("C"))
				{
					
					if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
					{
						quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
						
						
					}else
					{
						
						quantiteTotal=detailEstimation.getQuantite().multiply(quantiteCarton);
						
					}
					
				
				}else
				{
					
					
					if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
					{
						quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
						
						
					}else
					{
						
						quantiteTotal=detailEstimation.getQuantite().multiply(quantiteCarton);
						
					}
					
					
				}
				
				
				
				
				
				
			}else
			{
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC))
				{
					
					if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
					{
						 
						if(NombreBox.compareTo(BigDecimal.ONE)==1)
						{
							if(BoxClientExiste==true)
							{
								

								if(detailEstimation.getMatierePremier().getCode().contains("C"))
								{
									
									if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
									{
										quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
										
										
									}else
									{
										
										quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);	
										
									}
									
								
								}else
								{
									
									
									if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
									{
										quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
										
										
									}else
									{
										
										quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);	
										
									}
									
									
								}
								
								
								
							}else
							{
								quantiteTotal=(new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 6, RoundingMode.FLOOR).divide(NombreBox, 6, RoundingMode.FLOOR)).setScale(0, RoundingMode.FLOOR);	

							}
							
							
							
							
							
						}else
						{
						 
						
						
							
							
							if(detailEstimation.getMatierePremier().getCode().contains("C"))
							{
								
								if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
								{
									quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
									
									
								}else
								{
									
									quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);	
									
								}
								
							
							}else
							{
								
								
								if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
								{
									quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
									
									
								}else
								{
									
									quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);	
									
								}
								
								
							}
							
							
							
							
					 	
					}
					 	
					}else if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) &&  !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
					{
						
						

						if(detailEstimation.getMatierePremier().getCode().contains("C"))
						{
							
							if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
							{
								quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
								
								
							}else
							{
								
								quantiteTotal=new BigDecimal(quantite.getText()).multiply(detailEstimation.getQuantite());		
								
							}
							
						
						}else
						{
							
							
							if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
							{
								quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
								
								
							}else
							{
								
								quantiteTotal=new BigDecimal(quantite.getText()).multiply(detailEstimation.getQuantite());		
								
							}
							
							
						}
						
					}else
					{
						
					

						if(detailEstimation.getMatierePremier().getCode().contains("C"))
						{
							
							if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
							{
								quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
								
								
							}else
							{
								
								quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);			
								
							}
							
						
						}else
						{
							
							
							if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
							{
								quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
								
								
							}else
							{
								
								quantiteTotal=new BigDecimal(quantite.getText()).divide(detailEstimation.getQuantite(), 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);		
								
							}
							
							
						}
					
					}
					
				
					
					
					
				}else
				{
					
					
					
					if(detailEstimation.getMatierePremier().getCode().contains("C"))
					{
						
						if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim())!=null)
						{
							quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode().replace("C", "").trim());
							
							
						}else
						{
							
							quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));		
							
						}
						
					
					}else
					{
						
						
						if(mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C" )!=null)
						{
							quantiteTotal=mapQuantiteRestereMatierePremier.get(detailEstimation.getMatierePremier().getCode()+"C");
							
							
						}else
						{
							
							quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));	
							
						}
						
						
					}
					
				}
				
				
				
			}
			
			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				 coutMP= new CoutMP();
				
				stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinStockage.getId());
				stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
				
				for(int d=0;d<listEtatStockMPAfficherMagasinProduction.size();d++)
				{
					if(listEtatStockMPAfficherMagasinProduction.get(d).getMp().getId()==detailEstimation.getMatierePremier().getId())
					{
						
						if(listEtatStockMPAfficherMagasinProduction.get(d).getFournisseurMP()==null)
						{
							
							
							quantiteExistante=listEtatStockMPAfficherMagasinProduction.get(d).getQuantiteFinale();
							
							
							
						}
						
						
						
					}
					
					
					
					
					
				}
				
				
				for(int d=0;d<listEtatStockMPAfficherMagasinStockage.size();d++)
				{
					if(listEtatStockMPAfficherMagasinStockage.get(d).getMp().getId()==detailEstimation.getMatierePremier().getId())
					{
						
						if(listEtatStockMPAfficherMagasinStockage.get(d).getFournisseurMP()==null)
						{
							
							
					 if(listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale().compareTo(quantiteTotal)>=0)
						{
							
							rest=BigDecimal.ZERO;
							
							mapQuantiteRestereMatierePremier.put(listEtatStockMPAfficherMagasinStockage.get(d).getMp().getCode(), rest);
							
						}
							
					 
						if(quantiteTotal.compareTo(listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale()) >0)
						{
							
						rest=quantiteTotal.subtract(listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale());
							
						mapQuantiteRestereMatierePremier.put(listEtatStockMPAfficherMagasinStockage.get(d).getMp().getCode(), rest);
						
						quantiteTotal=listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale();
						
						}
					 
							
							
						}
						
						
						
					}
					
					
					
					
					
				}
				
				
				if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))!=null)
				{
					if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)!=0)
					{
						prix_unitaire=detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()));
					}else
					{
						prix_unitaire=BigDecimal.ZERO;
					}
					
					
				}else
				{
					prix_unitaire=BigDecimal.ZERO;
				}
				
				quantiteACharge=quantiteTotal.subtract(quantiteExistante);
			}
			
			
			
			
			
			if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
				quantiteACharge=BigDecimal.ZERO;
		
		if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
		
			quantiteACharge=(BigDecimal)(quantiteACharge);
			
		} 
		if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
			
			/*
			if(stockMP!=null){
				*/
				
				coutMP.setMatierePremier(detailEstimation.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantEstime(quantiteACharge);
				coutMP.setEstimation(detailEstimation.getQuantite());
				coutMP.setProdcutionCM(production);
				listCoutMP.add(coutMP);
			//	listCoutMP.add(coutMP);
				/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
				modeleMP.addRow(ligne);*/
				/*}*/
		
		}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
			MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
			if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
			
				/*
				if(stockMP!=null){
					*/
					if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
						if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
							quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
					
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setEstimation(detailEstimation.getQuantite());
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
				/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				/*}*/
				
			
				
			}
		}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			
		if(!chckbxArticleMixte.isSelected()){
			
			for(int d=0;d<tablempfournisseurmixte.getRowCount();d++)
			{
				if(!tablempfournisseurmixte.getValueAt(d, 2).equals(""))
				{
					
						
						
						MatierePremier matierePremiere=mapMatierePremier.get(tablempfournisseurmixte.getValueAt(d, 1).toString());
						FournisseurMP fournisseurMP=mapFournisseurMP.get(tablempfournisseurmixte.getValueAt(d, 2).toString());
						
							stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP (detailEstimation.getMatierePremier().getId(),magasinStockage.getId(),fournisseurMP.getId());
							stockMPMagasinProd=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId(),fournisseurMP.getId());
							
							 
								quantiteTotal=new BigDecimal(quantite.getText());
							 
							
						
							for(int t=0;t<listEtatStockMPAfficherMagasinProduction.size();t++)
							{
								if(listEtatStockMPAfficherMagasinProduction.get(t).getMp().getId()==detailEstimation.getMatierePremier().getId())
								{
									
									if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP()!=null)
									{
										if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP().getId()==fournisseurMP.getId())
										{
											quantiteExistante=listEtatStockMPAfficherMagasinProduction.get(t).getQuantiteFinale();
										}
										
										
									}
									
									
									
								}
								
								
								
								
								
							}
							
							
							if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))!=null)
							{
								if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)!=0)
								{
									prix_unitaire=detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()));
								}else
								{
									prix_unitaire=BigDecimal.ZERO;
								}
								
								
							}else
							{
								prix_unitaire=BigDecimal.ZERO;
							}
							quantiteACharge=quantiteTotal.subtract(quantiteExistante);
							
							
							
							if(detailEstimation.getMatierePremier().getId()== matierePremiere.getId()){
								
								/*
								if(stockMP!=null){
								*/
									coutMP= new CoutMP();
									coutMP.setMatierePremier(detailEstimation.getMatierePremier());
									coutMP.setPrixUnitaire(prix_unitaire);
									coutMP.setQuantite(quantiteTotal);
									coutMP.setQuantExistante(quantiteExistante);
									coutMP.setQuantEstime(quantiteACharge);
									coutMP.setEstimation(BigDecimal.ZERO);
									coutMP.setFournisseurMP(fournisseurMP);
									coutMP.setProdcutionCM(production);
									listCoutMP.add(coutMP);
								//	listCoutMP.add(coutMP);
									/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
									modeleMP.addRow(ligne);*/
								/*}*/
							}
							
						
				
					
					
					
					
					
				}
				
				
				
				
			}
			
		
	
		}else if(chckbxArticleMixte.isSelected()){
			
			/*
			if(detailEstimation.getPriorite()==2){
				*/
				
				for(int d=0;d<tablempfournisseurmixte.getRowCount();d++)
				{
					if(!tablempfournisseurmixte.getValueAt(d, 2).equals(""))
					{
						
							
							MatierePremier matierePremiere=mapMatierePremier.get(tablempfournisseurmixte.getValueAt(d, 1).toString());
							FournisseurMP fournisseurMP=mapFournisseurMP.get(tablempfournisseurmixte.getValueAt(d, 2).toString());
							
							stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP (detailEstimation.getMatierePremier().getId(),magasinStockage.getId(),fournisseurMP.getId());
							stockMPMagasinProd=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId(),fournisseurMP.getId());
							
							
						 
								quantiteTotal=BigDecimal.ZERO;
						 
							
							for(int t=0;t<listEtatStockMPAfficherMagasinProduction.size();t++)
							{
								if(listEtatStockMPAfficherMagasinProduction.get(t).getMp().getId()==detailEstimation.getMatierePremier().getId())
								{
									
									if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP()!=null)
									{
										if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP().getId()==fournisseurMP.getId())
										{
											quantiteExistante=listEtatStockMPAfficherMagasinProduction.get(t).getQuantiteFinale();
										}
										
										
									}
									
									
									
								}
								
								
								
								
								
							}
							
							
							if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))!=null)
							{
								if(detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)!=0)
								{
									prix_unitaire=detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()));
								}else
								{
									prix_unitaire=BigDecimal.ZERO;
								}
								
								
							}else
							{
								prix_unitaire=BigDecimal.ZERO;
							}
							quantiteACharge=quantiteTotal.subtract(quantiteExistante);
							
							
							
							if(detailEstimation.getMatierePremier().getId()==matierePremiere.getId() ){
								
								/*
								if(stockMP!=null){
								*/
									coutMP= new CoutMP();
									coutMP.setMatierePremier(detailEstimation.getMatierePremier());
									coutMP.setPrixUnitaire(prix_unitaire);
									coutMP.setQuantite(quantiteTotal);
									coutMP.setQuantExistante(quantiteExistante);
									coutMP.setQuantEstime(BigDecimal.ZERO);
									coutMP.setEstimation(BigDecimal.ZERO);
									coutMP.setFournisseurMP(fournisseurMP);
									coutMP.setProdcutionCM(production);
									listCoutMP.add(coutMP);
								//	listCoutMP.add(coutMP);
									/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
									modeleMP.addRow(ligne);*/
								/*}*/
							}
							
							
							
						
						
						
						
						
					}
					
					
					
					
				}
				
				
				
				
				/*
				
				if(stockMP!=null){
					
					for(int d=0;d<tablempfournisseurmixte.getRowCount();d++)
		  				{
						
						if(detailEstimation.getMatierePremier().getCode().equals(tablempfournisseurmixte.getValueAt(d, 0).toString()))
						{
							FournisseurMP fournisseurMP=mapFournisseurMP.get(tablempfournisseurmixte.getValueAt(d, 2).toString());
							if(fournisseurMP!=null)
							{
								coutMP.setFournisseurMP(fournisseurMP);
							}
							
						}

	
		  				}
					
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
					*/
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
					/*
				}
				
				*/
				
				
			/*	
			}*/
			
		}
		}		
		
		
	}
	
	
	//remplirQuantiteOffreMP(listCoutMP);
	afficher_tableCouMP(listCoutMP);
	return true;
	
}



boolean afficherTableMatierePremiereCreerOF(List<CoutMP> listCoutMP){
	creerOF=true;
	 
	if(!remplirMapQuantiteCharge()){
		creerOF=false;
		JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantité avant de créer l'OF", "Erreur", JOptionPane.ERROR_MESSAGE);
	}else {
	
	 
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	BigDecimal quantiteACharge=BigDecimal.ZERO;
	BigDecimal quantiteAChargeTHE=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO;
	BigDecimal quantiteManqaunte=BigDecimal.ZERO;
	BigDecimal quantiteStock=BigDecimal.ZERO;
	List listCoutMPTmp =new ArrayList<CoutMP>();
	 StockMP stockMP=new StockMP();
	mapQauntiteMatierePremier = new HashMap<>();
	 StockMP stockMPMagasinProd=new StockMP();
	
	BigDecimal reste=BigDecimal.ZERO;
	
    StockMP stockMPQauantiteManquante=new StockMP();
	CoutMP coutMP=new CoutMP();
	Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
	Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
	
	Date dateDebutPrevue=dateDebutChooser.getDate();
	CalculerStockFinale(magasinProd, dateDebutPrevue);
	listEtatStockMPAfficherMagasinProduction.addAll(listEtatStockMPAfficher);
	CalculerStockFinale(magasinStockage, dateDebutPrevue);
	listEtatStockMPAfficherMagasinStockage.addAll(listEtatStockMPAfficher);
	
	
	// Calculer Quantite Total Charger En Vrac
	BigDecimal QuantiteTotalEnvracCharger=BigDecimal.ZERO;
	for(int i=0;i<listCoutMP.size();i++){
		
	CoutMP	 coutMPTmp= listCoutMP.get(i);
		  
			
			if(coutMPTmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				
				QuantiteTotalEnvracCharger=QuantiteTotalEnvracCharger.add(mapQuantiteMP.get(coutMPTmp.getMatierePremier().getCode()+"_"+coutMPTmp.getFournisseurMP().getCodeFournisseur()).add(mapQuantiteMPExistante.get(coutMPTmp.getMatierePremier().getCode()+"_"+coutMPTmp.getFournisseurMP().getCodeFournisseur()))) ;
				 
				
				
			}
			
			
			
	}
	
	
	if(QuantiteTotalEnvracCharger.compareTo(BigDecimal.ZERO)==0)
	{
		JOptionPane.showMessageDialog(null, "Quantite Charger En Vrac Doit etre Diffrent De zero","Erreur",JOptionPane.ERROR_MESSAGE);
		creerOF=false;
	}
	
	
	
	//intialiserTableau();
	int j=0;
	for(int i=0;i<listCoutMP.size();i++){
		
		reste=BigDecimal.ZERO;
		
		 coutMP= listCoutMP.get(i);
		  stockMPQauantiteManquante=new StockMP();
		 quantiteStock=BigDecimal.ZERO;
		 quantiteExistante=BigDecimal.ZERO;
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				
				quantiteTotal=mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur());
				
				coutMP.setEstimation((mapQuantiteMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).add(mapQuantiteMPExistante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()))).divide(QuantiteTotalEnvracCharger, 6, RoundingMode.HALF_UP)) ;

				
				
					 //stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP (coutMP.getMatierePremier().getId(),magasinStockage.getId(),coutMP.getFournisseurMP().getId());
					 //stockMPMagasinProd=stockMPDAO.findStockByMagasinMPByFournisseurMP(coutMP.getMatierePremier().getId(),magasinProd.getId(),coutMP.getFournisseurMP().getId());
				
					 
					
					//// l'envrac contient Manque ou Plus 
				if(table.getValueAt(i, 8)!=null)
				{
					if(!table.getValueAt(i, 8).equals(""))
					{
						
						coutMP.setMoinsPlus(table.getValueAt(i,8).toString());
						
						
					}
					
					
					
					
				}
				
				
			}else
			{
				quantiteTotal=mapQuantiteMP.get(coutMP.getMatierePremier().getCode());
				// stockMP=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinStockage.getId());
				 //stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinProd.getId());
			}
			 
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				
				if(mapStockFinaleMagasinProduction.get(coutMP.getMatierePremier().getCode()+" "+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
				{
					quantiteExistante=mapStockFinaleMagasinProduction.get(coutMP.getMatierePremier().getCode()+" "+coutMP.getFournisseurMP().getCodeFournisseur()).getQuantiteFinale();
				}
				
				
				if(mapStockFinaleMagasinStiockage.get(coutMP.getMatierePremier().getCode()+" "+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
				{
					quantiteStock=mapStockFinaleMagasinStiockage.get(coutMP.getMatierePremier().getCode()+" "+coutMP.getFournisseurMP().getCodeFournisseur()).getQuantiteFinale();
				}
				
				
			}else
			{
				
				if(mapStockFinaleMagasinProduction.get(coutMP.getMatierePremier().getCode())!=null)
				{
					quantiteExistante=mapStockFinaleMagasinProduction.get(coutMP.getMatierePremier().getCode()).getQuantiteFinale();
				}
				
				
				if(mapStockFinaleMagasinStiockage.get(coutMP.getMatierePremier().getCode())!=null)
				{
					quantiteStock=mapStockFinaleMagasinStiockage.get(coutMP.getMatierePremier().getCode()).getQuantiteFinale();
				}
				
			}
			
			
			
			
			 
			
			quantiteACharge=quantiteTotal;
		
			
			if(quantiteACharge.compareTo(BigDecimal.ZERO)<0)
			{
				quantiteACharge=BigDecimal.ZERO;
			}
				
			
			if(quantiteACharge.compareTo(quantiteStock)>0){
				
								quantiteManqaunte=quantiteACharge.subtract(quantiteStock);
								stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								stockMPQauantiteManquante.setStock(quantiteManqaunte);
								stockMPQauantiteManquante.setMagasin(magasinStockage);
								creerOF=false;
								nomMP+="-"+coutMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
								mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
								j++;
		
				
				
				
				
			}
			if(   quantiteStock.compareTo(quantiteACharge)>=0){
				
				coutMP.setQuantCharge(quantiteACharge);
				 
				
				coutMP.setProdcutionCM(production);
				
				listCoutMPTmp.add(coutMP);
				Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantite() +" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),quantiteTotal};
				modeleMP.addRow(ligne);
			}
	}
	
	intialiserTableau();
	
	production.setListCoutMP(listCoutMPTmp);
	}
	return creerOF;
	
}

 void compterProduction(Date dateProd,String periode){
	 int compteur=0;
	 CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(dateProd,periode);
	 
	 if(compteurProduction !=null){
		 compteur=compteurProduction.getCompteur()+1;
		 compteurProduction.setCompteur(compteur);	
		 compteurProductionDAO.edit(compteurProduction);
		 
	 }else{
		 compteurProduction= new CompteurProduction();
		 compteurProduction.setDateProd(dateProd);
		 compteurProduction.setPeriode(periode);
		 compteurProduction.setCompteur(1);
		 compteurProductionDAO.add(compteurProduction);
		 
	 }
	 
 }

 
 void creerStockProduitFini(){
	 Articles article =mapAricle.get(categorie.getSelectedItem());
	 Magasin magasin =magasinStockagePF.get(comboMagasinPF.getSelectedItem());
	 StockPF stockPF = stockPFDAO.findStockByMagasinPF(article.getId(),magasin.getId());
	 
	 if(stockPF==null){
		 stockPF=new StockPF();
		 stockPF.setArticles(article);
		 stockPF.setMagasin(magasinStockagePF.get(comboMagasinPF.getSelectedItem()));
		 stockPF.setPrixUnitaire(BigDecimal.ZERO);
		 stockPF.setStock(BigDecimal.ZERO);
		 stockPFDAO.add(stockPF);
	 }
	 
 }
 
 
 boolean remplirMapQuantiteCharge(){
		boolean trouve=false;
		int i=0;
		int j=0;	
		 BigDecimal quantite=BigDecimal.ZERO;
		for( j=0;j<table.getRowCount();j++){
			
			if(table.getValueAt(j, 6).toString()!=null && !table.getValueAt(j, 6).toString().equals("")  ){
				
				
				
				if(table.getValueAt(j, 2).toString()!=null && !table.getValueAt(j, 2).toString().equals("")  )
				{
					
					mapQuantiteMP.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), new BigDecimal(table.getValueAt(j, 6).toString()));
					mapQuantiteMPExistante.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), new BigDecimal(table.getValueAt(j, 4).toString()));
				
				}else
				{
					mapQuantiteMP.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 6).toString()));
					
					mapQuantiteMPExistante.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 4).toString()));
				}
				
				
				i++;
			}
		}
		
		 if(i>=j)
			 trouve=true;
		return trouve;
	}

 
 List<CoutMP> remplirQuantiteChargeMP(List<CoutMP> listCoutMP){
	 
	 BigDecimal quantite=BigDecimal.ZERO;
	 
	 List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	 
	 for(int i=0;i<listCoutMP.size();i++){
		 
		 CoutMP coutMP=listCoutMP.get(i);
		 
		 quantite=mapQuantiteMP.get(coutMP.getMatierePremier().getCode());
		 
		 coutMP.setQuantCharge(quantite);
		 
		 listCoutMPTmp.add(coutMP);
	 }
	 return listCoutMPTmp;
 }
 
 void remplirQuantiteOffreMP(List<CoutMP> listCoutMP){
	 
	 boolean PrixMPZeroOuNull=false;
		String	Message="";	
		
	 if(checkPromotion.isSelected()==true)
		{
	Promotion promotion;
	 BigDecimal quantiteAcharge=BigDecimal.ZERO;
	 BigDecimal quantiteCalule=BigDecimal.ZERO;
	 BigDecimal quantiteExistante=BigDecimal.ZERO;
	 BigDecimal quantiteCarton=BigDecimal.ZERO;
	 BigDecimal quantiteThe=BigDecimal.ZERO;
	 BigDecimal quantiteTheTotal=BigDecimal.ZERO;
	 BigDecimal quantiteFilmGold=BigDecimal.ZERO;
	 BigDecimal quantiteFilmNormal=BigDecimal.ZERO;
	 BigDecimal quantiteBox=BigDecimal.ZERO;
	 boolean trouve=false;
	 boolean find=false;
	 boolean findBox=false;
	 CoutMP coutMP=new CoutMP();
		StockMP stockMPQauantiteManquante=new StockMP();
		Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
		Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
		
	if(checkPromotion.isSelected()==true){
	 
		ConditionOffre conditionOffre=mapConditionOffre.get(comboCondition.getSelectedItem());
			
			
		 
		 for(int j=0;j<listCoutMP.size();j++){
			  coutMP=listCoutMP.get(j); 
			 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
				 quantiteCarton= coutMP.getQuantite();
			 } 
			 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
				 
				 quantiteTheTotal= quantiteTheTotal.add(coutMP.getQuantite());
			
			 } 
			 if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL))
				  quantiteFilmNormal=coutMP.getQuantite();
			  if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD))
				  quantiteFilmGold=coutMP.getQuantite();
			 
		 }
		
		 
		  List<DetailEstimationPromo> listeDetailEstimationPromo=listDetailEstimationOffreMPSelectionner;
		 
		  
		  Date dateDebutPrevue=dateDebutChooser.getDate();
			CalculerStockFinale(magasinProd, dateDebutPrevue);
			listEtatStockMPAfficherMagasinProduction.addAll(listEtatStockMPAfficher);
			CalculerStockFinale(magasinStockage, dateDebutPrevue);
			listEtatStockMPAfficherMagasinStockage.addAll(listEtatStockMPAfficher);
			
		  
		  
		  for(int i=0;i<listeDetailEstimationPromo.size();i++){
			  DetailEstimationPromo detailEstimationPromo=listeDetailEstimationPromo.get(i);
			  trouve=false;
			  find=false;
			  
			 
			  if(detailEstimationPromo.getMatierePremiere().getTypeOffre()!=null)
				 {
					 if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
					 {
						 if(conditionOffre!=null)
							{
							 
							 if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
							 {
								 if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
								 {
									 
									 //quantiteCalule=mapQuantiteOffreVariant.get(detailEstimationPromo.getMatierePremiere().getCode());
									 quantiteCalule=BigDecimal.ZERO;
									 
								 }else
								 {
									 quantiteCalule=quantiteCarton.multiply(conditionOffre.getValeur().divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)); 
								 }
							 }else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
							 {
								 quantiteCalule=quantiteCarton.multiply(conditionOffre.getValeur().divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)); 

							 }
							 
							
							 
							 
							 
							} 
						 
						
					 }
					 
					 else
					 {
						 quantiteCalule=quantiteCarton.multiply(detailEstimationPromo.getQuantite() );
					 }
				 }else
				 {
					 quantiteCalule=quantiteCarton.multiply(detailEstimationPromo.getQuantite() );
				 }
			 
			  
			  
			  
			  if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  {
				  
				  
				  
				  
				  
				  for(int t=0;t<listEtatStockMPAfficherMagasinProduction.size();t++)
					{
						if(listEtatStockMPAfficherMagasinProduction.get(t).getMp().getId()==detailEstimationPromo.getMatierePremiere().getId())
						{
							
							if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP()!=null)
							{
								if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP().getId()==detailEstimationPromo.getFournisseur().getId())
								{
									quantiteExistante=listEtatStockMPAfficherMagasinProduction.get(t).getQuantiteFinale();
								}
								
								
							}
							
							
							
						}
						
						
						
						
						
					}
				  
				  
				  
				  
				  
			  }else
			  {
				 
				  

					for(int d=0;d<listEtatStockMPAfficherMagasinProduction.size();d++)
								{
									if(listEtatStockMPAfficherMagasinProduction.get(d).getMp().getId()==detailEstimationPromo.getMatierePremiere().getId())
									{
										
										if(listEtatStockMPAfficherMagasinProduction.get(d).getFournisseurMP()==null)
										{
											
											
											quantiteExistante=listEtatStockMPAfficherMagasinProduction.get(d).getQuantiteFinale();
											
											
											
										}
										
										
										
									}
									
									
									
									
									
								}	  
					  
			  }
			  
			 
			  
			  
			  if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  quantiteThe=quantiteCalule;
				  
			  for(int k=0;k<listCoutMP.size();k++){
					 
					  coutMP=listCoutMP.get(k);
					  
					  if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)){
						 // quantiteBox=quantiteCalule;
						  findBox=true;
						 
						  
						 }
					 
					 
					 if(trouve==false && detailEstimationPromo.getMatierePremiere().getCode().equals(coutMP.getMatierePremier().getCode())){
						 
						 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						 {
							 
							 if(detailEstimationPromo.getFournisseur().getId()==coutMP.getFournisseurMP().getId())
							 {
								 
								 quantiteCalule=quantiteCalule.add(coutMP.getQuantite()); 
								 quantiteAcharge=quantiteCalule.subtract(coutMP.getQuantExistante());
								 
								 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
									|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								 {
									 
									 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
									 {
										 if(detailEstimationPromo.getMatierePremiere().getTypeOffre()!=null)
										 {
											 if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
											 {
												 
												 if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
												 {
													 if(listeDetailEstimationPromo.size()>1)
													 {
														 quantiteAcharge= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING);  
														 quantiteCalule= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING); 
													 }else 
													 {
														 quantiteAcharge= quantiteAcharge;  
													 }
												 }else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
												 {
													 quantiteAcharge= quantiteAcharge;   
												 }
												 
											
												 
											 }else
											 {
												 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING);
											 }
											 
											 
											 
										 }else
										 {
											 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING);
										 }
										 
									 }else
									 {
										 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
									 }
									
									 
									 
									 
									 
								 }
									
								
								 coutMP.setQuantite(quantiteCalule);
								 coutMP.setQuantEstime(quantiteAcharge);
								 listCoutMP.set(k, coutMP);
								 trouve=true;
								 find=true;
							 }
							 
							 
						 }else
						 {
							 
							 quantiteCalule=quantiteCalule.add(coutMP.getQuantite()); 
							 quantiteAcharge=quantiteCalule.subtract(coutMP.getQuantExistante());
							 
							 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
								|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							 {
								 
								 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								 {
									 
									 if(detailEstimationPromo.getMatierePremiere().getTypeOffre()!=null)
									 {
										 if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
										 {
											 
											 if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
											 {
												 coutMP.setQuantiteOffreVariant(mapQuantiteOffreVariant.get(detailEstimationPromo.getMatierePremiere().getCode())); 
											 }
											 
											 if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
											 {
												 if(listeDetailEstimationPromo.size()>1)
												 {
													 quantiteAcharge= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING); 
													 quantiteCalule= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING); 
												 }else 
												 {
													 quantiteAcharge= quantiteAcharge;  
												 }
											 }else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
											 {
												 quantiteAcharge= quantiteAcharge;  
											 }
								
											 
											
										 
										 }else
										 {
											 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
										 }
										 
										 
									 }else
									 {
										 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
									 }
									 
								 }else
								 {
									 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
								 }
								 
								 
								 
							 }
								
							
							 coutMP.setQuantite(quantiteCalule);
							 coutMP.setQuantEstime(quantiteAcharge);
							 listCoutMP.set(k, coutMP);
							 trouve=true;
							 find=true; 
							 
						 }
						 
					
						 
					 } 
			  }
			  
			  
			  if(find==false){
				 	quantiteAcharge=quantiteCalule.subtract(quantiteExistante);
				 	if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
					 || detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
				 	{
				 		
				 		
				 		 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						 {
							 
							 if(detailEstimationPromo.getMatierePremiere().getTypeOffre()!=null)
							 {
								 if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
								 {
									 
									 if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
									 {
										 if(listeDetailEstimationPromo.size()>1)
										 {
											 quantiteAcharge= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING);  
											 quantiteCalule= BigDecimal.ZERO.setScale(0, RoundingMode.CEILING); 
										 }else 
										 {
											 quantiteAcharge= quantiteAcharge;  
										 } 
										 
									 }else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
									 {
										 quantiteAcharge= quantiteAcharge;  
										 
									 }
								
								 }else
								 {
									 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
								 }
								 
								 
							 }else
							 {
								 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
							 }
							 
						 }else
						 {
							 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
						 }
				 	}
						
				 	coutMP=new CoutMP();
				 	coutMP.setMatierePremier(detailEstimationPromo.getMatierePremiere());
				 	if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					 {
						 
						 if(detailEstimationPromo.getMatierePremiere().getTypeOffre()!=null)
						 {
							 if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
							 {
								 
								 if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
								 {
									 coutMP.setQuantiteOffreVariant(mapQuantiteOffreVariant.get(detailEstimationPromo.getMatierePremiere().getCode()));  								 }
								 
								 
							 }
				 	
						 }
					 }
				 	
				 	
				 	
					if(detailEstimationPromo.getMatierePremiere().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))!=null)
					{
						if(detailEstimationPromo.getMatierePremiere().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())).compareTo(BigDecimal.ZERO)!=0)
						{
							
							coutMP.setPrixUnitaire(detailEstimationPromo.getMatierePremiere().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate())));
						}else
						{
							PrixMPZeroOuNull=true;
	  		  				Message=Message+detailEstimationPromo.getMatierePremiere().getCode()+"\n";
							
							
							
						}
						
						
					}else
					{
						
						PrixMPZeroOuNull=true;
  		  				Message=Message+detailEstimationPromo.getMatierePremiere().getCode()+"\n";
						
					}
					
					if(detailEstimationPromo.getFournisseur()!=null)
					{
						coutMP.setFournisseurMP(detailEstimationPromo.getFournisseur());
					}
					
					
					coutMP.setQuantite(quantiteCalule);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteAcharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				 find=true;
			 }
		}
		  
		  
		  if(findBox==true){
			  quantiteCalule=BigDecimal.ZERO;
			  quantiteAcharge=BigDecimal.ZERO;
			  trouve=false;
				 find=false;
			  for(int j=0;j<listCoutMP.size();j++){
				  
				  coutMP=listCoutMP.get(j);
				  if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL)){
					  
					  System.out.println(coutMP.getMatierePremier().getNom() +" : "+coutMP.getQuantite());
					  
					  if(quantiteTheTotal.compareTo(BigDecimal.ZERO)!=0)
					  {
						  quantiteCalule=(quantiteFilmNormal.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP)).multiply(quantiteThe) ;
					  }
					 
					  
					  quantiteCalule=quantiteCalule.add(coutMP.getQuantite()); 
						 quantiteAcharge=quantiteCalule.subtract(coutMP.getQuantExistante());
						 coutMP.setQuantite(quantiteCalule);
						 coutMP.setQuantEstime(quantiteAcharge);
						 listCoutMP.set(j, coutMP);
						 trouve=true;
						 
						 System.out.println(coutMP.getMatierePremier().getNom() +" : Apres offre : "+coutMP.getQuantite());
				  }else  if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD)){
					  System.out.println(coutMP.getMatierePremier().getNom() +" : "+coutMP.getQuantite());
					  if(quantiteTheTotal.compareTo(BigDecimal.ZERO)!=0)
					  {
						  quantiteCalule=(quantiteFilmGold.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP)).multiply(quantiteThe);
						  
					  }
					
					  
					  quantiteCalule=quantiteCalule.add(coutMP.getQuantite()); 
						 quantiteAcharge=quantiteCalule.subtract(coutMP.getQuantExistante());
						 if(quantiteAcharge.compareTo(BigDecimal.ZERO)  <0)
						 {
							 quantiteAcharge=BigDecimal.ZERO; 
						 }
							
						 coutMP.setQuantite(quantiteCalule);
						 coutMP.setQuantEstime(quantiteAcharge);
						 listCoutMP.set(j, coutMP);
						 find=true;
						 System.out.println(coutMP.getMatierePremier().getNom() +" : Apres offre : "+coutMP.getQuantite());
				  } else if(trouve==true && find==true)
					  break;
			  }
			  
		  }
	 }
   }	
	 
	 
	 if(PrixMPZeroOuNull==true)
	 {
		 JOptionPane.showMessageDialog(null, Message, "Erreur Prix",JOptionPane.WARNING_MESSAGE);
			return; 
	 }
	 
 }

 
 private static String registerMailBody() {
		return "<HTML><b>OF N° :</b>"+production.getNumOF()+"<br><br>"
				+ "<br><b>Les détails de l'OF :</b><br>"
				+ "<LI><b>Article : "+production.getArticles().getLiblle()+"</b></LI><br>"
				+ "<LI><b>Code Article : "+production.getArticles().getCodeArticle()+"</b></LI><br>"
				+ "<LI><b>Quantité  : "+production.getQuantiteEstime()+"</b></LI><br>"
				+ "<LI><b>Depot Stockage  : "+production.getMagasinStockage().getDepot().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Magasin Stockage  : "+production.getMagasinStockage().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Machine  : "+production.getLigneMachine().getMachine().getNom()+"</b></LI><br>"
				+ "<LI><b>Machine  : "+production.getLigneMachine().getNom()+"</b></LI><br>"
				+ "<LI><b>Magasin Production  : "+production.getMagasinProd().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Machine  : "+production.getLigneMachine().getMachine().getNom()+"</b></LI><br>"
				+ "<LI><b>Groupe Production  : "+production.getEquipe().getNomEquipe()+"</b></LI><br>"
				+ "<LI><b>Groupe Générique  : "+production.getEquipeGen().getNomEquipe()+"</b></LI><br>"
				+ "<LI><b>Depot Production  : "+production.getMagasinProd().getDepot().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Magasin Production  : "+production.getMagasinProd().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Depot Stockage Produit Fini  : "+production.getMagasinPF().getDepot().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Magasin Stockage Produit Fini  : "+production.getMagasinPF().getLibelle()+"</b></LI><br>"
				+ "Merci pour votre confiance<br>"
				+ "Service Informatique<br>"
				+"Système Production</HTML>";
	}
 
 
	void afficher_tableMatierPremier(List<MatierePremier> listMatierePremier)
	{
		intialiserTableauMP();
		  int i=0;
			while(i<listMatierePremier.size())
			{	
				
				MatierePremier matierePremier=listMatierePremier.get(i);
				mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
				Object []ligne={matierePremier.getCode(),matierePremier.getNom(),""};

				modeleMPCAT.addRow(ligne);
				i++;
			}
	}
	
	
	void intialiserTableauMP(){
		modeleMPCAT =new DefaultTableModel(
			     	new Object[][] {
			     	},
			     	new String[] {
			     			"Code","Nom Matière Première", "Quantité Estimé"
			     	}
			     ) {
			     	boolean[] columnEditables = new boolean[] {
			     			false,false,true
			     	};
			     	public boolean isCellEditable(int row, int column) {
			     		return columnEditables[column];
			     	}
			     };
			     tableMP.setModel(modeleMPCAT); 
			     tableMP.getColumnModel().getColumn(0).setPreferredWidth(160);
			     tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
			     tableMP.getColumnModel().getColumn(2).setPreferredWidth(160);
			     tableMP.getTableHeader().setReorderingAllowed(false);
	    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	}
 
	
	boolean remplirMapQuantiteEstimation(){
		boolean trouve=false;
		int i=0;
		mapQuantiteMP=new HashMap<>();
		
		mapMatierePremierImp=new HashMap<>();


		for(int j=0;j<tableMP.getRowCount();j++){
			
			if(!tableMP.getValueAt(j, 2).toString().equals("") )
			{
				
				
				mapQuantiteMP.put(tableMP.getValueAt(j, 0).toString(), new BigDecimal((String) tableMP.getValueAt(j, 2)));
			
				mapMatierePremierImp.put(i, mapMatierePremierTmp.get(tableMP.getValueAt(j, 0).toString()));
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}


	List<DetailEstimation> remplirDetailEstimation(){
		BigDecimal quantite=BigDecimal.ZERO;
		
		
		
		List<DetailEstimation> listDetailEstimation= new ArrayList<DetailEstimation>();
		
		/*ajouter le teste si cet estimation existe déja ;*/
		//fff
		for(int i=0;i<mapMatierePremierImp.size();i++){
			
			DetailEstimation detailEstimation=new DetailEstimation();
		
			MatierePremier matierePremier =mapMatierePremierImp.get(i);
			
			quantite=mapQuantiteMP.get(matierePremier.getCode());
		
			
			detailEstimation.setQuantite(quantite);
			detailEstimation.setPriorite(2);
			detailEstimation.setMatierePremier(matierePremier);
			detailEstimation.setArticles(article);
			
		
			listDetailEstimation.add(detailEstimation);
		
		}
		return listDetailEstimation;
		
	}
	
	public void show()
	{
		lisDetailEstimationbycategorie.clear();
		
		comboBoxThe.setSelectedIndex(-1);
		comboBoxThe.setEnabled(false);
		scrollPaneMP.setVisible(true);
		btnvaliderEstimation.setVisible(true);
	}
	
	public void hide()
	{
		
		scrollPaneMP.setVisible(false);
		btnvaliderEstimation.setVisible(false);
		
		
	}
	
	public void InitialserTous()
	{
		comboSousCategorie.setSelectedItem("");
		btnValiderConditionMixte.setVisible(false);
			lblCondition.setVisible(false);
			comboCondition.setVisible(false);
			comboCondition.setSelectedItem("");	
			comboConditionChecked.setVisible(false);
			for(int i=0;i< comboConditionChecked.getItemCount();i++)
			{
				CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(i);
				checkBoxItem.setSelected(false);
			}
		
		codeArticle.setText("");
		categorie.setSelectedIndex(-1);
		
		quantite.setText("");
		comboScotch.setSelectedIndex(-1);
		chckbxArticleMixte.setSelected(false);
		comboDepot.setSelectedIndex(-1);
		comboMagasin.setSelectedIndex(-1);
		comboBoxThe.removeAllItems();
		comboPeriode.setSelectedIndex(-1);
		txtNumOF.setText("");
		txtDescription.setText("");
		checkPromotion.setSelected(false);
		listPromotion.clear();
 		
		dateDebutChooser.setDate(null);
		dateFinChooser.setDate(null);		
		comboMachine.setSelectedIndex(-1);
		comboLigneMachine.setSelectedIndex(-1);
		comboDepotPF.setSelectedIndex(-1);
		comboDepotProd.setSelectedIndex(-1);
		comboMagasinProd.setSelectedIndex(-1);
		comboMagasinPF.setSelectedIndex(-1);
		
		intialiserTableau();
		intialiserTableauFournisseurMPMixte();
		 

 			
	   		lblTypeOffre.setVisible(false);
 			comboTypeOffre.setVisible(false);
 		   comboTypeOffre.setSelectedItem("");	
	   			lblCondition.setVisible(false);
	   			comboCondition.setVisible(false);
	   		comboCondition.setSelectedItem("");
	   			
	   		intialiserTableauOffre()	;
	   	tableOffre.setVisible(false);
	   			
	   		 OffreValider=false; 
	   	EstimationEnVracCalculer=false; 
	   		
		
		
	}
	
	
	public void CalculerStockFinale( Magasin magasin , Date dat)
	{

		detailTransferStockMPDAO.ViderSession();
		
		MatierePremier matierePremier=null;
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=null;
			
				
			  	FournisseurMP fournisseurMP=null;
			
			  	if(magasin==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
			  		return;
			  	}
			  	
			  	
			  	
				if(dat==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de situation SVP");
			  		return;
			  	}
				
				
				
				Date mindate=null;
				
				Mindate=detailTransferStockMPDAO.MinDate(magasin);
				
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
					String year = simpleDateFormatyear.format(dat);
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=SubCategorieMPDAO.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);

				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

	listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dat, magasin, subCategorieMpthe, null, null);
  					
	listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
			listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dat, magasin, subCategorieMpthe, null, null);

				
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
			
			 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
			 {
				 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				 {
					 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
				 }else
				 {
					 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
				 }
				
				 
			 }else
			 {
				 
				 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				 {
					 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
				 }else
				 {
					 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
				 }
				 
			 }
			
			
			}
			
			
			
			
		}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
		{
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
				 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				 {
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					
					 
				 }else
				 {
					 
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					 
				 }
			}
			
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				 {
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					
					 
				 }else
				 {
					 
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					 
				 }
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				 {
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					
					 
				 }else
				 {
					 
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					 
				 }
				
			}
			
		}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
				 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				 {
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					
					 
				 }else
				 {
					 
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					 
				 }
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
				 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				 {
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					
					 
				 }else
				 {
					 
					 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
					 }else
					 {
						 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
					 }
					 
				 }
				
			}
			
		}else
		{
			
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
			 if(magasin.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
			 {
				 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				 {
					 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
				 }else
				 {
					 mapStockFinaleMagasinStiockage.put(etatStockMP.getMp().getCode(), etatStockMP);
				 }
				
				 
			 }else
			 {
				 
				 if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				 {
					 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode()+" "+etatStockMP.getFournisseurMP().getCodeFournisseur(), etatStockMP);
				 }else
				 {
					 mapStockFinaleMagasinProduction.put(etatStockMP.getMp().getCode(), etatStockMP);
				 }
				 
			 }
			
			
			
		}
			
			
		
		
		
		
		
		
		
			
			
			
			
			
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
	
	
	public void ValiderOffreMP()
	{
		listGrammageOffreSelectionner.clear();
		listDetailEstimationOffreMPSelectionner.clear();
		List<BigDecimal> listGrammageOffreSelectionnerComparer =new ArrayList<BigDecimal>();
		
		mapQuantiteOffreVariant.clear();
	 
		
		if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_PF))
			{
			if(comboVariant.getSelectedItem().equals(Constantes.OFFRE_VARIANT))
			{
			 
	 	 

				 
			 	
boolean erreurOffreVariante=false;
boolean erreurGrammagePF=false;
boolean erreurGrammageComparer=true;
int nbrErreur=0;
				
			for(int f=0;f<comboConditionChecked.getItemCount();f++)
			{
				
				 CheckBoxItem checkBoxItem = comboConditionChecked.getItemAt(f);
				
					if( checkBoxItem.isSelected()==true)
					{
						
						if(!checkBoxItem.equals(""))
						{
							
							BigDecimal condition=mapConditionOffre.get(checkBoxItem.getConditionOffre()).getValeur();
							BigDecimal conditionestimation=BigDecimal.ZERO;

							  
							  erreurGrammagePF=false;
							  erreurGrammageComparer=true;
							
							
							
							
							
							for(int i=0;i<tableOffre.getRowCount();i++)
							{
								
							boolean selected=(boolean)tableOffre.getValueAt(i, 4) ;
								
								if(selected==true)
								{
									
								MatierePremier matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString());
								 
										 
											if(matierePremier.getGrammageOffre()!=null)
											{
												if((condition.remainder(matierePremier.getGrammageOffre())).compareTo(BigDecimal.ZERO)==0)
												{
													erreurGrammageComparer=false;
												}else
												{
													for(int t=0;t<tableOffre.getRowCount();t++)
													{
														
													boolean selecte=(boolean)tableOffre.getValueAt(t, 4) ;
														
														if(selecte==true)
														{
															MatierePremier matierePremierTmp=	mapMPOffre.get(tableOffre.getValueAt(t, 0).toString());
															if(matierePremierTmp.getId()!=matierePremier.getId())
															{
																
																if(matierePremierTmp.getGrammageOffre()!=null)
																{
																	
																	if(((condition.remainder(matierePremier.getGrammageOffre())).remainder(matierePremierTmp.getGrammageOffre())).compareTo(BigDecimal.ZERO)==0)
																	{
																		erreurGrammageComparer=false;
																	}
																	
																	
																	
																}else
																{
																	erreurGrammagePF=true;
																}
																
																
															}
															
														}
													}
												}
											}else
											{
												erreurGrammagePF=true;
											}
											
											
											  
								
								
									DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
								
									
									if(!tableOffre.getValueAt(i, 3).equals(""))
									{
										if(new BigDecimal(tableOffre.getValueAt(i, 3).toString()).compareTo(BigDecimal.ZERO)!=0)
										{
											if(matierePremier.getGrammageOffre()!=null)
											{
												detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(tableOffre.getValueAt(i, 3).toString())));

											}
											
											 
											
											
										}else
										{
											if(matierePremier.getGrammageOffre()!=null)
											{
												detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));

											}
										}
										
										
										
									}else
									{
										
										if(matierePremier.getGrammageOffre()!=null)
										{
											detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));

											
										}
									}
									
									
									
									
									 
								listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
								
								boolean GrammageOffreExiste=false;
								 
								for(int y=0;y<listGrammageOffreSelectionner.size();y++)
								{
									if(listGrammageOffreSelectionner.get(y).compareTo(matierePremier.getGrammageOffre())==0)
									{
										GrammageOffreExiste=true;
									}
									
									
								}
								
								if(GrammageOffreExiste==false)
								{
									listGrammageOffreSelectionner.add(matierePremier.getGrammageOffre());
									listGrammageOffreSelectionnerComparer.add(matierePremier.getGrammageOffre());
								}
								
									
								}
								
								
							}
							
							if(erreurGrammageComparer==true || erreurGrammagePF==true)
							{
								nbrErreur=nbrErreur+1;
							}
						 
						 
							
							
							
						}
						
						
						
					}
		
					
					
					
						
				
			}
			
			/*

			if(nbrErreur!=0)
			{
				JOptionPane.showMessageDialog(null, "Total Grammage Des Artticle Sélectionner est différent de Condition Offre selectionner , Ou Article Offre Ne Contient Auccun Grammage","Erreur",JOptionPane.ERROR_MESSAGE );
				OffreValider=false;
				return;
				
			}else
			{
				
				
				
				OffreValider=true;
			}	
			
			*/
			
			OffreValider=true;
					 
			 	
			}else
			{
				
				

				
				BigDecimal condition=mapConditionOffre.get(comboCondition.getSelectedItem()).getValeur();
				BigDecimal conditionestimation=BigDecimal.ZERO;
				boolean ErreurGrammageOffre=false;
				boolean GrammageOffreExiste=false;
				
				
				
				
				
				for(int i=0;i<tableOffre.getRowCount();i++)
				{
					
				boolean selected=(boolean)tableOffre.getValueAt(i, 4) ;
					
					if(selected==true)
					{
						
					MatierePremier matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString());
					
					
					if(!tableOffre.getValueAt(i, 3).equals(""))
					{
						if(new BigDecimal(tableOffre.getValueAt(i, 3).toString()).compareTo(BigDecimal.ZERO)!=0)
						{
							if(matierePremier.getGrammageOffre()!=null)
							{
								if(matierePremier.getGrammageOffre().multiply(new BigDecimal(tableOffre.getValueAt(i, 3).toString())).compareTo(condition)>0)
								{
									ErreurGrammageOffre=true;
								}
								
							}else
							{
								ErreurGrammageOffre=true;
							}
							
							 
							
							
						}else
						{
							if(matierePremier.getGrammageOffre()!=null)
							{
								
								if(matierePremier.getGrammageOffre().compareTo(condition)>0)
								{
									ErreurGrammageOffre=true;
								}
							}else
							{
								ErreurGrammageOffre=true;
							}
							
							
							 
						}
						
						
						
					}else
					{
						if(matierePremier.getGrammageOffre()!=null)
						{
							if(matierePremier.getGrammageOffre().compareTo(condition)>0)
							{
								ErreurGrammageOffre=true;
							}
							
						}else
						{
							ErreurGrammageOffre=true;
						}
						
						
						 
					}
					
					
					
						DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
					
						
						if(!tableOffre.getValueAt(i, 3).equals(""))
						{
							if(new BigDecimal(tableOffre.getValueAt(i, 3).toString()).compareTo(BigDecimal.ZERO)!=0)
							{
								detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(tableOffre.getValueAt(i, 3).toString())));
								
								 
								
								
							}else
							{
								detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));
							}
							
							
							
						}else
						{
							detailEstimationPromo.setQuantite(matierePremier.getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));
						}
						
						
						
						
						 
					listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
					
					
					GrammageOffreExiste=false;
					for(int y=0;y<listGrammageOffreSelectionner.size();y++)
					{
						if(listGrammageOffreSelectionner.get(y).compareTo(matierePremier.getGrammageOffre())==0)
						{
							GrammageOffreExiste=true;
						}
						
						
					}
					
					if(GrammageOffreExiste==false)
					{
						listGrammageOffreSelectionner.add(matierePremier.getGrammageOffre());
						listGrammageOffreSelectionnerComparer.add(matierePremier.getGrammageOffre());
					}
					
						
					}
					
					
				}
				
				
				int Erreur=0;
				boolean ErreurSommeQuantiteOffre=false;
				
				for(int d=0;d<listGrammageOffreSelectionner.size();d++)
				{
					
				BigDecimal restDivision=condition.remainder(listGrammageOffreSelectionner.get(d));	
					
				if(restDivision.compareTo(BigDecimal.ZERO)!=0)
				{
					
					 
					ErreurSommeQuantiteOffre=false;	
					
				for(int t=0;t<listGrammageOffreSelectionnerComparer.size();t++)
				{
					
					if(listGrammageOffreSelectionnerComparer.get(t).compareTo(listGrammageOffreSelectionner.get(d))!=0)
					{
						
						BigDecimal restDivision2=restDivision.remainder(listGrammageOffreSelectionnerComparer.get(t));
						
						if(restDivision2.compareTo(BigDecimal.ZERO)==0)
						{
							ErreurSommeQuantiteOffre=true;	
							
						}
						
						if((listGrammageOffreSelectionner.get(d).add(listGrammageOffreSelectionnerComparer.get(t))).compareTo(condition)==0)
								{
							ErreurSommeQuantiteOffre=true;	
								}
						
						
						
					}
					
					
					
					
				}
				
				
				if(ErreurSommeQuantiteOffre==false)
				{
					Erreur=Erreur+1;
				}
				
					
					
					
					
					
				}
					
					
					
					
					
				}
				
				if(Erreur>0)
				{
					ErreurGrammageOffre=true;
				}
					
					if(ErreurGrammageOffre==true)
					{
						JOptionPane.showMessageDialog(null, "Total Grammage Des Artticle Sélectionner est différent de Condition Offre selectionner","Erreur",JOptionPane.ERROR_MESSAGE );
						OffreValider=false;
						return;
					}else
					{
						
						
						
						OffreValider=true;
					}
					
						
					 
			   			
					
				
			}
			
			
			
			}else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_SPECIALE))
			{
				BigDecimal condition=mapConditionOffre.get(comboCondition.getSelectedItem()).getValeur();
				
				for(int i=0;i<tableOffre.getRowCount();i++)
				{
					
				 
				if(!tableOffre.getValueAt(i, 2).toString().equals(""))
				{
					MatierePremier matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString()+" "+tableOffre.getValueAt(i, 2).toString());
					
					 
					 
					 
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						
						tableOffre.setValueAt(true, i, 4);
						
					} 
				}else
				{
					
					MatierePremier matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString());
					
					 
					 
					 
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
					{
						
						tableOffre.setValueAt(true, i, 4);
						
					} 
					
				}
				
				
							 
							 
						
					
						
						
					}
				
				MatierePremier matierePremier=null;
				for(int i=0;i<tableOffre.getRowCount();i++)
				{
					
				boolean selected=(boolean)tableOffre.getValueAt(i, 4) ;
				
				if(!tableOffre.getValueAt(i, 2).toString().equals(""))
				{
					matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString()+" "+tableOffre.getValueAt(i, 2).toString());
					
				}else
				{
					matierePremier=	mapMPOffre.get(tableOffre.getValueAt(i, 0).toString());
				}
				 
				
					if(selected==true)
					{
						
						if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE) || matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
						{
							
							 for(int j=0;j<listCoutMP.size();j++)
							 {
								 if(matierePremier.getId()==listCoutMP.get(j).getMatierePremier().getId())
								 {
									 
									if(listCoutMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(listCoutMP.get(j).getFournisseurMP().getCodeFournisseur().equals(tableOffre.getValueAt(i, 2)))
										{
											
											tableOffre.setValueAt(listCoutMP.get(j).getEstimation().multiply(condition.divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)), i, 3);
										
											DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
											detailEstimationPromo.setQuantite(listCoutMP.get(j).getEstimation().multiply(condition.divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)));
											listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
										}
										
									}else if(listCoutMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
									{
										tableOffre.setValueAt(condition.divide(listCoutMP.get(j).getEstimation().multiply(new BigDecimal(1000)), 6,  RoundingMode.HALF_DOWN), i, 3);
										DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
										detailEstimationPromo.setQuantite(condition.divide(listCoutMP.get(j).getEstimation().multiply(new BigDecimal(1000)), 6,  RoundingMode.HALF_DOWN));
										listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
									}
									 
									 
								 }
								 
								 
								 
							 }
						}else
						{
							
							if(!tableOffre.getValueAt(i, 3).equals(""))
							{
								
								DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
								detailEstimationPromo.setQuantite(new BigDecimal(tableOffre.getValueAt(i, 3).toString()));
								listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
								
							}
							
							
							
							
						}
						
						
						
				
					
						
						
					}
					
					
				}
				
				
				OffreValider=true;
					
					
				}else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES))
				{
					try {
						
					
					boolean select=false;
					boolean estimation=false;
					for(int i=0;i<tableOffre.getRowCount();i++)
					{
						
						boolean selected=(boolean)tableOffre.getValueAt(i, 4) ;
						
						if(selected==true)
						{
							select=true;
							if(!tableOffre.getValueAt(i, 3).equals(""))
							{
								
								
							BigDecimal estimationValeur=new BigDecimal(tableOffre.getValueAt(i, 3).toString())	;
							estimation=true;
							
							DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
							detailEstimationPromo.setQuantite(new BigDecimal(tableOffre.getValueAt(i, 3).toString()));
							listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
							
							}
							
							
							
							
							
						}
						
					
						
						
					}
					
					if(select==false)
					{
						JOptionPane.showMessageDialog(null, "veuillez Selectionner Une Ou Plusieur Articles Offre  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(estimation==false)
					{
						JOptionPane.showMessageDialog(null, "veuillez entrer Estimation Offre  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					
					OffreValider=true;
					
					
					
					
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "veuillez entrer estimation en Chiffre SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}	
					
					
					
					
					
				}else if(comboTypeOffre.getSelectedItem().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
				{
					try {
						
					
					boolean select=false;
					boolean estimation=false;
					for(int i=0;i<tableOffre.getRowCount();i++)
					{
						
						boolean selected=(boolean)tableOffre.getValueAt(i, 4) ;
						
						if(selected==true)
						{
							select=true;
							DetailEstimationPromo detailEstimationPromo=listDetailEstimationOffreMP.get(i);
							if(detailEstimationPromo.getMatierePremiere().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES))
							{
								if(!tableOffre.getValueAt(i, 3).equals(""))
								{
									
									
								BigDecimal estimationValeur=new BigDecimal(tableOffre.getValueAt(i, 3).toString())	;
								estimation=true;
								
							
								detailEstimationPromo.setQuantite(new BigDecimal(tableOffre.getValueAt(i, 3).toString()));
								listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
								
								}
							}else
							{
								
								detailEstimationPromo.setQuantite(detailEstimationPromo.getMatierePremiere().getGrammageOffre().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));
									
								
								listDetailEstimationOffreMPSelectionner.add(detailEstimationPromo);
								
								
							}
							
							
							
							
							
							
						}
						
					
						
						
					}
					
					if(select==false)
					{
						JOptionPane.showMessageDialog(null, "veuillez Selectionner Une Ou Plusieur Articles Offre  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(estimation==false)
					{
						JOptionPane.showMessageDialog(null, "veuillez entrer Estimation Offre  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					
					OffreValider=true;
					
					
					
					
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "veuillez entrer estimation en Chiffre SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}	
					
					
					
					
					
				}
				
				
				
			
				
				
				
				
				
			}
	
	
	
	
	public void VerifierOffreExistant()
	{
		
		
	List<Promotion>	 listPromotion=new ArrayList<>();
		
	listPromotion=promotiondao.findAll();
	
	List<DetailEstimationPromo>	listeDetailEstimationPromoTmp=new ArrayList<>();
	boolean trouver=false;
	boolean promotionExiste=true;
	boolean MPexiste=false;
	if(listPromotion.size()==0)
	{
		promotionExiste=false;
	}
			
	Promotion promotionTmp=null;
	for(int i=0;i<listPromotion.size();i++)
	{
		listeDetailEstimationPromoTmp.clear();
		listeDetailEstimationPromoTmp=detailEstimationPromoDAO.findByIdPromo(listPromotion.get(i).getId());
		if(listeDetailEstimationPromoTmp.size()==listDetailEstimationOffreMPSelectionner.size())
		{
			promotionExiste=true;
			for(int j=0;j<listeDetailEstimationPromoTmp.size();j++)
			{
				MPexiste=false;
				DetailEstimationPromo detailEstimationPromoTmp=listeDetailEstimationPromoTmp.get(j);
				
			for(int t=0;t<listDetailEstimationOffreMPSelectionner.size();t++)
			{
				DetailEstimationPromo detailEstimationOffreSelectionner=listDetailEstimationOffreMPSelectionner.get(j);
				
				if(detailEstimationPromoTmp.getMatierePremiere().getId()==detailEstimationOffreSelectionner.getMatierePremiere().getId())
				{
					
					if(detailEstimationPromoTmp.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						if(detailEstimationPromoTmp.getFournisseur().getId()==detailEstimationOffreSelectionner.getFournisseur().getId())
						{
							MPexiste=true;
							
						}
						
						
					}else
					{
						MPexiste=true;
					}
					
					
					
					
				}
				
				
				
				
			}
				
				if(MPexiste==false)
				{
					promotionExiste=false;
				}
				
				
			}
			
			
			if(promotionExiste==true)
			{
				
				trouver=true;
				promotionTmp=listeDetailEstimationPromoTmp.get(0).getPromotionest();
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	if(trouver==false)
	{
		
	TypeOffre typeOffre=mapTypeOffre.get(comboTypeOffre.getSelectedItem().toString());	
	ConditionOffre conditionOffre=mapConditionOffre.get(comboCondition.getSelectedItem().toString())	;
		
	String codeOffre=Constantes.PROMOTION_OFFRE+promotiondao.maxIdPromotion();
	Promotion promotion=new Promotion();	
	promotion.setTypeOffre(typeOffre);
	if(typeOffre!=null)
	{
		if(typeOffre.getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES))
		{
			promotion.setCondition(Constantes.TYPE_OFFRE_AUTRES);
		}else
		{
			promotion.setCondition(conditionOffre.getValeur().toString());
		}
	}
	
	promotion.setActif(true);
	promotion.setCode(codeOffre);
	promotiondao.add(promotion);
		
		
	
	
	
	for(int d=0;d<listDetailEstimationOffreMPSelectionner.size();d++)
	{
		DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
		detailEstimationPromo.setMatierePremiere(listDetailEstimationOffreMPSelectionner.get(d).getMatierePremiere());
		detailEstimationPromo.setQuantite(listDetailEstimationOffreMPSelectionner.get(d).getQuantite());
		detailEstimationPromo.setPromotionest (promotion);	
		if(listDetailEstimationOffreMPSelectionner.get(d).getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			 
				detailEstimationPromo.setFournisseur(listDetailEstimationOffreMPSelectionner.get(d).getFournisseur());
			 
			
		}
	
		
		
		
		detailEstimationPromoDAO.add(detailEstimationPromo);
	}
	
	production.setOffre(promotion.getCode());
		
	}else
	{
		production.setOffre(promotionTmp.getCode());
	}
		
		
	}
	
	
	
	private static JComboBox<CheckBoxItem> createCheckBoxComboBox(List<ConditionOffre> listCondition) {
        JComboBox<CheckBoxItem> comboBox = new JComboBox<>();
        CheckBoxItem checkBoxItemtmp = new CheckBoxItem("");

        comboBox.addItem(checkBoxItemtmp);
        for (ConditionOffre conditionOffre : listCondition) {
            CheckBoxItem checkBoxItem = new CheckBoxItem(conditionOffre.getConditionOffre());
            comboBox.addItem(checkBoxItem);

        }

        comboBox.setRenderer (new CheckBoxComboBoxRenderer());
        // Add an ActionListener to handle checkbox selection
        comboBox.addActionListener(e -> {
            JComboBox<CheckBoxItem> source = (JComboBox<CheckBoxItem>) e.getSource();
            CheckBoxItem selectedCheckBoxItem = (CheckBoxItem) source.getSelectedItem();
            selectedCheckBoxItem.setSelected(!selectedCheckBoxItem.isSelected());
        });
        return comboBox;
    }

    private static Object[] getSelectedItems(JComboBox<CheckBoxItem> comboBox) {
        List<Object> selectedList = new ArrayList<>();
        ComboBoxModel<CheckBoxItem> model = comboBox.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            CheckBoxItem checkBoxItem = model.getElementAt(i);
            if (checkBoxItem.isSelected()) {
                selectedList.add(checkBoxItem.getConditionOffre());
            }
        }

        return selectedList.toArray();
    }
	}
	
	
	
 

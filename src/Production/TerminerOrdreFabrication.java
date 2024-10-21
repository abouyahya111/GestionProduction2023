package Production;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;
import javax.swing.ButtonGroup;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;


import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import config.Connect;
import util.CheckableItem;
import util.CheckedComboBox;
import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.OffreProductionDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCoutHorsProdEnAttentDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.OffreProductionDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailCoutHorsProdEnAttent;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.FactureProduction;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.OffreProduction;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.Promotion;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;


public class TerminerOrdreFabrication extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleFournisseur;
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleTableOffreVariante;
	private DefaultTableModel	 modeleEquipeEm;
	private DefaultTableModel	 modeleEquipeGen;
	private DefaultTableModel	 modeleCoutHorsProductionEnAttent;
	private JXTable table= new JXTable();
	private JXTable table_1= new JXTable();
	private JXTable tableEmploye= new JXTable();
	private JXTable tableEmployeGen= new JXTable();
	private JTable tableOffreVariante= new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	private JTextField codeArticle;
	JComboBox comboBox = new JComboBox();
	private JComboBox<String> comboMachine;
	private JComboBox<String> comboLigneMachine;
	private JComboBox categorie;
	private List<Production> listProduction=new ArrayList<Production>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	private List<DetailTransferStockMP> listDetailTransfertMPRetour =new ArrayList<DetailTransferStockMP>();
	private List<DetailProduction> listDetailProduction =new ArrayList<DetailProduction>();
	private List<DetailProduction> listDetailProductionTMP=new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenTmp=new ArrayList<DetailProdGen>();
	private List<DetailProdGen> listeDetailProdGen=new ArrayList<DetailProdGen>();
	List<DetailProdRes> listDetailResponsableProdTmp=new ArrayList<DetailProdRes>();
	List<DetailProdRes> listDetailResponsableProdTmpVerification=new ArrayList<DetailProdRes>();
	List<DetailProdRes> listDetailProdResponsable=new ArrayList<DetailProdRes>();
	 
	
	List< CoutHorsProdEnAttent> listCoutHorsProductionEnAttenteAfficher=new ArrayList< CoutHorsProdEnAttent>();
	private Production production = new Production();
	private Map< String, Production> mapProduction = new HashMap<>();
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();
	private Map< String, String> mapDelaiEmployeHorsProduction = new HashMap<>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapNomEmploye = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map< String, String> mapHeureSupp25EmployeHorsProduction= new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();
	private Map< String, String> mapHeureSupp50EmployeHorsProduction= new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();
	JCheckBox chckbxRetourDepot = new JCheckBox("Retour Depot");
	
	private Map< String, String> mapQuantiteDechet = new HashMap<>();
	private Map< String, String> mapQuantiteReste = new HashMap<>();
	private Map< String, String> mapQuantiteDechetFour = new HashMap<>();
	private Map< String, String> mapCodeFournisseurMP = new HashMap<>();
	private Map< String, String> mapCodeFournisseurDechet = new HashMap<>();
	private Map< String, String> mapQuantiteManquante = new HashMap<>();
	private Map< String, String> mapQuantiteManquanteFrPlus = new HashMap<>();
	private Map< String, String> mapQuantiteOffre = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, CoutHorsProdEnAttent> mapDeatailCoutHorsProductionEnAttente = new HashMap<>();
	
	private JComboBox<String> comboEquipe;
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalEmployeEmbalage=BigDecimal.ZERO;
	private BigDecimal coutTotalHorsProductionEnAttent=BigDecimal.ZERO;
	private BigDecimal coutTotalAutreEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private BigDecimal coutTotalDechet=BigDecimal.ZERO;
	private JButton btnSaisieDelaiEquipeGen;
	private BigDecimal delaiTotal=BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;
	 private TransferStockMPDAO transferstockmpDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionDAO productionDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private  EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private boolean validerSaisie=false;
	private String codeDepot;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private DepotDAO depotdao;
	private FournisseurMPDAO fournisseurMPDAO;
	
	private JTable TableFournisseur;
	private DetailProdResDAO detailProdResDAO;
	int position=-1;
	private PromotionDAO PromotionDAO;
	 JComboBox txtNumOF = new JComboBox();
	 private JTextField txtcodeemployeproduction;
	 private JTextField txtdelaiproduction;
	 private JTextField txthsupp50production;
	 private JTextField txthsupp25production;
	 private JTextField txtcodeemployegenerique;
	 private JTextField txtdelaigenerique;
	 private JTextField txthsupp50generique;
	 private JTextField txthsupp25generique;
	 private JTextField txtcodeemployeemballage;
	 private JTextField txtdelaiemballage;
	 private JTextField txthsupp50emballage;
	 private JTextField txthsupp25emballage;
	 private EmployeDAO employeDAO;
	 JCheckBox checkabsentproduction = new JCheckBox("Absent");
	 JCheckBox checksortieproduction = new JCheckBox("Sortie");
	 JComboBox comboemployeproduction = new JComboBox();
	 JComboBox comboemployeemballage = new JComboBox();
	  JComboBox comboemployegenerique = new JComboBox();
	  JCheckBox checkabsentemballage = new JCheckBox("Absent");
	     JCheckBox checksortieemballage = new JCheckBox("Sortie");
	     JCheckBox checkabsentgenerique = new JCheckBox("Absent");
		     JCheckBox checksortiegenerique = new JCheckBox("Sortie");
		     JCheckBox checkretardproduction = new JCheckBox("Retard");
		     JCheckBox checkretardemballage = new JCheckBox("Retard");
		     JCheckBox checkretardgenerique = new JCheckBox("Retard");
		     private int compteur=0;
		     private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
		     private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
		     private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseur>();
		     private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
		     private List<CoutMP> listCoutMPEnVrac =new ArrayList<CoutMP>();
		     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		     
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
		     private Utilisateur utilisateur;
		     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		     
		     boolean ValiderSaisi=false;
		     private ParametreDAO ParametreDAO;
		     private JXTable TableDetailCoutHorsProductionEnAttent;
		     private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
		     private List<StatistiqueFinanciaireMP> listStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		    
		 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
		 private ParametreDAO parametreDAO;
		  private CoutMPDAO    CoutMPDAO;
			private DetailTransferMPDAO detailTransferStockMPDAO;
			SubCategorieMPDAO SubCategorieMPDAO;
			private JTextField txtQuantiteOffre =new JTextField();
			private JTextField txtQuantitePlus=new JTextField();
			private JTextField txtQuantiteMoins=new JTextField();
			JRadioButton radioMoins = new JRadioButton("Moins");
			JRadioButton radioPlus = new JRadioButton("Plus");
			  ButtonGroup group = new ButtonGroup();
			  DetailCoutHorsProdEnAttentDAO  detailCoutHorsProdEnAttentDAO;
			  private DetailEstimationDAO detailestimationDAO;
			  private List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>() ;
			  private Map< String, BigDecimal> mapQuantiteResterConsommer = new HashMap<>();  
			  private Map< String, Boolean> mapMPClientSaisir = new HashMap<>();  
			  String msgErreur="";
			  private List<SubCategorieMp> listSubCategorieMPClientNonSaisir = new ArrayList<SubCategorieMp>() ;
			  private List<String> listMPOffrePFMixte = new ArrayList<String>() ;
			  private List<String> listMPOffrePFMixteNonSaisir = new ArrayList<String>() ;
			  private Map< String, MatierePremier> mapMPOffrePFMixte = new HashMap<>();  
			  BigDecimal QuantiteTotalOffreMixtPFConsomme=BigDecimal.ZERO;
			  boolean OffrePFMixte=false;
			  private OffreProductionDAO offreProductionDAO;
			  private Map< String, BigDecimal> mapNombreCartonPourOffreVariante = new HashMap<>();
			  private Map< String, BigDecimal> mapGrammageOffreVariante = new HashMap<>();
			  JScrollPane scrollPane_6 ;
			  
			  StatistiqueFinanciereMPDAO StatistiqueFinanciereMPDAO; 
			  
	////////////////////////////////////////////////////////////////////////////////// Connecter Au Base Production Raja     ////////////////////////////////////////////////////////////////////////////////////////////////////////
			  /*
			  private ResultSet rset;
				private Statement stx;
				
				private Connection con;	  
				*/
			  
			  
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  
			  
			  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public TerminerOrdreFabrication(Production productionP,String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1924, 873);
        
        
        try{
        	utilisateur=AuthentificationView.utilisateur;
        	delaiTotalEquipeEmbalage=BigDecimal.ZERO;
        	delaiTotal=BigDecimal.ZERO;
        	coutTotalEmployeEmbalage=BigDecimal.ZERO;
        	coutTotalDechet=BigDecimal.ZERO;
        	coutTotalMP=BigDecimal.ZERO;
        	 employeDAO=new EmployeDAOImpl();
        	listCoutMP =new ArrayList<CoutMP>();
        	listEmploye=new ArrayList<Employe>();
        	listDetailProduction =new ArrayList<DetailProduction>();
        	listeDetailProdGen=new ArrayList<DetailProdGen>();
        	detailtransferMPDAO=new DetailTransferMPDAOImpl();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	mapDelaiEmploye = new HashMap<>();
        	mapDelaiEmployeEmabalage= new HashMap<>();
        //	mapQuantiteConsomme = new HashMap<>();
        	mapQuantiteDechet = new HashMap<>();
        	mapQuantiteReste = new HashMap<>();
        	mapQuantiteDechetFour= new HashMap<>();
        	mapCodeFournisseurMP= new HashMap<>();
        	mapQuantiteManquante= new HashMap<>();
        	mapQuantiteOffre= new HashMap<>();
        	mapQuantiteManquanteFrPlus=	new HashMap<>();
        	mapHeureSupp25EmployeEmbalage= new HashMap<>();
        	mapHeureSupp50EmployeEmbalage= new HashMap<>();
        	mapHeureSupp25EmployeProd= new HashMap<>();
        	mapHeureSupp50EmployeProd= new HashMap<>();
        	productionDAO=new ProductionDAOImpl();
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	compteurResponsableProdDAO= new CompteurResponsableProdDAOImpl();
        	compteurEmployeProdDAO= new CompteurEmployeProdDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	factureProductionDAO= new FactureProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	compteStockMPDAO=new CompteStockMPDAOImpl();
        	detailProductionDAO=new DetailProductionDAOImpl();
        	detailResponsableDAO= new DetailResponsableProdDAOImpl();
        	txtQuantiteRealise=new JTextField();
        	util.Utils.copycoller(txtQuantiteRealise);
			 txtPrixServiceProd = new JTextField();
			 util.Utils.copycoller(txtPrixServiceProd);
			 codeArticle=new JTextField();
			 util.Utils.copycoller(codeArticle);
			 categorie= new JComboBox();
			 comboEquipe=new JComboBox<String>();
			 comboLigneMachine=new JComboBox<String>();
			 transferstockmpDAO= new TransferStockMPDAOImpl();
			 detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
			 depotdao=new DepotDAOImpl();
			 fournisseurMPDAO=new FournisseurMPDAOImpl();
			 comboMachine=new JComboBox<String>();
			 detailProdResDAO=new DetailProdResDAOImpl();
			 PromotionDAO=new PromotionDAOImpl();
			 manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
			 detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
			 ParametreDAO=new ParametreDAOImpl();
			 detailestimationDAO= new DetailEstimationDAOImpl();
			 offreProductionDAO=new OffreProductionDAOImpl();
			 txtNumOF = new JComboBox();
			  
			  txtNumOF.addItem("");
			 
			  listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
			  CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
			  parametreDAO=new ParametreDAOImpl();;
			  CoutMPDAO=new CoutMPDAOImpl();
			  SubCategorieMPDAO=new SubCategorieMPAOImpl();		
			  productionDAO.ViderSession();
			  CoutMPDAO.ViderSession();
			  detailCoutHorsProdEnAttentDAO=new DetailCoutHorsProdEnAttentDAOImpl();
			  StatistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
	////////////////////////////////////////////////////////////////////////////////// Connecter Au Base Production Raja     ////////////////////////////////////////////////////////////////////////////////////////////////////////
				/*			  
			  Connect.connecToProduction();
	    		con=Connect.getConnexion();
	        	stx=con.createStatement();
	        	*/
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
			  
			  txtNumOF.addMouseListener(new MouseAdapter() {
			  	@Override
			  	public void mouseEntered(MouseEvent arg0) {}
			  });
	  		    
			 
			 listProduction=productionDAO.listeProductionEtatCreer(Constantes.ETAT_OF_LANCER,Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
			
		
			 for(int i=0;i<listProduction.size();i++)
			 {
				 
			Production production= listProduction.get(i);
				 
				   txtNumOF.addItem(production.getNumOF());
					mapProduction.put(production.getNumOF(), production);
			 }
			 txtNumOF.setSelectedIndex(-1);
			 
			 
        	if(productionP.getNumOF()!=null)
        	{
        		
        		production=productionP;
        		txtNumOF.setSelectedItem(production.getNumOF());
        		txtQuantiteRealise.setText(quantite);
        		txtPrixServiceProd.setText(nbreHeure);
        		
        		
        		AfficherMatierePremiere();
        		
        		
        	}
        	else {	
        	production = new Production();
        	}
        	
        	listFournisseurMP=fournisseurMPDAO.findAll();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        validerSaisie=false;
        mapParametre=Utils.listeParametre();	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();
          }
        codeDepot= AuthentificationView.utilisateur.getCodeDepot();
       
		
				  		  btnImprimer = new JButton("Imprimer D\u00E9tail OF");
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  
							 List<CoutMP> listCoutMP=productionDAO.listeCoutMP(production.getId());
							 
							Map parameters = new HashMap();
							parameters.put("numOF", production.getNumOF());
							parameters.put("ligneMachine", production.getLigneMachine().getNom());
							parameters.put("machine", production.getLigneMachine().getMachine().getNom());
							parameters.put("equipe", production.getEquipe().getNomEquipe());
							parameters.put("magasin", production.getMagasinProd().getLibelle());
							parameters.put("depot", production.getMagasinProd().getDepot().getLibelle());
							
							parameters.put("article", production.getArticles().getLiblle());
							parameters.put("CodeArticle", production.getArticles().getCodeArticle());
							parameters.put("quantiteDemande", production.getQuantiteEstime()+"");
							parameters.put("equipeGen", production.getEquipeGen().getNomEquipe());
							parameters.put("periode", production.getPeriode());
							
							parameters.put("quantiteRealise", production.getQuantiteReel()+"");
							parameters.put("heureTravaile", production.getNbreHeure()+"");
							
							
							JasperUtils.imprimerDetailOrdreFabrication(listCoutMP,parameters,production.getNumOF());
							
							//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		  	}
				  		  });
				  		initialiserTableOffreVariante();
				  		  		intialiserTableMP();
				  		  		initialiserTableauEmploye();
				  		  		initialiserTableauEquipeEmbalage();
				  		  		initialiserTableauEmployeGen();
				  		 	  		btnImprimer.setIcon(imgModifier);
				  		 btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnImprimer.setBounds(640, 822, 133, 24);
				  		 add(btnImprimer);
				  		 
				  		  btnChercherOF = new JButton("Chercher OF");
				  		  btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnChercherOF.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		   
				  		  	mapMPOffrePFMixte.clear();
				  		  	listMPOffrePFMixte.clear();
				  		  OffrePFMixte=false;
				  		production=mapProduction.get(txtNumOF.getSelectedItem().toString());
				  				if(production!=null){
				  					lisDetailEstimation.clear();
				  					
				  					lisDetailEstimation =detailestimationDAO.findDetilestimationActifByArticle(production.getArticles().getId());
				  					
				  					
				  					if(production.getStatut().equals(Constantes.ETAT_OF_LANCER))
				  					{
				  						
				  						
				  						listDetailResponsableProdTmpVerification.clear();
				  		     			
				  		     			int numberproduction=productionDAO.NombreProductionTerminerParDateParDepot(production.getDate(), Constantes.ETAT_OF_TERMINER,production.getCodeDepot());	
				  		     			
				  		     			int nbrProd=0;
				  		     			listDetailResponsableProdTmpVerification=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
if(listDetailResponsableProdTmpVerification.size()!=0)
{
	for(int i=0;i<listDetailResponsableProdTmpVerification.size();i++)
		{
		
		nbrProd=listDetailResponsableProdTmpVerification.get(i).getNbrProduction();
		
		}
	
	
	
	
	
	
}else
{
	JOptionPane.showMessageDialog(null, "Veuillez Entrer Les Employees Equipe Générique Pour Cette Production Dans la fenetre de saisir Equipe générique SVP","Errgeur",JOptionPane.ERROR_MESSAGE);
	return;
}
	
if(nbrProd==0)
{
	JOptionPane.showMessageDialog(null, "Le Nombre de Production  Déclarer Dans l'equipe Générique égale à 0 , Veuillez Modifier Le SVP","Errgeur",JOptionPane.ERROR_MESSAGE);
	return;
}

if(numberproduction!=0 && nbrProd!=0)
{

	if(numberproduction-nbrProd==0)
	{
		JOptionPane.showMessageDialog(null, "Vous Avez dépacer Le Nombre Productions Déclarer Dans l'equipe Générique SVP","Errgeur",JOptionPane.ERROR_MESSAGE);
		return;
	}else if(numberproduction-nbrProd>0)
	{
		
		JOptionPane.showMessageDialog(null, "Le Nombre de Production terminer dans cette date est Supérieur au Nombre Déclarer Dans l'equipe Générique","Errgeur",JOptionPane.ERROR_MESSAGE);
		return;
		
	}
	
	
	
}
				  						
				  						
				  						
				  					}
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					BigDecimal quantiteCarton=BigDecimal.ZERO;
				  					BigDecimal quantiteTotalOffreCalculer=BigDecimal.ZERO;
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					validerSaisie=false;
				  					
				  					listCoutHorsProductionEnAttent.clear();
				  				
				  					listDetailProdGenTmp.clear();
				  					listDetailProductionTMP.clear();
				  					listDetailResponsableProdTmp.clear();
				  			  		 if(txtQuantiteRealise.getText().equals("")){
				  			  			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					  }	else {
				  						  
				  						List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
				  						  
				  						if(production.getOffre()!=null || production.getTyoeOffre()!=null)
					  					{
					  						
					  						OffrePFMixte=false;
					  						int NBRPFMixte=0;
					  						
					  						Promotion promotion=PromotionDAO.findByCode(production.getOffre());
					  						
					  						 for(int j=0;j<listCoutMPTmp.size();j++){
					  							CoutMP  coutMP=listCoutMPTmp.get(j); 
					  							 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
					  								 
					  								quantiteCarton=quantiteCarton.add((new BigDecimal(txtQuantiteRealise.getText())).divide(coutMP.getEstimation() , 0,RoundingMode.FLOOR));
					  								 
					  							 } 
					  							  
					  							
					  								if(production.getTyoeOffre()!=null)
					  									
						  							{
                                                        if(production.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF) || production.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
						  									
							  							{
                                                        	if(production.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
                                                        	{
                                                        		if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
        							  							{
        					  										
        					  										 
        					  											
        					  										
        					  										listMPOffrePFMixte.add(coutMP.getMatierePremier().getCode());
        					  										mapMPOffrePFMixte.put(coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier());
        					  										
        					  										NBRPFMixte=NBRPFMixte+1;
        					  										
        					  										
        							  							}
                                                        	}else
                                                        	{
                                                        		
                                                        		
                                                        		if( coutMP.getMatierePremier().getTypeOffre()!=null)
        							  							{
        					  										
        					  										 
                                                        			if( coutMP.getMatierePremier().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
            							  							{
                                                        				listMPOffrePFMixte.add(coutMP.getMatierePremier().getCode());
            					  										mapMPOffrePFMixte.put(coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier());
            					  										
            					  										NBRPFMixte=NBRPFMixte+1;
                                                        				
            							  							}
        					  										
        					  										
        					  										
        					  										
        							  							}
                                                        		
                                                        	}
                                                        	
					  								
					  									
					  									
						  							}
					  								
					  								
					  							}
					  							 
					  						 }	
					  						 
					  						 
					  						 if(NBRPFMixte>=1)
					  						 {
					  							 
					  							OffrePFMixte=true;
					  							if(production.getOffreVariant()!=null)
		  										{
					  								if(production.getOffreVariant().equals(Constantes.CODE_OUI))
		  											{
					  									BigDecimal nombreCartonPourOffreVariante=BigDecimal.ZERO;
						  								
					  									if(!remplirMapNombreCartonPourChaqueConditionOffre()){
					  										 
					  										JOptionPane.showMessageDialog(null, "Il faut Entrer Le Nombre De carton Pour chaque Condition Offre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					  									
					  									return;
					  									
					  									}else {
					  										
					  										
					  										
					  										
					  										for(int t=0;t<tableOffreVariante.getRowCount();t++)
					  										{
					  											
					  											if(tableOffreVariante.getValueAt(t, 1)!=null)
					  											{
					  												
					  												if(!tableOffreVariante.getValueAt(t, 1).toString().equals(""))
						  											{
					  													
					  													quantiteTotalOffreCalculer=quantiteTotalOffreCalculer.add((mapGrammageOffreVariante.get(tableOffreVariante.getValueAt(t, 0).toString()).divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)).multiply(new BigDecimal(tableOffreVariante.getValueAt(t, 1).toString())));
					  													
					  													nombreCartonPourOffreVariante=nombreCartonPourOffreVariante.add(new BigDecimal(tableOffreVariante.getValueAt(t, 1).toString()));
					  													
					  													
					  													
					  													
						  											}
					  												
					  												
					  												
					  											}
					  											
					  										}
					  										
					  										
					  										if(nombreCartonPourOffreVariante.compareTo(quantiteCarton)!=0)
					  										{
					  											JOptionPane.showMessageDialog(null, "Le Nombre Total Des Cartons Entrer Pour Chaque conditon Offre est Different aux Total Carton De Production !!!!!! ");
					  											return;
					  										}else
					  										{
					  											tableOffreVariante.setVisible(false);
					  										}
					  										
					  										
					  									}
					  									
					  									
					  									
					  									
					  									
						  								
						  								
		  											}else
		  											{
							  							quantiteTotalOffreCalculer=  (production.getConditionOffre().getValeur().divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)).multiply(quantiteCarton);

		  											}
		  										}else
	  											{
						  							quantiteTotalOffreCalculer=  (production.getConditionOffre().getValeur().divide(new BigDecimal(1000), 6, RoundingMode.HALF_DOWN)).multiply(quantiteCarton);

	  											}
					  							
					  							
					  							
					  							
					  						 }else
					  						 {
					  							 
					  								 List<DetailEstimationPromo> listeDetailEstimationPromo=promotion.getDetailEstimationPromo(); 
							  						 
							  						 for(int j=0;j<listeDetailEstimationPromo.size();j++)
							  						 {
							  							 
							  							DetailEstimationPromo detailEstimationPromo= listeDetailEstimationPromo.get(j);
							  							
							  							if(!detailEstimationPromo.getMatierePremiere().getNom().contains("BOX") && !detailEstimationPromo.getMatierePremiere().getCode().equals(Constantes.CODE_MP_THERRES) && !detailEstimationPromo.getMatierePremiere().getNom().contains(Constantes.MP_CONTIENT_VERRE))
							  							{
							  								
							  								quantiteTotalOffreCalculer=quantiteTotalOffreCalculer.add(detailEstimationPromo.getQuantite().multiply(quantiteCarton));
							  								
							  							}
							  							
							  							
							  							 
							  							 
							  							 
							  						 }  
					  							
					  							 
					  						
					  						 }
					  						 
					  						
					  						 
					  						
					  						
					  						txtQuantiteOffre.setText(quantiteTotalOffreCalculer.setScale(6, RoundingMode.HALF_UP)+"");
					  						
					  						
					  					}
					  					
				  						  
				  						  
				  						  
				  						  
				  					Depot depot=depotdao.findByCode(production.getCodeDepot());
				  						  
				  			  
				  			  	
				  			  	
				  			  	
				  				if(!production.getStatut().equals(ETAT_OF_TERMINER))
				  				{
				  					afficherDetailPorduction(production.getArticles().getDetailEstimation(),listCoutMPTmp);
				  				}
				  				listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.findByEtatByDepotByDateByArticle (COUT_HORS_PRODUCTION_EN_ATTENT,depot,production.getDate(),production.getArticles());
				  			
				  				
				  				
				  				categorie.removeAllItems();
				  				comboLigneMachine.removeAllItems();
				  				comboMachine.removeAllItems();
				  				codeArticle.setText(production.getArticles().getCodeArticle());
				  				categorie.addItem(production.getArticles().getLiblle());
				  				categorie.setSelectedItem(production.getArticles().getLiblle());
				  				
				  				//comboEquipe.addItem(production.getEquipe().getNomEquipe());
				  				//comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
				  				
				  				comboLigneMachine.addItem(production.getLigneMachine().getNom());
				  				comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
				  				
				  				comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
				  				comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
				  				
				  				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  				String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
				  				String dateFinPrev=dateFormat.format(production.getDateFinFabPre());

				  				 listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
				  				 listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
				  				 //listDetailResponsableProdTmp=productionDAO.listeDetailResponsableProd(production.getId());
				  				listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
				  				
				  				afficher_tableMP(listCoutMPTmp);
				  				afficher_tableEmploye(listDetailProductionTMP);
				  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
				  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
				  				remplirQuantite();
				  				if(production.getStatut().equals(ETAT_OF_TERMINER))
				  				{
				  				
				  					
				  					listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO .findByProduction(production);
				  					afficher_tableDetailCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);	
				  					
				  					
				  				}else
				  				{
				  					
				  					
				  					afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
				  					
				  					
				  				}
				  				
				  				
				  				
				  				
				  				/*
				  				int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
				  				if(numberproduction==1)
				  				{
				  				
				  					 btnSaisieDelaiEquipeGen.setEnabled(true);
				  					
				  				}else
				  				{
				  					
				  					 btnSaisieDelaiEquipeGen.setEnabled(false);
				  					
				  				}
				  				*/
				  				
				  					  }
				  				}else{
				  				  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					
				  				}
				  		
				  		  		
				  		  	}
				  		  });
				  		  
				  		  
				  	
				  		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabrication.class.getResource("/img/chercher.png")));
				  		 btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnChercherOF.setBounds(1570, 8, 120, 23);
				  		 add(btnChercherOF);
				  		    
				  		    btnTerminerOF = new JButton("Terminer OF");
				  		    btnTerminerOF.setBounds(204, 822, 112, 24);
				  		    add(btnTerminerOF);
				  		    btnTerminerOF.setIcon(imgAjouter);
				  		    btnTerminerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Terminer cet Ordre de Fabrication?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										{
				  		     		if(txtQuantiteRealise.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(txtPrixServiceProd.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir le Delai Production!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(production.getListDetailProdGen()==null || production.getListDetailProdGen().size()<0){
				  		     			JOptionPane.showMessageDialog(null, "Il faut valider les équipes avant de terminer OF!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}
				  		     		else {
				  		     		if(production.getStatut().equals(Constantes.ETAT_OF_LANCER )) {
				  		     			QuantiteTotalOffreMixtPFConsomme=BigDecimal.ZERO;
				  		     			
				  		     			//ficheEmployeDAO.viderSession();
				  		     			if(validerSaisie==false){
				  		     				JOptionPane.showMessageDialog(null, "Veuillez valider la saisie Avant de Terminer OF!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}else {
				  		     				
				  		     				
				  		     			if(EcarValider()==false)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Les Ecarts Doit etre Egale à 0 SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     				
				  		     			if(OffrePFMixte==true)
				  						{
				  		     				if(production.getQuantiteOffre()!=null)
				  		     				{
				  		     					if(production.getQuantiteOffre().compareTo(QuantiteTotalOffreMixtPFConsomme)!=0)
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "La Quantite Total Offre Consomme Est Différent de Quantite Offre Déclaré!", "Erreur", JOptionPane.ERROR_MESSAGE);
						  		     				return;
				  		     					}
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     				
				  						}
				  		     				
				  		     				
				  		     				
				  		     				listCoutHorsProductionEnAttent.clear();
				  		     				Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				  		     				 
				  		     				
				  		     		 		/* délai des employés Hors Production*/
				  		     				
				  		     				boolean ErreurTotalHeursHorsproductionEnAttente=false;
				  		     				String EmployeMatriculeErreur="";
						  		     		for(int j=0;j<TableDetailCoutHorsProductionEnAttent.getRowCount();j++){
						  		     			boolean selected=Boolean.valueOf(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 7).toString()) ;
						  		     			
						  		     			if(selected==true)
						  		     			{
						  		     				if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 4).toString().equals("")){
								  		     			mapDelaiEmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 4).toString());
								  		     			//delaiTotalEqu=delaiTotalEquipeEmbalage.add(new BigDecimal(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 3).toString())) ;
								  		     			}
								  		     			else 
								  		     				mapDelaiEmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
								  		     			
								  		     			if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 5).toString().equals("")){
								  		     				mapHeureSupp25EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 5).toString());
								  		     			}else 
								  		     				mapHeureSupp25EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
							  		     			
							  		     			if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 6).toString().equals("")){
							  		     				mapHeureSupp50EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 6).toString());
							  		     			}else 
							  		     				mapHeureSupp50EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
								  		     			
								  		     		
							  		     			
							  		     			
							  		     			
							  		     			Employe employe=mapMatriculeEmploye.get(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString());
							  		     			
							  		     			
							  		     			
							  		     			
							  		     			
							  		     			if(employe!=null)
							  		     			{
							  		     				
							  		     				CoutHorsProdEnAttent coutHorsProdEnAttent=mapDeatailCoutHorsProductionEnAttente.get(employe.getMatricule()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString());
							  		     				
							  		     	 if(coutHorsProdEnAttent!=null)
							  		     	 {
							  		     		 
							  		     		 
							  		     		 
							  		     			coutHorsProdEnAttent.setEtat(ETAT_VALIDER);
							  		     			coutHorsProdEnAttent.setProduction(production);
							  		     			CoutHorsProdEnAttentDAO.edit(coutHorsProdEnAttent);
							  		     		 
							  		     		
							  		     		
							  		     		
							  		     		coutTotalHorsProductionEnAttent=coutTotalHorsProductionEnAttent.add(coutHorsProdEnAttent.getDelaiEmploye().multiply(coutHorsProdEnAttent.getCoutHoraire()).add(coutHorsProdEnAttent.getHeure25().multiply(coutHorsProdEnAttent.getCoutHoraire25())).add(coutHorsProdEnAttent.getHeure50().multiply(coutHorsProdEnAttent.getCoutHoraire50())));	
							  		     		
							  		     		 
							  		     	 }
							  		     			
							  		     			}
							  		     			
						  		     			}
						  		     			
						  		     
					  		     			
					  		     			
						  		     		
						  		     		}
						  		     		
						  		     		 
						  		     		
						  		     		
						  		     		
						  		     		
						  		     		
						  		     		
						  		     		
						  		     		
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			BigDecimal coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalMP);
				  		     			
				  		     		production.setNbreHeure(new BigDecimal(txtPrixServiceProd.getText()));
				  		     		production.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
				  		     		production.setDateDebFabRee(new Date());
				  		     		production.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		/* délai des employés Production*/
				  		     		for(int j=0;j<tableEmploye.getRowCount();j++){
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 3).toString().equals("")){
				  		     			mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 3).toString());
				  		     			delaiTotal=delaiTotal.add(new BigDecimal(tableEmploye.getValueAt(j, 3).toString())) ;
				  		     			}else 
			  		     				mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 4).toString().equals("")){
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 4).toString());
					  		     			}else 
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 5).toString().equals("")){
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 5).toString());
				  		     			}else 
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     		}
				  		     		
				  		     		/* délai des employés Emabalege*/
				  		     		for(int j=0;j<table_1.getRowCount();j++){
				  		     			if(!table_1.getValueAt(j, 3).toString().equals("")){
				  		     			mapDelaiEmployeEmabalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), table_1.getValueAt(j, 3).toString());
				  		     			delaiTotalEquipeEmbalage=delaiTotalEquipeEmbalage.add(new BigDecimal(table_1.getValueAt(j, 3).toString())) ;
				  		     			}
				  		     			else 
				  		     				mapDelaiEmployeEmabalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!table_1.getValueAt(j, 4).toString().equals("")){
				  		     				mapHeureSupp25EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), table_1.getValueAt(j, 4).toString());
				  		     			}else 
				  		     				mapHeureSupp25EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), String.valueOf(0));
			  		     			
			  		     			if(!table_1.getValueAt(j, 5).toString().equals("")){
			  		     				mapHeureSupp50EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), table_1.getValueAt(j, 5).toString());
			  		     			}else 
			  		     				mapHeureSupp50EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			}
				  		     		
				  		    
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		listeDetailProdGen=productionDAO.listeDetailProdGen(production.getId());
				  		     		listDetailProduction=productionDAO.listeDetailProduction(production.getId());
				  		     		
				  		     		
				  		     		production.setDetailProductions(calculeCoutEmploye(listDetailProduction,mapDelaiEmploye));
				  		     		production.setListDetailProdGen(calculeCoutEmployeEmbalage(listeDetailProdGen,mapDelaiEmployeEmabalage));
				  		     	 
				  		     		 
				  		     		production.setListCoutMP(calculeCoutMatierePremiere(production.getListCoutMP()));
				  		     		
				  		     		production.setDateDebFabRee(new Date());
				  		     		






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	     		
				  		     		
				  		     		
				  		     		//production.setListDetailResponsableProd(listDetailResponsableProd);
				  		     		production.setCoutTotalHorsProductionEnAttent(coutTotalHorsProductionEnAttent);
				  		     		production.setCoutTotalMP(coutTotalMP);
				  		     		production.setCoutTotalEmployeGen(coutTotalAutreEmploye);
				  		     		production.setCoutTotalEmployeEmbalage( coutTotalEmployeEmbalage);
				  		     		production.setCoutTotalEmploye(coutTotalEmploye);
				  		     		production.setCoutDechet(coutTotalDechet);
			  		     		
				  		     		
				  		     		coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalMP).add(coutTotalDechet).add(coutTotalHorsProductionEnAttent);
				  		     		production.setCoutTotal(coutTotal);
				  		     		production.setStatut(Constantes.ETAT_OF_TERMINER);
				  		     		productionDAO.edit(production);
				  		     		
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	/*	
				  		     		// Ajouter les quantite Retour au transfere stock MP de type retour 
				  		     		
				  		     		if(listDetailTransfertMPRetour.size()!=0)
				  		     		{
				  		     			
				  		     			transferStockMPTmp.setCodeTransfer(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_RETOUR));
				  		     			transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
				  		     			transferStockMPTmp.setDate(new Date());
				  		     			transferStockMPTmp.setDateTransfer(production.getDate_debFabPre());
				  		     			Depot depot=depotdao.findByCode(production.getCodeDepot());
				  		     			transferStockMPTmp.setDepot(depot);
				  		     			transferStockMPTmp.setStatut(ETAT_TRANSFER_RETOUR);
				  		     			transferStockMPTmp.setListDetailTransferMP(listDetailTransfertMPRetour);
				  		     		    transferstockmpDAO.add(transferStockMPTmp);	
				  		     		}
				  		     		*/
				  		     		
				  		     	 List<DetailProdRes> listeDetailResponsableProd =detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
		
				  		     		
				  		     		
				  		     	 int numberproduction=1;
				  		 	  
				  		  	 
				  		  	 
				  		  	 for(int j=0;j<listeDetailResponsableProd.size();j++)
				  		  	 {
				  		  		 numberproduction=listeDetailResponsableProd.get(j).getNbrProduction();
				  		  	 }
				  		  	 if(numberproduction==0)
				  		  	 {
				  		  		 numberproduction=1; 
				  		  	 }
				  		     		
				  		     		 
					  				if(numberproduction==1)
					  				{
					  					
					  					calculRemiseResponsableProduction(production.getDate(), production.getPeriode());
					  					
					  				}else
					  				{
					  					
					  					
					  					ModifierCoutDetailProdresponsable(production.getDate(), production.getPeriode());
					  					
					  					
					  				}
					  				
					  				
					  				calculerStockCoutProduitFini(coutTotal);
					  				
					  				
	    listStatistiqueFinanciereMP=StatistiqueFinanciereMPDAO.findAll();
	    StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listStatistiqueFinanciereMP.get(listStatistiqueFinanciereMP.size()-1);
				  		      
				  		     		
				  		     		
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
			  		     			
			  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
			  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage());
			  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac());
			  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction().subtract(production.getCoutTotalMP().add(production.getCoutDechet())));
			  		     			statistiqueFinanciaireMP.setCodeTransfer(production.getNumOF());
			  		     			statistiqueFinanciaireMP.setDate(new Date());
			  		     			statistiqueFinanciaireMP.setDateOperation(production.getDate());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction().add(production.getCoutTotalEmployeGen().add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalHorsProductionEnAttent))  );
			  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
			  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
			  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF().add(production.getCoutTotal()) );
			  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
			  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
			  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
			  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
			  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
			  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
			  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_TERMINER+" "+Constantes.PROD_PF);
			  		     		 
			  		     			StatistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
					  					
				  		     		
				  		     		
				  		     		
				  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication Terminé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		
				  		     		
				  		     		int rep = JOptionPane.showConfirmDialog(null, "Vous voulez créer une facture de service ?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(rep == JOptionPane.YES_OPTION )
										{
										List<DetailFactureProduction> listDetailFactureProduction= new ArrayList<DetailFactureProduction>();
										DetailFactureProduction detailFactureProduction= new DetailFactureProduction();
										FactureProduction factureProduction = new FactureProduction();
										
										ClientDAO clientDAO =new ClientDAOImpl();
										
										
										Client clientFournisseur=new  Client();
										Client client=new  Client();
										
										if(production.getNumOF().substring(0, 1).equals(DEBUT_NUM_OF_PRODUCTION_TANTAN))
											 clientFournisseur=clientDAO.findClientByCodeClient(CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_TANTAN);
										
										else if (production.getNumOF().substring(0, 1).equals(DEBUT_NUM_OF_PRODUCTION_LAAYOUNE))
											 clientFournisseur=clientDAO.findClientByCodeClient(CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_LAAYOUNE);
										
										client=clientDAO.findClientByCodeClient(production.getMagasinStockage().getCodeMachine());
										
										/*Creation service production*/
										MatierePremier matierePremier=matierePremiereDAO.findByCode(MATIERE_PREMIERE_SERVICE_PRODUCTION);
										
										
										/**/
										
										/*creation de la facture */
										BigDecimal montantGlobalFacture=BigDecimal.ZERO;
										BigDecimal prixFacture=new BigDecimal(txtPrixServiceProd.getText());
										BigDecimal quantite=new BigDecimal(txtQuantiteRealise.getText());
										BigDecimal montantTotal =prixFacture.multiply(quantite);
										detailFactureProduction.setMatierePremier(matierePremier);
										detailFactureProduction.setPrixUnitaire(prixFacture);
										detailFactureProduction.setQuantite(quantite);
										detailFactureProduction.setMontantTotal(montantTotal);
										detailFactureProduction.setFactureProduction(factureProduction);
										listDetailFactureProduction.add(detailFactureProduction);
										 montantGlobalFacture=montantGlobalFacture.add(montantTotal);
										
										factureProduction.setClientFournisseurMP(clientFournisseur);
										factureProduction.setClientMP(client);
										factureProduction.setCreerPar(AuthentificationView.utilisateur);
										factureProduction.setDateFacture(new Date());
										factureProduction.setDetailFactureProduction(listDetailFactureProduction);
										factureProduction.setMontantTotal(montantGlobalFacture);
										factureProduction.setNumFacture(Utils.genererNumFactureProduction(FACTURE_PRODUCTION_LIBELLE));
										factureProduction.setNumOF(production.getNumOF());
										

										factureProductionDAO.add(factureProduction);
										JOptionPane.showMessageDialog(null, "Facture a été crée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
										}
				  		     			}
				  		     		
				  		     		}else{
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication n'est pas encore lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     	  }
				  		     	 }
				  		     	}
				  		     });
				  		    	btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     
				  		     table_1.setShowVerticalLines(true);
				  		     table_1.setSelectionBackground(new Color(51, 204, 255));
				  		     table_1.setRowHeightEnabled(true);
				  		     table_1.setBackground(new Color(255, 255, 255));
				  		     table_1.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table_1.setColumnControlVisible(true);
				  		     table_1.setForeground(Color.BLACK);
				  		     table_1.setGridColor(new Color(0, 0, 255));
				  		     table_1.setAutoCreateRowSorter(true);
				  		     table_1.setBounds(2, 27, 411, 198);
				  		     table_1.setRowHeight(20);
				  		   DefaultCellEditor ce1 = (DefaultCellEditor) table_1.getDefaultEditor(Object.class);
					        JTextComponent textField1 = (JTextComponent) ce1.getComponent();
					        util.Utils.copycollercell(textField1);
				  		     table.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyTyped(KeyEvent arg0) {
				  		     		
				  		     	
				  		     		
				  		     	}
				  		     });
				  		     table.addMouseListener(new MouseAdapter() {	
				  		    	 @Override
				  		     	public void mouseClicked(MouseEvent e) {
				  		    		 
				  		    	 afficher_tableFournisseur(listFournisseurMP);
				  		    	 tableOffreVariante.setVisible(false);
				  		    	scrollPane_6.setVisible(false);
				  		    	validerSaisie=false;
				  		    	 
				  		    	 
				  		     	}
});
				  		     table.setShowVerticalLines(true);
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(8, 80, 1404, 186);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(7, 59, 1323, 23);
				  		     	add(titledSeparator);
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(7, 379, 877, 164);
		add(scrollPane_1);
		tableEmploye.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEmploye.setShowVerticalLines(true);
		tableEmploye.setSelectionBackground(new Color(51, 204, 255));
		tableEmploye.setRowHeightEnabled(true);
		tableEmploye.setRowHeight(20);
		tableEmploye.setGridColor(new Color(0, 0, 255));
		tableEmploye.setForeground(Color.BLACK);
		tableEmploye.setColumnControlVisible(true);
		tableEmploye.setBackground(new Color(255, 255, 255));
		tableEmploye.setAutoCreateRowSorter(true);
	//	scrollPane_1.setViewportView(tableEmploye);
		 DefaultCellEditor ce2 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
	        JTextComponent textField2 = (JTextComponent) ce2.getComponent();
	        util.Utils.copycollercell(textField2);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(8, 277, 859, 17);
		add(titledSeparator_1);
				  		    		  		     	
				  		    		  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		    		  		     	layeredPane.setBounds(9, 34, 1403, 29);
				  		    		  		     	add(layeredPane);
				  		    		  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		    
				  	 
				  		    
				  		    JButton btnValiderSaisie = new JButton("Valider Saisie");
				  		    btnValiderSaisie.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		
				  		    		remplirQuantite();
				  		    		List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
								  	afficherDetailPorduction(production.getArticles().getDetailEstimation(),listCoutMPTmp);
								  	validerSaisiQuantiteConsomme(listCoutMPTmp);
								  	if(msgErreur.equals(""))
								  	{
								  		ValiderSaisi=true;
								  	}else
								  	{
								  		validerSaisie=false;
								  		return;
								  	}
								  	
				  		    		
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnValiderSaisie.setBounds(425, 822, 102, 24);
				  		    add(btnValiderSaisie);
				  		    
				  		  
				  		    txtQuantiteRealise.setBounds(345, 6, 153, 26);
				  		    add(txtQuantiteRealise);
				  		    txtQuantiteRealise.setColumns(10);
				  		    
				  		    lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
				  		    lblQuantitRalise.setBounds(242, 6, 102, 26);
				  		    add(lblQuantitRalise);
				  		    lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JScrollPane scrollPane_2 = new JScrollPane(table_1);
				  		    scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		    scrollPane_2.setBounds(963, 359, 887, 145);
				  		    add(scrollPane_2);
				  		    
				  		    JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
				  		    GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
				  		    gridBagLayout_1.rowWeights = new double[]{0.0};
				  		    gridBagLayout_1.rowHeights = new int[]{0};
				  		    gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		    gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
				  		    titledSeparator_2.setTitle("Saisir D\u00E9lai Equipe Embalage");
				  		    titledSeparator_2.setBounds(961, 277, 932, 17);
				  		    add(titledSeparator_2);
				  		    
				  		   
				  		    txtPrixServiceProd.setBounds(654, 6, 120, 26);
				  		    add(txtPrixServiceProd);
				  		    txtPrixServiceProd.setColumns(10);
				  		    
				  		    JLabel lblQuantite = new JLabel("Delai Service Production :");
				  		    lblQuantite.setBounds(527, 6, 133, 26);
				  		    add(lblQuantite);
				  		    lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JButton btnAnnuler = new JButton("Annuler");
				  		    btnAnnuler.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		if(production.getId()<0){
				  		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			
				  		    		}else {
				  		    			
				  		    			
				  		    			if(!production.getStatut().equals(ETAT_OF_ANNULER)){
				  		    				 if(production.getStatut().equals(ETAT_OF_TERMINER)){
				  		    					 
				  		    					CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
								    			 int compteurProd=compteurProduction.getCompteur();
								    			 compteurProd=compteurProd-1;
								    			 compteurProduction.setCompteur(compteurProd);
				  		    					 
				  		    			annulerStockMatierePremiere(production.getListCoutMP(),production.getMagasinProd().getId(),production.getMagasinStockage().getId());
				  		    			
				  		    			annulerStockProduitFini();
				  		    			
				  		    			annulerDetailCoutHorsproductionEnAttent();
				  		    			
				  		    			
				  		    			
				  		    			//deleteListeObject(ficheEmployeDAO.findByNumOf(production.getNumOF()));
				  		    			production.setStatut(ETAT_OF_ANNULER);
				  		    			production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
				  		    			productionDAO.edit(production);
				  		    			compteurProductionDAO.edit(compteurProduction);
				  		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }else{
				  		    					JOptionPane.showMessageDialog(null, "OF doit étre Terminé", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }
				  		    			
				  		    			}else{
				  		    				JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			}
				  		    		}
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnAnnuler.setBounds(326, 823, 89, 23);
				  		    add(btnAnnuler);
				  		    
				  		    JButton btnFactureService = new JButton("Facture Service ");
				  		    btnFactureService.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		FactureProduction	factureProduction=factureProductionDAO.findFactureProductionByNumOF(production.getNumOF());

						  		  	if(factureProduction.getId()>0){
						  		  	List<DetailFactureProduction> listDetailFactureProduction =new ArrayList<DetailFactureProduction>();
						  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	String date=dateFormat.format(factureProduction.getDateFacture());
						  		  listDetailFactureProduction=factureProduction.getDetailFactureProduction();
						  		  
						  		BigDecimal tva20=(BigDecimal) (factureProduction.getMontantTotal().multiply(new BigDecimal(0.2)));
						  		BigDecimal totalTTC=tva20.add(factureProduction.getMontantTotal()) ;
									 
									Map parameters = new HashMap();
									parameters.put("nomClientFour", factureProduction.getClientFournisseurMP().getNom());
									parameters.put("adresseClientFour", factureProduction.getClientFournisseurMP().getAdresse());
									parameters.put("telClienFour", factureProduction.getClientFournisseurMP().getNumTel());
									parameters.put("numFacture", factureProduction.getNumFacture());
									parameters.put("dateFacture", date);
									parameters.put("nomClient", factureProduction.getClientMP().getNom());
									parameters.put("adresseClient", factureProduction.getClientMP().getAdresse());
									parameters.put("telClient", factureProduction.getClientMP().getNumTel());
									parameters.put("totalHorsTaxe", String.valueOf(factureProduction.getMontantTotal()));
									parameters.put("tva20", String.valueOf(tva20));
									parameters.put("totalTTC", String.valueOf(totalTTC));
									
								
									
									
									JasperUtils.imprimerFacutreProduction(listDetailFactureProduction,parameters,factureProduction.getNumFacture());
									
									//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  	}else {
						  		  	JOptionPane.showMessageDialog(null, "Facture n'existe pas !!", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
						  		  	}
				  		    		
				  		    	}
				  		    });
				  		    btnFactureService.setBounds(783, 822, 112, 23);
				  		    add(btnFactureService);
				  		    
				  		    JButton btnSaisirDelaiEmploy_1 = new JButton("Saisir Delai Employ\u00E9 Production");
				  		    btnSaisirDelaiEmploy_1.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent arg0) {
				  		    		if(production.getId()>0)
				  		    		{
				  		    			JFrame popupJFrame = new ListeEmploye(production,txtQuantiteRealise.getText(),txtPrixServiceProd.getText());
						  		    	  popupJFrame.setVisible(true);
				  		    		}else
				  		    		{
				  		    			JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		    		}
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnSaisirDelaiEmploy_1.setBounds(9, 824, 185, 23);
				  		  btnSaisirDelaiEmploy_1.setVisible(false);
				  		    add(btnSaisirDelaiEmploy_1);
				  		    
				  		    JButton btnSaisirDelaiEmploy = new JButton("Saisir Delai Employ\u00E9 Emballage");
				  		    btnSaisirDelaiEmploy.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent arg0) {
				  		    		if(production.getId()>0)
				  		    		{
				  		    		JFrame popupJFrame = new ListeEmployeEmballage(production,txtQuantiteRealise.getText(),txtPrixServiceProd.getText());
					  		    	  popupJFrame.setVisible(true);
				  		    		}
				  		    		else
				  		    		{
				  		    			JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		    		}
				  		    	}
				  		    });
				  		    btnSaisirDelaiEmploy.setBounds(910, 822, 185, 23);
				  		  btnSaisirDelaiEmploy.setVisible(false);
				  		    add(btnSaisirDelaiEmploy);
				  		    
				  		    comboEquipe.setBounds(1065, 36, 157, 24);
				  		    add(comboEquipe);
				  		    
				  		    JLabel lblEquipe = new JLabel("Equipe");
				  		    lblEquipe.setBounds(1020, 35, 51, 26);
				  		    add(lblEquipe);
				  		   
				  		    comboLigneMachine.setBounds(853, 36, 157, 24);
				  		    add(comboLigneMachine);
				  		    
				  		   JLabel lblGroupe = new JLabel("Ligne Machine");
				  		   lblGroupe.setBounds(764, 35, 77, 24);
				  		   add(lblGroupe);
				  		   lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   comboMachine.setBounds(597, 36, 157, 24);
				  		   add(comboMachine);
				  		   
				  		   JLabel lblMachine = new JLabel("Machine");
				  		   lblMachine.setBounds(538, 35, 58, 24);
				  		   add(lblMachine);
				  		   lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   categorie.setBounds(319, 35, 194, 26);
				  		   add(categorie);
				  		   
				  		   categorie.setForeground(Color.BLACK);
				  		   categorie.setBackground(Color.WHITE);
				  		   
				  		   JLabel label = new JLabel("Article:");
				  		   label.setBounds(262, 34, 102, 26);
				  		   add(label);
				  		   label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   codeArticle.setBounds(83, 35, 153, 26);
				  		   add(codeArticle);
				  		   
				  		   codeArticle.setDisabledTextColor(Color.BLACK);
				  		   codeArticle.setBackground(Color.WHITE);
				  		   codeArticle.setEnabled(false);
				  		   codeArticle.setColumns(10);
				  		   
				  		     JLabel lblCodeArticle = new JLabel("Code Article");
				  		     lblCodeArticle.setBounds(9, 35, 82, 26);
				  		     add(lblCodeArticle);
				  		     lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     
				  		     JScrollPane scrollPane_3 = new JScrollPane(tableEmployeGen);
				  		     
				  		     scrollPane_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane_3.setBounds(963, 583, 887, 145);
				  		     add(scrollPane_3);
				  		   
				  		     tableEmployeGen.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     tableEmployeGen.setShowVerticalLines(true);
				  		     tableEmployeGen.setSelectionBackground(new Color(51, 204, 255));
				  		     tableEmployeGen.setRowHeightEnabled(true);
				  		     tableEmployeGen.setRowHeight(20);
				  		     tableEmployeGen.setGridColor(new Color(0, 0, 255));
				  		     tableEmployeGen.setForeground(Color.BLACK);
				  		     tableEmployeGen.setColumnControlVisible(true);
				  		     tableEmployeGen.setBackground(new Color(255, 255, 255));
				  		     tableEmployeGen.setAutoCreateRowSorter(true);
				  		     
				  		     
				  		    scrollPane_6 = new JScrollPane(tableOffreVariante);
				  		 scrollPane_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
							scrollPane_6.setBounds(1422, 42, 496, 229);
							add(scrollPane_6);

							tableOffreVariante = new JTable();
							tableOffreVariante.setModel(new DefaultTableModel(
					  			new Object[][] {
					  			},
					  			new String[] {
					  					"OffrePF", "Nombre Carton"
					  			})
					  			 {
							     	boolean[] columnEditables = new boolean[] {
							     			false,true
							     	};
							     	public boolean isCellEditable(int row, int column) {
							     		return columnEditables[column];
							     	}});
					  		
							tableOffreVariante.setFillsViewportHeight(true);
							tableOffreVariante.getTableHeader().setReorderingAllowed(false);
							scrollPane_6.setViewportView(tableOffreVariante);
				  		     
				  		     
				  		     JButton supprimerEquipeProduction = new JButton("");
				  		     supprimerEquipeProduction.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     	
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
								 if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER))
								 {
									 JOptionPane.showMessageDialog(null, "OF est  Déja Terminer", "Erreur",JOptionPane.ERROR_MESSAGE);
									 return;
								 }
				  		     		if(listDetailProductionTMP.size()!=0)
				  		     		{
				  		     			if(tableEmploye.getSelectedRow()!=-1)
					  		     		{
					  		     			DetailProduction detailProduction=listDetailProductionTMP.get(tableEmploye.getSelectedRow()) ; 
					  		     			
					  		     			Production productionTmp=productionDAO.findByNumOFStatut(detailProduction.getProduction().getNumOF(), Constantes.ETAT_OF_LANCER);
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													
												  detailProductionDAO.delete(detailProduction.getId());
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 
												  listDetailProductionTMP.remove(tableEmploye.getSelectedRow());
												  productionTmp.setDetailProductions(listDetailProductionTMP);
												  productionDAO.edit(productionTmp);
													
												  afficher_tableEmploye(listDetailProductionTMP);
													
												}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF n'est pas lancé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
					  		     				return;
					  		     			}
					  		     			
					  		     			
					  		     			
					  		     		}
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     supprimerEquipeProduction.setBounds(894, 475, 58, 23);
				  		   supprimerEquipeProduction.setIcon(imgSupprimer);
				  		     add(supprimerEquipeProduction);
				  		     
				  		     JButton supprimeremployeempllage = new JButton("");
				  		     supprimeremployeempllage.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
								 if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER))
								 {
									 JOptionPane.showMessageDialog(null, "OF est  Déja Terminer", "Erreur",JOptionPane.ERROR_MESSAGE);
									 return;
								 }
				  		     		
				  		     		if(listDetailProdGenTmp.size()!=0)
				  		     		{
				  		     			if(table_1.getSelectedRow()!=-1)
					  		     		{
					  		     			DetailProdGen detailProdGen=listDetailProdGenTmp.get(table_1.getSelectedRow());
					  		     			
					  		     			Production productionTmp=productionDAO.findByNumOFStatut(detailProdGen.getProductionGen().getNumOF(), Constantes.ETAT_OF_LANCER);
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													
													detailProdGenDAO.delete(detailProdGen.getId());
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 
												  listDetailProdGenTmp.remove(table_1.getSelectedRow());
												  productionTmp.setListDetailProdGen(listDetailProdGenTmp);
												  productionDAO.edit(productionTmp);
													
												  afficher_tableEmployeEmabalage(listDetailProdGenTmp);
													
												}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF n'est pas lancé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
					  		     				return;
					  		     			}
					  		     			
					  		     			
					  		     			
					  		     		}
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     supprimeremployeempllage.setBounds(1860, 450, 58, 23);
				  		   supprimeremployeempllage.setIcon(imgSupprimer);
				  		     add(supprimeremployeempllage);
				  		     
				  		     JButton supprimeremployergen = new JButton("");
				  		     supprimeremployergen.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		

				  		     		
				  		     		
				  		     		if(listDetailResponsableProdTmp.size()!=0)
				  		     		{
				  		     			
				  		     			
				  		     			// int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
				  		     		Production production=	productionDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER);
						  				if(production==null)
						  				{
						  				
						  					if(tableEmployeGen.getSelectedRow()!=-1)
						  		     		{
						  		     			DetailProdRes detailResponsableProd=listDetailResponsableProdTmp.get(tableEmployeGen.getSelectedRow());
					  		     				
						  		     			
						  		     		
						  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
															"Satisfaction", JOptionPane.YES_NO_OPTION);
													 
													if(reponse == JOptionPane.YES_OPTION )
													{
														
														
													  
													  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
													  detailProdResDAO.delete(detailResponsableProd.getId());
													  listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow());
													 // productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp);
													 // productionDAO.edit(productionTmp);
														
													  afficher_tableEmployeGen(listDetailResponsableProdTmp);
														
													}
						  		     				
						  		     		}
						  					
						  				}else
						  				{
						  					
						  					JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF est terminé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
				  		     				return;
						  					
						  				}
				  		     			
				  		     			
				       			
				  		     		
					/*
					 * if(tableEmployeGen.getSelectedRow()!=-1) { DetailResponsableProd
					 * detailResponsableProd=production.getListDetailResponsableProd().get(
					 * tableEmployeGen.getSelectedRow());
					 * 
					 * 
					 * Production
					 * productionTmp=productionDAO.findByNumOFStatut(detailResponsableProd.
					 * getProduction().getNumOF(), Constantes.ETAT_OF_LANCER);
					 * if(productionTmp!=null) { int reponse = JOptionPane.showConfirmDialog(null,
					 * "Vous voulez vraiment Supprimer l'employer ?", "Satisfaction",
					 * JOptionPane.YES_NO_OPTION);
					 * 
					 * if(reponse == JOptionPane.YES_OPTION ) {
					 * 
					 * detailResponsableDAO.delete(detailResponsableProd.getId());
					 * 
					 * JOptionPane.showMessageDialog(null,
					 * "Employé supprimer avec succée ","Satisfaction",JOptionPane.
					 * INFORMATION_MESSAGE);
					 * detailProdResDAO.delete(listDetailResponsableProdTmp.get(tableEmployeGen.
					 * getSelectedRow()).getId());
					 *listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow()); //
					 * productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp); //
					 * productionDAO.edit(productionTmp);
					 * 
					 * afficher_tableEmployeGen(listDetailResponsableProdTmp);
					 * 
					 * }
					 * 
					 * 
					 * }else { JOptionPane.showMessageDialog(null,
					 * "Impossible de supprimer employé dont le OF est terminé !!!!","Erreur",
					 * JOptionPane.ERROR_MESSAGE ); return; }
					 * 
					 * 
					 * 
					 * }
					 */
				  		     				  		     			
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     supprimeremployergen.setBounds(1860, 667, 58, 23);
				  		   supprimeremployergen.setIcon(imgSupprimer);
				  		     add(supprimeremployergen);
				  		     
				  		     JButton btnExporterExcel = new JButton("Exporter excel");
				  		     btnExporterExcel.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		

					  				

						    		Magasin magasin=production.getMagasinStockage();
						    		if(magasin!=null)
						    		{
						    		
						    		String titre="Terminer OF Numero  "+txtNumOF.getSelectedItem().toString()+ " Magasin "+magasin.getLibelle();
						    		String titrefeuille="Terminer OF ";
						    		ExporterTableVersExcel.tabletoexcel(table, titre,titrefeuille);
						    		
						    		}else
						    		{


						    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
						    			return;
						    		
						    		
						    		}
					  				
					  				
					  				
					  				
					  				
					  				
					  				
					  			
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnExporterExcel.setBounds(537, 823, 102, 24);
				  		     add(btnExporterExcel);
				  		     
				  		     JScrollPane scrollPane_4 = new JScrollPane((Component) null);
				  		     scrollPane_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane_4.setBounds(1422, 39, 471, 206);
				  		     add(scrollPane_4);
				  		     
				  		     TableFournisseur = new JTable();
				  		     TableFournisseur.setModel(new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     		"Fournisseur", "Fournisseur Dechet"
				  		     	}
				  		     ));
				  		     TableFournisseur.getColumnModel().getColumn(0).setPreferredWidth(94);
				  		     TableFournisseur.getColumnModel().getColumn(1).setPreferredWidth(121);
				  		     TableFournisseur.setFillsViewportHeight(true);
				  		     TableFournisseur.getTableHeader().setReorderingAllowed(false); 
				  		     scrollPane_4.setViewportView(TableFournisseur);
				  		     
				  		     JButton btnValider = new JButton("Valider");
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		String fournisseurdechet="";
				  		     		
				  		     		
				  		     		for(int i=0; i<TableFournisseur.getRowCount();i++)
				  		     		{
				  		     			
				  		     			boolean fournisseurdeche=(boolean) TableFournisseur.getValueAt(i, 1);
				  		     			
				  		     			
				  		  			if(fournisseurdeche==true ){
				  		  				if(fournisseurdechet.equals(""))
				  		  				{
				  		  				fournisseurdechet=fournisseurdechet+TableFournisseur.getValueAt(i, 0);
				  		  				}else
				  		  				{
				  		  					
				  		  				fournisseurdechet=fournisseurdechet+", "+TableFournisseur.getValueAt(i, 0);
				  		  				}
				  		  			
				  		  					
				  		  			}
				  		     		
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		if(!fournisseurdechet.equals(""))
				  		     		{
				  		     			
				  		     			table.setValueAt(fournisseurdechet,position, 14);
				  		     		}
				  		     		
				  		     		
				  		     			
				  		     		afficher_tableFournisseur(listFournisseurMP);
				  		     	}
				  		     });
				  		     btnValider.setBounds(1549, 247, 102, 24);
				  		     add(btnValider);
				  		     
				  		     JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
				  		     GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_3.getLayout();
				  		     gridBagLayout_2.rowWeights = new double[]{0.0};
				  		     gridBagLayout_2.rowHeights = new int[]{0};
				  		     gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
				  		     titledSeparator_3.setTitle("Saisir D\u00E9lai Equipe Generique");
				  		     titledSeparator_3.setBounds(963, 515, 803, 17);
				  		     add(titledSeparator_3);
				  		     
				  		     txtcodeemployeproduction = new JTextField();
				  		     txtcodeemployeproduction.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		

				  					
				  					if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeproduction.getText());
				  						if(employe!=null)
				  						{
				  							comboemployeproduction.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  				
				  					
				  					
				  					
				  					
				  				
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployeproduction.setColumns(10);
				  		     txtcodeemployeproduction.setBounds(44, 333, 71, 26);
				  		     add(txtcodeemployeproduction);
				  		     
				  		     JLabel Code = new JLabel("Code :");
				  		     Code.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     Code.setBounds(8, 330, 37, 26);
				  		     add(Code);
				  		     
				  		     JLabel lblEmploye = new JLabel("Employe :");
				  		     lblEmploye.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblEmploye.setBounds(125, 333, 64, 26);
				  		     add(lblEmploye);
				  		     
				  		     txtdelaiproduction = new JTextField();
				  		     txtdelaiproduction.setColumns(10);
				  		     txtdelaiproduction.setBounds(389, 339, 58, 26);
				  		     add(txtdelaiproduction);
				  		     
				  		     JLabel lblDelaiH = new JLabel("Delai H :");
				  		     lblDelaiH.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblDelaiH.setBounds(340, 340, 61, 26);
				  		     add(lblDelaiH);
				  		     
				  		     txthsupp50production = new JTextField();
				  		     txthsupp50production.setColumns(10);
				  		     txthsupp50production.setBounds(525, 340, 58, 26);
				  		     add(txthsupp50production);
				  		     
				  		     JLabel lblHSupp = new JLabel("H Supp 50% :");
				  		     lblHSupp.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblHSupp.setBounds(453, 340, 77, 26);
				  		     add(lblHSupp);
				  		     
				  		     JLabel lblHSupp_1 = new JLabel("H Supp 25% :");
				  		     lblHSupp_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblHSupp_1.setBounds(593, 340, 77, 26);
				  		     add(lblHSupp_1);
				  		     
				  		     txthsupp25production = new JTextField();
				  		     txthsupp25production.setColumns(10);
				  		     txthsupp25production.setBounds(667, 339, 58, 26);
				  		     add(txthsupp25production);
				  		     
				  		      checkabsentproduction = new JCheckBox("Absent");
				  		     checkabsentproduction.setBounds(730, 342, 76, 23);
				  		     add(checkabsentproduction);
				  		     
				  		      checksortieproduction = new JCheckBox("Sortie");
				  		     checksortieproduction.setBounds(806, 342, 71, 23);
				  		     add(checksortieproduction);
				  		     
				  		     JLabel label_1 = new JLabel("Code :");
				  		     label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_1.setBounds(956, 539, 37, 26);
				  		     add(label_1);
				  		     
				  		     txtcodeemployegenerique = new JTextField();
				  		     txtcodeemployegenerique.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		
				  		     		if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
				  						if(employe!=null)
				  						{
				  							comboemployegenerique.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployegenerique.setColumns(10);
				  		     txtcodeemployegenerique.setBounds(993, 539, 77, 26);
				  		     add(txtcodeemployegenerique);
				  		     
				  		     JLabel label_2 = new JLabel("Employe :");
				  		     label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_2.setBounds(1080, 540, 61, 26);
				  		     add(label_2);
				  		     
				  		     JLabel label_3 = new JLabel("Delai H :");
				  		     label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_3.setBounds(1305, 539, 61, 26);
				  		     add(label_3);
				  		     
				  		     txtdelaigenerique = new JTextField();
				  		     txtdelaigenerique.setColumns(10);
				  		     txtdelaigenerique.setBounds(1354, 542, 58, 26);
				  		     add(txtdelaigenerique);
				  		     
				  		     JLabel label_4 = new JLabel("H Supp 50% :");
				  		     label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_4.setBounds(1418, 539, 77, 26);
				  		     add(label_4);
				  		     
				  		     txthsupp50generique = new JTextField();
				  		     txthsupp50generique.setColumns(10);
				  		     txthsupp50generique.setBounds(1491, 539, 58, 26);
				  		     add(txthsupp50generique);
				  		     
				  		     JLabel label_5 = new JLabel("H Supp 25% :");
				  		     label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_5.setBounds(1559, 540, 77, 26);
				  		     add(label_5);
				  		     
				  		     txthsupp25generique = new JTextField();
				  		     txthsupp25generique.setColumns(10);
				  		     txthsupp25generique.setBounds(1632, 542, 58, 26);
				  		     add(txthsupp25generique);
				  		     
				  		      checkabsentgenerique = new JCheckBox("Absent");
				  		     checkabsentgenerique.setBounds(1695, 545, 71, 23);
				  		     add(checkabsentgenerique);
				  		  
				  		      checksortiegenerique = new JCheckBox("Sortie");
				  		     checksortiegenerique.setBounds(1767, 545, 71, 23);
				  		     add(checksortiegenerique);
				  		     
				  		     JLabel label_6 = new JLabel("Code :");
				  		     label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_6.setBounds(963, 322, 37, 26);
				  		     add(label_6);
				  		     
				  		     txtcodeemployeemballage = new JTextField();
				  		     txtcodeemployeemballage.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		
				  		     		
				  					if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  						if(employe!=null)
				  						{
				  							comboemployeemballage.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployeemballage.setColumns(10);
				  		     txtcodeemployeemballage.setBounds(999, 322, 77, 26);
				  		     add(txtcodeemployeemballage);
				  		     
				  		     JLabel label_7 = new JLabel("Employe :");
				  		     label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_7.setBounds(1084, 321, 61, 26);
				  		     add(label_7);
				  		     
				  		     JLabel label_8 = new JLabel("Delai H :");
				  		     label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_8.setBounds(1317, 320, 61, 26);
				  		     add(label_8);
				  		     
				  		     txtdelaiemballage = new JTextField();
				  		     txtdelaiemballage.setColumns(10);
				  		     txtdelaiemballage.setBounds(1360, 322, 58, 26);
				  		     add(txtdelaiemballage);
				  		     
				  		     JLabel label_9 = new JLabel("H Supp 50% :");
				  		     label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_9.setBounds(1428, 322, 77, 26);
				  		     add(label_9);
				  		     
				  		     txthsupp50emballage = new JTextField();
				  		     txthsupp50emballage.setColumns(10);
				  		     txthsupp50emballage.setBounds(1497, 322, 58, 26);
				  		     add(txthsupp50emballage);
				  		     
				  		     JLabel label_10 = new JLabel("H Supp 25% :");
				  		     label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_10.setBounds(1564, 319, 77, 26);
				  		     add(label_10);
				  		     
				  		     txthsupp25emballage = new JTextField();
				  		     txthsupp25emballage.setColumns(10);
				  		     txthsupp25emballage.setBounds(1637, 322, 58, 26);
				  		     add(txthsupp25emballage);
				  		     
				  		      checkabsentemballage = new JCheckBox("Absent");
				  		     checkabsentemballage.setBounds(1701, 325, 71, 23);
				  		     add(checkabsentemballage);
				  		     
				  		  
				  		      checksortieemballage = new JCheckBox("Sortie");
				  		     checksortieemballage.setBounds(1774, 325, 64, 23);
				  		     add(checksortieemballage);
				  		     
				  		     JButton btnAjouterEmployeProduction = new JButton("");
				  		     btnAjouterEmployeProduction.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
				  		     	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
								 if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER))
								 {
									 JOptionPane.showMessageDialog(null, "OF est  Déja Terminer", "Erreur",JOptionPane.ERROR_MESSAGE);
									 return;
								 }
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		  	boolean retard=false;
				  		     		int idEmploye;
				  		     		
				  		     		boolean existe=false;
				  		     		
				  		     		
				  		      	if(!txtcodeemployeproduction.getText().equals("") && !comboemployeproduction.getSelectedItem().equals(""))
				  		      	{
				  		      		
				  		      	Employe employeTmp=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
				  		      		for(int t=0;t<listDetailProductionTMP.size();t++)
				  		      		{
				  		      			
				  		      			if(listDetailProductionTMP.get(t).getEmploye().getId()==employeTmp.getId())
				  		      			{
				  		      			existe=true;
				  		      			}
				  		      			
				  		      			
				  		      			
				  		      		}
				  		      		
				  		      		if(existe==true)
				  		      		{
				  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
				  		      			return;
				  		      		}
				  		      		
				  		      		
				  		      		
				  		      	DetailProduction detailproduction=new DetailProduction();
		  		  		    	detaildelai=BigDecimal.ZERO;
		  		  		    	detailheur25=BigDecimal.ZERO;
		  		  		    	detailheur50=BigDecimal.ZERO;
		  		  		    	absent=false;
		  		  		    	sortie=false;
		  		  		    retard=false;
		  		  		    //	int key=lsiteInt.get(i);
		  		  		    	Employe employe=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
		  		  		    	
		  		  		    	if(employe!=null)
		  		  		    	{
		  		  		    		idEmploye=employe.getId();
		  		  		    	
		  		  		   
		  		  		    	if(checkabsentproduction.isSelected()==true)
		  		  		    	{
		  		  		    	
		  		  		    	absent=true;
		  		  		    	
		  		  		    	
		  		  		    	detailproduction.setEmploye(employe);
		  		  		    detailproduction.setTypeResEmploye(employe.getTypeResEmploye());
		  		  		    	detailproduction.setDelaiEmploye(detaildelai);
		  		  		    	detailproduction.setHeureSupp25(detailheur25);
		  		  		    	detailproduction.setHeureSupp50(detailheur50);
		  		  		    	detailproduction.setAbsent(absent);
		  		  		    detailproduction.setCoutTotal(detaildelai.multiply(heure.getValeur()));
	  					        detailproduction.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
	  				            detailproduction.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
	  				            
	  				          detailproduction.setCoutHoraire(heure.getValeur());
	  				        detailproduction.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
	  				      detailproduction.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
	  				      
		  		  		    detailproduction.setSortie(false);
		  		  		detailproduction.setRetard(false);
		  		  	detailproduction.setAutorisation(false);
		  		  detailproduction.setValider(Constantes.ETAT_INVALIDER);
		  		  		    	detailproduction.setProduction(production);
		  		  		    	listDetailProductionTMP.add(detailproduction);
		  		  		    		
		  		  		    	}
		  		  		    	
		  		  		    	else if(checkabsentproduction.isSelected()==false)
		  		  		    	{
		  		  		    	
		  		  		    		if(!txtdelaiproduction.getText().equals(""))
		  		  		    		{
		  		  		    			
		  		  		    			if(new BigDecimal(txtdelaiproduction.getText()).compareTo(BigDecimal.ZERO) >0)
		  		  		    			{
		  		  		    				
		  		  		    				
		  		  		    				if(checksortieproduction.isSelected()==true)
		  		  		    				{
		  		  		    				sortie=true;
		  		  		    				}
 if(checkretardproduction.isSelected()==true)
		  		  		    				{
		  		  		    					retard=true;
		  		  		    				}
		  		  		    				
		  		  		    				
		  		  		    				
		  		  		    				detaildelai=new BigDecimal(txtdelaiproduction.getText());
		  		  		    				if(!txthsupp25production.getText().equals(""))
		  		  		    				{
		  		  		    					if(new BigDecimal(txthsupp25production.getText()).compareTo(BigDecimal.ZERO) >0)
		  		  		    					{
		  		  		    					detailheur25=new BigDecimal(txthsupp25production.getText());
		  		  		    					}
		  		  		    					
		  		  		    					
		  		  		    				}
		  		  		    		    		
		  		  		    			if(!txthsupp50production.getText().equals(""))
	  		  		    				{
	  		  		    					if(new BigDecimal(txthsupp50production.getText()).compareTo(BigDecimal.ZERO) >0)
	  		  		    					{
	  		  		    					detailheur50=new BigDecimal(txthsupp50production.getText());
	  		  		    					}
	  		  		    					
	  		  		    					
	  		  		    				}
		  		  		    		
		  		  		    		    	
		  		  		    		    	detailproduction.setEmploye(employe);
		  		  		    		    detailproduction.setTypeResEmploye(employe.getTypeResEmploye());
		  		  					    	detailproduction.setDelaiEmploye(detaildelai);
		  		  					    	detailproduction.setHeureSupp25(detailheur25);
		  		  					    	detailproduction.setHeureSupp50(detailheur50);
		  		  					    	detailproduction.setAbsent(absent);
		  		  					        detailproduction.setCoutTotal(detaildelai.multiply(heure.getValeur()));
		  		  					        detailproduction.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
		  		  				            detailproduction.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
		  		  				            
		  		  				        detailproduction.setCoutHoraire(heure.getValeur());
		  		  				        detailproduction.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
		  		  				      detailproduction.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
		  		  				            
		  		  					    	detailproduction.setSortie(sortie);
		  		  					        detailproduction.setRetard(retard);
		  		  					        detailproduction.setAutorisation(false);
		  		  					    	detailproduction.setProduction(production);
		  		  					    listDetailProductionTMP.add(detailproduction);
		  		  		    		
		  		  		    			}
		  		  		    			
		  		  		    			}
		  		  		    		}
		  		  		    	
		  		  		    	}
		  		  		   
		  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
		  		  		     		
		  		  		     		production.setDetailProductions(listDetailProductionTMP);
		  		  		     		//production.getDetailProductions().addAll(listDetailProduction);
		  		  		     		productionDAO.edit(production);
		  		  		     	listDetailProductionTMP.clear();
		  		  		    listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
		  		  		     	 afficher_tableEmploye(listDetailProductionTMP);
		  		  		  ViderEmployeProduction();
				  		      		
				  		      	}
				  		  		   
				  		  		    
				  		  	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		   btnAjouterEmployeProduction.setIcon(imgAjouter);
				  		     btnAjouterEmployeProduction.setBounds(895, 431, 58, 23);
				  		     add(btnAjouterEmployeProduction);
				  		     
				  		     JButton btnAjouterEmployeEmballage = new JButton("");
				  		     btnAjouterEmployeEmballage.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		
				  		     		
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
								 if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER))
								 {
									 JOptionPane.showMessageDialog(null, "OF est  Déja Terminer", "Erreur",JOptionPane.ERROR_MESSAGE);
									 return;
								 }
								 
								 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
								 
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		  	boolean retard=false;
				  		     		int idEmploye;
				  		     		boolean existe=false;
				  		      	
				  		     		Employe employeTmp=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  		      		for(int t=0;t<listDetailProdGenTmp.size();t++)
				  		      		{
				  		      			
				  		      			if(listDetailProdGenTmp.get(t).getEmploye().getId()==employeTmp.getId())
				  		      			{
				  		      			existe=true;
				  		      			}
				  		      			
				  		      			
				  		      			
				  		      		}
				  		      		
				  		      		if(existe==true)
				  		      		{
				  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
				  		      			return;
				  		      		}
				  		     		
				  		     		
				  		  		  
				  		  		    	DetailProdGen detailprodGen=new DetailProdGen();
				  		  		    	detaildelai=BigDecimal.ZERO;
				  		  		    	detailheur25=BigDecimal.ZERO;
				  		  		    	detailheur50=BigDecimal.ZERO;
				  		  		    	absent=false;
				  		  		    	sortie=false;
				  		  		    retard=false;
				  		  		    //	int key=lsiteInt.get(i);
				  		  		    	Employe employe=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  		  		    	
				  		  		    	if(employe!=null)
				  		  		    	{
				  		  		    		idEmploye=employe.getId();
				  		  		    	
				  		  		   
				  		  		    	if(checkabsentemballage.isSelected()==true)
				  		  		    	{
				  		  		    		
				  		  		    	/*	if(mapDelai.containsKey(employe.getNumDossier()))
				  		  		    		detaildelai=mapDelai.get(employe.getNumDossier());
				  		  		    	
				  		  		    	if(mapHeureSupp25.containsKey(employe.getNumDossier()))
				  		  		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
				  		  		    	
				  		  		    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
				  		  		    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());*/
				  		  		    	if(checkabsentemballage.isSelected()==true)
				  		  		    	absent=true;
				  		  		    	
				  		  		    	
				  		  		    	detailprodGen.setEmploye(employe);
				  		  		    detailprodGen.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    	detailprodGen.setDelaiEmploye(detaildelai);
				  		  		    	detailprodGen.setHeureSupp25(detailheur25);
				  		  		    	detailprodGen.setHeureSupp50(detailheur50);
				  		  		    	detailprodGen.setAbsent(absent);
				  		  		    	
				  		  		    detailprodGen.setCoutTotal(detaildelai.multiply(heure.getValeur()));
				  		  		detailprodGen.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
				  		  	detailprodGen.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
				  		  	
				  		  detailprodGen.setCoutHoraire(heure.getValeur());
				  		detailprodGen.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		detailprodGen.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  		  	
				  		  		        detailprodGen.setSortie(false);
				  		  		        detailprodGen.setRetard(false);
				  		  		        detailprodGen.setAutorisation(false);
				  		  		        detailprodGen.setValider(Constantes.ETAT_INVALIDER);
				  		  		    	detailprodGen.setProductionGen(production);
				  		  		    listDetailProdGenTmp.add(detailprodGen);
				  		  		    		
				  		  		    	}
				  		  		    	
				  		  		    	else if(checkabsentemballage.isSelected()==false)
				  		  		    	{
				  		  		    	
				  		  		    		if(!txtdelaiemballage.getText().equals(""))
				  		  		    		{
				  		  		    			
				  		  		    			if( new BigDecimal(txtdelaiemballage.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    			{
				  		  		    				
				  		  		    				if(checksortieemballage.isSelected()==true)
				  		  		    				{
				  		  		    				sortie=true;
				  		  		    				}
				  		  		    					
				  		  		    			if(checkretardemballage.isSelected()==true)
			  		  		    				{
			  		  		    				retard=true;
			  		  		    				}
				  		  		    				
				  		  		    				detaildelai=new BigDecimal(txtdelaiemballage.getText());
				  		  		    				if(!txthsupp25emballage.getText().equals(""))
				  		  		    				{
				  		  		    				if( new BigDecimal(txthsupp25emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
				  		  		    				{
				  		  		    				detailheur25=new BigDecimal(txthsupp25emballage.getText());
				  		  		    				}
				  		  		    					
				  		  		    					
				  		  		    				}
				  		  		    		    		
				  		  		    		    	
				  		  		    			if(!txthsupp50emballage.getText().equals(""))
			  		  		    				{
			  		  		    				if( new BigDecimal(txthsupp50emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
			  		  		    				{
			  		  		    				detailheur50=new BigDecimal(txthsupp50emballage.getText());
			  		  		    				}
			  		  		    					
			  		  		    					
			  		  		    				}
				  		  		    		    	
				  		  		    		    	detailprodGen.setEmploye(employe);
				  		  		    		    detailprodGen.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    		    	detailprodGen.setDelaiEmploye(detaildelai);
				  		  		    		    	detailprodGen.setHeureSupp25(detailheur25);
				  		  		    		    	detailprodGen.setHeureSupp50(detailheur50);
				  		  		    		    	detailprodGen.setAbsent(absent);
				  		  		    		    	detailprodGen.setSortie(sortie);
				  		  		    		    detailprodGen.setCoutTotal(detaildelai.multiply(heure.getValeur()));
								  		  		detailprodGen.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
								  		  	detailprodGen.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
								  		  	
								  		  detailprodGen.setCoutHoraire(heure.getValeur());
									  		detailprodGen.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
									  		detailprodGen.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
								  		  	
				  		  		    		    detailprodGen.setRetard(retard);
				  		  		    		detailprodGen.setAutorisation(false);
				  		  		    		    	detailprodGen.setProductionGen(production);
				  		  		    		    listDetailProdGenTmp.add(detailprodGen);
				  		  		    		
				  		  		    			}
				  		  		    			
				  		  		    			}
				  		  		    		}
				  		  		    	
				  		  		    	}
				  		  		     
				  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  		  		     		
				  		  		     		production.setListDetailProdGen(listDetailProdGenTmp);
				  		  		     		productionDAO.edit(production);
				  		  		     	listDetailProdGenTmp.clear();
				  		  		    listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
				  		  		     	afficher_tableEmployeEmabalage(listDetailProdGenTmp);
				  		     		ViderEmployeEmballage();
				  		     		
				  		     	}
				  		     });
				  		   btnAjouterEmployeEmballage.setIcon(imgAjouter);
				  		     btnAjouterEmployeEmballage.setBounds(1860, 416, 58, 23);
				  		     add(btnAjouterEmployeEmballage);
				  		     
				  		     JButton btnAjouterEmployeGenerique = new JButton("");
				  		     btnAjouterEmployeGenerique.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		

				  		     	 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				  		  		
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		  	boolean retard=false;
				  		     		int idEmploye;
				  		     		BigDecimal delaiComplet = BigDecimal.ZERO,detailheurComplet25=BigDecimal.ZERO,detailheurComplet50=BigDecimal.ZERO;
				  		  		CompteurProduction compteurProduction=new CompteurProduction(); 

				  		     		BigDecimal coutSupp25=BigDecimal.ZERO;
				  		  		BigDecimal coutSupp50=BigDecimal.ZERO;
				  		  	//	BigDecimal coutHoraire=0;
				  		  		BigDecimal coutHoraireComplet=BigDecimal.ZERO;
				  		  		BigDecimal coutHoraireTotal=BigDecimal.ZERO;
				  		  		 compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
				  		  		 int compteurProd=compteurProduction.getCompteur();
				  		  		 if(compteurProd<=0)
				  		  			 compteurProd=1;
				  		  		   
				  		  		    	DetailProdRes detailResponsableProd=new DetailProdRes();
				  		  		    	detaildelai=BigDecimal.ZERO;
				  		  		    	detailheur25=BigDecimal.ZERO;
				  		  		    	detailheur50=BigDecimal.ZERO;
				  		  		    	absent=false;
				  		  		    	sortie=false;
				  		  		    retard=false;
				  		  		    //	int key=lsiteInt.get(i);
				  		  		    	Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
				  		  		    	
				  		  		    	if(employe!=null)
				  		  		    	{
				  		  		    		idEmploye=employe.getId();
				  		  		    	
				  		  		   
				  		  		    	if(checkabsentgenerique.isSelected()==true)
				  		  		    	{
				  		  		    		
				  		  		    	
				  		  		    	
				  		  		    	if(checkabsentgenerique.isSelected()==true)
				  		  		    	absent=true;
				  		  		    	
				  		  		    	detailResponsableProd.setCoutSupp25(BigDecimal.ZERO);
				  		  	    		detailResponsableProd.setCoutSupp50(BigDecimal.ZERO);
				  		  	    		detailResponsableProd.setCoutTotal(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setDelaiEmploye(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setHeureSupp25(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setHeureSupp50(BigDecimal.ZERO);
				  		  		    	
				  		  		        detailResponsableProd.setCoutHoraire(heure.getValeur());
	  		  		    		        detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
	  		  		    	            detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  		  		    	
				  		  		    	detailResponsableProd.setEmploye(employe);
				  		  		        detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    	detailResponsableProd.setAbsent(absent);
				  		  		        detailResponsableProd.setSortie(false);
				  		  		        detailResponsableProd.setRetard(false);
				  		  		        detailResponsableProd.setAutorisation(false);
				  		  		        detailResponsableProd.setValider(Constantes.ETAT_INVALIDER);
				  		  		    	detailResponsableProd.setDateProduction(production.getDate());
				  		  		    	//detailResponsableProd.setProduction(production);
				  		  		    	//listDetailResponsableProd.add(detailResponsableProd);
				  		  		    	detailProdResDAO.add(detailResponsableProd);


				  		  		    		
				  		  		    	}else if(checkabsentgenerique.isSelected()==false){
				  		  		    	
				  		  		    		
				  		  		    	
				  		  		    		if(!txtdelaigenerique.getText().equals(""))
				  		  		    		{
				  		  		    			
				  		  		    			if(new BigDecimal(txtdelaigenerique.getText()).compareTo(BigDecimal.ZERO)  >0)
				  		  		    			{
				  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
				  		  		    				delaiComplet=detaildelai;///compteurProd;
				  		  		    				if(!employe.isSalarie()){
				  		  		    				
				  		  		    					
				  		  		    					if(checksortiegenerique.isSelected()==true)
				  		  		    					{
				  		  		    					sortie=true;
				  		  		    					}
				  		  			    				
				  		  		    				if(checkretardgenerique.isSelected()==true)
			  		  		    					{
			  		  		    					retard=true;
			  		  		    					}
				  		  		    					
				  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
				  		  		    				
				  		  		    			
				  		  		    				
				  		  		    				
				  		  				    		coutHoraireComplet=heure.getValeur().multiply(detaildelai);
				  		  				    		coutHoraireTotal=coutHoraireTotal.add(coutHoraireComplet);
				  		  		    				
				  		  		    				
				  		  		    				
				  		  		    				if(!txthsupp25generique.getText().equals(""))
				  		  		    					
				  		  		    				{
				  		  		    					if(new BigDecimal(txthsupp25generique.getText()).compareTo(BigDecimal.ZERO)  >0)
				  		  		    					{
				  		  		    					detailheur25=new BigDecimal(txthsupp25generique.getText());
				  		  		    					detailheurComplet25=detailheur25;///compteurProd;
				  		  		    		    		coutSupp25=detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp25);
				  		  		    					}
				  		  		    					
				  		  		    		    		
				  		  		    				}
				  		  		    				
				  		  		    		    	if(!txthsupp50generique.getText().equals(""))
				  		  		    		    		
				  		  		    		    	{
				  		  		    		    	if(new BigDecimal(txthsupp50generique.getText()).compareTo(BigDecimal.ZERO)  >0)
			  		  		    					{
				  		  		    		    	detailheur50=new BigDecimal(txthsupp50generique.getText());
			  		  		    		    		detailheurComplet50=detailheur50;//compteurProd;
			  		  		    		    		coutSupp50=detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
			  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp50);
			  		  		    					}
				  		  		    		    		
				  		  		    		    	}
				  		  		    		    	
				  		  		    			}
				  		  		    		    	/*
				  		  		    		    	 * */
				  		  		    			
				  		  		    				
				  		  		    				
				  		  		    		    	detailResponsableProd.setCoutSupp25(coutSupp25);
				  		  				    		detailResponsableProd.setCoutSupp50(coutSupp50);
				  		  				    		detailResponsableProd.setCoutTotal(coutHoraireComplet);
				  		  		    		    	detailResponsableProd.setDelaiEmploye(delaiComplet);
				  		  		    		    	detailResponsableProd.setHeureSupp25(detailheurComplet25);
				  		  		    		    	detailResponsableProd.setHeureSupp50(detailheurComplet50);
				  		  		    		    	
				  		  		    		    detailResponsableProd.setCoutHoraire(heure.getValeur());
				  		  		    		    detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		  		    	        detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  		  		    		
				  		  		    		    	detailResponsableProd.setEmploye(employe);
				  		  		    		       detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    		    	detailResponsableProd.setAbsent(absent);
				  		  		    		    	detailResponsableProd.setSortie(sortie);
				  		  		    		        detailResponsableProd.setRetard(retard);
				  		  		    		    detailResponsableProd.setAutorisation(false);
				  		  		    		    	//detailResponsableProd.setProduction(production);
				  		  		    		    	detailResponsableProd.setDateProduction(production.getDate());
				  		  		    		    	detailProdResDAO.add(detailResponsableProd);
				  		  		    		
				  		  		    			 }
				  		  		    			
				  		  		    			
				  		  		    			CompteurEmployeProd compteurEmployeProd =compteurEmployeProdDAO.findByDateProdPeriode(production.getDate(),production.getPeriode(), detailResponsableProd.getEmploye().getId());
				  		  		   			 if(compteurEmployeProd !=null){
				  		  		   				 compteur=compteurEmployeProd.getCompteur()+1;
				  		  		   				 compteurEmployeProd.setCompteur(compteur);	
				  		  		   				 compteurEmployeProdDAO.edit(compteurEmployeProd);
				  		  		   				 
				  		  		   			 }else{
				  		  		   				 compteurEmployeProd= new CompteurEmployeProd();
				  		  		   				 compteurEmployeProd.setDateProd(production.getDate());
				  		  		   				 compteurEmployeProd.setPeriode(production.getPeriode());
				  		  		   				 compteurEmployeProd.setEmploye(detailResponsableProd.getEmploye());
				  		  		   				 compteurEmployeProd.setCompteur(1);
				  		  		   				 compteurEmployeProdDAO.add(compteurEmployeProd);
				  		  		   				 
				  		  		   			 	}
				  		  		    			
				  		  		    		   }
				  		  		    		
				  		  		    		  
				  		  		    		}
				  		  		    	
						  				listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");

						  				 afficher_tableEmployeGen(listDetailResponsableProdTmp);
				  		  		    	
				  		  		    	ViderEmployeGenerique();
				  		  		    	
				  		  		    	
				  		  		    	}
				  		  		    
				  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  		  		     		
				  		   //production.setListDetailResponsableProd(listDetailResponsableProd);
				  		   //productionDAO.edit(production);	
				  		       		 
				  		  	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnAjouterEmployeGenerique.setBounds(1860, 635, 58, 23);
				  		   btnAjouterEmployeGenerique.setIcon(imgAjouter);
				  		     add(btnAjouterEmployeGenerique);
				  		     
				  		      comboemployeproduction = new JComboBox();
				  		      comboemployeproduction.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent arg0) {
				  		      		

				  		   	 		
				  		   	 		if(!comboemployeproduction.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployeproduction.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployeproduction.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 		
				  		   	 	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     comboemployeproduction.setSelectedIndex(-1);
				  		     comboemployeproduction.setBounds(176, 338, 157, 24);
				  		     add(comboemployeproduction);
				  		   AutoCompleteDecorator.decorate(comboemployeproduction);
				  		      comboemployeemballage = new JComboBox();
				  		      comboemployeemballage.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		      		

				  		      		

				  		   	 		
				  		   	 		if(!comboemployeemballage.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployeemballage.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployeemballage.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 		
				  		   	 	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     comboemployeemballage.setSelectedIndex(-1);
				  		     comboemployeemballage.setBounds(1135, 324, 170, 24);
				  		     add(comboemployeemballage);
				  		   AutoCompleteDecorator.decorate(comboemployeemballage);
				  		      comboemployegenerique = new JComboBox();
				  		      comboemployegenerique.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		   	 		if(!comboemployegenerique.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployegenerique.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployegenerique.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 						  		      	}
				  		      });
				  		     comboemployegenerique.setSelectedIndex(-1);
				  		     comboemployegenerique.setBounds(1131, 540, 170, 24);
				  		     add(comboemployegenerique);
				  		   AutoCompleteDecorator.decorate(comboemployegenerique);
				  		    
				  		   CheckableItem[] m = {
					  			      new CheckableItem("aaa", false),
					  			      new CheckableItem("bbbbb", false),
					  			      new CheckableItem("111", false),
					  			      new CheckableItem("33333", false),
					  			      new CheckableItem("2222", false),
					  			      new CheckableItem("ccccccc", false)
					  			    };
				  		  
				  	  
				  	  
				  		   comboMachine.addItem("");
				  		    comboEquipe.addItem("");
				  		    
				  		  afficher_tableEmploye(production.getDetailProductions());
				  		  afficher_tableMP(production.getListCoutMP());
				  		  afficher_tableEmployeEmabalage(production.getListDetailProdGen());
				  		  if(production.getDate()!=null)
				  		  {
						  		listDetailProdResponsable=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
  
				  		  }
				  		/*  
				  		int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
		  				if(numberproduction==1)
		  				{
		  				
		  					 btnSaisieDelaiEquipeGen.setEnabled(true);
		  					
		  				}else
		  				{
		  					
		  					 btnSaisieDelaiEquipeGen.setEnabled(false);
		  					
		  				}
		  				*/	
				  		 
				  		comboemployeproduction.addItem("");
						comboemployeemballage.addItem("");
						comboemployegenerique.addItem("");
						
						JButton btnViderEmployeProduction = new JButton("Vider");
						btnViderEmployeProduction.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								ViderEmployeProduction();
								
							}
						});
						btnViderEmployeProduction.setBounds(894, 385, 58, 23);
						add(btnViderEmployeProduction);
						
						JButton btnVideremployeemballage = new JButton("Vider");
						btnVideremployeemballage.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								ViderEmployeEmballage();
							}
						});
						btnVideremployeemballage.setBounds(1860, 372, 58, 23);
						add(btnVideremployeemballage);
						
						JButton btnVideremployegenerique = new JButton("Vider");
						btnVideremployegenerique.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
							ViderEmployeGenerique();	
								
							}
						});
						btnVideremployegenerique.setBounds(1860, 583, 58, 23);
						add(btnVideremployegenerique);
						
						 checkretardproduction = new JCheckBox("Retard");
						checkretardproduction.setBounds(883, 340, 80, 24);
						add(checkretardproduction);
						
						 checkretardemballage = new JCheckBox("Retard");
						checkretardemballage.setBounds(1841, 324, 77, 24);
						add(checkretardemballage);
						
						 checkretardgenerique = new JCheckBox("Retard");
						checkretardgenerique.setBounds(1846, 544, 78, 24);
						add(checkretardgenerique);
						
						JXTitledSeparator titledSeparator_4 = new JXTitledSeparator();
						GridBagLayout gridBagLayout_3 = (GridBagLayout) titledSeparator_4.getLayout();
						gridBagLayout_3.rowWeights = new double[]{0.0};
						gridBagLayout_3.rowHeights = new int[]{0};
						gridBagLayout_3.columnWeights = new double[]{0.0, 0.0, 0.0};
						gridBagLayout_3.columnWidths = new int[]{0, 0, 0};
						titledSeparator_4.setTitle("Saisir Cout Hors production En Attent");
						titledSeparator_4.setBounds(9, 564, 875, 17);
						add(titledSeparator_4);
						
						JScrollPane scrollPane_5 = new JScrollPane();
						scrollPane_5.setBounds(9, 598, 875, 193);
						add(scrollPane_5);
						
						TableDetailCoutHorsProductionEnAttent = new JXTable();
						TableDetailCoutHorsProductionEnAttent.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
}
						});
						scrollPane_5.setViewportView(TableDetailCoutHorsProductionEnAttent);
						TableDetailCoutHorsProductionEnAttent.setColumnSelectionAllowed(true);
						TableDetailCoutHorsProductionEnAttent.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"Code",	"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%","Valider"
							}
						));
						
						
						
						 txtNumOF.addActionListener(new ActionListener() {
							  	public void actionPerformed(ActionEvent arg0) {
							  		
							  		validerSaisie=false;
							  		
							  		if(txtNumOF.getSelectedIndex()!=-1)
							  		{
							  			
							  			if(!txtNumOF.getSelectedItem().equals(""))
							  			{
							  				
							  			  	Production production=productionDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER);
								  			if(production!=null)
								  			{
								  				
								  				Depot depot=depotdao.findByCode(production.getCodeDepot());
								  				
								  				txtNumOF.setToolTipText(production.getStatut());
								  			txtQuantiteRealise.setText(production.getQuantiteReel()+"");
								  			txtPrixServiceProd.setText(production.getNbreHeure()+"");
								  			txtQuantiteOffre.setText(production.getQuantiteOffre()+"");
								  			txtQuantitePlus.setText(production.getQuantitePlus()+"");
								  			txtQuantiteMoins.setText(production.getQuantiteMoins()+"");
								  			if(production.getQuantitePlus().compareTo(BigDecimal.ZERO)!=0)
								  			{
								  				radioPlus.setSelected(true);
								  			}
								  			
								  			if(production.getQuantiteMoins().compareTo(BigDecimal.ZERO)!=0)
								  			{
								  				radioMoins.setSelected(true);
								  			}
								  			
								  			List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
								  			
							  				categorie.removeAllItems();
							  				comboLigneMachine.removeAllItems();
							  				comboMachine.removeAllItems();
							  				codeArticle.setText(production.getArticles().getCodeArticle());
							  				categorie.addItem(production.getArticles().getLiblle());
							  				categorie.setSelectedItem(production.getArticles().getLiblle());
							  				
							  				//comboEquipe.addItem(production.getEquipe().getNomEquipe());
							  				//comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
							  				
							  				comboLigneMachine.addItem(production.getLigneMachine().getNom());
							  				comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
							  				
							  				comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
							  				comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
							  				
							  				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  				

							  				 listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
							  				 listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
							  				 //listDetailResponsableProdTmp=productionDAO.listeDetailResponsableProd(production.getId());
							  				listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
							  				
							  				afficher_tableMP(listCoutMPTmp);
							  				afficher_tableEmploye(listDetailProductionTMP);
							  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
							  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
							  				listCoutHorsProductionEnAttenteAfficher=CoutHorsProdEnAttentDAO.findByProduction(production);
							  				 
						  					afficher_tableDetailCoutHorsProductionEnAttent(listCoutHorsProductionEnAttenteAfficher);	
								  			 
								  			
								  			
								  			}else
								  			{
								  				
								  				
								  				Production productionTmp=mapProduction.get(txtNumOF.getSelectedItem().toString());
								  				
								  				if(productionTmp.getStatut().equals(Constantes.ETAT_OF_LANCER))
								  				{
								  					
								  					initialiserTableOffreVariante();
								  					
								  					if(productionTmp.getOffreVariant()!=null)
								  					{
								  						
								  						
								  						if(productionTmp.getTyoeOffre()!=null)
											  			{
								  							
								  							if(productionTmp.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
												  			{

										  						if(productionTmp.getOffreVariant().equals(Constantes.CODE_OUI))
										  						{
										  							
										  							tableOffreVariante.setVisible(true);
													  		    	scrollPane_6.setVisible(true);

										  							List<OffreProduction> listeOffreProductions=offreProductionDAO.findByProduction(productionTmp);
										  							
										  							afficher_tableOffreVariante(listeOffreProductions);
										  							 
										  							
										  						}else
										  						{
										  							tableOffreVariante.setVisible(false);
													  		    	scrollPane_6.setVisible(false);

										  						}
												  				
												  			}else
												  			{
												  				tableOffreVariante.setVisible(false);
												  		    	scrollPane_6.setVisible(false);

												  			}
											  			}else
											  			{
											  				tableOffreVariante.setVisible(false);
											  		    	scrollPane_6.setVisible(false);
											  			}
								  					 
								  			
								  						
								  						
								  					}else
								  					{
								  						tableOffreVariante.setVisible(false);
										  		    	scrollPane_6.setVisible(false);

								  					}
								  					
								  				}
								  				
								  				List<CoutMP>	listCoutMPTmp=new ArrayList<CoutMP>();
								  				listDetailProductionTMP.clear();
								  				listDetailProdGenTmp.clear();
								  				listDetailResponsableProdTmp.clear();
								  				listCoutHorsProductionEnAttent.clear();
								  				txtQuantiteRealise.setText("");
									  			txtPrixServiceProd.setText("");
									  			txtQuantiteOffre.setText("");
									  			txtQuantitePlus.setText("");
									  			txtQuantiteMoins.setText("");
									  			group.clearSelection();
									  			
									  			categorie.removeAllItems();
								  				comboLigneMachine.removeAllItems();
								  				comboMachine.removeAllItems();
								  				codeArticle.setText("");
									  			
								  				afficher_tableMP(listCoutMPTmp);
								  				afficher_tableEmploye(listDetailProductionTMP);
								  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
								  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
								  				 
								  				afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
									  			
								  			}
							  				
							  			}else
							  			{

							  				List<CoutMP>	listCoutMPTmp=new ArrayList<CoutMP>();
							  				listDetailProductionTMP.clear();
							  				listDetailProdGenTmp.clear();
							  				listDetailResponsableProdTmp.clear();
							  				listCoutHorsProductionEnAttent.clear();
							  				txtQuantiteRealise.setText("");
								  			txtPrixServiceProd.setText("");
								  			txtQuantiteOffre.setText("");
								  			txtQuantitePlus.setText("");
								  			txtQuantiteMoins.setText("");
								  			group.clearSelection();
								  			
								  			categorie.removeAllItems();
							  				comboLigneMachine.removeAllItems();
							  				comboMachine.removeAllItems();
							  				codeArticle.setText("");
								  			
							  				afficher_tableMP(listCoutMPTmp);
							  				afficher_tableEmploye(listDetailProductionTMP);
							  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
							  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
							  				 
							  				afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
								  			
							  			
							  			}
							  			
							  			
							
							  			
							  		}else
							  		{
						  				List<CoutMP>	listCoutMPTmp=new ArrayList<CoutMP>();
						  				listDetailProductionTMP.clear();
						  				listDetailProdGenTmp.clear();
						  				listDetailResponsableProdTmp.clear();
						  				listCoutHorsProductionEnAttent.clear();
						  				txtQuantiteRealise.setText("");
							  			txtPrixServiceProd.setText("");
							  			txtQuantiteOffre.setText("");
							  			txtQuantitePlus.setText("");
							  			txtQuantiteMoins.setText("");
							  			group.clearSelection();
							  			
							  			categorie.removeAllItems();
						  				comboLigneMachine.removeAllItems();
						  				comboMachine.removeAllItems();
						  				codeArticle.setText("");
							  			
						  				afficher_tableMP(listCoutMPTmp);
						  				afficher_tableEmploye(listDetailProductionTMP);
						  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
						  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
						  				 
						  				afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
							  			
						  			}
							  		
							  		
							  		
							  		
							  		
							  		
							  	}
							  });
							  
							 
					  		     txtNumOF.setBounds(75, 7, 157, 24);
					  		     add(txtNumOF);
					  		   AutoCompleteDecorator.decorate(txtNumOF); 
					  		 txtNumOF.setSelectedIndex(-1);
						
						
						
						JButton btnReglerStockPf = new JButton("Regler Stock PF");
						btnReglerStockPf.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								 List<Production> listeProduction=new ArrayList<Production>();
								 listeProduction=productionDAO.listeProductionByID();
								 JOptionPane.showMessageDialog(null, listeProduction.size());
								 boolean effectue=false;
								 for(int i=0;i<listeProduction.size();i++)
								 {
									 
									Production production= listeProduction.get(i);
									
									BigDecimal coutPF=BigDecimal.ZERO;
									BigDecimal nouveauCout=BigDecimal.ZERO;
									BigDecimal quantiteTotal=BigDecimal.ZERO ;
									BigDecimal coutStock=BigDecimal.ZERO;
									
									
									//coutTotal=production.getCoutTotalEmploye()+production.getCoutTotalEmployeGen()+production.getCoutTotalMP()+production.getCoutTotalEmployeEmbalage();
									
									coutPF=production.getCoutTotal().divide(production.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP);
									
									 StockPF stockPF = stockPFDAO.findStockByMagasinPF(production.getArticles().getId(),production.getMagasinPF().getId());
									 
									 quantiteTotal=stockPF.getStock().add(production.getQuantiteReel());
									 coutStock=stockPF.getStock().multiply(stockPF.getPrixUnitaire());
									 
									 	if(coutStock.compareTo(BigDecimal.ZERO) >0)
									 		nouveauCout=(production.getCoutTotal().add(coutStock)).divide(quantiteTotal, 6, BigDecimal.ROUND_HALF_UP) ;
									 	else 
									 		nouveauCout= coutPF;
									 	
									 	
									 stockPF.setArticles(production.getArticles());
									 stockPF.setPrixUnitaire(nouveauCout);
									 stockPF.setStock(quantiteTotal);
									 
									
									 DetailTransferProduitFini detailTransferProduitFini =new DetailTransferProduitFini();
								 	 TransferStockPF transferStockPF =new TransferStockPF();
								 	 List<DetailTransferProduitFini> listeDetailTransferProduitFini=new ArrayList<DetailTransferProduitFini>();
								 	
								 	detailTransferProduitFini.setArticle(production.getArticles());
								 
								 	detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
								 	detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
								 	detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
								 	detailTransferProduitFini.setQuantite(production.getQuantiteReel());
								 	detailTransferProduitFini.setPrixUnitaire(coutPF);
								 	detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
								 	detailTransferProduitFini.setTransferStockPF(transferStockPF);
								 	
								 	 listeDetailTransferProduitFini.add(detailTransferProduitFini);
								 	 
								 	 transferStockPF.setCodeTransfer(production.getNumOF());
								 	 transferStockPF.setCreerPar(AuthentificationView.utilisateur);
								 	 transferStockPF.setDate(new Date());
								 	 transferStockPF.setDateTransfer(production.getDate_debFabPre());
								 	 transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
								 	 transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
								 	 
								 	transferStockPFDAO.add(transferStockPF);
									// detailTransferProduitFiniDAO.add(detailTransferProduitFini);
								 	 
									 
									
									stockPFDAO.edit(stockPF); 
									effectue=true;
									 
									 
									 
								 }
								 
								 if(effectue==true)
								 {
									 JOptionPane.showMessageDialog(null, "Stock PF Réglé Avec Succée");
								 }
								
								 
								 
									
							}
							
							
						});
						btnReglerStockPf.setFont(new Font("Tahoma", Font.PLAIN, 11));
						btnReglerStockPf.setBounds(1048, 767, 112, 24);
						btnReglerStockPf.setVisible(false);
						add(btnReglerStockPf);
						
						JLabel lblQuantiteOffre = new JLabel("Quantite Offre :");
						lblQuantiteOffre.setFont(new Font("Tahoma", Font.PLAIN, 11));
						lblQuantiteOffre.setBounds(783, 7, 89, 26);
						add(lblQuantiteOffre);
						
						txtQuantiteOffre = new JTextField();
						txtQuantiteOffre.setEditable(false);
						txtQuantiteOffre.setColumns(10);
						txtQuantiteOffre.setBounds(873, 6, 120, 26);
						add(txtQuantiteOffre);
						
						txtQuantitePlus = new JTextField();
						txtQuantitePlus.setEditable(false);
						txtQuantitePlus.setColumns(10);
						txtQuantitePlus.setBounds(1080, 6, 120, 26);
						add(txtQuantitePlus);
						
						 radioPlus = new JRadioButton("Plus");
						 radioPlus.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent arg0) {
						 		
						 		if(radioPlus.isSelected()==true)
						 		{
						 			txtQuantiteMoins.setText("0");
						 			txtQuantiteMoins.setEditable(false);
						 			txtQuantitePlus.setText("");
						 			txtQuantitePlus.setEditable(true);
						 			
						 		}
						 		
						 		
						 		
						 	}
						 });
						radioPlus.setBounds(1001, 8, 64, 23);
						
						add(radioPlus);
						group.add(radioPlus);
						
						 radioMoins = new JRadioButton("Moins");
						 radioMoins.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		
						 		if(radioMoins.isSelected()==true)
						 		{
						 			txtQuantitePlus.setText("0");
						 			txtQuantitePlus.setEditable(false);
						 			txtQuantiteMoins.setText("");
						 			txtQuantiteMoins.setEditable(true);
						 			
						 		}
						 		
						 	}
						 });
						radioMoins.setBounds(1210, 7, 64, 23);
						add(radioMoins);
						group.add(radioMoins);
						txtQuantiteMoins = new JTextField();
						txtQuantiteMoins.setEditable(false);
						txtQuantiteMoins.setColumns(10);
						txtQuantiteMoins.setBounds(1289, 5, 120, 26);
						add(txtQuantiteMoins);
						
						JButton btnInitialiserMois = new JButton("Initialiser Mois / Plus");
						btnInitialiserMois.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 InitialiserPlusMoins();
							}
						});
						btnInitialiserMois.setHorizontalAlignment(SwingConstants.LEADING);
						btnInitialiserMois.setFont(new Font("Tahoma", Font.PLAIN, 11));
						btnInitialiserMois.setBounds(1418, 8, 142, 24);
						add(btnInitialiserMois);
						
						
				  		  for(int i=0;i<listEmploye.size();i++)
						  {
							  
							Employe employe=listEmploye.get(i);  
							comboemployeproduction.addItem(employe.getNomafficher());
							comboemployeemballage.addItem(employe.getNomafficher());
							comboemployegenerique.addItem(employe.getNomafficher());
							  mapMatriculeEmploye.put(employe.getMatricule(), employe);
						 mapNomEmploye.put(employe.getNomafficher(), employe);
						  
						  
						  }  
				  		
				  		  
				  		  
				  		listDetailResponsableProdTmp.clear();
				  		listDetailResponsableProdTmp.addAll(listDetailProdResponsable);
				  			 afficher_tableEmployeGen(listDetailProdResponsable);  
				  		 
				  		 
				 	  		
	}
	
	

	
	
	
	  void AfficherMatierePremiere()
	  {
			
		 
			  
		  	List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
		  	afficherDetailPorduction(production.getArticles().getDetailEstimation(),listCoutMPTmp);
			
			
			codeArticle.setText(production.getArticles().getCodeArticle());
			categorie.addItem(production.getArticles().getLiblle());
			categorie.setSelectedItem(production.getArticles().getLiblle());
			
			
			comboLigneMachine.addItem(production.getLigneMachine().getNom());
			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
			
			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
			String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
			
			List<DetailProdGen> listDetailProdGen=productionDAO.listeDetailProdGen(production.getId());
			List<DetailProduction> listDetailProduction=productionDAO.listeDetailProduction(production.getId());
			List<DetailProdRes> listDetailResponsableProd= detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
			afficher_tableEmployeGen(listDetailResponsableProd);
			afficher_tableMP(listCoutMPTmp);
			afficher_tableEmploye(listDetailProduction);
			afficher_tableEmployeEmabalage(listDetailProdGen);
			
			
	  }
	
void	intialiserTableMP(){
	
JComboBox<CheckableItem> jcombobox=new JComboBox();
	
		
		  final CheckableItem[] codefournisseur =new CheckableItem[listFournisseurMP.size()];
		  
		  for(int i=0;i<listFournisseurMP.size();i++) {
		  
		  FournisseurMP fournisseurMP= listFournisseurMP.get(i);
		  codefournisseur[i]=  new CheckableItem(fournisseurMP.getCodeFournisseur(),false);
		  
		  }

 
	jcombobox=new CheckedComboBox<CheckableItem>(new DefaultComboBoxModel<CheckableItem>(codefournisseur));
	jcombobox.setEditable(false);
	final ListModel<CheckableItem> model=jcombobox.getModel();
	
	 
		 modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			
	  		     			"CODE","NOM MP","Fournisseur","QTE ESTIME","QTE EXISTANTE","QTE CHARGE","CHARGE SUPP", "QTE CONSOMME REEL", "QTE DECHET","QTE DECHET FOUR", "QTE MOINS ", "QTE OFFRE", "QTE RESTE USINE","QTE PLUS","Code Fournisseur Dechet", "ECART OF", "ECART Mixte"

	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false,false,false, true, true, true,false, true,true,true,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		boolean existe=false;
	  		     	
	  		     		
	  		     		
	  		     		
	  		     	 for(int t=0;t<lisDetailEstimation.size();t++)
		     			 {
		     				 
		     				 if(lisDetailEstimation.get(t).getMatierePremier().getCode().equals( table.getValueAt(row, 0).toString()))
		     				 {
		     					 if(!lisDetailEstimation.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		     					 {
		     						 
		     						 
		     						if(lisDetailEstimation.get(t).getPriorite()==2)
			     					{
			     						
			     						existe=true;
			     					} 
		     					 }
		     					 
		     					
		     				 }
		     				 
		     			 }
	  		     	 
	  		     	 
	  		     	 if(OffrePFMixte==true)
	  		     	 {
	  		     		if( listMPOffrePFMixteNonSaisir.size()!=0)
	  		     		{
	  		     			existe=true;
	  		     		}
		  		     	 
		  		     	  
	  		     		 
	  		     	 }
	  		     	
	  		     		 
	  		     	 
	  		     		
	  		     		
	  		     		
	  		     		
	  		     		if(  existe==true)
	  		     		{
	  		     		  
	  		     			if(column==0 || column==1 ||column==2 ||column==3 ||column==4 ||column==5 ||column==6 ||column==7 ||column==8 ||column==9 ||column==10 ||column==11 ||column==12 ||column==13 ||column==14 ||column==15 ||column==16 )
	  		     			{
	  		     				for(int r=0;r<listSubCategorieMPClientNonSaisir.size();r++) 
	  		     				{
	  		     				 for(int t=0;t<lisDetailEstimation.size();t++)
	  			     			 {
	  		     					 if(lisDetailEstimation.get(t).getMatierePremier().getCode().equals( table.getValueAt(row, 0).toString()))
	  			     				 {
	  		     						 
	  		     						if(lisDetailEstimation.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getId()==listSubCategorieMPClientNonSaisir.get(r).getId() )
	  		     						{
	  		     							
	  		     							existe=false;
	  		     							
	  		     						 
	  		     						}
	  		     						 
	  			     				 }
	  			     			 }
	  		     				}
	  		     				
	  		     				
	  		     			 if(OffrePFMixte==true)
	  		  		     	 {
	  		  		     		if( listMPOffrePFMixteNonSaisir.size()!=0)
	  		  		     		{
	  		  		     			 if( table.getValueAt(row, 0).toString().equals(listMPOffrePFMixte.get(listMPOffrePFMixte.size()-1)))
	  		  		     			 {
	  		  		     				 if(table.getValueAt(row, 7).toString().equals(""))
	  		  		     				 {
	  		  		     				existe=false;
	  		  		     				 }
	  		  		     				 
	  		  		     			
	  		  		     			
	  		  		     			 }
	  		  		     		}
	  			  		     	 
	  			  		     	  
	  		  		     		 
	  		  		     	 }
	  		     				
	  		     				
	  		     				
	  		     				
	  		     			}
	  		     			
	  		     			if(column==7 || column==11)
	  		     			{
	  		     				existe=false;
	  		     			}
	  		     			
	  		     				return existe; 
	  		     			 
	  		     			
	  		     			
	  		     			
	  		     			
	  		     		}else  
	  		     		 
	  		     		
	  		     		return columnEditables[column];
	  		     		
	  		     		
	  		     	}
	  		     };
	  		     
	  		   table.setModel(modeleMP); 
	  		//  table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jcombobox)); // ajouter ComboBox Code Fournisseur au Jtable
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(30);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(160);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(3).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(4).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(5).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(6).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(8).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(9).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(10).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(11).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(12).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(12).setPreferredWidth(40);
	  		 table.getColumnModel().getColumn(13).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(14).setPreferredWidth(40);
	  		  table.getColumnModel().getColumn(15).setPreferredWidth(40);
	  		 table.getTableHeader().setReorderingAllowed(false);

	  		   
	  		 /*
	  		 jcombobox.addItemListener(new ItemListener() {
	  			   
	  			   

				  @Override public void itemStateChanged(ItemEvent e) { if (  e.getStateChange()
				  == ItemEvent.SELECTED) {
				  
				  List<String> sl = new ArrayList<>(); 
				  for (int i = 0; i < model.getSize(); i++)
				  { 
					  CheckableItem v = model.getElementAt(i); 
					  if (v.isSelected()) 
					  {
				  sl.add(v.toString()); 
				  } 
					  } 
				  if (sl.isEmpty()) {
				  //JOptionPane.showMessageDialog(null, "Vide"); // When returning the empty  string, the height of JComboBox may become 0 in some cases. 
					  } else {
				  
					  
				  table.setValueAt(String.valueOf(sl.stream().sorted().collect(Collectors.
				  joining(", "))), table.getSelectedRow(), table.getSelectedColumn()); 
				  
				  // JOptionPane.showMessageDialog(null,sl.stream().sorted().collect(Collectors.joining(", ")));
				  
				  }
				  
				 
				  for (int i = 0; i < model.getSize(); i++)
				  { 
					  CheckableItem v = model.getElementAt(i); 
					 v.setSelected(false);
				
					  } 
				 
				  
				 
				  } }
				 
				
				  
		  		
		        });
		  		    
	  		 
	  		 */
	  		 
	  		   
	  		  
	  		
	  		   
	  		   
	}
	
void afficher_tableMP(List<CoutMP> listCoutMP)
	{
	
	
	intialiserTableMP();
		  int i=0;
		  NumberFormat nf = new DecimalFormat("0.###");
		  

	
		  
		 
		  
		
			while(i<listCoutMP.size())
			{	
				CoutMP coutMP=listCoutMP.get(i);
			
				BigDecimal quantiteTotal=coutMP.getQuantite();
				BigDecimal quantiteExistante=coutMP.getQuantExistante();
				BigDecimal quantiteCharge=coutMP.getQuantCharge();
				BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
				BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
				BigDecimal quantiteDechet=coutMP.getQuantDechet();
				BigDecimal quantiteDechetFour=coutMP.getQuantDechetFournisseur();
				BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
				BigDecimal quantiteOffre=coutMP.getQuantiteOffre();
				BigDecimal quantiteReste=coutMP.getQuantReste();
				BigDecimal quantiteManquanteFrPlus=coutMP.getQuantiteManquanteFrPlus();
				String codeFournisseurMP="";
				String codeFournisseur="";
				String codeFournisseurdechet="";
				
				
				 
			
				
				
				
				
				
				if(coutMP.getCodeFournisseur()!=null)
				{
					codeFournisseurdechet=coutMP.getCodeFournisseur();	
				}
				
				if(coutMP.getCodeFournisseurdechet()!=null)
				{
					codeFournisseurdechet=coutMP.getCodeFournisseurdechet();	
				}
				
				if(coutMP.getFournisseurMP()!=null)
				{
					codeFournisseurMP=coutMP.getFournisseurMP().getCodeFournisseur();
					
				}
				
				BigDecimal ecart=BigDecimal.ZERO;
				BigDecimal ecartConsomme=BigDecimal.ZERO;
				
			
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
				{
					 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract((quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

					 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);
				}else if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
				{
					 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

					 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

				}
				
				else
				{
					 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(coutMP.getQuantiteManquante()).add(quantiteOffre).add(quantiteReste)).add(coutMP.getQuantiteManquanteFrPlus());

					 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

				}
				
				
				
				
				
				
				
				
				//ecart=NumberUtils.roundHalfDown(ecart,2 );
				
				ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				
				
				// String strEcart = nf.format(ecart);
				 String strEcart = NumberFormat.getNumberInstance(Locale.FRANCE).format(ecart);	 
					String quantiteTotalTmp="";
					String quantiteExistanteTmp="";
					String quantiteChargeTmp="";
					String quantitechargeSuppTmp="";
					String quantiteConsommeTmp="";
					String quantiteDechetTmp="";
					String quantiteDechetFourTmp="";
					String quantiteManquanteTmp="";
					String quantiteOffreTmp="";
					String quantiteResteTmp="";
					String quantiteManquanteFrPlusTmp="";
		
					
					
					if(coutMP.getQuantite().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteTotalTmp= coutMP.getQuantite().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
						
					} if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteExistanteTmp=coutMP.getQuantExistante().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
						
					} if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteChargeTmp=coutMP.getQuantCharge().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					} if(coutMP.getQuantChargeSupp().compareTo(BigDecimal.ZERO)!=0)
					{
						quantitechargeSuppTmp=coutMP.getQuantChargeSupp().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}if(coutMP.getQuantConsomme().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteConsommeTmp=coutMP.getQuantConsomme().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
						
					}
					if(coutMP.getQuantDechet().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteDechetTmp=coutMP.getQuantDechet().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}else
					{
						
						 
							 
								
								for(int j=0;j<lisDetailEstimation.size();j++)
								{
									if(lisDetailEstimation.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
									{
										if(lisDetailEstimation.get(j).getPriorite()==1)
										{
											if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
												{
													if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())==true)
													{
														quantiteDechetTmp=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
													}
												}
												
												
												
											}
											
										}
										
										
									}
								}
								
								
							 
						 
						
						
					}
					
					
					if(coutMP.getQuantDechetFournisseur().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteDechetFourTmp=coutMP.getQuantDechetFournisseur().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}else
					{
						
						for(int j=0;j<lisDetailEstimation.size();j++)
						{
							if(lisDetailEstimation.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
							{
								if(lisDetailEstimation.get(j).getPriorite()==1)
								{
									if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
										{
											if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())==true)
											{
												quantiteDechetFourTmp=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
											}
										}
										
										
										
									}
									
								}
								
								
							}
						}
						
						
					}
					
					
					
					if(coutMP.getQuantiteManquante().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteManquanteTmp=coutMP.getQuantiteManquante().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}else
					{
						
						for(int j=0;j<lisDetailEstimation.size();j++)
						{
							if(lisDetailEstimation.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
							{
								if(lisDetailEstimation.get(j).getPriorite()==1)
								{
									if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
										{
											if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())==true)
											{
												quantiteManquanteTmp=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
											}
										}
										
										
										
									}
									
								}
								
								
							}
						}
						
						
						
					}
					
					
					
					
					if(coutMP.getQuantiteOffre().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteOffreTmp=coutMP.getQuantiteOffre().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}
					if(coutMP.getQuantReste().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteResteTmp=coutMP.getQuantReste().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}else
					{
						
						for(int j=0;j<lisDetailEstimation.size();j++)
						{
							if(lisDetailEstimation.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
							{
								if(lisDetailEstimation.get(j).getPriorite()==1)
								{
									if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
										{
											if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())==true)
											{
												quantiteResteTmp=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
											}
										}
										
										
										
									}
									
								}
								
								
							}
						}
						
						
					}
					
					if(coutMP.getQuantiteManquanteFrPlus().compareTo(BigDecimal.ZERO)!=0)
					{
						quantiteManquanteFrPlusTmp=coutMP.getQuantiteManquanteFrPlus().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
					}else
					{
						
						for(int j=0;j<lisDetailEstimation.size();j++)
						{
							if(lisDetailEstimation.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
							{
								if(lisDetailEstimation.get(j).getPriorite()==1)
								{
									if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
										{
											if(mapMPClientSaisir.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())==true)
											{
												quantiteManquanteFrPlusTmp=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
											}
										}
										
										
										
									}
									
								}
								
								
							}
						}
						
						
					}
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						ecartConsomme=coutMP.getQuantite().subtract(coutMP.getQuantConsomme());
					}
					
					 
					
				Object []ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),codeFournisseurMP,quantiteTotalTmp+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), quantiteExistanteTmp,quantiteChargeTmp,quantitechargeSuppTmp,quantiteConsommeTmp,quantiteDechetTmp,quantiteDechetFourTmp,quantiteManquanteTmp,quantiteOffreTmp,quantiteResteTmp,quantiteManquanteFrPlusTmp,codeFournisseurdechet,ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN),ecartConsomme.setScale(2, BigDecimal.ROUND_HALF_DOWN)};

				modeleMP.addRow(ligne);
				 
				
				i++;
			}
			  table.setModel(modeleMP); 
	
	 
	}
	

void afficher_tableEmploye(List<DetailProduction> listDetailProduction)
	{
	initialiserTableauEmploye();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	
		  int i=0;
			while(i<listDetailProduction.size())
			{	
				DetailProduction detailProduction=listDetailProduction.get(i);
				delai=detailProduction.getDelaiEmploye();
				heureSupp25=detailProduction.getHeureSupp25();
				heureSupp50=detailProduction.getHeureSupp50();
				absent=detailProduction.isAbsent();
				sortie=detailProduction.isSortie();
				retard=detailProduction.isRetard();
				Object []ligne={detailProduction.getEmploye().getId(),detailProduction.getEmploye().getMatricule(),detailProduction.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard};

				modeleEmploye.addRow(ligne);
				i++;
			}
			tableEmploye.setModel(modeleEmploye);
	}



void afficher_tableOffreVariante(List<OffreProduction> listOffreProduction)
{
initialiserTableOffreVariante();
 mapGrammageOffreVariante.clear();

	  int i=0;
		while(i<listOffreProduction.size())
		{	
			OffreProduction offreProduction=listOffreProduction.get(i);
			
			mapGrammageOffreVariante.put(offreProduction.getConditionOffre().getConditionOffre(), offreProduction.getConditionOffre().getValeur());
			
			Object []ligne={offreProduction.getConditionOffre().getConditionOffre(), ""};

			modeleTableOffreVariante.addRow(ligne);
			
		 
		
			i++;
		}
		tableOffreVariante.setModel(modeleTableOffreVariante);
}




void afficher_tableEmployeEmabalage(List<DetailProdGen> listDetailProdGen)
{
	initialiserTableauEquipeEmbalage();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	  int i=0;
		while(i<listDetailProdGen.size())
		{	
			DetailProdGen detailProdGen=listDetailProdGen.get(i);
			
			delai =detailProdGen.getDelaiEmploye();
			heureSupp25=detailProdGen.getHeureSupp25();
			heureSupp50=detailProdGen.getHeureSupp50();
			absent=detailProdGen.isAbsent();
			sortie=detailProdGen.isSortie();
			retard=detailProdGen.isRetard();
			Object []ligne={detailProdGen.getEmploye().getId(),detailProdGen.getEmploye().getMatricule(),detailProdGen.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard};

			modeleEquipeEm.addRow(ligne);
			i++;
		}
		table_1.setModel(modeleEquipeEm);
}

void afficher_tableEmployeGen(List<DetailProdRes> listDetailResponsableProd)
{
initialiserTableauEmployeGen();
BigDecimal delai=BigDecimal.ZERO; 
BigDecimal heureSupp25=BigDecimal.ZERO; 
BigDecimal heureSupp50=BigDecimal.ZERO; 
boolean absent=false;
boolean sortie=false;
boolean retard=false;
	  int i=0;
	  boolean erreur=false;
		while(i<listDetailResponsableProd.size())
		{
			
			DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
			if(detailResponsableProd.getNbrProduction()-0!=0)
			{
				delai=detailResponsableProd.getDelaiEmploye().divide(new BigDecimal(detailResponsableProd.getNbrProduction()), 6, RoundingMode.HALF_UP);
			}else
			{
				erreur=true;
			}
			if(detailResponsableProd.getNbrProduction()-0!=0)
			{
				heureSupp25=detailResponsableProd.getHeureSupp25().divide(new BigDecimal(detailResponsableProd.getNbrProduction()), 6, RoundingMode.HALF_UP);
			} 
			if(detailResponsableProd.getNbrProduction()-0!=0)
			{
				heureSupp50=detailResponsableProd.getHeureSupp50().divide(new BigDecimal(detailResponsableProd.getNbrProduction()), 6, RoundingMode.HALF_UP);
			} 
			
			
			absent=detailResponsableProd.isAbsent();
			sortie=detailResponsableProd.isSortie();
			retard=detailResponsableProd.isRetard();
			Object []ligne={detailResponsableProd.getEmploye().getId(),detailResponsableProd.getEmploye().getMatricule(),detailResponsableProd.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard};

			modeleEquipeGen.addRow(ligne);
			i++;
		}
		
		if(erreur==true)
		{
			JOptionPane.showMessageDialog(null, "Le Nombre De Production Declarer dans l'equipe Generique Est Zero veuillez le Changer SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tableEmployeGen.setModel(modeleEquipeGen);
}


void afficher_tableCoutHorsProductionEnAttent(List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent)
{
initialiserTableauCoutHorsProductionEnAttent();

int i=0;


while(i<listCoutHorsProdEnAttent.size())
{
	
	 
	CoutHorsProdEnAttent coutHorsProdEnAttent=listCoutHorsProdEnAttent.get(i);
	
 
	 mapDeatailCoutHorsProductionEnAttente.put(coutHorsProdEnAttent.getEmploye().getMatricule()+coutHorsProdEnAttent.getCode(), coutHorsProdEnAttent);
	 
	 
	 Object []ligne={coutHorsProdEnAttent.getCode(), coutHorsProdEnAttent.getEmploye().getId(),coutHorsProdEnAttent.getEmploye().getMatricule(),coutHorsProdEnAttent.getEmploye().getNom(),coutHorsProdEnAttent.getDelaiEmploye(),coutHorsProdEnAttent.getHeure25(),coutHorsProdEnAttent.getHeure50(),false};

		modeleCoutHorsProductionEnAttent.addRow(ligne); 
		
		 	
			 
			
			i++;
} 
		TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent);
}


void afficher_tableDetailCoutHorsProductionEnAttent(List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent)
{
initialiserTableauCoutHorsProductionEnAttent();


		int i=0;
		while(i<listCoutHorsProdEnAttent.size())
		{
			 CoutHorsProdEnAttent  coutHorsProdEnAttent= listCoutHorsProdEnAttent.get(i);
			
			 
											
			Object []ligne={coutHorsProdEnAttent.getEmploye().getId(),coutHorsProdEnAttent.getEmploye().getMatricule(),coutHorsProdEnAttent.getEmploye().getNom(),coutHorsProdEnAttent.getDelaiEmploye(),coutHorsProdEnAttent.getHeure25(),coutHorsProdEnAttent.getHeure50()};

			modeleCoutHorsProductionEnAttent.addRow(ligne);
			i++;
		}
	


	  
		TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent);
}






	
List<DetailProduction> calculeCoutEmploye(List<DetailProduction> listDetailProduction,Map< Integer, String> mapDelaiEmploye){
		
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	
	BigDecimal delai=BigDecimal.ZERO;
		
		BigDecimal remise=BigDecimal.ZERO;
		BigDecimal coutHoraire=BigDecimal.ZERO;
		BigDecimal heureSupp25; 
		BigDecimal heureSupp50; 
		
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
		
		List<DetailProduction> listDetailProductionTmp= new ArrayList<DetailProduction>();
		for(int i=0;i<listDetailProduction.size();i++){
			
			remise=BigDecimal.ZERO;
			delai=BigDecimal.ZERO;
			coutHoraire=BigDecimal.ZERO;
			heureSupp25=BigDecimal.ZERO;
			heureSupp50=BigDecimal.ZERO;
			coutSupp25=BigDecimal.ZERO;
			coutSupp50=BigDecimal.ZERO;
			BigDecimal delaiProd=BigDecimal.ZERO;
			DetailProduction detailProduction =listDetailProduction.get(i);
			
			if(!detailProduction.getEmploye().isSalarie()){
				
				delai=new BigDecimal(mapDelaiEmploye.get(detailProduction.getEmploye().getId()));
				heureSupp25=new BigDecimal(mapHeureSupp25EmployeProd.get(detailProduction.getEmploye().getId()));
				heureSupp50=new BigDecimal(mapHeureSupp50EmployeProd.get(detailProduction.getEmploye().getId()));
			
			if(detailProduction.isAbsent()==true){
	    		
		   		// String code=Utils.genereCodeDateMoisAnnee(production.getDate());
					 
		   		// Utils.compterAbsenceEmploye(code, detailProduction.getEmploye(), production.getDate());
		   		 
		   		 delai=BigDecimal.ZERO;
		   		 heureSupp25=BigDecimal.ZERO;
		   		 heureSupp50=BigDecimal.ZERO;
		   		 
		   		 
		   		 
		   		}
			
			
			coutHoraire=delai.multiply(heure.getValeur());	
			//coutHoraire=detailProduction.getEmploye().getCoutHoraire().multiply(delai);
			coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
			coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
			
			coutTotalEmploye=coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50);
			detailProduction.setCoutTotal(coutHoraire);
			detailProduction.setDelaiEmploye(delai);
			detailProduction.setHeureSupp25(heureSupp25);
			detailProduction.setHeureSupp50(heureSupp50);
			detailProduction.setCoutSupp25(coutSupp25);
			detailProduction.setCoutSupp50(coutSupp50);
			detailProduction.setRemise(remise);
			
			
			if(!detailProduction.getEmploye().isSalarie()){
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailProduction.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				
				
				if(delai.compareTo(production.getNbreHeure())<0){
					if(detailProduction.isSortie())
						delaiProd=ficheEmploye.getDelaiProd().add(production.getNbreHeure());
					else 
						delaiProd=ficheEmploye.getDelaiProd().add(delai);
				}else {
					delaiProd=ficheEmploye.getDelaiProd().add(delai);
				}
				
			//	coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
				delai=delai.add(ficheEmploye.getDelaiEmploye()) ;
				String numOF=ficheEmploye.getNumOF()+"-"+production.getNumOF();
				
				
				
		/*	ficheEmploye.setDateSituation(production.getDate());
			
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
			ficheEmploye.setCoutSupp25(coutSupp25);
			ficheEmploye.setCoutSupp50(coutSupp50);*/
			
			ficheEmploye.setNumOF(numOF);
		//	ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setDelaiProd(delaiProd);
			
			ficheEmploye.setDelaiEmploye(delai);
			
			 if(detailProduction.isAbsent()==false && ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd()) >=0){
		   			
		   	
					
					if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 ficheEmploye.setRemise(remise);
			ficheEmployeDAO.edit(ficheEmploye);
			} else{
				ficheEmploye =new FicheEmploye();
				
				ficheEmploye.setNumOF(production.getNumOF());
				ficheEmploye.setDateSituation(production.getDate());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProduction.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
			
				if(delai.compareTo(production.getNbreHeure())<0){
					if(detailProduction.isSortie())
						delaiProd=production.getNbreHeure();
					else 
						delaiProd=delai;
				}else {
					delaiProd=delai;
				}
				
				
				 if(detailProduction.isAbsent()==false && delai.compareTo(delaiProd) >=0){
			   			
			   		
						
						if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(delaiProd);
				ficheEmployeDAO.add(ficheEmploye);
				
			}
			}
			
			listDetailProductionTmp.add(detailProduction);
		}
		}	
		return listDetailProductionTmp;
		
	}











List<DetailProdGen> calculeCoutEmployeEmbalage(List<DetailProdGen> listDetailProdGen,Map< Integer, String> mapDelaiEmployeEmabalage){
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	BigDecimal delai=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	List<DetailProdGen> listDetailDetailProdGenTmp= new ArrayList<DetailProdGen>();
	for(int i=0;i<listDetailProdGen.size();i++){
		
		DetailProdGen detailProdGen =listDetailProdGen.get(i);
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		
		if(!detailProdGen.getEmploye().isSalarie()){
			
			delai=new BigDecimal(mapDelaiEmployeEmabalage.get(detailProdGen.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25EmployeEmbalage.get(detailProdGen.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50EmployeEmbalage.get(detailProdGen.getEmploye().getId()));
		
		if(detailProdGen.isAbsent()==true){
    		
   		 String code=Utils.genereCodeDateMoisAnnee(production.getDate());
   		 Utils.compterAbsenceEmploye(code, detailProdGen.getEmploye(), production.getDate());
   		 delai=BigDecimal.ZERO;
   		 heureSupp25=BigDecimal.ZERO;
   		 heureSupp50=BigDecimal.ZERO;
   	} 
		
		 
		coutHoraire=delai.multiply(heure.getValeur());	
		//coutHoraire=detailProdGen.getEmploye().getCoutHoraire().multiply(delai);
		coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
		coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
		
		coutTotalEmployeEmbalage=coutTotalEmployeEmbalage.add(coutHoraire).add(coutSupp25).add(coutSupp50);
		detailProdGen.setCoutTotal(coutHoraire);
		detailProdGen.setDelaiEmploye(delai);
		detailProdGen.setRemise(remise);
		detailProdGen.setHeureSupp25(heureSupp25);
		detailProdGen.setHeureSupp50(heureSupp50);
		detailProdGen.setCoutSupp25(coutSupp25);
		detailProdGen.setCoutSupp50(coutSupp50);
		
		if(!detailProdGen.getEmploye().isSalarie()){
		FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailProdGen.getEmploye().getId());
		if(ficheEmploye!=null){
			/*Remplir fiche programme*/
			//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
			delai=delai.add(ficheEmploye.getDelaiEmploye());
			String numOF=ficheEmploye.getNumOF()+"-"+production.getNumOF();
			BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(production.getNbreHeure()) ;
	/*	ficheEmploye.setDateSituation(production.getDate());
		
		ficheEmploye.setEmploye(detailProdGen.getEmploye());;
		
		ficheEmploye.setHeureSupp25(heureSupp25);
		ficheEmploye.setHeureSupp50(heureSupp50);
		ficheEmploye.setCoutSupp25(coutSupp25);
		ficheEmploye.setCoutSupp50(coutSupp50);*/
		
		ficheEmploye.setNumOF(numOF);
	//	ficheEmploye.setCoutTotal(coutHoraire);
		ficheEmploye.setDelaiProd(delaiProd);
		
		ficheEmploye.setDelaiEmploye(delai);
		
		 if(detailProdGen.isAbsent()==false && ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd())>=0){
	   			
				
				if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
					remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
					remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
	   			
	   		}else {
	   			remise=BigDecimal.ZERO;
	   		}
		 ficheEmploye.setRemise(remise);
		ficheEmployeDAO.edit(ficheEmploye);
		}else {
			ficheEmploye =new FicheEmploye();
		//	ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setNumOF(production.getNumOF());
			ficheEmploye.setDateSituation(production.getDate());
			ficheEmploye.setDelaiEmploye(delai);
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
		//	ficheEmploye.setCoutSupp25(coutSupp25);
		//	ficheEmploye.setCoutSupp50(coutSupp50);
			
			
			 if(detailProdGen.isAbsent()==false && delai.compareTo(production.getNbreHeure()) >=0){
		   			
					
					if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 
			 ficheEmploye.setRemise(remise);
			 ficheEmploye.setDelaiProd(production.getNbreHeure());
			ficheEmployeDAO.add(ficheEmploye);
			
		}
		
		}
		listDetailDetailProdGenTmp.add(detailProdGen);
	}
	}
	return listDetailDetailProdGenTmp;
	
}

boolean remplirQuantite(){
	boolean trouve=false;
	boolean remplir=false;
	listMPOffrePFMixteNonSaisir.clear();
	listSubCategorieMPClientNonSaisir.clear();
	mapMPClientSaisir.clear();
	for(int j=0;j<table.getRowCount();j++){
		remplir=false;
		
		if(!table.getValueAt(j, 2).toString().equals(""))
		{
			if(!table.getValueAt(j, 8).toString().trim().equals("")){
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 8).toString()); 
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), String.valueOf(0));
			}
			if(!table.getValueAt(j, 9).toString().trim().equals("")){
				
				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 9).toString());
				
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(),  String.valueOf(0));
			}
			if(!table.getValueAt(j, 10).toString().trim().equals("")){
				
				mapQuantiteManquante.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 10).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteManquante.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(),  String.valueOf(0));
			}
			
			if(!table.getValueAt(j, 11).toString().trim().equals("")){
				
				mapQuantiteOffre.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 11).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteOffre.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(),  String.valueOf(0));
			}
			
			if(!table.getValueAt(j, 12).toString().trim().equals("")){
				
				mapQuantiteReste.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 12).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteReste.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(),  String.valueOf(0));
			}
			
			
		if(!table.getValueAt(j, 13).toString().trim().equals("")){
				
				mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 13).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(),  String.valueOf(0));
			}
			
	       if(!table.getValueAt(j, 14).toString().equals(null)){
				if(!table.getValueAt(j, 14).toString().equals(""))
				{
					mapCodeFournisseurMP.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 14).toString());
					trouve=true;
				}
				
			}
	      
	       if(!table.getValueAt(j, 14).toString().equals(null)){
	   			if(!table.getValueAt(j, 14).toString().equals(""))
	   			{
	   				mapCodeFournisseurDechet.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 14).toString());
	   				trouve=true;
	   			}
	   			
	   		}
	       
			
		}else
		{
			if(!table.getValueAt(j, 8).toString().trim().equals("")){
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 8).toString()); 
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), String.valueOf(0));
			}
			if(!table.getValueAt(j, 9).toString().trim().equals("")){
				
				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 9).toString());
				
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
			}
			if(!table.getValueAt(j, 10).toString().trim().equals("")){
				
				mapQuantiteManquante.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 10).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteManquante.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
			}
			
			if(!table.getValueAt(j, 11).toString().trim().equals("")){
				
				mapQuantiteOffre.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 11).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteOffre.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
			}
			
			if(!table.getValueAt(j, 12).toString().trim().equals("")){
				
				mapQuantiteReste.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 12).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteReste.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
			}
			
			
		if(!table.getValueAt(j, 13).toString().trim().equals("")){
				
				mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 13).toString());
				trouve=true;
				remplir=true;
			}else {
				mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
			}
			
	       if(!table.getValueAt(j, 14).toString().equals(null)){
				if(!table.getValueAt(j, 14).toString().equals(""))
				{
					mapCodeFournisseurMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 14).toString());
					trouve=true;
				}
				
			}
	      
	       if(!table.getValueAt(j, 14).toString().equals(null)){
	   			if(!table.getValueAt(j, 14).toString().equals(""))
	   			{
	   				mapCodeFournisseurDechet.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 14).toString());
	   				trouve=true;
	   			}
	   			
	   		}
	       
		}
		

       
	  	 for(int t=0;t<lisDetailEstimation.size();t++)
			 {
				 
      		 
      			 
      			 if(lisDetailEstimation.get(t).getMatierePremier().getCode().equals( table.getValueAt(j, 0).toString()))
 				 {
 					 if(!lisDetailEstimation.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
 					 {
 						if(lisDetailEstimation.get(t).getPriorite()==1)
     					{
 							if(remplir==false)
 							{
 								
 								listSubCategorieMPClientNonSaisir.add(lisDetailEstimation.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp());
 								 
 							 
 							}
 							
 							
 							
 							 
 								mapMPClientSaisir.put(lisDetailEstimation.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode(), remplir);
 								 
 							
     					}
 						 
 					 
 					 }
 					 
 					
 				 }
      			 
      		 
      		 
			
				 
			 }
	  	 
	  	 
	  	 
	  	 if(OffrePFMixte==true)
	  	 {
	  		 
	  		if(mapMPOffrePFMixte.get(table.getValueAt(j, 0).toString())!=null)
	  		{
	  			
	  			if(remplir==false)
					{
	  				
	  				if(!listMPOffrePFMixte.get(listMPOffrePFMixte.size()-1).equals( table.getValueAt(j, 0).toString()))
	  				{
	  				
	  					listMPOffrePFMixteNonSaisir.add( table.getValueAt(j, 0).toString());
	  					
	  				}
	  				
					}
	  			
	  			
	  			
	  		}
	  		 
	  		 
	  	 }
       
       
       
		
		
	}
	return trouve;
}
List<CoutMP> validerSaisiQuantiteConsomme(List<CoutMP> listCoutMP) {
	
	
	
	
	
	 
	mapQuantiteResterConsommer.clear();
	 
	 msgErreur="";
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteDechetFour=BigDecimal.ZERO;
	BigDecimal quantiteManquante=BigDecimal.ZERO;
	BigDecimal quantiteOffre=BigDecimal.ZERO;
	BigDecimal quantiteManquanteFrPlus=BigDecimal.ZERO;
	String codeFournisseur;
	String codeFournisseurdechet;
	List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	BigDecimal QuantiteConsommeTotalEnVrac=BigDecimal.ZERO;
	boolean ManquePlus=false;
	listCoutMPEnVrac.clear();
	boolean erreurGrammageOffre=false;
	String msgerreurGrammageOffre="";
	 List<DetailEstimationPromo> listeDetailEstimationPromo=new ArrayList<DetailEstimationPromo>();
	
	if(production.getOffre()!=null){
		Promotion promotion=PromotionDAO.findByCode(production.getOffre());
		
		  listeDetailEstimationPromo=promotion.getDetailEstimationPromo();
		  	  
	}
	
	boolean  OffreEnvracExiste=false;
	BigDecimal QuantiteCarton=BigDecimal.ZERO;
	
	String EnVracAvecFournisseur="";
	
	if(!txtQuantiteOffre.getText().equals(""))
	{
		production.setQuantiteOffre(new BigDecimal(txtQuantiteOffre.getText()));
	}
	
	
	if(radioMoins.isSelected()==true)
	{
		if(!txtQuantiteMoins.getText().equals(""))
		{
			production.setQuantiteMoins(new BigDecimal(txtQuantiteMoins.getText()));
			
		}else
		{
			production.setQuantiteMoins(BigDecimal.ZERO);
		}
		
		
	}else
	{
		production.setQuantiteMoins(BigDecimal.ZERO);
	}
	
	if(radioPlus.isSelected()==true)
	{
		if(!txtQuantitePlus.getText().equals(""))
		{
			production.setQuantitePlus(new BigDecimal(txtQuantitePlus.getText()));
			
		}else
		{
			production.setQuantitePlus(BigDecimal.ZERO);
		}
		
		
	}else
	{
		production.setQuantitePlus(BigDecimal.ZERO);
	}
	
	
	
	
	
	for(int i=0;i<listCoutMP.size();i++){ 
		 
			CoutMP coutMP=listCoutMP.get(i);
			
		
			
			
			
			if(coutMP.getFournisseurMP()!=null)
			{
				quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
				quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
				quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
				quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
				quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
				quantiteManquanteFrPlus=new BigDecimal(mapQuantiteManquanteFrPlus.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
		 
				
			}else
			{
				
			
				quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));
				quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
				quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));
				quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));
				quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));
				quantiteManquanteFrPlus=new BigDecimal(mapQuantiteManquanteFrPlus.get(coutMP.getMatierePremier().getCode()));
				
				 
			}
			
			
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				
				QuantiteConsommeTotalEnVrac=QuantiteConsommeTotalEnVrac.add((coutMP.getQuantCharge().add(coutMP.getQuantExistante()).add(coutMP.getQuantChargeSupp())).subtract(quantiteReste));
				
				
			for(int t=0;t<listeDetailEstimationPromo.size();t++)
			{
			
				DetailEstimationPromo detailEstimationPromo=listeDetailEstimationPromo.get(t);
				
				 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				 {
					 OffreEnvracExiste=true; 
					 
					 
				 }
				
				
			}
			
			
			
			EnVracAvecFournisseur=coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur();
				
				
			}
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
			{
				if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
				{
					
					 for(int y=0;y<lisDetailEstimation.size();y++)
					 {
						 if(lisDetailEstimation.get(y).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
						 {
							 if(lisDetailEstimation.get(y).getQuantite().compareTo(BigDecimal.ZERO)!=0)
							 {
									QuantiteCarton=QuantiteCarton.add(new BigDecimal(txtQuantiteRealise.getText()).divide(lisDetailEstimation.get(y).getQuantite() , 6,RoundingMode.FLOOR)) ;

							 }
							 

						 }

						 
					 }
					
					
					
					
					
					
				}
				
				
			}
			
			
			
			
		
  
	}

	
	 quantiteDechet=BigDecimal.ZERO;
	 quantiteReste=BigDecimal.ZERO;
	 quantiteDechetFour=BigDecimal.ZERO;
	 quantiteManquante=BigDecimal.ZERO;
	 quantiteOffre=BigDecimal.ZERO;
	 quantiteManquanteFrPlus=BigDecimal.ZERO;
	
	
	
BigDecimal estimationEnvrac=BigDecimal.ZERO;	
BigDecimal estimationOffre=BigDecimal.ZERO;
BigDecimal QuantiteOffreTotalConsommer=BigDecimal.ZERO;

BigDecimal QuantiteConsommeTotalCarton=BigDecimal.ZERO;
BigDecimal QuantiteConsommeTotalBox=BigDecimal.ZERO;
BigDecimal EstimationTotalCarton=BigDecimal.ZERO;
BigDecimal EstimationTotalBox=BigDecimal.ZERO;
boolean  QuantiteInferieurAzero=false;
BigDecimal QuantiteTotalOffreEnVrac=BigDecimal.ZERO;
BigDecimal QuantiteTotalPlusEnVrac=BigDecimal.ZERO;
BigDecimal NombreBoxUtiliser=BigDecimal.ZERO;
boolean BoxClientExiste=false;
boolean CartonClientExiste=false;
	boolean ErreurQuantiteOffre=false;
for(int i=0;i<listCoutMP.size();i++){ 
	codeFournisseur="";
	codeFournisseurdechet="";
		CoutMP coutMP=listCoutMP.get(i);
		
		estimationEnvrac=BigDecimal.ZERO;	
		
		
		
		if(coutMP.getFournisseurMP()!=null)
		{
			quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			quantiteManquanteFrPlus=new BigDecimal(mapQuantiteManquanteFrPlus.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
			
			if(mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				codeFournisseur=mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur());
			}
			if(!codeFournisseur.equals(""))
			{
				coutMP.setCodeFournisseur(codeFournisseur);
			}
			
			if(mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				codeFournisseurdechet=mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur());
			}
			
			
			if(!codeFournisseurdechet.equals(""))
			{
				coutMP.setCodeFournisseurdechet(codeFournisseurdechet);
			}
			
		}else
		{
			
		
			quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));
			quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
			quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));
			quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));
			quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));
			quantiteManquanteFrPlus=new BigDecimal(mapQuantiteManquanteFrPlus.get(coutMP.getMatierePremier().getCode()));
			
			if(mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode())!=null)
			{
				codeFournisseur=mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode());
			}
			if(!codeFournisseur.equals(""))
			{
				coutMP.setCodeFournisseur(codeFournisseur);
			}
			
			if(mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode())!=null)
			{
				codeFournisseurdechet=mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode());
			}
			
			
			if(!codeFournisseurdechet.equals(""))
			{
				coutMP.setCodeFournisseurdechet(codeFournisseurdechet);
			}
		}
	
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			
			if(QuantiteConsommeTotalEnVrac.compareTo(BigDecimal.ZERO)!=0)
			{
				
				estimationEnvrac=((coutMP.getQuantCharge().add(coutMP.getQuantExistante()).add(coutMP.getQuantChargeSupp())).subtract(quantiteReste)).divide(QuantiteConsommeTotalEnVrac, 12, RoundingMode.HALF_UP);
				
				coutMP.setQuantConsomme((estimationEnvrac).multiply(new BigDecimal(txtQuantiteRealise.getText())));
				if(OffreEnvracExiste==true)
				{
					if(!txtQuantiteOffre.getText().equals(""))
					{
						coutMP.setQuantiteOffre(estimationEnvrac.multiply(new BigDecimal(txtQuantiteOffre.getText())));
					}else
					{
						coutMP.setQuantiteOffre(BigDecimal.ZERO);
					}
				}else
				{
					coutMP.setQuantiteOffre(BigDecimal.ZERO);
				}
				
				
				
				
				
				if(radioMoins.isSelected()==true)
				{
					if(!txtQuantiteMoins.getText().equals(""))
					{
						coutMP.setQuantiteManquante(estimationEnvrac.multiply(new BigDecimal(txtQuantiteMoins.getText())));
						
					}else
					{
						coutMP.setQuantiteManquante(BigDecimal.ZERO);
					}
					
					
				}else
				{
					coutMP.setQuantiteManquante(BigDecimal.ZERO);
				}
				
				if(radioPlus.isSelected()==true)
				{
					if(!txtQuantitePlus.getText().equals(""))
					{
						coutMP.setQuantiteManquanteFrPlus(estimationEnvrac.multiply(new BigDecimal(txtQuantitePlus.getText())));
						
					}else
					{
						coutMP.setQuantiteManquanteFrPlus(BigDecimal.ZERO);
					}
					
					
				}else
				{
					coutMP.setQuantiteManquanteFrPlus(BigDecimal.ZERO);
				}
				
				
				
				
			}
			
		}else
		{
			
			if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
			{
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
				{
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
					{
						if(coutMP.getMatierePremier().getType()!=null) 
							{
							
							if(coutMP.getMatierePremier().getType().equals(Constantes.MP_CLIENT))
							{
								BoxClientExiste=true;
							}
							
							}
						
						NombreBoxUtiliser=NombreBoxUtiliser.add(BigDecimal.ONE);
						
					}
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
					{
						if(coutMP.getMatierePremier().getType()!=null) 
							{
							
							if(coutMP.getMatierePremier().getType().equals(Constantes.MP_CLIENT))
							{
								CartonClientExiste=true;
							}
							
							}
						
						 
						
					}
					
					if(production.getOffre()!=null){
						
						for(int f=0;f<listeDetailEstimationPromo.size();f++)
						{
							
						DetailEstimationPromo detailEstimationPromo=	listeDetailEstimationPromo.get(f);
						
						if(detailEstimationPromo.getMatierePremiere().getCode().equals(coutMP.getMatierePremier().getCode()))
						{
							
							if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
							{
								
								 
								coutMP.setQuantiteOffre((QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite())).setScale(0, RoundingMode.FLOOR));
								
							}else
							{
								coutMP.setQuantiteOffre(QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite()));
								
							}
						
							//coutMP.setQuantConsomme(coutMP.getQuantConsomme().subtract(coutMP.getQuantiteOffre()));
							
							
							
							
							
						}
							
							
						}
						
						
						
						
						
					}
					
					
					 
						 
						 for(int y=0;y<lisDetailEstimation.size();y++)
						 {
							 if(lisDetailEstimation.get(y).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
							 {
								 
								 
								 
								 if(lisDetailEstimation.get(y).getPriorite()==0 || lisDetailEstimation.get(y).getPriorite()==1 )
								 {
									 
									 System.out.println(( " Charger : "+ coutMP.getQuantCharge() +" existante : "+coutMP.getQuantExistante()+" Plus : "+quantiteManquanteFrPlus+" Supp : "+coutMP.getQuantChargeSupp() +" - Dechet : "+quantiteDechet+" - Dechet F : "+quantiteDechetFour+" - Manque : "+quantiteManquante)+" - Reste : "+quantiteReste+" - Offre : "+coutMP.getQuantiteOffre());
									 
									 
									 if(lisDetailEstimation.get(y).getQuantite().compareTo(BigDecimal.ZERO)==0)
									 {
										 
											coutMP.setQuantConsomme(BigDecimal.ZERO);
											mapQuantiteResterConsommer.put(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode(),(coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre()))));
										 
											 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
												{
													QuantiteConsommeTotalBox=QuantiteConsommeTotalBox.add(coutMP.getQuantConsomme());
													EstimationTotalBox=EstimationTotalBox.add(coutMP.getEstimation());
													
													
												}
												
												if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) )
												{
													
													QuantiteConsommeTotalCarton=QuantiteConsommeTotalCarton.add(coutMP.getQuantConsomme());
													EstimationTotalCarton=EstimationTotalCarton.add(coutMP.getEstimation());
												}
										 
										 
										 
										 
									 }else
									 {
										 
										 if(((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre())))).compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(lisDetailEstimation.get(y).getQuantite() , 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR))<=0)
											{
											 
											 
												coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre()))));
												mapQuantiteResterConsommer.put(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode(), (new BigDecimal(txtQuantiteRealise.getText()).divide(lisDetailEstimation.get(y).getQuantite() , 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR)).subtract((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre())))));
											 
												 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
													{
														QuantiteConsommeTotalBox=QuantiteConsommeTotalBox.add(coutMP.getQuantConsomme());
														EstimationTotalBox=EstimationTotalBox.add(coutMP.getEstimation());
														
														
													}
													
													if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) )
													{
														
														QuantiteConsommeTotalCarton=QuantiteConsommeTotalCarton.add(coutMP.getQuantConsomme());
														EstimationTotalCarton=EstimationTotalCarton.add(coutMP.getEstimation());
													}
											
											
											}else
											{
												
												msgErreur=msgErreur+" "+coutMP.getMatierePremier().getNom() +"\n";
												
												
											}
										 
									 }
									 
									
										
									
 
									 
									 
								 }else if(lisDetailEstimation.get(y).getPriorite()==2)
								 {
									 if(mapQuantiteResterConsommer.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode())!=null)
									 {
											coutMP.setQuantConsomme(mapQuantiteResterConsommer.get(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()));
											
											if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
											{
												QuantiteConsommeTotalBox=QuantiteConsommeTotalBox.add(coutMP.getQuantConsomme());
												EstimationTotalBox=EstimationTotalBox.add(coutMP.getEstimation());
												
												
											}
											
											if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) )
											{
												
												QuantiteConsommeTotalCarton=QuantiteConsommeTotalCarton.add(coutMP.getQuantConsomme());
												EstimationTotalCarton=EstimationTotalCarton.add(coutMP.getEstimation());
											}
											
											

									 }
									 
									 
										
 
									 
								 }
									 

								 
							 }

							 
						 }
						 

					  
					
					
				}else
				{
					
	if(production.getOffre()!=null){
						
						for(int f=0;f<listeDetailEstimationPromo.size();f++)
						{
							
						DetailEstimationPromo detailEstimationPromo=	listeDetailEstimationPromo.get(f);
						
						if(detailEstimationPromo.getMatierePremiere().getCode().equals(coutMP.getMatierePremier().getCode()))
						{
							
							if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
							{
								
								coutMP.setQuantiteOffre((QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite())).setScale(0, RoundingMode.FLOOR));
								
							}else
							{
								coutMP.setQuantiteOffre(QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite()));
								
							}
						
							//coutMP.setQuantConsomme(coutMP.getQuantConsomme().subtract(coutMP.getQuantiteOffre()));
							
							
							
							
							
						}
							
							
						}
						
						 
						
					}
					
					coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(quantiteOffre))));	
				}
					
				
				
		
				
				
			
			}else
			{
				
				if(production.getOffre()!=null || production.getTyoeOffre()!=null ){
					
					if(OffrePFMixte==true)
					{
						 
						if(coutMP.getMatierePremier().getTypeOffre()!=null)
						{
if( production.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
{

	if(coutMP.getMatierePremier().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
	{
		
		if(!listMPOffrePFMixte.get(listMPOffrePFMixte.size()-1).equals(coutMP.getMatierePremier().getCode()))
		{
			coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
			coutMP.setQuantiteOffre((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
			QuantiteOffreTotalConsommer=QuantiteOffreTotalConsommer.add(coutMP.getQuantConsomme());
			if(coutMP.getMatierePremier().getGrammageOffre().compareTo(BigDecimal.ZERO)!=0)
			{
				if( (coutMP.getQuantConsomme().multiply(new BigDecimal(1000)).remainder(coutMP.getMatierePremier().getGrammageOffre())).compareTo(BigDecimal.ZERO)!=0)
				{
					
				erreurGrammageOffre=true;
				msgerreurGrammageOffre=coutMP.getMatierePremier().getNom()+"\n";
					
					
					
				}
				
			}
	
		
		}else
		{
			if( production.getQuantiteOffre().compareTo(QuantiteOffreTotalConsommer)<0)
			{
				ErreurQuantiteOffre=true;
			}else
			{
				
				if(QuantiteOffreTotalConsommer.compareTo(BigDecimal.ZERO)!=0)
				{
					ErreurQuantiteOffre=false;
					coutMP.setQuantConsomme(production.getQuantiteOffre().subtract(QuantiteOffreTotalConsommer));
					coutMP.setQuantiteOffre(production.getQuantiteOffre().subtract(QuantiteOffreTotalConsommer));
					if(coutMP.getMatierePremier().getGrammageOffre().compareTo(BigDecimal.ZERO)!=0)
					{
						if( (coutMP.getQuantConsomme().multiply(new BigDecimal(1000)).remainder(coutMP.getMatierePremier().getGrammageOffre())).compareTo(BigDecimal.ZERO)!=0)
						{
							
						erreurGrammageOffre=true;
						msgerreurGrammageOffre=coutMP.getMatierePremier().getNom()+"\n";
							
							
							
						}
					}
					
					
					if((coutMP.getQuantConsomme().add(QuantiteOffreTotalConsommer)).compareTo(production.getQuantiteOffre())!=0)
					{
						ErreurQuantiteOffre=true;
					}
					
					
					
					
				}else
				{
					
					coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
					coutMP.setQuantiteOffre((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
				
					if(coutMP.getMatierePremier().getGrammageOffre().compareTo(BigDecimal.ZERO)!=0)
					{
						if( (coutMP.getQuantConsomme().multiply(new BigDecimal(1000)).remainder(coutMP.getMatierePremier().getGrammageOffre())).compareTo(BigDecimal.ZERO)!=0)
						{
							
						erreurGrammageOffre=true;
						msgerreurGrammageOffre=coutMP.getMatierePremier().getNom()+"\n";
							
							
							
						}
						
					}
					
					if(coutMP.getQuantConsomme().compareTo(production.getQuantiteOffre())!=0)
					{
						ErreurQuantiteOffre=true;
					}
					
				
				}
				
	
				
			
			}
			
		 
		}
		
	}
	
}else if( production.getTyoeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_AUTRES_PF))
{
	
/*
	if(coutMP.getMatierePremier().getTypeOffre().getTypeOffre().equals(Constantes.TYPE_OFFRE_PF))
	{
		*/
		if(listMPOffrePFMixte.get(listMPOffrePFMixte.size()-1).equals(coutMP.getMatierePremier().getCode()))
		{
			coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
			coutMP.setQuantiteOffre((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
			QuantiteOffreTotalConsommer=QuantiteOffreTotalConsommer.add(coutMP.getQuantConsomme());
			if(coutMP.getMatierePremier().getGrammageOffre().compareTo(BigDecimal.ZERO)!=0)
			{
				if( (coutMP.getQuantConsomme().multiply(new BigDecimal(1000)).remainder(coutMP.getMatierePremier().getGrammageOffre())).compareTo(BigDecimal.ZERO)!=0)
				{
					
				erreurGrammageOffre=true;
				msgerreurGrammageOffre=coutMP.getMatierePremier().getNom()+"\n";
					
					
					
				}
				
			}
			
			if(coutMP.getQuantConsomme().compareTo(production.getQuantiteOffre())!=0)
			{
				ErreurQuantiteOffre=true;
			}
	
		/*
		}*/
		
	} else
		
	{
		
		coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
		coutMP.setQuantiteOffre((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste))));
for(int d=0;d<listeDetailEstimationPromo.size();d++)
	
{
	
	if(listeDetailEstimationPromo.get(d).getMatierePremiere().getId()==coutMP.getMatierePremier().getId())
	{
		if(coutMP.getQuantConsomme().compareTo(listeDetailEstimationPromo.get(d).getQuantite().multiply(QuantiteCarton.setScale(0, RoundingMode.FLOOR)))!=0)
		{
			msgErreur=msgErreur+" "+coutMP.getMatierePremier().getNom() +"\n";
		}
	}
	
}
		
		
		
		
	}
	
	
}
				
							
						}
			
						
						
						
						
						
					}else
					{
						for(int f=0;f<listeDetailEstimationPromo.size();f++)
						{
							
						DetailEstimationPromo detailEstimationPromo=	listeDetailEstimationPromo.get(f);
						
						if(detailEstimationPromo.getMatierePremiere().getCode().equals(coutMP.getMatierePremier().getCode()))
						{
						
								coutMP.setQuantConsomme(QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite()));
								
								coutMP.setQuantiteOffre(QuantiteCarton.setScale(0, RoundingMode.FLOOR).multiply(detailEstimationPromo.getQuantite()));
							
							
							
						}
							
							
						}
						
						
						
						
						
					}
					
				 
					
				}
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		
		
		if(production.getOffre()!=null){
			
		if(OffreEnvracExiste==true)
		{
			
			estimationOffre=new BigDecimal(txtQuantiteOffre.getText()).divide(new BigDecimal(txtQuantiteRealise.getText()).add(new BigDecimal(txtQuantiteOffre.getText())), 12, RoundingMode.HALF_UP);
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD))
			{
				coutMP.setQuantiteOffre(((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste)))).multiply(estimationOffre));
				
				coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre()))));
			}
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL))
			{
				coutMP.setQuantiteOffre(((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste)))).multiply(estimationOffre));
				
				coutMP.setQuantConsomme((coutMP.getQuantCharge().add(coutMP.getQuantExistante().add(quantiteManquanteFrPlus).add(coutMP.getQuantChargeSupp()))).subtract(quantiteDechet.add(quantiteDechetFour.add(quantiteManquante).add(quantiteReste).add(coutMP.getQuantiteOffre()))));
				
				
				
			}
			
		}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		coutMP.setQuantDechet(quantiteDechet);
		coutMP.setQuantReste(quantiteReste);
		coutMP.setQuantDechetFournisseur(quantiteDechetFour);
		
	
		if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			coutMP.setQuantiteManquante(quantiteManquante);
			coutMP.setQuantiteManquanteFrPlus(quantiteManquanteFrPlus);
		} 
		
		if(coutMP.getQuantConsomme().compareTo(BigDecimal.ZERO)<0)
		{
			QuantiteInferieurAzero=true;
		}
		
		
		
		//listCoutMP.set(i,coutMP);
		listCoutMPTmp.add(coutMP);
}



 
	if(NombreBoxUtiliser.compareTo(BigDecimal.ONE)==0)
	{
		if(msgErreur.equals(""))
		{
			if(QuantiteConsommeTotalBox.compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(EstimationTotalBox , 6,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR))!=0)
			{
				msgErreur=msgErreur+" BOX " +"\n";
				
			}
		}
	
		
	}else
	{
		
		 
			for(int i=0;i<listCoutMP.size();i++)
			{ 
				CoutMP coutMP=listCoutMP.get(i);
				
				if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
				{
					if(BoxClientExiste==true)
					{

						if(msgErreur.equals(""))
						{
							if(QuantiteConsommeTotalBox.compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(EstimationTotalBox, 6, RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR))!=0)
							{
								msgErreur=msgErreur+" BOX " +"\n";
								
							}
						}
					
						
						
					}else
					{
						if(msgErreur.equals(""))
						{
							if(coutMP.getQuantConsomme().compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 6, RoundingMode.FLOOR).divide(NombreBoxUtiliser, 6, RoundingMode.FLOOR))!=0)
							{
								msgErreur=msgErreur+" BOX " +"\n";
								
							}
						}
					
					}
					
				
					
					
					
				}
				
				
			
				
			}
			
			
			
			
		 
		
	}
	
	for(int i=0;i<listCoutMP.size();i++)
	{ 
		CoutMP coutMP=listCoutMP.get(i);
		
		if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) )
		{
			
			if(EstimationTotalCarton.compareTo(BigDecimal.ZERO)!=0)
			{
				
				if(CartonClientExiste==true)
				{
					
					if(msgErreur.equals(""))
					{
						if(QuantiteConsommeTotalCarton.compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(EstimationTotalCarton, 6, RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR))!=0)
						{
							msgErreur=msgErreur+" CARTON " +"\n";
							
						}
					}
				
					
				}else
				{
					if(msgErreur.equals(""))
					{
						
						if(QuantiteConsommeTotalCarton.compareTo(new BigDecimal(txtQuantiteRealise.getText()).divide(EstimationTotalCarton , 0,RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR))!=0)
						{
							msgErreur=msgErreur+" CARTON " +"\n";
							
						}
					}
				
				}
				

				
				
				
				
			}
			
			
		}
		
	}
	

 







if(ErreurQuantiteOffre==true)
{
	validerSaisie=false;
	JOptionPane.showMessageDialog(null, "Les Quantités Consommées Offres est Supérieur à la Quantite Offre Déclarer !!!" );
	 
	
}




if(msgErreur.equals(""))
{
	
	if(ErreurQuantiteOffre==false)
	{
		afficher_tableMP(listCoutMPTmp);

		validerSaisie=true;
	}
	
}else
{
	validerSaisie=false;
	JOptionPane.showMessageDialog(null, "Les Quantités Consommées Des MP Suivants est Supérieur au Quantites Consommée Exacte : \n" +msgErreur);
	 
	 
}

if(QuantiteInferieurAzero==true)
{
	validerSaisie=false;
	JOptionPane.showMessageDialog(null, "Les Quantités Consommées doit etre Supérieur à 0 SVP ");
	 
}


if(erreurGrammageOffre==true)
{
	validerSaisie=false;

	JOptionPane.showMessageDialog(null, "Le Grammage Des Offre Suivante Est different au Quantite Saisi, Veuillez entrer les Quantite exacte aux Offres SVP :"+msgerreurGrammageOffre);

}


return listCoutMPTmp;
	
}

List<CoutMP>  calculeCoutMatierePremiere(List<CoutMP> listCoutMP){
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteMP=BigDecimal.ZERO;
	
	BigDecimal quantiteDechetFour=BigDecimal.ZERO;
	BigDecimal quantiteManquante=BigDecimal.ZERO;
	BigDecimal quantiteOffre=BigDecimal.ZERO;
	
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	BigDecimal prixMP=BigDecimal.ZERO;
	BigDecimal coutDechet=BigDecimal.ZERO;
	BigDecimal coutDechetFour=BigDecimal.ZERO;
	BigDecimal coutManquante=BigDecimal.ZERO;
	BigDecimal coutQuantiteOffre=BigDecimal.ZERO;
	BigDecimal quantiteStock=BigDecimal.ZERO;
	String codeFournisseur;
	String codeFournisseurdechet;
	List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	//StockMP stockmp=new StockMP();
detailTransferStockMPDAO.ViderSession();
	
	Date dateDebutPrevue=listCoutMP.get(0).getProdcutionCM().getDate();
	
	CalculerStockFinale(listCoutMP.get(0).getProdcutionCM().getMagasinStockage(), dateDebutPrevue);
	listEtatStockMPAfficherMagasinStockage.addAll(listEtatStockMPAfficher);
	
	listDetailManqueDechetFournisseur.clear();
	listDetailTransfertStockMP.clear();
	ManqueDechetFournisseur manqueDechetFournisseur= new ManqueDechetFournisseur(); 
	TransferStockMP transferStockMPTmp=new TransferStockMP();
	
	
	
	
	for(int i=0;i<listCoutMP.size();i++){ 
		
		CoutMP coutMP=listCoutMP.get(i);
		prixUnitaire=coutMP.getPrixUnitaire();
		/*
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			if(coutMP.getFournisseurMP()!=null)
			{
				stockmp=stockMPDAO.findStockByMagasinMPByFournisseurMP(coutMP.getMatierePremier().getId(), production.getMagasinProd().getId(),coutMP.getFournisseurMP().getId());
			}else
			{
				
				 stockmp=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(), production.getMagasinProd().getId());
				
			}
			
			 
			
		}else
		{
			
			 stockmp=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(), production.getMagasinProd().getId());
			 
		}
		*/
	
		
		codeFournisseur="";
		codeFournisseurdechet="";
		//quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));
		quantiteConsomme=coutMP.getQuantConsomme();
		
		if(coutMP.getFournisseurMP()!=null)
		{
			
			if(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				if(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).contains(","))
				{
					quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).replace(",", "."));

				}else
				{
					
					quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));

					
				}
				
				
				
			}
		if(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
		{
			
			if(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).contains(","))
			{
				
				quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).replace(",", "."));

			}else
			{
				quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));

				
			}
			
			
		}
			if(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				if(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).contains(","))
				{
					quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).replace(",", "."));

					
				}else
				{
					quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));

				}
				
				
			}
		
			if(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				
				
				if(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).contains(","))
				{
					
					quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).replace(",", "."));

					
				}else
				{
					quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));

					
				}
				
			}
			
			if(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				if(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).contains(","))
				{
					quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()).replace(",", "."));

					
				}else
				{
					quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));

					
				}
				
				
			}
			
			
			
			if(mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				codeFournisseur=mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur());
			}
			
			if(mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
			{
				codeFournisseurdechet=mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur());
			}
			
			
		}else
		{
			
			if(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode())!=null)
			{
				if(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()).contains(","))
				{
					quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()).replace(",", "."));
				}else
				{
					


					quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()) );
				}
				
				
			}
			
			if(mapQuantiteReste.get(coutMP.getMatierePremier().getCode())!=null)
			{
				if(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()).contains(","))
				{
					quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()).replace(",", "."));
				}else
				{
					quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
				}
				
			}
			
			if(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode())!=null)
			{
				if(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()).contains(","))
				{
					quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()).replace(",", "."));
				}else
				{
					quantiteDechetFour=new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));
				}
				
				
			}
			if(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode())!=null)
			{
				if(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()).contains(","))
				{
					quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()).replace(",", "."));
					
				}else
				{
					quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));
				}
				
				
				
			}
			
			if(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode())!=null)
			{
				if(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()).contains(","))
				{
					quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()).replace(",", "."));
				}
				else
				{
					quantiteOffre=new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));
				}
				
			}
			
			
			
			if(mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode())!=null)
			{
				codeFournisseur=mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode());
			}
			
			if(mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode())!=null)
			{
				codeFournisseurdechet=mapCodeFournisseurDechet.get(coutMP.getMatierePremier().getCode());
			}
		}

				
		  // Modifier quantite dechet de transfer stock MP 
		TransferStockMP transferstockmp=null;
		DetailTransferStockMP detailtransferstockmp=null;
		transferstockmp=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE) ; 
		
		
		if(transferstockmp!=null)
			if(coutMP.getFournisseurMP()!=null)
			{
				   detailtransferstockmp=detailtransferMPDAO.findDetailTransferStockMPByMPByTransferMPByFournisseur (coutMP.getMatierePremier(), transferstockmp,coutMP.getFournisseurMP());

			}else
			{
				   detailtransferstockmp=detailtransferMPDAO.findDetailTransferStockMPByMPByTransferMP(coutMP.getMatierePremier(), transferstockmp);

			}
		     
		     if(detailtransferstockmp !=null){
		    		if(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE))
		    		{
		    			if(!detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		    			{
		    				detailtransferstockmp.setQuantiteDechet(quantiteDechet.setScale(0, RoundingMode.CEILING));
		    			}else
		    			{
		    				detailtransferstockmp.setQuantiteDechet(quantiteDechet);
		    			}
		    		
		    			
		    
		    			
		    			
		    		}else
		    		{
		    			detailtransferstockmp.setQuantiteDechet(quantiteDechet);
		    			
		    	
		    		}
		    	 
					
						detailtransferstockmp.setQuantiteOffre(quantiteOffre);
						detailtransferstockmp.setQuantiteRetour(quantiteReste);
						
		    		
		    	 detailtransferMPDAO.edit(detailtransferstockmp);
		    	
		    	 
		     }
		
		
		if(!codeFournisseur.equals(""))
		{
			coutMP.setCodeFournisseur(codeFournisseur);	
		}
		
		
		if(!codeFournisseurdechet.equals(""))
		{
			coutMP.setCodeFournisseurdechet(codeFournisseurdechet);	
		}
		
		
		
		//quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
		coutMP.setQuantConsomme(quantiteConsomme);
		coutMP.setQuantDechet(quantiteDechet);
		//quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
		prixMP=quantiteConsomme.multiply(prixUnitaire);
		coutDechet=quantiteDechet.multiply(prixUnitaire);
		coutDechetFour=quantiteDechetFour.multiply(prixUnitaire);
		coutManquante=quantiteManquante.multiply(prixUnitaire);
		
		if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		{

			coutQuantiteOffre=quantiteOffre.multiply(prixUnitaire);

		}

		
		
		coutMP.setPrixTotal(prixMP);
		coutMP.setCoutDechet(coutDechet);
		coutMP.setCoutDechetFournisseur(coutDechetFour);
		coutMP.setCoutManquante(coutManquante);
		coutMP.setCoutOffre(coutQuantiteOffre);
		
		coutTotalMP=coutTotalMP.add(prixMP) ;
		coutTotalDechet=coutTotalDechet.add(coutDechet).add(coutDechetFour).add(coutManquante).add(coutQuantiteOffre) ;
		
		//quantiteReste=stockmp.getStock()-quantiteConsomme;
		
		
		BigDecimal stockmpfinale=BigDecimal.ZERO;
		
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			
			for(int t=0;t<listEtatStockMPAfficherMagasinProduction.size();t++)
			{
				if(listEtatStockMPAfficherMagasinProduction.get(t).getMp().getId()==coutMP.getMatierePremier().getId())
				{
					
					if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP()!=null)
					{
						if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP().getId()==coutMP.getFournisseurMP().getId())
						{
							stockmpfinale=listEtatStockMPAfficherMagasinProduction.get(t).getQuantiteFinale();
						}
						
						
					}
					
					
					
				}
				
				
				
				
				
			}
			
		}else
		{
			for(int t=0;t<listEtatStockMPAfficherMagasinProduction.size();t++)
			{
				if(listEtatStockMPAfficherMagasinProduction.get(t).getMp().getId()==coutMP.getMatierePremier().getId())
				{
					if(listEtatStockMPAfficherMagasinProduction.get(t).getFournisseurMP()==null)
					{
						
						
							stockmpfinale=listEtatStockMPAfficherMagasinProduction.get(t).getQuantiteFinale();
					
						
						
					}
					
					
					
				}
				
				
				
				
				
			}
		}
		
		
		
		
		
		
		
		quantiteMP=stockmpfinale.subtract(quantiteConsomme.add(quantiteDechet).add(quantiteReste).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre));
		
		if(quantiteMP.compareTo(BigDecimal.ZERO) !=0){
			
			 Calendar cal = Calendar.getInstance();
		     cal.setTime(production.getDate());
		     int  annee = cal.get(Calendar.YEAR);
		     int mois = cal.get(Calendar.MONTH)+1;
		     
		
		CompteStockMP compteStockMP =compteStockMPDAO.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(),mois,annee);
	//stockmp.setStock(BigDecimal.ZERO);
		if(compteStockMP==null) {
			compteStockMP=new CompteStockMP();
			compteStockMP.setMatierePremier(coutMP.getMatierePremier());
			compteStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
			compteStockMP.setQuantite(quantiteMP);
			compteStockMP.setMois(mois);
			compteStockMP.setAnnee(annee);
			compteStockMPDAO.add(compteStockMP);
			
		}else {
			
			BigDecimal quantite =compteStockMP.getQuantite().add(quantiteMP) ;
		//	BigDecimal coutTotal =(compteStockMP.getQuantite()*compteStockMP.getPrixUnitaire())+(quantiteMP*coutMP.getPrixUnitaire());
			
			//BigDecimal prixUnitaire=coutTotal/Math.abs(quantite);
			
			
			compteStockMP.setQuantite(quantite);
		//	compteStockMP.setPrixUnitaire(prixUnitaire);
			
			compteStockMPDAO.edit(compteStockMP);
			
			
		}
	}
		
	
		
		
	// Ajouter Quantite dechet ( dans le manque dechet fournisseur )			
		
		if(quantiteDechet.compareTo(BigDecimal.ZERO)!=0)
		{
			
			DetailManqueDechetFournisseur detailManqueDechetFournisseur=new DetailManqueDechetFournisseur();
			detailManqueDechetFournisseur.setQuantiteDechet(quantiteDechet);
			detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
			detailManqueDechetFournisseur.setMagasinDechet(production.getMagasinDechet());
			if(coutMP.getFournisseurMP()!=null)
			{
				detailManqueDechetFournisseur.setFourniseur(coutMP.getFournisseurMP());	
			}
				
				detailManqueDechetFournisseur.setMatierePremier(coutMP.getMatierePremier());
				detailManqueDechetFournisseur.setManquedechetfournisseur(manqueDechetFournisseur);
				listDetailManqueDechetFournisseur.add(detailManqueDechetFournisseur);
				
				DetailTransferStockMP DetailTransferStockMPTMP=new DetailTransferStockMP();
				if(coutMP.getFournisseurMP()!=null)
				{
					DetailTransferStockMPTMP.setFournisseur (coutMP.getFournisseurMP());	
				}
				DetailTransferStockMPTMP.setMagasinDestination(production.getMagasinDechet());
				DetailTransferStockMPTMP.setMatierePremier(coutMP.getMatierePremier());
				DetailTransferStockMPTMP.setPrixUnitaire(coutMP.getPrixUnitaire());
				DetailTransferStockMPTMP.setQuantiteDechet(quantiteDechet);
				DetailTransferStockMPTMP.setQuantiteManque(BigDecimal.ZERO);
				DetailTransferStockMPTMP.setTransferStockMP(transferStockMPTmp);
			listDetailTransfertStockMP.add(DetailTransferStockMPTMP);
		}
		
	
			//stockmp.setStock(quantiteReste);
			//stockMPDAO.edit(stockmp);
			
		CoutMPDAO.edit(coutMP);
		listCoutMP.set(i, coutMP);
		
	}
	
	
	
	if(listDetailManqueDechetFournisseur.size()!=0)
	{
		
		    manqueDechetFournisseur.setDateCreation(new Date());
			manqueDechetFournisseur.setDateDechet(production.getDate());
			manqueDechetFournisseur.setNumOF(txtNumOF.getSelectedItem().toString());
			manqueDechetFournisseur.setEtat(Constantes.ETAT_VALIDER);
			manqueDechetFournisseur.setDetailManqueDechetFournisseur(listDetailManqueDechetFournisseur);
			manqueDechetFournisseur.setType(TYPE_DECHET);
 			manqueDechetFournisseurDAO.add(manqueDechetFournisseur);
	}
	
	
	
	if(listDetailTransfertStockMP.size()!=0)
	{
		transferStockMPTmp.setCodeTransfer(txtNumOF.getSelectedItem().toString());
		transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
		transferStockMPTmp.setDate(new Date());
		transferStockMPTmp.setDateTransfer(production.getDate());
		transferStockMPTmp.setStatut(TYPE_DECHET);
		transferStockMPTmp.setDepot(production.getMagasinDechet().getDepot());
		transferstockmpDAO.add(transferStockMPTmp);
		
		
		for(int i=0;i<listDetailTransfertStockMP.size();i++)
		{
			
		detailtransferMPDAO.add(listDetailTransfertStockMP.get(i));	
		
		}
		
		
		
	}
	
	
	
	
	
	return listCoutMP;
  }
void afficherDetailPorduction(List<DetailEstimation> lisDetailEstimation,List<CoutMP> listCoutMP){
	
	 
	
	
	for(int d=0;d<listCoutMP.size();d++){
		
		
		if(listCoutMP.get(d).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		
		{
				
				listCoutMP.get(d).setQuantite(listCoutMP.get(d).getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
			
	
		}
		
		
	
		
	}
	
	
	
	/*
	
	for(int k=0;k<lisDetailEstimation.size();k++){
		
		detailEstimation=lisDetailEstimation.get(k);
		for(int l=0;l<listCoutMP.size();l++){
			coutMP=listCoutMP.get(l);
			
			if(detailEstimation.getMatierePremier().getId()==coutMP.getMatierePremier().getId()){
			
					quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
						
						
						if(coutMP.getEstimation()!=null)
						{
							
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0 ,RoundingMode.DOWN);
								
							}else
							{
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0 ,RoundingMode.DOWN);
							}
							
							
						}else
						{
							quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0 ,RoundingMode.DOWN);
						}
						
						
						
						
						quantitecarton=quantiteConsommme.setScale(0,RoundingMode.DOWN );
						quantiteCalculerCarton=coutMP.getQuantite();
						
						
							
						} 
				
			
			}
			
			
			
			
		}
	}
	/*
	 * 
	 */
	
	
	/*
	quantiteConsommme=BigDecimal.ZERO;
	
	
	
	
	for(int i=0;i<lisDetailEstimation.size();i++){
		
		detailEstimation=lisDetailEstimation.get(i);
		for(int j=0;j<listCoutMP.size();j++){
			coutMP=listCoutMP.get(j);
			
			if(detailEstimation.getMatierePremier().getId()==coutMP.getMatierePremier().getId()){
			
					
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
					
					if(articleMixte==true){
						if(detailEstimation.getPriorite()==PRIORITE_ESTIMATION_2){
							
							if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
							{
								
								if( coutMP.getMoinsPlus()==null)
								{
									
									
									if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
									{
										if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
										{
											
											coutMP.setQuantConsomme(coutMP.getQuantExistante());
											listCoutMP.set(j,coutMP);

										}else
										{
											coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
											listCoutMP.set(j,coutMP);
										}
										
									
									
									
									}else
									{
										coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
										listCoutMP.set(j,coutMP);
									}
									
									
								
									
								}else
								{
									
									if(coutMP.getMoinsPlus().equals(""))
									{
										
										
										if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
										{
											if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
											{
												
												coutMP.setQuantConsomme(coutMP.getQuantExistante());
												listCoutMP.set(j,coutMP);

											}else
											{
												coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
												listCoutMP.set(j,coutMP);
											}
											
										
										
										}else
										{
											coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
											listCoutMP.set(j,coutMP);
										}
										
									
										
										
										
										
										
									}else
									{
										
										if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
										{
											
											if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) <0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_MOINS))
												{
													
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
												}else
												{
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
													
												}
												
												
												
											}else 	if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) >0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_PLUS))
												{
													
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
												}else
												{
													
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
													
												}
												
												
												
											}
											
											
											
										}
										
									}
									
								
								
								}
								
								
								
							}else
							{
								
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
								listCoutMP.set(j,coutMP);
								
								
								
							}
							
							
							
						
							
							
							
						}
					}else{
						
						if(detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
							
							if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
							{
								
								
								if( coutMP.getMoinsPlus()==null)
								{
									if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
									{
										if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
										{
											
											coutMP.setQuantConsomme(coutMP.getQuantExistante());
											listCoutMP.set(j,coutMP);

										}else
										{
											coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
											listCoutMP.set(j,coutMP);
										}
										
									
									
									}else
									{
										coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
										listCoutMP.set(j,coutMP);
									}
									
								
									
								}else
								{
									
									if(coutMP.getMoinsPlus().equals(""))
									{
										
										if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
										{
											if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
											{
												
												coutMP.setQuantConsomme(coutMP.getQuantExistante());
												listCoutMP.set(j,coutMP);

											}else
											{
												coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
												listCoutMP.set(j,coutMP);
											}
											
											
										
										
										}else
										{
											coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
											listCoutMP.set(j,coutMP);
										}
										
										
										
									}else
									{
										
										if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
										{
											
											if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) <0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_MOINS))
												{
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
													
												}else
												{
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
															
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
												}
												
												
												
												
											}else if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) >0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_PLUS))
												{
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
													
												}else
												{
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														
															
														}
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
												}
												
												
												
												
											}
											
									
											
											
											
											
											
											
											
										}
										
									}
									
								
								
								}
								
								
							}else
							{
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
								listCoutMP.set(j,coutMP);
								
							}
							
								
							
						}
					}
				
				
				}else {
					
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC))
					{
						
						if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
						{
							
								if(coutMP.getEstimation()!=null)
								{
									
									if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
									{
										
										quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									}else
									{
										quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
									}
									
								}else
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								}
								
							
							
						}else if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC) )
						{
							if(coutMP.getEstimation()!=null)
							{
								if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									
								}else
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								}
								
								
							}else
							{
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								
							}
							
							

							
						}else
						{
							if(coutMP.getEstimation()!=null)
							{
								if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
								{
									
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									
								}else
								{
									
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
									
								}
								
							}else
							{
								
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								
							}
							
						}
						
						
						
						
						
						
					}else
					{
						if(coutMP.getEstimation()!=null)
						{
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								
							}else
							{
								
								quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								
							}
							
						}else
						{
							
							quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
						}
						
					}
					
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC)){
						
						
						
						quantiteConsommme=quantiteConsommme.setScale(0,RoundingMode.DOWN);
						
							
						} 
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF))
						{
						
						if(coutMP.getEstimation()!=null)
						{
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								quantiteConsommme=(coutMP.getEstimation().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
								
							}else
							{
								quantiteConsommme=(detailEstimation.getQuantite().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
							}
							
						}else
						{
							
							quantiteConsommme=(detailEstimation.getQuantite().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
							
						}
							
							
							
							
							
						}
					
					
					
					// Calculer La Quantite Consomme des Offres = (QuantiteCalculerOffre / QuantiteCalculerCartons ) * QuantiteConsommeCarton
						 if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						{
							
							
							quantiteConsommme=coutMP.getQuantite().divide(quantiteCalculerCarton, 6, RoundingMode.HALF_UP).multiply(quantitecarton);
							
							
						}
					
					coutMP.setQuantConsomme(quantiteConsommme);
					listCoutMP.set(j,coutMP);
				}
				
				
				
				
				}
		
				
			
			}
			
		}
	
	
	

	
	
	
	
	
	
	
	
	for(int k=0; k<listCoutMP.size();k++)
	{
		boolean trouve=false;
		
		
		CoutMP coutMPTemp=listCoutMP.get(k);
		for(int d=0;d<lisDetailEstimation.size();d++)
		{
			
			DetailEstimation detailEstimationtemp=lisDetailEstimation.get(d);
			
			if(coutMPTemp.getMatierePremier().getId()==detailEstimationtemp.getMatierePremier().getId())
			{
				trouve=true;
			}
			
		}
		
		if(trouve==false)
		{
			coutMPTemp.setQuantConsomme(BigDecimal.ZERO);
			
			
		}
		
		
		
	}
	
	*/
	
//	remplirQuantiteOffreMP( listCoutMP);
	
	
/*	if(quantiteRealise>0){
		
			coutMP=listCoutMP.get(position);
			BigDecimal quantite=coutMP.getQuantConsomme()+quantiteRealise;
			coutMP.setQuantConsomme(quantite);
			listCoutMP.set(position, coutMP);
		
	}*/
		
	}






void calculerStockCoutProduitFini(BigDecimal coutTotal){
	
	
	
	transferStockPFDAO.ViderSession();
	
	BigDecimal coutPF=BigDecimal.ZERO;
	BigDecimal nouveauCout=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO ;
	BigDecimal coutStock=BigDecimal.ZERO;
	
	
	//coutTotal=production.getCoutTotalEmploye()+production.getCoutTotalEmployeGen()+production.getCoutTotalMP()+production.getCoutTotalEmployeEmbalage();
	
	coutPF=coutTotal.divide(production.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP);
	
	 StockPF stockPF = stockPFDAO.findStockByMagasinPF(production.getArticles().getId(),production.getMagasinPF().getId());
	 
	 quantiteTotal=stockPF.getStock().add(production.getQuantiteReel());
	 coutStock=stockPF.getStock().multiply(stockPF.getPrixUnitaire());
	 
	 	if(coutStock.compareTo(BigDecimal.ZERO) >0)
	 		nouveauCout=(coutTotal.add(coutStock)).divide(quantiteTotal, 6, BigDecimal.ROUND_HALF_UP) ;
	 	else 
	 		nouveauCout= coutPF;
	 	
	 	
	 stockPF.setArticles(production.getArticles());
	 stockPF.setPrixUnitaire(nouveauCout);
	 stockPF.setStock(quantiteTotal);
	 
	
	 DetailTransferProduitFini detailTransferProduitFini =new DetailTransferProduitFini();
 	 TransferStockPF transferStockPF =new TransferStockPF();
 	 List<DetailTransferProduitFini> listeDetailTransferProduitFini=new ArrayList<DetailTransferProduitFini>();
 	
 	detailTransferProduitFini.setArticle(production.getArticles());
 
 	detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
 	detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
 	detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
 	detailTransferProduitFini.setQuantite(production.getQuantiteReel());
 	detailTransferProduitFini.setPrixUnitaire(coutPF);
 	detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
 	detailTransferProduitFini.setTransferStockPF(transferStockPF);
 	
 	 listeDetailTransferProduitFini.add(detailTransferProduitFini);
 	 
 	 transferStockPF.setCodeTransfer(production.getNumOF());
 	 transferStockPF.setCreerPar(AuthentificationView.utilisateur);
 	 transferStockPF.setDate(new Date());
 	 transferStockPF.setDateTransfer(production.getDate_debFabPre());
 	 transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
 	 transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
 	 
 	transferStockPFDAO.add(transferStockPF);
	// detailTransferProduitFiniDAO.add(detailTransferProduitFini);
 	 
	 
	
	stockPFDAO.edit(stockPF);
	
}

/*BigDecimal determinerRemiseEmploye(Equipe equipe){
	BigDecimal remiseEmploye = BigDecimal.ZERO;
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	Articles article=production.getArticles();
	
	if(delaiTotal.compareTo(BigDecimal.ZERO) !=0){
		if(article.getConditionnement().compareTo(new BigDecimal(2))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_500G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(5))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_200G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(10)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_100G);
			quantiteTounage=parametre.getValeur();
		}
	
	if(production.getQuantiteReel().compareTo(quantiteTounage)>=0)
		remiseEmploye=equipe.getRemise().divide(delaiTotal, 6, BigDecimal.ROUND_HALF_UP) ;
	}
	
	return remiseEmploye;
	
}*/

/*BigDecimal determinerRemiseEmployeEmbalage(Equipe equipe){
	BigDecimal remiseEmploye = BigDecimal.ZERO;
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	Articles article=production.getArticles();
	if(delaiTotalEquipeEmbalage.compareTo(BigDecimal.ZERO)  !=0){
		if(article.getConditionnement() .compareTo(new BigDecimal(2)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_500G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(5)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_200G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(10))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_100G);
			quantiteTounage=parametre.getValeur();
		}
			
		if(production.getQuantiteReel().compareTo(quantiteTounage) >=0)
			remiseEmploye=equipe.getRemise().divide(delaiTotalEquipeEmbalage, 6, BigDecimal.ROUND_HALF_UP);
	}
	
	return remiseEmploye;
	
}*/

void calculRemiseResponsableProduction(Date dateProd, String periode){
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	BigDecimal coutResponsableProd=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;

    
    
	coutTotalAutreEmploye=BigDecimal.ZERO;

	 List<Production> listeProduction =productionDAO.listeProductionByDateByPeriode(dateProd,periode);
	 int taileListeProduction=listeProduction.size();
	 List<DetailResponsableProd> listeDetailResponsableProdTmp = new ArrayList<DetailResponsableProd>();
	
	 

	//	 if(production.getStatut().equals(ETAT_OF_TERMINER) ){
			 List<DetailProdRes> listeDetailResponsableProd =detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
			 
			 
			 for(int j=0;j<listeDetailResponsableProd.size();j++){

				 DetailProdRes detailResponsableProd=listeDetailResponsableProd.get(j);
				 
				 Employe employe=detailResponsableProd.getEmploye();
				 remise=BigDecimal.ZERO;				 				

				 if(!employe.isSalarie()){
					 
					 if(detailResponsableProd.isAbsent()==true){
				    		
				   		 String code=Utils.genereCodeDateMoisAnnee(production.getDate());
							 
				   		 Utils.compterAbsenceEmploye(code, detailResponsableProd.getEmploye(), production.getDate());
				   		}else if( detailResponsableProd.getDelaiEmploye().compareTo(production.getNbreHeure())  >=0 ){
							
							if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							{
								Parametre parametre=ParametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);   
								if(parametre!=null)
								{
									remise=  parametre.getValeur();
								}else
								{
									remise=  BigDecimal.ZERO;
								}
								
							}
								
							if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							{
								
								Parametre parametre=ParametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);   
								
								if(parametre!=null)
								{
									remise=  parametre.getValeur();
								}else
								{
									remise=  BigDecimal.ZERO;
								}
								
							}
							
				   			
				   		}
					 
					 detailResponsableProd.setCoutTotal(detailResponsableProd.getDelaiEmploye().multiply(heure.getValeur()));
					 detailResponsableProd.setCoutSupp25(detailResponsableProd.getHeureSupp25().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25)));
					 detailResponsableProd.setCoutSupp50(detailResponsableProd.getHeureSupp50().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50)));					
					 detailResponsableProd.setRemise(remise);					 
					 detailProdResDAO.edit(detailResponsableProd);
				 }
				 }
					 
			 ModifierCoutDetailProdresponsable( dateProd,  periode); 
					 
					 
					 
					/*
						
					//	coutTotalAutreEmploye=coutTotalAutreEmploye+detailResponsableProd.getCoutTotal()+detailResponsableProd.getCoutSupp25()+detailResponsableProd.getCoutSupp50();
						
						BigDecimal coutTotal=detailResponsableProd.getEmploye().getCoutHoraire().multiply(detailResponsableProd.getDelaiEmploye()) ;
						
							FicheEmploye ficheEmploye=ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
							
							if(ficheEmploye!=null){
								
								
							//	ficheEmploye.setCoutTotal(coutTotal);
								ficheEmploye.setNumOF(production.getNumOF());
								ficheEmploye.setDateSituation(production.getDate());
								ficheEmploye.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
								ficheEmploye.setHeureSupp25(detailResponsableProd.getHeureSupp25());
								ficheEmploye.setHeureSupp50(detailResponsableProd.getHeureSupp50());
							//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
							//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
								ficheEmploye.setRemise(remise);
								ficheEmploye.setEmploye(detailResponsableProd.getEmploye());
								
						
								ficheEmployeDAO.edit(ficheEmploye);
								}else {
									 ficheEmploye=new FicheEmploye();
									
								//	ficheEmploye.setCoutTotal(coutTotal);
									ficheEmploye.setNumOF(production.getNumOF());
									ficheEmploye.setDateSituation(production.getDate());
									ficheEmploye.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
									ficheEmploye.setHeureSupp25(detailResponsableProd.getHeureSupp25());
									ficheEmploye.setHeureSupp50(detailResponsableProd.getHeureSupp50());
								//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
								//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
									ficheEmploye.setRemise(remise);
									ficheEmploye.setEmploye(detailResponsableProd.getEmploye());
									
									ficheEmployeDAO.add(ficheEmploye);
									
								}
							*/
					//	listeDetailResponsableProdTmp.add(detailResponsableProd);
						
			/*#############################################
			  Metter à jour les délais des employés Génériques
			   ###########################################*/
			// majDelaiEmployeGenerique(listeProduction);
			 /*###########################################*/
				 
			// }
	
//	return listeDetailResponsableProdTmp;
}



public void ModifierCoutDetailProdresponsable(Date dateProd, String periode)
{
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	BigDecimal coutResponsableProd=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;
	 List<Production> listeProduction =productionDAO.listeProductionByDateByPeriode(dateProd,periode);
	 
	 List<DetailProdRes> listeDetailResponsableProd =detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");

	
	 ////////////////////////////////////////////////////////////////////////Modifier Cout DetailProdResponsable //////////////////////////////////////////////////////////////////////////////////////////////////
	
	 BigDecimal delaiEmploye=BigDecimal.ZERO;
	 BigDecimal coutHeure25=BigDecimal.ZERO;
	 BigDecimal delaiHeure25=BigDecimal.ZERO;
	 BigDecimal coutHeure50=BigDecimal.ZERO;
	 BigDecimal delaiHeure50=BigDecimal.ZERO;
	 
	 int numberproduction=1;
	  
	 
	 
	 for(int j=0;j<listeDetailResponsableProd.size();j++)
	 {
		 numberproduction=listeDetailResponsableProd.get(j).getNbrProduction();
	 }
	 if(numberproduction==0)
	 {
		 numberproduction=1; 
	 }
	 
	 
	 for(int i=0;i<listeProduction.size();i++){
			coutTotalEmployeGen=BigDecimal.ZERO;
				 Production production =listeProduction.get(i);
				 if(production.getStatut().equals(ETAT_OF_TERMINER) ){
					
					 
					 
					 for(int k=0;k<listeDetailResponsableProd.size();k++){

						 DetailProdRes detailResponsableProdTmp=listeDetailResponsableProd.get(k);
						 
						 if(!detailResponsableProdTmp.getEmploye().isSalarie()){
						 
						
						
							 
							// coutResponsableProd=ficheEmploye.getCoutTotal().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP) ;
							 delaiEmploye=detailResponsableProdTmp.getDelaiEmploye().divide(new BigDecimal(numberproduction) , 6, BigDecimal.ROUND_HALF_UP);
						//	 coutHeure25=ficheEmploye.getCoutSupp25().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
							 delaiHeure25=detailResponsableProdTmp.getHeureSupp25().divide(new BigDecimal(numberproduction) , 6, BigDecimal.ROUND_HALF_UP);
							// ficheEmploye.getCoutSupp50().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
							 delaiHeure50=detailResponsableProdTmp.getHeureSupp50().divide(new BigDecimal(numberproduction) , 6, BigDecimal.ROUND_HALF_UP);
							 remise=detailResponsableProdTmp.getRemise().divide(new BigDecimal(numberproduction) , 6, BigDecimal.ROUND_HALF_UP);
							 
							 coutResponsableProd=delaiEmploye.multiply(heure.getValeur());
							 coutHeure50=delaiHeure50.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50));
							 coutHeure25=delaiHeure25.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25));
							 
							 coutTotalEmployeGen=coutTotalEmployeGen.add(coutResponsableProd).add(coutHeure25).add(coutHeure50);
							 
							 
						
						 }
						 
					 }
					 
					 coutTotal=production.getCoutTotalMP().add(production.getCoutDechet()).add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage().add(production.getCoutTotalHorsProductionEnAttent())).add(coutTotalEmployeGen)  ;
					 
					
					 production.setCoutTotalEmployeGen(coutTotalEmployeGen);
					 production.setCoutTotal(coutTotal);
					 productionDAO.edit(production);
						 
					 }
			
				}
	 
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 

	
	
}







void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   tableEmploye.setModel(modeleEmploye); 
		   tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(6).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(7).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(8).setPreferredWidth(60);
		   tableEmploye.getTableHeader().setReorderingAllowed(false);
}


void initialiserTableOffreVariante(){
	 
		    
		   modeleTableOffreVariante =new DefaultTableModel(
				     	new Object[][] {
				     	},
				     	new String[] {
				     			"OffrePF", "Nombre Carton"
				     	}
				     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,true
		     	};
		    
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class
					};
		      	
			       public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     tableOffreVariante.setModel(new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     		"OffrePF", "Nombre Carton"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     		false, true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     });
		     tableOffreVariante.getColumnModel().getColumn(0).setPreferredWidth(94);
		     tableOffreVariante.getColumnModel().getColumn(1).setPreferredWidth(121);
				   
		     tableOffreVariante.getTableHeader().setReorderingAllowed(false);
	   
		   
		   
}


void initialiserTableauCoutHorsProductionEnAttent(){
	modeleCoutHorsProductionEnAttent =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     		"Code",	"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%" ,"Valider"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,false,false,false,false ,true 
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class ,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent); 
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(0).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(1).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(2).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(3).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(4).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(5).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(6).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(7).setPreferredWidth(60);
		    
		   TableDetailCoutHorsProductionEnAttent.getTableHeader().setReorderingAllowed(false);
}

void initialiserTableauEmployeGen(){
	modeleEquipeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   tableEmployeGen.setModel(modeleEquipeGen); 
		   tableEmployeGen.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmployeGen.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tableEmployeGen.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(6).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(7).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(8).setPreferredWidth(60);
		   tableEmployeGen.getTableHeader().setReorderingAllowed(false);
}

void initialiserTableauEquipeEmbalage(){
	
	modeleEquipeEm =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom","Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		     
		     
		     table_1.setModel(modeleEquipeEm); 
		     table_1.getColumnModel().getColumn(0).setPreferredWidth(1);
		     table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(2).setPreferredWidth(160);
		     table_1.getColumnModel().getColumn(3).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(5).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(6).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(7).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(8).setPreferredWidth(60);
		     table_1.getTableHeader().setReorderingAllowed(false);
}

/*List<DetailProdGen> remplieDetailProdGen(List<Employe> listEmploye){
	List<DetailProdGen> listDetailProdGen=new ArrayList<DetailProdGen>();
	for(int i=0;i<listEmploye.size();i++){
		DetailProdGen detailProdGen= new DetailProdGen();
		Employe employe =listEmploye.get(i);
		detailProdGen.setCoutTotal(BigDecimal.ZERO);
		detailProdGen.setRemise(employe.getRemise());
		detailProdGen.setEmploye(employe);
		detailProdGen.setProductionGen(production);
		
		listDetailProdGen.add(detailProdGen);
	}
	
	production.setListDetailProdGen(listDetailProdGen);
	productionDAO.edit(production);
	return listDetailProdGen;
  }*/

/*List<DetailProduction>  remplieDetailProdcution(List<Employe> listEmploye){
	List<DetailProduction> listDetailProdcution=new ArrayList<DetailProduction>();

	
	for(int i=0;i<listEmploye.size();i++){
		DetailProduction detailProd= new DetailProduction();
		Employe employe =listEmploye.get(i);
		detailProd.setCoutTotal(BigDecimal.ZERO);
		detailProd.setRemise(employe.getRemise());
		detailProd.setEmploye(employe);
		detailProd.setProduction(production);
		production.getDetailProductions().add(detailProd);
	}

	productionDAO.edit(production);
	
	return production.getDetailProductions();
  }*/


void  annulerStockMatierePremiere(List<CoutMP> listCoutMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteCharge=BigDecimal.ZERO;
	BigDecimal quantiteStockmp=BigDecimal.ZERO;
	BigDecimal quantiteARetournerCompteMP=BigDecimal.ZERO;
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutMP coutMP=listCoutMP.get(i);
	
		
		 quantiteCharge=coutMP.getQuantCharge();
		 BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
		 BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
		 BigDecimal quantiteExistante=coutMP.getQuantExistante();
		 BigDecimal quantiteDechet=coutMP.getQuantDechet();
		 BigDecimal quantiteDechetFour=coutMP.getQuantDechetFournisseur();
		 BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
		 BigDecimal quantiteOffre=coutMP.getQuantiteOffre();
		 BigDecimal quantiteReste=coutMP.getQuantReste();
		 
		StockMP stockMPProd=null;
		StockMP stockMPStockage=null;
		
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			if(coutMP.getFournisseurMP()!=null)
			{
				
				stockMPProd=stockMPDAO.findStockByMagasinMPByFournisseurMP (coutMP.getMatierePremier().getId(),idMagasinProd,coutMP.getFournisseurMP().getId() );		
				stockMPStockage=stockMPDAO.findStockByMagasinMPByFournisseurMP (coutMP.getMatierePremier().getId(),idMagasinStockage ,coutMP.getFournisseurMP().getId());
				
				
			}else
			{
				
				stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );		
				stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
			}
		}else
		{
			stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );		
			stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
		}
	
		
		
		
		
		quantiteStockmp=quantiteExistante.add(stockMPProd.getStock());
		
		quantiteStockage=stockMPStockage.getStock().add(quantiteCharge.add(quantitechargeSupp));
		 
		BigDecimal ecart=(quantiteCharge.add(quantitechargeSupp).add(quantiteExistante)).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste) );

			 Calendar cal = Calendar.getInstance();
		     cal.setTime(production.getDate());
		     int  annee = cal.get(Calendar.YEAR);
		     int mois = cal.get(Calendar.MONTH)+1;
		     
		
		CompteStockMP compteStockMP =compteStockMPDAO.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(),mois,annee);
		
		if(compteStockMP!=null){
			quantiteARetournerCompteMP=compteStockMP.getQuantite().subtract(ecart);
			compteStockMP.setQuantite(quantiteARetournerCompteMP);
			compteStockMPDAO.edit(compteStockMP);
		}
		
		stockMPProd.setStock(quantiteStockmp);
		stockMPStockage.setStock(quantiteStockage);
		
		
		/*coutMP.setCoutDechet(0);
		coutMP.setQuantCharge(0);
		coutMP.setQuantChargeSupp(0);
		coutMP.setQuantConsomme(0);
		coutMP.setQuantDechet(0);
		coutMP.setQuantExistante(0);
		coutMP.setQuantite(0);
		coutMP.setQuantReste(0);
		coutMP.setQuantDechetFournisseur(0);
		coutMP.setQuantiteManquante(0);
		cou*/
		listCoutMP.set(i, coutMP);
	//	listCoutMP.remove(i);

		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		
	}
	List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	
	TransferStockMP transferStockMPCharge=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
	
	if(transferStockMPCharge!=null)
	{
		/*
		
		listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPCharge.getId());
		
		for(int i=0;i<listDetailTransferStockMP.size();i++)
		{
			
		DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
			
		detailtransferMPDAO.delete(detailTransferStockMP.getId());
			
			
			
		}
		
		*/
		
		transferstockmpDAO.deleteObject(transferStockMPCharge);
		
		
	}
	
	listDetailTransferStockMP.clear();
	
TransferStockMP transferStockMPChargeSupp=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
	
	if(transferStockMPChargeSupp!=null)
	{
		
		/*
		listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPChargeSupp.getId());
		
		for(int i=0;i<listDetailTransferStockMP.size();i++)
		{
			
		DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
			
		detailtransferMPDAO.delete(detailTransferStockMP.getId());
			
			
			
		}
		*/
		
		transferstockmpDAO.deleteObject(transferStockMPChargeSupp);
		
		
		
	}
	
	
	listDetailTransferStockMP.clear();
	
	TransferStockMP transferStockMPDechetFournisseur=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET_FOURNISSEUR);
		
		if(transferStockMPDechetFournisseur!=null)
		{
			
			/*
			listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPDechetFournisseur.getId());
			
			for(int i=0;i<listDetailTransferStockMP.size();i++)
			{
				
			DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
				
			detailtransferMPDAO.delete(detailTransferStockMP.getId());
				
				
				
			}
			*/
			
			transferstockmpDAO.deleteObject(transferStockMPDechetFournisseur);
			
			
			
		}
		
		listDetailTransferStockMP.clear();
		
		TransferStockMP transferStockMPDechet=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET);
			
			if(transferStockMPDechet!=null)
			{
				
				/*
				listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPDechet.getId());
				
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					
				DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
					
				detailtransferMPDAO.delete(detailTransferStockMP.getId());
					
					
					
				}
				
				*/
				
				
				transferstockmpDAO.deleteObject(transferStockMPDechet);
				
			}
			
	
	
	
	
	List<ManqueDechetFournisseur> listManqueDechetFournisseur =new ArrayList<ManqueDechetFournisseur>();
	
	List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur =new ArrayList<DetailManqueDechetFournisseur>();
	
	listManqueDechetFournisseur=manqueDechetFournisseurDAO.listeManqueDechetFournisseurByCode(production.getNumOF());
	
	/*
	for(int j=0;j<listManqueDechetFournisseur.size();j++)
	{
		
		ManqueDechetFournisseur manqueDechetFournisseur=listManqueDechetFournisseur.get(j);
		
	listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
		
		for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
		{
			
		DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseur.get(i);
		
		detailManqueDechetFournisseurDAO.delete(detailManqueDechetFournisseur.getId());
			
			
		}
		
		
	
		
		
		
	}
	*/
	
	
	for(int j=0;j<listManqueDechetFournisseur.size();j++)
	{
		
		ManqueDechetFournisseur manqueDechetFournisseur=listManqueDechetFournisseur.get(j);
		
		manqueDechetFournisseurDAO.deleteObject(manqueDechetFournisseur);
		
		
		
	}
	
	
	
	
	
	
	//production.setListCoutMP(listCoutMP);
  }
void  annulerStockProduitFini(){
	BigDecimal quantiteAannuler=BigDecimal.ZERO;
	BigDecimal quantite=BigDecimal.ZERO;
	
			StockPF stockPF = stockPFDAO.findStockByMagasinPF(production.getArticles().getId(),production.getMagasinPF().getId());
			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(production.getNumOF());
			quantiteAannuler=production.getQuantiteReel();
			quantite=stockPF.getStock().subtract(quantiteAannuler);

			stockPF.setStock(quantite);

			transferStockPFDAO.deleteObject(transferStockPF);

			stockPFDAO.edit(stockPF);

}


void  annulerDetailCoutHorsproductionEnAttent(){


listCoutHorsProductionEnAttent= CoutHorsProdEnAttentDAO.findByProduction(production);

	for(int t=0;t<listCoutHorsProductionEnAttent.size();t++)
	{
		CoutHorsProdEnAttent CoutHorsProdEnAttent=listCoutHorsProductionEnAttent.get(t);
		
		 
		 
			 
			CoutHorsProdEnAttent.setEtat(COUT_HORS_PRODUCTION_EN_ATTENT);
			CoutHorsProdEnAttentDAO.edit(CoutHorsProdEnAttent);
			
			
		 
		
		
		 
	}
	

}




void deleteListeObject(List<FicheEmploye> listFicheEmploye){
	
	for(int i=0;i<listFicheEmploye.size();i++){
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}



void majDelaiEmployeGenerique(List<Production> listeProduction){
	
	// List<Production> listeProduction =productionDAO.listeProductionByDateByPeriode(dateProd,periode);
	 BigDecimal coutResponsableProd=BigDecimal.ZERO;
	 BigDecimal delaiEmploye=BigDecimal.ZERO;
	 BigDecimal coutHeure25=BigDecimal.ZERO;
	 BigDecimal delaiHeure25=BigDecimal.ZERO;
	 BigDecimal coutHeure50=BigDecimal.ZERO;
	 BigDecimal delaiHeure50=BigDecimal.ZERO;
	 BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	 BigDecimal remise=BigDecimal.ZERO;
	 BigDecimal coutTotal=BigDecimal.ZERO;
	 
	 int compteur=1;
for(int i=0;i<listeProduction.size();i++){
	
	coutTotalEmployeGen=BigDecimal.ZERO;
		 Production production =listeProduction.get(i);
		 if(production.getStatut().equals(ETAT_OF_TERMINER) ){
			 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
			 List<DetailResponsableProd> listeDetailResponsableProd =production.getListDetailResponsableProd();
			 
			 
			 for(int j=0;j<listeDetailResponsableProd.size();j++){

				 DetailResponsableProd detailResponsableProd=listeDetailResponsableProd.get(j);
				 
				 if(!detailResponsableProd.getEmploye().isSalarie()){
				 
				 Employe employe=detailResponsableProd.getEmploye();
				 FicheEmploye ficheEmploye=ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
				 
				 CompteurEmployeProd compteurEmployeProd=compteurEmployeProdDAO.findByDateProdPeriode(production.getDate(),production.getPeriode(), employe.getId());
				 
				 if(compteurEmployeProd!=null)
				  compteur=compteurEmployeProd.getCompteur();
				 else 
					 compteur=1;
				 if(ficheEmploye!=null){
					 
					// coutResponsableProd=ficheEmploye.getCoutTotal().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP) ;
					 delaiEmploye=ficheEmploye.getDelaiEmploye().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
				//	 coutHeure25=ficheEmploye.getCoutSupp25().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 delaiHeure25=ficheEmploye.getHeureSupp25().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					// ficheEmploye.getCoutSupp50().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 delaiHeure50=ficheEmploye.getHeureSupp50().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 remise=ficheEmploye.getRemise().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 
					 coutResponsableProd=delaiEmploye.multiply(heure.getValeur());
					 coutHeure50=delaiHeure50.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50));
					 coutHeure25=delaiHeure25.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25));
					 
					 detailResponsableProd.setCoutTotal(coutResponsableProd);
					 detailResponsableProd.setDelaiEmploye(delaiEmploye);
					 detailResponsableProd.setCoutSupp25(coutHeure25);
					 detailResponsableProd.setHeureSupp25(delaiHeure25);
					 detailResponsableProd.setHeureSupp50(delaiHeure50);
					 detailResponsableProd.setCoutSupp50(coutHeure50);
					 detailResponsableProd.setRemise(remise);
					 
					 coutTotalEmployeGen=coutTotalEmployeGen.add(coutResponsableProd).add(coutHeure25).add(coutHeure50);
				 }
				 
				
				 
				 listeDetailResponsableProd.set(j, detailResponsableProd);
				 }
				 
			 }
			 
			 coutTotal=production.getCoutTotalMP().add(production.getCoutDechet()).add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage()).add(coutTotalEmployeGen)  ;
			 
			 production.setListDetailResponsableProd(listeDetailResponsableProd);
			 production.setCoutTotalEmployeGen(coutTotalEmployeGen);
			 production.setCoutTotal(coutTotal);
			 productionDAO.edit(production);
				 
			 }
		
	
		}
	}




////////////////////////////////////////// Initialiser Table Fournisseur ////////////////////////////////////////////////////

void	intialiserTableFournisseur(){
  modeleFournisseur =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Fournisseur", "Fournisseur Dechet"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		     
	  		   TableFournisseur.setModel(modeleFournisseur); 
	  		//  table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jcombobox)); // ajouter ComboBox Code Fournisseur au Jtable
	  		 TableFournisseur.getColumnModel().getColumn(0).setPreferredWidth(30);
	  		TableFournisseur.getColumnModel().getColumn(1).setPreferredWidth(160);
	  		TableFournisseur.getTableHeader().setReorderingAllowed(false); 
	}



void afficher_tableFournisseur(List<FournisseurMP> listFournisseurMP)
{
	
	position=table.getSelectedRow();
	modeleFournisseur =new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Fournisseur","Fournisseur Dechet"
			}
		) {
			boolean[] columnEditables = new boolean[] {
					false,true
			};
			
			Class[] columnTypes = new Class[] {
					String.class,Boolean.class
				};
			  	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	TableFournisseur.setModel(modeleFournisseur);
	TableFournisseur.getTableHeader().setReorderingAllowed(false); 
	int i=0;
	
	 
	while(i<listFournisseurMP.size())
	{	
		
		 FournisseurMP fournisseurMP= listFournisseurMP.get(i);
	

		
		Object []ligne={fournisseurMP.getCodeFournisseur(), false};

		modeleFournisseur.addRow(ligne);
		i++;
	}
}


void remplirQuantiteOffreMP(List<CoutMP> listCoutMP){
	 
	 
	 
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
	 	

		
	if(production.getOffre()!=null){
		 promotion=PromotionDAO.findByCode(production.getOffre());
		
		 for(int j=0;j<listCoutMP.size();j++){
			  coutMP=listCoutMP.get(j); 
			 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
				 quantiteCarton= coutMP.getQuantConsomme();
			 } 
			 if( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
				 quantiteTheTotal= quantiteTheTotal.add(coutMP.getQuantConsomme());
			
			 } 
			 if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL))
				  quantiteFilmNormal=coutMP.getQuantConsomme();
			  if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD))
				  quantiteFilmGold=coutMP.getQuantConsomme();
			 
		 }
		
		 
		  List<DetailEstimationPromo> listeDetailEstimationPromo=promotion.getDetailEstimationPromo();
		  
		  
		  for(int i=0;i<listeDetailEstimationPromo.size();i++){
			  DetailEstimationPromo detailEstimationPromo=listeDetailEstimationPromo.get(i);
			  trouve=false;
			  find=false;
			  quantiteCalule=quantiteCarton.multiply(detailEstimationPromo.getQuantite());
			  
			  
			  if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  quantiteThe=quantiteCalule;
				  
			  for(int j=0;j<listCoutMP.size();j++){
					 
					  coutMP=listCoutMP.get(j);
					  
					  if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)){
						 // quantiteBox=quantiteCalule;
						  findBox=true;
						 
						  
						 }
					 
					 
					 if(trouve==false && detailEstimationPromo.getMatierePremiere().getCode().equals(coutMP.getMatierePremier().getCode())){
						 
						 
						 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						 {
							 
							 if(detailEstimationPromo.getFournisseur().getId()==coutMP.getFournisseurMP().getId())
							 {
								 coutMP.setQuantiteOffre(quantiteCarton.multiply(detailEstimationPromo.getQuantite()));
							 }else
							 {
								 trouve=false;
							 }
							 
						 }else
						 {
							 coutMP.setQuantiteOffre(quantiteCalule);
							 System.out.println(detailEstimationPromo.getMatierePremiere().getNom() +" Avant offre : "+coutMP.getQuantConsomme());
							 
							 quantiteCalule=quantiteCalule.add(coutMP.getQuantConsomme()); 
							 
						 }
						 
						
						
						
						 /*
						 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
							|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						 {
							 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
						 }
							 
						
						 coutMP.setQuantConsomme(quantiteCalule);
						
						 listCoutMP.set(j, coutMP);
						 */
						 
						 if( detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								 {
									 quantiteAcharge= quantiteAcharge.setScale(0, RoundingMode.CEILING); 
									 coutMP.setQuantConsomme(quantiteCalule);
										
									 listCoutMP.set(j, coutMP);
								 }
									 
								
								
						 
						 
						 
						 
						 trouve=true;
						 find=true;
						 System.out.println(detailEstimationPromo.getMatierePremiere().getNom() +" Apres offre : "+coutMP.getQuantConsomme());
						 
						 if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						 {
							 
							 if(detailEstimationPromo.getFournisseur().getId()==coutMP.getFournisseurMP().getId())
							 {
								 
							 }else
							 {
								 trouve=false;
							 }
							 
						 }
						 
						 
						 
					 } 
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
					 
					  /*
					  quantiteCalule=(quantiteFilmNormal.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP)).multiply(quantiteThe) ;
					  
					
					  
					  quantiteCalule=quantiteCalule.add(coutMP.getQuantConsomme()); 
						
						 coutMP.setQuantConsomme(quantiteCalule);
						
						 listCoutMP.set(j, coutMP);
						  */
						 trouve=true;
						
				  }else  if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD)){
					 /*
					  quantiteCalule=(quantiteFilmGold.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP)).multiply(quantiteThe);
					 
					  
					  quantiteCalule=quantiteCalule.add(coutMP.getQuantConsomme()); 
						
						
						 coutMP.setQuantConsomme(quantiteCalule);
						
						 listCoutMP.set(j, coutMP);
						  */
						 find=true;
						
				  } else if(trouve==true && find==true)
					  break;
			  }
			  
		  }
	 }
  
}

public void ViderEmployeProduction()
{
	txtcodeemployeproduction.setText("");
	comboemployeproduction.setSelectedItem("");
	txtdelaiproduction.setText("");
	txthsupp25production.setText("");
	txthsupp50production.setText("");
	checkabsentproduction.setSelected(false);
	checksortieproduction.setSelected(false);
	checkretardproduction.setSelected(false);
	txtcodeemployeproduction.requestFocus();
}

public void ViderEmployeGenerique()
{
	
	txtcodeemployegenerique.setText("");
	comboemployegenerique.setSelectedItem("");
	txtdelaigenerique.setText("");
	txthsupp25generique.setText("");
	txthsupp50generique.setText("");
	checkabsentgenerique.setSelected(false);
	checksortiegenerique.setSelected(false);
	checkretardgenerique.setSelected(false);
	txtcodeemployegenerique.requestFocus();
	
}

public void ViderEmployeEmballage()
{
	txtcodeemployeemballage.setText("");
	comboemployeemballage.setSelectedItem("");
	txtdelaiemballage.setText("");
	txthsupp25emballage.setText("");
	txthsupp50emballage.setText("");
	checkabsentemballage.setSelected(false);
	checksortieemballage.setSelected(false);
	checkretardemballage.setSelected(false);
	txtcodeemployeemballage.requestFocus();
}


////////////////////////////////////////////////////////////////////////////////////////////////////////    CALCULER STOCK FINALE             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

			
////////////////////////////////////////////////////////////////////////////////////////////////////Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	   
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


////////////////////////////////////////////////////////////////////////////////////////////////////////Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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



////////////////////////////////////////////////////////////////////////////////////////////////////////Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
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



boolean remplirMapNombreCartonPourChaqueConditionOffre(){
	boolean trouve=true;
 
	int j=0;	
	 
	for( j=0;j<tableOffreVariante.getRowCount();j++){
		
		if(!tableOffreVariante.getValueAt(j, 1).toString().equals("")  ){
			
			mapNombreCartonPourOffreVariante.put(tableOffreVariante.getValueAt(j, 0).toString(), new BigDecimal(tableOffreVariante.getValueAt(j, 1).toString()));
			 
		}else
		{
			
			trouve=false;
			
		}
	}
	
	 
	return trouve;
}





void InitialiserPlusMoins()
{
	txtQuantitePlus.setText("");
		txtQuantitePlus.setEditable(false);
		txtQuantiteMoins.setText("");
		txtQuantiteMoins.setEditable(false);
		group.clearSelection();
}



public boolean EcarValider()
{
	
	boolean valider=true;
	
	
	  int i=0;
	 
	  


	  
	 
	  
	
		while(i<production.getListCoutMP().size())
		{	
			CoutMP coutMP=production.getListCoutMP().get(i);
		
			BigDecimal quantiteTotal=coutMP.getQuantite();
			BigDecimal quantiteExistante=coutMP.getQuantExistante();
			BigDecimal quantiteCharge=coutMP.getQuantCharge();
			BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
			BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
			BigDecimal quantiteDechet=coutMP.getQuantDechet();
			BigDecimal quantiteDechetFour=coutMP.getQuantDechetFournisseur();
			BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
			BigDecimal quantiteOffre=coutMP.getQuantiteOffre();
			BigDecimal quantiteReste=coutMP.getQuantReste();
			BigDecimal quantiteManquanteFrPlus=coutMP.getQuantiteManquanteFrPlus();
			 
	 
			
			BigDecimal ecart=BigDecimal.ZERO;
			BigDecimal ecartConsomme=BigDecimal.ZERO;
			
		
			
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract((quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);
			}else if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

			}
			
			else
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(coutMP.getQuantiteManquante()).add(quantiteOffre).add(quantiteReste)).add(coutMP.getQuantiteManquanteFrPlus());

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

			}
			
			
			
			
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				{
					ecartConsomme=coutMP.getQuantite().subtract(coutMP.getQuantConsomme());
					 if(ecartConsomme.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO)!=0)
					 {
						 valider=false;
					 }
						 
				}
				
				 if(ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO)!=0)
				 {
					 valider=false;
				 } 
				 
				if(OffrePFMixte==true)
				{
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					{
						if(!coutMP.getMatierePremier().getNom().contains("BOX") && !coutMP.getMatierePremier().getCode().equals(Constantes.CODE_MP_THERRES) && !coutMP.getMatierePremier().getNom().contains(Constantes.MP_CONTIENT_VERRE))
							{
							QuantiteTotalOffreMixtPFConsomme=QuantiteTotalOffreMixtPFConsomme.add(coutMP.getQuantConsomme());	
							}
						
						
						
					}
					
					
				}
				 
				 
				 
			
			
			
			i++;
		}
	
	
	
	
	
	return valider;
}
}

package FacturePF;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;

import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;

import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;

import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;

import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;

import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
import dao.entity.CompteCaissier;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;

import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailFraisDepot;

import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureVenteMP;
import dao.entity.FournisseurMP;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;

import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;



import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class SituationCaisse extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();

	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<FactureVenteMP> listFactureVenteMP =new ArrayList<FactureVenteMP>();
	private List<DetailFactureVenteMP> listDetailFactureVenteMP =new ArrayList<DetailFactureVenteMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	private List<CompteCaissier> listCompteCaissier =new ArrayList<CompteCaissier>();
	private Map< String, Articles> mapArticleOffre = new HashMap<>();
	private Map< String, Articles> mapArticleOffreCode = new HashMap<>();
	private List<Articles> listArticleOffre =new ArrayList<Articles>();

	private static SequenceurDAO sequenceurDAO= new SequenceurDAOImpl();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Articles> mapArticlePromo = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Client> mapFournisseur= new HashMap<>();

	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private JCheckBox checkttc = new JCheckBox("TTC");
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private   JComboBox comboparclient = new JComboBox();
	private JComboBox comboClientpf;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;

	
private DetailTransferMPDAO detailTransferMPDAO;
private DetailFactureVenteMPDAO detailFactureVenteMPDAO;
private ClientDAO fournisseurdao;

TransferStockPF transferStock ;
private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
private	List<DetailFactureVenteMP> listMatierePremiereFacture= new ArrayList<DetailFactureVenteMP>();
private TransferStockMPDAO transferStockMPDAO;
private List <Object[]> listeObject=new ArrayList<Object[]>();
	ChargeProduction chargeproduction;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	
	private DepotDAO depotdao;
	private ParametreDAO parametredao;

	private ParametreDAO parametreDAO;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser datefin;
	private  JComboBox combopardepot;
	private StockPFDAO stockpfDAO;
	JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	 JLabel labelPrixMin = new JLabel("");
	 JLabel labelPrixMax = new JLabel("");
	  JLabel stockDisponible = new JLabel("");
	  JLabel labelMarge = new JLabel("");
	  JButton supprimer_facture = new JButton();
	  JLabel lblPrix = new JLabel("Prix       :");
	  JLabel lblMontant = new JLabel("Montant  :");
	  JLabel lblRemise = new JLabel("Remise :");
	  JLabel labelpourcentage = new JLabel("%");
	  BigDecimal prixTTC=BigDecimal.ZERO;
	  JDateChooser datefacture = new JDateChooser();
	  JDateChooser datedebut = new JDateChooser();
	  BigDecimal StockFinale=BigDecimal.ZERO;
	  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
	  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
	  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
	  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
	  JLabel lblOffreTherre = new JLabel("Offre Therre :");
	  JComboBox comboBoxtherres = new JComboBox();
	  JLabel lblOffreVerres = new JLabel("Offre Verres :");
	  JComboBox comboBoxverres = new JComboBox();
	  JLabel stockdisponibleoffretherres = new JLabel("");
	  JLabel stockdisponibleoffreverres = new JLabel("");
	  JLabel lblOffrePromo = new JLabel("Offre Promo :");
	  JComboBox comboBoxPromo = new JComboBox();
	  JLabel stockdisponiblearticlepromo = new JLabel("");
	  
	  List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
		List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
		private SubCategorieMPDAO subcategoriempdao;
		private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
		private Map< String, CategorieMp> catMap = new HashMap<>();
		private CategorieMpDAO categoriempdao;
	  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
	  JComboBox soucategoriempcombo = new JComboBox();
	  private JFrame mainFrame;
	  private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	  private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	  private FournisseurMPDAO fournisseurMPDAO;
	  JComboBox combofournisseur = new JComboBox();
	  JComboBox comboMP = new JComboBox();
	  private ClientDAO clientDAO;
		private Map< String, Client> mapClient= new HashMap<>();
		FactureVenteMPDAO factureVenteMPDAO;
		private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
		 JComboBox categoriempcombo = new JComboBox();
			private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
			private MatierePremiereDAO matierePremiereDAO;
			private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
			private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
			JCheckBox checkBoxSansTVA = new JCheckBox("Sans TVA");
			 JComboBox comboReglement = new JComboBox();
			 private JTextField txttotaldebit;
			 private JTextField txttotalcredit;
			 private JTextField txtsolde;
			
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SituationCaisse() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));
         
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1895, 1062);
      
        try{ 
        	
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             detailTransferMPDAO=new DetailTransferMPDAOImpl();
             detailFactureVenteMPDAO=new DetailFactureVenteMPDAOImpl();
             transferStockMPDAO=new TransferStockMPDAOImpl();
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	
         	fournisseurdao=new ClientDAOImpl();
         
         	
        	parametreDAO=new ParametreDAOImpl();
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl() ;
        	listsubcategoriemp=subcategoriempdao.findAll();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	clientDAO=new ClientDAOImpl();
        	factureVenteMPDAO=new FactureVenteMPDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		
		JLabel lblConslterLesFactures = new JLabel("                        SITUATION CAISSE ");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 935, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1458, 291);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					 "Designation", "Montant Débit","Montant Crédit"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
		
		
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	ChargerFactures();
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1585, 51);
	    add(layeredPane_2);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(921, 13, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(230, 13, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     datefin = new JDateChooser();
	     datefin.setBounds(274, 14, 151, 26);
	     layeredPane_2.add(datefin);
	     datefin.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     datefin.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     datefin.setLocale(Locale.FRANCE);
	     datefin.setDateFormatString("dd/MM/yyyy");
	     
	     combopardepot = new JComboBox();
	     combopardepot.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		
   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
   	 			int i=0;
   	 		
   	 				if(!combopardepot.getSelectedItem().equals(""))
    			{
    				Depot depot=mapDepot.get(combopardepot.getSelectedItem());
    				if(depot!=null)
    				{
    					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
		     				if(listMagasin.size()!=0)
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
 		     				{
 		     					Magasin magasin=listMagasin.get(i);
 		     					combomagasin.addItem(magasin.getLibelle());
 		     					mapMagasin.put(magasin.getLibelle(), magasin);
 		     					i++;
 		     				}
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     			
		     			
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
    					
    				}
    				
    				
   	 		 }
   	 	
	
	     		
	     		
	     	}
	     });
	     combopardepot.setBounds(497, 14, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(435, 13, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     int l=0;
	     
	      comboClientpf = new JComboBox();
	     comboClientpf.setSelectedIndex(-1);
	     comboClientpf.setBounds(972, 14, 238, 24);
	     layeredPane_2.add(comboClientpf);
	     
	     AutoCompleteDecorator.decorate(comboClientpf);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		
	     		comboparclient.setSelectedItem("");
	     		datefin.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     	comboClientpf.setSelectedItem("");
	     	combomagasin.setSelectedItem("");
	     	
	     		
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
	     add(button);
	   


	  


	    comboClientpf.addItem("");
	    
	     datedebut = new JDateChooser();
	    datedebut.setLocale(Locale.FRANCE);
	    datedebut.setDateFormatString("dd/MM/yyyy");
	    datedebut.setBounds(69, 11, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(25, 13, 34, 24);
	    layeredPane_2.add(lblDu);
	    
	    JLabel label_10 = new JLabel("Magasin :");
	    label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_10.setBounds(669, 12, 56, 24);
	    layeredPane_2.add(label_10);
	    
	    combomagasin = new JComboBox();
	    combomagasin.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
		  		
		  		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
		  			
		  			 if(combomagasin.getSelectedIndex()!=-1)
		  			 {
		  				 if(!combomagasin.getSelectedItem().equals(""))
		  				 {
		  					comboClientpf.removeAllItems();
		  					 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  				List<Client>  Listeclient=clientDAO.findListClientByCodeDepot(magasin.getDepot().getCode());
			  				comboClientpf.addItem(""); 
			  				for(int i=0;i<Listeclient.size();i++)
			  				{
			  					
			  					Client client=Listeclient.get(i);
			  					mapClient.put(client.getNom(), client);
				  				comboClientpf.addItem(client.getNom()); 
			  					
			  				}
			  				
		  				 }
		  				
		  				 
		  			 }
		  			 
		  			 
   	 		 }
		  		
		  		
		  		
		  		
		  	}
	    });
	    combomagasin.setSelectedIndex(-1);
	    combomagasin.setBounds(723, 13, 183, 24);
	    layeredPane_2.add(combomagasin);
	    
	    JLabel label = new JLabel("Mode R\u00E9glement :");
	    label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label.setBounds(1220, 13, 114, 24);
	    layeredPane_2.add(label);
	    
	     comboReglement = new JComboBox();
	    comboReglement.setSelectedIndex(-1);
	    comboReglement.setBounds(1337, 14, 216, 24);
	    layeredPane_2.add(comboReglement);
	    
	     

	     labelPrixMin = new JLabel("");
	    labelPrixMin.setForeground(new Color(50, 205, 50));
	    labelPrixMin.setFont(new Font("Tahoma", Font.BOLD, 13));
	    labelPrixMin.setBounds(1478, 533, 193, 30);
	    add(labelPrixMin);
	    
	     labelPrixMax = new JLabel("");
	    labelPrixMax.setForeground(Color.RED);
	    labelPrixMax.setFont(new Font("Tahoma", Font.BOLD, 13));
	    labelPrixMax.setBounds(1681, 533, 193, 30);
	    add(labelPrixMax);
	    
	     stockDisponible = new JLabel("");
	    stockDisponible.setForeground(Color.RED);
	    stockDisponible.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockDisponible.setBounds(10, 973, 1084, 30);
	    add(stockDisponible);
	    
	     labelMarge = new JLabel("");
	    labelMarge.setForeground(Color.RED);
	    labelMarge.setFont(new Font("Tahoma", Font.BOLD, 14));
	    labelMarge.setBounds(10, 1014, 708, 30);
	    add(labelMarge);
	     stockdisponibleoffretherres = new JLabel("");
	    stockdisponibleoffretherres.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponibleoffretherres.setForeground(Color.RED);
	    stockdisponibleoffretherres.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockdisponibleoffretherres.setBounds(10, 910, 600, 24);
	    add(stockdisponibleoffretherres);
	    
	     stockdisponibleoffreverres = new JLabel("");
	    stockdisponibleoffreverres.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponibleoffreverres.setForeground(Color.RED);
	    stockdisponibleoffreverres.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockdisponibleoffreverres.setBounds(10, 942, 600, 30);
	    add(stockdisponibleoffreverres);
	    
	     stockdisponiblearticlepromo = new JLabel("");
	    stockdisponiblearticlepromo.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponiblearticlepromo.setForeground(Color.RED);
	    stockdisponiblearticlepromo.setFont(new Font("Tahoma", Font.BOLD, 12));
	    stockdisponiblearticlepromo.setBounds(1395, 585, 490, 30);
	    add(stockdisponiblearticlepromo);


		
		
if (AuthentificationView.utilisateur.getLogin().equals("admin")) {

listDepot =depotdao.findAll();
combopardepot.removeAllItems();
combopardepot.addItem("");

for(int d=0;d<listDepot.size();d++)
{

Depot depot=listDepot.get(d);
combopardepot.addItem(depot.getLibelle());
mapDepot.put(depot.getLibelle(), depot);




}



} else {
Depot depot = depotdao.findByCode(AuthentificationView.utilisateur.getCodeDepot());

if (depot != null) {
	combopardepot.removeAllItems();
	combopardepot.addItem("");
	combopardepot.addItem(depot.getLibelle());
mapDepot.put(depot.getLibelle(), depot);


}
}	
		
int i=0;
while(i<listsubcategoriemp.size())
{
subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
i++;
}	

listFournisseur=fournisseurMPDAO.findAll();
for(int j=0;j<listFournisseur.size();j++)
{
	FournisseurMP fournisseurMP=listFournisseur.get(j);
	combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
	mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
	
}


listMatierePremiereFacture=detailFactureVenteMPDAO.listeDetailFactureMPGroupByMP();

for(int d=0;d<listMatierePremiereFacture.size();d++)
{
	

	MatierePremier mp=listMatierePremiereFacture.get(d).getMatierePremier();
	mapMatierePremiere.put(mp.getNom(), mp);
	
	
	
	
}


comboReglement.addItem("");	
comboReglement.addItem(Constantes.MODE_REGLEMENT_ESPECE);
comboReglement.addItem(Constantes.MODE_REGLEMENT_CHEQUE);
comboReglement.addItem(Constantes.MODE_REGLEMENT_TRAITE);
comboReglement.addItem(Constantes.MODE_REGLEMENT_VERSEMENT);
comboReglement.addItem(Constantes.MODE_REGLEMENT_VIREMENT);
JLabel lblMontantTotal = new JLabel("Montant Total :");
lblMontantTotal.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
lblMontantTotal.setBounds(685, 478, 127, 24);
add(lblMontantTotal);
txttotaldebit = new JTextField();
txttotaldebit.setEditable(false);
txttotaldebit.setColumns(10);
txttotaldebit.setBounds(826, 481, 145, 26);
add(txttotaldebit);
txttotalcredit = new JTextField();
txttotalcredit.setEditable(false);
txttotalcredit.setColumns(10);
txttotalcredit.setBounds(995, 481, 145, 26);
add(txttotalcredit);
JLabel lblSolde = new JLabel("Solde :");
lblSolde.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
lblSolde.setBounds(685, 539, 127, 24);
add(lblSolde);
txtsolde = new JTextField();
txtsolde.setEditable(false);
txtsolde.setColumns(10);
txtsolde.setBounds(903, 537, 145, 26);
add(txtsolde);
JButton btnImprimer_1 = new JButton("Imprimer");
btnImprimer_1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		
	
			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		if(magasin!=null)
		{
			 if(listCompteCaissier.size()!=0)
			 {
			
					Map parameters = new HashMap();
					String datedu=((JTextField)datedebut.getDateEditor().getUiComponent()).getText();
					String dateau=((JTextField)datefin.getDateEditor().getUiComponent()).getText();
					parameters.put("magasin",magasin.getLibelle() );
					if(!datedu.equals("") && !dateau.equals(""))
					{
						
						parameters.put("date","  Du :"+datedu +" Au : "+dateau);	
					}else if(!datedu.equals("") && dateau.equals(""))
					{
						parameters.put("date","  Du :"+datedu +" Au : "+datedu);
					}else if(datedu.equals("") && !dateau.equals(""))
					{
						parameters.put("date","  Du :"+dateau +" Au : "+dateau);
					}
				
					
					JasperUtils.imprimerCompteCaissier(listCompteCaissier,parameters);
					
			 } else
			 {
				 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Compte Caissier  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 return;
			 }
		}
			
		
		
		
			
			
			
			
			
			
			
		
		
		
		
		
	}
});
btnImprimer_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
btnImprimer_1.setBounds(356, 516, 107, 24);
add(btnImprimer_1);
		
		}
	

boolean remplirmaptransfereblfacture(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 8);
		if(regle==true ){
			
			maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 8).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
	void initialiserPourFacture()
	{

	
	}
	
	

	

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant HT","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
}
	
	
	

	
	
	

	
	
	
	
	void afficher_tableFacturePF(List<FactureVenteMP> listFacture)
	{
		
		listCompteCaissier.clear();
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Designation", "Montant Débit","Montant Crédit"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,BigDecimal.class,BigDecimal.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		int i=0;
		BigDecimal MontantTotal=BigDecimal.ZERO;
		 
		while(i<listFacture.size())
		{	
			
			FactureVenteMP factureVenteMP=listFacture.get(i);
			
			MontantTotal=MontantTotal.add(factureVenteMP.getMontantTTC());
			
			
			
			
				Object []ligne={factureVenteMP.getDateFacture() +" ; BL N° : "+factureVenteMP.getNumFacture() +" ; Client : "+factureVenteMP.getClient().getNom()  ,NumberFormat.getNumberInstance(Locale.FRANCE).format(factureVenteMP.getMontantTTC()),"-" };

			modelefacture.addRow(ligne);
			
			
			
			Object []ligneTmp={ factureVenteMP.getModeReglement() +" Num : "+factureVenteMP.getNumPiece()+" Sur BL N° : "+factureVenteMP.getNumFacture() +" ; Client : "+factureVenteMP.getClient().getNom()  ,"-",NumberFormat.getNumberInstance(Locale.FRANCE).format(factureVenteMP.getMontantTTC()) };

			modelefacture.addRow(ligneTmp);
			
			
			
			////////////////////////////////////////////////////////////////////////////////////  Debit ////////////////////////////////////////////////////////////////////////////////////////////

			CompteCaissier compteCaissier=new CompteCaissier();
			
			compteCaissier.setDesignation(factureVenteMP.getDateFacture() +" ; BL N° : "+factureVenteMP.getNumFacture() +" ; Client : "+factureVenteMP.getClient().getNom() );
			compteCaissier.setDebit(factureVenteMP.getMontantTTC());
			compteCaissier.setCredit(BigDecimal.ZERO);
			listCompteCaissier.add(compteCaissier);
			
			////////////////////////////////////////////////////////////////////////////////////  Credit ////////////////////////////////////////////////////////////////////////////////////////////
			
CompteCaissier compteCaissierTmp=new CompteCaissier();
			
compteCaissierTmp.setDesignation(factureVenteMP.getModeReglement() +" Num : "+factureVenteMP.getNumPiece()+" Sur BL N° : "+factureVenteMP.getNumFacture() +" ; Client : "+factureVenteMP.getClient().getNom() );
compteCaissierTmp.setDebit(BigDecimal.ZERO);
compteCaissierTmp.setCredit(factureVenteMP.getMontantTTC());
listCompteCaissier.add(compteCaissierTmp);
			
			
			i++;
		}
		
		
		txttotaldebit.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTotal)+"");
		txttotalcredit.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTotal)+"");
		txtsolde.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTotal.subtract(MontantTotal))+"");
		
		
		
}
	
	void reglerTransferStockPF()
	{
		
		/* code pour regler le transfer stock produit fini pour que les articles de detail facture produits fini egal aux articles de detail transfer stock produit fini

		ClientPF clientpf=mapParClientPF.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		 boolean trouve=false;
		 List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
		 List<FacturePF> listFacturePFTmp =new ArrayList<FacturePF>();
		 List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
		
		 listFacturePFTmp= facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);
		 
		 for(int i=0 ; i<listFacturePFTmp.size() ; i++)
		 {
			 
			 FacturePF facturepf=listFacturePFTmp.get(i);
			 listDetailFacturePFTmp=facturepf.getDetailFacturePF();
			 TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturepf.getCodeTransfer());
			 listDetailTransferProduitFini=transferStockPF.getListDetailTransferProduitFini();
			 for(int j=0;j<listDetailFacturePFTmp.size();j++)
			 {
				trouve=false;
				 DetailFacturePF detailfacturepf=listDetailFacturePFTmp.get(j);
				
				 for(int k=0;k<listDetailTransferProduitFini.size();k++)
				 {
					if(detailfacturepf.getArticle().getId()== listDetailTransferProduitFini.get(k).getArticle().getId())
						
					{
						
						if( detailfacturepf.getQuantite().setScale(2, RoundingMode.HALF_UP).equals(listDetailTransferProduitFini.get(k).getQuantite().setScale(2,RoundingMode.HALF_UP)))
						{
							trouve=true;
						}
						
						}
					 
				 }
				 
				 if(trouve==false)
				 {
					DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
					detailTransferProduitFini.setArticle(detailfacturepf.getArticle());
					detailTransferProduitFini.setDateTransfer(transferStockPF.getDateTransfer());
					detailTransferProduitFini.setPrixUnitaire(detailfacturepf.getPrixUnitaire());
					detailTransferProduitFini.setQuantite(detailfacturepf.getQuantite());
					detailTransferProduitFini.setSousFamille(detailfacturepf.getSousFamille());
					detailTransferProduitFini.setTransferStockPF(transferStockPF);
					detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
					detailTransferProduitFini.setMagasinDestination(facturepf.getMagasin());
					detailTransferStockPFdao.add(detailTransferProduitFini);
				 }
				 
				 
			 }
			  
		 }
		 
		 JOptionPane.showMessageDialog(null, "La Modification Effectué avec succée");
		 
		 
		*/
		
	}
	
	
	public void ChargerFactures()
	{

factureVenteMPDAO.ViderSession();
    	
    	String requete="";
    	
    	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String DateDebut="";
    	String DateFin="";
    	
    	Depot depot=mapDepot.get(combopardepot.getSelectedItem());
    	Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
    	if(depot==null)
    	{
    		
    		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Depot SVP","Erreur",JOptionPane.ERROR_MESSAGE);
    		return;
    	}else if (magasin==null){
    		
    		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
    		return;
    	}else
    		
    	{
    		
    		
    		requete=requete+" magasin.id='"+magasin.getId()+"' ";	
    		
    	
    		
    	Client client=mapClient.get(comboClientpf.getSelectedItem())	;
    	if(client!=null)
    	{
    		
    		requete=requete+" and client.id='"+client.getId()+"' ";	
    		
    	}
    	
    	if(comboReglement.getSelectedIndex()!=-1)
    	{
    		if(!comboReglement.getSelectedItem().equals(""))
    		{
    			requete=requete+" and modeReglement='"+comboReglement.getSelectedItem()+"' ";	
    			
    			
    		}
    		
    		
    		
    		
    	} 
    		
    		if(datedebut.getDate()!=null && datefin.getDate()!=null)
    		{
    			
    			DateDebut=dt.format(datedebut.getDate());
    			DateFin=dt.format(datefin.getDate());
    			
    			requete=requete+" and dateFacture between '"+DateDebut+"' and  '"+DateFin+"' ";	
    			
    		}
    		
    		if(datedebut.getDate()!=null && datefin.getDate()==null)
    		{
    			DateDebut=dt.format(datedebut.getDate());
    			requete=requete+" and dateFacture ='"+DateDebut+"'";	
    			
    		}
    		
    		if(datedebut.getDate()==null && datefin.getDate()!=null)
    		{
    			DateFin=dt.format(datefin.getDate());
    			requete=requete+" and dateFacture ='"+DateFin+"'";	
    			
    		}
    		
    		requete=requete+" order by client.id";
    		
    		listFactureVenteMP=factureVenteMPDAO.findByRequete(requete);
    		
    		afficher_tableFacturePF(listFactureVenteMP);
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	
	}
	}





package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CheckableItem;
import util.ComparateurStockDechetMP;
import util.ComparateurStockMP;
import util.Constantes;
import util.JasperUtils;
import util.NumberUtils;
import util.Variables;

import com.toedter.calendar.JDateChooser;

import Production.AjouterEstimationOffre;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailMarchandiseDeplacer;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockDechetMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MarchandiseDeplacer;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConsulterEcartMarchandiseDeplacerEnAttente extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private static DefaultTableModel modeleEtatStock;
	private static DefaultTableModel modeleDetailEtatStock;

	private static JXTable tableEtatStock = new JXTable();

	private List<Depot> listDepotSource = new ArrayList<Depot>();
	
	private List<Depot> listparDepot = new ArrayList<Depot>();
	private List<Magasin> listMagasinSource = new ArrayList<Magasin>();
	private List<Magasin> listMagasinDestination = new ArrayList<Magasin>();
	

	// ******************************************* Listes Pour Mouvement de Stock Mp
	// **********************************************//

	private static List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTmp = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortieTmp = new ArrayList<DetailTransferStockMP>();
	private static List<MarchandiseDeplacer> listMarchandiseDeplacer = new ArrayList<MarchandiseDeplacer>();
	private static List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacer = new ArrayList<DetailMarchandiseDeplacer>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer = new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp = new ArrayList<DetailMouvementStock>();

	// *******************************************************************************************************************************//

	// ************************************************

	private List<DetailTransferStockMP> listDetailTransferStockMPReception = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPReceptionGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPCharge = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeGroupebyMP = new ArrayList<DetailTransferStockMP>();

	private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEnter = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEntrerGroupebyMP = new ArrayList<DetailTransferStockMP>();

	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP = new ArrayList<DetailTransferStockMP>();
	

	// ***************************************************

	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp = new ArrayList<DetailMouvementStock>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private List<MatierePremier> listMP = new ArrayList<MatierePremier>();

	private static Map<String, Depot> mapDepotSource = new HashMap<>();
	private static Map<String, Depot> mapDepotDestination = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private static Map<String, Magasin> mapMagasinSource = new HashMap<>();
	private static Map<String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;


	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private static DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;

	private JTextField txtlibelle = new JTextField();

	private DepotDAO depotdao;
	
	
	private JDateChooser dateChooser = new JDateChooser();

	JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;

	private MatierePremiereDAO MatierPremiereDAO;
	String titre = "";
	Workbook workbook = new HSSFWorkbook();
	private static JTextField txtCodTransfert;
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox comboMP = new JComboBox();
	
	private static JComboBox combomagasinSource= new JComboBox();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private CategorieMpDAO categoriempdao;
	static JDateChooser dateChooserdebut = new JDateChooser();
	static JDateChooser dateChooserfin = new JDateChooser();
	
	private static JComboBox combodepotSource= new JComboBox();
	private SubCategorieMPDAO subcategoriempdao;
	private MatierePremiereDAO matierePremiereDAO;
	JComboBox comboFournisseur = new JComboBox();
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	JButton ImprimerDetailEtatStockDechetMP = new JButton("Imprimer");
	static JComboBox combodepotDestination = new JComboBox();
	static JComboBox combomagasinDestination = new JComboBox();
	JFrame frame;
	static JComboBox comboetat = new JComboBox();
	private List<DetailTransferStockMP> listDetailTransfertMpTmp =new ArrayList<DetailTransferStockMP>();
	
	static JComboBox comboService = new JComboBox();
	static JComboBox comboAction = new JComboBox();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public ConsulterEcartMarchandiseDeplacerEnAttente() {
		 
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1485, 1029);

		try {

			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgModifierr = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgValider = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgChercher = new ImageIcon(this.getClass().getResource("/img/chercher.png"));
			imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
			utilisateur = AuthentificationView.utilisateur;
			depotdao =  new DepotDAOImpl();
			mouvementStockGlobaleDAO =  new MouvementStockGlobalDAOImpl();
			MatierPremiereDAO =  new MatierePremierDAOImpl();
			detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
			listMP = MatierPremiereDAO.findAll();
			categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	 fournisseurMPDAO=new FournisseurMPDAOImpl();
        	 transferStockMPDAO=new TransferStockMPDAOImpl();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		tableEtatStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
}
		});

		tableEtatStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {   "Code Transfert","Date Transfert","Depot Source","Magasin Source", "Date Transfert Entrer","Depot Destination","Magasin Destination","Code MP","MP","Fournisseur","Service","Type Action","Etat" }));
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		
		
		tableEtatStock.setShowVerticalLines(false);
		tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
		tableEtatStock.setRowHeightEnabled(true);
		tableEtatStock.setBackground(new Color(255, 255, 255));
		tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEtatStock.setColumnControlVisible(true);
		tableEtatStock.setForeground(Color.BLACK);
		tableEtatStock.setGridColor(new Color(0, 0, 255));
		tableEtatStock.setAutoCreateRowSorter(true);
		tableEtatStock.setBounds(2, 27, 411, 198);
		tableEtatStock.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(tableEtatStock);
		scrollPane.setBounds(10, 301, 1465, 481);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Marchandises Deplacer En Attente");
		titledSeparator.setBounds(10, 260, 1465, 30);
		add(titledSeparator);

		JLabel lblConslterLesFactures = new JLabel("          D\u00E9tail Consultation Des Ecarts MP D\u00E9placer");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(216, 11, 1143, 35);
		add(lblConslterLesFactures);
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		JButton btnAfficher = new JButton("Consulter");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Consulter();
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAfficher.setBounds(581, 225, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 57, 1383, 157);
		add(layeredPane);
		
		JLabel lblDu = new JLabel("Du  :");
		lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblDu.setBounds(10, 11, 67, 24);
		layeredPane.add(lblDu);
		
		JLabel lblCodeTransfert = new JLabel("Code Transfert :");
		lblCodeTransfert.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodeTransfert.setBounds(1072, 66, 111, 24);
		layeredPane.add(lblCodeTransfert);
		
		txtCodTransfert = new JTextField();
		txtCodTransfert.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					MatierePremier mp=MapCodeMatierPremiere.get(txtCodTransfert.getText().toUpperCase());
					if(mp!=null)
					{
						comboMP.setSelectedItem(mp.getNom());
					}else
					{
						JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
						return;
					}
					
	      		}
			
							}
		});
		txtCodTransfert.setText("");
		txtCodTransfert.setColumns(10);
		txtCodTransfert.setBounds(1182, 66, 191, 26);
		layeredPane.add(txtCodTransfert);
		
		 dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(48, 9, 163, 26);
		layeredPane.add(dateChooserdebut);
		JLabel lblMagasinSource = new JLabel("Magasin Source  :");
		lblMagasinSource.setFont(new Font("Verdana", Font.BOLD, 12));
		lblMagasinSource.setBounds(828, 11, 125, 26);
		layeredPane.add(lblMagasinSource);
		
		 combomagasinSource = new JComboBox();
		combomagasinSource.setBounds(963, 11, 263, 27);
		layeredPane.add(combomagasinSource);
		combomagasinSource.addItem("");
		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) util.DateUtils.getCurrentYear() + "-01-01");

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton button = new JButton("Initialiser");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				combodepotDestination.setSelectedItem("");
				combomagasinDestination.setSelectedItem("");
				combodepotSource.setSelectedItem("");
				combomagasinSource.setSelectedItem("");
				dateChooserdebut.setCalendar(null);
			dateChooserfin.setCalendar(null);
			txtCodTransfert.setText("");
			comboetat.setSelectedItem("");
			comboAction.setSelectedItem("");
			comboService.setSelectedItem("");

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(715, 225, 107, 24);
		add(button);
 		
 		JLabel lblAu = new JLabel("Au  :");
 		lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
 		lblAu.setBounds(227, 13, 67, 24);
 		layeredPane.add(lblAu);
 		
 		 dateChooserfin = new JDateChooser();
 		dateChooserfin.setLocale(Locale.FRANCE);
 		dateChooserfin.setDateFormatString("dd/MM/yyyy");
 		dateChooserfin.setBounds(265, 11, 163, 26);
 		layeredPane.add(dateChooserfin);
 		
 		JLabel lblDepot = new JLabel("Depot Source  :");
 		lblDepot.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepot.setBounds(457, 11, 117, 26);
 		layeredPane.add(lblDepot);
 		
 		 combodepotSource = new JComboBox();
 		 combodepotSource.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		
 		 		
 		 		if(combodepotSource.getSelectedItem()==null)
 		 		{
 		 			return;
 		 		}
 		 		
 		 		
 		 		if(!combodepotSource.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasinSource.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepotSource.get(combodepotSource.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasinSource = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasinSource.addItem("");
 						while (k < listMagasinSource.size()) {
 							Magasin magasin = listMagasinSource.get(k);

 							combomagasinSource.addItem(magasin.getLibelle());

 							mapMagasinSource.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasinSource.removeAllItems();
 		 			combomagasinSource.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 	}
 		 });
 		combodepotSource.setBounds(584, 11, 215, 27);
 		layeredPane.add(combodepotSource);
 		combodepotSource.addItem("");
 		
 		JLabel lblDepotDestination = new JLabel("Depot Destination  :");
 		lblDepotDestination.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepotDestination.setBounds(10, 66, 149, 26);
 		layeredPane.add(lblDepotDestination);
 		
 		 combodepotDestination = new JComboBox();
 		 combodepotDestination.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		if(combodepotDestination.getSelectedItem()==null)
 		 		{
 		 			return;
 		 		}

 		 		
 		 		if(!combodepotDestination.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasinDestination.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepotSource.get(combodepotDestination.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasinDestination = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasinDestination.addItem("");
 						while (k < listMagasinDestination.size()) {
 							Magasin magasin = listMagasinDestination.get(k);

 							combomagasinDestination.addItem(magasin.getLibelle());

 							mapMagasinDestination.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasinDestination.removeAllItems();
 		 			combomagasinDestination.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 	
 		 		
 		 		
 		 	}
 		 });
 		combodepotDestination.setBounds(147, 66, 202, 27);
 		layeredPane.add(combodepotDestination);
 		combodepotDestination.addItem("");
 		JLabel lblMagasinDestination = new JLabel("Magasin Destination  :");
 		lblMagasinDestination.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblMagasinDestination.setBounds(359, 63, 155, 26);
 		layeredPane.add(lblMagasinDestination);
 		
 		 combomagasinDestination = new JComboBox();
 		combomagasinDestination.setBounds(511, 63, 263, 27);
 		layeredPane.add(combomagasinDestination);
 		combomagasinDestination.addItem("");
  		  
  		  
		if (utilisateur.getLogin().equals("admin")) {
			
			listDepotSource =depotdao.findAll();
			
			combodepotSource.removeAllItems();
			combodepotSource.addItem("");
			combodepotDestination.removeAllItems();
			combodepotDestination.addItem("");
			
			for(int d=0;d<listDepotSource.size();d++)
			{
				
			Depot depot=listDepotSource.get(d);
			combodepotSource.addItem(depot.getLibelle());
			combodepotDestination.addItem(depot.getLibelle());
			mapDepotSource.put(depot.getLibelle(), depot);
			mapDepotDestination.put(depot.getLibelle(), depot);	
				
				
				
			}
			
	

		}
		
		
		
		
		combodepotDestination.setSelectedItem("");
		combomagasinDestination.setSelectedItem("");
		combodepotSource.setSelectedItem("");
		combomagasinSource.setSelectedItem("");
		
		JLabel lblEtat = new JLabel("Etat  :");
		lblEtat.setFont(new Font("Verdana", Font.BOLD, 12));
		lblEtat.setBounds(798, 65, 67, 26);
		layeredPane.add(lblEtat);
		
		 comboetat = new JComboBox();
		comboetat.setBounds(848, 66, 202, 27);
		layeredPane.add(comboetat);
		
		/*
		   frame.addWindowListener(new WindowAdapter() {

			   @Override
			   public void windowClosing(WindowEvent e) {
			       // handle closing the window
			         AjouterActionsMarchandiseDeplacerEnAttenteIsOpne = false;
			       frame.setVisible(false);
			       frame.dispose();
			   }
			   });
		
		*/
		
		comboetat.addItem("");
		comboetat.addItem(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
		comboetat.addItem(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
		comboetat.addItem(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
		comboetat.setSelectedItem("");
		
		JLabel lblService = new JLabel("Service  :");
		lblService.setFont(new Font("Verdana", Font.BOLD, 12));
		lblService.setBounds(10, 118, 67, 26);
		layeredPane.add(lblService);
		
		 comboService = new JComboBox();
		comboService.setBounds(92, 119, 202, 27);
		layeredPane.add(comboService);
		
		JLabel lblAction = new JLabel("Action  :");
		lblAction.setFont(new Font("Verdana", Font.BOLD, 12));
		lblAction.setBounds(315, 116, 67, 26);
		layeredPane.add(lblAction);
		
		 comboAction = new JComboBox();
		comboAction.setBounds(397, 117, 202, 27);
		layeredPane.add(comboAction);
		
		
		comboService.addItem("");
		comboService.addItem(Constantes.SERVICE_PRODUCTION);
		comboService.addItem(Constantes.SERVICE_PAIE);
		comboService.addItem(Constantes.SERVICE_FRAIS);
		
		comboService.setSelectedItem("");
		
		comboAction.addItem("");
		comboAction.addItem(Constantes.ACTION_QUANTITE_A_STOCKER);
		comboAction.addItem(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR);
		comboAction.addItem(Constantes.ACTION_ERREUR_MAGASINIER);
		comboAction.addItem(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR);
		comboAction.addItem(Constantes.ACTION_PERTE_DE_QUANTITE);
		comboAction.setSelectedItem("");
		
	}

	void InitialiseTableauDetailMouvementStock() {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Code","Matiere Premiere","Quantite Initial", "Quantite Reception","Transfert Entre", "Quantite Charger", "Quantite Charge Supp",
						 "Quantite Retour","Quantite Sortie", "Quantite Finale", }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
		tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);

	}

	/*
	 * void InitialiseTableauMouvementStock() { modeleMouvementStock =new
	 * DefaultTableModel( new Object[][] { }, new String[] { "Date Mouvement",
	 * "Depot", "Magasin" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false }; public boolean isCellEditable(int row, int column) {
	 * return columnEditables[column]; } }; table.setModel(modeleMouvementStock);
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * 
	 * 
	 * 
	 * }
	 */

	static void afficher_tableEtatStock(List<DetailMarchandiseDeplacer> listDetailMarchandise) {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {   "Code Transfert","Date Transfert","Depot Source","Magasin Source", "Date Transfert Entrer","Depot Destination","Magasin Destination","Code MP","MP","Fournisseur","Service","Type Action","Etat" }) {
			boolean[] columnEditables = new boolean[] {  false, false, false, false, false , false, false, false, false , false, false, false , false  };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		int i = 0;
		
		 

		while (i < listDetailMarchandise.size()) {
			
			DetailMarchandiseDeplacer detailmarchandiseDeplacer = listDetailMarchandise.get(i);
			
			String fournisseur="";
			String service="";
			if(detailmarchandiseDeplacer.getFournisseur()!=null)
			{
				fournisseur=detailmarchandiseDeplacer.getFournisseur().getCodeFournisseur();
				
			}
			
			if(detailmarchandiseDeplacer.getService()!=null)
			{
				service=detailmarchandiseDeplacer.getService();
				
			}
			
			

	       Object[] ligne = {detailmarchandiseDeplacer.getCodeTransfert(),detailmarchandiseDeplacer.getDateSortie(),detailmarchandiseDeplacer.getMagasinSouce().getDepot().getLibelle(),detailmarchandiseDeplacer.getMagasinSouce().getLibelle(),detailmarchandiseDeplacer.getDateEntrer(), detailmarchandiseDeplacer.getMagasinDestination().getDepot().getLibelle(),detailmarchandiseDeplacer.getMagasinDestination().getLibelle(),detailmarchandiseDeplacer.getMatierePremier().getCode(),detailmarchandiseDeplacer.getMatierePremier().getNom(),fournisseur,service,detailmarchandiseDeplacer.getAction(),  detailmarchandiseDeplacer.getEtat() };
	

	      modeleEtatStock.addRow(ligne);
	     
		

			i++;
		}
		
		
	 
		
		
		
		
	}
	
	
	
	
	
	
	
	public   void Consulter()
	{
		

		listDetailMarchandiseDeplacer.clear();
		
		
		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && combodepotSource.getSelectedItem().equals("") && combomagasinSource.getSelectedItem().equals("") && combodepotDestination.getSelectedItem().equals("") && combomagasinDestination.getSelectedItem().equals("") && txtCodTransfert.getText().equals("") && comboetat.getSelectedItem().equals("") && comboService.getSelectedItem().equals("") && comboAction.getSelectedItem().equals(""))
		{
			
			JOptionPane.showMessageDialog(null,"Veuillez Filtrer Par Un Ou Plusieurs Champs SVP");
			return;
		}else
		{
			listMarchandiseDeplacer.clear();
			
			String req="";
			
			if(comboetat.getSelectedItem().equals(""))
			{
				req=req+" where  d.transferStockMP.etat in ('"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"','"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER +"', '"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER+"') ";
			}else
			{
				req=req+" where  d.transferStockMP.etat='"+comboetat.getSelectedItem()+"' ";
				
			}
			
			if(!comboAction.getSelectedItem().equals(""))
			{
				
				req=req+" and d.action='"+comboAction.getSelectedItem().toString()+"' ";
				
			}
			
			if(!comboService.getSelectedItem().equals(""))
			{
				
				req=req+" and d.service='"+comboService.getSelectedItem().toString()+"' ";
				
			}
			
			
			
			dateChooserdebut.setDateFormatString("yyyy-MM-dd");
			String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			dateChooserfin.setDateFormatString("yyyy-MM-dd");
			String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();

			if(!dateDu.equals("") && dateAu.equals(""))
			{
				dateAu=dateDu;
			}else if(dateDu.equals("") && !dateAu.equals(""))
			{
				dateDu=dateAu;
			}
			
			
			
			if(!dateDu.equals("") && !dateAu.equals(""))
			{
				req=req+" and  d.transferStockMP.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
				
			}
			
			if(!txtCodTransfert.getText().equals(""))
			{
				req=req+" and d.transferStockMP.CodeTransfer='"+txtCodTransfert.getText()+"' ";
			}
			
			if(!combodepotDestination.getSelectedItem().equals(""))
			{
				req=req+" and d.transferStockMP.depot.id='"+mapDepotDestination.get(combodepotDestination.getSelectedItem()).getId()+"' ";
			}
			
			if(!combomagasinDestination.getSelectedItem().equals(""))
			{
				req=req+" and d.magasinDestination.id='"+mapMagasinDestination.get(combomagasinDestination.getSelectedItem()).getId()+"' ";
			}
			
			listDetailTransferStockMP=detailTransferStockMPDAO.findDetailTransferMPByRequete(req);
		
			
			req="";
			
			if(comboetat.getSelectedItem().equals(""))
			{
				
				
				req=req+" where  d.transferStockMP.statut in ('"+Constantes.ETAT_SORTIE_ENATTENT+"','"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"','"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER+"')  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.etat in ('"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"','"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER +"', '"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER+"')) ";
				
			}else
			{
				
				if(comboetat.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT))	
				{
					
					req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_SORTIE_ENATTENT+"'  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.etat in ('"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"')) ";
	
					
					
				}else if(comboetat.getSelectedItem().equals(Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER))
				{
					
					req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER+"'  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.etat ='"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER+"') ";

					
				}
				
				
				else if(comboetat.getSelectedItem().equals(Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER))
				{
					
					req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"'  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.etat ='"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER+"') ";

					
				}
				
				
				
				
			}
			
			
			
			
			if(!combodepotSource.getSelectedItem().equals(""))
			{
				req=req+" and d.transferStockMP.depot.id='"+mapDepotSource.get(combodepotSource.getSelectedItem()).getId()+"' ";
			}
			
			if(!combomagasinSource.getSelectedItem().equals(""))
			{
				req=req+" and d.magasinSouce.id='"+mapMagasinSource.get(combomagasinSource.getSelectedItem()).getId()+"' ";
			}
			
			listDetailTransferStockMPSortie=detailTransferStockMPDAO.findDetailTransferMPByRequete(req);
			
			boolean existe=false;
			for(int i=0;i<listDetailTransferStockMP.size();i++)
			{
				
				DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(i);
				existe=false;	
				Date dateSortie=null;
				Magasin magasinSource=null;
				for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
				{
					
					DetailTransferStockMP detailTransferStockMPTmp=listDetailTransferStockMPSortie.get(j);
				if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(detailTransferStockMPTmp.getTransferStockMP().getCodeTransfer()))
				{
					
					if(detailTransferStockMP.getFournisseur()!=null)
					{
						
						if(detailTransferStockMP.getMatierePremier().getId()==detailTransferStockMPTmp.getMatierePremier().getId()) 
						{
							if(detailTransferStockMPTmp.getFournisseur()!=null)
							{
								
								if(detailTransferStockMP.getFournisseur().getId()==detailTransferStockMPTmp.getFournisseur().getId())
								{
									
									
									existe=true;
									dateSortie=detailTransferStockMPTmp.getTransferStockMP().getDateTransfer();
									magasinSource=detailTransferStockMPTmp.getMagasinSouce();
									
									
								}
								
								
								
							}
							
							
							
							
						}
						
						
						
						
					}else
					{
						 
						
						if(detailTransferStockMP.getMatierePremier().getId()==detailTransferStockMPTmp.getMatierePremier().getId()) 
						{
							
							
							existe=true;
							dateSortie=detailTransferStockMPTmp.getTransferStockMP().getDateTransfer();
							magasinSource=detailTransferStockMPTmp.getMagasinSouce();
							
							
							
						}
						
					}
					
				
					
					
				}
					
					
					
				}
				
				if(existe==true)
				{
					
					if(detailTransferStockMP.getAction()!=null)
					{
						if(!detailTransferStockMP.getAction().equals(""))
						{
							DetailMarchandiseDeplacer detailMarchandiseDeplacer=new DetailMarchandiseDeplacer();	
							
							detailMarchandiseDeplacer.setCodeTransfert(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
							detailMarchandiseDeplacer.setTransferStockMP(detailTransferStockMP.getTransferStockMP());
							if(detailTransferStockMP.getAction()!=null)
							{
								detailMarchandiseDeplacer.setAction(detailTransferStockMP.getAction());	
							}else
							{
								detailMarchandiseDeplacer.setAction("");
							}
							
							if(detailTransferStockMP.getService()!=null)
							{
								detailMarchandiseDeplacer.setService(detailTransferStockMP.getService());
							}else
							{
								detailMarchandiseDeplacer.setService("");
							}
							
							detailMarchandiseDeplacer.setMagasinDestination(detailTransferStockMP.getMagasinDestination());
							detailMarchandiseDeplacer.setMagasinSouce(magasinSource);
							detailMarchandiseDeplacer.setDateEntrer(detailTransferStockMP.getTransferStockMP().getDateTransfer());
							detailMarchandiseDeplacer.setDateSortie(dateSortie);
							detailMarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
							detailMarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
							detailMarchandiseDeplacer.setEtat(detailTransferStockMP.getEtat());
							listDetailMarchandiseDeplacer.add(detailMarchandiseDeplacer);
						}
					
						
						
						
					}
					 
			
					
				}
				
				
				
				
				
				
			}
			
			
			
			afficher_tableEtatStock(listDetailMarchandiseDeplacer);
			
			
		}
		
		
		
		
		
		
	}
}

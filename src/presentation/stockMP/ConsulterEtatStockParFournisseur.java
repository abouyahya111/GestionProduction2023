package presentation.stockMP;

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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

import util.ComparateurStockMP;
import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsulterEtatStockParFournisseur extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private DefaultTableModel modeleEtatStock;
	private DefaultTableModel modeleMouvementStock;

	private JXTable tableEtatStock = new JXTable();

	private List<Depot> listDepot = new ArrayList<Depot>();
	private List<Depot> listparDepot = new ArrayList<Depot>();
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private List <Object[]> listeObjectStockFinale=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockActuel=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitial=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStock=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertSortieAncienne=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertSortieActuel=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertEntrerAncienne=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertEntrerActuel=new ArrayList<Object[]>();
	private List<EtatStockMP> listEtatStockMP = new ArrayList<EtatStockMP>();

	// ******************************************* Listes Pour Mouvement de Stock Mp
	// **********************************************//

	private List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer = new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp = new ArrayList<DetailMouvementStock>();

	// *******************************************************************************************************************************//

	// ************************************************

	private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
	private List<EtatStockMP> listEtatStockMPAfficherStockFinale=new ArrayList<EtatStockMP>();
	
	
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
	

	// ***************************************************

	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp = new ArrayList<DetailMouvementStock>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private List<MatierePremier> listMP = new ArrayList<MatierePremier>();

	private Map<String, Depot> mapDepot = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private Map<String, Magasin> mapMagasin = new HashMap<>();

	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseurMP = new HashMap<>();
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
	private DetailTransferMPDAO detailTransferStockMPDAO;

	private JTextField txtlibelle = new JTextField();

	private DepotDAO depotdao;
	
	
	private JDateChooser dateChooser = new JDateChooser();

	JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;

	private MatierePremiereDAO MatierPremiereDAO;
	String titre = "";
	Workbook workbook = new HSSFWorkbook();
	private JTextField txtCodeMP;
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox comboMP = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private CategorieMpDAO categoriempdao;
	JDateChooser dateChooserdebut = new JDateChooser();
	JDateChooser dateChooserfin = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private SubCategorieMPDAO subcategoriempdao;
	private MatierePremiereDAO matierePremiereDAO;
	JButton btnExporterExcel = new JButton("Exporter Excel");
	private List <Object[]> Mindate=new ArrayList<Object[]>();
	JComboBox combofournisseur = new JComboBox();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public ConsulterEtatStockParFournisseur() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1485, 1137);

		try {

			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgModifierr = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgValider = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgChercher = new ImageIcon(this.getClass().getResource("/img/chercher.png"));
			imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
			 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
			utilisateur = AuthentificationView.utilisateur;
			depotdao =  new DepotDAOImpl();
			mouvementStockGlobaleDAO = new MouvementStockGlobalDAOImpl();
			MatierPremiereDAO =  new MatierePremierDAOImpl();
			detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
			listMP = MatierPremiereDAO.findAll();
			categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		tableEtatStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {  "Code","Matiere Premiere","INITIAL", "RECEPTION","Transfert Entre", "Transfert Sortie", "Q.Charge","Charge Supp",
						 "Retour Machine","Autre Sortie", "Quantite Finale" }));
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
		tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
		tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(92);
		tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(92);
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
		scrollPane.setBounds(10, 299, 1465, 587);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Etat de Stock");
		titledSeparator.setBounds(10, 258, 1465, 30);
		add(titledSeparator);

		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			if(magasin!=null)
			{
				 if(listEtatStockMP.size()!=0)
				 {
				
						Map parameters = new HashMap();
						String datedu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
						String dateau=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
						parameters.put("magasin",magasin.getLibelle() );
						parameters.put("date","  Du :"+datedu +" Au : "+dateau);	
						 Collections.sort(listEtatStockMP, new ComparateurStockMP());
						JasperUtils.imprimerEtatStockParFournisseur(listEtatStockMP,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
			}
				
			
			
			}
		});

		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(698, 897, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);

		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		JButton btnAfficher = new JButton("Consulter");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CalculerStockFinale();
				
				
				
				
				listEtatStockMP.clear();
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				
				
				if((dateChooserfin.getDate()==null) && dateChooserdebut.getDate()!=null)
				{
					dateChooserfin.setDate(dateChooserdebut.getDate());
				}else if((dateChooserdebut.getDate()==null) && dateChooserfin.getDate()!=null)
				{
					dateChooserdebut.setDate(dateChooserfin.getDate());
				}else if((dateChooserfin.getDate()==null) && dateChooserdebut.getDate()==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				
					
				}
				
				
				
				FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem().toString());
				

				if(magasin==null)	{
					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					
					
					
					
						
						
	  					MatierePremier mp=MapMatierPremiere.get(comboMP.getSelectedItem());
	  					
	  					
	  				  	
	  				
	  				  	if(magasin==null)
	  				  	{
	  				  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
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
	  						String year = simpleDateFormatyear.format(dateChooserdebut.getDate());
	  						
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
	  					
	  					
	  					// listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(), magasin, subCategorieMpthe, null, null);
	 				  	  				  	
	  						listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateChooserdebut.getDate(), magasin, subCategorieMpthe, null, null) ;

	  					
	  						
	  						
	  					
	  					listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  				
	  					
	  	//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 					
	  				//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateChooserdebut.getDate(), magasin, subCategorieMpthe, null, null);

	  					
	  					
	  						listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateChooserdebut.getDate(), magasin, subCategorieMpthe, null, null) ;
	  						
	  					
	 
	  					//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					
	  					listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);
	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin, subCategorieMpthe, null, null);

	  					
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
	  			
	  	
	  			
	  			
	  			
	  			
						 
				
					Collections.sort(listEtatStockMPAfficher, new ComparateurStockMP());
					
					  
					  
						afficher_tableEtatStock(listEtatStockMPAfficher);
					
					
				}
			  
				
				
				

			
			
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAfficher.setBounds(590, 174, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 57, 1349, 106);
		add(layeredPane);
		
		JLabel lblDu = new JLabel("Du  :");
		lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblDu.setBounds(10, 11, 67, 24);
		layeredPane.add(lblDu);
		
		JLabel label_1 = new JLabel("Code MP:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(335, 55, 67, 24);
		layeredPane.add(label_1);
		
		txtCodeMP = new JTextField();
		txtCodeMP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					MatierePremier mp=MapCodeMatierPremiere.get(txtCodeMP.getText().toUpperCase());
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
		txtCodeMP.setText("");
		txtCodeMP.setColumns(10);
		txtCodeMP.setBounds(395, 55, 118, 26);
		layeredPane.add(txtCodeMP);
		
		 dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(48, 9, 163, 26);
		layeredPane.add(dateChooserdebut);
		
		JLabel label_2 = new JLabel("MP :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(523, 57, 37, 24);
		layeredPane.add(label_2);
		
		 comboMP = new JComboBox();
		 comboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {

	  		 	
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
  		 				txtCodeMP.setText(matierePremier.getCode());
  		 				
  		 					
  		 				
  		 			}else
  		 			{
  		 				txtCodeMP.setText(Constantes.CODE_MP);
  		 			}
  		 			
  		 				
  		 			
  		 		}else
  		 		{
  		 			txtCodeMP.setText(Constantes.CODE_MP);
  		 		}
  		 		
  		 		
  		 		
  		 		
  		 		
  		 	
		 		
		 		
		 		
		 	}
		 });
		 comboMP.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		comboMP.setSelectedIndex(-1);
		comboMP.setBounds(557, 57, 232, 24);
		layeredPane.add(comboMP);
		AutoCompleteDecorator.decorate(comboMP);
		JLabel label_3 = new JLabel("Magasin  :");
		label_3.setFont(new Font("Verdana", Font.BOLD, 12));
		label_3.setBounds(755, 10, 85, 26);
		layeredPane.add(label_3);
		
		 combomagasin = new JComboBox();
		combomagasin.setBounds(836, 13, 202, 27);
		layeredPane.add(combomagasin);
		
		JLabel label_4 = new JLabel("Sous-Categorie Mp");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(1048, 16, 144, 24);
		layeredPane.add(label_4);
		
		 soucategoriempcombo = new JComboBox();
		 soucategoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

		 		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  			categoriempcombo.addItem("");
  		  				while(i<listecategoriemp.size())
  		  				{
  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  				
  		  			}else
  		  			{
  		  			listecategoriemp.clear();
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			comboMP.removeAllItems();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  comboMP.removeAllItems();
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(1155, 16, 184, 24);
		layeredPane.add(soucategoriempcombo);
		AutoCompleteDecorator.decorate(soucategoriempcombo);
		JLabel label_5 = new JLabel("Categorie Mp");
		label_5.setBounds(20, 55, 80, 26);
		layeredPane.add(label_5);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {

		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  			comboMP.addItem(matierePremier.getNom());
  		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			listeMatierePremiereCombo.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listeMatierePremiereCombo.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 	}
		 });
		categoriempcombo.setBounds(95, 56, 208, 24);
		layeredPane.add(categoriempcombo);
		AutoCompleteDecorator.decorate(categoriempcombo);

		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) util.DateUtils.getCurrentYear() + "-01-01");

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton button = new JButton("Initialiser");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				combodepot.setSelectedItem("");
				soucategoriempcombo.setSelectedItem("");
				dateChooserdebut.setCalendar(null);
			dateChooserfin.setCalendar(null);
			

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(724, 174, 107, 24);
		add(button);


		
		 categoriempcombo.removeAllItems();
 		  categoriempcombo.addItem("");
 		soucategoriempcombo.removeAllItems();
 		soucategoriempcombo.addItem("");
 		
 		JLabel lblAu = new JLabel("Au  :");
 		lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
 		lblAu.setBounds(227, 13, 67, 24);
 		layeredPane.add(lblAu);
 		
 		 dateChooserfin = new JDateChooser();
 		dateChooserfin.setLocale(Locale.FRANCE);
 		dateChooserfin.setDateFormatString("dd/MM/yyyy");
 		dateChooserfin.setBounds(265, 11, 163, 26);
 		layeredPane.add(dateChooserfin);
 		
 		JLabel lblDepot = new JLabel("Depot  :");
 		lblDepot.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepot.setBounds(457, 11, 85, 26);
 		layeredPane.add(lblDepot);
 		
 		 combodepot = new JComboBox();
 		 combodepot.addItemListener(new ItemListener() {
 		 	public void itemStateChanged(ItemEvent e) {
 		 		
 		 		

 		 		

		  		
			  	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 	 			
	    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
	    	 		 {
	    	 			int i=0;
	    	 		
	    	 				if(!combodepot.getSelectedItem().equals(""))
	     			{
	     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
	     				if(depot!=null)
	     				{
	     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
	     					 listMagasinDechetMP= depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
			     				if(listMagasin.size()!=0 || listMagasinDechetMP.size()!=0 )
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
			     					
			     					  int j=0;
						  		      	while(j<listMagasinDechetMP.size())
						  		      		{	
						  		      			Magasin magasin=listMagasinDechetMP.get(j);
						  		      		combomagasin.addItem(magasin.getLibelle());
			  		     					mapMagasin.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
			     					
			     					
			     					
			     				}else
			     				{
			     					combomagasin.removeAllItems();
			     					
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
 		 });
 		 combodepot.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		/*
 		 		if(!combodepot.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasin.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepot.get(combodepot.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasin = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasin.addItem("");
 						while (k < listMagasin.size()) {
 							Magasin magasin = listMagasin.get(k);

 							combomagasin.addItem(magasin.getLibelle());

 							mapMagasin.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasin.removeAllItems();
 		 			combomagasin.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		*/
 		 		
 		 		
 		 		
 		 	}
 		 });
 		combodepot.setBounds(530, 13, 202, 27);
 		layeredPane.add(combodepot);
 		
 		JLabel label = new JLabel("Fournisseur :");
 		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		label.setBounds(816, 54, 78, 24);
 		layeredPane.add(label);
 		
 		 combofournisseur = new JComboBox();
 		combofournisseur.setSelectedIndex(-1);
 		combofournisseur.setBounds(904, 55, 232, 24);
 		layeredPane.add(combofournisseur);
 		
 		 btnExporterExcel = new JButton("Exporter Excel");
 		btnExporterExcel.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				

  				
  				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			if(tableEtatStock.getRowCount()!=0)
	    			{
	    				
	    				String titre="Etat Stock MP Par Fournisseur "+magasin.getLibelle();
			    		String titrefeuilleexcel="Etat Stock MP Par Fournisseur";
			    		ExporterTableVersExcel.tabletoexcelEtatStockMPParFournisseur (tableEtatStock, titre,titrefeuilleexcel);
	    				
	    				
	    			}else
	    			{
	    				
	    				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
		    			return;
	    				
	    				
	    			}
	    		
	    	
	    		}else
	    		{

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
  				
  				
  				
  				
  				
  				
  			
 				
 				
 				
 				
 			}
 		});
 		btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		btnExporterExcel.setBounds(855, 897, 135, 32);
 		btnExporterExcel.setIcon(imgExcel);
 		add(btnExporterExcel);
		  int i=0;
  		  while(i<listsubcategoriemp.size())
  		  {
  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
  			  i++;
  		  }
  		  
  		  
		if (utilisateur.getLogin().equals("admin")) {
			
			listDepot =depotdao.findAll();
			combodepot.removeAllItems();
			combodepot.addItem("");
			
			for(int d=0;d<listDepot.size();d++)
			{
				
			Depot depot=listDepot.get(d);
			combodepot.addItem(depot.getLibelle());
			mapDepot.put(depot.getLibelle(), depot);
				
				
				
				
			}
			
	

		} else {
			Depot depot = depotdao.findByCode(utilisateur.getCodeDepot());
			
			if (depot != null) {
				combodepot.removeAllItems();
				combodepot.addItem("");
				combodepot.addItem(depot.getLibelle());
				mapDepot.put(depot.getLibelle(), depot);
	

			}
		}
		
		
		
		listFournisseur=fournisseurMPDAO.findAll();
		combofournisseur.addItem("");
		for(int t=0;t<listFournisseur.size();t++)
		{
			
			FournisseurMP fournisseurMP=listFournisseur.get(t);
			
			combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
  		  
		

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

	void afficher_tableEtatStock(List<EtatStockMP> listEtatStockMP) {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Code","Matiere Premiere","Fournisseur",  "INITIAL", "RECEPTION","Transfert Entre", "Transfert Sortie", "Q.Charge","Charge Supp",
						 "Retour Machine","Autre Sortie", "Quantite Finale" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false,false, false ,false,false  };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		int i = 0;

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);
		
		String fournisseurMP="";
		
	
		
		
		while (i < listEtatStockMP.size()) {
			EtatStockMP EtatStockMP = listEtatStockMP.get(i);
			
			fournisseurMP="";
			if(EtatStockMP.getFournisseurMP()!=null)
			{
				fournisseurMP=EtatStockMP.getFournisseurMP().getCodeFournisseur();
			}
			
			Object[] ligne = {EtatStockMP.getMp().getCode(), EtatStockMP.getMp().getNom(),fournisseurMP,
					dfDecimal.format(
							EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteReception().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getTransferEntrer().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getTransferSortie().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteCharger().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteChargerSupp().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteRetour().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteAutreSortie().setScale(6, RoundingMode.HALF_UP)),
					dfDecimal.format(
							EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)) };

			
					
			
			modeleEtatStock.addRow(ligne);

			i++;
		}
	}
	
	
	
	public void CalculerStockMPNonThe()
	{
		
		
		
		if(listeObjectStockInitialGroupByMP.size()!=0)
		{
			
			
boolean initialIntrouvable=false;
			
			
			for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
			{
				
				 Object[] object=listeObjectStockInitialGroupByMP.get(i);
				
				MatierePremier mp= (MatierePremier)object[0];
				
				
				
				
				
				if(((BigDecimal)object[1]).compareTo(BigDecimal.ZERO)!=0)
				{
					
					initialIntrouvable=true;
				}
				
				
				

				
				
			}
			
			
			if(initialIntrouvable==true)
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
				
			}else
			{
				
				for(int i=0;i<listEtatStockMPAfficherStockFinale.size() ; i++)
				{
					if(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP()==null)
					{
						
						EtatStockMP etatStockMP=new EtatStockMP();
						
						
						
						
						etatStockMP.setMp(listEtatStockMPAfficherStockFinale.get(i).getMp());
												
						etatStockMP.setQuantiteInitial(listEtatStockMPAfficherStockFinale.get(i).getQuantiteFinale());
						
					
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
					
				
					
					
				}
				
			}
			
		
			
			
		}else if(listEtatStockMPAfficherStockFinale.size()!=0)
		{
			
			
			for(int i=0;i<listEtatStockMPAfficherStockFinale.size() ; i++)
			{
				if(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP()==null)
				{
					
					EtatStockMP etatStockMP=new EtatStockMP();
					
					
					
					
					etatStockMP.setMp(listEtatStockMPAfficherStockFinale.get(i).getMp());
											
					etatStockMP.setQuantiteInitial(listEtatStockMPAfficherStockFinale.get(i).getQuantiteFinale());
					
				
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
				
			
				
				
			}
			
			
			
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
		
		
		
		if(listeObjectStockInitialGroupByMPByFournisseur.size()!=0)
		{
			boolean initialIntrouvable=false;
			
			
			for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
			{
				
				 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
				
				MatierePremier mp= (MatierePremier)object[0];
				
				FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
				
				
				
				if(((BigDecimal)object[2]).compareTo(BigDecimal.ZERO)!=0)
				{
					
					initialIntrouvable=true;
				}
				
				
				

				
				
			}	
			
			if(initialIntrouvable==true)
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
				
			}else
			{
				
				for(int i=0;i<listEtatStockMPAfficherStockFinale.size() ; i++)
				{
					if(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP()!=null)
					{
						
						EtatStockMP etatStockMP=new EtatStockMP();
						
						
						
						
						etatStockMP.setMp(listEtatStockMPAfficherStockFinale.get(i).getMp());
												
						etatStockMP.setQuantiteInitial(listEtatStockMPAfficherStockFinale.get(i).getQuantiteFinale());
						
						etatStockMP.setFournisseurMP(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP());
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
					
				
					
					
				}
				
			}
			
			
			
		}else if(listEtatStockMPAfficherStockFinale.size()!=0)
		{
			
			
			
			for(int i=0;i<listEtatStockMPAfficherStockFinale.size() ; i++)
			{
				if(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP()!=null)
				{
					
					EtatStockMP etatStockMP=new EtatStockMP();
					
					
					
					
					etatStockMP.setMp(listEtatStockMPAfficherStockFinale.get(i).getMp());
											
					etatStockMP.setQuantiteInitial(listEtatStockMPAfficherStockFinale.get(i).getQuantiteFinale());
					
					etatStockMP.setFournisseurMP(listEtatStockMPAfficherStockFinale.get(i).getFournisseurMP());
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
				
			
				
				
			}
			
			
			
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
	
	public void CalculerStockFinale()	  
	{
		
		

		listEtatStockMP.clear();
		
		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
		CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
	
		
		if((dateChooserfin.getDate()==null) && dateChooserdebut.getDate()!=null)
		{
			dateChooserfin.setDate(dateChooserdebut.getDate());
		}else if((dateChooserdebut.getDate()==null) && dateChooserfin.getDate()!=null)
		{
			dateChooserdebut.setDate(dateChooserfin.getDate());
		}else if((dateChooserfin.getDate()==null) && dateChooserdebut.getDate()==null)
		{

			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		
			
		}
		
		Date DateMoinsUnJour=DateUtils.DateaddDay(dateChooserdebut.getDate(), -1);
		
		
		
		FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem().toString());
		

		if(magasin==null)	{
			JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			
			
			MatierePremier mp=MapMatierPremiere.get(comboMP.getSelectedItem());
				  	
				  	
				  	
				
					
					
					
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
						String year = simpleDateFormatyear.format(dateChooserdebut.getDate());
						
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
				  	  				  	
					
						listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;
						
					
						
						
					
					listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
				
					
	//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

					
					
						listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
						
					

					//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					
					listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);
					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,DateMoinsUnJour, magasin, subCategorieMpthe, null, null);

					
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
			listEtatStockMP.clear();
			listEtatStockMPAfficherStockFinale.clear();
			CalculerStockMPThe();	
			CalculerStockMPNonThe();		
				
				
			for(int j=0;j<listEtatStockMP.size();j++)
			{
				
			EtatStockMP etatStockMP=listEtatStockMP.get(j);	
				
			if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);
				}
				
				
				
				
			}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
			{
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);	
					
				}
				
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);	
					
				}
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);	
					
				}
				
			}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);	
					
				}
				
			}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
			{
				
				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
				{
					
					listEtatStockMPAfficherStockFinale.add(etatStockMP);	
					
				}
				
			}else
			{
				
				
				listEtatStockMPAfficherStockFinale.add(etatStockMP);	
				
				
				
			}
				
				
			
			
			
			
			
			
			
				
				
				
				
				
			}
			
	
			
			
			
			
				 
			
			Collections.sort(listEtatStockMPAfficherStockFinale, new ComparateurStockMP());
			
			
			
			
		}
	  }
	
		
		

	
	
	
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	


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
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
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
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsulterEtatStock extends JLayeredPane implements Constantes {
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

	private List<DetailTransferStockMP> listDetailTransferStockMPReception = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPReceptionGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPCharge = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
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

	private Map<String, Depot> mapDepot = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private Map<String, Magasin> mapMagasin = new HashMap<>();

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
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public ConsulterEtatStock() {
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
						JasperUtils.imprimerEtatStock(listEtatStockMP,parameters);
						
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
				listEtatStockMP.clear();
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
				
				if((dateChooserfin.getDate()==null))
				{
					dateChooserfin.setDate(dateChooserdebut.getDate());
				}
				
				
				for(int t=0;t<listMP.size();t++)
				{
					
					MatierePremier mp=listMP.get(t);
					EtatStockMP etatStockMP=new EtatStockMP();
					etatStockMP.setMp(mp);
					etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
					 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
					  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
					  etatStockMP.setTransferSortie(BigDecimal.ZERO);
					  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
					  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
					  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
					  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
					etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
					//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
					listEtatStockMP.add(etatStockMP);
					
				}
				
				
				
				
				
				
				listeObjectStockFinale=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);
				
				if(listeObjectStockFinale.size()==0)
				{
					listeObjectStockInitial=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);

				
				
				
				}
				
				
				listeObjectEtatStock=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);
				listeObjectTransfertSortieAncienne=detailTransferStockMPDAO.listetransfertSortieAncienneByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);
				 listeObjectTransfertSortieActuel=detailTransferStockMPDAO.listetransfertSortieActuelByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);
				 listeObjectTransfertEntrerAncienne=detailTransferStockMPDAO.listetransfertEntrerAncienneByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);
				 listeObjectTransfertEntrerActuel=detailTransferStockMPDAO.listetransfertEntrerActuelByMagasinBySubCategorieByCategorieBayMP(dateChooserdebut.getDate(),dateChooserfin.getDate(), magasin,subCategorieMp,categorieMp,matierePremier);

				 
				 
				for(int i=0;i<listeObjectStockFinale.size() ; i++)
				{
					
					 Object[] object=listeObjectStockFinale.get(i);
					
					MatierePremier mp= (MatierePremier)object[0];
								
					BigDecimal initial=BigDecimal.ZERO;
				BigDecimal ajout=BigDecimal.ZERO;
				BigDecimal entrer=BigDecimal.ZERO;	
				BigDecimal transfertentrer=BigDecimal.ZERO;
				BigDecimal charger=BigDecimal.ZERO;
				BigDecimal chargesupp=BigDecimal.ZERO;
				BigDecimal sortie=BigDecimal.ZERO;
				BigDecimal transfertsortie=BigDecimal.ZERO;
				BigDecimal retour=BigDecimal.ZERO;
				BigDecimal fabriquer=BigDecimal.ZERO;
				BigDecimal autresortie=BigDecimal.ZERO;
				
				if(object[1]!=null)
				{
					initial=(BigDecimal)object[1];
				}
				if(object[2]!=null)
				{
					ajout=(BigDecimal)object[2];
				}
				
				if(object[3]!=null)
				{
					entrer=(BigDecimal)object[3];
				}
				
				if(object[4]!=null)
				{
					transfertentrer=(BigDecimal)object[4];
				}
				
				if(object[9]!=null)
				{
					retour=(BigDecimal)object[9];
				}
				
				if(object[5]!=null)
				{
					charger=(BigDecimal)object[5];
				}
				if(object[6]!=null)
				{
					chargesupp=(BigDecimal)object[6];
				}
				if(object[7]!=null)
				{
					sortie=(BigDecimal)object[7];
				}
				
				if(object[8]!=null)
				{
					transfertsortie=(BigDecimal)object[8];
				}
				if(object[10]!=null)
				{
					autresortie=autresortie.add((BigDecimal)object[10]);
				}	
				if(object[11]!=null)
				{
					autresortie=autresortie.add((BigDecimal)object[11]);
				}
				if(object[12]!=null)
				{
					autresortie=autresortie.add((BigDecimal)object[12]);
				}
				if(object[13]!=null)
				{
					autresortie=autresortie.add((BigDecimal)object[13]);
				}	
				if(object[14]!=null)
				{
					fabriquer=fabriquer.add((BigDecimal)object[14]);
				}	
			
				
				
					
					System.out.println(mp.getCode()+" : Total Autres Sortie : "+autresortie);
					
					for(int d=0;d<listEtatStockMP.size();d++)
					{
						
						EtatStockMP etatStockMP =listEtatStockMP.get(d);
						if(mp.getId()==etatStockMP.getMp().getId())
						{
							
							etatStockMP.setQuantiteInitial((initial.add(ajout.add(entrer.add(transfertentrer.add(retour))).add(fabriquer))).subtract(charger.add(chargesupp.add(sortie.add(transfertsortie.add(autresortie))))));
							 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
							  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
							  etatStockMP.setTransferSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
							  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
							  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
							  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
							etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
							//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
							listEtatStockMP. set(d, etatStockMP);
							
						}
						
						
						
						
					}
						
					
					
				
				
				
				
				
			
				
				
				}
				
				
				
				
				
				if(listeObjectStockFinale.size()==0)
				{
					
					for(int i=0;i<listeObjectStockInitial.size() ; i++)
					{
						
						 Object[] object=listeObjectStockInitial.get(i);
						
						MatierePremier mp= (MatierePremier)object[0];
						
					
						for(int d=0;d<listEtatStockMP.size();d++)
						{
							
							EtatStockMP etatStockMP =listEtatStockMP.get(d);
							if(mp.getId()==etatStockMP.getMp().getId())
							{
								etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
								
								
								 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
								  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
								  etatStockMP.setTransferSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
								  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
								  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
								  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
								etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
								//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
								listEtatStockMP. set(d, etatStockMP);
								
								
							}
						}		
							
								
								
							
						
					
						
						
					
						
						
						
						
						
						
					}
				}
				
				
				
				
				
				/*
				 * ///////// Ajouter Transfert Entrer Ancienne au Initial
				 * 
				 * for(int k=0;k<listeObjectTransfertEntrerAncienne.size() ; k++) {
				 * 
				 * Object[] object=listeObjectTransfertEntrerAncienne.get(k); MatierePremier
				 * mp=(MatierePremier)object[0];
				 * 
				 * for(int d=0;d<listEtatStockMP.size();d++) {
				 * 
				 * if(listEtatStockMP.get(d).getMp().getId()==mp.getId()) {
				 * 
				 * EtatStockMP etatStockMP=listEtatStockMP.get(d);
				 * etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().add((
				 * BigDecimal)object[1]));
				 * 
				 * listEtatStockMP.set(d, etatStockMP);
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 */
				  
				/*
				 * ///////// Supprimer Transfert Sortie Ancienne au Initial
				 * 
				 * for(int k=0;k<listeObjectTransfertSortieAncienne.size() ; k++) {
				 * 
				 * Object[] object=listeObjectTransfertSortieAncienne.get(k); MatierePremier
				 * mp=(MatierePremier)object[0];
				 * 
				 * for(int d=0;d<listEtatStockMP.size();d++) {
				 * 
				 * if(listEtatStockMP.get(d).getMp().getId()==mp.getId()) {
				 * 
				 * EtatStockMP etatStockMP=listEtatStockMP.get(d);
				 * etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().subtract((
				 * BigDecimal)object[1]));
				 * 
				 * listEtatStockMP.set(d, etatStockMP);
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 */
				  
				  
				/*
				 * // Ajouter Transfert Entrer Actuel
				 * 
				 * for(int k=0;k<listeObjectTransfertEntrerActuel.size() ; k++) {
				 * 
				 * Object[] object=listeObjectTransfertEntrerActuel.get(k); MatierePremier
				 * mp=(MatierePremier)object[0];
				 * 
				 * for(int d=0;d<listEtatStockMP.size();d++) {
				 * 
				 * if(listEtatStockMP.get(d).getMp().getId()==mp.getId()) {
				 * 
				 * EtatStockMP etatStockMP=listEtatStockMP.get(d);
				 * etatStockMP.setTransferEntrer((BigDecimal)object[1]);
				 * 
				 * listEtatStockMP.set(d, etatStockMP);
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 */
				/*
				 * // Ajouter Transfert Sortie Actuel
				 * 
				 * for(int k=0;k<listeObjectTransfertSortieActuel.size() ; k++) {
				 * 
				 * Object[] object=listeObjectTransfertSortieActuel.get(k); MatierePremier
				 * mp=(MatierePremier)object[0];
				 * 
				 * for(int d=0;d<listEtatStockMP.size();d++) {
				 * 
				 * if(listEtatStockMP.get(d).getMp().getId()==mp.getId()) {
				 * 
				 * EtatStockMP etatStockMP=listEtatStockMP.get(d);
				 * etatStockMP.setTransferSortie((BigDecimal)object[1]);
				 * 
				 * listEtatStockMP.set(d, etatStockMP);
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 */
				
				
			
				
				
				
				
				boolean existe=false;
				
				  for(int j=0;j<listeObjectEtatStock.size() ; j++) {
				  
					  existe=false;
					  
				  Object[] object=listeObjectEtatStock.get(j);
				  MatierePremier mp=(MatierePremier)object[0];
					  
				  for(int k=0;k<listEtatStockMP.size();k++) {
				  
				  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
					  existe=true;
				  
				  EtatStockMP etatStockMP=listEtatStockMP.get(k);
				  
				  etatStockMP.setQuantiteReception(((BigDecimal)object[1]).add((BigDecimal)object[3]));
				  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[4]));
				  etatStockMP.setTransferSortie(((BigDecimal)object[7]).add((BigDecimal)object[8]));
				  etatStockMP.setQuantiteCharger((BigDecimal)object[5]);
				  etatStockMP.setQuantiteChargerSupp((BigDecimal)object[6]);
				  etatStockMP.setQuantiteRetour((BigDecimal)object[9]);
				  etatStockMP.setQuantiteAutreSortie((BigDecimal)object[10]);
				  
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer()))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()))));
				 
				  listEtatStockMP.set(k, etatStockMP);
				  
				  }
				  
		
				  
				  
				  
				  
				  
				  }
				  
					if(existe==false)
					{
						
						
						
						  EtatStockMP etatStockMP=new EtatStockMP();
						 
						  etatStockMP.setMp(mp);
						  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
						  etatStockMP.setQuantiteReception(((BigDecimal)object[1]).add((BigDecimal)object[3]));
						  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[4]));
						  etatStockMP.setTransferSortie(((BigDecimal)object[7]).add((BigDecimal)object[8]));
						  etatStockMP.setQuantiteCharger((BigDecimal)object[5]);
						  etatStockMP.setQuantiteChargerSupp((BigDecimal)object[6]);
						  etatStockMP.setQuantiteRetour((BigDecimal)object[9]);
						  etatStockMP.setQuantiteAutreSortie((BigDecimal)object[10]);
						 
						  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer()))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()))));
						 
						  listEtatStockMP.add(etatStockMP);	
						
						
						
					}
				  
				  
				  
				  
				  }
				 
				  
				  for(int k=0;k<listEtatStockMP.size();k++) {
					  
					  EtatStockMP etatStockMP=listEtatStockMP.get(k);
					
					  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer()))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()))));
					 
					  listEtatStockMP.set(k, etatStockMP);
					  
					  
					  }
				 
				
				  Collections.sort(listEtatStockMP, new ComparateurStockMP());
				  
				  
				afficher_tableEtatStock(listEtatStockMP);
				
				
				
				/*
				 * detailTransferStockMPDAO.ViderSession(); listEtatStockMP.clear();
				 * listDetailTransferStockMPReception.clear();
				 * listDetailTransferStockMPReceptionGroupebyMP.clear();
				 * listDetailTransferStockMPEnter.clear();
				 * listDetailTransferStockMPEntrerGroupebyMP.clear();
				 * listDetailTransferStockMPSortie.clear();
				 * listDetailTransferStockMPSortieGroupebyMP.clear();
				 * listDetailTransferStockMPCharge.clear();
				 * listDetailTransferStockMPChargeGroupebyMP.clear();
				 * listDetailTransferStockMPChargeSupp.clear();
				 * listDetailTransferStockMPChargeSuppGroupebyMP.clear();
				 * 
				 * listDetailTransferStockMPAllMP.clear();
				 * 
				 * SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY"); BigDecimal
				 * quantiteTotalreception=new BigDecimal(0); BigDecimal quantiteTotalEntrer=new
				 * BigDecimal(0); BigDecimal quantiteTotalsortie=new BigDecimal(0); BigDecimal
				 * quantiteTotalretour=new BigDecimal(0); BigDecimal quantiteTotalFinale=new
				 * BigDecimal(0); BigDecimal quantiteTotalchargesupp=new BigDecimal(0);
				 * BigDecimal quantiteTotalDechet=new BigDecimal(0); BigDecimal
				 * quantiteTotalOffreProduction=new BigDecimal(0);
				 * 
				 * boolean trouve=false; MatierePremier mp=mapMP.get(combomp.getSelectedItem());
				 * Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
				 * 
				 * if(magasin!=null) { if(dateChooserdebut.getDate()!=null &&
				 * dateChooserfin.getDate()!=null) { String
				 * d1=sdf.format(dateChooserdebut.getDate()); String
				 * d2=sdf.format(dateChooserfin.getDate());
				 * 
				 * if(!d1.equals(d2)) {
				 * if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0) {
				 * JOptionPane.showMessageDialog(null,
				 * "date de fin doit etre supérieur au date debut SVP !!!"); return; }
				 * 
				 * }
				 * 
				 * if(mp!=null) { titre="Etat de Stock de "+mp.getNom()
				 * +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2; }else {
				 * titre="Etat de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+
				 * " et "+d2; }
				 * 
				 * }
				 * 
				 * 
				 * if(dateChooserdebut.getDate()==null && mp!=null) {
				 * dateChooserfin.setCalendar(null); titre="Mouvement de Stock de "+mp.getNom()
				 * +" au magasin : "+magasin.getLibelle(); }else
				 * if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null &&
				 * mp!=null) { String d1=sdf.format(dateChooserdebut.getDate());
				 * titre="Mouvement de Stock de "+mp.getNom()
				 * +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
				 * 
				 * }else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null
				 * && mp==null) { String d1=sdf.format(dateChooserdebut.getDate());
				 * titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1
				 * +" et "+d1; }
				 * 
				 * 
				 * listDetailTransferStockMPReception
				 * =detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(
				 * dateChooserdebut.getDate(), dateChooserfin.getDate(), mp,
				 * ETAT_TRANSFER_STOCK_AJOUT,magasin);
				 * listDetailTransferStockMPReceptionGroupebyMP=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.
				 * getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AJOUT,magasin);
				 * 
				 * 
				 * listDetailTransferStockMPSortie=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(),
				 * dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE,magasin);
				 * listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.
				 * getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE,magasin);
				 * 
				 * listDetailTransferStockMPCharge=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(),
				 * dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
				 * listDetailTransferStockMPChargeGroupebyMP=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.
				 * getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
				 * 
				 * 
				 * 
				 * listDetailTransferStockMPEnter=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateChooserdebut.getDate(),
				 * dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ENTRE,magasin);
				 * listDetailTransferStockMPEntrerGroupebyMP=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateChooserdebut.
				 * getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ENTRE,magasin);
				 * 
				 * listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateChooserdebut.
				 * getDate(), dateChooserfin.getDate(), mp,
				 * ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
				 * listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.
				 * ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(
				 * dateChooserdebut.getDate(), dateChooserfin.getDate(), mp,
				 * ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
				 * 
				 * 
				 * listDetailTransferStockMPAllMP=
				 * detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
				 * 
				 * for(int p=0;p<listDetailTransferStockMPAllMP.size();p++) {
				 * DetailTransferStockMP
				 * detailtransferstockmp=listDetailTransferStockMPAllMP.get(p); EtatStockMP
				 * etatstock=new EtatStockMP();
				 * etatstock.setMp(detailtransferstockmp.getMatierePremier());
				 * etatstock.setQuantiteReception(BigDecimal.ZERO);
				 * etatstock.setQuantiteEntrer(BigDecimal.ZERO);
				 * etatstock.setQuantiteSortie(BigDecimal.ZERO);
				 * //etatstock.setQuantiteService(BigDecimal.ZERO);
				 * //etatstock.setQuantiteDechetService(BigDecimal.ZERO);
				 * etatstock.setQuantiteDechet(BigDecimal.ZERO);
				 * //etatstock.setQuantiteOffreService(BigDecimal.ZERO);
				 * etatstock.setQuantiteOffreProduction(BigDecimal.ZERO);
				 * etatstock.setQuantiteRetour(BigDecimal.ZERO);
				 * etatstock.setQuantiteFinale(BigDecimal.ZERO); listEtatStockMP.add(etatstock);
				 * 
				 * }
				 * 
				 * 
				 * // calculer quantite reception
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPReceptionGroupebyMP.size();j++) {
				 * 
				 * quantiteTotalreception=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPReception.size();k++) {
				 * 
				 * if(listDetailTransferStockMPReceptionGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPReception.get(k).getMatierePremier())) {
				 * 
				 * if(listDetailTransferStockMPReception.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPReception.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPReception.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPReception.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPReception.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * quantiteTotalreception=quantiteTotalreception.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPReception.get(k).
				 * getQuantite().setScale(0, RoundingMode.CEILING)); }else {
				 * 
				 * quantiteTotalreception=quantiteTotalreception.add(
				 * listDetailTransferStockMPReception.get(k).getQuantite()); }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if( !quantiteTotalreception.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPReceptionGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * 
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteReception(quantiteTotalreception.setScale(0,
				 * RoundingMode.CEILING));
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * 
				 * }else {
				 * 
				 * etatstockmp.setQuantiteReception(quantiteTotalreception);
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * 
				 * } }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * // calculer quantite Enter
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPEntrerGroupebyMP.size();j++) {
				 * 
				 * quantiteTotalEntrer=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPEnter.size();k++) {
				 * 
				 * if(listDetailTransferStockMPEntrerGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPEnter.get(k).getMatierePremier())) {
				 * 
				 * if(listDetailTransferStockMPEnter.get(k).getMatierePremier().getCategorieMp()
				 * .getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPEnter.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPEnter.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPEnter.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPEnter.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * quantiteTotalEntrer=quantiteTotalEntrer.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPEnter.get(k).getQuantite()
				 * .setScale(0, RoundingMode.CEILING)); }else {
				 * 
				 * quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockMPEnter.
				 * get(k).getQuantite()); }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if( !quantiteTotalEntrer.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPEntrerGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * 
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteEntrer(quantiteTotalEntrer.setScale(0,
				 * RoundingMode.CEILING));
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * 
				 * }else {
				 * 
				 * etatstockmp.setQuantiteEntrer(quantiteTotalEntrer);
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * 
				 * } }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * // calculer quantite Sortie
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPSortieGroupebyMP.size();j++) {
				 * 
				 * quantiteTotalsortie=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPSortie.size();k++) {
				 * 
				 * if(listDetailTransferStockMPSortieGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPSortie.get(k).getMatierePremier())) {
				 * 
				 * if(listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * quantiteTotalsortie=quantiteTotalsortie.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(k).getQuantite(
				 * ).setScale(0, RoundingMode.CEILING)); }else {
				 * 
				 * quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPSortie.
				 * get(k).getQuantite()); }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if( !quantiteTotalsortie.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPSortieGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * 
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalEntrer.setScale(0,
				 * RoundingMode.CEILING)) );
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * 
				 * }else {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalEntrer));
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * 
				 * } }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * // charger les new value de stock achat dans la liste Etat Stock MP
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) { for(int
				 * j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * etatstockmp.setQuantiteAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getQuantite());
				 * etatstockmp.setPrixAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getPrixUnitaire());
				 * etatstockmp.setMontantAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getQuantite().multiply(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getPrixUnitaire())); listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * // calculer Quantite Sortie (charge)
				 * 
				 * 
				 * 
				 * 
				 * for(int i=0;i<listDetailTransferStockMPChargeGroupebyMP.size();i++) {
				 * 
				 * 
				 * 
				 * quantiteTotalsortie=BigDecimal.ZERO;
				 * 
				 * quantiteTotalDechet=BigDecimal.ZERO;
				 * quantiteTotalOffreProduction=BigDecimal.ZERO;
				 * quantiteTotalretour=BigDecimal.ZERO; for(int
				 * j=0;j<listDetailTransferStockMPCharge.size();j++) {
				 * 
				 * if(listDetailTransferStockMPChargeGroupebyMP.get(i).getMatierePremier().
				 * equals(listDetailTransferStockMPCharge.get(j).getMatierePremier())) {
				 * if(listDetailTransferStockMPCharge.get(j).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPCharge.get(j).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPCharge.get(j).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPCharge.get(j).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPCharge.get(j).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * quantiteTotalsortie=quantiteTotalsortie.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPCharge.get(j).getQuantite(
				 * ).setScale(0, RoundingMode.CEILING));
				 * 
				 * if(listDetailTransferStockMPCharge.get(j).getQuantiteDechet()!=null) {
				 * quantiteTotalDechet=quantiteTotalDechet.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPCharge.get(j).
				 * getQuantiteDechet().setScale(0, RoundingMode.CEILING));
				 * 
				 * } if(listDetailTransferStockMPCharge.get(j).getQuantiteOffre()!=null) {
				 * quantiteTotalOffreProduction=quantiteTotalOffreProduction.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPCharge.get(j).
				 * getQuantiteOffre().setScale(0, RoundingMode.CEILING)); }
				 * 
				 * if(listDetailTransferStockMPCharge.get(j).getQuantiteRetour()!=null) {
				 * quantiteTotalretour=quantiteTotalretour.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPCharge.get(j).
				 * getQuantiteRetour().setScale(0, RoundingMode.CEILING)); }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * }else {
				 * quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPCharge.
				 * get(j).getQuantite());
				 * 
				 * 
				 * if(listDetailTransferStockMPCharge.get(j).getQuantiteDechet()!=null) {
				 * quantiteTotalDechet=quantiteTotalDechet.add(listDetailTransferStockMPCharge.
				 * get(j).getQuantiteDechet());
				 * 
				 * } if(listDetailTransferStockMPCharge.get(j).getQuantiteOffre()!=null) {
				 * quantiteTotalOffreProduction=quantiteTotalOffreProduction.add(
				 * listDetailTransferStockMPCharge.get(j).getQuantiteOffre());
				 * 
				 * }
				 * 
				 * if(listDetailTransferStockMPCharge.get(j).getQuantiteRetour()!=null) {
				 * quantiteTotalretour=quantiteTotalretour.add(listDetailTransferStockMPCharge.
				 * get(j).getQuantiteRetour());
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
				 * 
				 * }
				 * 
				 * if(!quantiteTotalsortie.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * DetailTransferStockMP
				 * detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
				 * detailtransferstockmp.setQuantite(quantiteTotalsortie);
				 * listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
				 * 
				 * 
				 * // charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock
				 * 
				 * for(int k=0;k<listEtatStockMP.size();k++) {
				 * 
				 * if(listEtatStockMP.get(k).getMp().equals(
				 * listDetailTransferStockMPChargeGroupebyMP.get(i).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(k);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalsortie.setScale(0,
				 * RoundingMode.CEILING)));
				 * 
				 * 
				 * 
				 * }else {
				 * 
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalsortie));
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * listEtatStockMP.set(k, etatstockmp); }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * /// inserer la quantite dechet , prix dechet et montant dechet
				 * 
				 * if(!quantiteTotalDechet.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * DetailTransferStockMP
				 * detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
				 * detailtransferstockmp.setQuantite(quantiteTotalsortie);
				 * listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
				 * 
				 * 
				 * // charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock
				 * 
				 * for(int k=0;k<listEtatStockMP.size();k++) {
				 * 
				 * if(listEtatStockMP.get(k).getMp().equals(
				 * listDetailTransferStockMPChargeGroupebyMP.get(i).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(k);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(
				 * quantiteTotalDechet.setScale(0, RoundingMode.CEILING)) );
				 * 
				 * 
				 * }else { etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(
				 * quantiteTotalDechet));
				 * 
				 * }
				 * 
				 * 
				 * listEtatStockMP.set(k, etatstockmp); }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * /// inserer la quantite Offre , prix Offre et montant Offre (production)
				 * 
				 * if(!quantiteTotalOffreProduction.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * DetailTransferStockMP
				 * detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
				 * detailtransferstockmp.setQuantite(quantiteTotalsortie);
				 * listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
				 * 
				 * 
				 * // charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock
				 * 
				 * for(int k=0;k<listEtatStockMP.size();k++) {
				 * 
				 * if(listEtatStockMP.get(k).getMp().equals(
				 * listDetailTransferStockMPChargeGroupebyMP.get(i).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(k);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction
				 * ().add(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING)));
				 * 
				 * 
				 * }else {
				 * etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction
				 * ().add(quantiteTotalOffreProduction));
				 * 
				 * }
				 * 
				 * listEtatStockMP.set(k, etatstockmp); }
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * /// inserer la quantite Retour (production)
				 * 
				 * if(!quantiteTotalretour.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * DetailTransferStockMP
				 * detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
				 * detailtransferstockmp.setQuantite(quantiteTotalsortie);
				 * listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
				 * 
				 * 
				 * // charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock
				 * 
				 * for(int k=0;k<listEtatStockMP.size();k++) {
				 * 
				 * if(listEtatStockMP.get(k).getMp().equals(
				 * listDetailTransferStockMPChargeGroupebyMP.get(i).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(k);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteRetour(etatstockmp.getQuantiteRetour().add(
				 * quantiteTotalretour.setScale(0, RoundingMode.CEILING)));
				 * 
				 * 
				 * }else { etatstockmp.setQuantiteRetour(etatstockmp.getQuantiteRetour().add(
				 * quantiteTotalretour));
				 * 
				 * }
				 * 
				 * listEtatStockMP.set(k, etatstockmp); }
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
				 * 
				 * 
				 * 
				 * // calculer le prix moyen et quantite ChargeSupp
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPChargeSuppGroupebyMP.size();j++) {
				 * 
				 * quantiteTotalchargesupp=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPChargeSupp.size();k++) {
				 * 
				 * if(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier())) {
				 * if(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp
				 * ().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp
				 * ().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp
				 * ().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp
				 * ().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * quantiteTotalchargesupp=quantiteTotalchargesupp.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPChargeSupp.get(k).
				 * getQuantite().setScale(0, RoundingMode.CEILING)); }else {
				 * 
				 * quantiteTotalchargesupp=quantiteTotalchargesupp.add(
				 * listDetailTransferStockMPChargeSupp.get(k).getQuantite()); }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if( !quantiteTotalchargesupp.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0,
				 * RoundingMode.CEILING))); listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }else {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalchargesupp)); listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * // calculer le prix moyen et quantite ChargeSupp
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPSortieGroupebyMP.size();j++) {
				 * 
				 * quantiteTotalsortie=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPSortie.size();k++) {
				 * 
				 * if(listDetailTransferStockMPSortieGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPSortie.get(k).getMatierePremier())) {
				 * if(listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp(
				 * ).getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPSortie.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * quantiteTotalsortie=quantiteTotalsortie.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(k).getQuantite(
				 * ).setScale(0, RoundingMode.CEILING)); }else {
				 * 
				 * quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPSortie.
				 * get(k).getQuantite()); }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if( !quantiteTotalsortie.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPSortieGroupebyMP.get(j).getMatierePremier())) {
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalsortie.setScale(0,
				 * RoundingMode.CEILING))); listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }else {
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalsortie)); listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * // calculer le prix moyen et quantite Service
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPServiceGroupebyMP.size();j++) {
				 * montantService=new BigDecimal(0); quantiteTotalService=new BigDecimal(0);
				 * montantDechetService=new BigDecimal(0); quantiteTotalDechetService=new
				 * BigDecimal(0); montantOffreService=new BigDecimal(0);
				 * quantiteTotalOffreService=new BigDecimal(0);
				 * 
				 * for(int k=0;k<listDetailTransferStockMPService.size();k++) {
				 * 
				 * if(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier().
				 * equals(listDetailTransferStockMPService.get(k).getMatierePremier())) {
				 * if(listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp
				 * ().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) ||
				 * listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) ||
				 * listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) ||
				 * listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().
				 * getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) ) {
				 * montantService=montantService.add(listDetailTransferStockMPService.get(k).
				 * getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).
				 * getQuantite())); quantiteTotalService=quantiteTotalService.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantite
				 * ().setScale(0, RoundingMode.CEILING));
				 * 
				 * montantDechetService=montantDechetService.add(
				 * listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(
				 * listDetailTransferStockMPService.get(k).getQuantiteDechet()));
				 * quantiteTotalDechetService=quantiteTotalDechetService.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).
				 * getQuantiteDechet().setScale(0, RoundingMode.CEILING));
				 * if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null) {
				 * montantOffreService=montantOffreService.add(listDetailTransferStockMPService.
				 * get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).
				 * getQuantiteOffre()));
				 * quantiteTotalOffreService=quantiteTotalOffreService.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).
				 * getQuantiteOffre().setScale(0, RoundingMode.CEILING)); }
				 * 
				 * 
				 * }else {
				 * montantService=montantService.add(listDetailTransferStockMPService.get(k).
				 * getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).
				 * getQuantite())); quantiteTotalService=quantiteTotalService.add(
				 * listDetailTransferStockMPService.get(k).getQuantite());
				 * 
				 * montantDechetService=montantDechetService.add(
				 * listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(
				 * listDetailTransferStockMPService.get(k).getQuantiteDechet()));
				 * quantiteTotalDechetService=quantiteTotalDechetService.add(
				 * listDetailTransferStockMPService.get(k).getQuantiteDechet());
				 * if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null) {
				 * montantOffreService=montantOffreService.add(listDetailTransferStockMPService.
				 * get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).
				 * getQuantiteOffre()));
				 * quantiteTotalOffreService=quantiteTotalOffreService.add(
				 * listDetailTransferStockMPService.get(k).getQuantiteOffre());
				 * 
				 * }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if(!montantService.equals(BigDecimal.ZERO) &&
				 * !quantiteTotalService.equals(BigDecimal.ZERO)) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier())) {
				 * 
				 * 
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * 
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) { etatstockmp.setQuantiteService
				 * (etatstockmp.getQuantiteService().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalService.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setPrixService(montantService.divide(quantiteTotalService.
				 * setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
				 * etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(
				 * etatstockmp.getPrixService()));
				 * 
				 * 
				 * etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().
				 * setScale(0, RoundingMode.CEILING).add(quantiteTotalDechetService.setScale(0,
				 * RoundingMode.CEILING)));
				 * if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).
				 * setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) { etatstockmp.setPrixDechetService(new
				 * BigDecimal(0));
				 * 
				 * }else { etatstockmp.setPrixDechetService(montantDechetService.divide(
				 * quantiteTotalDechetService.setScale(0,
				 * RoundingMode.CEILING),6,RoundingMode.HALF_UP));
				 * 
				 * } etatstockmp.setMontantDechetService
				 * (etatstockmp.getQuantiteDechetService().multiply(etatstockmp.
				 * getPrixDechetService()));
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }else { etatstockmp.setQuantiteService
				 * (etatstockmp.getQuantiteService().add(quantiteTotalService));
				 * etatstockmp.setPrixService
				 * (montantService.divide(quantiteTotalService,6,RoundingMode.HALF_UP));
				 * etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(
				 * etatstockmp.getPrixService()));
				 * 
				 * 
				 * etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().
				 * add(quantiteTotalDechetService));
				 * if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).
				 * setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) { etatstockmp.setPrixDechetService(new
				 * BigDecimal(0));
				 * 
				 * }else { etatstockmp.setPrixDechetService(montantDechetService.divide(
				 * quantiteTotalDechetService,6,RoundingMode.HALF_UP));
				 * 
				 * } etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().
				 * multiply(etatstockmp.getPrixDechetService()));
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * } // Ajouter la Quantite , prix et monatant Offre service
				 * if(!quantiteTotalOffreService.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier())) {
				 * 
				 * 
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * 
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().
				 * setScale(0, RoundingMode.CEILING).add(quantiteTotalOffreService.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setPrixOffreService(montantOffreService.divide(
				 * quantiteTotalOffreService.setScale(0,
				 * RoundingMode.CEILING),6,RoundingMode.HALF_UP));
				 * etatstockmp.setMontantOffreService
				 * (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.
				 * getPrixOffreService()));
				 * 
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }else {
				 * etatstockmp.setQuantiteOffreService
				 * (etatstockmp.getQuantiteOffreService().add(quantiteTotalOffreService));
				 * etatstockmp.setPrixOffreService
				 * (montantOffreService.divide(quantiteTotalOffreService,6,RoundingMode.HALF_UP)
				 * ); etatstockmp.setMontantOffreService
				 * (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.
				 * getPrixOffreService()));
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * // calculer calculer le prix moyen et quantite TransferMPPF
				 * 
				 * 
				 * for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMP.size();j++) {
				 * montantTransferMPPF=new BigDecimal(0); quantiteTotalTransferMPPF=new
				 * BigDecimal(0); quantiteOffreaSupprimer=new BigDecimal(0);
				 * quantiteDechetaSupprimer=new BigDecimal(0);
				 * 
				 * 
				 * for(int k=0;k<listDetailTransferStockMPTransferMPPF.size();k++) {
				 * 
				 * if(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier()
				 * .equals(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier())) {
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON)
				 * || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON)
				 * || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.
				 * CODE_STICKERS) ||
				 * listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.
				 * CODE_THERRES_VERRES) ) { montantTransferMPPF=montantTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(
				 * listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0,
				 * RoundingMode.CEILING)));
				 * quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).
				 * getQuantite().setScale(0, RoundingMode.CEILING));
				 * 
				 * 
				 * // calculer la somme de quantite dechet et offre a supprimer
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource()!=null) {
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(
				 * Constantes.MP_STOCK_DECHET)) {
				 * quantiteDechetaSupprimer=quantiteDechetaSupprimer.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).
				 * getQuantite().setScale(0, RoundingMode.CEILING)); }else
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(
				 * Constantes.MP_STOCK_OFFRE)) {
				 * quantiteOffreaSupprimer=quantiteOffreaSupprimer.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).
				 * getQuantite().setScale(0, RoundingMode.CEILING)) ; }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * /////////////////////////////////////////////////////////////////////////////
				 * /////
				 * 
				 * 
				 * }else { montantTransferMPPF=montantTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(
				 * listDetailTransferStockMPTransferMPPF.get(k).getQuantite()));
				 * quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
				 * 
				 * // calculer la somme de quantite dechet et offre a supprimer
				 * 
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource()!=null) {
				 * 
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(
				 * Constantes.MP_STOCK_DECHET)) {
				 * quantiteDechetaSupprimer=quantiteDechetaSupprimer.add(
				 * listDetailTransferStockMPTransferMPPF.get(k).getQuantite()); }else
				 * if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(
				 * Constantes.MP_STOCK_OFFRE)) {
				 * quantiteOffreaSupprimer=quantiteOffreaSupprimer.add(
				 * listDetailTransferStockMPTransferMPPF.get(k).getQuantite()) ; }
				 * 
				 * }
				 * 
				 * /////////////////////////////////////////////////////////////////////////////
				 * /////
				 * 
				 * }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if(!montantTransferMPPF.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setQuantite(
				 * quantiteTotalTransferMPPF);
				 * listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setPrixUnitaire(
				 * montantTransferMPPF.divide(quantiteTotalTransferMPPF,6,RoundingMode.HALF_UP))
				 * ;
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier()))
				 * { EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(
				 * montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING)),6,RoundingMode.HALF_UP));
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING)) );
				 * etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie())); // supprimer la quantite dechet et quantite
				 * offre
				 * etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().setScale(0,
				 * RoundingMode.CEILING).subtract(quantiteDechetaSupprimer.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(
				 * etatstockmp.getPrixDechet()));
				 * 
				 * etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction
				 * ().setScale(0,
				 * RoundingMode.CEILING).subtract(quantiteOffreaSupprimer.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction(
				 * ).multiply(etatstockmp.getPrixOffreProduction()));
				 * 
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }else { etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.
				 * getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalTransferMPPF));
				 * etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie())); // supprimer la quantite dechet et quantite
				 * offre etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().subtract(
				 * quantiteDechetaSupprimer));
				 * etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(
				 * etatstockmp.getPrixDechet()));
				 * 
				 * etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction
				 * ().setScale(6,
				 * RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimer.setScale(6,
				 * RoundingMode.HALF_UP)));
				 * etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction(
				 * ).multiply(etatstockmp.getPrixOffreProduction()));
				 * 
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * // calculer calculer le prix moyen et quantite TransferMPPFSERVICE
				 * 
				 * 
				 * for(int
				 * j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++) {
				 * montantTransferMPPF=new BigDecimal(0); quantiteTotalTransferMPPF=new
				 * BigDecimal(0); quantiteOffreaSupprimerService=new BigDecimal(0);
				 * quantiteDechetaSupprimerService=new BigDecimal(0);
				 * 
				 * 
				 * for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++) {
				 * 
				 * if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).
				 * getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k
				 * ).getMatierePremier())) {
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||
				 * listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON)
				 * || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON)
				 * || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.
				 * CODE_STICKERS) ||
				 * listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().
				 * getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.
				 * CODE_THERRES_VERRES) ) { montantTransferMPPF=montantTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().
				 * multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().
				 * setScale(0, RoundingMode.CEILING)));
				 * quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k)
				 * .getQuantite().setScale(0, RoundingMode.CEILING));
				 * 
				 * 
				 * // calculer la somme de quantite dechet et offre a supprimer
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=
				 * null) {
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().
				 * equals(Constantes.MP_STOCK_DECHET)) {
				 * quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k)
				 * .getQuantite().setScale(0, RoundingMode.CEILING)); }else
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().
				 * equals(Constantes.MP_STOCK_OFFRE)) {
				 * quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0,
				 * RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k)
				 * .getQuantite().setScale(0, RoundingMode.CEILING)) ; }
				 * 
				 * }
				 * /////////////////////////////////////////////////////////////////////////////
				 * /////
				 * 
				 * 
				 * }else { montantTransferMPPF=montantTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().
				 * multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
				 * quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(
				 * listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
				 * 
				 * // calculer la somme de quantite dechet et offre a supprimer
				 * 
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=
				 * null) {
				 * 
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().
				 * equals(Constantes.MP_STOCK_DECHET)) {
				 * quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(
				 * listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()); }else
				 * if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().
				 * equals(Constantes.MP_STOCK_OFFRE)) {
				 * quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(
				 * listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()) ; }
				 * 
				 * }
				 * 
				 * /////////////////////////////////////////////////////////////////////////////
				 * /////
				 * 
				 * }
				 * 
				 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
				 * getMatierePremier().getNom() +
				 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
				 * 
				 * }
				 * 
				 * 
				 * } if(!montantTransferMPPF.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6,
				 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
				 * RoundingMode.HALF_UP))) {
				 * 
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(
				 * quantiteTotalachat);
				 * listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.
				 * divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * if(listEtatStockMP.get(i).getMp().equals(
				 * listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).
				 * getMatierePremier())) { EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * 
				 * etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(
				 * montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING)),6,RoundingMode.HALF_UP));
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0,
				 * RoundingMode.CEILING)) );
				 * etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie())); // supprimer la quantite dechet et quantite
				 * offre
				 * etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().
				 * setScale(0,
				 * RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().
				 * multiply(etatstockmp.getPrixDechetService()));
				 * 
				 * etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().
				 * setScale(0,
				 * RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0,
				 * RoundingMode.CEILING)));
				 * etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().
				 * multiply(etatstockmp.getPrixOffreService()));
				 * 
				 * 
				 * 
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }else {
				 * 
				 * 
				 * 
				 * etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.
				 * getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));
				 * 
				 * etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(
				 * quantiteTotalTransferMPPF));
				 * 
				 * etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(
				 * etatstockmp.getPrixSortie())); // supprimer la quantite dechet et quantite
				 * offre
				 * etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().
				 * subtract(quantiteDechetaSupprimerService));
				 * etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().
				 * multiply(etatstockmp.getPrixDechetService()));
				 * 
				 * etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().
				 * setScale(6,
				 * RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6,
				 * RoundingMode.HALF_UP)));
				 * etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().
				 * multiply(etatstockmp.getPrixOffreService()));
				 * 
				 * listEtatStockMP.set(i, etatstockmp); }
				 * 
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * // calculer le stock finale et le prix moyen finale
				 * 
				 * 
				 * for(int i=0;i<listEtatStockMP.size();i++) {
				 * 
				 * 
				 * 
				 * EtatStockMP etatstockmp=listEtatStockMP.get(i);
				 * if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_BOX) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_CARTON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_TAMPON) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_STICKERS) ||
				 * etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(
				 * Constantes.CODE_THERRES_VERRES) ) {
				 * etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteReception().setScale(0,
				 * RoundingMode.CEILING).add(etatstockmp.getQuantiteEntrer().setScale(0,
				 * RoundingMode.CEILING)).add(etatstockmp.getQuantiteRetour().setScale(0,
				 * RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0,
				 * RoundingMode.CEILING)));
				 * 
				 * }else {
				 * etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteReception().add(
				 * etatstockmp.getQuantiteEntrer()).add(etatstockmp.getQuantiteRetour())).
				 * subtract(etatstockmp.getQuantiteSortie()));
				 * 
				 * 
				 * }
				 * 
				 * listEtatStockMP.set(i, etatstockmp);
				 * 
				 * }
				 * 
				 * 
				 * afficher_tableEtatStock(listEtatStockMP); } else {
				 * JOptionPane.showMessageDialog(null,
				 * "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
				 * return; }
				 * 
				 * 
				 */}
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
 		
 		 btnExporterExcel = new JButton("Exporter Excel");
 		btnExporterExcel.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				

  				
  				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			if(tableEtatStock.getRowCount()!=0)
	    			{
	    				
	    				String titre="Etat Stock MP "+magasin.getLibelle();
			    		String titrefeuilleexcel="Etat Stock MP ";
			    		ExporterTableVersExcel.tabletoexcelEtatStockMP (tableEtatStock, titre,titrefeuilleexcel);
	    				
	    				
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
				new String[] { "Code","Matiere Premiere","INITIAL", "RECEPTION","Transfert Entre", "Transfert Sortie", "Q.Charge","Charge Supp",
						 "Retour Machine","Autre Sortie", "Quantite Finale" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false,false, false ,false };

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
		
		
		
		
		while (i < listEtatStockMP.size()) {
			EtatStockMP EtatStockMP = listEtatStockMP.get(i);
			
			Object[] ligne = {EtatStockMP.getMp().getCode(), EtatStockMP.getMp().getNom(),
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
}

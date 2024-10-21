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
import util.DateUtils;
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
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.SequenceurDAO;
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
import dao.entity.Sequenceur;
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

public class ConsulterMarchandiseDeplacerEnAttente extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private static DefaultTableModel modeleEtatStock;
	private static DefaultTableModel modeleDetailEtatStock;

	private static JXTable tableEtatStock = new JXTable();
	private   JXTable tableDetailEtatStock = new JXTable();
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
	private static List<MarchandiseDeplacer> listMarchandiseDeplacerAfficher = new ArrayList<MarchandiseDeplacer>();
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
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerFrais = new ArrayList<DetailMarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerPaie = new ArrayList<DetailMarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerProduction = new ArrayList<DetailMarchandiseDeplacer>();

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
	private TransferStockMPDAO transferStockMPDAO;
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
	private JTable table;
	JButton btnBonFrais = new JButton("Bon Frais");
	JButton btnBonPaie = new JButton("Bon Paie");
	JButton btnBonProduction = new JButton("Bon Production");
	private SequenceurDAO sequenceurDAO;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public ConsulterMarchandiseDeplacerEnAttente() {
		 
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
        	 sequenceurDAO=new SequenceurDAOImpl();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		tableEtatStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tableEtatStock.getSelectedRow()!=-1)
				{
					
					
					btnBonFrais.setEnabled(false);
					btnBonPaie.setEnabled(false);
					btnBonProduction.setEnabled(false);
					
listDetailMarchandiseDeplacer.clear();
					
					 
					
					 TransferStockMP transferStockMPSortieEnAttente=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), Constantes.ETAT_SORTIE_ENATTENT);
					 TransferStockMP transferStockMPSortie=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
					 TransferStockMP transferStockMPSortieAnnuler=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER);

						if(transferStockMPSortieEnAttente!=null || transferStockMPSortie!=null || transferStockMPSortieAnnuler!=null)
						{
							 
							List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
							
						if(transferStockMPSortieEnAttente!=null)
						{
							listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortieEnAttente.getId());
							if(listDetailTransferStockMPSortie.size()!=0)
							{
								for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
								{
									DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
									DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
									detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
									if(detailTransferStockMP.getFournisseur()!=null)
									{
										detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
										
									}
									detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
									detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
									
									detailmarchandiseDeplacer.setMontant(BigDecimal.ZERO);
									listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
									
								}
								
								 
							}
							
							
						}else if(transferStockMPSortie!=null)
						{
							listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortie.getId());
							if(listDetailTransferStockMPSortie.size()!=0)
							{
								for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
								{
									DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
									DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
									detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
									if(detailTransferStockMP.getFournisseur()!=null)
									{
										detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
										
									}
									detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
									detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
								 
									listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
									
									
								}
							}
							
						}else if(transferStockMPSortieAnnuler!=null)
						{
							listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortieAnnuler.getId());
							
							if(listDetailTransferStockMPSortie.size()!=0)
							{
								for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
								{
									DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
									DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
									detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
									if(detailTransferStockMP.getFournisseur()!=null)
									{
										detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
										
									}
									detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
									detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
									detailmarchandiseDeplacer.setAction(detailTransferStockMP.getAction());
									detailmarchandiseDeplacer.setService(detailTransferStockMP.getService());
									detailmarchandiseDeplacer.setCommentaire(detailTransferStockMP.getCommentaire());
									detailmarchandiseDeplacer.setMontant(BigDecimal.ZERO);
									detailmarchandiseDeplacer.setDetailTransferStockMP(detailTransferStockMP);
									listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
								}
							}
							
							
						}
							
							 
									
							 
						
							
						}
					
					
					
					 TransferStockMP transferStockMPEntrerEnAttente=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
					 TransferStockMP transferStockMPEntrer=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), Constantes.ETAT_TRANSFER_STOCK_ENTRE);
					 TransferStockMP transferStockMPAnnuler=transferStockMPDAO.findTransferByCodeStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);

					 
				if(transferStockMPEntrerEnAttente!=null || transferStockMPEntrer!=null || transferStockMPAnnuler!=null)
				{
					 
					List<DetailTransferStockMP> listDetailTransferStockMPEntrer = new ArrayList<DetailTransferStockMP>();
					
				if(transferStockMPEntrerEnAttente!=null)
				{
					listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrerEnAttente.getId());
					if(listDetailTransferStockMPEntrer.size()!=0)
					{
						
						for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
						{
							
							DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
							
						 
							
							for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
							{
								DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
								
								if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
								{
									
									if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										
										if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
										{
											
											detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
											detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
											
										}
										
										
									}else
									{
										
										detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
										detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
										
									}
									
									
									
									
								}
								
								
								
							}
							
							listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
							
						}
						
						
						
						 
					}
					
					
				}else if(transferStockMPEntrer!=null)
				{
					listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrer.getId());
					if(listDetailTransferStockMPEntrer.size()!=0)
					{
						

						
						for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
						{
							
							DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
							
						 
							
							for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
							{
								DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
								
								if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
								{
									
									if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										
										if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
										{
											
											detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
											detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
											detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
											detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
											detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
											detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
											detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
											
											
										}
										
										
									}else
									{
										
										detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
										detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
										detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
										detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
										detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
										detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
										detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
									}
									
									
									
									
								}
								
								
								
							}
							
							listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
							
						}
						
						
						
						 
					
						
						
						 
					}
					
				}else if(transferStockMPAnnuler!=null)
				{
					listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPAnnuler.getId());
					
					if(listDetailTransferStockMPEntrer.size()!=0)
					{
						

						

						
						for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
						{
							
							DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
							
						 
							
							for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
							{
								DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
								
								if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
								{
									
									if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										
										if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
										{
											
											detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
											detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
											detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
											detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
											detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
											detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
											detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
										}
										
										
									}else
									{
										
										detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
										detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
										detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
										detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
										detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
										detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
										detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
										
									}
									
									
									
									
								}
								
								
								
							}
							
							listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
							
						}
						
						
						
						 
					
						
						
						 
					
						
						 
					}
					
					
				}
					
					
					
					
				} 
					 
					
				
				listDetailMarchandiseDeplacerImprimerFrais.clear();
				listDetailMarchandiseDeplacerImprimerPaie.clear();
				listDetailMarchandiseDeplacerImprimerProduction.clear();
				
				
				
				
				for(int t=0;t<listDetailMarchandiseDeplacer.size();t++)
				{
					 
					
					DetailTransferStockMP detailTransferStockMP=listDetailMarchandiseDeplacer.get(t).getDetailTransferStockMP();
					
			 
				 
					 
					
					
					if(detailTransferStockMP.getAction()!=null)
					{
						
						if(detailTransferStockMP.getAction().equals(Constantes.ACTION_PERTE_DE_QUANTITE))
						{
							if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

							}else
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

							}
							
							listDetailMarchandiseDeplacerImprimerFrais.add(listDetailMarchandiseDeplacer.get(t));
						}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_QUANTITE_A_STOCKER))
						{
							if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

							}else
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

							}
							
							listDetailMarchandiseDeplacerImprimerProduction.add(listDetailMarchandiseDeplacer.get(t));
						}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR))
						{
							if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

							}else
							{
								listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

								
							}
							
							listDetailMarchandiseDeplacerImprimerPaie.add(listDetailMarchandiseDeplacer.get(t));
						}
					}
					
					 
					
				}
				
				
				
				if(listDetailMarchandiseDeplacerImprimerFrais.size()!=0)
				{
					btnBonFrais.setEnabled(true);
					 
				}
				
				if(listDetailMarchandiseDeplacerImprimerPaie.size()!=0)
				{
					 
					btnBonPaie.setEnabled(true);
					 
				}
				
				if(listDetailMarchandiseDeplacerImprimerProduction.size()!=0)
				{
					 
					btnBonProduction.setEnabled(true);
				}
				
				
				
					
					
				afficher_DetailtableEtatStock(listDetailMarchandiseDeplacer);
					
					
					
				}
				
}
		});

		tableEtatStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {   "Code Transfert","Date Transfert","Depot Source","Magasin Source", "Date Transfert Entrer","Depot Destination","Magasin Destination","Etat" }));
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
		scrollPane.setBounds(10, 250, 1465, 396);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Marchandises Deplacer En Attente");
		titledSeparator.setBounds(10, 209, 1465, 30);
		add(titledSeparator);

		JLabel lblConslterLesFactures = new JLabel("          Consulter D\u00E9placement MP Ecart");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(277, 11, 1082, 35);
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
		btnAfficher.setBounds(590, 174, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 57, 1383, 106);
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

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(724, 174, 107, 24);
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
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(20, 674, 1465, 260);
		add(scrollPane_1);
		
		tableDetailEtatStock = new JXTable();
		tableDetailEtatStock.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code MP", "MP", "Fournisseur", "Quantite Sortie", "Quantite Entrer","Quantite Entrer","Action","Quantite Ecart","Montant Ecart",   "Service", "Commentaire"
			}
		));
		tableDetailEtatStock.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableDetailEtatStock.getColumnModel().getColumn(4).setPreferredWidth(103);
		tableDetailEtatStock.getColumnModel().getColumn(5).setPreferredWidth(89);
		tableDetailEtatStock.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableDetailEtatStock);
		
		  btnBonFrais = new JButton("Bon Frais");
		  btnBonFrais.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				
				///////////////////////////////////////////////////////////////////////////// BON FRAIS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
								
								
								 String date =""; 
								 String NumBon="";
								
								
								
								if(listDetailMarchandiseDeplacerImprimerFrais.size()!=0)
								{
									
									date= dcn.format(listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
									
									
								 
									
									
									  String code="PROD "; 
										 
									  
									  
									
									
									
									
									Map parameters = new HashMap();
									parameters.put("servicetitre","BON FRAIS" );
									
									parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
								
									
									if(listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonFrais()!=null)
									{
										if(!listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonFrais().equals(""))
										{
											parameters.put("bonnum",listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonFrais() );
										}else
										{
											

											Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
											  if(sequenceur!=null) { 
												  
												  
												  
												  int  valeur=sequenceur.getValeur()+1;
												  if(valeur<100 && valeur>=10) {
											  code=code+"0"+valeur+"-"+date; 
											  }else if(valeur<10) 
											  { code=code+"00"+valeur+"/"+date;
											  }else if(valeur>=100) 
											  { code=code+valeur+"-"+date; } 
												 
												  sequenceur.setValeur(valeur);
												  sequenceurDAO.edit(sequenceur);
											  
											  }else
												  {
													  
														Sequenceur sequenceurTmp=new Sequenceur();
														
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_BON);
														sequenceurTmp.setValeur(1);
														
														sequenceurDAO.add(sequenceurTmp);
											  
											  code=code+"00"+1+"-"+date;
											  
											 
											  
											  } 
											
											  parameters.put("bonnum",code );
											
										}
										
										
										
									}else
									{
										Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
										  if(sequenceur!=null) { 
											  
											  
											  
											  int  valeur=sequenceur.getValeur()+1;
											  if(valeur<100 && valeur>=10) {
										  code=code+"0"+valeur+"-"+date; 
										  }else if(valeur<10) 
										  { code=code+"00"+valeur+"/"+date;
										  }else if(valeur>=100) 
										  { code=code+valeur+"-"+date; } 
											 
											  sequenceur.setValeur(valeur);
											  sequenceurDAO.edit(sequenceur);
										  
										  }else
											  {
												  
													Sequenceur sequenceurTmp=new Sequenceur();
													
													sequenceurTmp.setCode(date);
													sequenceurTmp.setLibelle(Constantes.CODE_BON);
													sequenceurTmp.setValeur(1);
													
													sequenceurDAO.add(sequenceurTmp);
										  
										  code=code+"00"+1+"-"+date;
										  
										  
										  
										  } 
										  parameters.put("bonnum",code );
									}
									
									parameters.put("date", listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
									parameters.put("depot", listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
									parameters.put("magasin",listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
									
									JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerFrais,parameters);	
							
									
									
									
									
									
									
								}
		  		
		  		
		  		
		  	}
		  });
		btnBonFrais.setIcon(new ImageIcon(ConsulterMarchandiseDeplacerEnAttente.class.getResource("/img/imprimer.png")));
		btnBonFrais.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBonFrais.setBounds(45, 955, 152, 38);
		add(btnBonFrais);
		
		  btnBonPaie = new JButton("Bon Paie");
		  btnBonPaie.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
				///////////////////////////////////////////////////////////////////////////// BON PAIE /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
		  		SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				
		  		String date =""; 
		  		String  NumBon="";
				
				
				
				if(listDetailMarchandiseDeplacerImprimerPaie.size()!=0)
				{
					
					date= dcn.format(listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
					
					
				 
					
					
					  String code="PROD "; 
						 
					  
				
					
					
					
					
					Map parameters = new HashMap();
					parameters.put("servicetitre","BON PAIE" );
					
					if(listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonPaie()!=null)
					{
						
						if(!listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonPaie().equals(""))
						{
							
							 parameters.put("bonnum",listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonPaie() );
							
							
							
						}else
						{

							  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
							  if(sequenceur!=null) { 
								  int  valeur=sequenceur.getValeur()+1;
								  if(valeur<100 && valeur>=10) {
							  code=code+"0"+valeur+"-"+date; 
							  }else if(valeur<10) 
							  { code=code+"00"+valeur+"/"+date;
							  }else if(valeur>=100) 
							  { code=code+valeur+"-"+date; } 
								  
								  sequenceur.setValeur(valeur);
								  sequenceurDAO.edit(sequenceur);
							  
							  
							  }else
								  {
									  
										Sequenceur sequenceurTmp=new Sequenceur();
										
										sequenceurTmp.setCode(date);
										sequenceurTmp.setLibelle(Constantes.CODE_BON);
										sequenceurTmp.setValeur(1);
										
										sequenceurDAO.add(sequenceurTmp);
							  
							  code=code+"00"+1+"-"+date;
							  
							  
							  } 
							
							  parameters.put("bonnum",code );
						
						}
						
						
						
					}else
					{
						  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
						  if(sequenceur!=null) { 
							  int  valeur=sequenceur.getValeur()+1;
							  if(valeur<100 && valeur>=10) {
						  code=code+"0"+valeur+"-"+date; 
						  }else if(valeur<10) 
						  { code=code+"00"+valeur+"/"+date;
						  }else if(valeur>=100) 
						  { code=code+valeur+"-"+date; } 
							  
							  sequenceur.setValeur(valeur);
							  sequenceurDAO.edit(sequenceur);
						  
						  
						  }else
							  {
								  
									Sequenceur sequenceurTmp=new Sequenceur();
									
									sequenceurTmp.setCode(date);
									sequenceurTmp.setLibelle(Constantes.CODE_BON);
									sequenceurTmp.setValeur(1);
									
									sequenceurDAO.add(sequenceurTmp);
						  
						  code=code+"00"+1+"-"+date;
						  
						  
						  } 
						
						  parameters.put("bonnum",code );
					}
					
					
					parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
					parameters.put("date", listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
					parameters.put("depot", listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
					parameters.put("magasin",listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
					
					JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerPaie,parameters);	
			
					
					
					
					
					
					
				}
		  		
		  	}
		  });
		btnBonPaie.setIcon(new ImageIcon(ConsulterMarchandiseDeplacerEnAttente.class.getResource("/img/imprimer.png")));
		btnBonPaie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBonPaie.setBounds(207, 955, 152, 38);
		add(btnBonPaie);
		
		  btnBonProduction = new JButton("Bon Production");
		  btnBonProduction.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
				///////////////////////////////////////////////////////////////////////////// BON PRODUCTION /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
		  		SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				
		  		String date =""; 
		  		String  NumBon="";
				
				
				
				if(listDetailMarchandiseDeplacerImprimerProduction.size()!=0)
				{
					
					date= dcn.format(listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
					
					
				 
					
					
					  String code="PROD "; 
						 
					  
					  
					
					
					
					
					Map parameters = new HashMap();
					parameters.put("servicetitre","BON PRODUCTION" );
					
					if(listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonProduction()!=null)
					{
						
						if(!listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonProduction().equals(""))
						{
							parameters.put("bonnum",listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getNumbonProduction() );
							
							
						}else
						{

							Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
							  if(sequenceur!=null) { 
								  int  valeur=sequenceur.getValeur()+1;
								  if(valeur<100 && valeur>=10) {
							  code=code+"0"+valeur+"-"+date; 
							  }else if(valeur<10) 
							  { code=code+"00"+valeur+"/"+date;
							  }else if(valeur>=100) 
							  { code=code+valeur+"-"+date; } 
								  
								  sequenceur.setValeur(valeur);
								  sequenceurDAO.edit(sequenceur); 
								  
								  
								  }else
								  {
									  
										Sequenceur sequenceurTmp=new Sequenceur();
										
										sequenceurTmp.setCode(date);
										sequenceurTmp.setLibelle(Constantes.CODE_BON);
										sequenceurTmp.setValeur(1);
										
										sequenceurDAO.add(sequenceurTmp);
							  
							  code=code+"00"+1+"-"+date;
							  
							  } 
							  
								parameters.put("bonnum",code );
							
						
							
						}
						
						
						
						
					}else
					{
						Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
						  if(sequenceur!=null) { 
							  int  valeur=sequenceur.getValeur()+1;
							  if(valeur<100 && valeur>=10) {
						  code=code+"0"+valeur+"-"+date; 
						  }else if(valeur<10) 
						  { code=code+"00"+valeur+"/"+date;
						  }else if(valeur>=100) 
						  { code=code+valeur+"-"+date; } 
							  
							  sequenceur.setValeur(valeur);
							  sequenceurDAO.edit(sequenceur); 
							  
							  
							  }else
							  {
								  
									Sequenceur sequenceurTmp=new Sequenceur();
									
									sequenceurTmp.setCode(date);
									sequenceurTmp.setLibelle(Constantes.CODE_BON);
									sequenceurTmp.setValeur(1);
									
									sequenceurDAO.add(sequenceurTmp);
						  
						  code=code+"00"+1+"-"+date;
						  
						  } 
						  
							parameters.put("bonnum",code );
						
					}
					
				
					parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
					parameters.put("date", listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
					parameters.put("depot", listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
					parameters.put("magasin",listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
					
					JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerProduction,parameters);	
			
					
					
					
					
					
					
				}
		  		
		  		
		  	}
		  });
		btnBonProduction.setIcon(new ImageIcon(ConsulterMarchandiseDeplacerEnAttente.class.getResource("/img/imprimer.png")));
		btnBonProduction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBonProduction.setBounds(369, 956, 152, 37);
		add(btnBonProduction);
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

	static void afficher_tableEtatStock(List<MarchandiseDeplacer> listMarchandise) {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {   "Code Transfert","Date Transfert","Depot Source","Magasin Source", "Date Transfert Entrer","Depot Destination","Magasin Destination","Etat" }) {
			boolean[] columnEditables = new boolean[] {  false, false, false, false, false , false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		int i = 0;

		while (i < listMarchandise.size()) {
			
			MarchandiseDeplacer marchandiseDeplacer = listMarchandise.get(i);

	       Object[] ligne = {marchandiseDeplacer.getCodeTransfert(),marchandiseDeplacer.getDateSortie(),marchandiseDeplacer.getMagasinSouce().getDepot().getLibelle(),marchandiseDeplacer.getMagasinSouce().getLibelle(),marchandiseDeplacer.getDateEntrer(), marchandiseDeplacer.getMagasinDestination().getDepot().getLibelle(),marchandiseDeplacer.getMagasinDestination().getLibelle(),marchandiseDeplacer.getEtat() };
	

	      modeleEtatStock.addRow(ligne);

		

			i++;
		}
	}
	
	
	  void InitialiseDetailtableEtatStock( ) {
		modeleDetailEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {"Code MP", "MP", "Fournisseur", "Quantite Sortie", "Quantite Entrer", "Action","Quantite Ecart","Montant Ecart", "Service", "Commentaire"}) {
			boolean[] columnEditables = new boolean[] {  false, false, false, false, false , false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableDetailEtatStock.setModel(modeleDetailEtatStock);
		 
		 
	}
	  
	  void afficher_DetailtableEtatStock(List<DetailMarchandiseDeplacer> listDetailMarchandise) {
		  InitialiseDetailtableEtatStock();
			int i = 0;
String fournisseur="";
BigDecimal QuantiteEntrer=BigDecimal.ZERO;
String action="";
String service="";
String commentaire="";


			while (i < listDetailMarchandise.size()) {
				fournisseur="";
				QuantiteEntrer=BigDecimal.ZERO;
				  action="";
				  service="";
				  commentaire="";
				DetailMarchandiseDeplacer DetailmarchandiseDeplacer = listDetailMarchandise.get(i);
if(DetailmarchandiseDeplacer.getFournisseur()!=null)
{
	fournisseur=DetailmarchandiseDeplacer.getFournisseur().getCodeFournisseur();	
}

if(DetailmarchandiseDeplacer.getQuantiteEntrer()!=null)
{
	QuantiteEntrer=DetailmarchandiseDeplacer.getQuantiteEntrer();	
}
if(DetailmarchandiseDeplacer.getAction()!=null)
{
	action=DetailmarchandiseDeplacer.getAction();	
}

if(DetailmarchandiseDeplacer.getService()!=null)
{
	service=DetailmarchandiseDeplacer.getService();	
}

if(DetailmarchandiseDeplacer.getCommentaire()!=null)
{
	commentaire=DetailmarchandiseDeplacer.getCommentaire();	
}

		       Object[] ligne = {DetailmarchandiseDeplacer.getMatierePremier().getCode(),DetailmarchandiseDeplacer.getMatierePremier().getNom(),fournisseur,DetailmarchandiseDeplacer.getQuantiteSortie(),QuantiteEntrer, action,DetailmarchandiseDeplacer.getQuantiteManque(),DetailmarchandiseDeplacer.getMontant(),service,commentaire};
		

		      modeleDetailEtatStock.addRow(ligne);

			

				i++;
			}
		}
	
	
	public  void Consulter()
	{
		
		 
		
		
		
		
		listDetailMarchandiseDeplacer.clear();
		afficher_DetailtableEtatStock(listDetailMarchandiseDeplacer);
		
		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && combodepotSource.getSelectedItem().equals("") && combomagasinSource.getSelectedItem().equals("") && combodepotDestination.getSelectedItem().equals("") && combomagasinDestination.getSelectedItem().equals("") && txtCodTransfert.getText().equals("") && comboetat.getSelectedItem().equals(""))
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
			
			listDetailTransferStockMP=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
		
			
			req="";
			
			if(comboetat.getSelectedItem().equals(""))
			{
				
				
				req=req+" where  d.transferStockMP.statut in ('"+Constantes.ETAT_SORTIE_ENATTENT+"','"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"','"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER+"')  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.etat in ('"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"','"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER +"', '"+Constantes.ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER+"')) ";
				
			}else
			{
				
				if(comboetat.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT) )	
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
			
			listDetailTransferStockMPSortie=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
			
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
					 
					
					existe=true;
					dateSortie=detailTransferStockMPTmp.getTransferStockMP().getDateTransfer();
					magasinSource=detailTransferStockMPTmp.getMagasinSouce();
				}
					
					
					
				}
				
				if(existe==true)
				{
					
					MarchandiseDeplacer marchandiseDeplacer=new MarchandiseDeplacer();
					 TransferStockMP transferStockMPEntrerEnAttente=transferStockMPDAO.findTransferByCodeStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
					 TransferStockMP transferStockMPEntrer=transferStockMPDAO.findTransferByCodeStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_ENTRE);
					 TransferStockMP transferStockMPAnnuler=transferStockMPDAO.findTransferByCodeStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);

					 
				if(transferStockMPEntrerEnAttente!=null || transferStockMPEntrer!=null || transferStockMPAnnuler!=null)
				{
					List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
					
					
				if(transferStockMPEntrerEnAttente!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrerEnAttente.getId());
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						marchandiseDeplacer.setMagasinDestination(listDetailTransferStockMPSortie.get(0).getMagasinDestination());
					}
					
					
				}else if(transferStockMPEntrer!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrer.getId());
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						marchandiseDeplacer.setMagasinDestination(listDetailTransferStockMPSortie.get(0).getMagasinDestination());
					}
					
				}else if(transferStockMPAnnuler!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPAnnuler.getId());
					
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						marchandiseDeplacer.setMagasinDestination(listDetailTransferStockMPSortie.get(0).getMagasinDestination());
					}
					
					
				}
					
					
					
					
				}else
				{
					marchandiseDeplacer.setMagasinDestination(detailTransferStockMP.getMagasinDestination());
				}
					 
					marchandiseDeplacer.setCodeTransfert(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
					
					marchandiseDeplacer.setMagasinSouce(magasinSource);
					marchandiseDeplacer.setTransferStockMP(detailTransferStockMP.getTransferStockMP());
					marchandiseDeplacer.setDateEntrer(detailTransferStockMP.getTransferStockMP().getDateTransfer());
					marchandiseDeplacer.setDateSortie(dateSortie);
					marchandiseDeplacer.setEtat(detailTransferStockMP.getTransferStockMP().getEtat());
					listMarchandiseDeplacer.add(marchandiseDeplacer);
					
					
				}
				
				
				
				
				
				
			}
			
			ChargerLesTransfertQuiContietEcart();
			
			afficher_tableEtatStock(listMarchandiseDeplacerAfficher);
			
			
		}
		
		
		
		
		
		
	}
	
	
	void ChargerLesTransfertQuiContietEcart()
	{
		

		
		
		 
		
boolean ecarteExiste=false;
		
		 for(int s=0;s<listMarchandiseDeplacer.size();s++)
		 {
			 ecarteExiste=false;
			 listDetailMarchandiseDeplacer.clear();
			 
			 TransferStockMP transferStockMPSortieEnAttente=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), Constantes.ETAT_SORTIE_ENATTENT);
			 TransferStockMP transferStockMPSortie=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			 TransferStockMP transferStockMPSortieAnnuler=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER);

				if(transferStockMPSortieEnAttente!=null || transferStockMPSortie!=null || transferStockMPSortieAnnuler!=null)
				{
					 
					List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
					
				if(transferStockMPSortieEnAttente!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortieEnAttente.getId());
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
						{
							DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
							DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
							detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
							if(detailTransferStockMP.getFournisseur()!=null)
							{
								detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
								
							}
							detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
							detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
							
							detailmarchandiseDeplacer.setMontant(BigDecimal.ZERO);
							listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
							
						}
						
						 
					}
					
					
				}else if(transferStockMPSortie!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortie.getId());
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
						{
							DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
							DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
							detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
							if(detailTransferStockMP.getFournisseur()!=null)
							{
								detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
								
							}
							detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
							detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
						 
							listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
							
							
						}
					}
					
				}else if(transferStockMPSortieAnnuler!=null)
				{
					listDetailTransferStockMPSortie=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPSortieAnnuler.getId());
					
					if(listDetailTransferStockMPSortie.size()!=0)
					{
						for(int i=0;i<listDetailTransferStockMPSortie.size();i++)
						{
							DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPSortie.get(i);
							DetailMarchandiseDeplacer detailmarchandiseDeplacer=new DetailMarchandiseDeplacer();
							detailmarchandiseDeplacer.setMatierePremier(detailTransferStockMP.getMatierePremier());
							if(detailTransferStockMP.getFournisseur()!=null)
							{
								detailmarchandiseDeplacer.setFournisseur(detailTransferStockMP.getFournisseur());
								
							}
							detailmarchandiseDeplacer.setQuantiteSortie(detailTransferStockMP.getQuantite());
							detailmarchandiseDeplacer.setMagasinSouce(detailTransferStockMP.getMagasinSouce());
							detailmarchandiseDeplacer.setAction(detailTransferStockMP.getAction());
							detailmarchandiseDeplacer.setService(detailTransferStockMP.getService());
							detailmarchandiseDeplacer.setCommentaire(detailTransferStockMP.getCommentaire());
							detailmarchandiseDeplacer.setMontant(BigDecimal.ZERO);
							detailmarchandiseDeplacer.setDetailTransferStockMP(detailTransferStockMP);
							listDetailMarchandiseDeplacer.add(detailmarchandiseDeplacer);
						}
					}
					
					
				}
					
					 
							
					 
				
					
				}
			
			
			
			 TransferStockMP transferStockMPEntrerEnAttente=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
			 TransferStockMP transferStockMPEntrer=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), Constantes.ETAT_TRANSFER_STOCK_ENTRE);
			 TransferStockMP transferStockMPAnnuler=transferStockMPDAO.findTransferByCodeStatut(listMarchandiseDeplacer.get(s).getCodeTransfert(), ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);

			 
		if(transferStockMPEntrerEnAttente!=null || transferStockMPEntrer!=null || transferStockMPAnnuler!=null)
		{
			 
			List<DetailTransferStockMP> listDetailTransferStockMPEntrer = new ArrayList<DetailTransferStockMP>();
			
		if(transferStockMPEntrerEnAttente!=null)
		{
			listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrerEnAttente.getId());
			if(listDetailTransferStockMPEntrer.size()!=0)
			{
				
				for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
				{
					
					DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
					
				 
					
					for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
					{
						DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
						
						if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
						{
							
							if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
								{
									
									detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
									detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
									
								}
								
								
							}else
							{
								
								detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
								detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
								
							}
							
							
							
							
						}
						
						
						
					}
					
					listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
					
				}
				
				
				
				 
			}
			
			
		}else if(transferStockMPEntrer!=null)
		{
			listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPEntrer.getId());
			if(listDetailTransferStockMPEntrer.size()!=0)
			{
				

				
				for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
				{
					
					DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
					
				 
					
					for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
					{
						DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
						
						if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
						{
							
							if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
								{
									
									detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
									detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
									detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
									detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
									detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
									detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
									detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
									
									
								}
								
								
							}else
							{
								
								detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
								detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
								detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
								detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
								detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
								detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
								detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
							}
							
							
							
							
						}
						
						
						
					}
					
					listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
					
				}
				
				
				
				 
			
				
				
				 
			}
			
		}else if(transferStockMPAnnuler!=null)
		{
			listDetailTransferStockMPEntrer=detailTransferStockMPDAO.findByTransferStockMP(transferStockMPAnnuler.getId());
			
			if(listDetailTransferStockMPEntrer.size()!=0)
			{
				

				

				
				for(int j=0;j<listDetailMarchandiseDeplacer.size();j++)
				{
					
					DetailMarchandiseDeplacer detailMarchandiseDeplacerTmp=listDetailMarchandiseDeplacer.get(j);
					
				 
					
					for(int i=0;i<listDetailTransferStockMPEntrer.size();i++)
					{
						DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPEntrer.get(i);
						
						if(detailTransferStockMP.getMatierePremier().getId()==detailMarchandiseDeplacerTmp.getMatierePremier().getId())
						{
							
							if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								if(detailTransferStockMP.getFournisseur().getId()==detailMarchandiseDeplacerTmp.getFournisseur().getId())
								{
									
									detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
									detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
									detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
									detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
									detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
									detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
									detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
								}
								
								
							}else
							{
								
								detailMarchandiseDeplacerTmp.setMagasinDestination(detailTransferStockMP.getMagasinDestination());	
								detailMarchandiseDeplacerTmp.setQuantiteEntrer(detailTransferStockMP.getQuantite());
								detailMarchandiseDeplacerTmp.setAction(detailTransferStockMP.getAction());
								detailMarchandiseDeplacerTmp.setService(detailTransferStockMP.getService());
								detailMarchandiseDeplacerTmp.setCommentaire(detailTransferStockMP.getCommentaire());
								detailMarchandiseDeplacerTmp.setDetailTransferStockMP(detailTransferStockMP);
								detailMarchandiseDeplacerTmp.setQuantiteManque(detailMarchandiseDeplacerTmp.getQuantiteSortie().subtract(detailMarchandiseDeplacerTmp.getQuantiteEntrer()));
								
							}
							
							
							
							
						}
						
						
						
					}
					
					listDetailMarchandiseDeplacer.set(j, detailMarchandiseDeplacerTmp);
					
				}
				
				
				
				 
			
				
				
				 
			
				
				 
			}
			
			
		}
			
			
			
			
		} 
			 
			
		
		listDetailMarchandiseDeplacerImprimerFrais.clear();
		listDetailMarchandiseDeplacerImprimerPaie.clear();
		listDetailMarchandiseDeplacerImprimerProduction.clear();
		
		
		
		
		for(int t=0;t<listDetailMarchandiseDeplacer.size();t++)
		{
			 
			
			DetailTransferStockMP detailTransferStockMP=listDetailMarchandiseDeplacer.get(t).getDetailTransferStockMP();
			
	 
		 
			 
			
			
			if(detailTransferStockMP.getAction()!=null)
			{
				
				if(detailTransferStockMP.getAction().equals(Constantes.ACTION_PERTE_DE_QUANTITE))
				{
					if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

					}else
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

					}
					
					listDetailMarchandiseDeplacerImprimerFrais.add(listDetailMarchandiseDeplacer.get(t));
				}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_QUANTITE_A_STOCKER))
				{
					if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

					}else
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

					}
					
					listDetailMarchandiseDeplacerImprimerProduction.add(listDetailMarchandiseDeplacer.get(t));
				}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR))
				{
					if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

					}else
					{
						listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

						
					}
					
					listDetailMarchandiseDeplacerImprimerPaie.add(listDetailMarchandiseDeplacer.get(t));
				}
			}
			
			 
			
		}
		
		
		
		
		for(int y=0;y<listDetailMarchandiseDeplacer.size();y++)
		{
			
			
			if(listDetailMarchandiseDeplacer.get(y).getQuantiteEntrer().compareTo(listDetailMarchandiseDeplacer.get(y).getQuantiteSortie())!=0)
			{
				
				ecarteExiste=true;
			}
			
		}
		
		if(ecarteExiste==true)
		{
			
			listMarchandiseDeplacerAfficher.add(listMarchandiseDeplacer.get(s));
		}
		
		
		
			 
		 }
		

		
		
		
	}
	
	
	
}

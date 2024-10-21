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


public class ConsulterFactureMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
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
	private JButton btnAjouter;
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
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	 private JButton btnSupprimer = new JButton();
	 
	private JTextField txtnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser datefin;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
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
	  private JTextField txtquantite;
	  private JTextField txtCodeMP;
	  private JTextField txtmontant;
	  private JTextField txtPrix;
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
			
			
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureMP() {
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
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		
       		if(tableArticle.getSelectedRow()!=-1)
       		{
       			
       			MatierePremier matierePremier=mapMatierePremiere.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1).toString());
       			
       			soucategoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
       			categoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getNom());
       			comboMP.setSelectedItem(matierePremier.getNom());
       			txtCodeMP.setText(matierePremier.getCode());
       			combofournisseur.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       			txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       			txtmontant.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
       			btnAjouter.setEnabled(false);
       			btnModifier.setEnabled(true);
       			btnSupprimer.setEnabled(true);
       			
       		}
       		
       		
       		

       	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableArticle.setShowVerticalLines(false);
       tableArticle.setSelectionBackground(new Color(51, 204, 255));
       tableArticle.setRowHeightEnabled(true);
       tableArticle.setBackground(new Color(255, 255, 255));
       tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableArticle.setColumnControlVisible(true);
       tableArticle.setForeground(Color.BLACK);
       tableArticle.setGridColor(new Color(0, 0, 255));
       tableArticle.setAutoCreateRowSorter(true);
       tableArticle.setBounds(2, 27, 411, 198);
       tableArticle.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableArticle);
				  		     	scrollPane.setBounds(10, 677, 1375, 228);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 649, 1458, 30);
				  		     	add(titledSeparator);
		      


		     
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(listDetailFactureVenteMP.size()!=0)
			{
				
				FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
				TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureVenteMP.getCodeTransfer());
				
				factureVenteMP.setModifierPar(utilisateur);
				factureVenteMP.setMontantHT(new BigDecimal(txttotalmontantHT.getText()));
				factureVenteMP.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()));
				factureVenteMP.setMontantTTC(new BigDecimal(txttotalmontantTTC.getText()));
				factureVenteMP.setDateModifier(new Date());
				
				transferStockMP.setModifierPar(utilisateur);
				
				factureVenteMPDAO.edit(factureVenteMP);
				transferStockMPDAO.edit(transferStockMP);
				
				initialiser();
				
				
				listDetailFactureVenteMP.clear();
				afficher_tableDetailFacturePF(listDetailFactureVenteMP);
				ChargerFactures();
				txttotalmontantHT.setText("");
				txttotalmontantTVA.setText("");
				txttotalmontantTTC.setText("");
				txttotalquantite.setText("");
				JOptionPane.showMessageDialog(null, "Facture Vente MP Modifier Avec Succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "Liste Des Article est vide","Erreur",JOptionPane.ERROR_MESSAGE);
				
			}
				
				
				
				
				
			
			
			
			
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(622, 916, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 470, 1458, 30);
		add(titledSeparator_1);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(495, 625, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
				TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureVenteMP.getCodeTransfer());
				 StockFinale=BigDecimal.ZERO;
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
			FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem());
			String date="01/01/"+sdf.format(factureVenteMP.getDateFacture())+"";
	 		
	 			Date dateDebut= new Date(date);
			if(matierePremier==null)
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectinner MP SVP");
				return;
			}else if(txtPrix.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez entrer le Prix SVP");
				return;
				
			}else if(!txtPrix.getText().equals("") && ((new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0)))
			{
				
				JOptionPane.showMessageDialog(null, "le Prix doit etre superieur SVP");
				return;
				
				
			}else if(!txtCodeMP.getText().equals("") && ((new BigDecimal(txtCodeMP.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtCodeMP.getText()).compareTo(BigDecimal.ZERO)<0)))
			{
				
				JOptionPane.showMessageDialog(null, "la Quantité doit etre superieur SVP");
				return;
				
				
			}else
			{
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				
				
				if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP");
					return;
				}else
				{
					
					
					
					
				 		List<Object[]> listestockfinale=listestockfinale=detailTransferMPDAO.StockFinaleMPMagasinDechet(dateDebut, factureVenteMP.getDateFacture(), magasin, matierePremier, fournisseurMP);
				 		for(int i=0;i<listestockfinale.size();i++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(i);
							if(fournisseurMP!=null)
							{
								 if((int) object[0]==matierePremier.getId() && (int)object[1]==fournisseurMP.getId())
					 			 {
					 				StockFinale=(BigDecimal)object[2];
					 			 }
							}else
							{
								 if((int) object[0]==matierePremier.getId() )
					 			 {
					 				StockFinale=(BigDecimal)object[1];
					 			 }
							}
				 			
				 			 	
				 			}
					
				
				
						boolean existe=false;
						
					for(int i=0;i<listDetailFactureVenteMP.size();i++)
					{
						
						DetailFactureVenteMP DetailFactureVenteMP=listDetailFactureVenteMP.get(i);
						
						if(DetailFactureVenteMP.getMatierePremier().getId()==matierePremier.getId())
						{
							
						if(DetailFactureVenteMP.getFournisseurMP()!=null)
								{
							if(fournisseurMP!=null)
							{
								if(fournisseurMP.getId()==DetailFactureVenteMP.getFournisseurMP().getId())
								{
									existe=true;
								}
								
							}
								
									
								}else
								{
									
									if(fournisseurMP==null)
									{
										
											existe=true;
										
										
									}
									
									
									
								}
								
								
							
							
							
							
							
						}
					}
						
					if(existe==true)
					{
						JOptionPane.showMessageDialog(null, matierePremier.getNom()+" Déja Facturé !!!!!! ");
						return;
					}else
					{
						
						if(StockFinale.compareTo(new BigDecimal(txtCodeMP.getText()))<0)
						{
							
							JOptionPane.showMessageDialog(null, "Stock "+matierePremier.getNom()+" insuffisant !!!!!! ");
							return;
						}else
						{
							DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
							detailTransferStockMP.setMagasinSouce(magasin);
							detailTransferStockMP.setMatierePremier(matierePremier);
							detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
							detailTransferStockMP.setTransferStockMP(transferStockMP);
							
						DetailFactureVenteMP	 detailFactureVenteMP=new DetailFactureVenteMP();
						detailFactureVenteMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						detailFactureVenteMP.setQuantite(new BigDecimal(txtquantite.getText()));
						detailFactureVenteMP.setFactureVenteMP(factureVenteMP);
						detailFactureVenteMP.setMatierePremier(matierePremier);	
						detailFactureVenteMP.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).setScale(6, RoundingMode.HALF_UP));
						detailFactureVenteMP.setMontantTTC((detailFactureVenteMP.getMontantHT().multiply(new BigDecimal(1.2))).setScale(6, RoundingMode.HALF_UP));
						
						if(checkboxSansTva.isSelected()==true)
						{
							detailFactureVenteMP.setTva(BigDecimal.ZERO);
							detailFactureVenteMP.setMontantTVA(BigDecimal.ZERO);

							
						}else
						{
							detailFactureVenteMP.setTva(TVA.multiply(new BigDecimal(100)));
							detailFactureVenteMP.setMontantTVA((detailFactureVenteMP.getMontantHT().multiply(new BigDecimal(0.2))).setScale(6, RoundingMode.HALF_UP));

						}
						
						detailFactureVenteMP.setMontantTTC(detailFactureVenteMP.getMontantHT().add(detailFactureVenteMP.getMontantTVA()));

						
						if(fournisseurMP!=null)
							{
								detailFactureVenteMP.setFournisseurMP(fournisseurMP);
								detailTransferStockMP.setFournisseur(fournisseurMP);
							}
							
							listDetailFactureVenteMP.add(detailFactureVenteMP);
							listDetailTransfertStockMP.add(detailTransferStockMP);
							detailFactureVenteMPDAO.add(detailFactureVenteMP);
							detailTransferMPDAO.add(detailTransferStockMP);
							afficher_tableDetailFacturePF(listDetailFactureVenteMP);
							initialiser();
							
							
						}
						
							
					}
						
						
						
					
					
					
					
				}
				
				
			}
			
			
			
			
			
			
			
			
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {

		   		DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(tableArticle.getSelectedRow());
		   		DetailTransferStockMP detailTransferStockMPTmp=listDetailTransfertStockMP.get(tableArticle.getSelectedRow());
			   	 StockFinale=BigDecimal.ZERO;
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				 String date="01/01/"+sdf.format(detailFactureVenteMP.getFactureVenteMP().getDateFacture())+"";
			 		
		 			Date dateDebut= new Date(date);
					MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
					FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem());
					
					if(matierePremier==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectinner MP SVP");
						return;
					}else if(txtPrix.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le Prix SVP");
						return;
						
					}else if(!txtPrix.getText().equals("") && ((new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0)))
					{
						
						JOptionPane.showMessageDialog(null, "le Prix doit etre superieur SVP");
						return;
						
						
					}else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)))
					{
						
						JOptionPane.showMessageDialog(null, "la Quantité doit etre superieur SVP");
						return;
						
						
					}else
					{
						
						Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						
						
						if(magasin==null)
						{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP");
							return;
						}else
						{
							List<Object[]> listestockfinale=listestockfinale=detailTransferMPDAO.StockFinaleMPMagasinDechet(dateDebut, detailFactureVenteMP.getFactureVenteMP().getDateFacture(), magasin, matierePremier, fournisseurMP);
					 		for(int i=0;i<listestockfinale.size();i++)
					 			{
					 				
					 			 Object[] object=listestockfinale.get(i);
								if(fournisseurMP!=null)
								{
									 if((int) object[0]==matierePremier.getId() && (int)object[1]==fournisseurMP.getId())
						 			 {
						 				StockFinale=(BigDecimal)object[2];
						 			 }
								}else
								{
									 if((int) object[0]==matierePremier.getId() )
						 			 {
						 				StockFinale=(BigDecimal)object[1];
						 			 }
								}
					 			
					 			 	
					 			}
						
							
						
								boolean existe=false;
								
							for(int i=0;i<listDetailFactureVenteMP.size();i++)
							{
								
								DetailFactureVenteMP DetailFactureVenteMP=listDetailFactureVenteMP.get(i);
								
								if(i!=tableArticle.getSelectedRow())
								{
									if(DetailFactureVenteMP.getMatierePremier().getId()==matierePremier.getId())
									{
										
									if(DetailFactureVenteMP.getFournisseurMP()!=null)
											{
										if(fournisseurMP!=null)
										{
											if(fournisseurMP.getId()==DetailFactureVenteMP.getFournisseurMP().getId())
											{
												existe=true;
											}
											
										}
											
												
											}else
											{
												
												if(fournisseurMP==null)
												{
													
														existe=true;
													
													
												}
												
												
												
											}
											
											
									}
									
								}
								
								
							}
								
							if(existe==true)
							{
								JOptionPane.showMessageDialog(null, matierePremier.getNom()+" Déja Facturé !!!!!! ");
								return;
							}else
							{
								
								if(StockFinale.compareTo(new BigDecimal(txtquantite.getText()))<0)
								{
									
									JOptionPane.showMessageDialog(null, "Stock "+matierePremier.getNom()+" insuffisant !!!!!! ");
									return;
								}else
								{
									
									detailTransferStockMPTmp.setMatierePremier(matierePremier);
									detailTransferStockMPTmp.setQuantite(new BigDecimal(txtquantite.getText()));
									detailTransferStockMPTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									
									
								detailFactureVenteMP.setMatierePremier(matierePremier);	
								detailFactureVenteMP.setQuantite(new BigDecimal(txtquantite.getText()));
								detailFactureVenteMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								detailFactureVenteMP.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).setScale(6, RoundingMode.HALF_UP));
								
								
								if(checkboxSansTva.isSelected()==true)
								{
									detailFactureVenteMP.setTva(BigDecimal.ZERO);
									detailFactureVenteMP.setMontantTVA(BigDecimal.ZERO);

									
								}else
								{
									detailFactureVenteMP.setTva(TVA.multiply(new BigDecimal(100)));
									detailFactureVenteMP.setMontantTVA((detailFactureVenteMP.getMontantHT().multiply(new BigDecimal(0.2))).setScale(6, RoundingMode.HALF_UP));

								}
								
								detailFactureVenteMP.setMontantTTC(detailFactureVenteMP.getMontantHT().add(detailFactureVenteMP.getMontantTVA()));

							
								
								
								if(fournisseurMP!=null)
									{
										detailTransferStockMPTmp.setFournisseur(fournisseurMP);
										detailFactureVenteMP.setFournisseurMP(fournisseurMP);
									}
									
									
									
									detailTransferMPDAO.edit(detailTransferStockMPTmp);
									detailFactureVenteMPDAO.edit(detailFactureVenteMP);
									listDetailFactureVenteMP.set(tableArticle.getSelectedRow(), detailFactureVenteMP);
									listDetailTransfertStockMP.set(tableArticle.getSelectedRow(), detailTransferStockMPTmp);
									afficher_tableDetailFacturePF (listDetailFactureVenteMP);
									initialiser();
								}
								
									
							}
								
								
								
							
							
							
							
						}
						
						
					}
					
			   	
			   	
		   	
		   	
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {

		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(tableArticle.getSelectedRow());
		  			DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(tableArticle.getSelectedRow());
		  			detailFactureVenteMPDAO.delete(detailFactureVenteMP.getId());
		  			detailTransferMPDAO.delete(detailTransferStockMP.getId());
		  			listDetailFactureVenteMP.remove(tableArticle.getSelectedRow());
		  			listDetailTransfertStockMP.remove(tableArticle.getSelectedRow());
		  			afficher_tableDetailFacturePF(listDetailFactureVenteMP);
		  			initialiser();
		  			
		  		}
		  	
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1395, 736, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1119, 973, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1241, 973, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1012, 916, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(905, 916, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 706, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL De Vente  Dechet MP ");
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
if(table.getSelectedRow()!=-1)
{
	
	
	FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
	
	listDetailFactureVenteMP=detailFactureVenteMPDAO.listeDetailFactureMPByFacture(factureVenteMP.getId());
	TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureVenteMP.getCodeTransfer());
	listDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
	afficher_tableDetailFacturePF(listDetailFactureVenteMP);
	btnAjouter.setEnabled(true);
	btnModifier.setEnabled(false);
	btnSupprimer.setEnabled(false);
}
			
				
				
				
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Num BL", "Date BL", "Type", "Client", "Depot", "Magasin", "Montant HT", "Montant TVA", "Montant TTC","Mode Réglement","N° Réglement"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		
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
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(1119, 942, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1241, 942, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1241, 910, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1119, 910, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1585, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num BL:");
	    lblNumFacture.setBounds(10, 12, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtnumfacture = new JTextField();
	    txtnumfacture.setBounds(113, 17, 102, 26);
	    layeredPane_2.add(txtnumfacture);
	    util.Utils.copycoller(txtnumfacture);
	    txtnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtnumfacture.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(1121, 14, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(430, 14, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     datefin = new JDateChooser();
	     datefin.setBounds(474, 15, 151, 26);
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
	     combopardepot.setBounds(697, 15, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(635, 14, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     int l=0;
	     
	      comboClientpf = new JComboBox();
	     comboClientpf.setSelectedIndex(-1);
	     comboClientpf.setBounds(1172, 15, 238, 24);
	     layeredPane_2.add(comboClientpf);
	     
	     AutoCompleteDecorator.decorate(comboClientpf);
	   
	      btninitialiser = new JButton("Initialiser");
	     btninitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     	initialiser();
	     	}
	     });
	     btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btninitialiser.setBounds(622, 625, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		txtnumfacture.setText("");
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
	    datedebut.setBounds(269, 12, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(225, 14, 34, 24);
	    layeredPane_2.add(lblDu);
	    
	    JLabel label_10 = new JLabel("Magasin :");
	    label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_10.setBounds(869, 13, 56, 24);
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
	    combomagasin.setBounds(923, 14, 183, 24);
	    layeredPane_2.add(combomagasin);
	    
	     

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
	    
	     supprimer_facture = new JButton();
	    supprimer_facture.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	
	    	
	    	}
	    });
	    supprimer_facture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    supprimer_facture.setBounds(1478, 185, 73, 24);
	    supprimer_facture.setIcon(imgSupprimer);
	    add(supprimer_facture);
	    supprimer_facture.hide();
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
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(10, 499, 1458, 123);
	    add(layeredPane);
	    
	    txtquantite = new JTextField();
	    txtquantite.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		

	    		

        		
    			if(e.getKeyCode()==e.VK_ENTER)
		      		{
    				
    				
    				if(!txtPrix.getText().equals(""))
    				{
    					
    					if(!txtquantite.getText().equals(""))
    					{
    						
    						txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
    						
    					}else
    					{
    						txtmontant.setText(new BigDecimal(0)+"");
    						txtquantite.setText(new BigDecimal(0)+"");
    					}
    				}else
    				{
    					
    					txtmontant.setText(new BigDecimal(0)+"");
    					txtPrix.setText(new BigDecimal(0)+"");
    					
    					
    				}
    				
    				
    				
		      		}
        		
        		
        		
	    		
	    	
	    		
	    		
	    		
	    		
	    	}
	    });
	    txtquantite.setColumns(10);
	    txtquantite.setBounds(41, 55, 99, 26);
	    layeredPane.add(txtquantite);
	    
	    JLabel label_7 = new JLabel("Prix  :");
	    label_7.setBounds(150, 55, 36, 26);
	    layeredPane.add(label_7);
	    
	    txtCodeMP = new JTextField();
	    txtCodeMP.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		

     			if(e.getKeyCode()==e.VK_ENTER)
    		{
     				
     					
    			if(!txtCodeMP.getText().equals(""))
    			{
    				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
    				MatierePremier matierePremier=MapCodeMatierPremiere.get(txtCodeMP.getText().toUpperCase());
		    		
		    		if(matierePremier!=null)
		    		{	
		    			comboMP.setSelectedItem(matierePremier.getNom());
		    			
		    		}else
		    		{
		    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
		    			comboMP.setSelectedItem("");
		    			
		    		}
    				
    				
    		}else
    		{
    			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
    			
    			
    		}
     				
     				
    	
     				
     				
    		}
			
			
			
			
			
		
	    		
	    		
	    	}
	    });
	    txtCodeMP.setColumns(10);
	    txtCodeMP.setBounds(675, 11, 112, 26);
	    layeredPane.add(txtCodeMP);
	    
	    JLabel label_8 = new JLabel("Code  MP:");
	    label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_8.setBounds(604, 11, 82, 26);
	    layeredPane.add(label_8);
	    
	    JLabel label_9 = new JLabel("Libelle :");
	    label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_9.setBounds(797, 11, 57, 26);
	    layeredPane.add(label_9);
	    
	    JLabel label_12 = new JLabel("Montant  :");
	    label_12.setBounds(355, 55, 64, 26);
	    layeredPane.add(label_12);
	    
	    txtmontant = new JTextField();
	    txtmontant.setEditable(false);
	    txtmontant.setColumns(10);
	    txtmontant.setBounds(429, 55, 125, 26);
	    layeredPane.add(txtmontant);
	    
	    txtPrix = new JTextField();
	    txtPrix.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		

        		
    			if(e.getKeyCode()==e.VK_ENTER)
		      		{
    				
    				
    				if(!txtPrix.getText().equals(""))
    				{
    					
    					if(!txtquantite.getText().equals(""))
    					{
    						
    						txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
    						
    					}else
    					{
    						txtmontant.setText(new BigDecimal(0)+"");
    						txtquantite.setText(new BigDecimal(0)+"");
    					}
    				}else
    				{
    					
    					txtmontant.setText(new BigDecimal(0)+"");
    					txtPrix.setText(new BigDecimal(0)+"");
    					
    					
    				}
    				
    				
    				
		      		}
        		
        		
        		
	    		
	    	}
	    });
	    txtPrix.setColumns(10);
	    txtPrix.setBounds(196, 55, 135, 26);
	    layeredPane.add(txtPrix);
	    
	    JLabel label_13 = new JLabel("QU :");
	    label_13.setBounds(9, 55, 42, 26);
	    layeredPane.add(label_13);
	    
	     comboMP = new JComboBox();
	     comboMP.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		


           	 MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
           	 
           	 if(matierePremier!=null)
           	 {
           		 txtCodeMP.setText(matierePremier.getCode());
           		 
           		 
           	 }else
           	 {
           		txtCodeMP.setText(Constantes.CODE_MP);
           	 }
           	
	     		
	     		
	     		
	     	}
	     });
	    comboMP.setSelectedIndex(-1);
	    comboMP.setBounds(840, 11, 335, 27);
	    layeredPane.add(comboMP);
	    
	    JLabel label_14 = new JLabel("Fournisseur :");
	    label_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_14.setBounds(1181, 12, 79, 24);
	    layeredPane.add(label_14);
	    
	     combofournisseur = new JComboBox();
	    combofournisseur.setSelectedIndex(-1);
	    combofournisseur.setBounds(1259, 13, 171, 24);
	    layeredPane.add(combofournisseur);
	    
	    JLabel label_15 = new JLabel("Sous-Categorie Mp");
	    label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_15.setBounds(10, 11, 144, 24);
	    layeredPane.add(label_15);
	    
	     soucategoriempcombo = new JComboBox();
	    soucategoriempcombo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

		  		

		 		

		 		

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
	    soucategoriempcombo.setBounds(117, 11, 184, 24);
	    layeredPane.add(soucategoriempcombo);
	    
	    JLabel label_16 = new JLabel("Categorie Mp");
	    label_16.setBounds(311, 11, 80, 26);
	    layeredPane.add(label_16);
	    
	     categoriempcombo = new JComboBox();
	     categoriempcombo.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

		  		


		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText(Constantes.CODE_MP);
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  			comboMP.addItem(matierePremier.getNom());
  		  		mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			
  		  			listeMatierePremiereCombo.clear();
  		  		txtCodeMP.setText(Constantes.CODE_MP);
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
	    categoriempcombo.setBounds(386, 12, 208, 24);
	    layeredPane.add(categoriempcombo);


		
		
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
combofournisseur.addItem("");
 checkBoxSansTVA = new JCheckBox("Sans TVA");
checkBoxSansTVA.setBounds(1474, 499, 97, 23);
add(checkBoxSansTVA);
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

txtCodeMP.setText(Constantes.CODE_MP);

		
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
	

	
	


	void initialiser()
	{

	soucategoriempcombo.setSelectedItem("");
	categoriempcombo.setSelectedItem("");
	comboMP.setSelectedItem("");
	txtCodeMP.setText(Constantes.CODE_MP);
	txtquantite.setText("");	
	txtPrix.setText("");
	txtmontant.setText("");
	
	}
	void initialiserPourFacture()
	{

	
	}
	
	
	void initialiserGratuit()
	{

	
	}
	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date BL", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant HT","Transfere BL en Facture"
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
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFactureVenteMP> listDetailFactureVenteMP)
	{
		
		
		
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		BigDecimal MontantHT=BigDecimal.ZERO;
		BigDecimal MontantTVA=BigDecimal.ZERO;
		BigDecimal MontantTTC=BigDecimal.ZERO;
		BigDecimal Quantite=BigDecimal.ZERO;
		 
		while(i<listDetailFactureVenteMP.size())
		{	
		DetailFactureVenteMP detailfactureVenteMP=listDetailFactureVenteMP.get(i);
		String FourniseurMP="";
		if(detailfactureVenteMP.getFournisseurMP()!=null)
		{
			FourniseurMP=detailfactureVenteMP.getFournisseurMP().getCodeFournisseur();
		}
		
		MontantHT=MontantHT.add(detailfactureVenteMP.getMontantHT().setScale(6, RoundingMode.HALF_UP));
		MontantTVA=MontantTVA.add(detailfactureVenteMP.getMontantTVA().setScale(6, RoundingMode.HALF_UP));
		MontantTTC=MontantTTC.add(detailfactureVenteMP.getMontantTTC().setScale(6, RoundingMode.HALF_UP));
		Quantite=Quantite.add(detailfactureVenteMP.getQuantite());
			
			Object []ligne={detailfactureVenteMP.getMatierePremier().getCode() , detailfactureVenteMP.getMatierePremier().getNom(),FourniseurMP,detailfactureVenteMP.getPrixUnitaire(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfactureVenteMP.getQuantite()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfactureVenteMP.getMontantHT()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfactureVenteMP.getMontantTVA()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfactureVenteMP.getMontantTTC())};

			modeleArticle.addRow(ligne);
			i++;
		}
		
		txttotalmontantHT.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantHT)+"");
		txttotalmontantTVA.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTVA)+"");
		txttotalmontantTTC.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTTC)+"");
		txttotalquantite.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(Quantite)+"");
		
		
}
	
	
	

	
	
	
	
	void afficher_tableFacturePF(List<FactureVenteMP> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date BL", "Type", "Client", "Depot", "Magasin", "Montant HT", "Montant TVA", "Montant TTC","Mode Réglement","N° Réglement"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class,String.class,String.class
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
		BigDecimal MontantTTC=BigDecimal.ZERO;
		BigDecimal MontantHT=BigDecimal.ZERO;
		BigDecimal MontantTVA=BigDecimal.ZERO;
		 String ModeReglement="";
		 String NumReglement="";
		while(i<listFacture.size())
		{	
		MontantTTC=BigDecimal.ZERO;
		MontantHT=BigDecimal.ZERO;
		MontantTVA=BigDecimal.ZERO;
		ModeReglement="";
		NumReglement="";
		FactureVenteMP facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
			
		for(int j=0;j<facturepf.getDetailFactureVenteMP().size();j++)
		{
			
			MontantTTC=MontantTTC.add(facturepf.getDetailFactureVenteMP().get(j).getMontantTTC());
			MontantHT=MontantHT.add(facturepf.getDetailFactureVenteMP().get(j).getMontantHT());
			MontantTVA=MontantTVA.add(facturepf.getDetailFactureVenteMP().get(j).getMontantTVA());
		}
		
		if(facturepf.getModeReglement()!=null)
		{
			ModeReglement=facturepf.getModeReglement();
		}
		
		if(facturepf.getNumPiece()!=null)
		{
			NumReglement=facturepf.getNumPiece();
		}
		
			Object []ligne={facturepf.getNumFacture(),datefacture,facturepf.getEtat(),facturepf.getClient().getNom(),facturepf.getMagasin().getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantHT),NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTVA),NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTTC),ModeReglement,NumReglement};

			modelefacture.addRow(ligne);
			i++;
		}
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
    		
    	if(!txtnumfacture.getText().equals(""))	
    	{
    		requete=requete+" and numFacture='"+txtnumfacture.getText()+"' ";	
    	}
    		
    	Client client=mapClient.get(comboClientpf.getSelectedItem())	;
    	if(client!=null)
    	{
    		
    		requete=requete+" and client.id='"+client.getId()+"' ";	
    		
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
    		
    		listFactureVenteMP=factureVenteMPDAO.findByRequete(requete);
    		
    		afficher_tableFacturePF(listFactureVenteMP);
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	
	}
	
	
	
	}





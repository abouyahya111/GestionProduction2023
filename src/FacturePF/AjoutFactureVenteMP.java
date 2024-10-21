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
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import presentation.stockMP.TransfererStockMP;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
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
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;

import dao.daoManager.ArticlesDAO;
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
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;

import dao.entity.Articles;
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
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.lowagie.text.pdf.events.IndexEvents.Entry;
import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;


public class AjoutFactureVenteMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();

	private List<DetailFactureVenteMP> listDetailFactureMP =new ArrayList<DetailFactureVenteMP>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	

	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<MatierePremier> listDechetMP =new ArrayList<MatierePremier>();

	private List<TransferStockPF> listTransferStockPF =new ArrayList<TransferStockPF>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
		
	
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Client> mapClient= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private    JComboBox combofamille =new JComboBox();
	 private JComboBox comboMP;

	
	private  MatierePremiereDAO matierePremiereDAO;;
	private FournisseurMPDAO fournisseurMPDAO;
	private FactureVenteMP factureVenteMP=new FactureVenteMP();
	
private DetailFactureVenteMPDAO detailFactureMPdao;

private StockMPDAO stockMPDAO;
	private JTextField txtcodeMP;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumBL;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	
	private ClientDAO clientDAO;
	
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private JTextField txtmontant;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	private   JComboBox comboFournisseur = new JComboBox();
	private   JComboBox comboClientpf ;
	private JTextField txttotalmontantHT;
	private JTextField txttotalmontantTVA;
	private  JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	 JComboBox comboFamilleGratuit = new JComboBox();
	 JComboBox comboSousFamilleGratuit = new JComboBox();
	 JComboBox comboArticleGratuit = new JComboBox();
	 JLayeredPane layerArticle = new JLayeredPane();
	 JLabel labelSousFamillegratuit = new JLabel("Sous Famille :");
	 JLabel labelcodearticlegratuit = new JLabel("Code Article :");
	 JLabel labelfamillearticlegratuit = new JLabel("Famille Article :");
	 JLabel labelarticlegratuit = new JLabel("Libelle :");
	 JLabel labelquantitegratuit = new JLabel("Quantite :");
	  JLabel lblPrix = new JLabel("Prix  :");
	  JLabel lblMontant = new JLabel("Montant  :");
	  JLabel lblReduction = new JLabel("Remise :");
	  JLabel labelpourcentage = new JLabel("%");
	  private FactureVenteMPDAO factureVenteMPdao;
	
		  JCheckBox checkttc = new JCheckBox("TTC");
		  JLabel labelmarge = new JLabel("marge");
		  private JLabel stockdisponible;
		  JLabel labelprixmin = new JLabel("New label");
		  JLabel labelPrixMax = new JLabel("New label");
		  BigDecimal prixTTC=BigDecimal.ZERO;
		  BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
		  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
		  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
		  JLabel lblOffreTherre = new JLabel("Offre Therre :");
		  JComboBox comboBoxtherres = new JComboBox();
		  JLabel lbloffreverres = new JLabel("Offre Verres :");
		  JComboBox comboBoxverres = new JComboBox();
		  JLabel stockdisponibleoffre = new JLabel("");
		  private JLabel stockdisponibleoffretherres;
		  JLabel stockdisponibleoffreverres = new JLabel("");
		  JComboBox comboBoxPromo = new JComboBox();
		  JLabel lblOffrePromo = new JLabel("Offre Promo :");
		  JLabel stockdisponiblearticlepromo = new JLabel("");
		  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
		 TransferStockMPDAO transferStockMPDAO;
		 JLabel lblNum = new JLabel("Num :");
		 JComboBox comboReglement = new JComboBox();
		 private JTextField txtnumcheque;
		 JCheckBox chckbxSansTva = new JCheckBox("Sans TVA");
		 JButton btnInitialiserTous = new JButton("Initialiser Tous");
			private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
			private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
		 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFactureVenteMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1532, 913);
      
	
        try{ 
        	
        	 
        	 
      //  facturePF=new FacturePF();
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
            stockMPDAO=new StockMPDAOImpl();
         	depotdao=new DepotDAOImpl();
         	matierePremiereDAO=new MatierePremierDAOImpl();
         	 fournisseurMPDAO=new FournisseurMPDAOImpl();
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         	detailFactureMPdao=new DetailFactureVenteMPDAOImpl();
         	clientDAO=new ClientDAOImpl();
         	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {

       	if(tableArticle.getSelectedRow()!=-1)
       	{
       		
       	DetailFactureVenteMP detailFactureVenteMP=	listDetailFactureMP.get(tableArticle.getSelectedRow());
       		
       		txtcodeMP.setText(detailFactureVenteMP.getMatierePremier().getCode());
       		comboMP.setSelectedItem(detailFactureVenteMP.getMatierePremier().getNom());
       		if(detailFactureVenteMP.getFournisseurMP()!=null)
       		{
       			comboFournisseur.setSelectedItem(detailFactureVenteMP.getFournisseurMP().getCodeFournisseur());
       		}else
       		{
       			comboFournisseur.setSelectedItem("");
       		}
       		
       		txtPrix.setText(detailFactureVenteMP.getPrixUnitaire()+"");
       		txtquantite.setText(detailFactureVenteMP.getQuantite()+"");
       		txtmontant.setText(detailFactureVenteMP.getMontantHT()+"");
       		
       		
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
				  		     	scrollPane.setBounds(10, 465, 1031, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 424, 1027, 30);
				  		     	add(titledSeparator);
		       
		    
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				Client client=mapClient.get(comboClientpf.getSelectedItem());
				
				
				
				 if(depot==null)
				{
					
                   JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot SVP");
					
					return;
					
				}else if(magasin==null)
				{
					
					
                  JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
					
					return;
					
				}else if(dateChooserfacture.getDate()==null)
				{
					
                JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP");
				return;
					
				}else if(client==null)
				{
					
	                JOptionPane.showMessageDialog(null, "Veuillez Selectionner le client SVP");
					return;
						
					}
				
				
				else
				{
					
					if(comboReglement.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Mode de réglement SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else
					{
						if(comboReglement.getSelectedItem().equals(MODE_REGLEMENT_CHEQUE) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_VERSEMENT) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_TRAITE) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_VIREMENT))
						{
							if(txtnumcheque.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Entrer le Num de Piece Pour le Cheque ,Virement, Versement ou Traite SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					
						
					}
					
					
					
			
					
					SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
					
                	 String date =""; 
                	 String NumFacture="";
                	 if(dateChooserfacture.getDate()!=null)
                	 {
                		date= dcn.format(dateChooserfacture.getDate());
                		NumFacture=Utils.genererCodeFactureVente(date);
                		
                	 }else
                	 {
                		 JOptionPane.showMessageDialog (null, "Veuillez selectionner la date facture SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
                		 return;
                	 }
                	 
                	 txtnumBL.setText(NumFacture+"");
					
					
					if(listDetailFactureMP.size()!=0)
					{
						
						BigDecimal TotalMontantHT=BigDecimal.ZERO;
						BigDecimal TotalMontantTVA=BigDecimal.ZERO;
						BigDecimal TotalMontantTTC=BigDecimal.ZERO;
						
						for(int j=0;j<listDetailFactureMP.size();j++)
						{
							
							TotalMontantHT=TotalMontantHT.add(listDetailFactureMP.get(j).getMontantHT());
							TotalMontantTVA=TotalMontantTVA.add(listDetailFactureMP.get(j).getMontantTVA());
							TotalMontantTTC=TotalMontantTTC.add(listDetailFactureMP.get(j).getMontantTTC());
						}
						
						String codeTransfert=Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),TYPE_VENTE);	
						
						factureVenteMP.setCreerPar(AuthentificationView.utilisateur);
						factureVenteMP.setDateFacture(dateChooserfacture.getDate());
						factureVenteMP.setMontantHT(TotalMontantHT);
						factureVenteMP.setMontantTVA(TotalMontantTVA);
						factureVenteMP.setMontantTTC(TotalMontantTTC);
						factureVenteMP.setClient(client);
						factureVenteMP.setMagasin(magasin);
						factureVenteMP.setNumFacture (txtnumBL.getText());
						factureVenteMP.setCodeTransfer(codeTransfert);
						factureVenteMP.setModeReglement(comboReglement.getSelectedItem().toString());
						factureVenteMP.setEtat(ETAT_REGLE);
						if(!txtnumcheque.getText().equals(""))
						{
							factureVenteMP.setNumPiece(txtnumcheque.getText());
						}
						factureVenteMPdao.add(factureVenteMP);
						
						TransferStockMP transfererStockMP=new TransferStockMP();
						transfererStockMP.setCodeTransfer(codeTransfert);
						transfererStockMP.setCreerPar(AuthentificationView.utilisateur);
						transfererStockMP.setDate(new Date());
						transfererStockMP.setDateTransfer(dateChooserfacture.getDate());
						transfererStockMP.setDepot(depot);
						transfererStockMP.setStatut(TYPE_VENTE);
						transferStockMPDAO.add(transfererStockMP);
						
						 BigDecimal montantTotal=BigDecimal.ZERO;
                         BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                         BigDecimal montantTotalEmballage=BigDecimal.ZERO;
						
						for(int i=0;i<listDetailFactureMP.size();i++)
						{
							
							DetailFactureVenteMP detailFactureVenteMP=listDetailFactureMP.get(i);
							
							detailFactureMPdao.add(detailFactureVenteMP);
														
		/////////////////////////////////////////////////////////////////////////////// Detail Transfer Stock MP //////////////////////////////////////////////////////////////
						
							DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
							if(detailFactureVenteMP.getFournisseurMP()!=null)
							{
								detailTransferStockMP.setFournisseur(detailFactureVenteMP.getFournisseurMP());
							}
							detailTransferStockMP.setMagasinSouce(magasin);
							detailTransferStockMP.setMatierePremier(detailFactureVenteMP.getMatierePremier());
							detailTransferStockMP.setPrixUnitaire(detailFactureVenteMP.getPrixUnitaire());
							detailTransferStockMP.setQuantite(detailFactureVenteMP.getQuantite());
							detailTransferStockMP.setTransferStockMP(transfererStockMP);
							detailTransferStockMPDAO.add(detailTransferStockMP);
							
							
							if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		     			{
	  		     				montantTotalEnvrac=montantTotalEnvrac.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserfacture.getDate()))));
	  		     				
	  		     			}else
	  		     			{
	  		     				montantTotalEmballage=montantTotalEmballage.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserfacture.getDate()))));
	  		     			}
	  		     			montantTotal=montantTotal.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserfacture.getDate()))));
	  		     				
							
							
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
							
						}
						
					
		////////////////////////////////////////////////////////////////////////////// Num Facture /////////////////////////////////////////////////////////////////////////////////
						
				   		listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
	  		     		
	  		     		if(statistiqueFinanciaireMPTmp!=null)
	  		     		{
	  		     			
	  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
	  		     			
	  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
	  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
	  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
	  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
	  		     			statistiqueFinanciaireMP.setCodeTransfer(codeTransfert);
	  		     			statistiqueFinanciaireMP.setDate(new Date());
	  		     			statistiqueFinanciaireMP.setDateOperation(dateChooserfacture.getDate());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
	  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
	  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
	  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
	  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
	  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
	  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
	  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
	  		     			statistiqueFinanciaireMP.setCoutVente(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutVente()) );
	  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
	  		     			statistiqueFinanciaireMP.setEtat(TYPE_VENTE);
	  		     		 
	  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
	  		     			 
	  		     			
	  		     		} 
						
						
						 ///  Numerotation 2019
						 // Note changer string date to Date datefacture
							
							  String code=""; 
							Utilisateur  utilisateur=AuthentificationView.utilisateur;
							  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_PRODUCTION_TANTAN)) {
								  
							  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_FACTURE_VENTE_ETP); 
							  if(sequenceur!=null) { 
								  int  valeur=sequenceur.getValeur()+1;
								  if(valeur<100 && valeur>=10) {
							  code="0"+valeur+"/"+date; 
							  
							  }else if(valeur<10) 
							  {
								  code="00"+valeur+"/"+date;
								  
							  }else if(valeur>=100) 
							  {
								  code=valeur+"/"+date; 
								  } 
								  
								  sequenceur.setValeur(valeur); 
								  sequenceurDAO.edit(sequenceur);
								  
								  
								  }else
								  {
							  
							  code="00"+1+"/"+date;
							  
							  Sequenceur sequenceurTmp=new Sequenceur();
							  sequenceurTmp.setCode(date);
							  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
							  sequenceurTmp.setValeur(1);
							  sequenceurDAO.add(sequenceurTmp);
							  
							  }
							  
							  
							  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_PRODUCTION_LAAYOUNE)) {
								  
							  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_LAA); 
							  
							  if(sequenceur!=null) { 
								  int valeur=sequenceur.getValeur()+1; 
							  if(valeur<100 && valeur>=10)
							  {
								  
							  code="0"+valeur+"/"+date; }else if(valeur<10) 
								  
							  { 
								  code="00"+valeur+"/"+date;
								  
							  }else if(valeur>=100)
							  {
								  code=valeur+"/"+date;
								  } 
							  sequenceur.setValeur(valeur); 
							  sequenceurDAO.edit(sequenceur);
							  
							  
							  }else {
							  
							  code="00"+1+"/"+date;
							  Sequenceur sequenceurTmp=new Sequenceur();
							  sequenceurTmp.setCode(date);
							  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_LAA);
							  sequenceurTmp.setValeur(1);
							  sequenceurDAO.add(sequenceurTmp);
							  }
}
							  
							  


							 
			
						
						
						
						
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
						
						
						
						JOptionPane.showMessageDialog(null, "Facture Vente MP Ajouté Avec Succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
						
						factureVenteMP=new FactureVenteMP();
						listDetailFactureMP.clear();
					
						afficher_tableDetailFactureVenteMP(listDetailFactureMP);
						
						
					}else
					{
						JOptionPane.showMessageDialog(null, "La liste des article est vide SVP");
						return;
						
					}
					
					
					
					
					
					
					
				}
				
				
			
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(385, 740, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 180, 1031, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1083, 97);
		add(layeredPane_1);
		
		JLabel lblNBl = new JLabel("N\u00B0 BL:");
		lblNBl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNBl.setBounds(10, 13, 89, 24);
		layeredPane_1.add(lblNBl);
		
		txtnumBL = new JTextField();
		txtnumBL.setEditable(false);
		txtnumBL.setColumns(10);
		txtnumBL.setBounds(109, 12, 183, 26);
		layeredPane_1.add(txtnumBL);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(312, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {/*
				if(dateChooserfacture.getDate()!=null)
				{
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				
				 
				txtnumfacture.setText(Utils.genererCodeFactureVente(date));
				}
			*/}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(373, 11, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(574, 12, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(652, 13, 183, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		

 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
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
		  
		  
		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
	  			Depot	 depot = depotdao.findByCode(utilisateur.getCodeDepot());
		    		mapDepot.put(depot.getLibelle(), depot);
	      			combodepot.addItem(depot.getLibelle());
	      			
		    }else {
	  		  listDepot = depotdao.findAll();	
	  		     int  i=0;
	  		      	while(i<listDepot.size())
	  		      		{	
	  		      			Depot depot=listDepot.get(i);
	  		      			mapDepot.put(depot.getLibelle(), depot);
	  		      			combodepot.addItem(depot.getLibelle());
	  		      			
	  		      			i++;
	  		      		}
		    }
		 
		  
		  combodepot.setSelectedIndex(-1);
		 
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(10, 48, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		  combomagasin.setBounds(65, 49, 173, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(309, 48, 245, 24);
		  layeredPane_1.add(comboClientpf);
		  
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(248, 43, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel lblModeRglement = new JLabel("Mode R\u00E9glement :");
		  lblModeRglement.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblModeRglement.setBounds(565, 47, 114, 24);
		  layeredPane_1.add(lblModeRglement);
		  
			txtnumcheque = new JTextField();
			txtnumcheque.setColumns(10);
			txtnumcheque.setBounds(941, 51, 132, 26);
			layeredPane_1.add(txtnumcheque);
			
			 lblNum = new JLabel("Num :");
			lblNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNum.setBounds(875, 48, 56, 24);
			layeredPane_1.add(lblNum);
		  
		   comboReglement = new JComboBox();
		   comboReglement.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		
		   		if(comboReglement.getSelectedItem().equals(MODE_REGLEMENT_CHEQUE) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_VERSEMENT) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_TRAITE) || comboReglement.getSelectedItem().equals(MODE_REGLEMENT_VIREMENT))
		   		{
		   			txtnumcheque.setText("");
		   			txtnumcheque.setVisible(true);
		   			lblNum.setVisible(true);
		   			
		   			
		   			
		   		}else
		   		{
		   			txtnumcheque.setText("");
		   			txtnumcheque.setVisible(false);
		   			lblNum.setVisible(false);
		   		
		   		}
		   		
		  System.out.println(txtnumcheque.getText()); 		
		   		
		   		
		   		
		   	}
		   });
		  comboReglement.setSelectedIndex(-1);
		  comboReglement.setBounds(682, 48, 183, 24);
		  layeredPane_1.add(comboReglement);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 11, 1083, 30);
		add(titledSeparator_2);
		
	
		
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(390, 391, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 StockFinale=BigDecimal.ZERO;
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			MatierePremier matierePremier=mapMP.get(comboMP.getSelectedItem());
			FournisseurMP fournisseurMP=mapFournisseurMP.get(comboFournisseur.getSelectedItem());
			String date="01/01/2023";
	 		
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
					
				 		List<Object[]> listestockfinale=listestockfinale=detailTransferStockMPDAO.StockFinaleMPMagasinDechet(dateDebut, dateChooserfacture.getDate(), magasin, matierePremier, fournisseurMP);
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
						
					for(int i=0;i<listDetailFactureMP.size();i++)
					{
						
						DetailFactureVenteMP DetailFactureVenteMP=listDetailFactureMP.get(i);
						
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
						
						if(StockFinale.compareTo(new BigDecimal(txtquantite.getText()))<0)
						{
							
							JOptionPane.showMessageDialog(null, "Stock "+matierePremier.getNom()+" insuffisant !!!!!! ");
							return;
						}else
						{
							
						DetailFactureVenteMP	 detailFactureVenteMP=new DetailFactureVenteMP();
						detailFactureVenteMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						detailFactureVenteMP.setQuantite(new BigDecimal(txtquantite.getText()));
						detailFactureVenteMP.setFactureVenteMP(factureVenteMP);
						detailFactureVenteMP.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).setScale(6, RoundingMode.HALF_UP));

						if(chckbxSansTva.isSelected()==true)
						{
							detailFactureVenteMP.setTva(BigDecimal.ZERO);
							detailFactureVenteMP.setMontantTVA(BigDecimal.ZERO);
							
						}else
						{
							
							detailFactureVenteMP.setTva(TVA.multiply(new BigDecimal(100)));
							detailFactureVenteMP.setMontantTVA((detailFactureVenteMP.getMontantHT().multiply(new BigDecimal(0.2))).setScale(6, RoundingMode.HALF_UP));
						
							
						}
						
						detailFactureVenteMP.setMatierePremier(matierePremier);	
						detailFactureVenteMP.setMontantTTC(detailFactureVenteMP.getMontantHT().add(detailFactureVenteMP.getMontantTVA()));

						
						
						
						if(fournisseurMP!=null)
							{
								detailFactureVenteMP.setFournisseurMP(fournisseurMP);
							}
							
							listDetailFactureMP.add(detailFactureVenteMP);
							
							afficher_tableDetailFactureVenteMP(listDetailFactureMP);
							initialiser();
							
							
						}
						
							
					}
						
						
						
					
					
					
					
				}
				
				
			}
			
			
			
			
			
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(521, 390, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserFacture();
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {

		   	 StockFinale=BigDecimal.ZERO;
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			 String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
		 		
	 			Date dateDebut= new Date(date);
				MatierePremier matierePremier=mapMP.get(comboMP.getSelectedItem());
				FournisseurMP fournisseurMP=mapFournisseurMP.get(comboFournisseur.getSelectedItem());
				
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
						List<Object[]> listestockfinale=listestockfinale=detailTransferStockMPDAO.StockFinaleMPMagasinDechet(dateDebut, dateChooserfacture.getDate(), magasin, matierePremier, fournisseurMP);
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
							
						for(int i=0;i<listDetailFactureMP.size();i++)
						{
							
							DetailFactureVenteMP DetailFactureVenteMP=listDetailFactureMP.get(i);
							
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
								
							DetailFactureVenteMP	 detailFactureVenteMP=listDetailFactureMP.get(tableArticle.getSelectedRow());
							
							detailFactureVenteMP.setMatierePremier(matierePremier);	
							detailFactureVenteMP.setQuantite(new BigDecimal(txtquantite.getText()));
							detailFactureVenteMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							detailFactureVenteMP.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).setScale(6, RoundingMode.HALF_UP));
							
							if(chckbxSansTva.isSelected()==true)
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
								}
								
								listDetailFactureMP.set(tableArticle.getSelectedRow(), detailFactureVenteMP);
								afficher_tableDetailFactureVenteMP(listDetailFactureMP);
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
	
	
	
	listDetailFactureMP.remove(tableArticle.getSelectedRow());
	afficher_tableDetailFactureVenteMP(listDetailFactureMP);
	initialiser();
	
}
		  	
		  	
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1057, 515, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(779, 797, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(903, 797, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(662, 734, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(555, 734, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1057, 485, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(779, 735, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(903, 735, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(779, 767, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(903, 767, 134, 26);
		add(txttotalmontantTVA);
		
		                
 

		       		 
		 
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		     
		       		 
		       		   
		       		    layerArticle = new JLayeredPane();
		       		    layerArticle.setBounds(10, 221, 1031, 122);
		       		    add(layerArticle);
		       		    layerArticle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		              
		              // comboArticle.addItem("");
		               
		              
		                
               
		                JLayeredPane layerArticleGratuit = new JLayeredPane();
		                layerArticleGratuit.setBounds(0, 0, 1031, 123);
		                layerArticle.add(layerArticleGratuit);
		                layerArticleGratuit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		    		                   
		    		                   txtquantite = new JTextField();
		    		                   txtquantite.setBounds(41, 68, 99, 26);
		    		                   layerArticleGratuit.add(txtquantite);
		    		                   util.Utils.copycoller(txtquantite);
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
		    		                   
		    		                    lblPrix = new JLabel("Prix  :");
		    		                    lblPrix.setBounds(150, 68, 36, 26);
		    		                    layerArticleGratuit.add(lblPrix);
		           
		           txtcodeMP = new JTextField();
		           txtcodeMP.setBounds(80, 18, 112, 26);
		           layerArticleGratuit.add(txtcodeMP);
		           util.Utils.copycoller(txtcodeMP);
		           txtcodeMP.addKeyListener(new KeyAdapter() {
		           	@Override
		           	public void keyPressed(KeyEvent e) {
		             			if(e.getKeyCode()==e.VK_ENTER)
  			      		{
		             				
		             					
  			      			if(!txtcodeMP.getText().equals(""))
  			      			{
  			      				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
  			      				MatierePremier matierePremier=mapCodeMP.get(txtcodeMP.getText().toUpperCase());
  					    		
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
		           
		           
		           
		           txtcodeMP.setColumns(10);
		           
		             JLabel labellCodeArticle = new JLabel("Code  MP:");
		             labellCodeArticle.setBounds(9, 18, 82, 26);
		             layerArticleGratuit.add(labellCodeArticle);
		             labellCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		             
		             JLabel labelarticle = new JLabel("Libelle :");
		             labelarticle.setBounds(202, 18, 57, 26);
		             layerArticleGratuit.add(labelarticle);
		             labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		               
		     
		               
		                lblMontant = new JLabel("Montant  :");
		                lblMontant.setBounds(355, 68, 64, 26);
		                layerArticleGratuit.add(lblMontant);
		                
		                txtmontant = new JTextField();
		                txtmontant.setBounds(429, 68, 125, 26);
		                layerArticleGratuit.add(txtmontant);
		                txtmontant.setEditable(false);
		                txtmontant.setColumns(10);
		                
		                txtPrix = new JTextField();
		                txtPrix.setBounds(196, 68, 135, 26);
		                layerArticleGratuit.add(txtPrix);
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
		                
		                 
		                     	
		                      
		                      JLabel labelQuantit = new JLabel("QU :");
		                      labelQuantit.setBounds(9, 68, 42, 26);
		                      layerArticleGratuit.add(labelQuantit);
		                      // combofamille.addItem("");
		       
		                           
	
	
		                           
		    		                         
		                      
		                           
		                            comboMP = new JComboBox();
		                            comboMP.setBounds(245, 18, 405, 27);
		                            layerArticleGratuit.add(comboMP);
		                            comboMP.setSelectedIndex(-1);
		                            comboMP.addActionListener(new ActionListener() {
		                            	public void actionPerformed(ActionEvent arg0) {

		                            	 MatierePremier matierePremier=mapMP.get(comboMP.getSelectedItem());
		                            	 
		                            	 if(matierePremier!=null)
		                            	 {
		                            		 txtcodeMP.setText(matierePremier.getCode());
		                            		 
		                            		 
		                            	 }else
		                            	 {
		                            		 txtcodeMP.setText(Constantes.CODE_MP);
		                            	 }
		                            	}
		                            });
		                            AutoCompleteDecorator.decorate(comboMP);
		                            comboMP.setSelectedIndex(-1);
		                            
		                            JLabel lblFournisseur = new JLabel("Fournisseur :");
		                            lblFournisseur.setBounds(671, 18, 79, 24);
		                            layerArticleGratuit.add(lblFournisseur);
		                            lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            
		                             comboFournisseur = new JComboBox();
		                             comboFournisseur.setBounds(749, 19, 229, 24);
		                             layerArticleGratuit.add(comboFournisseur);
		                             comboFournisseur.setSelectedIndex(-1);
		                             stockdisponible = new JLabel("");
		                             stockdisponible.setHorizontalAlignment(SwingConstants.LEFT);
		                             stockdisponible.setFont(new Font("Tahoma", Font.BOLD, 9));
		                             stockdisponible.setForeground(Color.RED);
		                            stockdisponible.setBounds(1047, 258, 475, 69);
		                            add(stockdisponible);
		                            
		                             labelmarge = new JLabel("");
		                            labelmarge.setForeground(Color.RED);
		                            labelmarge.setFont(new Font("Tahoma", Font.BOLD, 14));
		                            labelmarge.setBounds(1047, 299, 600, 30);
		                            add(labelmarge);
		                            
		                             labelprixmin = new JLabel("");
		                            labelprixmin.setForeground(new Color(50, 205, 50));
		                            labelprixmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelprixmin.setBounds(1136, 213, 193, 38);
		                            add(labelprixmin);
		                            
		                             labelPrixMax = new JLabel("");
		                            labelPrixMax.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelPrixMax.setForeground(new Color(255, 0, 0));
		                            labelPrixMax.setBounds(1339, 213, 193, 38);
		                            add(labelPrixMax);
		                            
		                             stockdisponibleoffretherres = new JLabel("");
		                            stockdisponibleoffretherres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffretherres.setForeground(Color.RED);
		                            stockdisponibleoffretherres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffretherres.setBounds(1047, 328, 600, 30);
		                            add(stockdisponibleoffretherres);
		    		                  
		listDechetMP=matierePremiereDAO.findAllDechetMP();	
		listMP=matierePremiereDAO.findAll();		      
		comboMP.addItem("");		 
			for(int i=0;i<listMP.size();i++)	
			{
				
			MatierePremier matierePremier=listMP.get(i);	
			comboMP.addItem(matierePremier.getNom());	
			mapCodeMP.put(matierePremier.getCode(), matierePremier)	;
			mapMP.put(matierePremier.getNom(), matierePremier)	;
				
			}
			
			for(int i=0;i<listDechetMP.size();i++)	
			{
				
			MatierePremier matierePremier=listDechetMP.get(i);	
			comboMP.addItem(matierePremier.getNom());	
			mapCodeMP.put(matierePremier.getCode(), matierePremier)	;
			mapMP.put(matierePremier.getNom(), matierePremier)	;
				
			}
				      
				      
				      
			listFournisseurMP=fournisseurMPDAO.findAll();
			comboFournisseur.addItem("");
			for(int j=0;j<listFournisseurMP.size();j++)
			{
				FournisseurMP fournisseurMP=listFournisseurMP.get(j);	
				comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
				mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
				
			} 	
			
		comboReglement.addItem("");	
		comboReglement.addItem(Constantes.MODE_REGLEMENT_ESPECE);	
		comboReglement.addItem(Constantes.MODE_REGLEMENT_CHEQUE);
		comboReglement.addItem(Constantes.MODE_REGLEMENT_TRAITE);
		comboReglement.addItem(Constantes.MODE_REGLEMENT_VERSEMENT);
		comboReglement.addItem(Constantes.MODE_REGLEMENT_VIREMENT);
		
		 chckbxSansTva = new JCheckBox("Sans TVA");
		chckbxSansTva.setBounds(1068, 217, 82, 23);
		add(chckbxSansTva);
		
		 btnInitialiserTous = new JButton("Initialiser Tous");
		 btnInitialiserTous.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		initialiserTous();
		 	}
		 });
		btnInitialiserTous.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInitialiserTous.setBounds(141, 741, 134, 23);
		btnInitialiserTous.setIcon(imgInit);
		add(btnInitialiserTous);
		
	
		
		lblNum.setVisible(false);
		txtnumcheque.setVisible(false);
		txtcodeMP.setText(Constantes.CODE_MP);    		                
	}
	

	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedItem("");
		combomagasin.setSelectedItem("");
		comboClientpf.setSelectedItem("");
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedItem("");
		
		comboReglement.setSelectedItem("");
		txtnumcheque.setText("");
	}
	
	
	void initialiserTous()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedItem("");
		combomagasin.setSelectedItem("");
		comboClientpf.setSelectedItem("");
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedItem("");
		
		comboReglement.setSelectedItem("");
		txtnumcheque.setText("");
		txtnumBL.setText("");

		
		txtcodeMP.setText("");
comboMP.setSelectedItem("");
comboFournisseur.setSelectedItem("");
	txtPrix.setText("");
	txtquantite.setText("");
	txtmontant.setText("");
	txtcodeMP.requestFocus();
	chckbxSansTva.setSelected(false);
	
	initialiserFacture();
	InitialiseTableau();
		
		
	}
	
	
	

	void initialiser()
	{
		
		txtcodeMP.setText(Constantes.CODE_MP);
comboMP.setSelectedItem("");
comboFournisseur.setSelectedItem("");
	txtPrix.setText("");
	txtquantite.setText("");
	txtmontant.setText("");
	txtcodeMP.requestFocus();
	chckbxSansTva.setSelected(false);
	
	
	}

	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
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
		tableArticle.setModel(modeleChargefixe);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailFactureVenteMP(List<DetailFactureVenteMP> listDetailFactureVenteMP)
	{
		modeleChargefixe =new DefaultTableModel(
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
		tableArticle.setModel(modeleChargefixe);
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

			modeleChargefixe.addRow(ligne);
			i++;
		}
		
		txttotalmontantHT.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantHT)+"");
		txttotalmontantTVA.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTVA)+"");
		txttotalmontantTTC.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTTC)+"");
		txttotalquantite.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(Quantite)+"");
		
		
}
	
	
	
	
	
	
	}



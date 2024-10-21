package presentation.stockMP;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import config.Connect;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueImportationDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ServiceDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TypeSortieDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueImportationDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ServiceDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailTransferStockMP;
import dao.entity.DetailTypeSortie;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueImportation;
import dao.entity.MatierePremier;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;
import dao.entity.Utilisateur;
import dao.entity.service;

import java.awt.Component;
import javax.swing.JTable;


public class AjouterStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleSousType;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private JButton btnIntialiserOF;
	private Map< String, BigDecimal> mapQuantiteMP = new HashMap<>();
	private Map< String, TypeSortie> mapTypeSortie = new HashMap<>();
	private Map< String, DetailTypeSortie> mapDetailTypeSortie = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private Map< String, FournisseurMP> mapFournisseurNonThe = new HashMap<>();


	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private Map< String, service> mapService = new HashMap<>();

	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private List<service> listService =new ArrayList<service>();

	private List<FournisseurMP> listFournisseurNonThe =new ArrayList<FournisseurMP>();

	private List<TypeSortie> listTypeSortie =new ArrayList<TypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortie =new ArrayList<DetailTypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortieTmp =new ArrayList<DetailTypeSortie>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<MatierePremier> listMatierePremierTmp =new ArrayList<MatierePremier>();
	private List<DetailTransferStockMP> listDetailTransfertMp =new ArrayList<DetailTransferStockMP>();
	
	private TransferStockMP transferStock  = new TransferStockMP();
	private TypeSortieDAO typeSortieDAO;
	
	
	private JComboBox<String> comboDepotDestination;
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboMagasinDestination;
	
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private DetailTypeSortieDAO detailTypeSortieDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtCodeMP;
	private JTextField txtQuantite;
	JComboBox comboMP = new JComboBox();
	 JComboBox comboType = new JComboBox();
	 private JTextField txtcodetransfer;
	 JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	 JButton btnmodifier = new JButton();
	 JButton btnsupprimer = new JButton();
	 JLabel lblFournisseur = new JLabel("Fournisseur : ");
	 JComboBox comboFournisseur = new JComboBox();
	 JCheckBox entree = new JCheckBox("Entree");
	 private TypeSortie typeSortie=new TypeSortie();
	 JLabel labelsoustype = new JLabel("Sous Type :");
	 JComboBox combosoustype = new JComboBox();
	 JComboBox comboentresortie = new JComboBox();
	 JButton btnAfficherStock = new JButton();
	 private SubCategorieMPDAO categorieMPDAO;
	 JComboBox comboNumBL = new JComboBox();
	 JCheckBox checkImporterBonReception = new JCheckBox("Importer Bon Reception");
	 JLayeredPane layeredPane_2 = new JLayeredPane();
	 ManqueImportationDAO manqueImportationDAO;
	  JComboBox comboService = new JComboBox();
	  ServiceDAO serviceDAO;
//////////////////////////////////////////////////////////////////////////////////Connecter Au Base Gestion Commande Final    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
private ResultSet rset;
private Statement stx;

private Connection con;	  
private JTextField txtnumBL;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  

private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1635, 696);
        try{
        	serviceDAO=new ServiceDAOImpl();
        	manqueImportationDAO=new ManqueImportationDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	typeSortieDAO= new TypeSortieDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	listMP=matierePremiereDAO.findAll();
        	categorieMPDAO=new SubCategorieMPAOImpl();
        	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
        	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
        	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
        	listService=serviceDAO.findAll();
//////////////////////////////////////////////////////////////////////////////////Connecter Au Base  Gestion Commande Final      ////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*		  
Connect.connecToGestionCommande();
con=Connect.getConnexionCommande();
stx=con.createStatement();
*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
    	comboDepotDestination=new JComboBox();
    	comboDepotDestination.addItem("");
    	comboMagasinDestination=new JComboBox();
    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
    		 comboDepotDestination.addItem(depot.getLibelle());
	     		mapDepotDestionation.put(depot.getLibelle(), depot);
    }else {
    	
    	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotDestionation.put(depot.getLibelle(), depot);
	      			comboDepotDestination.addItem(depot.getLibelle());
	      			i++;
	      		}
    	
    }
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgModifier = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(480, 662, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		refrech();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  // btnIntialiserOF.setEnabled(false);
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(table.getSelectedRow()!=-1)
				  		     		{
				  		     			if(listDetailTransfertMp.size()!=0)
				  		     			{
				  		     				
				  		     				DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(table.getSelectedRow());
				  		     				
				  		     				txtCodeMP.setText(detailTransferStockMP.getMatierePremier().getCode());
				  		     				comboMP.setSelectedItem(detailTransferStockMP.getMatierePremier().getNom());
				  		     				txtQuantite.setText(detailTransferStockMP.getQuantite()+"");
				  		     				if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		     				{
				  		     					if(detailTransferStockMP.getFournisseur()!=null)
					  		     				{
					  		     					comboFournisseur.setSelectedItem(detailTransferStockMP.getFournisseur().getCodeFournisseur());
					  		     					
					  		     				}
				  		     					
				  		     				}else
				  		     				{
				  		     					if(detailTransferStockMP.getFournisseurNonThe()!=null)
					  		     				{
					  		     					comboFournisseur.setSelectedItem(detailTransferStockMP.getFournisseurNonThe().getCodeFournisseur());
					  		     					
					  		     				}
				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
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
				  		   intialiserTableau();
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 265, 1027, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
					  		      
					  	    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				  		     	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	  
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
				  		     	 	{
				  		     	 	Depot 	 depot =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
						  		       if(depot!=null)
						  		       {
						  		    		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		    		 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinDestination.addItem(magasin.getLibelle());
									  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
								  		      
								  		      
								  		    if(listMagasinDechetMP!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasinDechetMP.size())
									  		      		{	
									  		      			Magasin magasin=listMagasinDechetMP.get(j);
									  		      		comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      } 
								  		      
								  		      
								  		      
								  		      
						  		    	   
						  		       }
				  		     	  
				  		     	 	}
				  		     	 
				  		     	 	 }
				  		     	 	}
				  		     	 });
					  		      
				  		     
				  		   
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 236, 1027, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1027, 99);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot");
				  		     	lblMachine.setBounds(10, 25, 46, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotDestination.setBounds(49, 25, 144, 24);
				  		     	 layeredPane.add(comboDepotDestination);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin ");
				  		     	 lblGroupe.setBounds(203, 25, 66, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinDestination.setBounds(261, 26, 182, 24);
				  		     	 layeredPane.add(comboMagasinDestination);
				  		     	 
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(544, 23, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(453, 23, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		   
				  		   txtcodetransfer = new JTextField();
				  		   txtcodetransfer.addKeyListener(new KeyAdapter() {
				  		   	@Override
				  		   	public void keyPressed(KeyEvent e) {
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
}
				  		   });
				  		   txtcodetransfer.setText("");
				  		   txtcodetransfer.setColumns(10);
				  		   txtcodetransfer.setBounds(108, 62, 173, 26);
				  		   layeredPane.add(txtcodetransfer);
				  		   
				  		   JLabel lblCodeTransfre = new JLabel("N\u00B0 Reception  :");
				  		   lblCodeTransfre.setBounds(11, 60, 87, 26);
				  		   layeredPane.add(lblCodeTransfre);
				  		   
				  		   JLabel lblNBl = new JLabel("N\u00B0 BL:");
				  		   lblNBl.setBounds(310, 61, 87, 26);
				  		   layeredPane.add(lblNBl);
				  		   
				  		   txtnumBL = new JTextField();
				  		   txtnumBL.setText("");
				  		   txtnumBL.setColumns(10);
				  		   txtnumBL.setBounds(364, 63, 216, 26);
				  		   layeredPane.add(txtnumBL);
				  		   
				  		   JLabel lblService = new JLabel("Service : ");
				  		   lblService.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		   lblService.setBounds(601, 64, 66, 24);
				  		   layeredPane.add(lblService);
				  		   
				  		    comboService = new JComboBox();
				  		   comboService.setBounds(694, 64, 240, 24);
				  		   layeredPane.add(comboService);
				  		   
				  		
				  		
		 btnValiderTransfer = new JButton("Valider Reception MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(comboDepotDestination.getSelectedItem().equals(""))	{
	  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	  				} else if(dateTransfereChooser.getDate()==null)
	  				{
	  					JOptionPane.showMessageDialog(null, "Il faut entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  					return;
	  				}
                      else if(listDetailTransfertMp.size()==0)
	  				
	  				{
	  					JOptionPane.showMessageDialog(null, "Veuillez entrer les MP  SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  					return;
	  				} 
                      
                      
                      
                      else
	  				{
                    	  
                    	  /*
                    	  
	if(txtnumBL.getText().equals(""))
        		  				
    		  				{
    		  					JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de BL  SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
    		  					return;
    		  				}
                    	  */
                    	
                    		if(txtcodetransfer.getText().equals(""))
        		  				
    		  				{
    		  					JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Reception  SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
    		  					return;
    		  				}else
    		  				{
    		  					
    		  					TransferStockMP transferStockMPTmpReceptionEnAttente=transferStockMPDAO.findTransferByCodeStatut(txtcodetransfer.getText(), Constantes.ETAT_RECEPTION_ENATTENT);	
    		  					TransferStockMP transferStockMPTmpReceptionValider=transferStockMPDAO.findTransferByCodeStatut(txtcodetransfer.getText(), Constantes.ETAT_RECEPTION_VALIDER);
    		  					TransferStockMP transferStockMPTmpReception=transferStockMPDAO.findTransferByCodeStatut(txtcodetransfer.getText(), Constantes.ETAT_TRANSFER_STOCK_AJOUT);
    		  					
    		  					if(transferStockMPTmpReceptionEnAttente==null && transferStockMPTmpReceptionValider==null && transferStockMPTmpReception==null)
    		  					{
    		  						transferStock.setCodeTransfer(txtcodetransfer.getText());
    		  					}else
    		  					{
    		  						JOptionPane.showMessageDialog(null, "le Numero de Reception Déja Existant , Veuillez le Modifier SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
        		  					return;
    		  					}
    		  					
    		  				}
                    	
                    	  
	  					String codeTransfert="";
	  					Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
	  					Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
	  					
	  					service service=mapService.get(comboService.getSelectedItem().toString());
	  					if(service==null)
	  					{
	  						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Service SVPr SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
	  					}else
	  					{
	  						transferStock.setService(service);
	  					}
	  				  				
	  						
		  					transferStock.setCreerPar(AuthentificationView.utilisateur);
		  					transferStock.setDate(new Date());
		  					transferStock.setDateTransfer(dateTransfereChooser.getDate());
		  					transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		  				
		  					transferStock.setDepot(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
		  					//transferStock.setType(detailtypeSortie.getTypesortie());	
		  					
		  					for(int i=0;i<listDetailTransfertMp.size();i++)
		  					{
		  						listDetailTransfertMp.get(i).setQuantite(new BigDecimal(table.getValueAt(i, 3).toString()));
		  						listDetailTransfertMp.get(i).setNumBLReception(txtnumBL.getText());
		  					}
		  				
		  					transferStock.setListDetailTransferMP(listDetailTransfertMp);
		  					transferStockMPDAO.add(transferStock);
		  					
		  					 BigDecimal montantTotal=BigDecimal.ZERO;
                             BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                             BigDecimal montantTotalEmballage=BigDecimal.ZERO;
		  					
		  					for(int i=0;i<listDetailTransfertMp.size();i++)
		  					{

		  						DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(i);
		  						if(detailTransferStockMP.getQuantiteReceptionFacture().compareTo(detailTransferStockMP.getQuantite())==1)
		  						{
		  							
		  							ManqueImportation manqueImportation=new ManqueImportation();
		  							
		  							manqueImportation.setDate(detailTransferStockMP.getTransferStockMP().getDateTransfer());
		  							manqueImportation.setEtat(Constantes.ETAT_INVALIDER);
		  							manqueImportation.setMatierePremier(detailTransferStockMP.getMatierePremier());
		  							if(detailTransferStockMP.getFournisseur()!=null)
		  							{
		  								manqueImportation.setFournisseurMP(detailTransferStockMP.getFournisseur());
		  								
		  							}
		  							
		  							if(detailTransferStockMP.getFournisseurNonThe()!=null)
		  							{
		  								manqueImportation.setFournisseurMP(detailTransferStockMP.getFournisseurNonThe());
		  								
		  							}
		  							
		  							manqueImportation.setNumReception(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
		  							manqueImportation.setQuantite(detailTransferStockMP.getQuantiteReceptionFacture().subtract(detailTransferStockMP.getQuantite()));
		  							 
		  							manqueImportation.setQuantiteFacture(detailTransferStockMP.getQuantiteReceptionFacture());
		  							manqueImportation.setQuantiteReceptione(detailTransferStockMP.getQuantite());
		  							manqueImportation.setPrixUnitaire(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(detailTransferStockMP.getTransferStockMP().getDateTransfer())));
		  							manqueImportation.setMagasin(detailTransferStockMP.getMagasinDestination());
		  							manqueImportationDAO.add(manqueImportation);
		  						}
		  						
		  						
		  						
		  						if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		     			{
		  		     				montantTotalEnvrac=montantTotalEnvrac.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  		     				 
		  		     				
		  		     			}else
		  		     			{
		  		     				montantTotalEmballage=montantTotalEmballage.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  		     				 
		  		     			}
		  		     			montantTotal=montantTotal.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  		     			 
		  						
		  						

		  						
		  						
		  					}
		  					
		  					listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
		  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
		  		     		
		  		     		if(statistiqueFinanciaireMPTmp!=null)
		  		     		{
		  		     			
		  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
		  		     			
		  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
		  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().add(montantTotalEmballage));
		  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().add(montantTotalEnvrac));
		  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
		  		     			statistiqueFinanciaireMP.setCodeTransfer(transferStock.getCodeTransfer());
		  		     			statistiqueFinanciaireMP.setDate(new Date());
		  		     			statistiqueFinanciaireMP.setDateOperation(dateTransfereChooser.getDate());
		  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
		  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
		  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
		  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
		  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
		  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
		  		     			statistiqueFinanciaireMP.setCoutReception(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutReception()) );
		  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
		  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
		  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
		  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
		  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		  		     		 
		  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
		  		     			 
		  		     			
		  		     		} 
		  					
		  					
		  					//intialiserTous();
		  							  							  		
		  					JOptionPane.showMessageDialog(null, "Bon Reception MP Valider Avec Succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
		  					
		  					btnValiderTransfer.setEnabled(false);
	  						
		  					
		  					Map parameters = new HashMap();
		  					
		  					
		  					parameters.put("numTransfer", transferStock.getCodeTransfer());  
		  					parameters.put("numBL", txtnumBL.getText()); 
		  					parameters.put("service", service.getLibelle());
		  					parameters.put("magasinDest", comboMagasinDestination.getSelectedItem());
		  					parameters.put("depDest", comboDepotDestination.getSelectedItem());
		  					parameters.put("dateTransfer", dateTransfereChooser.getDate());
		  					JasperUtils.imprimerBonReceptionMP(listDetailTransfertMp,parameters);

		  					if(listDetailTransfertMp.size()!=0)
		  					{
		  						listDetailTransfertMp.clear();
		  						transferStock = new TransferStockMP();
		  					}else
		  					{
		  						transferStock = new TransferStockMP();
		  					}
		  						
		  					
		  					intialiserTous();
		  					btnIntialiserOF.setEnabled(false);
	  				 
	  			
	  					
	  				}
				
			}
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(288, 662, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnImprimerBonSortie = new JButton("Bon Entre MP ");
		btnImprimerBonSortie.setIcon(imgImprimer);
		btnImprimerBonSortie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				if(listDetailTransfertMp.size()!=0)
				{
					Map parameters = new HashMap();
					parameters.put("numTransfer", listDetailTransfertMp.get(0).getTransferStockMP().getCodeTransfer());                
					parameters.put("magasinDest", comboMagasinDestination.getSelectedItem());
					parameters.put("depDest", comboDepotDestination.getSelectedItem());
					parameters.put("dateTransfer", dateTransfereChooser.getDate());
					JasperUtils.imprimerBonDeclarationReceptionMagasinierMP(listDetailTransfertMp,parameters);
				}
					
			
			
			}
		});
		btnImprimerBonSortie.setBounds(624, 662, 178, 23);
		add(btnImprimerBonSortie);
		 
		   layeredPane_2 = new JLayeredPane();
		 layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_2.setBounds(9, 121, 1027, 104);
		 add(layeredPane_2);
		 
		 txtQuantite = new JTextField();
		 txtQuantite.setBounds(586, 12, 103, 26);
		 layeredPane_2.add(txtQuantite);
		 txtQuantite.setText("");
		 txtQuantite.setColumns(10);
		 
		 JLabel label_2 = new JLabel("Quantite :");
		 label_2.setBounds(526, 12, 102, 24);
		 layeredPane_2.add(label_2);
		 label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  txtCodeMP = new JTextField();
		  txtCodeMP.setBounds(86, 11, 144, 26);
		  layeredPane_2.add(txtCodeMP);
		  txtCodeMP.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyPressed(KeyEvent e) {
		  		

		  						
		  						if(e.getKeyCode()==e.VK_ENTER)
		  			      		{
		  							MatierePremier mp=mapCodeMP.get(txtCodeMP.getText());
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
		  
		  comboMP = new JComboBox();
		  comboMP.setBounds(274, 12, 232, 24);
		  layeredPane_2.add(comboMP);
		  comboMP.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent arg0) {
		  		

			  		   	 		
			  		   	 		if(!comboMP.getSelectedItem().equals(""))
			  		   	 		{
			  		   	 			
			  		   	 			MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
			  		   	 			if(mp!=null)
			  		   	 			{
			  		   	 				mapFournisseur.clear();
			  		   	 				mapFournisseurNonThe.clear();
			  		   	 				txtCodeMP.setText(mp.getCode());
			  		   	 				
			  		   	 				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		   	 				{
			  		   	 					
			  		   	 				comboFournisseur.removeAllItems();	
			  		   	 			comboFournisseur.addItem("");
			  		   	 			for(int i=0;i<listFournisseur.size();i++)
			  		   	 			{
			  		   	 			comboFournisseur.addItem(listFournisseur.get(i).getCodeFournisseur());
			  		   	 			mapFournisseur.put(listFournisseur.get(i).getCodeFournisseur(), listFournisseur.get(i));
			  		   	 				
			  		   	 			}
			  		   	 					
			  		   	 					
			  		   	 				}else
			  		   	 				{
			  		   	 					
			  		   	 				comboFournisseur.removeAllItems();	
				  		   	 			comboFournisseur.addItem("");
				  		   	 			listFournisseurNonThe=fournisseurMPDAO.findByCategorie(mp.getCategorieMp().getSubCategorieMp());
				  		   	 			for(int i=0;i<listFournisseurNonThe.size();i++)
				  		   	 			{
				  		   	 			comboFournisseur.addItem(listFournisseurNonThe.get(i).getCodeFournisseur());
				  		   	 		mapFournisseurNonThe.put(listFournisseurNonThe.get(i).getCodeFournisseur(), listFournisseurNonThe.get(i));
				  		   	 				
				  		   	 			}
				  		   	 					
			  		   	 					
			  		   	 				}
			  		   	 				
			  		   	 				
			  		   	 			}else
			  		   	 			{
			  		   	 				JOptionPane.showMessageDialog(null, "MP Introuvable");
			  		   	 			txtCodeMP.setText(Constantes.CODE_MP);	
			  		   	 				return;
			  		   	 			}
			  		   	 			
			  		   	 			
			  		   	 			
			  		   	 		}else
			  		   	 		{
			  		   	 		txtCodeMP.setText(Constantes.CODE_MP);	
			  		   	 		}
			  		   	 		
			  		   	 	
		  		
		  		
		  		
		  		
		  	}
		  });
		  comboMP.setSelectedIndex(-1);
		  comboMP.addItem("");
		  
		  JLabel label_1 = new JLabel("MP :");
		  label_1.setBounds(240, 12, 37, 24);
		  layeredPane_2.add(label_1);
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  

		  
		  JLabel label = new JLabel("Code MP:");
		  label.setBounds(20, 12, 102, 24);
		  layeredPane_2.add(label);
		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  
		   btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(387, 57, 119, 36);
		  btnAfficherStock.setText("Ajouter");
		  btnAfficherStock.setIcon(imgAjouter);
		  layeredPane_2.add(btnAfficherStock);
		 
		  
		  JButton button_1 = new JButton("Initialiser");
		  button_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  	intialiser();
		  	}
		  });
		  button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button_1.setBounds(528, 57, 112, 36);
		  button_1.setIcon(imgInit);
		  layeredPane_2.add(button_1);
		   btnmodifier = new JButton();
		  btnmodifier.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		boolean dechet_manque = false;
		  		if(table.getSelectedRow()!=-1)
		  		{
		  			
		  			
		  			
		  			if(comboDepotDestination.getSelectedItem().equals(""))	{
		  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
		  				} else if(comboMP.getSelectedItem().equals(""))
		  				{
		  					JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
		  				}else if(txtQuantite.getText().equals(""))
		  				{
		  					JOptionPane.showMessageDialog(null, "Il faut entrer la quantite SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
		  				}else if(dateTransfereChooser.getDate()==null)
		  				{
		  					JOptionPane.showMessageDialog(null, "Il faut entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
		  				}
                            else
		  				
		  				{
		  					
                            	MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
                            	FournisseurMP fournisseurMP=null;
		  		  				FournisseurMP fournisseurMPNonThe=null;
		  		  					
		  		  				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  				{
		  		  				fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
		  		  				}else
		  		  				{
		  		  				fournisseurMPNonThe=mapFournisseurNonThe.get(comboFournisseur.getSelectedItem());
		  		  				}
	  		  					
	  		  					
		  			
		  			
		  			DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(table.getSelectedRow());
		  			 Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
					        
					  Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
	  					
	  					
	  					
	  					
//////////////////////////////////////////////////////////////////////////////////////////////////////Vérifier Les prix Des MP Si Null ou égale à Zero     /////////////////////////////////////////////////////////////////////////////////////////////
  	/*	  				
  		  				
boolean PrixMPZeroOuNull=false;
String Message="Veuillez Entrer le Prix MP Suivant SVP :"+"\n";



if(mp.getPrix()==null)
{

PrixMPZeroOuNull=true;
Message=Message+mp.getCode()+"\n";

}else
{

if(mp.getPrix().compareTo(BigDecimal.ZERO)==0)
{

PrixMPZeroOuNull=true;
Message=Message+mp.getCode()+"\n";

}



}


if(PrixMPZeroOuNull==true)
{

JOptionPane.showMessageDialog(null, Message, "Erreur Prix",JOptionPane.WARNING_MESSAGE);
return;

}


*/

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  					
	  					
	  					
	  					

		  							detailTransferStockMP.setMagasinDestination(magasinDestination);
		  							detailTransferStockMP.setMagasinSouce(magasinSource);
		  							detailTransferStockMP.setMatierePremier(mp);
		  							detailTransferStockMP.setQuantite(new BigDecimal(txtQuantite.getText()));
		  							detailTransferStockMP.setQuantiteReceptionFacture(new BigDecimal(txtQuantite.getText()));
		  							detailTransferStockMP.setTransferStockMP(transferStock);
		  						
		  							if(fournisseurMP!=null)
		  		  					{
		  								detailTransferStockMP.setFournisseur(fournisseurMP);
		  		  					}
		  			  				   
		  		  					if(fournisseurMPNonThe!=null)
		  		  					{
		  		  					detailTransferStockMP.setFournisseurNonThe(fournisseurMPNonThe);
		  		  					}		  		  					
		  							listDetailTransfertMp.set(table.getSelectedRow(), detailTransferStockMP);
		  							//detailTransfertMPDAO.edit(detailTransferStockMP);
		  							
		  							
		  							
		  							afficher_tableMP(listDetailTransfertMp);
	  			  					intialiser();
		  							

		
	  						
	  						
	  					
	  						


	  					
	  					
	  					
		  				}	
	  					
	  					
		  		}
		  		
		  	}
		  });
		  btnmodifier.setBounds(1057, 278, 60, 23);
		  btnmodifier.setIcon(imgModifier);
		  add(btnmodifier);
		  
		   btnsupprimer = new JButton();
		  btnsupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		if(table.getSelectedRow()!=-1)
		  		{
		  			
		  		    DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(table.getSelectedRow());
		  			
		  			listDetailTransfertMp.remove(table.getSelectedRow());
		  			//detailTransfertMPDAO.delete(detailTransferStockMP.getId());
		  			afficher_tableMP(listDetailTransfertMp);
	  				intialiser();
		  			
		  		}
		  		
		  		
		  	}
		  });
		  btnsupprimer.setBounds(1057, 307, 60, 23);
		  btnsupprimer.setIcon(imgSupprimer);
		  add(btnsupprimer);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		


		  		  				if(comboDepotDestination.getSelectedItem().equals(""))	{
		  		  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  				} else if(comboMP.getSelectedItem().equals(""))
		  		  				{
		  		  					JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  				}else if(txtQuantite.getText().equals(""))
		  		  				{
		  		  					JOptionPane.showMessageDialog(null, "Il faut entrer la quantite SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  				}else if(dateTransfereChooser.getDate()==null)
		  		  				{
		  		  					JOptionPane.showMessageDialog(null, "Il faut entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  				}else
		  		  				
		  		  				{
		  		  				MatierePremier mp=mapMP.get(comboMP.getSelectedItem());


		  		  					FournisseurMP fournisseurMP=null;
		  		  				FournisseurMP fournisseurMPNonThe=null;
		  		  					
		  		  				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  				{
		  		  				fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem().toString());
		  		  				}else
		  		  				{
		  		  				fournisseurMPNonThe=mapFournisseurNonThe.get(comboFournisseur.getSelectedItem().toString());
		  		  				}
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
//////////////////////////////////////////////////////////////////////////////////////////////////////Vérifier Les prix Des MP Si Null ou égale à Zero     /////////////////////////////////////////////////////////////////////////////////////////////
			  		  				
	/*		  		  				
boolean PrixMPZeroOuNull=false;
String Message="Veuillez Entrer le Prix MP Suivant SVP :"+"\n";





if(mp.getPrix()==null)
{

PrixMPZeroOuNull=true;
Message=Message+mp.getCode()+"\n";

}else
{

if(mp.getPrix().compareTo(BigDecimal.ZERO)==0)
{

PrixMPZeroOuNull=true;
Message=Message+mp.getCode()+"\n";

}



}


if(PrixMPZeroOuNull==true)
{

JOptionPane.showMessageDialog(null, Message, "Erreur Prix",JOptionPane.WARNING_MESSAGE);
return;

}


*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  					{
		  		  						if(fournisseurMP==null)
		  		  						{
		  		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur SVP");
		  		  							return;
		  		  						}
		  		  					}
		  		  					
		  		  					
		  		  					if(mp==null)
		  		  					{
		  		  						JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  						return;
		  		  					}
		  		  					
		  		  					
		  		  					        Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		  		  					        
		  		  					        Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
		  		  					        

			  		  						DetailTransferStockMP detailtransfertmp=new DetailTransferStockMP();
			  			  					
			  			  					detailtransfertmp.setMagasinDestination (magasinDestination);
			  			  				    detailtransfertmp.setMagasinSouce(magasinSource);
			  			  					detailtransfertmp.setMatierePremier(mp);
			  			  					detailtransfertmp.setQuantite(new BigDecimal(txtQuantite.getText()));
			  			  				detailtransfertmp.setQuantiteReceptionFacture(new BigDecimal(txtQuantite.getText()));
			  			  					detailtransfertmp.setTransferStockMP(transferStock);
			  			  					//detailTransfertMPDAO.add(detailtransfertmp);
			  			  				if(fournisseurMP!=null)
			  		  					{
			  			  				 detailtransfertmp.setFournisseur(fournisseurMP);
			  		  					}
			  			  				   
			  		  					if(fournisseurMPNonThe!=null)
			  		  					{
			  		  					detailtransfertmp.setFournisseurNonThe(fournisseurMPNonThe);
			  		  					}
			  			  					listDetailTransfertMp.add(detailtransfertmp);
			  			  					afficher_tableMP(listDetailTransfertMp);
			  			  					intialiser();
			  			  					
			  			  			

		  		  					
		  		  					
		  		  					


		  		  					
		  		  					
		  		  				}
		  		  			  }
		  });
		for(int j=0;j<listMP.size();j++)
		{
			MatierePremier mp=listMP.get(j);
			comboMP.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
		}	
		

		


		
		
		txtCodeMP.setText(Constantes.CODE_MP);
		
		 lblFournisseur = new JLabel("Fournisseur : ");
		 lblFournisseur.setBounds(699, 12, 91, 24);
		 layeredPane_2.add(lblFournisseur);
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		  
		   comboFournisseur = new JComboBox();
		   comboFournisseur.setBounds(777, 13, 232, 24);
		   layeredPane_2.add(comboFournisseur);
		
		comboFournisseur.addItem("");
		comboFournisseur.setSelectedItem("");
		
		
		comboService.addItem("");
		for(int i=0;i<listService.size();i++)
		{
			
			service service=listService.get(i);
			comboService.addItem(service.getLibelle());
			mapService.put(service.getLibelle(), service);
		}
		comboService.setSelectedItem("");
		
		
		txtcodetransfer.setText(CODE_RECEPTION);
		
	}
	
	


	
	
	void intialiserTous()
	{
		
		comboDepotDestination.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		//txtRefTransfere.setText("");
		dateTransfereChooser.setCalendar(null);
		txtQuantite.setText("");
		txtCodeMP.setText("");
		txtCodeMP.setText(Constantes.CODE_MP);	
		comboMP.setSelectedItem("");
		
		intialiserTableau();
		
		btnmodifier.setEnabled(true);
		btnsupprimer.setEnabled(true);
	 	btnValiderTransfer.setEnabled(true);
	 	txtcodetransfer.setText(CODE_RECEPTION);
	 	comboType.setSelectedIndex(-1);
	 	comboentresortie.setSelectedItem("");
		checkImporterBonReception.setSelected(false);
		comboNumBL.removeAllItems();
		comboNumBL.addItem("");
		comboNumBL.setVisible(false);
	 	layeredPane_2.setVisible(true);
	 	btnmodifier.setVisible(true);
	   		 btnsupprimer.setVisible(true);
	   	comboService.setSelectedItem("");	
	   	txtnumBL.setText("");
	   		 
	   		 
	}
	
	void intialiser()
	{
	
		txtQuantite.setText("");
		txtCodeMP.setText("");
		comboMP.setSelectedItem("");
		
		comboFournisseur.setSelectedIndex(-1);
		
		txtCodeMP.setText(Constantes.CODE_MP);	
	}
	
	
	
	void refrech()
	{
		if(listDetailTransfertMp.size()!=0)
		{
			listDetailTransfertMp.clear();
			
			transferStockMPDAO.ViderSession();
			transferStock = new TransferStockMP();
		}else
		{
			transferStockMPDAO.ViderSession();
			transferStock = new TransferStockMP();
		}
			
		
		intialiserTous();
		btnIntialiserOF.setEnabled(false);
		
		
		
	}
	
	void afficher_tableMP(List<DetailTransferStockMP> listDetailTransfertMP)
	{

		
		intialiserTableau();
	String fournisseur="";
	        
		  int i=0;
			while(i<listDetailTransfertMP.size())
			{	
				fournisseur="";
				
				DetailTransferStockMP detailtransfertMP=listDetailTransfertMP.get(i);
				
				
				
				
			if(detailtransfertMP.getFournisseur()!=null)
			{
				if(!detailtransfertMP.getFournisseur().getCodeFournisseur().equals(""))
				{
					fournisseur=detailtransfertMP.getFournisseur().getCodeFournisseur();
				}
				

				
			}
			
			if(detailtransfertMP.getFournisseurNonThe()!=null)
			{
				if(!detailtransfertMP.getFournisseurNonThe().getCodeFournisseur().equals(""))
				{
					fournisseur=detailtransfertMP.getFournisseurNonThe().getCodeFournisseur();
				}
				

				
			}
			
			
				
			Object []ligne={detailtransfertMP.getMatierePremier().getCode(),detailtransfertMP.getMatierePremier().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(detailtransfertMP.getQuantite()),detailtransfertMP.getQuantite(),fournisseur};
			modeleMP.addRow(ligne);
			
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	
	
	
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<listMatierePremierTmp.size();i++){
		MatierePremier matierePremier =listMatierePremierTmp.get(i);
		quantite=mapQuantiteMP.get(matierePremier.getCode());
		
		
		
		if(quantite.compareTo(BigDecimal.ZERO)>0){
			
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			Magasin magasinSource =mapMagasinSource.get(comboMagasinDestination.getSelectedItem());
			Magasin magasinDestination=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
			//Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
			
			StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
			StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
			
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		
		
		prixStockDestination=stockMPDestination.getPrixUnitaire();
		prixStockSource=stockMPSource.getPrixUnitaire();
		
		BigDecimal	prixDest=prixStockDestination.multiply(stockMPDestination.getStock());
		BigDecimal	prixStock=prixStockSource.multiply(quantite);
		
		prixMoyen=prixDest.add(prixStock);
		if(sommeStock.compareTo(BigDecimal.ZERO)  >0)
		prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		else 
			prixMoyen=BigDecimal.ZERO;
		//stockMPDestination.setPrixUnitaire(prixMoyen);
		
		stockMPDestination.setStock(stockDestination);
		stockMPSource.setStock(stockSource);
		
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixStockSource);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
	}
		//		else {
//		JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
//	}
		
		
	}
	
	return listDetailTransferStockMP;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Quantité Facture","Quantité Receptionne","Fournisseur"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
    table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}

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

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
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
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ServiceDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.DetailTypeSortie;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
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


public class BonSortieStockMP extends JLayeredPane implements Constantes{
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
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private List<TypeSortie> listTypeSortie =new ArrayList<TypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortie =new ArrayList<DetailTypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortieTmp =new ArrayList<DetailTypeSortie>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<MatierePremier> listMatierePremierTmp =new ArrayList<MatierePremier>();
	private List<DetailTransferStockMP> listDetailTransfertMp =new ArrayList<DetailTransferStockMP>();
	
	private TransferStockMP transferStock  = new TransferStockMP();;
	private TypeSortieDAO typeSortieDAO;
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
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
	private JTextField txtdesignation;
	private JTextField txttype;
	 JComboBox comboType = new JComboBox();
	 private JTextField txtcodetransfer;
	 JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	 JButton btnmodifier = new JButton();
	 JButton btnsupprimer = new JButton();
	 JLabel lblFournisseur = new JLabel("Fournisseur : ");
	 JComboBox comboFournisseur = new JComboBox();
	
	 private JTextField txtsoustype;
	 private JTable tableSousTypeSortie;
	 private TypeSortie typeSortie=new TypeSortie();
	 JLabel labelsoustype = new JLabel("Sous Type :");
	 JComboBox combosoustype = new JComboBox();
	
	 JButton btnValider = new JButton();
	 private SubCategorieMPDAO categorieMPDAO;
	 private List<Magasin> listMagasinDechet = new ArrayList<Magasin>(); 
	 JComboBox comboService = new JComboBox();
	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 
	 private List <Object[]> Mindate=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
		private SubCategorieMPDAO subcategoriempdao;
		private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
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
		private DetailTransferMPDAO detailTransferStockMPDAO;
		ServiceDAO serviceDAO;
		private Map< String, service> mapService = new HashMap<>();
		private List<service> listService =new ArrayList<service>();
	 
		 private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		 private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
	 
	 
	 
	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public BonSortieStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1689, 696);
        try{
        	
        	serviceDAO=new ServiceDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	typeSortieDAO= new TypeSortieDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	  detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	categorieMPDAO=new SubCategorieMPAOImpl();
        	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
        	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
        	
        	listService=serviceDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        JLayeredPane layeredPane_2 = new JLayeredPane();
		 layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_2.setBounds(9, 121, 1136, 104);
		 add(layeredPane_2);
		 
		 comboMP = new JComboBox();
		 comboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

		 		 Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		  		
		  			
		  			
		 		
		 		
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=mapMP.get(comboMP.getSelectedItem());
  		 				if(matierePremier!=null)
  		 				{
  		 					txtCodeMP.setText(matierePremier.getCode());
  		 				}
  		 			
  		 				
  		 					
  		 				
  		 			}else
  		 			{
  		 				
  		 				if(magasin!=null)
  		 				{
  		 					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
  	  			  			{
  	  			  				
  	  							 txtCodeMP.setText(CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
  	  							 
  	  							
  	  			  			}else
  	  			  			{
  	  			  			 txtCodeMP.setText(Constantes.CODE_MP);
  	  			  				
  	  			  			}
  		 				}
  		 			
  		 				
  		 				
  		 				
  		 				
  		 			}
  		 			
  		 				
  		 			
  		 		}else
  		 		{
  		 			
  		 			if(magasin!=null)
		 				{
		 					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
	  			  			{
	  			  				
	  							 txtCodeMP.setText(CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
	  							 
	  							
	  			  			}else
	  			  			{
	  			  			 txtCodeMP.setText(Constantes.CODE_MP);
	  			  				
	  			  			}
		 				}
  		 			
  		 			
  		 		}
  		 		
  		 		
  		 		
  		 		
  		 		
  		 	
		 		
		 		
		 		
		 	
		  		
		  	
		 		
		 		
		 	}
		 });
		
		 comboMP.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
}
		 });


		 
		 comboMP.setBounds(267, 15, 249, 24);
		layeredPane_2.add(comboMP);  
        AutoCompleteDecorator.decorate(comboMP);
        
        
        
    	comboDepotSource=new JComboBox();
    	comboMagasinSource=new JComboBox();
    	comboMagasinSource.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
  		  		
  		  		if(comboMagasinSource.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!comboMagasinSource.getSelectedItem().equals(""))
  		  			{
  		  			 Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
  		  			listMP.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  			
		  			if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
  		  			{
		  				 listMP=matierePremiereDAO.findAllDechetMP();
						 txtCodeMP.setText(CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE);
							for(int j=0;j<listMP.size();j++)
							{
								MatierePremier mp=listMP.get(j);
								comboMP.addItem(mp.getNom());
								mapMP.put(mp.getNom(), mp);
								mapCodeMP.put(mp.getCode(), mp);
							} 
	  		  				
		  				
  		  			}else
  		  			{

	  		  			listMP=matierePremiereDAO.findAll();
	  		  		 txtCodeMP.setText(CODE_MP);
	  		  		for(int j=0;j<listMP.size();j++)
					{
						MatierePremier mp=listMP.get(j);
						comboMP.addItem(mp.getNom());
						mapMP.put(mp.getNom(), mp);
						mapCodeMP.put(mp.getCode(), mp);
					} 
	  		  			
  		  			}
		  			
  		  				
  		  				
  		  			}else
  		  			{
  		  			
  		  			listMP.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listMP.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
    			
    			
    		}
    	});
    	comboMagasinSource.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
}
    	});


	    
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
				  		     btnIntialiserOF.setBounds(402, 662, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		refrech();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnIntialiserOF.setEnabled(false);
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
				  		     				if(detailTransferStockMP.getFournisseur()!=null)
				  		     				{
				  		     					comboFournisseur.setSelectedItem(detailTransferStockMP.getFournisseur().getCodeFournisseur());
				  		     					
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
				  		      
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,MAGASIN_CODE_CATEGORIE_STOCKAGE);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
					  		      
					  		      
					  		      
					  	    comboDepotSource.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				  		     	  List<Magasin> listMagasinDechet=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinSource.removeAllItems();
				  		     	 comboMagasinSource.addItem("");
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotSource.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotSource.get(comboDepotSource.getSelectedItem());
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		     	 listMagasinDechet= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinSource.addItem(magasin.getLibelle());
								  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
							  		      
							  		      
      if(listMagasinDechet!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasinDechet.size())
								  		      		{	
								  		      			Magasin magasin=listMagasinDechet.get(j);
								  		      			comboMagasinSource.addItem(magasin.getLibelle());
								  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
							  		      
							  		      
							  		      
							  		      
				  		     	 	 }
				  		     	 	}
				  		     	 });
					  		      
				  		     
				  		     	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 236, 1027, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1136, 99);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot");
				  		     	lblMachine.setBounds(10, 25, 46, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(49, 25, 144, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin ");
				  		     	 lblGroupe.setBounds(203, 25, 66, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(261, 26, 182, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(544, 23, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(453, 23, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel lblType = new JLabel("Type :");
				  		  lblType.setBounds(709, 25, 46, 24);
				  		  layeredPane.add(lblType);
				  		  lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   comboType = new JComboBox();
				  		   comboType.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent arg0) {
				  		   		
				  		   		if(comboType.getSelectedIndex()!=-1)
				  		   		{
				  		   			/*
				  		   		if(comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_DECHET) || comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_MANQUE) || comboType.getSelectedItem().equals(Constantes.SOUS_TYPE_SORTIE_DEFINITIF)  || comboType.getSelectedItem().equals(Constantes.SOUS_TYPE_SORTIE_ENATTENT))
				  		   		{
				  		   			
				  		   			lblFournisseur.setVisible(true);
				  		   			 comboFournisseur.setVisible(true);
				  		   			
				  		   		}	else
				  		   		{
				  		   			
				  		   		lblFournisseur.setVisible(false);
			  		   			comboFournisseur.setVisible(false);
			  		   		comboFournisseur.setSelectedIndex(-1);
			  		   		
				  		   			
				  		   		}
				  		   		*/
				  		   		
				  		   		
				  		   		if(!comboType.getSelectedItem().equals(""))
				  		   		{
				  		   			
				  		   			if(!comboType.getSelectedItem().toString().trim().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT.trim()))
				  		   			{
				  			   			
						  		   		labelsoustype.setVisible(true);
					  		   			combosoustype.removeAllItems();
					  		   		combosoustype.addItem("");
					  		   		combosoustype.setVisible(true);
						  		   	TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(comboType.getSelectedItem().toString());	
						  		   		if(typeSortieTmp!=null)	
						  		   		{
						  		   		listSousTypeSortieTmp=detailTypeSortieDAO.listeDetailTypeSortieByTypeSortie(typeSortieTmp);
						  		   		if(listSousTypeSortieTmp.size()!=0)
						  		   		{
						  		   			
						  		   			for(int i=0;i<listSousTypeSortieTmp.size();i++)
						  		   			{
						  		   				
						  		   				DetailTypeSortie detailTypeSortie=listSousTypeSortieTmp.get(i);
						  		   				
						  		   			combosoustype.addItem(detailTypeSortie.getSousType());
						  		   		mapDetailTypeSortie.put(detailTypeSortie.getSousType().toString().trim(), detailTypeSortie);
						  		   			}
						  		   			
						  		   			
						  		   		}
						  		   		
						  		   		}
				  		   			}else
				  		   			{
				  		   				

					  		   			labelsoustype.setVisible(false);
					  		   			combosoustype.removeAllItems();
					  		   		combosoustype.addItem("");
					  		   	combosoustype.setSelectedItem("");
					  		   		combosoustype.setVisible(false);
					  		   			

			  			   			
					  		   		
					  		   	TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(comboType.getSelectedItem().toString());	
					  		   		if(typeSortieTmp!=null)	
					  		   		{
					  		   		listSousTypeSortieTmp=detailTypeSortieDAO.listeDetailTypeSortieByTypeSortie(typeSortieTmp);
					  		   		if(listSousTypeSortieTmp.size()!=0)
					  		   		{
					  		   			
					  		   			for(int i=0;i<listSousTypeSortieTmp.size();i++)
					  		   			{
					  		   				
					  		   				DetailTypeSortie detailTypeSortie=listSousTypeSortieTmp.get(i);
					  		   				
					  		   		mapDetailTypeSortie.put(detailTypeSortie.getSousType().trim(), detailTypeSortie);
					  		   			}
					  		   			
					  		   			
					  		   		}
					  		   		
					  		   		}
			  		   			
					  		   		
				  		   				
				  		   			}
				  	
				  		   			
				  		   			
				  		   		}else
				  		   		{
				  		   			labelsoustype.setVisible(false);
				  		   			combosoustype.removeAllItems();
				  		   		combosoustype.setVisible(false);
				  		   			
				  		   			
				  		   		}
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   			
				  		   		}
				  		   		
				  		   	}
				  		   });
				  		   comboType.setBounds(748, 25, 258, 24);
				  		   layeredPane.add(comboType);
				  		   
				  		   txtcodetransfer = new JTextField();
				  		   txtcodetransfer.addKeyListener(new KeyAdapter() {
				  		   	@Override
				  		   	public void keyPressed(KeyEvent e) {
				  		   		
				  		   	if (e.getKeyCode() == e.VK_ENTER)
					  		{
				  		   		
				  		   		
				  		   		if(!txtcodetransfer.getText().equals(""))
				  		   		{
				  		   			
				  		   			if(txtcodetransfer.getText().contains(ETAT_TRANSFER_STOCK_ENTRE))
				  		   			{
				  		   			 transferStock=transferStockMPDAO.findTransferByCodeStatut(txtcodetransfer.getText(),Constantes.ETAT_TRANSFER_STOCK_ENTRE);
				  		   			}else if(txtcodetransfer.getText().contains(ETAT_TRANSFER_STOCK_SORTIE))
				  		   			{
				  		   			 transferStock=transferStockMPDAO.findTransferByCodeStatut(txtcodetransfer.getText(),Constantes.ETAT_TRANSFER_STOCK_SORTIE);
				  		   			}
				  		   		
				  		   		 
				  		   		 
				  		   		if(transferStock!=null)
				  		   		{
				  		  		 if(transferStock.getDepot().getCode().equals(utilisateur.getCodeDepot()))
				  		   		 {
				  		   		 Depot depot=mapDepotSource.get(transferStock.getDepot().getLibelle());
					  		   		comboDepotSource.setSelectedItem(depot.getLibelle());
					  		   		dateTransfereChooser.setDate(transferStock.getDateTransfer());
					  		   		comboType.setSelectedItem(transferStock.getType().getLiblle());
					  		   		txtdesignation.setText(transferStock.getDesignation());
					  		   		listDetailTransfertMp=detailTransfertMPDAO.findByTransferStockMP(transferStock.getId());
					  		   		afficher_tableMP(listDetailTransfertMp);
					  		  
					  		   	btnValiderTransfer.setEnabled(false);
					  		   	btnmodifier.setEnabled(false);
					  		   	btnsupprimer.setEnabled(false);
					  		   	btnIntialiserOF.setEnabled(true); 
				  		   			 
				  		   		 }else
				  		   		 {
				  		   			 
				  		   			 JOptionPane.showMessageDialog(null, "le Code de transfert utilisé n'existe pas dans ce depot ");
				  		   			 return;
				  		   		 }
				  		   			
				  		   		}else
				  		   		{
				  		   			
				  		   		 JOptionPane.showMessageDialog(null, "le Code de transfert introuvable");
			  		   			 return;
				  		   		}
				  		  
				  		   		  
					  		   	
				  		   		}
                               
				  		   
				  		   		}
				  		   		
				  		  
				  		   		
				  		   	}
				  		   });
				  		   txtcodetransfer.setText("");
				  		   txtcodetransfer.setColumns(10);
				  		   txtcodetransfer.setBounds(108, 62, 173, 26);
				  		   layeredPane.add(txtcodetransfer);
				  		   
				  		   JLabel lblCodeTransfre = new JLabel("Code Transf\u00E8re :");
				  		   lblCodeTransfre.setBounds(11, 60, 87, 26);
				  		   layeredPane.add(lblCodeTransfre);
				  		   
				  		    labelsoustype = new JLabel("Sous Type :");
				  		   labelsoustype.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   labelsoustype.setBounds(681, 60, 74, 24);
				  		   layeredPane.add(labelsoustype);
				  		   
				  		    combosoustype = new JComboBox();
				  		   combosoustype.setBounds(748, 60, 258, 24);
				  		   layeredPane.add(combosoustype);
				  		   
				  		   JLabel lblService = new JLabel("Service");
				  		   lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblService.setBounds(301, 61, 66, 24);
				  		   layeredPane.add(lblService);
				  		   
				  		     comboService = new JComboBox();
				  		   comboService.setBounds(359, 62, 209, 24);
				  		   layeredPane.add(comboService);
				  		
		 btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(comboDepotSource.getSelectedItem().equals(""))	{
	  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	  				} else if(dateTransfereChooser.getDate()==null)
	  				{
	  					JOptionPane.showMessageDialog(null, "Il faut entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  					return;
	  				}else if(txtdesignation.getText().equals(""))
	  				{
	  					JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  					return;
	  				}else if(listDetailTransfertMp.size()==0)
	  				
	  				{
	  					JOptionPane.showMessageDialog(null, "Veuillez entrer les MP  SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  					return;
	  				}else
	  				{
	  					if(!comboType.getSelectedItem().toString().trim().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT.trim()))
	  		   			{
	  						 if(combosoustype.getSelectedIndex()==-1)
	  		  				{
	  		  					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Sous type Sortie SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  		  					return;
	  		  					
	  		  				}
	  		   			}
	  					
	  					
	  				service service=mapService.get(comboService.getSelectedItem().toString());
	  					
	  					String codeTransfert="";
	  					Depot depot=mapDepotSource.get(comboDepotSource.getSelectedItem());
	  					
	  					listMagasinDechet = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
	  				
	  						codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_SORTIE);
					 
	  					
	  					 	
	  					DetailTypeSortie detailtypeSortie=mapDetailTypeSortie.get(combosoustype.getSelectedItem().toString().trim());
	  					
	  					
	  					TypeSortie typeSortie=mapTypeSortie.get(comboType.getSelectedItem().toString());
	  					if(typeSortie!=null)
	  					{
	  						transferStock.setCodeTransfer(codeTransfert);
		  					transferStock.setCreerPar(AuthentificationView.utilisateur);
		  					transferStock.setDate(new Date());
		  					transferStock.setDateTransfer(dateTransfereChooser.getDate());
		  				
		  						if(typeSortie.getLiblle().trim().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT.trim()))
		  						{
		  							
		  							transferStock.setStatut(Constantes.ETAT_SORTIE_ENATTENT);
		  							detailtypeSortie=mapDetailTypeSortie.get(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT.trim());
		  							
		  						}else
		  						{
		  							
		  							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
		  							
		  						}
		  						
						 
		  					if(service!=null)
		  					{
		  						transferStock.setService(service);
		  					}
		  					
		  					transferStock.setType(typeSortie);		  					
		  					transferStock.setDepot(mapDepotSource.get(comboDepotSource.getSelectedItem()));
		  					//transferStock.setType(detailtypeSortie.getTypesortie());
		  					transferStock.setSoustype(detailtypeSortie);
		  					transferStock.setDesignation(txtdesignation.getText());
		  					//transferStock.setListDetailTransferMP(listDetailTransfertMp);
		  					transferStockMPDAO.add(transferStock);
		  					
		  					BigDecimal montantTotal=BigDecimal.ZERO;
		  	                BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
		  	                BigDecimal montantTotalEmballage=BigDecimal.ZERO;
		  					for(int i=0;i<listDetailTransfertMp.size();i++)
		  					{
		  						
		  						DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(i);
		  						if(detailtypeSortie.getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
		  						{
		  							if(listMagasinDechet.size()!=0)
		  							{
		  								detailTransferStockMP.setMagasinDestination(listMagasinDechet.get(0));
		  							}
		  						}
		  						
		  							detailTransfertMPDAO.add(detailTransferStockMP);
		  						
		  						
		  							if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  			     			{
		  			     				montantTotalEnvrac=montantTotalEnvrac.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  			     				 
		  			     				
		  			     			}else
		  			     			{
		  			     				montantTotalEmballage=montantTotalEmballage.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  			     				 
		  			     			}
		  			     			montantTotal=montantTotal.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
		  			     
		  							
		  							
		  							
		  					}
		  					
		  					
		  					txtcodetransfer.setText(codeTransfert);
		  					//intialiserTous();
		  					Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		  					
		  					if(!detailtypeSortie.getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
		  					{
		  						
		  						for(int i=0;i<listDetailTransfertMp.size();i++)
			  					{
			  						
			  						
			  							MatierePremier mp=listDetailTransfertMp.get(i).getMatierePremier();
			  							StockMP stockMP=null;
			  							if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					  					{
				  							stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP (mp.getId(), magasin.getId(),listDetailTransfertMp.get(i).getFournisseur().getId());	
					  					}else
					  					{
					  						stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());	
					  					}	
				  						
				  						
				  						stockMP.setStock(stockMP.getStock().subtract(listDetailTransfertMp.get(i).getQuantite()));
						  				stockMPDAO.edit(stockMP);
								 
			  						
					  				
					  				
			  						
			  					}
		  						
		  						
		  						 
		  							
		  							listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
		  				     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
		  				     		
		  				     		if(statistiqueFinanciaireMPTmp!=null)
		  				     		{
		  				     			
		  				     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
		  				     			
		  				     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
		  				     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
		  				     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
		  				     		 
		  				     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
		  				     			  
		  				     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
		  				     		  
		  				     			statistiqueFinanciaireMP.setCodeTransfer(transferStock.getCodeTransfer());
		  				     			statistiqueFinanciaireMP.setDate(new Date());
		  				     			statistiqueFinanciaireMP.setDateOperation(transferStock.getDateTransfer());
		  				     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
		  				     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
		  				     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
		  				     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
		  				     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());		  				     			
		  				     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
		  				     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie().add(montantTotal));
		  				     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
		  				     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
		  				     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour().add(montantTotal));
		  				     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
		  				     		 
		  				     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
		  				     			 
		  				     			
		  				     		} 
		  						 
		  						
		  						
		  						
		  						
		  						
		  					}
		  			
			  				
		  					JOptionPane.showMessageDialog(null, "Bon Sortie MP Valider Avec Succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
		  					
		  					btnValiderTransfer.setEnabled(false);


		  					

		  		  		  	if(transferStock.getId()>0){
		  		  		  		
		  		  		  		if(!comboDepotSource.getSelectedItem().equals("")){
		  		  		  			
		  		  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  		  	String date=dateFormat.format(transferStock.getDateTransfer());
		  		  		 
		  					// List<DetailTransferStockMP> listDetailTransferStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStock.getId());
		  					 if(listDetailTransfertMp.size()!=0)
		  					 {
		  						
		  						 
		  						    DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(0);
		  							Map parameters = new HashMap();
		  							parameters.put("numTransfer", transferStock.getCodeTransfer());
		  							
		  								if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
		  								{
		  									parameters.put("depot",   "Dépot Source      :");
		  									parameters.put("magasin", "Magasin Source   :");
		  									parameters.put("titre", "Bon de Sortie En Attente de la matière première");
		  									parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
		  									parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
		  								}else
		  								{
		  									parameters.put("depot",   "Dépot Source      :");
		  									parameters.put("magasin", "Magasin Source   :");
		  									parameters.put("titre", "Bon de Sortie de la matière première");
		  									parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
		  									parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
		  								}
		  								
		  								 
		  							
		  						
		  							parameters.put("dateTransfer", date);
		  							parameters.put("designation", detailTransferStockMP.getTransferStockMP().getDesignation().toUpperCase());
		  							if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType()!=null)
		  							{
		  								parameters.put("type", detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().toUpperCase());
		  							}else
		  							{
		  								parameters.put("type", detailTransferStockMP.getTransferStockMP().getType().getLiblle().toUpperCase());
		  							}
		  							
		  							JasperUtils.imprimerBonSortieMP(listDetailTransfertMp,parameters,transferStock.getCodeTransfer());
		  							
		  							btnIntialiserOF.setEnabled(true);
		  							btnValiderTransfer.setEnabled(false);
		  							
		  						 
		  					 }else
		  					 {
		  						 JOptionPane.showMessageDialog(null, "Il faut saisir les matieres premiere entrer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		  					 }
		  					
		  		  		  		}else {
		  		  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		  		  		  		}
		  					
		  				//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  		  	}else {
		  		  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
		  		  		  	}
		  		  		  	
		  				
		  				
		  				
		  				
		  				
	  						
	  					}else
	  					{
	  						JOptionPane.showMessageDialog(null, "Veuillez Selectionner   Type Sortie", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
	  					}
	  					
	  			
	  					
	  				}
				
			}
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(234, 662, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnImprimerBonSortie = new JButton("Bon Sortie MP D\u00E9plac\u00E9e");
		btnImprimerBonSortie.setIcon(imgImprimer);
		btnImprimerBonSortie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

	  		  	if(transferStock.getId()>0){
	  		  		
	  		  		if(!comboDepotSource.getSelectedItem().equals("")){
	  		  			
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(transferStock.getDateTransfer());
	  		 
				// List<DetailTransferStockMP> listDetailTransferStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStock.getId());
				 if(listDetailTransfertMp.size()!=0)
				 {
					
					 
					    DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(0);
						Map parameters = new HashMap();
						parameters.put("numTransfer", transferStock.getCodeTransfer());
						
							if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
							{
								parameters.put("depot",   "Dépot Source      :");
								parameters.put("magasin", "Magasin Source   :");
								parameters.put("titre", "Bon de Sortie En Attente de la matière première");
								parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
								parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
							}else
							{
								parameters.put("depot",   "Dépot Source      :");
								parameters.put("magasin", "Magasin Source   :");
								parameters.put("titre", "Bon de Sortie de la matière première");
								parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
								parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
							}
							
							 
						
					
						parameters.put("dateTransfer", date);
						parameters.put("designation", detailTransferStockMP.getTransferStockMP().getDesignation().toUpperCase());
						if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType()!=null)
						{
							parameters.put("type", detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().toUpperCase());
						}else
						{
							parameters.put("type", detailTransferStockMP.getTransferStockMP().getType().getLiblle().toUpperCase());
						}
						
						JasperUtils.imprimerBonSortieMP(listDetailTransfertMp,parameters,transferStock.getCodeTransfer());
						
						btnIntialiserOF.setEnabled(true);
						btnValiderTransfer.setEnabled(false);
						
					 
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir les matieres premiere entrer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				
	  		  		}else {
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
	  		  	
			
			
			
			
			}
		});
		btnImprimerBonSortie.setBounds(524, 662, 178, 23);
		add(btnImprimerBonSortie);
		 
		 JLabel lblDesignation = new JLabel("Designation :");
		 lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblDesignation.setBounds(0, 619, 102, 24);
		 add(lblDesignation);
		 
		 txtdesignation = new JTextField();
		 txtdesignation.setText("");
		 txtdesignation.setColumns(10);
		 txtdesignation.setBounds(89, 619, 947, 26);
		 add(txtdesignation);
		 
		 final JLayeredPane layertype = new JLayeredPane();
		 layertype.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layertype.setBounds(1259, 11, 420, 373);
		 add(layertype);
		 
		 btnValider = new JButton();
		 btnValider.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(!txttype.getText().equals(""))
		 		{
		 			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText().toUpperCase());
		 			if(typeSortieTmp!=null)
		 			{
		 				JOptionPane.showMessageDialog(null, "Type de Sortie Excistant deja !!!","Erreur",JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}else
		 			{
		 				if(listSousTypeSortie.size()==0)
		 				{
		 					JOptionPane.showMessageDialog(null, "Veuillez entrer les sous type sortie SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
			 				return;
		 				}
		 				
		 			    	typeSortie.setLiblle(txttype.getText().toUpperCase());

		 						 				
		 				typeSortieDAO.add(typeSortie);
		 				
		 				for(int i=0;i<listSousTypeSortie.size();i++)
		 				{
		 					detailTypeSortieDAO.add(listSousTypeSortie.get(i));
		 				}
		 				
		 				JOptionPane.showMessageDialog(null, "Type Sortie Ajouter Avec Succée ","Information",JOptionPane.INFORMATION_MESSAGE);
		 				txttype.setText("");
		 				txtsoustype.setText("");
		 				intialiserTableauSousType();
		 				ChargerTypeSortie();
		 				listSousTypeSortie.clear();
		 				typeSortie=new TypeSortie();
		 			}
		 		}else
		 		{
		 			
		 			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Type Sortie SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
	 				return;
		 		}
		 	
		 	}
		 });
		 btnValider.setText("Valider");
		 btnValider.setBounds(147, 316, 85, 36);
		 layertype.add(btnValider);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBounds(25, 11, 274, 44);
		 layertype.add(layeredPane_1);
		 
		 JLabel lblType_1 = new JLabel("Type :");
		 lblType_1.setBounds(10, 12, 46, 24);
		 layeredPane_1.add(lblType_1);
		 lblType_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		 txttype = new JTextField();
		 txttype.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		
		 		if(e.getKeyCode()==e.VK_ENTER)
	      		{
		 			
		 			
		 			if(!txttype.getText().equals(""))
		 			{
		 				
		 			TypeSortie typeSortie=typeSortieDAO.findByLibelle(txttype.getText());
		 				if(typeSortie!=null)
		 				{
		 					listSousTypeSortie=	detailTypeSortieDAO.listeDetailTypeSortieByTypeSortie(typeSortie);
		 					
		 					afficher_SousType(listSousTypeSortie);
		 					btnValider.setEnabled(false);
		 					
		 				}else
		 				{
		 					JOptionPane.showMessageDialog(null, "Type sortie Introuvable","Information",JOptionPane.INFORMATION_MESSAGE);
		 					listSousTypeSortie.clear();
		 					afficher_SousType(listSousTypeSortie);
		 					btnValider.setEnabled(true);
		 					return;
		 				}
		 			
		 				
		 			}
		 			
		 			
	      		}
		 		
		 			
		 		
		 	}
		 });
		 txttype.setBounds(66, 11, 167, 26);
		 layeredPane_1.add(txttype);
		 txttype.setText("");
		 txttype.setColumns(10);
		 
		 JLayeredPane layeredPane_3 = new JLayeredPane();
		 layeredPane_3.setBounds(25, 70, 274, 54);
		 layertype.add(layeredPane_3);
		 
		 txtsoustype = new JTextField();
		 txtsoustype.setText("");
		 txtsoustype.setColumns(10);
		 txtsoustype.setBounds(76, 11, 153, 26);
		 layeredPane_3.add(txtsoustype);
		 
		 JLabel lblSousType = new JLabel("Sous Type :");
		 lblSousType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblSousType.setBounds(10, 12, 67, 24);
		 layeredPane_3.add(lblSousType);
		 
		 JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		 scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 scrollPane_1.setBounds(25, 132, 274, 170);
		 layertype.add(scrollPane_1);
		 
		 tableSousTypeSortie = new JTable();
		 tableSousTypeSortie.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		
		 		if(tableSousTypeSortie.getSelectedRow()!=-1)
		 		{
		 			
		 			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
		 			
		 			txtsoustype.setText(detailTypeSortie.getSousType());
		 			
		 			
		 		}
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		 tableSousTypeSortie.setModel(new DefaultTableModel(
		 	new Object[][] {
		 	},
		 	new String[] {
		 		"Sous Type Sortie"
		 	}
		 ));
		 tableSousTypeSortie.setFillsViewportHeight(true);
		 scrollPane_1.setViewportView(tableSousTypeSortie);
		 
		 JButton btnAjouter = new JButton();
		 btnAjouter.setBounds(309, 82, 85, 26);
		 layertype.add(btnAjouter);
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		if(txttype.getText().equals(""))
		 		{
		 			
		 		JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Type SVP !!!");	
		 			return;
		 			
		 			
		 		}else if(txtsoustype.getText().equals(""))
		 		{
		 			JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Sous Type SVP !!!");	
		 			return;
		 		}else
		 		{
		 			
		 			boolean trouve=false;
		 			for(int i=0;i<listSousTypeSortie.size();i++)
		 			{
		 				
		 				DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(i);
		 				if(detailTypeSortie.getSousType().equals(txtsoustype.getText()))
		 				{
		 					trouve=true;
		 				}
		 				
		 				
		 				
		 			}
		 		
		 			if(trouve==false)
		 			{
		 				TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
		 				
		 				
		 				DetailTypeSortie detailTypeSortieTmp=new DetailTypeSortie();	
		 				detailTypeSortieTmp.setSousType(txtsoustype.getText());
		 				if(typeSortieTmp!=null)
		 				{
		 					detailTypeSortieTmp.setTypesortie(typeSortieTmp);
		 					detailTypeSortieDAO.add(detailTypeSortieTmp);
		 					ChargerTypeSortie();
		 				}else
		 				{
		 					detailTypeSortieTmp.setTypesortie(typeSortie);
		 				}
		 				
		 				listSousTypeSortie.add(detailTypeSortieTmp);		 				
		 				afficher_SousType(listSousTypeSortie);
		 				txtsoustype.setText("");
		 				
		 				
		 				
		 				
		 				
		 			}else
		 			{
		 				JOptionPane.showMessageDialog(null, "Sous Type Sortie Déja Entrer !!!!!");
		 				return;
		 			}
		 			
		 			
		 			
		 			
		 		}
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnAjouter.setText("Ajouter");
		 
		 JButton btnModifier = new JButton();
		 btnModifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		if(tableSousTypeSortie.getSelectedRow()!=-1)
		 		{
		 			
		 			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
		 			
		 			detailTypeSortie.setSousType(txtsoustype.getText());
		 			
		 			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
		 			if(typeSortieTmp!=null)
		 			{
		 				listSousTypeSortie.set(tableSousTypeSortie.getSelectedRow(), detailTypeSortie);
		 				detailTypeSortieDAO.edit(detailTypeSortie);
		 				ChargerTypeSortie();
		 				
		 			}else
		 			{
		 				listSousTypeSortie.set(tableSousTypeSortie.getSelectedRow(), detailTypeSortie);
		 				
		 			}
		 				
		 			afficher_SousType(listSousTypeSortie);
		 			
		 			
		 		}	
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnModifier.setText("Modifier");
		 btnModifier.setBounds(309, 119, 85, 26);
		 layertype.add(btnModifier);
		 
		 JButton btnSupprimer = new JButton();
		 btnSupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
try {
	
		
		if(tableSousTypeSortie.getSelectedRow()!=-1)
		{
			
			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
			
			
			
			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
			if(typeSortieTmp!=null)
			{
				detailTypeSortieDAO.delete(detailTypeSortie.getId());
				listSousTypeSortie.remove(tableSousTypeSortie.getSelectedRow());
				ChargerTypeSortie();
				
			}else
			{
				listSousTypeSortie.remove(tableSousTypeSortie.getSelectedRow());
				
			}
				
			afficher_SousType(listSousTypeSortie);
			
			
		}
	
	
	
} catch (Exception ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
}
			
		 		
		 		
		 		
		 		
		 	
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnSupprimer.setText("Supprimer");
		 btnSupprimer.setBounds(309, 156, 85, 26);
		 layertype.add(btnSupprimer);
		 
		
		 
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
		  
		  


		
		  
		  JLabel label_1 = new JLabel("MP :");
		  label_1.setBounds(240, 12, 37, 24);
		  layeredPane_2.add(label_1);
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  

		  
		  JLabel label = new JLabel("Code MP:");
		  label.setBounds(20, 12, 102, 24);
		  layeredPane_2.add(label);
		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(387, 57, 119, 36);
		  layeredPane_2.add(btnAfficherStock);
		  btnAfficherStock.setText("Ajouter");
		  btnAfficherStock.setIcon(imgAjouter);
		  
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
		  
		   lblFournisseur = new JLabel("Fournisseur : ");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(707, 12, 91, 24);
		  layeredPane_2.add(lblFournisseur);
		// lblFournisseur.setVisible(false);
   			
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setBounds(785, 13, 232, 24);
		  layeredPane_2.add(comboFournisseur);
   			//comboFournisseur.setVisible(false);
		  comboFournisseur.setSelectedIndex(-1);
		   btnmodifier = new JButton();
		  btnmodifier.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		boolean dechet_manque = false;
		  		if(table.getSelectedRow()!=-1)
		  		{
		  			
		  			
		  			
		  			if(comboDepotSource.getSelectedItem().equals(""))	{
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
		  				}else if(new BigDecimal(txtQuantite.getText()).setScale(2).equals(BigDecimal.ZERO.setScale(2)) || new BigDecimal(txtQuantite.getText()).compareTo(BigDecimal.ONE)<=0)
		  				{
		  					JOptionPane.showMessageDialog(null, "la Quantite doit etre supérieur à zero SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
		  				}else if(comboType.getSelectedIndex()==-1)
		  				{
		  					JOptionPane.showMessageDialog(null, "Veuillez selectionner le type SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  					return;
		  					
		  				}else
		  				
		  				{
		  					/*
		  					if(comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_DECHET) || comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_MANQUE))
			  		   		{
	  		  					
	  		  					if(comboFournisseur.getSelectedIndex()==-1)
	  		  					{
	  		  						
	  		  					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Fournisseur SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	  		  					return;
	  		  					}
	  		  					
	  		  				dechet_manque=true;
	  		  					
	  		  					
			  		   		}
		  					*/
		  					
	  		  					FournisseurMP fournisseurMP=null;
	  		  					/*
	  		  					if(dechet_manque==true)
	  		  					{
	  		  					*/
	  		  					
	  		  					
	  		  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
	  		  					
	  		  					
	  		  					/*
	  		  					
	  		  					}
	  		  					*/
		  			
		  			
		  			DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(table.getSelectedRow());
		  			Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	  					
	  					
	  					MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
	  					StockMP stockMPTmp=null;
	  					
	  				
	  					
	  					CalculerStockFinal();
	  					
	  					boolean erreur=false;
	  					boolean trouve=false;
	  		  				
	  					for(int i=0;i<listEtatStockMPAfficher.size() ;i++)
	  					{
	  										  						
	  					EtatStockMP etatStockMP=	listEtatStockMPAfficher.get(i);
	  					
	  					if(etatStockMP.getMp().getId()==mp.getId())
	  					{
	  						
	  						if(fournisseurMP!=null)
	  						{
	  							if(etatStockMP.getFournisseurMP()!=null)
	  							{
	  								
	  								if(etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId())
	  								{
	  									
	  									trouve=true;
	  									
	  									
	  									
	  									
	  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
	  									{
	  										
	  										erreur=true;
	  										
	  										
	  									}
	  									
	  									
	  								}
	  								
	  								
	  							}
	  							
	  							
	  							
	  							
	  							
	  						}else
	  						{
	  							
	  							if(etatStockMP.getFournisseurMP()==null)
	  							{
	  								trouve=true;
	  								
	  								
	  								
	  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
	  									{
	  										
	  										erreur=true;
	  										
	  										
	  									}
	  									
	  									
	  								
	  							}
	  							
	  							
	  							
	  							
	  							
	  						}
	  						
	  						
	  						
	  					}
	  						
	  						
	  						
	  						
	  						
	  						
	  						
	  					}
	  					
	  					
	  					
	  					if(trouve==true)
	  					{
	  						
	  						if(erreur==true)
	  						{
	  							
	  							JOptionPane.showMessageDialog(null, "Stock de : «"+mp.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);

		  						return;
	  						}else
	  						{
	  										
  			  					

	  							detailTransferStockMP.setMagasinSouce(magasin);
	  							detailTransferStockMP.setMatierePremier(mp);
	  							detailTransferStockMP.setQuantite(new BigDecimal(txtQuantite.getText()));
	  							detailTransferStockMP.setTransferStockMP(transferStock);
	  							if(mp.getPrix()!=null)
  			  					{
	  								detailTransferStockMP.setPrixUnitaire(mp.getPrix());
  			  					}else
  			  					{
  			  					detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
  			  					}
	  							
	  							
	  							/*
	  							if(dechet_manque==true)
	  		  					{
	  		  					*/
	  							if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  					{
	  								detailTransferStockMP.setFournisseur(fournisseurMP);
			  					}else
			  					{
			  						detailTransferStockMP.setFournisseur(null);
			  					}
	  								/*
	  		  					}
	  		  					*/
	  							listDetailTransfertMp.set(table.getSelectedRow(), detailTransferStockMP);
	  							//detailTransfertMPDAO.edit(detailTransferStockMP);	  							
	  							
	  							afficher_tableMP(listDetailTransfertMp);
  			  					intialiser();
	  							
	  						
  			  					
  			  					
  		  					
	  							
	  							
	  							
	  						}
	  						
	  						
	  						
	  						
	  					}else
	  					{
	  						
	  						JOptionPane.showMessageDialog(null, " «"+mp.getNom()+"» Introuvale dans le Stock", "Erreur", JOptionPane.ERROR_MESSAGE);

	  						return;
	  					}
	  		  				

	  					
	  					
	  					
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
		  
		  final JCheckBox chckbxAjouterType = new JCheckBox("Ajouter Type");
		  chckbxAjouterType.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		if(chckbxAjouterType.isSelected()==true)
		  		{
		  			layertype.setVisible(true);
		  		}else
		  		{
		  			layertype.setVisible(false);
		  		}
		  		
		  		
		  		
		  	}
		  });
		  chckbxAjouterType.setBounds(1151, 32, 102, 23);
		  add(chckbxAjouterType);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		boolean dechet_manque = false;
		  		  				if(comboDepotSource.getSelectedItem().equals(""))	{
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
		  		  				}else if(new BigDecimal(txtQuantite.getText()).setScale(2 , RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)) || new BigDecimal(txtQuantite.getText()).compareTo(BigDecimal.ZERO)<=0)
		  		  				{
		  		  					JOptionPane.showMessageDialog(null, "la Quantite doit etre supérieur à zero SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  				}else if(comboType.getSelectedIndex()==-1)
		  		  				{
		  		  					JOptionPane.showMessageDialog(null, "Veuillez selectionner le type SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  					
		  		  				}else
		  		  				
		  		  				{
		  		  					
		  		  				/*	
		  		  				if(comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_DECHET) || comboType.getSelectedItem().equals(Constantes.TYPE_SORTIE_MANQUE) || comboType.getSelectedItem().equals(Constantes.SOUS_TYPE_SORTIE_DEFINITIF)  || comboType.getSelectedItem().equals(Constantes.SOUS_TYPE_SORTIE_ENATTENT))
				  		   		{
		  		  					
		  		  					if(comboFournisseur.getSelectedIndex()==-1)
		  		  					{
		  		  						
		  		  					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Fournisseur SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  					return;
		  		  					}
		  		  					
		  		  				dechet_manque=true;
		  		  					
		  		  					
				  		   		}
				  		   		*/
		  		  					FournisseurMP fournisseurMP=null;
		  		  					
		  		  					/*
		  		  					if(dechet_manque==true)
		  		  					{
		  		  					*/
		  		  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
		  		  					/*
		  		  					}
		  		  					
		  		  					*/
		  		  					
		  		  					
		  		  					MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
		  		  					if(mp==null)
		  		  					{
		  		  						JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		  						return;
		  		  					}
		  		  					
		  		  					
		  		  					
		  		  					
		  		  				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		  					{
	  		  						if(fournisseurMP==null)
	  		  						{
	  		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur SVP");
	  		  							return;
	  		  						}
	  		  					}
		  		  				
		  		  			Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
  		  					StockMP stockMP=null;	
		  		  				
		  		  			CalculerStockFinal();
		  					
		  					boolean erreur=false;
		  					boolean trouve=false;
		  		  				
		  					for(int i=0;i<listEtatStockMPAfficher.size() ;i++)
		  					{
		  										  						
		  					EtatStockMP etatStockMP=	listEtatStockMPAfficher.get(i);
		  					
		  					if(etatStockMP.getMp().getId()==mp.getId())
		  					{
		  						
		  						if(fournisseurMP!=null)
		  						{
		  							if(etatStockMP.getFournisseurMP()!=null)
		  							{
		  								
		  								if(etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId())
		  								{
		  									
		  									trouve=true;
		  									
		  									
		  									
		  									
		  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
		  									{
		  										
		  										erreur=true;
		  										
		  										
		  									}
		  									
		  									
		  								}
		  								
		  								
		  							}
		  							
		  							
		  							
		  							
		  							
		  						}else
		  						{
		  							
		  							if(etatStockMP.getFournisseurMP()==null)
		  							{
		  								trouve=true;
		  								
		  								
		  								
		  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
		  									{
		  										
		  										erreur=true;
		  										
		  										
		  									}
		  									
		  									
		  								
		  							}
		  							
		  							
		  							
		  							
		  							
		  						}
		  						
		  						
		  						
		  					}
		  						
		  						
		  						
		  						
		  						
		  						
		  						
		  					}
		  					
		  					
		  					
		  					if(trouve==true)
		  					{
		  						
		  						if(erreur==true)
		  						{
		  							
		  							JOptionPane.showMessageDialog(null, "Stock de : «"+mp.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);

			  						return;
		  						}else
		  						{
		  							
		  							

	  		  						
	  		  						
	  		  						
	  		  						DetailTransferStockMP detailtransfertmp=new DetailTransferStockMP();
	  			  					
	  			  					detailtransfertmp.setMagasinSouce(magasin);
	  			  					detailtransfertmp.setMatierePremier(mp);
	  			  					detailtransfertmp.setQuantite(new BigDecimal(txtQuantite.getText()));
	  			  					detailtransfertmp.setTransferStockMP(transferStock);
	  			  					if(mp.getPrix()!=null)
	  			  					{
	  			  					detailtransfertmp.setPrixUnitaire(mp.getPrix());
	  			  					}else
	  			  					{
	  			  					detailtransfertmp.setPrixUnitaire(BigDecimal.ZERO);
	  			  					}
	  			  				
	  			  					
	  			  					//detailTransfertMPDAO.add(detailtransfertmp);
	  			  			
	  			  					/*
	  			  					if(dechet_manque==true)
	  		  					{
	  		  					*/
	  			  				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		  					{
	  			  					
	  			  				detailtransfertmp.setFournisseur(fournisseurMP);
	  			  				
	  		  					}
	  			  				
	  			  				/*
	  		  					}
	  			  					*/
	  			  					
	  			  					listDetailTransfertMp.add(detailtransfertmp);
	  			  					afficher_tableMP(listDetailTransfertMp);
	  			  					intialiser();
	  			  					
	  			  					
	  			  					
	  			  					
	  			  					
	  			  					
	  		  					
		  							
		  							
		  							
		  						}
		  						
		  						
		  						
		  						
		  					}else
		  					{
		  						
		  						JOptionPane.showMessageDialog(null, " «"+mp.getNom()+"» Introuvale dans le Stock", "Erreur", JOptionPane.ERROR_MESSAGE);

		  						return;
		  					}
		  		  				
		  		  					
		  		  					
		  		  					
		  		  			
		  		  				

		  		  					
		  		  					
		  		  				}
		  		  			  }
		  });
		
		
		comboFournisseur.addItem("");
		

		for(int k=0;k<listFournisseur.size();k++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(k);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		ChargerTypeSortie();	  
		layertype.setVisible(false);
		 

	
if (AuthentificationView.utilisateur.getLogin().equals("admin")) {

listDepot =depotDAO.findAll();
comboDepotSource.removeAllItems();
comboDepotSource.addItem("");

for(int d=0;d<listDepot.size();d++)
{

Depot depot=listDepot.get(d);
comboDepotSource.addItem(depot.getLibelle());
mapDepotSource.put(depot.getLibelle(), depot);




}



} else {
Depot depot = depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());

if (depot != null) {
comboDepotSource.removeAllItems();
comboDepotSource.addItem("");
comboDepotSource.addItem(depot.getLibelle());
mapDepotSource.put(depot.getLibelle(), depot);


}
}
	
	
txtCodeMP.setText(Constantes.CODE_MP);

comboService.addItem("");
for(int t=0;t<listService.size();t++)
{
	
	service service=listService.get(t);
	comboService.addItem(service.getLibelle());
	mapService.put(service.getLibelle(), service);
}
comboService.setSelectedItem("");
		
	}
	
	
	void ChargerTypeSortie()
	{
		comboType.removeAllItems();
		listTypeSortie.clear();
		listTypeSortie=typeSortieDAO.findAll();
		for(int i=0;i<listTypeSortie.size();i++)
		{
			TypeSortie typesortie=listTypeSortie.get(i);
			if(!typesortie.getLiblle().equals(TYPE_SORTIE_RETOUR_FOURNISSEUR))
			{
				comboType.addItem(typesortie.getLiblle());
				mapTypeSortie.put(typesortie.getLiblle(), typesortie);
			}
			
		}
		
	}
	
	
	void intialiserTous()
	{
		
		comboDepotSource.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		//txtRefTransfere.setText("");
		dateTransfereChooser.setCalendar(null);
		txtQuantite.setText("");
		txtCodeMP.setText("");
		txtCodeMP.setText(Constantes.CODE_MP);	
		comboMP.setSelectedItem("");
		txtdesignation.setText("");
		intialiserTableau();
		btnmodifier.setEnabled(true);
		btnsupprimer.setEnabled(true);
	 	btnValiderTransfer.setEnabled(true);
	 	txtcodetransfer.setText("");
	 	comboType.setSelectedIndex(-1);
	 combosoustype.removeAllItems();
		
	}
	
	void intialiser()
	{
	
		txtQuantite.setText("");
		txtCodeMP.setText("");
		comboMP.setSelectedIndex(-1);		
		comboFournisseur.setSelectedItem("");		
		txtCodeMP.setText(Constantes.CODE_MP);	
	}
	
	
	
	void refrech()
	{
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
	
	void afficher_tableMP(List<DetailTransferStockMP> listDetailTransfertMP)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listDetailTransfertMP.size())
			{	
				
				DetailTransferStockMP detailtransfertMP=listDetailTransfertMP.get(i);
			if(detailtransfertMP.getFournisseur()!=null)
			{

				Object []ligne={detailtransfertMP.getMatierePremier().getCode(),detailtransfertMP.getMatierePremier().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(detailtransfertMP.getQuantite()),detailtransfertMP.getFournisseur().getCodeFournisseur()};
				modeleMP.addRow(ligne);
			
				
			}else
			{
				Object []ligne={detailtransfertMP.getMatierePremier().getCode(),detailtransfertMP.getMatierePremier().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(detailtransfertMP.getQuantite())};
				modeleMP.addRow(ligne);
			}
				

			
				
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
			Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
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
		     			"Code","Nom MP","Quantité a Tranférer","Fournisseur"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
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


void intialiserTableauSousType(){
	
	
	modeleSousType =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"SousType"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 tableSousTypeSortie.setModel(modeleSousType); 
		 tableSousTypeSortie.getColumnModel().getColumn(0).setPreferredWidth(10);
    
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}


void afficher_SousType(List<DetailTypeSortie> listDetailTypeSortieMP)
{

	
	intialiserTableauSousType();

        
	  int i=0;
		while(i<listDetailTypeSortieMP.size())
		{	
			
			DetailTypeSortie DetailTypeSortieMP=listDetailTypeSortieMP.get(i);
	
			Object []ligne={DetailTypeSortieMP.getSousType()};
			modeleSousType.addRow(ligne);
		
			

		
			
			i++;
		}
		tableSousTypeSortie.setModel(modeleSousType); 
}





public void CalculerStockFinal()
{
	

	if(comboDepotSource.getSelectedItem().equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else {
		
		
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
				Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem().toString());
				
			  	FournisseurMP fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem().toString());
			
			  	if(magasin==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
			  		return;
			  	}
			  	
			  	
			  	
				if(dateTransfereChooser.getDate()==null)
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
					String year = simpleDateFormatyear.format(dateTransfereChooser.getDate());
					
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
					
				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);

				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

   listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
   listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
	
				
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
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]));

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
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]));

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
}

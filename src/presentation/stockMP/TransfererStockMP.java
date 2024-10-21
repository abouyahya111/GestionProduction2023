package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
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
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import javax.swing.JCheckBox;


public class TransfererStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	
	
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapStockFinaleMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< Integer, FournisseurMP> mapFournisseurMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	TransferStockMP transferStock = new TransferStockMP();
	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboDepotDestination=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private Depot depot = new Depot();
	private JTextField txtCodeTransfer;
	private FournisseurMPDAO fournisseurMPDAO;
	JCheckBox chckbxRetour = new JCheckBox("Retour");
	private JTextField txtnumbl;
	 JLabel lblNumBl = new JLabel("Num BL :");
	 private DetailTypeSortieDAO detailTypeSortieDAO;
	 
		private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
		private Map< String, FournisseurMP> mapFournisseurMPTmp = new HashMap<>();
	 
		JComboBox combofournisseur = new JComboBox();
		JLabel lblFournisseur = new JLabel("Fournisseur :");
		private DetailTransferMPDAO detailTransferStockMPDAO;
		private List <Object[]> Mindate=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
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
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
		List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
		private boolean erreur=false;
		private CategorieMpDAO categoriempdao;
		
		private SubCategorieMPDAO subcategoriempdao;
		 JButton btnAfficherStock = new JButton("");
		 private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		 private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
		 
		 
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 707);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		String Codedepot= AuthentificationView.utilisateur.getCodeDepot();
		listDepot =new ArrayList<Depot>(); 
		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				listDepot = depotDAO.findDepotByCodeSaufEnParametre(Codedepot);
		   	} else {
		   		depot = depotDAO.findByCode(Codedepot);
		   		listDepot.add(depot);
		   	}
    		 
    		
	     //	comboDepotDestination.addItem(depot.getLibelle());
	     //	comboDepotSource.addItem(depot.getLibelle());
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(413, 570, 112, 30);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
					  		  txtCodeTransfer = new JTextField();
					  		util.Utils.copycoller(txtCodeTransfer);
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
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
				  		     	scrollPane.setBounds(9, 209, 936, 350);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		   /*   List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			comboMagasinDestination.addItem(magasin.getLibelle());
						  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
					  		      			
						  		      			j++;
						  		      		}
					  		      }*/
					  		      
					  		      
				  		     
				  		     //	listDepot = depotDAO.findAll();	
				  		     	comboDepotSource.addItem("");
				  		     	comboDepotDestination.addItem("");
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboDepotSource.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinSource.removeAllItems();
					  		     	 Depot depot =new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	if(!comboDepotSource.getSelectedItem().equals(""))
						  		     	  	   	 depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
								  		       
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
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
					  		     	 	 }
					  		     	 	}
					  		     	 });
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				  		     	  List<Magasin> listMagasinDechet=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
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
							  		      
							  		      
							  		 	listMagasinDechet = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
							  		      if(listMagasinDechet!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasinDechet.size())
								  		      		{	
								  		      			Magasin magasin=listMagasinDechet.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      } 
							  		      
							  		      
							  		      
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 176, 936, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 936, 152);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 176, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 193, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		  comboMagasinDestination.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent e) {
				  		  		
				  		  		
				  		  	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		  		Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				  		  		
				  		  		if(magasin!=null)
				  		  		{
				  		  			
				  		  			if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
				  		  			{
				  		  				
				  		  				chckbxRetour.setEnabled(false);
				  		  				txtnumbl.setText("");
				  		  				txtnumbl.setVisible(true);
				  		  				lblNumBl.setVisible(true);
				  		  			chckbxRetour.setSelected(false);
				  		  			combofournisseur.setSelectedItem("");
				  		  		combofournisseur.setVisible(true);
				  		  	lblFournisseur.setVisible(true);
				  		  			
				  		  			
				  		  				
				  		  			}else
				  		  			{
				  		  			chckbxRetour.setEnabled(true);
				  		  		txtnumbl.setText("");
		  		  				txtnumbl.setVisible(false);
		  		  			lblNumBl.setVisible(false);
		  		  		chckbxRetour.setSelected(false);
		  		  	combofournisseur.setSelectedItem("");
	  		  		combofournisseur.setVisible(false);
	  		  	lblFournisseur.setVisible(false);
				  		  			}
				  		  			
				  		  			
				  		  			
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	 }
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(409, 74, 228, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(299, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("Dépot Destination");
				  		  lblDpotDestination.setBounds(299, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(409, 34, 200, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(289, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		   btnAfficherStock = new JButton("");
				  		btnAfficherStock.setIcon(imgRechercher);
				  		  btnAfficherStock.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  				if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					txtCodeTransfer.setText("");	
				  					List<StockMP> listStockMP=stockMPDAO.findSockNonVideByMagasin(mapMagasinSource.get(comboMagasinSource.getSelectedItem()).getId());
				  		
				  						
				  					
				  					Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				  					if(magasinDestination==null)
		  		  				  	{
		  		  				  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin Destination SVP");
		  		  				  		return;
		  		  				  	}
				  					
				  						MatierePremier matierePremier=null;
				  						
				  						
				  							
				  							SubCategorieMp subCategorieMp=null;
				  		  					CategorieMp categorieMp=null;
				  		  					MatierePremier mp=null;
				  		  					Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				  		  					
				  		  				  	FournisseurMP fournisseurMP=null;
				  		  				
				  		  				  	if(magasin==null)
				  		  				  	{
				  		  				  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
				  		  				  		return;
				  		  				  	}
				  		  				  	
				  		  				  	
				  		  				  	
				  		  					if(dateTransfereChooser.getDate()==null)
				  		  				  	{
				  		  				  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de transfere SVP");
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
				  	  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);

				  		  					
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
				  		  			
				  		  		
				  		  			
				  		  			
				  		  			
				  		  			
				  							 
				  						intialiserTableau();
				  						
				  					
				  						
				  						
				  					
				  				  
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					
				  					afficher_tableMP(listEtatStockMPAfficher);
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				}
				  			  }
				  		  });
				  		  btnAfficherStock.setBounds(890, 33, 36, 36);
				  		  layeredPane.add(btnAfficherStock);
				  		
				  		  txtCodeTransfer.setBounds(728, 74, 155, 24);
				  		  layeredPane.add(txtCodeTransfer);
				  		  txtCodeTransfer.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(725, 34, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(632, 33, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel label = new JLabel("Code Transafert ");
				  		  label.setBounds(642, 73, 94, 24);
				  		  layeredPane.add(label);
				  		  
				  		   lblNumBl = new JLabel("Num BL :");
				  		  lblNumBl.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblNumBl.setBounds(10, 117, 57, 24);
				  		  layeredPane.add(lblNumBl);
				  		lblNumBl.setVisible(false);
				  		  txtnumbl = new JTextField();
				  		  txtnumbl.setColumns(10);
				  		  txtnumbl.setBounds(73, 120, 155, 24);
				  		  layeredPane.add(txtnumbl);
				  		txtnumbl.setVisible(false);
				  		
				  		   lblFournisseur = new JLabel("Fournisseur :");
				  		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblFournisseur.setBounds(250, 117, 94, 24);
				  		  layeredPane.add(lblFournisseur);
				  		lblFournisseur.setVisible(false);
				  		   combofournisseur = new JComboBox();
				  		  combofournisseur.setBounds(329, 120, 228, 24);
				  		  layeredPane.add(combofournisseur);
				  		combofournisseur.setVisible(false);
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				
				erreur=false;
				Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				
				if(magasinDestination.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
		  			{
					
					if(txtnumbl.getText().equals(""))
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez Entrer le Num BL Pour Magasin Dechet", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}else
					{
						transferStock.setNumBL(txtnumbl.getText());
					}
					
					
					if(combofournisseur.getSelectedItem().equals(""))
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
						
					}else
					{
						
						transferStock.setDesignation(combofournisseur.getSelectedItem().toString());
						
					}
					
					
					
		  			}
				
				
				
				if(magasinSource.getCode().equals(magasinDestination.getCode())){
					JOptionPane.showMessageDialog(null, "Le Magasin source doit ètre différent déstination", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir la date de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				
				{
					
				txtCodeTransfer.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_SORTIE));
				transferStock.setCodeTransfer(txtCodeTransfer.getText());
				transferStock.setCreerPar(AuthentificationView.utilisateur);
				transferStock.setDate(new Date());
				transferStock.setDateTransfer(dateTransfereChooser.getDate());
				
				
  		  		if(magasinSource.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE) && magasinDestination.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
  		  		{
  		  			
  		  			
  		  		if(magasinDestination.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
	  			{
  		  			
  		  			
				DetailTypeSortie detailtypeSortie=detailTypeSortieDAO.DetailTypeSortieByTypeSortie(Constantes.DETAIL_TYPE_SORTIE_FOURNISSEUR_ENATTENT);
				
				transferStock.setStatut(Constantes.ETAT_SORTIE_ENATTENT);
				transferStock.setSoustype(detailtypeSortie);
				
				
	  			}else
	  			{
	  				
	  				
	  				transferStock.setStatut(ETAT_TRANSFER_STOCK_TRANSFERE);
	  				
	  				
	  			}
  		  			
  		  		
  		  			
  		  			
  		  		}else if(magasinSource.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) && magasinDestination.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION))
  		  		{
  		  			
  		  		transferStock.setStatut(Constantes.ETAT_SORTIE_ENATTENT);
  		  			
  		  		}else if(magasinSource.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) && magasinDestination.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
  		  		{
  		  			
  		  		transferStock.setStatut(ETAT_TRANSFER_RETOUR);
  		  			
  		  		}else
  		  		{
  		  			
  		  			
  		  		JOptionPane.showMessageDialog(null, "les Transferts acceptés sont de Magasin vers Magasin ou Machine vers Machine ou Machine vers Magasin  ");
  		  		
  		  		return;
  		  		
  		  		
  		  		}
  		  			
  		  		
			
			
				
				transferStock.setDepot(mapDepotSourcetmp.get(comboDepotSource.getSelectedItem()));
				
				if(erreur==true)
				{
					return;
				}
				
				transferStockMPDAO.add(transferStock);
				BigDecimal montantTotal=BigDecimal.ZERO;
                BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                BigDecimal montantTotalEmballage=BigDecimal.ZERO;
			for(int t=0;t<remplirDetailTransfer().size();t++)
			{
				
				detailTransferStockMPDAO.add(remplirDetailTransfer().get(t));
				
				if(remplirDetailTransfer().get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	     			{
	     				montantTotalEnvrac=montantTotalEnvrac.add(remplirDetailTransfer().get(t).getQuantite().multiply(remplirDetailTransfer().get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
	     				 
	     				
	     			}else
	     			{
	     				montantTotalEmballage=montantTotalEmballage.add(remplirDetailTransfer().get(t).getQuantite().multiply(remplirDetailTransfer().get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
	     				 
	     			}
	     			montantTotal=montantTotal.add(remplirDetailTransfer().get(t).getQuantite().multiply(remplirDetailTransfer().get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
	     			 
				
				
			}
			
			
			if(transferStock.getStatut().equals(ETAT_TRANSFER_RETOUR))
			{
				
				listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
	     		
	     		if(statistiqueFinanciaireMPTmp!=null)
	     		{
	     			
	     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
	     			
	     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
	     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().add(montantTotalEmballage));
	     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().add(montantTotalEnvrac));
	     		if(statistiqueFinanciaireMPTmp.getCoutProduction().subtract(montantTotal).compareTo(BigDecimal.ZERO)<0)
	     		{
	     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
	     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton().subtract(montantTotal));
	     		}else
	     		{
	     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction().subtract(montantTotal));
	     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
	     		}
	     			
	     			statistiqueFinanciaireMP.setCodeTransfer(transferStock.getCodeTransfer());
	     			statistiqueFinanciaireMP.setDate(new Date());
	     			statistiqueFinanciaireMP.setDateOperation(transferStock.getDateTransfer());
	     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
	     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
	     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
	     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
	     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
	     			
	     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
	     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
	     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
	     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
	     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour().add(montantTotal));
	     			statistiqueFinanciaireMP.setEtat(ETAT_TRANSFER_RETOUR);
	     		 
	     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
	     			 
	     			
	     		} 
			}
		
			
			
				
				
				JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				btnAfficherStock.doClick();
				
				transferStock=new TransferStockMP();
				
			//	initialierMapStock();
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(218, 570, 169, 30);
		add(btnValiderTransfer);
		
		JButton btnimprimer = new JButton("Bon Transfere MP D\u00E9plac\u00E9e");
		btnimprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

	  		  	if(listDetailTransferStockMP.size()!=0){
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("")){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(listDetailTransferStockMP.get(0).getTransferStockMP().getDateTransfer());
				 
				 if(listDetailTransferStockMP.size()!=0)
				 {
					    DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
						Map parameters = new HashMap();
						parameters.put("numTransfer", listDetailTransferStockMP.get(0).getTransferStockMP().getCodeTransfer());
						parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
						parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
						parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
						parameters.put("depDest", comboDepotDestination.getSelectedItem());
						parameters.put("dateTransfer", date);

						if(mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
			  			{
						
						if(txtnumbl.getText().equals(""))
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Entrer le Num BL Pour Magasin Dechet", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
						}else
						{
							
							parameters.put("numbl", "Num BL : "+txtnumbl.getText());
							
							
							
						}
						
						
						if(combofournisseur.getSelectedItem().equals(""))
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
							
						}else
						{
							
							parameters.put("fournisseur", "Fournisseur : "+combofournisseur.getSelectedItem());
							
						}
						
						
						
						
						
						
						
			  			}
						
						
						
						
						JasperUtils.imprimerBonTransfereMP(listDetailTransferStockMP,parameters,listDetailTransferStockMP.get(0).getTransferStockMP().getCodeTransfer());
						
						
						
						
						
						
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir les matières premiere à transferer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
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
		btnimprimer.setBounds(550, 570, 200, 30);
		btnimprimer.setIcon(imgImprimer);
		add(btnimprimer);
		
		
		listFournisseurMP=fournisseurMPDAO.findAll();
		combofournisseur.addItem("");
		for(int j=0;j<listFournisseurMP.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseurMP.get(j);	
			combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseurMPTmp.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		} 
		
		
				  		 
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		txtCodeTransfer.setText("");
		dateTransfereChooser.setCalendar(null);
		txtnumbl.setText("");
		combofournisseur.setSelectedItem("");
		for(int j=0;j<table.getRowCount();j++){
			table.setValueAt("", j, 3);
						
		}
				
		
	}
	
	void afficher_tableMP(List<EtatStockMP> listEtatStockMP)
	{

		
		intialiserTableau();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
	        
		  int i=0;
			while(i<listEtatStockMP.size())
			{	
				
				EtatStockMP stockMP=listEtatStockMP.get(i);
				mapMatierePremierTmp.put(stockMP.getMp().getCode(), stockMP.getMp());
				String fournisseur="";
				if(stockMP.getFournisseurMP()!=null)
				{
					fournisseur=stockMP.getFournisseurMP().getCodeFournisseur();
				}
				
				
				if(magasinSource.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) && magasinDestination.getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE))
				{
					if(stockMP.getQuantiteFinale().compareTo(BigDecimal.ZERO)!=0)
					{
						
						Object []ligne={stockMP.getMp().getCode(),stockMP.getMp().getNom(), fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(stockMP.getQuantiteFinale()),""};

						modeleMP.addRow(ligne);
						
						
					}
					
					
					
				}else
				{
					if(stockMP.getQuantiteFinale().compareTo(BigDecimal.ZERO)!=0)
					{
						
						
						Object []ligne={stockMP.getMp().getCode(),stockMP.getMp().getNom(), fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(stockMP.getQuantiteFinale()),""};

						modeleMP.addRow(ligne);
						
						
						
						
					}
					
				
				}
				
				
				
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
	mapMatierePremier= new HashMap<>();
	mapQuantiteMP= new HashMap<>();		
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 4).toString().equals("") ){
			
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			if(mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()).getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
				FournisseurMP fournisseurMP=fournisseurMPDAO.findByCode(table.getValueAt(j, 2).toString());
				if(fournisseurMP!=null)
				{
					mapFournisseurMP.put(i,fournisseurMP);
				}
				
				
				for(int d=0;d<listEtatStockMPAfficher.size();d++)
				{
					
					if(listEtatStockMPAfficher.get(d).getMp().getCode().equals(table.getValueAt(j, 0).toString()))
					{
						
						if(listEtatStockMPAfficher.get(d).getFournisseurMP()!=null)
						{
							
							if(listEtatStockMPAfficher.get(d).getFournisseurMP().getId()==fournisseurMP.getId())
							{
								
								mapStockFinaleMP.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), listEtatStockMPAfficher.get(d).getQuantiteFinale().toString());
								
								
								
							}
									
							
						}
						
						
						
						
					}
					
					
					
				}
				mapQuantiteMP.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 4).toString());
			
				
					}else
					{
						mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 4).toString());
						
						
						for(int d=0;d<listEtatStockMPAfficher.size();d++)
						{
							
							if(listEtatStockMPAfficher.get(d).getMp().getCode().equals(table.getValueAt(j, 0).toString()))
							{
								
								if(listEtatStockMPAfficher.get(d).getFournisseurMP()==null)
								{
									
									
										
										mapStockFinaleMP.put(table.getValueAt(j, 0).toString(), listEtatStockMPAfficher.get(d).getQuantiteFinale().toString());										
										
										
									
											
									
								}
								
								
								
								
							}
							
							
							
						}
						
						
					}
			
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal StockFinale=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;

	  listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(i);
	
		
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
			
			FournisseurMP fournisseurMP=mapFournisseurMP.get(i);
			
			if(fournisseurMP!=null)
			{
				StockFinale=new BigDecimal(mapStockFinaleMP.get(matierePremier.getCode()+"_"+fournisseurMP.getCodeFournisseur()));
					quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()+"_"+fournisseurMP.getCodeFournisseur()));
			}
			
			
			}else
			{
				quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
				StockFinale=new BigDecimal(mapStockFinaleMP.get(matierePremier.getCode()));
				 
			}
	
		
		if(StockFinale.compareTo(quantite)>=0){
			/*sommeStock=quantite+stockMPDestination.getStock();*/
		
		
		
		//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
		
		//=prixMoyen/sommeStock;
	//	stockMPDestination.setPrixUnitaire(prixMoyen);
	
		
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
		
		FournisseurMP fournisseurMP=mapFournisseurMP.get(i);
		detailTransferStockMP.setFournisseur(fournisseurMP);
		
		}
		detailTransferStockMP.setQuantite(quantite);
		if(matierePremier.getPrix()!=null)
		{
			detailTransferStockMP.setPrixUnitaire(matierePremier.getPrix());
		}else
		{
			detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
		}
		
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
	}else {
		JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
	erreur=true;
	
	}
		
		
	}
	

	
	
	return listDetailTransferStockMP;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Fournisseur","Quantité En Stock","Quantité a Tranférer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}



void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		table.setValueAt("", j,4);
		
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


 



}

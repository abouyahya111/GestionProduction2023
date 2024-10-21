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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
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
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConsultationTransfererStockMPProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleDetailMP;

	private JXTable table;
	JXTable table_DetailTransfert = new JXTable();
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private JDateChooser dateTransfereChooserDu;
	
	
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapStockFinaleMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< Integer, FournisseurMP> mapFournisseurMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
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
		 
		List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
		List<TransferStockMP> listeTransferStockMP= new ArrayList<TransferStockMP>();
		private boolean erreur=false;
		private CategorieMpDAO categoriempdao;
		JDateChooser dateTransfereChooserAu = new JDateChooser();
		private SubCategorieMPDAO subcategoriempdao;
		 JButton btnAfficherStock = new JButton("");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsultationTransfererStockMPProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1357, 992);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		   
					  		  txtCodeTransfer = new JTextField();
					  		util.Utils.copycoller(txtCodeTransfer);
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(table.getSelectedRow()!=-1)
				  		     		{
				  		     			
				  		     			TransferStockMP transferStockMP=listeTransferStockMP.get(table.getSelectedRow());
				  		     			if(transferStockMP!=null)
				  		     			{
				  		     				listDetailTransferStockMP=detailTransferStockMPDAO.findByTransferStockMP(transferStockMP.getId());
				  		     				afficher_tableDetailMP(listDetailTransferStockMP);
				  		     				
				  		     			}
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     table.setModel(new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     		"Code Transfert", "Date Transfert", "Etat"
				  		     	}
				  		     ));
				  		     table.getColumnModel().getColumn(0).setPreferredWidth(102);
				  		     table.getColumnModel().getColumn(1).setPreferredWidth(130);
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
				  		     	scrollPane.setBounds(9, 209, 1265, 350);
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
					  		      			mapDepotSource.put(depot.getLibelle(), depot);
					  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
					  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
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
						  		     	  	   	 depot = mapDepotSource.get(comboDepotSource.getSelectedItem());
								  		       
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
					  		     	  	   	 depot = mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
							  		       
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
				  		     	titledSeparator.setTitle("Liste Des Transferts");
				  		     	titledSeparator.setBounds(9, 176, 1265, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1265, 152);
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
				  		  
				  		  lblDpotDestination = new JLabel("DÈpot Destination");
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
				  		  		
				  		  		
				  		  		intialiserDetailTransfertMP();
				  		  		intialiserTableau();
				  		  		
				  		  		String req="";

				  		  	dateTransfereChooserDu.setDateFormatString("yyyy-MM-dd");
				  		  String dateDu=((JTextField)dateTransfereChooserDu.getDateEditor().getUiComponent()).getText();
				  		dateTransfereChooserAu.setDateFormatString("yyyy-MM-dd");
				  		  String dateAu=((JTextField)dateTransfereChooserAu.getDateEditor().getUiComponent()).getText();

				  		  if(!dateDu.equals("") && dateAu.equals(""))
				  		  {
				  		  	dateAu=dateDu;
				  		  }else if(dateDu.equals("") && !dateAu.equals(""))
				  		  {
				  		  	dateDu=dateAu;
				  		  }
				  		  
				  		  if(!dateDu.equals("") && !dateAu.equals(""))
				  		  {
				  			  req=req+" and d.transferStockMP.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
				  		  }
				  		  
				  		  if(!txtCodeTransfer.getText().equals(""))
				  		  {
				  			req=req+" and d.transferStockMP.CodeTransfer='"+txtCodeTransfer.getText()+"' ";
				  		  }
				  		  	
				  		  	Depot depotSource=mapDepotSource.get(comboDepotSource.getSelectedItem().toString());
				  		  Depot depotDestination=mapDepotDestionation.get(comboDepotDestination.getSelectedItem().toString());
				  		  Magasin magasinSource=mapMagasinSource.get(comboMagasinSource.getSelectedItem().toString());
				  		  Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem().toString());
				  		  
				  		  if(depotSource!=null)
				  		  {
				  			  
				  			req=req+" and d.magasinSouce.depot.id='" +depotSource.getId()+"' " ;
				  			  
				  		  }
				  		  
				  		if(depotDestination!=null)
				  		  {
				  			  
				  			req=req+" and d.magasinDestination.depot.id='" +depotDestination.getId()+"' " ;
				  			  
				  		  }
				  		
				  		if(magasinSource!=null)
				  		  {
				  			  
				  			req=req+" and d.magasinSouce.id='" +magasinSource.getId()+"' " ;
				  			  
				  		  }
				  		if(magasinDestination!=null)
				  		  {
				  			  
				  			req=req+" and d.magasinDestination.id='" +magasinDestination.getId()+"' " ;
				  			  
				  		  }
				  		
				  		if(!txtnumbl.getText().equals(""))
				  		{
				  			
				  			req=req+" and d.transferStockMP.numBL='" +txtnumbl.getText()+"' " ;
				  		}
				  		
				  		FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem());
				  		  
				  		if(fournisseurMP!=null)
				  		  {
				  			  
				  			req=req+" and d.fournisseur.id='" +fournisseurMP.getId()+"' " ;
				  			  
				  		  }
				  		
				  		
				  		listeTransferStockMP=transferStockMPDAO.listTransferMPProduction(req);
				  		afficher_tableMP(listeTransferStockMP);
				  		  	
				  		  	}
				  		  });
				  		  btnAfficherStock.setBounds(1175, 34, 36, 36);
				  		  layeredPane.add(btnAfficherStock);
				  		
				  		  txtCodeTransfer.setBounds(728, 74, 155, 24);
				  		  layeredPane.add(txtCodeTransfer);
				  		  txtCodeTransfer.setColumns(10);
				  		  
				  		   dateTransfereChooserDu = new JDateChooser();
				  		  dateTransfereChooserDu.setLocale(Locale.FRANCE);
				  		  dateTransfereChooserDu.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooserDu.setBounds(725, 34, 155, 26);
				  		  layeredPane.add(dateTransfereChooserDu);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re Du :");
				  		  lblDateTransfre.setBounds(619, 33, 100, 26);
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
		
		
		listFournisseurMP=fournisseurMPDAO.findAll();
		combofournisseur.addItem("");
		
		JLabel lblDateTransfreAu = new JLabel("Date Transf\u00E8re Au :");
		lblDateTransfreAu.setBounds(890, 32, 100, 26);
		layeredPane.add(lblDateTransfreAu);
		
		  dateTransfereChooserAu = new JDateChooser();
		dateTransfereChooserAu.setLocale(Locale.FRANCE);
		dateTransfereChooserAu.setDateFormatString("dd/MM/yyyy");
		dateTransfereChooserAu.setBounds(996, 33, 155, 26);
		layeredPane.add(dateTransfereChooserAu);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Details Transferts");
		titledSeparator_1.setBounds(9, 565, 1265, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(9, 595, 1265, 294);
		add(scrollPane_1);
		
		  table_DetailTransfert = new JXTable();
		scrollPane_1.setViewportView(table_DetailTransfert);
		table_DetailTransfert.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code MP", "Nom MP", "Fournisseur", "Quantite", "Prix", "Magasin Source", "Magasin Destination"
			}
		));
		table_DetailTransfert.getColumnModel().getColumn(0).setPreferredWidth(79);
		table_DetailTransfert.getColumnModel().getColumn(1).setPreferredWidth(162);
		table_DetailTransfert.getColumnModel().getColumn(3).setPreferredWidth(98);
		table_DetailTransfert.getColumnModel().getColumn(5).setPreferredWidth(146);
		table_DetailTransfert.getColumnModel().getColumn(6).setPreferredWidth(120);
		table_DetailTransfert.setShowVerticalLines(false);
		table_DetailTransfert.setSelectionBackground(new Color(51, 204, 255));
		table_DetailTransfert.setRowHeightEnabled(true);
		table_DetailTransfert.setRowHeight(20);
		table_DetailTransfert.setGridColor(Color.BLUE);
		table_DetailTransfert.setForeground(Color.BLACK);
		table_DetailTransfert.setColumnControlVisible(true);
		table_DetailTransfert.setBackground(Color.WHITE);
		table_DetailTransfert.setAutoCreateRowSorter(true);
		for(int j=0;j<listFournisseurMP.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseurMP.get(j);	
			combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseurMPTmp.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		} 
		
		
				  		 
	}
	
	void afficher_tableMP(List<TransferStockMP> listTransfertMP)
	{

		
		intialiserTableau();
		 
	        
		  int i=0;
			while(i<listTransfertMP.size())
			{	
				
			 
				TransferStockMP transferStockMP=  listTransfertMP.get(i);
						
						
						Object []ligne={transferStockMP.getCodeTransfer(),transferStockMP.getDateTransfer(),transferStockMP.getStatut()};

						modeleMP.addRow(ligne);
						
						  
				i++;
			}
			table.setModel(modeleMP); 
	}
	

	void afficher_tableDetailMP(List<DetailTransferStockMP> listDetailTransfertMP)
	{

		
		intialiserDetailTransfertMP();
		 
	        
		  int i=0;
			while(i<listDetailTransfertMP.size())
			{	
				
			 String fournisseur="";
				DetailTransferStockMP DetailtransferStockMP=  listDetailTransfertMP.get(i);
					 
						if(DetailtransferStockMP.getFournisseur()!=null)
						{
							fournisseur=DetailtransferStockMP.getFournisseur().getCodeFournisseur();
						}
						Object []ligne={DetailtransferStockMP.getMatierePremier().getCode(),DetailtransferStockMP.getMatierePremier().getNom(),fournisseur,DetailtransferStockMP.getQuantite(),DetailtransferStockMP.getMagasinSouce().getLibelle(),DetailtransferStockMP.getMagasinDestination().getLibelle()};

						modeleDetailMP.addRow(ligne);
						
						  
				i++;
			}
			table_DetailTransfert.setModel(modeleDetailMP); 
	}






void intialiserDetailTransfertMP(){
	
	
	modeleDetailMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Fournisseur","QuantitÈ","Magasin Source","Magasin Destination"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false ,false,false 
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table_DetailTransfert.setModel(modeleDetailMP); 
		 table_DetailTransfert.getColumnModel().getColumn(0).setPreferredWidth(50);
		 table_DetailTransfert.getColumnModel().getColumn(1).setPreferredWidth(160);
		 table_DetailTransfert.getColumnModel().getColumn(2).setPreferredWidth(160);
		 table_DetailTransfert.getColumnModel().getColumn(3).setPreferredWidth(160);
		 table_DetailTransfert.getColumnModel().getColumn(4).setPreferredWidth(160);
		 table_DetailTransfert.getColumnModel().getColumn(5).setPreferredWidth(160);
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}

void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Transfert","Date Transfert","Etat"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false 
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





}

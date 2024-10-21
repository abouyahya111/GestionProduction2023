package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EntrerStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	
	
	
	private Map< Integer, String> mapQuantiteMP = new HashMap<>();
	
	private Map< String, BigDecimal> mapQuantiteMPManquante = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< Integer, FournisseurMP> mapFournisseurMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<DetailTransferStockMP> listDetailTransfertMP=new ArrayList<DetailTransferStockMP>();
	TransferStockMP transferStock = new TransferStockMP();
	private List<DetailTransferStockMP> listDetailTransfertMpTmp =new ArrayList<DetailTransferStockMP>();
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	private JTextField txtdesignation;
	private FournisseurMPDAO fournisseurMPDAO;
	boolean ecart=false;
	boolean TransfertExistant=false;
	JComboBox comboCodeTransfert = new JComboBox();
	List<TransferStockMP> 	listTransfertSortieEnAttente=new ArrayList<TransferStockMP>();
	List<TransferStockMP> 	listTransfertEntreeEnAttente=new ArrayList<TransferStockMP>();
	private Map< String, TransferStockMP> mapCodeTransfertEnAttente = new HashMap<>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EntrerStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 661);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
       comboMagasinDestination=new JComboBox();
       comboMagasinDestination.addItemListener(new ItemListener() {
       	public void itemStateChanged(ItemEvent arg0) {
       		ChargerComboCodeTransfertEnAttente();
       	}
       });
    	comboDepotSource=new JComboBox();
    	comboMagasinSource=new JComboBox();
    	comboDepotDestination=new JComboBox();
    	
    		/* depot = depotDAO.findByCode(utilisateur.getCodeDepot());
    		
    		 comboDepotDestination.addItem(depot.getLibelle());*/
    	
    		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
    			  
    	    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
    	    		 comboDepotDestination.addItem(depot.getLibelle());
    	    	
    	    }else {
    	    	comboDepotDestination.addItem("");
    	    	
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
    		 comboDepotSource.addItem("");
    		
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(407, 572, 112, 23);
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
				  		     table.setSortable(false);
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
				  		     	scrollPane.setBounds(9, 155, 1015, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		    List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,MAGASIN_CODE_CATEGORIE_STOCKAGE);
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
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
							  		     
					  		     	 	//listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
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
				  		     	 	 }
				  		     	 	}
				  		     	 });
					  		  /*    
					  		      
				  		     
				  		     	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      	
					  		      	
					  		    
					  		      
					  	    comboDepotSource.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinSource.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotSource.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
							  		       
					  		     	  //	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
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
				  		     	 });*/
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 126, 1015, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1015, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 144, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 161, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(390, 74, 184, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(274, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("D\u00E9pot Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("Dépot Destination");
				  		  lblDpotDestination.setBounds(285, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(390, 34, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("D\u00E9pot D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(285, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTransfer = new JLabel("Code Transfer");
				  		  lblCodeTransfer.setBounds(566, 34, 81, 24);
				  		  layeredPane.add(lblCodeTransfer);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(701, 71, 214, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(610, 69, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		   comboCodeTransfert = new JComboBox();
				  		  comboCodeTransfert.setBounds(645, 34, 328, 24);
				  		  layeredPane.add(comboCodeTransfert);
				  		AutoCompleteDecorator.decorate(comboCodeTransfert);
		
		 btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ecart=false;
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				if(comboMagasinDestination.getSelectedItem().equals(comboMagasinSource.getSelectedItem())){
					JOptionPane.showMessageDialog(null, "Le Magasin source doit ètre différent déstination", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if(comboCodeTransfert.getSelectedItem().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					
				}
				else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir la date de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
					
					
					
					
					TransferStockMP transferStockMP=mapCodeTransfertEnAttente.get(comboCodeTransfert.getSelectedItem());
					if(transferStockMP==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Code Transfert SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					 
					Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
					
					
					
					TransferStockMP transferStockMPTmpEnAttente = null;
					
					if(transferStockMP.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT))
					{
						transferStockMPTmpEnAttente=transferStockMP;
					}
							 
					
					if(transferStockMPTmpEnAttente!=null)
					{
						transferStock=transferStockMPTmpEnAttente;
						transferStock.setCodeTransfer(transferStockMPTmpEnAttente.getCodeTransfer());
						transferStock.setCreerPar(AuthentificationView.utilisateur);
						transferStock.setDate(new Date());
						transferStock.setDateTransfer(dateTransfereChooser.getDate());
						if(ecart==true)
						{
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
							transferStock.setEtat(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
							transferStock.setVuPar(AFFICHER_TRANSFERT_ENATTENTE_ADMIN);
							
						}else
						{
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
							transferStock.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
						}
						
						listDetailTransfertMpTmp=remplirDetailTransfer();
						//transferStock.setListDetailTransferMP(listDetailTransfertMpTmp);
						transferStock.setDepot(magasinDestination.getDepot());
						transferStock.setDesignation(txtdesignation.getText());
						
						transferStockMPDAO.edit(transferStock);
						
						for(int d=0;d<listDetailTransfertMpTmp.size();d++)
						{
							
							if(listDetailTransfertMpTmp.get(d).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								DetailTransferStockMP detailTransferStockMP=detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMPByFournisseur(listDetailTransfertMpTmp.get(d).getMatierePremier(), listDetailTransfertMpTmp.get(d).getTransferStockMP(), listDetailTransfertMpTmp.get(d).getFournisseur());
								if(detailTransferStockMP!=null)
								{
									detailTransferStockMP.setQuantiteAncien(detailTransferStockMP.getQuantite());
									detailTransferStockMP.setQuantite(listDetailTransfertMpTmp.get(d).getQuantite());
									detailTransferStockMP.setPrixUnitaire(listDetailTransfertMpTmp.get(d).getPrixUnitaire());
									detailTransferStockMP.setFournisseur(listDetailTransfertMpTmp.get(d).getFournisseur());
									detailTransferMPDAO.edit(detailTransferStockMP);
								}
								
								
							}else
							{
								DetailTransferStockMP detailTransferStockMP=detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMP(listDetailTransfertMpTmp.get(d).getMatierePremier(), listDetailTransfertMpTmp.get(d).getTransferStockMP());
								if(detailTransferStockMP!=null)
								{
									detailTransferStockMP.setQuantiteAncien(detailTransferStockMP.getQuantite());
									detailTransferStockMP.setQuantite(listDetailTransfertMpTmp.get(d).getQuantite());
									detailTransferStockMP.setPrixUnitaire(listDetailTransfertMpTmp.get(d).getPrixUnitaire());
									detailTransferMPDAO.edit(detailTransferStockMP);
								}
							}
							
							
							
							
						}
						
						if(ecart==false)
						{
							JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String date=dateFormat.format(dateTransfereChooser.getDate());
							Map parameters = new HashMap();
							parameters.put("numTransfer", transferStockMPTmpEnAttente.getCodeTransfer());
							parameters.put("machineSource", listDetailTransfertMpTmp.get(0).getMagasinSouce().getLibelle());
							parameters.put("depSource", listDetailTransfertMpTmp.get(0).getMagasinSouce().getDepot().getLibelle());
							parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
							parameters.put("depDest", comboDepotDestination.getSelectedItem());
							parameters.put("dateTransfer", date);
							parameters.put("designation", txtdesignation.getText().toUpperCase());
							JasperUtils.imprimerBonEntrerMP(listDetailTransfertMpTmp,parameters,transferStockMPTmpEnAttente.getCodeTransfer());
						}else
						{
							JOptionPane.showMessageDialog(null, "Stock transféré Contient des Ecarts ", "Succès", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
						
						
						
						
					}else
					{
						transferStock.setCodeTransfer(transferStockMP.getCodeTransfer());
						transferStock.setCreerPar(AuthentificationView.utilisateur);
						transferStock.setDate(new Date());
						transferStock.setDateTransfer(dateTransfereChooser.getDate());
						if(ecart==true)
						{
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
							transferStock.setEtat(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
							transferStock.setVuPar(AFFICHER_TRANSFERT_ENATTENTE_ADMIN);
							
						}else
						{
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
							transferStock.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
						}
						
						listDetailTransfertMpTmp=remplirDetailTransfer();
						transferStock.setListDetailTransferMP(listDetailTransfertMpTmp);
						transferStock.setDepot(magasinDestination.getDepot());
						transferStock.setDesignation(txtdesignation.getText());
						
						transferStockMPDAO.add(transferStock);
						
						
						if(ecart==false)
						{
							
							JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String date=dateFormat.format(dateTransfereChooser.getDate());
							Map parameters = new HashMap();
							parameters.put("numTransfer", transferStockMP.getCodeTransfer());
							parameters.put("machineSource", listDetailTransfertMpTmp.get(0).getMagasinSouce().getLibelle());
							parameters.put("depSource", listDetailTransfertMpTmp.get(0).getMagasinSouce().getDepot().getLibelle());
							parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
							parameters.put("depDest", comboDepotDestination.getSelectedItem());
							parameters.put("dateTransfer", date);
							parameters.put("designation", txtdesignation.getText().toUpperCase());
							JasperUtils.imprimerBonEntrerMP(listDetailTransfertMpTmp,parameters,transferStock.getCodeTransfer());
							
						}else
						{
							JOptionPane.showMessageDialog(null, "Stock transféré Contient des Ecarts ", "Succès", JOptionPane.INFORMATION_MESSAGE);

						}
						
						
						
					}
					
			
				
				
				
				
				if(ecart==false)
				{
					
					if(transferStockMP.getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
					{
						transferStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
						transferStockMPDAO.edit(transferStockMP);
						
					}
					
					 
					
					
					
					
					
					TransferStockMP transferStockMPTmp=new TransferStockMP();
					transferStockMPTmp.setCodeTransfer(listDetailTransfertMpTmp.get(0).getTransferStockMP().getCodeTransfer());
					transferStockMPTmp.setCreerPar(utilisateur);
					transferStockMPTmp.setDate(new Date());
					transferStockMPTmp.setDateTransfer(transferStockMP.getDateTransfer());
					transferStockMPTmp.setDepot(listDetailTransfertMpTmp.get(0).getTransferStockMP().getDepot());
					transferStockMPTmp.setSoustype(listDetailTransfertMpTmp.get(0).getTransferStockMP().getSoustype());
					transferStockMPTmp.setType(listDetailTransfertMpTmp.get(0).getTransferStockMP().getType());
					transferStockMPTmp.setStatut(Constantes.ETAT_SORTIE_ENATTENT_VALIDER);
						transferStockMPDAO.add(transferStockMPTmp);
						
						for(int d=0;d<listDetailTransfertMpTmp.size();d++)
						{
							
						DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();	
						detailTransferStockMP.setFournisseur(listDetailTransfertMpTmp.get(d).getFournisseur());
						detailTransferStockMP.setMagasinDestination(listDetailTransfertMpTmp.get(d).getMagasinDestination());
						detailTransferStockMP.setMagasinSouce(listDetailTransfertMpTmp.get(d).getMagasinSouce());
						detailTransferStockMP.setMatierePremier(listDetailTransfertMpTmp.get(d).getMatierePremier());
						detailTransferStockMP.setPrixUnitaire(listDetailTransfertMpTmp.get(d).getPrixUnitaire());
						detailTransferStockMP.setQuantite(listDetailTransfertMpTmp.get(d).getQuantite());
						detailTransferStockMP.setQuantiteDechet(listDetailTransfertMpTmp.get(d).getQuantiteDechet());
						detailTransferStockMP.setQuantiteExistante(listDetailTransfertMpTmp.get(d).getQuantiteExistante());
						detailTransferStockMP.setQuantiteManque(listDetailTransfertMpTmp.get(d).getQuantiteManque());
						detailTransferStockMP.setQuantiteOffre(listDetailTransfertMpTmp.get(d).getQuantiteOffre());
						detailTransferStockMP.setQuantiteRetour(listDetailTransfertMpTmp.get(d).getQuantiteRetour());
						detailTransferStockMP.setStockSource(listDetailTransfertMpTmp.get(d).getStockSource());
						detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
						detailTransferMPDAO.add(detailTransferStockMP);
						
						
						}
					
				}
				
				
		
				ChargerComboCodeTransfertEnAttente();
				
				intialiserTableau();
				
				
			//	intialiser();
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(155, 572, 165, 23);
		add(btnValiderTransfer);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1057, 34, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  			/*	if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {*/
				
				detailTransferMPDAO.ViderSession();
				TransferStockMP transferStockMP=mapCodeTransfertEnAttente.get(comboCodeTransfert.getSelectedItem());
				 listDetailTransfertMpTmp.clear();
				if(transferStockMP==null)
				{
					listDetailTransfertMP.clear();
						afficher_tableMP(listDetailTransfertMP);
					return;
				}
				
				
			
				TransfertExistant=false;
			
				  					 
				  					
				  					TransferStockMP transferStockMPTmp =null;
				  					if(transferStockMP.getCodeTransfer().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT))
				  					{
				  						transferStockMPTmp=transferStockMP;
				  					}
				  					 
				  					if(transferStockMPTmp!=null)
				  					{
				  						if(transferStockMPTmp.getVuPar()!=null)
				  						{
				  							if(transferStockMPTmp.getVuPar().equals(Constantes.AFFICHER_TRANSFERT_ENATTENTE_USER))
				  							{
				  								TransfertExistant=true;
						  						transferStockMP=transferStockMPTmp;
				  							}
				  						}
				  						
				  					}
				  					
				  				//	if(transferStockMP!=null){
				  						DetailTransferStockMP detailTransferStockMP=null;
				  				//	Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				  					// listDetailTransfertMP=detailTransferMPDAO.findDetailTransferMPByNumOFStatut(txtReftransfer.getText(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
				  						
				  					if(transferStockMP!=null)
				  						
				  						if(transferStockMPTmp!=null)
					  					{
				  							
				  							if(transferStockMPTmp.getVuPar()!=null)
					  						{
					  							if(transferStockMPTmp.getVuPar().equals(Constantes.AFFICHER_TRANSFERT_ENATTENTE_USER))
					  							{

							  						
							  						listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
								  					//JOptionPane.showMessageDialog(null, listDetailTransfertMP.size());
								  					
								  					if(detailTransferStockMP!=null){
								  						comboMagasinSource.addItem(detailTransferStockMP.getMagasinSouce().getLibelle());
								  						comboMagasinSource.setSelectedItem(detailTransferStockMP.getMagasinSouce().getLibelle());
								  						comboDepotSource.addItem(detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
								  						comboDepotSource.setSelectedItem(detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
								  						
								  				/*		Magasin magasinDest= depotDAO.findByCode(utilisateur.getCodeDepot()).getListMagasin();  
								  						comboMagasinDestination.addItem(detailTransferStockMP.getMagasinDestination().getLibelle());
								  						comboMagasinDestination.setSelectedItem(detailTransferStockMP.getMagasinDestination().getLibelle());
								  						comboDepotDestination.addItem(detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());
								  						comboDepotDestination.setSelectedItem(detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());*/
								  					}
								  					
								  					
								  					afficher_tableMP(listDetailTransfertMP);
							  						
							  					
					  							}else
					  							{
					  								listDetailTransfertMP.clear();
					  								afficher_tableMP(listDetailTransfertMP);
					  								
					  							}
					  						}else
				  							{
				  								listDetailTransfertMP.clear();
				  								afficher_tableMP(listDetailTransfertMP);
				  								
				  							}
				  							
				  							
				  							
					  					}else
					  					{
					  						
					  						listDetailTransfertMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
						  					//JOptionPane.showMessageDialog(null, listDetailTransfertMP.size());
						  					
						  					if(detailTransferStockMP!=null){
						  						comboMagasinSource.addItem(detailTransferStockMP.getMagasinSouce().getLibelle());
						  						comboMagasinSource.setSelectedItem(detailTransferStockMP.getMagasinSouce().getLibelle());
						  						comboDepotSource.addItem(detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
						  						comboDepotSource.setSelectedItem(detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
						  						
						  				/*		Magasin magasinDest= depotDAO.findByCode(utilisateur.getCodeDepot()).getListMagasin();  
						  						comboMagasinDestination.addItem(detailTransferStockMP.getMagasinDestination().getLibelle());
						  						comboMagasinDestination.setSelectedItem(detailTransferStockMP.getMagasinDestination().getLibelle());
						  						comboDepotDestination.addItem(detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());
						  						comboDepotDestination.setSelectedItem(detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());*/
						  					}
						  					
						  					
						  					afficher_tableMP(listDetailTransfertMP);
					  						
					  					}
				  						
				  					
				  					
				  					
				  				/*	}else {
				  						JOptionPane.showMessageDialog(null, "Ce Transfert est déjà validé..!!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				  					}*/
				  					
				  			//	}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnBonEntrerMp = new JButton("Bon Entrer MP D\u00E9plac\u00E9e");
		btnBonEntrerMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

	  		  	if(transferStock.getId()>0){
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("")){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(transferStock.getDateTransfer());
				 List<DetailTransferStockMP> listDetailTransferStockMP=transferStock.getListDetailTransferMP();
				 
				 if(listDetailTransferStockMP.size()!=0)
				 {
					 
					 if(ecart==false)
						{
						 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
							Map parameters = new HashMap();
							parameters.put("numTransfer", transferStock.getCodeTransfer());
							parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
							parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
							parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
							parameters.put("depDest", comboDepotDestination.getSelectedItem());
							parameters.put("dateTransfer", date);
							parameters.put("designation", detailTransferStockMP.getTransferStockMP().getDesignation().toUpperCase());
							JasperUtils.imprimerBonEntrerMP(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
							intialiserTableau();
							//listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
							btnIntialiserOF.setEnabled(true);
							btnValiderTransfer.setEnabled(true);
							refrech();
						}else
						{
							 JOptionPane.showMessageDialog(null, "Stock Transféré Contient Des Ecarts", "Erreur", JOptionPane.INFORMATION_MESSAGE);
						}
					 
						
						
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir et valider les matières premiére", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				
	  		  		}else 
	  		  		{
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
				
				
				
			}
		});
		btnBonEntrerMp.setBounds(531, 572, 178, 23);
		btnBonEntrerMp.setIcon(imgImprimer);
		add(btnBonEntrerMp);
		
		JLabel label = new JLabel("Designation :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(9, 514, 102, 24);
		add(label);
		
		txtdesignation = new JTextField();
		txtdesignation.setText("");
		txtdesignation.setColumns(10);
		txtdesignation.setBounds(98, 514, 968, 26);
		add(txtdesignation);
		
		ChargerComboCodeTransfertEnAttente();		  		 
	}
	
	
	void intialiser()
	{
		//comboDepotDestination.setSelectedIndex(-1);
		comboDepotSource.setSelectedIndex(-1);
		//comboMagasinDestination.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		comboCodeTransfert.setSelectedItem("");
		dateTransfereChooser.setCalendar(null);
		txtdesignation.setText("");
		intialiserTableau();
		
		
	}
	
	void afficher_tableMP(List<DetailTransferStockMP> listDetailTransfertMP)
	{
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);
		
			intialiserTableau();
			 int i=0;
				while(i<listDetailTransfertMP.size())
				{	
					
					String fournisseur="";
					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(i);
					mapMatierePremierTmp.put(detailTransferStockMP.getMatierePremier().getCode(), detailTransferStockMP.getMatierePremier());
if(detailTransferStockMP.getFournisseur()!=null)
{
	fournisseur=detailTransferStockMP.getFournisseur().getCodeFournisseur();
}
 
	
	Object []ligne={detailTransferStockMP.getMatierePremier().getCode(),detailTransferStockMP.getMatierePremier().getNom(), fournisseur,dfDecimal.format(detailTransferStockMP.getQuantite()),""};

	modeleMP.addRow(ligne);
	
 

					
				
					
					i++;
				}

		 
		
			
			table.setModel(modeleMP); 
			
	}
	
	
	public void ChargerComboCodeTransfertEnAttente()
	{
		comboCodeTransfert.removeAllItems();
		comboCodeTransfert.addItem("");
		Depot depot = null;depotDAO.findByCode(utilisateur.getCodeDepot());
		if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
			
			 depot =depotDAO.findByCode(utilisateur.getCodeDepot());
			
		}else
		{
			depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem().toString());
		
		}
			  
   	transferStockMPDAO.ViderSession();
   	detailTransferMPDAO.ViderSession();
		 
	  	listTransfertSortieEnAttente.clear();
	  	listTransfertEntreeEnAttente.clear();
	  	
		if(depot!=null)
		{
			listTransfertEntreeEnAttente = transferStockMPDAO.ListeCodeTransfertEnAttente( Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT,depot);
		  	 
		  	 
		  	 
		  	listTransfertSortieEnAttente = transferStockMPDAO.ListeCodeTransfertEnAttente( Constantes.ETAT_SORTIE_ENATTENT,depot);
		  	boolean existe=false;
			
		 for(int i=0;i<listTransfertSortieEnAttente.size();i++)
		 {
			 TransferStockMP transferStockMP=listTransfertSortieEnAttente.get(i);
			 existe=false;
			 for(int t=0;t<listTransfertEntreeEnAttente.size();t++)
			 {
				 if(transferStockMP.getCodeTransfer().equals(listTransfertEntreeEnAttente.get(t).getCodeTransfer()))
				 {
					 if(listTransfertEntreeEnAttente.get(t).getVuPar().equals(AFFICHER_TRANSFERT_ENATTENTE_USER))
					 {
						 comboCodeTransfert.addItem(listTransfertEntreeEnAttente.get(t).getCodeTransfer());
						 mapCodeTransfertEnAttente .put(listTransfertEntreeEnAttente.get(t).getCodeTransfer(), listTransfertEntreeEnAttente.get(t)); 
						 
					 }
					 existe=true;
					 
				 }
				 
				 
			 }
			 if(existe==false)
			 {
				 if(transferStockMP.getDepotDestination().getId()==depot.getId())
				 {
					 comboCodeTransfert.addItem(transferStockMP.getCodeTransfer());
					 mapCodeTransfertEnAttente .put(transferStockMP.getCodeTransfer(), transferStockMP);
				 }
				
				 
			 }
			
			 
			 
		 }
		}
	  
		
	
	 
	 
	 
	 
		
	}
	
	
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
	mapQuantiteMP.clear();
	mapMatierePremier.clear();
	 
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 4).toString().equals("") ){
			//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			if(!table.getValueAt(j, 2).toString().equals(""))
			{
				FournisseurMP fournisseurMP=fournisseurMPDAO.findByCode(table.getValueAt(j, 2).toString());
				mapFournisseurMP.put(i, fournisseurMP);
			}
			
			mapQuantiteMP.put(j, table.getValueAt(j, 4).toString());
			 
			
			if(new BigDecimal(table.getValueAt(j, 4).toString()).compareTo(listDetailTransfertMP.get(j).getQuantite())!=0)
			{
				ecart=true;
			}
			
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}else
		{
			JOptionPane.showMessageDialog(null, "Veuillez Entrer La Quantite SVP , Si la Case de Quantité est vide Veuillez entre la Quantité 0 SVP"); 
			trouve=false;
			break;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	BigDecimal quantiteManquante=BigDecimal.ZERO;
	
	TransferStockMP transferStockMPTmpEnAttente =null;
	
	
	if(mapCodeTransfertEnAttente.get(comboCodeTransfert.getSelectedItem())!=null)
	{
		
		if(mapCodeTransfertEnAttente.get(comboCodeTransfert.getSelectedItem()).equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT))
		{
			
			transferStockMPTmpEnAttente=mapCodeTransfertEnAttente.get(comboCodeTransfert.getSelectedItem());
		}
		
		
	}
	
	 
	
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(i));
		
	//	if(quantite.compareTo(BigDecimal.ZERO) >0){
			
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		//Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		FournisseurMP fournisseurMP=null;
		
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			fournisseurMP=mapFournisseurMP.get(i);
		}
		
		
		/*
		StockMP stockMPSource=null;
		StockMP stockMPDestination=null;
		
		if(fournisseurMP!=null)
		{
			 stockMPSource=stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasinSource.getId(),fournisseurMP.getId());
			 stockMPDestination=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinDestination.getId(),fournisseurMP.getId());
			
		}else
		{
			 stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
			 stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
		}
		
		
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		
		
		prixStockDestination=stockMPDestination.getPrixUnitaire();
		prixStockSource=stockMPSource.getPrixUnitaire();
		*/
	//	prixMoyen=prixStockDestination.multiply(stockMPDestination.getStock()).add(prixStockSource) .multiply(quantite) ;
		
		//prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		//stockMPDestination.setPrixUnitaire(prixMoyen);
		
		//stockMPDestination.setPrixUnitaire(prixMoyen);
		/*
		stockMPDestination.setStock(stockDestination);
		stockMPSource.setStock(stockSource);
		
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		*/
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		if(detailTransferStockMP.getQuantite()!=null)
		{
			detailTransferStockMP.setQuantiteAncien(detailTransferStockMP.getQuantite());
		}
		 
		detailTransferStockMP.setQuantite(quantite);
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
			if(fournisseurMP!=null)
			{
				detailTransferStockMP.setFournisseur(fournisseurMP);
			}
			
			}
		detailTransferStockMP.setPrixUnitaire(matierePremier.getPrix());
		if(transferStockMPTmpEnAttente!=null)
		{
			detailTransferStockMP.setTransferStockMP(transferStockMPTmpEnAttente);
		}else
		{
			detailTransferStockMP.setTransferStockMP(transferStock);
		}
		
		listDetailTransferStockMP.add(detailTransferStockMP);
	//}
		/*else {
		JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}*/
		
		
	}
	
	return listDetailTransferStockMP;
	
}


void refrech()
{
	if(listDetailTransfertMpTmp.size()!=0)
	{
		listDetailTransfertMpTmp.clear();
		transferStock = new TransferStockMP();
	}else
	{
		transferStock = new TransferStockMP();
	}
		
	
	intialiser();
	btnIntialiserOF.setEnabled(false);
	
	 
	
}





void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Fournisseur", "Quantité En Stock","Quantité a Tranférer"
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
    table.getColumnModel().getColumn(3).setPreferredWidth(160);
    table.getColumnModel().getColumn(4).setPreferredWidth(160);
    table.isSortable();
    
}
}

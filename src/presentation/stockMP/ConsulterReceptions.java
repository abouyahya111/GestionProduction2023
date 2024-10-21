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
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ServiceDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ServiceDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import dao.entity.service;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ConsulterReceptions extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	private JDateChooser dateTransfereChooserdu;
	
	
	
	private Map< Integer, String> mapQuantiteMP = new HashMap<>();
	private Map< String, BigDecimal> mapQuantiteMPManquante = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<DetailTransferStockMP> listDetailTransfertMP=new ArrayList<DetailTransferStockMP>();
	List<DetailTransferStockMP> listDetailTransfertMPImprimer=new ArrayList<DetailTransferStockMP>();
	List<TransferStockMP> listNumreception=new ArrayList<TransferStockMP>();
	
	TransferStockMP transferStock = new TransferStockMP();
	private List<DetailTransferStockMP> listDetailTransfertMpTmp =new ArrayList<DetailTransferStockMP>();
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
	private JLabel lblDpotDestination;
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtReftransfer;
	JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	JDateChooser dateTransfereChooserau = new JDateChooser();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private JTextField txtCodeMP;
	 JComboBox combomp = new JComboBox();
	 JComboBox comboNumReception = new JComboBox();
	 JComboBox comboService = new JComboBox();
	 private List<service> listService =new ArrayList<service>();
	  ServiceDAO serviceDAO;
	  private Map< String, service> mapService = new HashMap<>();
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterReceptions() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1395, 661);
        try{
        	
        	serviceDAO=new ServiceDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
        	detailTransferMPDAO= new DetailTransferMPDAOImpl();
        	listService=serviceDAO.findAll();
        	listNumreception=transferStockMPDAO.listTransferByStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
       comboMagasinDestination=new JComboBox();
    	comboDepotDestination=new JComboBox();
    	
    
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(314, 570, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnIntialiserOF.setEnabled(false);
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
				  		     	scrollPane.setBounds(9, 200, 1192, 343);
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

				  		     	 	Depot  	 depot =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
							  		     
					  		     	 	//listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
				  		     	 	if(depot!=null)
				  		     	 	{
				  		     	 	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
				  		     	  listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
				  		     	 	}
				  		     	
				  		     
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
				  		     	titledSeparator.setTitle("  HISTORIQUE RECEPTION");
				  		     	titledSeparator.setBounds(9, 171, 1177, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 25, 1192, 111);
				  		     	add(layeredPane);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(278, 28, 184, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin :");
				  		  lblEquipe.setBounds(214, 27, 66, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblDpotDestination = new JLabel("D\u00E9pot :");
				  		  lblDpotDestination.setBounds(10, 27, 51, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(52, 28, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  JLabel lblCodeTransfer = new JLabel("Code Transfer");
				  		  lblCodeTransfer.setBounds(472, 28, 81, 24);
				  		  layeredPane.add(lblCodeTransfer);
				  		  
				  		  txtReftransfer = new JTextField();
				  		  txtReftransfer.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
}
				  		  });
				  		util.Utils.copycoller(txtReftransfer);
				  		  txtReftransfer.setBounds(563, 28, 144, 24);
				  		  layeredPane.add(txtReftransfer);
				  		  txtReftransfer.setColumns(10);
				  		  
				  		   dateTransfereChooserdu = new JDateChooser();
				  		  dateTransfereChooserdu.setLocale(Locale.FRANCE);
				  		  dateTransfereChooserdu.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooserdu.setBounds(767, 28, 144, 26);
				  		  layeredPane.add(dateTransfereChooserdu);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Du :");
				  		  lblDateTransfre.setBounds(717, 27, 58, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel lblDateAu = new JLabel("Date Au :");
				  		  lblDateAu.setBounds(931, 28, 58, 26);
				  		  layeredPane.add(lblDateAu);
				  		  
				  		   dateTransfereChooserau = new JDateChooser();
				  		  dateTransfereChooserau.setLocale(Locale.FRANCE);
				  		  dateTransfereChooserau.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooserau.setBounds(981, 29, 144, 26);
				  		  layeredPane.add(dateTransfereChooserau);
				  		  
				  		  JLabel label = new JLabel("Code MP:");
				  		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label.setBounds(10, 64, 66, 24);
				  		  layeredPane.add(label);
				  		  
				  		  txtCodeMP = new JTextField();
				  		  txtCodeMP.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
								if(e.getKeyCode()==e.VK_ENTER)
							      		{
									MatierePremier mp=mapCodeMatierePremiere.get(txtCodeMP.getText());
									if(mp!=null)
									{
										combomp.setSelectedItem(mp.getNom());
									}else
									{
										JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
										return;
									}
									
							      		}
							
								}
				  		  });
				  		  txtCodeMP.setColumns(10);
				  		  txtCodeMP.setBounds(70, 63, 106, 26);
				  		  layeredPane.add(txtCodeMP);
				  		  
				  		  JLabel label_1 = new JLabel("  MP :");
				  		  label_1.setBounds(187, 64, 44, 24);
				  		  layeredPane.add(label_1);
				  		  
				  		   combomp = new JComboBox();
				  		 combomp.addItem("");
				  		  combomp.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent arg0) {
				  		  		

						 		

				  		   		

					  	 		
					  	 		if(!combomp.getSelectedItem().equals(""))
					  	 		{
					  	 			
					  	 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
					  	 			if(mp!=null)
					  	 			{
					  	 				txtCodeMP.setText(mp.getCode());
					  	 			}else
					  	 			{
					  	 				JOptionPane.showMessageDialog(null, "MP Introuvable");
					  	 				return;
					  	 			}
					  	 			
					  	 			
					  	 			
					  	 		}else
					  	 		{
					  	 		txtCodeMP.setText(Constantes.CODE_MP);
					  	 		}
					  	 		
					  	 	
			  		   		
			  		   		
			  		   		
			  		   		
			  		   	
					 		
					 		
					 	
				  		  		
				  		  	}
				  		  });
				  		  combomp.setBounds(222, 64, 286, 24);
				  		  layeredPane.add(combomp);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1257, 44, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listDetailTransfertMP.clear();
				
        String requete="";
       
				Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
				Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				service service=mapService.get(comboService.getSelectedItem().toString());
				if(depot==null)
				{
					JOptionPane.showMessageDialog(null, "veuillez selectionner le depot SVP!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(dateTransfereChooserdu.getDate()!=null)
				{
					dateTransfereChooserdu.setDateFormatString("yyyy-MM-dd");
				}
				if(dateTransfereChooserau.getDate()!=null)
				{
					dateTransfereChooserau.setDateFormatString("yyyy-MM-dd");
				}
				
				String dateDu=((JTextField)dateTransfereChooserdu.getDateEditor().getUiComponent()).getText();
				
					String dateAu=((JTextField)dateTransfereChooserau.getDateEditor().getUiComponent()).getText();
					
					if(!dateDu.equals("") && dateAu.equals(""))
					{
						dateAu=dateDu;
					}else if(dateDu.equals("") && !dateAu.equals(""))
					{
						dateDu=dateAu;
					}
					
					
					
				
				
				
				requete=" where transferStockMP.depot.id='"+depot.getId()+"'";
				if(magasin!=null)
				{
					requete= requete+" and magasinDestination.id='"+magasin.getId()+"'";
				}
				
				 
				
				requete=requete+" and transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_AJOUT+"'";
				
				if(!dateDu.equals("") || !dateAu.equals(""))
				{
					
					requete=requete+" and transferStockMP.dateTransfer between '"+dateDu+"' and '"+dateAu+"'";
					
					
				}
				
				if(!combomp.getSelectedItem().equals(""))
				{
				MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem())	;
					if(mp!=null)
					{
						requete= requete+" and matierePremier.id='"+mp.getId()+"'";
					}
					
				}
				
				
				if(service!=null)
				{
					
					requete= requete+" and transferStockMP.service.id='"+service.getId()+"'";
				}
				
				
				if(!comboNumReception.getSelectedItem().equals(""))
				{
					
					requete= requete+" and transferStockMP.CodeTransfer='"+comboNumReception.getSelectedItem().toString()+"'";
					
				}
				
				listDetailTransfertMP=detailTransferMPDAO.ListHistoriqueReception(requete);
				
				
				  					
				  					afficher_tableMP(listDetailTransfertMP);
				  				/*	}else {
				  						JOptionPane.showMessageDialog(null, "Ce Transfert est déjà validé..!!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				  					}*/
				  					
				  			//	}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnBonEntrerMp = new JButton("Imprimer");
		btnBonEntrerMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

	  		  	
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("")){


				 
				 if(listDetailTransfertMP.size()!=0)
				 {
					
						Map parameters = new HashMap();


						parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
						parameters.put("depDest", comboDepotDestination.getSelectedItem());
						
						JasperUtils.HistoriqueReceptionStockMP(listDetailTransfertMP,parameters);


						
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Liste Vide", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				
	  		  		}else 
	  		  		{
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  
				
				
			}
		});
		btnBonEntrerMp.setBounds(438, 570, 178, 23);
		btnBonEntrerMp.setIcon(imgImprimer);
		add(btnBonEntrerMp);
		
		/* depot = depotDAO.findByCode(utilisateur.getCodeDepot());
		*/

		 comboDepotDestination.addItem("");
 	
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
		
		
		listMatierePremiere=matierePremiereDAO.findAll();
		for(int i=0;i<listMatierePremiere.size();i++)
		{
			
			MatierePremier matierePremier=listMatierePremiere.get(i);
			combomp.addItem(matierePremier.getNom());
			mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
			mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
		}
	
		txtCodeMP.setText(Constantes.CODE_MP);
		
		JLabel lblService = new JLabel("Service  :");
		lblService.setBounds(518, 64, 66, 24);
		layeredPane.add(lblService);
		
		  comboService = new JComboBox();
		  comboService.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent arg0) {
		  		
		  		if(!comboService.getSelectedItem().equals(""))
		  		{
		  			
		  			service service=mapService.get(comboService.getSelectedItem().toString()) ;
		  			
		  			comboNumReception.removeAllItems();
		  			comboNumReception.addItem("");
		  			for(int i=0;i<listNumreception.size();i++)
		  			{
		  				
		  				if(listNumreception.get(i).getService()!=null)
		  				{
		  					
		  					if(service.getId()==listNumreception.get(i).getService().getId())
		  					{
		  						
		  						comboNumReception.addItem(listNumreception.get(i).getCodeTransfer());
		  						
		  					}
		  					
		  					
		  				}
		  			}
		  			
		  		}else
		  		{
		  			comboNumReception.removeAllItems();
		  			comboNumReception.addItem("");
		  			for(int i=0;i<listNumreception.size();i++)
		  			{
		  				
		  			 
		  						
		  						comboNumReception.addItem(listNumreception.get(i).getCodeTransfer());
		  						
		  					 
		  					
		  				 
		  			}
		  		}
		  	}
		  });
		comboService.setBounds(573, 64, 166, 24);
		layeredPane.add(comboService);
		
		JLabel lblNumReception = new JLabel("Num Reception  :");
		lblNumReception.setBounds(749, 65, 94, 24);
		layeredPane.add(lblNumReception);
		
		  comboNumReception = new JComboBox();
		comboNumReception.setBounds(837, 65, 319, 24);
		layeredPane.add(comboNumReception);
		AutoCompleteDecorator.decorate(comboNumReception);
		JButton btnImprimerBon = new JButton("Imprimer Bon");
		btnImprimerBon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					
					if(listDetailTransfertMP.size()!=0)
					{
						
						TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(table.getValueAt(table.getSelectedRow(), 2).toString(), Constantes.ETAT_TRANSFER_STOCK_AJOUT);
						
						if(transferStockMP!=null)
						{
							listDetailTransfertMPImprimer=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
							Map parameters = new HashMap();
							parameters.put("numBL", listDetailTransfertMPImprimer.get(0).getNumBLReception());   
						parameters.put("numTransfer", listDetailTransfertMPImprimer.get(0).getTransferStockMP().getCodeTransfer());                
						parameters.put("magasinDest", listDetailTransfertMPImprimer.get(0).getMagasinDestination().getLibelle());
						parameters.put("depDest", listDetailTransfertMPImprimer.get(0).getMagasinDestination().getDepot().getLibelle());
						parameters.put("dateTransfer", listDetailTransfertMPImprimer.get(0).getTransferStockMP().getDateTransfer());
						if(transferStockMP.getService()!=null)
						{
							parameters.put("service", transferStockMP.getService().getLibelle());
						}
						
						JasperUtils.imprimerBonReceptionMP(listDetailTransfertMPImprimer,parameters);
						}
						
						
					
					 
					
					
					}
				}
			
				
				
				
				
			}
		});
		btnImprimerBon.setBounds(640, 570, 178, 23);
		btnImprimerBon.setIcon(imgImprimer);
		add(btnImprimerBon);
		
		comboService.addItem("");
		for(int i=0;i<listService.size();i++)
		{
			
			service service=listService.get(i);
			comboService.addItem(service.getLibelle());
			mapService.put(service.getLibelle(), service);
		}
		comboService.setSelectedItem("");	
		
		comboNumReception.addItem("");
		
		for(int i=0;i<listNumreception.size();i++)
		{
			
			 
			comboNumReception.addItem(listNumreception.get(i).getCodeTransfer());
			 
		}
		
		comboNumReception.setSelectedItem("");
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedIndex(-1);
	
		comboMagasinDestination.setSelectedIndex(-1);
		comboService.setSelectedItem("");
		txtReftransfer.setText("");
		dateTransfereChooserdu.setCalendar(null);
dateTransfereChooserau.setCalendar(null);
combomp.setSelectedItem("");
txtCodeMP.setText(Constantes.CODE_MP);
		intialiserTableau();
		
		
	}
	
	void afficher_tableMP(List<DetailTransferStockMP> listDetailTransfertMP)
	{

		
			intialiserTableau();
			 int i=0;
			 
			 String service="";
				while(i<listDetailTransfertMP.size())
				{	
					service="";
					DetailTransferStockMP detailTransferStockMP=listDetailTransfertMP.get(i);
					String fournisseur="";
					if(detailTransferStockMP.getFournisseur()!=null)
					{
						fournisseur=detailTransferStockMP.getFournisseur().getCodeFournisseur();
					}
					
					if(detailTransferStockMP.getTransferStockMP().getService()!=null)
					{
						service=detailTransferStockMP.getTransferStockMP().getService().getLibelle();
					}

						Object []ligne={detailTransferStockMP.getTransferStockMP().getDateTransfer(),detailTransferStockMP.getNumBLReception(), detailTransferStockMP.getTransferStockMP().getCodeTransfer(), detailTransferStockMP.getMatierePremier().getCode(), detailTransferStockMP.getMatierePremier().getNom(),fournisseur, NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),service};

						modeleMP.addRow(ligne);
				
					
					i++;
				}

		 
		
			
			table.setModel(modeleMP); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
	mapQuantiteMP.clear();
	mapMatierePremier.clear();
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapQuantiteMP.put(j, table.getValueAt(j, 3).toString());
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
	
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	BigDecimal quantiteManquante=BigDecimal.ZERO;
	
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(i));
		
	//	if(quantite.compareTo(BigDecimal.ZERO) >0){
			
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		//Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
		
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		
		
		prixStockDestination=stockMPDestination.getPrixUnitaire();
		prixStockSource=stockMPSource.getPrixUnitaire();
		
	//	prixMoyen=prixStockDestination.multiply(stockMPDestination.getStock()).add(prixStockSource) .multiply(quantite) ;
		
		//prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		//stockMPDestination.setPrixUnitaire(prixMoyen);
		
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
	//}
		/*else {
		JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}*/
		
		
	}
	
	return listDetailTransferStockMP;
	
}









void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     		"Date reception","Num BL","Code Reception","Code","Nom MP","Fournisseur","Quantité","Service"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false
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
    table.getColumnModel().getColumn(4).setPreferredWidth(60);
    table.getColumnModel().getColumn(5).setPreferredWidth(160);
   
}
}

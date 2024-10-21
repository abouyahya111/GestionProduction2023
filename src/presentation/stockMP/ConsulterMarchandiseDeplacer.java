package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;
 

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
 
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;


public class ConsulterMarchandiseDeplacer extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleDetailMP;
	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	private TransferStockMPDAO transferStockMPDAO;
	private Utilisateur utilisateur;
	JComboBox combotypeTransfert = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private DetailTransferMPDAO detailTransferMPDAO;
	private DepotDAO depotdao;
	JDateChooser dateChooserdebut = new JDateChooser();
	JDateChooser dateChooserfin = new JDateChooser();
	private JTextField txtCodeMP;
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	JComboBox comboMP = new JComboBox();
	private MatierePremiereDAO matierePremiereDAO;
	private List<MatierePremier> listeMatierePremiere=new ArrayList<MatierePremier>();
	private List <Object[]> listeObjectSituationTransfert=new ArrayList<Object[]>();
	private List <Object[]> listeObjectDetailSituationTransfert=new ArrayList<Object[]>();
	private List<TransferStockMP> listeTransfertStockMP=new ArrayList<TransferStockMP>();
	private List<DetailTransferStockMP> listeDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private Map< Integer, TransferStockMP> mapImprimer = new HashMap<>();
	private JTable tabledetailtransfert;
	JComboBox combostatut = new JComboBox();
	private JTextField txtCodeTransfert;
	JComboBox comboDepotDestination = new JComboBox();
	JComboBox comboMagasinDestination = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterMarchandiseDeplacer() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1343, 900);
        try{
        	
        	
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotdao= new DepotDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listeMatierePremiere=matierePremiereDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		
				  		     		if(table.getSelectedRow()!=-1)
				  		     		{
				  		     			 TransferStockMP transferStockMP=listeTransfertStockMP.get(table.getSelectedRow());
				  		     			 
				  		     			 listeDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
				  		     			afficher_DtailtableMP(listeDetailTransfertStockMP);
				  		     		}else
				  		     		{
				  		     			afficher_DtailtableMP(listeDetailTransfertStockMP);
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
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
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 163, 1231, 246);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Transferts ");
				  		     	titledSeparator.setBounds(9, 134, 1231, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 0, 1230, 123);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1095, 85, 113, 23);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeTransfertStockMP.clear();
				listeDetailTransfertStockMP.clear();
				String req="";

	  		  	dateChooserdebut.setDateFormatString("yyyy-MM-dd");
	  		  String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	  		dateChooserfin.setDateFormatString("yyyy-MM-dd");
	  		  String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				
	  		 Depot depot=depotdao.findByCode(combodepot.getSelectedItem().toString());
	  		  
	  		  if(depot!=null)
	  		  {
	  			req=req+"  d.transferStockMP.depot.id='"+depot.getId()+"' ";
	  		  }else
	  		  {
	  			  JOptionPane.showMessageDialog(null, "Veuillez SÈlectionner le depot SVP");
	  			return;
	  		  }
	  		  
	  		  
	  		  
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
		  		  
		  		  if(!txtCodeTransfert.getText().equals(""))
		  		  {
		  			req=req+" and d.transferStockMP.CodeTransfer='"+txtCodeTransfert.getText()+"' ";
		  		  }
		  		  	
		  		
		  		  
		  		  
		  		  
		  		  
		  		  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem().toString());
		  		  
		  		  
		  		  
		  		  if(magasin!=null)
		  		  {
		  			  
		  			  if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		  			  {
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"' ) and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null "; 
		  				  
		  				  
		  			  }else if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
		  			  {
		  				  
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE+"') and  d.magasinDestination.id='"+magasin.getId()+"' "; 
		  				  
		  				
		  				
		  			  }else if(combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
		  			  {
		  				  
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_SORTIE_ENATTENT+"') and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null "; 
		  				  
		  				
		  				
		  			  }else
		  			  {
		  				  
			  				req=req+" and (((d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE+"' ) and  d.magasinDestination.id='"+magasin.getId()+"') or ( (d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"'  or  d.transferStockMP.statut='"+Constantes.ETAT_SORTIE_ENATTENT+"' ) and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null ))"; 
 
		  				  
		  				  
		  			  }
		  			  
		  			  
		  		  }else
		  		  {
		  			JOptionPane.showMessageDialog(null, "Veuillez SÈlectionner le Magasin SVP");
		  			return;
		  		  }
		  		  
		  		  
		  		  Magasin magasinDestination=mapMagasin.get(comboMagasinDestination.getSelectedItem().toString());
		  		  if(magasinDestination!=null)
		  		  {
		  			 if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		  			  {
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"' ) and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null  and d.magasinEntrer.id='"+magasinDestination.getId()+"' "; 
		  				  
		  				  
		  			  } else if(combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
		  			  {
		  				  
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_SORTIE_ENATTENT+"') and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null and d.magasinEntrer.id='"+magasinDestination.getId()+"' ";  
		  				  
		  				
		  				
		  			  }else 
		  			  {
		  				  
		  				  
		  				req=req+" and (d.transferStockMP.statut='"+Constantes.ETAT_SORTIE_ENATTENT+"' or d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_SORTIE+"') and d.magasinSouce.id='"+magasin.getId()+"' and d.transferStockMP.depotDestination is not null and d.magasinEntrer.id='"+magasinDestination.getId()+"' ";  
		  				  
		  				
		  				
		  			  }
		  			 
		  			  
		  		  }
		  		  
		  		  
		  		  
		  		 if(!txtCodeMP.getText().equals("")) 
		  		 {
		  			 MatierePremier mp=MapCodeMatierPremiere.get(txtCodeMP.getText());
		  			 if(mp!=null)
		  			 {
		  				req=req+" and d.matierePremier.id='"+mp.getId()+"' ";
		  			 }
		  		 }
		  		 
		  		 if(!req.equals(""))
		  		 {
		  			 listeTransfertStockMP=transferStockMPDAO.listTransferMarchandiseDeplacer(req);
		  			 
		  			 
		  		 }else
		  		 {
		  			 JOptionPane.showMessageDialog(null, "veuillez Entrer Un Ou Plusieurs champs SVP !!!!!");
		  			 return;
		  		 }
		  		 
		  		  
		  		  afficher_tableMP(listeTransfertStockMP);
		  		  
		  		  
		  		  
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
	JLabel label = new JLabel("Date Debut :");
	label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	label.setBounds(10, 12, 101, 24);
	layeredPane.add(label);
	
	 dateChooserdebut = new JDateChooser();
	dateChooserdebut.setLocale(Locale.FRANCE);
	dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	dateChooserdebut.setBounds(106, 11, 169, 26);
	layeredPane.add(dateChooserdebut);
	
	JLabel label_1 = new JLabel("Date Fin :");
	label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	label_1.setBounds(285, 13, 80, 24);
	layeredPane.add(label_1);
	
	 dateChooserfin = new JDateChooser();
	dateChooserfin.setLocale(Locale.FRANCE);
	dateChooserfin.setDateFormatString("dd/MM/yyyy");
	dateChooserfin.setBounds(359, 11, 169, 26);
	layeredPane.add(dateChooserfin);
	
	JLabel lblDepot = new JLabel("Depot Source  :");
	lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblDepot.setBounds(538, 12, 77, 24);
	layeredPane.add(lblDepot);
	
	 combodepot = new JComboBox();
	 combodepot.addItemListener(new ItemListener() {
	 	public void itemStateChanged(ItemEvent e) {
	 		

     		
    		 if(e.getStateChange() == ItemEvent.SELECTED) {
    			 
    		
    				  Depot depot=depotdao.findByCode(combodepot.getSelectedItem().toString());
    				  if(depot!=null)
    				  {
    					
    					  
    					  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
    					  int k=0;
    					  combomagasin.removeAllItems();
    				     	 combomagasin.addItem("");
    				     	while (k<listMagasin.size())
    				     	{
    				     		Magasin magasin=listMagasin.get(k);
    				     		
    				     		
    				     		combomagasin.addItem(magasin.getLibelle());
    						     		
    						     		mapMagasin.put(magasin.getLibelle(), magasin);
    						     	
    				     		k++;
    				     		
    				     	}
    					 
    				  }else
    				  {
    					  combomagasin.removeAllItems();
    					  combomagasin.addItem("");
    				  }
    			 
    			 
    			 
    			 
    			 
    			 
    			 
    			 
    			 
    		 }
    		
    		
    		
    		
    		
    		
    	
	 		
	 		
	 		
	 		
	 	}
	 });
	combodepot.setBounds(621, 14, 176, 24);
	layeredPane.add(combodepot);
	
	 combomagasin = new JComboBox();
	combomagasin.setBounds(901, 13, 239, 24);
	layeredPane.add(combomagasin);
	
	JLabel lblMagasin = new JLabel("Magasin Source  :");
	lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblMagasin.setBounds(807, 12, 96, 24);
	layeredPane.add(lblMagasin);
	
	JLabel label_2 = new JLabel("Code MP:");
	label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
	label_2.setBounds(10, 83, 67, 24);
	layeredPane.add(label_2);
	
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
	txtCodeMP.setBounds(70, 83, 118, 26);
	layeredPane.add(txtCodeMP);
	
	JLabel label_3 = new JLabel("MP :");
	label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	label_3.setBounds(198, 85, 37, 24);
	layeredPane.add(label_3);
	
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
	comboMP.setSelectedIndex(-1);
	comboMP.setBounds(232, 85, 232, 24);
	layeredPane.add(comboMP);
	
	

    
    
    
	
		txtCodeMP.setText("");
	comboMP.removeAllItems();
		comboMP.addItem("");
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStatut.setBounds(484, 83, 96, 24);
		layeredPane.add(lblStatut);
		
		 combostatut = new JComboBox();
		combostatut.setBounds(552, 85, 197, 24);
		layeredPane.add(combostatut);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Detail Transferts ");
		titledSeparator_1.setBounds(9, 420, 1231, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 474, 1231, 273);
		add(scrollPane_1);
		
		tabledetailtransfert = new JTable();
		tabledetailtransfert.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code MP", "Mati\u00E8re Premi\u00E8re", "Date Transfert", "Code Transfert", "Statut", "Quantit\u00E9","Depot Source", "Magasin Source","Depot Destination", "Magasin Destination"
			}
		));
		tabledetailtransfert.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tabledetailtransfert);

		for(int i=0;i<listeMatierePremiere.size();i++)	
		{
			
			MatierePremier matierePremier=listeMatierePremiere.get(i);
		comboMP.addItem(matierePremier.getNom());
			MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
			MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
			
		}
		
		
		
		
		combostatut.addItem("");
		combostatut.addItem(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
		combostatut.addItem(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
		combostatut.addItem(Constantes.ETAT_SORTIE_ENATTENT);
		
		JLabel lblCodeTransfert = new JLabel("Code Transfert:");
		lblCodeTransfert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodeTransfert.setBounds(745, 49, 91, 24);
		layeredPane.add(lblCodeTransfert);
		
		txtCodeTransfert = new JTextField();
		txtCodeTransfert.setText("");
		txtCodeTransfert.setColumns(10);
		txtCodeTransfert.setBounds(848, 49, 144, 26);
		layeredPane.add(txtCodeTransfert);
		
		JLabel lblDepotDestination = new JLabel("Depot Destination :");
		lblDepotDestination.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepotDestination.setBounds(10, 48, 101, 24);
		layeredPane.add(lblDepotDestination);
		
		  comboDepotDestination = new JComboBox();
		  comboDepotDestination.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		

		 		

	     		
	    		 if(e.getStateChange() == ItemEvent.SELECTED) {
	    			 
	    		
	    				  Depot depot=depotdao.findByCode(comboDepotDestination.getSelectedItem().toString());
	    				  if(depot!=null)
	    				  {
	    					
	    					  
	    					  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
	    					  int k=0;
	    					  comboMagasinDestination.removeAllItems();
	    					  comboMagasinDestination.addItem("");
	    				     	while (k<listMagasin.size())
	    				     	{
	    				     		Magasin magasin=listMagasin.get(k);
	    				     		
	    				     		
	    				     		comboMagasinDestination.addItem(magasin.getLibelle());
	    						     		
	    						     		mapMagasin.put(magasin.getLibelle(), magasin);
	    						     	
	    				     		k++;
	    				     		
	    				     	}
	    					 
	    				  }else
	    				  {
	    					  comboMagasinDestination.removeAllItems();
	    					  comboMagasinDestination.addItem("");
	    				  }
	    			 
	    			 
	    			 
	    			 
	    			 
	    			 
	    			 
	    			 
	    			 
	    		 }
	    		
	    		
	    		
	    		
	    		
	    		
	    	
		 		
		 		
		 		
		 		
		 	
		  	}
		  });
		comboDepotDestination.setBounds(116, 48, 176, 24);
		layeredPane.add(comboDepotDestination);
		
		JLabel lblMagasinDestination = new JLabel("Magasin Destination:");
		lblMagasinDestination.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMagasinDestination.setBounds(313, 50, 113, 24);
		layeredPane.add(lblMagasinDestination);
		
		  comboMagasinDestination = new JComboBox();
		comboMagasinDestination.setBounds(423, 50, 281, 24);
		layeredPane.add(comboMagasinDestination);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<DetailTransferStockMP> listedetailTransferStockMPtmp=new ArrayList<DetailTransferStockMP>();
				mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut Selectionner les Marchandises ‡ imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
				for(int t=0;t<listeTransfertStockMP.size();t++)
				{
					
					TransferStockMP transferStockMP=listeTransfertStockMP.get(t);
				  if(mapImprimer.containsKey(transferStockMP.getId()))
						 {
					  
					  listeDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
					  
					  if(transferStockMP.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) || transferStockMP.getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
					  {
						  
						  
						  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  	String date=dateFormat.format(transferStockMP.getDateTransfer());
								Map parameters = new HashMap();
								parameters.put("numTransfer", transferStockMP.getCodeTransfer());
								parameters.put("machineSource", listeDetailTransfertStockMP.get(0).getMagasinSouce().getLibelle());
								parameters.put("depSource", listeDetailTransfertStockMP.get(0).getMagasinSouce().getDepot().getLibelle());
								parameters.put("magasinDest", listeDetailTransfertStockMP.get(0).getMagasinEntrer().getLibelle());
								parameters.put("depDest", listeDetailTransfertStockMP.get(0).getMagasinEntrer().getDepot().getLibelle());
								parameters.put("dateTransfer",date );
								JasperUtils.imprimerBonSortieMPDeplace(listeDetailTransfertStockMP,parameters,transferStockMP.getCodeTransfer());
						  
						  
					  }else if(transferStockMP.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE) )
					  {
						  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  	String date=dateFormat.format(transferStockMP.getDateTransfer());
						   
						  	TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCodeStatut(listeDetailTransfertStockMP.get(0).getTransferStockMP().getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
							Magasin magasinSource=null;	
						  	
							if(transferStockMPTmp!=null)
							{
								
								listedetailTransferStockMPtmp=detailTransferMPDAO.findByTransferStockMP(transferStockMPTmp.getId());
								for(int d=0;d<listedetailTransferStockMPtmp.size();d++)
								{
									if(listedetailTransferStockMPtmp.get(d).getMagasinSouce()!=null)
									{
										magasinSource=listedetailTransferStockMPtmp.get(d).getMagasinSouce();
									}
								}
							}
						  	
						  	
							Map parameters = new HashMap();
							parameters.put("numTransfer", transferStockMP.getCodeTransfer());
							parameters.put("machineSource", magasinSource.getLibelle());
							parameters.put("depSource", magasinSource.getDepot().getLibelle());
							parameters.put("magasinDest", listeDetailTransfertStockMP.get(0).getMagasinDestination().getLibelle());
							parameters.put("depDest", listeDetailTransfertStockMP.get(0).getMagasinDestination().getDepot().getLibelle());
							parameters.put("dateTransfer", date);
							if(transferStockMP.getDesignation()!=null)
							{
								parameters.put("designation", transferStockMP.getDesignation());
							}else
							{
								parameters.put("designation", "");
							}
							
							JasperUtils.imprimerBonEntrerMP(listeDetailTransfertStockMP,parameters,transferStockMP.getCodeTransfer());
						  
						  
					  }
						  
					  
					  
					 
						 }
					 
					
					
					
					
					
				}
					
					
					
					
					
					
					
				}
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimer.setBounds(555, 758, 113, 23);
		add(btnImprimer);
		
	    if(utilisateur.getLogin().equals("admin"))
		  {
	    	
	    	combodepot.addItem("");
	    	 comboDepotDestination.addItem("");
	    	listDepot =depotdao.findAll();
	    	for(int i=0;i<listDepot.size();i++)
	    	{
	    		
	    		Depot depot=listDepot.get(i);
	    		combodepot.addItem(depot.getLibelle());
	    		mapDepot.put(depot.getLibelle(), depot);
	    		comboDepotDestination.addItem(depot.getLibelle());
	    		 
	    		
	    	}
	    	
	  
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  
				  combodepot.addItem(""); 
				  combodepot.addItem(depot.getLibelle());
		    		mapDepot.put(depot.getLibelle(), depot);
				  
		
				 
			  }
			  
			  
			  comboDepotDestination.addItem("");
		    	listDepot =depotdao.findAll();
		    	for(int i=0;i<listDepot.size();i++)
		    	{
		    		
		    		Depot depotTmp=listDepot.get(i);
		    		comboDepotDestination.addItem(depotTmp.getLibelle());
		    		mapDepot.put(depotTmp.getLibelle(), depotTmp);
		    		
		    		
		    	}  
			  
			  
			  
		  }
    
	}
	
	
	boolean remplirMapImprimer(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean imprimer=(boolean) table.getValueAt(j, 4);
			if(imprimer==true ){
				TransferStockMP tarnsferMp=listeTransfertStockMP.get(j);
				
				mapImprimer.put(tarnsferMp.getId(), tarnsferMp);
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}
	
void afficher_tableMP(List<TransferStockMP> listeTransfertMarchandise)
	{
	intialiserTableau();
	intialiserTableauDetailMP(); 

	
	
	
		  
			for(int i=0;i<listeTransfertMarchandise.size();i++)
			{
				
			
			TransferStockMP transferStockMP=listeTransfertMarchandise.get(i);
				
					 
						Object []ligne={transferStockMP.getCodeTransfer(),transferStockMP.getDateTransfer(),transferStockMP.getDepot().getLibelle(),transferStockMP.getStatut(), false};

						modeleMP.addRow(ligne);
					 
			
				
			}
		  
		  	
	}

void afficher_DtailtableMP(List<DetailTransferStockMP> listeDetailTransfertMarchandise)
{
intialiserTableauDetailMP();
List<DetailTransferStockMP> listedetailTransferStockMPtmp=new ArrayList<DetailTransferStockMP>();
 
		for(int i=0;i<listeDetailTransfertMarchandise.size();i++)
		{
	
		DetailTransferStockMP detailTransferStockMP=listeDetailTransfertMarchandise.get(i);
				
				
if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) || detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
{
	Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom() ,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),detailTransferStockMP.getTransferStockMP().getDepot().getLibelle(), detailTransferStockMP.getMagasinSouce().getLibelle(),detailTransferStockMP.getTransferStockMP().getDepotDestination().getLibelle(),detailTransferStockMP.getMagasinEntrer().getLibelle()};
	modeleDetailMP.addRow(ligne);
}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
{
	
	TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
	Magasin magasinSource=null;
	
	if(transferStockMP!=null)
	{
		
		listedetailTransferStockMPtmp=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
		for(int d=0;d<listedetailTransferStockMPtmp.size();d++)
		{
			if(listedetailTransferStockMPtmp.get(d).getMagasinSouce()!=null)
			{
				magasinSource=listedetailTransferStockMPtmp.get(d).getMagasinSouce();
			}
		}
	}
	
	if(magasinSource!=null)
	{
		Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom() ,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),detailTransferStockMP.getTransferStockMP().getDepot().getLibelle(),detailTransferStockMP.getMagasinDestination().getLibelle()};
		modeleDetailMP.addRow(ligne);
	}

}
				
				
				
				
				
		
			
		
		
			
	
			
			
		}
	  
	  	
}




	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Transfert","Date Transfert", "Depot","Etat","Imprimer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,true 
		     	};
		    	Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		    
}


void intialiserTableauDetailMP(){
	
	 modeleDetailMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre","QuantitÈ ","Depot Source", "Magasin Source","Depot Destination", "Magasin Destination"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   tabledetailtransfert.setModel(modeleDetailMP); 
		   tabledetailtransfert.getColumnModel().getColumn(0).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(1).setPreferredWidth(260);
		   tabledetailtransfert.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(3).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(4).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(5).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(6).setPreferredWidth(160);
		    
}
}

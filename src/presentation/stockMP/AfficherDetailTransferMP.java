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


public class AfficherDetailTransferMP extends JLayeredPane implements Constantes{
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
	private JTable tabledetailtransfert;
	JComboBox combostatut = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherDetailTransferMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 837);
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
				  		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  		     			MatierePremier matierePremier=MapCodeMatierPremiere.get(table.getValueAt(table.getSelectedRow(), 0));
				  		     			
				  		     			listeObjectDetailSituationTransfert=detailTransferMPDAO.listeDetailSituationTransfert(magasin, dateChooserdebut.getDate(), dateChooserfin.getDate(), matierePremier);
				  		     			
				  		     			afficher_DtailtableMP(listeObjectDetailSituationTransfert);
				  		     			
				  		     		}else
				  		     		{
				  		     			afficher_DtailtableMP(listeObjectDetailSituationTransfert);
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
				  		     	scrollPane.setBounds(9, 130, 1231, 303);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Transferts ");
				  		     	titledSeparator.setBounds(9, 101, 1231, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 0, 1230, 90);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1078, 54, 113, 23);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				if(dateChooserdebut.getDate()==null || dateChooserfin.getDate() == null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez entre la date debut et fin SVP");
					return;
				}else if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP");
					return;
				}else
				{
					
					
				
					
					
				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
			
				
				
					
					listeObjectSituationTransfert=detailTransferMPDAO.listeSituationTransfert(magasin,dateChooserdebut.getDate(),dateChooserfin.getDate(),matierePremier);
					
				
					afficher_tableMP(listeObjectSituationTransfert);
					
					
					
					
					
					
					
				}
			
			
			
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
	dateChooserdebut.setBounds(112, 11, 163, 26);
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
	
	JLabel lblDepot = new JLabel("Depot  :");
	lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblDepot.setBounds(553, 12, 62, 24);
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
	combomagasin.setBounds(875, 14, 176, 24);
	layeredPane.add(combomagasin);
	
	JLabel lblMagasin = new JLabel("Magasin  :");
	lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblMagasin.setBounds(807, 12, 96, 24);
	layeredPane.add(lblMagasin);
	
	JLabel label_2 = new JLabel("Code MP:");
	label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
	label_2.setBounds(10, 51, 67, 24);
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
	txtCodeMP.setBounds(70, 51, 118, 26);
	layeredPane.add(txtCodeMP);
	
	JLabel label_3 = new JLabel("MP :");
	label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	label_3.setBounds(198, 53, 37, 24);
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
	comboMP.setBounds(232, 53, 232, 24);
	layeredPane.add(comboMP);
	
	
	
    if(utilisateur.getLogin().equals("admin"))
	  {
    	
    	combodepot.addItem("");
    	listDepot =depotdao.findAll();
    	for(int i=0;i<listDepot.size();i++)
    	{
    		
    		Depot depot=listDepot.get(i);
    		combodepot.addItem(depot.getLibelle());
    		mapDepot.put(depot.getLibelle(), depot);
    		
    		
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
	  }
    
    
    
	
		txtCodeMP.setText("");
	comboMP.removeAllItems();
		comboMP.addItem("");
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStatut.setBounds(507, 51, 96, 24);
		layeredPane.add(lblStatut);
		
		 combostatut = new JComboBox();
		combostatut.setBounds(575, 53, 197, 24);
		layeredPane.add(combostatut);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Detail Transferts ");
		titledSeparator_1.setBounds(9, 452, 1231, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 506, 1231, 303);
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
    
    
	}
	
void afficher_tableMP(List<Object[]> listeObjectSituationTransfert)
	{
	intialiserTableau();
	intialiserTableauDetailMP(); 

	
	List<DetailTransferStockMP> listedetailTransferStockMPtmp=new ArrayList<DetailTransferStockMP>();
	
		  
			for(int i=0;i<listeObjectSituationTransfert.size();i++)
			{
				
				Object[] object=listeObjectSituationTransfert.get(i);
				
				
				MatierePremier mp=(MatierePremier)object[0];
				
			if(mp!=null)	
			{
			
				if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
				{
					/*
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
					*/
					
					if( new BigDecimal(object[1].toString()).compareTo(BigDecimal.ZERO)!=0 )
					{
						Object []ligne={mp.getCode() , mp.getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[1].toString())),0,0};

						modeleMP.addRow(ligne);
					}
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
				{
					if(  new BigDecimal(object[2].toString()).compareTo(BigDecimal.ZERO)!=0)
					{
						Object []ligne={mp.getCode() , mp.getNom(),0,NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[2].toString())),0};

						modeleMP.addRow(ligne);
					}
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
				{
					
					
					
					
					if( ( new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString()))).compareTo(BigDecimal.ZERO)!=0)
					{
						Object []ligne={mp.getCode() , mp.getNom(),0,0,NumberFormat.getNumberInstance(Locale.FRANCE).format( new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString())))};

						modeleMP.addRow(ligne);
						
					}
				
				}else
				{
					if( new BigDecimal(object[1].toString()).compareTo(BigDecimal.ZERO)!=0 || new BigDecimal(object[2].toString()).compareTo(BigDecimal.ZERO)!=0 ||  new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString())).compareTo(BigDecimal.ZERO)!=0)
					{
						Object []ligne={mp.getCode() , mp.getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[1].toString())),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[2].toString())),NumberFormat.getNumberInstance(Locale.FRANCE).format( new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString())))};

						modeleMP.addRow(ligne);
						
					}
			
				}
				
				
			}
				
				
			}
		  
		  	
	}

void afficher_DtailtableMP(List<Object[]> listeObjectDetailSituationTransfert)
{
intialiserTableauDetailMP();
	 
	  Integer idmagasinsource=0;
	  Integer idmagasindestination=0;
		for(int i=0;i<listeObjectDetailSituationTransfert.size();i++)
		{
			idmagasinsource=0;
			idmagasindestination=0;
			
			Object[] object=listeObjectDetailSituationTransfert.get(i);
			if(object[5]!=null)
			{
				
				idmagasinsource=(Integer)object[5];
			}
			
			if(object[6]!=null)
			{
				
				idmagasindestination=(Integer)object[6];
			}
			
			
			
			MatierePremier mp=(MatierePremier)object[0];
			String statut="";
			
			if(object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
			{
				statut=Constantes.ETAT_TRANSFER_STOCK_SORTIE;
				
				
			}else if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
			{
				
				statut=Constantes.ETAT_TRANSFER_STOCK_ENTRE;
				
			}
			
			
			DetailTransferStockMP detailTransferStockMP=detailTransferMPDAO.findDetailTransferMPByCodeByMP(object[2].toString(), mp,statut);
			
			
			
			
		if(mp!=null)	
		{
		
			if(idmagasinsource!=0 && idmagasindestination!=0 )
			{
				
				Magasin magasinSource=depotdao.magasinById(idmagasinsource);
				Magasin magasinDestination=depotdao.magasinById(idmagasindestination);
				
				if(object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
				{
					if(detailTransferStockMP!=null)
					{
						magasinSource=detailTransferStockMP.getMagasinSouce();
					}
					
					
				}else if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
				{
					
					if(detailTransferStockMP!=null)
					{
						magasinDestination=detailTransferStockMP.getMagasinDestination();
					}
					
				}
				
				if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};

						modeleDetailMP.addRow(ligne);
					}
					
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};

						modeleDetailMP.addRow(ligne);
					}
					
					
					
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
				{
					if (object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT) || object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT_VALIDER))
					{
						
						
						
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};

						modeleDetailMP.addRow(ligne);
					}
					
				
				}else
				{
					
					if (object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT) || object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT_VALIDER))
					{
						DetailTransferStockMP detailTransferStockMPTmp=detailTransferMPDAO.findDetailTransferMPByCodeByMP(object[2].toString(), mp,Constantes.ETAT_TRANSFER_STOCK_SORTIE);
						
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),detailTransferStockMPTmp.getMagasinEntrer().getDepot().getLibelle(), detailTransferStockMPTmp.getMagasinEntrer().getLibelle()};

						modeleDetailMP.addRow(ligne);
						
						
					}else
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};

						modeleDetailMP.addRow(ligne);
					}
					
					
			
					
					
					
				}
				
				
				
			}else if(idmagasinsource==0 && idmagasindestination!=0 )
			{
				
				
				Magasin magasinDestination=depotdao.magasinById(idmagasindestination);
				
				if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),"","",magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};
						modeleDetailMP.addRow(ligne);
						
						
					}
				}else if (combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),"","",magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};
						modeleDetailMP.addRow(ligne);
						
						
					}
				}else if (combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
				{
					if (object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT) || object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT_VALIDER))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),"","",magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};
						modeleDetailMP.addRow(ligne);
						
						
					}
				}else
				{

					Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),"","",magasinDestination.getDepot().getLibelle(), magasinDestination.getLibelle()};
					modeleDetailMP.addRow(ligne);
					
					
				
				}
				
				
				
			}else if(idmagasinsource!=0 && idmagasindestination==0 )
			{
				
				Magasin magasinSource=depotdao.magasinById(idmagasinsource);
				if (combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),"",""};
						modeleDetailMP.addRow(ligne);
					}
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
				{
					if (object[3].toString().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),"",""};
						modeleDetailMP.addRow(ligne);
					}
					
				}else if(combostatut.getSelectedItem().equals(Constantes.ETAT_SORTIE_ENATTENT))
				{
					if (object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT) || object[3].toString().equals(Constantes.ETAT_SORTIE_ENATTENT_VALIDER))
					{
						Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),"",""};
						modeleDetailMP.addRow(ligne);
					}
					
				}else
				{

					Object []ligne={mp.getCode() , mp.getNom(),object[1].toString(),object[2].toString(),object[3].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[4].toString())),magasinSource.getDepot().getLibelle(), magasinSource.getLibelle(),"",""};
					modeleDetailMP.addRow(ligne);
				
				}
				
				
			}
			
		
		
			
		}
			
			
		}
	  
	  	
}




	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre", "QuantitÈ Entrer","QuantitÈ Sortie","QuantitÈ En Attent"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
		   table.getColumnModel().getColumn(4).setPreferredWidth(160);
}


void intialiserTableauDetailMP(){
	
	 modeleDetailMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre","Date Transfert","Code Transfert", "Statut","QuantitÈ ","Depot Source", "Magasin Source","Depot Destination", "Magasin Destination"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false
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
		   tabledetailtransfert.getColumnModel().getColumn(7).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(8).setPreferredWidth(160);
		   tabledetailtransfert.getColumnModel().getColumn(9).setPreferredWidth(160);
}
}

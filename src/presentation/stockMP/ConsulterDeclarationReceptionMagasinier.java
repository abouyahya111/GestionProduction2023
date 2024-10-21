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


public class ConsulterDeclarationReceptionMagasinier extends JLayeredPane implements Constantes{
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
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	JComboBox comboMP = new JComboBox();
	private MatierePremiereDAO matierePremiereDAO;
	private List<MatierePremier> listeMatierePremiere=new ArrayList<MatierePremier>();
	private List <Object[]> listeObjectDeclarationReceptionMagasinierGroupByDateByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP=new ArrayList<Object[]>();
	private JTable tabledetailtransfert;
	JComboBox combostatut = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterDeclarationReceptionMagasinier() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 878);
        try{
        	
        	
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotdao= new DepotDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	 
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				  		     		
				  		     		if(table.getSelectedRow()!=-1)
				  		     		{
				  		     			try {
				  		     				
				  		     		 
				  		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  		     			MatierePremier matierePremier=matierePremiereDAO.findByCode(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		     		 
				  		     			Date date= simpleDateFormat.parse(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		     			listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP=detailTransferMPDAO.listeDetailDeclarationReceptionMagasinier(magasin, date, matierePremier);
				  		     			
				  		     			afficher_DtailtableMP(listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP);
				  		     		} catch (Exception e2) {
				  						JOptionPane.showMessageDialog(null, e2.getMessage());
				  						return;
				  					}
				  					
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "veuillez séléctionner un Enregistrement");
				  		     			return;
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
				  		     	scrollPane.setBounds(10, 104, 1231, 303);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Transferts ");
				  		     	titledSeparator.setBounds(10, 75, 1231, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 0, 1230, 64);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1078, 14, 113, 23);
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
					
					
				 
			
				
				
					
					listeObjectDeclarationReceptionMagasinierGroupByDateByMP=detailTransferMPDAO.listeDeclarationReceptionMagasinier(magasin,dateChooserdebut.getDate(),dateChooserfin.getDate());
					
				
					afficher_tableMP(listeObjectDeclarationReceptionMagasinierGroupByDateByMP);
					
					
					
					
					
					
					
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
					"BL Num","Num Reception","Code MP","Matière Première","Quantite"			}
		));
		tabledetailtransfert.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tabledetailtransfert);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getRowCount()!=0)
				{
					
					Map parameters = new HashMap();
					String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					
					String dateau=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
					
					parameters.put("date", "Du : "+dateDu +" Au : "+dateau);
					parameters.put("depot", combodepot.getSelectedItem().toString());
				
					JasperUtils.imprimerDeclarationReceptionMagasinier (parameters,table.getModel()); 
					
				}
				
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimer.setBounds(547, 418, 113, 23);
		add(btnImprimer);
		
		JButton button = new JButton("Imprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat simpleDateFormatTmp = new SimpleDateFormat("dd/MM/yyyy");
				
				if(tabledetailtransfert.getRowCount()!=0)
				{
					try {
					Date date= simpleDateFormat.parse(table.getValueAt(table.getSelectedRow(), 0).toString());
					
					Map parameters = new HashMap();
					  
					parameters.put("date", "Date Déclaration : "+simpleDateFormatTmp.format(date).toString());
					parameters.put("depot", combodepot.getSelectedItem().toString());
				
					JasperUtils.imprimerDetailDeclarationReceptionMagasinier (parameters,tabledetailtransfert.getModel()); 
				} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
						return;
					}
				}
				 
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(547, 823, 113, 23);
		add(button);

		 
    
    
	}
	
void afficher_tableMP(List<Object[]> listeObjectDeclarationReceptionMagasinierGroupByDateByMP)
	{
	intialiserTableau();
	intialiserTableauDetailMP(); 

	
	
	
		  
			for(int i=0;i<listeObjectDeclarationReceptionMagasinierGroupByDateByMP.size();i++)
			{
				
				Object[] object=listeObjectDeclarationReceptionMagasinierGroupByDateByMP.get(i);
				
				
				MatierePremier mp=matierePremiereDAO.findById((int) object[1]) ;
				Date date=(Date)object[0];
			if(mp!=null)	
			{
			
				

						Object []ligne={date,mp.getCode() , mp.getNom(),new BigDecimal(object[2].toString())};

						modeleMP.addRow(ligne);
					
			
				
				
			}
				
				
			}
		  
		  	
	}

void afficher_DtailtableMP(List<Object[]> listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP)
{
intialiserTableauDetailMP();
	 
	  
		for(int i=0;i<listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP.size();i++)
		{
			 
			
			Object[] object=listeObjectDetailDeclarationReceptionMagasinierGroupByDateByMP.get(i);
		 
			
			
			
			MatierePremier mp=matierePremiereDAO.findById((int)object[2]) ;
			 
			
			
			
		if(mp!=null)	
		{
		
	

					Object []ligne={object[1].toString(),object[0].toString(), mp.getCode() , mp.getNom(),new BigDecimal(object[3].toString())};
					modeleDetailMP.addRow(ligne);
				
			
				
				
			
			
		
		
			
		}
			
			
		}
	  
	  	
}




	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date déclaration","Code Matière Première","Matière Première", "Quantité Total"
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
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
		  
}


void intialiserTableauDetailMP(){
	
	 modeleDetailMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"BL Num","Num Reception","Code MP","Matière Première","Quantite"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false 
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
		  
}
}

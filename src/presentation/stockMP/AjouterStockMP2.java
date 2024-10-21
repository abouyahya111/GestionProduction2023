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
import java.math.RoundingMode;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;
 

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
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

import com.toedter.calendar.JDateChooser;

import config.Connect;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JCheckBox;


public class AjouterStockMP2 extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleTransferMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	JComboBox comboMP = new JComboBox();
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();;
	
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< String, BigDecimal> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	List<TransferStockMP> listTransfertStockMP =new ArrayList<TransferStockMP>();
	private Utilisateur utilisateur;

	////////////////////////////////////////////////////////////////////// Importer Reception Excel  //////////////////////////////////////////////////////////////////////////////////////
	   
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell CellDate;
    private static XSSFCell CellNumBL;
    private static XSSFCell CellNumReception;
    private static XSSFCell CellCodeMP;
    private static XSSFCell CellQuantite;
    private static XSSFCell CellQuantiteExact;
	private JFrame mainFrame;
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	JButton btnAjouter ;
	private MatierePremiereDAO matierePremiereDAO;
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	List<DetailTransferStockMP> listDetailTransferStockMPTmp= new ArrayList<DetailTransferStockMP>();
	private JTable tableTransfertMP;
	private JTextField txtcode;
	JDateChooser dateDebut = new JDateChooser();
	JDateChooser dateFin = new JDateChooser();
	JLabel labelCodeReception = new JLabel("Code R\u00E9ception :");
	JCheckBox checkImporterBonReception = new JCheckBox("Importer Bon Reception");
	List<String> listNumBLCommande= new ArrayList<String>();
	private List<DetailTransferStockMP> listDetailTransfertMp =new ArrayList<DetailTransferStockMP>();
	private TransferStockMP transferStock ;
	private Map< String, Date> mapDateBL= new HashMap<>();
	List<Integer> listIDTransfert= new ArrayList<Integer>();
	private Map< String, Date> mapDateNumReception= new HashMap<>();
	List<DetailTransferStockMP> ListedetailTransferStockMPTmp=new ArrayList<DetailTransferStockMP>(); 
//////////////////////////////////////////////////////////////////////////////////Connecter Au Base Gestion Commande Final    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
private ResultSet rset;
private Statement stx;

private Connection con;	  
private JTextField txtlien;
 
List<TransferStockMP> listTransfertValider= new ArrayList<TransferStockMP>();
List<DetailTransferStockMP> listDetailTransferStockMPValider= new ArrayList<DetailTransferStockMP>();


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterStockMP2() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1419, 1040);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	listMP=matierePremiereDAO.findAll();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	 
//////////////////////////////////////////////////////////////////////////////////Connecter Au Base  Gestion Commande Final Serveur     ////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*	  
Connect.connecToGestionCommande();
con=Connect.getConnexionCommande();
stx=con.createStatement();
*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
	    		Depot depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	    		 mapDepot.put(depot.getLibelle(), depot);
	    		comboDepot.addItem(depot.getLibelle());
	    }else {
	    	
	  	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot);
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	    }
		btnAjouter = new JButton("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
             imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
             imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
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
				  		     intialiserTabeleau();
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 594, 1035, 274);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));


				  		     	
				  		     
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	btnAjouter.setVisible(true);
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	 	List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
					  		     	 	{

					  		     	  	   	Depot depot =mapDepot.get(comboDepot.getSelectedItem());
							  		       
					  		     	   listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		     	  listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
					  		     	   
							  		      if(listMagasin!=null && listMagasin.size()> 0){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasin.addItem(magasin.getLibelle());
								  		      			mapMagasin.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }else {
							  		    	//JOptionPane.showMessageDialog(null, "Ce dépot ne contient aucun magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
							  		    	btnAjouter.setVisible(false);
							  		      }
							  		      
							  		      
							  		      
							  		    if(listMagasinDechetMP!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasinDechetMP.size())
								  		      		{	
								  		      			Magasin magasin=listMagasinDechetMP.get(j);
								  		      		comboMagasin.addItem(magasin.getLibelle());
							  		      			mapMagasin.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
							  		      
							  		      
					  		     	 		
					  		     	 		
					  		     	 	}
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 565, 1035, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(21, 22, 1008, 116);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 27, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(51, 27, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(225, 26, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(281, 27, 192, 24);
				  		     	 layeredPane.add(comboMagasin);
				  		     	 
				  		     	 JLabel lblDateRception = new JLabel("Date Debut :");
				  		     	 lblDateRception.setBounds(510, 26, 87, 26);
				  		     	 layeredPane.add(lblDateRception);
				  		   	comboMagasin.setSelectedIndex(-1);
				  		   	comboDepot.setSelectedIndex(-1);
				  		   	
				  		   	 dateDebut = new JDateChooser();
				  		   	dateDebut.setBounds(582, 25, 155, 26);
				  		   	layeredPane.add(dateDebut);
				  		   	dateDebut.setLocale(Locale.FRANCE);
				  		   	dateDebut.setDateFormatString("dd/MM/yyyy");
				  		   	
				  		   	 dateFin = new JDateChooser();
				  		   	dateFin.setLocale(Locale.FRANCE);
				  		   	dateFin.setDateFormatString("dd/MM/yyyy");
				  		   	dateFin.setBounds(834, 27, 155, 26);
				  		   	layeredPane.add(dateFin);
				  		   	
				  		   	JLabel lblDateFin = new JLabel("Date Fin:");
				  		   	lblDateFin.setBounds(762, 28, 87, 26);
				  		   	layeredPane.add(lblDateFin);
				  		   	
				  		   	  checkImporterBonReception = new JCheckBox("Importer Bon Reception");
				  		   	  checkImporterBonReception.addActionListener(new ActionListener() {
				  		   	  	public void actionPerformed(ActionEvent arg0) {
				  		   	  		if(checkImporterBonReception.isSelected()==false)
				  		   	  		{
				  		   	  			listNumBLCommande.clear();
				  		   	  			
				  		   	  		}
				  		   	  		
				  		   	  		
				  		   	  	}
				  		   	  });
				  		   	checkImporterBonReception.setBounds(20, 75, 155, 23);
				  		   	layeredPane.add(checkImporterBonReception);
				  		  checkImporterBonReception.setVisible(true);
		JButton btnValiderStock = new JButton("Valider Stock");
		btnValiderStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapDateNumReception.clear();
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				if(!remplirMapreception())	{
					JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} else if(depot==null)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le depot avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le magasin avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			 
				else
				
				
				{
					
					TransferStockMP transferStockMP=null;
					
					if(checkImporterBonReception.isSelected()==true)
					{
						transferStockMP=ListedetailTransferStockMPTmp.get(tableTransfertMP.getSelectedRow()).getTransferStockMP();
					}else
					{
						transferStockMP=listTransfertStockMP.get(tableTransfertMP.getSelectedRow());
					}

				  
				
				if(transferStockMP.getImporterViaGestionCommande()!=null)
				{
					
					if(transferStockMP.getImporterViaGestionCommande().equals(CODE_IMPORTER))
					{
						
						ValiderReceptionImporterViaExcel();
						
					}else
					{
						ValiderReceptionNomImporter();
						
					}
					
					
				}else
				{
					ValiderReceptionNomImporter();
					
				}
				}
			
			}
		});
		btnValiderStock.setBounds(264, 913, 100, 23);
		add(btnValiderStock);
		
		JButton btnInitialiser = new JButton("Initialiser Tous");
		btnInitialiser.setIcon(imgInit);
		btnInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiserTous();
				
			}
		});
		
		btnAjouter.setIcon(imgRechercher);
		btnInitialiser.setBounds(385, 913, 127, 23);
		add(btnInitialiser);
		btnAjouter.setBounds(1146, 47, 65, 36);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				ChargerLesReceptionsMagasinier();
				
				
				}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(listDetailTransferStockMPTmp.size()!=0)
				{
					
				Map parameters = new HashMap();
				parameters.put("numTransfer", listDetailTransferStockMPTmp.get(0).getTransferStockMP().getCodeTransfer());   
				parameters.put("numBL", listDetailTransferStockMPTmp.get(0).getNumBLReception()); 
				parameters.put("magasinDest", comboMagasin.getSelectedItem());
				parameters.put("depDest", comboDepot.getSelectedItem());
				parameters.put("dateTransfer", listDetailTransferStockMPTmp.get(0).getTransferStockMP().getDateTransfer());
				JasperUtils.imprimerBonReceptionMP(listDetailTransferStockMPTmp,parameters);
				
				ChargerLesReceptionsMagasinier();
				
				listDetailTransferStockMPTmp.clear();
				
				
				}
							}
		});
		btnImprimer.setBounds(550, 915, 117, 23);
		btnImprimer.setIcon(imgImprimer);
		add(btnImprimer);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Receptions En Attent");
		titledSeparator_1.setBounds(10, 160, 1035, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 201, 1035, 262);
		add(scrollPane_1);
		
		tableTransfertMP = new JTable();
		tableTransfertMP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tableTransfertMP.getSelectedRow()!=-1)
				{
					
				
				
/*
 * 
 * Traitement Pour Les reception Importer Via Base Gestion Commande finale Serveur


if(checkImporterBonReception.isSelected()==true)
{
	 
		txtcode.setText("");
		txtcode.setEnabled(false);
		
		
		afficher_tableMPReceptionImporter(tableTransfertMP.getValueAt(tableTransfertMP.getSelectedRow(), 1).toString());
		
	 
	
	
}else
{
	
	TransferStockMP transferStockMP=	listTransfertStockMP.get(tableTransfertMP.getSelectedRow());
	listDetailTransferStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
	if(transferStockMP.getCodeTransfer().contains(CODE_RECEPTION_INVENTAIRE))
	{
		
		txtcode.setText(transferStockMP.getCodeTransfer());
		dateTransfereChooser.setDate(transferStockMP.getDateTransfer());
		
		txtcode.setEnabled(false);
		dateTransfereChooser.setEnabled(false);
		
	}else
	{
		txtcode.setText("");
		dateTransfereChooser.setDate(null);
		
		txtcode.setEnabled(true);
		dateTransfereChooser.setEnabled(true);
		
	}
	 
	afficher_tableMP(listDetailTransferStockMP);
}

	*/		
					
//////////////////////////////////////////////////////////  Traitement Les receptions Importer Via Excel  //////////////////////////////////////////////////////////////////////////
					
					  
							
							
							
					if(checkImporterBonReception.isSelected()==true)
					{
						listDetailTransferStockMP.clear();
						
						DetailTransferStockMP detailTransferStockMP=ListedetailTransferStockMPTmp.get(tableTransfertMP.getSelectedRow());
					
						
					   List<DetailTransferStockMP>	listDetailTransferStockMPTmp=detailTransferMPDAO.findByNumBlReceptionEnAttente(detailTransferStockMP.getNumBLReception(), Constantes.ETAT_RECEPTION_ENATTENT);
					     
					   for(int j=0;j<listDetailTransferStockMPTmp.size();j++)
					   {
						  
							TransferStockMP transferStockMPAjout=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(listDetailTransferStockMPTmp.get(j).getTransferStockMP().getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_AJOUT, listDetailTransferStockMPTmp.get(j).getNumBLReception(), listDetailTransferStockMPTmp.get(j).getMatierePremier()) ;

						   if(transferStockMPAjout==null)
						   {
							   listDetailTransferStockMP.add(listDetailTransferStockMPTmp.get(j));
						   }
						   
					   }
					   
					   
						
						afficher_tableMPReceptionImporterViaExcel(listDetailTransferStockMP);
						
						
					}else
					{
						TransferStockMP transferStockMP=	listTransfertStockMP.get(tableTransfertMP.getSelectedRow());
						

						listDetailTransferStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
						if(transferStockMP.getCodeTransfer().contains(CODE_RECEPTION_INVENTAIRE))
						{
							
							txtcode.setText(transferStockMP.getCodeTransfer());
							dateTransfereChooser.setDate(transferStockMP.getDateTransfer());
							
							txtcode.setEnabled(false);
							dateTransfereChooser.setEnabled(false);
							
						}else
						{
							txtcode.setText("");
							dateTransfereChooser.setDate(null);
							
							txtcode.setEnabled(true);
							dateTransfereChooser.setEnabled(true);
							
						}
						 
						afficher_tableMP(listDetailTransferStockMP);
						
					
					}
						 
						
						
					 
					 
 		 	
					
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					
					
					
					
				}
				
				
				
				
				
				
				
			}
		});
		tableTransfertMP.setFillsViewportHeight(true);
		tableTransfertMP.setSurrendersFocusOnKeystroke(true);
		scrollPane_1.setViewportView(tableTransfertMP);
		tableTransfertMP.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date Reception", "Code Reception"
			}
		));
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 474, 1035, 79);
		add(layeredPane_1);
		
		txtcode = new JTextField();
		txtcode.setColumns(10);
		txtcode.setBackground(Color.WHITE);
		txtcode.setBounds(120, 24, 189, 24);
		layeredPane_1.add(txtcode);
		
		  labelCodeReception = new JLabel("Num  BL:");
		labelCodeReception.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCodeReception.setBounds(21, 25, 102, 24);
		layeredPane_1.add(labelCodeReception);
		
		JLabel label_3 = new JLabel("Date R\u00E9ception :");
		label_3.setBounds(315, 24, 87, 26);
		layeredPane_1.add(label_3);
		
		 dateTransfereChooser = new JDateChooser();
		 dateTransfereChooser.setBounds(412, 24, 155, 26);
		 layeredPane_1.add(dateTransfereChooser);
		 dateTransfereChooser.setLocale(Locale.FRANCE);
		 dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
		 
		 JButton btnImporterReceptionExcel = new JButton("Importer Reception Excel");
		 btnImporterReceptionExcel.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		DataFormatter dataFormatter = new DataFormatter();
		 		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  				if(!txtlien.getText().equals(""))
  				{
  					
  					 File fileName = new File(txtlien.getText());
		  				
  					Magasin magasinDestination=mapMagasin.get(comboMagasin.getSelectedItem());
				        
				        Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
				        
				        if(magasinDestination==null)
				        {
				        	JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
				        	return;
				        }
				        
				        
		  				try {
		  					 
		  				       FileInputStream ExcelFile = new FileInputStream(fileName);
		  				      
		  				        
		  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
		  				       
		  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
		  				      
		  				     
		  				      
int t=0;							  				      
		  				      
int id=0;		

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date dat = new Date();	
String date=dateFormat.format(dat);

////////////////////////////////////////////////////////////////////////////////////////////////Ajouter Code Commerciale Client  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
boolean existe=false;
		  				   
		  							for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
		  							{
		  								
		  							try {
		  								
		  								 
		  						
		  								
		  								
		  				///////////////////////////////////////////////////////////////// Comparer les Articles et verifier les cellules si il sont vide //////////////////////////////////////////////////////////////////
		  								
		  								   Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
		  								


CellDate=ExcelWSheet.getRow(i).getCell(0);
CellNumBL=ExcelWSheet.getRow(i).getCell(1);
CellNumReception=ExcelWSheet.getRow(i).getCell(2);
CellCodeMP=ExcelWSheet.getRow(i).getCell(3);
CellQuantiteExact=ExcelWSheet.getRow(i).getCell(7);
 MatierePremier matierePremier=matierePremiereDAO.findByCode(CellCodeMP.getStringCellValue());
String NumBL = dataFormatter.formatCellValue(CellNumBL);
String datereception=dataFormatter.formatCellValue(CellDate);

if(NumBL!=null && NumBL.isEmpty()==false && !NumBL.equals(""))
{
	

TransferStockMP transferStockMPEnAttenteTmp=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionUnique(CellNumReception.getStringCellValue(),  Constantes.ETAT_RECEPTION_ENATTENT, NumBL);

if(transferStockMPEnAttenteTmp!=null)
{
	

	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();


	detailTransferStockMP.setMagasinSouce(magasinSource);
	detailTransferStockMP.setMagasinDestination(magasinDestination);

	detailTransferStockMP.setMatierePremier(matierePremier);
	detailTransferStockMP.setQuantite(new BigDecimal(CellQuantiteExact.getNumericCellValue()) );
if(matierePremier.getPrix()!=null)
{
	detailTransferStockMP.setPrixUnitaire(matierePremier.getPrix());
}else
{
	detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
}
detailTransferStockMP.setNumBLReception(NumBL);
detailTransferStockMP.setTransferStockMP(transferStockMPEnAttenteTmp);
detailTransferMPDAO.add(detailTransferStockMP);

}else
{
	TransferStockMP transferStock=new TransferStockMP();
	transferStock.setStatut(Constantes.ETAT_RECEPTION_ENATTENT);
	transferStock.setImporterViaGestionCommande(CODE_IMPORTER);
	transferStock.setDepot(magasinDestination.getDepot());
	transferStock.setDate(new Date());
 
		transferStock.setDateTransfer(simpleDateFormat.parse(datereception));
 
	transferStock.setCodeTransfer(CellNumReception.getStringCellValue());
	transferStock.setCreerPar(utilisateur);
	//transferStockMPValider.setListDetailTransferMP(listDetailTransferStockMPTmp);
    transferStockMPDAO.add(transferStock);
    DetailTransferStockMP detailTransferStockMPTmp=new DetailTransferStockMP();
detailTransferStockMPTmp.setMagasinSouce(magasinSource);
detailTransferStockMPTmp.setMagasinDestination(magasinDestination);

detailTransferStockMPTmp.setMatierePremier(matierePremier);
detailTransferStockMPTmp.setQuantite(new BigDecimal(CellQuantiteExact.getNumericCellValue()) );
if(matierePremier.getPrix()!=null)
{
	detailTransferStockMPTmp.setPrixUnitaire(matierePremier.getPrix());
}else
{
	detailTransferStockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
}
detailTransferStockMPTmp.setNumBLReception(NumBL);
detailTransferStockMPTmp.setTransferStockMP(transferStock);
 
detailTransferMPDAO.add(detailTransferStockMPTmp);
   	
}
	
}

 

t=t+1;
			  							
		  			} catch (Exception e) {

		  				 JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
		  				 return;
}

		  							
		  							
		  							
		  							
		  							}
		  							
		  						
			  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
		  					
		  					
		  					JOptionPane.showMessageDialog(null, "OK");
		  					
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, e.getMessage()); 
						}
		  				
  					
  				}
		 		
		 		
		 		
		 	}
		 });
		 btnImporterReceptionExcel.setBounds(733, 929, 188, 23);
		 add(btnImporterReceptionExcel);
		 final JFileChooser  fileDialog = new JFileChooser();
		 JButton button = new JButton("Ouvrir");
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

  				
 				 int returnVal = fileDialog.showOpenDialog(mainFrame);
 	            
 				java.io.File file = fileDialog.getSelectedFile();     
 				 
 				txtlien.setText(file.getAbsolutePath());
 				
 				
 				
 			
		 	}
		 });
		 button.setBounds(1289, 925, 89, 30);
		 add(button);
		 
		 txtlien = new JTextField();
		 txtlien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		 txtlien.setEditable(false);
		 txtlien.setColumns(10);
		 txtlien.setBounds(931, 925, 348, 36);
		 add(txtlien);
		
		for(int i=0;i<listMP.size();i++)
		{
			MatierePremier mp=listMP.get(i);
			comboMP.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
		}
		
				  		     
				  		 
	}
	
void afficher_tableMP(List<DetailTransferStockMP> listDetailTransfertStockMP)
	{
	
	
	
	intialiserTabeleau();
	
	mapCodeMP.clear();
		  int i=0;
			while(i<listDetailTransfertStockMP.size())
			{	
				
				DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(i);
				
				mapCodeMP.put(detailTransferStockMP.getMatierePremier().getCode(), detailTransferStockMP.getMatierePremier());
				if(detailTransferStockMP.getFournisseur()!=null)
				{
					
					if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().contains(CODE_RECEPTION_INVENTAIRE))
					{
						Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(),detailTransferStockMP.getFournisseur().getCodeFournisseur(), NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),detailTransferStockMP.getQuantite()};

						modeleMP.addRow(ligne);
						
					}else
					{
						Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(),detailTransferStockMP.getFournisseur().getCodeFournisseur(), NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),BigDecimal.ZERO};

						modeleMP.addRow(ligne);
					}
					
				
				}else
				{
					
					if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().contains(CODE_RECEPTION_INVENTAIRE))
					{
						Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(),"", NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),detailTransferStockMP.getQuantite()};

						modeleMP.addRow(ligne);
					}else
					{
						
						Object []ligne={detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(),"", NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),BigDecimal.ZERO};

						modeleMP.addRow(ligne);
					}
				
				}
				
				
				i++;
			}
	}

void afficher_tableMPReceptionImporterViaGestionBaseCommandeFinaleServeur(String numBLCommande)
{
/*
////////////////////////////////////////////////////////////////////////////////////////////////  Traitement Pour Reception Importer Via base Gestion Commande finale Serveur   /////////////////////////////////////////////////////////////////////////////////////////////
	
	 
	
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	Magasin magasinDestination=mapMagasin.get(comboMagasin.getSelectedItem());
	  Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
	  
	  


listDetailTransfertMp.clear();

transferStockMPDAO.ViderSession();

 

	try
		{
				
				
				
		 if(con.isClosed()==true)
			 {
				 
				 Connect.connecToGestionCommande();
		    		con=Connect.getConnexionCommande();
		        	stx=con.createStatement();
				 
			 }	
			
		 
		 String	 query="select  dr.DATE_RECEPTION, dr.NUM_LIVRE_FOUR, dr.NUM_RECEPTION, mp.code, mp.nom, dr.Quantite_RECU  from detail_reception dr , detail_commande dc , matiere_premier mp ,  categorie_mp c , sub_categorie_mp s where dc.id=dr.id_detailCommande and dc.id_mat_pre=mp.id and mp.id_cat=c.ID and c.id_sub_cat=s.id and s.id!=16 and  dr.NUM_RECEPTION is not null and  dr.NUM_LIVRE_FOUR='"+numBLCommande+"' " ; 
		 String msg="";		 
		 	 
			rset=stx.executeQuery(query); 
		 
			
			
			
			
			
			
			while(rset.next())
			{
				MatierePremier mp=matierePremiereDAO.findByCode(rset.getString(4))	;
		DetailTransferStockMP detailTransferStockMP=detailTransferMPDAO.DetailTransfertMPByMPBYCodeTransfertBYNumBLReceptionByEtat(mp, rset.getString(3), rset.getString(2), Constantes.ETAT_TRANSFER_STOCK_AJOUT);
				
			if(detailTransferStockMP==null)
			{
				if(mp!=null)
				{
					
				}else
				{
					
				msg=msg+" Code MP : "+rset.getString(4)+"\n";
				
				}
				
			DetailTransferStockMP detailtransfertmp=new DetailTransferStockMP();
			
			detailtransfertmp.setMagasinDestination (magasinDestination);
		    detailtransfertmp.setMagasinSouce(magasinSource);
			detailtransfertmp.setMatierePremier(mp);
		    detailtransfertmp.setNumBLReception (rset.getString(3));
			detailtransfertmp.setQuantite(rset.getBigDecimal(6)); 
			detailtransfertmp.setTransferStockMP(transferStock);
			listDetailTransfertMp.add(detailtransfertmp);
			}
				
				
			}
			
			 
			
			
			
		 
		 if(msg.equals(""))
		 {
			 
			 Date date=null;
			 mapCodeMP.clear();
			 	  int i=0;
			 		while(i<listDetailTransfertMp.size())
			 		{	
			 			date=null;
			 			DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(i);
			 			
			 			mapCodeMP.put(detailTransferStockMP.getMatierePremier().getCode(), detailTransferStockMP.getMatierePremier());
			 		
			 			try
			 			{
			 			 if(con.isClosed()==true)
			 				 {
			 					 
			 					 Connect.connecToGestionCommande();
			 			    		con=Connect.getConnexionCommande();
			 			        	stx=con.createStatement();
			 					 
			 				 }	
			 			 
			 			 String	 queryTmp="select  DATE_RECEPTION, NUM_RECEPTION from detail_reception where NUM_RECEPTION='"+detailTransferStockMP.getNumBLReception()+"' group by NUM_RECEPTION" ; 
			 			 	 
			 				rset=stx.executeQuery(queryTmp); 
			 			 
			 			
			 				while(rset.next())
			 				{
			 					
			 					date=rset.getDate(1);
			 					
			 				}
			 			 
			 			 
			 			 
			 			 
			 				
			 		} catch (Exception ex) {
			 			
			 			 
			 			
			 		}	
			 			
			 			
			 			
			 					
			 					Object []ligne={date,  numBLCommande,detailTransferStockMP.getNumBLReception(), detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(), NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),detailTransferStockMP.getQuantite()};

			 					modeleMP.addRow(ligne);
			 				
			 			 
			 			
			 			i++;
			 		} 
			 
			 
			 
		 }else
		 {
			 
			 JOptionPane.showMessageDialog(null, "les MP Suivant Introuvable : "+msg);
			listDetailTransfertMp.clear();
			intialiserTabeleauReceptionImporter();
			 return;
		 }
		 
		 
			
	} catch (Exception ex) {
		
		 
		
	}
 

		
	*/	
	

}


void afficher_tableMPReceptionImporterViaExcel(List<DetailTransferStockMP> listDetailTransfertStockMP)
{
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Traitement Les Receptions Importer Via Excel  ////////////////////////////////////////////////////////////////
	
	
intialiserTabeleauReceptionImporter();

int i=0;
while(i<listDetailTransfertStockMP.size())
{	

DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(i);






Object []ligne={detailTransferStockMP.getTransferStockMP().getDate(),  detailTransferStockMP.getNumBLReception(),detailTransferStockMP.getTransferStockMP().getCodeTransfer(), detailTransferStockMP.getMatierePremier().getCode() , detailTransferStockMP.getMatierePremier().getNom(), NumberFormat.getNumberInstance(Locale.FRANCE).format(detailTransferStockMP.getQuantite()),detailTransferStockMP.getQuantite(),""};

modeleMP.addRow(ligne);



i++;
} 	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

void afficher_tableTransferMP(List<TransferStockMP> listTransfertMP)
{
intialiserTabeleauTransferMP();
	  int i=0;
		while(i<listTransfertMP.size())
		{	
			
			TransferStockMP transferStockMP=listTransfertMP.get(i);
			
			Object []ligne={transferStockMP.getDateTransfer(),transferStockMP.getCodeTransfer()};

			modeleTransferMP.addRow(ligne);
			i++;
		}
}


void afficher_tableTransferMPCommande(List<String> listNumBL)
{
	
intialiserTabeleauTransferMP();
Date date=null;
	  int i=0;
		while(i<listNumBL.size())
		{	
			date=null;
			try
			{
		 
			 
			 
					
					date=mapDateBL.get(listNumBL.get(i).toString());
					
				 
			 
			 
			 
			 
				
		} catch (Exception ex) {
			
			 
			
		}	
			
			Object []ligne={date,listNumBL.get(i)};

			modeleTransferMP.addRow(ligne);
			i++;
		}
}

void afficher_tableTransferMPCommandeImporterExcel(List<DetailTransferStockMP> listeDetailTransfertMP)
{
	
intialiserTabeleauTransferMP();
Date date=null;
	  int i=0;
		while(i<listeDetailTransfertMP.size())
		{	
		 DetailTransferStockMP detailTransferStockMP=listeDetailTransfertMP.get(i);
			
			Object []ligne={detailTransferStockMP.getTransferStockMP().getDateTransfer(),detailTransferStockMP.getNumBLReception()};

			modeleTransferMP.addRow(ligne);
			i++;
		}
}



void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			 "Code Matière Première","Matière Première", "Fournisseur","Quantite Reception", "Quantité"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		    	public boolean isCellEditable(int row, int column) {
					
					if(column==4 && (table.getValueAt(row, 4)).equals(BigDecimal.ZERO))
					return true;
					else
						
					return columnEditables[column];
				}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(60);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		 
}

void intialiserTabeleauReceptionImporter(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			
		     			 "Date Reception","Num BL","Num Reception","Code MP","Matière Première","Quantite Reception", "Quantité", "Date Reception (Format : Annee-Mois-Jours)"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,true,true
		     	};
		    	public boolean isCellEditable(int row, int column) {
					
				 
						
					return columnEditables[column];
				}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(60);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
		 
}


void intialiserTabeleauTransferMP(){
	
	modeleTransferMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date Reception", "Code BL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   tableTransfertMP.setModel(modeleTransferMP); 
		   tableTransfertMP.getColumnModel().getColumn(0).setPreferredWidth(20);
		   tableTransfertMP.getColumnModel().getColumn(1).setPreferredWidth(160);
		  
		 
}





boolean remplirMapreception(){
	boolean trouve=false;
	
	
/////////////////////////////////////////////////////////////////////////////   Traitement Reception Importer Via Gestion Commande Finale Serveur  ////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	 * 
	 * 
	try {
		
		
		
		
		if(checkImporterBonReception.isSelected()==true)
		{
			
		
				
				
                  for(int j=0;j<table.getRowCount();j++){
					
					if(!table.getValueAt(j, 6).toString().equals("") ){
						//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
						mapQuantite.put(table.getValueAt(j, 3).toString(), new BigDecimal(table.getValueAt(j, 6).toString()));

						trouve=true;
					}
					
					
				}
				
		
			
			
			
		}else
		{
			
			
			for(int j=0;j<table.getRowCount();j++){
				
				if(!table.getValueAt(j, 4).toString().equals("") ){
					//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
					mapQuantite.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 4).toString()));

					trouve=true;
				}
				
				
			}
		}
		
	
		
		
		
		
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "La Quantite doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		
		trouve=false;
		
		
	}
	
	*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	
////////////////////////////////////////////////////////////////////////////    Traitement Reception Importer Via Excel ////////////////////////////////////////////////////////////////////////////////////	

	try {
		
		TransferStockMP transferStockMP=null;
		if(checkImporterBonReception.isSelected()==true)
		{
			transferStockMP=ListedetailTransferStockMPTmp.get(tableTransfertMP.getSelectedRow()).getTransferStockMP();
		}else
		{
			transferStockMP=listTransfertStockMP.get(tableTransfertMP.getSelectedRow());
		}
		 
		
		
		if(transferStockMP.getImporterViaGestionCommande()!=null)
		{
			
		
				if(transferStockMP.getImporterViaGestionCommande().equals(CODE_IMPORTER))
				{
				    for(int j=0;j<table.getRowCount();j++){
						
						if(!table.getValueAt(j, 6).toString().equals("") ){
							//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
							mapQuantite.put(table.getValueAt(j, 3).toString(), new BigDecimal(table.getValueAt(j, 6).toString()));

							trouve=true;
						}
						
						
					}
				}else
				{
					

					
					
					for(int j=0;j<table.getRowCount();j++){
						
						if(!table.getValueAt(j, 4).toString().equals("") ){
							//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
							mapQuantite.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 4).toString()));

							trouve=true;
						}
						
						
					}
				
					
				}
				
              
				
		
			
			
			
		}else
		{
			
			
			for(int j=0;j<table.getRowCount();j++){
				
				if(!table.getValueAt(j, 4).toString().equals("") ){
					//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
					mapQuantite.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 4).toString()));

					trouve=true;
				}
				
				
			}
		}
		
	
		
		
		
		
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "La Quantite doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		
		trouve=false;
		
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


	return trouve;
}





void initialiser(){



}


void initialiserTous(){

	listDetailTransferStockMPTmp.clear();
	intialiserTabeleau();
	intialiserTabeleauTransferMP();
	txtcode.setText("");
dateDebut.setDate(null);
dateFin.setDate(null);
dateTransfereChooser.setDate(null);


}

void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		table.setValueAt("", j,4);
		
	}
}


void ChargerLesReceptionsMagasinier()
{


	transferStockMPDAO= new TransferStockMPDAOImpl();
	detailTransferMPDAO=new DetailTransferMPDAOImpl();
	listDetailTransferStockMP.clear();
	txtcode.setText("");
	dateTransfereChooser.setDate(null);
	
	intialiserTabeleau();
	intialiserTabeleauTransferMP();
	
	
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
		Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
		if(depot==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(magasin==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		else
		{
			ListedetailTransferStockMPTmp.clear();
			
			if(dateDebut.getDate()==null && dateFin.getDate()!=null)
			{
				dateDebut.setDate(dateFin.getDate());
			}else if(dateDebut.getDate()!=null && dateFin.getDate()==null)
			{
				
				dateFin.setDate(dateDebut.getDate());
			}
			
			
			if(checkImporterBonReception.isSelected()==true)
			{
				
			//	ChargerBonLivraisonCommande();
			
				ChargerNumBLImporterExcel(dateDebut.getDate(), dateFin.getDate());
				afficher_tableTransferMPCommandeImporterExcel(ListedetailTransferStockMPTmp);
				
				//afficher_tableTransferMPCommande(listNumBLCommande);
				
			}else
			{
				
				listTransfertStockMP=transferStockMPDAO.findReceptionEnAttenteNonImporter (Constantes.ETAT_RECEPTION_ENATTENT, dateDebut.getDate(), dateFin.getDate(), depot);	
				
				afficher_tableTransferMP(listTransfertStockMP);
			}
			
			
			
			
		}
		
			
	
}


public void ChargerBonLivraisonCommande()
{
	
	/*
	 *  Traitement Reception Via Gestion Commande Finale Serveur
	
	
	mapDateBL.clear();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	listNumBLCommande.clear();
	try
	{
		
		
		if(dateDebut.getDate()==null || dateFin.getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la date Du et date Au SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		 
		
	 if(con.isClosed()==true)
		 {
			 
			 Connect.connecToGestionCommande();
	    		con=Connect.getConnexionCommande();
	        	stx=con.createStatement();
			 
		 }	
	 
	 String	 query="select  db.NUM_LIVRE_FOUR ,db.DATE_DETAIL_BON_LIVR,db.NUM_RECEPTION,mp.code from detail_bon_livraison db , matiere_premier mp, categorie_mp c , sub_categorie_mp s where db.id_mat_pre=mp.id and mp.id_cat=c.ID and c.id_sub_cat=s.id and s.id!=16 and  db.DATE_DETAIL_BON_LIVR between'"+simpleDateFormat.format(dateDebut.getDate())  +"' and '"+simpleDateFormat.format(dateFin.getDate())+"' and db.NUM_RECEPTION is not null group by db.NUM_LIVRE_FOUR,db.NUM_RECEPTION,mp.code" ; 
	 	 
		rset=stx.executeQuery(query); 
		boolean existe=false;	
		String x="";
		while(rset.next())
		{
			
		String numBL=rset.getString(1);
		String numReception=rset.getString(3);
		MatierePremier mp=matierePremiereDAO.findByCode(rset.getString(4))	;	
		
	 
		
		TransferStockMP transferStockMPEnAttente=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(numReception, Constantes.ETAT_RECEPTION_ENATTENT, numBL,mp);	
		TransferStockMP transferStockMPValider=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(numReception, Constantes.ETAT_RECEPTION_VALIDER, numBL,mp);	
		 existe=false;	
		if(transferStockMPEnAttente==null && transferStockMPValider==null)
		{
			for(int t=0;t<listNumBLCommande.size();t++)
			{
				if(listNumBLCommande.get(t).equals(numBL))
				{
					
					existe=true;
				}
				
			}
			if(existe==false)
			{
				mapDateBL.put(numBL, rset.getDate(2));
				listNumBLCommande.add(numBL);
			}
			
			
			
		}
		
			
		}
	  
		
		
} catch (Exception ex) {
	
	JOptionPane.showMessageDialog(null, ex.getMessage()); 
	
}
	
	
	*/
	
}





void ValiderReceptionImporterViaExcel()
{
	
List<String> ListeNumReception=new ArrayList<>();
	
	String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
	if(!remplirMapreception())	{
		JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	} else if(depot==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le depot avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}else if(magasin==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le magasin avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
 
	else {
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			
			
			
			TransferStockMP transferStockMPMagasinier=null;
			
			listDetailTransferStockMPTmp.clear();
			
			Magasin magasindestination=mapMagasin.get(comboMagasin.getSelectedItem().toString());
			
			TransferStockMP transferStockMPValider=new TransferStockMP();
			String numBLReception="";
		/*
			if(checkImporterBonReception.isSelected()==true)
		{
			*/
			
			listIDTransfert.clear();
			boolean datereceptionSaisir=true;
			boolean NumReceptionTrouver=false;
			for(int d=0;d<table.getRowCount();d++)
			{
				
				try {
					if(!table.getValueAt(d, 7).toString().equals(""))
					{
						
						
						
						Date daterecption= simpleDateFormat.parse(table.getValueAt(d, 7).toString());
						numBLReception=table.getValueAt(d, 1).toString();
						if (!table.getValueAt(d, 7).toString().trim().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
						{
							datereceptionSaisir=false;
						}
						NumReceptionTrouver=false;

						
						for(int j=0;j<ListeNumReception.size();j++)
						{
							if(ListeNumReception.get(j).equals(table.getValueAt(d, 2).toString()))
							{
								NumReceptionTrouver=true;
							}
						}
						if(NumReceptionTrouver==false)
						{
							ListeNumReception.add(table.getValueAt(d, 2).toString());
							mapDateNumReception.put(table.getValueAt(d, 2).toString(), daterecption);
						}
						
					}
					
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erreur de date Dans la ligne  "+(d+1) +" Veuillez Entrer La Date En Format Suivante SVP : Annee-Mois-Jours");
					return;
				}
				
				
				
			}
			
			 if(datereceptionSaisir==false)
			 {
				 JOptionPane.showMessageDialog(null, "Veuillez Entrer La Date reception Dans la Table Des Receptions en Format Suivant :  Annee-Mois-Jours  !!!!");
					return; 
			 }
			 
			 boolean DateReceptionNonDeclarerOU=false;
			 
			 for(int d=0;d<table.getRowCount();d++)
				{
					
					
						if(table.getValueAt(d, 7).toString().trim().equals(""))
						{


							
							for(int j=0;j<ListeNumReception.size();j++)
							{
								if(ListeNumReception.get(j).equals(table.getValueAt(d, 2).toString()))
								{
									DateReceptionNonDeclarerOU=true;
								}
							}
						
							
						}else
						{
							
							try {
							Date daterecption= simpleDateFormat.parse(table.getValueAt(d, 7).toString());
							
							for(int j=0;j<ListeNumReception.size();j++)
							{
								if(ListeNumReception.get(j).equals(table.getValueAt(d, 2).toString()))
								{
if(!daterecption.equals(mapDateNumReception.get(table.getValueAt(d, 2).toString())))
{
	DateReceptionNonDeclarerOU=true;

	
}

								}
							}
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Erreur de date Dans la ligne  "+(d+1) +" Veuillez Entrer La Date En Format Suivante SVP : Annee-Mois-Jours");
							return;
						}
							
						}
						
						
				
					
					
					
				}
			 
			 if(DateReceptionNonDeclarerOU==true)
			 {
				 JOptionPane.showMessageDialog(null, "Veuillez Entrer Meme Date Pour Meme Num Reception SVP");
				 return;
			 }
			 

				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					
					if(!table.getValueAt(i, 7).toString().equals(""))
					{
						DetailTransferStockMP detailTransferStockMPTmp=new DetailTransferStockMP();	
						MatierePremier mp=mapCodeMP.get(table.getValueAt(i, 3).toString());
						
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////    Ajout   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
						
						TransferStockMP transferStockMPAjout=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionUnique(table.getValueAt(i, 2).toString(),Constantes.ETAT_TRANSFER_STOCK_AJOUT, table.getValueAt(i, 1).toString());
	                   
						if(transferStockMPAjout!=null)
						{
							
							 
							
							DetailTransferStockMP detailTransferStockAjout=new DetailTransferStockMP();	
							
							if(listDetailTransferStockMP.get(i).getFournisseur()!=null)
							{
								detailTransferStockAjout.setFournisseur(listDetailTransferStockMP.get(i).getFournisseur());	
							}
							
						
						
							detailTransferStockAjout.setMagasinSouce(listDetailTransferStockMP.get(i).getMagasinSouce());
							detailTransferStockAjout.setMagasinDestination(magasindestination);
						
							detailTransferStockAjout.setMatierePremier(mp);
							detailTransferStockAjout.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockAjout.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockAjout.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockAjout.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockAjout.setTransferStockMP(transferStockMPAjout);
						 
						detailTransferMPDAO.add(detailTransferStockAjout);
						boolean existe=false;
						for(int y=0;y<listIDTransfert.size();y++)
						{
							
							if(listIDTransfert.get(y)==transferStockMPAjout.getId())
							{
								existe=true;
								
							}
							
						}
						
						if(existe==false)
						{
							listIDTransfert.add(transferStockMPAjout.getId());
						}
						
						
						
						}else
						{
							TransferStockMP transferStock=new TransferStockMP();
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
							transferStock.setImporterViaGestionCommande(CODE_IMPORTER);
							transferStock.setDepot(depot);
							transferStock.setDate(new Date());
							try {
								transferStock.setDateTransfer(simpleDateFormat.parse(table.getValueAt(i, 7).toString()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							transferStock.setCodeTransfer(table.getValueAt(i, 2).toString());
							transferStock.setCreerPar(utilisateur);
							//transferStockMPValider.setListDetailTransferMP(listDetailTransferStockMPTmp);
	                        transferStockMPDAO.add(transferStock);
	                        
	                        DetailTransferStockMP detailTransferStockAjout=new DetailTransferStockMP();	
	                        
	                        
	                        if(listDetailTransferStockMP.get(i).getFournisseur()!=null)
							{
	                        	detailTransferStockAjout.setFournisseur(listDetailTransferStockMP.get(i).getFournisseur());	
							}
							
						
						
	                        detailTransferStockAjout.setMagasinSouce(listDetailTransferStockMP.get(i).getMagasinSouce());
	                        detailTransferStockAjout.setMagasinDestination(magasindestination);
						
	                        detailTransferStockAjout.setMatierePremier(mp);
	                        detailTransferStockAjout.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockAjout.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockAjout.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockAjout.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockAjout.setTransferStockMP(transferStock);
					 
						detailTransferMPDAO.add(detailTransferStockAjout);
	                        
						boolean existe=false;
						for(int y=0;y<listIDTransfert.size();y++)
						{
							
							if(listIDTransfert.get(y)==transferStock.getId())
							{
								existe=true;
								
							}
							
						}
						
						if(existe==false)
						{
							listIDTransfert.add(transferStock.getId());
						}
						
							
						}	
						
						
						transferStockMPMagasinier=listDetailTransferStockMP.get(i).getTransferStockMP();
						
						
					}
					
					

				 
				
				}
				
				
				
				
				if(transferStockMPMagasinier!=null)
				{
					transferStockMPDAO= new TransferStockMPDAOImpl();
					detailTransferMPDAO=new DetailTransferMPDAOImpl();
					
					boolean existe=true;
					listTransfertValider.clear();
					listDetailTransferStockMPValider.clear();
					boolean transfertexiste=false;
						listDetailTransferStockMPValider=detailTransferMPDAO.findByNumBlReceptionEnAttente(listDetailTransferStockMP.get(0).getNumBLReception(), Constantes.ETAT_RECEPTION_ENATTENT);

					
				for(int j=0;j<listDetailTransferStockMPValider.size();j++)
				{
				
					TransferStockMP transferStockMPAjout=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(listDetailTransferStockMPValider.get(j).getTransferStockMP().getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_AJOUT, listDetailTransferStockMPValider.get(j).getNumBLReception(), listDetailTransferStockMPValider.get(j).getMatierePremier()) ;
if(transferStockMPAjout==null)
{
	existe=false;
}
				
for(int t=0;t<listTransfertValider.size();t++)
{
if(listTransfertValider.get(t).getId()==listDetailTransferStockMPValider.get(j).getTransferStockMP().getId())	
{
	transfertexiste=true;
}
}
if(transfertexiste==false)
{
	listTransfertValider.add(listDetailTransferStockMPValider.get(j).getTransferStockMP());
}

					
				}
					if(existe==true)
					{
						for(int d=0;d<listTransfertValider.size();d++)
						{
							TransferStockMP transferStockMPTmp=listTransfertValider.get(d);
							transferStockMPTmp.setStatut(Constantes.ETAT_RECEPTION_VALIDER);
						transferStockMPDAO.edit(transferStockMPTmp);
							
						}
						
					}
				
				
				
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, "le stock a été validée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				
				 
				
				
				for(int l=0;l<listIDTransfert.size();l++)
				{
					
					List<DetailTransferStockMP> listeDetailTransferStockMPAjouter= new ArrayList<DetailTransferStockMP>();
					
					
					 
					listeDetailTransferStockMPAjouter=detailTransferMPDAO. findByTransferStockMP(listIDTransfert.get(l));
					
					Map parameters = new HashMap();
					parameters.put("numTransfer",listeDetailTransferStockMPAjouter.get(0).getTransferStockMP().getCodeTransfer());    
					parameters.put("numBL", listeDetailTransferStockMPAjouter.get(0).getNumBLReception()); 

					parameters.put("magasinDest", comboMagasin.getSelectedItem());
					parameters.put("depDest", comboDepot.getSelectedItem());
					parameters.put("dateTransfer", listeDetailTransferStockMPAjouter.get(0).getTransferStockMP().getDateTransfer());
					JasperUtils.imprimerBonReceptionMP(listeDetailTransferStockMPAjouter,parameters);	
					
					
				}
				 
				
			 



			int reponse =JOptionPane.showConfirmDialog(null, "Voullez Vous Initialiser l'ecran","Satisfaction", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION )
			{
				ChargerLesReceptionsMagasinier();;
				 
				listDetailTransferStockMPTmp.clear();
				
				
			}
				
				
				

	    /*        
			
		}else
		{
			
		}
			
		*/	
			
		
		
	}
	
		
	
 

	
	
	
	
}


void ValiderReceptionNomImporter()
{
	
	String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
	if(!remplirMapreception())	{
		JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	} else if(depot==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le depot avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}else if(magasin==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le magasin avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
 
	else {
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			
			
			
			TransferStockMP transferStockMPMagasinier=null;
			
			listDetailTransferStockMPTmp.clear();
			
			Magasin magasindestination=mapMagasin.get(comboMagasin.getSelectedItem().toString());
			
			TransferStockMP transferStockMPValider=new TransferStockMP();
			String numBLReception="";

	if(dateTransfere.equals(""))
	{
		JOptionPane.showMessageDialog(null, "Il faut saisir la date d'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut (listDetailTransferStockMP.get(0).getTransferStockMP().getCodeTransfer() ,ETAT_TRANSFER_STOCK_AJOUT );
	
	if(transfererStockMP!=null)
	{
		JOptionPane.showMessageDialog(null, "Code Réception déja entrer Veuillez Choisir un autre Code SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
		
	}else
	{
		if(listDetailTransferStockMP.size()==0)
		{
			
			JOptionPane.showMessageDialog(null, "Il faut selection le transfert SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		  if(txtcode.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Il faut saisir le Num De BL SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		for(int i=0;i<listDetailTransferStockMP.size();i++)
		{
			
		DetailTransferStockMP detailTransferStockMPTmp=new DetailTransferStockMP();	
		MatierePremier mp=mapCodeMP.get(table.getValueAt(i, 0).toString());
	
		 

		transferStockMPMagasinier=listDetailTransferStockMP.get(i).getTransferStockMP();
		
			if(listDetailTransferStockMP.get(i).getFournisseur()!=null)
			{
				
				detailTransferStockMPTmp.setFournisseur(listDetailTransferStockMP.get(i).getFournisseur());	
				
			}
			
		
		
		detailTransferStockMPTmp.setMagasinSouce(listDetailTransferStockMP.get(i).getMagasinSouce());
		detailTransferStockMPTmp.setMagasinDestination(magasindestination);
		
		detailTransferStockMPTmp.setMatierePremier(mp);
		detailTransferStockMPTmp.setQuantite(new BigDecimal(table.getValueAt(i, 4).toString()));
		if(mp.getPrix()!=null)
		{
			detailTransferStockMPTmp.setPrixUnitaire(mp.getPrix());
		}else
		{
			detailTransferStockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
		}
		detailTransferStockMPTmp.setNumBLReception (txtcode.getText().toUpperCase());
		detailTransferStockMPTmp.setTransferStockMP(transferStockMPValider);
		listDetailTransferStockMPTmp.add(detailTransferStockMPTmp);
		
		listDetailTransferStockMP.get(i).setNumBLReception(txtcode.getText().toUpperCase());
		detailTransferMPDAO.edit(listDetailTransferStockMP.get(i));
		}
		
		
		transferStockMPValider.setStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		transferStockMPValider.setDepot(depot);
		transferStockMPValider.setDate(new Date());
		transferStockMPValider.setDateTransfer(dateTransfereChooser.getDate());
		transferStockMPValider.setCodeTransfer(listDetailTransferStockMP.get(0).getTransferStockMP().getCodeTransfer());
		transferStockMPValider.setCreerPar(utilisateur);
		//transferStockMPValider.setListDetailTransferMP(listDetailTransferStockMPTmp);
        transferStockMPDAO.add(transferStockMPValider);
        for(int i=0;i<listDetailTransferStockMPTmp.size();i++)
        {
        	detailTransferMPDAO.add(listDetailTransferStockMPTmp.get(i));
        }
        
        
if(transferStockMPMagasinier!=null)
{
transferStockMPMagasinier.setStatut(Constantes.ETAT_RECEPTION_VALIDER);
 
transferStockMPDAO.edit(transferStockMPMagasinier);
}
	
	
JOptionPane.showMessageDialog(null, "le stock a été validée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);


Map parameters = new HashMap();
parameters.put("numTransfer", transferStockMPValider.getCodeTransfer());      
parameters.put("numBL", listDetailTransferStockMPTmp.get(0).getNumBLReception()); 
parameters.put("magasinDest", comboMagasin.getSelectedItem());
parameters.put("depDest", comboDepot.getSelectedItem());
parameters.put("dateTransfer", dateTransfereChooser.getDate());
JasperUtils.imprimerBonReceptionMP(listDetailTransferStockMPTmp,parameters);




int reponse =JOptionPane.showConfirmDialog(null, "Voullez Vous Initialiser l'ecran","Satisfaction", JOptionPane.YES_NO_OPTION);
if(reponse == JOptionPane.YES_OPTION )
{
ChargerLesReceptionsMagasinier();

listDetailTransferStockMPTmp.clear();


}

	}
	
}

}


void ValiderReceptionImporterViaGestionCommandeFinaleServeur()
{
	
	String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
	if(!remplirMapreception())	{
		JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	} else if(depot==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le depot avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}else if(magasin==null)
	{
		JOptionPane.showMessageDialog(null, "Il faut selectionner le magasin avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
 
	else {
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			
			
			
			TransferStockMP transferStockMPMagasinier=null;
			
			listDetailTransferStockMPTmp.clear();
			
			Magasin magasindestination=mapMagasin.get(comboMagasin.getSelectedItem().toString());
			
			TransferStockMP transferStockMPValider=new TransferStockMP();
			String numBLReception="";
		if(checkImporterBonReception.isSelected()==true)
		{
			
			listIDTransfert.clear();
			boolean datereceptionSaisir=true;
			for(int d=0;d<table.getRowCount();d++)
			{
				
				try {
					if(!table.getValueAt(d, 7).toString().equals(""))
					{
						
						
						
						Date daterecption= simpleDateFormat.parse(table.getValueAt(d, 7).toString());
						numBLReception=table.getValueAt(d, 1).toString();
						if (!table.getValueAt(d, 7).toString().trim().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
						{
							datereceptionSaisir=false;
						}
						
					}
					
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erreur de date Dans la ligne  "+(d+1) +" Veuillez Entrer La Date En Format Suivante SVP : Annee-Mois-Jours");
					return;
				}
				
				
				
			}
			
			 if(datereceptionSaisir==false)
			 {
				 JOptionPane.showMessageDialog(null, "Veuillez Entrer La Date reception Dans la Table Des Receptions en Format Suivant :  Annee-Mois-Jours  !!!!");
					return; 
			 }

				for(int i=0;i<listDetailTransfertMp.size();i++)
				{
					
					if(!table.getValueAt(i, 7).toString().equals(""))
					{
						DetailTransferStockMP detailTransferStockMPTmp=new DetailTransferStockMP();	
						MatierePremier mp=mapCodeMP.get(table.getValueAt(i, 3).toString());
						
						
						
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////    Reception Valider   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
						
						TransferStockMP transferStockMPValiderTmp=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(table.getValueAt(i, 2).toString(), Constantes.ETAT_RECEPTION_VALIDER, table.getValueAt(i, 1).toString(),mp);
	                   
						if(transferStockMPValiderTmp!=null)
						{
							
							 
							
							
							
							if(listDetailTransfertMp.get(i).getFournisseur()!=null)
							{
								detailTransferStockMPTmp.setFournisseur(listDetailTransfertMp.get(i).getFournisseur());	
							}
							
						
						
						detailTransferStockMPTmp.setMagasinSouce(listDetailTransfertMp.get(i).getMagasinSouce());
						detailTransferStockMPTmp.setMagasinDestination(magasindestination);
						
						detailTransferStockMPTmp.setMatierePremier(mp);
						detailTransferStockMPTmp.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockMPTmp.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockMPTmp.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockMPTmp.setTransferStockMP(transferStockMPValiderTmp);
						listDetailTransferStockMPTmp.add(detailTransferStockMPTmp);
						detailTransferMPDAO.add(detailTransferStockMPTmp);
						
						}else
						{
							TransferStockMP transferStock=new TransferStockMP();
							transferStock.setStatut(Constantes.ETAT_RECEPTION_VALIDER);
							transferStock.setImporterViaGestionCommande(CODE_IMPORTER);
							transferStock.setDepot(depot);
							transferStock.setDate(new Date());
							try {
								transferStock.setDateTransfer(simpleDateFormat.parse(table.getValueAt(i, 7).toString()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							transferStock.setCodeTransfer(table.getValueAt(i, 2).toString());
							transferStock.setCreerPar(utilisateur);
							//transferStockMPValider.setListDetailTransferMP(listDetailTransferStockMPTmp);
	                        transferStockMPDAO.add(transferStock);
	                        
	                        if(listDetailTransfertMp.get(i).getFournisseur()!=null)
							{
								detailTransferStockMPTmp.setFournisseur(listDetailTransfertMp.get(i).getFournisseur());	
							}
							
						
						
						detailTransferStockMPTmp.setMagasinSouce(listDetailTransfertMp.get(i).getMagasinSouce());
						detailTransferStockMPTmp.setMagasinDestination(magasindestination);
						
						detailTransferStockMPTmp.setMatierePremier(mp);
						detailTransferStockMPTmp.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockMPTmp.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockMPTmp.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockMPTmp.setTransferStockMP(transferStock);
						listDetailTransferStockMPTmp.add(detailTransferStockMPTmp);
						detailTransferMPDAO.add(detailTransferStockMPTmp);
	                        
	                        
	                        
							
							
							
						}
						
						
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////    Ajout   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
						
						TransferStockMP transferStockMPAjout=transferStockMPDAO.findListeTransferByCodeStatutByNumBLReceptionByMP(table.getValueAt(i, 2).toString(),Constantes.ETAT_TRANSFER_STOCK_AJOUT, table.getValueAt(i, 1).toString(),mp);
	                   
						if(transferStockMPAjout!=null)
						{
							
							 
							
							DetailTransferStockMP detailTransferStockAjout=new DetailTransferStockMP();	
							
							if(listDetailTransfertMp.get(i).getFournisseur()!=null)
							{
								detailTransferStockAjout.setFournisseur(listDetailTransfertMp.get(i).getFournisseur());	
							}
							
						
						
							detailTransferStockAjout.setMagasinSouce(listDetailTransfertMp.get(i).getMagasinSouce());
							detailTransferStockAjout.setMagasinDestination(magasindestination);
						
							detailTransferStockAjout.setMatierePremier(mp);
							detailTransferStockAjout.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockAjout.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockAjout.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockAjout.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockAjout.setTransferStockMP(transferStockMPAjout);
						 
						detailTransferMPDAO.add(detailTransferStockAjout);
						boolean existe=false;
						for(int y=0;y<listIDTransfert.size();y++)
						{
							
							if(listIDTransfert.get(y)==transferStockMPAjout.getId())
							{
								existe=true;
								
							}
							
						}
						
						if(existe==false)
						{
							listIDTransfert.add(transferStockMPAjout.getId());
						}
						
						
						
						}else
						{
							TransferStockMP transferStock=new TransferStockMP();
							transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
							transferStock.setImporterViaGestionCommande(CODE_IMPORTER);
							transferStock.setDepot(depot);
							transferStock.setDate(new Date());
							try {
								transferStock.setDateTransfer(simpleDateFormat.parse(table.getValueAt(i, 7).toString()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							transferStock.setCodeTransfer(table.getValueAt(i, 2).toString());
							transferStock.setCreerPar(utilisateur);
							//transferStockMPValider.setListDetailTransferMP(listDetailTransferStockMPTmp);
	                        transferStockMPDAO.add(transferStock);
	                        
	                        DetailTransferStockMP detailTransferStockAjout=new DetailTransferStockMP();	
	                        
	                        
	                        if(listDetailTransfertMp.get(i).getFournisseur()!=null)
							{
	                        	detailTransferStockAjout.setFournisseur(listDetailTransfertMp.get(i).getFournisseur());	
							}
							
						
						
	                        detailTransferStockAjout.setMagasinSouce(listDetailTransfertMp.get(i).getMagasinSouce());
	                        detailTransferStockAjout.setMagasinDestination(magasindestination);
						
	                        detailTransferStockAjout.setMatierePremier(mp);
	                        detailTransferStockAjout.setQuantite(new BigDecimal(table.getValueAt(i,6).toString()));
						if(mp.getPrix()!=null)
						{
							detailTransferStockAjout.setPrixUnitaire(mp.getPrix());
						}else
						{
							detailTransferStockAjout.setPrixUnitaire(BigDecimal.ZERO);
						}
						detailTransferStockAjout.setNumBLReception(table.getValueAt(i, 1).toString());
						detailTransferStockAjout.setTransferStockMP(transferStock);
						listDetailTransferStockMPTmp.add(detailTransferStockAjout);
						detailTransferMPDAO.add(detailTransferStockAjout);
	                        
						boolean existe=false;
						for(int y=0;y<listIDTransfert.size();y++)
						{
							
							if(listIDTransfert.get(y)==transferStock.getId())
							{
								existe=true;
								
							}
							
						}
						
						if(existe==false)
						{
							listIDTransfert.add(transferStock.getId());
						}
						
							
						}						
						
						
					}
					
					

				 
				
				}
				
				
				
				JOptionPane.showMessageDialog(null, "le stock a été validée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				detailTransferMPDAO.ViderSession();
				transferStockMPDAO.ViderSession();
				for(int l=0;l<listIDTransfert.size();l++)
				{
					
					List<DetailTransferStockMP> listeDetailTransferStockMPAjouter= new ArrayList<DetailTransferStockMP>();
					
					
					 
					listeDetailTransferStockMPAjouter=detailTransferMPDAO. findByTransferStockMP(listIDTransfert.get(l));
					
					Map parameters = new HashMap();
					parameters.put("numTransfer",listeDetailTransferStockMPAjouter.get(0).getTransferStockMP().getCodeTransfer());                
					parameters.put("magasinDest", comboMagasin.getSelectedItem());
					parameters.put("depDest", comboDepot.getSelectedItem());
					parameters.put("dateTransfer", listeDetailTransferStockMPAjouter.get(0).getTransferStockMP().getDateTransfer());
					JasperUtils.imprimerBonReceptionMP(listeDetailTransferStockMPAjouter,parameters);	
					
					
				}
				 
				
			 



			int reponse =JOptionPane.showConfirmDialog(null, "Voullez Vous Initialiser l'ecran","Satisfaction", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION )
			{
				ChargerLesReceptionsMagasinier();
				
				listDetailTransferStockMPTmp.clear();
				
				
			}
				
				
				

	            
			
		}else
		{
			
		}
			
			
			
		
		
	}
	
		
	
 
}


void ChargerNumBLImporterExcel( Date DateDebut,Date DateFin)

{
	
	ListedetailTransferStockMPTmp=detailTransferMPDAO.ListTransferStockMPReceptionGroupByCodeTransfertByNumBL(dateDebut.getDate(), dateFin.getDate(), Constantes.ETAT_RECEPTION_ENATTENT);

	
}





}

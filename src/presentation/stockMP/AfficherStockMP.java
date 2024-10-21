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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Comparateur;
import util.ComparateurStockMP;
import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
 
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
 
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;


public class AfficherStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	private ImageIcon imgImprimer;
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();

	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	private Map<String, BigDecimal> mapPrixMP = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	
	private Map<String, MatierePremier> mapNomMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	
	private Map< String, Integer> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
	private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
	
	
	private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
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
	private List <Object[]> Mindate=new ArrayList<Object[]>();
	JDateChooser datechooserdebut = new JDateChooser();
	
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private Utilisateur utilisateur;
	private JTextField txtCodeMP;
	private MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categoriempdao;
	private FournisseurMPDAO fournisseurMPDAO;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMP = new JComboBox();
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox combofournisseur = new JComboBox();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	JDateChooser dateSituation = new JDateChooser();
	private ImageIcon imgExcel;
	private TransferStockMPDAO transferStockMPDAO;
	private JFrame mainFrame;
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell CellCodeMP;
    private static XSSFCell CellQuantite;
    private static XSSFCell CellPrix;
    private static XSSFCell CellFournisseur;
    private JTextField txtlien;
    JCheckBox CheckClient = new JCheckBox("Client");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1453, 860);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	 transferStockMPDAO= new TransferStockMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
             imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
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
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 275, 1142, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
					  		      if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
					  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
					  	    		comboDepot.addItem(depot.getLibelle());
					  	    		
					  	    		
					  	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasin.addItem(magasin.getLibelle());
							  		      			mapMagasin.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
					  	    }else {
					  	    	
					  	    	listDepot = depotDAO.findAll();	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepot.put(depot.getLibelle(), i);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  	    	
					  	    }
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 Depot depot=new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =listDepot.get(mapDepot.get(comboDepot.getSelectedItem()));
								  		       
						  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			mapMagasin.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 246, 1142, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1142, 165);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 73, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(69, 73, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(223, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(290, 74, 191, 24);
				  		     	 layeredPane.add(comboMagasin);
		
		JLabel label = new JLabel("Code MP:");
		label.setBounds(10, 128, 67, 24);
		layeredPane.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
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
		txtCodeMP.setBounds(70, 128, 118, 26);
		layeredPane.add(txtCodeMP);
		txtCodeMP.setText("");
		txtCodeMP.setColumns(10);
		
		JLabel label_1 = new JLabel("MP :");
		label_1.setBounds(198, 129, 37, 24);
		layeredPane.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 comboMP = new JComboBox();
		 comboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
	  		 	
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
  		 				if(matierePremier!=null)
  		 				{
  		 					txtCodeMP.setText(matierePremier.getCode());
  		 				}
  		 				
  		 				
  		 					
  		 				
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
		comboMP.setBounds(232, 129, 232, 24);
		layeredPane.add(comboMP);
		AutoCompleteDecorator.decorate(comboMP);
		comboMP.setSelectedIndex(-1);
		
		JButton btnImprimer = new JButton("Imprimer ");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Map parameters = new HashMap();
				parameters.put("depot", comboDepot.getSelectedItem());
				
				parameters.put("magasin",comboMagasin.getSelectedItem());
				
				  Collections.sort(listEtatStockMPAfficher, new ComparateurStockMP());
				
				 JasperUtils.imprimerSitutationStock (listEtatStockMPAfficher, parameters);
				 
				//JOptionPane.showMessageDialog(null, "PDF exportÈ avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
				
				
			}
		});
		btnImprimer.setBounds(352, 682, 143, 34);
		btnImprimer.setIcon(imgImprimer);
		add(btnImprimer);
		
		JButton btnModifier = new JButton("Ajouter Initial");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				File fileName = new File("C:/Users/main/Desktop/StockFinale2022.xlsx");
				try {
					/*
					InputStream ExcelFileToRead = new FileInputStream("C:/Users/main/Desktop/StockFinale2022.xlsx");
				    XSSFWorkbook wb=  new XSSFWorkbook(ExcelFileToRead);
					
					 XSSFWorkbook test = new XSSFWorkbook();
					    XSSFSheet sheet = wb.getSheetAt(0);
					    */
					    FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
			  			
	  				      
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
					    
					    
					    JOptionPane.showMessageDialog(null, ExcelWSheet.getPhysicalNumberOfRows()); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
				
				
/*
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
			
				String codeTransfert=Utils.genererCodeTransfer(magasin.getDepot().getCode(),ETAT_TRANSFER_STOCK_INITIAL);
				
				TransferStockMP transferStock = new TransferStockMP();
				transferStock.setCodeTransfer(codeTransfert);
				transferStock.setCreerPar(utilisateur);
				transferStock.setDate(dateSituation.getDate());
				transferStock.setDateTransfer(dateSituation.getDate());
				transferStock.setDepot(magasin.getDepot());
				//transferStock.setListDetailTransferMP(listDetailTransferStockMP);
				transferStock.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
				transferStockMPDAO.add(transferStock);
				for(int i=0;i<listEtatStockMPAfficher.size();i++)
				{
					
					EtatStockMP etatStockMP=listEtatStockMPAfficher.get(i);

					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					
						 
						if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  					{
							
							 
							detailTransferStockMP.setFournisseur(etatStockMP.getFournisseurMP());
							
		  					}else
		  					{
		  						
		  					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
						{
		  						if(etatStockMP.getFournisseurMP()!=null)
		  						{
		  						 
							detailTransferStockMP.setFournisseur(etatStockMP.getFournisseurMP());
							
							
		  						}
		  						
						}

		  						
		  					}
						
						
						
					
				
				detailTransferStockMP.setMagasinDestination(magasin);
				detailTransferStockMP.setMatierePremier(etatStockMP.getMp());
				
				if(magasin.getType().equals(Constantes.MP_CLIENT))
				{
					detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
					
				
				}else
				{
					detailTransferStockMP.setPrixUnitaire(etatStockMP.getMp().getPrix());
				}
				
				detailTransferStockMP.setQuantite(etatStockMP.getQuantiteFinale());
				detailTransferStockMP.setTransferStockMP(transferStock);
				 
				
					
					detailTransferStockMPDAO.add(detailTransferStockMP);
					
					
				
					
				}
				 JOptionPane.showMessageDialog(null, "Stock Valider Avec succÈe ");
			
			
			
			*/
			
			
			
			
			}
		});
		btnModifier.setBounds(586, 682, 131, 34);
		btnModifier.setVisible(true);
		add(btnModifier);
		btnModifier.setVisible(false);
		comboMP.addItem("");
		
		JLabel label_2 = new JLabel("Sous-Categorie Mp");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(507, 73, 144, 24);
		layeredPane.add(label_2);
		
		 soucategoriempcombo = new JComboBox();
		 soucategoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  				while(i<listecategoriemp.size())
  		  				{
  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  				
  		  			}else
  		  			{
  		  			listecategoriemp.clear();
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			comboMP.removeAllItems();
  		  			
  		  			ChargerMP();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  comboMP.removeAllItems();
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(614, 73, 184, 24);
		layeredPane.add(soucategoriempcombo);
		AutoCompleteDecorator.decorate(soucategoriempcombo);
		JLabel label_3 = new JLabel("Categorie Mp");
		label_3.setBounds(821, 71, 80, 26);
		layeredPane.add(label_3);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  			comboMP.addItem(matierePremier.getNom());
  		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			listeMatierePremiereCombo.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listeMatierePremiereCombo.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	}
		 });
		categoriempcombo.setBounds(896, 72, 208, 24);
		layeredPane.add(categoriempcombo);
		AutoCompleteDecorator.decorate(categoriempcombo);
		JLabel lblFournisseur = new JLabel("Fournisseur :");
		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFournisseur.setBounds(494, 127, 78, 24);
		layeredPane.add(lblFournisseur);
		
		 combofournisseur = new JComboBox();
		combofournisseur.setSelectedIndex(-1);
		combofournisseur.setBounds(582, 128, 232, 24);
		layeredPane.add(combofournisseur);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(498, 212, 113, 23);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				MatierePremier matierePremier=MapCodeMatierPremiere.get(txtCodeMP.getText());
				
				
					
					SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
  					CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
  					MatierePremier mp=MapMatierPremiere.get(comboMP.getSelectedItem().toString());
  					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
  					
  				  	FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem().toString());
  				
  				  	if(magasin==null)
  				  	{
  				  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
  				  		return;
  				  	}
  				  	
  				  	
  				  	
  					if(dateSituation.getDate()==null)
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
  						String year = simpleDateFormatyear.format(dateSituation.getDate());
  						
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

  					
  						
  						
  					
  					listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  				
  					
  	//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 					
  				//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

  					
  					
  						listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
  						
  					
 
  					//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					
  					listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
  					listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

  					
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
  				
  				if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() )
  				{
  					if(CheckClient.isSelected()==true)
  					{
  						if(etatStockMP.getMp().getType()!=null)
  						{
  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  						{
  		  						listEtatStockMPAfficher.add(etatStockMP);

  								
  	  						}
  						}
  						
  						
  						
  					}else
  					{
  						listEtatStockMPAfficher.add(etatStockMP);
  						
  					}
  				
  				}
  				
  				
  				
  				
  			}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
  			{
  				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
  				{
  					
  					if(CheckClient.isSelected()==true)
  					{
  						if(etatStockMP.getMp().getType()!=null)
  						{
  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  						{
  		  						listEtatStockMPAfficher.add(etatStockMP);

  								
  	  						}
  						}
  						
  						
  						
  					}else
  					{
  						listEtatStockMPAfficher.add(etatStockMP);
  						
  					}
  				
  					
  				}
  				
  				
  			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
  			{
  				
  				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
  				{
  					
  					if(CheckClient.isSelected()==true)
  					{
  						if(etatStockMP.getMp().getType()!=null)
  						{
  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  						{
  		  						listEtatStockMPAfficher.add(etatStockMP);

  								
  	  						}
  						}
  						
  						
  						
  					}else
  					{
  						listEtatStockMPAfficher.add(etatStockMP);
  						
  					}
  				
  					
  				}
  				
  			}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
  			{
  				if(etatStockMP.getFournisseurMP()!=null)
  				{
  	 				if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
  	  				{
  	  					
  	  					if(CheckClient.isSelected()==true)
  	  					{
  	  						if(etatStockMP.getMp().getType()!=null)
  	  						{
  	  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  	  						{
  	  		  						listEtatStockMPAfficher.add(etatStockMP);

  	  								
  	  	  						}
  	  						}
  	  						
  	  						
  	  						
  	  					}else
  	  					{
  	  						listEtatStockMPAfficher.add(etatStockMP);
  	  						
  	  					}
  	  				
  	  					
  	  				}
  					
  				}
 
  				
  			}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
  			{
  				if(etatStockMP.getFournisseurMP()!=null)
  				{
  					
  					if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
  	  				{
  	  					
  	  					if(CheckClient.isSelected()==true)
  	  					{
  	  						if(etatStockMP.getMp().getType()!=null)
  	  						{
  	  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  	  						{
  	  		  						listEtatStockMPAfficher.add(etatStockMP);

  	  								
  	  	  						}
  	  						}
  	  						
  	  						
  	  						
  	  					}else
  	  					{
  	  						listEtatStockMPAfficher.add(etatStockMP);
  	  						
  	  					}
  	  				
  	  					
  	  				}
  				}
  		
  				
  			}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
  			{
  				if(etatStockMP.getFournisseurMP()!=null)
  				{
  					if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
  	  				{
  	  					
  	  					if(CheckClient.isSelected()==true)
  	  					{
  	  						if(etatStockMP.getMp().getType()!=null)
  	  						{
  	  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  	  						{
  	  		  						listEtatStockMPAfficher.add(etatStockMP);

  	  								
  	  	  						}
  	  						}
  	  						
  	  						
  	  						
  	  					}else
  	  					{
  	  						listEtatStockMPAfficher.add(etatStockMP);
  	  						
  	  					}
  	  				
  	  					
  	  				}
  				}
  				
  			
  				
  			}else if(categorieMp==null && subCategorieMp==null && mp!=null && fournisseurMP==null)
  			{
  				
  				if(  etatStockMP.getMp().getId()==mp.getId() )
  				{
  					
  					if(CheckClient.isSelected()==true)
  					{
  						if(etatStockMP.getMp().getType()!=null)
  						{
  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  						{
  		  						listEtatStockMPAfficher.add(etatStockMP);

  								
  	  						}
  						}
  						
  						
  						
  					}else
  					{
  						listEtatStockMPAfficher.add(etatStockMP);
  						
  					}
  				
  					
  				}
  				
  			}else if(categorieMp==null && subCategorieMp==null && mp==null && fournisseurMP!=null)
  			{
  				if(etatStockMP.getFournisseurMP()!=null)
  				{
  					if(  etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
  	  				{
  	  					
  	  					if(CheckClient.isSelected()==true)
  	  					{
  	  						if(etatStockMP.getMp().getType()!=null)
  	  						{
  	  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  	  						{
  	  		  						listEtatStockMPAfficher.add(etatStockMP);

  	  								
  	  	  						}
  	  						}
  	  						
  	  						
  	  						
  	  					}else
  	  					{
  	  						listEtatStockMPAfficher.add(etatStockMP);
  	  						
  	  					}
  	  				
  	  					
  	  				}
  					
  				}
  	
  				
  			}else if(categorieMp==null && subCategorieMp==null && mp!=null && fournisseurMP!=null)
  			{
  				if(etatStockMP.getFournisseurMP()!=null)
  				{
  					if(  etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() && etatStockMP.getMp().getId()==mp.getId())
  	  				{
  	  					
  	  					if(CheckClient.isSelected()==true)
  	  					{
  	  						if(etatStockMP.getMp().getType()!=null)
  	  						{
  	  							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
  	  	  						{
  	  		  						listEtatStockMPAfficher.add(etatStockMP);

  	  								
  	  	  						}
  	  						}
  	  						
  	  						
  	  						
  	  					}else
  	  					{
  	  						listEtatStockMPAfficher.add(etatStockMP);
  	  						
  	  					}
  	  				
  	  					
  	  				}
  					
  				}
  			
  				
  			}
  			else
  			{
  				
  				
  				if(CheckClient.isSelected()==true)
					{
						if(etatStockMP.getMp().getType()!=null)
						{
							if(etatStockMP.getMp().getType().equals(Constantes.MP_CLIENT))
	  						{
		  						listEtatStockMPAfficher.add(etatStockMP);

								
	  						}
						}
						
						
						
					}else
					{
						listEtatStockMPAfficher.add(etatStockMP);
						
					}
				
  				
  				
  				
  			}
  				
  				
  			
  			
  			
  			
  			
  			
  			
  				
  				
  				
  				
  				
  			}
  			
  	
  			
  			
  			
  			
					 
				intialiserTableau();
				System.out.println(listEtatStockMPAfficher.size());
				Collections.sort(listEtatStockMPAfficher, new ComparateurStockMP());
				
				afficher_tableMP(listEtatStockMPAfficher);
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
			listFournisseur=fournisseurMPDAO.findAll();
			combofournisseur.addItem("");
			for(int i=0;i<listFournisseur.size();i++)
			{
				
				FournisseurMP fournisseurMP=listFournisseur.get(i);
				
				combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
				mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
				
			}
			
			 categoriempcombo.removeAllItems();
	  		  categoriempcombo.addItem("");
	  		soucategoriempcombo.removeAllItems();
	  		soucategoriempcombo.addItem("");
	  		
	  		JLabel lblDateSituation = new JLabel("Date Situation :");
	  		lblDateSituation.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  		lblDateSituation.setBounds(10, 24, 96, 24);
	  		layeredPane.add(lblDateSituation);
	  		
	  		 dateSituation = new JDateChooser();
	  		dateSituation.setLocale(Locale.FRANCE);
	  		dateSituation.setDateFormatString("dd/MM/yyyy");
	  		dateSituation.setBounds(100, 22, 163, 26);
	  		layeredPane.add(dateSituation);
	  		
	  		 CheckClient = new JCheckBox("Client");
	  		CheckClient.setBounds(868, 130, 97, 23);
	  		layeredPane.add(CheckClient);
	  		
	  		JButton btnExporterExcel = new JButton("Exporter Excel");
	  		btnExporterExcel.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				
	  				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
		    		if(magasin!=null)
		    		{
		    			if(table.getRowCount()!=0)
		    			{
		    				
		    				String titre="Etat Stock MP "+magasin.getLibelle();
				    		String titrefeuilleexcel="Etat Stock MP ";
				    		ExporterTableVersExcel.tabletoexcelStockMP (table, titre,titrefeuilleexcel);
		    				
		    				
		    			}else
		    			{
		    				
		    				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
			    			return;
		    				
		    				
		    			}
		    		
		    	
		    		}else
		    		{

		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		
		    		}
	  				
	  				
	  				
	  				
	  				
	  				
	  			}
	  		});
	  		btnExporterExcel.setBounds(170, 682, 156, 34);
	  		btnExporterExcel.setIcon(imgExcel);
	  		add(btnExporterExcel);
	  		 JFileChooser fileDialog = new JFileChooser();
	  		JButton button = new JButton("Ouvrir");
	  		button.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				

	                int returnVal = fileDialog.showOpenDialog(mainFrame);
	                File file = fileDialog.getSelectedFile();
	               txtlien.setText(file.getAbsolutePath());
	  			}
	  		});
	  		button.setBounds(841, 765, 89, 30);
	  		add(button);
	  		button.setVisible(false);
	  		JButton btnAjouterQuantiteInitial = new JButton("Ajouter Quantite Initial MP");
	  		btnAjouterQuantiteInitial.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {

	        		
	        		 
	        		
	        		if(!txtlien.getText().equals(""))
	  				{
	        			
	   				 File fileName = new File(txtlien.getText());
		  				
	  				 
		  				try {
		  					
		  			 
		  						
		  				       FileInputStream ExcelFile = new FileInputStream(fileName);
		  				      
		  				       
		  			
		  				       
		  				      
		  				       
		  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
		  				       
		  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
		  				      
		  				      
		  				      
		  				      
		Depot depot =depotDAO.findByCode(comboDepot.getSelectedItem().toString()) ;
		
		  Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString())	;			   
		  				      
		  String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_INITIAL);				      
		  				      
		  				    
		  				      
	int t=0;							  				      
		  				      
	int id=0;		


	   		TransferStockMP transferStockMP=new TransferStockMP();
	   		
	   		transferStockMP.setDate(new Date());					
	   		transferStockMP.setDateTransfer(dateSituation.getDate());				
	   		transferStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_INITIAL);
	   		transferStockMP.setDepot(depot);
	   		transferStockMP.setCodeTransfer(codeTransfert);
	   		transferStockMPDAO.add(transferStockMP);

	for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
	{
		
		

	try {
		
		 
		Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
		
		CellCodeMP=ExcelWSheet.getRow(i).getCell(0);
		CellQuantite=ExcelWSheet.getRow(i).getCell(2);
		CellFournisseur=ExcelWSheet.getRow(i).getCell(3);
		 
		DataFormatter dataFormatter = new DataFormatter();
		String formattedCellStr = dataFormatter.formatCellValue(CellCodeMP);
		
	 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
	 {
		 
		 
			 
			 DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			 MatierePremier mp=matierePremiereDAO.findByCode(formattedCellStr);
			 detailTransferStockMP.setMatierePremier(mp);
			  if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  {
				FournisseurMP fournisseurMP=fournisseurMPDAO.findByCode(CellFournisseur.getStringCellValue().toString())  ;
				detailTransferStockMP.setFournisseur(fournisseurMP);
				  
			  }
			  
			  detailTransferStockMP.setQuantite(new BigDecimal(CellQuantite.getNumericCellValue()) );
			 
			  detailTransferStockMP.setMagasinDestination(magasin);
			  detailTransferStockMP.setTransferStockMP(transferStockMP);
			  detailTransferStockMPDAO.add(detailTransferStockMP);
		 
		
	 }
	 
		



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	t=t+1;


	 




	} catch (Exception ex) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
	JOptionPane.showMessageDialog(null, ex.getMessage()); 
	return;
	}









	}



		
		  							
		  							
		  						
		  					
		  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////							  					
		  					
		  					
		  					JOptionPane.showMessageDialog(null, "OK");
		  					
							
							
							
							
						} catch (Exception exx) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, exx.getMessage()); 
						}
	        			
	        			
	        			
	  				}
	        		
	        		 	
	        	
	  				
	  			}
	  		});
	  		btnAjouterQuantiteInitial.setBounds(94, 765, 228, 36);
	  		add(btnAjouterQuantiteInitial);
	  		btnAjouterQuantiteInitial.setVisible(false);
	  		txtlien = new JTextField();
	  		txtlien.setEditable(false);
	  		txtlien.setColumns(10);
	  		txtlien.setBounds(372, 765, 459, 36);
	  		add(txtlien);
	  		txtlien.setVisible(false);
	  		JButton btnAjouterPrixMp = new JButton("Ajouter Prix MP");
	  		btnAjouterPrixMp.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				
	  				AjouterPrixMP();
	  			}
	  		});
	  		btnAjouterPrixMp.setBounds(94, 813, 228, 36);
	  		add(btnAjouterPrixMp);
	  		btnAjouterPrixMp.setVisible(false);
			  int i=0;
	  		  while(i<listsubcategoriemp.size())
	  		  {
	  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
	  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
	  			  i++;
	  		  }
	  		  
	  		  
	  		  
	  		ChargerMP();
	  		  
	   
	  		  
				  		 
	}
	
	void ChargerMP()
	{
		comboMP.removeAllItems();
		comboMP.addItem("");
  		listeMatierePremiereCombo=matierePremiereDAO.findAll();
  			for(int t=0;t<listeMatierePremiereCombo.size();t++)	
  			{
  				
  				MatierePremier matierePremier=listeMatierePremiereCombo.get(t);
  				
  			    comboMP.addItem(matierePremier.getNom());
  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  				
  			}	
  			
  			comboMP.setSelectedItem(""); 
	}
void AjouterPrixMP()
{


	if(dateSituation.getDate()==null)
	{
		JOptionPane.showMessageDialog(null, "Veuillez Entrer La date SVP");return;
	}
	 
	
	if(!txtlien.getText().equals(""))
		{
		
		 File fileName = new File(txtlien.getText());
			
		 
			try {
				
		 
					
			       FileInputStream ExcelFile = new FileInputStream(fileName);
			      
			       
		
			       
			      
			       
			       ExcelWBook = new XSSFWorkbook(ExcelFile);
			       
			      ExcelWSheet = ExcelWBook.getSheetAt(0);
			      
			      
			      
			      
 			      
			      
			    
			      
int t=0;							  				      
			      
int id=0;		


 

for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{



try {


Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();

CellCodeMP=ExcelWSheet.getRow(i).getCell(0);
CellPrix=ExcelWSheet.getRow(i).getCell(2);
 

DataFormatter dataFormatter = new DataFormatter();
String formattedCellStr = dataFormatter.formatCellValue(CellCodeMP);

if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
{
 
MatierePremier mp=matierePremiereDAO.findByCode(formattedCellStr);
 if(mp!=null)
 {
	 mp.setPrixByYear( DateUtils.getAnnee(dateSituation.getDate()), new BigDecimal(CellPrix.getNumericCellValue())); 
	 mp.setPrixInitialByYear( DateUtils.getAnnee(dateSituation.getDate()), new BigDecimal(CellPrix.getNumericCellValue()));
	 matierePremiereDAO.edit(mp);
	 
 }


}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;







} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}




						
						
					
				
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  					
				
				
				JOptionPane.showMessageDialog(null, "OK");
				
			
			
			
			
		} catch (Exception exx) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(null, exx.getMessage()); 
		}
		
		
		
		}
	
	 	

		
	
}
	
	
	
	
void afficher_tableMP(List<EtatStockMP> listEtatStockMP)
	{
	
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
	
		  int i=0;
			while(i<listEtatStockMP.size())
			{	
				
				EtatStockMP EtatstockMP=listEtatStockMP.get(i);
				
				String fournisseurMP="";
				
				if(EtatstockMP.getFournisseurMP()!=null)
				{
					fournisseurMP=EtatstockMP.getFournisseurMP().getCodeFournisseur();
				}
				
			//	mapPrixMP.put(stockMP.getMatierePremier().getCode(), stockMP.getPrixUnitaire());
				mapMP.put(EtatstockMP.getMp().getCode(), EtatstockMP.getMp());
				Object []ligne={EtatstockMP.getMp().getCode(),EtatstockMP.getMp().getNom(),dfDecimal.format(EtatstockMP.getQuantiteFinale()),fournisseurMP};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre", "QuantitÈ","Fournisseur"
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
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		 
		   table.getTableHeader().setReorderingAllowed(false);
}


void intialiserModifier(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre", "QuantitÈ"
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
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		  
		   table.getTableHeader().setReorderingAllowed(false);
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
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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

//////////////////////////////////////////////////////////////////////////////////////////////////////// Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		   
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


//////////////////////////////////////////////////////////////////////////////////////////////////////// Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
	retour=BigDecimal.ZERO;
	retourFournisseurAnnule=BigDecimal.ZERO;
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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





//////////////////////////////////////////////////////////////////////////////////////////////////////// Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





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

////////////////////////////////////////////////////////////////////////////////////////////////////////   Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

//////////////////////////////////////////////////////////////////////////////////////////////////////// Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
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

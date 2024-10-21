package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import util.ComparateurStockMP;
import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueImportationDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueImportationDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeFixe;
import dao.entity.ChargeProduction;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueImportation;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class ConsulterManqueImportation extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;

	private JXTable  tableMP = new JXTable();
	
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	 
	 
	 
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	//private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	
	
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiere= new HashMap<>();
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	
	 private JComboBox combomp;

	


	
private StockMPDAO stockMPDAO;
	private JTextField txtcodemp;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	private MatierePremiereDAO matierepremieredao;
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private   JDateChooser dateChooserDu ;
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private Map< String, FournisseurAdhesive> mapFournisseurAdhesif = new HashMap<>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private List<FournisseurAdhesive> listFournisseurAdhesif =new ArrayList<FournisseurAdhesive>();
	private FournisseurMPDAO fournisseurMPDAO;
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	 private SubCategorieMPDAO categorieMPDAO;
	 private List<ManqueImportation> listManqueImportation =new ArrayList<ManqueImportation>();
	 private List<ManqueImportation> listManqueImportationBon =new ArrayList<ManqueImportation>();
	 private ManqueImportationDAO manqueImportationDAO;
	 JComboBox soucategoriempcombo = new JComboBox();
	 List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	 List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
		private SubCategorieMPDAO subcategoriempdao;
		private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
		private Map< String, CategorieMp> catMap = new HashMap<>();
		private CategorieMpDAO categoriempdao;
		JComboBox categoriempcombo = new JComboBox();
		  JDateChooser dateChooserAu = new JDateChooser();
		  private JTextField txtNumReception;
		
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterManqueImportation() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 795);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	matierepremieredao=new MatierePremierDAOImpl();
         	stockMPDAO= new StockMPDAOImpl();
         	
        	
        
         transferStockMPDAO= new TransferStockMPDAOImpl();
         detailTransferStockMPDAO= new DetailTransferMPDAOImpl();
         fournisseurMPDAO=new FournisseurMPDAOImpl();
         fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
         
         manqueImportationDAO=new ManqueImportationDAOImpl();
         categoriempdao=new CategorieMpDAOImpl();
     	subcategoriempdao=new SubCategorieMPAOImpl() ;
     	listsubcategoriemp=subcategoriempdao.findAll();
     	//listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
    	listFournisseur=fournisseurMPDAO.findAll();
         	listFournisseurAdhesif=fournisseurAdhesiveDAO.findAll();
          } catch (Exception exp){exp.printStackTrace();}
        tableMP.setSortable(false);
        tableMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {

       	
       	
       	}
       });
        
       tableMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Num Reception","Date ","Magasin","Code MP","Matière Première","Fournisseur", "Quantite Facture" , "Quantite Receptione" , "Quantite Manque" 
       	}
       ));
				  		
       tableMP.setShowVerticalLines(false);
       tableMP.setSelectionBackground(new Color(51, 204, 255));
       tableMP.setRowHeightEnabled(true);
       tableMP.setBackground(new Color(255, 255, 255));
       tableMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMP.setColumnControlVisible(true);
       tableMP.setForeground(Color.BLACK);
       tableMP.setGridColor(new Color(0, 0, 255));
       tableMP.setAutoCreateRowSorter(true);
       tableMP.setBounds(2, 27, 411, 198);
       tableMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMP);
				  		     	scrollPane.setBounds(34, 317, 1211, 389);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des MP");
				  		     	titledSeparator.setBounds(34, 276, 1211, 30);
				  		     	add(titledSeparator);
		      
		     
		     	int i=0;
		     	while(i<listMP.size())
		     	{
		     		
		     		MatierePremier mp=listMP.get(i);
		     		mapCodeMP.put(mp.getCode(), mp);
		     		mapMatierePremiere.put(mp.getNom(), mp);
		     		combomp.addItem(mp.getNom());
		     		
		     		
		     		i++;
		     	}
		      
		 
		 
		
		JButton buttonvalider = new JButton("Exporter Excel");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(tableMP.getRowCount()!=0)
			{
				
				
				
				String titre="MANQUE IMPORTATION MAGASIN "+listManqueImportation.get(0).getMagasin().getLibelle();
	    		String titrefeuilleexcel="MANQUE IMPORTATION MAGASIN "+listManqueImportation.get(0).getMagasin().getLibelle();
	    		ExporterTableVersExcel.tabletoexcelManqueImportation(tableMP, titre, titrefeuilleexcel); 
				
				
				
				
				
				
			}else
			{
				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
			}
			
			
			
			
			
			}});
		buttonvalider.setIcon(new ImageIcon(ConsulterManqueImportation.class.getResource("/img/excel.png")));
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(756, 725, 158, 30);
		add(buttonvalider);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1200, 171);
		add(layeredPane_1);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(598, 31, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(664, 32, 209, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
     				if(depot!=null)
     				{
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
     					 listMagasinDechetMP= depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
		     				if(listMagasin.size()!=0 || listMagasinDechetMP.size()!=0 )
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
  		     				{
  		     					Magasin magasin=listMagasin.get(i);
  		     					combomagasin.addItem(magasin.getLibelle());
  		     					mapMagasin.put(magasin.getLibelle(), magasin);
  		     					i++;
  		     				}
		     					
		     					  int j=0;
					  		      	while(j<listMagasinDechetMP.size())
					  		      		{	
					  		      			Magasin magasin=listMagasinDechetMP.get(j);
					  		      		combomagasin.addItem(magasin.getLibelle());
		  		     					mapMagasin.put(magasin.getLibelle(), magasin);
					  		      			j++;
					  		      		}
		     					
		     					
		     					
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
     					
     				}else
     				{
     					combomagasin.removeAllItems();
     				}
     				
     				
    	 		 }
    	 	
 	}
		  });
		  combodepot.addItem("");
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	
		     	while (k<listDepot.size())
		     	{
		     	Depot depot=listDepot.get(k);
		     	combodepot.addItem(depot.getLibelle());
			     		
			     mapDepot.put(depot.getLibelle(), depot);
			     	
		     		
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedItem("");;
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(915, 32, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(995, 33, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.addItem("");
		  combomagasin.setSelectedItem("");
		  JLabel lblDateDu = new JLabel("Date  Du :");
		  lblDateDu.setBounds(10, 31, 62, 24);
		  layeredPane_1.add(lblDateDu);
		  
		   dateChooserDu = new JDateChooser();
		  dateChooserDu.setLocale(Locale.FRANCE);
		  dateChooserDu.setDateFormatString("dd/MM/yyyy");
		  dateChooserDu.setBounds(82, 28, 164, 26);
		  layeredPane_1.add(dateChooserDu);
		  
		  JLabel label_2 = new JLabel("Sous-Categorie Mp");
		  label_2.setBounds(10, 85, 144, 24);
		  layeredPane_1.add(label_2);
		  label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel label_1 = new JLabel("Fournisseur : ");
		  label_1.setBounds(10, 134, 73, 24);
		  layeredPane_1.add(label_1);
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  
		  comboFournisseur = new JComboBox();
		  comboFournisseur.setBounds(92, 136, 172, 24);
		  layeredPane_1.add(comboFournisseur);
		  comboFournisseur.setSelectedIndex(-1);
		  
		  comboFournisseur.addItem("");
		  
		   soucategoriempcombo = new JComboBox();
		   soucategoriempcombo.setBounds(117, 85, 184, 24);
		   layeredPane_1.add(soucategoriempcombo);
		   soucategoriempcombo.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		

		    		

		   		

		   		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  			categoriempcombo.addItem("");
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
  		  	combomp.removeAllItems();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  combomp.removeAllItems();
  		  		}
  		  		
  		  	
		   		
		   		
		   		
		   	
		   		
		   		
		   	
		    		
		    		
		    		
		    	
		   		
		   		
		   	}
		   });
		   
		 
		
		JLabel label_5 = new JLabel("Categorie Mp");
		label_5.setBounds(330, 85, 80, 26);
		layeredPane_1.add(label_5);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.setBounds(405, 86, 208, 24);
		 layeredPane_1.add(categoriempcombo);
		 
		   JLabel lblCodeArticle = new JLabel("Code MP :");
		   lblCodeArticle.setBounds(623, 84, 64, 26);
		   layeredPane_1.add(lblCodeArticle);
		   lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		   
		   txtcodemp = new JTextField();
		   txtcodemp.setBounds(702, 85, 82, 26);
		   layeredPane_1.add(txtcodemp);
		   util.Utils.copycoller(txtcodemp);
		   txtcodemp.addKeyListener(new KeyAdapter() {
		   	@Override
		   	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     					
			      			if(!txtcodemp.getText().equals(""))
			      			{

			      			MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
			      			if(mp!=null)
			      			{
			      				
			      				combomp.setSelectedItem(mp.getNom());
			      				
			      			}else
			      			{
			      				 JOptionPane.showMessageDialog(null, " Code MP  incorrecte ", "Erreur", JOptionPane.ERROR_MESSAGE);
			      				
			      			}
			      			
			      			
			      			}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		   });
		   
		   
		   
		   txtcodemp.setColumns(10);
		   
		   txtcodemp.setText(Constantes.CODE_MP);
		   
		   JLabel lbllibelle = new JLabel("Libelle :");
		   lbllibelle.setBounds(794, 84, 57, 26);
		   layeredPane_1.add(lbllibelle);
		   lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		   
		   
		      combomp = new JComboBox();
		      combomp.setBounds(834, 85, 344, 27);
		      layeredPane_1.add(combomp);
		      combomp.addItem("");
		      
		      JLabel lblDateAu = new JLabel("Date Au  :");
		      lblDateAu.setBounds(273, 31, 62, 24);
		      layeredPane_1.add(lblDateAu);
		      
		       dateChooserAu = new JDateChooser();
		      dateChooserAu.setLocale(Locale.FRANCE);
		      dateChooserAu.setDateFormatString("dd/MM/yyyy");
		      dateChooserAu.setBounds(340, 28, 181, 26);
		      layeredPane_1.add(dateChooserAu);
		      combomp.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent arg0) {

		      		if(combomp.getSelectedIndex()!=-1)
	  		 		{
		      			
		      			if(!combomp.getSelectedItem().equals(""))
  				 		{
  				 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
  				 			txtcodemp.setText(mp.getCode());
  				 		if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  				 		{
  				 			
  				 			comboFournisseur.removeAllItems();
  				 			comboFournisseur.addItem("");
  				 			 
  				 			for(int k=0;k<listFournisseur.size();k++)
  				 			{
  				 				FournisseurMP fournisseurMP=listFournisseur.get(k);
  				 				comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
  				 				mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
  				 				
  				 			}
  				 			
  				 			
  				 			
  				 		}else
  				 		{
  				 			
  				 			comboFournisseur.removeAllItems();
  				 			comboFournisseur.addItem("");
  				 			 
  				 			for(int k=0;k<listFournisseurAdhesif.size();k++)
  				 			{
  				 				FournisseurAdhesive fournisseurAdhesive=listFournisseurAdhesif.get(k);
  				 				comboFournisseur.addItem(fournisseurAdhesive.getCodeFournisseur());
  				 				mapFournisseurAdhesif.put(fournisseurAdhesive.getCodeFournisseur(), fournisseurAdhesive);
  				 				
  				 			}
  				 			
  				 			
  				 		}
  				 			
  				 		
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodemp.setText(Constantes.CODE_MP);
  				 			
  				 		}
  				 	
		      			
	  		 		}else
	  		 		{
	  		 			txtcodemp.setText(Constantes.CODE_MP);
	  		 		}
  		     	 	
  			 		
  			 		
  			 		
  			 		
  		     	 		
  		     	 	
		      	}
		      });
		 categoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

		  		


		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listMP.clear();
  		  			txtcodemp.setText("");
  		  		combomp.removeAllItems();
  		  	combomp.addItem("");
		  			
  		  listMP=matierepremieredao.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listMP.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listMP.get(i);
  		  			combomp.addItem(matierePremier.getNom());
  		  		mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			
  		  			listMP.clear();
  		  				txtcodemp.setText("");
  		  				combomp.removeAllItems();
  		  			combomp.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listMP.clear();
  		  	txtcodemp.setText("");
  		  combomp.removeAllItems();
  		combomp.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		  		
		  		
		  		
		  	
		 		
		 		
		 	}
		 });
		 
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1200, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Consulter");
		btnAjouter.setBounds(465, 222, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					
					if(dateChooserAu.getDate()==null && dateChooserDu.getDate()==null && combodepot.getSelectedIndex()==-1 && combomagasin.getSelectedIndex()==-1 && combomp.getSelectedItem().equals("") && txtcodemp.getText().equals("") &&  comboFournisseur.getSelectedItem().equals("") && soucategoriempcombo.getSelectedItem().equals("") && categoriempcombo.getSelectedItem().equals(""))
					{
						
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner un ou plusieurs champs SVP","Erreur",JOptionPane.ERROR_MESSAGE);	
					return;	
						
						
					}else
					{
						
						listManqueImportation.clear();
						
						String	dateDu="" ; 
						String dateAu=""; 
					
						String requete="";
						
						
						requete=requete+" where m.id>0 "	;
						
					if(dateChooserDu.getDate()==null && dateChooserAu.getDate()==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Date SVP");
						return;
					}
					
					if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()==null)
					{
						dateChooserAu.setDate(dateChooserDu.getDate());
						
					}
					if(dateChooserDu.getDate()==null && dateChooserAu.getDate()!=null)
					{
						dateChooserDu.setDate(dateChooserAu.getDate());
						
					}
					
					
					
					dateDu= sdf.format(dateChooserDu.getDate()) ;
					dateAu= sdf.format(dateChooserAu.getDate()) ;
					
				
					
					 
					
					
					
					if(!dateDu.equals("") && !dateAu.equals(""))
					{
						requete=requete+" and m.date between '"+dateDu+"' and '"+dateAu+"' ";
					}
					
					
					if(!dateDu.equals("") && dateAu.equals(""))
					{
						requete=requete+" and m.date ='"+dateDu+"' ";
					}
					
					if(dateDu.equals("") && !dateAu.equals(""))
					{
						requete=requete+" and m.date ='"+dateAu+"' ";
					}
						
						if(!combodepot.getSelectedItem().equals(""))
						{
							
							requete=requete+" and m.magasin.depot.id ='"+mapDepot.get(combodepot.getSelectedItem()).getId()+"' ";
							
						}
						
						if(!combomagasin.getSelectedItem().equals(""))
						{
							
							requete=requete+" and m.magasin.id ='"+mapMagasin.get(combomagasin.getSelectedItem()).getId()+"' ";
							
						}
						
						
						
						
						if(!soucategoriempcombo.getSelectedItem().equals(""))
						{
							
							SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
							if(subCategorieMp!=null)
							{
								requete=requete+" and m.matierePremier.categorieMp.subCategorieMp.id ='"+subCategorieMp.getId()+"' ";
							}
							
							
						}
						
						
						if(!categoriempcombo.getSelectedItem().equals(""))
						{
							
							CategorieMp  CategorieMp=catMap.get(categoriempcombo.getSelectedItem());
							if(CategorieMp!=null)
							{
								requete=requete+" and m.matierePremier.categorieMp.id ='"+CategorieMp.getId()+"' ";
							}
							
							
						}
						
						if(combomp.getSelectedIndex()!=-1)
						{
							if(!combomp.getSelectedItem().equals(""))
							{
								
								MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
								if(mp!=null)
								{
									requete=requete+" and m.matierePremier.id ='"+mp.getId()+"' ";
								}
								
								
							}
						}
						
						
						if(!comboFournisseur.getSelectedItem().equals(""))
						{
							
							FournisseurMP  fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
							
							
							if(fournisseurMP!=null)
							{
								requete=requete+" and m.fournisseurMP.id ='"+fournisseurMP.getId()+"' ";
							}
							
							
							FournisseurAdhesive fournisseurAdhesive=mapFournisseurAdhesif.get(comboFournisseur.getSelectedItem());
							
							
							if(fournisseurAdhesive!=null)
							{
								requete=requete+" and m.fournisseurAdhesif.id ='"+fournisseurAdhesive.getId()+"' ";
							}
							
							
						}
						
						if(!txtNumReception.getText().equals(""))
						{
							requete=requete+" and m.numReception ='"+txtNumReception.getText().toUpperCase().trim()+"' ";
							
						}
						
					
						requete=requete+" and m.etat='"+Constantes.ETAT_VALIDER+"' ";
						
					listManqueImportation=manqueImportationDAO.findByRequete(requete);
					afficher_tableMP(listManqueImportation);
						
						
						
						
					}
					
 
					
					
					
				
				
				
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "la Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
		
				}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(596, 221, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiserMP();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  soucategoriempcombo.addItem("");
		int j=0;
		  while(j<listsubcategoriemp.size())
		  {
			  subcatMap.put(listsubcategoriemp.get(j).getNom(), listsubcategoriemp.get(j));
			  soucategoriempcombo.addItem(listsubcategoriemp.get(j).getNom());
			  j++;
		  }	
		  
		  soucategoriempcombo.setSelectedItem("");
		  
		  JLabel label = new JLabel("Num reception  :");
		  label.setBounds(274, 134, 101, 26);
		  layeredPane_1.add(label);
		  
		  txtNumReception = new JTextField();
		  txtNumReception.setColumns(10);
		  txtNumReception.setBounds(385, 134, 155, 26);
		  layeredPane_1.add(txtNumReception);
		  
		  JButton btnImprimer_1 = new JButton("Imprimer Bon");
		  btnImprimer_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		if(tableMP.getSelectedRow()!=-1)
		  		{
		  			String req="";
		  			req=req+" where m.numReception='"+listManqueImportation.get(tableMP.getSelectedRow()).getNumReception()+"'  and m.etat='"+Constantes.ETAT_VALIDER+"'";
		  			listManqueImportationBon=manqueImportationDAO.findByRequete(req);
		  			
		  			
		  			Map parameters = new HashMap();
					parameters.put("depot", listManqueImportationBon.get(0).getMagasin().getDepot().getLibelle());
					
					parameters.put("magasin",listManqueImportationBon.get(0).getMagasin().getLibelle());
					
					parameters.put("numreception",listManqueImportationBon.get(0).getNumReception());
					parameters.put("datesituation",listManqueImportationBon.get(0).getDate());
					parameters.put("datesituationtext","Date Situation:");
					parameters.put("numreceptiontext","Num Réception :");
					JasperUtils.imprimerManqueImportation (listManqueImportationBon, parameters);
		  			
		  			
		  		}
		  		
				 
				
	  		  	
				
				
				
				
		  		
		  		
		  	}
		  });
		  btnImprimer_1.setIcon(new ImageIcon(ConsulterManqueImportation.class.getResource("/img/imprimer.png")));
		  btnImprimer_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnImprimer_1.setBounds(573, 725, 158, 30);
		  add(btnImprimer_1);
		  
		  JButton btnImprimerListe = new JButton("Imprimer Liste");
		  btnImprimerListe.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
				if(listManqueImportation.size()!=0)
		  		{
		  			
		  			Map parameters = new HashMap();
					parameters.put("depot", listManqueImportation.get(0).getMagasin().getDepot().getLibelle());
					
					parameters.put("magasin",listManqueImportation.get(0).getMagasin().getLibelle());
					
					
					JasperUtils.imprimerManqueImportation (listManqueImportation, parameters);
					
					//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  			
		  		}
		  		
		  		
		  		
		  	}
		  });
		  btnImprimerListe.setIcon(new ImageIcon(ConsulterManqueImportation.class.getResource("/img/imprimer.png")));
		  btnImprimerListe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnImprimerListe.setBounds(385, 725, 158, 30);
		  add(btnImprimerListe);
			
		}
	

	
	void initialiserdepot()
	{
		dateChooserDu.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		 
		}

	void initialiserMP()
	{
		
		soucategoriempcombo.setSelectedItem("");
		categoriempcombo.removeAllItems();
		categoriempcombo.addItem("");
		categoriempcombo.setSelectedItem("");
		combomp.removeAllItems();
		combomp.addItem("");
		txtcodemp.setText("");
		txtNumReception.setText("");
	     btnAjouter.setEnabled(true);

		 txtcodemp.setText(Constantes.CODE_MP);
		 comboFournisseur.setSelectedItem("");
		 initialiserdepot();
		
	}
	
	void InitialiseTableau()
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première","Fournisseur",  "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableMP.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableMP.getColumnModel().getColumn(2).setPreferredWidth(94);
	       tableMP.getColumnModel().getColumn(3).setPreferredWidth(94);
	    
		
	
}
	
	
	void afficher_tableMP(List<ManqueImportation> listManqueImportation)
	{
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);
		
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Reception","Date ","Magasin","Code MP","Matière Première","Fournisseur", "Quantite Facture" , "Quantite Receptione" , "Quantite Manque" 
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		int i=0;
		 
		while(i<listManqueImportation.size())
		{	
		ManqueImportation manqueImportation=listManqueImportation.get(i);
		
		
		
		
		String fournisseur="";
		
	 if(manqueImportation.getFournisseurMP()!=null)
	 {
		 fournisseur= manqueImportation.getFournisseurMP().getCodeFournisseur();
	 }
			
			Object []ligne={manqueImportation.getNumReception(), manqueImportation.getDate(),manqueImportation.getMagasin().getLibelle(),  manqueImportation.getMatierePremier().getCode(),manqueImportation.getMatierePremier().getNom(),fournisseur,dfDecimal.format(manqueImportation.getQuantiteFacture()),dfDecimal.format(manqueImportation.getQuantiteReceptione()),dfDecimal.format(manqueImportation.getQuantite()) };

			modeleMP.addRow(ligne);
			i++;
		}
}
	}



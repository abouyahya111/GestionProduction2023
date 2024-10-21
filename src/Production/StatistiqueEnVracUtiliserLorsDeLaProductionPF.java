package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;


import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SituationProductionTotalParArticlePFClass;
import dao.entity.StatistiqueEnVracConsommeClass;
import dao.entity.StatistiqueEnVracConsommeLorsProductionPFClass;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;


public class StatistiqueEnVracUtiliserLorsDeLaProductionPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableStatistiqueEnvracConsomme;
	
	  private ImageIcon imgExcel;
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ProductionDAO productionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private List<Production> listProduction=new ArrayList<Production>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseur>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurImprimer=new ArrayList<DetailManqueDechetFournisseur>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<Object[]> listObject =new ArrayList<Object[]>();
	private List<Object[]> listObjectGroupByArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjectArticleFabriqueAvecsonType =new ArrayList<Object[]>();
	private List<StatistiqueEnVracConsommeLorsProductionPFClass> listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass =new ArrayList<StatistiqueEnVracConsommeLorsProductionPFClass>();
	List<String> listeNomCategorie =new ArrayList<String>();
	List<String> listeCodeMP =new ArrayList<String>();
	
	List<String> listeColumn =new ArrayList<String>();
	List<String> listeRows =new ArrayList<String>();
 
	private  JComboBox combomp = new JComboBox();
	
	private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur;
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	JComboBox soucategoriempcombo = new JComboBox();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, CategorieMp> Mapcategorie = new HashMap<>();
	JComboBox combodepot = new JComboBox();
	JDateChooser dateChooser = new JDateChooser();
	private JDateChooser dateChooserDu;
	JDateChooser dateChooser_1 = new JDateChooser();
	private JDateChooser dateChooserAu;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMagasin = new JComboBox();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<CategorieMp> listCategorie =new ArrayList<CategorieMp>();
	private ArticlesDAO ArticleDAO;
	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, String> mapTypeArticle= new HashMap<>();
	
	  JComboBox comboFournisseur = new JComboBox();
	  JComboBox comboPlusMoins = new JComboBox();
	  CategorieMpDAO categorieMpDAO;
	  JLabel totalmoins = new JLabel("0.00");
	  JLabel totalplus = new JLabel("0.00");
	  JLabel totalfabrique = new JLabel("0.00");
	  JLabel totalcharger = new JLabel("0.00");
	  JLabel differenceplusmoins = new JLabel("0.00");
	  ParametreDAO parametreDAO;
	  MachineDAO machineDAO;
	  JComboBox comboArticle = new JComboBox();
	  private Map< Date, Integer> mapIdListSisuation= new HashMap<>();
	
	  JComboBox comboCategorie = new JComboBox();
	  private JTextField txtCodeArticle = new JTextField();
	  String etatCategorie=etatCategorie=CODE_NON;;
	  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public StatistiqueEnVracUtiliserLorsDeLaProductionPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 825);
        try{
        	categorieMpDAO=new CategorieMpDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	ArticleDAO= new ArticlesDAOImpl();
        	listObjectArticleFabriqueAvecsonType=productionDAO.listeArticleFabriqueEtSonType();
        	  machineDAO=new MachineDAOImpl();
        	  parametreDAO=new ParametreDAOImpl();
        	  matierePremiereDAO=new MatierePremierDAOImpl();
        	  subcategoriempdao=new SubCategorieMPAOImpl();
        	  
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
        	imgAjouter= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
       	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Article","Categorie","Quantite Consomme","Pourcentage"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	

				  		     	
				  		     		tableStatistiqueEnvracConsomme = new JXTable();
				  		     		tableStatistiqueEnvracConsomme.setHorizontalScrollEnabled(true);
				  		     		tableStatistiqueEnvracConsomme.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableStatistiqueEnvracConsomme.getSelectedRow()!=-1)
				  		     				{

				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     		});
				  		     		tableStatistiqueEnvracConsomme.setShowVerticalLines(false);
				  		     		tableStatistiqueEnvracConsomme.setSelectionBackground(new Color(51, 204, 255));
				  		     		tableStatistiqueEnvracConsomme.setRowHeightEnabled(true);
				  		     		tableStatistiqueEnvracConsomme.setBackground(new Color(255, 255, 255));
				  		     		tableStatistiqueEnvracConsomme.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		tableStatistiqueEnvracConsomme.setColumnControlVisible(true);
				  		     		tableStatistiqueEnvracConsomme.setForeground(Color.BLACK);
				  		     		tableStatistiqueEnvracConsomme.setGridColor(new Color(0, 0, 255));
				  		     		tableStatistiqueEnvracConsomme.setAutoCreateRowSorter(true);
				  		     		//    table.setBounds(2, 27, 411, 198);
				  		     		tableStatistiqueEnvracConsomme.setRowHeight(20);
				  		      modeleMP =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Article","NBR En Vrac Consomme"," En Vrac","Pourcentage"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableStatistiqueEnvracConsomme.setModel(modeleMP); 
				  			 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableStatistiqueEnvracConsomme.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableStatistiqueEnvracConsomme);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(21, 183, 1467, 443);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1458, 120);
				  		     	add(layeredPane);
		
		JLabel label_2 = new JLabel("Depot :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 11, 55, 26);
		layeredPane.add(label_2);
		
		 combodepot = new JComboBox();
		 combodepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		
		 		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
 		     	  	 // comboGroupe = new JComboBox();
		     	 	comboMagasin.removeAllItems();
		     	 Depot depot=new Depot();
		     	 	//comboGroupe.addItem("");
		     	 	if(!combodepot.getSelectedItem().equals(""))
 		     	  	   	 depot =mapDepotSource.get(combodepot.getSelectedItem());
		  		       if(depot!=null)
		  		       {
		  		    	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);   
		  		       }
 		     	  		
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
		combodepot.setBounds(65, 11, 202, 27);
		layeredPane.add(combodepot);

				
				
				
				
				combodepot.addItem("");
			    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
		    		Depot depot = depotDAO.findByCode(utilisateur.getCodeDepot());
			     		combodepot.addItem(depot.getLibelle());
			     		mapDepotSource.put(depot.getLibelle(), depot);
		    }else {
		    	
		    	listDepot = depotDAO.findAll();	
			      int i=0;
			      	while(i<listDepot.size())
			      		{	
			      		Depot depot=listDepot.get(i);
			      			mapDepotSource.put(depot.getLibelle(), depot);
			      			combodepot.addItem(depot.getLibelle());
			      			i++;
			      		}
		    	
		    }
		  		
		  		 comboMagasin = new JComboBox();
		  		comboMagasin.setBounds(342, 11, 202, 27);
		  		layeredPane.add(comboMagasin);
		  		
		  		JLabel lblMagasin = new JLabel("Magasin :");
		  		lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblMagasin.setBounds(287, 11, 55, 26);
		  		layeredPane.add(lblMagasin);
		  		
		  		JLabel label = new JLabel("Du :");
		  		label.setBounds(564, 13, 45, 24);
		  		layeredPane.add(label);
		  		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		
		  		 dateChooserDu = new JDateChooser();
		  		 dateChooserDu.setBounds(598, 11, 163, 26);
		  		 layeredPane.add(dateChooserDu);
		  		 dateChooserDu.setLocale(Locale.FRANCE);
		  		 dateChooserDu.setDateFormatString("dd/MM/yyyy");
		  		 
		  		 JLabel label_1 = new JLabel("Au :");
		  		 label_1.setBounds(771, 13, 36, 24);
		  		 layeredPane.add(label_1);
		  		 label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		 
		  		  dateChooserAu = new JDateChooser();
		  		  dateChooserAu.setBounds(801, 11, 169, 26);
		  		  layeredPane.add(dateChooserAu);
		  		  dateChooserAu.setLocale(Locale.FRANCE);
		  		  dateChooserAu.setDateFormatString("dd/MM/yyyy");
			   
			
			CategorieMp categorieChaara=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_CHAARA);
			Mapcategorie.put(categorieChaara.getNom(), categorieChaara);
			
			CategorieMp categorieMkarkeb=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_MKARKAB);
			Mapcategorie.put(categorieMkarkeb.getNom(), categorieMkarkeb);
		
		listFournisseur=fournisseurMPDAO.findAll();
		
		JLabel lblCtaegorie = new JLabel("Ctaegorie :");
		lblCtaegorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCtaegorie.setBounds(998, 10, 70, 26);
		layeredPane.add(lblCtaegorie);
		
		 comboCategorie = new JComboBox();
		 comboCategorie.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		


		 		

		   		

		 		

	     	 	int i=0;
		   	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		   	
		   	 		
		   	 		 comboArticle.removeAllItems();
		   	 		 txtCodeArticle.setText("");
		   	 		mapArticle.clear();
		   	 	mapCodeArticle.clear();
		   	 mapTypeArticle.clear();
		   	 		 if(!comboCategorie.getSelectedItem().equals(""))
		   	 		 {
		   	 			comboArticle.addItem("");
		   	 			 
		   	 			while(i<listObjectArticleFabriqueAvecsonType.size())
		   	 		{
		   	 			Object[] object=listObjectArticleFabriqueAvecsonType.get(i);
		   	 			
		   	 			if(object[1].toString().equals(comboCategorie.getSelectedItem()))
		   	 			{
		   	 				
	               Articles articles=ArticleDAO.findByCode(object[0].toString())	;
		   	 			
		   	 			
		   	 			comboArticle.addItem(articles.getLiblle());
		   	 			mapArticle.put(articles.getLiblle(), articles);
		   	 			mapCodeArticle.put(articles.getCodeArticle(), articles);
		   	 			mapTypeArticle.put(articles.getCodeArticle(), object[1].toString());
		   	 			
		   	 			
		   	 			}
		   	 			
		   	 		
		   	 			
		   	 			i++;
		   	 			
		   	 		}
		   	 	
		   	 	comboArticle.setSelectedItem("");
		   	 			 
		   	 			 
		   	 			 
		   	 			 
		   	 			 
		   	 			 
		   	 		 }else
		   	 		 {
		   	 			 
		   	 			 
		   	 			comboArticle.addItem("");
		   	 			comboArticle.setSelectedItem(""); 
		   	 			 
		   	 		 }


		           
		   	 	 	}
		   	 	
				
				
				
				
			
			   		
			   	
			 		
			 		
			 	
			 		
			 		
			 	
		 		
		 		
		 		
		 		
		 	}
		 });
		comboCategorie.setBounds(1078, 10, 271, 27);
		layeredPane.add(comboCategorie);
		comboCategorie.addItem("");
	SubCategorieMp subCategorieMp=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		listCategorie=categorieMpDAO.findBySubcategorie(subCategorieMp.getId());
		for(int d=0;d<listCategorie.size();d++)
		{
			
		CategorieMp categorieMp=	listCategorie.get(d);
		comboCategorie.addItem(categorieMp.getNom());
			Mapcategorie.put(categorieMp.getNom(), categorieMp);
			
		}
		
		
	comboCategorie.setSelectedItem("");	
	
	JLabel lblArticle = new JLabel("Article :");
	lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblArticle.setBounds(241, 64, 70, 26);
	layeredPane.add(lblArticle);
	
	 comboArticle = new JComboBox();
	 comboArticle.addItemListener(new ItemListener() {
	 	public void itemStateChanged(ItemEvent e) {

	 		

	   		

	 		

     	 	
   	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
   	
   	 		
   	 		 Articles articles=mapArticle.get(comboArticle.getSelectedItem().toString());
   	 		 
   	 		if(articles!=null)
   	 		{
   	 			

	  			txtCodeArticle.setText (articles.getCodeArticle());	
	  		
   	 			
   	 		}else
   	 		{
   	 			txtCodeArticle.setText ("");	
   	 		}
           
   	 	 	}
   	 	
		
		
		
		
	
	   		
	   	
	 		
	 		
	 	
	 		
	 		
	 	}
	 });
	comboArticle.setBounds(287, 64, 371, 27);
	layeredPane.add(comboArticle);
	comboArticle.addItem("");
	JLabel lblCodeArticle = new JLabel("Code Article");
	lblCodeArticle.setBounds(10, 64, 67, 26);
	layeredPane.add(lblCodeArticle);
	lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
	txtCodeArticle = new JTextField();
	txtCodeArticle.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {


	  		if (e.getKeyCode() == e.VK_ENTER)
	  		{
	  			
	  		Articles articles=mapCodeArticle.get(txtCodeArticle.getText());
	  		if(articles!=null)
	  		{
	  			comboArticle.setSelectedItem(articles.getLiblle());	
	  		}
	  		
	  		}
		
			
			
		}
	});
	txtCodeArticle.setBounds(72, 64, 153, 26);
	layeredPane.add(txtCodeArticle);
	txtCodeArticle.setColumns(10);
	
	JButton btnAfficherStock = new JButton();
	btnAfficherStock.setBounds(580, 141, 93, 31);
	add(btnAfficherStock);
	btnAfficherStock.setIcon(imgRechercher);
	btnAfficherStock.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.clear();
			listObjectGroupByArticle.clear();
			listObject.clear();
			if(combodepot.getSelectedItem().equals("") && comboMagasin.getSelectedItem().equals("") && dateChooserDu.getDate() ==null && dateChooserAu==null && comboCategorie.getSelectedItem().equals(""))
			{
				
				
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Un Ou Plusieurs Champ SVP");
				return;
					
			}else
			{
				
				if(combodepot.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Depot SVP");
					return;
				}else if(comboMagasin.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
					return;
				}else
				{
					
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
					
					listeNomCategorie.clear();
					listeCodeMP.clear();
					
				String	dateDu=sdf.format(dateChooserDu.getDate()) ;
				String dateAu= sdf.format(dateChooserAu.getDate()) ;	
					
				String requete="";	
					
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				
				requete=requete+"   and  c.matierePremier.id in (select mp.id from MatierePremier mp where  mp.categorieMp.id in  ( select  ca.id from CategorieMp ca where ca.subCategorieMp.id=1)) and  p.statut='TerminÈ' and p.magasinPF.id='"+magasin.getId()+"' ";	
					
				if(!comboCategorie.getSelectedItem().equals(""))
				{
					CategorieMp categorieMp=Mapcategorie.get(comboCategorie.getSelectedItem());
					if(categorieMp!=null)
					{
						
						etatCategorie=CODE_OUI;
					
						
						requete="   and  c.matierePremier.id in (select mp.id from MatierePremier mp where  mp.categorieMp.id in  ( select  ca.id from CategorieMp ca where ca.id ='"+categorieMp.getId() +"'))  and  p.statut='TerminÈ' and p.magasinPF.id='"+magasin.getId()+"' ";	

					}else
					{
						etatCategorie=CODE_NON;
					}
					
					
					
				}else
				{
					etatCategorie=CODE_NON;
				}
				
				
				if(!comboArticle.getSelectedItem().equals(""))
				{
					Articles articles=mapArticle.get(comboArticle.getSelectedItem());
					if(articles!=null)
					{
						requete=requete+"  and p.articles.id='"+articles.getId()+"' ";	
					}
					
					
					
				}
					
					
				listObject=productionDAO.listeStatistiqueEnVracConsommeLorsDeLaproductionPF(requete);
				listObjectGroupByArticle=productionDAO.listeStatistiqueEnVracConsommeLorsDeLaproductionPFGroupByArticle(requete);
				
				BigDecimal TotalQuantiteConsomme=BigDecimal.ZERO;
				BigDecimal TotalPourcentage=BigDecimal.ZERO;
				
				
			for(int i=0;i<listObject.size();i++)
			{
				 Object[] object=listObject.get(i);	
				
				 
				 StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=new StatistiqueEnVracConsommeLorsProductionPFClass();	 
				 
				if(object[0]!=null)
				{
					
				Articles articles=ArticleDAO.findByCode(object[0].toString())	;
				
					if(articles!=null)
					{
						
						
						statistiqueEnVracConsommeLorsProductionPFClass.setArticles(articles);
						
						MatierePremier matierePremier=matierePremiereDAO.findByCode(object[1].toString());
						if(matierePremier!=null)
						{
							statistiqueEnVracConsommeLorsProductionPFClass.setMatierePremier(matierePremier);
						}
						
						
					
						statistiqueEnVracConsommeLorsProductionPFClass.setQuantiteConsomme(new BigDecimal(object[2].toString()) );
						
						statistiqueEnVracConsommeLorsProductionPFClass.setCategorie(matierePremier.getCategorieMp().getNom());
						TotalQuantiteConsomme=TotalQuantiteConsomme.add(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsomme());
						
						listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.add(statistiqueEnVracConsommeLorsProductionPFClass);
						boolean existe=false;
						boolean trouver=false;
					for(int t=0;t<listeNomCategorie.size();t++)
					{
						if(listeNomCategorie.get(t).toString().equals(matierePremier.getCategorieMp().getNom()))
						{
							existe=true;
						}
						
						
					}
					
					if(existe==false)
					{
						listeNomCategorie.add(matierePremier.getCategorieMp().getNom());
					}
					
					for(int j=0;j<listeCodeMP.size();j++)
					{
						
						if(listeCodeMP.get(j).toString().equals(matierePremier.getCode()))
						{
							trouver=true;
						}
						
					}
					
					if(trouver==false)
					{
						listeCodeMP.add(matierePremier.getCode());
					}
						
						
					}
					
					
				}
				
					
				
			}
				
				
			for(int j=0;j<listObjectGroupByArticle.size();j++)
			{
				
				Object[] object=listObjectGroupByArticle.get(j);	
				Articles articles=ArticleDAO.findByCode(object[0].toString())	;
				if(articles!=null)
				{
					
					for(int t=0;t<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();t++)
					{
						
						StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(t);
						if(statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getId()==articles.getId())
						{
							
							statistiqueEnVracConsommeLorsProductionPFClass.setNombreEnvracConsomme(Integer.valueOf(object[1].toString()));
							statistiqueEnVracConsommeLorsProductionPFClass.setPourcentage(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsomme().divide(new BigDecimal(object[2].toString()) , 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
							listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.set(t, statistiqueEnVracConsommeLorsProductionPFClass);
						}
						
						
						
						
						
						
						
						
					}

					
				}

				
				
				
			}
			
			BigDecimal quantiteCategorie=BigDecimal.ZERO;
			
			BigDecimal pourcentageCategorie=BigDecimal.ZERO;
			
			for(int d=0;d<listeNomCategorie.size();d++)
			{
				
				quantiteCategorie=BigDecimal.ZERO;
				pourcentageCategorie=BigDecimal.ZERO;
				for(int t=0;t<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();t++)
				{
					StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(t);
					
				 if(listeNomCategorie.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getCategorie()))
				 {
					 
					 quantiteCategorie=quantiteCategorie.add(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsomme()) ;
					 
					 
					 
				 }
					
				
					
					
				}
				
				if(TotalQuantiteConsomme.compareTo(BigDecimal.ZERO)!=0)
				{
					pourcentageCategorie=quantiteCategorie.divide(TotalQuantiteConsomme, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
				}
				
				for(int t=0;t<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();t++)
				{
					StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(t);
					
				 if(listeNomCategorie.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getCategorie()))
				 {
					 System.out.println(listeNomCategorie.get(d).toString() +" "+pourcentageCategorie);
					 statistiqueEnVracConsommeLorsProductionPFClass.setPourcentage_categorie(pourcentageCategorie);
					 listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.set(t, statistiqueEnVracConsommeLorsProductionPFClass);
					 
					 
				 }
					
				
					
					
				}
				
				
				
				
				
				
			}
			
			BigDecimal quantiteMP=BigDecimal.ZERO;
			BigDecimal pourcentageMP=BigDecimal.ZERO;
			
			for(int d=0;d<listeCodeMP.size();d++)
			{
				
				quantiteMP=BigDecimal.ZERO;
				pourcentageMP=BigDecimal.ZERO;
				for(int t=0;t<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();t++)
				{
					StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(t);
					
				 if(listeCodeMP.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getCode()))
				 {
					 
					 quantiteMP=quantiteMP.add(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsomme()) ;
					 
					 
					 
				 }
					
				
					
					
				}
				
				if(TotalQuantiteConsomme.compareTo(BigDecimal.ZERO)!=0)
				{
					pourcentageMP=quantiteMP.divide(TotalQuantiteConsomme, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
				}
				
				for(int t=0;t<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();t++)
				{
					StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(t);
					
				 if(listeCodeMP.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getCode()))
				 {
					 System.out.println(listeCodeMP.get(d).toString() +" "+pourcentageMP);
					 statistiqueEnVracConsommeLorsProductionPFClass.setPourcentage_mp(pourcentageMP);
					 listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.set(t, statistiqueEnVracConsommeLorsProductionPFClass);
					 
					 
				 }
					
				
					
					
				}
				
				
				
				
				
				
			}
			listeColumn.clear();
			listeRows.clear();
			
			
			listeColumn.add("Articles");
			
			
			
			boolean existcolumn=false;
			boolean existrows=false;
			
			for(int i=0;i<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();i++)
			{
				 existcolumn=false;
				 existrows=false;
				StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(i);
				
				for(int j=0;j<listeColumn.size();j++)
				{
					if(listeColumn.get(j).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getNom()))
					{
						
						existcolumn=true;
					}
					
					
					
					
					
				}
				
				for(int j=0;j<listeRows.size();j++)
				{
					if(listeRows.get(j).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getLiblle()))
					{
						
						existrows=true;
					}
					
					
					
					
					
				}
				
				if(existcolumn==false)
				{
					
					listeColumn.add(statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getNom());
				}
				
				if(existrows==false)
				{
					
					listeRows.add(statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getLiblle());
				}
				
				
				
				
				
				
			}
			
			listeColumn.add("Total");
			 
			
			
			
			
			
			
			afficher_tableMP(listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass);
					
	
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		
		
		}
	});
	btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
	JButton btnInitialiser = new JButton();
	btnInitialiser.setBounds(697, 141, 93, 31);
	add(btnInitialiser);
	btnInitialiser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
			comboArticle.setSelectedItem("");
			txtCodeArticle.setText("");
			combodepot.setSelectedItem("");
			comboMagasin.setSelectedItem("");
			comboFournisseur.setSelectedItem("");
			comboPlusMoins.setSelectedItem("");
			
			dateChooserDu.setDate(null);
			dateChooserAu.setDate(null);
			
		}
	});
	btnInitialiser.setText("Initialiser");
	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
	 int i=0;
		while(i<listObjectArticleFabriqueAvecsonType.size())
		{
			Object[] object=listObjectArticleFabriqueAvecsonType.get(i);	
			Articles articles=ArticleDAO.findByCode(object[0].toString())	;
			
			
			comboArticle.addItem(articles.getLiblle());
			mapArticle.put(articles.getLiblle(), articles);
			mapCodeArticle.put(articles.getCodeArticle(), articles);
			mapTypeArticle.put(articles.getCodeArticle(), object[1].toString());
			
			i++;
			
		}
	
	comboArticle.setSelectedItem("");
	
	JButton btnExporterExcel = new JButton();
	btnExporterExcel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			

			

			


			 
				
			if(tableStatistiqueEnvracConsomme.getRowCount()!=0)
			{
				
				
				 
				
				
				
				
				String titre="Situation EnVrac Utiliser Lors De La production ";
	    		String titrefeuilleexcel="Situation EnVrac Utiliser Lors De La production ";
	    		ExporterTableVersExcel.tabletoexcelStatistiqueEnVracUtiliserLorsDeLaProduction(tableStatistiqueEnvracConsomme, titre,titrefeuilleexcel);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
				
				
			}
		
	
	
			
			
			
			
			
			
		
			
			
			
			
			
		
			
		
			
			
			
		
			
			
			
			
		}
	});
	btnExporterExcel.setText("Exporter Excel");
	btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	btnExporterExcel.setBounds(677, 643, 135, 40);
	btnExporterExcel.setIcon(imgExcel);
	add(btnExporterExcel);
	
	
	
	
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			 
				 
				Object []ligne={detailManqueDechetFournisseur.getManquedechetfournisseur().getNumOF(),detailManqueDechetFournisseur.getMatierePremier().getCode(),detailManqueDechetFournisseur.getMatierePremier().getNom(),detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur(),detailManqueDechetFournisseur.getQuantiteManque(),detailManqueDechetFournisseur.getQuantiteDechet()};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	
void afficher_tableMP(List<StatistiqueEnVracConsommeLorsProductionPFClass> listStatistiqueEnVracConsommeLorsProductionPFClass)
	{
	intialiserTableau2();
	/*
	int i=0;
	 
	while(i<listStatistiqueEnVracConsommeLorsProductionPFClass.size())
	{	
		
		
		StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnVracConsommeLorsProductionPFClass.get(i);
	
			
				 Object []ligne={statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getCodeArticle() , statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getLiblle() , statistiqueEnVracConsommeLorsProductionPFClass.getNombreEnvracConsomme(),statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getNom(),statistiqueEnVracConsommeLorsProductionPFClass.getPourcentage()};
				 modeleMP.addRow(ligne);
			 
				
		
		
		
		i++;
	}
	*/
	
	
	for(int i=0;i<listeRows.size();i++)
	{
		
		Vector<Object> data = new Vector<Object>();	
		
		data.add(listeRows.get(i));
		for(int t=1;t<listeColumn.size();t++)
		{
			data.add("-");
			
			
		}
		
		 modeleMP.addRow(data);
		
		
		
		
		
		
	}
	

	
	
	
	BigDecimal total=BigDecimal.ZERO;
	
	

	
	
	for(int y=0;y<tableStatistiqueEnvracConsomme.getRowCount();y++)
	{
	
		total=BigDecimal.ZERO;
		
		for(int d=0;d<tableStatistiqueEnvracConsomme.getColumnCount();d++)
		{
			
			
		for(int p=0;p<listStatistiqueEnVracConsommeLorsProductionPFClass.size();p++)
		{
			
			StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnVracConsommeLorsProductionPFClass.get(p);
			
			
			if(statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getNom().equals(tableStatistiqueEnvracConsomme.getColumnName(d).toString()))
			{
				
				if(statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getLiblle().equals(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()))
				{
					
					tableStatistiqueEnvracConsomme.setValueAt(statistiqueEnVracConsommeLorsProductionPFClass.getPourcentage()+" % ", y, d);
					
					total=total.add(statistiqueEnVracConsommeLorsProductionPFClass.getPourcentage());
					
					
				}
				
				
				
			}
			
			
		}
			
			
			
			
			
			
		}
		
		tableStatistiqueEnvracConsomme.setValueAt(total +" %", y, tableStatistiqueEnvracConsomme.getColumnCount()-1);
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
		
			
		
	}









void intialiserTableau2(){
	
	
	
	modeleMP = new DefaultTableModel();
	
	
for(int i=0;i<listeColumn.size();i++)
{
	modeleMP.addColumn(listeColumn.get(i));
	
}
	

tableStatistiqueEnvracConsomme.setModel(modeleMP);
	
	
 
		
}






public void Vider()
{
	
	combodepot.setSelectedItem("");
	comboMagasin.setSelectedItem("");
	dateChooserDu.setDate(new Date());
	dateChooserAu.setDate(new Date());
	comboCategorie.setSelectedItem("");
	
	
	
}
}

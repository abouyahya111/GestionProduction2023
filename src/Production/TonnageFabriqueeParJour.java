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
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import util.ComparateurSituationEnvracParRegion;
import util.ComparateurSituationProductionTotalParArticle;
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


public class TonnageFabriqueeParJour extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableStatistiqueEnvracConsomme;
	
	
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
	private List<SituationProductionTotalParArticlePFClass> listSituationProductionTotalParArticlePFClass =new ArrayList<SituationProductionTotalParArticlePFClass>();
 
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
	  private Map< String, String> mapTypeArticle= new HashMap<>();
	  private List<Object[]> listObjectArticleFabriqueAvecsonType =new ArrayList<Object[]>();
	  JLabel LabelTotalQuantiteConsomme = new JLabel("");
	  JLabel LabelTotalPourcentage = new JLabel("");
	  private ImageIcon imgExcel;
	  List<String> listeNomCategorie =new ArrayList<String>();
	  List<String> listeCodeArticle =new ArrayList<String>();
	  String etatCategorie=etatCategorie=CODE_NON;;
	  BigDecimal TotalQuantiteFabriquee=BigDecimal.ZERO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TonnageFabriqueeParJour() {
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
        	  listArticle=ArticleDAO.findAll();
        	  machineDAO=new MachineDAOImpl();
        	  parametreDAO=new ParametreDAOImpl();
        	  matierePremiereDAO=new MatierePremierDAOImpl();
        	  subcategoriempdao=new SubCategorieMPAOImpl();
        	  listObjectArticleFabriqueAvecsonType=productionDAO.listeArticleFabriqueEtSonType();
        	  
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					  		     			"Article","Categorie","Quantite FABRIQU�E","Pourcentage"
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
				  			     			"Date Production","Total fabriquee"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false 
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
		label_2.setBounds(156, 55, 55, 26);
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
		combodepot.setBounds(211, 55, 202, 27);
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
		  		comboMagasin.setBounds(488, 55, 202, 27);
		  		layeredPane.add(comboMagasin);
		  		
		  		JLabel lblMagasin = new JLabel("Magasin :");
		  		lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblMagasin.setBounds(433, 55, 55, 26);
		  		layeredPane.add(lblMagasin);
		  		
		  		JLabel label = new JLabel("Du :");
		  		label.setBounds(710, 57, 45, 24);
		  		layeredPane.add(label);
		  		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		
		  		 dateChooserDu = new JDateChooser();
		  		 dateChooserDu.setBounds(744, 55, 163, 26);
		  		 layeredPane.add(dateChooserDu);
		  		 dateChooserDu.setLocale(Locale.FRANCE);
		  		 dateChooserDu.setDateFormatString("dd/MM/yyyy");
		  		 
		  		 JLabel label_1 = new JLabel("Au :");
		  		 label_1.setBounds(917, 57, 36, 24);
		  		 layeredPane.add(label_1);
		  		 label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		 
		  		  dateChooserAu = new JDateChooser();
		  		  dateChooserAu.setBounds(947, 55, 169, 26);
		  		  layeredPane.add(dateChooserAu);
		  		  dateChooserAu.setLocale(Locale.FRANCE);
		  		  dateChooserAu.setDateFormatString("dd/MM/yyyy");
			   
			
		 
	
	JButton btnAfficherStock = new JButton();
	btnAfficherStock.setBounds(580, 141, 93, 31);
	add(btnAfficherStock);
	btnAfficherStock.setIcon(imgRechercher);
	btnAfficherStock.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 
			if(combodepot.getSelectedItem().equals("") && comboMagasin.getSelectedItem().equals("") && dateChooserDu.getDate() ==null && dateChooserAu==null && comboCategorie.getSelectedItem().equals(""))
			{
				
				
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Un Ou Plusieurs Champ SVP");
				return;
					
			}else
			{
				
				
				String	dateDu="" ; 
				String dateAu=""; 
				
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
					
					
					
					dateDu= sdf.format(dateChooserDu.getDate()) ;
					dateAu= sdf.format(dateChooserAu.getDate()) ;
					
				String requete="";	
					
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					
					
					
					if(!dateDu.equals("") && !dateAu.equals(""))
					{
						requete=requete+" and p.date between '"+dateDu+"' and '"+dateAu+"' ";
					}
					
					
					if(!dateDu.equals("") && dateAu.equals(""))
					{
						requete=requete+" and p.date ='"+dateDu+"' ";
					}
					
					if(dateDu.equals("") && !dateAu.equals(""))
					{
						requete=requete+" and p.date ='"+dateAu+"' ";
					}
					
				 
					
				
				requete=requete+"   and p.magasinPF.id='"+magasin.getId()+"' ";	
					
				
				
				
			
					
					
				listObject=productionDAO.listeTonnageProductionparJour (requete);
				
		 
				
				afficher_tableMP(listObject);
			 
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				
				
				
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
	
	JLabel lblTotalQuantiteFabrique = new JLabel("TOTAL QUANTITE FABRIQUE  :");
	lblTotalQuantiteFabrique.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteFabrique.setBounds(538, 639, 275, 26);
	add(lblTotalQuantiteFabrique);
	
	 LabelTotalQuantiteConsomme = new JLabel("");
	LabelTotalQuantiteConsomme.setForeground(Color.RED);
	LabelTotalQuantiteConsomme.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteConsomme.setBounds(807, 639, 209, 26);
	add(LabelTotalQuantiteConsomme);
	
	JButton button = new JButton("Exporter Excel");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			

			


			 	
				
			if(tableStatistiqueEnvracConsomme.getRowCount()!=0)
			{
			 
				
				
				
				
				String titre="Tonnage Production Par Jour ";
	    		String titrefeuilleexcel="Tonnage Production Par Jour ";
	    		ExporterTableVersExcel.tabletoexcelTonnageproductionParJour  (tableStatistiqueEnvracConsomme, titre,titrefeuilleexcel, TotalQuantiteFabriquee);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
				
				
			}
		
	
	
			
			
			
			
			
			
		
			
			
			
			
			
		
			
		
			
			
			
		}
	});
	button.setBounds(918, 700, 156, 40);
	button.setIcon(imgExcel);
	add(button);
	
	
	
	
	}
	
	
 
	
	
void afficher_tableMP(List<Object[]> listeTonnageProductionParJour)
	{
	
	
	intialiserTableau2();
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	TotalQuantiteFabriquee=BigDecimal.ZERO;
	
	int i=0;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 
	
	
	 
	 
	while(i<listeTonnageProductionParJour.size())
	{	
		 Object[] object=listeTonnageProductionParJour.get(i);	
		 if(object[0]!=null)
			{
			 
			 Date date=(Date)object[0];
			 
			 
			 TotalQuantiteFabriquee=TotalQuantiteFabriquee.add(new BigDecimal(object[1].toString()) );
						 Object []ligne={sdf.format(date),new BigDecimal(object[1].toString()) };
						 modeleMP.addRow(ligne);
			}

			 
				
		
		
		
		i++;
	}
	
	
	LabelTotalQuantiteConsomme.setText(dfDecimal.format(TotalQuantiteFabriquee.setScale(2, RoundingMode.HALF_UP))+"");
	 
		
		
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date Production","Total fabriquee"

		     	
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false 
		     			
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableStatistiqueEnvracConsomme.setModel(modeleMP); 
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(1).setPreferredWidth(60);
	 
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

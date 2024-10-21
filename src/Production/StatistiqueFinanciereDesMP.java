package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.google.common.collect.Lists;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.entity.Articles;
import dao.entity.CompteStockMP;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EtatCoutProduction;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTabbedPane;


public class StatistiqueFinanciereDesMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	

	
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<Production> listProductions=new ArrayList<Production>();
	 List<DetailProdRes> listEmployeGesnerique=new ArrayList<DetailProdRes>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<EtatCoutProduction> listEtatCoutProduction =new ArrayList<EtatCoutProduction>();
	private List<Object[]> listObjetCoutMPParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetQuantiteReelMPParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProductionParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProdResParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProdGenParArticle =new ArrayList<Object[]>();
	 private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private CoutMPDAO coutMPDAO;
	private DepotDAO depotdao;
	List < Articles> listArticles = new ArrayList<Articles>();
	 JComboBox combocodearticle = new JComboBox();
	 JComboBox comboBoxArticle = new JComboBox();
	 private Map< String, Articles> mapCodeArticle= new HashMap<>();
		private Map< String, Articles> mapLibelleAricle = new HashMap<>();
		private ArticlesDAO articlesDAO;
		private JXTable table;
		 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
		 JLabel labelTotalCoutMp = new JLabel("");
		 JLabel labeltotalCoutGenerique = new JLabel("");
		 JLabel labelTotalCoutProduction = new JLabel("");
		  JLabel labelTotalCoutEmballage = new JLabel("");
		  JLabel labelTotal = new JLabel("");
		  JLabel labelTotalrealiser = new JLabel("");
		  JLabel labelCout = new JLabel("");
		  private ImageIcon imgExcel;
		  
		  
		  private DetailProdResDAO detailProdResDAO;
			private DetailProductionDAO detailProductionDAO;
			private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
			 private List<StatistiqueFinanciaireMP> listStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();

		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public StatistiqueFinanciereDesMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
        	depotdao= new DepotDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	 CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	 detailProdResDAO=new DetailProdResDAOImpl();
       	  detailProductionDAO=new DetailProductionDAOImpl();
       	statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Num OF","Date", "Depot","Article","Statut"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
		
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
		     		k++;
		     		
		     	}
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
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
		  titledSeparator_1.setTitle("Statistique MP");
		  titledSeparator_1.setBackground(Color.RED);
		  titledSeparator_1.setBounds(9, 76, 1120, 30);
		  add(titledSeparator_1);
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(9, 117, 1560, 604);
		  add(scrollPane);
		  
		  table = new JXTable();
		  table.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  			"Operation",	"Alimentation", "Stock Emballage", "Stock En Vrac", "Cout Production", "Cout Employee","Cout Reception","Cout Sortie", "Cout Vente","Cout PF", "Ecart"
		  	}
		  ));
		  table.setColumnSelectionAllowed(true);
		  scrollPane.setViewportView(table);
		  
		   labelTotalCoutMp = new JLabel("");
		  labelTotalCoutMp.setForeground(Color.RED);
		  labelTotalCoutMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutMp.setBounds(10, 743, 550, 24);
		  add(labelTotalCoutMp);
		  
		   labelTotalCoutProduction = new JLabel("");
		  labelTotalCoutProduction.setForeground(Color.RED);
		  labelTotalCoutProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutProduction.setBounds(10, 814, 550, 24);
		  add(labelTotalCoutProduction);
		  
		   labelTotalCoutEmballage = new JLabel("");
		  labelTotalCoutEmballage.setForeground(Color.RED);
		  labelTotalCoutEmballage.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutEmballage.setBounds(10, 849, 550, 24);
		  add(labelTotalCoutEmballage);
		  
		   labelTotal = new JLabel("");
		  labelTotal.setForeground(Color.RED);
		  labelTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotal.setBounds(10, 885, 550, 24);
		  add(labelTotal);
		  
		   labelTotalrealiser = new JLabel("");
		  labelTotalrealiser.setForeground(Color.RED);
		  labelTotalrealiser.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalrealiser.setBounds(10, 924, 550, 24);
		  add(labelTotalrealiser);
		  
		  JButton button_1 = new JButton("Exporter Excel");
		  button_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		

		  		
		  		

				


			 	
				
				if(table.getRowCount()!=0)
				{
				 
					String titre="Statistique Financiere MP";
		    		String titrefeuilleexcel="Statistique Financiere MP";
		    		ExporterTableVersExcel.tabletoexcelStatistiqueFinanciereMP (table, titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			
		
		 
		  	
		  		
		  	}
		  });
		  button_1.setBounds(905, 760, 156, 34);
		  button_1.setIcon(imgExcel);
		  add(button_1);
		  
		   labelCout = new JLabel("");
		  labelCout.setForeground(Color.RED);
		  labelCout.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelCout.setBounds(9, 969, 550, 24);
		  add(labelCout);
		
		
		  listStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
		  	afficher_tableMP(listStatistiqueFinanciereMP);
		   		     
				  		 
	}
	
void CalculerCoutProductionParArticle()
{
}

	void ChargerComboArticle()
	{
		 comboBoxArticle.removeAllItems();
		 combocodearticle.removeAllItems();
		 combocodearticle.addItem("");
		 comboBoxArticle.addItem("");
		   listArticles=articlesDAO.findAll();
	        int i=0;
		      	while(i<listArticles.size())
		      		{	
		      			Articles article=listArticles.get(i);
		      			mapCodeArticle.put(article.getCodeArticle(), article);
		      			mapLibelleAricle.put( article.getLiblle(),article);
		      			
		      			comboBoxArticle.addItem(article.getLiblle());
		      			combocodearticle.addItem(article.getCodeArticle());
		      			i++;
		      		}
			 
		 
		 
		 
	}
	
	
	
	void afficher_tableMP(List<StatistiqueFinanciaireMP> listStatistiqueFinanciere)
	{
	intialiserTableau();
		 
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
		int i=0;
		BigDecimal ecart=BigDecimal.ZERO;
		
		
		
		while(i<listStatistiqueFinanciere.size())
		{	
			ecart=BigDecimal.ZERO;
			StatistiqueFinanciaireMP statistiqueFinanciaireMP=listStatistiqueFinanciere.get(i);
		
		
			
			
			ecart=statistiqueFinanciaireMP.getAlimentation().subtract(statistiqueFinanciaireMP.getStockEmballage()).subtract(statistiqueFinanciaireMP.getStockEnVrac()).subtract(statistiqueFinanciaireMP.getCoutProduction().add(statistiqueFinanciaireMP.getCoutProductionCarton().add(statistiqueFinanciaireMP.getCoutVente()))).add(statistiqueFinanciaireMP.getCoutEmployeeProduction().add(statistiqueFinanciaireMP.getCoutEmployeeCarton()).add(statistiqueFinanciaireMP.getCoutReception())).subtract(statistiqueFinanciaireMP.getCOUTPF().add(statistiqueFinanciaireMP.getCoutSortie()));
			
			

					 Object []ligne={statistiqueFinanciaireMP.getEtat(),statistiqueFinanciaireMP.getAlimentation().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getStockEmballage().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getStockEnVrac().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutProduction().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutEmployeeProduction().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutReception().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutProductionCarton().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutEmployeeCarton().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutSortie().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCoutVente().setScale(2, RoundingMode.HALF_UP),statistiqueFinanciaireMP.getCOUTPF().setScale(2, RoundingMode.HALF_UP),ecart.setScale(2, RoundingMode.HALF_UP)};
					 modeleMP.addRow(ligne);
					
							 
			
			
			
			i++;
		}
		
	
			
		
	}
	
	
	void intialiserTableau(){
		 modeleMP =new DefaultTableModel(
			     	new Object[][] {
			     	},
			     	new String[] {
			     			"Operation",	"Alimentation", "Stock Emballage", "Stock En Vrac", "Cout Production", "Cout Employee","Cout Reception","Cout Production carton","Cout Employee carton","Cout Sortie","Cout Vente", "Cout PF", "Ecart"
			     			}
			     ) {
			     	boolean[] columnEditables = new boolean[] {
			     			false,false,false,false,false,false,false,false,false,false,false,false,false  
			     	};
			     	public boolean isCellEditable(int row, int column) {
			     		return columnEditables[column];
			     	}
			     };
			     
			     table.setModel(modeleMP); 
			     table.getColumnModel().getColumn(0).setPreferredWidth(60);
			     table.getColumnModel().getColumn(1).setPreferredWidth(120);
			     table.getColumnModel().getColumn(2).setPreferredWidth(60);
			     table.getColumnModel().getColumn(3).setPreferredWidth(60);
			     table.getColumnModel().getColumn(4).setPreferredWidth(60);
			     table.getColumnModel().getColumn(5).setPreferredWidth(60);
			     table.getColumnModel().getColumn(6).setPreferredWidth(60);
			     table.getColumnModel().getColumn(7).setPreferredWidth(60);
			     table.getColumnModel().getColumn(8).setPreferredWidth(60);
			     table.getColumnModel().getColumn(9).setPreferredWidth(60);
			     table.getColumnModel().getColumn(10).setPreferredWidth(60);
			     table.getColumnModel().getColumn(11).setPreferredWidth(60);
			     table.getColumnModel().getColumn(12).setPreferredWidth(60);
			     
			     
			 
	}
}

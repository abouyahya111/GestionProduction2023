package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailChargeVariableDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailChargeVariableDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.ChargeProduction;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;
import dao.entity.FicheEmploye;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.JComboBox;


public class ProductionArticleParMois extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 Modelchargefix;
	
	private DefaultTableModel	 Modelchargevariable;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<dao.entity.ProductionArticleParMois> listProductionArticleParMois=new ArrayList<dao.entity.ProductionArticleParMois>();
	private List<Production> listProduction=new ArrayList<Production>();
	private List<DetailCoutProduction> listChargeFix=new ArrayList<DetailCoutProduction>();
	private List<DetailCoutProduction> listChargeVariable=new ArrayList<DetailCoutProduction>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private List<DetailCoutProduction> listChargeFixMP=new ArrayList<DetailCoutProduction>();
	private List<DetailCoutProduction> listChargeVariableMP=new ArrayList<DetailCoutProduction>();
	private DetailCoutProductionDAO detailcoutProductiondao;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private DetailChargeVariableDAO detailchargevariabledao;
	private ProductionDAO productionDAO;
	private ChargeProductionDAO chargeproductionDAO;
	private DepotDAO depotDAO;
	private Utilisateur utilisateur;
	private JTable tableArticleParMois;
	BigDecimal quanititetotal=BigDecimal.ZERO;
	BigDecimal couttotal=BigDecimal.ZERO;
	BigDecimal couttotalchargefix=BigDecimal.ZERO;
	BigDecimal couttotalchargevariable=BigDecimal.ZERO;
	BigDecimal sommecouts=BigDecimal.ZERO;
	BigDecimal coutunitaire=BigDecimal.ZERO;
	BigDecimal coutmp=BigDecimal.ZERO;
	BigDecimal coutemploye=BigDecimal.ZERO;
	BigDecimal coutdechet=BigDecimal.ZERO;
	BigDecimal pourcentagecoutmp=BigDecimal.ZERO;
	BigDecimal pourcentagecoutemploye=BigDecimal.ZERO;
	BigDecimal pourcentagecoutdechet=BigDecimal.ZERO;
	BigDecimal pourcentagefix=BigDecimal.ZERO;
	BigDecimal pourcentagevariable=BigDecimal.ZERO;
	BigDecimal coutunitairemp=BigDecimal.ZERO;
	BigDecimal coutunitaireemploye=BigDecimal.ZERO;
	BigDecimal coutunitairedechet=BigDecimal.ZERO;
	BigDecimal pourcentagedhmp=BigDecimal.ZERO;
	BigDecimal pourcentagedhemploye=BigDecimal.ZERO;
	BigDecimal pourcentagedhdechet=BigDecimal.ZERO;
	BigDecimal coutunitairempfix=BigDecimal.ZERO;
	BigDecimal coutunitairempvariable=BigDecimal.ZERO;
	BigDecimal pourcentagedhmpfix=BigDecimal.ZERO;
	BigDecimal pourcentagedhmpvariable=BigDecimal.ZERO;
	BigDecimal pourcentagecouttotal=BigDecimal.ZERO;
	BigDecimal porcentagequantitechargefix=BigDecimal.ZERO;
	BigDecimal porcentagequantitechargevariable=BigDecimal.ZERO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ProductionArticleParMois() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1346, 1026);
        try{
        	
        	chargeproductionDAO=new ChargeProductionDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	detailcoutProductiondao= new DetailCoutProductionDAOImpl();
        	detailchargevariabledao=new DetailChargeVariableDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	listProduction=productionDAO.listeProductionGroupByArticle();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
		int k=0;
	      
      	if(utilisateur.getNom().equals("admin"))
      	{
      		
      		listDepot=depotDAO.findAll();
      		
      		while(k<listDepot.size())
      		{
      			
      			Depot depot=listDepot.get(k);
      			mapDepot.put(depot.getLibelle(), depot);
      			comboDepot.addItem(depot.getLibelle());
      			k++;
      		}
      		
      		
      	}else
      	{
      		
      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      			mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
		
		JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_3.getLayout();
		gridBagLayout_2.rowWeights = new double[]{0.0};
		gridBagLayout_2.rowHeights = new int[]{0};
		gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
		titledSeparator_3.setTitle("Cout Charge Fixe");
		titledSeparator_3.setBackground(Color.RED);
		titledSeparator_3.setBounds(11, 48, 1148, 30);
		add(titledSeparator_3);
		 Modelchargefix =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     			
	  		     	}
	  		  
				   ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     		 false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		 Modelchargevariable =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     			
	  		     	}
	  		  
				   ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     		 false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 110, 1307, 155);
		add(scrollPane);
		
		tableArticleParMois = new JTable();
		tableArticleParMois.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code","Libelle","Mois 1 " ,"Mois 2","Mois 3","Mois 4","Mois 5 " ,"Mois 6","Mois 7","Mois 8","Mois 9 " ,"Mois 10","Mois 11","Mois 12"
			}
		));
		tableArticleParMois.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableArticleParMois);
		
		JButton btnCoutProduction = new JButton("Cout Production");
		btnCoutProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCoutProduction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCoutProduction.setIcon(imgImprimer);
		btnCoutProduction.setBounds(300, 971, 133, 23);
		add(btnCoutProduction);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setBounds(1208, 48, 31, 31);
		add(btnAfficherStock);
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for(int i=0 ; i<listProduction.size();i++)
			{
				Production production=listProduction.get(i);
			
					dao.entity.ProductionArticleParMois articleParMois=new dao.entity.ProductionArticleParMois();
					articleParMois.setArticle(production.getArticles()); 
					articleParMois.setQtteMois1(BigDecimal.ZERO);
					articleParMois.setQtteMois2(BigDecimal.ZERO);
					articleParMois.setQtteMois3(BigDecimal.ZERO);
					articleParMois.setQtteMois4(BigDecimal.ZERO);
					articleParMois.setQtteMois5(BigDecimal.ZERO);
					articleParMois.setQtteMois6(BigDecimal.ZERO);
					articleParMois.setQtteMois7(BigDecimal.ZERO);
					articleParMois.setQtteMois8(BigDecimal.ZERO);
					articleParMois.setQtteMois9(BigDecimal.ZERO);
					articleParMois.setQtteMois10(BigDecimal.ZERO);
					articleParMois.setQtteMois11(BigDecimal.ZERO);
					articleParMois.setQtteMois12(BigDecimal.ZERO);
					
					listProductionArticleParMois.add(articleParMois);
					
				
					
			
				
			}
				
				
			for(int i=1; i<13 ; i++)
			{
				
				listeObject=productionDAO.listeProductionParArticleParMois(i);
				
				for(int j=0; j<listeObject.size() ; j++)
				{
					
					 Object[] object=listeObject.get(j);	
					
				for(int k=0;k<listProductionArticleParMois.size();k++)	
				{
					
				if(listProductionArticleParMois.get(k).getArticle().getCodeArticle().equals(object[0].toString()))	
				{
					
					if(i==1)
					{
						
						listProductionArticleParMois.get(k).setQtteMois1(new BigDecimal(object[2].toString()) );
						
					}else if(i==2)
					{
						listProductionArticleParMois.get(k).setQtteMois2(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==3)
					{
						listProductionArticleParMois.get(k).setQtteMois3(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==4)
					{
						listProductionArticleParMois.get(k).setQtteMois4(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==5)
					{
						listProductionArticleParMois.get(k).setQtteMois5(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==6)
					{
						listProductionArticleParMois.get(k).setQtteMois6(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==7)
					{
						listProductionArticleParMois.get(k).setQtteMois7(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==8)
					{
						listProductionArticleParMois.get(k).setQtteMois8(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==9)
					{
						listProductionArticleParMois.get(k).setQtteMois9(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==10)
					{
						listProductionArticleParMois.get(k).setQtteMois10(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==11)
					{
						listProductionArticleParMois.get(k).setQtteMois11(new BigDecimal(object[2].toString()) );	
						
						
					}else if(i==12)
					{
						listProductionArticleParMois.get(k).setQtteMois12(new BigDecimal(object[2].toString()) );	
						
						
					}
					
					
				}
					
				}
					 
				
				}
				
				
			}
			intialiserTableChargeFix();
			
			for(int d=0;d<listProductionArticleParMois.size();d++)
			{
				
				
				Object []ligne={listProductionArticleParMois.get(d).getArticle().getCodeArticle() , listProductionArticleParMois.get(d).getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois1()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois2()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois3()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois4()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois5()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois6()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois7()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois8()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois9()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois10()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois11()),NumberFormat.getNumberInstance(Locale.FRANCE).format(listProductionArticleParMois.get(d).getQtteMois12())};

				Modelchargefix.addRow( ligne);
				
				
				
			}
			
				
				
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
	
				  		     
				  		 
	}
	
	
	void	intialiserTableChargeFix(){
		 Modelchargefix =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Libelle","Mois 1 " ,"Mois 2","Mois 3","Mois 4","Mois 5 " ,"Mois 6","Mois 7","Mois 8","Mois 9 " ,"Mois 10","Mois 11","Mois 12"
	  		     	}
	  		     ) ;
	  		     
		 tableArticleParMois.setModel(Modelchargefix); 
		 tableArticleParMois.getColumnModel().getColumn(0).setPreferredWidth(30);
		 tableArticleParMois.getColumnModel().getColumn(1).setPreferredWidth(160);
		 tableArticleParMois.getColumnModel().getColumn(2).setPreferredWidth(40);
		 tableArticleParMois.getColumnModel().getColumn(3).setPreferredWidth(40);
		 tableArticleParMois.getColumnModel().getColumn(4).setPreferredWidth(40);
	  		
	}
	

	
	
	
}

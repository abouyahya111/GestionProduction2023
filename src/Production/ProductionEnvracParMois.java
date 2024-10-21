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
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailChargeVariableDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailChargeVariableDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.ChargeProduction;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailTransferStockMP;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.ProductionArticleParMois;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.JComboBox;
import java.util.Locale;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ProductionEnvracParMois extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 Modelchargefix;
	
	private DefaultTableModel	 Modelchargevariable;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgExcel;
	private ImageIcon imgRechercher;
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<dao.entity.ProductionArticleParMois> listProductionArticleParMois=new ArrayList<dao.entity.ProductionArticleParMois>();
	private List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	
	private List<DetailCoutProduction> listChargeVariable=new ArrayList<DetailCoutProduction>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private List<DetailCoutProduction> listChargeFixMP=new ArrayList<DetailCoutProduction>();
	private List<DetailCoutProduction> listChargeVariableMP=new ArrayList<DetailCoutProduction>();
	private DetailCoutProductionDAO detailcoutProductiondao;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private List <Object[]> listeObjectInitialEnVrac=new ArrayList<Object[]>();
	private DetailChargeVariableDAO detailchargevariabledao;
	private DetailTransferMPDAO detailTransferMPDAO;
	private ProductionDAO productionDAO;
	private ChargeProductionDAO chargeproductionDAO;
	MatierePremiereDAO matierePremiereDAO;
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
	JDateChooser dateChooserdebut = new JDateChooser();
	JDateChooser dateChooserfin = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	JComboBox soucategoriempcombo = new JComboBox();
	private DepotDAO depotdao;
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private Map<String, Magasin> mapMagasin = new HashMap<>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private SubCategorieMPDAO subcategoriempdao;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ProductionEnvracParMois() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1475, 1026);
        try{
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	chargeproductionDAO= new ChargeProductionDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	detailcoutProductiondao= new DetailCoutProductionDAOImpl();
        	detailchargevariabledao= new DetailChargeVariableDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	listsubcategoriemp=subcategoriempdao.findAll();
        	detailTransferMPDAO= new DetailTransferMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	depotdao =  new DepotDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
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
		titledSeparator_3.setTitle("Production En Vrac Par Mois");
		titledSeparator_3.setBackground(Color.RED);
		titledSeparator_3.setBounds(10, 0, 1444, 30);
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
		scrollPane.setBounds(11, 110, 1443, 520);
		add(scrollPane);
		
		tableArticleParMois = new JTable();
		tableArticleParMois.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code","Libelle","Mois 1 " ,"Mois 2","Mois 3","Mois 4","Mois 5 " ,"Mois 6","Mois 7","Mois 8","Mois 9 " ,"Mois 10","Mois 11","Mois 12","Initial" , "Total Production"
			}
		));
		tableArticleParMois.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableArticleParMois);
		
		JButton btnCoutProduction = new JButton("Exporter Excel");
		btnCoutProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				

				


				BigDecimal TotalPourcentage=BigDecimal.ZERO;	
					
				if(tableArticleParMois.getRowCount()!=0)
				{
					
					
				 
					
					
					
					
					String titre="Situation MP Consomme par Mois ";
		    		String titrefeuilleexcel="Situation MP Consomme par Mois ";
		    		ExporterTableVersExcel.tabletoexcelSituationMPConsommeParMois(tableArticleParMois, titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			
		
		
				
				
				
				
				
				
			
				
				
				
				
				
			
				
			
				
				
				
			
			}
		});
		btnCoutProduction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCoutProduction.setIcon(imgExcel);
		btnCoutProduction.setBounds(717, 656, 133, 23);
		add(btnCoutProduction);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 41, 1444, 57);
		add(layeredPane);
		
		JLabel label = new JLabel("Du  :");
		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(10, 11, 67, 24);
		layeredPane.add(label);
		
		 dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(48, 9, 163, 26);
		layeredPane.add(dateChooserdebut);
		
		JLabel label_3 = new JLabel("Magasin  :");
		label_3.setFont(new Font("Verdana", Font.BOLD, 12));
		label_3.setBounds(755, 10, 85, 26);
		layeredPane.add(label_3);
		
		 combomagasin = new JComboBox();
		combomagasin.setBounds(836, 13, 202, 27);
		layeredPane.add(combomagasin);
		
		JLabel label_4 = new JLabel("Sous-Categorie Mp");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(1048, 16, 144, 24);
		layeredPane.add(label_4);
		
		 soucategoriempcombo = new JComboBox();
		soucategoriempcombo.setBounds(1155, 16, 184, 24);
		layeredPane.add(soucategoriempcombo);
		
		JLabel label_6 = new JLabel("Au  :");
		label_6.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label_6.setBounds(227, 13, 67, 24);
		layeredPane.add(label_6);
		
		 dateChooserfin = new JDateChooser();
		dateChooserfin.setLocale(Locale.FRANCE);
		dateChooserfin.setDateFormatString("dd/MM/yyyy");
		dateChooserfin.setBounds(265, 11, 163, 26);
		layeredPane.add(dateChooserfin);
		
		JLabel label_7 = new JLabel("Depot  :");
		label_7.setFont(new Font("Verdana", Font.BOLD, 12));
		label_7.setBounds(457, 11, 85, 26);
		layeredPane.add(label_7);
		
		 combodepot = new JComboBox();
		 combodepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		

 		 		
 		 		if(!combodepot.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasin.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepot.get(combodepot.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasin = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasin.addItem("");
 						while (k < listMagasin.size()) {
 							Magasin magasin = listMagasin.get(k);

 							combomagasin.addItem(magasin.getLibelle());

 							mapMagasin.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasin.removeAllItems();
 		 			combomagasin.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
		 		
		 		
		 	}
		 });
		combodepot.setBounds(530, 13, 202, 27);
		layeredPane.add(combodepot);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setBounds(1383, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String requete="";
				listProductionArticleParMois.clear();
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				
				if(depot==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner depot SVP !!!");
					return;
				}else if(magasin==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!");
					return;
				
				
				}
				
				else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la periode SVP !!!");
					return;
				
				
				}else if(subCategorieMp==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la categorie SVP !!!");
					return;
				}else
				{
					
					
					
					
					if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()!=null)
					{
						dateChooserdebut.setDate(dateChooserfin.getDate());
					}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null)
					{
						dateChooserfin.setDate(dateChooserdebut.getDate());
					}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
					{
						
						if(dateChooserdebut.getDate().compareTo(dateChooserfin.getDate()) > 0)
						{
							JOptionPane.showMessageDialog(null, "La Date debut doit etre inferieur au date fin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							
							return ;
						}
						
						
						
						
					}
					if(dateChooserdebut.getDate()!=null)
					{
						dateChooserdebut.setDateFormatString("yyyy-MM-dd");
					}
					if(dateChooserfin.getDate()!=null)
					{
						dateChooserfin.setDateFormatString("yyyy-MM-dd");
					}
					
					String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					
						String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
						
						if(!dateDu.equals("") && dateAu.equals(""))
						{
							dateAu=dateDu;
						}else if(dateDu.equals("") && !dateAu.equals(""))
						{
							dateDu=dateAu;
						}
						
						 requete=" where prodcutionCM.codeDepot='"+depot.getCode()+"'";
						requete=requete+" and prodcutionCM.magasinStockage.id= '"+magasin.getId()+"'";
						
						 requete=requete+" and prodcutionCM.date between '"+dateDu+"' and '"+dateAu+"'";
						
						 requete=requete+" and matierePremier.categorieMp.subCategorieMp.id= '"+subCategorieMp.getId()+"' ";	
						
						 listCoutMP=productionDAO.listeCoutMPGroupByEnVrac();	
						
					
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				dateChooserdebut.setDateFormatString("yyyy");
				
				
				
				
			for(int i=0 ; i<listCoutMP.size();i++)
			{
				CoutMP coutmp=listCoutMP.get(i);
				if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(subCategorieMp.getCode()))
				{
					dao.entity.ProductionArticleParMois articleParMois=new dao.entity.ProductionArticleParMois();
					articleParMois.setMp(coutmp.getMatierePremier());
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
					articleParMois.setQtteInitial(BigDecimal.ZERO);
					articleParMois.setQtteTotal(BigDecimal.ZERO);
					listProductionArticleParMois.add(articleParMois);
					
				}
					
			
				
			}
			String annee=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			
			listeObjectInitialEnVrac=detailTransferMPDAO.listeInitialEnVrac(Integer.valueOf(annee), Constantes.ETAT_TRANSFER_STOCK_INITIAL,magasin, subCategorieMp.getId());
			
			for(int i=0;i<listeObjectInitialEnVrac.size() ; i++)
			{
				boolean existe=false;
				 Object[] object=listeObjectInitialEnVrac.get(i);		
				
				for(int k=0;k<listProductionArticleParMois.size() ;k++)
				{
					
					ProductionArticleParMois envrac =listProductionArticleParMois.get(k);
					
					if(envrac.getMp().getCode().equals(object[0].toString()))
					{
						existe=true;
						envrac.setQtteInitial(new BigDecimal( object[2].toString()) );
						
						listProductionArticleParMois.set(k, envrac);
						
						
					}
					
				}
				
				if(existe==false)
				{
					
					MatierePremier matierePremiere=matierePremiereDAO.findByCode(object[0].toString());
					dao.entity.ProductionArticleParMois articleParMois=new dao.entity.ProductionArticleParMois();
					articleParMois.setMp(matierePremiere);
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
					articleParMois.setQtteInitial(new BigDecimal(object[2].toString()));
					articleParMois.setQtteTotal(BigDecimal.ZERO);
					listProductionArticleParMois.add(articleParMois);	
					
					
				}
				
				
			}
			
			dateChooserdebut.setDateFormatString("MM");
			dateChooserfin.setDateFormatString("MM");
			String moisdebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();	
			String moisfin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();	
				
			for(int i=Integer.valueOf(moisdebut) ; i<Integer.valueOf(moisfin)+1 ; i++)
			{
				
				listeObject=productionDAO.listeProductionParEnvracParMois(i, Constantes.ETAT_OF_TERMINER);
				
				for(int j=0; j<listeObject.size() ; j++)
				{
					
					 Object[] object=listeObject.get(j);	
					
				for(int k=0;k<listProductionArticleParMois.size();k++)	
				{
					
				if(listProductionArticleParMois.get(k).getMp().getCode().equals(object[0].toString()))	
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
					
					listProductionArticleParMois.get(k).setQtteTotal(listProductionArticleParMois.get(k).getQtteMois1().add(listProductionArticleParMois.get(k).getQtteMois2().add(listProductionArticleParMois.get(k).getQtteMois3().add(listProductionArticleParMois.get(k).getQtteMois4().add(listProductionArticleParMois.get(k).getQtteMois5().add(listProductionArticleParMois.get(k).getQtteMois6().add(listProductionArticleParMois.get(k).getQtteMois7().add(listProductionArticleParMois.get(k).getQtteMois8().add(listProductionArticleParMois.get(k).getQtteMois9().add(listProductionArticleParMois.get(k).getQtteMois10().add(listProductionArticleParMois.get(k).getQtteMois11().add(listProductionArticleParMois.get(k).getQtteMois12()))))))))))));
					
					
					
				}
					
				}
					 
				
				}
				
				
			}
			intialiserTableChargeFix();
			
			for(int d=0;d<listProductionArticleParMois.size();d++)
			{
				
				
				Object []ligne={listProductionArticleParMois.get(d).getMp().getCode() , listProductionArticleParMois.get(d).getMp().getNom(), listProductionArticleParMois.get(d).getQtteMois1() ,listProductionArticleParMois.get(d).getQtteMois2(),listProductionArticleParMois.get(d).getQtteMois3(),listProductionArticleParMois.get(d).getQtteMois4(),listProductionArticleParMois.get(d).getQtteMois5(),listProductionArticleParMois.get(d).getQtteMois6(),listProductionArticleParMois.get(d).getQtteMois7(),listProductionArticleParMois.get(d).getQtteMois8(),listProductionArticleParMois.get(d).getQtteMois9(),listProductionArticleParMois.get(d).getQtteMois10(),listProductionArticleParMois.get(d).getQtteMois11(),listProductionArticleParMois.get(d).getQtteMois12() , listProductionArticleParMois.get(d).getQtteTotal()};

				Modelchargefix.addRow( ligne);
				
				
				
			}
			
				
				
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
	
		if (utilisateur.getLogin().equals("admin")) {
			
			listDepot =depotdao.findAll();
			combodepot.removeAllItems();
			combodepot.addItem("");
			
			for(int d=0;d<listDepot.size();d++)
			{
				
			Depot depot=listDepot.get(d);
			combodepot.addItem(depot.getLibelle());
			mapDepot.put(depot.getLibelle(), depot);
				
				
				
				
			}
			
	

		} else {
			Depot depot = depotdao.findByCode(utilisateur.getCodeDepot());
			
			if (depot != null) {
				combodepot.removeAllItems();
				combodepot.addItem("");
				combodepot.addItem(depot.getLibelle());
				mapDepot.put(depot.getLibelle(), depot);
	

			}
		}
		
		
		  int i=0;
  		  while(i<listsubcategoriemp.size())
  		  {
  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
  			  i++;
  		  }
				  		 
	}
	
	
	void	intialiserTableChargeFix(){
		 Modelchargefix =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Libelle","Mois 1 " ,"Mois 2","Mois 3","Mois 4","Mois 5 " ,"Mois 6","Mois 7","Mois 8","Mois 9 " ,"Mois 10","Mois 11","Mois 12" , "Total Production"
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

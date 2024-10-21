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


public class StatistiqueEnVracConsomme extends JLayeredPane implements Constantes{
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
	private List<StatistiqueEnVracConsommeClass> listStatistiqueEnvracConsomme =new ArrayList<StatistiqueEnVracConsommeClass>();
 
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
	private JTextField txtcodearticle = new JTextField();
	JComboBox comboarticle = new JComboBox();
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
	  JLabel LabelTotalQuantiteConsomme = new JLabel("");
	  JLabel LabelTotalPourcentage = new JLabel("");
	  private Map< Date, Integer> mapIdListSisuation= new HashMap<>();
	
	  JComboBox comboCategorie = new JComboBox();
	  private ImageIcon imgExcel;
	  String etatCategorie=etatCategorie=CODE_NON;
	  List<String> listeNomCategorie =new ArrayList<String>();
		List<String> listeCodeMP =new ArrayList<String>();
		 
		JLabel LabelTotalQuantiteOffre = new JLabel("");
		JLabel LabelTotalQuantitePlus = new JLabel("");
		JLabel LabelTotalQuantiteConsommeTotal = new JLabel("");
		JLabel LabelTotalQuantiteManquante = new JLabel("");
		JLabel LabelTotalQuantiteDechetFournisseur = new JLabel("");
		JLabel LabelTotalQuantiteDechetUsine = new JLabel("");
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public StatistiqueEnVracConsomme() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 912);
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
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					  		     			"Code En Vrac","N° En Vrac","Type En Vrac","Quantite Consomme OF","Quantite Plus","Quantite Offre","Quantite Manque","Quantite Dechet Usine","Quantite Dechet Fournisseur","Quantite Consomme Cout","Pourcentage"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,false,false,false
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
				  			     			"N° En Vrac","Type En Vrac","Quantite Consomme OF","Quantite Plus","Quantite Offre","Quantite Consomme Total","Pourcentage"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false
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
				  				scrollPane_1.setBounds(21, 156, 1467, 470);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1458, 120);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(564, 62, 93, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				listStatistiqueEnvracConsomme.clear();
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
						
						
						
					String	dateDu=sdf.format(dateChooserDu.getDate()) ;
					String dateAu= sdf.format(dateChooserAu.getDate()) ;	
						
					String requete="";	
						
						Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					
					requete=requete+"  c.prodcutionCM.magasinStockage.id='"+magasin.getId()+"' and c.prodcutionCM.date>='"+dateDu+"' and c.prodcutionCM.date<='"+dateAu+"' and  c.prodcutionCM.statut='"+ETAT_OF_TERMINER+"'  and c.matierePremier.categorieMp.subCategorieMp.code='"+SOUS_CATEGORIE_MATIERE_PREMIERE_THE+"' ";	
						
					if(!comboCategorie.getSelectedItem().equals(""))
					{
						CategorieMp categorieMp=Mapcategorie.get(comboCategorie.getSelectedItem());
						if(categorieMp!=null)
						{
							etatCategorie=CODE_OUI;
							requete=requete+"  and c.matierePremier.categorieMp.id='"+categorieMp.getId()+"' ";	
						}else
						{
							etatCategorie=CODE_NON;
						}
						
						
						
					}else
					{
						etatCategorie=CODE_NON;
					}
					
					listeNomCategorie.clear();
					listeCodeMP.clear();
						
					listObject=productionDAO.listeStatistiqueEnVracConsomme(requete);
					
					BigDecimal TotalQuantiteConsomme=BigDecimal.ZERO;
					BigDecimal TotalPourcentage=BigDecimal.ZERO;
					
					
				for(int i=0;i<listObject.size();i++)
				{
					 Object[] object=listObject.get(i);	
					
					 
				StatistiqueEnVracConsommeClass statistiqueEnVracConsommeClass=new StatistiqueEnVracConsommeClass();	 
					 
					if(object[0]!=null)
					{
						
					MatierePremier matierePremier=matierePremiereDAO.findByCode(object[0].toString())	;
					
					
					
					
						if(matierePremier!=null)
						{
							
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
							
							
							statistiqueEnVracConsommeClass.setMp(matierePremier);
							statistiqueEnVracConsommeClass.setQuantiteConsomme(new BigDecimal(object[3].toString()) );
							statistiqueEnVracConsommeClass.setQuantitePlus(new BigDecimal(object[4].toString()) );
							statistiqueEnVracConsommeClass.setQuantiteOffre(new BigDecimal(object[5].toString()) );
							statistiqueEnVracConsommeClass.setQuantiteDechet(new BigDecimal(object[6].toString()) );
							statistiqueEnVracConsommeClass.setQuantiteDechetFournisseur(new BigDecimal(object[7].toString()) );
							statistiqueEnVracConsommeClass.setQuantiteManque(new BigDecimal(object[8].toString()) );
							statistiqueEnVracConsommeClass.setCategorie(matierePremier.getCategorieMp().getNom());
							statistiqueEnVracConsommeClass.setQuantiteConsommeTotal(new BigDecimal(object[3].toString()).subtract(new BigDecimal(object[4].toString()) ).add(new BigDecimal(object[5].toString()).add(new BigDecimal(object[6].toString())).add(new BigDecimal(object[7].toString()).add(new BigDecimal(object[8].toString()))) ) );
							TotalQuantiteConsomme=TotalQuantiteConsomme.add(new BigDecimal(object[3].toString()).subtract(new BigDecimal(object[4].toString()) ).add(new BigDecimal(object[5].toString()).add(new BigDecimal(object[6].toString()).add(new BigDecimal(object[7].toString()).add(new BigDecimal(object[8].toString())))) ) );
							listStatistiqueEnvracConsomme.add(statistiqueEnVracConsommeClass);
							
							
							
						}
						
						
					}
					
						
					
				}
					
					
				for(int j=0;j<listStatistiqueEnvracConsomme.size();j++)
				{
					
					
					
					StatistiqueEnVracConsommeClass statistiqueEnVracConsommeClassTmp=listStatistiqueEnvracConsomme.get(j);	 
					
					statistiqueEnVracConsommeClassTmp.setPourcentage((statistiqueEnVracConsommeClassTmp.getQuantiteConsommeTotal().divide(TotalQuantiteConsomme, 6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
					TotalPourcentage=TotalPourcentage.add(statistiqueEnVracConsommeClassTmp.getPourcentage());
					listStatistiqueEnvracConsomme.set(j, statistiqueEnVracConsommeClassTmp);
				}
				
				
				
				BigDecimal quantiteCategorie=BigDecimal.ZERO;
				
				BigDecimal pourcentageCategorie=BigDecimal.ZERO;
				
				for(int d=0;d<listeNomCategorie.size();d++)
				{
					
					quantiteCategorie=BigDecimal.ZERO;
					pourcentageCategorie=BigDecimal.ZERO;
					for(int t=0;t<listStatistiqueEnvracConsomme.size();t++)
					{
						StatistiqueEnVracConsommeClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnvracConsomme.get(t);
						
					 if(listeNomCategorie.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getCategorie()))
					 {
						 
						 quantiteCategorie=quantiteCategorie.add(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsommeTotal()) ;
						 
						 
						 
					 }
						
					
						
						
					}
					
					if(TotalQuantiteConsomme.compareTo(BigDecimal.ZERO)!=0)
					{
						pourcentageCategorie=quantiteCategorie.divide(TotalQuantiteConsomme, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					}
					
					for(int t=0;t<listStatistiqueEnvracConsomme.size();t++)
					{
						StatistiqueEnVracConsommeClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnvracConsomme.get(t);
						
					 if(listeNomCategorie.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getCategorie()))
					 {
						 System.out.println(listeNomCategorie.get(d).toString() +" "+pourcentageCategorie);
						 statistiqueEnVracConsommeLorsProductionPFClass.setPourcentage_categorie(pourcentageCategorie);
						 listStatistiqueEnvracConsomme.set(t, statistiqueEnVracConsommeLorsProductionPFClass);
						 
						 
					 }
						
					
						
						
					}
					
					
					
					
					
					
				}
				
				BigDecimal quantiteMP=BigDecimal.ZERO;
				BigDecimal pourcentageMP=BigDecimal.ZERO;
				
				for(int d=0;d<listeCodeMP.size();d++)
				{
					
					quantiteMP=BigDecimal.ZERO;
					pourcentageMP=BigDecimal.ZERO;
					for(int t=0;t<listStatistiqueEnvracConsomme.size();t++)
					{
						StatistiqueEnVracConsommeClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnvracConsomme.get(t);
						
					 if(listeCodeMP.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getMp().getCode()))
					 {
						 
						 quantiteMP=quantiteMP.add(statistiqueEnVracConsommeLorsProductionPFClass.getQuantiteConsommeTotal()) ;
						 
						 
						 
					 }
						
					
						
						
					}
					
					if(TotalQuantiteConsomme.compareTo(BigDecimal.ZERO)!=0)
					{
						pourcentageMP=quantiteMP.divide(TotalQuantiteConsomme, 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					}
					
					for(int t=0;t<listStatistiqueEnvracConsomme.size();t++)
					{
						StatistiqueEnVracConsommeClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnvracConsomme.get(t);
						
					 if(listeCodeMP.get(d).toString().equals(statistiqueEnVracConsommeLorsProductionPFClass.getMp().getCode()))
					 {
						 System.out.println(listeCodeMP.get(d).toString() +" "+pourcentageMP);
						 statistiqueEnVracConsommeLorsProductionPFClass.setPourcentage_mp(pourcentageMP);
						 listStatistiqueEnvracConsomme.set(t, statistiqueEnVracConsommeLorsProductionPFClass);
						 
						 
					 }
						
					
						
						
					}
					
					
					
					
					
					
				}
				
				
				
						
					afficher_tableMP(listStatistiqueEnvracConsomme);
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
				}
			
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
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
		  		    	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);   
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
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		

		 	if(listStatistiqueEnvracConsomme.size()!=0)
		 	{
			
		 		Map parameters = new HashMap();
		 		
		 		parameters.put("dateDu", dateChooserDu.getDate() );
				parameters.put("dateAu", dateChooserAu.getDate() );
				parameters.put("depot", combodepot.getSelectedItem() );
				parameters.put("magasin", comboMagasin.getSelectedItem() );
				parameters.put("etatcategorie",etatCategorie) ;
				JasperUtils.imprimerStatistiqueEnVracConsomme(listStatistiqueEnvracConsomme,parameters); 
		 	
		 		
		 	}	
		 	
		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(797, 693, 135, 40);
		 add(button_1);

				
				
				
				
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
			    int i=0;
				while(i<listArticle.size())
				{
					Articles article=listArticle.get(i);
					comboarticle.addItem(article.getLiblle());
					mapArticle.put(article.getLiblle(), article);
					mapCodeArticle.put(article.getCodeArticle(), article);
					
					
					i++;
					
				}
			
			CategorieMp categorieChaara=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_CHAARA);
			Mapcategorie.put(categorieChaara.getNom(), categorieChaara);
			
			CategorieMp categorieMkarkeb=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_MKARKAB);
			Mapcategorie.put(categorieMkarkeb.getNom(), categorieMkarkeb);
		
		listFournisseur=fournisseurMPDAO.findAll();
		
		JButton btnInitialiser = new JButton();
		btnInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtcodearticle.setText("");
				comboarticle.setSelectedItem("");
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
		btnInitialiser.setBounds(681, 62, 93, 31);
		layeredPane.add(btnInitialiser);
		
		JLabel lblCtaegorie = new JLabel("Ctaegorie :");
		lblCtaegorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCtaegorie.setBounds(998, 10, 70, 26);
		layeredPane.add(lblCtaegorie);
		
		 comboCategorie = new JComboBox();
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
	
	JButton button = new JButton("Exporter Excel");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			


			BigDecimal TotalPourcentage=BigDecimal.ZERO;	
				
			if(tableStatistiqueEnvracConsomme.getRowCount()!=0)
			{
				
				
				for(int i=0;i<listStatistiqueEnvracConsomme.size();i++)
				{
					TotalPourcentage=TotalPourcentage.add(listStatistiqueEnvracConsomme.get(i).getPourcentage());
					
				}
				
				
				
				
				String titre="Statistique En Vrac Consomme ";
	    		String titrefeuilleexcel="Statistique En Vrac Consomme ";
	    		ExporterTableVersExcel.tabletoexcelStatistiqueEnVracConsomme (tableStatistiqueEnvracConsomme, titre,titrefeuilleexcel, TotalPourcentage);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
				
				
			}
 			
		}
	});
	button.setBounds(978, 693, 156, 34);
	button.setIcon(imgExcel);
	add(button);
	
	JLabel lblTotalQuantiteConsomme = new JLabel("TOTAL QUANTITE CONSOMME  OF:");
	lblTotalQuantiteConsomme.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteConsomme.setBounds(74, 637, 305, 26);
	add(lblTotalQuantiteConsomme);
	
	 LabelTotalQuantiteConsomme = new JLabel("");
	LabelTotalQuantiteConsomme.setForeground(Color.RED);
	LabelTotalQuantiteConsomme.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteConsomme.setBounds(389, 637, 209, 26);
	add(LabelTotalQuantiteConsomme);
	
	JLabel lblTotalPourcentage = new JLabel("TOTAL POURCENTAGE :");
	lblTotalPourcentage.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalPourcentage.setBounds(1079, 637, 219, 26);
	add(lblTotalPourcentage);
	
	 LabelTotalPourcentage = new JLabel("");
	LabelTotalPourcentage.setForeground(Color.RED);
	LabelTotalPourcentage.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalPourcentage.setBounds(1292, 637, 209, 26);
	add(LabelTotalPourcentage);
	
	JLabel lblTotalQuantitePlus = new JLabel("TOTAL QUANTITE PLUS :");
	lblTotalQuantitePlus.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantitePlus.setBounds(74, 674, 275, 26);
	add(lblTotalQuantitePlus);
	
	  LabelTotalQuantitePlus = new JLabel("");
	LabelTotalQuantitePlus.setForeground(Color.RED);
	LabelTotalQuantitePlus.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantitePlus.setBounds(389, 674, 209, 26);
	add(LabelTotalQuantitePlus);
	
	JLabel  lblTotalQuantiteOffre = new JLabel("TOTAL QUANTITE OFFRE:");
	lblTotalQuantiteOffre.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteOffre.setBounds(74, 711, 275, 26);
	add(lblTotalQuantiteOffre);
	
	  LabelTotalQuantiteOffre = new JLabel("");
	LabelTotalQuantiteOffre.setForeground(Color.RED);
	LabelTotalQuantiteOffre.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteOffre.setBounds(389, 711, 209, 26);
	add(LabelTotalQuantiteOffre);
	
	JLabel lblTotalQuantiteConsomme_1 = new JLabel("TOTAL QUANTITE CONSOMME TOTAL:");
	lblTotalQuantiteConsomme_1.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteConsomme_1.setBounds(51, 875, 324, 26);
	add(lblTotalQuantiteConsomme_1);
	
	  LabelTotalQuantiteConsommeTotal = new JLabel("");
	LabelTotalQuantiteConsommeTotal.setForeground(Color.RED);
	LabelTotalQuantiteConsommeTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteConsommeTotal.setBounds(377, 875, 209, 26);
	add(LabelTotalQuantiteConsommeTotal);
	
	JLabel lblTotalQuantiteDechet = new JLabel("TOTAL QUANTITE DECHET USINE :");
	lblTotalQuantiteDechet.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteDechet.setBounds(74, 751, 305, 26);
	add(lblTotalQuantiteDechet);
	
	  LabelTotalQuantiteDechetUsine = new JLabel("");
	LabelTotalQuantiteDechetUsine.setForeground(Color.RED);
	LabelTotalQuantiteDechetUsine.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteDechetUsine.setBounds(389, 751, 209, 26);
	add(LabelTotalQuantiteDechetUsine);
	
	JLabel lblTotalQuantiteDechet_1 = new JLabel("TOTAL QUANTITE DECHET FOURNISSEUR:");
	lblTotalQuantiteDechet_1.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteDechet_1.setBounds(74, 788, 358, 26);
	add(lblTotalQuantiteDechet_1);
	
	  LabelTotalQuantiteDechetFournisseur = new JLabel("");
	LabelTotalQuantiteDechetFournisseur.setForeground(Color.RED);
	LabelTotalQuantiteDechetFournisseur.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteDechetFournisseur.setBounds(442, 788, 236, 26);
	add(LabelTotalQuantiteDechetFournisseur);
	
	JLabel lblTotalQuantiteManquante = new JLabel("TOTAL QUANTITE MANQUANTE :");
	lblTotalQuantiteManquante.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblTotalQuantiteManquante.setBounds(74, 825, 324, 26);
	add(lblTotalQuantiteManquante);
	
	  LabelTotalQuantiteManquante = new JLabel("");
	LabelTotalQuantiteManquante.setForeground(Color.RED);
	LabelTotalQuantiteManquante.setFont(new Font("Tahoma", Font.BOLD, 16));
	LabelTotalQuantiteManquante.setBounds(442, 825, 236, 26);
	add(LabelTotalQuantiteManquante);
		
				  		 
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
	
	
void afficher_tableMP(List<StatistiqueEnVracConsommeClass> listStatistiqueEnVracConsomme)
	{
	intialiserTableau2();
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
	BigDecimal TotalQuantiteConsommeOf=BigDecimal.ZERO;
	BigDecimal TotalQuantiteConsommeTotal=BigDecimal.ZERO;
	BigDecimal TotalQuantitePlus=BigDecimal.ZERO;
	BigDecimal TotalQuantiteOffre=BigDecimal.ZERO;
	BigDecimal TotalQuantiteDechetusine=BigDecimal.ZERO;
	BigDecimal TotalQuantiteDechetFournisseur=BigDecimal.ZERO;
	BigDecimal TotalQuantiteManque=BigDecimal.ZERO;
	BigDecimal TotalPourcentage=BigDecimal.ZERO;
	
	int i=0;
	 
	while(i<listStatistiqueEnVracConsomme.size())
	{	
		
		
		StatistiqueEnVracConsommeClass statistiqueEnVracConsommeClass=listStatistiqueEnVracConsomme.get(i);
		TotalQuantiteConsommeTotal=TotalQuantiteConsommeTotal.add(statistiqueEnVracConsommeClass.getQuantiteConsommeTotal().setScale(2, RoundingMode.HALF_UP));
		TotalQuantiteConsommeOf=TotalQuantiteConsommeOf.add(statistiqueEnVracConsommeClass.getQuantiteConsomme().setScale(2, RoundingMode.HALF_UP));
		TotalQuantitePlus=TotalQuantitePlus.add(statistiqueEnVracConsommeClass.getQuantitePlus().setScale(2, RoundingMode.HALF_UP));
		TotalQuantiteOffre=TotalQuantiteOffre.add(statistiqueEnVracConsommeClass.getQuantiteOffre().setScale(2, RoundingMode.HALF_UP));
		TotalQuantiteDechetusine=TotalQuantiteDechetusine.add(statistiqueEnVracConsommeClass.getQuantiteDechet().setScale(2, RoundingMode.HALF_UP));
		TotalQuantiteDechetFournisseur=TotalQuantiteDechetFournisseur.add(statistiqueEnVracConsommeClass.getQuantiteDechetFournisseur().setScale(2, RoundingMode.HALF_UP));
		TotalQuantiteManque=TotalQuantiteManque.add(statistiqueEnVracConsommeClass.getQuantiteManque().setScale(2, RoundingMode.HALF_UP));
		TotalPourcentage=TotalPourcentage.add(statistiqueEnVracConsommeClass.getPourcentage());
			
				 Object []ligne={statistiqueEnVracConsommeClass.getMp().getCode(), statistiqueEnVracConsommeClass.getMp().getNom(),statistiqueEnVracConsommeClass.getMp().getCategorieMp().getNom(),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteConsomme().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantitePlus().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteOffre().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteManque().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteDechet().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteDechetFournisseur().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(statistiqueEnVracConsommeClass.getQuantiteConsommeTotal().setScale(2, RoundingMode.HALF_UP)) ,statistiqueEnVracConsommeClass.getPourcentage().setScale(2, RoundingMode.HALF_UP)+"%" };
				 modeleMP.addRow(ligne);
 		
		i++;
	}
		
		LabelTotalQuantiteConsomme.setText( dfDecimal.format(TotalQuantiteConsommeOf.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantiteConsommeTotal.setText( dfDecimal.format(TotalQuantiteConsommeTotal.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantitePlus.setText( dfDecimal.format(TotalQuantitePlus.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantiteOffre.setText( dfDecimal.format(TotalQuantiteOffre.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantiteDechetUsine.setText( dfDecimal.format(TotalQuantiteDechetusine.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantiteDechetFournisseur.setText( dfDecimal.format(TotalQuantiteDechetFournisseur.setScale(2, RoundingMode.HALF_UP))+"");
		LabelTotalQuantiteManquante.setText( dfDecimal.format(TotalQuantiteManque.setScale(2, RoundingMode.HALF_UP))+"");
		
	LabelTotalPourcentage.setText(TotalPourcentage.setScale(2, RoundingMode.HALF_UP)+"");
	
	
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code En Vrac","N° En Vrac","Type En Vrac","Quantite Consomme OF","Quantite Plus","Quantite Offre","Quantite Manque","Quantite Dechet Usine","Quantite Dechet Fournisseur","Quantite Consomme Cout","Pourcentage"

		     	
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false
		     			
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableStatistiqueEnvracConsomme.setModel(modeleMP); 
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(6).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(7).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(8).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(9).setPreferredWidth(60);
		 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(10).setPreferredWidth(60);
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

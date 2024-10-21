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
import dao.entity.SituationPFParAnneeClass;
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


public class SituationPFParAnnee extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableSituationManqueEnVrac;
	
	
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
	private List<Object[]> listProductionGroupByDate =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeProduction =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployegenerique =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeResponsable =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeAdhesif =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeRepos =new ArrayList<Object[]>();
	private List<SituationPFParAnneeClass> listSituationPFParAnnee =new ArrayList<SituationPFParAnneeClass>();
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
	  private Map< Date, Integer> mapIdListSisuation= new HashMap<>();
		Integer anneDu=0;
		Integer anneAu=0;
	
	  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationPFParAnnee() {
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
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
        	imgAjouter= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
       	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Date Production","Total Employes","N° Employe","Generique","Adhesif","Absente","Machine","Article","Total Heures","Prix","Nombres Des Employes","Quantite Fabrique","Cout" ,"Cout Fix","Difference"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	

				  		     	
				  		     		tableSituationManqueEnVrac = new JXTable();
				  		     		tableSituationManqueEnVrac.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableSituationManqueEnVrac.getSelectedRow()!=-1)
				  		     				{

				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     		});
				  		     		tableSituationManqueEnVrac.setShowVerticalLines(false);
				  		     		tableSituationManqueEnVrac.setSelectionBackground(new Color(51, 204, 255));
				  		     		tableSituationManqueEnVrac.setRowHeightEnabled(true);
				  		     		tableSituationManqueEnVrac.setBackground(new Color(255, 255, 255));
				  		     		tableSituationManqueEnVrac.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		tableSituationManqueEnVrac.setColumnControlVisible(true);
				  		     		tableSituationManqueEnVrac.setForeground(Color.BLACK);
				  		     		tableSituationManqueEnVrac.setGridColor(new Color(0, 0, 255));
				  		     		tableSituationManqueEnVrac.setAutoCreateRowSorter(true);
				  		     		//    table.setBounds(2, 27, 411, 198);
				  		     		tableSituationManqueEnVrac.setRowHeight(20);
				  		      modeleMP =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Mois","Quantite Fabrique","% "+anneDu,"Quantite Fabrique","% "+anneAu,"Différence"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableSituationManqueEnVrac.setModel(modeleMP); 
				  			 tableSituationManqueEnVrac.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableSituationManqueEnVrac.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableSituationManqueEnVrac);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(21, 156, 1467, 470);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1458, 120);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1062, 47, 93, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				listObject.clear();
			
				listSituationPFParAnnee.clear();
				
				Parametre parametre=parametreDAO.findByCode(PARAMETRE_CODE_COUT_HORAIRE_CNSS);
						
				
				

			if(combodepot.getSelectedItem().equals("") )
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner depot SVP !!!");
				return;
			}else if(comboMagasin.getSelectedItem().equals("") )
			{

				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!");
				return;
			
			
			}else
			{
				
				Depot depot=mapDepotSource.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());


				
				
				
				if(dateChooserDu.getDate()==null && dateChooserAu.getDate()!=null)
				{
					dateChooserDu.setDate(dateChooserAu.getDate());
				}else if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()==null)
				{
					dateChooserAu.setDate(dateChooserDu.getDate());
				}else if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()!=null)
				{
					
					if(dateChooserDu.getDate().compareTo(dateChooserAu.getDate()) > 0)
					{
						JOptionPane.showMessageDialog(null, "La Date debut doit etre inferieur au date fin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}else if (dateChooserAu.getDate().compareTo(dateChooserDu.getDate()) > 1)
					{
						
                   JOptionPane.showMessageDialog(null, "La Difference Entre la Date Début et Date fin doit etre une Annee SVPP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}
					
					
					
					
				}


					


				
					
					if(depot==null || magasin==null)
					{
						
JOptionPane.showMessageDialog(null, "Veuillez Selectionner Depot et Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}
				
				



					 anneDu= Integer.valueOf(((JTextField)dateChooserDu.getDateEditor().getUiComponent()).getText()) ;
					 anneAu=Integer.valueOf(((JTextField)dateChooserAu.getDateEditor().getUiComponent()).getText());
					
				 
					
					
					for(int t=1;t<13;t++)
					{
						
						SituationPFParAnneeClass situationPFParAnneeClass=new SituationPFParAnneeClass();
						situationPFParAnneeClass.setAnnedu(anneDu);
						situationPFParAnneeClass.setAnneAu(anneAu);
						situationPFParAnneeClass.setMagasin(magasin);
						situationPFParAnneeClass.setMoisAnneDu( t  );
						situationPFParAnneeClass.setMoisAnneAu(String.valueOf(t) );
						situationPFParAnneeClass.setPourcentageAnneDu(BigDecimal.ZERO);
						situationPFParAnneeClass.setPourcentageAnneAu(BigDecimal.ZERO);
						situationPFParAnneeClass.setQuantiteFabriquerAnneDu(BigDecimal.ZERO);
						situationPFParAnneeClass.setQuantiteFabriquerAnneAu(BigDecimal.ZERO);
						situationPFParAnneeClass.setDifference(BigDecimal.ZERO);
						listSituationPFParAnnee.add(situationPFParAnneeClass);
						
						
					}
					

					
	////////////////////////////////////////////////////////////////////////////////////////////////////////     Anne Actuel             ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				BigDecimal TotalQuantiteAnneDu=BigDecimal.ZERO;	
				BigDecimal TotalQuantiteAnneAu=BigDecimal.ZERO;	
					
					listObject=productionDAO.listeSituationProductionParAnnee(anneDu, Constantes.ETAT_OF_TERMINER, depot.getCode());
					
					int i=0;
					 
					while(i<listObject.size())
					{	
						 Object[] object=listObject.get(i);
						 
						
									
					for(int d=0;d<listSituationPFParAnnee.size();d++)
					{
						
						if(object[0] != null && object[1]!=null && object[2]!=null) 
						
						
						{
							
							if(listSituationPFParAnnee.get(d).getAnnedu().equals(Integer.valueOf(object[0].toString()) ))
							{
								
								if(listSituationPFParAnnee.get(d).getMoisAnneDu().equals(Integer.valueOf(object[1].toString()) ))
								{
									
									
									listSituationPFParAnnee.get(d).setQuantiteFabriquerAnneDu((BigDecimal)object[2]);
									
								}
								
								
								
								
							}
							
						}
						
						
						
						
						
					}
					
					
					
				
						
				i++;		
				
					}
					
					
	////////////////////////////////////////////////////////////////////////////////////////////////////////     Anne Actuel             ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					listObject=productionDAO.listeSituationProductionParAnnee(anneAu, Constantes.ETAT_OF_TERMINER, depot.getCode());
					
					int j=0;
					 
					while(j<listObject.size())
					{	
						 Object[] object=listObject.get(j);
						 
						
									
					for(int d=0;d<listSituationPFParAnnee.size();d++)
					{
						
						if(object[0] != null && object[1]!=null && object[2]!=null) 
						
						
						{
							
							if(listSituationPFParAnnee.get(d).getAnneAu().equals(Integer.valueOf(object[0].toString()) ))
							{
								
								if(listSituationPFParAnnee.get(d).getMoisAnneAu().equals(object[1].toString()))
								{
									
									
									listSituationPFParAnnee.get(d).setQuantiteFabriquerAnneAu((BigDecimal)object[2]);
									
								}
								
								
								
								
							}
							
						}
						
						
						
						
						
					}
					
					
					
				
					j++;	
						
				
					}		
					
					
					for(int d=0;d<listSituationPFParAnnee.size();d++)
					{
						
					 TotalQuantiteAnneDu=TotalQuantiteAnneDu.add(listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneDu());
					 TotalQuantiteAnneAu=TotalQuantiteAnneAu.add(listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneAu());
						
						
						
						
					}	
					
					for(int d=0;d<listSituationPFParAnnee.size();d++)
					{
						
					if(TotalQuantiteAnneDu.compareTo(BigDecimal.ZERO)!=0)	
					{
						listSituationPFParAnnee.get(d).setPourcentageAnneDu(listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneDu().divide(TotalQuantiteAnneDu,6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
					}
					if(TotalQuantiteAnneAu.compareTo(BigDecimal.ZERO)!=0)	
					{
						listSituationPFParAnnee.get(d).setPourcentageAnneAu (listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneAu().divide(TotalQuantiteAnneAu,6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
					}
					
			
		
			listSituationPFParAnnee.get(d).setDifference(listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneAu().subtract(listSituationPFParAnnee.get(d).getQuantiteFabriquerAnneDu()));		
						
						
					}
					
					

					
					
					
				afficher_tableMP(listSituationPFParAnnee);
				
				
			}

			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label_2 = new JLabel("Depot :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(20, 47, 55, 26);
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
		combodepot.setBounds(75, 47, 202, 27);
		layeredPane.add(combodepot);
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

		 	if(listSituationPFParAnnee.size()!=0)
		 	{
				if(dateChooserDu.getDate()!=null)
				{
					dateChooserDu.setDateFormatString("dd/MM/yyyy");
				}
				if(dateChooserAu.getDate()!=null)
				{
					dateChooserAu.setDateFormatString("dd/MM/yyyy");
				}
				
				String dateDu=((JTextField)dateChooserDu.getDateEditor().getUiComponent()).getText();
				
					String dateAu=((JTextField)dateChooserAu.getDateEditor().getUiComponent()).getText();
					
					if(!dateDu.equals("") && dateAu.equals(""))
					{
						dateAu=dateDu;
					}else if(dateDu.equals("") && !dateAu.equals(""))
					{
						dateDu=dateAu;
					}
		 		
		 		Map parameters = new HashMap();
		 		parameters.put("magasin", comboMagasin.getSelectedItem().toString());
		 		
		 		parameters.put("annedu", anneDu+"");
		 		parameters.put("anneau", anneAu+"");
		 		parameters.put("moisannedu", "MOIS /"+anneDu);
		 		parameters.put("moisanneau", "MOIS /"+anneAu);
		 		parameters.put("porcentage", "POURCENTAGE");
		 		parameters.put("mois", "MOIS");
		 		parameters.put("quantite", "QUANTITE");
		 		
		 	
		 		if(!dateDu.equals("") || !dateAu.equals(""))
		 		{
		 			parameters.put("Periode", " Du : "+anneDu +" Au : "+anneAu);
		 		}
		 		
		 		
		 		
	
		 		for(int i=0;i<listSituationPFParAnnee.size();i++)
		 		{
		 			
		 			System.out.println(listSituationPFParAnnee.get(i).getMoisAnneDu());
		 			
		 			System.out.println(listSituationPFParAnnee.get(i).getQuantiteFabriquerAnneDu());
		 			
		 		}

		 	
		 		
				JasperUtils.imprimerSituationPFParAnnee(listSituationPFParAnnee,parameters); 
		 	
				
				JasperUtils.imprimerSituationPFParAnneeGraphParQauntite(listSituationPFParAnnee,parameters); 
				
				
		 		
		 	}		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(799, 650, 135, 40);
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
		  		comboMagasin.setBounds(352, 47, 202, 27);
		  		layeredPane.add(comboMagasin);
		  		
		  		JLabel lblMagasin = new JLabel("Magasin :");
		  		lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblMagasin.setBounds(297, 47, 55, 26);
		  		layeredPane.add(lblMagasin);
		  		
		  		JLabel label = new JLabel("Du :");
		  		label.setBounds(574, 49, 45, 24);
		  		layeredPane.add(label);
		  		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		
		  		 dateChooserDu = new JDateChooser();
		  		 dateChooserDu.setBounds(608, 47, 163, 26);
		  		 layeredPane.add(dateChooserDu);
		  		 dateChooserDu.setLocale(Locale.FRANCE);
		  		 dateChooserDu.setDateFormatString("yyyy");
		  		 
		  		 JLabel label_1 = new JLabel("Au :");
		  		 label_1.setBounds(781, 49, 36, 24);
		  		 layeredPane.add(label_1);
		  		 label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		 
		  		  dateChooserAu = new JDateChooser();
		  		  dateChooserAu.setBounds(811, 47, 169, 26);
		  		  layeredPane.add(dateChooserAu);
		  		  dateChooserAu.setLocale(Locale.FRANCE);
		  		  dateChooserAu.setDateFormatString("yyyy");
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
		btnInitialiser.setBounds(1179, 47, 93, 31);
		layeredPane.add(btnInitialiser);
		
		JButton btnExporterExcel = new JButton();
		btnExporterExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				

				

				


			 	
				
				if(tableSituationManqueEnVrac.getRowCount()!=0)
				{
				 
					
					
					
					
					String titre="Situation PF Par Annee ";
		    		String titrefeuilleexcel="Situation PF Par Annee ";
		    		ExporterTableVersExcel.tabletoexcelSituationPFParAnnee  (tableSituationManqueEnVrac,titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			
		
		
				 
			
				
			
				
				
				
			
				
			}
		});
		btnExporterExcel.setText("Exporter Excel");
		btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExporterExcel.setBounds(609, 650, 135, 40);
		add(btnExporterExcel);
		for(int j=0;j<listFournisseur.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(j);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		
				  		 
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
	
	
void afficher_tableMP(List<SituationPFParAnneeClass> listSituationPFParAnneeClass)
	{
	intialiserTableau2();
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
	
	
	int i=0;
	 
	while(i<listSituationPFParAnneeClass.size())
	{	
		SituationPFParAnneeClass situationPFParAnneeClass=listSituationPFParAnneeClass.get(i);
	
	
		
		
	
			
				 Object []ligne={"Mois "+ situationPFParAnneeClass.getMoisAnneDu(),dfDecimal.format(situationPFParAnneeClass.getQuantiteFabriquerAnneDu()) ,situationPFParAnneeClass.getPourcentageAnneDu(),dfDecimal.format(situationPFParAnneeClass.getQuantiteFabriquerAnneAu()),situationPFParAnneeClass.getPourcentageAnneAu(),dfDecimal.format(situationPFParAnneeClass.getDifference())};
				 modeleMP.addRow(ligne);
			 
				
		
		
		
		i++;
	}
		
		
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Mois","Quantite Fabrique"+anneDu,"% "+anneDu,"Quantite Fabrique"+anneAu,"% "+anneAu,"Différence"
		     			//"Date Production","Total Heures","Prix","Nombres Des Employes","Quantite Fabrique","Machine","Article"

		     	
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false
		     			//	false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableSituationManqueEnVrac.setModel(modeleMP); 
		 tableSituationManqueEnVrac.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(5).setPreferredWidth(60);
		
		
}






public void Vider()
{
	
	combomp.setSelectedItem("");
}
}

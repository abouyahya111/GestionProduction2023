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
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CoutMachineDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.FormeParBoxDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutMachineDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FormeParBoxDAO;
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
import dao.entity.CoutMachine;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.FicheEmploye;
import dao.entity.FormeParBox;
import dao.entity.FournisseurMP;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
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


public class SituationProgrammeProduction extends JLayeredPane implements Constantes{
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
	private FormeParBoxDAO formeParBoxDAO;
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
	private List<Object[]> listObjectEmployeProductionParDate =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployegenerique =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployegeneriqueParDate =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeAdhesifParDate =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeResponsable =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeAdhesif =new ArrayList<Object[]>();
	private List<Object[]> listObjectEmployeRepos =new ArrayList<Object[]>();
	private List<dao.entity.SituationProgrammeProduction> listSituationProduction =new ArrayList<dao.entity.SituationProgrammeProduction>();
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
	  
	  private List<Integer> listNombreEmploye =new ArrayList<Integer>();
	  private List<Integer> listNombreGenerique =new ArrayList<Integer>();
	  private List<Integer> listNombreAdhesif =new ArrayList<Integer>();
	  private List<Integer> listAbsent =new ArrayList<Integer>();
	  CoutMachineDAO coutMachineDAO;
	  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationProgrammeProduction() {
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
        	  formeParBoxDAO=new FormeParBoxDAOImpl();
        	  coutMachineDAO=new CoutMachineDAOImpl();
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
				  			     			"Date Production","Total Employes","N° Employe","Generique","Adhesif","Absente","Repos","Machine","Article","Total Heures","Prix","Nombres Des Employes","Quantite Fabrique","Cout","Cout Fix","Difference"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
		btnAfficherStock.setBounds(1184, 24, 93, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				listObject.clear();
				listProductionGroupByDate.clear();
				listSituationProduction.clear();
				
						
				
				

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
					}
					
					
					
					
				}


					
				Parametre parametre=parametreDAO.findByDateByLibelle(dateChooserDu.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				Parametre parametreTauxCnssGlobale=parametreDAO.findByDateByLibelle(dateChooserDu.getDate(), Constantes.PARAMETRE_LIBELLE_TAUX_CNSS_GLOBAL);

				
					
					if(depot==null || magasin==null)
					{
						
JOptionPane.showMessageDialog(null, "Veuillez Selectionner Depot et Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}
				
				




				 
					listProductionGroupByDate=productionDAO.listeSituationProductionGroupByDate(dateChooserDu.getDate(), dateChooserAu.getDate(), Constantes.ETAT_OF_TERMINER, depot.getCode());
				 
				
					listObject=productionDAO.listeSituationProduction(dateChooserDu.getDate(), dateChooserAu.getDate(), Constantes.ETAT_OF_TERMINER, depot.getCode());
					
					int i=0;
					 
					while(i<listObject.size())
					{	
						 Object[] object=listObject.get(i);
						 
						String famille=""; 
					
					dao.entity.SituationProgrammeProduction situationProgrammeProduction=new dao.entity.SituationProgrammeProduction();
					
					
					
					
							Machine machine=machineDAO.findByCodeNom(object[3].toString());
							Articles article=ArticleDAO.findByLibelle(object[4].toString());
							
							FormeParBox formeParBox=formeParBoxDAO.findById((int)object[7]);
							if(article.getId()==228 || article.getId()==209)
							{
								System.out.print(formeParBox.getSubCategorieMp().getNom() +"*****" + formeParBox.getForme().getLibelle());
							}
							
							
					situationProgrammeProduction.setDate((Date)object[0]);
					situationProgrammeProduction.setPrix((parametre.getValeur().add(parametre.getValeur().multiply(parametreTauxCnssGlobale.getValeur().divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))));
					situationProgrammeProduction.setQuantite((BigDecimal)object[2]);
					situationProgrammeProduction.setFormeParBox(formeParBox);
					int num = Integer.parseInt(object[5].toString());
					
					situationProgrammeProduction.setNombreOF(new BigDecimal(num));
					
					
					situationProgrammeProduction.setMachine(machine);
					situationProgrammeProduction.setArticle(article);
					if(article.getCentreCout3()!=null)
					{
						
						if(article.getLiblle().contains(Constantes.SOUS_FAMILLE_CHAARA))
						{
							famille=Constantes.SOUS_FAMILLE_CHAARA;
						}else if(article.getLiblle().contains(Constantes.SOUS_FAMILLE_CHAARA) && article.getLiblle().contains(Constantes.SOUS_FAMILLE_BOIT))
						{
							famille=Constantes.SOUS_FAMILLE_CHAARA +""+Constantes.SOUS_FAMILLE_BOIT;
						}else if(article.getLiblle().contains(Constantes.SOUS_FAMILLE_3505))
						{
							famille=Constantes.SOUS_FAMILLE_3505;
						}
						
						if(article.getLiblle().equals("JOJ JMAL VERT CHAARA  2KG "))
						{
							System.out.println(article.getLiblle());	
							
						}
						
						
						if(article.getCentreCout3().compareTo(new BigDecimal(1000))<0)
						{
							
							
							situationProgrammeProduction.setGrammage(article.getCentreCout3().setScale(0)+" G" +"/"+article.getCentreCout4().setScale(0)+" KG"+" ("+famille +")");
							
							System.out.println(article.getLiblle());
							
						}else
						{
							
							System.out.println(article.getLiblle());
							situationProgrammeProduction.setGrammage(article.getCentreCout3().divide(new BigDecimal(1000), 0, RoundingMode.HALF_UP)+" KG"+"/"+article.getCentreCout4().setScale(0)+" KG"+" ("+famille +")");
						}
						
						
						
						
						
					}
					
					situationProgrammeProduction.setNombreEmploye(BigDecimal.ZERO);
					situationProgrammeProduction.setGenerique(BigDecimal.ZERO);
					situationProgrammeProduction.setAbsent(BigDecimal.ZERO);
					situationProgrammeProduction.setNumEmploye(BigDecimal.ZERO);
					situationProgrammeProduction.setAdhesif(BigDecimal.ZERO);
					situationProgrammeProduction.setRepos(BigDecimal.ZERO);
					situationProgrammeProduction.setTotalEmploye(BigDecimal.ZERO);
					
					situationProgrammeProduction.setTotalHeures(BigDecimal.ZERO);
					
					situationProgrammeProduction.setCout(BigDecimal.ZERO);
						listSituationProduction.add(situationProgrammeProduction);	
						i++;
					}
					
					
					
					for(int j=0;j<listSituationProduction.size();j++)
					{
						
						dao.entity.SituationProgrammeProduction situationProgrammeProduction=listSituationProduction.get(j);
						
						
						
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EmployeProduction ////////////////////////////////////////////////////////////////////////////					
				
						
						listObjectEmployeProduction=productionDAO.NombreEmployeProduction(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, situationProgrammeProduction.getMachine(), depot.getCode(), false, situationProgrammeProduction.getArticle().getCentreCout3());
						
						for(int k=0;k<listObjectEmployeProduction.size();k++)
						{
							 Object[] object=listObjectEmployeProduction.get(k);	
							 if(object[0]!=null)
							 {
								 situationProgrammeProduction.setTotalHeures(situationProgrammeProduction.getTotalHeures().add((BigDecimal)object[0]));
								
							 }
							 if(object[1]!=null)
							 {
								 situationProgrammeProduction.setNombreEmploye(situationProgrammeProduction.getNombreEmploye().add(new BigDecimal(object[1].toString()) ));
								 situationProgrammeProduction.setNumEmploye(situationProgrammeProduction.getNumEmploye().add(new BigDecimal(object[1].toString())));

							 }
							
							
							
						}
						listObjectEmployeProduction.clear();
						listObjectEmployeProduction=productionDAO.NombreEmployeProduction(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, situationProgrammeProduction.getMachine(), depot.getCode(), true, situationProgrammeProduction.getArticle().getCentreCout3());
						
						
						for(int k=0;k<listObjectEmployeProduction.size();k++)
						{
							 Object[] object=listObjectEmployeProduction.get(k);	
							 if(object[1]!=null)
							 {
								 situationProgrammeProduction.setAbsent(situationProgrammeProduction.getAbsent().add(new BigDecimal(object[1].toString())));

							 }
							
						}
						
						
						
						
						
						
						
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EmployeGenerique( /////////////////////////////////////////////////////////////////////////////////////					
						
listObjectEmployegenerique=productionDAO.NombreEmployeGenerique(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, situationProgrammeProduction.getMachine(), depot.getCode(), false,situationProgrammeProduction.getArticle().getCentreCout3());
						
						for(int k=0;k<listObjectEmployegenerique.size();k++)
						{
							 Object[] object=listObjectEmployegenerique.get(k);	
							
								 if(object[0]!=null)
								 {
									 situationProgrammeProduction.setTotalHeures(situationProgrammeProduction.getTotalHeures().add(new BigDecimal(object[0].toString())));

								 }
								 
								 
								 if(object[1]!=null)
								 {
									 situationProgrammeProduction.setNombreEmploye(situationProgrammeProduction.getNombreEmploye().add(new BigDecimal(object[1].toString())));
									 situationProgrammeProduction.setNumEmploye(situationProgrammeProduction.getNumEmploye().add(new BigDecimal(object[1].toString())));  
								 }
								
							 
							
							
						}
						
						listObjectEmployegenerique.clear();
						listObjectEmployegenerique=productionDAO.NombreEmployeGenerique(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, situationProgrammeProduction.getMachine(), depot.getCode(), true, situationProgrammeProduction.getArticle().getCentreCout3());
						
						
						for(int k=0;k<listObjectEmployegenerique.size();k++)
						{
							 Object[] object=listObjectEmployegenerique.get(k);	
							 if(object[1]!=null)
							 {
								 situationProgrammeProduction.setAbsent(situationProgrammeProduction.getAbsent().add(new BigDecimal(object[1].toString())));
 
							 }
							
						}
						
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EmployeResponsable  //////////////////////////////////////////////////////////////////////////////////////////////////////						
						
                    listObjectEmployeResponsable=productionDAO.NombreEmployeResponsable(situationProgrammeProduction.getDate(), false);
						
						for(int k=0;k<listObjectEmployeResponsable.size();k++)
						{
							 Object[] object=listObjectEmployeResponsable.get(k);	
							 if(object[1]!=null)
							 {
								 situationProgrammeProduction.setGenerique(situationProgrammeProduction.getGenerique().add(new BigDecimal(object[1].toString())));

							 }
							
							
						}
						

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////EmployeRepos //////////////////////////////////////////////////////////////////////////////////////////////////////						

						
						
listObjectEmployeRepos=productionDAO.NombreEmployeRepos(situationProgrammeProduction.getDate(),  depot.getCode() ) ;

for(int k=0;k<listObjectEmployeRepos.size();k++)
{
Object[] objectTmp=listObjectEmployeRepos.get(k);	
if(objectTmp[1]!=null)
{
	situationProgrammeProduction.setRepos(situationProgrammeProduction.getRepos().add(new BigDecimal(objectTmp[1].toString())));

}


}



						
						
/////////////////////////////////////////////////////////////////////////////////////////////// EmployeAdhesif ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
listObjectEmployeAdhesif=productionDAO.NombreEmployeAdhesif(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, depot.getCode(), false);

for(int k=0;k<listObjectEmployeAdhesif.size();k++)
{
Object[] object=listObjectEmployeAdhesif.get(k);	
if(object[1]!=null)
{
	situationProgrammeProduction.setAdhesif (situationProgrammeProduction.getAdhesif().add(new BigDecimal(object[1].toString())));

}


}

listObjectEmployeAdhesif.clear();
listObjectEmployeAdhesif=productionDAO.NombreEmployeAdhesif(situationProgrammeProduction.getDate(), Constantes.ETAT_OF_TERMINER, depot.getCode(), true);


for(int k=0;k<listObjectEmployeAdhesif.size();k++)
{
Object[] object=listObjectEmployeAdhesif.get(k);	
if(object[1]!=null)
{
	situationProgrammeProduction.setAbsent( situationProgrammeProduction.getAbsent().add(new BigDecimal(object[1].toString())));

}

}	





						
						
situationProgrammeProduction.setCout(situationProgrammeProduction.getTotalHeures().multiply(situationProgrammeProduction.getPrix()).divide(situationProgrammeProduction.getQuantite(), 3, RoundingMode.HALF_UP));					
						
						
						
						listSituationProduction.set(j, situationProgrammeProduction)	;
						
						
						
						
					}
					BigDecimal Totalabsents=BigDecimal.ZERO;	
					BigDecimal absents=BigDecimal.ZERO;	
					BigDecimal absentResponsable=BigDecimal.ZERO;	
					BigDecimal EmployeRepos=BigDecimal.ZERO;	
		int d=0;
		while(d<listProductionGroupByDate.size())
		{
			Totalabsents=BigDecimal.ZERO;	
			absents=BigDecimal.ZERO;	
	BigDecimal NombreEmploye=BigDecimal.ZERO;
	absentResponsable=BigDecimal.ZERO;	
	EmployeRepos=BigDecimal.ZERO;	
	Object[] object=listProductionGroupByDate.get(d);	
	
	/*
	
	for(int k=0;k<listSituationProduction.size();k++)
	{
		
		if(listSituationProduction.get(k).getDate().equals((Date)object[0]) )
		{
			
			NombreEmploye=NombreEmploye.add(listSituationProduction.get(k).getNombreEmploye());
		
			
		}
		
		
		
	}
	*/
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////// EmployeRepos ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	listObjectEmployeRepos.clear();
listObjectEmployeRepos=productionDAO.NombreEmployeRepos((Date)object[0],  depot.getCode() ) ;

for(int k=0;k<listObjectEmployeRepos.size();k++)
{
Object[] objectTmp=listObjectEmployeRepos.get(k);	
if(objectTmp[1]!=null)
{
	EmployeRepos=new BigDecimal(objectTmp[1].toString());

}


}
	
	
	
	
	
	//////////////////////////////////////////////////////// les absents Responsable production /////////////////////////////////////////////////////////////////////////
	listObjectEmployeResponsable.clear();
	listObjectEmployeResponsable=productionDAO.NombreEmployeResponsable((Date)object[0], true);
	
	
	for(int k=0;k<listObjectEmployeResponsable.size();k++)
	{
		 Object[] objectTmp=listObjectEmployeResponsable.get(k);	
		 if(objectTmp[1]!=null)
		 {
			 absentResponsable=new BigDecimal(objectTmp[1].toString());
 
		 }
		
		
	}
	
	//////////////////////////////////////////////////////// les autres absents production /////////////////////////////////////////////////////////////////////////
	
	
	
	for(int l=0;l<listSituationProduction.size();l++)
	{
		 if(object[0]!=null)
		 {
				if(listSituationProduction.get(l).getDate().equals((Date)object[0]) )
				{
					/*
					listSituationProduction.get(l).setNumEmploye(NombreEmploye);
					*/
					absents=absents.add(listSituationProduction.get(l).getAbsent())	; 
					
				
				} 
		 }
	
		
		
		
	}
	
///////////////////////////////////////////////////////////////// Total Absent //////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	Totalabsents=absentResponsable.add(absents);
			
	for(int l=0;l<listSituationProduction.size();l++)
	{
		
		if(listSituationProduction.get(l).getDate().equals((Date)object[0]) )
		{
			
			//listSituationProduction.get(l).setNumEmploye(NombreEmploye);
			//listSituationProduction.get(l).setAbsent(Totalabsents);
			listSituationProduction.get(l).setTotalEmploye(listSituationProduction.get(l).getNumEmploye().add(listSituationProduction.get(l).getAbsent().add(listSituationProduction.get(l).getGenerique().add(listSituationProduction.get(l).getAdhesif()).add(listSituationProduction.get(l).getRepos()))));
			
		
		}
		
		
		
	}		
			
		
			
		d++;	
		}
					
					
					
					
 		List<Date>  listdate=new ArrayList<>();
 		boolean existe=false;
 		
 	for(int f=0;f<listSituationProduction.size();f++)
 	{
 		
 		existe=false;
 		
 	for(int j=0;j<listdate.size();j++)
 	{
 		if(listSituationProduction.get(f).getDate().equals(listdate.get(j)))
 		{
 			existe=true;
 		}
 	}
 		
 	if(existe==false)	
 	{
 		
 		mapIdListSisuation.put(listSituationProduction.get(f).getDate(), f);
 		
 		listdate.add(listSituationProduction.get(f).getDate());
 	}
 		
 		
 	}
					
					
	 	BigDecimal totalEmployeImprimer=BigDecimal.ZERO;	
	 	BigDecimal totalNumEmployeImprimer=BigDecimal.ZERO;
	 	BigDecimal totalEmployeGeniriqueImprimer=BigDecimal.ZERO;	
	 	BigDecimal totalEmployeAdhesifImprimer=BigDecimal.ZERO;
	 	BigDecimal totalEmployeAbsentImprimer=BigDecimal.ZERO;
	 	BigDecimal totalEmployeReposImprimer=BigDecimal.ZERO;
	 		
	 	for(int k=0;k<listdate.size();k++)
	 	{
	 		
	 		
	 		
	 		 listObjectEmployeResponsable=productionDAO.NombreEmployeResponsable(listdate.get(k), true);
				
				for(int n=0;n<listObjectEmployeResponsable.size();n++)
				{
					 Object[] object=listObjectEmployeResponsable.get(n);	
					 if(object[1]!=null)
					 {
						 dao.entity.SituationProgrammeProduction situationProgrammeProduction= listSituationProduction.get(mapIdListSisuation.get(listdate.get(k)));
						 situationProgrammeProduction.setAbsent(situationProgrammeProduction.getAbsent().add(new BigDecimal(object[1].toString())));
						 listSituationProduction.set(mapIdListSisuation.get(listdate.get(k)), situationProgrammeProduction)	 ;
						 
						 

					 }
					
					
				}	
				
			
				
				listNombreAdhesif.clear();
				listNombreEmploye.clear();	
				listNombreGenerique.clear();
			 totalNumEmployeImprimer=BigDecimal.ZERO;	
				
			listObjectEmployeProductionParDate=	productionDAO.ListeNombreEmployeProductionParDate(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), false);
				
			for(int l=0;l<listObjectEmployeProductionParDate.size();l++)
			{
				 Object[] objectTmp=listObjectEmployeProductionParDate.get(l);	
				 if(objectTmp[1]!=null)
				 {
					 listNombreEmploye.add(Integer.valueOf(objectTmp[1].toString()))  ;
		 
				 }
				
				
			}
			
			
			
			
			
			
			
			listObjectEmployegeneriqueParDate=	productionDAO.ListeNombreEmployeGeneriqueParDate(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), false);
			boolean trouver=false;
			for(int l=0;l<listObjectEmployegeneriqueParDate.size();l++)
			{
				
				 Object[] objectTmp=listObjectEmployegeneriqueParDate.get(l);	
				 if(objectTmp[1]!=null)
				 {
					 trouver=false;
					 
					for(int t=0;t<listNombreEmploye.size();t++)
					{
						
						if(Integer.valueOf(objectTmp[1].toString()).equals(listNombreEmploye.get(t)) )
						{
							trouver=true;
						}
						
					}
					 
					 if(trouver==false)
					 {
						 listNombreEmploye.add(Integer.valueOf(objectTmp[1].toString())); 
						 
					 }
		 
				 }
				 
				 
				
				
				
			}
			
		
			
			
			trouver=false;
			 totalEmployeAdhesifImprimer=BigDecimal.ZERO;
				
				listObjectEmployeAdhesifParDate=productionDAO.ListeNombreEmployeAdhesif(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), false);
					
				for(int l=0;l<listObjectEmployeAdhesifParDate.size();l++)
				{
					 Object[] objectTmp=listObjectEmployeAdhesifParDate.get(l);	
					 if(objectTmp[1]!=null)
					 {
						 listNombreAdhesif.add(Integer.valueOf(objectTmp[1].toString()));
						 
						 for(int t=0;t<listNombreEmploye.size();t++)
							{
								
								if(Integer.valueOf(objectTmp[1].toString()).equals(listNombreEmploye.get(t)) )
								{
									listNombreEmploye.remove(t);
								}
								
							}
			 
					 }
					
					
				}
			
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Total Absent Par Date  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				 totalEmployeAbsentImprimer=BigDecimal.ZERO;
				 listAbsent.clear();
				 listObjectEmployeProductionParDate=	productionDAO.ListeNombreEmployeProductionParDate(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), true);
				 trouver=false;
					for(int l=0;l<listObjectEmployeProductionParDate.size();l++)
					{
						 Object[] objectTmp=listObjectEmployeProductionParDate.get(l);	
						 if(objectTmp[1]!=null)
						 {
							 trouver=false;
							 for(int t=0;t<listAbsent.size();t++)
								{
									
									if(Integer.valueOf(objectTmp[1].toString()).equals(listAbsent.get(t)) )
									{
										trouver=true;
									}
									
								}
							 
							 if(trouver==false)
							 {
								 listAbsent.add(Integer.valueOf(objectTmp[1].toString())); 
								 
							 }
							 
							  
				 
						 }
						 
						 
						
						
					}
					
					 trouver=false;
					listObjectEmployeAdhesifParDate=	productionDAO.ListeNombreEmployeAdhesif(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), true);
					
					for(int l=0;l<listObjectEmployeAdhesifParDate.size();l++)
					{
						 Object[] objectTmp=listObjectEmployeAdhesifParDate.get(l);	
						 if(objectTmp[1]!=null)
						 {
							 
							 trouver=false;
							 for(int t=0;t<listAbsent.size();t++)
								{
									
									if(Integer.valueOf(objectTmp[1].toString()).equals(listAbsent.get(t)) )
									{
										trouver=true;
									}
									
								}
							 
							 if(trouver==false)
							 {
								 listAbsent.add(Integer.valueOf(objectTmp[1].toString())); 
								 
							 } 
							 
							 
				 
						 }
						
						
					}
					
					
					
					
					
					listObjectEmployegeneriqueParDate=	productionDAO.ListeNombreEmployeGeneriqueParDate(listdate.get(k), Constantes.ETAT_OF_TERMINER, depot.getCode(), true);
					
					 trouver=false;
					for(int l=0;l<listObjectEmployegeneriqueParDate.size();l++)
					{
						 Object[] objectTmp=listObjectEmployegeneriqueParDate.get(l);	
						 if(objectTmp[1]!=null)
						 {
							 trouver=false;
							 for(int t=0;t<listAbsent.size();t++)
								{
									
									if(Integer.valueOf(objectTmp[1].toString()).equals(listAbsent.get(t)) )
									{
										trouver=true;
									}
									
								}
							 
							 if(trouver==false)
							 {
								 listAbsent.add(Integer.valueOf(objectTmp[1].toString())); 
								 
							 } 
				 
						 }
						
						
					}
					
					
					
					
					
					listObjectEmployeResponsable=productionDAO.ListeNombreEmployeResponsable(listdate.get(k), true);			
					 trouver=false;
					for(int l=0;l<listObjectEmployeResponsable.size();l++)
					{
						 Object[] objectTmp=listObjectEmployeResponsable.get(l);	
						 if(objectTmp[1]!=null)
						 {
							 trouver=false;
							 for(int t=0;t<listAbsent.size();t++)
								{
									
									if(Integer.valueOf(objectTmp[1].toString()).equals( listAbsent.get(t)))
									{
										trouver=true;
									}
									
								}
							 
							 if(trouver==false)
							 {
								 listAbsent.add(Integer.valueOf(objectTmp[1].toString())); 
								 
							 } 
				 
						 }
						
						
					}
	 		
	 		
	 		
	 		 totalEmployeImprimer=BigDecimal.ZERO;	
		 	
		 	 totalEmployeGeniriqueImprimer=BigDecimal.ZERO;	
		 	
		 	
		 	 totalEmployeReposImprimer=BigDecimal.ZERO;
	 	for(int s=0;s<listSituationProduction.size();s++)	
	 	{
	 		if(listSituationProduction.get(s).getDate().equals(listdate.get(k)))
	 		{
	 			
	 			totalEmployeGeniriqueImprimer=listSituationProduction.get(s).getGenerique();
	 		
	 			
	 			totalEmployeReposImprimer=listSituationProduction.get(s).getRepos();
	 			
	 		}
	 		
	 		
	 		
	 	}
	 	
	 	
	 	
	 	
	 	
	 	totalNumEmployeImprimer= new BigDecimal(listNombreEmploye.size()); 
	 	totalEmployeAdhesifImprimer= new BigDecimal(listNombreAdhesif.size()); 
	 	totalEmployeAbsentImprimer= new BigDecimal(listAbsent.size()); 
	 		
	 	for(int l=0;l<listSituationProduction.size();l++)	
	 	{
	 		if(listSituationProduction.get(l).getDate().equals(listdate.get(k)))
	 		{
	 			listSituationProduction.get(l).setTotalNumEmployeImprimer (totalNumEmployeImprimer);
	 			listSituationProduction.get(l).setTotalEmployeGeniriqueImprimer(totalEmployeGeniriqueImprimer);
	 			listSituationProduction.get(l).setTotalEmployeAdhesifImprimer(totalEmployeAdhesifImprimer);
	 			listSituationProduction.get(l).setTotalEmployeAbsentImprimer (totalEmployeAbsentImprimer);
	 			listSituationProduction.get(l).setTotalEmployeReposImprimer(totalEmployeReposImprimer);
	 			listSituationProduction.get(l).setTotalEmployeImprimer(listSituationProduction.get(l).getTotalEmployeAbsentImprimer().add(listSituationProduction.get(l).getTotalEmployeAdhesifImprimer().add(listSituationProduction.get(l).getTotalEmployeGeniriqueImprimer().add(listSituationProduction.get(l).getTotalNumEmployeImprimer().add(listSituationProduction.get(l).getTotalEmployeReposImprimer())))));
	 		}
	 		
	 		
	 		
	 	}
	 		
	 	
	 		
	 	}	
					
					
					
				afficher_tableMP(listSituationProduction);
				
				
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

		 	if(listSituationProduction.size()!=0)
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
		 		
		 		


		 		
		 	
		 		if(!dateDu.equals("") || !dateAu.equals(""))
		 		{
		 			parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
		 		}
		 		
		 		
		 		
	
		 		

		 	
		 	
		 	
		 	
		 		
				JasperUtils.imprimerSituationProgrammeProduction(listSituationProduction,parameters); 
		 	
		 		
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
		btnInitialiser.setBounds(1301, 24, 93, 31);
		layeredPane.add(btnInitialiser);
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
	
	
void afficher_tableMP(List<dao.entity.SituationProgrammeProduction> listSituationProduction)
	{
	intialiserTableau2();
	
	int i=0;
	 
	while(i<listSituationProduction.size())
	{	
		dao.entity.SituationProgrammeProduction situationProgrammeProduction=listSituationProduction.get(i);
	
		if(situationProgrammeProduction.getArticle().getId()==1)
		{
			System.out.println(situationProgrammeProduction.getArticle().getLiblle());
		}
		
		
		
		if(situationProgrammeProduction.getArticle().getCentreCout3()!=null)
		{
			
			if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_100G)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_100G);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_100G);
				
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);
				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
if(coutMachineTmp!=null)
{
	situationProgrammeProduction.setCoutMachine(coutMachineTmp);
	situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());

}else
{
	situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

}
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			}
			else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_125G)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_125G);

				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_125G);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);
					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
				
			}
			
			
			
			else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_160G)==0)
			{
				
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_160G);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_160G);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);
				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_200G)==0)
			{
			 
				if(situationProgrammeProduction.getFormeParBox().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
				{
					CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_200G);
					//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_200G);
					CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

					if(coutMachine!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachine.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachine);

					}else
					{
						CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
						if(coutMachineTmp!=null)
						{
							situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
							situationProgrammeProduction.setCoutMachine(coutMachineTmp);


						}else
						{
							situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

						}				
						
					}
					
					situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
					
				}else if(situationProgrammeProduction.getFormeParBox().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE))
				{

					CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_METEL_200G);
					//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_200G);
					CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

					if(coutMachine!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachine.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachine);

					}else
					{
						CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
						if(coutMachineTmp!=null)
						{
							situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
							situationProgrammeProduction.setCoutMachine(coutMachineTmp);


						}else
						{
							situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

						}				
						
					}
					
					situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
					
				
					
				}
				
				

			
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_250G)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_250G);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_250G);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}				
					
				
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_400G)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_400G);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_400G);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}
					
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_450G)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_450G);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_450G);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}				
					
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_500G)==0)
			{
				
				if(situationProgrammeProduction.getFormeParBox().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
				{
					
					CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_500G);
					//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_500G);
					CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

					
					if(coutMachine!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachine.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachine);

					}else
					{
						CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
						if(coutMachineTmp!=null)
						{
							situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
							situationProgrammeProduction.setCoutMachine(coutMachineTmp);


						}else
						{
							situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

						}				
						
					
					}
					
					situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
					
					
				}else if(situationProgrammeProduction.getFormeParBox().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE))
				{
					CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_METEL_500G);
					//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_500G);
					CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

					
					if(coutMachine!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachine.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachine);

					}else
					{
						CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
						if(coutMachineTmp!=null)
						{
							situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
							situationProgrammeProduction.setCoutMachine(coutMachineTmp);


						}else
						{
							situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

						}				
						
					
					}
					
					situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
					
					
				}
					
				
		
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_1KG)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_1KG);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_1KG);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}			
					
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_2KG)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_2KG);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_2KG);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}			
					
				
				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else if(situationProgrammeProduction.getArticle().getCentreCout3().compareTo(BOX_5KG)==0)
			{
				CategorieMp categorieMp=categorieMpDAO.findByCode(Constantes.CODE_CATEGORIE_BOX_5KG);
				//Parametre parametre=parametreDAO.findByLibelle(COUT_BOX_5KG);
				CoutMachine coutMachine=coutMachineDAO.CoutMachineParDateParTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);

				if(coutMachine!=null)
				{
					situationProgrammeProduction.setCout_fix(coutMachine.getCout());
					situationProgrammeProduction.setCoutMachine(coutMachine);

				}else
				{
					CoutMachine coutMachineTmp=coutMachineDAO.CoutMachineParDateParMaxTonnageParForme(situationProgrammeProduction.getDate(), situationProgrammeProduction.getQuantite(), situationProgrammeProduction.getFormeParBox().getForme(),categorieMp);
					if(coutMachineTmp!=null)
					{
						situationProgrammeProduction.setCout_fix(coutMachineTmp.getCout());
						situationProgrammeProduction.setCoutMachine(coutMachineTmp);


					}else
					{
						situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);

					}				}
				
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			
				
			}else
			{
				situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);
				situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
				
			}
			
			
			
		}else
		{
			situationProgrammeProduction.setCout_fix(BigDecimal.ZERO);
			situationProgrammeProduction.setDifference(situationProgrammeProduction.getCout().subtract(situationProgrammeProduction.getCout_fix()));
		}
		
		
		System.out.print(situationProgrammeProduction.getArticle().getLiblle() +" ****** "+situationProgrammeProduction.getFormeParBox().getSubCategorieMp().getNom() +" ******  "+situationProgrammeProduction.getFormeParBox().getForme().getLibelle());
		
		
		listSituationProduction.set(i, situationProgrammeProduction);
			
			
				 Object []ligne={situationProgrammeProduction.getDate(),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getNombreOF()) , NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getTotalEmploye()) ,situationProgrammeProduction.getNumEmploye(), NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getGenerique()), NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getAdhesif()) , NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getAbsent()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getRepos()),situationProgrammeProduction.getMachine().getNom(),situationProgrammeProduction.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getTotalHeures()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getPrix()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getNombreEmploye()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getQuantite()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getCout()) , NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getCout_fix()),NumberFormat.getNumberInstance(Locale.FRANCE).format(situationProgrammeProduction.getDifference()) };
				 modeleMP.addRow(ligne);
			 
				
		
		
		
		i++;
	}
		
		
	
 
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date Production","Nombres OF","Total Employes","N° Employe","Generique","Adhesif","Absente","Repos","Machine","Article","Total Heures","Prix","Nombres Des Employes","Quantite Fabrique","Cout","Cout Fix","Difference"
		     			//"Date Production","Total Heures","Prix","Nombres Des Employes","Quantite Fabrique","Machine","Article"

		     	
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
		 tableSituationManqueEnVrac.getColumnModel().getColumn(6).setPreferredWidth(60);
		  tableSituationManqueEnVrac.getColumnModel().getColumn(7).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(8).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(9).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(10).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(11).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(12).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(13).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(14).setPreferredWidth(60);
		 tableSituationManqueEnVrac.getColumnModel().getColumn(15).setPreferredWidth(60);
}






public void Vider()
{
	
	combomp.setSelectedItem("");
}
}

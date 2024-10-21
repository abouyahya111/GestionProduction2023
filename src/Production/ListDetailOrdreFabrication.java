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

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
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


public class ListDetailOrdreFabrication extends JLayeredPane implements Constantes{
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
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<EtatCoutProduction> listEtatCoutProduction =new ArrayList<EtatCoutProduction>();
	private List<Object[]> listObjetCoutMPParArticle =new ArrayList<Object[]>();
	private List<Production> listObjetQuantiteReelMPParArticle =new ArrayList<Production>();
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
		 JLabel labeltotalCoutGenerique = new JLabel("");
		 JLabel labelTotalCoutProduction = new JLabel("");
		 JLabel labelTotalCoutEmballage = new JLabel("");
		 JLabel labelTotalrealiser = new JLabel("");
		  JLabel labelTotalCoutMp = new JLabel("");
		  JLabel labelTotal = new JLabel("");
		  private ImageIcon imgExcel;
		  JLabel labelCout = new JLabel("");
		  private DetailProdResDAO detailProdResDAO;
			private DetailProductionDAO detailProductionDAO;
			
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ListDetailOrdreFabrication() {
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
        	 detailProdResDAO=new DetailProdResDAOImpl();
          	  detailProductionDAO=new DetailProductionDAOImpl();
        	 CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 1120, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1120, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(158, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		 
		dateDebutChooser.setBounds(37, 11, 111, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(191, 11, 124, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(325, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(373, 12, 149, 24);
		layeredPane.add(combodepot);
		
		
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
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label = new JLabel("Code Article");
		  label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  label.setBounds(533, 10, 67, 24);
		  layeredPane.add(label);
		  
		   combocodearticle = new JComboBox();
		   combocodearticle.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		

		 		
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapCodeArticle.get(combocodearticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			
			  			comboBoxArticle.setSelectedItem (articles.getLiblle());	
			  	
			
	     	 		}else
	     	 		{
	     	 			comboBoxArticle.setSelectedItem ("");	
	     	 		}
             
	     	 	 	}
	     	 	
	  		
	  		
	  		
	  		
	  	
		 		
		 		
		 	
		   		
		   		
		   	}
		   });
		  combocodearticle.setBounds(610, 12, 138, 24);
		  layeredPane.add(combocodearticle);
		  AutoCompleteDecorator.decorate(combocodearticle);
		  JLabel label_1 = new JLabel("Libelle Article");
		  label_1.setBounds(775, 10, 108, 26);
		  layeredPane.add(label_1);
		  
		   comboBoxArticle = new JComboBox();
		   comboBoxArticle.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		

		 		

	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			

		  			combocodearticle.setSelectedItem (articles.getCodeArticle());	
		  		
	     	 			
	     	 		}else
	     	 		{
	     	 			combocodearticle.setSelectedItem ("");	
	     	 		}
                 
	     	 	 	}
	     	 	
	 		
	 		
	 		
	 		
	 	
		   		
		   	}
		   });
		  comboBoxArticle.setBounds(858, 11, 252, 24);
		  layeredPane.add(comboBoxArticle);
		  AutoCompleteDecorator.decorate(comboBoxArticle);
		  
		  JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		  gridBagLayout.rowWeights = new double[]{0.0};
		  gridBagLayout.rowHeights = new int[]{0};
		  gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_1.setTitle("Cout Production");
		  titledSeparator_1.setBackground(Color.RED);
		  titledSeparator_1.setBounds(9, 76, 1120, 30);
		  add(titledSeparator_1);
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(1160, 21, 31, 31);
		  add(btnAfficherStock);
		  btnAfficherStock.setIcon(imgRechercher);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
		  		String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
		  	if(dateDebut.equals(""))	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date DÈbut", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	} else if(dateFin.equals("")){
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else if(combodepot.getSelectedIndex()==-1)
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	}else
		  	
		  	{
		  		
		  		CalculerCoutProductionParproduction();
		  		
		  		/*
		  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
		  		Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
		  		listEtatCoutProduction.clear();
		  		listObjetCoutDetailProdGenParArticle.clear();
		  		listObjetCoutMPParArticle.clear();
		  		listObjetCoutDetailProdResParArticle.clear();
		  		listObjetCoutDetailProductionParArticle.clear();
		  		listObjetQuantiteReelMPParArticle.clear();
		  		
		  		listObjetQuantiteReelMPParArticle=coutMPDAO.listeSumQuantiteReelParProduction (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutMPParArticle=coutMPDAO.listeCoutMPParProduction(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProdGenParArticle=coutMPDAO.listeCoutDetailProdGenParProduction (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProdResParArticle=coutMPDAO.listeCoutDetailProdResParProduction (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProductionParArticle=coutMPDAO.listeCoutDetailProductionParProduction (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		
		  		listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.ListHeursCoutHorsProdEnAttentParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(), Constantes.ETAT_OF_TERMINER, "");
		  		
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Quantite Realise des Articles   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  		
		  		for(int j=0;j<listObjetQuantiteReelMPParArticle.size();j++)
				{
					 	
					
					Production production=	listObjetQuantiteReelMPParArticle.get(j);
						
						EtatCoutProduction etatCoutProduction=new EtatCoutProduction();
						
						etatCoutProduction.setProduction(production);
						etatCoutProduction.setArticle(production.getArticles());
						etatCoutProduction.setCoutMP(BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeGenerique(BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeProduction (BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeEmballage(BigDecimal.ZERO);
						etatCoutProduction.setQuantiteRealiser(production.getQuantiteReel());
						etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
						etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
						listEtatCoutProduction.add(etatCoutProduction);
						
					
					
				
				
				}
		  		
		  		
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts MP   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  		
		  		
		  		for(int j=0;j<listObjetCoutMPParArticle.size();j++)
				{
					 Object[] object=listObjetCoutMPParArticle.get(j);	
					if(object[0]!=null)
					{
						Production production=(Production)object[0];
						
					for(int d=0;d<listEtatCoutProduction.size();d++)
					{
						
						EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);
						
						
					if(production.getId()==etatCoutProduction.getProduction().getId())	
					{
						
						if(object[1]!=null)
						{
							
							etatCoutProduction.setCoutMP(etatCoutProduction.getCoutMP().add(new BigDecimal(object[1].toString())));
							etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
							etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			
							listEtatCoutProduction.set(d, etatCoutProduction);
						}
						
						
						
						
						
					}
						
						
					}
						
						
						
						
						
						
						
					}
					
				
				
				}
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Prod Res   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  	
		  		
		  		BigDecimal coutTotal=BigDecimal.ZERO;
		  		BigDecimal cout25=BigDecimal.ZERO;
		  		BigDecimal cout50=BigDecimal.ZERO;
		 		for(int j=0;j<listObjetCoutDetailProdResParArticle.size();j++)
				{
		 			
		 			 coutTotal=BigDecimal.ZERO;
			  		 cout25=BigDecimal.ZERO;
			  		 cout50=BigDecimal.ZERO;	
					 Object[] object=listObjetCoutDetailProdResParArticle.get(j);	
					if(object[0]!=null)
					{
						Integer idproduction=(Integer)object[0];
						
						if(object[1]!=null)
						{
							coutTotal=new BigDecimal(object[1].toString());

						}
						
						if(object[2]!=null)
						{
						cout25=new BigDecimal(object[2].toString());
						
						}
						if(object[3]!=null)
						{
							cout50=new BigDecimal(object[3].toString());
						}
						
						
						
					for(int d=0;d<listEtatCoutProduction.size();d++)
					{
						
						EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);
						
						
					if(idproduction==etatCoutProduction.getProduction().getId())	
					{
						
						
							
							etatCoutProduction.setCoutEmployeGenerique(etatCoutProduction.getCoutEmployeGenerique().add(coutTotal.add(cout50.add(cout25))));
							etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
							etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			
							listEtatCoutProduction.set(d, etatCoutProduction);
						
						
						
						
						
						
					}
						
						
					}
						
						
						
						
						
						
						
					}
					
				
				
				}
		  		
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Production   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
			  	
		  		

for(int j=0;j<listObjetCoutDetailProductionParArticle.size();j++)
{

coutTotal=BigDecimal.ZERO;
cout25=BigDecimal.ZERO;
cout50=BigDecimal.ZERO;	
Object[] object=listObjetCoutDetailProductionParArticle.get(j);	
if(object[0]!=null)
{
	Integer idproduction=(Integer)object[0];

if(object[1]!=null)
{
coutTotal=new BigDecimal(object[1].toString());

}

if(object[2]!=null)
{
cout25=new BigDecimal(object[2].toString());

}
if(object[3]!=null)
{
cout50=new BigDecimal(object[3].toString());
}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(idproduction==etatCoutProduction.getProduction().getId())	
{



etatCoutProduction.setCoutEmployeProduction (etatCoutProduction.getCoutEmployeProduction().add(coutTotal.add(cout50.add(cout25))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}



}



}	



/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Employer Hors Production   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
	
	

for(int j=0;j<listCoutHorsProductionEnAttent.size();j++)
{


	CoutHorsProdEnAttent  coutHorsProdEnAttent=listCoutHorsProductionEnAttent.get(j);	





for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(coutHorsProdEnAttent.getProduction().getId()==etatCoutProduction.getProduction().getId())	
{



etatCoutProduction.setCoutEmployeProduction (etatCoutProduction.getCoutEmployeProduction().add(coutHorsProdEnAttent.getCoutTotal().add(coutHorsProdEnAttent.getCoutHoraire50().add(coutHorsProdEnAttent.getCoutHoraire25()))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}







}







		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Emballage (prodgen)   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
	
	

for(int j=0;j<listObjetCoutDetailProdGenParArticle.size();j++)
{

coutTotal=BigDecimal.ZERO;
cout25=BigDecimal.ZERO;
cout50=BigDecimal.ZERO;	
Object[] object=listObjetCoutDetailProdGenParArticle.get(j);	
if(object[0]!=null)
{
	Integer idproduction=(Integer)object[0];

if(object[1]!=null)
{
coutTotal=new BigDecimal(object[1].toString());

}

if(object[2]!=null)
{
cout25=new BigDecimal(object[2].toString());

}
if(object[3]!=null)
{
cout50=new BigDecimal(object[3].toString());
}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(idproduction==etatCoutProduction.getProduction().getId())	
{



etatCoutProduction.setCoutEmployeEmballage(etatCoutProduction.getCoutEmployeEmballage().add(coutTotal.add(cout50.add(cout25))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}



}



}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
if(etatCoutProduction.getQuantiteRealiser().compareTo(BigDecimal.ZERO)!=0)
{
	etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

}

listEtatCoutProduction.set(d, etatCoutProduction);









}



afficher_tableMP(listEtatCoutProduction);


*/


		  		
		
		  	}
		    }
		  });
		  btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(9, 117, 1256, 604);
		  add(scrollPane);
		  
		  table = new JXTable();
		  table.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  			"Date", "OF","CODE ARTICLE", "ARTICLE", "COUT MP", "COUT G.GENERIQUE", "COUT G.PRODUCTION", "G.EMBALLAGE", "TOTAL", "QTE REALISEE", "Cout / KG"
		  	}
		  ));
		  table.setColumnSelectionAllowed(true);
		  scrollPane.setViewportView(table);
		  
		  JButton button = new JButton();
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {

		  		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		
		  		if(listEtatCoutProduction.size()!=0)
		  		{
		  		
					if(dateDebutChooser.getDate()!=null && dateFinChooser.getDate()==null)
					{
						dateFinChooser.setDate(dateDebutChooser.getDate());
					}else if(dateDebutChooser.getDate() == null && dateFinChooser.getDate()!=null)
					{
						dateDebutChooser.setDate(dateFinChooser.getDate());
					}
					
					
					String dateDu=dateFormat.format(dateDebutChooser.getDate());
					String	dateAu=dateFormat.format(dateFinChooser.getDate());
					
					
		  			Map parameters = new HashMap();
			 		parameters.put("magasin", combodepot.getSelectedItem().toString());			  		
			 		parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
					JasperUtils.imprimerCoutProductionParProduction(parameters,  listEtatCoutProduction); 
		  		}
		  		
		  		
		  		
		  	
		  		
		  		
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(662, 748, 104, 31);
		  button.setIcon(imgImprimer);
		  add(button);
		  
		   labelTotalCoutMp = new JLabel("");
		   labelTotalCoutMp.setForeground(Color.RED);
		  labelTotalCoutMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutMp.setBounds(9, 732, 550, 24);
		  add(labelTotalCoutMp);
		  
		   labeltotalCoutGenerique = new JLabel("");
		   labeltotalCoutGenerique.setForeground(Color.RED);
		  labeltotalCoutGenerique.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labeltotalCoutGenerique.setBounds(9, 768, 550, 24);
		  add(labeltotalCoutGenerique);
		  
		   labelTotalCoutProduction = new JLabel("");
		   labelTotalCoutProduction.setForeground(Color.RED);
		  labelTotalCoutProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutProduction.setBounds(9, 803, 550, 24);
		  add(labelTotalCoutProduction);
		  
		   labelTotalCoutEmballage = new JLabel("");
		   labelTotalCoutEmballage.setForeground(Color.RED);
		  labelTotalCoutEmballage.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutEmballage.setBounds(9, 838, 550, 24);
		  add(labelTotalCoutEmballage);
		  
		   labelTotal = new JLabel("");
		   labelTotal.setForeground(Color.RED);
		  labelTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotal.setBounds(9, 874, 550, 24);
		  add(labelTotal);
		  
		   labelTotalrealiser = new JLabel("");
		   labelTotalrealiser.setForeground(Color.RED);
		  labelTotalrealiser.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalrealiser.setBounds(9, 913, 550, 24);
		  add(labelTotalrealiser);
		  
		  JButton button_1 = new JButton("Exporter Excel");
		  button_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		

				


				 	
					
				if(table.getRowCount()!=0)
				{
				 
					
					
					
					
					String titre="List Detail Ordre fabrication ";
		    		String titrefeuilleexcel="List Detail Ordre fabrication ";
		    		ExporterTableVersExcel.tabletoexcelListDetailOrdreFabrication (table, titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			
		
		
				
				
				
				
				
				
			
				
				
				
				
				
			
				
			
		  		
		  		
		  		
		  	}
		  });
		  button_1.setBounds(782, 745, 156, 34);
		  button_1.setIcon(imgExcel);
		  add(button_1);
		  
		   labelCout = new JLabel("");
		  labelCout.setForeground(Color.RED);
		  labelCout.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelCout.setBounds(9, 967, 550, 24);
		  add(labelCout);
		
		
	
		ChargerComboArticle();		  		     
				  		 
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
	
	
	
	void afficher_tableMP(List<EtatCoutProduction> listEtatCoutProduction)
	{
	intialiserTableau();
		 
		int i=0;
		
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);
		
		 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		BigDecimal TotalcoutMP=BigDecimal.ZERO;
		BigDecimal TotalcoutGenerique=BigDecimal.ZERO;
		BigDecimal TotalcoutProduction=BigDecimal.ZERO;
		BigDecimal TotalcoutEmballage=BigDecimal.ZERO;
		BigDecimal TotalcoutTotal=BigDecimal.ZERO;
		BigDecimal TotalRealiser=BigDecimal.ZERO;
		BigDecimal cout=BigDecimal.ZERO;
		
		while(i<listEtatCoutProduction.size())
		{	
			EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(i);
		
		
			String date="";
			
			date=dateFormat.format(etatCoutProduction.getProduction().getDate());
			
			TotalcoutMP=TotalcoutMP.add(etatCoutProduction.getCoutMP());
			TotalcoutGenerique=TotalcoutGenerique.add(etatCoutProduction.getCoutEmployeGenerique());
			TotalcoutProduction=TotalcoutProduction.add(etatCoutProduction.getCoutEmployeProduction());
			TotalcoutEmballage=TotalcoutEmballage.add(etatCoutProduction.getCoutEmployeEmballage());
			TotalcoutTotal=TotalcoutTotal.add(etatCoutProduction.getTotal());
			TotalRealiser=TotalRealiser.add(etatCoutProduction.getQuantiteRealiser());
			
					 Object []ligne={date,etatCoutProduction.getProduction().getNumOF(),  etatCoutProduction.getProduction().getArticles().getCodeArticle(),etatCoutProduction.getProduction().getArticles().getLiblle(),dfDecimal.format(etatCoutProduction.getCoutMP().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getCoutEmployeGenerique().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getCoutEmployeProduction().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getCoutEmployeEmballage().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getTotal().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getQuantiteRealiser().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(etatCoutProduction.getCout().setScale(2, RoundingMode.HALF_UP))};
					 modeleMP.addRow(ligne);
					
							 
			
			
			
			i++;
		}
		
		labelTotalCoutMp.setText("Total Cout MP : "+dfDecimal.format( TotalcoutMP.setScale(2, RoundingMode.HALF_UP)));
		labeltotalCoutGenerique.setText("Total Cout Generique : "+dfDecimal.format(TotalcoutGenerique.setScale(2, RoundingMode.HALF_UP)));
		labelTotalCoutProduction.setText("Total Cout production : "+dfDecimal.format(TotalcoutProduction.setScale(2, RoundingMode.HALF_UP)));
		labelTotalCoutEmballage.setText("Total Cout Emballage : "+dfDecimal.format(TotalcoutEmballage.setScale(2, RoundingMode.HALF_UP)));
		labelTotal.setText("Total Cout Total : "+dfDecimal.format(TotalcoutTotal.setScale(2, RoundingMode.HALF_UP)));
		labelTotalrealiser.setText("Total RÈaliser : "+dfDecimal.format(TotalRealiser.setScale(2, RoundingMode.HALF_UP)));
		if(TotalRealiser.compareTo(BigDecimal.ZERO)!=0)
		{
			labelCout.setText("Cout Moyen : "+TotalcoutTotal.divide(TotalRealiser, 2, RoundingMode.HALF_UP)+"");
		}	
		
	}
	
	
	void intialiserTableau(){
		 modeleMP =new DefaultTableModel(
			     	new Object[][] {
			     	},
			     	new String[] {
				  			"Date", "OF","CODE ARTICLE", "ARTICLE", "COUT MP", "COUT G.GENERIQUE", "COUT G.PRODUCTION", "G.EMBALLAGE", "TOTAL", "QTE REALISEE", "Cout / KG"
			     	}
			     ) {
			     	boolean[] columnEditables = new boolean[] {
			     			false,false,false,false,false,false,false,false,false,false,false
			     	};
			     	public boolean isCellEditable(int row, int column) {
			     		return columnEditables[column];
			     	}
			     };
			     
			     table.setModel(modeleMP); 
			     table.getColumnModel().getColumn(0).setPreferredWidth(60);
			     table.getColumnModel().getColumn(1).setPreferredWidth(60);
			     table.getColumnModel().getColumn(2).setPreferredWidth(60);
			     table.getColumnModel().getColumn(3).setPreferredWidth(120);
			     table.getColumnModel().getColumn(4).setPreferredWidth(60);
			     table.getColumnModel().getColumn(5).setPreferredWidth(60);
			     table.getColumnModel().getColumn(6).setPreferredWidth(60);
			     table.getColumnModel().getColumn(7).setPreferredWidth(60);
			     table.getColumnModel().getColumn(8).setPreferredWidth(60);
			     table.getColumnModel().getColumn(9).setPreferredWidth(60);
			     table.getColumnModel().getColumn(10).setPreferredWidth(60);
	}
	
	
	
	void CalculerCoutProductionParproduction()
	{
		
		Depot depot=mapDepot.get(combodepot.getSelectedItem());
  		Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
  		
  		listObjetQuantiteReelMPParArticle.clear();
  		
  		listObjetQuantiteReelMPParArticle=coutMPDAO.listeSumQuantiteReelParProduction (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);

  		
  		for(int j=0;j<listObjetQuantiteReelMPParArticle.size();j++)
		{
			 	
			
			Production production=	listObjetQuantiteReelMPParArticle.get(j);
				
				EtatCoutProduction etatCoutProduction=new EtatCoutProduction();
				
				etatCoutProduction.setProduction(production);
				etatCoutProduction.setArticle(production.getArticles());
				etatCoutProduction.setCoutMP(BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeGenerique(BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeProduction (BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeEmballage(BigDecimal.ZERO);
				etatCoutProduction.setQuantiteRealiser(BigDecimal.ZERO);
				etatCoutProduction.setTotal(BigDecimal.ZERO);
				etatCoutProduction.setCout(BigDecimal.ZERO);
				listEtatCoutProduction.add(etatCoutProduction);
				
			
			
		
		
		}
  		
  		
  		
  		
  	for(int s=0;s<listEtatCoutProduction.size();s++)
  	{
  		
  		EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(s);	
  		
  		BigDecimal coutTotalEmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalEmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalEmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
		BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
		BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
		BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
		BigDecimal coutTotalPlus= BigDecimal.ZERO;
		BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
		BigDecimal QuantiteReel= BigDecimal.ZERO;
		
	 
			
			 
			
			QuantiteReel=QuantiteReel.add(etatCoutProduction.getProduction().getQuantiteReel());
			listCoutMP=etatCoutProduction.getProduction().getListCoutMP();
			listEmployeGesnerique= detailProdResDAO.ListHeursDetailResponsableProdParDepot(etatCoutProduction.getProduction().getDate(), etatCoutProduction.getProduction().getDate(), etatCoutProduction.getProduction().getMagasinStockage().getDepot().getId(),"");
			listEmployeEmballage=etatCoutProduction.getProduction().getListDetailProdGen();
			listEmployeProduction=detailProductionDAO.ListEmployeDetailProductionByProduction(etatCoutProduction.getProduction());
			
			
			listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.findByProduction(etatCoutProduction.getProduction());
			
			for(int k=0;k<listCoutMP.size();k++)
			{
				
				coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getCoutDechet());
				coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getCoutOffre());
				coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(listCoutMP.get(k).getQuantConsomme().multiply(listCoutMP.get(k).getPrixUnitaire()).subtract(listCoutMP.get(k).getQuantiteManquanteFrPlus().multiply(listCoutMP.get(k).getPrixUnitaire())));
				coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getCoutDechetFournisseur());
				coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getCoutManquante());
			}
			
		
			for(int i=0;i<listEmployeGesnerique.size();i++)
			{
				coutTotalEmployeGenerique=coutTotalEmployeGenerique.add((listEmployeGesnerique.get(i).getDelaiEmploye().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraire()));
				coutTotalSupp25EmployeGenerique=coutTotalSupp25EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp25().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp25()));
				coutTotalSupp50EmployeGenerique=coutTotalSupp50EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp50().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp50()));
			}
			
			for(int j=0;j<listEmployeProduction.size();j++)
			{
				coutTotalEmployeProduction=coutTotalEmployeProduction.add(listEmployeProduction.get(j).getCoutTotal());
				coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp25());
				coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp50());
				
			}
			
			for(int j=0;j<listCoutHorsProductionEnAttent.size();j++)
			{
				coutTotalEmployeProduction=coutTotalEmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutTotal());
				coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutSupp25());
				coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutSupp50());
				
			}
			
			boolean existe=false;
			for(int t=0;t<listCoutHorsProductionEnAttent.size();t++)
			{
				existe=false;
				
				for(int d=0;d<listEmployeProduction.size();d++)
				{
					
					if(listEmployeProduction.get(d).getEmploye().getId()==listCoutHorsProductionEnAttent.get(t).getEmploye().getId())
					{
						
						existe=true;
						listEmployeProduction.get(d).setDelaiEmploye(listEmployeProduction.get(d).getDelaiEmploye().add(listCoutHorsProductionEnAttent.get(t).getDelaiEmploye()));	
						listEmployeProduction.get(d).setHeureSupp25(listEmployeProduction.get(d).getHeureSupp25().add(listCoutHorsProductionEnAttent.get(t).getHeure25()));
						listEmployeProduction.get(d).setHeureSupp50(listEmployeProduction.get(d).getHeureSupp50().add(listCoutHorsProductionEnAttent.get(t).getHeure50()));
						listEmployeProduction.get(d).setCoutTotal(listEmployeProduction.get(d).getCoutTotal().add(listCoutHorsProductionEnAttent.get(t).getCoutTotal()));
						listEmployeProduction.get(d).setCoutSupp25 (listEmployeProduction.get(d).getCoutSupp25().add(listCoutHorsProductionEnAttent.get(t).getCoutSupp25()));
						listEmployeProduction.get(d).setCoutSupp50 (listEmployeProduction.get(d).getCoutSupp50().add(listCoutHorsProductionEnAttent.get(t).getCoutSupp50()));
						
					}
					
					
					
				}
				
				if(existe==false)
				{
					
					DetailProduction detailProduction=new DetailProduction();	
					
					detailProduction.setEmploye(listCoutHorsProductionEnAttent.get(t).getEmploye());
					detailProduction.setDelaiEmploye(listCoutHorsProductionEnAttent.get(t).getDelaiEmploye());
					detailProduction.setHeureSupp25(listCoutHorsProductionEnAttent.get(t).getHeure25());
					detailProduction.setHeureSupp50(listCoutHorsProductionEnAttent.get(t).getHeure50());
					detailProduction.setCoutTotal(listCoutHorsProductionEnAttent.get(t).getCoutTotal());
					detailProduction.setCoutSupp25(listCoutHorsProductionEnAttent.get(t).getCoutSupp25());
					detailProduction.setCoutSupp50(listCoutHorsProductionEnAttent.get(t).getCoutSupp50());
					
					listEmployeProduction.add(detailProduction);
					
					
				}
				
			
			
			}
			
			
			
			
			
			for(int j=0;j<listEmployeEmballage.size();j++)
			{
				coutTotalEmployeEmballage=coutTotalEmployeEmballage.add(listEmployeEmballage.get(j).getCoutTotal());
				coutTotalSupp25EmployeEmballage=coutTotalSupp25EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp25());
				coutTotalSupp50EmployeEmballage=coutTotalSupp50EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp50());
			}
			
			
	 
  		
  		
		
		
		etatCoutProduction.setCoutMP(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP)))));
		etatCoutProduction.setCoutEmployeGenerique(coutTotalEmployeGenerique);
		etatCoutProduction.setCoutEmployeProduction (coutTotalEmployeProduction);
		etatCoutProduction.setCoutEmployeEmballage(coutTotalEmployeEmballage);
		etatCoutProduction.setQuantiteRealiser(QuantiteReel);
		etatCoutProduction.setTotal( (coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP)));
		etatCoutProduction.setCout(((coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP))) .divide(etatCoutProduction.getQuantiteRealiser(), 2, RoundingMode.HALF_UP));	
		
		listEtatCoutProduction.set(s, etatCoutProduction);
  		
  		
  	}
  		
  		
  	afficher_tableMP(listEtatCoutProduction);	
  		
  		
  		
  		
  		
  		
  		
	}
	
	
	
}

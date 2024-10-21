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
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
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


public class CoutParArticle extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	

	List<Production> listProductions=new ArrayList<Production>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<DetailProdRes> listEmployeGesnerique=new ArrayList<DetailProdRes>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	   private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
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
	private List<Object[]> listObjetCoutMPProductionParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutProductionParArticle =new ArrayList<Object[]>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private JTextField txtCodeArticle;
	private JTextField txtLibelle;
	private JTextField txtcoutmp;
	private JTextField txtcoutdechetmanque;
	private JTextField txtcoutdechet;
	private JTextField txtQuantiteReel;
	private JTextField txtcoutemploye;
	private JTextField txtcoutdechetfournisseur;
	List < Articles> listArticles = new ArrayList<Articles>();
	 JComboBox combocodearticle = new JComboBox();
	 JComboBox comboBoxArticle = new JComboBox();
	 private Map< String, Articles> mapCodeArticle= new HashMap<>();
		private Map< String, Articles> mapLibelleAricle = new HashMap<>();
		private ArticlesDAO articlesDAO;
		private JTextField txtcoutemployeemballage;
		private JTextField txtcoutemployegenerique;
		private JTextField txtprixmp;
		private JTextField txtpourcentagemp;
		private JTextField txtprixdechet;
		private JTextField txtpourcentagedechet;
		private JTextField txtprixdechetfournisseur;
		private JTextField txtpourcentagedechetfournisseur;
		private JTextField txtprixdechetmanque;
		private JTextField txtpourcentagedechetmanque;
		private JTextField txtprixemployee;
		private JTextField txtpourcentageemploye;
		private JTextField txtprixemployeemballage;
		private JTextField txtpourcentageemployeemballage;
		private JTextField txtprixemployegenerique;
		private JTextField txtpourcentageemployegenerique;
		private JTextField txtprixunitaire;
		private DetailProdResDAO detailProdResDAO;
		private DetailProductionDAO detailProductionDAO;
		 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CoutParArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao= new DepotDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	  detailProdResDAO=new DetailProdResDAOImpl();
        	  detailProductionDAO=new DetailProductionDAOImpl();
        	  CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
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
		  
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCodeArticle.setBounds(9, 117, 122, 23);
		  add(lblCodeArticle);
		  
		  txtCodeArticle = new JTextField();
		  txtCodeArticle.setEditable(false);
		  txtCodeArticle.setColumns(10);
		  txtCodeArticle.setBounds(240, 117, 182, 30);
		  add(txtCodeArticle);
		  
		  JLabel lblLibelle = new JLabel("Libelle :");
		  lblLibelle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblLibelle.setBounds(447, 114, 122, 23);
		  add(lblLibelle);
		  
		  txtLibelle = new JTextField();
		  txtLibelle.setEditable(false);
		  txtLibelle.setColumns(10);
		  txtLibelle.setBounds(624, 114, 259, 30);
		  add(txtLibelle);
		  
		  JLabel lblDateProduction = new JLabel("Cout MP :");
		  lblDateProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblDateProduction.setBounds(9, 155, 122, 23);
		  add(lblDateProduction);
		  
		  txtcoutmp = new JTextField();
		  txtcoutmp.setEditable(false);
		  txtcoutmp.setColumns(10);
		  txtcoutmp.setBounds(240, 154, 182, 30);
		  add(txtcoutmp);
		  
		  JLabel lblQuantitDemand = new JLabel("Cout Dechet Manque :");
		  lblQuantitDemand.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitDemand.setBounds(9, 274, 174, 23);
		  add(lblQuantitDemand);
		  
		  txtcoutdechetmanque = new JTextField();
		  txtcoutdechetmanque.setEditable(false);
		  txtcoutdechetmanque.setColumns(10);
		  txtcoutdechetmanque.setBounds(240, 271, 182, 30);
		  add(txtcoutdechetmanque);
		  
		  JLabel lblPeriode = new JLabel("Cout Dechet :");
		  lblPeriode.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPeriode.setBounds(9, 189, 122, 23);
		  add(lblPeriode);
		  
		  txtcoutdechet = new JTextField();
		  txtcoutdechet.setEditable(false);
		  txtcoutdechet.setColumns(10);
		  txtcoutdechet.setBounds(240, 189, 182, 30);
		  add(txtcoutdechet);
		  
		  JLabel lblQuantitRel = new JLabel("Quantit\u00E9 R\u00E9el :");
		  lblQuantitRel.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitRel.setBounds(9, 439, 122, 23);
		  add(lblQuantitRel);
		  
		  txtQuantiteReel = new JTextField();
		  txtQuantiteReel.setEditable(false);
		  txtQuantiteReel.setColumns(10);
		  txtQuantiteReel.setBounds(240, 436, 186, 30);
		  add(txtQuantiteReel);
		  
		  JLabel lblCoutTotal = new JLabel("Cout Employe :");
		  lblCoutTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotal.setBounds(9, 315, 122, 23);
		  add(lblCoutTotal);
		  
		  txtcoutemploye = new JTextField();
		  txtcoutemploye.setEditable(false);
		  txtcoutemploye.setColumns(10);
		  txtcoutemploye.setBounds(240, 307, 182, 30);
		  add(txtcoutemploye);
		  
		  JLabel lblPrix = new JLabel("Cout Dechet Fournisseur  :");
		  lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrix.setBounds(9, 225, 174, 23);
		  add(lblPrix);
		  
		  txtcoutdechetfournisseur = new JTextField();
		  txtcoutdechetfournisseur.setEditable(false);
		  txtcoutdechetfournisseur.setColumns(10);
		  txtcoutdechetfournisseur.setBounds(240, 225, 182, 30);
		  add(txtcoutdechetfournisseur);
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(1160, 21, 31, 31);
		  add(btnAfficherStock);
		  btnAfficherStock.setIcon(imgRechercher);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
		  		String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
		  	if(dateDebut.equals(""))	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	} else if(dateFin.equals("")){
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else if(combodepot.getSelectedIndex()==-1)
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	}else if(comboBoxArticle.getSelectedItem()==null)
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut selectionner l'Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else if(comboBoxArticle.getSelectedItem().equals(""))
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut selectionner l'Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else if(comboBoxArticle.getSelectedIndex()==-1)
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut selectionner l'Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else
		  	
		  	{
		  		
		  		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
				symbols.setGroupingSeparator(' ');
				DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
				dfDecimal.setDecimalFormatSymbols(symbols);
				dfDecimal.setGroupingSize(3);
				dfDecimal.setGroupingUsed(true);
		  		
		  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
		  		Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
		  		/*
		  		
		  		listObjetCoutMPProductionParArticle.clear();
		  		listObjetCoutProductionParArticle.clear();
		  		listObjetCoutMPProductionParArticle=productionDAO.listeCoutMPProductionParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutProductionParArticle=productionDAO.listeCoutProductionParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		
		  		
				for(int j=0;j<listObjetCoutProductionParArticle.size();j++)
				{
					 Object[] object=listObjetCoutProductionParArticle.get(j);	
					if(object[0]==null)
					{
						vider();
						return;
					}
					txtQuantiteReel.setText(new BigDecimal(object[0].toString()).toString());
					txtcoutemployegenerique.setText(new BigDecimal(object[1].toString()).toString());
					txtcoutemployeemballage.setText(new BigDecimal(object[2].toString()).toString());
					txtcoutemploye.setText(new BigDecimal(object[3].toString()).toString());
					
					
				}
		  		
		  		
		  		
				for(int k=0;k<listObjetCoutMPProductionParArticle.size();k++)
				{
					 Object[] object=listObjetCoutMPProductionParArticle.get(k);	
				
					System.out.print("Code Article "+object[0].toString());
					System.out.print(" Article "+object[1].toString());
					txtCodeArticle.setText(object[0].toString());
					txtLibelle.setText(object[1].toString());
					txtcoutmp.setText(new BigDecimal(object[2].toString()).toString());
					txtcoutdechet.setText(new BigDecimal(object[3].toString()).toString());
					txtcoutdechetfournisseur.setText(new BigDecimal(object[4].toString()).toString());	
					txtcoutdechetmanque.setText(new BigDecimal(object[5].toString()).toString());
					
					
					
				}
				*/
				
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
		  		listProductions=productionDAO.listeProductionTerminerbyDepotEntreDeuxDateByArticle(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
				
				for(int s=0;s<listProductions.size();s++)
				{
					
					Production production=	listProductions.get(s);
					
					QuantiteReel=QuantiteReel.add(production.getQuantiteReel());
					listCoutMP=production.getListCoutMP();
					listEmployeGesnerique= detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
					listEmployeEmballage=production.getListDetailProdGen();
					listEmployeProduction=detailProductionDAO.ListEmployeDetailProductionByProduction(production);
					
					
					listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.findByProduction(production);
					
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
					
					
				}
				
				txtQuantiteReel.setText(QuantiteReel.toString());
				txtcoutemployegenerique.setText(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)+"");
				txtcoutemployeemballage.setText(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP)+"");
				txtcoutemploye.setText(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)+"");	
			 
				txtCodeArticle.setText(articles.getCodeArticle());
				txtLibelle.setText(articles.getLiblle());
				txtcoutmp.setText(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP)+"");
				txtcoutdechet.setText(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP)+"");
				txtcoutdechetfournisseur.setText(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)+"");	
				txtcoutdechetmanque.setText(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP)+"");
				
				
				
		  		
			//	NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(object[2].toString()))
				
				
				
				
				
				txtprixmp.setText(new BigDecimal(txtcoutmp.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixdechet.setText(new BigDecimal(txtcoutdechet.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixdechetfournisseur.setText(new BigDecimal(txtcoutdechetfournisseur.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixdechetmanque.setText(new BigDecimal(txtcoutdechetmanque.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixemployee.setText(new BigDecimal(txtcoutemploye.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixemployeemballage.setText(new BigDecimal(txtcoutemployeemballage.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixemployegenerique.setText(new BigDecimal(txtcoutemployegenerique.getText()).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
				txtprixunitaire.setText( (new BigDecimal(txtcoutmp.getText()).add(new BigDecimal(txtcoutdechet.getText())).add(new BigDecimal(txtcoutdechetfournisseur.getText())).add(new BigDecimal(txtcoutdechetmanque.getText())).add(new BigDecimal(txtcoutemploye.getText())).add(new BigDecimal(txtcoutemployeemballage.getText())).add(new BigDecimal(txtcoutemployegenerique.getText()))).divide(new BigDecimal(txtQuantiteReel.getText()), 6, RoundingMode.FLOOR).toString());
		  		
				txtpourcentagemp.setText(new BigDecimal(txtprixmp.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
				txtpourcentagedechet.setText(new BigDecimal(txtprixdechet.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
				txtpourcentagedechetfournisseur.setText(new BigDecimal(txtprixdechetfournisseur.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
		  		txtpourcentagedechetmanque.setText(new BigDecimal(txtprixdechetmanque.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
		  		txtpourcentageemploye.setText(new BigDecimal(txtprixemployee.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
		  		txtpourcentageemployeemballage.setText(new BigDecimal(txtprixemployeemballage.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+" %");
		  		txtpourcentageemployegenerique.setText(new BigDecimal(txtprixemployegenerique.getText()).divide(new BigDecimal(txtprixunitaire.getText()) , 6 ,RoundingMode.FLOOR).multiply(new BigDecimal(100))+ " %");
		  	
		  	}
		    }
		  });
		  btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  txtcoutemployeemballage = new JTextField();
		  txtcoutemployeemballage.setEditable(false);
		  txtcoutemployeemballage.setColumns(10);
		  txtcoutemployeemballage.setBounds(240, 349, 186, 30);
		  add(txtcoutemployeemballage);
		  
		  JLabel lblCoutEmployeEmballage = new JLabel("Cout Employe Emballage :");
		  lblCoutEmployeEmballage.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutEmployeEmballage.setBounds(13, 357, 170, 23);
		  add(lblCoutEmployeEmballage);
		  
		  JLabel lblCoutEmployeGenerique = new JLabel("Cout Employe Generique:");
		  lblCoutEmployeGenerique.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutEmployeGenerique.setBounds(13, 398, 170, 23);
		  add(lblCoutEmployeGenerique);
		  
		  txtcoutemployegenerique = new JTextField();
		  txtcoutemployegenerique.setEditable(false);
		  txtcoutemployegenerique.setColumns(10);
		  txtcoutemployegenerique.setBounds(240, 390, 186, 30);
		  add(txtcoutemployegenerique);
		  
		  JLabel lblPrixMp = new JLabel("Prix MP :");
		  lblPrixMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixMp.setBounds(447, 155, 122, 23);
		  add(lblPrixMp);
		  
		  txtprixmp = new JTextField();
		  txtprixmp.setEditable(false);
		  txtprixmp.setColumns(10);
		  txtprixmp.setBounds(624, 155, 182, 30);
		  add(txtprixmp);
		  
		  txtpourcentagemp = new JTextField();
		  txtpourcentagemp.setEditable(false);
		  txtpourcentagemp.setColumns(10);
		  txtpourcentagemp.setBounds(834, 157, 182, 30);
		  add(txtpourcentagemp);
		  
		  JLabel lblPrixDechet = new JLabel("Prix Dechet:");
		  lblPrixDechet.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixDechet.setBounds(447, 187, 122, 23);
		  add(lblPrixDechet);
		  
		  txtprixdechet = new JTextField();
		  txtprixdechet.setEditable(false);
		  txtprixdechet.setColumns(10);
		  txtprixdechet.setBounds(624, 187, 182, 30);
		  add(txtprixdechet);
		  
		  txtpourcentagedechet = new JTextField();
		  txtpourcentagedechet.setEditable(false);
		  txtpourcentagedechet.setColumns(10);
		  txtpourcentagedechet.setBounds(834, 189, 182, 30);
		  add(txtpourcentagedechet);
		  
		  JLabel lblPrixDechetFournisseur = new JLabel("Prix Dechet Fournisseur:");
		  lblPrixDechetFournisseur.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixDechetFournisseur.setBounds(447, 225, 167, 23);
		  add(lblPrixDechetFournisseur);
		  
		  txtprixdechetfournisseur = new JTextField();
		  txtprixdechetfournisseur.setEditable(false);
		  txtprixdechetfournisseur.setColumns(10);
		  txtprixdechetfournisseur.setBounds(624, 225, 182, 30);
		  add(txtprixdechetfournisseur);
		  
		  txtpourcentagedechetfournisseur = new JTextField();
		  txtpourcentagedechetfournisseur.setEditable(false);
		  txtpourcentagedechetfournisseur.setColumns(10);
		  txtpourcentagedechetfournisseur.setBounds(834, 227, 182, 30);
		  add(txtpourcentagedechetfournisseur);
		  
		  JLabel lblPrixDechetManque = new JLabel("Prix Dechet Manque :");
		  lblPrixDechetManque.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixDechetManque.setBounds(447, 274, 167, 23);
		  add(lblPrixDechetManque);
		  
		  txtprixdechetmanque = new JTextField();
		  txtprixdechetmanque.setEditable(false);
		  txtprixdechetmanque.setColumns(10);
		  txtprixdechetmanque.setBounds(624, 274, 182, 30);
		  add(txtprixdechetmanque);
		  
		  txtpourcentagedechetmanque = new JTextField();
		  txtpourcentagedechetmanque.setEditable(false);
		  txtpourcentagedechetmanque.setColumns(10);
		  txtpourcentagedechetmanque.setBounds(834, 276, 182, 30);
		  add(txtpourcentagedechetmanque);
		  
		  JLabel lblPrixEmployee = new JLabel("Prix Employee :");
		  lblPrixEmployee.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixEmployee.setBounds(447, 307, 167, 23);
		  add(lblPrixEmployee);
		  
		  txtprixemployee = new JTextField();
		  txtprixemployee.setEditable(false);
		  txtprixemployee.setColumns(10);
		  txtprixemployee.setBounds(624, 307, 182, 30);
		  add(txtprixemployee);
		  
		  txtpourcentageemploye = new JTextField();
		  txtpourcentageemploye.setEditable(false);
		  txtpourcentageemploye.setColumns(10);
		  txtpourcentageemploye.setBounds(834, 309, 182, 30);
		  add(txtpourcentageemploye);
		  
		  JLabel lblPrixEmployeeEmballage = new JLabel("Prix Employee Emballage :");
		  lblPrixEmployeeEmballage.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixEmployeeEmballage.setBounds(447, 349, 167, 23);
		  add(lblPrixEmployeeEmballage);
		  
		  txtprixemployeemballage = new JTextField();
		  txtprixemployeemballage.setEditable(false);
		  txtprixemployeemballage.setColumns(10);
		  txtprixemployeemballage.setBounds(624, 349, 182, 30);
		  add(txtprixemployeemballage);
		  
		  txtpourcentageemployeemballage = new JTextField();
		  txtpourcentageemployeemballage.setEditable(false);
		  txtpourcentageemployeemballage.setColumns(10);
		  txtpourcentageemployeemballage.setBounds(834, 351, 182, 30);
		  add(txtpourcentageemployeemballage);
		  
		  JLabel lblPrixEmployeeGenerique = new JLabel("Prix Employee Generique :");
		  lblPrixEmployeeGenerique.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixEmployeeGenerique.setBounds(447, 389, 167, 23);
		  add(lblPrixEmployeeGenerique);
		  
		  txtprixemployegenerique = new JTextField();
		  txtprixemployegenerique.setEditable(false);
		  txtprixemployegenerique.setColumns(10);
		  txtprixemployegenerique.setBounds(624, 389, 182, 30);
		  add(txtprixemployegenerique);
		  
		  txtpourcentageemployegenerique = new JTextField();
		  txtpourcentageemployegenerique.setEditable(false);
		  txtpourcentageemployegenerique.setColumns(10);
		  txtpourcentageemployegenerique.setBounds(834, 391, 182, 30);
		  add(txtpourcentageemployegenerique);
		  
		  JLabel lblPrixUnitaire = new JLabel("Prix Unitaire:");
		  lblPrixUnitaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrixUnitaire.setBounds(447, 442, 122, 23);
		  add(lblPrixUnitaire);
		  
		  txtprixunitaire = new JTextField();
		  txtprixunitaire.setEditable(false);
		  txtprixunitaire.setColumns(10);
		  txtprixunitaire.setBounds(624, 436, 186, 30);
		  add(txtprixunitaire);
		
		
	
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
	
	
	
	void vider()
	{
		txtCodeArticle.setText("");
		txtcoutdechet.setText("");
		txtcoutdechetfournisseur.setText("");
		txtcoutdechetmanque.setText("");
		txtcoutemploye.setText("");
		txtcoutemployeemballage.setText("");
		txtcoutemployegenerique.setText("");
		txtcoutmp.setText("");
		txtLibelle.setText("");
		txtpourcentagedechet.setText("");
		txtpourcentagedechetfournisseur.setText("");
		txtpourcentagedechetmanque.setText("");
		txtpourcentageemploye.setText("");
		txtpourcentageemployeemballage.setText("");
		txtpourcentageemployegenerique.setText("");
		txtpourcentagemp.setText("");
		txtprixdechet.setText("");
		txtprixdechetfournisseur.setText("");
		txtprixdechetmanque.setText("");
		txtprixemployee.setText("");
		txtprixemployeemballage.setText("");
		txtprixemployegenerique.setText("");
		txtQuantiteReel.setText("");
		txtprixunitaire.setText("");
		txtprixmp.setText("");
	}
	
	
}

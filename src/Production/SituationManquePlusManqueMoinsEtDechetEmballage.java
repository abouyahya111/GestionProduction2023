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
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
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
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.SituationManquePlusEnVrac;
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


public class SituationManquePlusManqueMoinsEtDechetEmballage extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableSituationManqueEnVrac;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgExcel;
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
	private Map< String, SubCategorieMp> mapsubcategorie = new HashMap<>();
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();

	
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
	JComboBox comboClient = new JComboBox();
	  JComboBox comboFournisseur = new JComboBox();
	  JComboBox comboPlusMoins = new JComboBox();
	  CategorieMpDAO categorieMpDAO;
	  JLabel totalmoins = new JLabel("0.00");
	  JLabel totalplus = new JLabel("0.00");
	  JLabel totalfabrique = new JLabel("0.00");
	  private JLabel totalDechetFournisseur;
	  JLabel totalcharger = new JLabel("0.00");
	  private JLabel totalDechet;
	  JLabel differenceplusmoins = new JLabel("0.00");
	  private JTextField txtcodemp;
	  JComboBox combocategorie = new JComboBox();
		JComboBox combomp = new JComboBox();
		JComboBox comboOF = new JComboBox();
		JComboBox comboDechetusineFournisseur = new JComboBox();
		JLabel totalMontantDechet = new JLabel("0.00");
		JLabel totalMontantDechetFournisseur = new JLabel("0.00");
		JLabel totalMontantplus = new JLabel("0.00");
		JLabel totalMontantmoins = new JLabel("0.00");
		JLabel differenceMontantplusmoins = new JLabel("0.00");
		 private Map< String, String> mapClient = new HashMap<>();
		 
		 BigDecimal  totalmoinsCalculer=BigDecimal.ZERO ;
		 BigDecimal totalPlusCalculer=BigDecimal.ZERO ;
		 BigDecimal totalDechetCalculer=BigDecimal.ZERO ;
		 BigDecimal  totalMontantmoinsCalculer=BigDecimal.ZERO ;
		 BigDecimal totalMontantPlusCalculer=BigDecimal.ZERO ;
		 BigDecimal totalMontantDechetCalculer=BigDecimal.ZERO ;
		 BigDecimal totalDechetFournisseurCalculer=BigDecimal.ZERO ;
			BigDecimal totalMontantDechetFournisseurCalculer=BigDecimal.ZERO ; 
		
			private List<SituationManquePlusEnVrac> listSituationManquePlusEnVrac=new ArrayList<SituationManquePlusEnVrac>();
		 
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationManquePlusManqueMoinsEtDechetEmballage() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 892);
        try{
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	categorieMpDAO=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	ArticleDAO=new ArticlesDAOImpl();
        	  listArticle=ArticleDAO.findAll();
        	  manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
          	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	
        	listProduction=productionDAO.listeProductionEtatTerminer(Constantes.ETAT_OF_TERMINER, utilisateur.getCodeDepot());
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgExcel= new ImageIcon(this.getClass().getResource("/img/excel.png"));
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
					  		     			"Num Production","Date Production","Article","MP","Fournisseur","Quantite Dechet","Quantite Dechet Fournisseur","Manque Plus","Manque Moins"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,false
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
				  			     			"Num Production","Date Production","Article","MP","Fournisseur","Quantite Dechet Usine","Quantite Dechet Fournisseur","Manque Plus","Manque Moins"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false
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
				  		     	layeredPane.setBounds(9, 11, 1479, 134);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1259, 11, 93, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCoutMP.clear();

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
				
				totalMontantDechetCalculer=BigDecimal.ZERO ;
				totalDechetCalculer=BigDecimal.ZERO ;
				
				
				Depot depot=mapDepotSource.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				Articles articles=mapArticle.get(comboarticle.getSelectedItem());
				SubCategorieMp subCategorieMp=mapsubcategorie.get(combocategorie.getSelectedItem());
				MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
				Production production=mapProduction.get(comboOF.getSelectedItem());
				String client=mapClient.get(comboClient.getSelectedItem());
				
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
				if(dateChooserDu.getDate()!=null)
				{
					dateChooserDu.setDateFormatString("yyyy-MM-dd");
				}
				if(dateChooserAu.getDate()!=null)
				{
					dateChooserAu.setDateFormatString("yyyy-MM-dd");
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
				
					
					if(depot==null || magasin==null)
					{
JOptionPane.showMessageDialog(null, "Veuillez Selectionner Depot et Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}
				
				
				String requete=" where prodcutionCM.codeDepot='"+depot.getCode()+"'";
				requete=requete+" and prodcutionCM.magasinStockage.id= '"+magasin.getId()+"'";
				
				requete=requete+" and prodcutionCM.statut= '"+ Constantes.ETAT_OF_TERMINER+"'";
				
				
				if(!dateDu.equals("") || !dateAu.equals(""))
				{
					requete=requete+" and prodcutionCM.date between '"+dateDu+"' and '"+dateAu+"'";
				}
				
				 if(articles!=null)
				 {
					 requete=requete+" and prodcutionCM.articles.id= '"+articles.getId()+"'";
				 }
				
				if(subCategorieMp!=null)
				{
					 requete=requete+" and matierePremier.categorieMp.subCategorieMp.id ='"+subCategorieMp.getId()+"'";
				}else
				{
					 requete=requete+" and matierePremier.categorieMp.subCategorieMp.id not in (1)";
				}
				
				if(matierePremier!=null)
				{
					 requete=requete+" and matierePremier.id = '"+matierePremier.getId()+"'";
				}
				
				 if(client!=null)
				 {
					 
					 requete=requete+" and matierePremier.type= '"+client+"'"; 
				 }

if(production!=null)
{
	
	requete=requete+" and prodcutionCM.numOF= '"+production.getNumOF()+"'";
}
				 
				 if(!comboFournisseur.getSelectedItem().equals(""))
				 {
					 requete=requete+" and (codeFournisseur like  '%, "+ comboFournisseur.getSelectedItem().toString()+"%' or codeFournisseur like  '%"+comboFournisseur.getSelectedItem().toString()+", %' or codeFournisseur='"+comboFournisseur.getSelectedItem().toString()+"' or codeFournisseurdechet like  '%, "+ comboFournisseur.getSelectedItem().toString()+"%' or codeFournisseurdechet like  '%"+comboFournisseur.getSelectedItem().toString()+", %' or codeFournisseurdechet='"+comboFournisseur.getSelectedItem().toString()+"' )  "; 
					
					 
				 }
				 
				 if(!comboPlusMoins.getSelectedItem().equals(""))
				 {
					 
					 if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_PLUS))
					 {
						 requete=requete+" and quantiteManquanteFrPlus >0 "; 
					 }else if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_MOINS))
					 {
						 requete=requete+" and quantiteManquante >0 ";  
					 }
					  
				 }
				 
			
				 
			
				 
				
				 listCoutMP=productionDAO.listeSituationManqueEtPLus(requete) ;
					listSituationManquePlusEnVrac.clear();	 
					 boolean existe=false;
					for(int i=0;i<listCoutMP.size();i++)
					{
						existe=false;
						
						CoutMP coutMP=listCoutMP.get(i);
						
						ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(coutMP.getProdcutionCM().getNumOF(),Constantes.TYPE_DECHET_FOURNISSEUR);	
if(manqueDechetFournisseur!=null)
{
	listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByIdMPByManqueDechetFournisseur(manqueDechetFournisseur.getId(), coutMP.getMatierePremier().getId());
	
	
	
	for(int j=0;j<listDetailManqueDechetFournisseur.size();j++)
	{
		
		DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(j);
		
		if(detailManqueDechetFournisseur.getEtat().equals(Constantes.ETAT_VALIDER))
		{
			
			 if(!comboFournisseur.getSelectedItem().equals(""))
			 {
				 if(detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur().equals(comboFournisseur.getSelectedItem()))
				 {
					 
					 SituationManquePlusEnVrac situationManquePlusEnVrac=new SituationManquePlusEnVrac();
						situationManquePlusEnVrac.setProdcutionCM(coutMP.getProdcutionCM());
						situationManquePlusEnVrac.setMatierePremier(coutMP.getMatierePremier());
						situationManquePlusEnVrac.setFournisseurMP(detailManqueDechetFournisseur.getFourniseur());
						situationManquePlusEnVrac.setQuantExistante(coutMP.getQuantExistante());
						situationManquePlusEnVrac.setQuantCharge(coutMP.getQuantCharge());
						situationManquePlusEnVrac.setQuantChargeSupp(coutMP.getQuantChargeSupp());
						situationManquePlusEnVrac.setQuantConsomme(coutMP.getQuantConsomme());
						situationManquePlusEnVrac.setQuantiteOffre(coutMP.getQuantiteOffre());
						situationManquePlusEnVrac.setQuantReste(coutMP.getQuantReste());
						situationManquePlusEnVrac.setQuantiteManquanteFrPlus(detailManqueDechetFournisseur.getQuantitePlus());
						situationManquePlusEnVrac.setQuantiteManquante(detailManqueDechetFournisseur.getQuantiteManque());
						situationManquePlusEnVrac.setPrixUnitaire(coutMP.getPrixUnitaire().setScale(2, RoundingMode.HALF_UP));
						
						situationManquePlusEnVrac.setQuantDechet(coutMP.getQuantDechet());
						situationManquePlusEnVrac.setQuantDechetFournisseur(detailManqueDechetFournisseur.getQuantiteDechet());
						situationManquePlusEnVrac.setMontantPlus(situationManquePlusEnVrac.getQuantiteManquanteFrPlus().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setMontantMoins(situationManquePlusEnVrac.getQuantiteManquante().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setMontantDechetUsine(coutMP.getQuantDechet().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					
						
						
						situationManquePlusEnVrac.setMontantDechetFournisseur (situationManquePlusEnVrac.getQuantDechetFournisseur().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setCodeFournisseur(detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur());
						
						listSituationManquePlusEnVrac.add(situationManquePlusEnVrac); 
				 }
				
				 
				 
				 
			 }else
			 {
				 SituationManquePlusEnVrac situationManquePlusEnVrac=new SituationManquePlusEnVrac();
					situationManquePlusEnVrac.setProdcutionCM(coutMP.getProdcutionCM());
					situationManquePlusEnVrac.setMatierePremier(coutMP.getMatierePremier());
					situationManquePlusEnVrac.setFournisseurMP(detailManqueDechetFournisseur.getFourniseur());
					situationManquePlusEnVrac.setQuantExistante(coutMP.getQuantExistante());
					situationManquePlusEnVrac.setQuantCharge(coutMP.getQuantCharge());
					situationManquePlusEnVrac.setQuantChargeSupp(coutMP.getQuantChargeSupp());
					situationManquePlusEnVrac.setQuantConsomme(coutMP.getQuantConsomme());
					situationManquePlusEnVrac.setQuantiteOffre(coutMP.getQuantiteOffre());
					situationManquePlusEnVrac.setQuantReste(coutMP.getQuantReste());
					situationManquePlusEnVrac.setQuantiteManquanteFrPlus(detailManqueDechetFournisseur.getQuantitePlus());
					situationManquePlusEnVrac.setQuantiteManquante(detailManqueDechetFournisseur.getQuantiteManque());
					situationManquePlusEnVrac.setPrixUnitaire(coutMP.getPrixUnitaire().setScale(2, RoundingMode.HALF_UP));
					
					situationManquePlusEnVrac.setQuantDechet(coutMP.getQuantDechet());
					situationManquePlusEnVrac.setQuantDechetFournisseur(detailManqueDechetFournisseur.getQuantiteDechet());
					
					situationManquePlusEnVrac.setMontantPlus(situationManquePlusEnVrac.getQuantiteManquanteFrPlus().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					situationManquePlusEnVrac.setMontantMoins(situationManquePlusEnVrac.getQuantiteManquante().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					situationManquePlusEnVrac.setMontantDechetUsine(situationManquePlusEnVrac.getQuantDechet().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					
					situationManquePlusEnVrac.setMontantDechetFournisseur (situationManquePlusEnVrac.getQuantDechetFournisseur().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					situationManquePlusEnVrac.setCodeFournisseur(detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur());
					
					listSituationManquePlusEnVrac.add(situationManquePlusEnVrac);
			 }
			
			existe=true;
			
		}
		
		 
		
	}
	
	
	if(existe==false)
	{
		
		
     
		
		
		SituationManquePlusEnVrac situationManquePlusEnVrac=new SituationManquePlusEnVrac();
		situationManquePlusEnVrac.setProdcutionCM(coutMP.getProdcutionCM());
		situationManquePlusEnVrac.setMatierePremier(coutMP.getMatierePremier());
		situationManquePlusEnVrac.setFournisseurMP(coutMP.getFournisseurMP());
		situationManquePlusEnVrac.setQuantExistante(coutMP.getQuantExistante());
		situationManquePlusEnVrac.setQuantCharge(coutMP.getQuantCharge());
		situationManquePlusEnVrac.setQuantChargeSupp(coutMP.getQuantChargeSupp());
		situationManquePlusEnVrac.setQuantConsomme(coutMP.getQuantConsomme());
		situationManquePlusEnVrac.setQuantiteOffre(coutMP.getQuantiteOffre());
		situationManquePlusEnVrac.setQuantReste(coutMP.getQuantReste());
		situationManquePlusEnVrac.setQuantiteManquanteFrPlus(coutMP.getQuantiteManquanteFrPlus());
		situationManquePlusEnVrac.setQuantiteManquante(coutMP.getQuantiteManquante());
		situationManquePlusEnVrac.setPrixUnitaire(coutMP.getPrixUnitaire().setScale(2, RoundingMode.HALF_UP));
		situationManquePlusEnVrac.setQuantDechet(coutMP.getQuantDechet());
		situationManquePlusEnVrac.setQuantDechetFournisseur(coutMP.getQuantDechetFournisseur());
		situationManquePlusEnVrac.setMontantPlus(situationManquePlusEnVrac.getQuantiteManquanteFrPlus().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
		situationManquePlusEnVrac.setMontantMoins(situationManquePlusEnVrac.getQuantiteManquante().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
		situationManquePlusEnVrac.setMontantDechetUsine(situationManquePlusEnVrac.getQuantDechet().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
		situationManquePlusEnVrac.setMontantDechetFournisseur (situationManquePlusEnVrac.getQuantDechetFournisseur().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
		situationManquePlusEnVrac.setCodeFournisseur(coutMP.getCodeFournisseur());
		listSituationManquePlusEnVrac.add(situationManquePlusEnVrac);
		 
	}
	
	totalDechetCalculer=totalDechetCalculer.add(coutMP.getQuantDechet());			
	totalMontantDechetCalculer=	totalMontantDechetCalculer.add(coutMP.getQuantDechet().multiply(coutMP.getPrixUnitaire().setScale(2, RoundingMode.HALF_UP)))	;
	
}
			
						
						
						
						
			    
						
				 
				
				
					}
				 
				afficher_tableMP(listSituationManquePlusEnVrac);
				
				
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
	

		 	if(listSituationManquePlusEnVrac.size()!=0)
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
		 		parameters.put("totaldechet", totalDechetCalculer);
		 		parameters.put("totalMontantdechet", totalMontantDechetCalculer);
		 		
		 		


		 		
		 		if(comboPlusMoins.getSelectedItem().equals(""))	
		 		{
		 			parameters.put("titre", "SITUATION MANQUE PLUS/ MOINS ET DECHET EMBALLAGE ");
		 		}else if(!comboPlusMoins.getSelectedItem().equals(""))
		 		{
		 			
		 			if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_PLUS))
		 			{
		 				parameters.put("titre", "SITUATION MANQUE PLUS ET DECHET EMBALLAGE");
		 			}else if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_MOINS))
		 			{
		 				parameters.put("titre", "SITUATION MANQUE MOINS ET DECHET EMBALLAGE");
		 			}
		 			
		 			
		 		}
		 		if(!dateDu.equals("") || !dateAu.equals(""))
		 		{
		 			parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
		 		}
		 		
		 		
				
		 		
		 		 if(!comboDechetusineFournisseur.getSelectedItem().equals(""))
		 		 {
		 			 
		 			 if(comboDechetusineFournisseur.getSelectedItem().equals(Constantes.DECHET_USINE))
		 			 {
		 				 
		 				JasperUtils.imprimerSituationManquePlusEtMoinsEmballageDechetUsine(listSituationManquePlusEnVrac,parameters); 
		 				 

		 			 }else if(comboDechetusineFournisseur.getSelectedItem().equals(Constantes.DECHET_FOURNISSEUR))
		 			 {

		 				JasperUtils.imprimerSituationManquePlusEtMoinsEmballageDechetFournisseur (listSituationManquePlusEnVrac,parameters); 
		 			 
		 			 }
		 			  
		 		 }else
		 		 {
		 			 
		 			JasperUtils.imprimerSituationManquePlusEtMoinsEmballage(listSituationManquePlusEnVrac,parameters); 
		 			
		 		 }
		 		
				
		 	
		 		
		 	}		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(80, 647, 104, 31);
		 add(button_1);
		 comboFournisseur = new JComboBox();
			comboFournisseur.setBounds(543, 57, 218, 27);
			layeredPane.add(comboFournisseur);

				
				
				
				
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
		  		  
		  		  JLabel label_3 = new JLabel("Code Article :");
		  		  label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		  label_3.setBounds(10, 57, 74, 26);
		  		  layeredPane.add(label_3);
		  		  
		  		  txtcodearticle = new JTextField();
		  		  txtcodearticle.addKeyListener(new KeyAdapter() {
		  		  	@Override
		  		  	public void keyPressed(KeyEvent e) {
		  		  		

			    		

			     		

		     			if(e.getKeyCode()==e.VK_ENTER)
				      		{
		     				
		     					
				      			if(!txtcodearticle.getText().equals(""))
				      			{
				      				Articles article=mapCodeArticle.get(txtcodearticle.getText().toUpperCase());
				      				
						    		
						    		if(article!=null)
						    		{	
						    			comboarticle.setSelectedItem(article.getLiblle());
						    			
						    		}else
						    		{
						    			 JOptionPane.showMessageDialog(null, "Code Article Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						    		
						    			
						    		}
				      				
				      				
				      		}else
				      		{
				      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				      			
				      			
				      		}
		     				
		     				
				      	
		     				
		     				
				      		}
			     			
			     	
			    	
		  		  		
		  		  	}
		  		  });
		  		  txtcodearticle.setText("");
		  		  txtcodearticle.setColumns(10);
		  		  txtcodearticle.setBounds(82, 57, 93, 26);
		  		  layeredPane.add(txtcodearticle);
		  		  
		  		  JLabel label_4 = new JLabel("Libelle :");
		  		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		  label_4.setBounds(185, 57, 45, 26);
		  		  layeredPane.add(label_4);
		  		  
		  		   comboarticle = new JComboBox();
		  		   comboarticle.addItemListener(new ItemListener() {
		  		   	public void itemStateChanged(ItemEvent arg0) {

			     		
			     		
			 			if(!comboarticle.getSelectedItem().equals(""))
				 		{
				 			Articles article=mapArticle.get(comboarticle.getSelectedItem());
				 			txtcodearticle.setText(article.getCodeArticle());
				 			
				 		  				 			
				 		}else
				 		{
		txtcodearticle.setText("");
				 			
				 		}
				 	
			 		
			     	
		  		   		
		  		   		
		  		   		
		  		   	}
		  		   });
		  		  comboarticle.setBounds(230, 57, 218, 27);
		  		  layeredPane.add(comboarticle);
				
		  	    comboarticle.addItem("");
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
			
			JLabel lblFournisseur = new JLabel("Fournisseur :");
			lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFournisseur.setBounds(471, 57, 73, 26);
			layeredPane.add(lblFournisseur);
			
			
			
			JLabel lblPlusMoins = new JLabel("Plus / Moins :");
			lblPlusMoins.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPlusMoins.setBounds(781, 57, 73, 26);
			layeredPane.add(lblPlusMoins);
			
			 comboPlusMoins = new JComboBox();
			comboPlusMoins.setBounds(853, 57, 218, 27);
			layeredPane.add(comboPlusMoins);
				
		comboPlusMoins.addItem("");	
		comboPlusMoins.addItem(Constantes.MANQUE_PLUS);	
		comboPlusMoins.addItem(Constantes.MANQUE_MOINS);		
		
		listFournisseur=fournisseurMPDAO.findAll();
		comboFournisseur.addItem("");
		
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
				txtcodemp.setText(Constantes.CODE_MP);
			}
		});
		btnInitialiser.setText("Initialiser");
		btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInitialiser.setBounds(1376, 11, 93, 31);
		layeredPane.add(btnInitialiser);
		
		JLabel lblCodeMp = new JLabel("Code MP:");
		lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodeMp.setBounds(323, 93, 74, 26);
		layeredPane.add(lblCodeMp);
		
		txtcodemp = new JTextField();
		txtcodemp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				     		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtcodemp.getText().equals(""))
		      			{
		      				MatierePremier matierePremier=mapCodeMatierePremiere.get(txtcodemp.getText().toUpperCase());
		      				
				    		
				    		if(matierePremier!=null)
				    		{	
				    			combomp.setSelectedItem(matierePremier.getNom());
				    			
				    		}else
				    		{
				    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				    		
				    			
				    		}
		      				
		      				
		      		}else
		      		{
		      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		      			
		      			
		      		}
     				
     				
		      	
     				
     				
		      		}
	     			
	     	
	    	
  		  		
  		  	
				
				
				
			}
		});
		txtcodemp.setText("");
		txtcodemp.setColumns(10);
		txtcodemp.setBounds(395, 93, 93, 26);
		layeredPane.add(txtcodemp);
		
		JLabel lblMp = new JLabel("MP:");
		lblMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMp.setBounds(498, 93, 45, 26);
		layeredPane.add(lblMp);
		
		 combomp = new JComboBox();
		 combomp.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		
		 		if(combomp.getSelectedIndex()!=-1)
		 		{
		 			
		 			if(!combomp.getSelectedItem().equals(""))
		 			{
		 				
		 				MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem().toString());
		 				
		 				if(matierePremier!=null)
		 				{
		 					txtcodemp.setText(matierePremier.getCode());
		 					
		 					
		 				}else
		 				{
		 					txtcodemp.setText(Constantes.CODE_MP);
		 				}
		 				
		 			}else
		 			{
		 				txtcodemp.setText(Constantes.CODE_MP);
		 			}
		 			
		 			
		 			
		 			
		 		}else
		 		{
		 			txtcodemp.setText(Constantes.CODE_MP);
		 		}
		 		
		 		
		 	}
		 });
		combomp.setBounds(543, 93, 266, 27);
		layeredPane.add(combomp);
		AutoCompleteDecorator.decorate(combomp);
		
		JLabel lblCategorie = new JLabel("Categorie :");
		lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCategorie.setBounds(10, 92, 74, 26);
		layeredPane.add(lblCategorie);
		
		 combocategorie = new JComboBox();
		 combocategorie.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		
		 		
		 		if(!combocategorie.getSelectedItem().equals(""))
		 		{
		 			
		 			
		 			
		 			SubCategorieMp subcategorieMp=mapsubcategorie.get(combocategorie.getSelectedItem());
		 			combomp.removeAllItems();
		 			txtcodemp.setText("");
		 			mapCodeMatierePremiere.clear();
		 			mapMatierePremiere.clear();
		 			listMatierePremiere=matierePremiereDAO.listeMatierePremierBycategorieBySubCategorieByMP(subcategorieMp, null, null);
		 			
		 			combomp.addItem("");
		 			for(int k=0;k<listMatierePremiere.size();k++)
		 			{
		 				MatierePremier matierePremier=listMatierePremiere.get(k);
		 				combomp.addItem(matierePremier.getNom());
		 				mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
		 				mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
		 				
		 				
		 				
		 			}
		 			
		 			
		 			
		 		}else
		 		{
		 			combomp.removeAllItems();
		 			txtcodemp.setText("");
		 			combomp.addItem("");
		 		}
		 		
		 		
		 		
		 		
		 	}
		 });
		combocategorie.setBounds(92, 92, 221, 27);
		layeredPane.add(combocategorie);
		
		 totalmoins = new JLabel("0.00");
		totalmoins.setHorizontalAlignment(SwingConstants.CENTER);
		totalmoins.setForeground(Color.RED);
		totalmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalmoins.setBounds(1178, 639, 252, 39);
		add(totalmoins);
		
		 totalplus = new JLabel("0.00");
		totalplus.setHorizontalAlignment(SwingConstants.CENTER);
		totalplus.setForeground(Color.RED);
		totalplus.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalplus.setBounds(936, 639, 231, 39);
		add(totalplus);
		
		 totalDechetFournisseur = new JLabel("0.00");
		totalDechetFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		totalDechetFournisseur.setForeground(Color.RED);
		totalDechetFournisseur.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalDechetFournisseur.setBounds(611, 639, 294, 39);
		add(totalDechetFournisseur);
		
		 totalDechet = new JLabel("0.00");
		totalDechet.setHorizontalAlignment(SwingConstants.CENTER);
		totalDechet.setForeground(Color.RED);
		totalDechet.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalDechet.setBounds(296, 639, 294, 39);
		add(totalDechet);
		
		 differenceplusmoins = new JLabel("0.00");
		differenceplusmoins.setHorizontalAlignment(SwingConstants.CENTER);
		differenceplusmoins.setForeground(Color.RED);
		differenceplusmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		differenceplusmoins.setBounds(946, 690, 484, 39);
		add(differenceplusmoins);
		for(int j=0;j<listFournisseur.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(j);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		combocategorie.addItem("");
		
		JLabel lblOf = new JLabel("OF :");
		lblOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOf.setBounds(819, 94, 45, 26);
		layeredPane.add(lblOf);
		
		 comboOF = new JComboBox();
		comboOF.setBounds(853, 94, 163, 27);
		layeredPane.add(comboOF);
		
		listsubcategoriemp=subcategoriempdao.findAllSauf(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			for(int j=0;j<listsubcategoriemp.size();j++)
			{
				SubCategorieMp subCategorieMp=listsubcategoriemp.get(j);
				combocategorie.addItem(subCategorieMp.getNom());
				mapsubcategorie.put(subCategorieMp.getNom(), subCategorieMp);
			}
			

		
		
		comboOF.addItem("");
		
		JLabel lblDusineDfournisseur = new JLabel("D.Usine / D.Fournisseur :");
		lblDusineDfournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDusineDfournisseur.setBounds(1026, 93, 127, 26);
		layeredPane.add(lblDusineDfournisseur);
		
		 comboDechetusineFournisseur = new JComboBox();
		comboDechetusineFournisseur.setBounds(1150, 93, 182, 27);
		layeredPane.add(comboDechetusineFournisseur);
		
		for(int k=0;k<listProduction.size();k++ ) 
		
		{
			
			Production production=listProduction.get(k);
			comboOF.addItem(production.getNumOF());
			mapProduction.put(production.getNumOF(), production);
		}
		
		
		
		comboDechetusineFournisseur.addItem("");	
		comboDechetusineFournisseur.addItem(Constantes.DECHET_USINE);	
		comboDechetusineFournisseur.addItem(Constantes.DECHET_FOURNISSEUR);		  		 
		
		JLabel label_5 = new JLabel("Client :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(1093, 53, 55, 26);
		layeredPane.add(label_5);
		
		 comboClient = new JComboBox();
		comboClient.setBounds(1148, 53, 202, 27);
		layeredPane.add(comboClient);
		
		 totalMontantDechet = new JLabel("0.00");
		totalMontantDechet.setHorizontalAlignment(SwingConstants.CENTER);
		totalMontantDechet.setForeground(Color.RED);
		totalMontantDechet.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalMontantDechet.setBounds(196, 753, 351, 39);
		add(totalMontantDechet);
		
		 totalMontantDechetFournisseur = new JLabel("0.00");
		totalMontantDechetFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		totalMontantDechetFournisseur.setForeground(Color.RED);
		totalMontantDechetFournisseur.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalMontantDechetFournisseur.setBounds(557, 753, 369, 39);
		add(totalMontantDechetFournisseur);
		
		 totalMontantplus = new JLabel("0.00");
		totalMontantplus.setHorizontalAlignment(SwingConstants.CENTER);
		totalMontantplus.setForeground(Color.RED);
		totalMontantplus.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalMontantplus.setBounds(947, 753, 268, 39);
		add(totalMontantplus);
		
		 totalMontantmoins = new JLabel("0.00");
		totalMontantmoins.setHorizontalAlignment(SwingConstants.CENTER);
		totalMontantmoins.setForeground(Color.RED);
		totalMontantmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalMontantmoins.setBounds(1225, 753, 263, 39);
		add(totalMontantmoins);
		
		 differenceMontantplusmoins = new JLabel("0.00");
		differenceMontantplusmoins.setHorizontalAlignment(SwingConstants.CENTER);
		differenceMontantplusmoins.setForeground(Color.RED);
		differenceMontantplusmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		differenceMontantplusmoins.setBounds(1027, 804, 419, 39);
		add(differenceMontantplusmoins);
		comboClient.addItem("");
		comboClient.addItem(Constantes.MP_CLIENT);
		mapClient.put(Constantes.MP_CLIENT, Constantes.MP_CLIENT);
		comboClient.addItem(Constantes.MP_INTERNE);
		
		JButton btnBonFrais = new JButton();
		btnBonFrais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				

			 	if(listSituationManquePlusEnVrac.size()!=0)
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
			 		
			 		parameters.put("depot", combodepot.getSelectedItem().toString());
			 		
			 	
			 		
			 		parameters.put("quantitedechetusine", totalDechetCalculer);
			 		parameters.put("montantdechetusine", totalMontantDechetCalculer);
			 		
			 		
			 	
			 		if(!dateDu.equals("") || !dateAu.equals(""))
			 		{
			 			parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
			 		}
			 		
			 		
			 		JasperUtils.imprimerBonFraisManquePlusEtMoinsDechetEmballage(listSituationManquePlusEnVrac,parameters); 
			 	
			 		
			 		
			 	}		 	
			 	
			 	
				
				
				
				
			}
		});
		btnBonFrais.setText("Bon Frais");
		btnBonFrais.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBonFrais.setBounds(80, 700, 104, 31);
		btnBonFrais.setIcon(imgImprimer);
		add(btnBonFrais);
		mapClient.put(Constantes.MP_INTERNE, Constantes.MP_INTERNE);
	
		txtcodemp.setText(Constantes.CODE_MP);	
		
		JButton btnExporterExcel = new JButton();
		btnExporterExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 					
				if(tableSituationManqueEnVrac.getRowCount()!=0)
				{
										
					String titre="Situation Manque Et Plus Dechet Emballage";
		    		String titrefeuilleexcel="Situation Manque Et Plus Dechet Emballage";
		    		ExporterTableVersExcel.tabletoexcelSituationManqueEtDechetEmballage(tableSituationManqueEnVrac, titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			 				
			}
		});
		btnExporterExcel.setText("Exporter Excel");
		btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExporterExcel.setBounds(56, 759, 132, 31);
		btnExporterExcel.setIcon(imgExcel);
		add(btnExporterExcel);
		ChargerMP();
	}
	
	
	
	void ChargerMP()
	{
		combomp.removeAllItems();
		combomp.addItem("");
  		listMatierePremiere=matierePremiereDAO.findMatierePremierSaufCatTHE();
  			for(int t=0;t<listMatierePremiere.size();t++)	
  			{
  				
  				MatierePremier matierePremier=listMatierePremiere.get(t);
  				
  				combomp.addItem(matierePremier.getNom());
  				mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  				mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
  				
  			}	
  			
  			combomp.setSelectedItem(""); 
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
	
	
void afficher_tableMP(List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac)
	{
	
	
	 
	String fournisseur="";
	 
	 if(!comboDechetusineFournisseur.getSelectedItem().equals(""))
	 {
		 
		 if(comboDechetusineFournisseur.getSelectedItem().equals(Constantes.DECHET_USINE))
		 {
			 intialiserTableauDechetUsine(); 
			
			   totalmoinsCalculer=BigDecimal.ZERO ;
				 totalPlusCalculer=BigDecimal.ZERO ;
				
				
				 totalMontantmoinsCalculer=BigDecimal.ZERO ;
				 totalMontantPlusCalculer=BigDecimal.ZERO ;
				 
				 totalMontantDechetFournisseurCalculer=BigDecimal.ZERO ;
					int i=0;
					
					
					
					
					
					
					
					
					
					 
					while(i<listSituationManquePlusEtMoinsEnVrac.size())
					{	
						fournisseur="";
						SituationManquePlusEnVrac SituationManquePlusEnVrac=listSituationManquePlusEtMoinsEnVrac.get(i);
					
						 totalmoinsCalculer=totalmoinsCalculer.add(SituationManquePlusEnVrac.getQuantiteManquante());
						 totalMontantmoinsCalculer=totalMontantmoinsCalculer.add(SituationManquePlusEnVrac.getMontantMoins());
						 totalPlusCalculer=totalPlusCalculer.add(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus());
						 totalMontantPlusCalculer=totalMontantPlusCalculer.add(SituationManquePlusEnVrac.getMontantPlus());
						  
						// totalMontantDechetFournisseurCalculer=totalMontantDechetFournisseurCalculer.add(SituationManquePlusEnVrac.getMontantDechetFournisseur());
						 if(SituationManquePlusEnVrac.getCodeFournisseur()!=null)
						 {
							 if(!SituationManquePlusEnVrac.getCodeFournisseur().equals(""))
							 {
								 fournisseur= SituationManquePlusEnVrac.getCodeFournisseur();
							 }
							 
							 
						 }
						 
						 if(SituationManquePlusEnVrac.getCodeFournisseurdechet()!=null)
						 {
							 if(!SituationManquePlusEnVrac.getCodeFournisseurdechet().equals(""))
							 {
								 fournisseur= SituationManquePlusEnVrac.getCodeFournisseurdechet();
								 SituationManquePlusEnVrac.setCodeFournisseur(SituationManquePlusEnVrac.getCodeFournisseurdechet());
							 }
							 
							 
						 }
						
							
								 Object []ligne={SituationManquePlusEnVrac.getProdcutionCM().getNumOF(),SituationManquePlusEnVrac.getProdcutionCM().getDate(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getCodeArticle(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getLiblle(),SituationManquePlusEnVrac.getMatierePremier().getCode(),SituationManquePlusEnVrac.getMatierePremier().getNom(),fournisseur,SituationManquePlusEnVrac.getPrixUnitaire(),SituationManquePlusEnVrac.getQuantDechet().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantDechetUsine().setScale(6, RoundingMode.FLOOR) ,SituationManquePlusEnVrac.getQuantiteManquanteFrPlus().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantPlus().setScale(6, RoundingMode.FLOOR) ,SituationManquePlusEnVrac.getQuantiteManquante().setScale(6, RoundingMode.FLOOR) ,SituationManquePlusEnVrac.getMontantMoins().setScale(6, RoundingMode.FLOOR)};
								 modeleMP.addRow(ligne);
								
						
						
						
						i++;
					}
					
					this.totalmoins.setText("Total Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalmoinsCalculer));
					this.totalmoins.setText("Total Montant Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantmoinsCalculer));
					this.totalplus.setText("Total Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer));
					this.totalMontantplus.setText("Total Montant Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer));
					this.totalDechet.setText("Total Dechet : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechetCalculer));
					this.totalMontantDechet.setText("Total Montant Dechet : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantDechetCalculer));
					this.totalDechetFournisseur.setText("");
					this.totalMontantDechetFournisseur.setText("");
					this.differenceMontantplusmoins.setText("Difference Montant : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer.subtract(totalMontantmoinsCalculer)));
			 
			 
			 
			 
			 
		 }else if(comboDechetusineFournisseur.getSelectedItem().equals(Constantes.DECHET_FOURNISSEUR))
		 {
			 intialiserTableauDechetFournisseur();
			 
			 totalmoinsCalculer=BigDecimal.ZERO ;
				 totalPlusCalculer=BigDecimal.ZERO ;
				
				 totalDechetFournisseurCalculer=BigDecimal.ZERO ;
				
				  totalMontantmoinsCalculer=BigDecimal.ZERO ;
					 totalMontantPlusCalculer=BigDecimal.ZERO ;
					
					 totalMontantDechetFournisseurCalculer=BigDecimal.ZERO ;
				
					int i=0;
					 
					while(i<listSituationManquePlusEtMoinsEnVrac.size())
					{	
						fournisseur="";
						SituationManquePlusEnVrac SituationManquePlusEnVrac=listSituationManquePlusEtMoinsEnVrac.get(i);
					
						 totalmoinsCalculer=totalmoinsCalculer.add(SituationManquePlusEnVrac.getQuantiteManquante());
						 totalMontantmoinsCalculer=totalMontantmoinsCalculer.add(SituationManquePlusEnVrac.getMontantMoins());
						 totalPlusCalculer=totalPlusCalculer.add(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus());		
						 totalMontantPlusCalculer=totalMontantPlusCalculer.add(SituationManquePlusEnVrac.getMontantPlus());
						 totalDechetFournisseurCalculer=totalDechetFournisseurCalculer.add(SituationManquePlusEnVrac.getQuantDechetFournisseur());
						 totalMontantDechetFournisseurCalculer=totalMontantDechetFournisseurCalculer.add(SituationManquePlusEnVrac.getMontantDechetFournisseur());
							
						 if(SituationManquePlusEnVrac.getCodeFournisseur()!=null)
						 {
							 if(!SituationManquePlusEnVrac.getCodeFournisseur().equals(""))
							 {
								 fournisseur= SituationManquePlusEnVrac.getCodeFournisseur();
							 }
							 
							 
						 }
						 
						 if(SituationManquePlusEnVrac.getCodeFournisseurdechet()!=null)
						 {
							 if(!SituationManquePlusEnVrac.getCodeFournisseurdechet().equals(""))
							 {
								 fournisseur= SituationManquePlusEnVrac.getCodeFournisseurdechet();
								 SituationManquePlusEnVrac.setCodeFournisseur(SituationManquePlusEnVrac.getCodeFournisseurdechet());
							 }
							 
							 
						 }
						 
								 Object []ligne={SituationManquePlusEnVrac.getProdcutionCM().getNumOF(),SituationManquePlusEnVrac.getProdcutionCM().getDate(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getCodeArticle(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getLiblle(),SituationManquePlusEnVrac.getMatierePremier().getCode(),SituationManquePlusEnVrac.getMatierePremier().getNom(),fournisseur,SituationManquePlusEnVrac.getPrixUnitaire(),SituationManquePlusEnVrac.getQuantDechetFournisseur().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantDechetFournisseur().setScale(6, RoundingMode.FLOOR) , SituationManquePlusEnVrac.getQuantiteManquanteFrPlus().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantPlus().setScale(6, RoundingMode.FLOOR), SituationManquePlusEnVrac.getQuantiteManquante().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantMoins().setScale(6, RoundingMode.FLOOR)};
								 modeleMP.addRow(ligne);
								
						
						
						
						i++;
					}
					
					this.totalmoins.setText("Total Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalmoinsCalculer));
					this.totalMontantmoins.setText("Total Montant Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantmoinsCalculer));
					this.totalplus.setText("Total Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer));
					this.totalMontantplus.setText("Total Montant Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer));
					this.totalDechet.setText("");	
					this.totalMontantDechet.setText("");	
					this.totalDechetFournisseur.setText("Total Dechet Fournisseur : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechetFournisseurCalculer));
					this.totalMontantDechetFournisseur.setText("Total Montant Dechet Fournisseur : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantDechetFournisseurCalculer));
					this.differenceMontantplusmoins.setText("Difference Montant : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer.subtract(totalMontantmoinsCalculer)));
			 
			 
			 
		 }
		  
	 }else
	 {
			intialiserTableau2(); 
			
			 totalmoinsCalculer=BigDecimal.ZERO ;
			 totalPlusCalculer=BigDecimal.ZERO ;
			  
			 totalDechetFournisseurCalculer=BigDecimal.ZERO ;
			
			 totalMontantmoinsCalculer=BigDecimal.ZERO ;
			 totalMontantPlusCalculer=BigDecimal.ZERO ;
			 
			 totalMontantDechetFournisseurCalculer=BigDecimal.ZERO ;
			
				int i=0;
				 
				while(i<listSituationManquePlusEtMoinsEnVrac.size())
				{	
					fournisseur="";
					SituationManquePlusEnVrac SituationManquePlusEnVrac=listSituationManquePlusEtMoinsEnVrac.get(i);
				
					 totalmoinsCalculer=totalmoinsCalculer.add(SituationManquePlusEnVrac.getQuantiteManquante());
					 totalMontantmoinsCalculer=totalMontantmoinsCalculer.add(SituationManquePlusEnVrac.getMontantMoins());
					 totalPlusCalculer=totalPlusCalculer.add(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus());
					 totalMontantPlusCalculer=totalMontantPlusCalculer.add(SituationManquePlusEnVrac.getMontantPlus());
					
					 totalDechetFournisseurCalculer=totalDechetFournisseurCalculer.add(SituationManquePlusEnVrac.getQuantDechetFournisseur());
					 totalMontantDechetFournisseurCalculer=totalMontantDechetFournisseurCalculer.add(SituationManquePlusEnVrac.getMontantDechetFournisseur());
					
					 System.out.println(SituationManquePlusEnVrac.getMontantDechetFournisseur());
					 
					 if(SituationManquePlusEnVrac.getCodeFournisseur()!=null)
					 {
						 if(!SituationManquePlusEnVrac.getCodeFournisseur().equals(""))
						 {
							 fournisseur= SituationManquePlusEnVrac.getCodeFournisseur();
						 }
						 
						 
					 }
					 
					 if(SituationManquePlusEnVrac.getCodeFournisseurdechet()!=null)
					 {
						 if(!SituationManquePlusEnVrac.getCodeFournisseurdechet().equals(""))
						 {
							 fournisseur= SituationManquePlusEnVrac.getCodeFournisseurdechet();
							 SituationManquePlusEnVrac.setCodeFournisseur(SituationManquePlusEnVrac.getCodeFournisseurdechet());
						 }
						 
						 
					 }
						
							 Object []ligne={SituationManquePlusEnVrac.getProdcutionCM().getNumOF(),SituationManquePlusEnVrac.getProdcutionCM().getDate(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getCodeArticle(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getLiblle(),SituationManquePlusEnVrac.getMatierePremier().getCode(),SituationManquePlusEnVrac.getMatierePremier().getNom(),fournisseur,SituationManquePlusEnVrac.getPrixUnitaire(),SituationManquePlusEnVrac.getQuantDechet().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantDechetUsine().setScale(6, RoundingMode.FLOOR) ,SituationManquePlusEnVrac.getQuantDechetFournisseur().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantDechetFournisseur().setScale(6, RoundingMode.FLOOR) , SituationManquePlusEnVrac.getQuantiteManquanteFrPlus().setScale(6, RoundingMode.FLOOR),SituationManquePlusEnVrac.getMontantPlus().setScale(6, RoundingMode.FLOOR) , SituationManquePlusEnVrac.getQuantiteManquante().setScale(6, RoundingMode.FLOOR) ,SituationManquePlusEnVrac.getMontantMoins().setScale(6, RoundingMode.FLOOR) };
							 modeleMP.addRow(ligne);
							
					
					
					
					i++;
				}
				
				this.totalmoins.setText("Total Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalmoinsCalculer));
				this.totalMontantmoins.setText("Total Montant Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantmoinsCalculer));
				this.totalplus.setText("Total Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer));
				this.totalMontantplus.setText("Total Montant Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer));
				this.totalDechet.setText("Total Dechet : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechetCalculer));
				this.totalMontantDechet.setText("Total Montant Dechet : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantDechetCalculer));
				this.totalDechetFournisseur.setText("Total Dechet Fournisseur : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechetFournisseurCalculer));
				this.totalMontantDechetFournisseur.setText("Total Montant Dechet Fournisseur : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantDechetFournisseurCalculer));
				this.differenceplusmoins.setText("Difference : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer.subtract(totalmoinsCalculer)));
				this.differenceMontantplusmoins.setText("Difference Montant : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer.subtract(totalMontantmoinsCalculer)));
			
	 }
	
	

	
		
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Production","Date Production","Code Article","Article","Code MP","MP","Fournisseur","Prix","Quantite Dechet Usine","Montant Dechet Usine","Quantite Dechet Fournisseur","Montant Dechet Fournisseur", "Manque Plus","Montant Manque Plus","Manque Moins","Montant Manque Moins"

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

void intialiserTableauDechetFournisseur(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Production","Date Production","Code Article","Article","Code MP","MP","Fournisseur", "Prix","Quantite Dechet Fournisseur","Montant Dechet Fournisseur","Manque Plus","Montant Manque Plus","Manque Moins","Montant Manque Moins"

		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
		
		 

}


void intialiserTableauDechetUsine(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Production","Date Production","Code Article","Article","Code MP","MP","Fournisseur","Prix","Quantite Dechet Usine","Montant Dechet","Manque Plus","Montant Manque Plus","Manque Moins","Montant Manque Moins"

		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
	
		 

}
}

package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransfererMPProduitFini extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	
	
	
	
	private Map< String, MatierePremier> mapcodeMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<MatierePremier> listMatierePremier =new ArrayList<MatierePremier>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private CategorieMpDAO categoriempdao;
	

	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboDepotDestination=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	private JButton btnimprimer = new JButton("Bon Transfere MP Produit Fini");
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockpfdao;
	private TransferStockMPDAO transferStockMPDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articledao;
	private Depot depot = new Depot();
	private JTextField txtCodeTransfer;
	private JTextField txtcodearticle;
	private JTextField txtcodemp;
	private JTextField txtquantite;
	private  JComboBox comboarticle = new JComboBox();
	private   JComboBox combomp = new JComboBox();
	private JTextField txtCode;
	private JTextField txtLibelle;
	private JTextField txtConditionnement;
	private 	JLayeredPane layerArticle = new JLayeredPane();
	Articles article =new Articles ();
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	boolean detailtransfermp=false;
	boolean detailtransferpf=false;
	private Map< String, BigDecimal> mapGrammageBox= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageCarton= new HashMap<>();
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	JComboBox combogrammagecarton = new JComboBox();
	JComboBox combogrammagebox = new JComboBox();
	 JComboBox soucategoriempcombo = new JComboBox();
	 JComboBox categoriempcombo = new JComboBox();
	 private SubCategorieMPDAO subcategoriempdao;
	 JComboBox comboFournisseur = new JComboBox();
		private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
		private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
		private FournisseurMPDAO fournisseurMPDAO;
		
		 private List <Object[]> Mindate=new ArrayList<Object[]>();
		 private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
			private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
			private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
			private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
			private List<EtatStockMP> listEtatStockMPAfficherMagasinStockage=new ArrayList<EtatStockMP>();
			private List<EtatStockMP> listEtatStockMPAfficherMagasinProduction=new ArrayList<EtatStockMP>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurReception=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=new ArrayList<Object[]>();
			private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
			private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
			private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
			SubCategorieMPDAO SubCategorieMPDAO;
			private DetailTransferMPDAO detailTransferStockMPDAO;
			
			private List<DetailTransferStockMP> listeDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
			private List<DetailTransferProduitFini> listeDetailTransfertStockPF =new ArrayList<DetailTransferProduitFini>();
			private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
			private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
			
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererMPProduitFini() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 732);
        try{
        	categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	articledao=  new ArticlesDAOImpl();
        	stockpfdao= new StockPFDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	 imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
        	 fournisseurMPDAO=new FournisseurMPDAOImpl();           
         	SubCategorieMp subCategorieMp=subcategoriempdao.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
         	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
         	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
         	SubCategorieMPDAO=new SubCategorieMPAOImpl();	
         	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		String Codedepot= AuthentificationView.utilisateur.getCodeDepot();
		listDepot =new ArrayList<Depot>(); 
		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				listDepot = depotDAO.findDepotByCodeSaufEnParametre(Codedepot);
		   	} else {
		   		depot = depotDAO.findByCode(Codedepot);
		   		listDepot.add(depot);
		   	}
    		
    		
	     //	comboDepotDestination.addItem(depot.getLibelle());
	     //	comboDepotSource.addItem(depot.getLibelle());
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(398, 532, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
					  		  txtCodeTransfer = new JTextField();
					  		util.Utils.copycoller(txtCodeTransfer);
					       
					      
				  		      
				  		   /*   List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			comboMagasinDestination.addItem(magasin.getLibelle());
						  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
					  		      			
						  		      			j++;
						  		      		}
					  		      }*/
					  		      
					  		      
				  		     
				  		     //	listDepot = depotDAO.findAll();	
					  		
				    		 listMatierePremier=matierePremiereDAO.findAll();
				  		     	comboDepotSource.addItem("");
				  		     	comboDepotDestination.addItem("");
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboDepotSource.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinSource.removeAllItems();
					  		     	 Depot depot =new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	if(!comboDepotSource.getSelectedItem().equals(""))
						  		     	  	   	 depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
								  		       
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinSource.addItem(magasin.getLibelle());
									  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1265, 278);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 193, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 193, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(491, 74, 200, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination produit Fini");
				  		  lblEquipe.setBounds(316, 73, 165, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("D\u00E9pot Destination Produit Fini");
				  		  lblDpotDestination.setBounds(316, 33, 155, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(491, 34, 200, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination Produit Fini");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(289, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		
				  		  txtCodeTransfer.setBounds(806, 75, 155, 24);
				  		  layeredPane.add(txtCodeTransfer);
				  		  txtCodeTransfer.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(803, 35, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(710, 34, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel label = new JLabel("Code Transafert ");
				  		  label.setBounds(720, 74, 94, 24);
				  		  layeredPane.add(label);
				  		  
				  		  JLabel lblCodeArticle = new JLabel("Code Article");
				  		  lblCodeArticle.setBounds(10, 125, 94, 24);
				  		  layeredPane.add(lblCodeArticle);
				  		  
				  		  txtcodearticle = new JTextField();
				  		  txtcodearticle.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
				  		  	if (e.getKeyCode() == e.VK_ENTER)
					  		{
				  		  	if(!txtcodearticle.getText().equals(""))
			  		  		{
			  		  			Articles articles=mapCodeArticle.get(txtcodearticle.getText());
			  		  			if(articles!=null)
			  		  			{
			  		  				comboarticle.setSelectedItem(articles.getLiblle());
			  		  			}else
			  		  			{
			  		  			int reponse = JOptionPane.showConfirmDialog(null, "Article Introuvable , Voullez vous le Créer ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )
									{
									layerArticle.setVisible(true);
			  		  				
			  		  				
			  		  			}
			  		  			
			  		  		}
					  		}
				  		  		
				  		  		
				  		  	}
				  		  }});
				  		  txtcodearticle.setBounds(96, 126, 155, 24);
				  		  layeredPane.add(txtcodearticle);
				  		  txtcodearticle.setColumns(10);
				  		  
				  		  JLabel lblArticle = new JLabel(" Article");
				  		  lblArticle.setBounds(299, 125, 53, 24);
				  		  layeredPane.add(lblArticle);
				  		  
				  		   comboarticle = new JComboBox();
				  		   comboarticle.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent e) {
				  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		   		 if(!comboarticle.getSelectedItem().equals(""))
				  		   		 {
				  		   		 Articles article=mapArticle.get(comboarticle.getSelectedItem());
				  		   		 if(article!=null)
				  		   		 {
				  		   			 txtcodearticle.setText(article.getCodeArticle());
				  		   		 }
				  		   		 }else
				  		   		 {
				  		   		 txtcodearticle.setText("");
				  		   		 }
				  		   		 
				  		   	 }
				  		   		
				  		   		
				  		   	}
				  		   });
				  		  comboarticle.setBounds(362, 127, 247, 24);
				  		  layeredPane.add(comboarticle);
				  		 AutoCompleteDecorator.decorate(comboarticle);
				  		  JLabel lblCodeMp = new JLabel("Code MP");
				  		  lblCodeMp.setBounds(582, 167, 53, 24);
				  		  layeredPane.add(lblCodeMp);
				  		  
				  		  txtcodemp = new JTextField();
				  		  txtcodemp.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
				  		  	if (e.getKeyCode() == e.VK_ENTER)
				  		  	{
				  		  	if(!txtcodemp.getText().equals(""))
			  		  			
			  		  		{
			  		  			MatierePremier matierepremiere=mapcodeMP.get(txtcodemp.getText().toUpperCase());
			  		  			if(matierepremiere!=null)
			  		  			{
			  		  				
			  		  				combomp.setSelectedItem(matierepremiere.getNom());
			  		  			}else
			  		  			{
			  		  				JOptionPane.showMessageDialog(null, "Matiere Premiere introuvable !!!");
			  		  			}
			  		  			
			  		  			
			  		  			
			  		  			
			  		  		}
				  		  	}
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  txtcodemp.setColumns(10);
				  		  txtcodemp.setBounds(633, 167, 107, 24);
				  		  layeredPane.add(txtcodemp);
				  		  
				  		  JLabel lblLibelleMp = new JLabel("Matiere Premiere");
				  		  lblLibelleMp.setBounds(751, 167, 91, 24);
				  		  layeredPane.add(lblLibelleMp);
				  		  
				  		   combomp = new JComboBox();
				  		   combomp.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent e) {
				  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		   		 
				  		   		 MatierePremier matierepremiere=mapMatierePremier.get(combomp.getSelectedItem());
				  		   		 if(matierepremiere!=null)
				  		   		 {
				  		   			 txtcodemp.setText(matierepremiere.getCode());
				  		   		 }else
				  		   		 {
				  		   		txtcodemp.setText(Constantes.CODE_MP);	
				  		   		 }
				  		   		 
				  		   	 	 }
				  		   		
				  		   	}
				  		   });
				  		  combomp.setBounds(852, 169, 247, 24);
				  		  layeredPane.add(combomp);
				  		  AutoCompleteDecorator.decorate(combomp);
				  		  
				  		  txtquantite = new JTextField();
				  		  txtquantite.setColumns(10);
				  		  txtquantite.setBounds(1168, 169, 87, 24);
				  		  layeredPane.add(txtquantite);
				  		  
				  		  JLabel lblQuantit = new JLabel("Quantit\u00E9 ");
				  		  lblQuantit.setBounds(1109, 167, 62, 24);
				  		  layeredPane.add(lblQuantit);
			
		ChargerArticle();
		
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				detailtransfermp=false;
				detailtransferpf=false;
				
				try {
					
					
					String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
	if(comboDepotSource.getSelectedItem().equals(""))
	{
		JOptionPane.showMessageDialog(null, "Veuillez choisir le depot source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
		

				} else if(comboMagasinSource.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Magasin source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				else if(comboDepotSource.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le depot destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
				}else if(comboMagasinDestination.getSelectedItem().equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir la date de transfere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				else if(txtcodearticle.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code article SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(comboarticle.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir l'article  SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtcodemp.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code Matiere Premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(combomp.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir la matiere  Premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(txtquantite.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité matiere premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
				{
					JOptionPane.showMessageDialog(null, "la quantité matiere premiere doit etre supérieur à 0 SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				
				{
					Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
					Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
					
					if(magasinSource.getCode().equals(magasinDestination.getCode())){
						JOptionPane.showMessageDialog(null, "Le Magasin source doit ètre différent déstination", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					}
					else
					
					{
						
						
						
						MatierePremier matierePremier =mapMatierePremier.get(combomp.getSelectedItem());

	  					FournisseurMP fournisseurMP=null;
	  					  		  					
	  						  		  				
	  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
						
						if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  					{
							
							if(fournisseurMP==null)
		  						{
		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur EN Vrac SVP");
		  							return;
		  						}
							
		  					}
						BigDecimal montantTotal=BigDecimal.ZERO;
                        BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                        BigDecimal montantTotalEmballage=BigDecimal.ZERO;
						
						listeDetailTransfertStockMP.clear();
						//transfere stock MP
						TransferStockMP transferStock = new TransferStockMP();
						
						
					txtCodeTransfer.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_SORTIE));
					transferStock.setCodeTransfer(txtCodeTransfer.getText());
					transferStock.setCreerPar(AuthentificationView.utilisateur);
					transferStock.setDate(new Date());
					transferStock.setDateTransfer(dateTransfereChooser.getDate());
					transferStock.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
					transferStock.setDepot(mapDepotSourcetmp.get(comboDepotSource.getSelectedItem()));
					listeDetailTransfertStockMP=remplirDetailTransfer();
					for(int i=0;i<listeDetailTransfertStockMP.size();i++)
					{
						DetailTransferStockMP detailTransferStockMP=listeDetailTransfertStockMP.get(i);
						detailTransferStockMP.setTransferStockMP(transferStock);
						listeDetailTransfertStockMP.set(i, detailTransferStockMP);
						
						if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  		     			{
  		     				montantTotalEnvrac=montantTotalEnvrac.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
  		     				
  		     			}else
  		     			{
  		     				montantTotalEmballage=montantTotalEmballage.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
  		     			}
  		     			montantTotal=montantTotal.add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))));
						
						
					}
					transferStock.setListDetailTransferMP(listeDetailTransfertStockMP);	
					
					
					// transfer stock produit fini
					TransferStockPF transferPF=new TransferStockPF();	
					transferPF.setCodeTransfer(txtCodeTransfer.getText());
					transferPF.setCreerPar(AuthentificationView.utilisateur);
					transferPF.setDate(new Date());
					transferPF.setDateTransfer(dateTransfereChooser.getDate());
					transferPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
					listeDetailTransfertStockPF=remplirDetailTransferProduitFini();
					for(int j=0;j<listeDetailTransfertStockPF.size();j++)
					{
						DetailTransferProduitFini detailTransferProduitFini=listeDetailTransfertStockPF.get(j);
						
						detailTransferProduitFini.setTransferStockPF(transferPF);
						listeDetailTransfertStockPF.set(j, detailTransferProduitFini);
					}
					transferPF.setListDetailTransferProduitFini(listeDetailTransfertStockPF);
					 if(detailtransfermp==false)
					 {
						 if(detailtransferpf==false)
						 {
							 	transferStockMPDAO.add(transferStock);
								transferStockPFDAO.add(transferPF);	
								
								
								listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
			  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
			  		     		
			  		     		if(statistiqueFinanciaireMPTmp!=null)
			  		     		{
			  		     			
			  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
			  		     			
			  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
			  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
			  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
			  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
			  		     			statistiqueFinanciaireMP.setCodeTransfer(txtCodeTransfer.getText());
			  		     			statistiqueFinanciaireMP.setDate(new Date());
			  		     			statistiqueFinanciaireMP.setDateOperation(dateTransfereChooser.getDate());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
			  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
			  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
			  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF().add(montantTotal));
			  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
			  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
			  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
			  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF()) );
			  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
			  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
			  		     			statistiqueFinanciaireMP.setEtat(ETAT_TRANSFER_STOCK_SORTIE_PF);
			  		     		 
			  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
			  		     			 
			  		     			
			  		     		} 
								
								
								
								JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								
								 if(listeDetailTransfertStockMP.size()!=0)
								 {
									 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  		  	String date=dateFormat.format(transferStock.getDateTransfer());
									 DetailTransferStockMP detailTransferStockMP=listeDetailTransfertStockMP.get(0);
										Map parameters = new HashMap();
										parameters.put("numTransfer", transferStock.getCodeTransfer());
										parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
										parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
										parameters.put("magasinDest", detailTransferStockMP.getMagasinDestination().getLibelle());
										parameters.put("depDest", detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());
										parameters.put("dateTransfer", date);
										JasperUtils.imprimerBonTransfereMPProduitFini(listeDetailTransfertStockMP,parameters,transferStock.getCodeTransfer());
									 
								 }
								
								intialiser();
							 
							 
						 }else
						 {
							///	JOptionPane.showMessageDialog(null, "Liste produit fini est vide", "Erreur", JOptionPane.INFORMATION_MESSAGE);
							 return;
						 }
						 
						 
					 }else
					 {
							JOptionPane.showMessageDialog(null, "Liste Matière premiere est vide", "Erreur", JOptionPane.INFORMATION_MESSAGE);
						 return;
					 }
					
						
			
					}
				}
					
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "La quantité de matiere premiere doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				

		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(200, 532, 172, 23);
		add(btnValiderTransfer);
		
		 layerArticle = new JLayeredPane();
		layerArticle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layerArticle.setBounds(9, 300, 1265, 207);
		add(layerArticle);
		
		JLabel label_1 = new JLabel("Code Article");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(27, 34, 144, 24);
		layerArticle.add(label_1);
		
		JLabel label_2 = new JLabel("Libelle Article");
		label_2.setBounds(280, 33, 83, 26);
		layerArticle.add(label_2);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(101, 34, 144, 24);
		layerArticle.add(txtCode);
		
		txtLibelle = new JTextField();
		txtLibelle.setColumns(10);
		txtLibelle.setBounds(373, 34, 222, 24);
		layerArticle.add(txtLibelle);
		
		JLabel label_3 = new JLabel("Conditionnement");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(637, 34, 144, 24);
		layerArticle.add(label_3);
		
		txtConditionnement = new JTextField();
		txtConditionnement.setColumns(10);
		txtConditionnement.setBounds(727, 34, 144, 24);
		layerArticle.add(txtConditionnement);
		layerArticle.setVisible(false);
		JButton ajoutarticle = new JButton("Ajouter Article");
		ajoutarticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtCode.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}else if(txtLibelle.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le libelle", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				} else if(txtConditionnement.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut remplir le conditionnement", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				} else {
					Articles articleexiste=articledao.findByCode(txtCode.getText());
					if(articleexiste!=null)
					{
						JOptionPane.showMessageDialog(null, "Article existe déja !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}else
					{
						article =new Articles ();
						article.setDateCreation(new Date());
						article.setCodeArticle(txtCode.getText());
						article.setLiblle(txtLibelle.getText());
						article.setConditionnement(new BigDecimal(txtConditionnement.getText()));
						if(combogrammagebox.getSelectedIndex()!=-1)
						{
							
							article.setCentreCout3(mapGrammageBox.get(combogrammagebox.getSelectedItem()));
							
						}else
						{
							article.setCentreCout3(BigDecimal.ONE);
							
						}
						
						
						if(combogrammagecarton.getSelectedIndex()!=-1)
						{
							
							article.setCentreCout4(mapGrammageCarton.get(combogrammagecarton.getSelectedItem()));  //// le poid de Carton
							
						}else
						{
							article.setCentreCout4(BigDecimal.ONE);
							
						}
						
						articledao.add(article);
						JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						initialiserArticle();
						layerArticle.setVisible(false);
						ChargerArticle();
						
						}
					
				}
				
				
			}
		});
		ajoutarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ajoutarticle.setBounds(245, 153, 158, 23);
		ajoutarticle.setIcon(imgAjouter);
		layerArticle.add(ajoutarticle);
		
		JLabel label_4 = new JLabel("Grammage Box :");
		label_4.setBounds(27, 85, 82, 26);
		layerArticle.add(label_4);
		
		 combogrammagebox = new JComboBox();
		combogrammagebox.setSelectedIndex(-1);
		combogrammagebox.setBounds(119, 89, 158, 22);
		layerArticle.add(combogrammagebox);
		
		JLabel label_5 = new JLabel("Grammage carton :");
		label_5.setBounds(287, 85, 108, 26);
		layerArticle.add(label_5);
		
		 combogrammagecarton = new JComboBox();
		combogrammagecarton.setSelectedIndex(-1);
		combogrammagecarton.setBounds(390, 89, 160, 22);
		layerArticle.add(combogrammagecarton);
		
		listGrammageBox=grammageBoxDAO.findAll();
    	listGrammageCarton=grammageCartonDAO.findAll();
	  
	  for(int j=0;j<listGrammageBox.size() ; j++)
	  {
		  
		  GrammageBox gramaBox=listGrammageBox.get(j);
		  combogrammagebox.addItem(gramaBox.getCodeGrammage());
		  mapGrammageBox.put(gramaBox.getCodeGrammage(), gramaBox.getGrammageBox());
		  
	  }
	  combogrammagebox.setSelectedIndex(-1);
	  
	  
	  for(int d=0;d<listGrammageCarton.size() ; d++)
	  {
		  
		  GrammageCarton gramacaCarton=listGrammageCarton.get(d);
		  combogrammagecarton.addItem(gramacaCarton.getCodeGrammage());
		  mapGrammageCarton.put(gramacaCarton.getCodeGrammage(), gramacaCarton.getGrammageCarton());
		  
	  }
	  combogrammagecarton.setSelectedIndex(-1);
	  
	  JButton button = new JButton("Initialiser");
	  button.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent arg0) {
	  		
	  		initialiserArticle();
	  		
	  		
	  	}
	  });
	  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  button.setBounds(421, 153, 112, 23);
	  layerArticle.add(button);

	  txtcodemp.setText(Constantes.CODE_MP);	
	  
	  JLabel label_6 = new JLabel("Sous-Categorie Mp");
	  label_6.setBounds(10, 169, 102, 24);
	  layeredPane.add(label_6);
	  label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  
	   soucategoriempcombo = new JComboBox();
	   soucategoriempcombo.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent arg0) {
	   		

	 		

	 		

		  		int i=0;
		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
		  		{
		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
		  			{
		  			categoriempcombo.removeAllItems();
		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
		  			if(listecategoriemp!=null)
		  			{
		  				while(i<listecategoriemp.size())
		  				{
		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
		  					i++;
		  				}
		  				
		  			}
		  				
		  			}else
		  			{
		  			listecategoriemp.clear();
		  			categoriempcombo.removeAllItems();
		  		categoriempcombo.addItem("");
		  			combomp.removeAllItems();
		  			}
		  	
		  			
		  		}else
		  		{
		  		listecategoriemp.clear();
		  		categoriempcombo.removeAllItems();
		  	categoriempcombo.addItem("");
		  combomp.removeAllItems();
		  		}
		  		
		  	
	 		
	 		
	 		
	 	
	 		
	 		
	 		
	   		
	   		
	   		
	   	}
	   });
	   soucategoriempcombo.addItemListener(new ItemListener() {
	   	public void itemStateChanged(ItemEvent arg0) {
	   		
	   		
	   		
	   		
	   		
	   	}
	   });
	  soucategoriempcombo.setBounds(103, 169, 179, 24);
	  layeredPane.add(soucategoriempcombo);
	  AutoCompleteDecorator.decorate(soucategoriempcombo);
	  JLabel label_7 = new JLabel("Categorie Mp");
	  label_7.setBounds(289, 167, 80, 26);
	  layeredPane.add(label_7);
	  
	   categoriempcombo = new JComboBox();
	   categoriempcombo.addItemListener(new ItemListener() {
	   	public void itemStateChanged(ItemEvent e) {
	   				  		
		  		if(categoriempcombo.getSelectedIndex()!=-1)
		  		{
		  			
		  			if(!categoriempcombo.getSelectedItem().equals(""))
		  			{
		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
		  				listMatierePremier.clear();
		  			
		  		combomp.removeAllItems();
	  			combomp.addItem("");
	  			txtcodemp.setText(Constantes.CODE_MP);	
	  			listMatierePremier=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
		  			for(int i=0;i<listMatierePremier.size();i++)	
		  			{
		  				
		  				MatierePremier matierePremier=listMatierePremier.get(i);
		  				combomp.addItem(matierePremier.getNom());
		  				mapMatierePremier.put(matierePremier.getNom(), matierePremier);
		  				mapcodeMP.put(matierePremier.getCode(), matierePremier);
		  				
		  			}
		  				
		  				
		  				
		  			}else
		  			{
		  				listMatierePremier.clear();
		  				txtcodemp.setText(Constantes.CODE_MP);	
		  				combomp.removeAllItems();
		  				combomp.addItem("");
		  				
		  			}
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  		}else
		  		{
		  			listMatierePremier.clear();
		  			txtcodemp.setText(Constantes.CODE_MP);	
  				combomp.removeAllItems();
  				combomp.addItem("");
		  			
		  		}
		  		
		  		
		  		
	 	
	   		
	   	}
	   });
	  categoriempcombo.setBounds(364, 168, 208, 24);
	  layeredPane.add(categoriempcombo);
	  AutoCompleteDecorator.decorate(categoriempcombo);		  		     
	  
	  JLabel label_8 = new JLabel("Fournisseur : ");
	  label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  label_8.setBounds(10, 220, 91, 24);
	  layeredPane.add(label_8);
	  
	   comboFournisseur = new JComboBox();
	  comboFournisseur.setSelectedIndex(-1);
	  comboFournisseur.setBounds(88, 221, 232, 24);
	  layeredPane.add(comboFournisseur);
				  		 
	
	  int j=0;
		  while(j<listsubcategoriemp.size())
		  {
			  subcatMap.put(listsubcategoriemp.get(j).getNom(), listsubcategoriemp.get(j));
			  soucategoriempcombo.addItem(listsubcategoriemp.get(j).getNom());
			  j++;
		  }
		  
		  
		  
		  comboFournisseur.addItem("");
			for(int k=0;k<listFournisseur.size();k++)
			{
				FournisseurMP fournisseurMP=listFournisseur.get(k);
				comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
				mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
				
			}
			
	
	
	}
	
	void initialiserArticle()
	{
		txtCode.setText("");
		txtLibelle.setText("");
		txtConditionnement.setText("");
		combogrammagebox.setSelectedIndex(-1);
		combogrammagecarton.setSelectedIndex(-1);
		txtcodemp.setText(Constantes.CODE_MP);	
		
	}
	
	void intialiser()
	{
		comboDepotDestination.setSelectedIndex(-1);
		comboDepotSource.setSelectedIndex(-1);
		comboMagasinDestination.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		comboarticle.setSelectedIndex(-1);
		combomp.setSelectedIndex(-1);
		txtquantite.setText("");
		txtcodearticle.setText("");
		txtcodemp.setText("");
		txtcodemp.setText(Constantes.CODE_MP);	
		txtCodeTransfer.setText("");
		dateTransfereChooser.setCalendar(null);
		comboFournisseur.setSelectedItem("");
		soucategoriempcombo.setSelectedIndex(-1);
		categoriempcombo.setSelectedIndex(-1);
	}
	 void ChargerArticle()
	 {
		 listArticle=articledao.findAll();
		 comboarticle.removeAllItems();
		 int j=0;
		 comboarticle.addItem("");
			while(j<listArticle.size())
			{
				Articles articles=listArticle.get(j);
				comboarticle.addItem(articles.getLiblle());
				
				mapArticle.put(articles.getLiblle(), articles);
				mapCodeArticle.put(articles.getCodeArticle(), articles);
				j++;
			}
	 }
	

List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;

	BigDecimal prixStockSource=BigDecimal.ZERO;
	
	BigDecimal stockSource=BigDecimal.ZERO;
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	Date dat=dateTransfereChooser.getDate();
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(combomp.getSelectedItem());
		quantite=new BigDecimal(txtquantite.getText());
		
		CalculerStockFinale(magasinSource, dat);
		
		listEtatStockMPAfficherMagasinProduction.addAll(listEtatStockMPAfficher);
		
		FournisseurMP fournisseurMP=null;
			boolean stocksuffisant=false;	
			boolean trouver=false;	
		StockMP stockMPSource=null;	
			fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
		
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				{
			
			
			for(int i=0;i<listEtatStockMPAfficherMagasinProduction.size();i++)
			{
				
				if(listEtatStockMPAfficherMagasinProduction.get(i).getMp().getId()==matierePremier.getId())
				{
					
					if(listEtatStockMPAfficherMagasinProduction.get(i).getFournisseurMP()!=null)
					{
						
						if(listEtatStockMPAfficherMagasinProduction.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
						{
							trouver=true;
							
							if(listEtatStockMPAfficherMagasinProduction.get(i).getQuantiteFinale().compareTo(quantite)>=0)
							{
								
								stocksuffisant=true;	
								
								
							}
						}
						
						
						
						
					}
					
					
					
				}
				
				
				
				
			}
			


			
				}else
				{
					for(int i=0;i<listEtatStockMPAfficherMagasinProduction.size();i++)
					{
						
						if(listEtatStockMPAfficherMagasinProduction.get(i).getMp().getId()==matierePremier.getId())
						{
							
							if(listEtatStockMPAfficherMagasinProduction.get(i).getFournisseurMP()==null)
							{
								
								trouver=true;
									
									if(listEtatStockMPAfficherMagasinProduction.get(i).getQuantiteFinale().compareTo(quantite)>=0)
									{
										
										stocksuffisant=true;	
										
										
									}
								
								
								
								
								
							}
							
							
							
						}
						
						
						
						
					}
				}
		
		
				if(trouver==true)
				{
					if(stocksuffisant==true){
						/*sommeStock=quantite+stockMPDestination.getStock();*/
					
					
						
						//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
						
						//=prixMoyen/sommeStock;
				     	//	stockMPDestination.setPrixUnitaire(prixMoyen);
						
					
						
						
						detailTransferStockMP.setMagasinDestination(magasinDestination);
						detailTransferStockMP.setMagasinSouce(magasinSource);
						detailTransferStockMP.setMatierePremier(matierePremier);
						detailTransferStockMP.setQuantite(quantite);
						if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							detailTransferStockMP.setFournisseur(fournisseurMP);
						}
						
						if(DateUtils.getAnnee(dateTransfereChooser.getDate())==2023)
						{
							if(matierePremier.getPrix()!=null)
							{
								detailTransferStockMP.setPrixUnitaire(matierePremier.getPrix());
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
						}else
						{
							if(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))!=null)
							{
								detailTransferStockMP.setPrixUnitaire(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate())));
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
						}
						
						
							
							
						
						
						listDetailTransferStockMP.add(detailTransferStockMP);
				
					
				}else {
					JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» Introuvable dans le magasin selectionnerr", "Erreur", JOptionPane.ERROR_MESSAGE);

				}

		
if(listDetailTransferStockMP.size()==0)
{
	detailtransfermp=true;
}
	
	return listDetailTransferStockMP;
	
}


List<DetailTransferProduitFini> remplirDetailTransferProduitFini(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	
	List<DetailTransferProduitFini> listDetailTransferStockPF= new ArrayList<DetailTransferProduitFini>();
	

	
		DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(combomp.getSelectedItem());
		Articles article=mapArticle.get(comboarticle.getSelectedItem());
		quantite=new BigDecimal(txtquantite.getText());
		
		StockPF stockPFDestination=stockpfdao.findStockByMagasinPF(article.getId(), magasinDestination.getId());
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		
	if(detailtransfermp==false)
	{
		/*sommeStock=quantite+stockMPDestination.getStock();*/
		if(stockPFDestination!=null)
		{
			
			stockDestination=stockPFDestination.getStock().add(quantite);
			
			
			if(DateUtils.getAnnee(dateTransfereChooser.getDate())==2023)
			{
				if(matierePremier.getPrix()!=null)
				{
					prixStockDestination=matierePremier.getPrix();
				}else
				{
					prixStockDestination=BigDecimal.ZERO;
				}
			}else
			{
				
				if(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))!=null)
				{
					prixStockDestination=matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()));
				}else
				{
					prixStockDestination=BigDecimal.ZERO;
				}
				
			}
			
			
			
			
			
			//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
			
			//=prixMoyen/sommeStock;
		//	stockMPDestination.setPrixUnitaire(prixMoyen);
			
			stockPFDestination.setStock(stockDestination);
		
			stockpfdao.edit(stockPFDestination);
			
			
			detailTransferStockPF.setMagasinDestination(magasinDestination);
			detailTransferStockPF.setMagasinSouce(magasinDestination);
			
			detailTransferStockPF.setArticle(article);
			detailTransferStockPF.setQuantite(quantite);
			detailTransferStockPF.setPrixUnitaire(prixStockDestination);
			detailTransferStockPF.setDateTransfer(dateTransfereChooser.getDate());
			detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
			listDetailTransferStockPF.add(detailTransferStockPF);
		}else
		{
			
			StockPF stockpf=new StockPF();
			stockpf.setArticles(article);
			stockpf.setMagasin(magasinDestination);
			stockpf.setStock(quantite);
			stockpf.setStockMin(BigDecimal.ONE);
			
			if(DateUtils.getAnnee(dateTransfereChooser.getDate())==2023)
			{
				
				if(matierePremier.getPrix()!=null)
				{
					stockpf.setPrixUnitaire(matierePremier.getPrix());
				}else
				{
					stockpf.setPrixUnitaire(BigDecimal.ZERO);
				}
			}else
			{
				
				if(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))!=null)
				{
					stockpf.setPrixUnitaire(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate())));
				}else
				{
					stockpf.setPrixUnitaire(BigDecimal.ZERO);
				}
				
			}
			
			
			
		
			
			//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
			
			//=prixMoyen/sommeStock;
		//	stockMPDestination.setPrixUnitaire(prixMoyen);
			
		
			stockpfdao.add(stockpf);
		
			detailTransferStockPF.setMagasinDestination(magasinDestination);
			detailTransferStockPF.setMagasinSouce(magasinDestination);
			
			detailTransferStockPF.setArticle(article);
			detailTransferStockPF.setQuantite(quantite);
			if(DateUtils.getAnnee(dateTransfereChooser.getDate())==2023)
			{
				if(matierePremier.getPrix()!=null)
				{
					detailTransferStockPF.setPrixUnitaire(matierePremier.getPrix());
				}else
				{
					detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
				}
				
			}else
			{
				
				if(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate()))!=null)
				{
					detailTransferStockPF.setPrixUnitaire(matierePremier.getPrixByYear(DateUtils.getAnnee(dateTransfereChooser.getDate())));
				}else
				{
					detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
				}
				
				
				
			}
			
			
			
			detailTransferStockPF.setDateTransfer(dateTransfereChooser.getDate());
			detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
			listDetailTransferStockPF.add(detailTransferStockPF);
			
		}
		
	}
		
	
if(listDetailTransferStockPF.size()==0)
{ 
	stockSource=stockMPSource.getStock().add(quantite);
	stockMPDAO.edit(stockMPSource);
	detailtransferpf=true;
	
	
}
	
	return listDetailTransferStockPF;

	
}


public void CalculerStockFinale( Magasin magasin , Date dat)
{

	detailTransferStockMPDAO.ViderSession();
	
	MatierePremier matierePremier=null;
	
	
		
		SubCategorieMp subCategorieMp=null;
			CategorieMp categorieMp=null;
			MatierePremier mp=null;
		
			
		  	FournisseurMP fournisseurMP=null;
		
		  	if(magasin==null)
		  	{
		  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
		  		return;
		  	}
		  	
		  	
		  	
			if(dat==null)
		  	{
		  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de situation SVP");
		  		return;
		  	}
			
			
			
			Date mindate=null;
			
			Mindate=detailTransferStockMPDAO.MinDate(magasin);
			
			for(int i=0;i<Mindate.size();i++)
			{
				
				 Object[] object= Mindate.get(i);
				
				
				if(Mindate.get(i)!=null)
				{
					mindate=(Date)object[0];
				}
				
			}
			
			String patternYear = "yyyy";
			String patternDate = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
			SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

			
			
			
			if(mindate!=null)
			{
				
				String date = simpleDateFormatDate.format(mindate);
				
				
				try {
				mindate=simpleDateFormatDate.parse(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}else
			{
				String year = simpleDateFormatyear.format(dat);
				
				try {
				mindate=simpleDateFormatDate.parse(year+"-01-01");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
			
			
			
			SubCategorieMp subCategorieMpthe=SubCategorieMPDAO.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
			
			
		  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
		  	  				  	
			
				listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

			
				
				
			
			listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);

			
////////////////////////////////////////////////////////////////////////////////////////////////////Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

			
			
				listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
				
			

listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dat, magasin, subCategorieMpthe, null, null);
					
listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dat, magasin, subCategorieMpthe, null, null);

			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
		
	listEtatStockMP.clear();
	listEtatStockMPAfficher.clear();
	CalculerStockMPThe();	
	CalculerStockMPNonThe();		
		
		
	for(int j=0;j<listEtatStockMP.size();j++)
	{
		
	EtatStockMP etatStockMP=listEtatStockMP.get(j);	
		
	if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
	{
		
		if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
		{
			
		listEtatStockMPAfficher.add(etatStockMP);
		}
		
		
		
		
	}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
	{
		if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
		{
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
		}
		
		
	}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
	{
		
		if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
		{
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
		}
		
	}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
	{
		
		if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
		{
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
		}
		
	}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
	{
		
		if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
		{
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
		}
		
	}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
	{
		
		if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
		{
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
		}
		
	}else
	{
		
		
		listEtatStockMPAfficher.add(etatStockMP);	
		
		
		
	}
		
		
	
	
	
	
	
	
	
		
		
		
		
		
	}
	

	
	
	
	

	
	




}



public void CalculerStockMPThe()
{







	
	for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
	{
		
		 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
		EtatStockMP etatStockMP=new EtatStockMP();
		MatierePremier mp= (MatierePremier)object[0];
		
		FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		
		etatStockMP.setMp(mp);
								
		etatStockMP.setQuantiteInitial((BigDecimal)object[2]);
		
		etatStockMP.setFournisseurMP(fournisseurMP);
		 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
		etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
		listEtatStockMP.add(etatStockMP);
		
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
boolean existe=false;

  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurReception.size() ; j++) {
  
	  existe=false;
	  
  Object[] object=listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
  MatierePremier mp=(MatierePremier)object[0];
  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
	  
  for(int k=0;k<listEtatStockMP.size();k++) {
  
  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	  existe=true;
  
  EtatStockMP etatStockMP=listEtatStockMP.get(k);
  
 
  if((BigDecimal)object[2]!=null)
  {
	  etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add((BigDecimal)object[2]) ); 
  }
 

  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
  listEtatStockMP.set(k, etatStockMP);
  
  
  
  }
  

  
  
  
  
  
  }
  
	if(existe==false)
	{
		
		
		
		  EtatStockMP etatStockMP=new EtatStockMP();
		 
		  etatStockMP.setMp(mp);
		  etatStockMP.setFournisseurMP(fournisseurMP);
		  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
		  if((BigDecimal)object[2]!=null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[2]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		 
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		  
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
		 
		  listEtatStockMP.add(etatStockMP);	
		
		
	}
  
  
  
  
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 existe=false;
	 
	 BigDecimal enter=BigDecimal.ZERO;
	 BigDecimal transfert=BigDecimal.ZERO;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurEntrer.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMPByFournisseurEntrer.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
	  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
	  
	  enter=BigDecimal.ZERO;
	  transfert=BigDecimal.ZERO;
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  enter=BigDecimal.ZERO;
		  transfert=BigDecimal.ZERO;
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
		  existe=true;
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	  
	 
	  if(((BigDecimal)object[2])!=null)
	  {
		  enter=(BigDecimal)object[2];
		 
	  }
	  if(((BigDecimal)object[3])!=null)
	  {
		  transfert=(BigDecimal)object[3];
		 
	  }
	  
	  
	  etatStockMP.setTransferEntrer (etatStockMP.getTransferEntrer().add(enter.add(transfert)));
	 
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setFournisseurMP(fournisseurMP);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  if(((BigDecimal)object[2])!=null)
			  {
				  enter=(BigDecimal)object[2];
				 
			  }
			  if(((BigDecimal)object[3])!=null)
			  {
				  transfert=(BigDecimal)object[3];
				 
			  }  
			  
			  
			 
			etatStockMP.setTransferEntrer (enter.add(transfert));
			 
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
		}
	  
	  
	  
	  
	  }
	   
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;

for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurSortie.size() ; j++) {

existe=false;
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;
Object[] object=listeObjectEtatStockGroupByMPByFournisseurSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if(((BigDecimal)object[2])!=null)
{
sortie=(BigDecimal)object[2];
 
}
if(((BigDecimal)object[3])!=null)
{
  transfert=(BigDecimal)object[3];
 
}  
etatStockMP.setTransferSortie (etatStockMP.getTransferSortie(). add(sortie.add(transfert)));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
sortie=(BigDecimal)object[2];
 
}
if(((BigDecimal)object[3])!=null)
{
  transfert=(BigDecimal)object[3];
 
} 


etatStockMP.setTransferSortie(sortie.add(transfert));


etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal charge=BigDecimal.ZERO;
BigDecimal chargesupp=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurCharger.size() ; j++) {
 charge=BigDecimal.ZERO;
 chargesupp=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurCharger.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
charge=BigDecimal.ZERO;
 chargesupp=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if((BigDecimal)object[2]!=null)
{
charge=(BigDecimal)object[2];

}
etatStockMP.setQuantiteCharger(etatStockMP.getQuantiteCharger().add(charge));
if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteChargerSupp(etatStockMP.getQuantiteChargerSupp().add(chargesupp));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);

if((BigDecimal)object[2]!=null)
{
charge=(BigDecimal)object[2];

}

if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteCharger(charge);

etatStockMP.setQuantiteChargerSupp(chargesupp);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
retour=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retour=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if((BigDecimal)object[2]!=null)
{
retour=(BigDecimal)object[2];

}
etatStockMP.setQuantiteRetour (etatStockMP.getQuantiteRetour().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
if((BigDecimal)object[2]!=null)
{
retour=(BigDecimal)object[2];

}

etatStockMP.setQuantiteRetour(retour);


etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
sortie=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size() ; j++) {
 sortie=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
 sortie=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortie));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortie));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   SortiePF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortiePF=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size() ; j++) {

sortiePF=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {

sortiePF=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortiePF=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortiePF));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortiePF=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortiePF));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal sortieEnAttente=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size() ; j++) {
sortieEnAttente=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
sortieEnAttente=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortieEnAttente=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortieEnAttente));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortieEnAttente=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortieEnAttente));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Perte////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal perte=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size() ; j++) {
perte=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
perte=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
perte=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(perte));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
perte=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((perte));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
retour=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size() ; j++) {
retour=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retour=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
retour=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
retour=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retour));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour Fournisseur Annule////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
retourFournisseurAnnule=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.size() ; j++) {
retourFournisseurAnnule=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retourFournisseurAnnule=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retourFournisseurAnnule));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retourFournisseurAnnule));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}




////////////////////////////////////////////////////////////////////////////////////////////////////////Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal resteMachine=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurResterMachine.size() ; j++) {
resteMachine=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
resteMachine=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
resteMachine=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(resteMachine));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
resteMachine=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteResterMachine(resteMachine);


etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	  

////////////////////////////////////////////////////////////////////////////////////////////////////////Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal fabriquer=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurFabrique.size() ; j++) {
fabriquer=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
fabriquer=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
fabriquer=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteFabriquer (etatStockMP.getQuantiteFabriquer().add(fabriquer));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
fabriquer=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteFabriquer(fabriquer);


etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	

////////////////////////////////////////////////////////////////////////////////////////////////////////Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal existante=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurExistante.size() ; j++) {
existante=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurExistante.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
existante=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);

}

etatStockMP.setQuantiteExistante(etatStockMP.getQuantiteExistante().add(existante));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteExistante(existante);


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
	boolean trouver=false;
	
	  for(int j=0;j<listeEtatStockTransfertEnAttenteThe.size() ; j++) {
	  
		  trouver=false;
		  
	  DetailTransferStockMP detailTransferStockMP=listeEtatStockTransfertEnAttenteThe.get(j);
	 
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==detailTransferStockMP.getFournisseur().getId()) {
		
		if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
			
			
		{
		if(detailTransferStockMP.getMagasinSouce()!=null)	
		{
			
			if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
				
			{
				
				
				  trouver=true;
				  
				  EtatStockMP etatStockMP=listEtatStockMP.get(k);
				  
				 
				  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
				 		  listEtatStockMP.set(k, etatStockMP);
				
				
				
				
			}
			
			
			
		}
			
			
			
			
			
		}
		  
	
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(trouver==false)
		{
			
			 if(detailTransferStockMP.getFournisseur()!=null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							  EtatStockMP etatStockMP=new EtatStockMP();
								 
							  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
							  etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
							  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
							  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
							  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
							  etatStockMP.setTransferSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
							  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
							  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
							  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
							  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
							  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
							  
							  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
							 
							  listEtatStockMP.add(etatStockMP);	
							
						}
							
						}
					}
					}
			 
		
			
			
		}
	  
	  
	  
	  
	  }
  
  
  
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  
  
  
  
  
  
 
  
  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
	 
	  if(etatStockMP.getFournisseurMP()!=null)
	  {
		  System.out.println(etatStockMP.getMp().getCode() +" *** "+etatStockMP.getFournisseurMP().getCodeFournisseur() + "****"+etatStockMP.getQuantiteInitial()+" *** "+etatStockMP.getQuantiteReception()+" *** "+ etatStockMP.getQuantiteRetour()+" *** "+etatStockMP.getTransferEntrer()+" *** "+etatStockMP.getQuantiteResterMachine()+" *** "+etatStockMP.getQuantiteFabriquer() +" ---- "+ etatStockMP.getQuantiteCharger() +" *****" + etatStockMP.getQuantiteChargerSupp()+" ***** "+etatStockMP.getQuantiteAutreSortie() +" ***** "+ etatStockMP.getTransferSortie() +" ***** "+ etatStockMP.getQuantiteExistante());

	  }
	  
	  
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  }

	

}

public void CalculerStockMPNonThe()
{




	
	for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
	{
		
		 Object[] object=listeObjectStockInitialGroupByMP.get(i);
		EtatStockMP etatStockMP=new EtatStockMP();
		MatierePremier mp= (MatierePremier)object[0];
		etatStockMP.setMp(mp);
								
		etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
		
	
		 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
		
		//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
		listEtatStockMP.add(etatStockMP);
		
		
	}


boolean existe=false;

  for(int j=0;j<listeObjectEtatStockGroupByMP.size() ; j++) {
  
	  existe=false;
	  
  Object[] object=listeObjectEtatStockGroupByMP.get(j);
  MatierePremier mp=(MatierePremier)object[0];
	  
  for(int k=0;k<listEtatStockMP.size();k++) {
  
  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
	  if(listEtatStockMP.get(k).getFournisseurMP()==null)
	  {
		 
		  
		  
		  existe=true;
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		  if(etatStockMP.getMp().getCode().equals("MP_703"))
		  {
			  
			System.out.println(etatStockMP.getMp().getCode());  
			  
		  }
		 
		  if((BigDecimal)object[1] != null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
		  {
			  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
		  }else
		  {
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  }
		 
		  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
		  {
			  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
		  }else
		  {
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  }
		 if((BigDecimal)object[4]!=null)
		 {
			 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
		 }else
		 {
			 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
		 }
		  
		 if((BigDecimal)object[5]!=null)
		 {
			 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
		 }else
		 {
			 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		 }
		 
		 if((BigDecimal)object[8]!=null)
		 {
			 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
		 }else
		 {
			 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		 }
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

		 }else
		 {
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

		 }
		 if(((BigDecimal)object[10])!=null)
		 {
			  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
		 }else
		 {
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		 }
		
		 if((BigDecimal)object[11]!=null)
		 {
			  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
		 }else
		 {
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		 }
		if((BigDecimal)object[12]!=null)
		{
			 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
		}else
		{
			 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		}
		 
		  
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.set(k, etatStockMP);
	  }

  
  }
  

  
  
  
  
  
  }
  
	if(existe==false)
	{
		
		
		
		  EtatStockMP etatStockMP=new EtatStockMP();
		 
		  etatStockMP.setMp(mp);
		  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
		  
		  if((BigDecimal)object[1] != null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
		  {
			  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
		  }else
		  {
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  }
		 
		  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
		  {
			  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
		  }else
		  {
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  }
		 if((BigDecimal)object[4]!=null)
		 {
			 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
		 }else
		 {
			 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
		 }
		  
		 if((BigDecimal)object[5]!=null)
		 {
			 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
		 }else
		 {
			 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		 }
		 
		 if((BigDecimal)object[8]!=null)
		 {
			 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
		 }else
		 {
			 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		 }
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

		 }else
		 {
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

		 }
		 if(((BigDecimal)object[10])!=null)
		 {
			  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
		 }else
		 {
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		 }
		
		 if((BigDecimal)object[11]!=null)
		 {
			  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
		 }else
		 {
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		 }
		if((BigDecimal)object[12]!=null)
		{
			 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
		}else
		{
			 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		}
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.add(etatStockMP);	
		
		
		
	}
  
  
  
  
  }
 
/////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  
	boolean trouver=false;
	
	  for(int j=0;j<listeEtatStockTransfertEnAttenteNonThe.size() ; j++) {
	  
		  trouver=false;
		  
	 DetailTransferStockMP  detailTransferStockMP=listeEtatStockTransfertEnAttenteNonThe.get(j);
	
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId()) {
		  if(listEtatStockMP.get(k).getFournisseurMP()==null)
		  {
			 if(detailTransferStockMP.getFournisseur()==null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							
							  trouver=true;
							  
							  EtatStockMP etatStockMP=listEtatStockMP.get(k);
							  
							
							  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
							 		  listEtatStockMP.set(k, etatStockMP);
							
							
							
							
						}
						
						
						
					}
						
						
						
						
						
					}
				 
				 
				 
			 }
			 
		  }

	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(trouver==false)
		{
			
			 if(detailTransferStockMP.getFournisseur()==null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							
							
							  EtatStockMP etatStockMP=new EtatStockMP();
								 
							  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
							  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
							  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
							  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
							  etatStockMP.setTransferSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
							  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
							  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
							  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
							  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
							  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
							  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
							 
							  listEtatStockMP.add(etatStockMP);	
							
							
							
							
							
							
							
						}
					}
					}
			 }
			
			
			
			
			
		}
	  
	  
	  
	  
	  }
  
  
  
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  
  
  
  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
	 
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  }




}






}

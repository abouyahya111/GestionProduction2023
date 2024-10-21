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


public class SituationManquePlusManqueMoinsEnVrac extends JLayeredPane implements Constantes{
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
	private List<SituationManquePlusEnVrac> listSituationManquePlusEnVrac=new ArrayList<SituationManquePlusEnVrac>();
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	 List<Magasin> listClient=new ArrayList<Magasin>();
	 private Map< String, String> mapClient = new HashMap<>();
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
	  JComboBox combotypeEnVrac = new JComboBox();
	  JComboBox comboFournisseur = new JComboBox();
	  JComboBox comboPlusMoins = new JComboBox();
	  CategorieMpDAO categorieMpDAO;
	  JLabel totalmoins = new JLabel("0.00");
	  JLabel totalplus = new JLabel("0.00");
	  JLabel totalfabrique = new JLabel("0.00");
	  JLabel totalcharger = new JLabel("0.00");
	  JLabel differenceplusmoins = new JLabel("0.00");
	  private JTextField txtCodeMP =new JTextField();
	  JComboBox ComboMP = new JComboBox();
	  JComboBox comboClient = new JComboBox();
	  JLabel totalmontantplus = new JLabel("0.00");
	  JLabel totalmontantmoins = new JLabel("0.00");
	  JLabel differencemontantplusmoins = new JLabel("0.00");
	  BigDecimal totalmoinsCalculer=BigDecimal.ZERO ;
		BigDecimal totalPlusCalculer=BigDecimal.ZERO ;
		BigDecimal totalMontantmoinsCalculer=BigDecimal.ZERO ;
		BigDecimal totalMontantPlusCalculer=BigDecimal.ZERO ;
		BigDecimal totalMontantDechetCalculer=BigDecimal.ZERO ;
		BigDecimal totalDechetCalculer=BigDecimal.ZERO ;
		
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationManquePlusManqueMoinsEnVrac() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 825);
        try{
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	categorieMpDAO=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	ArticleDAO=new ArticlesDAOImpl();
        	manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	  listArticle=ArticleDAO.findAll();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  			     			"Num Production","Date Production","Article","En Vrac","Type En Vrac","Fournisseur","Quantite Existante","Quantite Charge","Quantite Charge Supp","Quantite Fabrique","Quantite Offre","Quantite Retour","Manque Plus","Manque Moins","Prix","Montant Plus","Montant Moins"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
				  			     			"Num Production","Date Production","Article","En Vrac","Type En Vrac","Fournisseur","Quantite Existante","Quantite Charge","Quantite Charge Supp","Quantite Fabrique","Quantite Offre","Quantite Retour","Manque Plus","Manque Moins","Prix","Montant Plus","Montant Moins"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
				  		     	layeredPane.setBounds(9, 11, 1458, 134);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1246, 53, 82, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCoutMP.clear();

			if(combodepot.getSelectedItem().equals("") )
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner depot SVP !!!");
				return;
			}
			else if(comboMagasin.getSelectedItem().equals("") )
			{

				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!");
				return;
			
			
			}else
			{
				
				Depot depot=mapDepotSource.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				Articles articles=mapArticle.get(comboarticle.getSelectedItem());
				MatierePremier matierePremier=mapMatierePremiere.get(ComboMP.getSelectedItem());
				String client=mapClient.get(comboClient.getSelectedItem());
				FournisseurMP fournisseurMP=mapfournisseur.get(comboFournisseur.getSelectedItem());
				
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
				
					
					if(depot==null || magasin==null )
					{
JOptionPane.showMessageDialog(null, "Veuillez Selectionner Depot et Magasin  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
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
				
				 if(!combotypeEnVrac.getSelectedItem().equals(""))
				 {
					 CategorieMp categorieMp=Mapcategorie.get(combotypeEnVrac.getSelectedItem());
					 if(categorieMp!=null)
					 {
						 requete=requete+" and matierePremier.categorieMp.nom= '"+categorieMp.getNom()+"'"; 
					 }
					
				 }
				 
				 if(fournisseurMP!=null)
				 {
					
						 requete=requete+" and  fournisseurMP.id='"+fournisseurMP.getId()+"'"; 
						
					 
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
				 
				 if(matierePremier!=null)
				 {
					 requete=requete+" and matierePremier.id='"+matierePremier.getId()+"'";
				 }
				 
				 if(client!=null)
				 {
					 
					 requete=requete+" and matierePremier.type= '"+client+"'"; 
				 }
				 
				 
				 requete=requete+" and matierePremier.categorieMp.subCategorieMp.id=1";
				 
			
				 
				
				 listCoutMP=productionDAO.listeSituationManqueEtPLus(requete) ;
				 
			listSituationManquePlusEnVrac.clear();	 
			 boolean existe=false; 
			 boolean trouver=false; 
		for(int i=0;i<listCoutMP.size();i++)
		{
			existe=false; 
			CoutMP coutMP=listCoutMP.get(i);
			
			ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(coutMP.getProdcutionCM().getNumOF(),Constantes.TYPE_DECHET_FOURNISSEUR);	
			if(manqueDechetFournisseur!=null)
			{
				listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByIdMPByManqueDechetFournisseur(manqueDechetFournisseur.getId(), coutMP.getMatierePremier().getId() );
				for(int j=0;j<listDetailManqueDechetFournisseur.size();j++)
				{
					DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(j);
					
					if(detailManqueDechetFournisseur.getEtat().equals(ETAT_VALIDER))
					{
						
						
						trouver=false;
						for(int d=0;d<listSituationManquePlusEnVrac.size();d++)
						{
							
							if(detailManqueDechetFournisseur.getManquedechetfournisseur().getNumOF().equals(listSituationManquePlusEnVrac.get(d).getProdcutionCM().getNumOF()))
							{
								if(detailManqueDechetFournisseur.getMatierePremier().getId()==listSituationManquePlusEnVrac.get(d).getMatierePremier().getId())
								{
									
									if(detailManqueDechetFournisseur.getFourniseur().getId()==listSituationManquePlusEnVrac.get(d).getFournisseurMP().getId())
									{
										trouver=true;
									}
									
									
									
									
								}
							}
							
						}
						
						
						
						
						if(trouver==false)
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
							situationManquePlusEnVrac.setPrixUnitaire(coutMP.getPrixUnitaire());
							situationManquePlusEnVrac.setQuantDechet(coutMP.getQuantDechet());
							situationManquePlusEnVrac.setMontantPlus(situationManquePlusEnVrac.getQuantiteManquanteFrPlus().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
							situationManquePlusEnVrac.setMontantMoins(situationManquePlusEnVrac.getQuantiteManquante().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
							situationManquePlusEnVrac.setMontantDechetUsine(situationManquePlusEnVrac.getQuantDechet().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
							situationManquePlusEnVrac.setMontantDechetFournisseur (situationManquePlusEnVrac.getQuantDechetFournisseur().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						
							
							
							listSituationManquePlusEnVrac.add(situationManquePlusEnVrac);
							existe=true; 
							
						}
						
					}
				
					
					
					
				}
				if(trouver==false)
				{
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
						situationManquePlusEnVrac.setQuantiteManquanteFrPlus(BigDecimal.ZERO);
						situationManquePlusEnVrac.setQuantiteManquante(BigDecimal.ZERO);
						situationManquePlusEnVrac.setPrixUnitaire(coutMP.getPrixUnitaire());
						situationManquePlusEnVrac.setQuantDechet(coutMP.getQuantDechet());
						situationManquePlusEnVrac.setMontantPlus(situationManquePlusEnVrac.getQuantiteManquanteFrPlus().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setMontantMoins(situationManquePlusEnVrac.getQuantiteManquante().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setMontantDechetUsine(situationManquePlusEnVrac.getQuantDechet().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
						situationManquePlusEnVrac.setMontantDechetFournisseur (situationManquePlusEnVrac.getQuantDechetFournisseur().multiply(situationManquePlusEnVrac.getPrixUnitaire()));
					
						
						
						listSituationManquePlusEnVrac.add(situationManquePlusEnVrac);
					}
				}
				
				
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
		 		
		 		


		 		
		 		if(comboPlusMoins.getSelectedItem().equals(""))	
		 		{
		 			parameters.put("titre", "SITUATION MANQUE PLUS ET MOINS EN VRAC");
		 		}else if(!comboPlusMoins.getSelectedItem().equals(""))
		 		{
		 			
		 			if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_PLUS))
		 			{
		 				parameters.put("titre", "SITUATION MANQUE PLUS EN VRAC");
		 			}else if(comboPlusMoins.getSelectedItem().equals(Constantes.MANQUE_MOINS))
		 			{
		 				parameters.put("titre", "SITUATION MANQUE MOINS EN VRAC");
		 			}
		 			
		 			
		 		}
		 		if(!dateDu.equals("") || !dateAu.equals(""))
		 		{
		 			parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
		 		}
		 		
		 		
		 		
		 		
				JasperUtils.imprimerSituationManquePlusEtMoinsEnVrac(listSituationManquePlusEnVrac,parameters); 
		 	
		 		
		 	}		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(418, 696, 104, 31);
		 add(button_1);
		 comboFournisseur = new JComboBox();
			comboFournisseur.setBounds(762, 57, 218, 27);
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
		  		 dateChooserDu.setBounds(598, 11, 137, 26);
		  		 layeredPane.add(dateChooserDu);
		  		 dateChooserDu.setLocale(Locale.FRANCE);
		  		 dateChooserDu.setDateFormatString("dd/MM/yyyy");
		  		 
		  		 JLabel label_1 = new JLabel("Au :");
		  		 label_1.setBounds(745, 13, 36, 24);
		  		 layeredPane.add(label_1);
		  		 label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		 
		  		  dateChooserAu = new JDateChooser();
		  		  dateChooserAu.setBounds(775, 11, 137, 26);
		  		  layeredPane.add(dateChooserAu);
		  		  dateChooserAu.setLocale(Locale.FRANCE);
		  		  dateChooserAu.setDateFormatString("dd/MM/yyyy");
		  		  
		  		  JLabel label_3 = new JLabel("Code Article :");
		  		  label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		  label_3.setBounds(931, 11, 74, 26);
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
		  		  txtcodearticle.setBounds(1003, 11, 93, 26);
		  		  layeredPane.add(txtcodearticle);
		  		  
		  		  JLabel label_4 = new JLabel("Libelle :");
		  		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		  label_4.setBounds(1111, 11, 45, 26);
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
		  		  comboarticle.setBounds(1156, 11, 218, 27);
		  		  layeredPane.add(comboarticle);
				
		  	    comboarticle.addItem("");
		  	    
		  	    JLabel lblTypeEnVrac = new JLabel("Type En Vrac :");
		  	    lblTypeEnVrac.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  	    lblTypeEnVrac.setBounds(10, 57, 70, 26);
		  	    layeredPane.add(lblTypeEnVrac);
		  	    
		  	     combotypeEnVrac = new JComboBox();
		  	     combotypeEnVrac.addItemListener(new ItemListener() {
		  	     	public void itemStateChanged(ItemEvent arg0) {
		  	     		
		  	     		if(!combotypeEnVrac.getSelectedItem().equals(""))
		  	     		{
		  	     			ComboMP.removeAllItems();
		  	     			txtCodeMP.setText("");
		  	     			ComboMP.addItem("");
		  	     			mapMatierePremiere.clear();
		  	     			mapCodeMatierePremiere.clear();
		  	     	CategorieMp categorieMp=Mapcategorie.get(combotypeEnVrac.getSelectedItem());
		  	     			
		  	     		listMatierePremiere=matierePremiereDAO.listeMatierePremierByCategorie(categorieMp.getCode()) 	;
		  	     			
		  	     		for(int i=0;i<listMatierePremiere.size();i++)
		  	     		{
		  	     			MatierePremier matierePremier=listMatierePremiere.get(i);
		  	     			
		  	     			ComboMP.addItem(matierePremier.getNom());
		  	     			mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
		  	     			mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
		  	     			
		  	     			
		  	     		}
		  	     			
		  	     			
		  	     			
		  	     		}else
		  	     		{
		  	     			ComboMP.removeAllItems();
		  	     			txtCodeMP.setText("");
		  	     			ComboMP.addItem("");
		  	     			mapMatierePremiere.clear();
		  	     			mapCodeMatierePremiere.clear();
		  	     		}
		  	     		
		  	     		
		  	     		
		  	     		
		  	     	}
		  	     });
		  	    combotypeEnVrac.setBounds(90, 57, 131, 27);
		  	    layeredPane.add(combotypeEnVrac);
			    int i=0;
				while(i<listArticle.size())
				{
					Articles article=listArticle.get(i);
					comboarticle.addItem(article.getLiblle());
					mapArticle.put(article.getLiblle(), article);
					mapCodeArticle.put(article.getCodeArticle(), article);
					
					
					i++;
					
				}
				
			combotypeEnVrac.addItem("");
			
			CategorieMp categorieChaara=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_CHAARA);
			combotypeEnVrac.addItem(categorieChaara.getNom());
			Mapcategorie.put(categorieChaara.getNom(), categorieChaara);
			
			CategorieMp categorieMkarkeb=categorieMpDAO.findByCode(Constantes.CATEGORIE_MATIERE_PREMIERE_MKARKAB);
			combotypeEnVrac.addItem(categorieMkarkeb.getNom());		
			Mapcategorie.put(categorieMkarkeb.getNom(), categorieMkarkeb);
			
			JLabel lblFournisseur = new JLabel("Fournisseur :");
			lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFournisseur.setBounds(690, 57, 73, 26);
			layeredPane.add(lblFournisseur);
			
			
			
			JLabel lblPlusMoins = new JLabel("Plus / Moins :");
			lblPlusMoins.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPlusMoins.setBounds(1000, 57, 73, 26);
			layeredPane.add(lblPlusMoins);
			
			 comboPlusMoins = new JComboBox();
			comboPlusMoins.setBounds(1072, 57, 146, 27);
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
				combotypeEnVrac.setSelectedItem("");
				dateChooserDu.setDate(null);
				dateChooserAu.setDate(null);
				ComboMP.setSelectedItem("");
				comboClient.setSelectedItem("");
				txtCodeMP.setText(Constantes.CODE_MP);	
			}
		});
		btnInitialiser.setText("Initialiser");
		btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInitialiser.setBounds(1355, 53, 93, 31);
		layeredPane.add(btnInitialiser);
		
		JLabel lblCodeMp = new JLabel("Code MP:");
		lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodeMp.setBounds(231, 57, 74, 26);
		layeredPane.add(lblCodeMp);
		
		txtCodeMP = new JTextField();
		txtCodeMP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				

  		  		

	    		

	     		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtCodeMP.getText().equals(""))
		      			{
		      				MatierePremier matierePremier=mapCodeMatierePremiere.get(txtCodeMP.getText().toUpperCase());
		      				
				    		
				    		if(matierePremier!=null)
				    		{	
				    			ComboMP.setSelectedItem(matierePremier.getNom());
				    			
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
		txtCodeMP.setText("");
		txtCodeMP.setColumns(10);
		txtCodeMP.setBounds(303, 57, 93, 26);
		layeredPane.add(txtCodeMP);
		
		JLabel label_6 = new JLabel("Libelle :");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(411, 57, 45, 26);
		layeredPane.add(label_6);
		
		 ComboMP = new JComboBox();
		 ComboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		


	     		if(ComboMP.getSelectedIndex()!=-1)
	     		{
	     			if(!ComboMP.getSelectedItem().equals(""))
			 		{
			 			MatierePremier matierePremier=mapMatierePremiere.get(ComboMP.getSelectedItem());
			 			txtCodeMP.setText(matierePremier.getCode());
			 			
			 		  				 			
			 		}else
			 		{
			 			txtCodeMP.setText(Constantes.CODE_MP);	
			 			
			 		}
	     		}else
	     		{
	     			txtCodeMP.setText(Constantes.CODE_MP);	
	     		}
	     		
	 			
		 	
	 		
	     	
  		   		
  		   		
  		   		
  		   		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		ComboMP.setBounds(456, 57, 218, 27);
		layeredPane.add(ComboMP);
		AutoCompleteDecorator.decorate(ComboMP);
		JLabel lblClient = new JLabel("Client :");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClient.setBounds(20, 96, 55, 26);
		layeredPane.add(lblClient);
		
		 comboClient = new JComboBox();
		comboClient.setBounds(75, 96, 202, 27);
		layeredPane.add(comboClient);
		
		 totalmoins = new JLabel("0.00");
		totalmoins.setHorizontalAlignment(SwingConstants.CENTER);
		totalmoins.setForeground(Color.RED);
		totalmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalmoins.setBounds(760, 637, 171, 39);
		add(totalmoins);
		
		 totalplus = new JLabel("0.00");
		totalplus.setHorizontalAlignment(SwingConstants.CENTER);
		totalplus.setForeground(Color.RED);
		totalplus.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalplus.setBounds(570, 637, 179, 39);
		add(totalplus);
		
		 totalfabrique = new JLabel("0.00");
		totalfabrique.setHorizontalAlignment(SwingConstants.CENTER);
		totalfabrique.setForeground(Color.RED);
		totalfabrique.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalfabrique.setBounds(281, 637, 294, 39);
		add(totalfabrique);
		
		 totalcharger = new JLabel("0.00");
		totalcharger.setHorizontalAlignment(SwingConstants.CENTER);
		totalcharger.setForeground(Color.RED);
		totalcharger.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalcharger.setBounds(69, 637, 247, 39);
		add(totalcharger);
		
		 differenceplusmoins = new JLabel("0.00");
		differenceplusmoins.setHorizontalAlignment(SwingConstants.CENTER);
		differenceplusmoins.setForeground(Color.RED);
		differenceplusmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		differenceplusmoins.setBounds(570, 688, 319, 39);
		add(differenceplusmoins);
		for(int j=0;j<listFournisseur.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(j);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		
		Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
		
		comboClient.addItem("");
		
		 totalmontantplus = new JLabel("0.00");
		totalmontantplus.setHorizontalAlignment(SwingConstants.CENTER);
		totalmontantplus.setForeground(Color.RED);
		totalmontantplus.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalmontantplus.setBounds(961, 637, 262, 39);
		add(totalmontantplus);
		
		 totalmontantmoins = new JLabel("0.00");
		totalmontantmoins.setHorizontalAlignment(SwingConstants.CENTER);
		totalmontantmoins.setForeground(Color.RED);
		totalmontantmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalmontantmoins.setBounds(1233, 637, 255, 39);
		add(totalmontantmoins);
		
		 differencemontantplusmoins = new JLabel("0.00");
		differencemontantplusmoins.setHorizontalAlignment(SwingConstants.CENTER);
		differencemontantplusmoins.setForeground(Color.RED);
		differencemontantplusmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		differencemontantplusmoins.setBounds(1007, 688, 460, 39);
		add(differencemontantplusmoins);
		
		
			
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
				 		
				 		parameters.put("quantitemanque", totalmoinsCalculer);
				 		parameters.put("montantmanque", totalMontantmoinsCalculer);
				 		parameters.put("quantitedechet", totalDechetCalculer);
				 		parameters.put("montantdechet", totalMontantDechetCalculer);
				 		
				 	
				 		if(!dateDu.equals("") || !dateAu.equals(""))
				 		{
				 			parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
				 		}
				 		
				 		
				 		
				 		
				 		
						JasperUtils.imprimerBonFraisManquePlusEtMoinsEnVrac(listSituationManquePlusEnVrac,parameters); 
				 	
				 		
				 	}		 	
				 	
				 	}
			});
			btnBonFrais.setText("Bon Frais");
			btnBonFrais.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnBonFrais.setBounds(238, 696, 104, 31);
			btnBonFrais.setIcon(imgImprimer);
			add(btnBonFrais);
			mapClient.put(Constantes.MP_INTERNE, Constantes.MP_INTERNE);
			
		
			txtCodeMP.setText(Constantes.CODE_MP);	
		
		
		ChargerMP();
		
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
	
	
	
	void ChargerMP()
	{
		SubCategorieMp subCategorieMp=subcategoriempdao.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		ComboMP.removeAllItems();
		ComboMP.addItem("");
  		listMatierePremiere=matierePremiereDAO.listeMatierePremierByidSubcategorie(subCategorieMp.getId());
  			for(int t=0;t<listMatierePremiere.size();t++)	
  			{
  				
  				MatierePremier matierePremier=listMatierePremiere.get(t);
  				
  				ComboMP.addItem(matierePremier.getNom());
  				mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  				mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
  				
  			}	
  			
  			ComboMP.setSelectedItem(""); 
	}
	
	
void afficher_tableMP(List<SituationManquePlusEnVrac> listSituationManquePlusEtMoinsEnVrac)
	{
	intialiserTableau2();
	
	
	
	 totalmoinsCalculer=BigDecimal.ZERO ;
	 totalPlusCalculer=BigDecimal.ZERO ;
	BigDecimal totalCharge=BigDecimal.ZERO ;
	BigDecimal totalfabrique=BigDecimal.ZERO ;
	BigDecimal diffenceplusmoins=BigDecimal.ZERO ;
	 totalMontantmoinsCalculer=BigDecimal.ZERO ;
	 totalMontantPlusCalculer=BigDecimal.ZERO ;
		int i=0;
		
		
 
		 
		while(i<listSituationManquePlusEtMoinsEnVrac.size())
		{	
			SituationManquePlusEnVrac SituationManquePlusEnVrac=listSituationManquePlusEtMoinsEnVrac.get(i);
			 
			
 totalDechetCalculer=totalDechetCalculer.add(SituationManquePlusEnVrac.getQuantDechet());
			 
			 totalCharge=totalCharge.add(SituationManquePlusEnVrac.getQuantCharge());
			 totalfabrique=totalfabrique.add(SituationManquePlusEnVrac.getQuantConsomme());
			 
			totalMontantDechetCalculer=totalMontantDechetCalculer.add(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantDechet()));
			
			
			
			
			 totalmoinsCalculer=totalmoinsCalculer.add(SituationManquePlusEnVrac.getQuantiteManquante());
			 totalPlusCalculer=totalPlusCalculer.add(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus());
			 
			 totalMontantmoinsCalculer=totalMontantmoinsCalculer.add(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquante()));
			 totalMontantPlusCalculer=totalMontantPlusCalculer.add(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus()));
			 
			SituationManquePlusEnVrac.setMontantMoins(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquante()));
			SituationManquePlusEnVrac.setMontantPlus(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus()));
			 
			 
			 
					 Object []ligne={SituationManquePlusEnVrac.getProdcutionCM().getNumOF(),SituationManquePlusEnVrac.getProdcutionCM().getDate(),SituationManquePlusEnVrac.getProdcutionCM().getArticles().getLiblle(),SituationManquePlusEnVrac.getMatierePremier().getNom(),SituationManquePlusEnVrac.getMatierePremier().getCategorieMp().getNom(),SituationManquePlusEnVrac.getFournisseurMP().getCodeFournisseur(),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantExistante()), NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantCharge()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantChargeSupp()), NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantConsomme()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantiteOffre()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantReste()), NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getQuantiteManquante()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getPrixUnitaire()),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquanteFrPlus())),NumberFormat.getNumberInstance(Locale.FRANCE).format(SituationManquePlusEnVrac.getPrixUnitaire().multiply(SituationManquePlusEnVrac.getQuantiteManquante())) };
					 modeleMP.addRow(ligne);
				 
					
			
			
			
			i++;
		}
		
		this.totalmoins.setText("Total Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalmoinsCalculer));
		this.totalplus.setText("Total Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer));
		this.totalcharger.setText("Total Charge : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalCharge));
		this.totalfabrique.setText("Total Fabriquer : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalfabrique));
		this.differenceplusmoins.setText("Difference : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlusCalculer.subtract(totalmoinsCalculer)));
		this.totalmontantmoins.setText("Total Montant Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantmoinsCalculer));
		this.totalmontantplus.setText("Total Montant Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer));
		this.differencemontantplusmoins.setText("Difference : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalMontantPlusCalculer.subtract(totalMontantmoinsCalculer)));
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
			     			"Num Production","Date Production","Article","En Vrac","Type En Vrac","Fournisseur","Quantite Existante","Quantite Charge","Quantite Charge Supp","Quantite Fabrique","Quantite Offre","Quantite Retour","Manque Plus","Manque Moins","Prix","Montant Plus","Montant Moins"

		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
		 tableSituationManqueEnVrac.getColumnModel().getColumn(16).setPreferredWidth(60);
}
}

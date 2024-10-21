package ProductionCarton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;


import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CheckableItem;
import util.CheckedComboBox;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CoutProdMPDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CoutProductionMPDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.EtatStockMP;
import dao.entity.FactureProduction;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class TerminerOrdreFabricationMP extends JLayeredPane implements Constantes{

	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleFournisseur;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleEquipeEm;
	private DefaultTableModel	 modeleEquipeGen;
	private JXTable table= new JXTable();
	private JXTable tableEmploye= new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private ImageIcon imgSupprimer;
	private JButton btnChercherOF;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	private List<ProductionMP> listProductionMP=new ArrayList<ProductionMP>();
	private List<DetailProductionMP> listDetailProductionMPTMP=new ArrayList<DetailProductionMP>();
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	private List<FournisseurAdhesive> listFournisseurAdhesive =new ArrayList<FournisseurAdhesive>();
	private List<DetailProductionMP> listDetailProductionMP =new ArrayList<DetailProductionMP>();
	private ProductionMP productionMP = new ProductionMP();
	private Map< String, ProductionMP> mapProductionMP = new HashMap<>();
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();
	
	private Map< String, String> mapQuantiteConsomme = new HashMap<>();
	private Map< String, String> mapQuantiteDechet = new HashMap<>();
	private Map< String, String> mapQuantiteDechetFournisseur = new HashMap<>();
	private Map< String, String> mapQuantiteReste = new HashMap<>();
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalEmployeEmbalage=BigDecimal.ZERO;
	private BigDecimal coutTotalAutreEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private BigDecimal coutTotalDechet=BigDecimal.ZERO;
	private BigDecimal delaiTotal=BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;
	
	private DetailProductionMPDAO detailproductionMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionMPDAO productionMPDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private ParametreDAO parametreDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private  EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private boolean validerSaisie=false;
	private String codeDepot;
	private Map< String, String> mapCodeFournisseurMP = new HashMap<>();
	private Map< String, String> mapQuantiteManquante = new HashMap<>();
	private Map< String, String> mapQuantiteManquanteFrPlus = new HashMap<>();
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	
	 JComboBox txtNumOF = new JComboBox();
	 private JTextField txtcodeemployeproductioncarton;
	 private JTextField txtdelaiproductioncarton;
	 private JTextField txthsupp50productioncarton;
	 private JTextField txthsupp25productioncarton;
		JComboBox comboemployeproductioncarton = new JComboBox();
		JCheckBox checkabsentproductioncarton = new JCheckBox("Absent");
		JCheckBox checksortieproductioncarton = new JCheckBox("Sortie");
		JCheckBox checkretardproductioncarton = new JCheckBox("Retard");
		private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
		private Map< String, Employe> mapNomEmploye = new HashMap<>();
		private EmployeDAO employeDAO;
		private Map< String, BigDecimal> mapParametre = new HashMap<>();
		private TransferStockMPDAO transferStockMPDAO;
		private DetailTransferMPDAO detailTransfertMPDAO ;
		 private CoutProductionMPDAO coutProductionMPDAO;
		
		
	/////////////////////////////////////////////////////////////////////// Listes des etats Stock Finale MP	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
		
		
		private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
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
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
		
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
		private List <Object[]> Mindate=new ArrayList<Object[]>();
		private SubCategorieMPDAO subcategoriempdao;
		private JTable TableFournisseur;
		int position=-1;
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;	
		
		
		
		
		
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public TerminerOrdreFabricationMP(final ProductionMP productionMPP,String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1620, 716);
        
       
        
        try{
        	
        	
        	
        	
        	
        	delaiTotalEquipeEmbalage=BigDecimal.ZERO;
        	delaiTotal=BigDecimal.ZERO;
        	coutTotalEmployeEmbalage=BigDecimal.ZERO;
        	coutTotalDechet=BigDecimal.ZERO;
        	coutTotalMP=BigDecimal.ZERO;
        	 employeDAO=new EmployeDAOImpl();
        	listCoutProdMP =new ArrayList<CoutProdMP>();
        	listEmploye=new ArrayList<Employe>();
        	listDetailProductionMP =new ArrayList<DetailProductionMP>();
        	listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	mapDelaiEmploye = new HashMap<>();
        	mapDelaiEmployeEmabalage= new HashMap<>();
        	mapQuantiteConsomme = new HashMap<>();
        	mapQuantiteDechet = new HashMap<>();
        	mapQuantiteReste = new HashMap<>();
        	mapCodeFournisseurMP= new HashMap<>();
        	mapQuantiteManquante= new HashMap<>();
            mapQuantiteManquanteFrPlus=	new HashMap<>();
        	mapHeureSupp25EmployeEmbalage= new HashMap<>();
        	mapHeureSupp50EmployeEmbalage= new HashMap<>();
        	mapHeureSupp25EmployeProd= new HashMap<>();
        	mapHeureSupp50EmployeProd= new HashMap<>();
        	productionMPDAO=new ProductionMPDAOImpl();
        	detailproductionMPDAO=new DetailProductionMPDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	stockPFDAO=new StockPFDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	compteurResponsableProdDAO=new CompteurResponsableProdDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	factureProductionDAO=new FactureProductionDAOImpl();
        	fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
        	coutProductionMPDAO=new CoutProdMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	compteStockMPDAO=new CompteStockMPDAOImpl();
        	txtQuantiteRealise=new JTextField();
        	 util.Utils.copycoller(txtQuantiteRealise);
			 txtPrixServiceProd = new JTextField();
			 util.Utils.copycoller(txtPrixServiceProd);
			 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
			 
			 txtNumOF = new JComboBox();
			 txtNumOF.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		
			 		

			 		

			  		
			  		if(txtNumOF.getSelectedIndex()!=-1)
			  		{
			  			
			  			
			  	ProductionMP productionMP=productionMPDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER) ;  
			  			if(productionMP!=null)
			  			{
			  				txtNumOF.setToolTipText(productionMP.getStatut());
			  				
			  				if(productionMP.getStatut().equals(Constantes.ETAT_OF_TERMINER))
			  				{
			  					
			  					txtQuantiteRealise.setText(productionMP.getQuantiteReel()+"");	
			  				txtPrixServiceProd.setText(productionMP.getNbreHeure()+"");	
			  					
			  				listDetailProductionMPTMP.clear();  
	  						  
			  			  	List<CoutProdMP>listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
			  			  	 
			  			 
			  			  	
			  			  listDetailProductionMPTMP=productionMPDAO.listeDetailProduction(productionMP.getId()) ;
			  				
			  				afficher_tableMP(listCoutProdMPTmp);
			  				afficher_tableEmploye(listDetailProductionMPTMP);	
			  				}
			  					
			  					
			  				
			  				
			  				
			  				
			  			}else
			  			{
			  				txtQuantiteRealise.setText("");	
			  				txtPrixServiceProd.setText("");	
			  				List<CoutProdMP>listCoutProdMPTmp=new ArrayList<CoutProdMP>();
			  				listDetailProductionMPTMP.clear();
			  				afficher_tableMP(listCoutProdMPTmp);
			  				afficher_tableEmploye(listDetailProductionMPTMP);	
			  			}
			  			
			  		}
			  		
			  		
			  		
			  	
			 		
			 		
			 		
			 	
			 		
			 		
			 		
			 	}
			 });
			 txtNumOF.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseEntered(MouseEvent e) {}
			 });
			    txtNumOF.setBounds(81, 8, 249, 24);
			
			    add(txtNumOF);
			  AutoCompleteDecorator.decorate(txtNumOF); 
			
			 listProductionMP=productionMPDAO.listeProductionMPEtatCreer(Constantes.ETAT_OF_LANCER,Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
		
		
			 for(int i=0;i<listProductionMP.size();i++)
			 {
				 
			ProductionMP productionMP= listProductionMP.get(i);
				 
				   txtNumOF.addItem(productionMP.getNumOFMP());
					mapProductionMP.put(productionMP.getNumOFMP(), productionMP);
			 }
			 txtNumOF.setSelectedIndex(-1);
			 
			 
        	if(productionMPP.getNumOFMP()!=null)
        	{
        		
        		productionMP=productionMPP;
        		txtNumOF.setSelectedItem(productionMP.getNumOFMP());
        		txtQuantiteRealise.setText(quantite);
        		txtPrixServiceProd.setText(nbreHeure);
        		
        		
        		AfficherMatierePremiere();
        		
        		
        	}
        	else {	
        		productionMP = new ProductionMP();
        	}
        	
        	listFournisseurAdhesive=fournisseurAdhesiveDAO.findAll();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        mapParametre=Utils.listeParametre();
        validerSaisie=false;
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();
          }
        codeDepot= AuthentificationView.utilisateur.getCodeDepot();
				  		
				  		  		intialiserTableMP();
				  		  		initialiserTableauEmploye();
				  		  	
				  		  btnChercherOF = new JButton("Chercher OF");
				  		  btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnChercherOF.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		
				  		  		
				  		productionMP=mapProductionMP.get(txtNumOF.getSelectedItem().toString());
				  		
				  		
				  				if(productionMP!=null){
				  				    
				  			  		 if(txtQuantiteRealise.getText().equals("")){
				  			  			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					  }	else {
				  						  
				  						listDetailProductionMPTMP.clear();  
				  						  
				  			  	List<CoutProdMP>listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
				  			  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
				  			//	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  			//	String dateProduction=dateFormat.format(productionMP.getDateProduction());
				  				
				  				//List<DetailProductionMP> listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
				  			  	
				  			  listDetailProductionMPTMP=productionMPDAO.listeDetailProduction(productionMP.getId());
				  				
				  				afficher_tableMP(listCoutProdMPTmp);
				  				afficher_tableEmploye(listDetailProductionMPTMP);
				  			
				  				
				  					  }
				  				}else{
				  				  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					
				  				}
				  		
				  		  	}
				  		  });
				  		  
				  		  
				  	
				  		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabricationMP.class.getResource("/img/chercher.png")));
				  		 btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnChercherOF.setBounds(1028, 9, 120, 23);
				  		 add(btnChercherOF);
				  		    
				  		    btnTerminerOF = new JButton("Terminer OF");
				  		    btnTerminerOF.setBounds(516, 681, 112, 24);
				  		    add(btnTerminerOF);
				  		    btnTerminerOF.setIcon(imgAjouter);
				  		    btnTerminerOF.addActionListener(new ActionListener() {
		  		     	public void actionPerformed(ActionEvent e) {
			  		     	
				  		     	  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Terminer cet Ordre de Fabrication?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										{
				  		     		if(txtQuantiteRealise.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(txtPrixServiceProd.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir le Prix Unitaire :Service Production!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     	
			  		     		}
				  		     		else {
				  		     		if(productionMP.getStatut().equals(Constantes.ETAT_OF_LANCER)) {
				  		     			
				  		     			if(validerSaisie==false){
				  		     				JOptionPane.showMessageDialog(null, "Il faut valider la saisie!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     			}else {
			  		     			BigDecimal coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalDechet).add(coutTotalMP);
				  		     			
			  		     			productionMP.setNbreHeure(new BigDecimal(txtPrixServiceProd.getText()));
			  		     			productionMP.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
			  		     		//	productionMP.setDateProduction(new Date());
			  		     			productionMP.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		 /*délai des employés Production*/
				  		     		
				  		     		for(int j=0;j<tableEmploye.getRowCount();j++){
			  		     			
				  		     			if(!tableEmploye.getValueAt(j, 3).toString().equals("")){
				  		     			mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 3).toString());
				  		     			delaiTotal=delaiTotal.add(new BigDecimal(tableEmploye.getValueAt(j, 3).toString())) ;
				  		     			}else 
			  		     				mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 4).toString().equals("")){
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 4).toString());
					  		     			}else 
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 5).toString().equals("")){
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 5).toString());
				  		     			}else 
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
			  		     		}
			  		     		
			  		     		/* délai des employés Emabalege*/
			  		     	
			  		     		
			  		     	
				  		     		//listDetailProductionMP=detailproductionMPDAO.ListHeursDetailProductionMPParProductionMP(productionMP) ;
				  		     		
				  		     		//productionMP.setDetailProductionsMP(calculeCoutEmploye(listDetailProductionMP,mapDelaiEmploye));
				  		     		remplirQuantite();
				  		     		 
				  		     		productionMP.setListCoutProdMP(calculeCoutMatierePremiere(productionMP.getListCoutProdMP()));
				  		     		
				  		     		calculeCoutEmploye(listDetailProductionMP, mapDelaiEmploye);
				  		     		
				  		     		productionMP.setCoutTotalMP(coutTotalMP);
				  		     		productionMP.setCoutTotalEmploye(coutTotalEmploye);
				  		     		productionMP.setCoutDechet(coutTotalDechet);
				  		     		coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalMP) ;
				  		     		productionMP.setCoutTotal(coutTotal);
				  		     		productionMP.setStatut(Constantes.ETAT_OF_TERMINER);
				  		     		productionMPDAO.edit(productionMP);
				  		     		calculerStockCoutProduitFini(coutTotal);
				  		     		
				  		     		 
				  		     		
			  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication Terminé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		
			  		     			}
			  		     		
			  		     		}else{
			  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication n'est pas encore lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
			  		     	  }
			  		     	 }
				  		     
				  		     	}
				  		     });
				  		    	btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     	 afficher_tableFournisseur(listFournisseurAdhesive);
				  		    	 
				  		    	validerSaisie=false;
				  		     		
				  		     	}
				  		     });
				  		     table.setSortable(false);
				  		  
				  		     table.setShowVerticalLines(true);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(8, 80, 1301, 186);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(7, 59, 1302, 23);
				  		     	add(titledSeparator);
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);
		tableEmploye.setSortable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 349, 1078, 308);
		add(scrollPane_1);
		tableEmploye.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEmploye.setShowVerticalLines(true);
		tableEmploye.setSelectionBackground(new Color(51, 204, 255));
		tableEmploye.setRowHeightEnabled(true);
		tableEmploye.setRowHeight(20);
		tableEmploye.setGridColor(new Color(0, 0, 255));
		tableEmploye.setForeground(Color.BLACK);
		tableEmploye.setColumnControlVisible(true);
		tableEmploye.setBackground(new Color(255, 255, 255));
		tableEmploye.setAutoCreateRowSorter(true);
	//	scrollPane_1.setViewportView(tableEmploye);
		  DefaultCellEditor ce1 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
	        JTextComponent textField1 = (JTextComponent) ce1.getComponent();
	        util.Utils.copycollercell(textField1);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(9, 265, 1300, 17);
		add(titledSeparator_1);
				  		    
				  		    JButton btnValiderSaisie = new JButton("Valider Saisie");
				  		    btnValiderSaisie.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		remplirQuantite();
				  		    		List<CoutProdMP>listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
								  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
				  		    		validerSaisiQuantiteDechetReste(listCoutProdMPTmp);
				  		    	}
				  		    });
				  		    btnValiderSaisie.setBounds(791, 681, 112, 24);
				  		    add(btnValiderSaisie);
				  		    
				  		  
				  		    txtQuantiteRealise.setBounds(502, 7, 153, 26);
				  		    add(txtQuantiteRealise);
				  		    txtQuantiteRealise.setColumns(10);
				  		    
				  		    lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
				  		    lblQuantitRalise.setBounds(399, 7, 102, 26);
				  		    add(lblQuantitRalise);
				  		    lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		   
				  		    txtPrixServiceProd.setBounds(849, 7, 153, 26);
				  		    add(txtPrixServiceProd);
				  		    txtPrixServiceProd.setColumns(10);
				  		    
				  		    JLabel lblQuantite = new JLabel("Delai Service Production :");
				  		    lblQuantite.setBounds(696, 7, 143, 26);
				  		    add(lblQuantite);
				  		    lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JButton btnAnnuler = new JButton("Annuler");
				  		    btnAnnuler.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		if(productionMP.getId()<0){
			  		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			
				  		    		}else {
				  		    			
				  		    			
				  		    			if(!productionMP.getStatut().equals(ETAT_OF_ANNULER)){
				  		    				 if(productionMP.getStatut().equals(ETAT_OF_TERMINER)){
				  		    					 annulerStockMatierePremiere(productionMP.getListCoutProdMP(),productionMP.getMagasinProd().getId(),productionMP.getMagasinStockage().getId());
				  		    			
				  		    					 deleteListeObject(ficheEmployeDAO.findByNumOf(productionMP.getNumOFMP()));
				  		    					 productionMP.setStatut(ETAT_OF_ANNULER);
				  		    					 productionMP.setUtilisateurAnnulation(AuthentificationView.utilisateur);
				  		    					 annulerStockProduitFini();
				  		    					 
				  		    					 productionMPDAO.edit(productionMP);
				  		    			
				  		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }else{
				  		    					JOptionPane.showMessageDialog(null, "OF doit étre Terminé", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }
				  		    			
				  		    			}else{
				  		    				JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			}
				  		    		}
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnAnnuler.setBounds(638, 682, 143, 23);
				  		    add(btnAnnuler);
				  		    
				  		    JButton btnSaisirDelaiEmploy_1 = new JButton("Saisir Delai Employ\u00E9 Production");
				  		    btnSaisirDelaiEmploy_1.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent arg0) {
				  		    		if(productionMP.getId()>0)
				  		    		{
				  		    			JFrame popupJFrame = new ListeEmployeProdCarton(productionMP,txtQuantiteRealise.getText(),txtPrixServiceProd.getText());
						  		    	  popupJFrame.setVisible(true);
				  		    		}else
				  		    		{
				  		    			JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		    		}
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnSaisirDelaiEmploy_1.setBounds(321, 682, 185, 23);
				  		    add(btnSaisirDelaiEmploy_1);
				  		  btnSaisirDelaiEmploy_1.setVisible(false);
				  		    JButton button = new JButton("");
				  		    button.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		     		if(listDetailProductionMP.size()!=0)
				  		     		{
				  		     			if(tableEmploye.getSelectedRow()!=-1)
					  		     		{
					  		     			DetailProductionMP detailProduction=listDetailProductionMP.get(tableEmploye.getSelectedRow()) ; 
					  		     			
					  		     			ProductionMP productionTmp=productionMPDAO.findByNumOFStatut(detailProduction.getProductionMP().getNumOFMP(), Constantes.ETAT_OF_LANCER);
					  		     			
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													
												  detailproductionMPDAO.delete(detailProduction.getId());
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 
												  listDetailProductionMP.remove(tableEmploye.getSelectedRow());
												  productionTmp.setDetailProductionsMP(listDetailProductionMP);
												  productionMPDAO.edit(productionTmp);
													
												  afficher_tableEmploye(listDetailProductionMP);
													
												}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF n'est pas lancé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
					  		     				return;
					  		     			}
					  		     			
					  		     		}
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		    	}
				  		    });
				  		    button.setBounds(1109, 424, 58, 23);
				  		  button.setIcon(imgSupprimer);
				  		    add(button);
				  		    
				  		   
				  		txtNumOF.setSelectedIndex(-1);
				  		
				  		JLabel label = new JLabel("Code :");
				  		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label.setBounds(8, 297, 37, 26);
				  		add(label);
				  		
				  		txtcodeemployeproductioncarton = new JTextField();
				  		txtcodeemployeproductioncarton.addKeyListener(new KeyAdapter() {
				  			@Override
				  			public void keyPressed(KeyEvent e) {
				  				

			  		     		

			  					
			  					if(e.getKeyCode()==e.VK_ENTER)
			  		      		{
			  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeproductioncarton.getText());
			  						if(employe!=null)
			  						{
			  							comboemployeproductioncarton.setSelectedItem(employe.getNomafficher());
			  						}else
			  						{
			  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
			  							return;
			  						}
			  						
			  		      		}
			  				
			  					
			  					
			  					
			  					
			  				
			  		     		
			  		     		
			  		     		
			  		     	
				  				
				  				
				  				
				  			}
				  		});
				  		txtcodeemployeproductioncarton.setColumns(10);
				  		txtcodeemployeproductioncarton.setBounds(44, 300, 71, 26);
				  		add(txtcodeemployeproductioncarton);
				  		
				  		JLabel label_1 = new JLabel("Employe :");
				  		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_1.setBounds(125, 300, 64, 26);
				  		add(label_1);
				  		
				  		 comboemployeproductioncarton = new JComboBox();
				  		 comboemployeproductioncarton.addItemListener(new ItemListener() {
				  		 	public void itemStateChanged(ItemEvent arg0) {
				  		 		

			  		   	 		
			  		   	 		if(!comboemployeproductioncarton.getSelectedItem().equals(""))
			  		   	 		{
			  		   	 			
			  		   	 			Employe employe=mapNomEmploye.get(comboemployeproductioncarton.getSelectedItem());
			  		   	 			if(employe!=null)
			  		   	 			{
			  		   	 				txtcodeemployeproductioncarton.setText(employe.getMatricule());
			  		   	 			}else
			  		   	 			{
			  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
			  		   	 				return;
			  		   	 			}
			  		   	 			
			  		   	 			
			  		   	 			
			  		   	 		}
			  		   	 		
			  		   	 	
				  		 		
				  		 	}
				  		 });
				  		comboemployeproductioncarton.setSelectedIndex(-1);
				  		comboemployeproductioncarton.setBounds(176, 305, 233, 24);
				  		add(comboemployeproductioncarton);
				  		 AutoCompleteDecorator.decorate(comboemployeproductioncarton);
				  		JLabel label_2 = new JLabel("Delai H :");
				  		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_2.setBounds(419, 307, 61, 26);
				  		add(label_2);
				  		
				  		txtdelaiproductioncarton = new JTextField();
				  		txtdelaiproductioncarton.setColumns(10);
				  		txtdelaiproductioncarton.setBounds(468, 306, 58, 26);
				  		add(txtdelaiproductioncarton);
				  		
				  		JLabel label_3 = new JLabel("H Supp 50% :");
				  		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_3.setBounds(532, 307, 77, 26);
				  		add(label_3);
				  		
				  		txthsupp50productioncarton = new JTextField();
				  		txthsupp50productioncarton.setColumns(10);
				  		txthsupp50productioncarton.setBounds(604, 307, 58, 26);
				  		add(txthsupp50productioncarton);
				  		
				  		JLabel label_4 = new JLabel("H Supp 25% :");
				  		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_4.setBounds(672, 307, 77, 26);
				  		add(label_4);
				  		
				  		txthsupp25productioncarton = new JTextField();
				  		txthsupp25productioncarton.setColumns(10);
				  		txthsupp25productioncarton.setBounds(746, 306, 58, 26);
				  		add(txthsupp25productioncarton);
				  		
				  		 checkabsentproductioncarton = new JCheckBox("Absent");
				  		checkabsentproductioncarton.setBounds(809, 309, 76, 23);
				  		add(checkabsentproductioncarton);
				  		
				  		 checksortieproductioncarton = new JCheckBox("Sortie");
				  		checksortieproductioncarton.setBounds(885, 309, 61, 23);
				  		add(checksortieproductioncarton);
				  		
				  		 checkretardproductioncarton = new JCheckBox("Retard");
				  		checkretardproductioncarton.setBounds(962, 305, 71, 24);
				  		add(checkretardproductioncarton);
				  		
				  		JButton button_1 = new JButton("Vider");
				  		button_1.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				
				  			ViderEmployeProductionCarton();	
				  				
				  			}
				  		});
				  		button_1.setBounds(1094, 304, 81, 32);
				  		add(button_1);
				  		
				  		JButton button_2 = new JButton("");
				  		button_2.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				
				  				Parametre heure=parametreDAO.findByDateByLibelle(productionMP.getDateProduction(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);

				  				BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  				boolean absent=false;
				  		   		int idEmploye;
				  		   		boolean sortie=false;
				  		   		boolean retard=false;
				  		    	
				  		  	if(!txtcodeemployeproductioncarton.getText().equals("") && !comboemployeproductioncarton.getSelectedItem().equals(""))
			  		      	{
				  				    	DetailProductionMP detailproductionMP=new DetailProductionMP();
				  				    	detaildelai=BigDecimal.ZERO;
				  				    	detailheur25=BigDecimal.ZERO;
				  				    	detailheur50=BigDecimal.ZERO;
				  				    	absent=false;
				  				    	sortie=false;
				  				    	retard=false;
				  				    //	int key=lsiteInt.get(i);
				  				    	Employe employe= mapMatriculeEmploye.get(txtcodeemployeproductioncarton.getText());
				  				    	
				  				    	if(employe!=null)
				  				    	{
				  				    		idEmploye=employe.getId();
				  				    	
				  				   
				  				    	if(checkabsentproductioncarton.isSelected()==true)
				  				    	{
				  				    		
				  				    		/*
				  				    		if(mapDelai.containsKey(employe.getNumDossier()))
				  				    		detaildelai=mapDelai.get(employe.getNumDossier());
				  				    	
				  				    	if(mapHeureSupp25.containsKey(employe.getNumDossier()))
				  				    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
				  				    	
				  				    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
				  				    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());
				  				    		*/
				  				    	
				  				    	
				  				    	absent=true;
				  				    	
				  				    	
				  				    	detailproductionMP.setEmploye(employe);
				  				    	detailproductionMP.setTypeResEmploye(employe.getTypeResEmploye());
				  				    	detailproductionMP.setDelaiEmploye(detaildelai);
				  				    	detailproductionMP.setHeureSupp25(detailheur25);
				  				    	detailproductionMP.setHeureSupp50(detailheur50);
				  				    	detailproductionMP.setAbsent(absent);
				  				    	
				  				    	detailproductionMP.setCoutTotal(detaildelai.multiply(heure.getValeur()));
				  				    	detailproductionMP.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
				  				    	detailproductionMP.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
				  				    	
				  				    	detailproductionMP.setCoutHoraire(heure.getValeur());
				  				    	detailproductionMP.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
				  				    	detailproductionMP.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  				    	
				  				    	detailproductionMP.setSortie(false);
				  				    	detailproductionMP.setRetard(false);
				  				    	detailproductionMP.setAutorisation(false);
				  				    	detailproductionMP.setValider(Constantes.ETAT_INVALIDER);
				  				    	detailproductionMP.setProductionMP(productionMP);
				  				    	listDetailProductionMP.add(detailproductionMP);
				  				    		
				  				    	}
				  				    	
				  				    	else if(checkabsentproductioncarton.isSelected()==false)
				  				    	{
				  				    	
				  				    		if(!txtdelaiproductioncarton.getText().equals(""))
				  				    		{
				  				    			
				  				    			if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  				    			{
				  				    				
				  				    				if(checksortieproductioncarton.isSelected()==true)
				  		  		    				{
				  		  		    				sortie=true;
				  		  		    				}
		 if(checkretardproductioncarton.isSelected()==true)
				  		  		    				{
				  		  		    					retard=true;
				  		  		    				}
		 
				  				    				detaildelai=new BigDecimal(txtdelaiproductioncarton.getText());
				  				    				
				  				    				if(!txthsupp25productioncarton.getText().equals(""))
				  		  		    				{
				  				    					
				  				    					if(new BigDecimal(txthsupp25productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    					{
				  		  		    					detailheur25=new BigDecimal(txthsupp25productioncarton.getText());
				  		  		    					}
				  				    					
				  		  		    				}
				  				    		    		
				  				    		    	
				  				    				if(!txthsupp50productioncarton.getText().equals(""))
				  		  		    				{
				  		  		    					if(new BigDecimal(txthsupp50productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    					{
				  		  		    					detailheur50=new BigDecimal(txthsupp50productioncarton.getText());
				  		  		    					}
				  		  		    					
				  		  		    					
				  		  		    				}
				  				    		    	
				  				    		    	detailproductionMP.setEmploye(employe);
				  				    		    	detailproductionMP.setTypeResEmploye(employe.getTypeResEmploye());
				  				    		    	detailproductionMP.setDelaiEmploye(detaildelai);
				  				    		    	detailproductionMP.setHeureSupp25(detailheur25);
				  				    		    	detailproductionMP.setHeureSupp50(detailheur50);
				  				    		    	
				  				    		   	detailproductionMP.setCoutTotal(detaildelai.multiply(heure.getValeur()));
						  				    	detailproductionMP.setCoutSupp25(detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
						  				    	detailproductionMP.setCoutSupp50(detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
						  				    	
						  				    	detailproductionMP.setCoutHoraire(heure.getValeur());
						  				    	detailproductionMP.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
						  				    	detailproductionMP.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
						  				    	
						  				    	
				  				    		    	detailproductionMP.setAbsent(absent);
				  				    		    	detailproductionMP.setSortie(sortie);
				  				    		    	detailproductionMP.setRetard(retard);
				  				    		    	detailproductionMP.setAutorisation(false);
				  				    		    	detailproductionMP.setProductionMP(productionMP);
				  							    	listDetailProductionMP.add(detailproductionMP);
				  				    		
				  				    			}
				  				    			
				  				    			}
				  				    		}
				  				    	
				  				    	}
				  				     }
				  				     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  				     		
				  				     		productionMP.setDetailProductionsMP(listDetailProductionMP);
				  				     		productionMPDAO.edit(productionMP);
				  				     		listDetailProductionMP.clear();
				  				     		listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());;
						  		  		     	 afficher_tableEmploye(listDetailProductionMP);
						  		  		  ViderEmployeProductionCarton();
				  			
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  			}
				  		});
				  		button_2.setBounds(1109, 380, 58, 23);
				  		button_2.setIcon(imgAjouter);
				  		add(button_2);
				  		  afficher_tableEmploye(productionMP.getDetailProductionsMP());
				  		  afficher_tableMP(productionMP.getListCoutProdMP());
				  		
				  		
				 	
				  		comboemployeproductioncarton.addItem("");	  
				  		
				  		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
				  		scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		scrollPane_2.setBounds(1319, 59, 471, 206);
				  		add(scrollPane_2);
				  		
				  		TableFournisseur = new JTable();
				  		TableFournisseur.setModel(new DefaultTableModel(
				  			new Object[][] {
				  			},
				  			new String[] {
				  				"Fourniseur", "Select"
				  			}
				  		));
				  		TableFournisseur.getColumnModel().getColumn(0).setPreferredWidth(106);
				  		TableFournisseur.setFillsViewportHeight(true);
				  		scrollPane_2.setViewportView(TableFournisseur);
				  		
				  		JButton btnValider = new JButton("Valider");
				  		btnValider.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				

			  		     		
			  		     		String fournisseurdechet="";
			  		     		
			  		     		
			  		     		for(int i=0; i<TableFournisseur.getRowCount();i++)
			  		     		{
			  		     			
			  		     			boolean fournisseurdeche=(boolean) TableFournisseur.getValueAt(i, 1);
			  		     			
			  		     			
			  		  			if(fournisseurdeche==true ){
			  		  				if(fournisseurdechet.equals(""))
			  		  				{
			  		  				fournisseurdechet=fournisseurdechet+TableFournisseur.getValueAt(i, 0);
			  		  				}else
			  		  				{
			  		  					
			  		  				fournisseurdechet=fournisseurdechet+", "+TableFournisseur.getValueAt(i, 0);
			  		  				}
			  		  			
			  		  					
			  		  			}
			  		     		
			  		     			
			  		     			
			  		     		}
			  		     		
			  		     		
			  		     		if(!fournisseurdechet.equals(""))
			  		     		{
			  		     			
			  		     			table.setValueAt(fournisseurdechet,position, 12);
			  		     		}
			  		     		
			  		     		
			  		     			
			  		     		afficher_tableFournisseur(listFournisseurAdhesive);
			  		     	
				  				
				  				
				  			}
				  		});
				  		btnValider.setBounds(1426, 275, 81, 32);
				  		add(btnValider);
				  		  
				  	  for(int i=0;i<listEmploye.size();i++)
					  {
						  
						Employe employe=listEmploye.get(i);  
						comboemployeproductioncarton.addItem(employe.getNomafficher());
						
					mapMatriculeEmploye.put(employe.getMatricule(), employe);
					 mapNomEmploye.put(employe.getNomafficher(), employe);
					  
					  
					  }  		  
				  		  
				  		  
				  		  
	}
	
	
	void	intialiserTableFournisseur(){
		  modeleFournisseur =new DefaultTableModel(
			  		     	new Object[][] {
			  		     	},
			  		     	new String[] {
			  		     			"Fournisseur", "Select"
			  		     	}
			  		     ) {
			  		     	boolean[] columnEditables = new boolean[] {
			  		     			false,false
			  		     	};
			  		     	public boolean isCellEditable(int row, int column) {
			  		     		return columnEditables[column];
			  		     	}
			  		     };
			  		     
			  		   TableFournisseur.setModel(modeleFournisseur); 
			  		//  table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jcombobox)); // ajouter ComboBox Code Fournisseur au Jtable
			  		 TableFournisseur.getColumnModel().getColumn(0).setPreferredWidth(30);
			  		TableFournisseur.getColumnModel().getColumn(1).setPreferredWidth(160);
			  		TableFournisseur.getTableHeader().setReorderingAllowed(false); 
			}
	
	
	void afficher_tableFournisseur(List<FournisseurAdhesive> listFournisseurMP)
	{
		
	 	position=table.getSelectedRow();
		modeleFournisseur =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Fournisseur","Select"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,true
				};
				
				Class[] columnTypes = new Class[] {
						String.class,Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		TableFournisseur.setModel(modeleFournisseur);
		TableFournisseur.getTableHeader().setReorderingAllowed(false); 
		int i=0;
		
		 
		while(i<listFournisseurMP.size())
		{	
			
			 FournisseurAdhesive fournisseurMP= listFournisseurMP.get(i);
		

			
			Object []ligne={fournisseurMP.getCodeFournisseur(), false};

			modeleFournisseur.addRow(ligne);
			i++;
		}
	}
	
	  void AfficherMatierePremiere()
	  {
			
		 
			  
		  	List<CoutProdMP>	listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
		  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
			
			
		
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dateproduction=dateFormat.format(productionMP.getDateProduction());
		
			
			
			List<DetailProductionMP> listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
		
			
			afficher_tableMP(listCoutProdMPTmp);
			afficher_tableEmploye(listDetailProductionMP);
			
			
	  }
	
void	intialiserTableMP(){
	
	/*
	
	JComboBox<CheckableItem> jcombobox=new JComboBox();
	
	
	  final CheckableItem[] codefournisseur =new CheckableItem[listFournisseurAdhesive.size()];
	  
	  for(int i=0;i<listFournisseurAdhesive.size();i++) {
	  
	  FournisseurAdhesive fournisseurAdhesive= listFournisseurAdhesive.get(i);
	  codefournisseur[i]=  new CheckableItem(fournisseurAdhesive.getCodeFournisseur(),false);
	  
	  }


jcombobox=new CheckedComboBox<CheckableItem>(new DefaultComboBoxModel<CheckableItem>(codefournisseur));
jcombobox.setEditable(false);
final ListModel<CheckableItem> model=jcombobox.getModel();
*/
		 modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP","Quantité Calculée","Quantité Existante","Quantité Chargée","Charge Supp", "Quantité Consommée", "Quantité Déchet","Quantité Déchet Fournisseur", "Quantité Restée", "QTE MOINS ","QTE PLUS","Code Fournisseur MP", "Ecart"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false, true, true, true,true,true,true,true,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		     
	  		   table.setModel(modeleMP); 
	  		 // table.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(jcombobox)); // ajouter ComboBox Code Fournisseur au Jtable
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(160);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(8).setPreferredWidth(100);
	  		   table.getColumnModel().getColumn(9).setPreferredWidth(60);
	  		 table.getColumnModel().getColumn(10).setPreferredWidth(60);
	  		 table.getColumnModel().getColumn(11).setPreferredWidth(60);
	  		 table.getColumnModel().getColumn(12).setPreferredWidth(60);
	  		 table.getColumnModel().getColumn(13).setPreferredWidth(60);
	  		 
	 
		  		    
	  		 
	  		  
	  		 
	  		 
	  		 
	  		 /*
	  		   jcombobox.addItemListener(new ItemListener() {

	 			  @Override public void itemStateChanged(ItemEvent e) { if (e.getStateChange()
	 			  == ItemEvent.SELECTED) {
	 			  
	 			  List<String> sl = new ArrayList<>(); 
	 			  for (int i = 0; i < model.getSize(); i++)
	 			  { 
	 				  CheckableItem v = model.getElementAt(i); 
	 				  if (v.isSelected()) 
	 				  {
	 			  sl.add(v.toString()); 
	 			  } 
	 				  } 
	 			  if (sl.isEmpty()) {
	 			  //JOptionPane.showMessageDialog(null, "Vide"); // When returning the empty  string, the height of JComboBox may become 0 in some cases. 
	 				  } else {
	 			  
	 			  table.setValueAt(String.valueOf(sl.stream().sorted().collect(Collectors.
	 			  joining(", "))), table.getSelectedRow(), table.getSelectedColumn()); 
	 			  
	 			   JOptionPane.showMessageDialog(null,sl.stream().sorted().collect(Collectors.joining(", ")));
	 			  
	 			  }
	 			  
	 			  
	 			 
	 			  } }
	 			 
	 	  		
	 	        }); */
	  		   
	  		   
	}
	
void afficher_tableMP(List<CoutProdMP> listCoutMP)
	{
	
	intialiserTableMP();
		  int i=0;
		  NumberFormat nf = new DecimalFormat("0.###");
		
			while(i<listCoutMP.size())
			{	
				CoutProdMP coutMP=listCoutMP.get(i);
			
				BigDecimal quantiteTotal=coutMP.getQuantite();
				BigDecimal quantiteExistante=coutMP.getQuantExistante();
				BigDecimal quantiteCharge=coutMP.getQuantCharge();
				BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
				BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
				BigDecimal quantiteDechet=coutMP.getQuantDechet();
				BigDecimal quantiteDechetFournisseur=coutMP.getQuantDechetFournisseur();
				BigDecimal quantiteReste=coutMP.getQuantReste();
				BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
				
				BigDecimal quantiteManquanteFrPlus=coutMP.getQuantiteManquanteFrPlus();
				if((quantiteConsomme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsomme.setScale(0,RoundingMode.FLOOR ))).compareTo(new BigDecimal(0.5))>=0)
				{
					quantiteConsomme=quantiteConsomme.add(BigDecimal.ONE).setScale(0,RoundingMode.FLOOR);
				}else
				{
					quantiteConsomme=quantiteConsomme.setScale(0,RoundingMode.FLOOR );	
				}
				
				BigDecimal ecart=(quantiteCharge.add(quantitechargeSupp).add(quantiteExistante) ).subtract(quantiteConsomme.setScale(6, RoundingMode.HALF_UP).add(quantiteDechet).add(quantiteDechetFournisseur).add(quantiteReste).add(quantiteManquante)).add(quantiteManquanteFrPlus);
				String codeFournisseur="";
				if(coutMP.getCodeFournisseur()!=null)
				{
					codeFournisseur=coutMP.getCodeFournisseur();	
				}
				//ecart=NumberUtils.roundHalfDown(ecart,2 );
				//  String strEcart = nf.format(ecart);
				
				Object []ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteExistante.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteCharge.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantitechargeSupp.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteConsomme.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteDechet.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteDechetFournisseur.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteReste.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteManquante.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteManquanteFrPlus.setScale(6)),codeFournisseur, NumberFormat.getNumberInstance(Locale.FRANCE).format(ecart)};

				modeleMP.addRow(ligne);
				i++;
			}
			  table.setModel(modeleMP); 
	}
	

void afficher_tableEmploye(List<DetailProductionMP> listDetailProductionMP)
	{
	initialiserTableauEmploye();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	
		  int i=0;
			while(i<listDetailProductionMP.size())
			{	
				DetailProductionMP detailProductionMP=listDetailProductionMP.get(i);
				delai=detailProductionMP.getDelaiEmploye();
				heureSupp25=detailProductionMP.getHeureSupp25();
				heureSupp50=detailProductionMP.getHeureSupp50();
				absent=detailProductionMP.isAbsent();
				sortie=detailProductionMP.isSortie();
				retard=detailProductionMP.isRetard();
				Object []ligne={detailProductionMP.getEmploye().getId(),detailProductionMP.getEmploye().getMatricule(),detailProductionMP.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard};

				modeleEmploye.addRow(ligne);
				i++;
			}
			tableEmploye.setModel(modeleEmploye);
	}





	
List<DetailProductionMP> calculeCoutEmploye(List<DetailProductionMP> listDetailProductionMP,Map< Integer, String> mapDelaiEmploye){
		
	Parametre heure=parametreDAO.findByDateByLibelle(productionMP.getDateProduction(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	BigDecimal delai=BigDecimal.ZERO;
		
		BigDecimal remise=BigDecimal.ZERO;
		BigDecimal coutHoraire=BigDecimal.ZERO;
		BigDecimal heureSupp25; 
		BigDecimal heureSupp50; 
		
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
		
		List<DetailProductionMP> listDetailProductionMPTmp= new ArrayList<DetailProductionMP>();
		for(int i=0;i<listDetailProductionMP.size();i++){
			
			DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			
			if(detailProductionMP.isAbsent()==true){
	    		
		   		 String code=Utils.genereCodeDateMoisAnnee(productionMP.getDateProduction());
					 
		   		 Utils.compterAbsenceEmploye(code, detailProductionMP.getEmploye(), productionMP.getDateProduction());
		   		}
			
			delai=new BigDecimal(mapDelaiEmploye.get(detailProductionMP.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25EmployeProd.get(detailProductionMP.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50EmployeProd.get(detailProductionMP.getEmploye().getId()));
			
				
			coutHoraire=heure.getValeur().multiply(delai);
			coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25) ;
			coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50) ;
			
			coutTotalEmploye=coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50) ;
			detailProductionMP.setCoutTotal(coutHoraire);
			detailProductionMP.setDelaiEmploye(delai);
			detailProductionMP.setHeureSupp25(heureSupp25);
			detailProductionMP.setHeureSupp50(heureSupp50);
			detailProductionMP.setCoutSupp25(coutSupp25);
			detailProductionMP.setCoutSupp50(coutSupp50);
			detailProductionMP.setRemise(remise);
			
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(productionMP.getDateProduction(), detailProductionMP.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal()) ;
				delai=delai.add(ficheEmploye.getDelaiEmploye());
				String numOF=ficheEmploye.getNumOF()+"-"+productionMP.getNumOFMP();
				BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(productionMP.getNbreHeure()) ;
		/*	ficheEmploye.setDateSituation(production.getDate());
			
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
			ficheEmploye.setCoutSupp25(coutSupp25);
			ficheEmploye.setCoutSupp50(coutSupp50);*/
			
			ficheEmploye.setNumOF(numOF);
			//ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setDelaiProd(delaiProd);
			
			ficheEmploye.setDelaiEmploye(delai);
			
			 if(detailProductionMP.isAbsent()==false && ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd())  >=0){
		   			
		   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
					
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=parametre_remise_ouvrier.getValeur();
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=parametre_remise_ouvrier_vrac.getValeur();
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 ficheEmploye.setRemise(remise);
			ficheEmployeDAO.edit(ficheEmploye);
			} else{
				ficheEmploye =new FicheEmploye();
				//ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setNumOF(productionMP.getNumOFMP());
				ficheEmploye.setDateSituation(productionMP.getDateProduction());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				//ficheEmploye.setCoutSupp25(coutSupp25);
				//ficheEmploye.setCoutSupp50(coutSupp50);
				
				
				 if(detailProductionMP.isAbsent()==false && delai.compareTo(productionMP.getNbreHeure()) >=0){
			   			
			   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
						
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=parametre_remise_ouvrier.getValeur();
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=parametre_remise_ouvrier_vrac.getValeur();
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(productionMP.getNbreHeure());
				ficheEmployeDAO.add(ficheEmploye);
				
			}
			}
			
			listDetailProductionMPTmp.add(detailProductionMP);
		}
		}	
		return listDetailProductionMPTmp;
		
	}




boolean remplirQuantite(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 7).toString().equals("")){
			mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 7).toString()); 
			trouve=true;
		}else {
			mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), String.valueOf(0));
		}
		
		if(!table.getValueAt(j, 8).toString().equals("")){
			mapQuantiteDechetFournisseur.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 8).toString()); 
			trouve=true;
		}else {
			mapQuantiteDechetFournisseur.put(table.getValueAt(j, 0).toString(), String.valueOf(0));
		}
		
		if(!table.getValueAt(j, 9).toString().equals("")){
			mapQuantiteReste.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 9).toString());
			trouve=true;
		}else {
			mapQuantiteReste.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
		}
		
	if(!table.getValueAt(j, 10).toString().equals("")){
			
			mapQuantiteManquante.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 10).toString());
			trouve=true;
		}else {
			mapQuantiteManquante.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
		}
	
		
	
if(!table.getValueAt(j, 11).toString().equals("")){
		
		mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 11).toString());
		trouve=true;
	}else {
		mapQuantiteManquanteFrPlus.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
	}
	
if(table.getValueAt(j, 12)!=null)
{
if(!table.getValueAt(j, 12).toString().equals("")){
		
		mapCodeFournisseurMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 12).toString());
		trouve=true;
	}
}


   
	
		
		
		
		
		
	}
	return trouve;
}
List<CoutProdMP> validerSaisiQuantiteDechetReste(List<CoutProdMP> listCoutProdMP) {
	
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteDechetFournisseur=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteManquante=BigDecimal.ZERO;
	BigDecimal quantiteManquanteFrPlus=BigDecimal.ZERO;
	String codeFournisseur;
	
	
	
	List<CoutProdMP> listCoutProdMPTmp=new ArrayList<CoutProdMP>();
	
for(int i=0;i<listCoutProdMP.size();i++){ 
	codeFournisseur="";
		CoutProdMP coutMP=listCoutProdMP.get(i);
		
		quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));
		quantiteDechetFournisseur=new BigDecimal(mapQuantiteDechetFournisseur.get(coutMP.getMatierePremier().getCode()));
		quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
		
		quantiteManquante=new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));
		quantiteManquanteFrPlus=new BigDecimal(mapQuantiteManquanteFrPlus.get(coutMP.getMatierePremier().getCode()));
		
		
		if(mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode())!=null)
		{
			codeFournisseur=mapCodeFournisseurMP.get(coutMP.getMatierePremier().getCode());
		}
		if(!codeFournisseur.equals(""))
		{
			coutMP.setCodeFournisseur(codeFournisseur);
		}
		
		
		coutMP.setQuantDechet(quantiteDechet);
		coutMP.setQuantReste(quantiteReste);
		coutMP.setQuantiteManquante(quantiteManquante);
		coutMP.setQuantiteManquanteFrPlus(quantiteManquanteFrPlus);
		coutMP.setQuantDechetFournisseur(quantiteDechetFournisseur);
		
		//listCoutMP.set(i,coutMP);
		listCoutProdMPTmp.add(coutMP);
}
afficher_tableMP(listCoutProdMPTmp);

validerSaisie=true;
return listCoutProdMPTmp;
	
}

List<CoutProdMP>  calculeCoutMatierePremiere(List<CoutProdMP> listCoutProdMP){
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteDechetFournisseur=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteMP=BigDecimal.ZERO;
	
	BigDecimal prixMP=BigDecimal.ZERO;
	BigDecimal coutDechet=BigDecimal.ZERO;
	BigDecimal coutDechetFournisseur=BigDecimal.ZERO;
	List<DetailTransferStockMP> listdetailtransfertStockMP =new ArrayList<DetailTransferStockMP>();
	TransferStockMP transferStockMP=null;
	if(productionMP!=null)
	{
		transferStockMP=transferStockMPDAO.findTransferByCodeStatut (productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE) ;
	}
	
	if(transferStockMP!=null)
	{
		listdetailtransfertStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStockMP.getId());
	}
	
	 
	List<CoutProdMP> listCoutProdMPTmp=new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutProdMP.size();i++){ 
		
		CoutProdMP coutProdMP=listCoutProdMP.get(i);

		 
		
		//quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));
		quantiteConsomme=coutProdMP.getQuantConsomme();
		if((quantiteConsomme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsomme.setScale(0,RoundingMode.FLOOR ))).compareTo(new BigDecimal(0.5))>=0)
		{
			quantiteConsomme=quantiteConsomme.add(BigDecimal.ONE).setScale(0,RoundingMode.FLOOR);
		}else
		{
			quantiteConsomme=quantiteConsomme.setScale(0,RoundingMode.FLOOR );	
		}
		
		
		quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutProdMP.getMatierePremier().getCode()));
		quantiteReste=new BigDecimal(mapQuantiteReste.get(coutProdMP.getMatierePremier().getCode()));
		quantiteDechetFournisseur=new BigDecimal(mapQuantiteDechetFournisseur.get(coutProdMP.getMatierePremier().getCode()));
		//quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
		coutProdMP.setQuantConsomme(quantiteConsomme);
		coutProdMP.setQuantDechet(quantiteDechet);
		
		if(mapCodeFournisseurMP.get(coutProdMP.getMatierePremier().getCode())!=null)
		{
			coutProdMP.setCodeFournisseur(mapCodeFournisseurMP.get(coutProdMP.getMatierePremier().getCode()));
		}
		
		
		//quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
		prixMP=quantiteConsomme.multiply(coutProdMP.getPrixUnitaire()) ;
		coutDechet=quantiteDechet.multiply(coutProdMP.getPrixUnitaire()) ;
		coutDechetFournisseur=quantiteDechetFournisseur.multiply(coutProdMP.getPrixUnitaire()) ;
		coutProdMP.setPrixTotal(prixMP);
		coutProdMP.setCoutDechet(coutDechet);
		coutTotalMP=coutTotalMP.add(prixMP) ;
		coutTotalDechet=coutTotalDechet.add(coutDechet).add(coutDechetFournisseur)  ;
		//quantiteReste=stockmp.getStock()-quantiteConsomme;
		
	
		for(int j=0;j<listdetailtransfertStockMP.size();j++)
		{
			
			DetailTransferStockMP detailTransferStockMP=listdetailtransfertStockMP.get(j);
			if(detailTransferStockMP.getMatierePremier().getId()==coutProdMP.getMatierePremier().getId())
			{
				detailTransferStockMP.setQuantiteRetour(quantiteReste);
				detailTransferStockMP.setQuantiteDechet(quantiteDechet);
				
				detailTransfertMPDAO.edit(detailTransferStockMP);
				
			}
		}
		
		
	}
	return listCoutProdMPTmp;
  }
void afficherDetailPorduction(List<DetailEstimationMP> lisDetailEstimationMP,List<CoutProdMP> listCoutProdMP){
	DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
	CoutProdMP coutProdMP=new CoutProdMP();
	CoutProdMP coutProdMPTmp=new CoutProdMP();
	int position=-1;
	BigDecimal quantiteConsommme=BigDecimal.ZERO;
	BigDecimal quantiteRealise=new BigDecimal(txtQuantiteRealise.getText());
	boolean trouve =false;
	int priorite=0;
	BigDecimal quantiteCarton=BigDecimal.ZERO;
	
	for(int i=0;i<lisDetailEstimationMP.size();i++){
		trouve =false;
		detailEstimationMP=lisDetailEstimationMP.get(i);
		for(int j=0;j<listCoutProdMP.size();j++){
			coutProdMP=listCoutProdMP.get(j);
			
			if(detailEstimationMP.getMatierePremier().getId()==coutProdMP.getMatierePremier().getId()){
				
					if(lisDetailEstimationMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
					{
						
						quantiteConsommme=detailEstimationMP.getQuantite().multiply(quantiteRealise) ;
						quantiteCarton=quantiteCarton.add(quantiteConsommme);
						coutProdMP.setQuantConsomme(quantiteConsommme);
						listCoutProdMP.set(j,coutProdMP);
						
					}else
					{
						quantiteConsommme=detailEstimationMP.getQuantite().multiply(quantiteRealise) ;
						
						coutProdMP.setQuantConsomme(quantiteConsommme);
						listCoutProdMP.set(j,coutProdMP);
						
					}
				
				
				
				
				
				}
				
			
			}
			
		}
	
	
	for(int k=0;k<listCoutProdMP.size();k++)
	{
		CoutProdMP coutProdMPTMP=listCoutProdMP.get(k);
		
		if(lisDetailEstimationMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
		{
			
			if(quantiteCarton.compareTo(quantiteRealise)!=0)
			{
				
				quantiteConsommme=coutProdMPTMP.getQuantEstime().divide(coutProdMPTMP.getProdcutionCM().getQuantiteReel(), 6, RoundingMode.HALF_UP).multiply(quantiteRealise);
				
				if(quantiteConsommme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsommme.setScale(0,RoundingMode.FLOOR )).compareTo((new BigDecimal(0.2)))>=0)
				{
					coutProdMPTMP.setQuantConsomme(coutProdMPTMP.getQuantConsomme().add(BigDecimal.ONE));
					listCoutProdMP.set(k, coutProdMPTMP);
					
					quantiteCarton=quantiteCarton.add(BigDecimal.ONE);
				}
				
			}
			
			
			
		}
	
		
		
	}
	
	
	
	
	
	

		
	}













void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent", "Sortie", "Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class,Boolean.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   tableEmploye.setModel(modeleEmploye); 
		   tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(6).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(7).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(8).setPreferredWidth(60);
}

void initialiserTableauEmployeGen(){
	modeleEquipeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent", "Sortie", "Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class,BigDecimal.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
}





List<DetailProductionMP>  remplieDetailProdcution(List<Employe> listEmploye){
	List<DetailProductionMP> listDetailProdcutionMP=new ArrayList<DetailProductionMP>();

	
	for(int i=0;i<listEmploye.size();i++){
		DetailProductionMP detailProdMP= new DetailProductionMP();
		Employe employe =listEmploye.get(i);
		detailProdMP.setCoutTotal(BigDecimal.ZERO);
		detailProdMP.setRemise(employe.getRemise());
		detailProdMP.setEmploye(employe);
		detailProdMP.setProductionMP(productionMP);
		
		//listDetailProdcution.add(detailProd);
		productionMP.getDetailProductionsMP().add(detailProdMP);
	}
//	production.setDetailProductions(listDetailProdcution);
	
	
/*	List<DetailProduction> listDetailProdcutionTmp=production.getDetailProductions();
	DetailProduction detailProdDeleted=new DetailProduction();
	if(listDetailProdcutionTmp!=null && listDetailProdcutionTmp.size()>0){
	for(int j=0;j<listDetailProdcutionTmp.size();j++){
		DetailProduction detailProd= listDetailProdcutionTmp.get(j);
		if(!listEmploye.contains(detailProd.getEmploye())){
			detailProdDeleted=production.removeDetailProduction(detailProd);
			System.out.println("######"+detailProdDeleted.getId());
		}
	}
	
	}*/
	productionMPDAO.edit(productionMP);
	
	return productionMP.getDetailProductionsMP();
  }


void  annulerStockMatierePremiere(List<CoutProdMP> listCoutProdMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteCharge=BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	for(int i=0;i<listCoutProdMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutProdMP coutProdMP=listCoutProdMP.get(i);
	
		
		 quantiteCharge=coutProdMP.getQuantCharge();
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinProd );
		StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinStockage );
		quantiteExistante=coutProdMP.getQuantExistante().add(stockMPProd.getStock()) ;
		
		quantiteStockage=stockMPStockage.getStock().add(quantiteCharge) ;
		
		
		stockMPProd.setStock(quantiteExistante);
		stockMPStockage.setStock(quantiteStockage);
		
		coutProdMP.setCoutDechet(BigDecimal.ZERO);
		coutProdMP.setQuantCharge(BigDecimal.ZERO);
		coutProdMP.setQuantChargeSupp(BigDecimal.ZERO);
		coutProdMP.setQuantConsomme(BigDecimal.ZERO);
		coutProdMP.setQuantDechet(BigDecimal.ZERO);
		coutProdMP.setQuantExistante(BigDecimal.ZERO);
		coutProdMP.setQuantite(BigDecimal.ZERO);
		coutProdMP.setQuantReste(BigDecimal.ZERO);
		listCoutProdMP.set(i, coutProdMP);
	//	listCoutMP.remove(i);

		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		
	}
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	
	TransferStockMP transferStockMPCharge=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
	
	if(transferStockMPCharge!=null)
	{

		
		transferStockMPDAO.deleteObject(transferStockMPCharge);
		
		
	}
	
	listDetailTransferStockMP.clear();
	
TransferStockMP transferStockMPChargeSupp=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
	
	if(transferStockMPChargeSupp!=null)
	{
		

		
		transferStockMPDAO.deleteObject(transferStockMPChargeSupp);
		
		
		
	}
	
	
	TransferStockMP transferStockMPFabriquer=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
	
	if(transferStockMPFabriquer!=null)
	{
		
		
		
		transferStockMPDAO.deleteObject(transferStockMPFabriquer);
		
		
	}

	
	
	
	productionMP.setListCoutProdMP(listCoutProdMP);
  }
void  annulerStockProduitFini(){

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
	
	 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
	 
	 BigDecimal p1=productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
	 BigDecimal q1=productionMP.getQuantiteReel();
	 
	 BigDecimal montantQ1P1=q1.multiply(p1) ;
	 
	 BigDecimal q2=stockMP.getStock();
	 BigDecimal p2=stockMP.getPrixUnitaire();
	 
	 BigDecimal montantQ2P2=q2.multiply(p2) ;
	 
	 BigDecimal q=q2.subtract(q1) ;
	
	 BigDecimal p = (montantQ2P2 .subtract(montantQ1P1) ).divide(q, 6, BigDecimal.ROUND_HALF_UP)  ;
	 
	 stockMP.setStock(q);
	 stockMP.setPrixUnitaire(p);
	 stockMPDAO.edit(stockMP);
}

void deleteListeObject(List<FicheEmploye> listFicheEmploye){
	
	for(int i=0;i<listFicheEmploye.size();i++){
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}


void calculerStockCoutProduitFini(BigDecimal prixTotal){
	
	BigDecimal prixPF=BigDecimal.ZERO;
	BigDecimal nouveauprix=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO ;
	BigDecimal prixStock=BigDecimal.ZERO;
	BigDecimal prixOld=BigDecimal.ZERO;
	BigDecimal QuantiteOld=BigDecimal.ZERO;
	BigDecimal prixNew=BigDecimal.ZERO;
	
	
	
	
	CalculerStockFinaleMP();
	
	
	
	//prixTotal=productionMP.getCoutTotalEmploye()+productionMP.getCoutTotalMP()+productionMP.getCoutDechet();
	
	prixPF=prixTotal.divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
	
	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
	
	for(int j=0;j<listEtatStockMPAfficher.size();j++)
	{
		
		EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
		if(etatStockMP.getMp().getId()==matierePremier.getId())
		{
			if(etatStockMP.getMp().getPrixByYear(  DateUtils.getAnnee(productionMP.getDateProduction()) )!=null)
			{
				prixOld=etatStockMP.getMp().getPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()));
			}else
			{
				prixOld=BigDecimal.ZERO;
			}
			
			
			QuantiteOld=etatStockMP.getQuantiteFinale();
			
		}
		
		
		
	}
	
	
	
	/*
	 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
	 
	 quantiteTotal=stockMP.getStock().add(productionMP.getQuantiteReel());
	 prixStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
	 
	 	if(prixStock.compareTo(BigDecimal.ZERO)>0)
	 		nouveauprix=(prixTotal.add(prixStock) ).divide(quantiteTotal, 6, BigDecimal.ROUND_HALF_UP) ;
	 	else {
	 		nouveauprix= prixPF;
	 	}
	 	
	 	stockMP.setStock(quantiteTotal);
	 	stockMP.setPrixUnitaire(nouveauprix);
	 	stockMPDAO.edit(stockMP);
	 	*/
	 	TransferStockMP transferStockMP=new TransferStockMP();
	 	
	 	transferStockMP.setCodeTransfer(productionMP.getNumOFMP());
	 	transferStockMP.setCreerPar(productionMP.getUtilisateurCreation());
	 	transferStockMP.setDate(new Date());
	 	transferStockMP.setDateTransfer(productionMP.getDateProduction());
	 	transferStockMP.setDepot(productionMP.getMagasinStockage().getDepot());
	 	transferStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
	 	transferStockMPDAO.add(transferStockMP);
	 	
	 	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
	 	detailTransferStockMP.setMagasinDestination(productionMP.getMagasinStockage());
	 	detailTransferStockMP.setMatierePremier(matierePremier);
	 	detailTransferStockMP.setQuantite(productionMP.getQuantiteReel());
	 	detailTransferStockMP.setTransferStockMP(transferStockMP);	
	 	detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP));
       detailTransfertMPDAO.add(detailTransferStockMP);
       
       if(productionMP.getQuantiteReel().add(QuantiteOld).compareTo(BigDecimal.ZERO)!=0)
       {
           prixNew=  (((productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP)).multiply(productionMP.getQuantiteReel()) ).add(prixOld.multiply(QuantiteOld))).divide(productionMP.getQuantiteReel().add(QuantiteOld), 6, RoundingMode.HALF_UP);

       }
       
       matierePremier.setPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()) , prixNew); 
       matierePremiereDAO.edit(matierePremier);
       
       
       listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	    StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
				  		      
				  		     		
				  		     		
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
			  		     			
			  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
			  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().add(prixTotal));
			  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac());
			  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
			  		     			statistiqueFinanciaireMP.setCodeTransfer(productionMP.getNumOFMP());
			  		     			statistiqueFinanciaireMP.setDate(new Date());
			  		     			statistiqueFinanciaireMP.setDateOperation(productionMP.getDateProduction());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton().add(coutTotalEmploye));
			  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
			  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
			  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton().add(prixTotal));
			  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF() );
			  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton().subtract(productionMP.getCoutTotalMP().add(productionMP.getCoutDechet())));
			  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
			  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
			  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
			  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
			  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
			  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_TERMINER+" "+Constantes.PROD_CARTON);
			  		     		 
			  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
					  					
      
	 	
}



public void CalculerStockFinaleMP()
{
	
	

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
		
	
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=null;
				Magasin magasin=productionMP.getMagasinStockage();
				
			  	FournisseurMP fournisseurMP=null;
			
			 
			  	
			  	
			  	
			
				
				
				
				Date mindate=null;
				
				Mindate=detailTransfertMPDAO.MinDate(magasin);
				
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
					String year = simpleDateFormatyear.format(productionMP.getDateProduction());
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
			
				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

				//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				
				listeObjectEtatStockGroupByMPByFournisseurReception=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);

				
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
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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

//////////////////////////////////////////////////////////////////////////////////////////////////////// Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		   
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


//////////////////////////////////////////////////////////////////////////////////////////////////////// Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
	retour=BigDecimal.ZERO;
	retourFournisseurAnnule=BigDecimal.ZERO;
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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





//////////////////////////////////////////////////////////////////////////////////////////////////////// Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





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

////////////////////////////////////////////////////////////////////////////////////////////////////////   Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

//////////////////////////////////////////////////////////////////////////////////////////////////////// Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
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






public void ViderEmployeProductionCarton()
{
	txtcodeemployeproductioncarton.setText("");
	comboemployeproductioncarton.setSelectedItem("");
	txtdelaiproductioncarton.setText("");
	txthsupp25productioncarton.setText("");
	txthsupp50productioncarton.setText("");
	checkabsentproductioncarton.setSelected(false);
	checksortieproductioncarton.setSelected(false);
	checkretardproductioncarton.setSelected(false);
	txtcodeemployeproductioncarton.requestFocus();
}
}

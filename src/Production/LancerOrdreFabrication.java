package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.hibernate.type.YesNoType;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdRes;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LancerOrdreFabrication extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleOF;

	private JXTable  table = new JXTable();
	private JXTable TableOF = new JXTable();
	private ImageIcon imgImprimer;
	private ImageIcon imgValider;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnLancerOF;
	private JButton btnRechercher;
	
	private JTextField quantite;
	private JTextField codeArticle;
	private DepotDAO depotDAO;
	
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO ;
	private	List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferStockMP> listDetailTransferStockMPCharge= new ArrayList<DetailTransferStockMP>();
	private List<Production> listProductions=new ArrayList<Production>();
	private List<Production> listProductionsLance=new ArrayList<Production>();
	
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	
	private JComboBox<String> categorie;
	private JComboBox<String> comboMachine;
	private  JComboBox<String> comboLigneMachine;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private static Production production = new Production();
	
	private JComboBox<String> comboEquipe;
	private JTextField txtDateDebutPrev;
	private JTextField txtDateFinPrev;
	private Utilisateur utilisateur;
	SubCategorieMPDAO SubCategorieMPDAO;
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	List<DetailProdRes> listDetailResponsableProdTmp=new ArrayList<DetailProdRes>();
	List<Production> listProductionsterminerGroupByDate=new ArrayList<Production>();
	private DetailProdResDAO detailProdResDAO;
	String datesProductionErreur="";
	private CoutMPDAO coutMPDAO;
	private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
	
/////////////////////////////////////////////////////////////////////////// POUR CALCULER STOCK FINALE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
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
		private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		 
		private DetailTransferMPDAO detailTransferStockMPDAO;

	
	
	
	
	
	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public LancerOrdreFabrication() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 756);
        try{
        	
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	SubCategorieMPDAO=new SubCategorieMPAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	 detailProdResDAO=new DetailProdResDAOImpl();
        	 detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	 coutMPDAO=new CoutMPDAOImpl();
        	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	final String codeDepot=AuthentificationView.utilisateur.getCodeDepot();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
    
		
		
				  		  btnImprimer = new JButton("Fiche Calcule MP");
				  		 
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

					  		  	if(production.getId()>0 && production.getStatut().equals(Constantes.ETAT_OF_LANCER)){
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(production.getDate());
								 List<CoutMP> listCoutMP=production.getListCoutMP();
								 
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine", production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", "");
								parameters.put("magasin", production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheCalculeMatierePremiere(listCoutMP,parameters,production.getNumOF());
								
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					  		  	}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
					  		  	
				  		  	}
				  		  });
				  		  
				  		modeleOF =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Date OF","Num OF", "Etat"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		   TableOF.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		   		
				  		   		
				  		   		

				  		  		
if(TableOF.getSelectedRow()!=-1)
{
	production=listProductions.get(TableOF.getSelectedRow());
	
		if(production!=null){
			
			if(production.getStatut().equals(Constantes.ETAT_OF_CREER))
			{
				detailTransferStockMPDAO.ViderSession();
				
				 
				CalculerStockFinale(production.getMagasinProd(), production.getDate());
				listEtatStockMPAfficherMagasinProduction.addAll(listEtatStockMPAfficher);
				
				
				
				
	  			listCoutMP=productionDAO.listeCoutMP(production.getId());
				
	  			for(int i=0;i<listCoutMP.size();i++)
	  			{
	  				
	  				CoutMP coutMP=listCoutMP.get(i);
	  				
	  				for(int j=0;j<listEtatStockMPAfficherMagasinProduction.size();j++)
	  	  			{
	  					
	  					EtatStockMP etatStockMP=listEtatStockMPAfficherMagasinProduction.get(j);
	  					
	  					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  					{
	  						
	  						if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  	  					{
	  							
	  							if(etatStockMP.getMp().getId()==coutMP.getMatierePremier().getId())
	  							{
	  								if(etatStockMP.getFournisseurMP()!=null)
	  								{
	  									if(coutMP.getFournisseurMP()!=null)
	  									{
	  										
	  										if(etatStockMP.getFournisseurMP().getId()==coutMP.getFournisseurMP().getId())
	  										{
	  											
	  											coutMP.setQuantExistante(etatStockMP.getQuantiteFinale());
	  											
	  											
	  										}
	  										
	  										
	  										
	  										
	  										
	  									}
	  									
	  									
	  									
	  									
	  									
	  								}
	  								
	  								
	  								
	  							}
	  							
	  							
	  							
	  							
	  							
	  							
	  	  					}
	  						
	  						
	  						
	  					}else
	  					{
	  						
	  						if(!etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  	  					{
	  							
	  							
	  							if(etatStockMP.getMp().getId()==coutMP.getMatierePremier().getId())
	  							{
	  								coutMP.setQuantExistante(etatStockMP.getQuantiteFinale());
	  								
	  							}
	  							
	  							
	  							
	  	  					}
	  						
	  						
	  						
	  						
	  					}
	  					
	  					
	  					
	  					
	  	  			}
	  				
	  				 
	  				listCoutMP.set(i, coutMP);
	  				
	  				
	  				
	  			}	
				
				
			}else
			{
				listCoutMP=productionDAO.listeCoutMP(production.getId());
			}
			
			
  			
  	
  			
  			
  			
  			
  			
  			
  			
  			//txtDescription.setText(production.getDescription());
  			quantite.setText(""+NumberFormat.getNumberInstance(Locale.FRANCE).format(production.getQuantiteEstime()));
  			codeArticle.setText(production.getArticles().getCodeArticle());
  			categorie.addItem(production.getArticles().getLiblle());
  			categorie.setSelectedItem(production.getArticles().getLiblle());
  			
  		//	comboEquipe.addItem(production.getEquipe().getNomEquipe());
  		//	comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
  			
  			comboLigneMachine.addItem(production.getLigneMachine().getNom());
  			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
  			
  			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
  			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
  			
  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
				String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
  			txtDateDebutPrev.setText(dateDebutPrev);
  			txtDateFinPrev.setText(dateFinPrev);
  			afficher_tableMP(listCoutMP);
  			listDetailTransferStockMPCharge.clear();
		}else{
  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
  				
  			}
	
	
}
					  			
					  		
					 

					  		
				  		  		
				  		  	
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   TableOF.setModel(modeleOF); 
				  		 TableOF.getColumnModel().getColumn(0).setPreferredWidth(10);
				  		TableOF.getColumnModel().getColumn(1).setPreferredWidth(300);
				  		 
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		modeleMP =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Code","Nom Matière Première   ","Fournisseur", "Quantité","Quantite Existante","Quantite Charge" , "Charge Supp", "Ajouter Charge Supp"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false,false,false,false,false, true
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		   table.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   table.setModel(modeleMP); 
				  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
				  		   table.getColumnModel().getColumn(1).setPreferredWidth(300);
				  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
				  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
				  		 table.getColumnModel().getColumn(4).setPreferredWidth(60);
				  		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
				  		 table.getColumnModel().getColumn(6).setPreferredWidth(60);
				  		 table.getColumnModel().getColumn(7).setPreferredWidth(60);
				  		   btnImprimer.setIcon(imgImprimer);
				  		   btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnImprimer.setBounds(388, 721, 131, 24);
				  		   add(btnImprimer);
				  		 
				  		   // Charger La Liste Des OF en Etat Creation 
				  		afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot));
				  		    
				  		    btnLancerOF = new JButton("Lancer OF");
				  		    btnLancerOF.setBounds(10, 721, 107, 24);
				  		    add(btnLancerOF);
				  		    btnLancerOF.setIcon(imgAjouter);
				  		    btnLancerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		if(production.getStatut().equals(Constantes.ETAT_OF_CREER)){
				  		     			
				  		     			
				  		     			listProductionsLance=productionDAO.LesProductionLancerSansTerminerParDateParDepot(production.getDate(), Constantes.ETAT_OF_LANCER, production.getCodeDepot());

				  		     			if(listProductionsLance.size()!=0)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez Terminer les Productions lancer Avant de Lancer Une Autre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			
				  		     			
				  		     			
				  		     			VeriffierLesNombresproductionsterminer();
				  		     			if(!datesProductionErreur.equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Le Nombre des production Dans les date suivant est Différent aux Nombre Productions Déclarer dans l'équipe Générique"+"  "+datesProductionErreur,"Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		production.setDateDebFabRee(new Date());
				  		     		
				  		     		
				  		     		production.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		
				  		     		for(int i=0;i<listCoutMP.size();i++)
				  		     		{
				  		     			
				  		     			CoutMP coutMP=listCoutMP.get(i);
				  		     			coutMPDAO.edit(coutMP);
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		//	List<CoutMP> listCoutMP=reglerStockMatierePremiere(production.getListCoutMP(),production.getMagasinProd().getId(),production.getMagasinStockage().getId());
				  		     		
				  		     	
				  		     		
				  		     		List<DetailResponsableProd> listDetailResponsableProd=production.getListDetailResponsableProd();
				  		     		production.setStatut(Constantes.ETAT_OF_LANCER);
				  		     		
				  		     		
		////////////////////////////////////////////////////////////////////////////////////////////////  Creer Transfert Stock MP    ////////////////////////////////////////////////////////////////////////////////////////////
				  		     		transferStockMPDAO.ViderSession();
				  		     		detailTransfertMPDAO.ViderSession();
				  		     		/*
				  		     		TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCodeStatut(production.getNumOF(), ETAT_TRANSFER_STOCK_CHARGE);
				  		     		
				  		     		if(transferStockMPTmp!=null)
				  		     		{
				  		     			transferStockMPDAO.delete(transferStockMPTmp.getId());
				  		     		}*/
				  		     		
				  		     		TransferStockMP transferStock = new TransferStockMP();
                                 BigDecimal montantTotal=BigDecimal.ZERO;
                                 BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                                 BigDecimal montantTotalEmballage=BigDecimal.ZERO;
				  		     		for(int i=0;i<listCoutMP.size();i++){ 
				  		     			CoutMP coutMP=listCoutMP.get(i);
				  		     		
				  		     		
				  		     			
				  		     			
				  		     			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
				  		     			detailTransferStockMP.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
				  		     			detailTransferStockMP.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
				  		     			detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
				  		     			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		     			{
				  		     				if(coutMP.getFournisseurMP()!=null)
				  		     				{
				  		     					detailTransferStockMP.setFournisseur(coutMP.getFournisseurMP());
				  		     				}
				  		     				
				  		     			}
				  		     			detailTransferStockMP.setPrixUnitaire(coutMP.getMatierePremier().getPrix());
				  		     			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) ||  coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
				  		     			{
				  		     				if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
				  		     				{
				  		     					detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, RoundingMode.CEILING));
				  		     					detailTransferStockMP.setQuantiteExistante(coutMP.getQuantExistante().setScale(0, RoundingMode.CEILING));
				  		     				}else
				  		     				{
				  		     					detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
				  		     					detailTransferStockMP.setQuantiteExistante(coutMP.getQuantExistante());
				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}else
				  		     			{
				  		     				detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
				  		     				detailTransferStockMP.setQuantiteExistante(coutMP.getQuantExistante());
				  		     				
				  		     			}
				  		     			
				  		     			detailTransferStockMP.setTransferStockMP(transferStock);
				  		     			listDetailTransferStockMPCharge.add(detailTransferStockMP);
				  		     			
				  		     			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		     			{
				  		     				montantTotalEnvrac=montantTotalEnvrac.add(coutMP.getQuantCharge().multiply(coutMP.getPrixUnitaire()));
				  		     				montantTotalEnvrac=montantTotalEnvrac.add(coutMP.getQuantExistante().multiply(coutMP.getPrixUnitaire()));
				  		     				
				  		     			}else
				  		     			{
				  		     				montantTotalEmballage=montantTotalEmballage.add(coutMP.getQuantCharge().multiply(coutMP.getPrixUnitaire()));
				  		     				montantTotalEmballage=montantTotalEmballage.add(coutMP.getQuantExistante().multiply(coutMP.getPrixUnitaire()));
				  		     			}
				  		     			montantTotal=montantTotal.add(coutMP.getQuantCharge().multiply(coutMP.getPrixUnitaire()));
				  		     			montantTotal=montantTotal.add(coutMP.getQuantExistante().multiply(coutMP.getPrixUnitaire()));
				  		     			
				  		     		}	  		     		
				  		     		
				  		     		
				  		     		 
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			  		     		
				  		     		
				  		     		// ajouter transfer stock MP type charge 
			  		     			String codeTransfert=Utils.genererCodeTransfer(production.getCodeDepot(),ETAT_TRANSFER_STOCK_CHARGE);
									if(listDetailTransferStockMPCharge.size()!=0)
									{
										transferStock.setCodeTransfer(production.getNumOF());
										transferStock.setCreerPar(utilisateur);
										transferStock.setDate(new Date());
										transferStock.setDateTransfer(production.getDate_debFabPre());
										transferStock.setDepot(production.getMagasinProd().getDepot());
										//transferStock.setListDetailTransferMP(listDetailTransferStockMPCharge);
										transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE);
										
										transferStockMPDAO.add(transferStock);
									}
								
									for(int i=0;i<listDetailTransferStockMPCharge.size();i++)
				  		     		{
				  		     			detailTransfertMPDAO.add(listDetailTransferStockMPCharge.get(i));
				  		     		}
				  		     		productionDAO.edit(production);
				  		     		
				  		     		listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
				  		     		
				  		     		if(statistiqueFinanciaireMPTmp!=null)
				  		     		{
				  		     			
				  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
				  		     			
				  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
				  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
				  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
				  		     			statistiqueFinanciaireMP.setCoutProduction(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutProduction()));
				  		     			statistiqueFinanciaireMP.setCodeTransfer(production.getNumOF());
				  		     			statistiqueFinanciaireMP.setDate(new Date());
				  		     			statistiqueFinanciaireMP.setDateOperation(production.getDate());
				  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
				  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
				  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
				  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
				  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
				  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
				  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
				  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
				  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
				  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
				  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
				  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_LANCER+" "+Constantes.PROD_PF);
				  		     		 
				  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
				  		     			 
				  		     			
				  		     		} 
				  		     		
				  		     		
				  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication lancé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot));   
				  		     		InitialzeTous();
				  		     		
				  		     		/*try {
										EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
											"OF Lancé avec succès",
											registerMailBody());
									} catch (AddressException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (MessagingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}*/
				  		     		}else{
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication est déjà lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     	}
				  		     });
				  		    btnLancerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    comboEquipe = new JComboBox<String>();
				  		     btnAnnulerOF = new JButton("Initialiser");
				  		     btnAnnulerOF.setBounds(120, 721, 106, 23);
				  		     add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		    // table.setRowHeightEnabled(true);
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
				  		     	scrollPane.setBounds(10, 321, 971, 229);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		     	comboMachine = new JComboBox();		
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 296, 971, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 561, 971, 149);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Machine");
				  		     	lblMachine.setBounds(10, 86, 58, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 
				  		     	 comboMachine.setBounds(119, 87, 136, 24);
				  		     	 layeredPane.add(comboMachine);
				  		     	 comboMachine.addItem("");
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Ligne Machine");
				  		     	 lblGroupe.setBounds(283, 86, 77, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 comboLigneMachine = new JComboBox();
				  		     	 comboLigneMachine.setBounds(367, 87, 144, 24);
				  		     	 layeredPane.add(comboLigneMachine);
				  		     	 
				  		 
				  		  comboEquipe.setBounds(619, 87, 152, 24);
				  		  layeredPane.add(comboEquipe);
				  		  comboEquipe.addItem("");
				  		  
				  		  JLabel lblEquipe = new JLabel("Equipe");
				  		  lblEquipe.setBounds(550, 86, 51, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date D\u00E9but Pr\u00E9vue");
				  		  lblDatePrevue.setBounds(10, 48, 102, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  
				  		  JLabel lblDateFin = new JLabel("Date Fin pr\u00E9vue");
				  		  lblDateFin.setBounds(283, 48, 77, 26);
				  		  layeredPane.add(lblDateFin);
				  		  codeArticle = new JTextField();
				  		util.Utils.copycoller(codeArticle);
				  		  codeArticle.setBounds(119, 10, 136, 26);
				  		  layeredPane.add(codeArticle);
				  		  categorie = new JComboBox();
				  		  categorie.setEnabled(false);
				  		  categorie.setEditable(true);
				  		  categorie.setBackground(Color.WHITE);
				  		  categorie.addItem("");
		codeArticle.setColumns(10);
		
		  JLabel lblCodeArticle = new JLabel("Code Article");
		  lblCodeArticle.setBounds(8, 10, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  categorie.setBounds(367, 13, 144, 26);
		  layeredPane.add(categorie);
		  categorie.addItem(""); 
		  
		  quantite = new JTextField();
		  util.Utils.copycoller(quantite);
		  quantite.setBounds(614, 11, 157, 26);
		  layeredPane.add(quantite);
		  quantite.setColumns(10);
		  
		  JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
		  lblQuantite.setBounds(533, 12, 68, 26);
		  layeredPane.add(lblQuantite);
		  lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		    
		    JLabel lblArticle = new JLabel("Article:");
		    lblArticle.setBounds(283, 11, 102, 26);
		    layeredPane.add(lblArticle);
		    lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    
		    txtDateDebutPrev = new JTextField();
		    util.Utils.copycoller(txtDateDebutPrev);
		    txtDateDebutPrev.setColumns(10);
		    txtDateDebutPrev.setBounds(119, 51, 136, 26);
		    layeredPane.add(txtDateDebutPrev);
		    
		    txtDateFinPrev = new JTextField();
		    util.Utils.copycoller(txtDateFinPrev);
		    txtDateFinPrev.setColumns(10);
		    txtDateFinPrev.setBounds(367, 51, 144, 26);
		    layeredPane.add(txtDateFinPrev);
		
		JButton btnValiderChargeSupp = new JButton("Valider Charge Supp");
		btnValiderChargeSupp.setIcon(imgValider);
		
		btnValiderChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listDetailTransferStockMPChargeSupp.clear();
				if(production.getStatut().equals(Constantes.ETAT_OF_LANCER)){	
					
					
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité à charger ", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Valider cette charge ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						{
						
						BigDecimal QuantiteTotal=BigDecimal.ZERO;

						
					TransferStockMP	transferStock=new TransferStockMP();
						List<CoutMP> listCoutMPTmp= new ArrayList<CoutMP>();
					BigDecimal	QuantiteCharge=BigDecimal.ZERO;
					 BigDecimal montantTotal=BigDecimal.ZERO;
                     BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                     BigDecimal montantTotalEmballage=BigDecimal.ZERO;
						for(int i=0;i<listCoutMP.size();i++){
							CoutMP coutMP =listCoutMP.get(i);
							
							QuantiteCharge=BigDecimal.ZERO;
							if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
									if(coutMP.getFournisseurMP()!=null)
									{
										if(mapChargeSupp.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur())!=null)
										{
											
											QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()+"_"+coutMP.getFournisseurMP().getCodeFournisseur()));
											
										}
										
										
									}else
									{
										
										if(mapChargeSupp.get(coutMP.getMatierePremier().getCode())!=null)
										{
											QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()));
										
										}
										
									}
									
									
									montantTotalEnvrac=montantTotalEnvrac.add(QuantiteCharge.multiply(coutMP.getPrixUnitaire()));
									
								
								
								
							}else
							{
								if(mapChargeSupp.get(coutMP.getMatierePremier().getCode())!=null)
								{
									QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()));
									
									
									
								}
								
								montantTotalEmballage=montantTotalEmballage.add(QuantiteCharge.multiply(coutMP.getPrixUnitaire()));
							
							}
						
							
							montantTotal=montantTotal.add(QuantiteCharge.multiply(coutMP.getPrixUnitaire()));
							
							QuantiteTotal=coutMP.getQuantChargeSupp().add(QuantiteCharge);
											
							coutMP.setQuantChargeSupp(QuantiteTotal);
							
							TransferStockMP transferStockMPTMP=transferStockMPDAO.findTransferByCodeStatut(coutMP.getProdcutionCM().getNumOF(), ETAT_TRANSFER_STOCK_CHARGE_SUPP);
							if(transferStockMPTMP==null)
							{
								DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
								detailTransferStockMP.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
								detailTransferStockMP.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
								detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
								if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
								{
									if(coutMP.getFournisseurMP()!=null)
									{
										detailTransferStockMP.setFournisseur(coutMP.getFournisseurMP());
										
									}
									
									
								}
								
								
								detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
						if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
						{
							
							if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							{
								detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));
							}else
							{
								detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp());
							}
						
						
						
						}else
						{
						detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp());
						}
								
								detailTransferStockMP.setTransferStockMP(transferStock);
								listDetailTransferStockMPChargeSupp.add(detailTransferStockMP);
								
							}else
							{
								
								List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppTmp= detailTransfertMPDAO.findByTransferStockMP(transferStockMPTMP.getId());
								
								Boolean trouve=false;
								
								for(int j=0;j<listDetailTransferStockMPChargeSuppTmp.size();j++)
								{
									
									if(listDetailTransferStockMPChargeSuppTmp.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
									{
										trouve=true;
										
										if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
										{
											if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
											{
												listDetailTransferStockMPChargeSuppTmp.get(j).setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));	
												detailTransfertMPDAO.edit(listDetailTransferStockMPChargeSuppTmp.get(j));
											}else
											{
												
												listDetailTransferStockMPChargeSuppTmp.get(j).setQuantite(coutMP.getQuantChargeSupp());
												
												detailTransfertMPDAO.edit(listDetailTransferStockMPChargeSuppTmp.get(j));
											}
											
											
										}else
										{
											listDetailTransferStockMPChargeSuppTmp.get(j).setQuantite(coutMP.getQuantChargeSupp());
											
											detailTransfertMPDAO.edit(listDetailTransferStockMPChargeSuppTmp.get(j));
										}
										
										
										
									}
									
								}
								
								if(trouve==false)
								{
									
									DetailTransferStockMP detailTransferStockMPTEM=new DetailTransferStockMP();
									detailTransferStockMPTEM.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
									detailTransferStockMPTEM.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
									detailTransferStockMPTEM.setMatierePremier(coutMP.getMatierePremier());
									if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(coutMP.getFournisseurMP()!=null)
										{
											detailTransferStockMPTEM.setFournisseur(coutMP.getFournisseurMP());
											
										}
										
										
									}
									detailTransferStockMPTEM.setPrixUnitaire(coutMP.getPrixUnitaire());
							if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
							{
								if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								{
									detailTransferStockMPTEM.setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));
								}else
								{
									detailTransferStockMPTEM.setQuantite(coutMP.getQuantChargeSupp());
								}
								
								
								
							}else
							{
								detailTransferStockMPTEM.setQuantite(coutMP.getQuantChargeSupp());
							}
									
							detailTransferStockMPTEM.setTransferStockMP(transferStockMPTMP);
									detailTransfertMPDAO.add(detailTransferStockMPTEM);
												
								}
								
								
								
								
							}
							
							
						
							
							listCoutMPTmp.add(coutMP);
						}				
						
						
						
						
						
						production.setListCoutMP(listCoutMPTmp);
	
						

						//	production.setListCoutMP(ajouetrChargeSupp(listCoutMP,mapChargeSupp,production.getMagasinProd().getId(),production.getMagasinStockage().getId()));
				productionDAO.edit(production);
				
				// ajouter transfer stock MP type charge  Supp
		     	   String codeTransfert=Utils.genererCodeTransfer(production.getMagasinProd().getDepot().getCode(),ETAT_TRANSFER_STOCK_CHARGE);
		     		if(listDetailTransferStockMPChargeSupp.size()!=0)
		     		{
		     			
		     			transferStock.setCodeTransfer(production.getNumOF());
						transferStock.setCreerPar(production.getUtilisateurCreation());
						transferStock.setDate(new Date());
						transferStock.setDateTransfer(production.getDate_debFabPre());
						transferStock.setDepot(production.getMagasinProd().getDepot());
						transferStock.setListDetailTransferMP(listDetailTransferStockMPChargeSupp);
						transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE_SUPP);
						transferStockMPDAO.add(transferStock);
						
		     		}
					
		     		
		     		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		     		
		     		 
		     		
	  		     		listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
	  		     		
	  		     		if(statistiqueFinanciaireMPTmp!=null)
	  		     		{
	  		     			
	  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
	  		     			
	  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
	  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
	  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
	  		     			statistiqueFinanciaireMP.setCoutProduction(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutProduction()));
	  		     			statistiqueFinanciaireMP.setCodeTransfer(production.getNumOF());
	  		     			statistiqueFinanciaireMP.setDate(new Date());
	  		     			statistiqueFinanciaireMP.setDateOperation(production.getDate());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
	  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
	  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
	  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
	  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
	  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
	  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
	  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
	  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
	  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
	  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP+" "+Constantes.PROD_PF);
	  		     		 
	  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
	  		     			 
	  		     			
	  		     		} 	
	  		     		
	  		     		
		     		
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "La charge a été ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				
				
				afficher_tableMP(listCoutMP);
	 				
						}
				
			/*	try {
					EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
						"Charge Supplémetaire",
						registerMailBody2());
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
				}else {
					JOptionPane.showMessageDialog(null, "Il faut Lancer l'OF avant de valider la charge supplemntaire ", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
			
		  }
		});
		btnValiderChargeSupp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderChargeSupp.setBounds(228, 721, 158, 23);
		add(btnValiderChargeSupp);
		
		JButton btnImprimerSortieChargeSupp = new JButton("Bon Sortie Charge Supp");
		
		btnImprimerSortieChargeSupp.setIcon(imgImprimer);
		btnImprimerSortieChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	  		  	if(production.getStatut().equals(Constantes.ETAT_OF_LANCER)){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(production.getDate());
				 List<CoutMP> listCoutMP=production.getListCoutMP();
				 List<CoutMP> listCoutMPTmp= new ArrayList<CoutMP>();
				 for(int i=0;i<listCoutMP.size();i++)
				 {
					 if(listCoutMP.get(i).getQuantChargeSupp().setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))>0)
					 {
						 listCoutMPTmp.add(listCoutMP.get(i));
					 }
				 }
				 
				Map parameters = new HashMap();
				parameters.put("numOF", production.getNumOF());
				parameters.put("machine", production.getLigneMachine().getMachine().getNom());
				parameters.put("equipe", "");
				parameters.put("magasin", production.getMagasinProd().getLibelle());
				parameters.put("dateProd", date);
				JasperUtils.imprimerBonSortieMPChargeSupp(listCoutMPTmp,parameters,production.getNumOF());
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
	  		  	
  		  	
				
			}
		});
		btnImprimerSortieChargeSupp.setBounds(521, 721, 167, 23);
		add(btnImprimerSortieChargeSupp);
		
		JButton btnAnnulerOf = new JButton("Annuler OF");
		btnAnnulerOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    		
		    		if(production.getId()<=0){
		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message", JOptionPane.ERROR_MESSAGE);
		    			
		    		}else {
		    			
		    			if(!production.getStatut().equals(ETAT_OF_ANNULER)){
		    				 if(production.getStatut().equals(ETAT_OF_LANCER)){
		    					 
		    					 CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
				    			 int compteurProd=compteurProduction.getCompteur();
				    			 compteurProd=compteurProd-1;
				    			 compteurProduction.setCompteur(compteurProd);
		    					 
		    			annulerTransfertStockMPOF();
		    			
		    			production.setStatut(ETAT_OF_ANNULER);
		    			production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
		    			
		    			productionDAO.edit(production);
		    			compteurProductionDAO.edit(compteurProduction);
		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
		    			afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot));   
	  		     		InitialzeTous();
		    			
		    				 }else if(production.getStatut().equals(ETAT_OF_CREER)){
		    					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment annuler OF ?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										
										
									{
										 production.setStatut(ETAT_OF_ANNULER);
					 		    			production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
					 		    			
					 		    			productionDAO.edit(production);
					    					 
					 		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
					 		    			afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot));   
						  		     		InitialzeTous();
					 		    			
									}
		    					
		    				 }
		    			
		    			}else{
		    				JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
		    			}
		    		}
		    		
		    		
		    	}
		});
		btnAnnulerOf.setBounds(697, 721, 89, 23);
		add(btnAnnulerOf);
		
		JButton btnExporterExcel = new JButton("Exporter Excel");
		btnExporterExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

		     		
		     		

  				

	    		Magasin magasin=production.getMagasinStockage();
	    		if(magasin!=null)
	    		{
	    			
	    			if(TableOF.getSelectedRow()!=-1)
	    			{
	    				Production production=listProductions.get(TableOF.getSelectedRow());
	    				if(production!=null)
	    				{
	    					String titre="Lancer OF Numero  "+production.getNumOF()+ " Magasin "+magasin.getLibelle();
		    	    		String titrefeuille="Lancer OF ";
		    	    		ExporterTableVersExcel.tabletoexcel(table, titre,titrefeuille);
	    				}	
	    				
	    			}
	    		
	    		
	    		}else
	    		{
    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
  				
  				
				
				
			}
		});
		btnExporterExcel.setBounds(794, 722, 104, 23);
		add(btnExporterExcel);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("   Les OF En Cours     ");
		titledSeparator_1.setBounds(11, 13, 970, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 46, 971, 229);
		add(scrollPane_1);
		
		
		TableOF.setShowVerticalLines(false);
		TableOF.setSelectionBackground(new Color(51, 204, 255));
		TableOF.setRowHeightEnabled(true);
		TableOF.setRowHeight(20);
		TableOF.setGridColor(Color.BLUE);
		TableOF.setForeground(Color.BLACK);
		TableOF.setColumnControlVisible(true);
		TableOF.setBackground(Color.WHITE);
		TableOF.setAutoCreateRowSorter(true);
		TableOF.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(TableOF);
	   
	   
	   SubCategorieMp subCategorieMp=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		
		listFournisseurMP=fournisseurMPDAO.findByCategorie(subCategorieMp);
		for(int j=0;j<listFournisseurMP.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseurMP.get(j);
			
			mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
				  		 
	}
	
	
	void intialiser()
	{
		quantite.setText("");
		codeArticle.setText("");
		categorie.setSelectedItem("");
		
	}
	
void 	intialiserTableau() {
		
		modeleMP =new DefaultTableModel(
  		     	new Object[][] {
  		     	},
  		     	new String[] {
  		     			
					
  		     			"Code","Nom Matière Première   ","Fournisseur", "Quantité","Quantite Existante","Quantite Charge" , "Charge Supp", "Ajouter Charge Supp"
  		     	}
  		     ) {
  		     	boolean[] columnEditables = new boolean[] {
  		     			false,false,false,false,false,false,false,true
  		     	};
  		     	public boolean isCellEditable(int row, int column) {
  		     		return columnEditables[column];
  		     	}
  		     };
  		   table.setModel(modeleMP); 
  		   table.getColumnModel().getColumn(0).setPreferredWidth(30);
  		   table.getColumnModel().getColumn(1).setPreferredWidth(300);
  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
  		 table.getColumnModel().getColumn(4).setPreferredWidth(60);
		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
		   table.getTableHeader().setReorderingAllowed(false);
	}


void 	intialiserTableauOF() {
	
	modeleOF =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date OF","Num OF" , "Etat"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   TableOF.setModel(modeleOF); 
		   TableOF.getColumnModel().getColumn(0).setPreferredWidth(30);
		   TableOF.getColumnModel().getColumn(1).setPreferredWidth(300);
		   TableOF.getTableHeader().setReorderingAllowed(false);
}


	
	void afficher_tableMP(List<CoutMP> listCoutMP)
	{
	        BigDecimal chargeSupp;
		  int i=0;
		  intialiserTableau();
			while(i<listCoutMP.size())
			{
			
				
				CoutMP coutMP=listCoutMP.get(i);
				
				String fournisseurMP="";
				if(coutMP.getFournisseurMP()!=null)
				{
					fournisseurMP=coutMP.getFournisseurMP().getCodeFournisseur();
				}
				
				
				BigDecimal quantiteTotal=coutMP.getQuantite();
				BigDecimal quantiteExistante=coutMP.getQuantExistante();
				BigDecimal quantiteACharge=coutMP.getQuantCharge();
				chargeSupp=coutMP.getQuantChargeSupp();
				
				Object []ligne={coutMP.getMatierePremier().getCode(),
						coutMP.getMatierePremier().getNom(),fournisseurMP,
						NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6, RoundingMode.HALF_DOWN))+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
						NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteExistante.setScale(6, RoundingMode.HALF_DOWN)),
						NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteACharge.setScale(6, RoundingMode.HALF_DOWN)),
						NumberFormat.getNumberInstance(Locale.FRANCE).format(chargeSupp.setScale(6, RoundingMode.HALF_DOWN)),""};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
	
	
	
	void afficher_tableOF(List<Production> listProduction)
	{
	      
		  int i=0;
		  intialiserTableauOF();
			while(i<listProduction.size())
			{	
				
				Production production=listProduction.get(i);			
				Object []ligne={production.getDate() , production.getNumOF(), production.getStatut()};

				modeleOF.addRow(ligne);
				i++;
			}
	}	
	
	
	
	
	
	
	
	
	
	
	
	

boolean remplirMapChargeSupp(){
	
	mapChargeSupp.clear();
	
	
	
	
	boolean trouve=false;
	BigDecimal quantite=BigDecimal.ZERO;
	for(int j=0;j<table.getRowCount();j++){
		if(table.getValueAt(j, 7).toString().equals(""))
		{
			quantite =BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP);
		}else
		{
			quantite =new BigDecimal(table.getValueAt(j, 7).toString());
		}
		
		
		if(!table.getValueAt(j, 7).toString().equals("") && quantite.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) != 0){
			if(!table.getValueAt(j, 2).toString().equals(""))
			{
				
				mapChargeSupp.put(table.getValueAt(j, 0).toString()+"_"+table.getValueAt(j, 2).toString(), table.getValueAt(j, 7).toString());
			}else
			{
				mapChargeSupp.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 7).toString());
			}
			
			trouve=true;
		}else {
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), "0");
		}
		
	}
	return trouve;
}




void  annulerTransfertStockMPOF(){
	 
	
TransferStockMP transferStockMPCharge=transferStockMPDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
	
	if(transferStockMPCharge!=null)
	{

		
		transferStockMPDAO.deleteObject(transferStockMPCharge);
		
		
	}
	
	
	
TransferStockMP transferStockMPChargeSupp=transferStockMPDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
	
	if(transferStockMPChargeSupp!=null)
	{
		
		
		
		transferStockMPDAO.deleteObject(transferStockMPChargeSupp);
		
		
	}
	
 
  }


private static String registerMailBody() {
	return "<HTML><b>OF N°  :</b>"+production.getNumOF()+" a été lance <br><br>"
			
			+ "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
}


private static String registerMailBody2() {
	List<CoutMP> listCoutMP =new ArrayList<CoutMP>(); 
	
	listCoutMP =production.getListCoutMP();
	String mail;
	String mail1;
	String mail2 = "";
	String mail3;
	String MP[]= null;
	BigDecimal quantite[] = null;
	for (int i=0;i< listCoutMP.size();i++){
		CoutMP coutMP=listCoutMP.get(i);
		if(coutMP.getQuantChargeSupp().compareTo(BigDecimal.ZERO)>0)
		mail2=mail2+coutMP.getMatierePremier().getNom()+":"+coutMP.getQuantChargeSupp()+"<br><br>";
		
	}
		
	
	mail1= "<HTML><b>Charge Supplémentaire OF N°  :</b>"+production.getNumOF()+"  <br><br>";
			
	mail3		= "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
		
	
	return mail1+mail2+mail3;
}

List<Production> ChargerOF(String statutCreer,String statutLancer, String depot)
{
	listProductions.clear();
	 listProductions=productionDAO.listeProductionEtatCreer(statutCreer,statutLancer, depot);
		
	return listProductions;
}


public void InitialzeTous()
{
	intialiserTableau();
codeArticle.setText("");
quantite.setText("");
categorie.setSelectedIndex(-1);	
txtDateDebutPrev.setText("");
txtDateFinPrev.setText("");
comboEquipe.setSelectedIndex(-1);
comboLigneMachine.setSelectedIndex(-1);
comboMachine.setSelectedIndex(-1);
	
}


public void VeriffierLesNombresproductionsterminer()
{
	
	listProductionsterminerGroupByDate=productionDAO.LesProductionTerminerParDateParDepotGroupByDate(production.getDate(), Constantes.ETAT_OF_TERMINER, production.getCodeDepot())	;
	
	 datesProductionErreur="";
	for(int j=0;j<listProductionsterminerGroupByDate.size();j++)
	{
		
		
		listDetailResponsableProdTmp.clear();
		
		int numberproduction=productionDAO.NombreProductionTerminerParDateParDepot(listProductionsterminerGroupByDate.get(j).getDate(), Constantes.ETAT_OF_TERMINER,listProductionsterminerGroupByDate.get(j).getCodeDepot());	
		
		int nbrProd=0;	
		
		listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(listProductionsterminerGroupByDate.get(j).getDate(), listProductionsterminerGroupByDate.get(j).getDate(), listProductionsterminerGroupByDate.get(j).getMagasinStockage().getDepot().getId(),"");

		for(int i=0;i<listDetailResponsableProdTmp.size();i++)
		{

		nbrProd=listDetailResponsableProdTmp.get(i).getNbrProduction();

		}
		
		if(numberproduction-nbrProd!=0)
		{
			datesProductionErreur=datesProductionErreur+" "+listProductionsterminerGroupByDate.get(j).getDate()+"\n";
			
		}
		
		
	}
	
	
	
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

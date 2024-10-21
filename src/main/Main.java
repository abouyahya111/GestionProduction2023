package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicMenuBarUI;

import matierePremiere.AjoutAutresDechetMatierePremiere;
import matierePremiere.AjoutForme;
import matierePremiere.AjoutFormeParBox;
import matierePremiere.AjoutMatierePremiere;
import matierePremiere.AjouterMPCompose;
import matierePremiere.ChercherAutresDechetMatierePremiere;
import matierePremiere.ChercherMatierePremiere;
import matierePremiere.ListeMatierePremiere;
import matierePremiere.ModifierMatierePremierCompose;
import presentation.article.AjoutGrammageArticle;
import presentation.article.AjouterEstimationThe;
import presentation.article.CreerEstimationArticle;
import presentation.article.ListeDesArticles;
import presentation.article.ModifierEstimationArticle;
import presentation.client.AjoutClient;
import presentation.client.ChercherClient;
import presentation.depot.AjoutDepot;
import presentation.depot.ChercherDepot;
import presentation.parametre.ActiverOffre;
import presentation.parametre.AjoutCharges;
import presentation.parametre.AjoutEmployeBloque;
import presentation.parametre.AjoutOffre;
import presentation.parametre.AjoutParametre;
import presentation.parametre.AjoutUtilisateur;
import presentation.parametre.AjouterPrixClientMP;
import presentation.parametre.AjouterTypeEtSousTypeBonSortieStockMP;
import presentation.parametre.GererAuthUtilisateur;
import presentation.stastiques.CoutDechetProduction;
import presentation.stockMP.AfficherDetailTransferMP;
import presentation.stockMP.AfficherEtatComparaisonPrixAnneeActuelAvecAnneeProchaine;
import presentation.stockMP.AfficherStockMP;
import presentation.stockMP.AjoutChargeDepot;
import presentation.stockMP.AjoutCompteMagasinier;
import presentation.stockMP.AjoutFournisseurAdhesive;
import presentation.stockMP.AjoutFournisseurMP;
import presentation.stockMP.AjoutInitialMP;
import presentation.stockMP.AjoutManqueImportation;
import presentation.stockMP.AjouterActionPerteMP;
import presentation.stockMP.AjouterReceptionDechet;
import presentation.stockMP.AjouterInventaire1;
import presentation.stockMP.AjouterStockMP2;
import presentation.stockMP.BonSortieStockMP;
import presentation.stockMP.ConsultationTransfererMPEnPF;
import presentation.stockMP.ConsultationTransfererStockMPProduction;
import presentation.stockMP.ConsulterActionPerteMP;
import presentation.stockMP.ConsulterBonSortieStockMP;
import presentation.stockMP.ConsulterCompteMagasinier;
import presentation.stockMP.ConsulterDeclarationReceptionMagasinier;
import presentation.stockMP.ConsulterEcartMarchandiseDeplacerEnAttente;
import presentation.stockMP.ConsulterEtatStock;
import presentation.stockMP.ConsulterEtatStockDechetMP;
import presentation.stockMP.ConsulterEtatStockParFournisseur;
import presentation.stockMP.ConsulterInitialMP;
import presentation.stockMP.ConsulterManqueImportation;
import presentation.stockMP.ConsulterMarchandiseDeplacerEnAttente;
import presentation.stockMP.TraitementMarchandiseDeplacerEnAttente;
import presentation.stockMP.ConsulterMouvementStock;
import presentation.stockMP.ConsulterMouvementStockDechetManque;
import presentation.stockMP.ConsulterMouvementTransferStockMPEtPF;
import presentation.stockMP.ConsulterPerteMP;
import presentation.stockMP.ConsulterReceptions;
import presentation.stockMP.ConsulterReceptionsDechet;
import presentation.stockMP.ConsulterTotalReceptions;
import presentation.stockMP.AjouterStockMP;
import presentation.stockMP.EntrerStockMP;
import presentation.stockMP.HistoriquePrixEtQuantiteInitialMP;
import presentation.stockMP.ConsulterMarchandiseDeplacer;
import presentation.stockMP.ListeMPNonUtiliser;
import presentation.stockMP.RegularisationInventaire;
import presentation.stockMP.AjouterInventaire2;
import presentation.stockMP.AjouterPerteMP;
import presentation.stockMP.SituationStockEmballage;
import presentation.stockMP.SituationStockEnVrac;
import presentation.stockMP.SortirStockMP;
import presentation.stockMP.TransfererMPProduitFini;
import presentation.stockMP.TransfererStockMP;
import presentation.stockPF.AfficherStockPF;
import presentation.stockPF.ConsulterEtatStockPF;
import presentation.stockPF.ConsulterMouvementStockPF;
import presentation.stockPF.SortirStockPF;
import util.Constantes;
import util.Utils;
import Equipe.AfficherFicheEmploye;
import Equipe.AfficherFicheGlobaleEmploye;
import Equipe.AfficherListeEmploye;
import Equipe.AjoutEmploye;
import Equipe.AjoutEquipe;
import Equipe.AjoutPrimeAnciennte;
import Equipe.AjoutTravailHorsProd;
import Equipe.AjouterEmployeRepos;
import Equipe.AutorisationDesAbsents;
import Equipe.ChercherEmploye;
import Equipe.ChercherEquipe;
import Equipe.ConsulterAutorisationDesAbsents;
import Equipe.ConsulterDesEmployeesRepos;
import Equipe.ImpressionFicheEmployeeEtBuletin;
import Equipe.SituationDesAbsents;
import Equipe.SituationEmployeProduction;
import Equipe.SituationGlobaleEmployee;
import FacturePF.AjoutFactureVenteMP;
import FacturePF.ConsulterFactureMP;
import FacturePF.ImprimerFactureMP;
import FacturePF.SituationCaisse;
import Production.AfficherCompteStockMP;
import Production.AjoutChargeFixeProd;
import Production.AjoutChargeVariableProd;
import Production.AjouterManquePlusDechetEmballage;
import Production.AjouterManquePlusEnVrac;
import Production.ConsulterCoutsHorsProductionsEnAttent;
import Production.ConsulterManquePlusDechetEmballage;
import Production.ConsulterManquePlusEnVrac;
import Production.ConsulterOffres;
import Production.CoutParArticle;
import Production.CoutProduction;
import Production.CoutProductionParArticle;
import Production.CreerOrdreFabrication;
import Production.DetailOF;
import Production.DetailOFParArticle;
import Production.LancerOrdreFabrication;
import Production.ListDetailOrdreFabrication;
import Production.ListePFNonFabrique;
import Production.PercentageProduction;
import Production.ProductionArticleParMois;
import Production.ProductionEnvracParMois;
import Production.RegularisationDesPrixMP;
import Production.SaisirListeEmployeGen;
import Production.SaisirListeMenage;
import Production.SituationDechetManqueParJour;
import Production.SituationGlobaleCoutArticle;
import Production.SituationManquePlusManqueMoinsEnVrac;
import Production.SituationManquePlusManqueMoinsEtDechetEmballage;
import Production.SituationPFParAnnee;
import Production.SituationProductionTotalParArticlePF;
import Production.SituationProgrammeProduction;
import Production.StatistiqueEnVracConsomme;
import Production.StatistiqueEnVracUtiliserLorsDeLaProductionPF;
import Production.StatistiqueFinanciereDesMP;
import Production.TerminerOrdreFabrication;
import Production.TonnageFabriqueeParJour;
import ProductionCarton.AjouterDechetFournisseurCarton;
import ProductionCarton.ConsulterManqueDechetFournisseurCarton;
import ProductionCarton.CreerOrdreFabricationMP;
import ProductionCarton.LancerOrdreFabricationMP;
import ProductionCarton.TerminerOrdreFabricationMP;
import Referentiel.AjoutActionMP;
import Referentiel.AjoutCategorie;
import Referentiel.AjoutConditionOffre;
import Referentiel.AjoutCoutMachine;
import Referentiel.AjoutMotifPerteMP;
import Referentiel.AjoutService;
import Referentiel.AjoutSubCategorie;
import Referentiel.AjoutTypeOffre;
import UniteFabrication.AjoutMachine;
import UniteFabrication.ChercherMachine;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MenuDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ConsulterReceptionTotal;
import dao.entity.Habilitation;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Menu;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.Promotion;
import dao.entity.SituationProductionTotalParArticlePFClass;
import dao.entity.Utilisateur;


public class Main extends JFrame implements Constantes{
    
        public static JPanel mainPane;
        public static JLayeredPane contentPane;
        public static JLayeredPane precedentContentPane;
        public static JScrollPane generalScrollPane;
        public static JPanel titlePanel;
        public static JLabel title;
        public static JLabel bottomMessage;
        public static JPanel messagePanel; 
        
        public static JPanel userLoginPanel;
        public static JLabel  loginLabel;
        
        public static JPanel msgPanel;
        public static JLabel  messageLabel;
        
        private JButton cancelButto;
        private JButton btnStatistique;
        private JButton btnDeconnexion;
        
        private JButton btnGenerer;
        
        public JScrollPane listScrollPane;
        public static Color color;
       
       
      
      
    	private HabilitationDAO habilitationDAO;
    	private Utilisateur utilisateur =AuthentificationView.utilisateur;
     
    	private MenuDAO menuDAO;
    	/*Menu */
    	
    	private JMenu depotMenu;
    	private JMenu fournisseurMenu;
    	private JMenu MatierePremierMenu;
    	private JMenu articleMenu;
    	private  JMenu stockMPMenu;
    	private JMenu categorieMenu;
    	private JMenu formBoxMenu;
    	private JMenu uniteFabMenu;
    	private JMenu equipeFabMenu ;
    	private JMenu ordreFabMenu;
    	private JMenu prodCartMenu;
    	private JMenu stockPFMenu;
    	private JMenu parametreMenu;
    	private  JMenu clientMenu ;
    	private  JMenu ReferentielMenu;
    	private JMenu magasinierMenu;
    	private JMenu offreMenu;
    	private JMenu perteMenu;
    	private JMenu parametreEmployeMenu;
    	private JMenu employeProductionMenu;
    	private JMenu heursTravailMenu;
    	private JMenu reposMenu;
    	private JMenu salaireEmployerMenu;
    	private JMenu consultationEmployeMenu;
    	private  JMenu StatistiqueMenu;
    	private  JMenu StatistiquesMenu;
    	private  JMenu InventaireMenu;
    	private  JMenu VenteDechetMenu;
    	private  JMenu InitialMenu;
    	private  JMenu ReceptionMPMenu;
    	private  JMenu ReceptionDechetMenu;
    	JMenuBar menubar = new JMenuBar();
    	private JTextPane txtLabel = new JTextPane();
    	private static Map< String, Boolean>  mapHabilitation= new HashMap<>();
		private static Map< Integer, String>  mapMenu = new HashMap<>();
		private static Map< String, JMenu>  mapSousSousMenu = new HashMap<>();
		JMenu ajouterMPMenuItem ;
		JMenu deplacementMPMenuItem;
		JMenu transfertstockMPMenuItem;
		JMenu situationstockMPMenuItem;
		JMenu BonStortieStockMPMenu;
		
		 JMenu AutresMenu;
		 JMenu ProductionPFMenu;
		 JMenu SituationManqueDechetMenu;
		 JMenu CoutProductionMenu;
		 private  JMenu EcartMPEnAttenteMenuItem ;
        public Main(){
        	 
        	 
        	habilitationDAO= new HabilitationDAOImpl();
        	 
        	menuDAO=new MenuDAOImpl();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Bienvenue dans Application de Gestion Production V1.0");
            final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight()-10);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
           
            
           mainPane=new JPanel();
           // mainPane   = new MainEntityFlatMenu();
           // mainPane.setBackground(new Color(204, 204, 255));
            mainPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
            setContentPane(mainPane);
            mainPane.setLayout(null);
            contentPane  = new JLayeredPane();
            
            titlePanel = new JPanel();
            titlePanel.setBounds(0, 0, (int) dim.getWidth(), 33);
            titlePanel.setBackground(Color.white);
            titlePanel.setBorder(new SoftBevelBorder(0, null, null, null, null));
            mainPane.add(titlePanel);
            title = new JLabel("Accueil Bienvenue " + AuthentificationView.login);
            title.setForeground(new Color(255, 140, 0));
            title.setFont(new Font("New Times", 1, 16));
            //btnDeconnexion=new ImageIcon(this.getClass().getResource("/img/deconnect.png"))
           btnDeconnexion = new JButton(new ImageIcon(this.getClass().getResource("/img/deconnect.png")));
            btnDeconnexion.setBounds((int)dim.getWidth() - 40, 0, 35, 32);
            btnDeconnexion.setToolTipText("Déconnecter");
           
            cancelButto = new JButton(new ImageIcon(this.getClass().getResource("/img/Setting.png")));
            cancelButto.setBounds((int)dim.getWidth() - 80, 0, 35, 32);
            cancelButto.setToolTipText("Paramètre");
            
            btnStatistique = new JButton(new ImageIcon(this.getClass().getResource("/img/statistik.png")));
            btnStatistique.setBounds((int)dim.getWidth() - 120, 0, 35, 32);
            btnStatistique.setToolTipText("Statistiques");
            
            btnGenerer = new JButton(new ImageIcon(this.getClass().getResource("/img/generer.png")));
            btnGenerer.setBounds((int)dim.getWidth() - 160, 0, 35, 32);
            btnGenerer.setToolTipText("Générer Stock");
            
            
          
            titlePanel.add(this.btnDeconnexion);
            titlePanel.add(this.cancelButto);
            titlePanel.add(this.btnStatistique);
            titlePanel.add(this.btnGenerer);
            
            GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
            gl_titlePanel.setHorizontalGroup(
            	gl_titlePanel.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_titlePanel.createSequentialGroup()
            			.addComponent(title, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
            			.addContainerGap(1286, Short.MAX_VALUE))
            );
            gl_titlePanel.setVerticalGroup(
            	gl_titlePanel.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_titlePanel.createSequentialGroup()
            			.addComponent(title, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            titlePanel.setLayout(gl_titlePanel);
            
            messagePanel = new JPanel(new FlowLayout(0));
            messagePanel.setBounds(5, dim.height - 120 + 5, dim.width - 450, 20);
            messagePanel.setLayout(new BoxLayout(messagePanel, 1));
            messagePanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(messagePanel);
            bottomMessage = new JLabel();
            bottomMessage.setText("");
            messagePanel.add(bottomMessage);
            
            userLoginPanel = new JPanel();
            userLoginPanel.setBounds(messagePanel.getWidth() + 5, dim.height - 120 + 5, dim.width - messagePanel.getWidth() - 350, 20);
            userLoginPanel.setLayout(new BoxLayout(this.userLoginPanel, 1));
            userLoginPanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(userLoginPanel);
            color = userLoginPanel.getBackground();
            loginLabel = new JLabel();
            loginLabel.setText(utilisateur.getLogin());
            userLoginPanel.add(loginLabel);
            
            msgPanel = new JPanel();
            msgPanel.setBounds(messagePanel.getWidth() + 105, dim.height - 120 + 5, dim.width - messagePanel.getWidth() - 125, 20);
            msgPanel.setLayout(new BoxLayout(msgPanel, 1));
            msgPanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(msgPanel);
            messageLabel = new JLabel();
            msgPanel.add(messageLabel);
            
           
            generalScrollPane = new JScrollPane();
            
            generalScrollPane.setViewportView(contentPane);
            generalScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            generalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            generalScrollPane.setBounds(0,30,(int)dim.getWidth(),(int)dim.getHeight()-10-140+5);
            
            mainPane.add(generalScrollPane);
            contentPane.setOpaque(true);
            contentPane.setBackground(Color.WHITE);
            contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
            contentPane.setLayout(null);
            txtLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            txtLabel.setBackground(Color.PINK);
            
            
            txtLabel.setBounds(10, 52, 609, 417);
            
            
            
            
            
            
            
            
          //  contentPane.add(txtLabel);
            
            /*TRAITEMENTS DES ALERTES DU STOCK MINIMAL*/
           
           // List<StockMP> listeStockMP =stockMPDAO.findStockMin(utilisateur.getId());
            
            // ALERTE DU STOCK MINIMAL
            
           /* Map< Integer, Float> mapStockTotalByMp= new HashMap<>();
            mapStockTotalByMp=stockMPDAO.findStockTotalByMagasin(utilisateur.getId());
            List<MatierePremier>	listMP =dao.findAll();
            for(i=0;i<listMP.size();i++){
            	MatierePremier matierePremier=listMP.get(i);
            	float quantiteStock =mapStockTotalByMp.get(matierePremier.getId());	
            	nomMP+="-"+matierePremier.getNom()+" : "+quantiteStock+"\n";
            }
            txtLabel.setText(nomMP);*/
        	getContentPane().setLayout(null);
        	  btnDeconnexion.addActionListener(new ActionListener() {
  	  		  	public void actionPerformed(ActionEvent e) {
  	  		  		moveToTheNewWindow();
  	  		  	}
        	  });
        	  
        	  btnGenerer.addActionListener(new ActionListener() {
    	  		  	public void actionPerformed(ActionEvent e) {
    	  		    
    	  		  
		  		    
		  		   
		  		    
		  		   	 
		  		  		
		  		   	//	Utils.genererStockByMagasinMP(listMP,listMagasin);
		  		   	//	Utils.genererStockProduitFiniByMagasin(listArticles, listMagasinProduitFini);
		  		   		 
		  		   		
		  		   	JOptionPane.showMessageDialog(null, "Le stock est généré avec succès", "Attention", JOptionPane.INFORMATION_MESSAGE);
    	  		  	}
          	  });
                   
            createMenuBar();
            
        }
        
       
        
        
        public Main(int id_user,String user,Connection conn){
        	
        	
        }
        

        
        
 private void createMenuBar() {

         menubar = new JMenuBar();
        menubar.setBackground(new Color(248, 248, 255));
        menubar.setOpaque(true);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menubar.add(Box.createRigidArea(new Dimension(0,35)));
        menubar.setFont(new Font("New Times",Font.BOLD,10));
        menubar.setUI ( new BasicMenuBarUI (){
       public void paint ( Graphics g, JComponent c ){
       g.setColor ( new Color(248, 248, 255) );
       
       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
    }
} );
         menubar.setForeground(Color.white);
         
         
         
         
        
  /* Menu Accueil */////   
         
        /*
        JMenu acceuilMenu = new JMenu("Accueil");
        menubar.add(acceuilMenu);
        acceuilMenu.setName("acceuilMenu");
        
        JMenuItem acceuilItem = new JMenuItem("Accueil");
        acceuilItem.setName("acceuilItem");
        acceuilItem.setMnemonic(KeyEvent.VK_A);
        acceuilMenu.add(acceuilItem);
        acceuilItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText(AuthentificationView.login);
                                                            	titlePanel.add(title);
                                                            	Main window = new Main();
                                            	 				
                                            	 				window.setVisible(true);
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        JMenuItem acceuilFermerMenuItem = new JMenuItem("Fermer");
        acceuilFermerMenuItem.setName("acceuilFermerMenuItem");
        acceuilFermerMenuItem.setMnemonic(70);
        acceuilMenu.add(acceuilFermerMenuItem);
        */
        
        
        /*Menu Referentiel
         * 
         */
        
        ReferentielMenu = new JMenu("Referentiel");
        ReferentielMenu.setName("ReferentielMenu");
          menubar.add(ReferentielMenu);
          
          
    
          
          
          
          clientMenu = new JMenu("Client");
          clientMenu.setName("clientMenu");
          clientMenu.setMnemonic(KeyEvent.VK_A);
          mapSousSousMenu.put("clientMenu", clientMenu);
          ReferentielMenu.add(clientMenu);
         
         JMenuItem ajoutClientItem = new JMenuItem("Ajout Client");
         ajoutClientItem.setName("ajoutClientItem");
         ajoutClientItem.setMnemonic(KeyEvent.VK_A);
         
         clientMenu.add(ajoutClientItem);
         ajoutClientItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Ajouter Client");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutClient();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                             }
                                                                  });
         
         JMenuItem chercherClientItem = new JMenuItem("Chercher Client");
         chercherClientItem.setName("chercherClientItem");
         chercherClientItem.setMnemonic(KeyEvent.VK_A);
         clientMenu.add(chercherClientItem);
         chercherClientItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Chercher Client");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ChercherClient();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
        fournisseurMenu = new JMenu("Fournisseur");
         fournisseurMenu.setName("fournisseurMenu");
         fournisseurMenu.setMnemonic(KeyEvent.VK_A);
         mapSousSousMenu.put("fournisseurMenu", fournisseurMenu);
         ReferentielMenu.add(fournisseurMenu);
          
          
          JMenuItem FournisseurMPItem = new JMenuItem("Ajout Fournisseur MP");
         FournisseurMPItem.setName("FournisseurMPItem");
         FournisseurMPItem.setMnemonic(65);
          fournisseurMenu.add(FournisseurMPItem);
         FournisseurMPItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(final ActionEvent event) {
                 title.setText("Ajout Fournisseur MP");
                titlePanel.add(Main.title);
                 (contentPane = new AjoutFournisseurMP()).setPreferredSize(new Dimension((int)dim.getWidth() + 100, (int)dim.getHeight() - 70));
                 generalScrollPane.setViewportView(Main.contentPane);
                contentPane.setOpaque(true);
             }
         });
         
          JMenuItem FournisseurAdhesiveItem = new JMenuItem("Ajout Fournisseur Adhesive");
         FournisseurAdhesiveItem.setName("FournisseurAdhesiveItem");
         FournisseurAdhesiveItem.setMnemonic(65);
          fournisseurMenu.add(FournisseurAdhesiveItem);
         FournisseurAdhesiveItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(final ActionEvent event) {
                title.setText("Ajout Fournisseur Adhesive");
                titlePanel.add(Main.title);
                 (contentPane = new AjoutFournisseurAdhesive()).setPreferredSize(new Dimension((int)dim.getWidth() + 100, (int)dim.getHeight() - 70));
                 generalScrollPane.setViewportView(Main.contentPane);
                 contentPane.setOpaque(true);
             }
         });
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         

          
          
          
         depotMenu = new JMenu("Dépot");
         depotMenu.setName("depotMenu");
         depotMenu.setMnemonic(KeyEvent.VK_A);
         mapSousSousMenu.put("depotMenu", depotMenu);
         ReferentielMenu.add(depotMenu);
         
        JMenuItem depotItem = new JMenuItem("Ajouter Dépot");
        depotItem.setName("depotItem");
        depotItem.setMnemonic(KeyEvent.VK_A);
        depotMenu.add(depotItem);
        depotItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Dépot");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutDepot();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
        
        JMenuItem chercherDepotItem = new JMenuItem("Chercher Dépot");
        chercherDepotItem.setName("chercherDepotItem");
        chercherDepotItem.setMnemonic(KeyEvent.VK_A);
        depotMenu.add(chercherDepotItem);
        chercherDepotItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Chercher Dépot");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ChercherDepot();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
          
          
         
        uniteFabMenu = new JMenu("Unité de Fabrication");
        uniteFabMenu.setName("uniteFabMenu");
        uniteFabMenu.setMnemonic(KeyEvent.VK_A);
        mapSousSousMenu.put("uniteFabMenu", uniteFabMenu);
        ReferentielMenu.add(uniteFabMenu);
       
        JMenuItem AjouterClientMenuItem = new JMenuItem("Ajouter Unité de Fabrication");
        AjouterClientMenuItem.setName("AjouterClientMenuItem");
        AjouterClientMenuItem.setMnemonic(KeyEvent.VK_A);
        uniteFabMenu.add(AjouterClientMenuItem);
        AjouterClientMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Machine");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutMachine();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem SoldeClientMenuItem = new JMenuItem("Chercher Unité de Fabrication");
        SoldeClientMenuItem.setName("SoldeClientMenuItem");
        SoldeClientMenuItem.setMnemonic(KeyEvent.VK_A);
        uniteFabMenu.add(SoldeClientMenuItem);
        SoldeClientMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	contentPane  = new ChercherMachine();
                                                            	title.setText("Chercher Unité de Fabrication");
                                                            	titlePanel.add(title);
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        
        magasinierMenu = new JMenu("Magasinier");
        magasinierMenu.setName("magasinierMenu");
        magasinierMenu.setMnemonic(KeyEvent.VK_A);
        mapSousSousMenu.put("magasinierMenu", magasinierMenu);
        ReferentielMenu.add(magasinierMenu);   
        
        
        JMenuItem ajouterCompteMagasinierItem = new JMenuItem("Ajouter Compte Magasinier");
        ajouterCompteMagasinierItem.setName("ajouterCompteMagasinierItem");
        ajouterCompteMagasinierItem.setMnemonic(KeyEvent.VK_A);
        magasinierMenu.add(ajouterCompteMagasinierItem);
        ajouterCompteMagasinierItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Compte Magasinier");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutCompteMagasinier();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem ConsulterCompteMagasinierItem = new JMenuItem("Consulter Compte Magasinier");
        ConsulterCompteMagasinierItem.setName("ConsulterCompteMagasinierItem");
        ConsulterCompteMagasinierItem.setMnemonic(KeyEvent.VK_A);
        magasinierMenu.add(ConsulterCompteMagasinierItem);
        ConsulterCompteMagasinierItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Consulter Compte Magasinier");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ConsulterCompteMagasinier();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
    
        
        
        
      
        categorieMenu = new JMenu("Categorie");
        categorieMenu.setName("categorieMenu");
        categorieMenu.setMnemonic(KeyEvent.VK_A);
        mapSousSousMenu.put("categorieMenu", categorieMenu);
        ReferentielMenu.add(categorieMenu); 
          
        
          JMenuItem ajouterCategorietMenuItem = new JMenuItem("Ajouter Categorie");
          ajouterCategorietMenuItem.setMnemonic(KeyEvent.VK_A);
          categorieMenu.add(ajouterCategorietMenuItem);
          ajouterCategorietMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Categorie");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutSubCategorie();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          
          JMenuItem ajouterSubCategorietMenuItem = new JMenuItem("Ajouter Sous Categorie");
          ajouterSubCategorietMenuItem.setMnemonic(KeyEvent.VK_A);
          categorieMenu.add(ajouterSubCategorietMenuItem);
          ajouterSubCategorietMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Sous Categorie");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutCategorie();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          formBoxMenu = new JMenu("Form Box");
          formBoxMenu.setName("formBoxMenu");
          formBoxMenu.setMnemonic(KeyEvent.VK_A);
          mapSousSousMenu.put("formBoxMenu", formBoxMenu);
          ReferentielMenu.add(formBoxMenu);   
            
   
          JMenuItem ajouterFormeBoxtMenuItem = new JMenuItem("Ajouter La Forme De Box");
          ajouterFormeBoxtMenuItem.setMnemonic(KeyEvent.VK_A);
          formBoxMenu.add(ajouterFormeBoxtMenuItem);
          ajouterFormeBoxtMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter La Forme De Box");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutForme();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          JMenuItem ajouterFormeParBoxtMenuItem = new JMenuItem("Ajouter La Forme Par Box");
          ajouterFormeParBoxtMenuItem.setMnemonic(KeyEvent.VK_A);
          formBoxMenu.add(ajouterFormeParBoxtMenuItem);
          ajouterFormeParBoxtMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter La Forme Par Box");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutFormeParBox();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          




offreMenu = new JMenu("Offre");
offreMenu.setName("offreMenu");
offreMenu.setMnemonic(KeyEvent.VK_A);
mapSousSousMenu.put("offreMenu", offreMenu);
ReferentielMenu.add(offreMenu);

	 
JMenuItem AjouterTypeOffreMenuItem = new JMenuItem("Ajouter Offre");
AjouterTypeOffreMenuItem.setName("AjouterTypeOffreMenuItem");
AjouterTypeOffreMenuItem.setMnemonic(KeyEvent.VK_A);
offreMenu.add(AjouterTypeOffreMenuItem);
AjouterTypeOffreMenuItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent event) {
	title.setText("Ajout Type Offre");
	titlePanel.add(title);
	contentPane  = new AjoutTypeOffre();
	
 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
 generalScrollPane.setViewportView(contentPane);                
 contentPane.setOpaque(true);
 
      }

  });

JMenuItem AjouterConditionOffreMenuItem = new JMenuItem("Ajouter Condition Offre");
AjouterConditionOffreMenuItem.setName("AjouterConditionOffreMenuItem");
AjouterConditionOffreMenuItem.setMnemonic(KeyEvent.VK_A);
offreMenu.add(AjouterConditionOffreMenuItem);
AjouterConditionOffreMenuItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent event) {
	title.setText("Ajouter Condition Offre");
	titlePanel.add(title);
	contentPane  = new AjoutConditionOffre();
	
 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
 generalScrollPane.setViewportView(contentPane);                
 contentPane.setOpaque(true);
 
      }

  });


JMenuItem ConsulterListeOffresMenuItem = new JMenuItem("Consulter Liste Offres");
ConsulterListeOffresMenuItem.setName("ConsulterListeOffresMenuItem");
ConsulterListeOffresMenuItem.setMnemonic(KeyEvent.VK_A);
offreMenu.add(ConsulterListeOffresMenuItem);
ConsulterListeOffresMenuItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent event) {
	title.setText("Consulter Liste Offres");
	titlePanel.add(title);
	contentPane  = new ConsulterOffres();
	
 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
 generalScrollPane.setViewportView(contentPane);                
 contentPane.setOpaque(true);
 
      }

  });

perteMenu = new JMenu("Perte");
perteMenu.setName("perteMenu");
perteMenu.setMnemonic(KeyEvent.VK_A);
mapSousSousMenu.put("perteMenu", perteMenu);
ReferentielMenu.add(perteMenu);


JMenuItem ajouterMotifPerteMPMenuItem = new JMenuItem("Ajouter Motif De Perte MP");
ajouterMotifPerteMPMenuItem.setName("ajouterMotifPerteMPMenuItem");
ajouterMotifPerteMPMenuItem.setMnemonic(KeyEvent.VK_A);
perteMenu.add(ajouterMotifPerteMPMenuItem);
ajouterMotifPerteMPMenuItem.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent event) {
                                        	title.setText("Ajouter Motif De Perte MP");
                                        	titlePanel.add(title);
                                        	contentPane  = new AjoutMotifPerteMP();
                                        	
                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                            generalScrollPane.setViewportView(contentPane);                
                                            contentPane.setOpaque(true);
                                            

                                                 }
                                             });


JMenuItem ajouterActionMPMenuItem = new JMenuItem("Ajouter Action MP");
ajouterActionMPMenuItem.setName("ajouterActionMPMenuItem");
ajouterActionMPMenuItem.setMnemonic(KeyEvent.VK_A);
perteMenu.add(ajouterActionMPMenuItem);
ajouterActionMPMenuItem.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent event) {
                                        	title.setText("Ajouter Action MP");
                                        	titlePanel.add(title);
                                        	contentPane  = new AjoutActionMP();
                                        	
                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                            generalScrollPane.setViewportView(contentPane);                
                                            contentPane.setOpaque(true);
                                            

                                                 }
                                             });

      


JMenuItem ajoutUtilisateurMenuItem = new JMenuItem("Ajouter Utilisateur");
ajoutUtilisateurMenuItem.setName("ajoutUtilisateurMenuItem");
ajoutUtilisateurMenuItem.setMnemonic(KeyEvent.VK_A);
ReferentielMenu.add(ajoutUtilisateurMenuItem);
ajoutUtilisateurMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Ajout Utilisateur");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new AjoutUtilisateur();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                         });

JMenuItem gererUtilisateurMenuItem = new JMenuItem("Gérer Authorisation");
gererUtilisateurMenuItem.setName("gererUtilisateurMenuItem");
gererUtilisateurMenuItem.setMnemonic(KeyEvent.VK_A);
ReferentielMenu.add(gererUtilisateurMenuItem);
gererUtilisateurMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Gérer Authorisation");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new GererAuthUtilisateur();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                    
                                                         });



        
          
          
         
        
          
          
          /*Menu Paramètres
           * 
           */
          
            parametreMenu = new JMenu("Paramètres");
            parametreMenu.setName("parametreMenu");
            menubar.add(parametreMenu);
            
            JMenuItem ajouterPrixClientMenuItem = new JMenuItem("Ajouter Prix Client ");
            ajouterPrixClientMenuItem.setName("ajouterPrixClientMenuItem");
            ajouterPrixClientMenuItem.setMnemonic(KeyEvent.VK_A);
            parametreMenu.add(ajouterPrixClientMenuItem);
            ajouterPrixClientMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Prix Client");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterPrixClientMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ChargesMenuItem = new JMenuItem("Ajouter Charges");
            ChargesMenuItem.setName("ChargesMenuItem");
            ChargesMenuItem.setMnemonic(KeyEvent.VK_A);
            parametreMenu.add(ChargesMenuItem);
            ChargesMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajout Charges");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutCharges();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    
                                                                         }
                                                                
                                                                     });
            
            
 




            




JMenuItem ajoutParametreMenuItem = new JMenuItem("Ajouter Paramètre");
ajoutParametreMenuItem.setName("ajoutParametreMenuItem");
ajoutParametreMenuItem.setMnemonic(KeyEvent.VK_A);
parametreMenu.add(ajoutParametreMenuItem);
ajoutParametreMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Ajouter Paramètre");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new AjoutParametre();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                         });


JMenuItem ajouterCoutMachineMenuItem = new JMenuItem("Ajouter Le Cout Machine");
ajouterCoutMachineMenuItem.setMnemonic(KeyEvent.VK_A);
parametreMenu.add(ajouterCoutMachineMenuItem);
ajouterCoutMachineMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Ajouter Le Cout Machine");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new AjoutCoutMachine();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                         });   


JMenuItem ajouterServiceMenuItem = new JMenuItem("Ajouter Service");
ajouterServiceMenuItem.setMnemonic(KeyEvent.VK_A);
parametreMenu.add(ajouterServiceMenuItem);
ajouterServiceMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Ajouter Service");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new AjoutService();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                         });   



JMenuItem ajouterTypeSousTypeBonSortieMenuItem = new JMenuItem("Ajouter Type Sous Type Bon Sortie");
ajouterTypeSousTypeBonSortieMenuItem.setMnemonic(KeyEvent.VK_A);
parametreMenu.add(ajouterTypeSousTypeBonSortieMenuItem);
ajouterTypeSousTypeBonSortieMenuItem.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent event) {
                                                    	title.setText("Ajouter Type Sous Type Bon Sortie");
                                                    	titlePanel.add(title);
                                                    	contentPane  = new AjouterTypeEtSousTypeBonSortieStockMP();
                                                    	
                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                        generalScrollPane.setViewportView(contentPane);                
                                                        contentPane.setOpaque(true);
                                                        

                                                             }
                                                         });   
         
MatierePremierMenu = new JMenu("Matière Première");
MatierePremierMenu.setName("MatierePremierMenu");
MatierePremierMenu.setMnemonic(KeyEvent.VK_A);
menubar.add(MatierePremierMenu); 
          

          
          JMenuItem saisirMatierePremiereItem = new JMenuItem("Ajouter Matière Première");
          saisirMatierePremiereItem.setName("saisirMatierePremiereItem");
          saisirMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(saisirMatierePremiereItem);
          saisirMatierePremiereItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Matière Première");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutMatierePremiere();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
         
          JMenuItem chercherMatierePremiereItem = new JMenuItem("Chercher Matière Première");
          chercherMatierePremiereItem.setName("chercherMatierePremiereItem");
          chercherMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(chercherMatierePremiereItem);
          chercherMatierePremiereItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Chercher Matière Première");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ChercherMatierePremiere();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          JMenuItem ajouterMpComposeItem = new JMenuItem("Ajouter Matière Première Composé");
          ajouterMpComposeItem.setName("ajouterMpComposeItem");
          ajouterMpComposeItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(ajouterMpComposeItem);
          ajouterMpComposeItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Matière Première Composé");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjouterMPCompose();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          JMenuItem modifierMpComposeItem = new JMenuItem("Modifier Matière Première Composé");
          modifierMpComposeItem.setName("modifierMpComposeItem");
          modifierMpComposeItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(modifierMpComposeItem);
          modifierMpComposeItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Modifier Matière Première Composé");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ModifierMatierePremierCompose();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem listeMpItem = new JMenuItem("Liste Matiere Premiere");
          listeMpItem.setName("listeMpItem");
          listeMpItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(listeMpItem);
          listeMpItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Liste Matiere Premiere");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ListeMatierePremiere();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
        
          
          
          JMenuItem saisirAutresDechetMatierePremiereItem = new JMenuItem("Ajouter Autres Dechet Matière Première");
          saisirAutresDechetMatierePremiereItem.setName("saisirAutresDechetMatierePremiereItem");
          saisirAutresDechetMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(saisirAutresDechetMatierePremiereItem);
          saisirAutresDechetMatierePremiereItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Autres Dechet Matière Première");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutAutresDechetMatierePremiere();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          JMenuItem chercherAutresDechetMatierePremiereItem = new JMenuItem("Chercher Autres Dechet Matière Première");
          chercherAutresDechetMatierePremiereItem.setName("chercherAutresDechetMatierePremiereItem");
          chercherAutresDechetMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
          MatierePremierMenu.add(chercherAutresDechetMatierePremiereItem);
          chercherAutresDechetMatierePremiereItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Chercher Autres Dechet Matière Première");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ChercherAutresDechetMatierePremiere();
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });    
          
          articleMenu = new JMenu("Article");
          articleMenu.setName("articleMenu");
          articleMenu.setMnemonic(KeyEvent.VK_A);
          menubar.add(articleMenu);  
        
          
           
           JMenuItem CreerEstimationMenuItem = new JMenuItem("Ajouter Article");
           CreerEstimationMenuItem.setName("CreerEstimationMenuItem");
           CreerEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
           articleMenu.add(CreerEstimationMenuItem);
           CreerEstimationMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Créer Estimation article");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new CreerEstimationArticle();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
           JMenuItem modifierEstimationMenuItem = new JMenuItem("Chercher Article");
           modifierEstimationMenuItem.setName("modifierEstimationMenuItem");
           modifierEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
           articleMenu.add(modifierEstimationMenuItem);
           modifierEstimationMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Modifier Estimation article");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ModifierEstimationArticle();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           JMenuItem ajouterEstimationMenuItem = new JMenuItem("Ajouter Estimation catégorie thé ");
           ajouterEstimationMenuItem.setName("ajouterEstimationMenuItem");
           ajouterEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
           articleMenu.add(ajouterEstimationMenuItem);
           ajouterEstimationMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Ajouter Estimation catégorie thé ");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjouterEstimationThe();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
           
           JMenuItem ajouterGrammageArticleMenuItem = new JMenuItem("Ajouter Grammage Article ");
           ajouterGrammageArticleMenuItem.setName("ajouterGrammageArticleMenuItem");
           ajouterGrammageArticleMenuItem.setMnemonic(KeyEvent.VK_A);
           articleMenu.add(ajouterGrammageArticleMenuItem);
           ajouterGrammageArticleMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Ajouter Grammage Article ");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutGrammageArticle();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           JMenuItem afficherListeDesArticleMenuItem = new JMenuItem("Afficher Liste Des Articles");
           afficherListeDesArticleMenuItem.setName("afficherListeDesArticleMenuItem");
           afficherListeDesArticleMenuItem.setMnemonic(KeyEvent.VK_A);
           articleMenu.add(afficherListeDesArticleMenuItem);
           afficherListeDesArticleMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Afficher Liste Des Articles");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ListeDesArticles();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           JMenuItem PrimeAncienneteMenuItem = new JMenuItem("Ajouter Prime Anciennete");
           PrimeAncienneteMenuItem.setName("PrimeAncienneteMenuItem");
           PrimeAncienneteMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreMenu.add(PrimeAncienneteMenuItem);
           PrimeAncienneteMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Ajouter Prime Anciennete");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutPrimeAnciennte();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           
           
           JMenuItem ajouterRegularisationPrixMenuItem = new JMenuItem("Regularisation Prix Par Annee");
           ajouterRegularisationPrixMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreMenu.add(ajouterRegularisationPrixMenuItem);
           ajouterRegularisationPrixMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Regularisation Prix Par Annee");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new RegularisationDesPrixMP();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
           /*Menu Equipe
            * 
            */
           
             equipeFabMenu = new JMenu("Equipe");
             equipeFabMenu.setName("equipeFabMenu");
             menubar.add(equipeFabMenu);
             
             
             
             
             
             
             
           /*  
           JMenuItem creerEquipeMenuItem = new JMenuItem("Créer Equipe");
           AjouterClientMenuItem.setMnemonic(KeyEvent.VK_A);
           equipeFabMenu.add(creerEquipeMenuItem);
           creerEquipeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Créer Equipe");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutEquipe();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
          */
           /*
           JMenuItem chercherEquipeMenuItem = new JMenuItem("Chercher Equipe");
           chercherEquipeMenuItem.setMnemonic(KeyEvent.VK_A);
           equipeFabMenu.add(chercherEquipeMenuItem);
           chercherEquipeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Chercher Equipe");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ChercherEquipe();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
                                                                    
                                                                 
              */   
             
             parametreEmployeMenu = new JMenu("Parametre Employe");
             parametreEmployeMenu.setName("parametreEmployeMenu");
             parametreEmployeMenu.setMnemonic(KeyEvent.VK_A);
             mapSousSousMenu.put("parametreEmployeMenu", parametreEmployeMenu);
             equipeFabMenu.add(parametreEmployeMenu);
             
             
             
           JMenuItem ajoutEmployeMenuItem = new JMenuItem("Ajouter Employé");
           ajoutEmployeMenuItem.setName("ajoutEmployeMenuItem");
           ajoutEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreEmployeMenu.add(ajoutEmployeMenuItem);
           ajoutEmployeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Ajouter Employé");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutEmploye();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           JMenuItem creerEmployeMenuItem = new JMenuItem("Chercher Employé");
           creerEmployeMenuItem.setName("creerEmployeMenuItem");
           creerEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreEmployeMenu.add(creerEmployeMenuItem);
           creerEmployeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Chercher Employé");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ChercherEmploye();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           /*
           JMenuItem BloquerEmployeMenuItem = new JMenuItem("Bloquer Employé");
           BloquerEmployeMenuItem.setName("BloquerEmployeMenuItem");
           BloquerEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreEmployeMenu.add(BloquerEmployeMenuItem);
           BloquerEmployeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Bloquer Employé");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutEmployeBloque();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });

           */
           
           
           JMenuItem listeEmployeMenuItem = new JMenuItem("Afficher liste Employées");
           listeEmployeMenuItem.setName("listeEmployeMenuItem");
           listeEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
           parametreEmployeMenu.add(listeEmployeMenuItem);
           listeEmployeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Afficher liste Employées");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AfficherListeEmploye();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           
           
           
           employeProductionMenu = new JMenu("Employer Production");
           employeProductionMenu.setName("employeProductionMenu");
           employeProductionMenu.setMnemonic(KeyEvent.VK_A);
           mapSousSousMenu.put("employeProductionMenu", employeProductionMenu);
           equipeFabMenu.add(employeProductionMenu);      
           
           heursTravailMenu = new JMenu("Heurs Travails");
           heursTravailMenu.setName("heursTravailMenu");
           heursTravailMenu.setMnemonic(KeyEvent.VK_A);
           mapSousSousMenu.put("heursTravailMenu", heursTravailMenu);
           employeProductionMenu.add(heursTravailMenu); 
           
           JMenuItem saisitDelaiTravailMenuItem = new JMenuItem("Saisir Travail Hors Prod");
           saisitDelaiTravailMenuItem.setName("saisitDelaiTravailMenuItem");
           saisitDelaiTravailMenuItem.setMnemonic(KeyEvent.VK_A);
           heursTravailMenu.add(saisitDelaiTravailMenuItem);
           saisitDelaiTravailMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Saisir Travail Hors Prod");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjoutTravailHorsProd();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
           JMenuItem saisitDelaiTravailMenageMenuItem = new JMenuItem("Saisir Travail Menage");
           saisitDelaiTravailMenageMenuItem.setName("saisitDelaiTravailMenageMenuItem");
           saisitDelaiTravailMenageMenuItem.setMnemonic(KeyEvent.VK_A);
           heursTravailMenu.add(saisitDelaiTravailMenageMenuItem);
           saisitDelaiTravailMenageMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Saisir Travail Menage");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new SaisirListeMenage();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });  
           
           
           
           
           reposMenu = new JMenu("Repos");
           reposMenu.setName("reposMenu");
           reposMenu.setMnemonic(KeyEvent.VK_A);
           mapSousSousMenu.put("reposMenu", reposMenu);
           employeProductionMenu.add(reposMenu);     
           
           
           JMenuItem AjouterEmployeReposMenuItem = new JMenuItem("Ajouter Employe repos");
           AjouterEmployeReposMenuItem.setName("AjouterEmployeReposMenuItem");
           AjouterEmployeReposMenuItem.setMnemonic(KeyEvent.VK_A);
           reposMenu.add(AjouterEmployeReposMenuItem);
           AjouterEmployeReposMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Ajouter Employe repos");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AjouterEmployeRepos();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           JMenuItem ConsulterEmployeReposMenuItem = new JMenuItem("Consulter Employe repos");
           ConsulterEmployeReposMenuItem.setName("ConsulterEmployeReposMenuItem");
           ConsulterEmployeReposMenuItem.setMnemonic(KeyEvent.VK_A);
           reposMenu.add(ConsulterEmployeReposMenuItem);
           ConsulterEmployeReposMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Consulter Employe repos");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ConsulterDesEmployeesRepos();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           
           
           salaireEmployerMenu = new JMenu("Salaire Employe");
           salaireEmployerMenu.setName("salaireEmployerMenu");
           salaireEmployerMenu.setMnemonic(KeyEvent.VK_A);
           mapSousSousMenu.put("salaireEmployerMenu", salaireEmployerMenu);
           equipeFabMenu.add(salaireEmployerMenu);   
           
           
           
           
           
           JMenuItem ficheEmployeMenuItem = new JMenuItem("Fiche Employé");
           ficheEmployeMenuItem.setName("ficheEmployeMenuItem");
           ficheEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
           salaireEmployerMenu.add(ficheEmployeMenuItem);
           ficheEmployeMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Fiche Employé");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AfficherFicheEmploye();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           JMenuItem ficheEmployeGlobaleMenuItem = new JMenuItem("Fiche Employé Globale");
           ficheEmployeGlobaleMenuItem.setName("ficheEmployeGlobaleMenuItem");
           ficheEmployeGlobaleMenuItem.setMnemonic(KeyEvent.VK_A);
           salaireEmployerMenu.add(ficheEmployeGlobaleMenuItem);
           ficheEmployeGlobaleMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Fiche Employé Globale");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AfficherFicheGlobaleEmploye();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           JMenuItem ImpressionficheEmployeBuletinMenuItem = new JMenuItem("Impression Fiche Employe / Buletin");
           ImpressionficheEmployeBuletinMenuItem.setName("ficheEmployeGlobaleMenuItem");
           ImpressionficheEmployeBuletinMenuItem.setMnemonic(KeyEvent.VK_A);
           salaireEmployerMenu.add(ImpressionficheEmployeBuletinMenuItem);
           ImpressionficheEmployeBuletinMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Impression Fiche Employe / Buletin");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ImpressionFicheEmployeeEtBuletin();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
          
           
           
           consultationEmployeMenu = new JMenu("Consultation");
           consultationEmployeMenu.setName("consultationEmployeMenu");
           consultationEmployeMenu.setMnemonic(KeyEvent.VK_A);
           mapSousSousMenu.put("consultationEmployeMenu", consultationEmployeMenu);
           equipeFabMenu.add(consultationEmployeMenu);   
           
           
           
       
           
           JMenuItem SituationEmployeeProductionMenuItem = new JMenuItem("Situation Des Employees Production");
           SituationEmployeeProductionMenuItem.setName("SituationEmployeeProductionMenuItem");
           SituationEmployeeProductionMenuItem.setMnemonic(KeyEvent.VK_A);
           consultationEmployeMenu.add(SituationEmployeeProductionMenuItem);
           SituationEmployeeProductionMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Situation Des Employees Production");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new SituationEmployeProduction();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           JMenuItem SituationEmployeeAbsentMenuItem = new JMenuItem("Situation Des Employees Absents");
           SituationEmployeeAbsentMenuItem.setName("SituationEmployeeAbsentMenuItem");
           SituationEmployeeAbsentMenuItem.setMnemonic(KeyEvent.VK_A);
           consultationEmployeMenu.add(SituationEmployeeAbsentMenuItem);
           SituationEmployeeAbsentMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Situation Des Employees Absents");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new SituationDesAbsents();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           
           JMenuItem ConsulterAutorisationEmployeeAbsentMenuItem = new JMenuItem("Consulter Autorisation Des Employees Absents");
           ConsulterAutorisationEmployeeAbsentMenuItem.setName("ConsulterAutorisationEmployeeAbsentMenuItem");
           ConsulterAutorisationEmployeeAbsentMenuItem.setMnemonic(KeyEvent.VK_A);
           consultationEmployeMenu.add(ConsulterAutorisationEmployeeAbsentMenuItem);
           ConsulterAutorisationEmployeeAbsentMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Consulter Autorisation Des Employees Absents");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new ConsulterAutorisationDesAbsents();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });     
           
           
           JMenuItem SituationEmployeGlobalMenuItem = new JMenuItem("Situation Employee Global");
           SituationEmployeGlobalMenuItem.setName("SituationEmployeGlobalMenuItem");
           SituationEmployeGlobalMenuItem.setMnemonic(KeyEvent.VK_A);
           consultationEmployeMenu.add(SituationEmployeGlobalMenuItem);
           SituationEmployeGlobalMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Situation Employee Global");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new SituationGlobaleEmployee();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
           
           
           JMenuItem AutorisationEmployeeAbsentMenuItem = new JMenuItem("Autorisation Des Employees Absents");
           AutorisationEmployeeAbsentMenuItem.setName("AutorisationEmployeeAbsentMenuItem");
           AutorisationEmployeeAbsentMenuItem.setMnemonic(KeyEvent.VK_A);
           equipeFabMenu.add(AutorisationEmployeeAbsentMenuItem);
           AutorisationEmployeeAbsentMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Autorisation Des Employees Absents");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AutorisationDesAbsents();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    }); 
           
         
           
       
           
           
         
   
           
           
           /*Menu Production Carton 
            * 
            */
           
            
           prodCartMenu = new JMenu("Production Carton");
           prodCartMenu.setName("prodCartMenu");
           menubar.add(prodCartMenu);
          
           JMenuItem creerordrefabricationmpMenuItem = new JMenuItem("Créer Ordre de Fabrication MP");
           creerordrefabricationmpMenuItem.setName("creerordrefabricationmpMenuItem");
           creerordrefabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
           prodCartMenu.add(creerordrefabricationmpMenuItem);
           creerordrefabricationmpMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Créer Ordre de Fabrication MP");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new CreerOrdreFabricationMP();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           
           
           
           JMenuItem lancerorderfabricationmpMenuItem = new JMenuItem("Lancer Ordre de Fabrication MP");
           lancerorderfabricationmpMenuItem.setName("lancerorderfabricationmpMenuItem");
           lancerorderfabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
           prodCartMenu.add(lancerorderfabricationmpMenuItem);
           lancerorderfabricationmpMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Lancer Ordre de Fabrication MP");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new LancerOrdreFabricationMP();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
          
           
       
            
           
            JMenuItem terminerorderfabricationmpMenuItem = new JMenuItem("Terminer Ordre de Fabrication MP");
            terminerorderfabricationmpMenuItem.setName("terminerorderfabricationmpMenuItem");
            terminerorderfabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
            prodCartMenu.add(terminerorderfabricationmpMenuItem);
            terminerorderfabricationmpMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Terminer Ordre de Fabrication MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new TerminerOrdreFabricationMP(new ProductionMP(),"","");
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
           
           
            
            JMenuItem ajoutermanquedechetfournisseurcartonMenuItem = new JMenuItem("Ajouter Manque Dechet Fournisseur Carton");
            ajoutermanquedechetfournisseurcartonMenuItem.setName("ajoutermanquedechetfournisseurcartonMenuItem");
            ajoutermanquedechetfournisseurcartonMenuItem.setMnemonic(KeyEvent.VK_A);
            prodCartMenu.add(ajoutermanquedechetfournisseurcartonMenuItem);
            ajoutermanquedechetfournisseurcartonMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Manque Dechet Fournisseur Carton");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterDechetFournisseurCarton();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterManqueDechetFournisseurCartonMenuItem = new JMenuItem("Consulter Manque Dechet Fournisseur Carton");
            ConsulterManqueDechetFournisseurCartonMenuItem.setName("ConsulterManqueDechetFournisseurCartonMenuItem");
            ConsulterManqueDechetFournisseurCartonMenuItem.setMnemonic(KeyEvent.VK_A);
            prodCartMenu.add(ConsulterManqueDechetFournisseurCartonMenuItem);
            ConsulterManqueDechetFournisseurCartonMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Manque Dechet Fournisseur Carton");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new  ConsulterManqueDechetFournisseurCarton();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     }); 
            
            /*Menu Ordre de fabrication 
             * 
             */
            
             
              ordreFabMenu = new JMenu("Ordre de Fabrication");
              ordreFabMenu.setName("ordreFabMenu");
              menubar.add(ordreFabMenu);
              /*
               ProductionPFMenu = new JMenu("Production PF");
              ProductionPFMenu.setName("ProductionPFMenu");
              ProductionPFMenu.setMnemonic(KeyEvent.VK_A);
              mapSousSousMenu.put("ProductionPFMenu", ProductionPFMenu);
              ordreFabMenu.add(ProductionPFMenu);  
              */
              
              
              
              
              
              
              
            JMenuItem creerOrdreMenuItem = new JMenuItem("Créer Order de Fabrication");
            creerOrdreMenuItem.setName("creerOrdreMenuItem");
            creerOrdreMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(creerOrdreMenuItem);
            creerOrdreMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Créer Order de fabrication");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new CreerOrdreFabrication(null,null ,"","","","","","","", null,null,"","","","","","",false);
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
         
          
            JMenuItem lancerOrdreMenuItem = new JMenuItem("Lancer Order de Fabrication");
            lancerOrdreMenuItem.setName("lancerOrdreMenuItem");
            lancerOrdreMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(lancerOrdreMenuItem);
            lancerOrdreMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Lancer Order de fabrication");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new LancerOrdreFabrication();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            

            JMenuItem ajouterHeuresTravailEquipeGeneriqueMenuItem = new JMenuItem("Ajouter Heurs Travail Equipe Generique");
            ajouterHeuresTravailEquipeGeneriqueMenuItem.setName("ajouterHeuresTravailEquipeGeneriqueMenuItem");
            ajouterHeuresTravailEquipeGeneriqueMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(ajouterHeuresTravailEquipeGeneriqueMenuItem);
            ajouterHeuresTravailEquipeGeneriqueMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Heurs Travail Equipe Generique");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new SaisirListeEmployeGen();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem terminerOrdreMenuItem = new JMenuItem("Terminer Order de Fabrication");
            terminerOrdreMenuItem.setName("terminerOrdreMenuItem");
            terminerOrdreMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(terminerOrdreMenuItem);
            terminerOrdreMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Terminer Order de Fabrication");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new TerminerOrdreFabrication(new Production(),"","");
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem AjouterManquePlusDechetEmballageMenuItem = new JMenuItem("Ajouter Manque Plus Dechet Emballage");
            AjouterManquePlusDechetEmballageMenuItem.setName("AjouterManquePlusDechetEmballageMenuItem");
            AjouterManquePlusDechetEmballageMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(AjouterManquePlusDechetEmballageMenuItem);
            AjouterManquePlusDechetEmballageMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Manque Plus Dechet Emballage");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterManquePlusDechetEmballage();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem AjouterManquePlusEnVracMenuItem = new JMenuItem("Ajouter Manque Plus En Vrac");
            AjouterManquePlusEnVracMenuItem.setName("AjouterManquePlusEnVracMenuItem");
            AjouterManquePlusEnVracMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(AjouterManquePlusEnVracMenuItem);
            AjouterManquePlusEnVracMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Manque Plus En Vrac");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterManquePlusEnVrac();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterEtatCoutHorsProductionEnAttentMenuItem = new JMenuItem("Consulter Cout Hors Production En Attent");
            ConsulterEtatCoutHorsProductionEnAttentMenuItem.setName("ConsulterEtatCoutHorsProductionEnAttentMenuItem");
            ConsulterEtatCoutHorsProductionEnAttentMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(ConsulterEtatCoutHorsProductionEnAttentMenuItem);
            ConsulterEtatCoutHorsProductionEnAttentMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Cout Hors Production En Attent");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new  ConsulterCoutsHorsProductionsEnAttent();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     }); 
            
            JMenuItem PercentageProductionMenuItem = new JMenuItem("Percentage Production");
            PercentageProductionMenuItem.setName("PercentageProductionMenuItem");
            PercentageProductionMenuItem.setMnemonic(KeyEvent.VK_A);
            ordreFabMenu.add(PercentageProductionMenuItem);
            PercentageProductionMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Percentage Production");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new PercentageProduction();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
  
           
           JMenuItem afficherCoutDechetMenuItem = new JMenuItem("Afficher Cout total des déchets");
           afficherCoutDechetMenuItem.setName("afficherCoutDechetMenuItem");
           afficherCoutDechetMenuItem.setMnemonic(KeyEvent.VK_A);
        //   ordreFabMenu.add(afficherCoutDechetMenuItem);
           afficherCoutDechetMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Afficher Cout total des déchets");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new CoutDechetProduction();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
           JMenuItem afficherCompteStockMPMenuItem = new JMenuItem("Afficher Compte Stock Matière Première");
           afficherCompteStockMPMenuItem.setName("afficherCompteStockMPMenuItem");
           afficherCompteStockMPMenuItem.setMnemonic(KeyEvent.VK_A);
           ordreFabMenu.add(afficherCompteStockMPMenuItem);
           afficherCompteStockMPMenuItem.addActionListener(new ActionListener() {
                                                               public void actionPerformed(ActionEvent event) {
                                                               	title.setText("Afficher Compte Stock Matière Première");
                                                               	titlePanel.add(title);
                                                               	contentPane  = new AfficherCompteStockMP();
                                                               	
                                                                   contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                   generalScrollPane.setViewportView(contentPane);                
                                                                   contentPane.setOpaque(true);
                                                                   

                                                                        }
                                                                    });
           
            
        
            
            /*Menu Stock matière première
             * 
             */
             
             
            
             stockMPMenu = new JMenu("Stock Matière Première");
             stockMPMenu.setName("stockMPMenu");
            menubar.add(stockMPMenu);
            
             ajouterMPMenuItem = new JMenu("Ajouter Matière Première");
            ajouterMPMenuItem.setName("ajouterMPMenuItem");
            ajouterMPMenuItem.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("ajouterMPMenuItem", ajouterMPMenuItem);
            stockMPMenu.add(ajouterMPMenuItem);
            
            
            InitialMenu = new JMenu("Initial");
            InitialMenu.setName("InitialMenu");
            InitialMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("InitialMenu", InitialMenu);
            ajouterMPMenuItem.add(InitialMenu);
            
            
            
            JMenuItem ajouterInitialMPMenuItem = new JMenuItem("Ajouter Initial Matière Première");
            ajouterInitialMPMenuItem.setName("ajouterInitialMPMenuItem");
            ajouterInitialMPMenuItem.setMnemonic(KeyEvent.VK_A);
            InitialMenu.add(ajouterInitialMPMenuItem);
            ajouterInitialMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Initial Matière Première");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutInitialMP();	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem historiqueInitialMPMenuItem = new JMenuItem("Historique Initial Matière Première");
            historiqueInitialMPMenuItem.setName("historiqueInitialMPMenuItem");
            historiqueInitialMPMenuItem.setMnemonic(KeyEvent.VK_A);
            InitialMenu.add(historiqueInitialMPMenuItem);
            historiqueInitialMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Historique Initial Matière Première");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterInitialMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem historiquePrixEtQuantiteInitialMPMenuItem = new JMenuItem("Historique Prix Et Quantite Initial MP");
            historiquePrixEtQuantiteInitialMPMenuItem.setName("historiquePrixEtQuantiteInitialMPMenuItem");
            historiquePrixEtQuantiteInitialMPMenuItem.setMnemonic(KeyEvent.VK_A);
            InitialMenu.add(historiquePrixEtQuantiteInitialMPMenuItem);
            historiquePrixEtQuantiteInitialMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Historique Prix Et Quantite Initial MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new HistoriquePrixEtQuantiteInitialMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            ReceptionMPMenu = new JMenu("Rception MP");
            ReceptionMPMenu.setName("ReceptionMPMenu");
            ReceptionMPMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("ReceptionMPMenu", ReceptionMPMenu);
            ajouterMPMenuItem.add(ReceptionMPMenu);
            
           
            
            
            JMenuItem ajouterStockMenuItem = new JMenuItem("Ajouter Reception MP");
            ajouterStockMenuItem.setName("ajouterStockMenuItem");
            ajouterStockMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(ajouterStockMenuItem);
            ajouterStockMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Reception MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterStockMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            
            
            
            
            
            
            
            JMenuItem historiqueReceptionStockMenuItem = new JMenuItem("Consultation Detail Reception MP");
            historiqueReceptionStockMenuItem.setName("historiqueReceptionStockMenuItem");
            historiqueReceptionStockMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(historiqueReceptionStockMenuItem);
            historiqueReceptionStockMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consultation Detail Reception MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterReceptions();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem historiqueReceptionTotalStockMenuItem = new JMenuItem("Consultation Global Reception MP");
            historiqueReceptionTotalStockMenuItem.setName("historiqueReceptionTotalStockMenuItem");
            historiqueReceptionTotalStockMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(historiqueReceptionTotalStockMenuItem);
            historiqueReceptionTotalStockMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consultation Global Reception MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterTotalReceptions();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            /*
            JMenuItem consulterDeclarationReceptionStockEnAttenteMenuItem = new JMenuItem("Consulter Declaration Reception Stock Matière Première En Attente");
            consulterDeclarationReceptionStockEnAttenteMenuItem.setName("consulterDeclarationReceptionStockEnAttenteMenuItem");
            consulterDeclarationReceptionStockEnAttenteMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(consulterDeclarationReceptionStockEnAttenteMenuItem);
            consulterDeclarationReceptionStockEnAttenteMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Declaration Reception Stock Matière Première En Attente");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterDeclarationReceptionMagasinier();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            */
            
            JMenuItem ajouterManqueImportationMPMenuItem = new JMenuItem("Ajouter Manque Importation MP");
            ajouterManqueImportationMPMenuItem.setName("ajouterManqueImportationMPMenuItem");
            ajouterManqueImportationMPMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(ajouterManqueImportationMPMenuItem);
            ajouterManqueImportationMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Manque Importation MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutManqueImportation();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
      
            JMenuItem ConsulterManqueImportationMPMenuItem = new JMenuItem("Consulter Manque Importation MP");
            ConsulterManqueImportationMPMenuItem.setName("ConsulterManqueImportationMPMenuItem");
            ConsulterManqueImportationMPMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionMPMenu.add(ConsulterManqueImportationMPMenuItem);
            ConsulterManqueImportationMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Manque Importation MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterManqueImportation();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });      
            
            
            
            ReceptionDechetMenu= new JMenu("Rception Dechet");
            ReceptionDechetMenu.setName("ReceptionDechetMenu");
            ReceptionDechetMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("ReceptionDechetMenu", ReceptionDechetMenu);
            ajouterMPMenuItem.add(ReceptionDechetMenu);
            
            
            
            JMenuItem AjouterReceptionDechetMPItem = new JMenuItem("Ajouter Réception Dechets");
            AjouterReceptionDechetMPItem.setName("AjouterReceptionDechetMPItem");
            AjouterReceptionDechetMPItem.setMnemonic(KeyEvent.VK_A);
            ReceptionDechetMenu.add(AjouterReceptionDechetMPItem);
            AjouterReceptionDechetMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Réception Dechets");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterReceptionDechet();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem historiqueReceptionDechetMenuItem = new JMenuItem("Historique Reception Dechets");
            historiqueReceptionDechetMenuItem.setName("historiqueReceptionDechetMenuItem");
            historiqueReceptionDechetMenuItem.setMnemonic(KeyEvent.VK_A);
            ReceptionDechetMenu.add(historiqueReceptionDechetMenuItem);
            historiqueReceptionDechetMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Historique Reception Dechets");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterReceptionsDechet();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });  
            
            
                       
           
            
             deplacementMPMenuItem = new JMenu("Déplacement Matière Première");
            deplacementMPMenuItem.setName("deplacementMPMenuItem");      
            deplacementMPMenuItem.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("deplacementMPMenuItem", deplacementMPMenuItem);
            stockMPMenu.add(deplacementMPMenuItem);
            
            JMenuItem entrerMPMenuItem = new JMenuItem("Recevoir Stock Matière Première");
            entrerMPMenuItem.setName("entrerMPMenuItem");
            entrerMPMenuItem.setMnemonic(KeyEvent.VK_A);
            deplacementMPMenuItem.add(entrerMPMenuItem);
            entrerMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Recevoir Stock Matière Première");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EntrerStockMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem sortirMPMenuItem = new JMenuItem("Sortir Stock Matière Première ");
            sortirMPMenuItem.setName("sortirMPMenuItem");
            sortirMPMenuItem.setMnemonic(KeyEvent.VK_A);
            deplacementMPMenuItem.add(sortirMPMenuItem);
            sortirMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Sortir Stock Matière Première");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new SortirStockMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem detailTransferMenuItem = new JMenuItem("Afficher Détail Déplacement");
            detailTransferMenuItem.setName("detailTransferMenuItem");
            detailTransferMenuItem.setMnemonic(KeyEvent.VK_A);
            deplacementMPMenuItem.add(detailTransferMenuItem);
            detailTransferMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Afficher Détail Déplacement");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AfficherDetailTransferMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterMPDeplacerMenuItem = new JMenuItem("Consulter Stock MP Déplacer");
            ConsulterMPDeplacerMenuItem.setName("ConsulterMPDeplacerMenuItem");
            ConsulterMPDeplacerMenuItem.setMnemonic(KeyEvent.VK_A);
            deplacementMPMenuItem.add(ConsulterMPDeplacerMenuItem);
            ConsulterMPDeplacerMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Stock MP Déplacer");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterMarchandiseDeplacer();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            EcartMPEnAttenteMenuItem = new JMenu("Ecart MP EN-ATTENTE");
            EcartMPEnAttenteMenuItem.setName("EcartMPEnAttenteMenuItem");      
            EcartMPEnAttenteMenuItem.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("EcartMPEnAttenteMenuItem", EcartMPEnAttenteMenuItem);
            deplacementMPMenuItem.add(EcartMPEnAttenteMenuItem);
            
            
            
            JMenuItem TRFSecartMPdeplacerMenuItem = new JMenuItem("TRFS Ecart MP Déplacer");
            TRFSecartMPdeplacerMenuItem.setName("TRFSecartMPdeplacerMenuItem");
            TRFSecartMPdeplacerMenuItem.setMnemonic(KeyEvent.VK_A);
            EcartMPEnAttenteMenuItem.add(TRFSecartMPdeplacerMenuItem);
            TRFSecartMPdeplacerMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("TRFS Ecart MP Déplacer");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new TraitementMarchandiseDeplacerEnAttente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     }); 
            JMenuItem consulterdeplacementMPEcartMenuItem = new JMenuItem("Consulter Déplacement MP Ecart");
            consulterdeplacementMPEcartMenuItem.setName("consulterdeplacementMPEcartMenuItem");
            consulterdeplacementMPEcartMenuItem.setMnemonic(KeyEvent.VK_A);
            EcartMPEnAttenteMenuItem.add(consulterdeplacementMPEcartMenuItem);
            consulterdeplacementMPEcartMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Déplacement MP Ecart");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterMarchandiseDeplacerEnAttente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });  
            
            JMenuItem detailconsultationdesecartsMPdeplacerMenuItem = new JMenuItem("Détail Consultation Des Ecarts MP Déplacer");
            detailconsultationdesecartsMPdeplacerMenuItem.setName("detailconsultationdesecartsMPdeplacerMenuItem");
            detailconsultationdesecartsMPdeplacerMenuItem.setMnemonic(KeyEvent.VK_A);
            EcartMPEnAttenteMenuItem.add(detailconsultationdesecartsMPdeplacerMenuItem);
            detailconsultationdesecartsMPdeplacerMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Détail Consultation Des Ecarts MP Déplacer");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterEcartMarchandiseDeplacerEnAttente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });  

            
            
            
            
            
            
            
             transfertstockMPMenuItem = new JMenu("Transfert Stock Matière Première");
            transfertstockMPMenuItem.setName("transfertstockMPMenuItem");
            transfertstockMPMenuItem.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("transfertstockMPMenuItem", transfertstockMPMenuItem);
            stockMPMenu.add(transfertstockMPMenuItem);
            
            
            JMenuItem transfererMPMenuItem = new JMenuItem("Transférer Stock Matière Première Production");
            transfererMPMenuItem.setName("transfererMPMenuItem");
            transfererMPMenuItem.setMnemonic(KeyEvent.VK_A);
            transfertstockMPMenuItem.add(transfererMPMenuItem);
            transfererMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Transférer Stock Matière Première Production");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new TransfererStockMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            JMenuItem ConsultertransfererMPMenuItem = new JMenuItem("Consulter Transférer Stock Matière Première Production");
            ConsultertransfererMPMenuItem.setName("ConsultertransfererMPMenuItem");
            ConsultertransfererMPMenuItem.setMnemonic(KeyEvent.VK_A);
            transfertstockMPMenuItem.add(ConsultertransfererMPMenuItem);
            ConsultertransfererMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Transférer Stock Matière Première Production");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsultationTransfererStockMPProduction();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem TransfereMpProduitFiniItem = new JMenuItem("Transfere Matiere Premiere Produit Fini");
            TransfereMpProduitFiniItem.setName("TransfereMpProduitFiniItem");
            TransfereMpProduitFiniItem.setMnemonic(KeyEvent.VK_A);
            transfertstockMPMenuItem.add(TransfereMpProduitFiniItem);
            TransfereMpProduitFiniItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Transfere Matiere Premiere Produit Fini");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new TransfererMPProduitFini();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterTransfereMpProduitFiniItem = new JMenuItem("Consulter Transfere Matiere Premiere Produit Fini");
            ConsulterTransfereMpProduitFiniItem.setName("ConsulterTransfereMpProduitFiniItem");
            ConsulterTransfereMpProduitFiniItem.setMnemonic(KeyEvent.VK_A);
            transfertstockMPMenuItem.add(ConsulterTransfereMpProduitFiniItem);
            ConsulterTransfereMpProduitFiniItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Transfere Matiere Premiere Produit Fini");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsultationTransfererMPEnPF();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
             situationstockMPMenuItem = new JMenu("Situation Stock Matière Première");
            situationstockMPMenuItem.setName("situationstockMPMenuItem");
            situationstockMPMenuItem.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("situationstockMPMenuItem", situationstockMPMenuItem);
            stockMPMenu.add(situationstockMPMenuItem);
            
            
            
            
            JMenuItem afficherMPMenuItem = new JMenuItem("Afficher Stock MP Par Jour");
            afficherMPMenuItem.setName("afficherMPMenuItem");
            afficherMPMenuItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(afficherMPMenuItem);
            afficherMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Afficher Stock MP Par Jour");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AfficherStockMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            /*
            
            JMenuItem SituationEnVracMPMenuItem = new JMenuItem("Situation Stock En Vrac");
            SituationEnVracMPMenuItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(SituationEnVracMPMenuItem);
            SituationEnVracMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Situation Stock En Vrac");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new SituationStockEnVrac();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
         */
            
            JMenuItem SituationEmballageMPMenuItem = new JMenuItem("Situation Stock MP Par Region");
            SituationEmballageMPMenuItem.setName("SituationEmballageMPMenuItem");
            SituationEmballageMPMenuItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(SituationEmballageMPMenuItem);
            SituationEmballageMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Situation Stock MP Par Region");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new SituationStockEmballage();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
           
            JMenuItem ConsulterMouvementStockMPItem = new JMenuItem("Consulter Mouvement Stock MP");
            ConsulterMouvementStockMPItem.setName("ConsulterMouvementStockMPItem");
            ConsulterMouvementStockMPItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterMouvementStockMPItem);
            ConsulterMouvementStockMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Mouvement Stock MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterMouvementStock();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterEtatStockMPItem = new JMenuItem("Consulter Etat Stock MP");
            ConsulterEtatStockMPItem.setName("ConsulterEtatStockMPItem");
            ConsulterEtatStockMPItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterEtatStockMPItem);
            ConsulterEtatStockMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Etat Stock MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterEtatStock();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem ConsulterEtatStockMPParFournisseurItem = new JMenuItem("Consulter Etat Stock MP Par Fournisseur");
            ConsulterEtatStockMPParFournisseurItem.setName("ConsulterEtatStockMPParFournisseurItem");
            ConsulterEtatStockMPParFournisseurItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterEtatStockMPParFournisseurItem);
            ConsulterEtatStockMPParFournisseurItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Etat Stock MP Par Fournisseur");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterEtatStockParFournisseur();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            JMenuItem ConsulterEtatStockDechetMPItem = new JMenuItem("Consulter Etat Stock Dechet MP");
            ConsulterEtatStockDechetMPItem.setName("ConsulterEtatStockDechetMPItem");
            ConsulterEtatStockDechetMPItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterEtatStockDechetMPItem);
            ConsulterEtatStockDechetMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Etat Stock Dechet MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterEtatStockDechetMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            /*
            JMenuItem ConsulterMouvementStockMPDechetManqueItem = new JMenuItem("Consulter Mouvement Stock Dechet & Manque MP");
            ConsulterMouvementStockMPDechetManqueItem.setName("ConsulterMouvementStockMPDechetManqueItem");
            ConsulterMouvementStockMPDechetManqueItem.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterMouvementStockMPDechetManqueItem);
            ConsulterMouvementStockMPDechetManqueItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Mouvement Stock Dechet & Manque MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterMouvementStockDechetManque();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            */
            
            JMenuItem ConsulterMouvementTransfertStockMP = new JMenuItem("Consulter les Mouvements de Transfert MP");
            ConsulterMouvementTransfertStockMP.setName("ConsulterMouvementTransfertStockMP");
            ConsulterMouvementTransfertStockMP.setMnemonic(KeyEvent.VK_A);
            situationstockMPMenuItem.add(ConsulterMouvementTransfertStockMP);
            ConsulterMouvementTransfertStockMP.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter les Mouvements de Transfert MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterMouvementTransferStockMPEtPF();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            

            
             BonStortieStockMPMenu = new JMenu("Bon Autres Sorties");
            BonStortieStockMPMenu.setName("BonStortieStockMPMenu");
            BonStortieStockMPMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("BonStortieStockMPMenu", BonStortieStockMPMenu);
            stockMPMenu.add(BonStortieStockMPMenu);
            

            JMenuItem BonStortieStockMPItem = new JMenuItem("Bon Autres Sorties");
            BonStortieStockMPItem.setName("BonStortieStockMPItem");
            BonStortieStockMPItem.setMnemonic(KeyEvent.VK_A);
            BonStortieStockMPMenu.add(BonStortieStockMPItem);
            BonStortieStockMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Bon Autres Sorties");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new BonSortieStockMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ConsulterBonStortieStockMPItem = new JMenuItem("Consultation  Bon Entrer / Sortie Stock MP");
            ConsulterBonStortieStockMPItem.setName("ConsulterBonStortieStockMPItem");
            ConsulterBonStortieStockMPItem.setMnemonic(KeyEvent.VK_A);
            BonStortieStockMPMenu.add(ConsulterBonStortieStockMPItem);
            ConsulterBonStortieStockMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consultation  Bon Entrer / Sortie Stock MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterBonSortieStockMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     }); 
            
            
            
            
            
            
             
            
             InventaireMenu = new JMenu("Inventaire");
             InventaireMenu.setName("InventaireMenu");
             InventaireMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("InventaireMenu", InventaireMenu);
            stockMPMenu.add(InventaireMenu); 
            
            JMenuItem AjouterPertMPItem = new JMenuItem("Ajouter Perte MP");
            AjouterPertMPItem.setName("AjouterPertMPItem");
            AjouterPertMPItem.setMnemonic(KeyEvent.VK_A);
            InventaireMenu.add(AjouterPertMPItem);
            AjouterPertMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Perte MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterPerteMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });   
            
            
            JMenuItem ConsulterPertMPItem = new JMenuItem("Consulter Perte MP");
            ConsulterPertMPItem.setName("ConsulterPertMPItem");
            ConsulterPertMPItem.setMnemonic(KeyEvent.VK_A);
            InventaireMenu.add(ConsulterPertMPItem);
            ConsulterPertMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Perte MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterPerteMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });


            JMenuItem AjouterActionPertMPItem = new JMenuItem("Ajouter Action de Perte MP");
            AjouterActionPertMPItem.setName("AjouterActionPertMPItem");
            AjouterActionPertMPItem.setMnemonic(KeyEvent.VK_A);
            InventaireMenu.add(AjouterActionPertMPItem);
            AjouterActionPertMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Action de Perte MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjouterActionPerteMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem ConsulterActionPertMPItem = new JMenuItem("Consulter Actions de Perte MP");
            ConsulterActionPertMPItem.setName("ConsulterActionPertMPItem");
            ConsulterActionPertMPItem.setMnemonic(KeyEvent.VK_A);
            InventaireMenu.add(ConsulterActionPertMPItem);
            ConsulterActionPertMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Actions de Perte MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterActionPerteMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     }); 
            
            
            
             
             JMenuItem AjouterInventaireItem = new JMenuItem("Ajouter Inventaire");
             AjouterInventaireItem.setName("AjouterInventaireItem");
             AjouterInventaireItem.setMnemonic(KeyEvent.VK_A);
             InventaireMenu.add(AjouterInventaireItem);
             AjouterInventaireItem.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent event) {
                                                                 	title.setText("Ajouter Inventaire");
                                                                 	titlePanel.add(title);
                                                                 	contentPane  = new AjouterInventaire1();
                                                                     contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                     generalScrollPane.setViewportView(contentPane);                
                                                                     contentPane.setOpaque(true);
                                                                     

                                                                          }
                                                                      });
             
             
             JMenuItem AjouterInventaire2Item = new JMenuItem("Ajouter Inventaire 2");
             AjouterInventaire2Item.setName("AjouterInventaire2Item");
             AjouterInventaire2Item.setMnemonic(KeyEvent.VK_A);
             InventaireMenu.add(AjouterInventaire2Item);
             AjouterInventaire2Item.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent event) {
                                                                 	title.setText("Ajouter Inventaire 2");
                                                                 	titlePanel.add(title);
                                                                 	contentPane  = new AjouterInventaire2();
                                                                     contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                     generalScrollPane.setViewportView(contentPane);                
                                                                     contentPane.setOpaque(true);
                                                                     

                                                                          }
                                                                      });
             
             
             
             JMenuItem RegularisationInventaireItem = new JMenuItem("Régularisation Inventaire");
             RegularisationInventaireItem.setName("RegularisationInventaireItem");
             RegularisationInventaireItem.setMnemonic(KeyEvent.VK_A);
             InventaireMenu.add(RegularisationInventaireItem);
             RegularisationInventaireItem.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent event) {
                                                                 	title.setText("Régularisation Inventaire");
                                                                 	titlePanel.add(title);
                                                                 	contentPane  = new RegularisationInventaire();
                                                                     contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                     generalScrollPane.setViewportView(contentPane);                
                                                                     contentPane.setOpaque(true);
                                                                     

                                                                          }
                                                                      });
             
            
                     
             
             VenteDechetMenu = new JMenu("Vente Dechet");
             VenteDechetMenu.setName("VenteDechetMenu");
             VenteDechetMenu.setMnemonic(KeyEvent.VK_A);
            mapSousSousMenu.put("VenteDechetMenu", VenteDechetMenu);
            stockMPMenu.add(VenteDechetMenu);    
             
            
            JMenuItem VenteDechetMPItem = new JMenuItem("Vente Dechet MP");
            VenteDechetMPItem.setName("VenteDechetMPItem");
            VenteDechetMPItem.setMnemonic(KeyEvent.VK_A);
            VenteDechetMenu.add(VenteDechetMPItem);
            VenteDechetMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Vente Dechet MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutFactureVenteMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            

            JMenuItem ConsulterVenteDechetMPItem = new JMenuItem("Consulter Vente Dechet MP");
            ConsulterVenteDechetMPItem.setName("ConsulterVenteDechetMPItem");
            ConsulterVenteDechetMPItem.setMnemonic(KeyEvent.VK_A);
            VenteDechetMenu.add(ConsulterVenteDechetMPItem);
            ConsulterVenteDechetMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Vente Dechet MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterFactureMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem ImprimerFactureVenteDechetMPItem = new JMenuItem("Imprimer BL De Vente Dechet MP");
            ImprimerFactureVenteDechetMPItem.setName("ImprimerFactureVenteDechetMPItem");
            ImprimerFactureVenteDechetMPItem.setMnemonic(KeyEvent.VK_A);
            VenteDechetMenu.add(ImprimerFactureVenteDechetMPItem);
            ImprimerFactureVenteDechetMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Imprimer BL De Vente Dechet MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new  ImprimerFactureMP();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem ConsulterSituationCaisseVenteMPItem = new JMenuItem("Consulter Situation Caisse");
            ConsulterSituationCaisseVenteMPItem.setName("ConsulterSituationCaisseVenteMPItem");
            ConsulterSituationCaisseVenteMPItem.setMnemonic(KeyEvent.VK_A);
            VenteDechetMenu.add(ConsulterSituationCaisseVenteMPItem);
            ConsulterSituationCaisseVenteMPItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Situation Caisse");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new  SituationCaisse();
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });   
             
             
             
           
             
             
             
             
            
             
             /*
             JMenuItem ChargeDepotItem = new JMenuItem("Charge Depot");
             ChargeDepotItem.setName("ChargeDepotItem");
             ChargeDepotItem.setMnemonic(KeyEvent.VK_A);
             stockMPMenu.add(ChargeDepotItem);
             ChargeDepotItem.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent event) {
                                                                 	title.setText("Charge Depot");
                                                                 	titlePanel.add(title);
                                                                 	contentPane  = new AjoutChargeDepot();
                                                                     contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                     generalScrollPane.setViewportView(contentPane);                
                                                                     contentPane.setOpaque(true);
                                                                     

                                                                          }
                                                                      });
             
             
           */

                    
            
             
             
             /*Menu Stock produit fini
              * 
              */
            
              stockPFMenu = new JMenu("Stock Produit Fini");
              stockPFMenu.setName("stockPFMenu");
             menubar.add(stockPFMenu);
            
             JMenuItem afficherPFMenuItem = new JMenuItem("Afficher Stock Produit Fini");
             afficherPFMenuItem.setName("afficherPFMenuItem");
             afficherPFMenuItem.setMnemonic(KeyEvent.VK_A);
             stockPFMenu.add(afficherPFMenuItem);
             afficherPFMenuItem.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent event) {
                                                                 	title.setText("Afficher Stock Produit Fini");
                                                                 	titlePanel.add(title);
                                                                 	contentPane  = new AfficherStockPF();
                                                                 	
                                                                     contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                     generalScrollPane.setViewportView(contentPane);                
                                                                     contentPane.setOpaque(true);
                                                                     

                                                                          }
                                                                      });
         
              
             
              JMenuItem sortirPFMenuItem = new JMenuItem("Sortir Stock Produit Fini");
              sortirPFMenuItem.setName("sortirPFMenuItem");
              sortirPFMenuItem.setMnemonic(KeyEvent.VK_A);     
              stockPFMenu.add(sortirPFMenuItem);
              sortirPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Sortir Stock Produit Fini");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new SortirStockPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              JMenuItem ConsulterMouvementStockPFMenuItem = new JMenuItem("Consulter Mouvement Stock PF");
              ConsulterMouvementStockPFMenuItem.setName("ConsulterMouvementStockPFMenuItem");
              ConsulterMouvementStockPFMenuItem.setMnemonic(KeyEvent.VK_A);
              stockPFMenu.add(ConsulterMouvementStockPFMenuItem);
              ConsulterMouvementStockPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Mouvement Stock PF");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterMouvementStockPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              JMenuItem ConsulterEtatStockPFMenuItem = new JMenuItem("Consulter Etat Stock PF");
              ConsulterEtatStockPFMenuItem.setName("ConsulterEtatStockPFMenuItem");
              ConsulterEtatStockPFMenuItem.setMnemonic(KeyEvent.VK_A);
              stockPFMenu.add(ConsulterEtatStockPFMenuItem);
              ConsulterEtatStockPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Etat Stock PF");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterEtatStockPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              
            
            
              /*Menu Statistique
               * 
               */
              
              StatistiqueMenu = new JMenu("Statistique");
              StatistiqueMenu.setName("StatistiqueMenu");
                menubar.add(StatistiqueMenu);      
        
                SituationManqueDechetMenu = new JMenu("Situation Manque et Déchet");
                SituationManqueDechetMenu.setName("SituationManqueDechetMenu");
                SituationManqueDechetMenu.setMnemonic(KeyEvent.VK_A);
                mapSousSousMenu.put("SituationManqueDechetMenu", SituationManqueDechetMenu);
                StatistiqueMenu.add(SituationManqueDechetMenu); 
                

                
                
                
                /*
                JMenuItem ConsulterManquePlusDechetEmballageMenuItem = new JMenuItem("Consulter Manque Plus Dechet Emballage");
                ConsulterManquePlusDechetEmballageMenuItem.setName("ConsulterManqueDechetFournisseurMenuItem");
                ConsulterManquePlusDechetEmballageMenuItem.setMnemonic(KeyEvent.VK_A);
                SituationManqueDechetMenu.add(ConsulterManquePlusDechetEmballageMenuItem);
                ConsulterManquePlusDechetEmballageMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Manque Plus Dechet Emballage");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  ConsulterManquePlusDechetEmballage();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                JMenuItem ConsulterManquePlusEnVracMenuItem = new JMenuItem("Consulter Manque Plus En Vrac");
                ConsulterManquePlusEnVracMenuItem.setName("ConsulterManquePlusEnVracMenuItem");
                ConsulterManquePlusEnVracMenuItem.setMnemonic(KeyEvent.VK_A);
                SituationManqueDechetMenu.add(ConsulterManquePlusEnVracMenuItem);
                ConsulterManquePlusEnVracMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Manque Plus En Vrac");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  ConsulterManquePlusEnVrac();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                
                JMenuItem ConsulterSituationDechetManqueMenuItem = new JMenuItem("Consulter Situation Manque Et Dechet");
                ConsulterSituationDechetManqueMenuItem.setName("ConsulterSituationDechetManqueMenuItem");
                ConsulterSituationDechetManqueMenuItem.setMnemonic(KeyEvent.VK_A);
                SituationManqueDechetMenu.add(ConsulterSituationDechetManqueMenuItem);
                ConsulterSituationDechetManqueMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Situation Manque Et Dechet");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationDechetManqueParJour();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                */
                
                JMenuItem ConsulterSituationManquePlusEtMoinsEnVracMenuItem = new JMenuItem("Consulter Situation Manque Et Plus Et Dechet En Vrac");
                ConsulterSituationManquePlusEtMoinsEnVracMenuItem.setName("ConsulterSituationManquePlusEtMoinsEnVracMenuItem");
                ConsulterSituationManquePlusEtMoinsEnVracMenuItem.setMnemonic(KeyEvent.VK_A);
                SituationManqueDechetMenu.add(ConsulterSituationManquePlusEtMoinsEnVracMenuItem);
                ConsulterSituationManquePlusEtMoinsEnVracMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Situation Manque Et Plus Et Dechet En Vrac");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationManquePlusManqueMoinsEnVrac();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });  
                
                JMenuItem ConsulterSituationManquePlusEtMoinsEmballageMenuItem = new JMenuItem("Consulter Situation Manque Et Dechet Emballage");
                ConsulterSituationManquePlusEtMoinsEmballageMenuItem.setName("ConsulterSituationManquePlusEtMoinsEmballageMenuItem");
                ConsulterSituationManquePlusEtMoinsEmballageMenuItem.setMnemonic(KeyEvent.VK_A);
                SituationManqueDechetMenu.add(ConsulterSituationManquePlusEtMoinsEmballageMenuItem);
                ConsulterSituationManquePlusEtMoinsEmballageMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Situation Manque Et Dechet Emballage");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationManquePlusManqueMoinsEtDechetEmballage();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
        
     
     

                CoutProductionMenu = new JMenu("Cout Production");
                CoutProductionMenu.setName("CoutProductionMenu");
                CoutProductionMenu.setMnemonic(KeyEvent.VK_A);
                mapSousSousMenu.put("CoutProductionMenu", CoutProductionMenu);
                StatistiqueMenu.add(CoutProductionMenu); 
                
                
                JMenuItem detailOrdreFabricationMenuItem = new JMenuItem("Cout Par OF");
                detailOrdreFabricationMenuItem.setName("detailOrdreFabricationMenuItem");
                detailOrdreFabricationMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(detailOrdreFabricationMenuItem);
                detailOrdreFabricationMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Cout Par OF");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new DetailOF();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                
               
                
                JMenuItem CoutParArticleMenuItem = new JMenuItem("Detail Cout Par Article");
                CoutParArticleMenuItem.setName("CoutParArticleMenuItem");
                CoutParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(CoutParArticleMenuItem);
                CoutParArticleMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Detail Cout Par Article");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new CoutParArticle();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                JMenuItem CoutProductionParArticleMenuItem = new JMenuItem("Liste Des Couts Par Article");
                CoutProductionParArticleMenuItem.setName("CoutProductionParArticleMenuItem");
                CoutProductionParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(CoutProductionParArticleMenuItem);
                CoutProductionParArticleMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Liste Des Couts Par Article");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new CoutProductionParArticle();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                JMenuItem listDetailOrdreFabricationMenuItem = new JMenuItem("Liste Cout Article Par OF");
                listDetailOrdreFabricationMenuItem.setName("listDetailOrdreFabricationMenuItem");
                listDetailOrdreFabricationMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(listDetailOrdreFabricationMenuItem);
                listDetailOrdreFabricationMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Liste Cout Article Par OF");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ListDetailOrdreFabrication();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                

                JMenuItem SituationGlobaleCoutProductionMenuItem = new JMenuItem("Situation Globale Cout production");
                SituationGlobaleCoutProductionMenuItem.setName("SituationGlobaleCoutProductionMenuItem");
                SituationGlobaleCoutProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(SituationGlobaleCoutProductionMenuItem);
                SituationGlobaleCoutProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Situation Globale Cout production");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new SituationGlobaleCoutArticle();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                JMenuItem ConsulterSituationProgrammeProductionMenuItem = new JMenuItem("Consulter Cout Machine");
                ConsulterSituationProgrammeProductionMenuItem.setName("ConsulterSituationProgrammeProductionMenuItem");
                ConsulterSituationProgrammeProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(ConsulterSituationProgrammeProductionMenuItem);
                ConsulterSituationProgrammeProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Cout Machine");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationProgrammeProduction();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                
                JMenuItem ChargeVariableProductionMenuItem = new JMenuItem("Saisir Charge Production Variable");
                ChargeVariableProductionMenuItem.setName("ChargeVariableProductionMenuItem");
                ChargeVariableProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(ChargeVariableProductionMenuItem);
                ChargeVariableProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Saisir Charge Production Variable");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new AjoutChargeVariableProd();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                
                JMenuItem ChargeFixeProductionMenuItem = new JMenuItem("Saisir Charge Production Fixe");
                ChargeFixeProductionMenuItem.setName("ChargeFixeProductionMenuItem");
                ChargeFixeProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(ChargeFixeProductionMenuItem);
                ChargeFixeProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Saisir Charge Production Fixe");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new AjoutChargeFixeProd();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                JMenuItem CoutProductionMenuItem = new JMenuItem("Cout Production");
                CoutProductionMenuItem.setName("CoutProductionMenuItem");
                CoutProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                CoutProductionMenu.add(CoutProductionMenuItem);
                CoutProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Cout Production");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new CoutProduction();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
               
                
                StatistiquesMenu = new JMenu("STATISTIQUES");
                StatistiquesMenu.setName("StatistiquesMenu");
                StatistiquesMenu.setMnemonic(KeyEvent.VK_A);
                mapSousSousMenu.put("StatistiquesMenu", StatistiquesMenu);
                StatistiqueMenu.add(StatistiquesMenu);          
               
                
                
                JMenuItem listeProduitNonFabriquerMenuItem = new JMenuItem("Liste Des Pruits Fini Non Fabriqué");
                listeProduitNonFabriquerMenuItem.setName("listeProduitNonFabriquerMenuItem");
                listeProduitNonFabriquerMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(listeProduitNonFabriquerMenuItem);
                listeProduitNonFabriquerMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Liste Des Pruits Fini Non Fabriqué");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ListePFNonFabrique();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });  
                
            
                
                JMenuItem StatistiqueEnVracConsommeMenuItem = new JMenuItem("Statistique En Vrac Consommé");
                StatistiqueEnVracConsommeMenuItem.setName("StatistiqueEnVracConsommeMenuItem");
                StatistiqueEnVracConsommeMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(StatistiqueEnVracConsommeMenuItem);
                StatistiqueEnVracConsommeMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Statistique En Vrac Consommé");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  StatistiqueEnVracConsomme();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                JMenuItem SituationProductionTotalParArticlePFMenuItem = new JMenuItem("Situation Production Total Par Article PF");
                SituationProductionTotalParArticlePFMenuItem.setName("SituationProductionTotalParArticlePFMenuItem");
                SituationProductionTotalParArticlePFMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(SituationProductionTotalParArticlePFMenuItem);
                SituationProductionTotalParArticlePFMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Situation Production Total Par Article PF");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationProductionTotalParArticlePF();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                
                JMenuItem EnVracUtiliserMenuItem = new JMenuItem("% EN Vrac Utiliser Par Article");
                EnVracUtiliserMenuItem.setName("EnVracUtiliserMenuItem");
                EnVracUtiliserMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(EnVracUtiliserMenuItem);
                EnVracUtiliserMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("% EN Vrac Utiliser Par Article");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  StatistiqueEnVracUtiliserLorsDeLaProductionPF();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });
                
                
                JMenuItem SituationPFParAnneeMenuItem = new JMenuItem("Situation PF Par Annee");
                SituationPFParAnneeMenuItem.setName("SituationPFParAnneeMenuItem");
                SituationPFParAnneeMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(SituationPFParAnneeMenuItem);
                SituationPFParAnneeMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Situation PF Par Annee");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  SituationPFParAnnee();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });   
                
                
                
                JMenuItem TonnageProductionFabriqueeParJourMenuItem = new JMenuItem("Tonnage Fabriquee Par Jour");
                TonnageProductionFabriqueeParJourMenuItem.setName("TonnageProductionFabriqueeParJourMenuItem");
                TonnageProductionFabriqueeParJourMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(TonnageProductionFabriqueeParJourMenuItem);
                TonnageProductionFabriqueeParJourMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Tonnage Fabriquee Par Jour");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  TonnageFabriqueeParJour();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                
                
                JMenuItem StatistiqueFinanciereMPMenuItem = new JMenuItem("Consulter Statistiaue Financiere MP");
                StatistiqueFinanciereMPMenuItem.setName("StatistiqueFinanciereMPMenuItem");
                StatistiqueFinanciereMPMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(StatistiqueFinanciereMPMenuItem);
                StatistiqueFinanciereMPMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Statistiaue Financiere MP");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  StatistiqueFinanciereDesMP();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         }); 
                 
                
                
                
                
                
                /*
                JMenuItem SituationGlobalDetailOrdreFabricationParPFMenuItem = new JMenuItem("Situation Global Detail Ordre Fabrication Par PF");
                SituationGlobalDetailOrdreFabricationParPFMenuItem.setName("SituationGlobalDetailOrdreFabricationParPFMenuItem");
                SituationGlobalDetailOrdreFabricationParPFMenuItem.setMnemonic(KeyEvent.VK_A);
                StatistiquesMenu.add(SituationGlobalDetailOrdreFabricationParPFMenuItem);
                SituationGlobalDetailOrdreFabricationParPFMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Situation Global Detail Ordre Fabrication Par PF");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new  DetailOFParArticle();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });    
                
                
                
                */
                
                
           
               
               
               
               JMenuItem ProductionArticleParMoisMenuItem = new JMenuItem("Consommation MP Par Mois");
               ProductionArticleParMoisMenuItem.setName("ProductionArticleParMoisMenuItem");
               ProductionArticleParMoisMenuItem.setMnemonic(KeyEvent.VK_A);
               StatistiquesMenu.add(ProductionArticleParMoisMenuItem);
               ProductionArticleParMoisMenuItem.addActionListener(new ActionListener() {
                                                                   public void actionPerformed(ActionEvent event) {
                                                                   	title.setText("Consommation MP Par Mois");
                                                                   	titlePanel.add(title);
                                                                   	contentPane  = new ProductionEnvracParMois();
                                                                   	
                                                                       contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                       generalScrollPane.setViewportView(contentPane);                
                                                                       contentPane.setOpaque(true);
                                                                       

                                                                            }
                                                                        }); 
               
               
               JMenuItem ListMPNonUtilseesMenuItem = new JMenuItem("Liste Des MP Non Utilisees");
               ListMPNonUtilseesMenuItem.setName("ListMPNonUtilseesMenuItem");
               ListMPNonUtilseesMenuItem.setMnemonic(KeyEvent.VK_A);
               StatistiquesMenu.add(ListMPNonUtilseesMenuItem);
               ListMPNonUtilseesMenuItem.addActionListener(new ActionListener() {
                                                                   public void actionPerformed(ActionEvent event) {
                                                                   	title.setText("Liste Des MP Non Utilisees");
                                                                   	titlePanel.add(title);
                                                                   	contentPane  = new ListeMPNonUtiliser();
                                                                   	
                                                                       contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                       generalScrollPane.setViewportView(contentPane);                
                                                                       contentPane.setOpaque(true);
                                                                       

                                                                            }
                                                                        }); 
                     
                
                /*
                
               JMenuItem AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem = new JMenuItem("Consulter Comparaison Valeur Annee Actuel Et Annee Prochaine");
               AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem.setName("AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem");
               AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem.setMnemonic(KeyEvent.VK_A);
               StatistiquesMenu.add(AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem);
               AfficherEtatComparaisonPrixAnneeActuelAvecAnneProchaineMenuItem.addActionListener(new ActionListener() {
                                                                   public void actionPerformed(ActionEvent event) {
                                                                   	title.setText("Consulter Comparaison Valeur Annee Actuel Et Annee Prochaine");
                                                                   	titlePanel.add(title);
                                                                   	contentPane  = new AfficherEtatComparaisonPrixAnneeActuelAvecAnneeProchaine();
                                                                   	
                                                                       contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                       generalScrollPane.setViewportView(contentPane);                
                                                                       contentPane.setOpaque(true);
                                                                       

                                                                            }
                                                                        }); 
                
               
                */
                
                
              
                
              
                
        
       





        
        
        /* Menu Fournisseur 
         * 
         */
        /*  JMenu fournisseurMenu = new JMenu("Fournisseur");
        menubar.add(fournisseurMenu);
        JMenuItem fournisseurItem = new JMenuItem("Ajouter Fournisseur");
        fournisseurItem.setMnemonic(KeyEvent.VK_A);
        fournisseurMenu.add(fournisseurItem);
        fournisseurItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Fournisseur");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterFournisseur();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
        JMenuItem fournisseur2Item = new JMenuItem("Chercher Fournisseur");
        fournisseurItem.setMnemonic(KeyEvent.VK_A);
        fournisseurMenu.add(fournisseur2Item);
        fournisseur2Item.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Fournisseur");
                                                            	titlePanel.add(title);
                                                            	//contentPane  = new ajouterClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
      
      Menu commande 
        JMenu commandeMenu = new JMenu("Commande");
        menubar.add(commandeMenu);
        JMenuItem commandeItem = new JMenuItem("Ajouter Commande");
        commandeItem.setMnemonic(KeyEvent.VK_A);
        commandeMenu.add(commandeItem);
        commandeItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Commande");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });*/
        
       
        

        
        
         
        
        setJMenuBar(menubar);
        
        autoriseMenuUtilisateur();
        
      //  depotMenu.setVisible(false);
    }
 
 public void moveToTheNewWindow() {
		this.dispose();
		AuthentificationView authentification = new AuthentificationView();
		authentification.setVisible(true);
		authentification.setLocationRelativeTo(null);
	}
 
 void autoriseMenuUtilisateur(){
	/*
	 List<Habilitation> listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 

	 boolean trouveMenu=false;
	 boolean trouveSousMenu=false;
	 boolean trouveSousSousmenu=false;
	for(int t=0;t<menubar.getMenuCount();t++) 
	{
		if(menubar.getMenu(t)!=null)
		{
		
		  trouveMenu=false;
		  trouveSousMenu=false;
	
	Menu menu=menuDAO.findByCode(menubar.getMenu(t).getName())	 ; 
	
	
		
	 if(menu!=null)
	 {
		 trouveMenu=true; 
	 }
		 
		
	 
	 if(trouveMenu==false)
	 {
		 if(menubar.getMenu(t).getName()!=null)
		 {
				Menu AddMenu=new Menu(); 
				AddMenu.setCode(menubar.getMenu(t).getName());
				AddMenu.setLibelle("Menu "+menubar.getMenu(t).getText());
				AddMenu.setType("M");
				menuDAO.add(AddMenu);
		 }
	
		 
	 }
	
	 for(int d=0;d<menubar.getMenu(t).getItemCount();d++) 
		{
		 
		 
		 if(menubar.getMenu(t).getItem(d)!=null)
		 {
			 trouveSousMenu=false; 
			 
			
			 
			 Menu Smenu=menuDAO.findByCode(menubar.getMenu(t).getItem(d).getName())	 ; 
				
					
				 if(Smenu!=null){
					
					 trouveSousMenu=true;
					}
				 
				 if(trouveSousMenu==false)
				 {
					 if(menubar.getMenu(t).getItem(d).getName()!=null)
					 {
						 Menu AddMenu=new Menu(); 
							AddMenu.setCode(menubar.getMenu(t).getItem(d).getName());
							AddMenu.setCodeParent(menubar.getMenu(t).getName());
							AddMenu.setLibelle(menubar.getMenu(t).getItem(d).getText());
							AddMenu.setType("SM");
							menuDAO.add(AddMenu); 
					 }
				
					 
					
					 
				 }	
				 
				 
				 
					for(int s=0;s< mapSousSousMenu.size();s++) 
					{
						
						
					JMenu SousSousMenu=mapSousSousMenu.get(menubar.getMenu(t).getItem(d).getName());	
						
					if(SousSousMenu!=null)
					{
						 for(int n=0;n<SousSousMenu.getItemCount();n++) 
							{
							 
							 
							 if(SousSousMenu.getItem(n)!=null)
							 {
								 trouveSousSousmenu=false; 
								 
								
								 
								 Menu SSmenu=menuDAO.findByCode(SousSousMenu.getItem(n).getName())	 ; 
									
										
									 if(SSmenu!=null){
										
										 trouveSousSousmenu=true;
										}
									 
									 if(trouveSousSousmenu==false)
									 {
										 if(SousSousMenu.getItem(n).getName()!=null)
										 {
											 Menu AddMenu=new Menu(); 
												AddMenu.setCode(SousSousMenu.getItem(n).getName());
												AddMenu.setCodeParent(SousSousMenu.getName());
												AddMenu.setLibelle(SousSousMenu.getItem(n).getText());
												AddMenu.setType("SM");
												menuDAO.add(AddMenu); 
										 }
									
										 
										
										 
									 }	
									 
									 
								 
							 }
							 
							 
							}
						
					}
					
					
						
						
						
						
						
					}	 
				 
				 
				 
		
				 
				 
				 
			 
			 
			 
			 
		 }
		 
		 
		}
		
	 
	 
	 
	 
	 
	 
	
		
		}
		
		
		
	}
	
	*/
 
	 
	List<Habilitation> listHabilitationTmp=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 
	 
	 
	
		
	 
		for( int c=0;c<listHabilitationTmp.size();c++){
		 mapHabilitation.put(listHabilitationTmp.get(c).getMenu().getCode(), listHabilitationTmp.get(c).isAutorise());
		 mapMenu.put(c, listHabilitationTmp.get(c).getMenu().getCode());
		
		}	
		
String codeMenu="";		
	 
	 
	 for(int i=0;i<mapHabilitation.size();i++){
		 
		for(int y=0;y<menubar.getMenuCount();y++)
		{
		
			if(menubar.getMenu(y)!=null)
			{
				if(mapHabilitation.containsKey( menubar.getMenu(y).getName()))
				{
					
					
						menubar.getMenu(y).setVisible(mapHabilitation.get( menubar.getMenu(y).getName()));
				
					
					
					
					
				}
				
				
				
			}
			
			
			
			
		}
		 
	
    	 
	 }
	 
	 
	 for(int i=0;i<mapHabilitation.size();i++){
		 
		for(int y=0;y<menubar.getMenuCount();y++)
		{
		
			if(menubar.getMenu(y)!=null)
			{
				
			for(int d=0;d<menubar.getMenu(y).getItemCount();d++)	
			{
				
				
				if(mapHabilitation.containsKey( menubar.getMenu(y).getItem(d).getName()))
				{
					
					
					menubar.getMenu(y).getItem(d).setVisible(mapHabilitation.get( menubar.getMenu(y).getItem(d).getName()));
				
					
					
					
					
				}
				
				
				
				
				for(int s=0;s<mapSousSousMenu.size();s++)
				{
				
					JMenu SousSousMenu=mapSousSousMenu.get( menubar.getMenu(y).getItem(d).getName());	
						if(SousSousMenu!=null)
						{
							
							for(int n=0;n<SousSousMenu.getItemCount();n++)	
							{
								
								if(SousSousMenu.getItem(n)!=null)
								{
									if(mapHabilitation.containsKey( SousSousMenu.getItem(n).getName()))
									{
										
										
										SousSousMenu.getItem(n).setVisible(mapHabilitation.get(SousSousMenu.getItem(n).getName()));
									
										
										
										
										
									}
								}
							
								
								
							}
							
						}
				
						
						
						
						
						
					
					
					
					
					
				}
				
				
				
				
				
				
			}
			
			
			
			
			
				
				
				
				
				
			}
			
			
			
			
		}
		 
	
    	 
	 }
	 
	 
	 
	

	
	 
	 
	 
	 
	 
	 
	 
 

	 
 }
}



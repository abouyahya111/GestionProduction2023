package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import dao.daoImplManager.ActionPerteMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailActionPerteMPDAOImpl;
import dao.daoImplManager.DetailPerteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.InventaireDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PerteMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ActionPerteMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailActionPerteMPDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.InventaireDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.PerteMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.ActionPerteMP;
import dao.entity.BonDePerte;
import dao.entity.CategorieMp;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailActionPerteMP;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Inventaire;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.PerteMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class AjouterInventaire2 extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();

	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	private Map<String, BigDecimal> mapPrixMP = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	
	private Map<String, MatierePremier> mapNomMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	
	private Map< String, Integer> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<StockMP> listStockMP=new ArrayList<StockMP>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private List<BonDePerte> listBonDePerte=new ArrayList<BonDePerte>();
	private List<Inventaire> listInventaire=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaire2=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaireAfficher=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaireVerifier=new ArrayList<Inventaire>();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private Utilisateur utilisateur;
	private MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categoriempdao;
	private FournisseurMPDAO fournisseurMPDAO;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMP = new JComboBox();
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox combofournisseur = new JComboBox();
	private InventaireDAO inventaireDAO;
	JDateChooser dateoperation = new JDateChooser();
	private List <Object[]> listeObjectStockFinale=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitial=new ArrayList<Object[]>();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	JButton btnValider = new JButton("Valider");
	private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
	private List<DetailActionPerteMP> listDetailAcionPerteMP=new ArrayList<DetailActionPerteMP>();
	private DetailActionPerteMPDAO detailActionPerteMPDAO;
	private List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	JComboBox comboMagasinDechet = new JComboBox();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	 private CompteMagasinierDAO compteMagasinierDAO;
	 private TransferStockMPDAO transferStockMPDAO;
		private ActionPerteMPDAO ActionPerteMPDAO;
		 private PerteMPDAO PerteMPDAO;
		 private DetailPerteMPDAO detailPerteMPDAO;
		 
		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 
		 
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
			private List <Object[]> Mindate=new ArrayList<Object[]>();
			private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
			private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
			private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
			private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
			private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();

		 
		 
		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 
		 
		 
		 
		 
		 
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterInventaire2() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1453, 716);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	inventaireDAO=new InventaireDAOImpl();
        	detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
        	detailActionPerteMPDAO=new DetailActionPerteMPDAOImpl();
        	compteMagasinierDAO=new CompteMagasinierDAOImpl();
        	  transferStockMPDAO=new TransferStockMPDAOImpl();
        	  ActionPerteMPDAO=new ActionPerteMPDAOImpl();
        		PerteMPDAO=new PerteMPDAOImpl();
        		detailPerteMPDAO=new DetailPerteMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyTyped(KeyEvent e) {

				  		     	
				  		     	}
				  		     	@Override
				  		     	public void keyReleased(KeyEvent e) {
				  		     						  		     		
					  		     	  int key = e.getKeyCode();
					                    if (key == KeyEvent.VK_ENTER) {
					                    	
	if(table.getSelectedRow()!=table.getRowCount() && table.getSelectedRow()!=0)
	{
		
		 table.setValueAt("", table.getSelectedRow(), 7);
		 
		 
		 
	}
	
	
	
					                      
					                    }
					  		     		
				  		     	}
				  		     });
				  		     table.setShowVerticalLines(false);
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
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		 table.getTableHeader().setEnabled(false);
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 231, 1332, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1142, 121);
				  		     	add(layeredPane);
				  		     	comboMagasinDechet = new JComboBox();
				  		  		comboMagasinDechet.setBounds(598, 37, 208, 24);
				  		  		layeredPane.add(comboMagasinDechet);
				  		     	
					  		      if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
					  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
					  	    		comboDepot.addItem(depot.getLibelle());
					  	    		
					  	    		
					  	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
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
						  		      
						  		      
						  			listMagasinDechetMP = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP); 
						  		      
						  		    if(listMagasinDechetMP!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasinDechetMP.size())
							  		      		{	
							  		      			Magasin magasin=listMagasinDechetMP.get(j);
							  		      		comboMagasinDechet.addItem(magasin.getLibelle());
							  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }	      
						  		      
						  		      
						  		      
						  		      
					  	    }else {
					  	    	
					  	    	listDepot = depotDAO.findAll();	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepot.put(depot.getLibelle(), i);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  	    	
					  	    }
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 Depot depot=new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =listDepot.get(mapDepot.get(comboDepot.getSelectedItem()));
								  		       
						  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		     	  	listMagasinDechetMP = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
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
								  		      
								  		      
								  		    if(listMagasinDechetMP!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasinDechetMP.size())
									  		      		{	
									  		      			Magasin magasin=listMagasinDechetMP.get(j);
									  		      		comboMagasinDechet.addItem(magasin.getLibelle());
									  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
								  		      
								  		      
								  		      
								  		      
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 202, 1150, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 37, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(69, 37, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(223, 37, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(290, 38, 191, 24);
				  		     	 layeredPane.add(comboMagasin);
				  		     	
		 btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				listBonDePerte.clear();
				listInventaireAfficher.clear();
				
			 
				
				 
				try {
					
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					boolean casevide=false;
					//////////////////////////////////////////////////////////////// verifier les quantites si en chiffre ////////////////////////////////////////			
								for(int i=0; i<table.getRowCount(); i++){
									
									
									
									if(!table.getValueAt(i, 7).toString().equals(""))
									{
										
										
									BigDecimal quantite=new BigDecimal(table.getValueAt(i, 7).toString());
									
									
									table.setValueAt(quantite, i, 7);
									
									
											
										}else
										{
											
											casevide=true;	
										}
										
									}
								
								if(casevide==true)
								{
									
									
									JOptionPane.showMessageDialog(null, "Une ou Plusieurs Case est vide Veuillez entrer la Quantite SVP !!!!");
									return;
									
								}
									
	//////////////////////////////////////////////////////////////////////////////////////////// verifier les ®Perte et Actions Perte Si Valider //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								
								Magasin magasindechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
								if(magasindechet==null)
								{
									
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
									return;
									
								}
								
				 	 			PerteMP perteMPIvalider=PerteMPDAO.findByDateByMagasin (dateoperation.getDate(), magasindechet, ETAT_INVALIDER);
				 	 			if(perteMPIvalider!=null)
								{
									
									JOptionPane.showMessageDialog(null, "Veuillez Valider les Perte MP SVP !!!!");
									return;
									
								}
								
								
								
							
								
								
								PerteMP perteMPValider=PerteMPDAO.findByDateByMagasin (dateoperation.getDate(), magasindechet, ETAT_VALIDER);
								
								
								
								ActionPerteMP  ActionPerteMPValider=ActionPerteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasindechet, ETAT_VALIDER);
								
								if(perteMPValider!=null && ActionPerteMPValider==null)
								{
									JOptionPane.showMessageDialog(null, "Veuillez Valider l'Action de Perte MP SVP !!!!");
									return;
								}
								
								if(perteMPValider==null && ActionPerteMPValider!=null)
								{
									JOptionPane.showMessageDialog(null, "Veuillez Valider le Perte MP SVP !!!!");
									return;
								}
								
								
							
								
								
								boolean existe=false;
								
								for(int i=0; i<table.getRowCount(); i++){
								
								String codeMP = table.getValueAt(i, 0).toString();
								
								MatierePremier matierePremier=mapCodeMP.get(codeMP);
								
								
								if(!table.getValueAt(i, 7).toString().equals(""))
								{
									FournisseurMP fournisseurMP=null;
									Inventaire inventaire=null;
									if(!table.getValueAt(i, 2).toString().equals(""))
									{
										
										
										fournisseurMP= fournisseurMPDAO.findByCode(table.getValueAt(i, 2).toString());
										 inventaire=inventaireDAO.findByDateByMagasinByMPByFournisseur(magasin, dateoperation.getDate(), matierePremier,fournisseurMP,Constantes.CODE_INVENTAIRE_2,Constantes.ETAT_VALIDER);
										
										 if(inventaire==null)
											{
											 
											 inventaire=inventaireDAO.findByDateByMagasinByMPByFournisseur(magasin, dateoperation.getDate(), matierePremier,fournisseurMP,Constantes.CODE_INVENTAIRE_2,Constantes.ETAT_INVALIDER);

											 
											}
									
									
									}else
									{
										
										
										 inventaire=inventaireDAO.findByDateByMagasinByMP(magasin, dateoperation.getDate(), matierePremier,Constantes.CODE_INVENTAIRE_2,Constantes.ETAT_VALIDER);
									
										 if(inventaire==null)
											{
											 
											 inventaire=inventaireDAO.findByDateByMagasinByMP(magasin, dateoperation.getDate(), matierePremier,Constantes.CODE_INVENTAIRE_2,Constantes.ETAT_INVALIDER);

											 
											}
									
									
									}
									
								
									if(inventaire==null)
									{
										
										existe=true;
										Inventaire inventaireTmp=new Inventaire();
										inventaireTmp.setDateoperation(dateoperation.getDate());
										inventaireTmp.setMagasin(magasin);
										inventaireTmp.setMatierePremier(matierePremier);
										inventaireTmp.setCodeInventaire(Constantes.CODE_INVENTAIRE_2);
										if(fournisseurMP!=null)
										{
											inventaireTmp.setFournisseurMP(fournisseurMP);
										}
										/*
										if(!table.getValueAt(i, 7).toString().equals(""))
										{
											inventaireTmp.setQuantitePerte(new BigDecimal(table.getValueAt(i, 7).toString()));
										}else
										{
											inventaireTmp.setQuantitePerte(BigDecimal.ZERO);

										}
										*/
										
									////////////////////////////////////////////////////////
										
										inventaireTmp.setQuantitePerte(BigDecimal.ZERO);
										
									///////////////////////////////////////////////////////////	
										
										inventaireTmp.setQuantite(new BigDecimal(table.getValueAt(i, 7).toString()));
										inventaireTmp.setQuantiteStock (listInventaireVerifier.get(i).getQuantiteStock());
										inventaireTmp.setDepot(magasin.getDepot());
										inventaireTmp.setEtat(ETAT_INVALIDER);
										inventaireTmp.setDateSaisir(new Date());
										inventaireTmp.setMagasindechet(listInventaireVerifier.get(i).getMagasindechet());
										inventaireTmp.setCreerPar(utilisateur);
										inventaireDAO.add(inventaireTmp);
										listInventaireAfficher.add(inventaireTmp);
										
										


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

									
										
										
										
										
			
										
										
									}
									
								}
								
								}
							
								if(existe==true)
								{
									
									for(int i=0;i<listInventaire.size();i++)
									{
										Inventaire inventaire1=listInventaire.get(i);				
										inventaire1.setEtat(ETAT_VALIDER);	
										inventaireDAO.edit(inventaire1);
									}
									
									
////////////////////////////////////////////////////////////////////////////   Valider Les Actions de perte  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////									
								
									
									
									if(perteMPValider!=null && ActionPerteMPValider!=null)
									{
										
										listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMPValider);	
										
										TransferStockMP transfererStockMP=new TransferStockMP();	
										
										 for(int i=0;i<listDetailPerteMP.size();i++)
								 	 		{
								 	 		listDetailAcionPerteMP.clear();
								 	 		DetailPerteMP detailPerteMP=listDetailPerteMP.get(i);
							 	 			
							 	 			listDetailAcionPerteMP=detailActionPerteMPDAO.listeDetailActionPerteMPByDetailPerteMP(detailPerteMP);	
								 	 		
								 	 		for(int j=0;j<listDetailAcionPerteMP.size();j++)
								 	 		{
								 	 			
								 	 		DetailActionPerteMP detailActionPerteMP=	listDetailAcionPerteMP.get(j);
								 	 			
								 	 	
								 	 			
								 	 		
								 	 				
								 	 				if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 				{
								 	 					
								 	 					
								 	 						
								 	 						if(detailActionPerteMP.getActionMP().getAction().equals(TRANSFERT_MAGASIN_DECHET))
								 	 						{
								 	 							
								 	 					
								 	 						
								 	 		
								 	 		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								 	 						
								 	 						
								 	 						DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
								 	 						detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 						if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 						{
								 	 							detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 						}else
								 	 						{
								 	 							if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 							{
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 								}
								 	 								
								 	 							}else
								 	 							{
								 	 								
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 								}
								 	 							}
								 	 							

								 	 							
								 	 						}
								 	 						
								 	 						detailTransferStockMP.setFournisseur(detailActionPerteMP.getFournisseurMP());	
								 	 						detailTransferStockMP.setMagasinSouce(magasin);
								 	 						detailTransferStockMP.setMagasinDestination(magasindechet);
								 	 						detailTransferStockMP.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 						detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
								 	 						detailTransferStockMP.setTransferStockMP(transfererStockMP);
								 	 						listDetailTransfertStockMP.add(detailTransferStockMP);
								 	 					
								 	 						
								 	 						
								 	 						/*
								 	 						listDetailCompteMagasinier.clear();
								 	 						
								 	 						CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
								 	 						DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
								 	 						detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
								 	 						detailCompteMagasinier.setDepot(detailActionPerteMP.getActionperteMP().getMagasin().getDepot());
								 	 						detailCompteMagasinier.setMagasin(detailActionPerteMP.getActionperteMP().getMagasin());
								 	 						detailCompteMagasinier.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 					
								 	 						detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
								 	 						
								 	 						
								 	 						if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 						{
								 	 							if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 							{
								 	 								detailCompteMagasinier.setPrix(detailActionPerteMP.getMatierePremier().getPrix());
								 	 								
								 	 							}else
								 	 							{
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailCompteMagasinier.setPrix(BigDecimal.ZERO);
								 	 								}
								 	 								
								 	 								
								 	 							}
								 	 							

								 	 							detailCompteMagasinier.setFournisseurMP(detailActionPerteMP.getFournisseurMP());
								 	 							
								 	 						}else
								 	 						{

								 	 							if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 							{
								 	 								detailCompteMagasinier.setPrix(detailActionPerteMP.getMatierePremier().getPrix());
								 	 								
								 	 							}else
								 	 							{
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMP (detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailCompteMagasinier.setPrix(BigDecimal.ZERO);
								 	 								}
								 	 								
								 	 								
								 	 							}
								 	 							

								 	 						
								 	 							
								 	 						
								 	 							
								 	 						}
								 	 						
								 	 						detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
								 	 						
								 	 						detailCompteMagasinier.setDateoperation(detailActionPerteMP.getActionperteMP().getDateoperation());
								 	 						detailCompteMagasinier.setDesignation(TYPE_PERTE);
								 	 						compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
								 	 						listDetailCompteMagasinier.add(detailCompteMagasinier);	 	 					
								 	 						compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
								 	 						compteMagasinierDAO.edit(compteMagasinier);
								 	 						
								 	 						*/
								 	 						
								 	 						
								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					

								 	 						
								 	 						
								 	 						
								 	 							
								 	 							
								 	 						}else if(detailActionPerteMP.getActionMP().getAction().equals(AVANCE_SUR_MAGASINIER))
								 	 						{
								 	 							
								 	 							
								 	 							listDetailCompteMagasinier.clear();
								 	 							
								 	 							CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
								 	 							DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
								 	 							detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
								 	 							detailCompteMagasinier.setDepot(detailActionPerteMP.getActionperteMP().getMagasin().getDepot());
								 	 							detailCompteMagasinier.setMagasin(detailActionPerteMP.getActionperteMP().getMagasin());
								 	 							detailCompteMagasinier.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 						
								 	 							detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
								 	 							
								 	 							
								 	 							if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 							{
								 	 								if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 								{
								 	 									detailCompteMagasinier.setPrix(detailActionPerteMP.getMatierePremier().getPrix());
								 	 									
								 	 								}else
								 	 								{
								 	 									StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 									if(stockMP!=null)
								 	 									{
								 	 										detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
								 	 										
								 	 									}else
								 	 									{
								 	 										detailCompteMagasinier.setPrix(BigDecimal.ZERO);
								 	 									}
								 	 									
								 	 									
								 	 								}
								 	 								

								 	 								detailCompteMagasinier.setFournisseurMP(detailActionPerteMP.getFournisseurMP());
								 	 								
								 	 							}
								 	 							
								 	 							detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
								 	 							
								 	 							detailCompteMagasinier.setDateoperation(detailActionPerteMP.getActionperteMP().getDateoperation());
								 	 							detailCompteMagasinier.setDesignation(TYPE_PERTE);
								 	 							compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
								 	 							listDetailCompteMagasinier.add(detailCompteMagasinier);
								 	 							
								 	 							compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
								 	 							compteMagasinierDAO.edit(compteMagasinier);
								 	 							
								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								 	 							
								 	 							
								 	 	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
								 	 	detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 	if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 	{
								 	 	detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 	}else
								 	 	{
								 	 	if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 	{
								 	 	StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 	if(stockMP!=null)
								 	 	{
								 	 	detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 	}else
								 	 	{
								 	 		detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 	}

								 	 	}else
								 	 	{

								 	 	StockMP stockMP=stockMPDAO.findStockByMagasinMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 	if(stockMP!=null)
								 	 	{
								 	 	detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 	}else
								 	 	{
								 	 		detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 	}
								 	 	}



								 	 	}

								 	 	detailTransferStockMP.setFournisseur(detailActionPerteMP.getFournisseurMP());	
								 	 	detailTransferStockMP.setMagasinSouce(magasin);

								 	 	detailTransferStockMP.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 	detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
								 	 	detailTransferStockMP.setTransferStockMP(transfererStockMP);
								 	 	listDetailTransfertStockMP.add(detailTransferStockMP);			

								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					

								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 						}else if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
								 	 						{
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 						}
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 					
								 	 					
								 	 					
								 	 					
								 	 				}
								 	 				
								 	 				
								 	 				
								 	 			
								 	 				

								 	 				
								 	 				if(detailActionPerteMP.getFournisseurMP()==null)
								 	 				{
								 	 					
								 	 					
								 	 						
								 	 						if(detailActionPerteMP.getActionMP().getAction().equals(TRANSFERT_MAGASIN_DECHET))
								 	 						{
								 	 							
								 	 					
								 	 						
								 	 		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								 	 						
								 	 						
								 	 						DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
								 	 						detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 						if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 						{
								 	 							detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 						}else
								 	 						{
								 	 							if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 							{
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 								}
								 	 								
								 	 							}else
								 	 							{
								 	 								
								 	 								StockMP stockMP=stockMPDAO.findStockByMagasinMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 								}
								 	 							}
								 	 							

								 	 							
								 	 						}
								 	 						
								 	 						detailTransferStockMP.setFournisseur(detailActionPerteMP.getFournisseurMP());	
								 	 						detailTransferStockMP.setMagasinSouce(magasin);
								 	 						detailTransferStockMP.setMagasinDestination(magasindechet);
								 	 						detailTransferStockMP.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 						detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
								 	 						detailTransferStockMP.setTransferStockMP(transfererStockMP);
								 	 						listDetailTransfertStockMP.add(detailTransferStockMP);
								 	 						
								 	 						
								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								 	 						
								 	 						
								 	 		
								 	 							
								 	 							
								 	 						}else if(detailActionPerteMP.getActionMP().getAction().equals(AVANCE_SUR_MAGASINIER))
								 	 						{
								 	 							
								 	 							
								 	 							listDetailCompteMagasinier.clear();
								 	 						
								 	 							CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
								 	 							DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
								 	 							detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
								 	 							detailCompteMagasinier.setDepot(detailActionPerteMP.getActionperteMP().getMagasin().getDepot());
								 	 							detailCompteMagasinier.setMagasin(detailActionPerteMP.getActionperteMP().getMagasin());
								 	 							detailCompteMagasinier.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 						
								 	 							detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
								 	 							
								 	 						
								 	 								

								 	 								if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 								{
								 	 									detailCompteMagasinier.setPrix(detailActionPerteMP.getMatierePremier().getPrix());
								 	 									
								 	 								}else
								 	 								{
								 	 									StockMP stockMP=stockMPDAO.findStockByMagasinMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 								if(stockMP!=null)
								 	 								{
								 	 									detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
								 	 								}else
								 	 								{
								 	 									detailCompteMagasinier.setPrix(BigDecimal.ZERO);
								 	 								}
								 	 									
								 	 									
								 	 								}
								 	 								
								 	 								
								 	 								
								 	 							
								 	 							
								 	 							detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
								 	 							
								 	 							detailCompteMagasinier.setDateoperation(detailActionPerteMP.getActionperteMP().getDateoperation());
								 	 							detailCompteMagasinier.setDesignation(TYPE_PERTE);
								 	 							compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
								 	 							listDetailCompteMagasinier.add(detailCompteMagasinier);
								 	 							
								 	 							compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
								 	 							compteMagasinierDAO.edit(compteMagasinier);
								 	 							
								 	 							
								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								 	 							
								 	 							
								 	 							DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
								 	 							detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 							if(detailActionPerteMP.getMatierePremier().getPrix()!=null)
								 	 							{
								 	 								detailTransferStockMP.setPrixUnitaire(detailActionPerteMP.getMatierePremier().getPrix());
								 	 							}else
								 	 							{
								 	 								if(detailActionPerteMP.getFournisseurMP()!=null)
								 	 								{
								 	 									StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId(), detailActionPerteMP.getFournisseurMP().getId())	;
								 	 									if(stockMP!=null)
								 	 									{
								 	 										detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 									}else
								 	 									{
								 	 										detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 									}
								 	 									
								 	 								}else
								 	 								{
								 	 									
								 	 									StockMP stockMP=stockMPDAO.findStockByMagasinMP(detailActionPerteMP.getMatierePremier().getId(), detailActionPerteMP.getActionperteMP().getMagasin().getId())	;
								 	 									if(stockMP!=null)
								 	 									{
								 	 										detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								 	 									}else
								 	 									{
								 	 										detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								 	 									}
								 	 								}
								 	 								

								 	 								
								 	 							}
								 	 							
								 	 							detailTransferStockMP.setFournisseur(detailActionPerteMP.getFournisseurMP());	
								 	 							detailTransferStockMP.setMagasinSouce(magasin);
								 	 							
								 	 							detailTransferStockMP.setMatierePremier(detailActionPerteMP.getMatierePremier());
								 	 							detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
								 	 							detailTransferStockMP.setTransferStockMP(transfererStockMP);
								 	 							listDetailTransfertStockMP.add(detailTransferStockMP);			
								 	 							
								 	 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								 	 							
								 	 							
								 	 							
								 	 						}else if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
								 	 						{
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 							
								 	 						}
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 						
								 	 					
								 	 					
								 	 					
								 	 					
								 	 				}
								 	 				
								 	 				
								 	 				
								 	 			
								 	 				
								 	 		
								 	 			
								 	 			
								 	 			
								 	 			
								 	 			
								 	 			
								 	 		
								 	 			
								 	 		}
								 	 		
								 	 		}

								 	 		if(listDetailTransfertStockMP.size()!=0)
											{
												String codeTransfert=Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),TYPE_PERTE);
												
												transfererStockMP.setCodeTransfer(codeTransfert);
												transfererStockMP.setCreerPar(AuthentificationView.utilisateur);
												transfererStockMP.setDate(new Date());
												transfererStockMP.setDateTransfer(ActionPerteMPValider.getDateoperation());
												transfererStockMP.setDepot(ActionPerteMPValider.getMagasin().getDepot());
												transfererStockMP.setStatut(TYPE_PERTE);
												transferStockMPDAO.add(transfererStockMP);
												
												
												for(int i=0;i<listDetailTransfertStockMP.size();i++)
												{
													
												
													detailTransferStockMPDAO.add(listDetailTransfertStockMP.get(i));
													
													
												}
												
												
												
											}
										
										
									}
						 	 		
							 	 	
									
									
									
									

	///////////////////////////////////////////////////////////////// Impression /////////////////////////////////////////////////////////////////////////////////////////////////////////////								
									
									 
									Map parameters = new HashMap();
									parameters.put("depot", comboDepot.getSelectedItem());
									
									parameters.put("magasin",comboMagasin.getSelectedItem());
									parameters.put("inventaire",Constantes.CODE_INVENTAIRE_2);
									
									
									JasperUtils.imprimerInventaire2(table.getModel(), parameters);
									
									//JOptionPane.showMessageDialog(null, "PDF exportÈ avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
						  		  	
							
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////									
									
									
									
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////								
									
									
									CalculerStockFinale();
									
									Boolean trouve=false;
									
									listInventaire2=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(),Constantes.CODE_INVENTAIRE_2,ETAT_INVALIDER);
									for(int i=0;i<listInventaire2.size();i++)
									{
										
										 trouve=false;
										 Inventaire inventaire=listInventaire2.get(i);

										for(int f=0;f<listEtatStockMPAfficher.size() ; f++)
										{
											
											if(inventaire.getMatierePremier().getId()==listEtatStockMPAfficher.get(f).getMp().getId())
											{
												
												if(inventaire.getFournisseurMP()!=null)
												{
													if(listEtatStockMPAfficher.get(f).getFournisseurMP()!=null)
													{
														 if(inventaire.getFournisseurMP().getId()==listEtatStockMPAfficher.get(f).getFournisseurMP().getId())
														 {
															 trouve=true;
															 inventaire.setQuantiteStockApresDeuxiemInventaire(listEtatStockMPAfficher.get(f).getQuantiteFinale());
															 
															 
														 }
														
													}
												}else if(inventaire.getFournisseurMP()==null)
												{
													if(listEtatStockMPAfficher.get(f).getFournisseurMP()==null)
													{
														trouve=true;
														 inventaire.setQuantiteStockApresDeuxiemInventaire(listEtatStockMPAfficher.get(f).getQuantiteFinale());
														
													}
													
													
												}
												
											}
											
										}
										
										if(trouve==true)
										{
											inventaireDAO.edit(inventaire);
										} 
										
										
										
										
										
										
									}
									
                                    intialiserTableau();
									
									dateoperation.setDate(null);
									
									btnValider.setEnabled(false);
									
									
									
									JOptionPane.showMessageDialog(null, "L'Inventaire 2 valider avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);	
								}else
								{
									
									
									JOptionPane.showMessageDialog(null, "L'Inventaire 2 des MP deja existant", "Erreur", JOptionPane.ERROR_MESSAGE);	
									return;
								}
							
					
					
				} catch (NumberFormatException e) {
					
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer les quantite en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
				
				
			
				
				
			
			
			
			}
		});
		btnValider.setBounds(560, 638, 104, 23);
		btnValider.setVisible(true);
		add(btnValider);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(505, 168, 113, 23);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboMagasin.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateoperation.getDate()==null){
				JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else {
				
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				
				
				SubCategorieMp subCategorieMp=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
				CategorieMp categorieMp=null;
				MatierePremier matierePremier=null;
				
				Magasin magasindechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				if(magasindechet==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
					return;
					
				}
				ActionPerteMP  ActionPerteMPInvalider=ActionPerteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasindechet, ETAT_INVALIDER);
				if(ActionPerteMPInvalider!=null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez Valider l'Action de Perte MP SVP !!!!");
					return;
					
				}
				
				
					listInventaire=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(),Constantes.CODE_INVENTAIRE_1,ETAT_INVALIDER);
					
					if(listInventaire.size()==0)
					{
						
						listInventaire=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(),Constantes.CODE_INVENTAIRE_1,ETAT_VALIDER);
						if(listInventaire.size()!=0)
						{
							JOptionPane.showMessageDialog(null, "Inventaire dÈja Valider !!!!");
							listInventaire.clear();
							
						}else
						{
							JOptionPane.showMessageDialog(null, "Inventaire n'existe pas!!!!");
						}
						
					}
					
					
									
				intialiserTableau();
				afficher_tableMP(listInventaire);
				btnValider.setEnabled(true);
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
			listFournisseur=fournisseurMPDAO.findAll();
			for(int i=0;i<listFournisseur.size();i++)
			{
				
				FournisseurMP fournisseurMP=listFournisseur.get(i);
				
				combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
				mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
				
			}
	  		
	  		JLabel label_4 = new JLabel("Date Op\u00E9ration :");
	  		label_4.setBounds(839, 35, 96, 26);
	  		layeredPane.add(label_4);
	  		
	  		 dateoperation = new JDateChooser();
	  		dateoperation.setLocale(Locale.FRANCE);
	  		dateoperation.setDateFormatString("dd/MM/yyyy");
	  		dateoperation.setBounds(930, 35, 174, 26);
	  		layeredPane.add(dateoperation);
	  		
	  		JLabel label = new JLabel("Magasin Dechet :");
	  		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		label.setBounds(491, 34, 97, 24);
	  		layeredPane.add(label);
	  		
	  		
	  		
	  		JButton button = new JButton(" Situation Confirmation 2eme Inventaire");
	  		button.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				

					if(listInventaireVerifier.size()!=0)
					{
						
						 
						Map parameters = new HashMap();
						parameters.put("depot", listInventaireVerifier.get(0).getMagasin().getDepot().getLibelle());
						
						parameters.put("magasin",listInventaireVerifier.get(0).getMagasin().getLibelle());
						
						
						
						JasperUtils.imprimerSituationInventaire(listInventaireVerifier, parameters);
						
						
					}	
	  				
	  				
	  				
	  				
	  				
	  				
	  			}
	  		});
	  		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  		button.setBounds(269, 638, 223, 23);
	  		add(button);
			
			  int i=0;
	  		  while(i<listsubcategoriemp.size())
	  		  {
	  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
	  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
	  			  i++;
	  		  }
				  		 
	}
	
void afficher_tableMP(List<Inventaire> listInventaire)
	{
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
		  int i=0;
			while(i<listInventaire.size())
			{	
				
				Inventaire inventaire=listInventaire.get(i);
				
			String fournisseur="";
			
			if(inventaire.getFournisseurMP()!=null)
			{
				fournisseur=inventaire.getFournisseurMP().getCodeFournisseur();
			}
			
			if(inventaire.getQuantiteStock().compareTo(inventaire.getQuantite().add(inventaire.getQuantitePerte()))!=0)
			{
				
				listInventaireVerifier.add(inventaire);
				
				mapCodeMP.put(inventaire.getMatierePremier().getCode(), inventaire.getMatierePremier());
				Object []ligne={inventaire.getMatierePremier().getCode(),inventaire.getMatierePremier().getNom(),fournisseur,dfDecimal.format(inventaire.getQuantite()),dfDecimal.format(inventaire.getQuantitePerte()),dfDecimal.format(inventaire.getQuantiteStock()),dfDecimal.format(inventaire.getQuantite().add(inventaire.getQuantitePerte()).subtract( inventaire.getQuantiteStock())),BigDecimal.ZERO};

				modeleMP.addRow(ligne);
			}
			
				
			//	mapPrixMP.put(stockMP.getMatierePremier().getCode(), stockMP.getPrixUnitaire());
				
				
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre","Fournisseur", "QuantitÈ Inventaire","QuantitÈ Perte","QuantitÈ Stock","Ecart","Quantite"
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
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
		   table.getColumnModel().getColumn(4).setPreferredWidth(160);
		   table.getColumnModel().getColumn(5).setPreferredWidth(160);
		   table.getColumnModel().getColumn(6).setPreferredWidth(160);
		   table.getColumnModel().getColumn(7).setPreferredWidth(160);
		 
		   table.getTableHeader().setReorderingAllowed(false);
}


void intialiserModifier(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MatiËre PremiËre","MatiËre PremiËre","Fournisseur", "QuantitÈ Inventaire ","QuantitÈ Stock ","Ecart"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
		   table.getColumnModel().getColumn(4).setPreferredWidth(160);
		  
		   table.getTableHeader().setReorderingAllowed(false);
}





public void CalculerStockFinale()
{
	

	if(comboDepot.getSelectedItem().equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else {
		
		//MatierePremier matierePremier=MapCodeMatierPremiere.get("");
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=null;
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
				
			  	FournisseurMP fournisseurMP=null;
			
			  	if(magasin==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
			  		return;
			  	}
			  	
			  	
			  	
				if(dateoperation.getDate()==null)
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
					String year = simpleDateFormatyear.format(dateoperation.getDate());
					
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
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
			
				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

				//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				
				listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);

				
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



























}

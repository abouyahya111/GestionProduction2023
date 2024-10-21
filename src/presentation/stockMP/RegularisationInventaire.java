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
import javax.swing.JTable;
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

import util.CheckableItem;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailActionPerteMPDAOImpl;
import dao.daoImplManager.DetailCompteMagasinierDAOImpl;
import dao.daoImplManager.DetailPerteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.InventaireDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PerteMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailActionPerteMPDAO;
import dao.daoManager.DetailCompteMagasinierDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.InventaireDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.PerteMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
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
import javax.swing.ListModel;

import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RegularisationInventaire extends JLayeredPane implements Constantes{
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
	private Map< String, CompteMagasinier> MapCompteMagasinier = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private List<BonDePerte> listBonDePerte=new ArrayList<BonDePerte>();
	private List<Inventaire> listInventaire=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaireAfficher=new ArrayList<Inventaire>();
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
	private List<DetailTransferStockMP> listDetailTransfertMp =new ArrayList<DetailTransferStockMP>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinierImprimer =new ArrayList<DetailCompteMagasinier>();
	private List<CompteMagasinier> listComptaMagasinier =new ArrayList<CompteMagasinier>();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private CompteMagasinierDAO compteMagasinierDAO;
	private DetailCompteMagasinierDAO detailCompteMagasinierDAO;
	JButton btnValider = new JButton("Valider");
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	 private PerteMPDAO PerteMPDAO;
	 private DetailPerteMPDAO detailPerteMPDAO;
	 private DetailActionPerteMPDAO detailActionPerteMPDAO;
	 private List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	 private List<DetailActionPerteMP> listDetailAcionPerteMP=new ArrayList<DetailActionPerteMP>();
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public RegularisationInventaire() {
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
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	compteMagasinierDAO=new CompteMagasinierDAOImpl();
        	listComptaMagasinier=compteMagasinierDAO.findAll();
        	detailCompteMagasinierDAO=new DetailCompteMagasinierDAOImpl();
        	PerteMPDAO=new PerteMPDAOImpl();
        	detailPerteMPDAO=new DetailPerteMPDAOImpl();
        	detailActionPerteMPDAO=new DetailActionPerteMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent e) {
				  		     		/*
				  		     	    int column = table.columnAtPoint( e.getPoint() );
				  		     		int row =table.rowAtPoint(e.getPoint());
				  		     	
				  		     		
				  		     		
				  		     	if(column==6)	
				  		     	{
				  		     		boolean manque=(boolean) table.getValueAt(row, 6);	
			  		     			if(manque==true)
				  		     		{
				  		     			modeleMP.setValueAt(false, row, 6);
				  		     		
				  		     		
				  		     		
				  		     			
				  		     		}else
				  		     		{
				  		     			
				  		     			modeleMP.setValueAt(true, row, 6);
				  		     			
				  		     			
				  		     		}
			  		     			
			  		     			
			  		     			
			  		     			
				  		     	}
				  		  	

				  		  	if(column==7)	
			  		     	{
				  		  	boolean plus=(boolean) table.getValueAt(row, 7);
				  			
		  		     		
		  		     		if(plus==true)
		  		     		{
		  		     			modeleMP.setValueAt(false, row, 7);
		  		     		
		  		     		
		  		     		
		  		     			
		  		     		} else
		  		     		{
		  		     			modeleMP.setValueAt(true, row, 7);
		  		     		}
		  		     		
		  		     		
		  		     		
			  		     	}
		  		     		
				  		     		
				  		     		*/
				  		     		
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
				  		 table.getTableHeader().setEnabled(false);;
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 231, 1156, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
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
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 202, 1142, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1142, 121);
				  		     	add(layeredPane);
				  		     	
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
				listDetailCompteMagasinierImprimer.clear();
				listBonDePerte.clear();
				listDetailTransfertMp.clear();
				listDetailCompteMagasinier.clear();
				listDetailTransfertStockMP.clear();
				try {
					
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					
					//////////////////////////////////////////////////////////////// verifier les quantites si en chiffre ////////////////////////////////////////			
							boolean erreur=false;	
							boolean erreurmanque=false;	
					
					for(int i=0; i<table.getRowCount(); i++){
									
									int x=0;
									
									if((boolean) table.getValueAt(i, 6)==false && (boolean) table.getValueAt(i, 7)==false && (boolean) table.getValueAt(i, 8)==false)
									{
										
										
										erreur=true;
											
										}else
										{
											
											if((boolean) table.getValueAt(i, 6)==true)
											{
												if(table.getValueAt(i, 9).toString().equals(""))
												{
													erreurmanque=true;
												}else
												{
													x=x+1;
												}
												
											}
											
											if((boolean) table.getValueAt(i, 7)==true)
											{
												x=x+1;
											}
											
											
											
											
											
											
											
										}
									
									if(x>1)
									{
										erreur=true;
									}
									
									
									
									
										
									}
					
					if(erreur==true)
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez selectionner Manque ou Plus ou Retour SVP ");
						return;
					}
					
					if(erreurmanque==true)
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez selectionner le Compte Magasinier Pour le Manque SVP ");
						return;
					}
					
					
									
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								
					   Magasin magasinSource=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
					   
						///////////////////////////////////////////// Transfert Reception /////////////////////////////////////////////////
						
						
						String codeTransfert="";
	  					
	  					
	  					codeTransfert=Utils.genererCodeTransferInventaire(magasin.getDepot().getCode(),ETAT_RECEPTION_ENATTENT);
	  					TransferStockMP transferStock  = new TransferStockMP();	
	  						transferStock.setCodeTransfer(codeTransfert);
		  					transferStock.setCreerPar(AuthentificationView.utilisateur);
		  					transferStock.setDate(new Date());
		  					transferStock.setDateTransfer(dateoperation.getDate());
		  				
		  					transferStock.setStatut(Constantes.ETAT_RECEPTION_ENATTENT);
		  				
		  					transferStock.setDepot(magasin.getDepot());
		  					//transferStock.setType(detailtypeSortie.getTypesortie());		  					
		  				
		  		///////////////////////////////////////////////////////// Perte /////////////////////////////////////////////////////////////////////			
		  					codeTransfert=Utils.genererCodeTransfer(magasin.getDepot().getCode(),Constantes.TYPE_PERTE);
		  					TransferStockMP transferStockperte  = new TransferStockMP();	
		  					transferStockperte.setCodeTransfer(codeTransfert);
		  					transferStockperte.setCreerPar(AuthentificationView.utilisateur);
		  					transferStockperte.setDate(new Date());
		  					transferStockperte.setDateTransfer(dateoperation.getDate());
		  					transferStockperte.setStatut(Constantes.TYPE_PERTE);		  				
		  					transferStockperte.setDepot(magasin.getDepot());
		  					
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								
								boolean existe=false;
								boolean perte,plus,moins=false;
								
								for(int i=0; i<table.getRowCount(); i++){
								
								String codeMP = table.getValueAt(i, 0).toString();
								
								MatierePremier matierePremier=mapCodeMP.get(codeMP);
								perte=false;
								plus=false;
								moins=false;
								
								
								if(!table.getValueAt(i, 6).toString().equals(""))
								{
									FournisseurMP fournisseurMP=null;
									Inventaire inventaire=null;
									if(!table.getValueAt(i, 2).toString().equals(""))
									{
										fournisseurMP= fournisseurMPDAO.findByCode(table.getValueAt(i, 2).toString());
										 inventaire=inventaireDAO.findByDateByMagasinByMPByFournisseur(magasin, dateoperation.getDate(), matierePremier,fournisseurMP,Constantes.CODE_INVENTAIRE_2,ETAT_INVALIDER);
										
									}else
									{
										 inventaire=inventaireDAO.findByDateByMagasinByMP(magasin, dateoperation.getDate(), matierePremier,Constantes.CODE_INVENTAIRE_2,ETAT_INVALIDER);
									}
									
								
									if(inventaire!=null)
									{
									
										
///////////////////////////////////////////////////////////////////////////////////// Reception  ///////////////////////////////////////////////////////////////////////////////////////////////////////
								
										if((boolean) table.getValueAt(i, 7)==true)
										{
											BigDecimal QuantiteReception=BigDecimal.ZERO;
		                          DetailTransferStockMP detailtransfertmp=new DetailTransferStockMP();
			  			  					
			  			  					detailtransfertmp.setMagasinDestination (inventaire.getMagasin());
			  			  				    detailtransfertmp.setMagasinSouce(magasinSource);
			  			  					detailtransfertmp.setMatierePremier(inventaire.getMatierePremier());
			  			  				if((listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite())) .compareTo(BigDecimal.ZERO)<0)
										{
			  			  				QuantiteReception=(listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite())).multiply(new BigDecimal(-1));
										}else
										{
											QuantiteReception=(listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite()));
										}
			  			  					detailtransfertmp.setQuantite(QuantiteReception);
			  			  					detailtransfertmp.setTransferStockMP(transferStock);
			  			  					//detailTransfertMPDAO.add(detailtransfertmp);
			  			  				if(inventaire.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		  					{
			  			  				 detailtransfertmp.setFournisseur(fournisseurMP);
			  		  					}
			  			  				   
			  		  					
			  			  					listDetailTransfertMp.add(detailtransfertmp);
										
										
										plus=true;
										existe=true;
										}
										
////////////////////////////////////////////////////////////////////////////////////////////////////  Manque   /////////////////////////////////////////////////////////////////////////////////////////////////////////////										
										
										if((boolean) table.getValueAt(i, 6)==true)
										{
											
											if(!table.getValueAt(i, 9).toString().equals(""))
											{
												listDetailCompteMagasinier.clear();
												BigDecimal QuantiteManque=BigDecimal.ZERO;
												CompteMagasinier compteMagasinier=MapCompteMagasinier.get(table.getValueAt(i, 9).toString());
												DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
												detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
												detailCompteMagasinier.setDepot(inventaire.getDepot());
												detailCompteMagasinier.setMagasin(inventaire.getMagasin());
												detailCompteMagasinier.setMatierePremier(inventaire.getMatierePremier());
												if((listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite())).compareTo(BigDecimal.ZERO)<0)
												{
													QuantiteManque=(listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite())).multiply(new BigDecimal(-1));
												}else
												{
													QuantiteManque=(listInventaireAfficher.get(i).getQuantiteStockApresDeuxiemInventaire().subtract(listInventaireAfficher.get(i).getQuantite()));
												}
												detailCompteMagasinier.setQuantite(QuantiteManque);
												
												
												if(inventaire.getFournisseurMP()!=null)
												{
													if(inventaire.getMatierePremier().getPrix()!=null)
													{
														detailCompteMagasinier.setPrix(inventaire.getMatierePremier().getPrix());
													}else
													{
														StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaire.getMatierePremier().getId(), inventaire.getMagasin().getId(), inventaire.getFournisseurMP().getId())	;
														if(stockMP!=null)
														{
															detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
														}else
														{
															detailCompteMagasinier.setPrix(BigDecimal.ZERO);
														}
														
														
														
													}
													

													detailCompteMagasinier.setFournisseurMP(inventaire.getFournisseurMP());
												}else
												{
													

													if(inventaire.getMatierePremier().getPrix()!=null)
													{
														detailCompteMagasinier.setPrix(inventaire.getMatierePremier().getPrix());
													}else
													{
														StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaire.getMatierePremier().getId(), inventaire.getMagasin().getId())	;
														
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
												
												detailCompteMagasinier.setDateoperation(dateoperation.getDate());
												detailCompteMagasinier.setDesignation(MANQUE_INVENTAIRE);
												compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
												listDetailCompteMagasinier.add(detailCompteMagasinier);
												listDetailCompteMagasinierImprimer.add(detailCompteMagasinier);
												//compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
												
											
												detailCompteMagasinierDAO.add(detailCompteMagasinier);
													
													
													
												
												
												
												
												compteMagasinierDAO.edit(compteMagasinier);
												
												
												moins=true;
												
												
												existe=true;
												
												DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
												detailTransferStockMP.setPrixUnitaire(inventaire.getMatierePremier().getPrix());
												if(inventaire.getMatierePremier().getPrix()!=null)
												{
												detailTransferStockMP.setPrixUnitaire(inventaire.getMatierePremier().getPrix());
												}else
												{
												if(inventaire.getFournisseurMP()!=null)
												{
												StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaire.getMatierePremier().getId(), inventaire.getMagasin().getId(), inventaire.getFournisseurMP().getId())	;
												if(stockMP!=null)
												{
												detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
												}else
												{
													detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
													
												}

												}else
												{

												StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaire.getMatierePremier().getId(), inventaire.getMagasin().getId())	;
												if(stockMP!=null)
												{
												detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
												}else
												{
													detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
												}
												}



												}

												detailTransferStockMP.setFournisseur(inventaire.getFournisseurMP());	
												detailTransferStockMP.setMagasinSouce(magasin);

												detailTransferStockMP.setMatierePremier(inventaire.getMatierePremier());
												detailTransferStockMP.setQuantite(QuantiteManque);
												detailTransferStockMP.setTransferStockMP(transferStockperte);
												listDetailTransfertStockMP.add(detailTransferStockMP);
												
												
											}
											
											
											
											
											
										}
										
										
								
									inventaire.setPlus(plus);
									inventaire.setMoins(moins);
									inventaire.setEtat(ETAT_VALIDER);
									inventaireDAO.edit(inventaire)	;
									}
									
								}
								
							
								
								}
								
								
///////////////////////////////////////////////////////////////////////////////////// Reception  ///////////////////////////////////////////////////////////////////////////////////////////////////////

if(listDetailTransfertMp.size()!=0)
{

transferStock.setListDetailTransferMP(listDetailTransfertMp);
transferStockMPDAO.add(transferStock);

}



//////////////////////////////////////////////////////////////////////////////////////  Perte ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////										

				
if(listDetailTransfertStockMP.size()!=0)
{

transferStockperte.setListDetailTransferMP(listDetailTransfertStockMP);
transferStockMPDAO.add(transferStockperte);

}


								
//////////////////////////////////////////////////////////////////////////////////////////////////////////Impression //////////////////////////////////////////////////////////////////////////////////////////	
								
							
								if(existe==true)
								{
									JOptionPane.showMessageDialog(null, "L'Inventaire 2 valider avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);	
									
									dateoperation.setDate(null);
									
									intialiserTableau();
									
////////////////////////////////////////////////////////////////////////////////////////////////////////// Impression //////////////////////////////////////////////////////////////////////////////////////////	
								
									
									


									if(listDetailTransfertMp.size()!=0)
									{
										
										DetailTransferStockMP detailTransferStockMP=listDetailTransfertMp.get(0);
										Map parameters = new HashMap();
										parameters.put("numTransfer", listDetailTransfertMp.get(0).getTransferStockMP().getCodeTransfer());                
										parameters.put("magasinDest", detailTransferStockMP.getMagasinDestination().getLibelle());
										parameters.put("depDest", detailTransferStockMP.getMagasinDestination().getDepot().getLibelle());
										parameters.put("dateTransfer", detailTransferStockMP.getTransferStockMP().getDateTransfer());
										JasperUtils.imprimerBonDeclarationReceptionMagasinierMP(listDetailTransfertMp,parameters);
										
									}
									
									
							
								
								
									if(listDetailCompteMagasinierImprimer.size()!=0)
									{
										
										DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinierImprimer.get(0);
										Map parameters = new HashMap();					             
										parameters.put("magasinDest",  detailCompteMagasinier.getMagasin().getLibelle());
										parameters.put("depDest", detailCompteMagasinier.getDepot().getLibelle());
										parameters.put("dateTransfer", detailCompteMagasinier.getDateoperation());
										JasperUtils.imprimerBonDeManque (listDetailCompteMagasinierImprimer,parameters);
										
									}
								
								btnValider.setEnabled(false);
									
									
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////									
									
									
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
		btnValider.setBounds(467, 638, 89, 23);
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
				
				listComptaMagasinier=compteMagasinierDAO.findCompteMagasinierByMagasin(magasin);
				
				
					listInventaire=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(),Constantes.CODE_INVENTAIRE_2,ETAT_INVALIDER);
					
				
					
									
				intialiserTableau();
				afficher_tableMP(listInventaire);
				
				for(int i=0;i<table.getRowCount();i++)
				{

  		     			boolean manque=(boolean) table.getValueAt(i, 6);	
  		     			if(manque==true)
	  		     		{
	  		     			modeleMP.setValueAt("", i, 9);
	  		     		
	  		     		
	  		     		
	  		     			
	  		     		}else
	  		     		{
	  		     			modeleMP.setValueAt("", i, 9);
	  		     		}

  		   	
  		     		boolean plus=(boolean) table.getValueAt(i, 7);	
  		     		
  		     		if(plus==true)
  		     		{
  		     			modeleMP.setValueAt("", i, 9);
  		     		
  		     		
  		     		
  		     			
  		     		} 
  		    	 
  		     		
					
				}
				
				
				
				
				
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
	  		label_4.setBounds(498, 37, 96, 26);
	  		layeredPane.add(label_4);
	  		
	  		 dateoperation = new JDateChooser();
	  		dateoperation.setLocale(Locale.FRANCE);
	  		dateoperation.setDateFormatString("dd/MM/yyyy");
	  		dateoperation.setBounds(589, 37, 174, 26);
	  		layeredPane.add(dateoperation);
			
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
	
	listInventaireAfficher.clear();
	
	boolean plus=false;
	boolean manque=false;
	boolean retour=false;
		  int i=0;
		  
		   
			PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat ("",listInventaire.get(0).getDateoperation(), listInventaire.get(0).getMagasindechet(), ETAT_VALIDER);
	 			
	 			listDetailPerteMP.clear();
	 			if(perteMP!=null)
	 			{
	 				 
	 				
	 				listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMP);
	 			}
		 
		  
			while(i<listInventaire.size())
			{	
				
				Inventaire inventaire=listInventaire.get(i);
				
			String fournisseur="";
			
			if(inventaire.getFournisseurMP()!=null)
			{
				fournisseur=inventaire.getFournisseurMP().getCodeFournisseur();
			}
			
			if(inventaire.getQuantite().compareTo(inventaire.getQuantiteStockApresDeuxiemInventaire())!=0)
			{
				
				
				
				
				retour=false;
				
				 plus=false;
				 manque=false;
				
				if(inventaire.getQuantite().compareTo(inventaire.getQuantiteStockApresDeuxiemInventaire())>0)
				{
					plus=true;
					
					
				}else 	if(inventaire.getQuantite().compareTo(inventaire.getQuantiteStockApresDeuxiemInventaire())<0)
				{
					
					 manque=true;
					
				}
				
				
			for(int d=0;d<listDetailPerteMP.size();d++)
			{
				
				listDetailAcionPerteMP.clear();
 	 			DetailPerteMP detailPerteMP=listDetailPerteMP.get(d);
 	 			
 	 			listDetailAcionPerteMP=detailActionPerteMPDAO.listeDetailActionPerteMPByDetailPerteMP(detailPerteMP);		
				
 	 			for(int c=0;c<listDetailAcionPerteMP.size();c++)
 	 			{
 	 				
 	 				
 	 				DetailActionPerteMP detailActionPerteMP=listDetailAcionPerteMP.get(c);
 	 				
 	 				
 	 				if(detailActionPerteMP.getMatierePremier().getId()==inventaire.getMatierePremier().getId())
 	 				{
 	 					
 	 					if(detailActionPerteMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
 	 					{
 	 						if(detailActionPerteMP.getFournisseurMP()!=null)
 	 						{
 	 							if(inventaire.getFournisseurMP()!=null)
 	 	 						{
 	 								if(detailActionPerteMP.getFournisseurMP().getId()==inventaire.getFournisseurMP().getId())
 	 								{
 	 								  if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
 	 			 	 				{
 	 			 	 					  
 	 			 	 					retour=true;
 	 			 	 				plus=false;
 	 			 				 manque=false;
 	 			 	 					
 	 			 	 					
 	 			 	 				}
 	 									
 	 								}
 	 								
 	 	 						}
 	 							
 	 							
 	 							
 	 						}
 	 						
 	 						
 	 					}else
 	 					{
 	 						
 	 						
 	 					  if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
 	  	 				{
 	  	 					  
 	  	 					retour=true;
 	  	 				plus=false;
 	  				 manque=false;
 	  	 					
 	  	 				}
 	 					}
 	 					
 	 					
 	 					
 	 					
 	 				}
 	 				
 	 			
 	 				
 	 				
 	 				
 	 				
 	 				 
 	 				
 	 				
 	 				
 	 				
 	 			}
				
			}
				
				
				
				
				
			
				
				
				 
				
				
				listInventaireAfficher.add(inventaire);
				mapCodeMP.put(inventaire.getMatierePremier().getCode(), inventaire.getMatierePremier());
				Object []ligne={inventaire.getMatierePremier().getCode(),inventaire.getMatierePremier().getNom(),fournisseur,dfDecimal.format(inventaire.getQuantite()),dfDecimal.format(inventaire.getQuantiteStockApresDeuxiemInventaire()),dfDecimal.format(inventaire.getQuantite().subtract(inventaire.getQuantiteStockApresDeuxiemInventaire() )),manque,plus,retour,""};

				modeleMP.addRow(ligne);
			}
				
			//	mapPrixMP.put(stockMP.getMatierePremier().getCode(), stockMP.getPrixUnitaire());
				
				
				i++;
			}
	}
	
void intialiserTableau(){
	
	JComboBox jcombobox=new JComboBox();
	
	jcombobox.addItem("");
	  
	  for(int i=0;i<listComptaMagasinier.size();i++) {
	  
	  CompteMagasinier compteMagasinier= listComptaMagasinier.get(i);
	  
	  jcombobox.addItem(compteMagasinier.getNom());
	  MapCompteMagasinier.put(compteMagasinier.getNom(), compteMagasinier);
	  
	  }



jcombobox.setEditable(false);
final ListModel<CheckableItem> model=jcombobox.getModel();
	
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Matière Première","Matière Première","Fournisseur", "Quantité Inventaire ","Quantité Stock ","Ecart","Manque","Plus","Retour","Compte Magasinier"
		     	}
		     ) {
		 boolean[] columnEditables = new boolean[] {
					false,false,false,false,false,false,true,true,true,false
			};
			Class[] columnTypes = new Class[] {
					String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class,Boolean.class,Boolean.class,Boolean.class,String.class
				};
			  public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			public boolean isCellEditable(int row, int column) {
				
				if(column==9 && (boolean)(table.getValueAt(row, 6))==true)
				return true;
				else if(column==6 || column==7 || column==8)
				{
					return false;
				}else
					
				return columnEditables[column];
			}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(jcombobox));
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		   table.getColumnModel().getColumn(3).setPreferredWidth(160);
		   table.getColumnModel().getColumn(4).setPreferredWidth(160);
		   table.getColumnModel().getColumn(5).setPreferredWidth(160);
		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
		   table.getColumnModel().getColumn(7).setPreferredWidth(60);	
		   table.getColumnModel().getColumn(8).setPreferredWidth(60);	
		   table.getColumnModel().getColumn(9).setPreferredWidth(160);
		   table.getTableHeader().setReorderingAllowed(false);
}


void intialiserModifier(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Matière Première","Matière Première","Fournisseur", "Quantité Inventaire ","Quantité Stock ","Ecart"
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




}

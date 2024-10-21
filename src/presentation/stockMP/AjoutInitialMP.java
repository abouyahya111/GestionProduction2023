package presentation.stockMP;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.ChargeFixe;
import dao.entity.ChargeProduction;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class AjoutInitialMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;

	private JXTable  tableMP = new JXTable();
	
	private JFrame mainFrame;

	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listStockMPTmp =new ArrayList<StockMP>();
	private List<StockMP> listStockMPAfficher =new ArrayList<StockMP>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	//private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	
	
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiere= new HashMap<>();
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	
	 private JComboBox combomp;

	 private static XSSFSheet ExcelWSheet;
	    private static XSSFWorkbook ExcelWBook;
	    private static XSSFCell CellCodeMP;
	    private static XSSFCell CellQuantite;
	    private static XSSFCell CellPrix;
	    private static XSSFCell CellFournisseur;


	
private StockMPDAO stockMPDAO;
	private JTextField txtcodemp;
	
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	private MatierePremiereDAO matierepremieredao;
	private static SequenceurDAO sequenceurDAO= new SequenceurDAOImpl();
	
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private   JDateChooser dateChooserMP ;
	private JTextField txtprix;
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	 private SubCategorieMPDAO categorieMPDAO;
	 private JTextField txtlien;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutInitialMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 905);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	matierepremieredao=new MatierePremierDAOImpl();
         	stockMPDAO= new StockMPDAOImpl();
         	
        	
         listMP=matierepremieredao.findAll();
         transferStockMPDAO= new TransferStockMPDAOImpl();
         detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         fournisseurMPDAO=new FournisseurMPDAOImpl();
         categorieMPDAO=new SubCategorieMPAOImpl();
     	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
     	//listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
    	listFournisseur=fournisseurMPDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableMP.setSortable(false);
        tableMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		txtcodemp.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 0).toString());
       		combomp.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 1));
       		comboFournisseur.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 2).toString());
       		txtquantite.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 3).toString());
       		txtprix.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 4).toString());
       		
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Code MP","Matière Première","Fournisseur", "Quantite","Prix Unitaire"
       	}
       ));
				  		
       tableMP.setShowVerticalLines(false);
       tableMP.setSelectionBackground(new Color(51, 204, 255));
       tableMP.setRowHeightEnabled(true);
       tableMP.setBackground(new Color(255, 255, 255));
       tableMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMP.setColumnControlVisible(true);
       tableMP.setForeground(Color.BLACK);
       tableMP.setGridColor(new Color(0, 0, 255));
       tableMP.setAutoCreateRowSorter(true);
       tableMP.setBounds(2, 27, 411, 198);
       tableMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMP);
				  		     	scrollPane.setBounds(10, 491, 1117, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	scrollPane.setVisible(false);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des MP");
				  		     	titledSeparator.setBounds(10, 450, 1117, 30);
				  		     	add(titledSeparator);
				  		     	titledSeparator.setVisible(false);
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 298, 1200, 108);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(10, 39, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(170, 38, 57, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodemp = new JTextField();
		      util.Utils.copycoller(txtcodemp);
		      txtcodemp.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     					
			      			if(!txtcodemp.getText().equals(""))
			      			{

			      			MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
			      			if(mp!=null)
			      			{
			      				
			      				combomp.setSelectedItem(mp.getNom());
			      				
			      			}else
			      			{
			      				 JOptionPane.showMessageDialog(null, " Code MP  incorrecte ", "Erreur", JOptionPane.ERROR_MESSAGE);
			      				
			      			}
			      			
			      			
			      			}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(78, 39, 82, 26);
		      layeredPane.add(txtcodemp);
		    
		   
		       combomp = new JComboBox();
		       combomp.addItem("");
		       combomp.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	
  			 			if(!combomp.getSelectedItem().equals(""))
  				 		{
  				 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
  				 			txtcodemp.setText(mp.getCode());
  				 		
  				 			
  				 		
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodemp.setText(Constantes.CODE_MP);
  				 			
  				 		}
  				 	
  			 		
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      combomp.setBounds(221, 38, 278, 27);
		      layeredPane.add(combomp);
		      
		     
		     	int i=0;
		     	while(i<listMP.size())
		     	{
		     		
		     		MatierePremier mp=listMP.get(i);
		     		mapCodeMP.put(mp.getCode(), mp);
		     		mapMatierePremiere.put(mp.getNom(), mp);
		     		combomp.addItem(mp.getNom());
		     		
		     		
		     		i++;
		     	}
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(840, 39, 57, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(899, 39, 112, 26);
		      layeredPane.add(txtquantite);
		      
		 
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean existe=false;
			//	MouvementStockGlobal mouvementStockGlobal=null;
				
				 if(dateChooserMP.getDate()==null)
					{

						JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combodepot.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combomagasin.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
						
					}else if(listStockMP.size()==0)
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer les matieres premieres  SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;		
						
						
					}else
					{
						
						 //////////////////////////////////////////////////////////Chercher le Journée et liste des mouvement de stock de cet journée /////////////////////////////////////////
						
					
						  Depot depot=mapDepot.get(combodepot.getSelectedItem());
						  Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
						  /*
		               Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateChooserMP.getDate(), Constantes.ETAT_STATUT_OVERTE,depot.getId());
		               
		               
		               if( journeeDestination!=null)
		              {
		                     	 
		                      ///////// Mouvement Stock Magasin Destination ////////////////////
		                      
		                    
		               
		                      
		                 	 
		                 	 if(depot!=null )
		                 	 {
		                 		 
		                 		mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateChooserMP.getDate(), depot.getId(), magasin.getId());
		                      if(mouvementStockGlobal!=null)
		                       {
		                    	  listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
		                    	  existe=true;
		                   }else
		                   {
		                	
		                	   ////////// creer un nouveau mouvement de stock si le magasin de stockage n'existe pas 
		                	   
		                	   existe=false;
  		   							listDetailMouvementStock.clear();
  		   						
  		   						listStockMPTmp=stockMPDAO.findAllByMagasin(magasin.getId());
  		   							MouvementStockGlobal mouvementStockGlobalTmp=new MouvementStockGlobal();
  		   							for(int k=0;k<listStockMPTmp.size();k++)
  		   							{
  		   								StockMP stockmp=listStockMPTmp.get(k);
  		   								
  		   								DetailMouvementStock detailMouvementStock=new DetailMouvementStock();
  		   								
  		   								detailMouvementStock.setDateCreation(dateChooserMP.getDate());
  		   								detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
  		   								detailMouvementStock.setInitial(stockmp.getStock());
  		   								detailMouvementStock.setUtilisateurCreation(utilisateur);
  		   								detailMouvementStock.setCharge(BigDecimal.ZERO);
  		   								detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
  		   								detailMouvementStock.setReception(BigDecimal.ZERO);
  		   								detailMouvementStock.setSorties(BigDecimal.ZERO);
  		   								detailMouvementStock.setStockFinaldb(stockmp.getStock());
  		   								detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
  		   								detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
  		   						    	detailMouvementStock.setRetour(BigDecimal.ZERO);
  		   								detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobalTmp);
  		   								listDetailMouvementStock.add(detailMouvementStock);
  		   									
  		   								
  		   								
  		   							}
  		   					       mouvementStockGlobalTmp.setDateCreation(dateChooserMP.getDate());
  		   					       mouvementStockGlobalTmp.setDateMouvement(dateChooserMP.getDate());
  		   				           mouvementStockGlobalTmp.setDepot(depot);
  		   			               mouvementStockGlobalTmp.setMagasin(magasin);
  		   		                   mouvementStockGlobalTmp.setUtilisateurCreation(utilisateur);
  		                       	   mouvementStockGlobalTmp.setDetailMouvementStock(listDetailMouvementStock);
  		                           mouvementStockGlobalDAO.add(mouvementStockGlobalTmp);
  		   						
  		   							
		                	   JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ","Bravo",JOptionPane.INFORMATION_MESSAGE);
		                	   initialiserMP();
		                	   initialiserdepot();
		                	   InitialiseTableau();
		                	   listStockMPAfficher.clear();
		                	   listStockMP.clear();
		                   }
		                       
		                 		 
		                 	 }

		                      }
		               
		               else{
		                    	  
		                    	  JOptionPane.showMessageDialog(null, "Veuillez créer Journnée SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		                    	  return;
		                      }
		                    	 

             
		                  ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Destination et Modifier Transfere  entrer Charger et leStock Finale  ////////////////////////////////

		               if(existe==true)
		               {
		            	   
		            	   for(int j=0;j<listDetailMouvementStock.size();j++)
			               {
			               MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
			               DetailMouvementStock detailMouvementStockDestination=listDetailMouvementStock.get(j);
			               MatierePremier matierepremiereTmp=mapMatierePremierTmp.get(matierepremiere.getCode());
			               
			            if(matierepremiereTmp!=null)
			               {
			            	StockMP stock=stockMPDAO.findStockByMagasinMP(matierepremiereTmp.getId(), magasin.getId());

			          	  detailMouvementStockDestination.setInitial(stock.getStock());
			          	  detailMouvementStockDestination.setStockFinaldb((detailMouvementStockDestination.getInitial().add(detailMouvementStockDestination.getReception()).add(detailMouvementStockDestination.getTransfertEntrees()).subtract((detailMouvementStockDestination.getSorties().add(detailMouvementStockDestination.getTransfertSorties()).add(detailMouvementStockDestination.getCharge()).add(detailMouvementStockDestination.getChargeSupplementaire())))).add(detailMouvementStockDestination.getRetour()));

			                listDetailMouvementStock.set(j, detailMouvementStockDestination);


			              }


			              }
			                  
			                  
			                if( mouvementStockGlobal!=null)
			                {
			                	
			              	  mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);

			                 mouvementStockGlobalDAO.edit(mouvementStockGlobal);
			                 JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ");
			                 initialiserMP();
		                	   initialiserdepot();
		                	   InitialiseTableau();
		                	   listStockMPAfficher.clear();
		                	   listStockMP.clear();

			                   }


			                  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							
							
							
							 
		            	   
		               }
		               */
						  String patternYear = "yyyy";
						  String patternDate = "yyyy-MM-dd";
						  SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
		  					SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);
		  					String year = simpleDateFormatyear.format(dateChooserMP.getDate());
		               
						String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_INITIAL);
						Date date=null;
						try {
							date=simpleDateFormatDate.parse(year+"-01-01");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						transferStock.setCodeTransfer(codeTransfert);
						transferStock.setCreerPar(utilisateur);
						transferStock.setDate(date);
						transferStock.setDateTransfer(dateChooserMP.getDate());
						transferStock.setDepot(depot);
						//transferStock.setListDetailTransferMP(listDetailTransferStockMP);
						transferStock.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
						transferStockMPDAO.add(transferStock);
						for(int i=0;i<listDetailTransferStockMP.size();i++)
						{
							detailTransferStockMPDAO.add(listDetailTransferStockMP.get(i));
							
							
							MatierePremier matierePremier=listDetailTransferStockMP.get(i).getMatierePremier();
							if(matierePremier.getPrix()==null)
							{
								matierePremier.setPrix(listDetailTransferStockMP.get(i).getPrixUnitaire());
								matierepremieredao.edit(matierePremier);
							}
							
						}
						 JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ");
		                 initialiserMP();
	                	   initialiserdepot();
	                	   InitialiseTableau();
	                	   listStockMPAfficher.clear();
	                	   listDetailTransferStockMP.clear();
	                	   listStockMP.clear();
		             
						
					}
				
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(441, 766, 112, 24);
		add(buttonvalider);
		buttonvalider.setVisible(false);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations MP");
		titledSeparator_1.setBounds(10, 257, 1200, 30);
		add(titledSeparator_1);
		titledSeparator_1.setVisible(false);
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1200, 97);
		add(layeredPane_1);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(439, 30, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(505, 31, 209, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
     				if(depot!=null)
     				{
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
     					 listMagasinDechetMP= depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
		     				if(listMagasin.size()!=0 || listMagasinDechetMP.size()!=0 )
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
  		     				{
  		     					Magasin magasin=listMagasin.get(i);
  		     					combomagasin.addItem(magasin.getLibelle());
  		     					mapMagasin.put(magasin.getLibelle(), magasin);
  		     					i++;
  		     				}
		     					
		     					  int j=0;
					  		      	while(j<listMagasinDechetMP.size())
					  		      		{	
					  		      			Magasin magasin=listMagasinDechetMP.get(j);
					  		      		combomagasin.addItem(magasin.getLibelle());
		  		     					mapMagasin.put(magasin.getLibelle(), magasin);
					  		      			j++;
					  		      		}
		     					
		     					
		     					
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
     					
     				}else
     				{
     					combomagasin.removeAllItems();
     				}
     				
     				
    	 		 }
    	 	
 	}
		  });
		  
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
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(756, 31, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(836, 32, 183, 24);
		  layeredPane_1.add(combomagasin);
		  
		  JLabel label = new JLabel("Date  :");
		  label.setBounds(58, 33, 62, 24);
		  layeredPane_1.add(label);
		  
		   dateChooserMP = new JDateChooser();
		  dateChooserMP.setLocale(Locale.FRANCE);
		  dateChooserMP.setDateFormatString("yyyy");
		  dateChooserMP.setBounds(162, 30, 181, 26);
		  layeredPane_1.add(dateChooserMP);
		 
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1200, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(341, 418, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					boolean existe=false;
					BigDecimal quantitetotal=BigDecimal.ZERO;
					BigDecimal newprix=BigDecimal.ZERO;
					
					
					 if(dateChooserMP.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combodepot.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combomagasin.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
							
						}else if(combomp.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodemp.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if(txtprix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir le Prix MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} 
							
							
							
							
							
							else 	{
								  Depot depot=mapDepot.get(combodepot.getSelectedItem());
								Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
								MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
								
								
									if(  (new BigDecimal(txtprix.getText()).compareTo(BigDecimal.ZERO)<0))
									{
										JOptionPane.showMessageDialog(null, "le Prix MP doit etre superieur ou égale à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									
									}
									
								
							
								
									
							
							
								
								
								
								
								StockMP stockmp=null;
								
								
								
	FournisseurMP fournisseurMP=null;
	  		  					
		  		  				
	  		  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					
	  		  					if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		  					{
	  		  						if(fournisseurMP==null)
	  		  						{
	  		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur EN Vrac SVP");
	  		  							return;
	  		  						}
	  		  					}
	  		  					
	  		  				if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  		  					{
	  		  				 stockmp=stockMPDAO.findStockByMagasinMPByFournisseurMP (mp.getId(), magasin.getId(),fournisseurMP.getId());
	  		  					
  		  					}else
  		  					{
  		  					 stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
  		  					}
	  		  				
	  		  					
	  		  					
								
								
								/*
								  Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateChooserMP.getDate(), Constantes.ETAT_STATUT_OVERTE,depot.getId());
								if(journeeDestination==null)
								{
									 
			                    	  JOptionPane.showMessageDialog(null, "Veuillez créer Journnée SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			                    	  return;
								}
								*/
								
	  		  			DetailTransferStockMP detailtransferStockMP=null;
	  		  				
	  		  		if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  					{
	  		  		 detailtransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPByMPByFournisseurInitialiser(mp, ETAT_TRANSFER_STOCK_INITIAL,depot,magasin,fournisseurMP);
	  		  			
	  					}else
	  					{
	  						 detailtransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPByMPInitialiser(mp, ETAT_TRANSFER_STOCK_INITIAL,depot,magasin);
	  					}
								
								
								if(detailtransferStockMP!=null)
								{
									
									JOptionPane.showMessageDialog(null, "La Matière Première déja initialiser SVP !!!!!");
									return;
								}
								
								
								
							
								
								
								
								
								for(int j=0;j<listStockMPAfficher.size();j++)
								{
									
									
										
										if(listStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
										{
											if(listStockMPAfficher.get(j).getFournisseurMP()!=null)
											{
												if(fournisseurMP!=null)
												{
													if(listStockMPAfficher.get(j).getFournisseurMP().getId()==fournisseurMP.getId())
													{
														existe=true;
													}
												}
												
											}else
											{
												if(fournisseurMP==null)
												{
													
														existe=true;
													
												}
												
												
											}
											
										}
										
									
								
									
								
								
								
								
								}
								
								if(existe==false)
								{
									DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
									
										StockMP	stocktmp=new StockMP();
										stocktmp.setMatierePremier(mp);
										stocktmp.setMagasin(magasin);
										if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		  					{
											
											stocktmp.setFournisseurMP(fournisseurMP);
											detailTransferStockMP.setFournisseur(fournisseurMP);
											
			  		  					}else
			  		  					{
			  		  						
			  		  					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
										{
			  		  						if(fournisseurMP!=null)
			  		  						{
			  		  						stocktmp.setFournisseurMP(fournisseurMP);
											detailTransferStockMP.setFournisseur(fournisseurMP);
			  		  						}
			  		  						
										}

			  		  						
			  		  					}
										
										if(mp.getType().equals(Constantes.MP_CLIENT))
										{
											stocktmp.setPrixUnitaire(BigDecimal.ZERO);
										
										}else
										{
											stocktmp.setPrixUnitaire(new BigDecimal(txtprix.getText()));
										}
										
										
										
										
										
										stocktmp.setStock(new BigDecimal(txtquantite.getText()));	
										listStockMPAfficher.add(stocktmp);
									
								
								detailTransferStockMP.setMagasinDestination(magasin);
								detailTransferStockMP.setMatierePremier(mp);
								
								if(mp.getType().equals(Constantes.MP_CLIENT))
								{
									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
									
								
								}else
								{
									detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtprix.getText()));
								}
								
								detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
								detailTransferStockMP.setTransferStockMP(transferStock);
								listDetailTransferStockMP.add(detailTransferStockMP);
								
								
								
								
									
								}else
								{
									JOptionPane.showMessageDialog(null , "La Matière première existe deja dans la liste des MP veuillez le modifier SVP !!!!");
									return;
									
								}
								
								
							
									if(stockmp!=null)
									{
										
										quantitetotal=stockmp.getStock().add(new BigDecimal(txtquantite.getText()));
										
									//	newprix=((stockmp.getPrixUnitaire().multiply(stockmp.getStock())).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))))).divide(quantitetotal, 6, RoundingMode.HALF_UP);
									//	stockmp.setPrixUnitaire(newprix);
										stockmp.setStock(quantitetotal);
										listStockMP.add(stockmp);
										if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
										{
											stockMPDAO.edit(stockmp);
										}
										
								  	 
								  	    afficher_tableMP(listStockMPAfficher);
										mapMatierePremierTmp.put(mp.getCode(), mp);
										mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
										   initialiserMP();
									}else
									{
										StockMP stockmpTMP=new StockMP();
										stockmpTMP.setMagasin(magasin);
										stockmpTMP.setMatierePremier(mp);
										if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		  					{
											stockmpTMP.setFournisseurMP(fournisseurMP);
			  		  					}else
			  		  					{
			  		  						
			  		  					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
										{
			  		  						if(fournisseurMP!=null)
			  		  						{
			  		  						stockmpTMP.setFournisseurMP(fournisseurMP);
											
			  		  						}
			  		  						
										}

			  		  						
			  		  					}
										
										
										if(mp.getType().equals(Constantes.MP_CLIENT))
										{
											stockmpTMP.setPrixUnitaire(BigDecimal.ZERO);
										
										}else
										{
											stockmpTMP.setPrixUnitaire(new BigDecimal(txtprix.getText()));
										}
										
										stockmpTMP.setStock(new BigDecimal(txtquantite.getText()));
										stockmpTMP.setStockMin(BigDecimal.ZERO);
										stockmpTMP.setQuantiteCommande(BigDecimal.ZERO);
										listStockMP.add(stockmpTMP);
										if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
										{
											stockMPDAO.add(stockmpTMP);
										}
										
										afficher_tableMP(listStockMPAfficher);
										 mapMatierePremierTmp.put(mp.getCode(), mp);
										 mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
										 initialiserMP();
									}
									
								
								
							}
						        
							
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "la Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
		
				}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		btnAjouter.setVisible(false);
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(472, 417, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiserMP();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser.setVisible(false);
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserdepot();;
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		
		   		try {
		   			
		   			

			   		
			   		if(tableMP.getSelectedRow()!=-1)
			   		{
			   		

			   			boolean trouve=false;
			   			BigDecimal quantitetotal=BigDecimal.ZERO;
			   			BigDecimal newprix=BigDecimal.ZERO;
			   			BigDecimal oldQuantite=BigDecimal.ZERO;
			   			BigDecimal oldPrix=BigDecimal.ZERO;
			   			
			   					if(combomp.getSelectedItem().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtcodemp.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtquantite.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
			   					{
			   						JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					
			   					}else if(txtprix.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir le Prix MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					} 
			   					
			   					
			   					else {
			   						Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			   						MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
			   						
			   					
										if(  (new BigDecimal(txtprix.getText()).compareTo(BigDecimal.ZERO)<0))
										{
											JOptionPane.showMessageDialog(null, "le Prix MP doit etre superieur ou égale à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
										
										}
										
									
			   						
			   						
			   						
			   						
			   						FournisseurMP fournisseurMP=null;
		  		  					
			  		  				
		  		  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					
		  		  					if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  					{
		  		  						if(fournisseurMP==null)
		  		  						{
		  		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur EN Vrac SVP");
		  		  							return;
		  		  						}
		  		  					}
			   						
			   						
			   					
			   						
			   						for(int i=0;i<listStockMPAfficher.size();i++)
			   						{
			   							
			   							if(i!= tableMP.getSelectedRow())
			   							{
			   								
			   								

											
											
											
											if(listStockMPAfficher.get(i).getMatierePremier().getNom().equals(mp.getNom()))
											{
												if(listStockMPAfficher.get(i).getFournisseurMP()!=null)
												{
													if(fournisseurMP!=null)
													{
														if(listStockMPAfficher.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
														{
															trouve=true;
														}
													}
													
												}else
												{
													if(fournisseurMP==null)
													{
														
														trouve=true;
														
													}
													
													
												}
												
											}
											
										
									
			   							}
			   							
			   						}
			   						
			   						
			   						
			   						
			   						
			   						
			   						
			   						if(trouve==false)
			   						{
			   						 StockMP stockmptmp =null;
			   							
			   							if(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		  					{
			   							  stockmptmp=stockMPDAO.findStockByMagasinMPByFournisseurMP(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getFournisseurMP().getId());

			  		  					}else
			  		  					{
			  		  						
			  		  					 stockmptmp=stockMPDAO.findStockByMagasinMP(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId());

			  		  					}
			   							
							        	
			   						 /// modifier list detail transfer Stock PF
			   						 DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMP.get(tableMP.getSelectedRow());
			   						
			   						detailtransferstockmp.setMagasinSouce(magasin);
			   						detailtransferstockmp.setMatierePremier(mp);
			   						if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  					{
			   							detailtransferstockmp.setFournisseur(fournisseurMP);
		  		  					}else
		  		  					{
		  		  					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
									{
		  		  						
		  		  						if(fournisseurMP!=null)
		  		  						{
		  		  						detailtransferstockmp.setFournisseur(fournisseurMP);
		  		  						}
		  		  						
									}else
									{
										detailtransferstockmp.setFournisseur(null);
									}
		  		  					
		  		  					
		  		  					}
			   						
			   						if(mp.getType().equals(Constantes.MP_CLIENT))
									{
			   							detailtransferstockmp.setPrixUnitaire(BigDecimal.ZERO);
										
									
									}else
									{
										detailtransferstockmp.setPrixUnitaire(new BigDecimal(txtprix.getText()));
									}
			   					
			   						
			   						
			   						detailtransferstockmp.setQuantite(new BigDecimal(txtquantite.getText()));
			   					
									listDetailTransferStockMP.set(tableMP.getSelectedRow(), detailtransferstockmp);
									
									////////////////////////////////////////////////////////
						        	 
						        	 oldQuantite=stockmptmp.getStock().subtract(listStockMPAfficher.get(tableMP.getSelectedRow()).getStock());
						        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
						        	 {
						        		 oldQuantite=BigDecimal.ZERO; 
						        		 oldPrix=stockmptmp.getPrixUnitaire();
						        		
						        	 }else
						        	 {
							        	 oldPrix=((stockmptmp.getPrixUnitaire().multiply((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().add(oldQuantite)))).subtract((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().multiply(listStockMPAfficher.get(tableMP.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
		 
						        	 }
						        	
						        	 stockmptmp.setStock(oldQuantite);
							        	
						     		if(mp.getType().equals(Constantes.MP_CLIENT))
									{
			   							
			   							stockmptmp.setPrixUnitaire(BigDecimal.ZERO);
										
									
									}else
									{
										stockmptmp.setPrixUnitaire(oldPrix);
									}
						        	 
						        	 
						        	 
						        	 if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
										{
						        		 stockMPDAO.edit(stockmptmp);
										}
						        	
						        	 mapMatierePremierTmp.remove(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getCode());
			   						mapQuantiteDetailMouvement.remove(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId());
						        	
									StockMP stockmp=null;
									if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  					{
										stockmp=stockMPDAO.findStockByMagasinMPByFournisseurMP(mp.getId(), magasin.getId(),fournisseurMP.getId());
										
		  		  					}else
		  		  					{
		  		  						
		  		  					stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
		  		  					
		  		  					}
											
									
									StockMP stockMPAffichage=listStockMPAfficher.get(tableMP.getSelectedRow());
									stockMPAffichage.setMatierePremier(mp);
									stockMPAffichage.setMagasin(magasin);
									if(fournisseurMP!=null)
	  		  						{
										stockMPAffichage.setFournisseurMP(fournisseurMP);
	  		  						}else
	  		  						{
	  		  						stockMPAffichage.setFournisseurMP(null);
	  		  						}
									
									if(mp.getType().equals(Constantes.MP_CLIENT))
									{
			   							
										stockMPAffichage.setPrixUnitaire(BigDecimal.ZERO);
										
									
									}else
									{
										stockMPAffichage.setPrixUnitaire(new BigDecimal(txtprix.getText()));
									}
									
									
									
									
									stockMPAffichage.setStock(new BigDecimal(txtquantite.getText()));
									listStockMPAfficher.set(tableMP.getSelectedRow(), stockMPAffichage);
						        	 if(stockmp!=null)
						        	 {
						        		 
						        		 quantitetotal=stockmp.getStock().add(new BigDecimal(txtquantite.getText()));
											
											newprix=((stockmp.getPrixUnitaire().multiply(stockmp.getStock())).add((new BigDecimal(txtprix.getText()).multiply(new BigDecimal(txtquantite.getText()))))).divide(quantitetotal, 6, RoundingMode.HALF_UP);
											
											
											if(mp.getType().equals(Constantes.MP_CLIENT))
											{
					   							
												stockmp.setPrixUnitaire(BigDecimal.ZERO);
												
											
											}else
											{
												stockmp.setPrixUnitaire(newprix);
											}
											
											stockmp.setStock(quantitetotal);
											listStockMP.set(tableMP.getSelectedRow(), stockmp);
											 if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
												{
												 stockMPDAO.edit(stockmp);
												}
											
									  	  
									  	    afficher_tableMP(listStockMPAfficher);
									  	  mapMatierePremierTmp.put(mp.getCode(), mp);
									  	mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
									    initialiserMP();
						        		 
						        	 }else
						        	 {
						        		  listStockMP.remove(tableMP.getSelectedRow()); 
						        		  StockMP stockmpTMP=new StockMP();
											stockmpTMP.setMagasin(magasin);
											stockmpTMP.setMatierePremier(mp);
											if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		  					{
												stockmpTMP.setFournisseurMP(fournisseurMP);
				  		  					}
											if(fournisseurMP!=null)
			  		  						{
												stockMPAffichage.setFournisseurMP(fournisseurMP);
			  		  						}else
			  		  						{
			  		  						stockMPAffichage.setFournisseurMP(null);
			  		  						}
											
											if(mp.getType().equals(Constantes.MP_CLIENT))
											{
					   							
												stockmpTMP.setPrixUnitaire(BigDecimal.ZERO);
												
											
											}else
											{
												stockmpTMP.setPrixUnitaire(new BigDecimal(txtprix.getText()));
											}
											
											stockmpTMP.setStock(new BigDecimal(txtquantite.getText()));
											stockmpTMP.setStockMin(BigDecimal.ZERO);
											stockmpTMP.setQuantiteCommande(BigDecimal.ZERO);
											listStockMP.add(stockmpTMP);
											if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
											{
												stockMPDAO.add(stockmpTMP);
											}
											
											
											 afficher_tableMP(listStockMPAfficher);
											 mapMatierePremierTmp.put(mp.getCode(), mp);
											 mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
											 initialiserMP();
						        	 }
						        	 
			   							
			   							
			   							
			   						}else
			   						{
			   							JOptionPane.showMessageDialog(null, "MP deja existant dans la liste des MP Veuillez le Modifier SVP !!!!");
			   							
			   							return;
			   						}
			   						
			   						
			   						
			   					}
			   				
			   		}
			   		
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showConfirmDialog(null, "la Quantité et le Prix doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnModifier.setVisible(false);
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal oldPrix=BigDecimal.ZERO;
				BigDecimal oldQuantite=BigDecimal.ZERO;
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());

		  		if(tableMP.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la matiere premiere SVP  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							 StockMP stockmp =null;

							
							if(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  		  					{
								
 								 stockmp = stockMPDAO.findStockByMagasinMPByFournisseurMP (listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId(),listStockMPAfficher.get(tableMP.getSelectedRow()).getFournisseurMP().getId());

								
  		  					}else
  		  					{
  								 stockmp = stockMPDAO.findStockByMagasinMP(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId());

  		  					}
				        	
							 
							 
							 oldQuantite=stockmp.getStock().subtract(listStockMPAfficher.get(tableMP.getSelectedRow()).getStock());
				        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
				        	 {
				        		 oldQuantite=BigDecimal.ZERO; 
				        		 oldPrix=stockmp.getPrixUnitaire();
				        	 }else
				        	 {
					        	 oldPrix=((stockmp.getPrixUnitaire().multiply((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().add(oldQuantite)))).subtract((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().multiply(listStockMPAfficher.get(tableMP.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
 
				        	 }
				        	 stockmp.setStock(oldQuantite);
				        	 
				        	 
				        		if(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getType().equals(Constantes.MP_CLIENT))
								{
		   							
				        			stockmp.setPrixUnitaire(BigDecimal.ZERO);
									
								
								}else
								{
									 stockmp.setPrixUnitaire(oldPrix);
								}
				        	
				        	 
				        	 
				        	 if(!magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
								{
				        		 stockMPDAO.edit(stockmp);
								}
							
							listStockMP.set(tableMP.getSelectedRow(), stockmp);
							listStockMPAfficher.remove(tableMP.getSelectedRow());
							mapMatierePremierTmp.remove(listStockMP.get(tableMP.getSelectedRow()).getMatierePremier().getCode());
							mapQuantiteDetailMouvement.remove(listStockMP.get(tableMP.getSelectedRow()).getMatierePremier().getId());
							listDetailTransferStockMP.remove(tableMP.getSelectedRow());
							afficher_tableMP(listStockMPAfficher);
							
							JOptionPane.showMessageDialog(null, "MP Supprimer avec succée !!!!!!!");
							 initialiserMP();
						}
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  		}
		  		
		  		
		  		
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1137, 552, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		  btnSupprimer.setVisible(false);
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1137, 522, 73, 24);
		add(btnModifier);
		
		txtcodemp.setText(Constantes.CODE_MP);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setBounds(1021, 39, 39, 26);
		layeredPane.add(lblPrix);
		
		txtprix = new JTextField();
		txtprix.setColumns(10);
		txtprix.setBounds(1062, 39, 112, 26);
		layeredPane.add(txtprix);
		
		JLabel label_1 = new JLabel("Fournisseur : ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(520, 40, 91, 24);
		layeredPane.add(label_1);
		
		comboFournisseur = new JComboBox();
		comboFournisseur.setSelectedIndex(-1);
		comboFournisseur.setBounds(598, 41, 232, 24);
		layeredPane.add(comboFournisseur);
		
		comboFournisseur.addItem("");
		 JFileChooser fileDialog = new JFileChooser();
		JButton btnOuvrire = new JButton("Ouvrire");
		btnOuvrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

  				

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
               txtlien.setText(file.getAbsolutePath());
  			
			}
		});
		btnOuvrire.setBounds(733, 196, 130, 30);
		add(btnOuvrire);
		
		txtlien = new JTextField();
		txtlien.setEditable(false);
		txtlien.setBounds(282, 199, 406, 25);
		add(txtlien);
		txtlien.setColumns(10);
		
		JButton btnAjouterPrix = new JButton("Ajouter Prix");
		btnAjouterPrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterPrixMP();
			}
		});
		btnAjouterPrix.setBounds(113, 196, 130, 30);
		add(btnAjouterPrix);
		for(int k=0;k<listFournisseur.size();k++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(k);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		
		layeredPane.setVisible(false);
		tableMP.setVisible(false);
		}
	

	
	void initialiserdepot()
	{
		dateChooserMP.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboFournisseur.setSelectedItem("");;
		}

	void initialiserMP()
	{
		txtcodemp.setText("");
		combomp.setSelectedItem("");
	  txtprix.setText("");
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
         combofamille.setSelectedItem("");
		 combosousfamille.setSelectedIndex(-1);
		 
		 txtcodemp.setText(Constantes.CODE_MP);
		 comboFournisseur.setSelectedItem("");
		
	}
	
	void InitialiseTableau()
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première","Fournisseur", "Prix Unitaire", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableMP.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableMP.getColumnModel().getColumn(2).setPreferredWidth(94);
	       tableMP.getColumnModel().getColumn(3).setPreferredWidth(94);
	       tableMP.getColumnModel().getColumn(4).setPreferredWidth(94);
		
	
}
	
	
	void AjouterPrixMP()
	{


		if(dateChooserMP.getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez Entrer La date SVP");return;
		}
		 
		
		if(!txtlien.getText().equals(""))
			{
			
			 File fileName = new File(txtlien.getText());
				
			 
				try {
					
			 
						
				       FileInputStream ExcelFile = new FileInputStream(fileName);
				      
				       
			
				       
				      
				       
				       ExcelWBook = new XSSFWorkbook(ExcelFile);
				       
				      ExcelWSheet = ExcelWBook.getSheetAt(0);
				      
				      
				      
				      
	 			      
				      
				    
				      
	int t=0;							  				      
				      
	int id=0;		


	 

	for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
	{



	try {


	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();

	CellCodeMP=ExcelWSheet.getRow(i).getCell(0);
	CellPrix=ExcelWSheet.getRow(i).getCell(2);
	 

	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellCodeMP);

	if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
	{
	 
	MatierePremier mp=matierepremieredao.findByCode(formattedCellStr);
	 if(mp!=null)
	 {
		 mp.setPrixByYear( DateUtils.getAnnee(dateChooserMP.getDate()), new BigDecimal(CellPrix.getNumericCellValue())); 
		 mp.setPrixInitialByYear( DateUtils.getAnnee(dateChooserMP.getDate()), new BigDecimal(CellPrix.getNumericCellValue()));
		 matierepremieredao.edit(mp);
		 
	 }


	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	t=t+1;







	} catch (Exception ex) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
	JOptionPane.showMessageDialog(null, ex.getMessage()); 
	return;
	}









	}




							
							
						
					
					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				  					
					
					
					JOptionPane.showMessageDialog(null, "OK");
					
				
				
				
				
			} catch (Exception exx) {
				// TODO Auto-generated catch block
				 JOptionPane.showMessageDialog(null, exx.getMessage()); 
			}
			
			
			
			}
		
		 	

			
		
	}
	
	void afficher_tableMP(List<StockMP> listStockMP)
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première","Fournisseur","Quantite","Prix Unitaire"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		int i=0;
		 
		while(i<listStockMP.size())
		{	
		StockMP stockmp=listStockMP.get(i);
		
		String fournisseur="";
		
		if(stockmp.getFournisseurMP()!=null)
		{
			fournisseur=stockmp.getFournisseurMP().getCodeFournisseur();
		}
			
			Object []ligne={stockmp.getMatierePremier().getCode(),stockmp.getMatierePremier().getNom(),fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(stockmp.getStock()),stockmp.getPrixUnitaire()};

			modeleMP.addRow(ligne);
			i++;
		}
}
	}



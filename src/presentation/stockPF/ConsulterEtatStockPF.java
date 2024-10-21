package presentation.stockPF;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ExporterTableVersExcel;
import util.NumberUtils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.Magasin;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Utilisateur;


public class ConsulterEtatStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private List<EtatStockPF> listEtatStockPFImprimer =new ArrayList<EtatStockPF>();
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	
	//**********************************************************************listes Mouvemement Stock***************************************************
	
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
	
	
	
	//*************************************************************************************************************************************************
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 private JComboBox comboarticle;
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferProduitFiniDAO detailTransferStockPFDAO;

	private JTextField txtlibelle=new JTextField();
	 JComboBox comboFamille = new JComboBox();
	  JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();

	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	
	private ArticlesDAO ArticleDAO;
	private JTextField txtcodearticle;
	String titre="";
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 1062);
      
	
        try{ 
        	
        	
        	
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao= new DepotDAOImpl();
         	mouvementStockGlobaleDAO= new MouvementStockGlobalDAOImpl();
         	ArticleDAO= new ArticlesDAOImpl();
         	
         	detailTransferStockPFDAO= new DetailTransferProduitFiniDAOImpl();
         listArticle=ArticleDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article","Production PF" ,"MP vers PF","Sortie PF","Quantite Finale"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
     
       
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setAutoCreateRowSorter(true);
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 1465, 587);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Etat de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/*
				BigDecimal totalmontantinitial=BigDecimal.ZERO;
				BigDecimal totalmontantachat=BigDecimal.ZERO;
				BigDecimal totalmontantproduction=BigDecimal.ZERO;
				BigDecimal totalmontantvente=BigDecimal.ZERO;
				BigDecimal totalmontantavoir=BigDecimal.ZERO;
				BigDecimal totalmontantfinale=BigDecimal.ZERO;
				
				 if(listEtatStockPFImprimer.size()!=0)
				 {
					 
					 int i=0;
					 while(i<listEtatStockPFImprimer.size())
					 {
						 EtatStockPF etatstockpf=listEtatStockPFImprimer.get(i);
						 totalmontantinitial=totalmontantinitial.add(etatstockpf.getMontantInitial()) ;
						 totalmontantachat=totalmontantachat.add(etatstockpf.getMontantAchat());
						 totalmontantproduction=totalmontantproduction.add(etatstockpf.getMontantProduction());
						 totalmontantvente=totalmontantvente.add(etatstockpf.getMontantSortie());	
						 totalmontantavoir=totalmontantavoir.add(etatstockpf.getMontantAvoir());
						 totalmontantfinale=totalmontantfinale.add(etatstockpf.getMontantFinale());
						 
						 
						 
						 i++;
					 }
					 
					 
					 
						Map parameters = new HashMap();


						parameters.put("totalmontantinitial",totalmontantinitial );
						parameters.put("totalmontantachat",totalmontantachat );	
						parameters.put("totalmontantproduction",totalmontantproduction);	
						parameters.put("totalmontantvente",totalmontantvente);	
						parameters.put("totalmontantavoir",totalmontantavoir);	
						parameters.put("totalmontantfinale",totalmontantfinale);							
						
						
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatStockPF(listEtatStockPFImprimer,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			*/}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(698, 793, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		
	    		listEtatStockPF.clear();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	    	
	    		BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	    		BigDecimal quantiteTotalSortie=new BigDecimal(0);
	    		
	    		BigDecimal quantiteTotalproduction=new BigDecimal(0);
	    	
	    		BigDecimal quantiteTotalFinale=new BigDecimal(0);
	    		
	    		boolean trouve=false;
	    		//FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	    		Articles article=mapArticle.get(comboarticle.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			String d2=sdf.format(dateChooserfin.getDate());
		    			
		    		if(!d1.equals(d2))
		    		{
		    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
		    			{
		    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
		    				return;
		    			}
		    			
		    		}
		    		
		    		if(article!=null)
		    		{
		    			titre="Etat de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Etat de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    				    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d1;
		    		}
		    		
		    	Client client=null;

		    			listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
		    			listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
		    			
	
		    			listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
		    			listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
		    			
		    		
		    			listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		    			listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		    		
		    		
		    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		    		
		    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		    		{
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		    			EtatStockPF etatstock=new EtatStockPF();
		    			etatstock.setArticle(detailtransferstockpf.getArticle());
		    			
		    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
		    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
		    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
		    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
		    			listEtatStockPF.add(etatstock);
		    			
		    		}
		    		
		    		
		    		
		    		
		    		// calculer la quantite production
		    		
		    		
		    	for(int j=0;j<listDetailTransferStockPFProductionGroupebyPF.size();j++)
		    	{
		    		
		    		quantiteTotalproduction=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFProduction.size();k++)
		    	{
		    		
		    		if(listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFProduction.get(k).getArticle()) )
		    		{
		    			
		    			quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalproduction.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle()))
			    			{
			    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
			    				etatstockpf.setQuantiteProduction(quantiteTotalproduction);
			    				
			    				listEtatStockPF.set(i, etatstockpf);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
		    		
		    	}
		    	
	
		   		// calculer la quantite Entrer
	    		
	    		
		    	for(int j=0;j<listDetailTransferStockPFEntrerGroupebyPF.size();j++)
		    	{
		    		
		    		quantiteTotalEntrer=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFEntrer.size();k++)
		    	{
		    		
		    		if(listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFEntrer.get(k).getArticle()) )
		    		{
		    			
		    			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle()))
			    			{
			    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
			    				etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
			    				
			    				listEtatStockPF.set(i, etatstockpf);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
		    		
		    	}
		    	
		    	
// calculer la quantite Sortie
	    		
	    		
		    	for(int j=0;j<listDetailTransferStockPFSortieGroupebyPF.size();j++)
		    	{
		    		
		    		quantiteTotalSortie=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFSortie.size();k++)
		    	{
		    		
		    		if(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFSortie.get(k).getArticle()) )
		    		{
		    			
		    			quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalSortie.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle()))
			    			{
			    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
			    				etatstockpf.setQuantiteSortie(quantiteTotalSortie);
			    				
			    				listEtatStockPF.set(i, etatstockpf);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
		    		
		    	}
		    	
		    	
		    	// Calculer Stock Finale
		    	
		    	
			   	for(int i=0;i<listEtatStockPF.size();i++)
		    	{
			   	 BigDecimal prixMoyen=BigDecimal.ZERO;
			   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
			   		   
		    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
		    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteEntrer().add(etatstockpf.getQuantiteProduction())).subtract(etatstockpf.getQuantiteSortie()));
		    			
		    			listEtatStockPF.set(i, etatstockpf);
		    		
		    	}
			   	
			
			   	
		    	afficher_tableEtatStock(listEtatStockPF);
		    	
		    	
	    			
	    		}else
	    		{
	    			

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    	
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(10, 57, 1491, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(101, 11, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(274, 9, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(348, 9, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     comboarticle = new JComboBox();
	     comboarticle.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	 			if(!comboarticle.getSelectedItem().equals(""))
		 		{
		 			Articles article=mapArticle.get(comboarticle.getSelectedItem());
		 			txtcodearticle.setText(article.getCodeArticle());
		 			
		 		  				 			
		 		}else
		 		{
//txtcodearticle.setText(" ");
		 			
		 		}
		 	
	 		
	     	}
	     });
	    comboarticle.setBounds(998, 10, 218, 27);
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
	    
	    
	    JLabel label_2 = new JLabel("Libelle :");
	    label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_2.setBounds(953, 10, 45, 26);
	    layeredPane.add(label_2);
	    
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
	    txtcodearticle.setBounds(850, 10, 93, 26);
	    layeredPane.add(txtcodearticle);
	    
	    JLabel lblCodeMp = new JLabel("Code Article :");
	    lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblCodeMp.setBounds(778, 10, 74, 26);
	    layeredPane.add(lblCodeMp);
	    
	    JLabel label_3 = new JLabel("Depot  :");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_3.setBounds(527, 8, 45, 26);
	    layeredPane.add(label_3);
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(568, 11, 202, 27);
	    layeredPane.add(combodepot);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
	          
	           comboFamille = new JComboBox();
	          comboFamille.setBounds(1280, 11, 201, 27);
	          layeredPane.add(comboFamille);
	          
	          JLabel lblFamille = new JLabel("Famille  :");
	          lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblFamille.setBounds(1226, 11, 57, 26);
	          layeredPane.add(lblFamille);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		comboFamille.setSelectedItem("");
	     		comboarticle.setSelectedItem("");
	     		txtcodearticle.setText("");
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat de Stock PF "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat de Stock PF ";
	    		ExporterTableVersExcel.tabletoexcelEtatStockPF(tableEtatStock, titre,titrefeuille);
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(565, 793, 123, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	 /*   comboFamille.addItem("");
	  for(int k=0;k<listFamilleArticlesF.size();k++)
	  {
		  FamilleArticlePF familleArticle=listFamilleArticlesF.get(k);
		  comboFamille.addItem(familleArticle.getLiblle());
		  mapFamilleArticles.put(familleArticle.getLiblle(), familleArticle);
	  }*/
	     if(utilisateur.getLogin().equals("admin"))
	 		  {
	 	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_PF);
	 			  int k=0;
	 		     	 combodepot.addItem("");
	 		     	while (k<listMagasin.size())
	 		     	{
	 		     		Magasin magasin=listMagasin.get(k);
	 		     		
	 		     		
	 			     			combodepot.addItem(magasin.getLibelle());
	 				     		
	 				     		mapMagasin.put(magasin.getLibelle(), magasin);
	 				     	
	 		     		k++;
	 		     		
	 		     	}
	 		      
	 		  }else
	 		  {
	 			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depot!=null)
	 			  {
	 				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
	 				  int k=0;
	 			     	 combodepot.addItem("");
	 			     	while (k<listMagasin.size())
	 			     	{
	 			     		Magasin magasin=listMagasin.get(k);
	 			     		
	 			     		
	 				     			combodepot.addItem(magasin.getLibelle());
	 					     		
	 					     		mapMagasin.put(magasin.getLibelle(), magasin);
	 					     	
	 			     		k++;
	 			     		
	 			     	}
	 				 
	 			  }
	 		  }
	    
	    
	    
		
		}
	
	

	
	
	

	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Production" ,"Entrer","Sortie","Quantite Finale"				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
	 
		
}
	
	
/*	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement", "Depot", "Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleMouvementStock);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
		
	
}*/
	
	
	
	void afficher_tableEtatStock(List<EtatStockPF> listEtatStockPF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Production PF" ,"MP Vers PF","Sortie PF","Quantite Finale"	
						}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		 
		while(i<listEtatStockPF.size())
		{	
		EtatStockPF EtatStockPF=listEtatStockPF.get(i);
		
			
				Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN)};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}



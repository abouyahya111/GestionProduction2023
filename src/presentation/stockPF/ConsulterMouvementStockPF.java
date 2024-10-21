package presentation.stockPF;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Utilisateur;


public class ConsulterMouvementStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleDetailMouvementStock;
	private DefaultTableModel	 modeleMouvementStock;
	
	private DefaultTableModel	 modelePF;
	

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticleSortie =new ArrayList<DetailTransferProduitFini>();
	
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();

	private List<Client> listClient =new ArrayList<Client>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, MatierePremier> mapMP= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 private JComboBox comboperation;
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFDAO;

private DetailTransferMPDAO detailTransferStockMPDAO;
private TransferStockMPDAO transferStockMPDAO;
	private JTextField txtlibelle=new JTextField();
	
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
	 JComboBox combodepot = new JComboBox();
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	private ClientDAO clientDAO;
	
	String titre="";
	private JTable tableMouvementStockPF;
	JComboBox comboclient = new JComboBox();
	String statut="";
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMouvementStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1485, 1137);
      
	
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
         	productionDAO=new ProductionDAOImpl();
        	detailTransferStockPFDAO= new DetailTransferProduitFiniDAOImpl();
        	mouvementStockGlobaleDAO= new MouvementStockGlobalDAOImpl();
        	clientDAO=new ClientDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
       tableEtatStock.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		
       		if(tableEtatStock.getSelectedRow()!=-1)
       		{
       			
       			if(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 1)==null)
       			{
       				return;
       				
       			}
       			
       			if(!tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 1).equals(0))
       			{
       				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
       				EtatStockPF etatstockpf=listEtatStockPF.get(tableEtatStock.getSelectedRow());
       				Articles article=etatstockpf.getArticle();
       			
       				Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
       				listMouvementStockPF.clear();
       				listDetailTransferStockPF.clear();
       				listDetailTransferStockPFGroupebyArticle.clear();
       				listDetailTransferStockPFGroupebyArticleSortie.clear();
       				listDetailTransferStockPFSortie.clear();
       				if(statut.equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE) || statut.equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
       				{
       					listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin,article);
    		    		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin,article);
       				}else if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
       				{
       					listDetailTransferStockPFSortie=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransferSortie(magasin,article);
    		    		listDetailTransferStockPFGroupebyArticleSortie=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticleSortie(magasin,article);
       				}
    	    		
		    		
		    		
		    		
    	    		BigDecimal QuantiteTotal=BigDecimal.ZERO;
       				
    	    		String typetransfer[]={Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_SORTIE ,ETAT_TRANSFER_STOCK_ENTRER_MP};
    	    		
    	    		BigDecimal entrer=BigDecimal.ZERO;
		    		BigDecimal production=BigDecimal.ZERO;
		    		BigDecimal sortie=BigDecimal.ZERO;
    	    		
		    		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
		    		{
		    			
		    			entrer=new BigDecimal(0);
		    			
		    			production=new BigDecimal(0);
		    			
		    			for(int k=0;k<typetransfer.length;k++)
		    			{
		    				
		    			for(int j=0;j<listDetailTransferStockPF.size();j++)
		    			{
		    				
		    			if(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer().equals(listDetailTransferStockPF.get(j).getTransferStockPF().getDateTransfer()) 
		    					&& listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId()== listDetailTransferStockPF.get(j).getArticle().getId() && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
		    			{
		    		 if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
		    			{
		    				production=production.add(listDetailTransferStockPF.get(j).getQuantite());
		    		
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
		    			{ 
		    				entrer=entrer.add(listDetailTransferStockPF.get(j).getQuantite());
		    			}
		    						    				
		    			}
		    					    			
		    			}
		    			}
		    			
		    				
	    				MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
		    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
		    			mouvementstockPF.setVente(sortie);
		    			mouvementstockPF.setEntrerProduction(production);
		    			mouvementstockPF.setTransferEntrer(entrer);
		    			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
		    			listMouvementStockPF.add(mouvementstockPF);
		    			
		    			
		    			

		    		}
		    		
		    		/// sortie
		    		
		    		for(int i=0;i<listDetailTransferStockPFGroupebyArticleSortie.size();i++)
		    		{
		    			
		    			
		    			sortie=new BigDecimal(0);
		    			
		    			for(int k=0;k<typetransfer.length;k++)
		    			{
		    				
		    			for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		    			{
		    				
		    			if(listDetailTransferStockPFGroupebyArticleSortie.get(i).getTransferStockPF().getDateTransfer().equals(listDetailTransferStockPFSortie.get(j).getTransferStockPF().getDateTransfer()) 
		    					&& listDetailTransferStockPFGroupebyArticleSortie.get(i).getArticle().getId()== listDetailTransferStockPFSortie.get(j).getArticle().getId() && listDetailTransferStockPFSortie.get(j).getTypeTransfer().equals(typetransfer[k]))	
		    			{
		    		 if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE))
		    			{ 
		    				sortie=sortie.add(listDetailTransferStockPFSortie.get(j).getQuantite());	
		    				
		    			}
		    						    				
		    			}
		    					    			
		    			}
		    			}
		    			
		    			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
		    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticleSortie.get(i).getTransferStockPF().getDateTransfer());
		    			mouvementstockPF.setVente(sortie);
		    			mouvementstockPF.setClient (listDetailTransferStockPFGroupebyArticleSortie.get(i).getClient());
		    			mouvementstockPF.setEntrerProduction(production);
		    			mouvementstockPF.setTransferEntrer(entrer);
		    			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticleSortie.get(i).getArticle());
		    			mouvementstockPF.setDepot(listDetailTransferStockPFGroupebyArticleSortie.get(i).getMagasinSouce().getDepot());	
		    			listMouvementStockPF.add(mouvementstockPF);
		    			
		    			
		    			

		    		}
		    		
		    		
		    		
       				
		    		
		    		// detailtransfer entre deux date et par article
		    		
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article!=null)
		    		{
		    			listMouvementStockPFAfficher.clear();
		    			listMouvementStockPFAfficherTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			
		    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockPF.get(i).getArticle().getId()==article.getId())
		    			{

		    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
		    			}
		    				
		    			
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
		    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockPFAfficher.get(j).getArticle().getId()==article.getId())
		    			{
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
		    			}
		    				
		    			}
		    			
		    		}
		    		
		    		
		    		if(listMouvementStockPFAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficher);
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			listMouvementStockPFAfficherTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    			}
			    			
			    		}
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		    		{
		    			
		    			listMouvementStockPFAfficherTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    			}
			    		
			    		}
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			listMouvementStockPFAfficherTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
			    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article==null)
		    		{
		    			listMouvementStockPFAfficher.clear();
		    			listMouvementStockPFAfficherTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    			
		    		
		    		
		    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
		    			
		    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
		    			
		    			}
		    			
		    		}
		    		
		    		if(listMouvementStockPFAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficher);
		    		}
		    				    			
		    		}
		    		
    	    		
    	    		
    	    		
    	    		
       				
       			}
       			
       		}
       		
       	}
       });
       tableEtatStock.setEditable(false);
       tableEtatStock.setAutoStartEditOnKeyStroke(false);
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article", "Quantite"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(260);
    
      
       
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 925, 348);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Articles");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter le Mouvement de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		InitialiseTableauMouvementStock();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		BigDecimal quantiteTotalProduction=new BigDecimal(0);
	    		BigDecimal quantiteTotalEntre=new BigDecimal(0);
	    		BigDecimal quantiteTotalSortie=new BigDecimal(0);
	    		InitialiseTableauPF();
	    	   
	    	
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	    		
	    		boolean trouve=false;
	    		
	    		Articles article=null;
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		
	    		if(magasin!=null)
	    		{
	    			listEtatStockPF.clear();
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
			    		
			    		Client client=mapClient.get(comboclient.getSelectedItem());
			    		detailTransferStockPFDAO.ViderSession();
			    		
if(comboperation.getSelectedItem().equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
{
statut=Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE;
	listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
	listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
	



}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
{
	statut=Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP;
	listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
	listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
	
	
	
}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
{
	statut=Constantes.ETAT_TRANSFER_STOCK_SORTIE;

	listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
	listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
	
	
}
			    		
listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);

for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
{
	DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
	EtatStockPF etatstock=new EtatStockPF();
	etatstock.setArticle(detailtransferstockpf.getArticle());
	etatstock.setQuantiteProduction(BigDecimal.ZERO);
	etatstock.setQuantiteEntrer(BigDecimal.ZERO);
	listEtatStockPF.add(etatstock);
	
} 	


if(comboperation.getSelectedItem().equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
{

	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
	{
		quantiteTotalProduction=BigDecimal.ZERO;
		
		
		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
		{
			
			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()))
			{
				
			
				quantiteTotalProduction=quantiteTotalProduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
				
				
			}
			
			
		}
		
		
		
		if( !quantiteTotalProduction.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteProduction(quantiteTotalProduction);
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
		}
		
		
		
	}
	afficher_tablePF(listEtatStockPF, TYPE_TRANSFER_PRODUIT_FINI_ENTRE);


}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
{
	
	
	
 	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
	{
		quantiteTotalEntre=BigDecimal.ZERO;
		
		
		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
		{
			
			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFEntrer.get(j).getArticle()))
			{
				
				
				quantiteTotalEntre=quantiteTotalEntre.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalEntre.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntre);
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}
	

 	afficher_tablePF(listEtatStockPF, ETAT_TRANSFER_STOCK_ENTRER_MP);
 	
 	
 	
 	


}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
{
	
 	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
	{
		quantiteTotalSortie=BigDecimal.ZERO;
		
		
		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		{
			
			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()))
			{
				
				
				quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortie.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalSortie.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteSortie(quantiteTotalSortie); 
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}
	

 	afficher_tablePF(listEtatStockPF, ETAT_TRANSFER_STOCK_SORTIE);
 	
 	
 	
 	


}

    		
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
	    layeredPane.setBounds(10, 57, 1465, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(112, 9, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(296, 11, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(399, 11, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     comboperation = new JComboBox();
	     comboperation.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
}
	     });
	    comboperation.setBounds(924, 10, 218, 27);
	    layeredPane.add(comboperation);
	    
	    comboperation.addItem("");
	    comboperation.addItem(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP);
	    comboperation.addItem(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
	    comboperation.addItem( Constantes.ETAT_TRANSFER_STOCK_SORTIE);
	   
	    
	    JLabel lblOperation = new JLabel("Operation :");
	    lblOperation.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblOperation.setBounds(856, 10, 68, 26);
	    layeredPane.add(lblOperation);
	    
	    JLabel lblDepot = new JLabel("Depot  :");
	    lblDepot.setBounds(592, 11, 45, 26);
	    layeredPane.add(lblDepot);
	    lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(634, 11, 202, 27);
	    layeredPane.add(combodepot);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
	          
	          JLabel lblClient = new JLabel("Client :");
	          lblClient.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblClient.setBounds(1152, 10, 68, 26);
	          layeredPane.add(lblClient);
	          
	           comboclient = new JComboBox();
	          comboclient.setBounds(1220, 10, 218, 27);
	          layeredPane.add(comboclient);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		combomp.setSelectedItem("");
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		combodepot.setSelectedItem("");
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JScrollPane scrollPane_1 = new JScrollPane((Component) null);
	    scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    scrollPane_1.setBounds(10, 585, 925, 280);
	    add(scrollPane_1);
	    
	    tableMouvementStockPF = new JTable();
	    tableMouvementStockPF.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Date", "Article", "Quantite"
	    	}
	    ));
	    tableMouvementStockPF.setFillsViewportHeight(true);
	    scrollPane_1.setViewportView(tableMouvementStockPF);
	    
	    JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
	    GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	    gridBagLayout.rowWeights = new double[]{0.0};
	    gridBagLayout.rowHeights = new int[]{0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gridBagLayout.columnWidths = new int[]{0, 0, 0};
	    titledSeparator_1.setTitle("Liste Detail Articles");
	    titledSeparator_1.setBounds(10, 549, 1465, 30);
	    add(titledSeparator_1);
	  
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
	    
	    
	    listClient=clientDAO.findAll();
	    comboclient.addItem("");
	    for(int i=0;i<listClient.size();i++)
	    {
      Client client=listClient.get(i);
      comboclient.addItem(client.getNom());    	
    	mapClient.put(client.getNom(), client); 	
	    	
	    	
	    }
	    
	    
		
		}
	

	
	void InitialiseTableauPF()
	{
		modelePF =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modelePF);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	      
}
	
	
	void InitialiseTableauMouvementStock()
	{
		modeleDetailMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date","Article","Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			tableMouvementStockPF.setModel(modeleDetailMouvementStock);
			tableMouvementStockPF.getColumnModel().getColumn(0).setPreferredWidth(121);
			tableMouvementStockPF.getColumnModel().getColumn(1).setPreferredWidth(106);
			tableMouvementStockPF.getColumnModel().getColumn(2).setPreferredWidth(111);
		
		
	
}
	
	
	
	void afficher_tablePF(List<EtatStockPF> listEtatStockPF , String statut)
	{
	
		
			
			modelePF =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Article", "Quantite"
					}
				) {
					
				};
			tableEtatStock.setModel(modelePF);
			int i=0;
			 
			while(i<listEtatStockPF.size())
			{	
			EtatStockPF etatStockPF=listEtatStockPF.get(i);
			if(statut.equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
			{
				Object []ligne={etatStockPF.getArticle().getLiblle(),etatStockPF.getQuantiteProduction()};

				modelePF.addRow(ligne);
				
				
			}else if(statut.equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
			{
				
				Object []ligne={etatStockPF.getArticle().getLiblle(),etatStockPF.getQuantiteEntrer()};

				modelePF.addRow(ligne);
				
			}else if(statut.equals(ETAT_TRANSFER_STOCK_SORTIE))
			{
				
				Object []ligne={etatStockPF.getArticle().getLiblle(),etatStockPF.getQuantiteSortie()};

				modelePF.addRow(ligne);
			}
				
					
				
				
				i++;
			}

			
			
		
			
}
	
	
	void afficher_tableDetailMouvementStock(List<MouvementStockProduitsFini> listMouvementStock)
	{
		if(comboperation.getSelectedItem().equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
		{
			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date","Article","Quantite"
					}
				) {
					
				};
			tableMouvementStockPF.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listMouvementStock.size())
			{	
				
				
				MouvementStockProduitsFini MouvementStock=listMouvementStock.get(i);
			
			
				
				Object []ligne={MouvementStock.getDateStockPF(),MouvementStock.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(MouvementStock.getEntrerProduction())};

				modeleDetailMouvementStock.addRow(ligne);
				i++;
			}
			
		}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
		{
			

			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date","Matiere Premiere","Quantite"
					}
				) {
					
				};
			tableMouvementStockPF.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listMouvementStock.size())
			{	
				
				
				MouvementStockProduitsFini MouvementStock=listMouvementStock.get(i);
			
			
				
				Object []ligne={MouvementStock.getDateStockPF(),MouvementStock.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(MouvementStock.getTransferEntrer())};

				modeleDetailMouvementStock.addRow(ligne);
				i++;
			}
			
		
		}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		{
			

			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date","Matiere Premiere","Quantite", "Depot","Client"
					}
				) {
					
				};
			tableMouvementStockPF.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listMouvementStock.size())
			{	
				
	          MouvementStockProduitsFini MouvementStock=listMouvementStock.get(i);
			if(!MouvementStock.getVente().equals(BigDecimal.ZERO) && MouvementStock.getClient()!=null)
			{
				Object []ligne={MouvementStock.getDateStockPF(),MouvementStock.getArticle().getLiblle(),MouvementStock.getVente(),MouvementStock.getDepot().getLibelle(), MouvementStock.getClient().getNom()};
				modeleDetailMouvementStock.addRow(ligne);
				
			}
			i++;	
			}
			
		
		}
	
}
	}



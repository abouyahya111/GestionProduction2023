package presentation.stockPF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.Magasin;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class AfficherStockPF extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	private Utilisateur utilisateur;
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, Articles> mapLibelleAricle = new HashMap<>();
	
	List < Articles> listArticles = new ArrayList<Articles>();
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();

	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private ArticlesDAO articlesDAO;
	JComboBox combocodearticle = new JComboBox();
	JComboBox comboBox = new JComboBox();
	JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	 private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1367, 692);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	detailTransferStockPFDAO= new DetailTransferProduitFiniDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
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
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 187, 1215, 471);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		      if(!utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_SIEGE)	) {
					  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
					  	    		comboDepot.addItem(depot.getLibelle());
					  	    		mapDepot.put(depot.getLibelle(), depot);
					  	    		
					  	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
					  		      			mapDepot.put(depot.getLibelle(), depot);
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
					  		     	  
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
					  		     	 	{
					  		     	 	Depot depot =mapDepot.get(comboDepot.getSelectedItem());
							  		       
				  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Produits Fini  ");
				  		     	titledSeparator.setBounds(9, 158, 1215, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1261, 88);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(58, 11, 155, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(223, 10, 67, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(289, 11, 234, 24);
				  		     	 layeredPane.add(comboMagasin);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1139, 25, 113, 23);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				
				
				
				 Date datedebut=null;
				try {
					datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
				if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
				{
					
					dateChooserfin.setDate(new Date());
					
				}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null)
				{
					
					datedebut=dateChooserdebut.getDate();
					dateChooserfin.setDate(dateChooserdebut.getDate());
				}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()!=null)
				{
					datedebut=dateChooserfin.getDate();
					
				}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
				{
					datedebut=dateChooserdebut.getDate();
					
				}
				
				
				
				
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				
				

	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		
	    		listEtatStockPF.clear();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		
	    	
	    		BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	    		BigDecimal quantiteTotalSortie=new BigDecimal(0);
	    		
	    		BigDecimal quantiteTotalproduction=new BigDecimal(0);
	    	
	    		BigDecimal quantiteTotalFinale=new BigDecimal(0);
	    		
	    		boolean trouve=false;
	    		//FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	    		Articles article=mapLibelleAricle.get(comboBox.getSelectedItem());
	    		
	    		if(magasin!=null)
	    		{
	    			
		   
		    		
		    				    		
		    	
		    		
		    	Client client=null;

		    			listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
		    			listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
		    			
	
		    			listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
		    			listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
		    			
		    		
		    			listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		    			listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateChooserfin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		    		
		    		
		    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		    		
		    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		    		{
		    			
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		    			
		    			if(article==null)
		    			{
		    				EtatStockPF etatstock=new EtatStockPF();
			    			etatstock.setArticle(detailtransferstockpf.getArticle());
			    			
			    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
			    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
			    			listEtatStockPF.add(etatstock);
		    			}else
		    			{
		    				if(detailtransferstockpf.getArticle().getId()==article.getId())
		    				{
		    					EtatStockPF etatstock=new EtatStockPF();
				    			etatstock.setArticle(detailtransferstockpf.getArticle());
				    			
				    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
				    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
				    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
				    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
				    			listEtatStockPF.add(etatstock);
		    				}
		    				
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		
		    		
		    		
		    		// calculer la quantite production
		    		
		    		
		    	for(int j=0;j<listDetailTransferStockPFProductionGroupebyPF.size();j++)
		    	{
		    		
		    		quantiteTotalproduction=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFProduction.size();k++)
		    	{
		    		
		    		if(listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId() ==listDetailTransferStockPFProduction.get(k).getArticle().getId() )
		    		{
		    			
		    			quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalproduction.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId())
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
		    		
		    		if(listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFEntrer.get(k).getArticle().getId() )
		    		{
		    			
		    			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId())
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
		    		
		    		if(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFSortie.get(k).getArticle().getId() )
		    		{
		    			
		    			quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if( !quantiteTotalSortie.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId())
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
			   	
			
			   	
		    	afficher_tableMP(listEtatStockPF);
		    	
		    	
	    			
	    		}else
	    		{
	    			

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    	
	    	
				
				/*
				
				String requet="";
				Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
				if(articles!=null)
				{
					
					requet=requet+" and articles.id='"+articles.getId()+"'";
				}
				
				List<StockPF> listStockPF=stockPFDAO.findStockProduitFiniByMagasin(mapMagasin.get(comboMagasin.getSelectedItem()), requet);
				
				afficher_tableMP(listStockPF);
				*/
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Code Article");
		label.setBounds(546, 11, 67, 24);
		layeredPane.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 combocodearticle = new JComboBox();
		 combocodearticle.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapCodeArticle.get(combocodearticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			
			  			comboBox.setSelectedItem (articles.getLiblle());	
			  	
			
	     	 		}else
	     	 		{
	     	 			comboBox.setSelectedItem ("");	
	     	 		}
              
	     	 	 	}
	     	 	
	  		
	  		
	  		
	  		
	  	
		 		
		 		
		 	}
		 });
		combocodearticle.setBounds(623, 13, 138, 24);
		layeredPane.add(combocodearticle);
		 AutoCompleteDecorator.decorate(combocodearticle);
		JLabel label_1 = new JLabel("Libelle Article");
		label_1.setBounds(788, 11, 108, 26);
		layeredPane.add(label_1);
		
		 comboBox = new JComboBox();
		 comboBox.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	
		     	 		
		     	 		 Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
		     	 		 
		     	 		if(articles!=null)
		     	 		{
		     	 			

 			  			combocodearticle.setSelectedItem (articles.getCodeArticle());	
 			  		
		     	 			
		     	 		}else
		     	 		{
		     	 			combocodearticle.setSelectedItem ("");	
		     	 		}
	                  
		     	 	 	}
		     	 	
		 		
		 		
		 		
		 		
		 	}
		 });
		comboBox.setBounds(871, 12, 224, 24);
		layeredPane.add(comboBox);
		 AutoCompleteDecorator.decorate(comboBox);
		 comboBox.addItem("");	  		     
		 
		 JLabel label_2 = new JLabel("Date Debut :");
		 label_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		 label_2.setBounds(10, 51, 136, 24);
		 layeredPane.add(label_2);
		 
		  dateChooserdebut = new JDateChooser();
		 dateChooserdebut.setLocale(Locale.FRANCE);
		 dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		 dateChooserdebut.setBounds(112, 49, 163, 26);
		 layeredPane.add(dateChooserdebut);
		 
		 JLabel label_3 = new JLabel("Date Fin :");
		 label_3.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		 label_3.setBounds(296, 51, 106, 24);
		 layeredPane.add(label_3);
		 
		  dateChooserfin = new JDateChooser();
		 dateChooserfin.setLocale(Locale.FRANCE);
		 dateChooserfin.setDateFormatString("dd/MM/yyyy");
		 dateChooserfin.setBounds(372, 51, 176, 26);
		 layeredPane.add(dateChooserfin);
		 ChargerComboArticle();		  		 
	}
	
void afficher_tableMP(List<EtatStockPF> listStockPF)
	{
	intialiserTableau();
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				EtatStockPF stockPF=listStockPF.get(i);
				
				
				Object []ligne={stockPF.getArticle().getCodeArticle(),stockPF.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(stockPF.getQuantiteFinale())};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article", "Quantit�"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
}


void ChargerComboArticle()
{
	 comboBox.removeAllItems();
	 combocodearticle.removeAllItems();
	 combocodearticle.addItem("");
	 comboBox.addItem("");
	   listArticles=articlesDAO.findAll();
        int i=0;
	      	while(i<listArticles.size())
	      		{	
	      			Articles article=listArticles.get(i);
	      			mapCodeArticle.put(article.getCodeArticle(), article);
	      			mapLibelleAricle.put( article.getLiblle(),article);
	      			
	      			comboBox.addItem(article.getLiblle());
	      			combocodearticle.addItem(article.getCodeArticle());
	      			i++;
	      		}
		 
	 
	 
	 
}
}

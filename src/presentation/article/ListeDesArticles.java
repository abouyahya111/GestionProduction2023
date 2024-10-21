package presentation.article;

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
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.ProdLauncher;
import util.Constantes;
import util.ExporterTableVersExcel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.Magasin;
import dao.entity.MatierePremier;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;


public class ListeDesArticles extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgModifier;
	private ImageIcon imgExcel;

	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Articles> mapAricle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, Articles> mapLibelleAricle = new HashMap<>();
	
	List < Articles> listArticles = new ArrayList<Articles>();

	
	List<DetailEstimation> listDetailEstimation = new ArrayList<DetailEstimation>();
	
	
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articlesDAO;
	Articles article =new Articles ();
	private JComboBox comboBox;
	private JButton btnChercher;
	private JButton btnIntialiser;
	private DetailEstimationDAO detailestimationdao;
	private JLabel label_3;
	private JComboBox combogrammagebox;
	private JLabel label_4;
	private JComboBox combogrammagecarton;
	
	
	private Map<BigDecimal, String> mapGrammageBoxbyGramme= new HashMap<>();
	private Map<BigDecimal , String> mapGrammageCartonbyGramme= new HashMap<>();
	
	private Map< String, BigDecimal> mapGrammageBox= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageCarton= new HashMap<>();
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	private JComboBox comboMatierePremiere = new JComboBox();
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private JTextField txtquantite= new JTextField();
	private JComboBox combocodearticle =new JComboBox();
	JCheckBox chckbxClient = new JCheckBox("Client");
	private JLabel labelClient;
	private JComboBox comboClient;
	 ClientDAO clientDAO;
		private Map< String, Client> MapClient = new HashMap<>();
		List<Client> listeClient= new ArrayList<Client>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ListeDesArticles() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 796);
        try{
        	
        	
        	clientDAO=new ClientDAOImpl();
        	articlesDAO= new ArticlesDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	 comboBox = new JComboBox();
        	 comboBox.addItem("");
        	 listArticles=articlesDAO.findAll();
        	 listeClient=clientDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));

          } catch (Exception exp){exp.printStackTrace();}
       
				  	
				  		      comboBox.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	
				  		     	 		
				  		     	 		 Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
				  		     	 		 
				  		     	 		if(articles!=null)
				  		     	 		{
				  		     	 			

					  			  			combocodearticle.setSelectedItem (articles.getCodeArticle());	
					  			  			if(articles.getCentreCout3()!=null)  /// Grammage Box
					  			  			{
					  			  				
					  			  			if(mapGrammageBoxbyGramme.get(articles.getCentreCout3().setScale(2, RoundingMode.HALF_UP))!=null)  
					  			  			{
					  			  				
					  			  				combogrammagebox.setSelectedItem(mapGrammageBoxbyGramme.get(articles.getCentreCout3().setScale(2, RoundingMode.HALF_UP)));
					  			  				
					  			  			}
					  			  			
					  			  			}
					  			  			
					  			  		if(articles.getCentreCout4()!=null)  /// Grammage Carton
				  			  			{
				  			  				
				  			  			if(mapGrammageCartonbyGramme.get(articles.getCentreCout4().setScale(2, RoundingMode.HALF_UP))!=null)  
				  			  			{
				  			  				
				  			  				combogrammagecarton.setSelectedItem(mapGrammageCartonbyGramme.get(articles.getCentreCout4().setScale(2, RoundingMode.HALF_UP)));
				  			  				
				  			  			}
				  			  			
				  			  			}
					  			  			
					  			  		
					  			  	
							  			
							  				
							  				if(articles.isClient()==  true)
									  		{
									  			
									  			
									  			chckbxClient.setSelected(true);
									  			
									  			
									  		}else
									  		{
									  			chckbxClient.setSelected(false);
									  		}
							  				
							  		
					  			  				
				  		     	 			
				  		     	 		}
				  	                  
				  		     	 	 	}else
				  		     	 	 	{
				  		     	 	 	InitialiserMP();
				  		     	 	afficher_tableArticles(listArticles);
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
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
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(36, 211, 1180, 541);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(36, 170, 1180, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(36, 13, 1180, 146);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 13, 67, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(313, 13, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		 
				  		  comboBox.setBounds(396, 14, 333, 24);
				  		  layeredPane.add(comboBox);
				  		 AutoCompleteDecorator.decorate(comboBox);
				  		  btnChercher = new JButton("Chercher");
				  		  btnChercher.setIcon(imgChercher);
				  		  btnChercher.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		String req="";
				  		  		
				  		  		if(!combocodearticle.getSelectedItem().toString().equals(""))
				  		  		{
				  		  			req=req+" and codeArticle='"+combocodearticle.getSelectedItem().toString()+"' ";
				  		  		}
				  		  		

				  		  	if(combogrammagebox.getSelectedIndex()!=-1)
		  		  			{
		  		  				
				  		  	req=req+" and centreCout3='"+mapGrammageBox.get(combogrammagebox.getSelectedItem())+"' ";
		  		  		
		  		  				
		  		  				
		  		  			}
		  		  			if(combogrammagecarton.getSelectedIndex()!=-1)
		  		  			{
		  		  			req=req+" and centreCout4='"+mapGrammageCarton.get(combogrammagecarton.getSelectedItem())+"' ";
		  		  			}
		  		  			
		  		  			if(chckbxClient.isSelected()==true)
		  		  			{
		  		  			req=req+" and client='"+1+"' ";
		  		  			
		  		  			
		  		  			
		  		  			
		  		  		if(comboClient.getSelectedItem()!=null)
		  		  		{
		  		  			if(!comboClient.getSelectedItem().toString().equals(""))
		  		  			{
		  		  				
		  		  				
		  		  				Client client=MapClient.get(comboClient.getSelectedItem().toString());
		  		  				if(client!=null)
		  		  				{
		  		  				req=req+" and articleClient.id='"+client.getId()+"' ";
		  		  				}
		  		  				
		  		  				
		  		  			}
		  		  		}
		  		  			}
		  		  			
		  		  			
		  		  			
		  		  			
		  		  			
		  		  			listArticles=articlesDAO.listeArticlesByReq(req);
		  		  			afficher_tableArticles(listArticles);
		  		  			
				  		  	}
				  		  });
				  		  btnChercher.setBounds(356, 111, 114, 24);
				  		  layeredPane.add(btnChercher);
				  		  
				  		  btnIntialiser = new JButton("Intialiser");
				  		  btnIntialiser.setIcon(imgInit);
				  		  btnIntialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser();
				  		  	}
				  		  });
				  		  btnIntialiser.setBounds(488, 111, 114, 24);
				  		  layeredPane.add(btnIntialiser);
				  		  
				  		  label_3 = new JLabel("Grammage Box :");
				  		  label_3.setBounds(10, 47, 108, 26);
				  		  layeredPane.add(label_3);
				  		  
				  		  combogrammagebox = new JComboBox();
				  		  combogrammagebox.setSelectedIndex(-1);
				  		  combogrammagebox.setBounds(97, 51, 187, 22);
				  		  layeredPane.add(combogrammagebox);
				  		  
				  		  label_4 = new JLabel("Grammage carton :");
				  		  label_4.setBounds(294, 49, 108, 26);
				  		  layeredPane.add(label_4);
				  		  
				  		  combogrammagecarton = new JComboBox();
				  		  combogrammagecarton.setSelectedIndex(-1);
				  		  combogrammagecarton.setBounds(397, 53, 332, 22);
				  		  layeredPane.add(combogrammagecarton);
		listGrammageBox=grammageBoxDAO.findAll();
    	listGrammageCarton=grammageCartonDAO.findAll();
	  
	  for(int j=0;j<listGrammageBox.size() ; j++)
	  {
		  
		  GrammageBox gramaBox=listGrammageBox.get(j);
		  combogrammagebox.addItem(gramaBox.getCodeGrammage());
		  mapGrammageBox.put(gramaBox.getCodeGrammage(), gramaBox.getGrammageBox());
		  mapGrammageBoxbyGramme.put(gramaBox.getGrammageBox().setScale(2, RoundingMode.HALF_UP), gramaBox.getCodeGrammage());
	  }
	  combogrammagebox.setSelectedIndex(-1);
	  
	  
	  for(int k=0;k<listGrammageCarton.size() ; k++)
	  {
		  
		  GrammageCarton gramacaCarton=listGrammageCarton.get(k);
		  combogrammagecarton.addItem(gramacaCarton.getCodeGrammage());
		  mapGrammageCarton.put(gramacaCarton.getCodeGrammage(), gramacaCarton.getGrammageCarton());
		  mapGrammageCartonbyGramme.put(gramacaCarton.getGrammageCarton().setScale(2, RoundingMode.HALF_UP), gramacaCarton.getCodeGrammage());
	  }
	  combogrammagecarton.setSelectedIndex(-1);
	  
	  combocodearticle = new JComboBox();
	  combocodearticle.addItemListener(new ItemListener() {
	  	public void itemStateChanged(ItemEvent e) {

	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapCodeArticle.get(combocodearticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			

			  			comboBox.setSelectedItem (articles.getLiblle());	
			  			if(articles.getCentreCout3()!=null)  /// Grammage Box
			  			{
			  				
			  			if(mapGrammageBoxbyGramme.get(articles.getCentreCout3().setScale(2, RoundingMode.HALF_UP))!=null)  
			  			{
			  				
			  				combogrammagebox.setSelectedItem(mapGrammageBoxbyGramme.get(articles.getCentreCout3().setScale(2, RoundingMode.HALF_UP)));
			  				
			  			}
			  			
			  			}
			  			
			  		if(articles.getCentreCout4()!=null)  /// Grammage Carton
		  			{
		  				
		  			if(mapGrammageCartonbyGramme.get(articles.getCentreCout4().setScale(2, RoundingMode.HALF_UP))!=null)  
		  			{
		  				
		  				combogrammagecarton.setSelectedItem(mapGrammageCartonbyGramme.get(articles.getCentreCout4().setScale(2, RoundingMode.HALF_UP)));
		  				
		  			}
		  			
		  			}
			  			
			  		
			  	
	  			
	  				
	  				if(articles.isClient()==  true)
			  		{
			  			
			  			
			  			chckbxClient.setSelected(true);
			  			
			  			
			  		}else
			  		{
			  			chckbxClient.setSelected(false);
			  		}
	  				
	  		
			  				
	     	 			
	     	 		}
               
	     	 	 	}else
	     	 	 	{
	     	 	 	InitialiserMP();
	     	 	afficher_tableArticles(listArticles);
	     	 	 	}
	     	 	
}
	  });
	  combocodearticle.setBounds(87, 15, 206, 24);
	  layeredPane.add(combocodearticle);
	  AutoCompleteDecorator.decorate(combocodearticle);
	  
	   chckbxClient = new JCheckBox("Client");
	   chckbxClient.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		

	   		

	   		

	 		if(chckbxClient.isSelected()==true)
	 		{
	 			comboClient.setVisible(true);
	 			comboClient.setSelectedItem("");
	 			labelClient.setVisible(true);
	 		}else
	 		{

	 			comboClient.setVisible(false);
	 			comboClient.setSelectedItem("");
	 			labelClient.setVisible(false);

	 		}
	 	
   		
   	
	   	
	   		
	   	}
	   });
	  chckbxClient.setBounds(754, 14, 88, 23);
	  layeredPane.add(chckbxClient);
	  
	  labelClient = new JLabel("Client :");
	  labelClient.setBounds(739, 51, 67, 26);
	  layeredPane.add(labelClient);
	  
	  comboClient = new JComboBox();
	  comboClient.setSelectedIndex(-1);
	  comboClient.setBounds(800, 55, 374, 22);
	  layeredPane.add(comboClient);
	  AutoCompleteDecorator.decorate(combocodearticle);
	  JButton button = new JButton("Exporter Excel");
	  button.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		

		
    			if(table.getRowCount()!=0)
    			{
    				String titre="";
    				String titrefeuilleexcel="";
    				if(chckbxClient.isSelected()==true)
    				{
    					 titrefeuilleexcel="Liste Des Article Clients";
    					 titre="Liste Des Article Clients";
    				}else
    				{
    					 titrefeuilleexcel="Liste Des Article";
    					 titre="Liste Des Article";
    				}
    				
    				
    				
		    		
		    		ExporterTableVersExcel.tabletoexcelListeDesArticles (table, titre,titrefeuilleexcel);
    				
    				
    			}else
    			{
    				
    				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
    				
    				
    			}
    		
    			
				
			
	  	}
	  });
	  button.setBounds(352, 763, 156, 34);
	  button.setIcon(imgExcel);
	  add(button);
	  
	     	
	     	
	    	ChargerComboArticle();
	
	afficher_tableArticles(listArticles);
	
	comboClient.addItem("");
	 
	for(int t=0;t<listeClient.size();t++)
	{
		
		Client client=listeClient.get(t);
		comboClient.addItem(client.getNom());
		 
		MapClient.put(client.getNom(), client);
		
	}
	comboClient.setSelectedItem("");
	comboClient.setVisible(false);
	labelClient.setVisible(false);
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
	

	
	void intialiser()
	{
		
		combocodearticle.setSelectedItem("");	
	comboBox.setSelectedItem("");	
	chckbxClient.setSelected(false);
	 combogrammagebox.setSelectedIndex(-1);
	 combogrammagecarton.setSelectedIndex(-1);
	}
	
	void afficher_tableArticles(List<Articles> listArticles)
	{
		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Libelle"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleMP); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		  
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listArticles.size())
			{	
				
				Articles articles=listArticles.get(i);
				
				Object []ligne={articles.getCodeArticle(),articles.getLiblle()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	



void InitialiserMP()
{
	
	comboMatierePremiere.setSelectedIndex(-1);
	txtquantite.setText("");
	
	
	
}
}

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
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.DetailEstimation;
import dao.entity.MatierePremier;

import javax.swing.JCheckBox;


public class AjouterEstimationThe extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private JButton btnIntialiserOF;
	private JComboBox comboCatThe = new JComboBox();
	private	JComboBox comboArticle = new JComboBox();
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, Integer> mapPriorite = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapAricle = new HashMap<>();
	private Map< String, String> mapCodeArticle= new HashMap<>();
	private Map< String, String> mapLibelleAricle = new HashMap<>();
	private Map< String, CategorieMp> mapCategorieMP = new HashMap<>();
	private List<Articles> listArticles =new ArrayList<Articles>();
	
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articlesDAO;
	Articles article =new Articles ();
	private JTextField txtCode = new JTextField();
	private JCheckBox checkmixte = new JCheckBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterEstimationThe() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	util.Utils.copycoller(txtCode);
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesDAO= new ArticlesDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(316, 515, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		 comboCatThe.addItem("");	  		     
				  		 comboArticle.addItem(""); 
				  		   listArticles=articlesDAO.findAll();
				  	        int i=0;
				  		      	while(i<listArticles.size())
				  		      		{	
				  		      			Articles article=listArticles.get(i);
				  		      			mapCodeArticle.put(article.getLiblle(), article.getCodeArticle());
				  		      			mapLibelleAricle.put( article.getCodeArticle(),article.getLiblle());
				  		      			mapAricle.put(article.getLiblle(), article);
				  		      			comboArticle.addItem(article.getLiblle());
				  		      			i++;
				  		      		}
				  			
				  		      comboArticle.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	
				  		     	 		txtCode.setText(mapCodeArticle.get(comboArticle.getSelectedItem()));
				  		     	 	intialiserTableau();
				  		     	 	comboCatThe.setSelectedIndex(-1);
				  		     	 	checkmixte.setSelected(false);
				  	                  
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		      
				  		    txtCode.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			
				  			  		comboArticle.setSelectedItem(mapLibelleAricle.get(txtCode.getText()));
				  			  		}}});
				  		    
				  		 /*liste catégorie matière première thé */
				  		  List<CategorieMp> listeCategorieMP = matierePremiereDAO.listeCategorieTHE();	
			  		       i=0;
			  		      	while(i<listeCategorieMP.size())
			  		      		{	
			  		      			CategorieMp categorieMP=listeCategorieMP.get(i);
			  		      			mapCategorieMP.put(categorieMP.getNom(), categorieMP);
			  		      			
			  		      			comboCatThe.addItem(categorieMP.getNom());
			  		      			i++;
			  		      		}
			  		      comboCatThe.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 	
			  		     	 	//comboGroupe.addItem("");
			  		     	 		 
			  		     	 		 CategorieMp categorieMP =mapCategorieMP.get(comboCatThe.getSelectedItem());
						  		       if(!comboCatThe.getSelectedItem().equals(""))
						  		       {
						  		    		List<MatierePremier> listeMP = matierePremiereDAO.listeMatierePremierByCategorie(categorieMP.getCode());
								  		      if(listeMP!=null){
								  		    	  
								  		    	afficher_tableMP(listeMP);
								  		      }
						  		       }else
						  		       {
						  		    	intialiserTableau();
						  		    	   
						  		       }
				  		     	  
			  		     	 	 }
			  		     	 	}
			  		     	 });
				  		    
				  		/*""""""""""""""""""""""""""""""""""""""""*/    
				  		    
				  		 
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     intialiserTableau();	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 167, 782, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 781, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 35, 144, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  
				  		  txtCode.setBounds(98, 35, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  JLabel lblCon = new JLabel("Cat\u00E9gorie Th\u00E9 : ");
				  		  lblCon.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblCon.setBounds(10, 70, 144, 24);
				  		  layeredPane.add(lblCon);
				  		  
				  		  JLabel lblArticle = new JLabel("Article");
				  		  lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblArticle.setBounds(286, 35, 144, 24);
				  		  layeredPane.add(lblArticle);
				  		  
				  		 
				  		  comboCatThe.setBounds(98, 70, 149, 24);
				  		  layeredPane.add(comboCatThe);
				  		
				  		  comboArticle.setBounds(335, 35, 200, 24);
				  		  layeredPane.add(comboArticle);
				  		  
				  		   checkmixte = new JCheckBox("  Mixte");
				  		  checkmixte.setBounds(335, 72, 76, 20);
				  		  layeredPane.add(checkmixte);
		
		JButton btnValiderTransfer = new JButton("Ajouter Estimation");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean trouve=false;
				
				BigDecimal number ; 
				BigDecimal somme = new BigDecimal("0");
				BigDecimal result = new BigDecimal("1");
			if(!remplirMapQuantiteEstimation())	{
				
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				List<DetailEstimation> listeDetailEstimationtmp= remplirDetailEstimation();
				mapPriorite.clear();
				if(checkmixte.isSelected()==true)
				{
					
					for(int i=0;i<listeDetailEstimationtmp.size();i++)
					{
						
						if(listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE)<0 || listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE)==0)
						{
							trouve=true;
						}
						
					}
				
					if(trouve==true)
					{
						JOptionPane.showMessageDialog(null, "la Quantité Mixte doit etre entre 0 et 1", "Errgeur", JOptionPane.ERROR_MESSAGE);
						return;
					}else
					{
						for(int i=0;i<listeDetailEstimationtmp.size();i++)
						{
							DetailEstimation detailestimation=listeDetailEstimationtmp.get(i);
							mapPriorite.put(detailestimation.getMatierePremier().getCode().toString(),2);
							
							number =new BigDecimal(listeDetailEstimationtmp.get(i).getQuantite()+"");
							somme=somme.add(number);
							
						}
					
						if(somme.compareTo(result)==1 || somme.compareTo(result)==-1)
						{
							JOptionPane.showMessageDialog(null, "La somme de la quantité Mixte doit etre egale 1 ", "Errgeur", JOptionPane.ERROR_MESSAGE);
							return;
							
						}
						else if(somme.compareTo(result)==0)
						{
							
							article=articlesDAO.findByCode(txtCode.getText());
							List<DetailEstimation> listeDetailEstimation= remplirDetailEstimation();
							article.setDetailEstimation(listeDetailEstimation);
								
							articlesDAO.edit(article);
							JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							intialiserTableau();
							intialiser();
							
						}
							
						
					}
					
				}
				else
				{
					
					for(int i=0;i<listeDetailEstimationtmp.size();i++)
					{
						if(listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE)<0 || listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE)==0)
						{
							trouve=true;
						}
						
					}
					
					if(trouve==true)
					{
						JOptionPane.showMessageDialog(null, "la Quantite d'estimation non mixte doit etre egale à 1 SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else
					{
						
						for(int i=0;i<listeDetailEstimationtmp.size();i++)
						{
							DetailEstimation detailestimation=listeDetailEstimationtmp.get(i);
							mapPriorite.put(detailestimation.getMatierePremier().getCode().toString(),1);
							
						}
						article=articlesDAO.findByCode(txtCode.getText());
						List<DetailEstimation> listeDetailEstimation= remplirDetailEstimation();
						article.setDetailEstimation(listeDetailEstimation);
							
						articlesDAO.edit(article);
							JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							intialiserTableau();
							intialiser();
						
					}
					
					
				
				}
				
				
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(148, 515, 158, 23);
		add(btnValiderTransfer);
		
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		checkmixte.setSelected(false);
		intialiserTableau();
		comboArticle.setSelectedItem("");
		comboCatThe.setSelectedItem("");
		
		
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{
		intialiserTableau();
		  int i=0;
			while(i<listMatierePremier.size())
			{	
				
				MatierePremier matierePremier=listMatierePremier.get(i);
				mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
				Object []ligne={matierePremier.getCode(),matierePremier.getNom(),""};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	


boolean remplirMapQuantiteEstimation(){
	boolean trouve=false;
	int i=0;
	mapQuantiteMP=new HashMap<>();
	mapPriorite	=new HashMap<>();
	mapMatierePremier=new HashMap<>();


	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
		
			
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			mapPriorite.put(table.getValueAt(j, 0).toString(),1);
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailEstimation> remplirDetailEstimation(){
	BigDecimal quantite=BigDecimal.ZERO;
	int priorite=0;
	
	
	List<DetailEstimation> listDetailEstimation= new ArrayList<DetailEstimation>();
	
	/*ajouter le teste si cet estimation existe déja ;*/
	//fff
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailEstimation detailEstimation=new DetailEstimation();
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		priorite=mapPriorite.get(matierePremier.getCode());
		
		//if(priorite<=0)
			//priorite=1;
		
		detailEstimation.setQuantite(quantite);
		detailEstimation.setPriorite(priorite);
		detailEstimation.setMatierePremier(matierePremier);
		detailEstimation.setArticles(article);
		
	
		listDetailEstimation.add(detailEstimation);
	
	}
	return listDetailEstimation;
	
}

void intialiserTableau(){
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom Matière Première", "Quantité Estimé"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true 
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
		 table.getColumnModel().getColumn(1).setPreferredWidth(260);
		 table.getColumnModel().getColumn(2).setPreferredWidth(160);
		// table.getColumnModel().getColumn(3).setPreferredWidth(60);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}

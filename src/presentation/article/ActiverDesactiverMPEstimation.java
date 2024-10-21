package presentation.article;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.MatierePremier;


import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ActiverDesactiverMPEstimation extends JFrame implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private JButton btnIntialiserOF;

	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private List<DetailEstimation> listDetailEstimation = new ArrayList<DetailEstimation>();

	
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articlesDAO;
	Articles article =new Articles ();
	private JTextField txtCode;
	private JComboBox comboMatierePremiere = new JComboBox();
	private  JComboBox combogrammagebox = new JComboBox();
	private  JComboBox combogrammagecarton = new JComboBox();
	private Map< String, BigDecimal> mapGrammageBox= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageCarton= new HashMap<>();
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	 private JTextField	txtpriorite = new JTextField();
	JCheckBox actif = new JCheckBox("Actif");
	private JComboBox comboBox;
	List < Articles> listArticles = new ArrayList<Articles>();
	private Map< String, Articles> mapAricle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, Articles> mapLibelleAricle = new HashMap<>();
	private DetailEstimationDAO detailestimationdao;
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ActiverDesactiverMPEstimation( Articles articlesEnvoyer) {
		setBackground(Color.WHITE);
	
	 
		
        setBounds(0, 0, 1284, 775);
        try{
        	
        	
        	
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesDAO= new ArticlesDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	detailestimationdao= new DetailEstimationDAOImpl();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        getContentPane().setLayout(null);
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(367, 120, 112, 23);
				  		     getContentPane().add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {}
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 199, 808, 438);
				  		     	getContentPane().add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 809, 84);
				  		     	getContentPane().add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 35, 78, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(260, 32, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  txtCode = new JTextField();
				  		  txtCode.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		

			  			  		if (e.getKeyCode() == e.VK_ENTER)
			  			  		{
			  			  			
			  			  			if(!txtCode.getText().equals(""))
			  			  			{
			  			  				
			  			  			Articles articles=mapCodeArticle.get(txtCode.getText());
			  			  			if(articles!=null)
			  			  			{
			  			  			comboBox.setSelectedItem(articles.getLiblle());	
			  			  		listDetailEstimation=detailestimationdao.findDetilestimationByArticle(articles.getId());
			  			  		afficher_tableMP(listDetailEstimation);
			  			  			
			  			  			
			  			  			}else
			  			  			{
			  			  			JOptionPane.showMessageDialog(null, "Le Code introuvable !!!!");
		  			  				return ;
			  			  			}
				  			  		
				  			  		
			  			  			}else
			  			  			{
			  			  				JOptionPane.showMessageDialog(null, "Le Code est vide Veuillez entre le code article SVP !!!!");
			  			  				return ;
			  			  				
			  			  			}
			  			  		
			  			  		
			  			  		
			  			  		
			  			  		
			  			  		
			  			  		
			  			  		
			  			  		}
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  
				  		util.Utils.copycoller(txtCode);
				  		  txtCode.setBounds(98, 35, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  comboBox = new JComboBox();
				  		  comboBox.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent e) {

			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	
			  		     	 		
			  		     	 		 Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
			  		     	 		 
			  		     	 		if(articles!=null)
			  		     	 		{
			  		     	 			
			  		     	 		 
				  			  			txtCode.setText(articles.getCodeArticle());	
				  			  	
				  			  		listDetailEstimation=detailestimationdao.findDetilestimationByArticle(articles.getId());
				  			  		afficher_tableMP(listDetailEstimation);
			  		     	 			
			  		     	 		}
			  	                  
			  		     	 	 	}else
			  		     	 	 	{
			  		     	 	 		
			  		     	 	 	 
			  		     				listDetailEstimation.clear();
				  		     	 	 	afficher_tableMP(listDetailEstimation);
			  		     			 
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	
			  		     	 	 	}
			  		     	 	
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  comboBox.setBounds(342, 35, 291, 24);
				  		  layeredPane.add(comboBox);
						
						
				  	
					      	
		
		JButton btnValiderTransfer = new JButton("Valider");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listDetailEstimation.size()!=0)
				{
					for(int j=0;j<table.getRowCount();j++){
						
						boolean actif=(boolean) table.getValueAt(j, 4);
					
						DetailEstimation detailEstimation=	listDetailEstimation.get(j);
						detailEstimation.setActif(actif);
						detailestimationdao.edit(detailEstimation);
						
						
					}
					
				JOptionPane.showMessageDialog(null, "Modification effectué ");	
				
				dispose();
				}else
				{
					JOptionPane.showMessageDialog(null, "Article Introuvable ");	
					return ;
				}
			
				
				
				
				
				
}
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(253, 648, 158, 23);
		getContentPane().add(btnValiderTransfer);
		
		JButton button = new JButton("Chercher");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtCode.getText().equals(""))
				{
					Articles articles=mapCodeArticle.get(txtCode.getText());
					if(articles!=null)
					{
						listDetailEstimation=detailestimationdao.findDetilestimationByArticle(articles.getId());
						
						afficher_tableMP(listDetailEstimation);
						
					}else
					{
						JOptionPane.showMessageDialog(null, "article introuvable SVP !!!");
						return;
					}
				
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code article SVP !!!");
					return;
				}
				
				
			}
		});
		button.setBounds(223, 119, 114, 24);
		getContentPane().add(button);
	
		ChargerComboArticle();
		
		if(articlesEnvoyer!=null)
			{
			

	 	 	comboBox.setSelectedItem(articlesEnvoyer.getLiblle());
	 	 	
	 	 	 
	 	 	
	 		 
			}	
	 
				  		 
	}
	
	 void ChargerComboArticle()
	 {
		 comboBox.removeAllItems();
		 comboBox.addItem("");
		   listArticles=articlesDAO.findAll();
	        int i=0;
		      	while(i<listArticles.size())
		      		{	
		      			Articles article=listArticles.get(i);
		      			mapCodeArticle.put(article.getCodeArticle(), article);
		      			mapLibelleAricle.put( article.getLiblle(),article);
		      			
		      			comboBox.addItem(article.getLiblle());
		      			i++;
		      		}
			 
		 
		 
		 
	 }
	
	
	void intialiser()
	{
		txtCode.setText("");
		
		comboBox.setSelectedIndex(-1);
		combogrammagebox.setSelectedIndex(-1);
		combogrammagecarton.setSelectedIndex(-1);
		listDetailEstimation.clear();
		afficher_tableMP(listDetailEstimation);
		
	}
	
	void afficher_tableMP(List<DetailEstimation> listDetailEstimation)
	{

		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé","priorite","Actif"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,true
	  		     	};
	  		     	
	  		     	Class[] columnTypes = new Class[] {
							String.class,String.class,String.class,String.class, Boolean.class
						};
	  		     	
	  		     	public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		 table.setModel(modeleMP); 
	  		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
	         table.getColumnModel().getColumn(1).setPreferredWidth(260);
	         table.getColumnModel().getColumn(2).setPreferredWidth(160);
	        table.getColumnModel().getColumn(3).setPreferredWidth(160);
	        table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimation.size())
			{	
				
				DetailEstimation detailEstimation=listDetailEstimation.get(i);
				
				Object []ligne={detailEstimation.getMatierePremier().getCode(), detailEstimation.getMatierePremier().getNom(),detailEstimation.getQuantite(), detailEstimation.getPriorite(),detailEstimation.isActif()};

				modeleMP.addRow(ligne);
				i++;
			}
			
	}
	

	/*
boolean remplirMapQuantiteEstimation(){

	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
	
}
*/







}

package matierePremiere;

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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.DetailEstimationMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.MatierePremier;
import dao.entity.Promotion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class ModifierMatierePremierCompose extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgModifier;
	private JButton btnIntialiserOF;

	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, ArticlesMP> mapAricleMP = new HashMap<>();
	private Map< String, String> mapCodeArticleMP= new HashMap<>();
	private Map< String, String> mapLibelleAricleMP = new HashMap<>();
	
	List < ArticlesMP> listArticlesMP = new ArrayList<ArticlesMP>();
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	List<DetailEstimationMP> listDetailEstimationMP = new ArrayList<DetailEstimationMP>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP() ;
	private JTextField txtCode;
	private JComboBox comboBox;
	private JButton btnChercher;
	private JButton btnIntialiser;
	private JButton bouttonModifier;
	private JButton btnSupprimer;
	private JButton btnAjouter;
	private DetailEstimationMPDAO detailestimationmpdao;
	private JTextField codeMatierePremiere;
	private JTextField txtpriorite;
	private JTextField txtquantite;
	JComboBox comboMatierePremiere = new JComboBox();
	JCheckBox actif = new JCheckBox("Actif");
	public ModifierMatierePremierCompose() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 730);
        try{
        	
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesMPDAO= new ArticlesMPDAOImpl();
        	detailestimationmpdao= new DetailEstimationMPDAOImpl();
        	 comboBox = new JComboBox();
        	 comboBox.addItem("");
        	  txtCode = new JTextField();
        	  util.Utils.copycoller(txtCode);
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(432, 664, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     
				  		   listArticlesMP=articlesMPDAO.findAll();
				  	        int i=0;
				  		      	while(i<listArticlesMP.size())
				  		      		{	
				  		      			ArticlesMP articleMP=listArticlesMP.get(i);
				  		      			mapCodeArticleMP.put(articleMP.getLiblle(), articleMP.getCodeArticle());
				  		      			mapLibelleAricleMP.put( articleMP.getCodeArticle(),articleMP.getLiblle());
				  		      			mapAricleMP.put(articleMP.getLiblle(), articleMP);
				  		      			comboBox.addItem(articleMP.getLiblle());
				  		      			i++;
				  		      		}
				  			
				  		      comboBox.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	
				  		     	 		txtCode.setText(mapCodeArticleMP.get(comboBox.getSelectedItem()));
				  		     	 		
				  		     	 		if(txtCode.getText().equals(""))
				  		     	 		{
				  		     	 		txtCode.setText(Constantes.CODE_MP);	
				  		     	 		}
				  	                  
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		      
				  			txtCode.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			
				  			  		comboBox.setSelectedItem(mapLibelleAricleMP.get(txtCode.getText()));
				  			  		}}});
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(listDetailEstimationMP.size()!=0)
				  				 	{
				  				 		if(table.getSelectedRow()!=-1)
				  				 		{
				  				 			
				  				 			codeMatierePremiere.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  				 			comboMatierePremiere.setSelectedItem (table.getValueAt(table.getSelectedRow(), 1).toString());
				  				 			txtquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				  				 			txtpriorite.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				  				 			actif.setSelected(Boolean.valueOf(table.getValueAt(table.getSelectedRow(), 4).toString()) );
				  				 			btnSupprimer.setEnabled(true);
				  				 			bouttonModifier.setEnabled(true);
				  				 			btnAjouter.setEnabled(false);
				  				 		
				  				 			
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
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(36, 309, 960, 344);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(36, 274, 960, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(36, 13, 960, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 22, 80, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(304, 21, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		
				  		  txtCode.setBounds(98, 22, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		 
				  		  comboBox.setBounds(383, 22, 289, 24);
				  		  layeredPane.add(comboBox);
				  		 AutoCompleteDecorator.decorate(comboBox);
				  		  btnChercher = new JButton("Chercher");
				  		  btnChercher.setIcon(imgChercher);
				  		  btnChercher.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(!txtCode.getText().equals("") && comboBox.getSelectedIndex()!=-1)
				  		  		
				  		  		{
				  		  			
				  		  		articlesMP =articlesMPDAO.findByCode(txtCode.getText());
					  		  	listDetailEstimationMP=articlesMP.getDetailEstimationMP();
					  		    listDetailEstimationMP.size();
					  		  	afficher_tableMP(listDetailEstimationMP);
					  		  	
				  		  		}
				  		  		else
				  		  		{
				  		  			JOptionPane.showMessageDialog(null, "ArticleMP Introuvable !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		  			return;
				  		  		}
				  		  	
				  		  	}
				  		  });
				  		  btnChercher.setBounds(231, 76, 114, 24);
				  		  layeredPane.add(btnChercher);
				  		  
				  		  btnIntialiser = new JButton("Intialiser");
				  		  btnIntialiser.setIcon(imgInit);
				  		  btnIntialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser();
				  		  	
				  		  	}
				  		  });
				  		  
				  		  btnIntialiser.setBounds(363, 76, 114, 24);
				  		  layeredPane.add(btnIntialiser);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(36, 135, 960, 108);
		add(layeredPane_1);
		
		 bouttonModifier = new JButton("Modifier");
		 bouttonModifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		

				
		 		
				
				
				
				if(codeMatierePremiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else
				{
					

					
					MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
				

								if(txtpriorite.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de l'En Vrac !!!!");
									return;
								}
								Boolean existe=false;
								Boolean MemePrioriteCarton=false;
								
								for(int i=0;i<listDetailEstimationMP.size();i++)
								{
									
									DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(i);
									if(i!=table.getSelectedRow())
									{
										
										if(detailEstimationMP.getMatierePremier().getId()==matierePremier.getId())
										{
											existe=true;
											
										}
										
							if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))			
							{
								
								if(detailEstimationMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
								{
									
									if(Integer.valueOf(txtpriorite.getText())==detailEstimationMP.getPriorite())
									{
										
										MemePrioriteCarton=true;
										
									}
									
									
									
								}
								
								
								
								
							}	
										
									}
					
									
									
									
									
									
									
								}
								
								
								if(existe==true)
								{
									
									JOptionPane.showMessageDialog(null, "MP "+matierePremier.getNom() +" déja existant" , "Information",JOptionPane.INFORMATION_MESSAGE);
									return;
									
								}
								
								
								if(MemePrioriteCarton==true)
								{
									JOptionPane.showMessageDialog(null, "Veuillez Changer la Priorité de carton SVP " , "Information",JOptionPane.INFORMATION_MESSAGE);
									return;
								}
							
						
						DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(table.getSelectedRow());						
						detailEstimationMP.setMatierePremier(matierePremier);
						 detailEstimationMP.setQuantite(new BigDecimal(txtquantite.getText()));
						 detailEstimationMP.setPriorite(Integer.valueOf(txtpriorite.getText()));				
					     detailEstimationMP.setActif(actif.isSelected());
						
					     detailestimationmpdao.edit(detailEstimationMP);
							listDetailEstimationMP.set(table.getSelectedRow(), detailEstimationMP);
						
						afficher_tableMP(listDetailEstimationMP);
						InitialiserMP();
								
						
				}

				
		 	}
		 });
		bouttonModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bouttonModifier.setEnabled(false);
		bouttonModifier.setBounds(115, 59, 107, 24);
		layeredPane_1.add(bouttonModifier);
		
		JButton button_1 = new JButton("Initialiser");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InitialiserMP();
				
				}
		});
		
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(247, 60, 106, 23);
		layeredPane_1.add(button_1);
		
		 btnSupprimer = new JButton("Supprimer");
		 btnSupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(table.getSelectedRow()!=-1)
				{
					if(listDetailEstimationMP.size()!=0)
					{
						 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la matiere premiere ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
							    detailestimationmpdao.delete(listDetailEstimationMP.get(table.getSelectedRow()).getId());
								listDetailEstimationMP.remove(table.getSelectedRow());
								afficher_tableMP(listDetailEstimationMP);
								JOptionPane.showMessageDialog(null, "Matiere Premiere supprimer avec succée ");
								 InitialiserMP();
								
							}
						}
					}
		 		}
		 });
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(383, 59, 107, 24);
		layeredPane_1.add(btnSupprimer);
		
		 btnAjouter = new JButton("Ajouter");
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
				if(codeMatierePremiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
					
					
				}else
				{
					

					
					MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
				

						
						
						
								if(txtpriorite.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite SVP !!!!");
									return;
								}
								
								if(txtquantite.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantite d'estimation SVP !!!!");
									return;
								}
									
							Boolean existe=false;
							Boolean MemePrioriteCarton=false;
							
							for(int i=0;i<listDetailEstimationMP.size();i++)
							{
								
								DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(i);
								
								if(detailEstimationMP.getMatierePremier().getId()==matierePremier.getId())
								{
									existe=true;
									
								}
								
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))			
					{
						
						if(detailEstimationMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
						{
							
							if(Integer.valueOf(txtpriorite.getText())==detailEstimationMP.getPriorite())
							{
								
								MemePrioriteCarton=true;
								
							}
							
							
							
						}
						
						
						
						
					}
								
								
								
								
								
								
							}
							
							
							if(existe==true)
							{
								
								JOptionPane.showMessageDialog(null, "MP "+matierePremier.getNom() +" déja existant" , "Information",JOptionPane.INFORMATION_MESSAGE);
								return;
								
							}
							
							
							if(MemePrioriteCarton==true)
							{
								JOptionPane.showMessageDialog(null, "Veuillez Changer la Priorité de carton SVP " , "Information",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							
						DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
						detailEstimationMP.setArticlesMP(articlesMP);
						detailEstimationMP.setMatierePremier(matierePremier);
						 detailEstimationMP.setQuantite(new BigDecimal(txtquantite.getText()));
						 detailEstimationMP.setPriorite(Integer.valueOf(txtpriorite.getText()));				
					     detailEstimationMP.setActif(actif.isSelected());
					 	detailestimationmpdao.add(detailEstimationMP);
	    				listDetailEstimationMP.add(detailEstimationMP);
	    				afficher_tableMP(listDetailEstimationMP);
				
						InitialiserMP();
						
					
					
				}
					

	    		
		 		
		 		
		 	}
		 });
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter.setBounds(507, 59, 107, 24);
		layeredPane_1.add(btnAjouter);
		
		JLabel label = new JLabel("Code :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 18, 36, 26);
		layeredPane_1.add(label);
		
		codeMatierePremiere = new JTextField();
		codeMatierePremiere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				

				


		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			
		  			comboMatierePremiere.setSelectedItem(mapCode.get(codeMatierePremiere.getText().toUpperCase()));
		  		}
  				
  				
  				
  				
  				
  				
  			
				
				
				
			
				
			}
		});
		codeMatierePremiere.setColumns(10);
		codeMatierePremiere.setBounds(57, 19, 106, 26);
		layeredPane_1.add(codeMatierePremiere);
		
		JLabel label_1 = new JLabel("Nom :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(173, 18, 130, 26);
		layeredPane_1.add(label_1);
		
		 comboMatierePremiere = new JComboBox();
		 comboMatierePremiere.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
    	 		 if(comboMatierePremiere.getSelectedIndex()!=-1)
    	 		 {
    	 			 
    	 			 if(!comboMatierePremiere.getSelectedItem().equals(""))
    	 			 {
    	 				 
    	 				codeMatierePremiere.setText(mapLibelle.get(comboMatierePremiere.getSelectedItem()));
	  		     	   	
		  		     	 MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
		  		     	   actif.setSelected(true);	 
		  		 
		  		     	
		  		     	
		  		     	if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
		  		     	{
		  		     		txtpriorite.setText("");
		  		     		txtquantite.setEnabled(true);
		  		     		txtpriorite.setEnabled(true);
		  		     	}else
		  		     	{
		  		     		txtpriorite.setText("");
		  		     		txtquantite.setEnabled(true);
		  		     		txtpriorite.setEnabled(false);
		  		     		txtpriorite.setText(1+"");
		  		     	}
    	 			 }else
    	 			 {
    	 				 
    	 				 codeMatierePremiere.setText(Constantes.CODE_MP);
    	 				txtpriorite.setText("");
    	 				txtquantite.setText("");
    	 				txtquantite.setEnabled(true);
	  		     		txtpriorite.setEnabled(false);
    	 				 
    	 			 }
    	 	
    	 			 
    	 		 }else
    	  {
    	 			 
    	 			 codeMatierePremiere.setText(Constantes.CODE_MP);
    		  
    	  }
   
    	   	
             
    	 	 	
		
	
		
		
		
		
	
	 		
	 		
	 		
	 		
	 		
	 	
		 		
		 		
		 	}
		 });
		comboMatierePremiere.setSelectedIndex(-1);
		comboMatierePremiere.setBounds(225, 19, 315, 26);
		layeredPane_1.add(comboMatierePremiere);
		 AutoCompleteDecorator.decorate(comboMatierePremiere);
		JLabel label_2 = new JLabel("Priorite :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(550, 16, 60, 26);
		layeredPane_1.add(label_2);
		
		txtpriorite = new JTextField();
		txtpriorite.setEnabled(false);
		txtpriorite.setColumns(10);
		txtpriorite.setBounds(610, 19, 91, 26);
		layeredPane_1.add(txtpriorite);
		
		JLabel label_3 = new JLabel("Quantite :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(711, 19, 60, 26);
		layeredPane_1.add(label_3);
		
		txtquantite = new JTextField();
		txtquantite.setEnabled(false);
		txtquantite.setColumns(10);
		txtquantite.setBounds(771, 22, 91, 26);
		layeredPane_1.add(txtquantite);
		
		 actif = new JCheckBox("Actif");
		actif.setBounds(879, 21, 69, 23);
		layeredPane_1.add(actif);
		
		txtCode.setText(Constantes.CODE_MP);
		
		 listMatierePremiere = matierePremiereDAO.findAll();	
		 comboMatierePremiere.addItem("");
	      int j=0;
	      	while(j<listMatierePremiere.size())
	      		{	
	      			MatierePremier matierePremiere=listMatierePremiere.get(j);
	      			mapMatierePremier.put(matierePremiere.getNom(), matierePremiere);
	      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
		      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
	      			
	      			comboMatierePremiere.addItem(matierePremiere.getNom());
	      			j++;
	      		}
	      	comboMatierePremiere.setSelectedIndex(-1);  
		
	      	 codeMatierePremiere.setText(Constantes.CODE_MP);
		
				  		 
	}
	void InitialiserMP()
	{
		
		 codeMatierePremiere.setText(Constantes.CODE_MP);
		comboMatierePremiere.setSelectedIndex(-1);
		txtpriorite.setText("");
		actif.setSelected(false);
		txtquantite.setText("");
		bouttonModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
	btnAjouter.setEnabled(true);	
		
	}
	
	void intialiser()
	{
		
	txtCode.setText("");
	comboBox.setSelectedItem("");	
	txtCode.setText(Constantes.CODE_MP);
	}
	
	void afficher_tableMP(List<DetailEstimationMP> listDetailEstimationMP)
	{

		 DecimalFormat format = new DecimalFormat("#0.000000");

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé","priorite","Actif"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleMP); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
	         table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimationMP.size())
			{	
				
				DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(i);
				
				Object []ligne={detailEstimationMP.getMatierePremier().getCode(),detailEstimationMP.getMatierePremier().getNom(),format.format(detailEstimationMP.getQuantite()),detailEstimationMP.getPriorite(),detailEstimationMP.isActif()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
}

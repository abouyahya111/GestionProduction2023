package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.MatierePremier;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjouterMPCompose extends JLayeredPane {
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
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private List<DetailEstimationMP> listDetailEstimationMP = new ArrayList<DetailEstimationMP>();
	
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP ();
	private JTextField txtCode;
	private JTextField txtLibelle;
	private JTextField codeMatierePremiere;
	private JTextField txtpriorite;
	private JTextField txtquantite;
	JComboBox comboMatierePremiere = new JComboBox();
	JCheckBox actif = new JCheckBox("Actif");
	JButton btnAjouter = new JButton("Ajouter MP");
	JButton bouttonModifier = new JButton("Modifier MP");
	JButton btnSupprimer = new JButton("Supprimer MP");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterMPCompose() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 719);
        try{
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesMPDAO= new ArticlesMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(359, 603, 112, 23);
				  		     add(btnIntialiserOF);
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
				  		     	public void mouseClicked(MouseEvent e) {
				  		     		

				  		     		
				  		     		if(listDetailEstimationMP.size()!=0)
				  				 	{
				  				 		if(table.getSelectedRow()!=-1)
				  				 		{
				  				 			
				  				 			txtCode.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 255, 938, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 223, 938, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 938, 96);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblarticle = new JLabel("Code Article");
				  		     	lblarticle.setBounds(10, 35, 144, 24);
				  		     	layeredPane.add(lblarticle);
				  		     	lblarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(339, 34, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		  txtCode.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
				  		  	if (e.getKeyCode() == e.VK_ENTER)
		  			  		{
				  		  	if(!txtCode.getText().equals(""))
			  		  		{
			  		  			
			  		  			
			  		  			MatierePremier matierePremier=matierePremiereDAO.findByCode(txtCode.getText());
			  		  			txtLibelle.setText(matierePremier.getNom());
			  		  			
			  		  		}	
		  			  		}
				  		  					  		  		
				  		  		
				  		  	}
				  		  });
				  		  txtCode.setBounds(98, 35, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  txtLibelle = new JTextField();
				  		 util.Utils.copycoller(txtLibelle);
				  		  txtLibelle.setColumns(10);
				  		  txtLibelle.setBounds(436, 35, 222, 24);
				  		  layeredPane.add(txtLibelle);
		
		JButton btnValiderTransfer = new JButton("Ajouter Article");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					if(listDetailEstimationMP.size()==0)	{
						JOptionPane.showMessageDialog(null, "Il faut remplir la liste des MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						if(txtCode.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Il faut saisir le code", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
							
						}else if(txtLibelle.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Il faut saisir le libelle", "Erreur", JOptionPane.ERROR_MESSAGE);
							
						return;
							
						} else {
							
						ArticlesMP articlesMPTmp=articlesMPDAO.findByCode(txtCode.getText().trim().toUpperCase());
						ArticlesMP articlesMPNom=articlesMPDAO.findByNom(txtLibelle.getText().trim().toUpperCase());
						
						if(articlesMPTmp!=null)
						{
							
							JOptionPane.showMessageDialog(null, "Code ArticlMP déja existant !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
							
							return;
							
						}else if(articlesMPNom!=null)
						{
							
			             JOptionPane.showMessageDialog(null, "Nom ArticlMP déja existant !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
							
							return;
							
						}else
						{
							
							
							articlesMP.setCodeArticle(txtCode.getText());
							articlesMP.setLiblle(txtLibelle.getText());
							articlesMP.setDetailEstimationMP(listDetailEstimationMP);
							articlesMPDAO.add(articlesMP);
							
							JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							
							intialiser();
							listDetailEstimationMP.clear();
							afficher_tableMP(listDetailEstimationMP);
							articlesMP =new ArticlesMP();
							InitialiserMP();
							
						}
							
							
						}
						
						
						
						
					}
					
				} catch (Exception e2) {JOptionPane.showMessageDialog(null, e2.getMessage());
					
				}
				
				

		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(191, 603, 158, 23);
		add(btnValiderTransfer);
	
				  		     
		txtCode.setText(Constantes.CODE_MP);		  		 
		
		JLabel label = new JLabel("Code :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(9, 150, 36, 26);
		add(label);
		
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
		codeMatierePremiere.setBounds(56, 151, 106, 26);
		add(codeMatierePremiere);
		
		JLabel label_1 = new JLabel("Nom :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(172, 150, 130, 26);
		add(label_1);
		
		 comboMatierePremiere = new JComboBox();
		 comboMatierePremiere.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
	     	 		 
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
		 comboMatierePremiere.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {}
		 });
		comboMatierePremiere.setSelectedIndex(-1);
		comboMatierePremiere.setBounds(224, 151, 315, 26);
		add(comboMatierePremiere);
		
		 AutoCompleteDecorator.decorate(comboMatierePremiere);
		
		JLabel label_2 = new JLabel("Priorite :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(549, 148, 60, 26);
		add(label_2);
		
		txtpriorite = new JTextField();
		txtpriorite.setEnabled(false);
		txtpriorite.setColumns(10);
		txtpriorite.setBounds(609, 151, 91, 26);
		add(txtpriorite);
		
		JLabel label_3 = new JLabel("Quantite :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(710, 151, 60, 26);
		add(label_3);
		
		txtquantite = new JTextField();
		txtquantite.setEnabled(false);
		txtquantite.setColumns(10);
		txtquantite.setBounds(770, 154, 91, 26);
		add(txtquantite);
		
		 actif = new JCheckBox("Actif");
		actif.setBounds(878, 153, 69, 23);
		add(actif);
		
		 btnAjouter = new JButton("Ajouter MP");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				
				
				
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
                     listDetailEstimationMP.add(detailEstimationMP);					
					afficher_tableMP(listDetailEstimationMP);
			
					InitialiserMP();
					
			
				
					
					
					
					
					
				
				
				
			}
				
				
				
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter.setBounds(132, 189, 98, 23);
		add(btnAjouter);
		
		 bouttonModifier = new JButton("Modifier MP");
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
					detailEstimationMP.setArticlesMP(articlesMP);
					detailEstimationMP.setMatierePremier(matierePremier);
					 detailEstimationMP.setQuantite(new BigDecimal(txtquantite.getText()));
					 detailEstimationMP.setPriorite(Integer.valueOf(txtpriorite.getText()));				
				     detailEstimationMP.setActif(actif.isSelected());
					
					listDetailEstimationMP.set(table.getSelectedRow() , detailEstimationMP);
					
					afficher_tableMP(listDetailEstimationMP);
					InitialiserMP();
							
					
			}
			
				
				
				
			}
		});
		bouttonModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bouttonModifier.setBounds(256, 189, 98, 23);
		bouttonModifier.setEnabled(false);
		add(bouttonModifier);
		
		 btnSupprimer = new JButton("Supprimer MP");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(table.getSelectedRow()!=-1)
				{
					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer MP ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							
							listDetailEstimationMP.remove(table.getSelectedRow());
							afficher_tableMP(listDetailEstimationMP);
							InitialiserMP();	
							
						}
					
					
					
								
				}
				
				
				
				
			
				
				
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.setBounds(377, 189, 98, 23);
		btnSupprimer.setEnabled(false);
		add(btnSupprimer);
		
		JButton button_3 = new JButton("Intialiser MP");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InitialiserMP();
				
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_3.setBounds(502, 189, 112, 23);
		add(button_3);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
		titledSeparator_1.setBounds(9, 113, 938, 30);
		add(titledSeparator_1);
	
 		 listMatierePremiere = matierePremiereDAO.findAll();	
 		 comboMatierePremiere.addItem("");
	      int i=0;
	      	while(i<listMatierePremiere.size())
	      		{	
	      			MatierePremier matierePremiere=listMatierePremiere.get(i);
	      			mapMatierePremier.put(matierePremiere.getNom(), matierePremiere);
	      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
		      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
	      			
	      			comboMatierePremiere.addItem(matierePremiere.getNom());
	      			i++;
	      		}
	      	comboMatierePremiere.setSelectedIndex(-1);  
	
	    	codeMatierePremiere.setText(Constantes.CODE_MP);
	
	
	}
	
	
	void intialiser()
	{
		txtCode.setText("");
		txtLibelle.setText("");
		txtCode.requestFocus();
		 
		InitialiserMP();
	}
	
	void InitialiserMP()
	{
		
		 codeMatierePremiere.setText(Constantes.CODE_MP);
		comboMatierePremiere.setSelectedIndex(-1);
		txtpriorite.setText("");
		actif.setSelected(false);
		txtquantite.setText("");
		btnSupprimer.setEnabled(false);
			bouttonModifier.setEnabled(false);
		
		
	}
	
	void afficher_tableMP(List<DetailEstimationMP> listDetailEstimationMP)
	{

		

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
	        table.getColumnModel().getColumn(3).setPreferredWidth(160);
	        table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimationMP.size())
			{	
				
				DetailEstimationMP detailEstimation=listDetailEstimationMP.get(i);
				
				Object []ligne={detailEstimation.getMatierePremier().getCode(), detailEstimation.getMatierePremier().getNom(),detailEstimation.getQuantite(), detailEstimation.getPriorite(),detailEstimation.isActif()};

				modeleMP.addRow(ligne);
				i++;
			}
			
	}
	




List<DetailEstimationMP> remplirDetailEstimationMP(){
	BigDecimal quantite=BigDecimal.ZERO;

	List<DetailEstimationMP> listDetailEstimationMP= new ArrayList<DetailEstimationMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		
//		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
//			detailEstimationMP.setPriorite(1);
//		else 
		detailEstimationMP.setPriorite(1);
		
		detailEstimationMP.setQuantite(quantite);
		detailEstimationMP.setMatierePremier(matierePremier);
		detailEstimationMP.setArticlesMP(articlesMP);
		
		listDetailEstimationMP.add(detailEstimationMP);
	}
	
	
	return listDetailEstimationMP;
	
}
}

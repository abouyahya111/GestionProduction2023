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
import dao.entity.MatierePremier;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;


public class ModifierEstimationArticle extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgModifier;
private JButton btnmodifier;
private JButton btnajouter;
	private JButton btnsupprimer;
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
	private JTextField txtcodematiere;
	private JTextField txtpriorite;
	private DetailEstimationDAO detailestimationdao;
	private JTextField txtlibelleArticle;
	private JCheckBox actif;
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
	private JLabel lblQuantite;
	private JTextField txtquantite= new JTextField();
	private JComboBox combocodearticle =new JComboBox();
	JCheckBox chckbxClient = new JCheckBox("Client");
	private JLabel labelclient;
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
	public ModifierEstimationArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 796);
        try{
        	
        	
        	clientDAO=new ClientDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesDAO= new ArticlesDAOImpl();
        	detailestimationdao= new DetailEstimationDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	 comboBox = new JComboBox();
        	 comboBox.addItem("");
        	 listeClient=clientDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
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
				  		     	 	 	listDetailEstimation.clear();
				  		     	 	 	afficher_tableMP(listDetailEstimation);
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(listDetailEstimation.size()!=0)
				  				 	{
				  				 		if(table.getSelectedRow()!=-1)
				  				 		{
				  				 			
				  				 			DetailEstimation detailEstimation= listDetailEstimation.get(table.getSelectedRow())	;
				  				 			txtcodematiere.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  				 			comboMatierePremiere.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString()); 
				  				 			
				  				 			txtpriorite.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				  				 			btnsupprimer.setEnabled(true);
				  				 			btnmodifier.setEnabled(true);
				  				 			btnajouter.setEnabled(false);
				  				 			//txtcodematiere.setEditable(false);
				  				 			
				  				 			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
					  		     			{
					  		     				txtquantite.setText(detailEstimation.getQuantite()+"");
					  		     				
					  		     			}else
					  		     			{
					  		     				txtquantite.setText("");
					  		     			}
				  				 			
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
				  		     	scrollPane.setBounds(36, 330, 1120, 344);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(36, 289, 1120, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(36, 13, 1120, 146);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 13, 67, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(313, 13, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		 
				  		  comboBox.setBounds(396, 14, 206, 24);
				  		  layeredPane.add(comboBox);
				  		 AutoCompleteDecorator.decorate(comboBox);
				  		  btnChercher = new JButton("Chercher");
				  		  btnChercher.setIcon(imgChercher);
				  		  btnChercher.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	article =articlesDAO.findByCode(combocodearticle.getSelectedItem().toString());
				  		  	
				  		
				  		  	
				  		  	listDetailEstimation=  detailestimationdao.findDetilestimationByArticle(article.getId());
				  		  listDetailEstimation.size();
				  		  	afficher_tableMP(listDetailEstimation);
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
				  		  
				  		  txtlibelleArticle = new JTextField();
				  		  txtlibelleArticle.setColumns(10);
				  		  txtlibelleArticle.setBounds(688, 13, 206, 26);
				  		  util.Utils.copycoller(txtlibelleArticle);
				  		  layeredPane.add(txtlibelleArticle);
				  		  
				  		  
				  		  JLabel lblArticle = new JLabel("    Article");
				  		  lblArticle.setBounds(611, 12, 67, 26);
				  		  layeredPane.add(lblArticle);
				  		  
				  		  JButton button = new JButton("Modifier");
				  		  button.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		
				  		  		if(!comboBox.getSelectedItem().equals(""))
				  		  		{
				  		  			
				  		  				Articles article=articlesDAO.findByCode(combocodearticle.getSelectedItem().toString());
				  		  				
				  		  				
				  		  			if(!txtlibelleArticle.getText().equals(""))
				  		  			{
				  		  				
				  		  			Articles articleTmp = articlesDAO.findByLibelle(txtlibelleArticle.getText());
				  		  			if(articleTmp==null )
				  		  			{
				  		  			article.setLiblle(txtlibelleArticle.getText());
				  		  			
				  		  			}else if(articleTmp!=null &&  !articleTmp.getLiblle().equals(article.getLiblle()))
				  		  			{
				  		  			 JOptionPane.showMessageDialog(null, "Article Déja existant ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		  			 return;
				  		  			}
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			}
				  		  			
				  		  			
				  		  			if(combogrammagebox.getSelectedIndex()!=-1)
				  		  			{
				  		  				
				  		  				
				  		  			article.setCentreCout3(mapGrammageBox.get(combogrammagebox.getSelectedItem()));	
				  		  				
				  		  				
				  		  			}
				  		  			if(combogrammagecarton.getSelectedIndex()!=-1)
				  		  			{
				  		  			article.setCentreCout4(mapGrammageCarton.get(combogrammagecarton.getSelectedItem()));	
				  		  			}
				  		  			
				  		  		article.setClient(chckbxClient.isSelected());
				  		  			
				  		  			
				  		  			
				  		  			articlesDAO.edit(article);
				  		  		    JOptionPane.showMessageDialog(null, "Article Modifier Avec Succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
				  		  			ChargerComboArticle();
				  		  		InitialiserMP();
				  		  	intialiser();
		  		     	 	     	listDetailEstimation.clear();
		  		     	 	 	    afficher_tableMP(listDetailEstimation);
				  		  				
				  		  			
				  		  			
				  		  		}
				  		  		
				  		  	}
				  		  });
				  		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  button.setBounds(239, 112, 107, 24);
				  		  layeredPane.add(button);
				  		  
				  		  label_3 = new JLabel("Grammage Box :");
				  		  label_3.setBounds(10, 47, 108, 26);
				  		  layeredPane.add(label_3);
				  		  
				  		  combogrammagebox = new JComboBox();
				  		  combogrammagebox.setSelectedIndex(-1);
				  		  combogrammagebox.setBounds(97, 51, 167, 22);
				  		  layeredPane.add(combogrammagebox);
				  		  
				  		  label_4 = new JLabel("Grammage carton :");
				  		  label_4.setBounds(274, 50, 108, 26);
				  		  layeredPane.add(label_4);
				  		  
				  		  combogrammagecarton = new JComboBox();
				  		  combogrammagecarton.setSelectedIndex(-1);
				  		  combogrammagecarton.setBounds(377, 54, 205, 22);
				  		  layeredPane.add(combogrammagecarton);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(36, 170, 1120, 108);
		add(layeredPane_1);
		
		JLabel label_1 = new JLabel("Code Matiere :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 11, 82, 26);
		layeredPane_1.add(label_1);
		
		JLabel label_2 = new JLabel("Libelle :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(216, 11, 68, 26);
		layeredPane_1.add(label_2);
		
		 btnmodifier = new JButton("Modifier");
		 btnmodifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
				
				

				
				if(combogrammagebox.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Box SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				if(combogrammagecarton.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Carton SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
				}	
				
				
				
			if(txtcodematiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
			{
				JOptionPane.showMessageDialog(null, "Il faut selectionner MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;	
				
			}else
			{
				

				
				MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
				
					
					BigDecimal grammagebox=mapGrammageBox.get(combogrammagebox.getSelectedItem());
					BigDecimal grammageCarton=mapGrammageCarton.get(combogrammagecarton.getSelectedItem()); // grammage carton
					
					
					 if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							if(txtpriorite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de l'En Vrac !!!!");
								return;
							}
								
						}
					
					DetailEstimation detailEstimation=listDetailEstimation.get(table.getSelectedRow());
					detailEstimation.setArticles(article);
					detailEstimation.setMatierePremier(matierePremier);
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX)  || matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE))
					{
						detailEstimation.setQuantite(grammagebox.divide(new BigDecimal(1000)));
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
					}else if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON))
					{
						detailEstimation.setQuantite(grammageCarton);
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
						
					}else if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						if(txtpriorite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de l'En Vrac !!!!");
							return;
						}else
						{
							
							detailEstimation.setQuantite(BigDecimal.ZERO);
							detailEstimation.setPriorite( Integer.valueOf(txtpriorite.getText()));
							
						}
						
						
						
						
					}else
					{
						
						detailEstimation.setQuantite(new BigDecimal(txtquantite.getText()) );
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
						
						
					}
					
					detailEstimation.setActif(actif.isSelected());
					
					listDetailEstimation.set(table.getSelectedRow() , detailEstimation);
					detailestimationdao.edit(detailEstimation);
					afficher_tableMP(listDetailEstimation);
					InitialiserMP();
					
					
				
				
					
					
					
					
					
					
			}
			
				
				
				
			
				
				
				
				
				
				
				
			}
		 });
		btnmodifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnmodifier.setEnabled(false);
		btnmodifier.setBounds(216, 61, 107, 24);
		layeredPane_1.add(btnmodifier);
		
		JButton btninitialiser = new JButton("Initialiser");
		btninitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InitialiserMP();
				
				
				
			}
		});
		btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btninitialiser.setBounds(348, 62, 106, 23);
		layeredPane_1.add(btninitialiser);
		
		txtcodematiere = new JTextField();
		util.Utils.copycoller(txtcodematiere);
		txtcodematiere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
	      			if(!txtcodematiere.getText().equals(""))
	      			{
	      				MatierePremier matierepremiere=matierePremiereDAO.findByCode(txtcodematiere.getText().toUpperCase());
			    		
			    		if(matierepremiere!=null)
			    		{	
			    			comboMatierePremiere.setSelectedItem(matierepremiere.getNom());
			    			
			    		}else
			    		{
			    			 JOptionPane.showMessageDialog(null, "Code matiere premiere Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
			    			 comboMatierePremiere.setSelectedIndex(-1);
			    			 txtcodematiere.requestFocus();
			    		}
			    		
	      		}else
	      		{
	      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code matiere premiere SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	      			comboMatierePremiere.setSelectedIndex(-1);
	      			txtcodematiere.requestFocus();
	      		}
	      		}
			
				
				
				
				
				
				
			}
		});
		txtcodematiere.setColumns(10);
		txtcodematiere.setBounds(89, 11, 120, 26);
		layeredPane_1.add(txtcodematiere);
		
		 btnsupprimer = new JButton("Supprimer");
		 btnsupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(table.getSelectedRow()!=-1)
				{
					if(listDetailEstimation.size()!=0)
					{
						 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la matiere premiere ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
								
							  detailestimationdao.delete(listDetailEstimation.get(table.getSelectedRow()).getId());
							  listDetailEstimation.remove(table.getSelectedRow());
								afficher_tableMP(listDetailEstimation);
								InitialiserMP();
								
							}
						}
					}
		 		
		 		
		 	}
		 });
		btnsupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnsupprimer.setEnabled(false);
		btnsupprimer.setBounds(484, 61, 107, 24);
		layeredPane_1.add(btnsupprimer);
		
		 btnajouter = new JButton("Ajouter");
		 btnajouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
				
				if(combogrammagebox.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Box SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				if(combogrammagecarton.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Carton SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;	
				}	
				
				
				
			if(txtcodematiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
			{
				JOptionPane.showMessageDialog(null, "Il faut selectionner MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;	
				
				
			}else
			{
				

				
				MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
			
					
					BigDecimal grammagebox=mapGrammageBox.get(combogrammagebox.getSelectedItem());
					BigDecimal grammageCarton=mapGrammageCarton.get(combogrammagecarton.getSelectedItem()); // grammage carton
					
					
					 if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							if(txtpriorite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de l'En Vrac !!!!");
								return;
							}
								
						}
					
					DetailEstimation detailEstimation=new DetailEstimation();
					detailEstimation.setArticles(article);
					detailEstimation.setMatierePremier(matierePremier);
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX)  || matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE))
					{
						detailEstimation.setQuantite(grammagebox.divide(new BigDecimal(1000)));
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
					}else if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON))
					{
						detailEstimation.setQuantite(grammageCarton);
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
						
					}else if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						if(txtpriorite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de l'En Vrac !!!!");
							return;
						}else
						{
							
							
							detailEstimation.setQuantite(BigDecimal.ZERO);
							detailEstimation.setPriorite( Integer.valueOf(txtpriorite.getText()));
						}
						
						
						
						
					}else
					{
						
						
						detailEstimation.setQuantite(new BigDecimal(txtquantite.getText()) );
						if(matierePremier.getCode().contains("C"))
						{
							if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
						}else
						{
							
							if(mapCode.get(matierePremier.getCode()+"C")!=null)
							 {

								 if(txtpriorite.getText().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer la priorite de : "+matierePremier.getCode());
										return;
									}else
									{
										 detailEstimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
									}
		  		     			 
		  		     			 
							 }else
							 {
								 
								 detailEstimation.setPriorite(0);
								 
							 }
							
							
						}
						
					}
					
					detailEstimation.setActif(actif.isSelected());
					
					listDetailEstimation.add(detailEstimation);
					detailestimationdao.add(detailEstimation);
					afficher_tableMP(listDetailEstimation);
					InitialiserMP();
					
					
				
					
					
					
					
					
				
				
				
			}
				
				
				
			}
		 });
		btnajouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnajouter.setBounds(608, 61, 107, 24);
		layeredPane_1.add(btnajouter);
		
		JLabel lblPriorit = new JLabel("Priorit\u00E9 :");
		lblPriorit.setBounds(629, 11, 61, 26);
		layeredPane_1.add(lblPriorit);
		
		txtpriorite = new JTextField();
		util.Utils.copycoller(txtpriorite);
		txtpriorite.setColumns(10);
		txtpriorite.setBounds(684, 11, 82, 26);
		layeredPane_1.add(txtpriorite);
		
		actif = new JCheckBox("Actif");
		actif.setBounds(937, 13, 69, 23);
		layeredPane_1.add(actif);
		
		 comboMatierePremiere = new JComboBox();
		 comboMatierePremiere.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

		 		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 		 
		     	 		 if(comboMatierePremiere.getSelectedIndex()!=-1)
		     	 		 {
		     	 			 
		     	 			txtcodematiere.setText(mapLibelle.get(comboMatierePremiere.getSelectedItem()));
	  		     	   	
		  		     	 MatierePremier matierePremier=mapMatierePremier.get(comboMatierePremiere.getSelectedItem())	;
		  		     	   actif.setSelected(true);	 
		  		     	if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		     	{
		  		     		
		  		     		
		  		     		txtpriorite.setEnabled(true);
		  		     		
		  		     		
		  		     	}else
		  		     	{
		  		     		if(matierePremier.getCode().contains("C"))
							{
		  		     			
		  		     			
		  		     		 if(mapCode.get(matierePremier.getCode().replace("C", "").trim())!=null)
							 {
		  		     			txtpriorite.setEnabled(true);
		  		     			 
		  		     			 
							 }else
							 {
								 txtpriorite.setEnabled(false);
							 }
		  		     			
		  		     			
		  		     		 
		  		     			
							}else
							{
								
								if(mapCode.get(matierePremier.getCode()+"C")!=null)
								 {
			  		     			txtpriorite.setEnabled(true);
			  		     			 
			  		     			 
								 }else
								 {
									 txtpriorite.setEnabled(false);
								 }
								
							}
		  		     	}
		  		     	
		  		  	if(!matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) && !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) && !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) && !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) )
	  		     	{
	  		     		txtquantite.setEnabled(true);
	  		     	}else
	  		     	{
	  		     		txtquantite.setEnabled(false);
	  		     	}
		  		     	
		  		     	
		  		     	
		  		     	
		     	 			 
		     	 		 }else
 		     	  {
 		     		  combocodearticle.setSelectedItem("");
 		     		txtcodematiere.setText(Constantes.CODE_MP);	
 		     	  }
 		    
 		     	   	
	                   
		     	 	 	}
		     	 	
		 		
		 	
		 		
		 	}
		 });
		comboMatierePremiere.setSelectedIndex(-1);
		comboMatierePremiere.setBounds(262, 11, 340, 26);
		layeredPane_1.add(comboMatierePremiere);
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
			  			
			  		if(articles.getCentreCout4()!=null)  /// Grammage Box
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
	     	 	 	listDetailEstimation.clear();
	     	 	 	afficher_tableMP(listDetailEstimation);
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
	 			labelclient.setVisible(true);
	 		}else
	 		{

	 			comboClient.setVisible(false);
	 			comboClient.setSelectedItem("");
	 			labelclient.setVisible(false);

	 		}
	 	
   		
   	
	   	}
	   });
	  chckbxClient.setBounds(590, 51, 67, 23);
	  layeredPane.add(chckbxClient);
	  
	  labelclient = new JLabel("Client :");
	  labelclient.setBounds(677, 50, 79, 26);
	  layeredPane.add(labelclient);
	  
	  comboClient = new JComboBox();
	  comboClient.setSelectedIndex(-1);
	  comboClient.setBounds(735, 54, 330, 22);
	  layeredPane.add(comboClient);
	  AutoCompleteDecorator.decorate(comboClient);
	  listMatierePremiere = matierePremiereDAO.findAll();	
	  	
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
	     	
	     	lblQuantite = new JLabel("Quantite :");
	     	lblQuantite.setBounds(774, 11, 61, 26);
	     	layeredPane_1.add(lblQuantite);
	     	
	     	txtquantite = new JTextField();
	     	txtquantite.setColumns(10);
	     	txtquantite.setBounds(829, 11, 82, 26);
	     	layeredPane_1.add(txtquantite);
	     	
	     	
	    	ChargerComboArticle();
				  		 
	
			txtcodematiere.setText(Constantes.CODE_MP);	
	
			comboClient.addItem("");
			 
			for(int t=0;t<listeClient.size();t++)
			{
				
				Client client=listeClient.get(t);
				comboClient.addItem(client.getNom());
				 
				MapClient.put(client.getNom(), client);
				
			}
			comboClient.setSelectedItem("");
			comboClient.setVisible(false);
			labelclient.setVisible(false);
			
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
	txtlibelleArticle.setText("");
	chckbxClient.setSelected(false);
	 combogrammagebox.setSelectedIndex(-1);
	 combogrammagecarton.setSelectedIndex(-1);
	}
	
	void afficher_tableMP(List<DetailEstimation> listDetailEstimation)
	{
		 DecimalFormat format = new DecimalFormat("#0.000000");
		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé","Priorité"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false
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
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimation.size())
			{	
				
				DetailEstimation detailEstimation=listDetailEstimation.get(i);
				
				Object []ligne={detailEstimation.getMatierePremier().getCode(),detailEstimation.getMatierePremier().getNom(),format.format(detailEstimation.getQuantite()),detailEstimation.getPriorite()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	


boolean remplirMapQuantiteEstimation(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailEstimation> remplirDetailEstimation(List<DetailEstimation> listDetailEstimation){
	BigDecimal quantite=BigDecimal.ZERO;

	
	List<DetailEstimation> listDetailEstimationTmp= new ArrayList<DetailEstimation>();
	
	
	for(int i=0;i<listDetailEstimation.size();i++){
		
		DetailEstimation detailEstimation=listDetailEstimation.get(i);
	
		
		quantite=new BigDecimal(mapQuantiteMP.get(detailEstimation.getMatierePremier().getCode()));
		
		detailEstimation.setQuantite(quantite);
		
		listDetailEstimationTmp.add(detailEstimation);
	}
	
	
	return listDetailEstimationTmp;
	
}

void InitialiserMP()
{
	
	txtcodematiere.setText("");
	comboMatierePremiere.setSelectedIndex(-1);
	txtpriorite.setText("");
	txtquantite.setText("");
	actif.setSelected(false);
	txtcodematiere.setText(Constantes.CODE_MP);	
	
	
	
}
}

package presentation.article;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.MatierePremier;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CreerEstimationArticle extends JLayeredPane {
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
	private JTextField txtLibelle;
	private JTextField txtConditionnement;
	private JTextField codeMatierePremiere;
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
	private JTextField txtquantite =new JTextField();
	 private DetailEstimationDAO detailestimationdao;
	 JCheckBox chckbxClient = new JCheckBox("Client");
	 JComboBox comboClient = new JComboBox();
	 JLabel labelClient = new JLabel("Client :");
	 ClientDAO clientDAO;
		private Map< String, Client> MapClient = new HashMap<>();
		List<Client> listeClient= new ArrayList<Client>();
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CreerEstimationArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 775);
        try{
        	
        	
         	clientDAO=new ClientDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesDAO= new ArticlesDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	detailestimationdao=new DetailEstimationDAOImpl();
        	listeClient=clientDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(318, 187, 112, 23);
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
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(table.getSelectedRow()!=-1)
				  		     		{
				  		     			
				  		     		DetailEstimation detailEstimation= listDetailEstimation.get(table.getSelectedRow())	;
				  		     			codeMatierePremiere.setText(detailEstimation.getMatierePremier().getCode());
				  		     			comboMatierePremiere.setSelectedItem(detailEstimation.getMatierePremier().getNom());
				  		     			txtpriorite.setText(detailEstimation.getPriorite()+"");
				  		     			actif.setSelected(detailEstimation.isActif());
				  		     			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
				  		     			{
				  		     				txtquantite.setText(detailEstimation.getQuantite()+"");
				  		     				
				  		     			}else
				  		     			{
				  		     				txtquantite.setText("");
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
				  		     	scrollPane.setBounds(9, 342, 808, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 221, 880, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 938, 163);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 35, 144, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(260, 32, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  txtCode = new JTextField();
				  		  
				  		util.Utils.copycoller(txtCode);
				  		  txtCode.setBounds(98, 35, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  txtLibelle = new JTextField();
				  		util.Utils.copycoller(txtLibelle);
				  		  txtLibelle.setColumns(10);
				  		  txtLibelle.setBounds(343, 35, 440, 24);
				  		  layeredPane.add(txtLibelle);
				  		  
				  		  JLabel lblCon = new JLabel("Conditionnement");
				  		  lblCon.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblCon.setBounds(10, 76, 144, 24);
				  		  layeredPane.add(lblCon);
				  		  
				  		  txtConditionnement = new JTextField();
				  		util.Utils.copycoller(txtConditionnement);
				  		  txtConditionnement.setColumns(10);
				  		  txtConditionnement.setBounds(98, 76, 144, 24);
				  		  layeredPane.add(txtConditionnement);
				  		  
				  		  JLabel lblGrammageBox = new JLabel("Grammage Box :");
				  		  lblGrammageBox.setBounds(260, 74, 108, 26);
				  		  layeredPane.add(lblGrammageBox);
				  		  
				  		   combogrammagebox = new JComboBox();
				  		  combogrammagebox.setBounds(346, 78, 150, 22);
				  		  layeredPane.add(combogrammagebox);
				  		  
				  		  JLabel lblGrammageCarton = new JLabel("Grammage carton :");
				  		  lblGrammageCarton.setBounds(520, 74, 108, 26);
				  		  layeredPane.add(lblGrammageCarton);
				  		  
				  		   combogrammagecarton = new JComboBox();
				  		  combogrammagecarton.setBounds(623, 78, 160, 22);
				  		  layeredPane.add(combogrammagecarton);
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
						codeMatierePremiere.setBounds(56, 262, 106, 26);
						add(codeMatierePremiere);
						 comboMatierePremiere = new JComboBox();
						 comboMatierePremiere.addItemListener(new ItemListener() {
						 	public void itemStateChanged(ItemEvent e) {
						 		

			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 		 
			  		     	 		 if(comboMatierePremiere.getSelectedIndex()!=-1)
			  		     	 		 {
			  		     	 			 
			  		     	 			codeMatierePremiere.setText(mapLibelle.get(comboMatierePremiere.getSelectedItem()));
					  		     	   	
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
						  		     	
						  		     	
						  		     	if(!matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) && !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) && !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)&& !matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE))
						  		     	{
						  		     		txtquantite.setEnabled(true);
						  		     	}else
						  		     	{
						  		     		txtquantite.setEnabled(false);
						  		     	}
			  		     	 			 
			  		     	 		 }else
				  		     	  {
				  		     		  txtCode.setText("");
				  		     	  }
				  		    
				  		     	   	
			  	                   
			  		     	 	 	}
			  		     	 	
						 		
						 	}
						 });
						comboMatierePremiere.setBounds(224, 262, 315, 26);
						add(comboMatierePremiere);
						
						
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
					      	
		
		JButton btnValiderTransfer = new JButton("Ajouter Article");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
				if(txtCode.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}else if(txtLibelle.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le libelle", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				} else if(txtConditionnement.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut remplir le conditionnement", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					
					Articles articleexiste=articlesDAO.findByCode(txtCode.getText());
					Articles articleLibelleexiste=articlesDAO.findByLibelle(txtLibelle.getText());
					
				if(chckbxClient.isSelected()==true)	
				{
					if(comboClient.getSelectedItem()!=null)
					{
						Client client=MapClient.get(comboClient.getSelectedItem().toString());
						if(client==null)
						{
							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Client SVP");
							return;
						}
						
					}else
					{
						JOptionPane.showMessageDialog(null, "veuillez Selectionner le Client SVP");
						return;
					}
					 
				
					
					
				}
					
					
					if(articleexiste!=null || articleLibelleexiste!=null)
					{
						JOptionPane.showMessageDialog(null, "Article existe déja !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}else
					{
						if(listDetailEstimation.size()!=0)
						{
						article.setDateCreation(new Date());
						article.setCodeArticle(txtCode.getText());
						article.setLiblle(txtLibelle.getText());
						article.setConditionnement(new BigDecimal(txtConditionnement.getText()));
						
						article.setClient(chckbxClient.isSelected());
						
						if(chckbxClient.isSelected()==true)	
						{
							 
								Client client=MapClient.get(comboClient.getSelectedItem().toString());
						 
								article.setArticleClient(client);
						
							
							
						}
						
						
						//article.setDetailEstimation(listDetailEstimation);
						if(combogrammagebox.getSelectedIndex()!=-1)
						{
							
							article.setCentreCout3(mapGrammageBox.get(combogrammagebox.getSelectedItem()));
							
						}else
						{
							article.setCentreCout3(BigDecimal.ONE);
							
						}
						
						
						if(combogrammagecarton.getSelectedIndex()!=-1)
						{
							
							article.setCentreCout4(mapGrammageCarton.get(combogrammagecarton.getSelectedItem()));  //// le poid de Carton
							
						}else
						{
							article.setCentreCout4(BigDecimal.ONE);
							
						}
						articlesDAO.add(article);
						
						for(int i=0;i<listDetailEstimation.size();i++)
						{
							
							detailestimationdao.add(listDetailEstimation.get(i));
							
							
						}
						
						
						
						JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						intialiser();
						listDetailEstimation.clear();
						afficher_tableMP(listDetailEstimation);
						article =new Articles ();
						
						
						
						
						}else
						{
							

							article.setDateCreation(new Date());
							article.setCodeArticle(txtCode.getText());
							article.setLiblle(txtLibelle.getText());
							article.setConditionnement(new BigDecimal(txtConditionnement.getText()));		
							article.setClient(chckbxClient.isSelected());
							
							if(chckbxClient.isSelected()==true)	
							{
								 
									Client client=MapClient.get(comboClient.getSelectedItem().toString());
							 
									article.setArticleClient(client);
							
								
								
							}
							
							if(combogrammagebox.getSelectedIndex()!=-1)
							{
								
								article.setCentreCout3(mapGrammageBox.get(combogrammagebox.getSelectedItem()));  /// le poid de box
								
							}else
							{
								article.setCentreCout3(BigDecimal.ONE);
								
							}
							
							if(combogrammagecarton.getSelectedIndex()!=-1)
							{
								
								article.setCentreCout4(mapGrammageCarton.get(combogrammagecarton.getSelectedItem()));  //// le poid de Carton
								
							}else
							{
								article.setCentreCout4(BigDecimal.ONE);
								
							}
							
							articlesDAO.add(article);
							JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							intialiser();
							listDetailEstimation.clear();
							afficher_tableMP(listDetailEstimation);
							article =new Articles ();
							
							
							
							
							
							
							
							
						}
					
				}
				
				
				
				
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(253, 690, 158, 23);
		add(btnValiderTransfer);
		
		JLabel label = new JLabel("Code :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(9, 261, 114, 26);
		add(label);
		
	
		
		JLabel label_1 = new JLabel("Nom :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(172, 261, 130, 26);
		add(label_1);
		
		JButton btnAjouterMp = new JButton("Ajouter MP");
		btnAjouterMp.addActionListener(new ActionListener() {
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
				
				
				
			if(codeMatierePremiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
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
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE))
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
						
						if(!txtquantite.getText().equals(""))
						{
							detailEstimation.setQuantite(new BigDecimal(txtquantite.getText()));
						}else
						{
							detailEstimation.setQuantite(BigDecimal.ZERO);
						}
						
						
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
					
					afficher_tableMP(listDetailEstimation);
					InitialiserMP();
					
					
			
				
					
					
					
					
					
				
				
				
			}
				
				
				
			}
		});
		btnAjouterMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouterMp.setBounds(100, 308, 98, 23);
		add(btnAjouterMp);
		
		JLabel lblPriorite = new JLabel("Priorite :");
		lblPriorite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPriorite.setBounds(549, 259, 60, 26);
		add(lblPriorite);
		
		txtpriorite = new JTextField();
		txtpriorite.setColumns(10);
		txtpriorite.setBounds(609, 262, 91, 26);
		txtpriorite.setEnabled(false);
		add(txtpriorite);
		
		 actif = new JCheckBox("Actif");
		actif.setBounds(878, 264, 69, 23);
		add(actif);
		listGrammageBox=grammageBoxDAO.findAll();
    	listGrammageCarton=grammageCartonDAO.findAll();
	  
	  for(int j=0;j<listGrammageBox.size() ; j++)
	  {
		  
		  GrammageBox gramaBox=listGrammageBox.get(j);
		  combogrammagebox.addItem(gramaBox.getCodeGrammage());
		  mapGrammageBox.put(gramaBox.getCodeGrammage(), gramaBox.getGrammageBox());
		  
	  }
	  combogrammagebox.setSelectedIndex(-1);
	  
	  
	  for(int k=0;k<listGrammageCarton.size() ; k++)
	  {
		  
		  GrammageCarton gramacaCarton=listGrammageCarton.get(k);
		  combogrammagecarton.addItem(gramacaCarton.getCodeGrammage());
		  mapGrammageCarton.put(gramacaCarton.getCodeGrammage(), gramacaCarton.getGrammageCarton());
		  
	  }
	  combogrammagecarton.setSelectedIndex(-1);
	  
	   chckbxClient = new JCheckBox("Client");
	   chckbxClient.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent arg0) {
	   		

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
	  chckbxClient.setBounds(813, 36, 88, 23);
	  layeredPane.add(chckbxClient);
	  
	    labelClient = new JLabel("Client :");
	  labelClient.setBounds(10, 111, 108, 26);
	  layeredPane.add(labelClient);
	  
	    comboClient = new JComboBox();
	  comboClient.setSelectedIndex(-1);
	  comboClient.setBounds(96, 115, 315, 22);
	  layeredPane.add(comboClient);
	  
	  AutoCompleteDecorator.decorate(comboClient);
		JButton btnModifierMp = new JButton("Modifier MP");
		btnModifierMp.addActionListener(new ActionListener() {
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
				
				
				
			if(codeMatierePremiere.getText().equals("") || comboMatierePremiere.getSelectedIndex()==-1)	
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
						
						detailEstimation.setQuantite(new BigDecimal(txtquantite.getText()));
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
					
					afficher_tableMP(listDetailEstimation);
					InitialiserMP();
					
					
				
				
					
					
					
					
					
					
			}
			
				
				
				
			
				
				
				
				
				
				
				
			}
		});
		btnModifierMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModifierMp.setBounds(224, 308, 98, 23);
		add(btnModifierMp);
		
		JButton btnSupprimerMp = new JButton("Supprimer MP");
		btnSupprimerMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow()!=-1)
				{
					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer MP ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							
							listDetailEstimation.remove(table.getSelectedRow());
							afficher_tableMP(listDetailEstimation);
							InitialiserMP();	
							
						}
					
					
					
								
				}
				
				
				
				
			}
		});
		btnSupprimerMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimerMp.setBounds(345, 308, 98, 23);
		add(btnSupprimerMp);
		
		JButton btnIntialiserMp = new JButton("Intialiser MP");
		btnIntialiserMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitialiserMP();
			}
		});
		btnIntialiserMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIntialiserMp.setBounds(470, 308, 112, 23);
		add(btnIntialiserMp);
		
		JLabel lblQuantite = new JLabel("Quantite :");
		lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantite.setBounds(710, 262, 60, 26);
		add(lblQuantite);
		
		txtquantite = new JTextField();
		txtquantite.setEnabled(false);
		txtquantite.setColumns(10);
		txtquantite.setBounds(770, 265, 91, 26);
		add(txtquantite);
		
		
		comboClient.addItem("");
		 
		for(int t=0;t<listeClient.size();t++)
		{
			
			Client client=listeClient.get(t);
			comboClient.addItem(client.getNom());
			 
			MapClient.put(client.getNom(), client);
			
		}
		comboClient.setSelectedItem("");
		comboClient.setVisible(false);
		
				  		 
	}
	
	
	void intialiser()
	{
		txtCode.setText("");
		txtConditionnement.setText("");
		txtLibelle.setText("");
		combogrammagebox.setSelectedIndex(-1);
		combogrammagecarton.setSelectedIndex(-1);
		chckbxClient.setSelected(false);
		comboClient.setVisible(false);
	}
	
	
	void InitialiserMP()
	{
		
		codeMatierePremiere.setText("");
		comboMatierePremiere.setSelectedIndex(-1);
		txtpriorite.setText("");
		actif.setSelected(false);
		txtquantite.setText("");
		
		
		
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

List<DetailEstimation> remplirDetailEstimation(){
	BigDecimal quantite=BigDecimal.ZERO;

	
	List<DetailEstimation> listDetailEstimation= new ArrayList<DetailEstimation>();
	
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailEstimation detailEstimation=new DetailEstimation();
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal((mapQuantiteMP.get(matierePremier.getCode()))) ;
		
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			detailEstimation.setPriorite(1);
		else 
			detailEstimation.setPriorite(0);
		
		detailEstimation.setQuantite(quantite);
		detailEstimation.setMatierePremier(matierePremier);
		detailEstimation.setArticles(article);
		
		listDetailEstimation.add(detailEstimation);
	}
	
	
	return listDetailEstimation;
	
}
}

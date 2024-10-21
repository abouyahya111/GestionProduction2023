package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.CategorieMp;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.MatierePremier;
import dao.entity.MatierePremierImprimer;
import dao.entity.SubCategorieMp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import javax.swing.JCheckBox;


public class ListeMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	List<MatierePremier> listeMatierePremiere= new ArrayList<MatierePremier>();
	List<MatierePremier> listeMatierePremiereCombo= new ArrayList<MatierePremier>();
	List<MatierePremierImprimer> listeMatierePremiereImprimer= new ArrayList<MatierePremierImprimer>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP ();
	private JComboBox soucategoriempcombo;
	private JComboBox categoriempcombo;
	private CategorieMp categoriemp;
	private CategorieMpDAO categoriempdao;
	private SubCategorieMp subcategoriemp;
	private SubCategorieMPDAO subcategoriempdao;
	private JButton btnAfficher;
	private JTextField txtcode=new JTextField();
	JComboBox combomp = new JComboBox();
	JDateChooser dateSituation = new JDateChooser();	
	JCheckBox CheckTous = new JCheckBox("Tous");
	JCheckBox CheckSansClient = new JCheckBox("Sans Client");
	JCheckBox checkClient = new JCheckBox("Client");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ListeMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 629);
        try{
        	
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	articlesMPDAO= new ArticlesMPDAOImpl();
        	categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	 imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	listsubcategoriemp=subcategoriempdao.findAll();
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
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
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 167, 1191, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 1191, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1250, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblsouscategorie = new JLabel("Sous-Categorie Mp");
				  		     	lblsouscategorie.setBounds(363, 23, 144, 24);
				  		     	layeredPane.add(lblsouscategorie);
				  		     	lblsouscategorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Categorie Mp");
				  		  lblDpotDestination.setBounds(704, 22, 80, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  soucategoriempcombo = new JComboBox();
				  		  soucategoriempcombo.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		int i=0;
				  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
				  		  		{
				  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
				  		  			{
				  		  			categoriempcombo.removeAllItems();
				  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
				  		  			if(listecategoriemp!=null)
				  		  			{
				  		  				while(i<listecategoriemp.size())
				  		  				{
				  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
				  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
				  		  					i++;
				  		  				}
				  		  				
				  		  			}
				  		  				
				  		  			}else
				  		  			{
				  		  			listecategoriemp.clear();
				  		  			categoriempcombo.removeAllItems();
				  		  		categoriempcombo.addItem("");
				  		  			combomp.removeAllItems();
				  		  			}
				  		  	
				  		  			
				  		  		}else
				  		  		{
				  		  		listecategoriemp.clear();
				  		  		categoriempcombo.removeAllItems();
				  		  	categoriempcombo.addItem("");
				  		  	combomp.removeAllItems();
				  		  		}
				  		  		
				  		  	}
				  		  });
				  		  soucategoriempcombo.setBounds(486, 24, 208, 24);
				  		  layeredPane.add(soucategoriempcombo);
				  		AutoCompleteDecorator.decorate(soucategoriempcombo);
				  		  
				  		  categoriempcombo = new JComboBox();
				  		  categoriempcombo.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent arg0) {
				  		  		
				  		  		if(categoriempcombo.getSelectedIndex()!=-1)
				  		  		{
				  		  			
				  		  			if(!categoriempcombo.getSelectedItem().equals(""))
				  		  			{
				  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
				  		  				listeMatierePremiereCombo.clear();
				  		  			
				  		  		txtcode.setText(Constantes.CODE_MP);	
			  		  				combomp.removeAllItems();
			  		  			combomp.addItem("");
			  		  			
			  		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
				  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
				  		  			{
				  		  				
				  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
				  		  				combomp.addItem(matierePremier.getNom());
				  		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
				  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
				  		  				
				  		  			}
				  		  				
				  		  				
				  		  				
				  		  			}else
				  		  			{
				  		  			listeMatierePremiereCombo.clear();
				  		  		txtcode.setText(Constantes.CODE_MP);	
				  		  				combomp.removeAllItems();
				  		  			combomp.addItem("");
				  		  				
				  		  			}
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			
				  		  			
				  		  		}else
				  		  		{
				  		  		listeMatierePremiereCombo.clear();
				  				txtcode.setText(Constantes.CODE_MP);	
		  		  				combomp.removeAllItems();
		  		  			combomp.addItem("");
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  categoriempcombo.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  categoriempcombo.setBounds(779, 23, 272, 24);
				  		  layeredPane.add(categoriempcombo);
				  		AutoCompleteDecorator.decorate(categoriempcombo);
				  		  categoriempcombo.removeAllItems();
				  		  categoriempcombo.addItem("");
				  		soucategoriempcombo.removeAllItems();
				  		soucategoriempcombo.addItem("");
				  		
				  		btnAfficher = new JButton("Afficher");
				  		btnAfficher.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				String req="";
				  				
				  				if(checkClient.isSelected()==true)
				  				{
				  					req=req+" and type='"+Constantes.MP_CLIENT+"' ";
				  				}
				  				
				  				if(CheckSansClient.isSelected()==true)
				  				{
				  					req=req+" and type='"+Constantes.MP_INTERNE+"' ";
				  				}
				  				
				  				if(soucategoriempcombo.getSelectedIndex()!=-1 || !soucategoriempcombo.getSelectedItem().equals("") || !categoriempcombo.getSelectedItem().equals("") || categoriempcombo.getSelectedIndex()!=-1  || combomp.getSelectedIndex()!=-1 || !combomp.getSelectedItem().equals(""))
				  				{
				  					
				  					SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				  					CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				  					MatierePremier mp=MapMatierPremiere.get(combomp.getSelectedItem());
				  					
				  					if(subCategorieMp!=null)
				  					{
				  						req=req+" and  categorieMp.subCategorieMp.id='"+subCategorieMp.getId()+"' ";
				  						
				  					}
				  					
				  					if(categorieMp!=null)
				  					{
				  						req=req+" and  categorieMp.id='"+categorieMp.getId()+"' ";
				  						
				  					}
				  					
				  					if(categorieMp!=null)
				  					{
				  						req=req+" and id='"+categorieMp.getId()+"' ";
				  						
				  					}
				  					
				  					
				  					listeMatierePremiere=matierePremiereDAO.listeMatierePremierByReq(req);
				  					afficher_tableMP(listeMatierePremiere);
				  				}else
				  				{
				  					listeMatierePremiere=matierePremiereDAO.listeMatierePremierByReq(req);
				  					afficher_tableMP(listeMatierePremiere);
				  				}
				  				
				  				
				  				
				  			}
				  		});
				  		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnAfficher.setBounds(1143, 35, 97, 24);
				  		layeredPane.add(btnAfficher);
				  		
				  		JLabel label = new JLabel("Code :");
				  		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label.setBounds(10, 70, 51, 26);
				  		layeredPane.add(label);
				  		
				  		txtcode = new JTextField();
				  		txtcode.addKeyListener(new KeyAdapter() {
				  			@Override
				  			public void keyPressed(KeyEvent e) {
				  				

						  		if (e.getKeyCode() == e.VK_ENTER)
						  		{
						  			
						  			if(!txtcode.getText().equals(""))
						  			{
						  				
						  				MatierePremier matierePremier=MapCodeMatierPremiere.get(txtcode.getText().toUpperCase());
						  				if(matierePremier!=null)
						  				{
						  					combomp.setSelectedItem(matierePremier.getNom());
						  				}else
						  				{
						  					JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!");
						  					return;
						  				}
						  				
						  				
						  				
						  			}else
						  			{
						  				JOptionPane.showMessageDialog(null, "veuillez entrer le Code MP SVP !!!");
					  					return;
						  			}
						  			
						  			
						  			
						  		}
				  				
				  				
				  				
				  			}
				  		});
				  		txtcode.setColumns(10);
				  		txtcode.setBounds(56, 72, 108, 26);
				  		layeredPane.add(txtcode);
				  		
				  		JLabel label_1 = new JLabel("Nom :");
				  		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label_1.setBounds(174, 73, 44, 26);
				  		layeredPane.add(label_1);
				  		
				  		 combomp = new JComboBox();
				  		 combomp.addItemListener(new ItemListener() {
				  		 	public void itemStateChanged(ItemEvent arg0) {
				  		 	
				  		 		if(combomp.getSelectedIndex()!=-1)
				  		 		{
				  		 			
				  		 			if(!combomp.getSelectedItem().equals(""))
				  		 			{
				  		 				
				  		 				MatierePremier matierePremier=MapMatierPremiere.get(combomp.getSelectedItem());
				  		 				txtcode.setText(matierePremier.getCode());
				  		 				
				  		 					
				  		 				
				  		 			}else
				  		 			{
				  		 				txtcode.setText(Constantes.CODE_MP);	
				  		 			}
				  		 			
				  		 				
				  		 			
				  		 		}else
				  		 		{
				  		 			txtcode.setText(Constantes.CODE_MP);	
				  		 		}
				  		 		
				  		 		
				  		 		
				  		 		
				  		 		
				  		 	}
				  		 });
				  		combomp.setBounds(226, 74, 439, 26);
				  		layeredPane.add(combomp);
				  		AutoCompleteDecorator.decorate(combomp);
				  		  int i=0;
				  		  while(i<listsubcategoriemp.size())
				  		  {
				  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
				  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
				  			  i++;
				  		  }
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listeMatierePremiereImprimer.size()!=0)
				{
					 
					
					Map parameters = new HashMap();
				
					JasperUtils.imprimerListeMatierePremiere(listeMatierePremiereImprimer,parameters);
					
					
				}
				
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimer.setBounds(277, 515, 158, 23);
		add(btnImprimer);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
		txtcode.setText(Constantes.CODE_MP);	
		
		 dateSituation = new JDateChooser();
		dateSituation.setLocale(Locale.FRANCE);
		dateSituation.setDateFormatString("yyyy");
		dateSituation.setBounds(100, 24, 227, 26);
		layeredPane.add(dateSituation);
		
		JLabel label_2 = new JLabel("Date Situation :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 26, 96, 24);
		layeredPane.add(label_2);
		
		 checkClient = new JCheckBox("Client");
		 checkClient.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(checkClient.isSelected()==true)
		 		{
		 			CheckTous.setSelected(false);
		 			CheckSansClient.setSelected(false);
		 		}
		 	}
		 });
		checkClient.setBounds(687, 74, 72, 23);
		layeredPane.add(checkClient);
		
		 CheckSansClient = new JCheckBox("Sans Client");
		 CheckSansClient.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(CheckSansClient.isSelected()==true)
		 		{
		 			CheckTous.setSelected(false);
		 			checkClient.setSelected(false);
		 		}
		 		
		 	}
		 });
		CheckSansClient.setBounds(773, 73, 97, 23);
		layeredPane.add(CheckSansClient);
		
		 CheckTous = new JCheckBox("Tous");
		CheckTous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CheckTous.isSelected()==true)
				{
					checkClient.setSelected(false);
					CheckSansClient.setSelected(false);
				}
				
			}
		});
		CheckTous.setBounds(884, 73, 97, 23);
		layeredPane.add(CheckTous);
		
		JButton btnExporterExcel = new JButton("Exporter Excel");
		btnExporterExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
					
					if(table.getRowCount()!=0)
					{
						
						
						 
						
						
						
						
						String titre="LIST MATIERE PREMIERE ";
			    		String titrefeuilleexcel="LIST MATIERE PREMIERE";
			    		ExporterTableVersExcel.tabletoexcelListeMMPr(table, titre,titrefeuilleexcel);
						
						
					}else
					{
						
						JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
		    			return;
						
						
					}
					
					
				
				
				
				
				
				
			}
		});
		btnExporterExcel.setIcon(new ImageIcon(ListeMatierePremiere.class.getResource("/img/excel.png")));
		btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExporterExcel.setBounds(458, 515, 158, 23);
		add(btnExporterExcel);
	ChargerMP();
	
	}
	
	
	
	void ChargerMP()
	{
		combomp.removeAllItems();
		combomp.addItem("");
	
	  		listeMatierePremiereCombo=matierePremiereDAO.findAll();
		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
		  			{
		  				
		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
		  				combomp.addItem(matierePremier.getNom());
		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
		  				
		  			}
		  			combomp.setSelectedItem("");
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{
		listeMatierePremiereImprimer.clear();
		intialiserTableau();
		  int i=0;
		  
		 
		  
		  
		  
		  listeMatierePremiere.stream().forEach(e->{
			  String unite="";
			  String client="";
			  if(e.getUnite()!=null)
				{
					 unite=e.getUnite();
				}else
				{
					unite="";
				}
			  
			  if(e.getClient()!=null)
				{
					 client=e.getClient().getNom();
				}else
				{
					client="";
				}
			  
			  MatierePremierImprimer matierePremierImprimer=new MatierePremierImprimer();
			  matierePremierImprimer.setCategorieMp(e.getCategorieMp());
			  matierePremierImprimer.setCode(e.getCode());
			  matierePremierImprimer.setNom(e.getNom());
			  matierePremierImprimer.setUnite(unite);
			  matierePremierImprimer.setClient(client);
			  
			  
			  if(DateUtils.getAnnee(dateSituation.getDate())==2023)
			  {
				  if(e.getPrix()!=null)
					{
					  matierePremierImprimer.setPrix(e.getPrix());
						Object []ligne={e.getId(),e.getCode(),e.getNom(),e.getCategorieMp().getNom(),e.getCategorieMp().getSubCategorieMp().getNom(),e.getPrix(),unite,client};

						modeleMP.addRow(ligne);
						
					}else
					{
						 matierePremierImprimer.setPrix(BigDecimal.ZERO);
						Object []ligne={e.getId(),e.getCode(),e.getNom(),e.getCategorieMp().getNom(),e.getCategorieMp().getSubCategorieMp().getNom(),"",unite,client};

						modeleMP.addRow(ligne);
						
					}
				  
			  }else
			  {
				  
				  if(e.getPrixByYear(DateUtils.getAnnee(dateSituation.getDate()))!=null)
					{
					  
					  matierePremierImprimer.setPrix(e.getPrixByYear(DateUtils.getAnnee(dateSituation.getDate())));
						Object []ligne={e.getId(),e.getCode(),e.getNom(),e.getCategorieMp().getNom(),e.getCategorieMp().getSubCategorieMp().getNom(),e.getPrixByYear(DateUtils.getAnnee(dateSituation.getDate())),unite,client};

						modeleMP.addRow(ligne);
						
					}else
					{
						 matierePremierImprimer.setPrix(BigDecimal.ZERO);
						Object []ligne={e.getId(),e.getCode(),e.getNom(),e.getCategorieMp().getNom(),e.getCategorieMp().getSubCategorieMp().getNom(),"",unite,client};

						modeleMP.addRow(ligne);
						
					}
				  
			  }
			  
			  listeMatierePremiereImprimer.add(matierePremierImprimer)  ;
			  
		  });
		  
	

			table.setModel(modeleMP); 

		

	        
		 
	}
	
	void intialiserTableau(){
		modeleMP=	new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code","Nom MP", "Catégorie", "Sous Catégorie", "prix","Unite De Base","Client"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			table.setModel(modeleMP);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
		    table.getColumnModel().getColumn(1).setPreferredWidth(60);
		    table.getColumnModel().getColumn(2).setPreferredWidth(260);
		    table.getColumnModel().getColumn(3).setPreferredWidth(60);
		    table.getColumnModel().getColumn(4).setPreferredWidth(60);
		    table.getColumnModel().getColumn(5).setPreferredWidth(60);
		    table.getColumnModel().getColumn(6).setPreferredWidth(60);
		    table.getColumnModel().getColumn(7).setPreferredWidth(60);

		    table.getTableHeader().setReorderingAllowed(false);
	}
}



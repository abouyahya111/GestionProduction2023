package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;



import util.Constantes;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.DetailPromotionDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailPromotionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPromotion;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.SubCategorieMp;

import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjouterEstimationOffre extends JFrame implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEstimationPromo;
	private JXTable  tableEmploye=new JXTable();
	private JXTable tableestimationpromo;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	
	private JComboBox<String> comboMachine;
	private  JComboBox<String> comboLigneMachine;
	private Map<String, CategorieMp> mapCategorieMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map<String, MatierePremier> mapNomMP = new HashMap<>();
	private Production production = new Production();
	private List<Employe> listEmploye =new ArrayList<Employe>();
	private List<DetailPromotion> listDetailPromotion=new ArrayList<DetailPromotion>();
	private List<DetailEstimationPromo> listDetailEstimationPromo=new ArrayList<DetailEstimationPromo>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<>();
	private List<FournisseurMP> listFournisseur=new ArrayList<>();
	
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapNote = new HashMap<>();
	private Map< Integer, String> mapMotifEmploye = new HashMap<>();
	private Map< Integer, String> mapHeureSupp25 = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50 = new HashMap<>();
	private Map< String, DetailResponsableProd> mapDetailResponProd = new HashMap<>();
	
	private Map< String, Employe> mapEmployeTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeAideTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeChefProd = new HashMap<>();
	private Map< String, Employe> mapEmployeChefEquipe = new HashMap<>();
	private Map< String, Employe> mapListEmployeResponsable = new HashMap<>();
	private Map< String, Employe> mapAutreEmploye = new HashMap<>();
	private PromotionDAO promotionDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private DetailPromotionDAO detailPromotionDAO;
	private DetailEstimationPromoDAO detailEstimationPromoDAO;
	private MatierePremiereDAO matierePremiereDAO;
	 JComboBox combocategorie = new JComboBox();
	 private JLabel lblMp;
	 private JComboBox combomp;
	 private JTextField txtcodemp;
	 private JTextField txtquantite;
	 JComboBox comboFournisseur = new JComboBox();
	 SubCategorieMPDAO SubCategorieMPDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	 @SuppressWarnings("serial")
	public AjouterEstimationOffre(final Promotion Promotion, final Articles article , final String quantite, final String Scotch, final String Depot, final String Magasin,final String Periode,final  String envrac,final String NumOF,final Date dateDebut,final Date  dateFin,final String Machine,final String DepotProd,final String LigneMachine,final String MagasinProd,final  String DepotPF,final String MagasinPF , final boolean mixte ) {
		
		 setBackground(new Color(248, 248, 255));
	 setForeground(new Color(248, 248, 255));

		 final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	       setBounds(0, 0, 1366, 671);
        try {
        	javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
        	
        	tableestimationpromo=new JXTable();
        	tableestimationpromo.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent arg0) {
        			
        			
        			if(tableestimationpromo.getSelectedRow()!=-1)
        			{
        				MatierePremier matierePremier=matierePremiereDAO.findByCode(tableestimationpromo.getValueAt(tableestimationpromo.getSelectedRow(), 0).toString());
        				
        				combocategorie.setSelectedItem(matierePremier.getCategorieMp().getNom());
        				txtcodemp.setText(matierePremier.getCode());
        				
        				if(!tableestimationpromo.getValueAt(tableestimationpromo.getSelectedRow(), 2).toString().equals(""))
        				{
        					comboFournisseur.setEnabled(false);
        					comboFournisseur.setSelectedItem(tableestimationpromo.getValueAt(tableestimationpromo.getSelectedRow(), 2).toString());
        				}
        				
        				combomp.setSelectedItem(matierePremier.getNom().toString());
        				txtquantite.setText(tableestimationpromo.getValueAt(tableestimationpromo.getSelectedRow(), 3).toString());
        				
        				
        			}
        			
        				
        			
        		}
        	});
        	 DefaultCellEditor ce = (DefaultCellEditor) tableestimationpromo.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	mapEmployeChefProd = new HashMap<>();
        	listEmploye =new ArrayList<Employe>();
        	mapListEmployeResponsable = new HashMap<>();
        	
        	listDetailPromotion=new ArrayList<DetailPromotion>();
        	promotionDAO=new PromotionDAOImpl();
        	detailEstimationPromoDAO=new DetailEstimationPromoDAOImpl();
        	detailPromotionDAO=new DetailPromotionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	SubCategorieMPDAO=new SubCategorieMPAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupp1= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
        initialiserTableauEmploye();
				  		    getContentPane().setLayout(null);
		
				  		 
				  	
				  		    
				  		    btnValiderDelai = new JButton("Valider ");
				  		    btnValiderDelai.setBounds(434, 356, 112, 24);
				  		    getContentPane().add(btnValiderDelai);
				  		    btnValiderDelai.setIcon(imgAjouter);
				  		    btnValiderDelai.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {

				  		     	if(listDetailEstimationPromo.size()!=0)
				  		     	{
				  		     		
					  		     	JOptionPane.showMessageDialog(null, "Promotion Valider");
					  		     	
					  		     	
Main.contentPane=new CreerOrdreFabrication(Promotion, article , quantite,Scotch,Depot,Magasin,Periode,envrac,NumOF, dateDebut, dateFin,Machine,DepotProd,LigneMachine,MagasinProd,DepotPF,MagasinPF , mixte) ;
				  		     		
				  		     		Main.generalScrollPane.setViewportView(Main.contentPane);
				  		     		Main.contentPane.setOpaque(true);		
				  		     		//terminerorderfabrication.productioon
				  		     		//terminerorderfabrication.txtNumOF.setText("ghghghg");
				  		     		dispose();
					  		     	
					  		     	
				  		     	}
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	}
				  		     });
				  		    


				  		    btnValiderDelai.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableestimationpromo);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 1226, 232);
				  		     getContentPane().add(scrollPane);
				  		     
				  		     JLabel label = new JLabel("Categorie  :");
				  		     label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label.setBounds(0, 11, 68, 26);
				  		     getContentPane().add(label);
				  		     
				  		      combocategorie = new JComboBox();
				  		      combocategorie.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent arg0) {
				  		      		
				  		      		if(combocategorie.getSelectedIndex()!=-1)
				  		      		{
				  		      			
				  		      			
				  		      			CategorieMp categorieMp =mapCategorieMP.get(combocategorie.getSelectedItem());
				  		      			if(categorieMp!=null)
				  		      			{
				  		      			
				  		      			combomp.removeAllItems();	
				  		      				
				  		      			listMatierePremiere=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
				  		      			for(int i=0;i<listMatierePremiere.size();i++)
				  		      			{
				  		      				
				  		      				MatierePremier matierePremiere=listMatierePremiere.get(i);
				  		      				
				  		      				combomp.addItem(matierePremiere.getNom());
				  		      				
				  		      				mapNomMP.put(matierePremiere.getNom(), matierePremiere);
				  		      			mapCodeMP.put(matierePremiere.getCode(), matierePremiere);
				  		      			}
				  		      			
				  		      				
				  		      			}
				  		      			
				  		      			
				  		      			
				  		      			
				  		      		}
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     combocategorie.setSelectedIndex(-1);
				  		     combocategorie.setBounds(65, 13, 196, 26);
				  		     getContentPane().add(combocategorie);
				  		     
				  		     lblMp = new JLabel("Matiere Premiere  :");
				  		     lblMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblMp.setBounds(443, 13, 103, 26);
				  		     getContentPane().add(lblMp);
				  		     
				  		     combomp = new JComboBox();
				  		     combomp.addItemListener(new ItemListener() {
				  		     	public void itemStateChanged(ItemEvent e) {
				  		     		
				  		     		if(combomp.getSelectedIndex()!=-1)
				  		     		{
				  		     			
				  		     			
				  		     			MatierePremier matierePremier=mapNomMP.get(combomp.getSelectedItem());
				  		     			if(matierePremier!=null)
				  		     			{
				  		     				
				  		     				txtcodemp.setText(matierePremier.getCode());
				  		     				
				  		     				if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		     				{
				  		     					
				  		     					comboFournisseur.setSelectedItem("");
				  		     					comboFournisseur.setEnabled(true);
				  		     					
				  		     				}else
				  		     				{
				  		     					comboFournisseur.setSelectedItem("");
				  		     					comboFournisseur.setEnabled(false);
				  		     				}
				  		     				
				  		     				
				  		     			}
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     combomp.setSelectedIndex(-1);
				  		     combomp.setBounds(556, 13, 245, 26);
				  		     getContentPane().add(combomp);
				  		     
				  		     JLabel lblCodeMp = new JLabel("Code MP :");
				  		     lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblCodeMp.setBounds(271, 11, 87, 26);
				  		     getContentPane().add(lblCodeMp);
				  		     
				  		     txtcodemp = new JTextField();
				  		     txtcodemp.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		
									if(e.getKeyCode()==e.VK_ENTER)
						      		{
										MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
										if(mp!=null)
										{
											combomp.setSelectedItem(mp.getNom());
										}else
										{
											JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
											return;
										}
										
						      		}
								
									
				  		     	}
				  		     });
				  		     txtcodemp.setColumns(10);
				  		     txtcodemp.setBounds(335, 13, 98, 26);
				  		     getContentPane().add(txtcodemp);
				  		     
				  		     JLabel lblQuantite = new JLabel("Quantite  :");
				  		     lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblQuantite.setBounds(1075, 11, 87, 26);
				  		     getContentPane().add(lblQuantite);
				  		     
				  		     txtquantite = new JTextField();
				  		     txtquantite.setColumns(10);
				  		     txtquantite.setBounds(1138, 11, 98, 26);
				  		     getContentPane().add(txtquantite);
				  		     
				  		     JButton btnAjouter = new JButton("Ajouter");
				  		     btnAjouter.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		try {
				  		     			
				  		     			boolean trouve=false;
				  		     			if(combocategorie.getSelectedIndex()==-1)
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Selectionner La Categorie SVP !!!!");
					  		     			return ;
					  		     		}else if(combomp.getSelectedIndex()==-1)
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP !!!!");
					  		     			return ;
					  		     		}else if(txtquantite.getText().equals(""))
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez entrer la quantite SVP !!!!");
					  		     			return ;	
					  		     		}else
					  		     		{
					  		     			
					  		     		FournisseurMP fournisseurMP=mapFournisseurMP.get(comboFournisseur.getSelectedItem());
					  		     			
					  		     			
					  		     			
					  		     			
					  		     			
					  		     		CategorieMp categorieMp=mapCategorieMP.get(combocategorie.getSelectedItem())	;
					  		     			
					  		     		if(categorieMp!=null)
					  		     		{
					  		     			
					  		     			MatierePremier matierePremier=mapNomMP.get(combomp.getSelectedItem());
					  		     			if(matierePremier==null)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP !!!!");
						  		     			return ;
					  		     			}else
					  		     			{
					  		     				
					  		     				
					  		     				if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					  		     				{
					  		     					
					  		     					if(fournisseurMP==null)
					  		     					{
					  		     						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP !!!!");
								  		     			return ;
					  		     					}
					  		     					
					  		     					
					  		     					
					  		     				}
					  		     				
					  		     				
					  		     				
					  		     				
					  		     				if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
					  		     				{
					  		     					
					  		     					
					  		     					
					  		     					for(int i=0;i<listDetailEstimationPromo.size();i++)
					  		     					{
					  		     						
					  		     				DetailEstimationPromo detailEstimationPromo=	listDetailEstimationPromo.get(i);
					  		     				if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					  		     				{
					  		     					
					  		     					if(detailEstimationPromo.getMatierePremiere().getId()==matierePremier.getId()  )
				  		     						{
					  		     						
					  		     						
					  		     						
					  		     						if(detailEstimationPromo.getFournisseur()!=null  )
					  		     						{
					  		     							
					  		     							if(fournisseurMP!=null)
					  		     							{
					  		     								
					  		     								if(detailEstimationPromo.getFournisseur().getId()==fournisseurMP.getId()  )
					  		     								{
					  		     									
					  		     									trouve=true;
					  		     									
					  		     									
					  		     								}
					  		     								
					  		     								
					  		     							}
					  		     							
					  		     							
					  		     							
					  		     							
					  		     						}
					  		     						
					  		     						
					  		     						
					  		     						
					  		     						
					  		     						
					  		     						
				  		     						}
					  		     					
					  		     					
					  		     					
					  		     					
					  		     					
					  		     					
					  		     				}else
					  		     				{
					  		     					
					  		     					if(detailEstimationPromo.getMatierePremiere().getId()==matierePremier.getId()  )
				  		     						{
				  		     							trouve=true;
				  		     						}
					  		     					
					  		     				}
					  		     				
					  		     						
					  		     						
					  		     					
					  		     					
					  		     					
					  		     					}
					  		     					
					  		     					if(trouve==false)
					  		     					{
					  		     						
					  		     						DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
					  		     						detailEstimationPromo.setMatierePremiere(matierePremier);
					  		     						detailEstimationPromo.setQuantite(new BigDecimal(txtquantite.getText()));
					  		     						detailEstimationPromo.setPromotionest (Promotion);	
					  		     						if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							  		     				{
					  		     							if(fournisseurMP!=null)
						  		     						{
						  		     							detailEstimationPromo.setFournisseur(fournisseurMP);
						  		     						}
					  		     							
							  		     				}
					  		     					
					  		     						
					  		     						
					  		     						
					  		     						detailEstimationPromoDAO.add(detailEstimationPromo);
					  		     						listDetailEstimationPromo.add(detailEstimationPromo);
					  		     						afficher_tableEstimationPromo(listDetailEstimationPromo);
					  		     						JOptionPane.showMessageDialog(null, "Matiere Premiere Ajouter Avec succee SVP !!!!");
					  		     						Initialiser();
					  		     						
					  		     					}else
					  		     					{
					  		     						JOptionPane.showMessageDialog(null, "Matiere Premiere deja existant SVP !!!!");
								  		     			return ;
					  		     					}
					  		     					
					  		     				
					  		     					
					  		     					
					  		     					
					  		     					
					  		     					
					  		     					
					  		     					
					  		     				}else
					  		     				{
					  		     					
					  		     					JOptionPane.showMessageDialog(null, "la quantite doit etre supperieur ‡ zero SVP !!!!");
							  		     			return ;
					  		     					
					  		     					
					  		     				}
					  		     				
					  		     				
					  		     				
					  		     				
					  		     			}
					  		     			
					  		     			
					  		     			
					  		     			
					  		     			
					  		     		}
					  		     			
					  		     			
					  		     			
					  		     			
					  		     			
					  		     			
					  		     			
					  		     		}
				  		     			
										
									} catch (NumberFormatException ex) {
										JOptionPane.showMessageDialog(null, "La Quantite doit etre en chiffre SVP !!!!");
										
									}
				  		     
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnAjouter.setBounds(214, 65, 112, 24);
				  		     getContentPane().add(btnAjouter);
				  		     
				  		     JButton btnModifier = new JButton("Modifier");
				  		     btnModifier.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		try {
				  		     			
				  		     			
				  		     			if(tableestimationpromo.getSelectedRow()!=-1)
				  		     			{
				  		     				boolean trouve=false;
					  		     			if(combocategorie.getSelectedIndex()==-1)
						  		     		{
						  		     			JOptionPane.showMessageDialog(null, "Veuillez Selectionner La Categorie SVP !!!!");
						  		     			return ;
						  		     		}else if(combomp.getSelectedIndex()==-1)
						  		     		{
						  		     			JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP !!!!");
						  		     			return ;
						  		     		}else if(txtquantite.getText().equals(""))
						  		     		{
						  		     			JOptionPane.showMessageDialog(null, "Veuillez entrer la quantite SVP !!!!");
						  		     			return ;	
						  		     		}else
						  		     		{
						  		     			FournisseurMP fournisseurMP=mapFournisseurMP.get(comboFournisseur.getSelectedItem());
						  		     		CategorieMp categorieMp=mapCategorieMP.get(combocategorie.getSelectedItem())	;
						  		     			
						  		     		if(categorieMp!=null)
						  		     		{
						  		     			
						  		     			MatierePremier matierePremier=mapNomMP.get(combomp.getSelectedItem());
						  		     			if(matierePremier==null)
						  		     			{
						  		     				JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP !!!!");
							  		     			return ;
						  		     			}else
						  		     			{
						  		     				
						  		     				if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						  		     				{
						  		     					
						  		     					if(fournisseurMP==null)
						  		     					{
						  		     						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP !!!!");
									  		     			return ;
						  		     					}
						  		     					
						  		     					
						  		     					
						  		     				}
						  		     				
						  		     				if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
						  		     				{
						  		     					
						  		     					
						  		     					
						  		     					for(int i=0;i<listDetailEstimationPromo.size();i++)
						  		     					{
						  		     						
						  		     						if(tableestimationpromo.getSelectedRow()!=i)
						  		     						{
						  		     							DetailEstimationPromo detailEstimationPromo=	listDetailEstimationPromo.get(i);
						  		     							if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									  		     				{
						  		     								
						  		     								if(detailEstimationPromo.getMatierePremiere().getId()==matierePremier.getId())
								  		     						{
								  		     							
								  		     						
								  		     							
								  		     							
								  		     					
						  		     								if(detailEstimationPromo.getFournisseur()!=null  )
								  		     						{
								  		     							
								  		     							if(fournisseurMP!=null)
								  		     							{
								  		     								
								  		     								if(detailEstimationPromo.getFournisseur().getId()==fournisseurMP.getId()  )
								  		     								{
								  		     									
								  		     									trouve=true;
								  		     									
								  		     									
								  		     								}
								  		     								
								  		     								
								  		     							}
								  		     							
								  		     							
								  		     							
								  		     							
								  		     						}
									  		     				}
						  		     								
									  		     				}else
									  		     				{
									  		     					
									  		     					if(detailEstimationPromo.getMatierePremiere().getId()==matierePremier.getId()  )
								  		     						{
								  		     							trouve=true;
								  		     						}
									  		     					
									  		     				}
						  		     							
						  		     							
						  		     							
						  		     							
									  		     				
							  		     						
						  		     						}
						  		     						
						  		     				
						  		     						
						  		     					}
						  		     					
						  		     					if(trouve==false)
						  		     					{
						  		     						
						  		     						DetailEstimationPromo detailEstimationPromo=listDetailEstimationPromo.get(tableestimationpromo.getSelectedRow());
						  		     						detailEstimationPromo.setMatierePremiere(matierePremier);
						  		     						detailEstimationPromo.setQuantite(new BigDecimal(txtquantite.getText()));
						  		     						detailEstimationPromo.setPromotionest(Promotion);	
						  		     						if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
								  		     				{
						  		     							if(fournisseurMP!=null)
							  		     						{
							  		     							detailEstimationPromo.setFournisseur(fournisseurMP);
							  		     						}
						  		     							
								  		     				}
						  		     						
						  		     						
						  		     						
						  		     						detailEstimationPromoDAO.edit(detailEstimationPromo);
						  		     						listDetailEstimationPromo.set(tableestimationpromo.getSelectedRow(),detailEstimationPromo);
						  		     						afficher_tableEstimationPromo(listDetailEstimationPromo);
						  		     						JOptionPane.showMessageDialog(null, "Matiere Premiere Modifier Avec succee SVP !!!!");
						  		     						Initialiser();
						  		     						
						  		     					}else
						  		     					{
						  		     						JOptionPane.showMessageDialog(null, "Matiere Premiere deja existant SVP !!!!");
									  		     			return ;
						  		     					}
						  		     					
						  		     				
						  		     					
						  		     					
						  		     					
						  		     					
						  		     					
						  		     					
						  		     					
						  		     				}else
						  		     				{
						  		     					
						  		     					JOptionPane.showMessageDialog(null, "la quantite doit etre supperieur ‡ zero SVP !!!!");
								  		     			return ;
						  		     					
						  		     					
						  		     				}
						  		     				
						  		     				
						  		     				
						  		     				
						  		     			}
						  		     			
						  		     			
						  		     			
						  		     			
						  		     			
						  		     		}
						  		     			
						  		     			
						  		     			
						  		     			
						  		     			
						  		     			
						  		     			
						  		     		}
				  		     			}
				  		     			
				  		     
				  		     			
										
									} catch (NumberFormatException ex) {
										JOptionPane.showMessageDialog(null, "La Quantite doit etre en chiffre SVP !!!!");
										
									}
				  		     
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnModifier.setBounds(336, 66, 112, 24);
				  		     getContentPane().add(btnModifier);
				  		     
				  		     JButton btnSupprimer = new JButton("Supprimer");
				  		     btnSupprimer.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     	if(tableestimationpromo.getSelectedRow()!=-1)
				  		     	{
				  		     		
				  		     	DetailEstimationPromo detailEstimationPromo =listDetailEstimationPromo.get(tableestimationpromo.getSelectedRow());
				  		     		
				  		     	detailEstimationPromoDAO.delete(detailEstimationPromo.getId());
				  		     		listDetailEstimationPromo.remove(tableestimationpromo.getSelectedRow());
				  		     		afficher_tableEstimationPromo(listDetailEstimationPromo);
				  		     		
				  		     		
				  		     	}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnSupprimer.setBounds(469, 66, 112, 24);
				  		     getContentPane().add(btnSupprimer);
				  		     
				  		     JButton btnInitialiser = new JButton("Initialiser");
				  		     btnInitialiser.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		Initialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnInitialiser.setBounds(591, 64, 120, 26);
				  		     getContentPane().add(btnInitialiser);
	//	listDetailProdGen=detailProdGenDAO.findByDateProdPeriode(production.getDate(), production.getPeriode());
				  		  
				  		   
				listDetailPromotion=detailPromotionDAO.findByIdPromo(Promotion.getId())  	;	   
				combocategorie.addItem("");
				
				
				JLabel lblFournisseur = new JLabel("Fournisseur  :");
				lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblFournisseur.setBounds(811, 11, 81, 26);
				getContentPane().add(lblFournisseur);
				
				 comboFournisseur = new JComboBox();
				comboFournisseur.setSelectedIndex(-1);
				comboFournisseur.setBounds(888, 11, 177, 26);
				getContentPane().add(comboFournisseur);
				  	for(int j=0;j<listDetailPromotion.size();j++)	   
				  	{
				  		DetailPromotion detailPromotion=listDetailPromotion.get(j);
				  		combocategorie.addItem(detailPromotion.getCategorie().getNom());
				  		mapCategorieMP.put(detailPromotion.getCategorie().getNom(), detailPromotion.getCategorie());
				  		
				  	}
				  	listDetailEstimationPromo=detailEstimationPromoDAO.findByIdPromo(Promotion.getId());
				  	
				  	afficher_tableEstimationPromo(listDetailEstimationPromo);	
				  		   
				  	SubCategorieMp subCategorieMp=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);		  		   
	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);	
	comboFournisseur.addItem("");
	for(int j=0;j<listFournisseur.size();j++)
	{
		FournisseurMP fournisseurMP=listFournisseur.get(j);		
		comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
		mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
		
	}
	
	
	}
	
	

	

	

void afficher_tableEstimationPromo(List<DetailEstimationPromo> listDetailEstimationPromo)
	{
	initialiserTableauEmploye();

String fournisseur="";
		for(int i=0;i<listDetailEstimationPromo.size();i++)
			{	
			fournisseur="";
			DetailEstimationPromo detailEstimationPromo=listDetailEstimationPromo.get(i);
			
			if(detailEstimationPromo.getFournisseur()!=null)
			{
				fournisseur=detailEstimationPromo.getFournisseur().getCodeFournisseur();
			}
			
				Object []ligne={detailEstimationPromo.getMatierePremiere().getCode(),detailEstimationPromo.getMatierePremiere().getNom(),fournisseur ,  detailEstimationPromo.getQuantite() };

				modeleEstimationPromo.addRow(ligne);
				
			}
	}


	


void initialiserTableauEmploye(){
	modeleEstimationPromo =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Nom MP","Fournisseur","Quantite"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   tableestimationpromo.setModel(modeleEstimationPromo); 
		   tableestimationpromo.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableestimationpromo.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableestimationpromo.getColumnModel().getColumn(2).setPreferredWidth(60);
		   tableestimationpromo.getColumnModel().getColumn(3).setPreferredWidth(160);
		  
}

void Initialiser()
{
	combocategorie.setSelectedIndex(-1);
	txtcodemp.setText("");
	combomp.removeAllItems();
	combomp.setSelectedIndex(-1);
	txtquantite.setText("");
	comboFournisseur.setSelectedItem("");
	comboFournisseur.setEnabled(true);
}
}

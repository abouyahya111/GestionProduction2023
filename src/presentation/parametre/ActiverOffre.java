package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DetailPromotionDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailPromotionDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPromotion;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JCheckBox;

import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ActiverOffre extends JLayeredPane implements Constantes{
	
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modelePromotion;
	private DefaultTableModel	 modelePromotionOffre;
	private JXTable  tableoffre = new JXTable();
	

	
private ImageIcon imgModifierr;
private ImageIcon imgSupprimer;
private ImageIcon imgAjouter;
private ImageIcon imgInit;
private ImageIcon imgValider;
private JButton btnChercherOF;
private JButton btnImprimer;
private JButton btnRechercher;
private Utilisateur utilisateur;
private JButton buttonvalider;
private PromotionDAO promotiondao=new PromotionDAOImpl();
private ArticlesDAO articledao=new ArticlesDAOImpl();
private Map<String, Articles> mapArticle = new HashMap<>();
private List<Articles> listArticles =new ArrayList<Articles>();

private List<DetailPromotion> listDetailPromotion =new ArrayList<DetailPromotion>();
private MatierePremiereDAO matierepremierdao=new MatierePremierDAOImpl();
private List<Promotion> listPromotion =new ArrayList<Promotion>();
private JTextField txtcodeoffre;
private JButton buttonsupprimer ;
private DetailPromotionDAO detailepromotiondao;
private JXTable table;
private JButton btnModifier;
private JButton btnSupprimer;
private Promotion promotion;
JComboBox combosubcategorie = new JComboBox();
JComboBox combocategorie = new JComboBox();
private CategorieMpDAO categorieMpDAOo;
private SubCategorieMPDAO subcategorieMpDAOo;
private Map<String, SubCategorieMp> mapSubCategorieMP = new HashMap<>();
private Map<String, CategorieMp> mapCategorieMP = new HashMap<>();
private List<CategorieMp> listCategorieMP =new ArrayList<CategorieMp>();
private List<SubCategorieMp> listSubCategorieMP =new ArrayList<SubCategorieMp>();
JButton btnAjouter = new JButton("Ajouter");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public ActiverOffre(){
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1472, 624);
       
        try{
        	detailepromotiondao=new DetailPromotionDAOImpl();
        	categorieMpDAOo=new CategorieMpDAOImpl();
        	subcategorieMpDAOo=new SubCategorieMPAOImpl();
        	   imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
       tableoffre.setSortable(false);
       tableoffre.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		if(tableoffre.getSelectedRow()!=-1)
       		{
       			buttonsupprimer.setEnabled(true);
       			buttonvalider.setEnabled(true);
       		}				
       	}
       });
 
       tableoffre.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"Code", "Etat"
				  		   	}
    		   ) {
	     	boolean[] columnEditables = new boolean[] {
	     			false,true 
	     	};
	     	Class[] columnTypes = new Class[] {
	     			String.class, Boolean.class
				};
			  	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     });
       tableoffre.setShowVerticalLines(false);
       tableoffre.setSelectionBackground(new Color(51, 204, 255));
       tableoffre.setRowHeightEnabled(true);
       tableoffre.setBackground(new Color(255, 255, 255));
       tableoffre.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableoffre.setColumnControlVisible(true);
       tableoffre.setForeground(Color.BLACK);
       tableoffre.setGridColor(new Color(0, 0, 255));
       tableoffre.setAutoCreateRowSorter(true);
       tableoffre.setBounds(2, 27, 411, 198);
       tableoffre.setRowHeight(20);
       tableoffre.setSortable(false);
       DefaultCellEditor ce = (DefaultCellEditor) tableoffre.getDefaultEditor(Object.class);
	        JTextComponent textField = (JTextComponent) ce.getComponent();
	        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableoffre);
				  		     	scrollPane.setBounds(14, 192, 607, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Offres");
				  		     	titledSeparator.setBounds(14, 167, 607, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(14, 65, 607, 54);
				  		     	add(layeredPane);
		
		 buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valider=false;
				if(listPromotion.size()!=0)
				{
					for(int i=0;i<tableoffre.getRowCount();i++)
					{
						 promotion=listPromotion.get(i);
						promotion.setActif(Boolean.valueOf(tableoffre.getValueAt(i, 1).toString()));
						listPromotion.set(i,promotion);
						promotiondao.edit(promotion);
						valider=true;
						}
					if(valider==true)
					{
						JOptionPane.showMessageDialog(null, "Promotion valider avec succee ");
						InitialisertablePromotion();
						InitialisertablePromotionOffre();
						listDetailPromotion.clear();
						buttonvalider.setEnabled(false);
						listPromotion=promotiondao.findAll();
						afficher_tablePromotion(listPromotion);
						txtcodeoffre.setText("");
						txtcodeoffre.requestFocus();
					}
					
				}
				
				}
			});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(239, 467, 112, 24);
		add(buttonvalider);
		
		
		txtcodeoffre = new JTextField();
		util.Utils.copycoller(txtcodeoffre);
		txtcodeoffre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

		  		if(e.getKeyCode()==e.VK_ENTER)
	      		{
	      			if(!txtcodeoffre.getText().equals(""))
	      			{
	      				Promotion promotion=promotiondao.findByCode(txtcodeoffre.getText());
	      				if(promotion!=null)
	      				{
	      					listPromotion.clear();
	      					listPromotion.add(promotion);
	      					afficher_tablePromotion(listPromotion);
	      				}
	      				
	      			}else{
	      				JOptionPane.showMessageDialog(null, "Code Offre introuvable !!!");
	      			}
		  		
	      		}	
			}
		});
		txtcodeoffre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		txtcodeoffre.setColumns(10);
		txtcodeoffre.setBounds(103, 11, 185, 26);
		layeredPane.add(txtcodeoffre);
		
		JLabel lblCodeOffre = new JLabel("Code Offre :");
		lblCodeOffre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodeOffre.setBounds(25, 11, 82, 26);
		layeredPane.add(lblCodeOffre);
		listPromotion.clear();
		afficher_tablePromotion(listPromotion);
		buttonvalider.setEnabled(false);
	
			
			JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
			GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
			gridBagLayout.rowWeights = new double[]{0.0};
			gridBagLayout.rowHeights = new int[]{0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
			gridBagLayout.columnWidths = new int[]{0, 0, 0};
			titledSeparator_1.setTitle("Liste Matiere Premiere ");
			titledSeparator_1.setBounds(763, 172, 580, 30);
			add(titledSeparator_1);
			
			JLayeredPane layeredPane_1 = new JLayeredPane();
			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			layeredPane_1.setBounds(740, 48, 693, 108);
			add(layeredPane_1);
			
			 btnModifier = new JButton("Modifier");
			btnModifier.setEnabled(false);
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						boolean trouve=false;
						
				if(combocategorie.getSelectedIndex()==-1)
		    		{
					  JOptionPane.showMessageDialog(null, "Veuillez  selectionner la categorie MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		    		  return;
		    		}else
						{
		    			
		    			
							if(listDetailPromotion.size()!=0)
							{
								if(table.getSelectedRow()!=-1)
								{
								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la Catégorie premiere ?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION )
										{
											
											CategorieMp categorieMp=mapCategorieMP.get(combocategorie.getSelectedItem());
											
											if(categorieMp!=null)
								    		{
												
												
												for(int i=0;i<listDetailPromotion.size();i++)
							    				{
							    					
							    				if(table.getSelectedRow() !=i)	
							    				{
							    					
							    					if(listDetailPromotion.get(i).getCategorie().getId()==categorieMp.getId())
							    					{
							    						trouve=true;
							    					}
							    					
							    					
							    					
							    					
							    					
							    				}
							    					
							    					
							    				}
												
												
												
							    				if(trouve==false)
							    				{
							    					DetailPromotion detailpromotion=listDetailPromotion.get(table.getSelectedRow());
							    					detailpromotion.setCategorie(categorieMp);
							    					listDetailPromotion.set(table.getSelectedRow(), detailpromotion);
							    					detailepromotiondao.edit(detailpromotion);
													afficher_tablePromotionOffre(listDetailPromotion);
													JOptionPane.showMessageDialog(null, "Catégorie MP modifier avec succée ");
													 initialiser();
							    				}else
							    				{
							    					JOptionPane.showMessageDialog(null, "Catégorie MP déja existant !!!!!! ");
							    					return;
							    				}
												
												
												
												
												
								    		}
											
								
									 
									 
									 
									 
										}
								}
								
							}else
							{
								JOptionPane.showMessageDialog(null, "liste Matiere premiere est vide !!!!!!");
								
							}
							
							
						}
							
					} catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
			}
			});
			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnModifier.setBounds(115, 59, 107, 24);
			layeredPane_1.add(btnModifier);
			
			JButton initialiser = new JButton("Initialiser");
			initialiser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					initialiser();
							
				}
			});
			
			
			initialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
			initialiser.setBounds(247, 60, 106, 23);
			layeredPane_1.add(initialiser);
			
			 btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setEnabled(false);
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow()!=-1)
					{
						if(listDetailPromotion.size()!=0)
						{
							 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la Catégorie MP ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )
								{
									detailepromotiondao.delete(listDetailPromotion.get(table.getSelectedRow()).getId());
									listDetailPromotion.remove(table.getSelectedRow());
									afficher_tablePromotionOffre(listDetailPromotion);
									JOptionPane.showMessageDialog(null, "Catégorie MP supprimer avec succée ");
									 initialiser();
									
								}
							
							
						}
						}
					
				}
			});
			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnSupprimer.setBounds(383, 59, 107, 24);
			layeredPane_1.add(btnSupprimer);
			
			 btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					boolean trouve=false;
try {
		    			
		    	 if(combocategorie.getSelectedItem().equals(""))
		    		{
		    		 JOptionPane.showMessageDialog(null, "veuillez selectionner la categorie MP SVP !!!");
		    			return;
		    		}
		    		else
		    		{
		    			
		    			CategorieMp  categorieMp=mapCategorieMP.get(combocategorie.getSelectedItem());
		    			if(categorieMp!=null)
		    			{
		    				for(int i=0;i<listDetailPromotion.size();i++)
		    				{
		    					
		    					DetailPromotion detailPromotion =listDetailPromotion.get(i);
		    					if(detailPromotion.getCategorie().getId()==categorieMp.getId())
		    					{
		    						trouve=true;
		    					}
		    					
		    					
		    					
		    					
		    				}
		    				if(trouve==false)
		    				{

		    					DetailPromotion  detailpromotion=new DetailPromotion();
								/*
								 * matierepremiere.setCode(txtcodematiere.getText().toUpperCase());
								 * matierepremiere.setNom(txtlibelle.getText());
								 */
			    				
			    				detailpromotion.setPromotion(promotion);
			    				detailpromotion.setCategorie(categorieMp);
			    				
			    				listDetailPromotion.add(detailpromotion);
			    				detailepromotiondao.add(detailpromotion);
			    				
		    				afficher_tablePromotionOffre(listDetailPromotion);
		    				initialiser();
		    						
		    				}
		    				
		    			
		    				
		    				
		    			}
		    				}
		    		
		    					
					}  catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
		    		
					
					
					
					
					
					
					
				}
			});
			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnAjouter.setBounds(507, 59, 107, 24);
			layeredPane_1.add(btnAjouter);
			
			JLabel label = new JLabel("Sub categorie :");
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(10, 11, 102, 26);
			layeredPane_1.add(label);
			
			 combosubcategorie = new JComboBox();
			 combosubcategorie.addItemListener(new ItemListener() {
			 	public void itemStateChanged(ItemEvent arg0) {
			 		

		      		
		      		combocategorie.removeAllItems();
		      		combocategorie.addItem("");
		      		SubCategorieMp subCategorieMp=mapSubCategorieMP.get(combosubcategorie.getSelectedItem());
		      		if(subCategorieMp !=null)
		      		{
		      			listCategorieMP=categorieMpDAOo.findBySubcategorie(subCategorieMp.getId());
		      			for(int i=0;i<listCategorieMP.size();i++)
		      			{
		      				
		      			CategorieMp  categorieMp =listCategorieMP.get(i);
		      			combocategorie.addItem(categorieMp.getNom());
		      				mapCategorieMP.put(categorieMp.getNom(), categorieMp);
		      				
		      			}
		      			
		      		}
		      		
		      		
		      		
		      		
		      		
		      		
		      		
		      		
		      		
		      	
			 		
			 		
			 		
			 	}
			 });
			combosubcategorie.setSelectedIndex(-1);
			combosubcategorie.setBounds(90, 11, 245, 26);
			layeredPane_1.add(combosubcategorie);
			
			JLabel label_1 = new JLabel("Categorie  :");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_1.setBounds(358, 12, 68, 26);
			layeredPane_1.add(label_1);
			
			 combocategorie = new JComboBox();
			combocategorie.setSelectedIndex(-1);
			combocategorie.setBounds(435, 14, 245, 26);
			layeredPane_1.add(combocategorie);
			
			JButton btnNewButton = new JButton("Consulter Offre");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg ) {
				
					if(listPromotion.size()!=0)
					{
						if(tableoffre.getSelectedRow()!=-1)
						{
							 promotion =listPromotion.get(tableoffre.getSelectedRow());
                            listDetailPromotion=detailepromotiondao.findByIdPromo(promotion.getId());
							
							afficher_tablePromotionOffre(listDetailPromotion);
							
					}
						
						
					}else
					{
						JOptionPane.showMessageDialog(null, "la liste des promotion est vide !!!!!");
					}
	
				}
			});
			btnNewButton.setBounds(631, 233, 112, 23);
			add(btnNewButton);
			
			JScrollPane scrollPane_1 = new JScrollPane((Component) null);
			scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			scrollPane_1.setBounds(761, 202, 582, 264);
			add(scrollPane_1);
			
			 table = new JXTable();
			 table.setSortable(false);
			 table.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent arg0) {
			 		
			 	if(listDetailPromotion.size()!=0)
			 	{
			 		if(table.getSelectedRow()!=-1)
			 		{
			 			combosubcategorie.setSelectedItem(table.getValueAt(table.getSelectedRow(), 0).toString());
		       			combocategorie.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString());
		       			
			 			
			 			btnSupprimer.setEnabled(true);
			 			btnModifier.setEnabled(true);
			 			btnAjouter.setEnabled(false);
			 			
			 			
			 		}
			 		
			 		
			 	}
			 		
			 		
			 		
			 		
			 		
			 		
			 		
			 	}
			 });
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"SubCategorieMP", "Categorie MP"
				}
			));
			table.getColumnModel().getColumn(1).setPreferredWidth(92);
			table.setShowVerticalLines(false);
			table.setSelectionBackground(new Color(51, 204, 255));
			table.setRowHeightEnabled(true);
			table.setRowHeight(20);
			table.setGridColor(Color.BLUE);
			table.setForeground(Color.BLACK);
			table.setColumnControlVisible(true);
			table.setBackground(Color.WHITE);
			scrollPane_1.setViewportView(table);
			
			 buttonsupprimer = new JButton();
			buttonsupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(tableoffre.getSelectedRow()!=-1)
					{
						
						if(table.getRowCount()!=0 && listDetailPromotion.size()!=0 )
						{
							if(listDetailPromotion.get(0).getPromotion().getCode().equals(tableoffre.getValueAt(0, tableoffre.getSelectedRow())))
							{
								
								JOptionPane.showMessageDialog(null, "Impossible de supprimer l'offre veullez valider la categorie MP avant la suppression de son offre !!!!!");
								return;
							}
							
							}
						
						  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer Cet Offre?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
								
									Promotion promotion=listPromotion.get(tableoffre.getSelectedRow());
								promotiondao.delete(promotion.getId());
								
								JOptionPane.showMessageDialog(null, "Offre Supprimer avec succée ");
								
								listPromotion=promotiondao.findAll();
								afficher_tablePromotion(listPromotion);
								List<DetailPromotion> listDetailPromotion=new ArrayList<DetailPromotion>();
								afficher_tablePromotionOffre(listDetailPromotion);
								buttonsupprimer.setEnabled(false);
								
							}
						
						
					}
					
					}
				});
					
					
					
			
			buttonsupprimer.setEnabled(false);
			buttonsupprimer.setBounds(647, 281, 73, 24);
			buttonsupprimer.setIcon(imgSupprimer);
			add(buttonsupprimer);
			
			listSubCategorieMP.clear();
			
			listSubCategorieMP=subcategorieMpDAOo.findAll();
			
			for(int j=0;j<listSubCategorieMP.size();j++)
			{
				
				SubCategorieMp subCategorieMp=listSubCategorieMP.get(j);
				combosubcategorie.addItem(subCategorieMp.getNom());
				mapSubCategorieMP.put(subCategorieMp.getNom(), subCategorieMp);
				
				
			}
			combosubcategorie.setSelectedIndex(-1);
			
			
			listPromotion=promotiondao.findAll();
			afficher_tablePromotion(listPromotion);
		
		
		}
	 void initialiser()
	 {
		 
		    combosubcategorie.setSelectedIndex(-1);
	    	btnSupprimer.setEnabled(false);
 			btnModifier.setEnabled(false);
 			btnAjouter.setEnabled(true);
			
		 
	 }
	void afficher_tablePromotion(List<Promotion> listPromotion)
	{
		modelePromotion =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Etat"
				}
			)    {
		     	boolean[] columnEditables = new boolean[] {
		     			false,true 
		     	};
		     	Class[] columnTypes = new Class[] {
		     			String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		tableoffre.setModel(modelePromotion);
		int i=0;
		 
		while(i<listPromotion.size())
		{	
			Promotion promotion=listPromotion.get(i);
			
			Object []ligne={promotion.getCode(),promotion.isActif()};

			modelePromotion.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tablePromotionOffre(List<DetailPromotion> listDetailPromotion)
	{
		modelePromotionOffre =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"SubCategorieMP", "Categorie MP"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelePromotionOffre);
		
		
		for(int i=0;i<listDetailPromotion.size();i++)
		{	
DetailPromotion detailPromotion=listDetailPromotion.get(i);
			
			Object []ligne={detailPromotion.getCategorie().getSubCategorieMp().getNom(),detailPromotion.getCategorie().getNom()};			

			modelePromotionOffre.addRow(ligne);
			}
			
		
}
	
	
	
	void InitialisertablePromotionOffre()
	{
		modelePromotionOffre =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"SubCategorieMP", "Categorie MP"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelePromotionOffre);
	
}
	
	
	void InitialisertablePromotion()
	{
		modelePromotion =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Etat"
				}
			)    {
		     	boolean[] columnEditables = new boolean[] {
		     			false,true 
		     	};
		     	Class[] columnTypes = new Class[] {
		     			String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		tableoffre.setModel(modelePromotion);
		
}
}




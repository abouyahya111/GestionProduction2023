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
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutOffre extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modelePromotion;

	private JXTable  tablematiere = new JXTable();
	private List<DetailPromotion> listDetailPromotion =new ArrayList<DetailPromotion>();

	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JCheckBox checkactif;
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private PromotionDAO promotiondao=new PromotionDAOImpl();
private CategorieMpDAO categorieMpDAOo;
private SubCategorieMPDAO subcategorieMpDAOo;
private Map<String, SubCategorieMp> mapSubCategorieMP = new HashMap<>();
private Map<String, CategorieMp> mapCategorieMP = new HashMap<>();
private List<SubCategorieMp> listSubCategorieMP =new ArrayList<SubCategorieMp>();
private MatierePremiereDAO matierepremierdao=new MatierePremierDAOImpl();
private List<CategorieMp> listCategorieMP =new ArrayList<CategorieMp>();
	private JTextField textcode;
	private JButton suppbutton;
	private JButton modifbutton;
	   JComboBox combosubcategorie = new JComboBox();
	private Promotion promotion =new Promotion();
	  JComboBox combocategorie = new JComboBox();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutOffre(){
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 624);
      
	
        try{
        	
        	promotion =new Promotion();
        	categorieMpDAOo=new CategorieMpDAOImpl();
        	subcategorieMpDAOo=new SubCategorieMPAOImpl();
        			
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
        tablematiere.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       			
       			combosubcategorie.setSelectedItem(tablematiere.getValueAt(tablematiere.getSelectedRow(), 0).toString());
       			combocategorie.setSelectedItem(tablematiere.getValueAt(tablematiere.getSelectedRow(), 1).toString());
       			
       			btnAjouter.setEnabled(false);
       			modifbutton.setEnabled(true);
       			suppbutton.setEnabled(true);
       		
       		
       		 	}
       });
        
       tablematiere.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"SubCategorieMP", "Categorie MP"
				  		   	}
				  		   ));
				  		
       tablematiere.setShowVerticalLines(false);
       tablematiere.setSelectionBackground(new Color(51, 204, 255));
       tablematiere.setRowHeightEnabled(true);
       tablematiere.setBackground(new Color(255, 255, 255));
       tablematiere.setHighlighters(HighlighterFactory.createSimpleStriping());
       tablematiere.setColumnControlVisible(true);
       tablematiere.setForeground(Color.BLACK);
       tablematiere.setGridColor(new Color(0, 0, 255));
       tablematiere.setAutoCreateRowSorter(true);
       tablematiere.setBounds(2, 27, 411, 198);
       tablematiere.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tablematiere);
				  		     	scrollPane.setBounds(14, 192, 933, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Matiere Premiere ");
				  		     	titledSeparator.setBounds(14, 167, 933, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(4, 48, 943, 108);
				  		     	add(layeredPane);
		
		  JLabel codeoffre = new JLabel("Sub categorie :");
		  codeoffre.setBounds(32, 11, 102, 26);
		  layeredPane.add(codeoffre);
		  codeoffre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Categorie  :");
		  lbllibelle.setBounds(380, 12, 68, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		   
		    
		    btnAjouter = new JButton("Ajouter");
		    btnAjouter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		
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
		    				boolean trouve=false;
		    				
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
			    				afficher_tablePromotion(listDetailPromotion);
			    				intialiser();
		    				}else
		    				{
		    					JOptionPane.showMessageDialog(null, "Categorie MP Deja Existant !!!!!!");
		    					return;
		    				}
		    				
		    				
		    				
		    				
		    				
		    			}
		    				}
		    		
		    					
					}  catch (Exception e) {  JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
		    		
		    				
		    		
		    	}
		    });
		    btnAjouter.setBounds(375, 74, 107, 24);
		    layeredPane.add(btnAjouter);
		    btnAjouter.setIcon(imgAjouter);
		    
		      btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      btnInitialiser = new JButton("Initialiser");
		      btnInitialiser.setBounds(509, 75, 106, 23);
		      layeredPane.add(btnInitialiser);
		      btnInitialiser.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      	intialiser();
		      		
		      	}
		      });
		      btnInitialiser.setIcon(imgInit);
		      btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
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
		      combosubcategorie.setBounds(112, 11, 245, 26);
		      layeredPane.add(combosubcategorie);
		      
		       combocategorie = new JComboBox();
		      combocategorie.setSelectedIndex(-1);
		      combocategorie.setBounds(457, 14, 245, 26);
		      layeredPane.add(combocategorie);
		
		 modifbutton = new JButton();
		modifbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				if(combocategorie.getSelectedIndex()==-1)
	    		{
	    		  JOptionPane.showMessageDialog(null, "Veuillez  selectionner la categorie MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		  return;
	    			   
	    		}
	    		else
	    		{
	    		try {
	    			
	    			
	    			
	    			
	    				
	    		CategorieMp categorieMp=mapCategorieMP.get(combocategorie.getSelectedItem());
	    		
	    		if(categorieMp!=null)
	    		{
	    			
	    			Integer row=tablematiere.getSelectedRow();
	    			boolean trouve=false;
	    			if(row!=-1)
	    			{
	    				for(int i=0;i<listDetailPromotion.size();i++)
	    				{
	    					
	    				if(row !=i)	
	    				{
	    					
	    					if(listDetailPromotion.get(i).getCategorie().getId()==categorieMp.getId())
	    					{
	    						trouve=true;
	    					}
	    					
	    					
	    					
	    					
	    					
	    				}
	    					
	    					
	    				}
	   
									/*
									 * matierepremiere.setCode(txtcodematiere.getText().toUpperCase());
									 * matierepremiere.setNom(txtlibelle.getText());
									 */
	    				
	    				
	    				if(trouve==false)
	    				{
	    					DetailPromotion detailPromotion=listDetailPromotion.get(row);
		    				detailPromotion.setCategorie(categorieMp);	    			
		    				detailPromotion.setPromotion(promotion);
		    				listDetailPromotion.set(row, detailPromotion);
		    				afficher_tablePromotion(listDetailPromotion);
		    				intialiser();
		    				
	    				}else
	    				{
	    					JOptionPane.showMessageDialog(null, "Categorie MP Deja Existant !!!!!!");
	    					return;
	    				}
	    				
	    				
	    				
	    				
	    				
	    				
	    			}else
	    			{
	    				 JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    			}
	    		
	    		
	    		
	    		}else
	    		{
	    			 JOptionPane.showMessageDialog(null, "Veuillez  selectionner la categorie MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    			
	    		
					} catch (NumberFormatException e) {  JOptionPane.showMessageDialog(null, "la Quantite de Matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
	    		
	    		
	    		}
				
			
				
				
			}
		});
		modifbutton.setIcon(imgModifierr);
		modifbutton.setBounds(969, 264, 73, 24);
		add(modifbutton);
		
		 suppbutton = new JButton();
	 	 suppbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablematiere.getSelectedRow() !=-1)
				{
					
				listDetailPromotion.remove(tablematiere.getSelectedRow());
				afficher_tablePromotion(listDetailPromotion);
				intialiser();
					
				}
				else
				{

   				 JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}
					
			}
		});
		suppbutton.setIcon(imgSupprimer);
		suppbutton.setBounds(969, 311, 73, 24);
		add(suppbutton);
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				BigDecimal totale=BigDecimal.ZERO;
				if(textcode.getText().equals(""))
						{
					
					 JOptionPane.showMessageDialog(null, "Veuillez actualiser la page SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					 return;
					
						}else if(listDetailPromotion.size()>0)
							{
								    
						
								    promotion.setCode(textcode.getText());
									promotion.setActif(checkactif.isSelected());
									
									promotion.setDetailPromotion(listDetailPromotion);
									promotiondao.add(promotion);
									JOptionPane.showMessageDialog(null, "promotion valider avec succée !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
									intialiser();
									listDetailPromotion.clear();
									afficher_tablePromotion(listDetailPromotion);
									 checkactif.setSelected(false);
								     combosubcategorie.setSelectedIndex(-1);
								    
								     ChargerNewCodePromo();
								     
								     promotion =new Promotion();
									
						
							}
							else
							{
								 JOptionPane.showMessageDialog(null, "La liste des matieres premiere est vide !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
								
							}}
			});
		
		
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(319, 478, 112, 24);
		add(buttonvalider);
		
		 checkactif = new JCheckBox("  Actif");
		checkactif.setBounds(237, 13, 62, 23);
		add(checkactif);
		
		textcode = new JTextField();
		 util.Utils.copycoller(textcode);
		textcode.setEditable(false);
		textcode.setColumns(10);
		textcode.setBounds(57, 11, 157, 26);
		add(textcode);
		
		JLabel lblCode = new JLabel("Code :");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCode.setBounds(10, 11, 68, 26);
		add(lblCode);
		
		  modifbutton.setEnabled(false);
		  suppbutton.setEnabled(false);
		  listSubCategorieMP.clear();
		
		  
		  
			listSubCategorieMP=subcategorieMpDAOo.findAll();
		
			for(int j=0;j<listSubCategorieMP.size();j++)
			{
				
				SubCategorieMp subCategorieMp=listSubCategorieMP.get(j);
				combosubcategorie.addItem(subCategorieMp.getNom());
				mapSubCategorieMP.put(subCategorieMp.getNom(), subCategorieMp);
				
				
			}
			combosubcategorie.setSelectedIndex(-1);
		  ChargerNewCodePromo();
		
		}
	
	
	void intialiser()
	{
		combosubcategorie.setSelectedIndex(-1);
	     btnAjouter.setEnabled(true);
	    
	     modifbutton.setEnabled(false);
		 suppbutton.setEnabled(false);
		
	}
	
	void ChargerNewCodePromo()
	{
		textcode.setText(Constantes.PROMOTION_OFFRE+promotiondao.maxIdPromotion());
		
	}
	
	void afficher_tablePromotion(List<DetailPromotion> listDetailPromotion)
	{
		modelePromotion =new DefaultTableModel(
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
		tablematiere.setModel(modelePromotion);
		int i=0;
		 
		while(i<listDetailPromotion.size())
		{	
			DetailPromotion detailPromotion=listDetailPromotion.get(i);
			
			Object []ligne={detailPromotion.getCategorie().getSubCategorieMp().getNom(),detailPromotion.getCategorie().getNom()};

			modelePromotion.addRow(ligne);
			i++;
		}
}
}




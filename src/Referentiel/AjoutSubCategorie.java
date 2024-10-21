package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main.AuthentificationView;
import main.ProdLauncher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutSubCategorie extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private  SubCategorieMPDAO subcategorieMPDAO;
	private CategorieMpDAO categorieDAO;
	private MatierePremiereDAO matierePremiereDAO;
	
 	CategorieMp categoriemp=new CategorieMp();
	
 	private Map< String, SubCategorieMp> mapSubCategorie = new HashMap<>();
	private List<CategorieMp> listCategorie =new ArrayList<CategorieMp>();
	private List<SubCategorieMp> listSubCategorie =new ArrayList<SubCategorieMp>();
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodecategorie;
	private JTextField txtnomcategorie;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	JComboBox combosubcategorie = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutSubCategorie() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	subcategorieMPDAO=new SubCategorieMPAOImpl();
        	categorieDAO=new CategorieMpDAOImpl();
        	
        	listSubCategorie.addAll(subcategorieMPDAO.findAll());
        	listCategorie.addAll(categorieDAO.findAll());
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(31, 32, 685, 151);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNomClient = new JLabel("Categorie :");
				  		   lblNomClient.setBounds(7, 25, 114, 26);
				  		   layeredPane.add(lblNomClient);
				  		   lblNomClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code SubCategorie :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 130, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Nom  :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodecategorie = new JTextField();
				  		   		util.Utils.copycoller(txtcodecategorie);
				  		   			txtcodecategorie.setColumns(10);
				  		   			txtcodecategorie.setBounds(152, 62, 191, 26);
				  		   			layeredPane.add(txtcodecategorie);
				  		   			
				  		   			txtnomcategorie = new JTextField();
				  		   		util.Utils.copycoller(txtnomcategorie);
				  		   			txtnomcategorie.setColumns(10);
				  		   			txtnomcategorie.setBounds(152, 99, 191, 26);
				  		   			layeredPane.add(txtnomcategorie);
				  		   			
				  		   			 combosubcategorie = new JComboBox();
				  		   			combosubcategorie.setBounds(152, 28, 191, 23);
				  		   			layeredPane.add(combosubcategorie);
				  		   			
				  		   			int i=0;
				  		   			while(i<listSubCategorie.size())
				  		   			{
				  		   				SubCategorieMp subcategorie=listSubCategorie.get(i);
				  		   			combosubcategorie.addItem(subcategorie.getNom());
				  		   			mapSubCategorie.put(listSubCategorie.get(i).getNom(),subcategorie );
				  		   				
				  		   				i++;
				  		   			
				  		   			}
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(30, 255, 686, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						if(listCategorie.size()>0)
				  		   						{
				  		   							
				  		   							combosubcategorie.setSelectedItem(mapSubCategorie.get(table.getValueAt(table.getSelectedRow(), 2).toString()).getNom());
				  		   							txtcodecategorie.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtnomcategorie.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		   							
					  				 		     	btnModifier.setEnabled(true);
					  				 			    btnAjouter.setEnabled(false);
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Code", "Nom", "SubCategorie"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		afficher_tableCat(listCategorie);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   				if(combosubcategorie.getSelectedIndex()==-1 || combosubcategorie.getSelectedItem().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner subCategorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtcodecategorie.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtnomcategorie.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir Nom Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listCategorie.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la categorie ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listCategorie.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listCategorie.get(i).getCode().equals(txtcodecategorie.getText()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Categorie existe deja dans la liste des catégories SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												CategorieMp categoriemp=listCategorie.get(table.getSelectedRow());
				  											categoriemp.setCode(txtcodecategorie.getText());
				  											categoriemp.setNom(txtnomcategorie.getText());
				  											categoriemp.setSubCategorieMp(mapSubCategorie.get(combosubcategorie.getSelectedItem()));
				  											categorieDAO.edit(categoriemp);
				  											listCategorie.clear();
				  											 listCategorie.addAll(categorieDAO.findAll());
						  		  							 afficher_tableCat(listCategorie);
						  		  							JOptionPane.showMessageDialog(null, "Catégorie modifier avec succée ");
						  		  							initialiser();
				  											
			  											}
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			}});
				  		   			btnModifier.setBounds(91, 194, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(223, 195, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(377, 194, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					boolean trouve=false;
				  		   					if(combosubcategorie.getSelectedIndex()==-1 || combosubcategorie.getSelectedItem().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner subCategorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtcodecategorie.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtnomcategorie.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir Nom Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listCategorie.size())
				  		   						{
				  		   							if(listCategorie.get(i).getCode().equals(txtcodecategorie.getText()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, "Categorie existe deja dans la liste des catégories SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   							CategorieMp categorie=new CategorieMp();
				  		   						categorie.setCode(txtcodecategorie.getText());
				  		   						categorie.setNom(txtnomcategorie.getText());
				  		   					categorie.setSubCategorieMp(mapSubCategorie.get(combosubcategorie.getSelectedItem()));
				  		   					categorieDAO.add(categorie);
				  		   				listCategorie.clear();
				  		   				    listCategorie.addAll(categorieDAO.findAll());
				  		   				    afficher_tableCat(listCategorie);
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Categorie ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
				  		   				initialiser();
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
	}
	
	void initialiser()
	{
		combosubcategorie.setSelectedIndex(-1);
		txtcodecategorie.setText("");
		txtnomcategorie.setText("");
		
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void afficher_tableCat(List<CategorieMp> listcaCategorieMp)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code", "Nom", "SubCategorie"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listcaCategorieMp.size())
			{	
				
				
				Object []ligne={listcaCategorieMp.get(i).getCode() ,listcaCategorieMp.get(i).getNom(),listcaCategorieMp.get(i).getSubCategorieMp().getNom()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

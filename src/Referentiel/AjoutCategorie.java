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
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
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


public class AjoutCategorie extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  SubCategorieMPDAO subcategorieMPDAO;
	
	
	JComboBox combounite = new JComboBox();
	
 	
	private List<SubCategorieMp> listSubCategorieMP =new ArrayList<SubCategorieMp>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodeCategorie;
	private JTextField txtlibelle;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutCategorie() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	subcategorieMPDAO=new SubCategorieMPAOImpl();
        	
        	
        	listSubCategorieMP.addAll(subcategorieMPDAO.findAll());
        	
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(31, 32, 685, 151);
				  		   add(layeredPane);
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code Categorie :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(10, 22, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(10, 59, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodeCategorie = new JTextField();
				  		   		util.Utils.copycoller(txtcodeCategorie);
				  		   			txtcodeCategorie.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   				if (e.getKeyCode() == e.VK_ENTER)
								  		{
				  		   					if(!txtcodeCategorie.getText().equals(""))
				  		   					{
				  		   						
				  		   						SubCategorieMp subCategorieMp=subcategorieMPDAO.findByCode(txtcodeCategorie.getText());
				  		   						if(subCategorieMp!=null)
				  		   						{
				  		   							for(int i=0;i<listSubCategorieMP.size();i++)
				  		   							{
				  		   								if(listSubCategorieMP.get(i).getCode().equals(subCategorieMp.getCode()))
				  		   								{
				  		   									table.setRowSelectionInterval(i, i);
				  		   									
				  		   								btnSupprimer.setEnabled(true);
						  				 		     	btnModifier.setEnabled(true);
						  				 			    btnAjouter.setEnabled(false);
				  		   									
				  		   								}
				  		   								
				  		   							}
				  		   					txtlibelle.setText(subCategorieMp.getNom());
				  		   					if(subCategorieMp.getUnite()!=null)
				  		   					{
				  		   						combounite.setSelectedItem(subCategorieMp.getUnite().toString());
				  		   					}
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   					}
				  		   					
								  		}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtcodeCategorie);
				  		   			txtcodeCategorie.setColumns(10);
				  		   			txtcodeCategorie.setBounds(155, 22, 191, 26);
				  		   			layeredPane.add(txtcodeCategorie);
				  		   			
				  		   			txtlibelle = new JTextField();
				  		   		util.Utils.copycoller(txtlibelle);
				  		   			txtlibelle.setColumns(10);
				  		   			txtlibelle.setBounds(155, 59, 191, 26);
				  		   			layeredPane.add(txtlibelle);
				  		   			
				  		   			JLabel lblUnite = new JLabel("Unite :");
				  		   			lblUnite.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblUnite.setBounds(10, 100, 114, 26);
				  		   			layeredPane.add(lblUnite);
				  		   			
				  		   			  combounite = new JComboBox();
				  		   			combounite.setBounds(155, 103, 191, 23);
				  		   			layeredPane.add(combounite);
				  		   			
				  		   		
				  		   			
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
				  		   						if(listSubCategorieMP.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtcodeCategorie.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		   							combounite.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
				  		   							btnSupprimer.setEnabled(true);
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
				  		   					"Code", "Libelle"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		afficher_tableTypeVente(listSubCategorieMP);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodeCategorie.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Motif de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le Motif de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listSubCategorieMP.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la categorie Selectionne ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listSubCategorieMP.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listSubCategorieMP.get(i).getCode().equals(txtcodeCategorie.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Code categorie deja dans la liste des Categorie SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												SubCategorieMp subCategorieMp=listSubCategorieMP.get(table.getSelectedRow());
			  												subCategorieMp.setCode (txtcodeCategorie.getText().toUpperCase());
			  												subCategorieMp.setNom(txtlibelle.getText().toUpperCase());
			  												if(combounite.getSelectedItem()!=null)
			  					  		   					{
			  					  		   					if(!combounite.getSelectedItem().toString().equals(""))
			  					  		   					{
			  					  		   					subCategorieMp.setUnite(combounite.getSelectedItem().toString());
			  					  		   						
			  					  		   					}else
			  					  		   					{
			  					  		   					subCategorieMp.setUnite("");
			  					  		   					}
			  					  		   						
			  					  		   					}else
			  					  		   					{
			  					  		   					subCategorieMp.setUnite("");
			  					  		   					}
				  											subcategorieMPDAO.edit(subCategorieMp);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Categorie modifier avec succée ");
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
				  		   			button_1.setBounds(442, 195, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(325, 194, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					boolean trouve=false;
				  		   					 if(txtcodeCategorie.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code de Motif de PerteSVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtlibelle.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le Motif de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listSubCategorieMP.size())
				  		   						{
				  		   							if(listSubCategorieMP.get(i).getCode().equals(txtcodeCategorie.getText().toUpperCase()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, " Code Categorie existe deja dans la liste des  Categorie  SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   							SubCategorieMp   subCategorieMp=new SubCategorieMp();
				  		   						subCategorieMp.setCode (txtcodeCategorie.getText().toUpperCase());
				  		   					subCategorieMp.setNom(txtlibelle.getText().toUpperCase());
				  		   					if(combounite.getSelectedItem()!=null)
				  		   					{
				  		   					if(!combounite.getSelectedItem().toString().equals(""))
				  		   					{
				  		   					subCategorieMp.setUnite(combounite.getSelectedItem().toString());
				  		   						
				  		   					}else
				  		   					{
				  		   					subCategorieMp.setUnite("");
				  		   					}
				  		   						
				  		   					}else
				  		   					{
				  		   					subCategorieMp.setUnite("");
				  		   					}
				  		   				subcategorieMPDAO.add(subCategorieMp);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Categorie ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
				  		   				initialiser();
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			 btnSupprimer = new JButton("Supprimer");
				  		   			btnSupprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						SubCategorieMp subCategorieMp =listSubCategorieMP.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la categorie Selectionne ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											subcategorieMPDAO.delete(subCategorieMp.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " Categorie supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 194, 107, 24);
				  		   			add(btnSupprimer);
				  		   		chargerUnite();	  		   			
	}
	
	
	
	void charger()
	{
		
		listSubCategorieMP.clear();
		listSubCategorieMP.addAll(subcategorieMPDAO.findAll());
 			afficher_tableTypeVente(listSubCategorieMP);	
		
	}
	void chargerUnite()
	{
		
	combounite.addItem("");
	combounite.addItem(Constantes.UNITE_KG);
	combounite.addItem(Constantes.UNITE_PIECE);
	combounite.addItem(Constantes.UNITE_METTRE);
	combounite.setSelectedItem("");
	}
	
	
	void initialiser()
	{
		
		txtcodeCategorie.setText("");
		txtlibelle.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
			combounite.setSelectedItem("");
	}
	
	void afficher_tableTypeVente(List<SubCategorieMp> listSubCategorieMp)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code", "Libelle","Unite"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(100);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(1).setPreferredWidth(100);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        String unite="";
		  int i=0;
			while(i<listSubCategorieMp.size())
			{	
				unite="";
				if(listSubCategorieMp.get(i).getUnite()!=null)
				{
					unite=listSubCategorieMp.get(i).getUnite();
				}
				Object []ligne={listSubCategorieMp.get(i).getCode(),listSubCategorieMp.get(i).getNom(),unite};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

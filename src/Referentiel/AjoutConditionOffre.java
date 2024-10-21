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
import dao.daoImplManager.ActionMPDAOImpl;
import dao.daoImplManager.ConditionOffreDAOImpl;
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ConditionOffreDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.ActionMP;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.ConditionOffre;
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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class AjoutConditionOffre extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  ConditionOffreDAO conditionOffreDAO ;
	
	
 	
	
 	
	private List<ConditionOffre> listConditionOffre =new ArrayList<ConditionOffre>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtvaleur;
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
	public AjoutConditionOffre() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	conditionOffreDAO=new ConditionOffreDAOImpl();
        	
        	
        	listConditionOffre.addAll(conditionOffreDAO.findAll());
        	
        

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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Valeur  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtvaleur = new JTextField();
				  		   			txtvaleur.addInputMethodListener(new InputMethodListener() {
				  		   				public void caretPositionChanged(InputMethodEvent arg0) {
				  		   				}
				  		   				public void inputMethodTextChanged(InputMethodEvent arg0) {
				  		   					
				  		   				
				  		   				}
				  		   			});
				  		   			txtvaleur.addPropertyChangeListener(new PropertyChangeListener() {
				  		   				public void propertyChange(PropertyChangeEvent arg0) {
				  		   				 
				  		   				
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtvaleur);
				  		   			txtvaleur.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   				if (e.getKeyCode() == e.VK_ENTER)
								  		{
				  		   					if(!txtvaleur.getText().equals(""))
				  		   					{
				  		   						
				  		   						ConditionOffre conditionOffre=conditionOffreDAO.findByconditionOffre(txtlibelle.getText().toUpperCase());
				  		   						if(conditionOffre!=null)
				  		   						{
				  		   							for(int i=0;i<listConditionOffre.size();i++)
				  		   							{
				  		   								if(listConditionOffre.get(i).getConditionOffre().equals(conditionOffre.getConditionOffre()))
				  		   								{
				  		   									table.setRowSelectionInterval(i, i);
				  		   									
				  		   								btnSupprimer.setEnabled(true);
						  				 		     	btnModifier.setEnabled(true);
						  				 			    btnAjouter.setEnabled(false);
				  		   									
				  		   								}
				  		   								
				  		   							}
				  		   					txtlibelle.setText(conditionOffre.getConditionOffre());
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   					}
				  		   					
								  		}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   				@Override
				  		   				public void keyTyped(KeyEvent arg0) {
				  		   					 
				  		   						
				  		   					 
				  		   					
				  		   				}
				  		   				@Override
				  		   				public void keyReleased(KeyEvent arg0) {
				  		   					
				  		   				txtlibelle.setText(txtvaleur.getText()+"G");
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtvaleur);
				  		   			txtvaleur.setColumns(10);
				  		   			txtvaleur.setBounds(152, 62, 191, 26);
				  		   			layeredPane.add(txtvaleur);
				  		   			
				  		   			txtlibelle = new JTextField();
				  		   			txtlibelle.setEditable(false);
				  		   		util.Utils.copycoller(txtlibelle);
				  		   			txtlibelle.setColumns(10);
				  		   			txtlibelle.setBounds(152, 99, 191, 26);
				  		   			layeredPane.add(txtlibelle);
				  		   			
				  		   		
				  		   			
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
				  		   						if(listConditionOffre.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtvaleur.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
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
				  		   					"Valeur", "Libelle"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		afficher_tableTypeVente(listConditionOffre);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   					try {
				  		   					
				  		   			 if(txtvaleur.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(new BigDecimal(txtvaleur.getText()).compareTo(BigDecimal.ZERO)<=0)
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir la Valeur Supérieur à 0 SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}
			  		   					
			  		   					
			  		   					else
			  		   					{
			  		   					if(listConditionOffre.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier Condition Offre ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listConditionOffre.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listConditionOffre.get(i).getConditionOffre().equals(txtlibelle.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Condition Offre existe deja dans la liste des Conditions Offre SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												ConditionOffre conditionOffre=listConditionOffre.get(table.getSelectedRow());
			  												conditionOffre.setValeur (new BigDecimal(txtvaleur.getText()) );
			  												conditionOffre.setConditionOffre(txtlibelle.getText().toUpperCase());
				  											
				  											conditionOffreDAO.edit(conditionOffre);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Condition Offre modifier avec succée ");
						  		  							initialiser();
				  											
			  											}
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			 
				  		   			} catch (NumberFormatException ex) {
										JOptionPane.showMessageDialog(null, "Veuillez Entrer La valeur en Chiffre SVP");
										initialiser();
										return;
									}
		  		   					
				  		   			 
				  		   			 
				  		   			}
				  		   				
				  		   			
				  		   			
				  		   			});
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
				  		   					
				  		   					try {
				  		   						
				  		   						
				  		   					
					  		   					 if(txtvaleur.getText().equals(""))
					  		   					{
					  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir la Valeur SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
					  		   					}else if(txtlibelle.getText().equals(""))
					  		   					{
					  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir la Valeur SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
					  		   					}else if(new BigDecimal(txtvaleur.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		   					{
					  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir la Valeur Supérieur à 0 SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
					  		   					}
					  		   					
					  		   					
					  		   					
					  		   					else
					  		   					{
					  		   						int i=0;
					  		   						while(i<listConditionOffre.size())
					  		   						{
					  		   							if(listConditionOffre.get(i).getConditionOffre().toUpperCase().equals(txtlibelle.getText().toUpperCase()))
					  		   							{
					  		   								trouve=true;
					  		   								
					  		   							}
					  		   						i++;
					  		   						}
					  		   						
					  		   						if(trouve==true)
					  		   						{
					  		   						JOptionPane.showMessageDialog(null, " Condition Offre existe deja dans la liste des  Conditions Offres SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
						  							return;
					  		   						}else
					  		   						{
					  		   							
					  		   							ConditionOffre  conditionOffre=new ConditionOffre();
					  		   						conditionOffre.setValeur (new BigDecimal(txtvaleur.getText()));
					  		   					conditionOffre.setConditionOffre (txtlibelle.getText().toUpperCase());
					  		   					
					  		   					conditionOffreDAO.add(conditionOffre);
					  		   				charger();
					  		   					
					  		   				JOptionPane.showMessageDialog(null, "Condition Offre ajouté avec succée  !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
					  		   				initialiser();
					  		   							
					  		   						}
					  		   						
					  		   						
					  		   						
					  		   						
					  		   					}
												
											} catch (NumberFormatException e) {
												JOptionPane.showMessageDialog(null, "Veuillez Entrer La valeur en Chiffre SVP");
												initialiser();
												return;
											}
				  		   					
				  		   					
				  		   	
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			 btnSupprimer = new JButton("Supprimer");
				  		   			btnSupprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						ConditionOffre conditionOffre =listConditionOffre.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la Condition Offre ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											conditionOffreDAO.delete(conditionOffre.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " Condition Offre supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 194, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
	}
	
	
	
	void charger()
	{
		
		listConditionOffre.clear();
		listConditionOffre.addAll(conditionOffreDAO.findAll());
 			afficher_tableTypeVente(listConditionOffre);	
		
	}
	
	void initialiser()
	{
		
		txtvaleur.setText("");
		txtlibelle.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void afficher_tableTypeVente(List<ConditionOffre> listConditionOffre)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Valeur", "Libelle"
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
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listConditionOffre.size())
			{	
				
				
				Object []ligne={listConditionOffre.get(i).getValeur() ,listConditionOffre.get(i).getConditionOffre()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

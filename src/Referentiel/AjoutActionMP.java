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
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.ActionMP;
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


public class AjoutActionMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  ActionMPDAO actionMPDAO ;
	
	
 	
	
 	
	private List<ActionMP> listActionMP =new ArrayList<ActionMP>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodetypevente;
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
	public AjoutActionMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	actionMPDAO=new ActionMPDAOImpl();
        	
        	
        	listActionMP.addAll(actionMPDAO.findAll());
        	
        

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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodetypevente = new JTextField();
				  		   		util.Utils.copycoller(txtcodetypevente);
				  		   			txtcodetypevente.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   				if (e.getKeyCode() == e.VK_ENTER)
								  		{
				  		   					if(!txtcodetypevente.getText().equals(""))
				  		   					{
				  		   						
				  		   						ActionMP actionMP=actionMPDAO.findByCode(txtcodetypevente.getText());
				  		   						if(actionMP!=null)
				  		   						{
				  		   							for(int i=0;i<listActionMP.size();i++)
				  		   							{
				  		   								if(listActionMP.get(i).getCodeAction().equals(actionMP.getCodeAction()))
				  		   								{
				  		   									table.setRowSelectionInterval(i, i);
				  		   									
				  		   								btnSupprimer.setEnabled(true);
						  				 		     	btnModifier.setEnabled(true);
						  				 			    btnAjouter.setEnabled(false);
				  		   									
				  		   								}
				  		   								
				  		   							}
				  		   					txtlibelle.setText(actionMP.getAction());
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   					}
				  		   					
								  		}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtcodetypevente);
				  		   			txtcodetypevente.setColumns(10);
				  		   			txtcodetypevente.setBounds(152, 62, 191, 26);
				  		   			layeredPane.add(txtcodetypevente);
				  		   			
				  		   			txtlibelle = new JTextField();
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
				  		   						if(listActionMP.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtcodetypevente.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
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
				  		   					"Code", "Libelle"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		afficher_tableTypeVente(listActionMP);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodetypevente.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listActionMP.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier l'Action MP ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listActionMP.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listActionMP.get(i).getCodeAction().equals(txtcodetypevente.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Action MP existe deja dans la liste des Actions MP SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												ActionMP actionMP=listActionMP.get(table.getSelectedRow());
			  												actionMP.setCodeAction (txtcodetypevente.getText().toUpperCase());
			  												actionMP.setAction(txtlibelle.getText().toUpperCase());
				  											
				  											actionMPDAO.edit(actionMP);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Action MP modifier avec succée ");
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
				  		   					 if(txtcodetypevente.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtlibelle.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir l'Action MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listActionMP.size())
				  		   						{
				  		   							if(listActionMP.get(i).getCodeAction().equals(txtcodetypevente.getText().toUpperCase()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, " Action MP existe deja dans la liste des  Actions MP SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   							ActionMP  actionMP=new ActionMP();
				  		   						actionMP.setCodeAction (txtcodetypevente.getText().toUpperCase());
				  		   					actionMP.setAction (txtlibelle.getText().toUpperCase());
				  		   					
				  		   					actionMPDAO.add(actionMP);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Action MP ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
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
				  		   						ActionMP actionMP =listActionMP.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'Action MP ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											actionMPDAO.delete(actionMP.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " Action MP supprimer avec succée ");
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
		
		listActionMP.clear();
		listActionMP.addAll(actionMPDAO.findAll());
 			afficher_tableTypeVente(listActionMP);	
		
	}
	
	void initialiser()
	{
		
		txtcodetypevente.setText("");
		txtlibelle.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void afficher_tableTypeVente(List<ActionMP> listActionMP)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code", "Libelle"
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
			while(i<listActionMP.size())
			{	
				
				
				Object []ligne={listActionMP.get(i).getCodeAction() ,listActionMP.get(i).getAction()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

package matierePremiere;

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
import dao.daoImplManager.FormeDAOImpl;
import dao.daoImplManager.FormeParBoxDAOImpl;
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.FormeParBoxDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FormeParBox;
import dao.entity.LigneMachine;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;
import dao.entity.forme;

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


public class AjoutFormeParBox extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  FormeParBoxDAO formeParBoxDAO ;
	
	
 	
	private List<forme> listForme =new ArrayList<forme>();
	private List<SubCategorieMp> listSousCategorie =new ArrayList<SubCategorieMp>();
private FormeDAO formeDAO;
private SubCategorieMPDAO subCategorieMPDAO;
private Map< String, SubCategorieMp> mapSubcategorie = new HashMap<>();
private Map< String, forme> mapForme = new HashMap<>();

	private List<FormeParBox> listFormeParBox =new ArrayList<FormeParBox>();
	
	
	private JLabel lblConfirmerMotDe;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	JComboBox comboSousCategorie = new JComboBox();
	JComboBox comboForme = new JComboBox();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutFormeParBox() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	formeParBoxDAO=new FormeParBoxDAOImpl();
        	formeDAO=new FormeDAOImpl();
        	subCategorieMPDAO=new SubCategorieMPAOImpl();
        	
        	listFormeParBox.addAll(formeParBoxDAO.findAll());
        	listForme=formeDAO.findAll();
        	listSousCategorie=subCategorieMPDAO.findAll();
        

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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Sous Categorie  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Forme :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			 comboSousCategorie = new JComboBox();
				  		   			comboSousCategorie.setBounds(144, 65, 259, 26);
				  		   			layeredPane.add(comboSousCategorie);
				  		   			
				  		   			 comboForme = new JComboBox();
				  		   			comboForme.setBounds(144, 102, 259, 26);
				  		   			layeredPane.add(comboForme);
				  		   			
				  		   		
				  		   			
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
				  		   						if(listFormeParBox.size()>0)
				  		   						{
				  		   							
				  		   						FormeParBox formeParBox=listFormeParBox.get(table.getSelectedRow());
				  		   							comboSousCategorie.setSelectedItem(formeParBox.getSubCategorieMp().getNom());
				  		   							comboForme.setSelectedItem(formeParBox.getForme().getLibelle());
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
				  		   					"Sous categorie", "Forme"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   				 if(comboSousCategorie.getSelectedItem().toString().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(comboForme.getSelectedItem().toString().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
			  		   					if(listFormeParBox.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la forme Par Box ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											
			  											forme forme=mapForme.get(comboForme.getSelectedItem().toString());	
							  		   					SubCategorieMp subCategorieMp=mapSubcategorie.get(comboSousCategorie.getSelectedItem().toString());		
							  		   						
							  		   					if(forme==null)
							  		   					{
							  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
							  							return;
							  		   					}
							  		   					
							  		   				if(subCategorieMp==null)
						  		   					{
							  		   				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
						  							return;
						  		   					}
			  											
			  											
			  											int i=0;
			  						    				
			  							    			while( i<listFormeParBox.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listFormeParBox.get(i).getSubCategorieMp().getId()==subCategorieMp.getId() && listFormeParBox.get(i).getForme().getId()==forme.getId())
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Forme Par Box existe deja dans la liste des Formes par Box SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												FormeParBox formeParBox=listFormeParBox.get(table.getSelectedRow());

formeParBox.setSubCategorieMp(subCategorieMp);
formeParBox.setForme(forme);
				  											
				  											formeParBoxDAO.edit(formeParBox);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Forme Par Box modifier avec succée ");
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
				  		   					 if(comboSousCategorie.getSelectedItem().toString().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(comboForme.getSelectedItem().toString().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						
				  		   					forme forme=mapForme.get(comboForme.getSelectedItem().toString());	
				  		   					SubCategorieMp subCategorieMp=mapSubcategorie.get(comboSousCategorie.getSelectedItem().toString());		
				  		   						
				  		   					if(forme==null)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}
				  		   					
				  		   				if(subCategorieMp==null)
			  		   					{
				  		   				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Categorie SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}
				  		   					
				  		   						int i=0;
				  		   						while(i<listFormeParBox.size())
				  		   						{
				  		   							if(listFormeParBox.get(i).getSubCategorieMp().getId()==subCategorieMp.getId() && listFormeParBox.get(i).getForme().getId()==forme.getId())
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, " Forme Par Box existe deja dans la liste des  Motifs de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   							FormeParBox  formeParBox=new FormeParBox();
				  		   						formeParBox.setSubCategorieMp(subCategorieMp);
				  		   					formeParBox.setForme(forme);
				  		   					
				  		   					formeParBoxDAO.add(formeParBox);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Forme Par Box ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
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
				  		   						FormeParBox formeParBox =listFormeParBox.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la forme Par Box Selectionner ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											formeParBoxDAO.delete(formeParBox.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " forme Par Box  supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 194, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
				  		   			ChargerCombobox();
				  		   			
	}
	
	
	

	
	void initialiser()
	{
		
		comboForme.setSelectedItem("");
		comboSousCategorie.setSelectedItem("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void charger()
	{
		listFormeParBox=formeParBoxDAO.findAll();
		afficher_tableFormeParBox(listFormeParBox);
	}
	void ChargerCombobox()
	{
		comboForme.removeAllItems();
		comboSousCategorie.removeAllItems();
		comboSousCategorie.addItem("");
		for(int i=0;i<listSousCategorie.size();i++)
		{
			
			if(listSousCategorie.get(i).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || listSousCategorie.get(i).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE) )
			{
			comboSousCategorie.addItem(listSousCategorie.get(i).getNom());	
			mapSubcategorie.put(listSousCategorie.get(i).getNom(), listSousCategorie.get(i));
				
			}
			
			
		}
		
		comboForme.setSelectedItem("");
		
		comboForme.addItem("");
		for(int i=0;i<listForme.size();i++)
		{
			
			
			comboForme.addItem(listForme.get(i).getLibelle());	
			mapForme.put(listForme.get(i).getLibelle(), listForme.get(i));
				
			
			
		}
		comboForme.setSelectedItem("");
		
		charger();
		
	}
	
	void afficher_tableFormeParBox(List<FormeParBox> listFormeParBox)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Sous Categorie", "Forme"
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
			while(i<listFormeParBox.size())
			{	
				
				
				Object []ligne={listFormeParBox.get(i).getSubCategorieMp().getNom() ,listFormeParBox.get(i).getForme().getLibelle()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

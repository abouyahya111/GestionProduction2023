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
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.daoManager.MatierePremiereDAO;

import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.MatierePremier;

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


public class AjoutForme extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private DefaultTableModel	 modeleCart;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private FormeDAO formeDAO;
	
	
 	
	
 	
	private List<forme> listForme =new ArrayList<forme>();
	
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtforme;
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
	public AjoutForme() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	formeDAO=new FormeDAOImpl();
        	listForme=formeDAO.findAll();
        

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
				  		   layeredPane.setBounds(28, 88, 528, 75);
				  		   add(layeredPane);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Forme :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(126, 25, 107, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtforme = new JTextField();
				  		   		util.Utils.copycoller(txtforme);
				  		   			txtforme.setColumns(10);
				  		   			txtforme.setBounds(208, 25, 208, 26);
				  		   			layeredPane.add(txtforme);

				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(28, 236, 538, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						if(listForme.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtforme.setText(table.getValueAt(table.getSelectedRow(), 1).toString().toUpperCase());
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
				  		   					"Code Grammage", "Grammage"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(111);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(123);
				  		   			//table.getColumnModel().getColumn(2).setPreferredWidth(115);
				  		   			table.setFillsViewportHeight(true);
				  		   	     	chargerForme();;
				  		   	     	
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtforme.getText().equals("")){
					  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom De Forme SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   				}else
					  		   				{
			  		   					if(listForme.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la Forme ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listForme.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listForme.get(i).getLibelle().trim().toUpperCase().equals(txtforme.getText().toUpperCase().trim()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Forme existe deja dans la liste des Forme SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												forme forme=listForme.get(table.getSelectedRow());
			  												forme.setLibelle(txtforme.getText().toUpperCase());
			  												formeDAO.edit(forme);
						  		  							JOptionPane.showMessageDialog(null, "Forme modifier avec succée ");
						  		  							chargerForme();
						  		  							initialiser();
				  											
			  											}
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			}});
				  		   			btnModifier.setBounds(38, 201, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(389, 202, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(272, 201, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {

				  		   				 if(txtforme.getText().equals("")){
				  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nome de Forme SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  		   					return;
				  		   				}else
				  		   				{
				  		   					
				  		   				forme formeTmp=formeDAO.findByLibelle(txtforme.getText().trim().toUpperCase());
				  		   				if(formeTmp==null)
				  		   				{
				  		   				forme forme=new forme();
				  		   			forme.setLibelle(txtforme.getText().toUpperCase());
				  		   					formeDAO.add(forme);
				  		   				chargerForme();;
				  		   				initialiser();
				  		   						
				  		   				}else {
				  		   					
				  		   					JOptionPane.showMessageDialog(null, "Forme Existant Deja !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
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
				  		   						forme forme=listForme.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la forme selectionne ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											formeDAO.delete(forme.getId());
		  											
		  											JOptionPane.showMessageDialog(null, "Forme supprimer avec succée ");
		  											chargerForme();;
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(155, 201, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
				  		   			JLabel lblGrammageBox = new JLabel("               Ajouter Forme ");
				  		   			lblGrammageBox.setFont(new Font("Times New Roman", Font.BOLD, 35));
				  		   			lblGrammageBox.setBounds(53, 32, 513, 45);
				  		   			add(lblGrammageBox);
				  		
				  		   			
	}
	
	
	
	void chargerForme()
	{
		
		listForme.clear();
		listForme.addAll(formeDAO.findAll());
 		 afficher_tableForme(listForme);	
		
	}
	
	

	
	void initialiser()
	{
		
		txtforme.setText("");
		
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void afficher_tableForme(List<forme> listForme)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"ID", "Libelle"
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
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listForme.size())
			{	
				
				forme forme=listForme.get(i);
				Object []ligne={forme.getId() , forme.getLibelle()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
	
	
	
	
}

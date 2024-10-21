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
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.ServiceDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.ServiceDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Sequenceur;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;
import dao.entity.service;

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


public class AjoutService extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  ServiceDAO serviceDAO;
	private SequenceurDAO sequenceurDAO;
	
	JComboBox combounite = new JComboBox();
	
 	
	private List<service> listService =new ArrayList<service>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodeService;
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
	public AjoutService() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	sequenceurDAO=new SequenceurDAOImpl();
        	serviceDAO=new ServiceDAOImpl();
        	
        	
        	listService.addAll(serviceDAO.findAll());
        	
        

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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code Service:");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(10, 22, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(10, 59, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodeService = new JTextField();
				  		   			txtcodeService.setEditable(false);
				  		   		util.Utils.copycoller(txtcodeService);
				  		   			txtcodeService.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {

				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtcodeService);
				  		   			txtcodeService.setColumns(10);
				  		   			txtcodeService.setBounds(155, 22, 191, 26);
				  		   			layeredPane.add(txtcodeService);
				  		   			
				  		   			txtlibelle = new JTextField();
				  		   		util.Utils.copycoller(txtlibelle);
				  		   			txtlibelle.setColumns(10);
				  		   			txtlibelle.setBounds(155, 59, 191, 26);
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
				  		   						if(listService.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtcodeService.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
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
				  		   		afficher_tableTypeVente(listService);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodeService.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Motif de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le Motif de Perte SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listService.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la Service Selectionne ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listService.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listService.get(i).getLibelle().trim().toUpperCase().equals(txtcodeService.getText().trim().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Service deja dans la liste des Service SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												service service=listService.get(table.getSelectedRow());
			  												service.setLibelle (txtlibelle.getText().toUpperCase());
			  										
				  											serviceDAO.edit(service);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Service modifier avec succée ");
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
				  		   					 if(txtcodeService.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code de Service  !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtlibelle.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le Nom De Service SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listService.size())
				  		   						{
				  		   							if(listService.get(i).getLibelle().trim().toUpperCase().equals(txtlibelle.getText().trim().toUpperCase()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, " Nom De Service existe deja dans la liste des  Service  SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   						service service=new service();
				  		   					service.setCode(txtcodeService.getText().toUpperCase());
				  		   					service.setLibelle(txtlibelle.getText().toUpperCase());
				  		   				serviceDAO.add(service);
				  		   				Utils.incrementerValeurSequenceurService(Constantes.CODE_SERVICE_SEQUENCEUR);
				  		   		
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Service ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
				  		   				initialiser();
				  		   					charger();		
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
				  		   						service service =listService.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la Service Selectionne ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											serviceDAO.delete(service.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " Service supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 194, 107, 24);
				  		   			add(btnSupprimer);

				  		   		 Sequenceur sequenceur=sequenceurDAO.findByCode(Constantes.CODE_SERVICE_SEQUENCEUR); 
				  		   		 if(sequenceur!=null)
				  		   		 {
				  		   			 txtcodeService.setText(Utils.genererCodeService(Constantes.CODE_SERVICE_SEQUENCEUR));
				  		   			 
				  		   		 }else
				  		   		 {
				  		   			 Sequenceur sequenceurTmp=new Sequenceur();
				  		   		sequenceurTmp.setCode(Constantes.CODE_SERVICE_SEQUENCEUR);
				  		   	sequenceurTmp.setLibelle(Constantes.CODE_SERVICE_SEQUENCEUR);
				  		  sequenceurTmp.setValeur(0);
				  		  sequenceurDAO.add(sequenceurTmp);
				  		  
				  		 txtcodeService.setText(Utils.genererCodeService(Constantes.CODE_SERVICE_SEQUENCEUR));
				  		 
				  		   		 }
	
	}
	
	
	
	void charger()
	{
		
		listService.clear();
		listService.addAll(serviceDAO.findAll());
 			afficher_tableTypeVente(listService);	
		
	}

	
	void initialiser()
	{
		txtcodeService.setText(Utils.genererCodeService(Constantes.CODE_SERVICE_SEQUENCEUR));
		txtlibelle.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
			combounite.setSelectedItem("");
	}
	
	void afficher_tableTypeVente(List<service> listService)
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
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(100);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(100);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
		  int i=0;
			while(i<listService.size())
			{	
				
			
				Object []ligne={listService.get(i).getCode(),listService.get(i).getLibelle()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

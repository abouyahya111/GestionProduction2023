package presentation.article;

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
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;

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


public class AjoutGrammageArticle extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private DefaultTableModel	 modeleCart;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	
	
 	
	
 	
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodegrammage;
	private JTextField txtgrammage;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	private JTextField txtcodegrammagecarton;
	private JTextField txtgrammagecarton;
	private JButton btnModifiercarton;
	private JButton btnSupprimerCarton;
	private JButton btnAjouterCarton;
	private JButton button_4;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutGrammageArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	listGrammageBox=grammageBoxDAO.findAll();
        	listGrammageCarton=grammageCartonDAO.findAll();
        

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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code Grammage :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(10, 11, 107, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Grammage  Box :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(230, 11, 107, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodegrammage = new JTextField();
				  		   		util.Utils.copycoller(txtcodegrammage);
				  		   			txtcodegrammage.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
}
				  		   			});
				  		   		util.Utils.copycoller(txtcodegrammage);
				  		   			txtcodegrammage.setColumns(10);
				  		   			txtcodegrammage.setBounds(113, 11, 107, 26);
				  		   			layeredPane.add(txtcodegrammage);
				  		   			
				  		   			txtgrammage = new JTextField();
				  		   		util.Utils.copycoller(txtgrammage);
				  		   			txtgrammage.setColumns(10);
				  		   			txtgrammage.setBounds(333, 11, 133, 26);
				  		   			layeredPane.add(txtgrammage);

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
				  		   						if(listGrammageBox.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtcodegrammage.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtgrammage.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
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
				  		   	     	chargerGrammageBox();
				  		   	     	
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodegrammage.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le code grammage SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtgrammage.getText().equals("")){
					  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   				}else if(new BigDecimal(txtgrammage.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		   				{
					  		   				JOptionPane.showMessageDialog(null, "le Grammage doit etre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   					
					  		   				}else
					  		   				{
			  		   					if(listGrammageBox.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier le grammage d'article ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listGrammageBox.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listGrammageBox.get(i).getCodeGrammage().equals(txtcodegrammage.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Code Grammage d'article existe deja dans la liste des grammages d'article SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												GrammageBox grammagebox=listGrammageBox.get(table.getSelectedRow());
			  												grammagebox.setCodeGrammage(txtcodegrammage.getText());
			  												grammagebox.setGrammageBox(new BigDecimal(txtgrammage.getText()));
			  												grammageBoxDAO.edit(grammagebox);
						  		  							JOptionPane.showMessageDialog(null, "Grammage Article modifier avec succée ");
						  		  							chargerGrammageBox();
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

				  		   				if(txtcodegrammage.getText().equals(""))
				  		   				{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez entrer le code Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
				  		   				}else if(txtgrammage.getText().equals("")){
				  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  		   					return;
				  		   				}else if(new BigDecimal(txtgrammage.getText()).compareTo(BigDecimal.ZERO)<=0)
				  		   				{
				  		   				JOptionPane.showMessageDialog(null, "le Grammage doit etre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  		   					return;
				  		   					
				  		   				}else
				  		   				{
				  		   					
				  		   				GrammageBox grammageboxTmp=grammageBoxDAO.findByCode(txtcodegrammage.getText());
				  		   				if(grammageboxTmp==null)
				  		   				{
				  		   				GrammageBox grammagebox=new GrammageBox();
				  		   				grammagebox.setCodeGrammage(txtcodegrammage.getText());
				  		   			grammagebox.setGrammageBox(new BigDecimal(txtgrammage.getText()));
				  		   					grammageBoxDAO.add(grammagebox);
				  		   				chargerGrammageBox();
				  		   				initialiser();
				  		   						
				  		   				}else {
				  		   					
				  		   					JOptionPane.showMessageDialog(null, "Code Grammage Existant Deja !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
				  		   						GrammageBox grammagebox=listGrammageBox.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer le grammage d'article ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											grammageBoxDAO.delete(grammagebox.getId());
		  											
		  											JOptionPane.showMessageDialog(null, "grammage d' Article supprimer avec succée ");
		  											chargerGrammageBox();
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(155, 201, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
				  		   			JLabel lblGrammageBox = new JLabel("               Grammage Box ");
				  		   			lblGrammageBox.setFont(new Font("Times New Roman", Font.BOLD, 35));
				  		   			lblGrammageBox.setBounds(53, 32, 513, 45);
				  		   			add(lblGrammageBox);
				  		   			
				  		   			JLabel lblGrammageCarton = new JLabel("               Grammage Carton");
				  		   			lblGrammageCarton.setFont(new Font("Times New Roman", Font.BOLD, 35));
				  		   			lblGrammageCarton.setBounds(609, 32, 513, 45);
				  		   			add(lblGrammageCarton);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   			layeredPane_1.setBounds(646, 88, 538, 75);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JLabel label = new JLabel("Code Grammage :");
				  		   			label.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			label.setBounds(10, 11, 107, 26);
				  		   			layeredPane_1.add(label);
				  		   			
				  		   			JLabel lblGrammageCarton_1 = new JLabel("Grammage  Carton:");
				  		   			lblGrammageCarton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblGrammageCarton_1.setBounds(230, 11, 114, 26);
				  		   			layeredPane_1.add(lblGrammageCarton_1);
				  		   			
				  		   			txtcodegrammagecarton = new JTextField();
				  		   			txtcodegrammagecarton.setColumns(10);
				  		   			txtcodegrammagecarton.setBounds(113, 11, 107, 26);
				  		   			layeredPane_1.add(txtcodegrammagecarton);
				  		   			
				  		   			txtgrammagecarton = new JTextField();
				  		   			txtgrammagecarton.setColumns(10);
				  		   			txtgrammagecarton.setBounds(354, 11, 133, 26);
				  		   			layeredPane_1.add(txtgrammagecarton);
				  		   			
				  		   			btnModifiercarton = new JButton("Modifier");
				  		   			btnModifiercarton.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					

				  		   					boolean trouve=false;
				  		   			 if(txtcodegrammagecarton.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir le code grammage SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtgrammagecarton.getText().equals("")){
					  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   				}else if(new BigDecimal(txtgrammagecarton.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		   				{
					  		   				JOptionPane.showMessageDialog(null, "le Grammage doit etre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   					
					  		   				}else
					  		   				{
			  		   					if(listGrammageCarton.size()!=0)
			  							{
			  								if(table_1.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier le grammage d'article ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listGrammageCarton.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table_1.getSelectedRow())
			  							    					{
			  							    						if(listGrammageCarton.get(i).getCodeGrammage().equals(txtcodegrammagecarton.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Code Grammage d'article existe deja dans la liste des grammages d'article SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												GrammageCarton grammagecarton=listGrammageCarton.get(table_1.getSelectedRow());
			  												grammagecarton.setCodeGrammage(txtcodegrammagecarton.getText());
			  												grammagecarton.setGrammageCarton(new BigDecimal(txtgrammagecarton.getText()));
			  												grammageCartonDAO.edit(grammagecarton);
						  		  							JOptionPane.showMessageDialog(null, "Grammage Article modifier avec succée ");
						  		  							chargerGrammageCarton();
						  		  							initialiserCarton();
				  											
			  											}
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			
				  		   					
				  		   				}
				  		   			});
				  		   			btnModifiercarton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifiercarton.setEnabled(false);
				  		   			btnModifiercarton.setBounds(665, 190, 107, 24);
				  		   			add(btnModifiercarton);
				  		   			
				  		   			btnSupprimerCarton = new JButton("Supprimer");
				  		   			btnSupprimerCarton.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					

				  		   					
				  		   					if(table_1.getSelectedRow()!=-1)
				  		   					{
				  		   						GrammageCarton grammageCarton=listGrammageCarton.get(table_1.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer le grammage d'article ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											grammageCartonDAO.delete(grammageCarton.getId());
		  											
		  											JOptionPane.showMessageDialog(null, "grammage d' Article supprimer avec succée ");
		  											chargerGrammageCarton();;
				  		  							initialiserCarton();
		  											
		  										}
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimerCarton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimerCarton.setEnabled(false);
				  		   			btnSupprimerCarton.setBounds(782, 190, 107, 24);
				  		   			add(btnSupprimerCarton);
				  		   			
				  		   			btnAjouterCarton = new JButton("Ajouter");
				  		   			btnAjouterCarton.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   				


					  		   				if(txtcodegrammagecarton.getText().equals(""))
					  		   				{
					  		   					JOptionPane.showMessageDialog(null, "Veuillez entrer le code Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		   					return;
					  		   				}else if(txtgrammagecarton.getText().equals("")){
					  		   				JOptionPane.showMessageDialog(null, "Veuillez entrer le Grammage SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   				}else if(new BigDecimal(txtgrammagecarton.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		   				{
					  		   				JOptionPane.showMessageDialog(null, "le Grammage doit etre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
					  		   					
					  		   				}else
					  		   				{
					  		   					
					  		   				GrammageCarton grammageboxTmp=grammageCartonDAO.findByCode(txtcodegrammagecarton.getText());
					  		   				if(grammageboxTmp==null)
					  		   				{
					  		   				GrammageCarton grammagecaton=new GrammageCarton();
					  		   			grammagecaton.setCodeGrammage(txtcodegrammagecarton.getText());
					  		   		grammagecaton.setGrammageCarton(new BigDecimal(txtgrammagecarton.getText()));
					  		   	grammageCartonDAO.add(grammagecaton);
					  		  chargerGrammageCarton();
					  		  initialiserCarton();
					  		   						
					  		   				}else {
					  		   					
					  		   					JOptionPane.showMessageDialog(null, "Code Grammage Existant Deja !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		   					return;
					  		   				}
					  		   					
					  		   				}
					  		   				
					  		   				
					  		   				
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnAjouterCarton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnAjouterCarton.setBounds(899, 190, 107, 24);
				  		   			add(btnAjouterCarton);
				  		   			
				  		   			button_4 = new JButton("Initialiser");
				  		   			button_4.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   				initialiserCarton();
				  		   				}
				  		   			});
				  		   			button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			button_4.setBounds(1016, 191, 106, 23);
				  		   			add(button_4);
				  		   			
				  		   			scrollPane_1 = new JScrollPane((Component) null);
				  		   			scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane_1.setBounds(646, 236, 538, 365);
				  		   			add(scrollPane_1);
				  		   			
				  		   			table_1 = new JTable();
				  		   			table_1.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					

				  		   					if(table_1.getSelectedRow()!=-1)
				  		   					{
				  		   						if(listGrammageCarton.size()>0)
				  		   						{
				  		   							
				  		   							txtcodegrammagecarton.setText(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
				  		   							txtgrammagecarton.setText(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
				  		   							btnSupprimerCarton.setEnabled(true);
					  				 		     	btnModifiercarton.setEnabled(true);
					  				 			    btnAjouterCarton.setEnabled(false);
				  		   							
				  		   						}
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			table_1.setFillsViewportHeight(true);
				  		   			scrollPane_1.setViewportView(table_1);
				  		   		
				  		   		table_1.setModel(new DefaultTableModel(
			  		   				new Object[][] {
			  		   				},
			  		   				new String[] {
			  		   					"Code Grammage", "Grammage Carton"
			  		   				}
			  		   			));
				  		   	table_1.getColumnModel().getColumn(0).setPreferredWidth(111);
				  		  table_1.getColumnModel().getColumn(1).setPreferredWidth(123);
				  		//table_1.getColumnModel().getColumn(2).setPreferredWidth(115);
				  		table_1.setFillsViewportHeight(true);
				  		chargerGrammageCarton();  		
				  		
				  		   			
	}
	
	
	
	void chargerGrammageBox()
	{
		
		listGrammageBox.clear();
		listGrammageBox.addAll(grammageBoxDAO.findAll());
 		 afficher_tableGrammageBox(listGrammageBox);	
		
	}
	
	
	
	void chargerGrammageCarton()
	{
		
		listGrammageCarton.clear();
		listGrammageCarton.addAll(grammageCartonDAO.findAll());
 		 afficher_tableGrammageCarton(listGrammageCarton);	
		
	}
	
	void initialiser()
	{
		
		txtcodegrammage.setText("");
		txtgrammage.setText("");
		
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	
	void initialiserCarton()
	{
		
		txtcodegrammagecarton.setText("");
		txtgrammagecarton.setText("");
		
		btnSupprimerCarton.setEnabled(false);
			btnModifiercarton.setEnabled(false);
			btnAjouterCarton.setEnabled(true);
	}
	
	void afficher_tableGrammageBox(List<GrammageBox> listGrammageBox)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code Grammage", "Grammage"
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
			while(i<listGrammageBox.size())
			{	
				
				GrammageBox grammagebox=listGrammageBox.get(i);
				Object []ligne={grammagebox.getCodeGrammage() , grammagebox.getGrammageBox()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
	
	void afficher_tableGrammageCarton(List<GrammageCarton> listGrammageCarton)
	{
		
		

		modeleCart =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code Grammage", "Grammage Carton"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table_1.setModel(modeleCart);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listGrammageCarton.size())
			{	
				
				GrammageCarton grammagecarton=listGrammageCarton.get(i);
				Object []ligne={grammagecarton.getCodeGrammage() , grammagecarton.getGrammageCarton()};

				modeleCart.addRow(ligne);
				i++;
			}
	}
	
	
}

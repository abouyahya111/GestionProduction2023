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

import org.apache.commons.collections4.set.MapBackedSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CoutMachineDAOImpl;
import dao.daoImplManager.FormeDAOImpl;
import dao.daoImplManager.FormeParBoxDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CoutMachineDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.FormeParBoxDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CoutMachine;
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
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class AjoutCoutMachine extends JLayeredPane implements Constantes{
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
	private List<CoutMachine> listCoutMachine =new ArrayList<CoutMachine>();
	private List<CategorieMp> listBox =new ArrayList<CategorieMp>();

	
	private JLabel lblConfirmerMotDe;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	JComboBox comboSousCategorie = new JComboBox();
	JComboBox comboForme = new JComboBox();
	private JTextField txtTonnage;
	private JTextField txtNbrPersonne;
	private JTextField txtCoutMachine;
	JDateChooser dateCoutMachine = new JDateChooser();
	private Map< String, FormeParBox> mapFormeParBox = new HashMap<>();
	private Map< String, CategorieMp> mapBox = new HashMap<>();
	CoutMachineDAO coutMachineDAO;
	JComboBox comboBoxMP = new JComboBox();
	MatierePremiereDAO matierePremiereDAO;
	CategorieMpDAO categorieMpDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutCoutMachine() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	formeParBoxDAO=new FormeParBoxDAOImpl();
        	formeDAO=new FormeDAOImpl();
        	subCategorieMPDAO=new SubCategorieMPAOImpl();
        	coutMachineDAO=new CoutMachineDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listFormeParBox.addAll(formeParBoxDAO.findAll());
        	listForme=formeDAO.findAll();
        	listSousCategorie=subCategorieMPDAO.findAll();
        	categorieMpDAO=new CategorieMpDAOImpl();

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
				  		   layeredPane.setBounds(31, 32, 1020, 288);
				  		   add(layeredPane);
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Sous Categorie  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 48, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Forme :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 85, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			 comboSousCategorie = new JComboBox();
				  		   			 comboSousCategorie.addActionListener(new ActionListener() {
				  		   			 	public void actionPerformed(ActionEvent e) {
				  		   			 		

				  		   		   		if(!comboSousCategorie.getSelectedItem().equals(""))
				  		   		   		{
				  		   		   			listBox.clear();
				  		   		   			mapBox.clear();
				  		   		   			listFormeParBox.clear();
				  		   		   			comboForme.removeAllItems();
				  		   		   	comboBoxMP.removeAllItems();
				  		   		   			comboForme.addItem("");
				  		   		   			comboBoxMP.addItem("");
				  		   		   			mapFormeParBox.clear();
				  		   		   			SubCategorieMp subCategorieMp=mapSubcategorie.get(comboSousCategorie.getSelectedItem().toString());
				  		   		   			if(subCategorieMp!=null) {
				  		   		   				listFormeParBox=formeParBoxDAO.findBySubCategorie(subCategorieMp);
				  		   		   				listBox=categorieMpDAO.findBySubcategorie(subCategorieMp.getId());
				  		   		   				for(int i=0;i<listFormeParBox.size();i++)
				  		   		   				{
				  		   		   					FormeParBox formeParBox=listFormeParBox.get(i);
				  		   		   					comboForme.addItem(formeParBox.getForme().getLibelle());
				  		   		   					mapFormeParBox.put(formeParBox.getForme().getLibelle(), formeParBox);
				  		   		   					
				  		   		   				}
				  		   		   				
				  		   		   		for(int i=0;i<listBox.size();i++)
		  		   		   				{
		  		   		   					CategorieMp categorieMp=listBox.get(i);
		  		   		   					comboBoxMP.addItem(categorieMp.getNom());
		  		   		   					mapBox.put(categorieMp.getNom(), categorieMp);
		  		   		   					
		  		   		   				}
				  		   		   				
				  		   		   comboBoxMP.setSelectedItem("");
				  		   		   				
				  		   		   			}else
				  		   		   			{
				  		   		   				listFormeParBox.clear();
				  		   		   				listBox.clear();
				  		   		   				comboBoxMP.removeAllItems();
				  		   		   				mapBox.clear();
				  		   		   				comboBoxMP.addItem("");
				  		   			   			comboForme.removeAllItems();
				  		   			   			comboForme.addItem("");
				  		   			   			mapFormeParBox.clear();
				  		   			   			comboForme.setSelectedItem("");
				  		   			   	comboBoxMP.setSelectedItem("");
				  		   		   			}
				  		   		   			
				  		   		   		}else
				  		   		   		{
				  		   		   			listFormeParBox.clear();
				  		   		   	listBox.clear();
	  		   		   				comboBoxMP.removeAllItems();
	  		   		   				mapBox.clear();
	  		   		   				comboBoxMP.addItem("");
				  		   		   			comboForme.removeAllItems();
				  		   		   			comboForme.addItem("");
				  		   		   			mapFormeParBox.clear();
				  		   		   			comboForme.setSelectedItem("");
				  		   		    	comboBoxMP.setSelectedItem("");
				  		   		   			
				  		   		   		}
				  		   		   	
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			comboSousCategorie.setBounds(144, 48, 259, 26);
				  		   			layeredPane.add(comboSousCategorie);
				  		   			
				  		   			 comboForme = new JComboBox();
				  		   			comboForme.setBounds(144, 85, 259, 26);
				  		   			layeredPane.add(comboForme);
				  		   			
				  		   			JLabel label = new JLabel("Date D\u00E9but");
				  		   			label.setBounds(10, 11, 77, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 dateCoutMachine = new JDateChooser();
				  		   			dateCoutMachine.setLocale(Locale.FRANCE);
				  		   			dateCoutMachine.setDateFormatString("dd/MM/yyyy");
				  		   			dateCoutMachine.setBounds(144, 11, 259, 26);
				  		   			layeredPane.add(dateCoutMachine);
				  		   			
				  		   			JLabel lblTonnage = new JLabel("Tonnage");
				  		   			lblTonnage.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblTonnage.setBounds(7, 163, 82, 26);
				  		   			layeredPane.add(lblTonnage);
				  		   			
				  		   			txtTonnage = new JTextField();
				  		   			txtTonnage.setColumns(10);
				  		   			txtTonnage.setBounds(143, 163, 260, 26);
				  		   			layeredPane.add(txtTonnage);
				  		   			
				  		   			JLabel lblNombrePersonne = new JLabel("Nombre Personne");
				  		   			lblNombrePersonne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNombrePersonne.setBounds(7, 200, 126, 26);
				  		   			layeredPane.add(lblNombrePersonne);
				  		   			
				  		   			txtNbrPersonne = new JTextField();
				  		   			txtNbrPersonne.setColumns(10);
				  		   			txtNbrPersonne.setBounds(143, 200, 260, 26);
				  		   			layeredPane.add(txtNbrPersonne);
				  		   			
				  		   			txtCoutMachine = new JTextField();
				  		   			txtCoutMachine.setColumns(10);
				  		   			txtCoutMachine.setBounds(143, 237, 260, 26);
				  		   			layeredPane.add(txtCoutMachine);
				  		   			
				  		   			JLabel lblCout = new JLabel("Cout");
				  		   			lblCout.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCout.setBounds(7, 237, 126, 26);
				  		   			layeredPane.add(lblCout);
				  		   			
				  		   			JLabel lblBox = new JLabel("Categorie  :");
				  		   			lblBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblBox.setBounds(7, 122, 144, 26);
				  		   			layeredPane.add(lblBox);
				  		   			
				  		   			  comboBoxMP = new JComboBox();
				  		   			comboBoxMP.setBounds(144, 122, 259, 26);
				  		   			layeredPane.add(comboBoxMP);
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(30, 412, 1000, 410);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						if(listCoutMachine.size()>0)
				  		   						{
				  		   							
				  		   						CoutMachine coutMachine=listCoutMachine.get(table.getSelectedRow());
				  		   						dateCoutMachine.setDate(coutMachine.getDate());
				  		   						comboSousCategorie.setSelectedItem(coutMachine.getFormeParBox().getSubCategorieMp().getNom());
				  		   						comboForme.setSelectedItem(coutMachine.getFormeParBox().getForme().getLibelle());
				  		   						comboBoxMP.setSelectedItem(coutMachine.getCategorie().getNom());
				  		   						txtTonnage.setText(coutMachine.getTonnage().toString());
				  		   						txtNbrPersonne.setText(coutMachine.getNombrePersonne().toString());
				  		   						txtCoutMachine.setText(coutMachine.getCout().toString());
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
				  		   					"Date", "Sous Categorie","Forme","Categorie","Tonnage","Nombre Personne","Cout"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {


				  		   				if(dateCoutMachine.getDate()==null)
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez Entrer la Date SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		  		return;
			  		   					}
			  		   					
			  		   					
			  		   				if(comboForme.getSelectedItem()!=null)
				  		     		  {
				  		     			  

				  		     		  		if(!comboForme.getSelectedItem().toString().equals(""))
				  		     		  		{
				  		     		  			
				  		     		  			FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
				  		     		  			if(formeParBox==null)
				  		     		  			{
				  		     		  				
				  		     		  		
				  		     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		  		return;
				  		     		  			}
				  		     		  		}else
				  		     		  		{
				  		     		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		  	return;
				  		     		  		}
				  		     		  }	else
				  		     		  {
				  		     			  
				  		     				JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
return;
				  		     			  
				  		     		  }
			  		   				
			  		   				if(txtTonnage.getText().equals(""))
			  		   				{
			  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Tonnage SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		   			return;
			  		   							  		     			  
			  		   				}
			  		   				
			  		   			if(txtNbrPersonne.getText().equals(""))
		  		   				{
		  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre de Personne SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		   			return;
		  		   							  		     			  
		  		   				}

			  		   		if(txtCoutMachine.getText().equals(""))
	  		   				{
	  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Cout SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
	  		   			return;
	  		   							  		     			  
	  		   				}
			  		   		
			  		   		
			  		   	if(comboBoxMP.getSelectedItem().toString().equals(""))
		  		   		{
		  		   			
		  		   		JOptionPane.showMessageDialog(null, "Veuillez Selectionne le Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
	  		   			return;
		  		   			
		  		   		}		
			  		   					if(listCoutMachine.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier le Cout Machine ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											
			  											CoutMachine coutMachine=listCoutMachine.get(table.getSelectedRow());
			  											
			  											try {
			  							  		   			
			  								  		   		FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
			  								  		   	CategorieMp categorieMp=mapBox.get(comboBoxMP.getSelectedItem().toString());
			  								  		  	if(formeParBox==null)
			  						     		  			{
			  						     		  				
			  						     		  		
			  						     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  						     		  		return;
			  						     		  			}
			  								  		  	
			  								  		  	
			  								  		if(categorieMp==null)
			  				     		  			{
			  				     		  				
			  				     		  		
			  				     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionne le Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  				     		  		return;
			  				     		  			}
			  								  		  	
			  								  		   		coutMachine.setDate(dateCoutMachine.getDate());
			  								  		   	coutMachine.setFormeParBox(formeParBox);	
			  								  		  coutMachine.setTonnage(new BigDecimal(txtTonnage.getText()));
			  								  		coutMachine.setNombrePersonne(new BigDecimal(txtNbrPersonne.getText()));
			  								  		coutMachine.setCout(new BigDecimal(txtCoutMachine.getText()));	
			  								  	coutMachine.setCategorie(categorieMp);
			  								  coutMachine.setModifierPar(utilCreation);
			  								coutMachine.setDateModifier(new Date());
			  								  		   	coutMachineDAO.edit(coutMachine);		
			  								  		   		charger();
			  								  		   		initialiser();
			  								  		   		JOptionPane.showMessageDialog(null, "Cout Machine Modifier Avec succee");
			  								  		   			
			  													
			  												} catch (NumberFormatException ex) {
			  													JOptionPane.showMessageDialog(null, "Veuillez Entrer les Chiffres Pour Tonnage,Nombre de Personne et le Cout  SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  								  		   			return;
			  												}
			  											
			  											
			  											
			  									
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				
				  		   			}});
				  		   			btnModifier.setBounds(223, 331, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(574, 332, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(457, 331, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {

				  		   					if(dateCoutMachine.getDate()==null)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez Entrer la Date SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		  		return;
				  		   					}
				  		   					
				  		   					
				  		   				if(comboForme.getSelectedItem()!=null)
					  		     		  {
					  		     			  

					  		     		  		if(!comboForme.getSelectedItem().toString().equals(""))
					  		     		  		{
					  		     		  			
					  		     		  			FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
					  		     		  			if(formeParBox==null)
					  		     		  			{
					  		     		  				
					  		     		  		
					  		     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		     		  		return;
					  		     		  			}
					  		     		  		}else
					  		     		  		{
					  		     		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		     		  	return;
					  		     		  		}
					  		     		  }	else
					  		     		  {
					  		     			  
					  		     				JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
	return;
					  		     			  
					  		     		  }
				  		   				
				  		   				if(txtTonnage.getText().equals(""))
				  		   				{
				  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Tonnage SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   							  		     			  
				  		   				}
				  		   				
				  		   			if(txtNbrPersonne.getText().equals(""))
			  		   				{
			  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre de Personne SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		   			return;
			  		   							  		     			  
			  		   				}

				  		   		if(txtCoutMachine.getText().equals(""))
		  		   				{
		  		   				JOptionPane.showMessageDialog(null, "Veuillez Entrer le Cout SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		   			return;
		  		   							  		     			  
		  		   				}
				  		   		
				  		   		if(comboBoxMP.getSelectedItem().toString().equals(""))
				  		   		{
				  		   			
				  		   		JOptionPane.showMessageDialog(null, "Veuillez Selectionne le Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		   			return;
				  		   			
				  		   		}
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		try {
				  		   			
				  		   		FormeParBox formeParBox=mapFormeParBox.get(comboForme.getSelectedItem().toString());
				  		   		CategorieMp categorieBox=mapBox.get(comboBoxMP.getSelectedItem().toString());
				  		   		
				  		   		
				  		  	if(formeParBox==null)
		     		  			{
		     		  				
		     		  		
		     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Forme De Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		     		  		return;
		     		  			}
				  		  	
				  			if(categorieBox==null)
	     		  			{
	     		  				
	     		  		
	     		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionne le Box SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
	     		  		return;
	     		  			}
				  		  	
				  		   			CoutMachine coutMachine=new CoutMachine();
				  		   		coutMachine.setDate(dateCoutMachine.getDate());
				  		   	coutMachine.setFormeParBox(formeParBox);	
				  		  coutMachine.setTonnage(new BigDecimal(txtTonnage.getText()));
				  		coutMachine.setNombrePersonne(new BigDecimal(txtNbrPersonne.getText()));
				  		coutMachine.setCout(new BigDecimal(txtCoutMachine.getText()));		
				  		coutMachine.setCategorie(categorieBox); 
				  		coutMachine.setCreerPar(utilCreation);
				  		coutMachine.setDateCreer(new Date());
				  		   	coutMachineDAO.add(coutMachine);		
				  		   		charger();
				  		   		initialiser();
				  		   		JOptionPane.showMessageDialog(null, "Cout Machine Ajouter Avec succee");
				  		   			
									
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Veuillez Entrer les Chiffres Pour Tonnage,Nombre de Personne et le Cout  SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
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
				  		   						CoutMachine coutMachine =listCoutMachine.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer le Cout Machine Selectionner ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											coutMachineDAO.delete(coutMachine.getId());
		  											
		  											charger();
		  											
		  											JOptionPane.showMessageDialog(null, " Cout Machine  supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(340, 331, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
				  		   			ChargerSousCategorieBox();
				  		   			charger();
				  		   			
	}
	
	
	

	
	void initialiser()
	{
		comboBoxMP.removeAllItems();
	   	comboBoxMP.addItem("");
		comboForme.setSelectedItem("");
		comboSousCategorie.setSelectedItem("");
		dateCoutMachine.setDate(null);
		txtCoutMachine.setText("");
		txtNbrPersonne.setText("");
		txtTonnage.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
			
	}
	
	void charger()
	{
		listCoutMachine=coutMachineDAO.findAll();
		afficher_tableCoutMachine(listCoutMachine);
	}
	void ChargerSousCategorieBox()
	{
		comboSousCategorie.removeAllItems();
		comboSousCategorie.addItem("");
		listSousCategorie=subCategorieMPDAO.findAll();
		comboForme.addItem("");
		for(int t=0;t<listSousCategorie.size();t++)
		{
			
			if(listSousCategorie.get(t).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || listSousCategorie.get(t).getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE) )
			{
			comboSousCategorie.addItem(listSousCategorie.get(t).getNom());	
			mapSubcategorie.put(listSousCategorie.get(t).getNom(), listSousCategorie.get(t));
				
			}
			
			
		}
		
		comboSousCategorie.setSelectedItem("");
		comboForme.setSelectedItem("");
		
	}
	
	void afficher_tableCoutMachine(List<CoutMachine> listCoutMachine)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
  		   					"Date", "Sous Categorie","Forme","Categorie","Tonnage","Nombre Personne","Cout"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(100);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(2).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(3).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(4).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(5).setPreferredWidth(100);
	  		 table.getColumnModel().getColumn(6).setPreferredWidth(100);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listCoutMachine.size())
			{	
				
				
				Object []ligne={listCoutMachine.get(i).getDate(),listCoutMachine.get(i).getFormeParBox().getSubCategorieMp().getNom(),listCoutMachine.get(i).getFormeParBox().getForme().getLibelle(),listCoutMachine.get(i).getCategorie().getNom(), listCoutMachine.get(i).getTonnage(),listCoutMachine.get(i).getNombrePersonne(),listCoutMachine.get(i).getCout()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}

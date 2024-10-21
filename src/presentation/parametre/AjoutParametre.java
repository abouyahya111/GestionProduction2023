package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ParametreModifierDAOImpl;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ParametreModifierDAO;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.Parametre;
import dao.entity.ParametreModifier;

import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;


public class AjoutParametre extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	
	private DefaultTableModel modele;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField txtCode;
	private JTextField txtLibelle;
	private int valeur;
	
	private List <Parametre> listeParametres = new ArrayList<Parametre>();
	private Map< Integer, Parametre> mapParametre = new HashMap<>();
	private List <Parametre> listeParametresGroupByLibelle = new ArrayList<Parametre>();
	private Map< String, Parametre> mapParametreGroupByLibelle = new HashMap<>();
	private JLayeredPane layeredPane_1;
	private JTextField  txtCodeModif = new JTextField();
	
	private JTextField txtLibelleModif= new JTextField();
	private JLabel lblCatModif;
	private JLabel lblNomModif;
	private JLabel lblCodeModif;
	private JButton btnValiderModif;
	private JButton initialiserModif;
	
	private JTextField txtValeur;
	private JTextField  txtValeurModif = new JTextField();
	
	private ParametreDAO parametreDAO;
	private EquipeDAO equipeDAO;
	private EmployeDAO employeDAO;
	private Parametre parametre=new Parametre();
	JDateChooser dateParametre = new JDateChooser();
	 JCheckBox checkLibelleExistant = new JCheckBox("Libelle Existant");
	 JComboBox combolibelle = new JComboBox();
	 private JComboBox combolibelleModifie;
	 private JCheckBox checkLibelleExistantModifie;
	 JDateChooser dateParametreModifier = new JDateChooser();
	 ParametreModifierDAO parametreModifierDAO;
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutParametre() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	parametreModifierDAO=new ParametreModifierDAOImpl();
        	parametreDAO= new ParametreDAOImpl();
        	equipeDAO= new EquipeDAOImpl();
        	employeDAO= new EmployeDAOImpl();
        	util.Utils.copycoller(txtValeurModif);
        	util.Utils.copycoller(txtCodeModif);
        	util.Utils.copycoller(txtLibelleModif);
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
	
		table = new JXTable();
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setBackground(new Color(255, 255, 255));
		table.setHighlighters(HighlighterFactory.createSimpleStriping());
		table.setColumnControlVisible(true);
		table.setForeground(Color.BLACK);
		table.setGridColor(new Color(0, 0, 255));
		table.setAutoCreateRowSorter(true);
		
	
	

		table.setBounds(2, 27, 411, 198);
		  table.setRowHeight(20);
		
 
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(0, 235, 782, 227);
		
		this.add(scrollPane);
				  		 
				  		 JXLabel lblListDes = new JXLabel();
				  		 lblListDes.setForeground(new Color(255, 69, 0));
				  		 lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		 lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		 lblListDes.setText("List des Param\u00E8tres");
				  		 lblListDes.setBounds(0, 207, 782, 24);
				  		 add(lblListDes);
				  		layeredPane_1 = new JLayeredPane();
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  
								int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut sélectionner un ligne!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {  
									    
									   layeredPane_1.setVisible(true);
									   row = table.getSelectedRow();
									   parametre=mapParametre.get(row);
									   
									   txtCodeModif.setText(parametre.getCode());
									   txtLibelleModif.setText(parametre.getLibelle());
									   txtValeurModif.setText(parametre.getValeur()+"");
									   if(parametre.getDate()!=null)
									   {
										 dateParametre.setDate(parametre.getDate());  
										 
									   }else
									   {
										   dateParametre.setDate(null);  
									   }
				                 
								   }
							
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(792, 272, 112, 24);
				  		 add(btnModifier);
				  		 
				  		  btnSupprimer = new JButton("D\u00E9sactiver");
				  		  btnSupprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

								
								 try{/*
									   int row=0;
									   if(table.getSelectedRow()==-1)
										     JOptionPane.showMessageDialog(null, "Il faut sélectionner une Matière Première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									   else
									   {
										   
										   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment désactiver cette Matière Première?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION ){
										   
										row = table.getSelectedRow();
									  
									   //stx=con.createStatement();
										int  id=Integer.parseInt(table.getValueAt(row, 0).toString());
									     MatierePremier mp = dao.findById(id);
									     mp.setDeleted(true);
									   	 dao.edit(mp);
									     afficher_table();  
				                        table.setRowSorter(null);
									     modele.removeRow(row);
											}
									   }
						                */}catch (Exception e1){
						                	}
										
							
				  		  	}
				  		  });
				  		btnSupprimer.setIcon(imgSupprimer);
				  		 btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnSupprimer.setBounds(792, 306, 112, 23);
				  		 add(btnSupprimer);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(10, 11, 630, 192);
				  		 add(layeredPane);
				  		 
				  		 txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		 txtCode.setForeground(Color.BLUE);
				  		 txtCode.setBackground(Color.WHITE);
				  		 txtCode.setEditable(false);
				  		 txtCode.setColumns(10);
				  		 txtCode.setBounds(165, 13, 191, 26);
				  		 layeredPane.add(txtCode);
				  		 
				  		 txtLibelle = new JTextField();
				  		 util.Utils.copycoller(txtLibelle);
				  		 txtLibelle.setColumns(10);
				  		 txtLibelle.setBounds(165, 44, 191, 26);
				  		 layeredPane.add(txtLibelle);
				  		 
				  		 JLabel lblValeur = new JLabel("Valeur:");
				  		 lblValeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblValeur.setBounds(54, 73, 102, 26);
				  		 layeredPane.add(lblValeur);
				  		 
				  		 JLabel lblLibelle = new JLabel("Libelle :");
				  		 lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblLibelle.setBounds(54, 42, 130, 26);
				  		 layeredPane.add(lblLibelle);
				  		 
				  		 JLabel lblCode = new JLabel("Code :");
				  		 lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblCode.setBounds(54, 13, 114, 26);
				  		 layeredPane.add(lblCode);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(119, 157, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   
				  		   		if(checkLibelleExistant.isSelected()==true)
				  		   		{
				  		   			if(combolibelle.getSelectedItem()!=null)
				  		   			{
				  		   				
				  		   			if(combolibelle.getSelectedItem().toString().equals(""))
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		return;
					  		   		
				  		   			}
				  		   				
				  		   				
				  		   			}else
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		return;
					  		   		
				  		   			}
				  		   			
				  		   		}else
				  		   		{
				  		   		if(txtLibelle.getText().equals(""))
				  		   		{
				  		   		JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
				  		   		}
				  		   			
				  		   		
				  		   		}
				  		   		
				  		   		if(dateParametre.getDate()==null)
				  		   		{	JOptionPane.showMessageDialog(null, "Il faut saisir la date SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
				  		   			
				  		   		}
				  		   		
				  		   		 if (txtValeur.equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir la Valeur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else {
				  		   			
				  		   			
			 /////////////////////////////////////////////////////////  Tester Si la Valeur entrer est en Chiffre            ////////////////////////////////////////////////////////////////////////////////////
				  		   			try {
				  		   				
				  		   				
				  		   				if(new BigDecimal(txtValeur.getText())!=null)
				  		   				{
				  		   					
				  		   				}
										
									} catch (NumberFormatException e2) {
										JOptionPane.showMessageDialog(null, "Il faut saisir le Chiffre Pour la Valeur SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		   		return;
									}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		   			
				  		   			
				  		   			
				  		   			
				  		   			if(checkLibelleExistant.isSelected()==true)
				  		   			{
				  		   				
				  		   			Parametre parametreTmp=parametreDAO.findUniqueParametreByDateByLibelle(dateParametre.getDate(), combolibelle.getSelectedItem().toString())	;
				  		   			
				  		   			if(parametreTmp!=null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Parameter Avec Date déja existante!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		return;
				  		   			}
				  		   			
				  		   			
				  		   			
				  		   			}else
				  		   			{
				  		   				
				  		   			Parametre parametreTmp=parametreDAO.findUniqueParametreByDateByLibelle(dateParametre.getDate(), txtLibelle.getText())	;
				  		   			
				  		   		if(parametreTmp!=null)
			  		   			{
			  		   			JOptionPane.showMessageDialog(null, "Parameter Avec Date déja existante!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
			  		   			}
				  		   			}
				  		   		
				  		   			
				  		   			
				  		   			
				  		   			
				  		   		
				  		   		Parametre parametre = new Parametre();
				  		   		parametre.setCode(txtCode.getText());
				  		   	if(checkLibelleExistant.isSelected()==true)
			  		   		{
				  		   	parametre.setLibelle(combolibelle.getSelectedItem().toString());
				  		   		
			  		   		}else
			  		   		{
			  		   			
			  		   		parametre.setLibelle(txtLibelle.getText());
			  		   		
			  		   		}
				  		   		
				  		   		parametre.setValeur(new BigDecimal(txtValeur.getText()));
				  		   	parametre.setDate(dateParametre.getDate());
				  		   		
				  		   		parametreDAO.add(parametre);
				  		   		Utils.incrementerValeurSequenceur(Constantes.PARAMETRE_LIBELLE);
				  		   		listeParametres.add(parametre);
				  		   		intialiser();
				  		   		String code =Utils.genererCode(Constantes.PARAMETRE_LIBELLE);
				  		   		txtCode.setText(code);
								afficher_table(listeParametres);
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(269, 158, 102, 23);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   txtValeur = new JTextField();
				  		 util.Utils.copycoller(txtValeur);
				  		   txtValeur.setColumns(10);
				  		   txtValeur.setBounds(165, 73, 191, 26);
				  		   layeredPane.add(txtValeur);
				  		   
				  		   
				  		   layeredPane_1.setBackground(new Color(135, 206, 235));
				  		   layeredPane_1.setVisible(false);
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(135, 206, 250), new Color(135, 206, 250)));
				  		   layeredPane_1.setBounds(654, 11, 606, 192);
				  		   add(layeredPane_1);
				  		   
				  		  
				  		   txtCodeModif.setColumns(10);
				  		   txtCodeModif.setBounds(165, 13, 191, 26);
				  		   layeredPane_1.add(txtCodeModif);
				  		   
				  		   txtLibelleModif.setColumns(10);
				  		   txtLibelleModif.setBounds(165, 44, 191, 26);
				  		   layeredPane_1.add(txtLibelleModif);
				  		   
				  		   lblCatModif = new JLabel("Valeur :");
				  		   lblCatModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCatModif.setBounds(54, 73, 102, 26);
				  		   layeredPane_1.add(lblCatModif);
				  		   
				  		   lblNomModif = new JLabel("Libelle:");
				  		   lblNomModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblNomModif.setBounds(54, 42, 130, 26);
				  		   layeredPane_1.add(lblNomModif);
				  		   
				  		   lblCodeModif = new JLabel("Code:");
				  		   lblCodeModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeModif.setBounds(54, 13, 114, 26);
				  		   layeredPane_1.add(lblCodeModif);
				  		   
				  		   btnValiderModif = new JButton("Valider Modification");
				  		   btnValiderModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	/*
				  		   	parametre.setLibelle(txtLibelleModif.getText());
				  		   	parametre.setValeur(new BigDecimal(txtValeurModif.getText()));
				  		   	parametreDAO.edit(parametre);
				  		  
				  		   	MAJRemiseParametre(parametre);
				  		   	
				  		   	
						   	JOptionPane.showMessageDialog(null, "les paramètre ont été modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						   	layeredPane_1.setVisible(false);
						   	listeParametres = new ArrayList<Parametre>();
						   listeParametres=parametreDAO.findAll();
						   afficher_table(listeParametres);
						     */
				  		   		
				  		   		
				  		   		if(checkLibelleExistantModifie.isSelected()==true)
				  		   		{
				  		   			if(combolibelleModifie.getSelectedItem()!=null)
				  		   			{
				  		   				
				  		   			if(combolibelleModifie.getSelectedItem().toString().equals(""))
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		return;
					  		   		
				  		   			}
				  		   				
				  		   				
				  		   			}else
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		return;
					  		   		
				  		   			}
				  		   			
				  		   		}else
				  		   		{
				  		   		if(txtLibelleModif.getText().equals(""))
				  		   		{
				  		   		JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
				  		   		}
				  		   			
				  		   		
				  		   		}
				  		   		
				  		   		if(dateParametreModifier.getDate()==null)
				  		   		{	JOptionPane.showMessageDialog(null, "Il faut saisir la date SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
				  		   			
				  		   		}
				  		   		
				  		   		 if (txtValeurModif.equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir la Valeur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else {
				  		   			
				  		   		 /////////////////////////////////////////////////////////  Tester Si la Valeur entrer est en Chiffre            ////////////////////////////////////////////////////////////////////////////////////
				  		   			try {
				  		   				
				  		   				
				  		   				if(new BigDecimal(txtValeurModif.getText())!=null)
				  		   				{
				  		   					
				  		   				}
										
									} catch (NumberFormatException e2) {
										JOptionPane.showMessageDialog(null, "Il faut saisir le Chiffre Pour la Valeur SVP !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		   		return;
									}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		   			
	
				  		   			
				  		   			
				  		   			if(checkLibelleExistantModifie.isSelected()==true)
				  		   			{
				  		   				
				  		   			Parametre parametreTmp=parametreDAO.findUniqueParametreByDateByLibelle(dateParametreModifier.getDate(), combolibelleModifie.getSelectedItem().toString())	;
				  		   			
				  		   			if(parametreTmp!=null)
				  		   			{
				  		   				
				  		   				if(parametreTmp.getId()!=parametre.getId())
				  		   				{
				  		   				JOptionPane.showMessageDialog(null, "Parameter Avec Date déja existante!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		   		return;
				  		   				}
				  		   		
				  		   			}
				  		   			
				  		   			
				  		   			
				  		   			}else
				  		   			{
				  		   				
				  		   			Parametre parametreTmp=parametreDAO.findUniqueParametreByDateByLibelle(dateParametreModifier.getDate(), txtLibelleModif.getText())	;
				  		   			
				  		   		if(parametreTmp!=null)
			  		   			{
				  		   		if(parametreTmp.getId()!=parametre.getId())
		  		   				{
				  		   		JOptionPane.showMessageDialog(null, "Parameter Avec Date déja existante!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		return;
		  		   				}
			  		   		
				  		   		
				  		   		
			  		   			}
				  		   			}
				  		   		
				  		   		 try {
				  		   			 
				  		   			 
				  		   		ParametreModifier parametreModifier=new ParametreModifier();
					  		   	parametreModifier.setCode(parametre.getCode());
					  		   	if(parametre.getDate()!=null)
					  		   	{
					  		   	parametreModifier.setDate(parametre.getDate());
					  		   	}
					  		  parametreModifier.setDateModifier(new Date());
					  		parametreModifier.setLibelle(parametre.getLibelle());	
					  		parametreModifier.setValeur(parametre.getValeur());
					  		parametreModifierDAO.add(parametreModifier);
					  		   		 
					  		   	if(checkLibelleExistantModifie.isSelected()==true)
				  		   		{
					  		   	parametre.setLibelle(combolibelleModifie.getSelectedItem().toString());
					  		   		
				  		   		}else
				  		   		{
				  		   			
				  		   		parametre.setLibelle(txtLibelleModif.getText());
				  		   		
				  		   		}
					  		   		
					  		   		parametre.setValeur(new BigDecimal(txtValeurModif.getText()));
					  		   	parametre.setDate(dateParametreModifier.getDate());
					  		   		
					  		   	 parametreDAO.edit(parametre);
					  		   	layeredPane_1.setVisible(false);
							   	listeParametres = new ArrayList<Parametre>();
							   listeParametres=parametreDAO.findAll();
					  		   	 txtCodeModif.setText("");
					  		   	txtLibelleModif.setText("");
					  		   	combolibelleModifie.setSelectedItem("");
					  		   	txtValeurModif.setText("");
					  			JOptionPane.showMessageDialog(null, "les paramètre ont été modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE); 
									afficher_table(listeParametres);
				  		   			 
				  		   			 
									
								} catch (Exception e2) {
									
									
								 
								}
				  		   		
				  		   		
								
								
								
				  		   		}	
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnValiderModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnValiderModif.setBounds(141, 142, 130, 24);
				  		   layeredPane_1.add(btnValiderModif);
				  		   
				  		   initialiserModif = new JButton("Initialiser");
				  		   initialiserModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	}
				  		   });
				  		  
				  		   initialiserModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   initialiserModif.setBounds(292, 143, 102, 23);
				  		   layeredPane_1.add(initialiserModif);
				  		   
				  		   	
				  		   	String code =Utils.genererCode(Constantes.PARAMETRE_LIBELLE);
				  		  txtCode.setText(code);
				  		  
				  		  JLabel lblDate = new JLabel("Date :");
				  		  lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblDate.setBounds(54, 110, 102, 26);
				  		  layeredPane.add(lblDate);
				  		  
				  		    dateParametre = new JDateChooser();
				  		  dateParametre.setLocale(Locale.FRANCE);
				  		  dateParametre.setDateFormatString("dd/MM/yyyy");
				  		  dateParametre.setBounds(165, 110, 191, 26);
				  		  layeredPane.add(dateParametre);
				  		  
				  		    combolibelle = new JComboBox();
				  		  combolibelle.setBounds(165, 47, 267, 24);
				  		  layeredPane.add(combolibelle);
				  		  
				  		    checkLibelleExistant = new JCheckBox("Libelle Existant");
				  		    checkLibelleExistant.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		if(checkLibelleExistant.isSelected()==true)
				  		    		{
				  		    			txtLibelle.setText("");
				  		    			txtLibelle.setVisible(false);
				  		    			combolibelle.setVisible(true);
				  		    			combolibelle.removeAllItems();
				  		    			combolibelle.addItem("");
				  		    			chargerComboLibelle();
				  		    			 combolibelle.setSelectedItem("");
				  		    			
				  		    		}else
				  		    		{
				  		    			txtLibelle.setText("");
				  		    			txtLibelle.setVisible(true);
				  		    			combolibelle.setVisible(false);
				  		    			combolibelle.removeAllItems();
				  		    			combolibelle.addItem("");
				  		    			chargerComboLibelle();
				  		    			 combolibelle.setSelectedItem("");
				  		    		}
				  		    	}
				  		    });
				  		  checkLibelleExistant.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		  checkLibelleExistant.setBounds(467, 46, 121, 23);
				  		  layeredPane.add(checkLibelleExistant);
				  		  txtValeurModif.setText(valeur+"");
				  		   txtValeurModif.setColumns(10);
				  		   txtValeurModif.setBounds(165, 73, 191, 26);
				  		   layeredPane_1.add(txtValeurModif);
				  		   
				  		   JLabel label = new JLabel("Date :");
				  		   label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   label.setBounds(54, 105, 102, 26);
				  		   layeredPane_1.add(label);
				  		   
				  		     dateParametreModifier = new JDateChooser();
				  		   dateParametreModifier.setLocale(Locale.FRANCE);
				  		   dateParametreModifier.setDateFormatString("dd/MM/yyyy");
				  		   dateParametreModifier.setBounds(165, 105, 191, 26);
				  		   layeredPane_1.add(dateParametreModifier);
				  		   
				  		   combolibelleModifie = new JComboBox();
				  		   combolibelleModifie.setBounds(165, 47, 259, 24);
				  		   layeredPane_1.add(combolibelleModifie);
				  		   
				  		   checkLibelleExistantModifie = new JCheckBox("Libelle Existant");
				  		   checkLibelleExistantModifie.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		

			  		    		
			  		    		if(checkLibelleExistantModifie.isSelected()==true)
			  		    		{
			  		    			txtLibelleModif.setText("");
			  		    			txtLibelleModif.setVisible(false);
			  		    			combolibelleModifie.setVisible(true);
			  		    			combolibelleModifie.removeAllItems();
			  		    			combolibelleModifie.addItem("");
			  		    			chargerComboLibelle();
			  		    			 combolibelleModifie.setSelectedItem("");
			  		    			
			  		    		}else
			  		    		{
			  		    			txtLibelleModif.setText("");
			  		    			txtLibelleModif.setVisible(true);
			  		    			combolibelleModifie.setVisible(false);
			  		    			combolibelleModifie.removeAllItems();
			  		    			combolibelleModifie.addItem("");
			  		    			chargerComboLibelle();
			  		    			 combolibelleModifie.setSelectedItem("");
			  		    		}
			  		    	
				  		   	}
				  		   });
				  		   checkLibelleExistantModifie.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		   checkLibelleExistantModifie.setBounds(442, 46, 121, 23);
				  		   layeredPane_1.add(checkLibelleExistantModifie);
				  		 listeParametres = new ArrayList<Parametre>();
				  		 
				  		combolibelle.addItem("");
				  		 listeParametres=parametreDAO.findAll();
				  		 
				  	 chargerComboLibelle();
				  		 combolibelle.setSelectedItem("");
				  		afficher_table(listeParametres);
				  		
				  		combolibelle.setVisible(false);
				  		combolibelleModifie.setVisible(false);
	}
	
	
	
	
	void afficher_table(List <Parametre> listeParametres)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code Paramètre","Libelle Paramètre", "Valeur","Date"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modele);
		  int i=0;
			while(i<listeParametres.size())
			{	
				Parametre parametre=listeParametres.get(i);
				if(parametre.getDate()!=null)
				{
					Object []ligne={parametre.getId(),parametre.getCode(),parametre.getLibelle(),parametre.getValeur(),parametre.getDate()};
					mapParametre.put(i, parametre);

					modele.addRow(ligne);
				}else
				{
					Object []ligne={parametre.getId(),parametre.getCode(),parametre.getLibelle(),parametre.getValeur(),""};
					mapParametre.put(i, parametre);

					modele.addRow(ligne);
				}
				
			
				i++;
			}
}
	void intialiser()
	{
		txtLibelle.setText("");
		txtValeur.setText("");
		dateParametre.setDate(null);
		combolibelle.setSelectedItem("");
		combolibelle.setVisible(false);
		txtLibelle.setVisible(true);
		checkLibelleExistant.setSelected(false);
	}
	
	void chargerComboLibelle()
	{
		
		listeParametresGroupByLibelle=parametreDAO.findAllGroupByLibelle();
 		 for(int j=0;j<listeParametresGroupByLibelle.size();j++)
 		 {
 			Parametre parametre=listeParametresGroupByLibelle.get(j) ;
 			combolibelle.addItem(parametre.getLibelle());
 			combolibelleModifie.addItem(parametre.getLibelle());
 			mapParametreGroupByLibelle.put(parametre.getLibelle(), parametre);
 			 
 			 
 		 }
	}
	
	void MAJRemiseParametre(Parametre parametre){
		
		if(parametre.getCode().equals(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION)){
			List<Equipe> listeEquipe =equipeDAO.findListeEquipeByType(TYPE_EQUIPE_CODE_PRPDUCTION,AuthentificationView.utilisateur.getCodeDepot()) ;
			 for(int i=0;i<listeEquipe.size();i++){
				 Equipe equipe =listeEquipe.get(i);
				 equipe.setRemise(parametre.getValeur());
				 equipeDAO.edit(equipe);
			 }
		}

		if(parametre.getCode().equals(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE)){
			List<Equipe> listeEquipe =equipeDAO.findListeEquipeByType(TYPE_EQUIPE_CODE_GENERIQUE,AuthentificationView.utilisateur.getCodeDepot()) ;
			 for(int i=0;i<listeEquipe.size();i++){
				 Equipe equipe =listeEquipe.get(i);
				 equipe.setRemise(parametre.getValeur());
				 equipeDAO.edit(equipe);
			 }
		}
	 	if(parametre.getCode().equals(PARAMETRE_CODE_COUT_HORAIRE_CNSS)){
	 		List<Employe> listeEmploye = employeDAO.findAll();
		   		BigDecimal coutHoriareCNSS=parametre.getValeur();
		   		Parametre parametre0 =parametreDAO.findByCode(PARAMETRE_CODE_COUT_HORAIRE);
		   		Parametre parametre1 =parametreDAO.findByCode(PARAMETRE_CODE_TAUX_CNSS_226);
		   		Parametre parametre2 =parametreDAO.findByCode(PARAMETRE_CODE_TAUX_CNSS_448);
		   		BigDecimal taux226=parametre1.getValeur().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)    ;
		   		BigDecimal taux448=parametre2.getValeur().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP) ;
		   		BigDecimal cout226=coutHoriareCNSS.multiply(taux226)    ;
		   		BigDecimal cout448=coutHoriareCNSS.multiply(taux448)   ;
		   		BigDecimal coutHoraire =coutHoriareCNSS.subtract(cout226.add(cout448));
		   		parametre0.setValeur(coutHoraire);
		   		parametreDAO.edit(parametre0);
		   		
		   		for(int i=0;i<listeEmploye.size();i++){
		   			Employe employe =listeEmploye.get(i);
		   			if(employe.isSalarie()){
		   				employe.setCoutHoraire(BigDecimal.ZERO);
		   			}else
		   			employe.setCoutHoraire(coutHoraire);
		   			employeDAO.edit(employe);
				 }
		   		
		   	}
		
		
	}
}

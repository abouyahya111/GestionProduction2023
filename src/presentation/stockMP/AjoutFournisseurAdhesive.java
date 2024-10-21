package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import util.ConverterNumberToWords;
import util.Utils;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoManager.ChargesDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ParametreDAO;
import dao.entity.Charges;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Parametre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutFournisseurAdhesive extends JLayeredPane implements Constantes  {
	public JLayeredPane contentPane;	

	private DefaultTableModel modele;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField txtCode;
	private int valeur;
	private List <FournisseurAdhesive> listeFournisseuradAdhesives = new ArrayList<FournisseurAdhesive>();
    private JTextField txtadresse;
	
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO =new FournisseurAdhesiveDAOImpl();
	private JTable TableCharges;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTextField txtnom;
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutFournisseurAdhesive() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
 	
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(48, 43, 630, 192);
				  		 add(layeredPane);
				  		 
				  		 txtCode = new JTextField();
				  		 txtCode.setForeground(Color.BLUE);
				  		 txtCode.setText("<dynamic>");
				  		 txtCode.setColumns(10);
				  		 txtCode.setBounds(165, 13, 191, 26);
				  		 layeredPane.add(txtCode);
				  		 
				  		 JLabel lblValeur = new JLabel("Adresse :");
				  		 lblValeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblValeur.setBounds(54, 85, 102, 26);
				  		 layeredPane.add(lblValeur);
				  		 
				  		 JLabel lblLibelle = new JLabel("Code Fournisseur:");
				  		 lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblLibelle.setBounds(54, 11, 130, 26);
				  		 layeredPane.add(lblLibelle);
				  		 
				  		 JLabel lblCode = new JLabel("Nom :");
				  		 lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblCode.setBounds(54, 48, 114, 26);
				  		 layeredPane.add(lblCode);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(75, 138, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   if(txtCode.getText().equals(""))
				  		   {
				  			 JOptionPane.showMessageDialog(null, "Il faut saisir le code de Fournisseur!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
				  		   }
				  		   else if (txtnom.getText().equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le Nom de Fournisseur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  
				  		   		else {
				  		   			
				  		   			FournisseurAdhesive fournisseurAdhesiveTmp=fournisseurAdhesiveDAO.findByCode(txtCode.getText().toUpperCase());
				  		   			if(fournisseurAdhesiveTmp!=null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "le Fournisseur deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   			}
				  		   		
				  		   		FournisseurAdhesive fournisseurAdhesive=new FournisseurAdhesive();
				  		   	
				  		   	fournisseurAdhesive.setCodeFournisseur(txtCode.getText().toUpperCase());
				  		  fournisseurAdhesive.setNom(txtnom.getText());
				  		fournisseurAdhesive.setAdresse(txtadresse.getText());
				  		fournisseurAdhesiveDAO.add(fournisseurAdhesive);
				  		 		
				  		   	    JOptionPane.showMessageDialog(null, "Fournisseur Adhesive Ajouté avec succée", "Validation", JOptionPane.INFORMATION_MESSAGE);
				  		   	    charger_les_Fournisseurs();
				  		   	    
							// listeMatierePremiere = new ArrayList<MatierePremier>();
							 //listeMatierePremiere=dao.findAll();
				  		     intialiser();
				  		  
					  			afficher_table(listeFournisseuradAdhesives);
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(424, 138, 102, 24);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   txtadresse = new JTextField();
				  		 util.Utils.copycoller(txtadresse);
				  		   txtadresse.setColumns(10);
				  		   txtadresse.setBounds(165, 85, 191, 26);
				  		   layeredPane.add(txtadresse);
				  		   
				  		   btnModifier = new JButton("Modifier");
				  		   btnModifier.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		Integer row=TableCharges.getSelectedRow();
				  		   		if(row>=0)
				  		   		{
				  		   			if(txtCode.getText().equals(""))
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Veuillez saisir le code de Fournisseur SVp !!!");
				  		   			
				  		   			}
				  		   			else if(txtnom.getText().equals(""))
				  		   			{
				  		   				JOptionPane.showMessageDialog(null, "Veuillez saisir le Nom de Fournisseur SVp !!!");
				  		   			}else
				  		   			{
				  		   			FournisseurAdhesive fournisseur=fournisseurAdhesiveDAO.findById(listeFournisseuradAdhesives.get(row).getId());
				  		   			FournisseurAdhesive fournisseurAdhesiveTmp=fournisseurAdhesiveDAO.findByCode(txtCode.getText().toUpperCase());
				  		   			if(fournisseurAdhesiveTmp!=null)
				  		   			{
				  		   				if(fournisseur.getId()!=fournisseurAdhesiveTmp.getId())
				  		   				{
				  		   				JOptionPane.showMessageDialog(null, "le Fournisseur deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   			return;
				  		   				}
				  		   		
				  		   			}
				  		   				
				  		   			fournisseur.setNom(txtnom.getText());
				  		   		    fournisseur.setAdresse(txtadresse.getText());
				  		   		    fournisseur.setCodeFournisseur(txtCode.getText().toUpperCase());
				  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment modifier cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
				  		   				{
				  		   				fournisseurAdhesiveDAO.edit(fournisseur);
				  		   			JOptionPane.showMessageDialog(null, "Fournisseur Adhesive Modifier avec succée ");
				  		   		    charger_les_Fournisseurs();
					  		   	    afficher_table(listeFournisseuradAdhesives);
					  		   		intialiser();
				  		   				}
				  		   								  		   				
				  		   			}
				  		   		}else
				  		   		{
				  		   			
				  		   		JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement  SVp !!!");
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		   btnModifier.setBounds(190, 137, 107, 26);
				  		   layeredPane.add(btnModifier);
				  		   
				  		   btnSupprimer = new JButton("Supprimer");
				  		   btnSupprimer.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   	Integer row=TableCharges.getSelectedRow();
			  		   		if(row>=0)
			  		   		{
			  		   			
			  		   				FournisseurAdhesive fournisseurAdhesive =fournisseurAdhesiveDAO.findById(listeFournisseuradAdhesives.get(row).getId());
			  		   			
			  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
			  		   				{
			  		   				fournisseurAdhesiveDAO.delete(fournisseurAdhesive.getId());
			  		   			 JOptionPane.showMessageDialog(null, "Fournisseur Adhesives Supprimer avec succée ");
			  		   		    charger_les_Fournisseurs();;
				  		   	    afficher_table(listeFournisseuradAdhesives);
				  		   		intialiser();
			  		   				}
			  		   			
			  		   		}else
			  		   		{
			  		   		JOptionPane.showMessageDialog(null, "Veuillez selectier un enregistrement  SVp !!!");
			  		   		}	}
				  		   });
				  		   btnSupprimer.setBounds(307, 137, 107, 26);
				  		   layeredPane.add(btnSupprimer);
				  		   
				  		   txtnom = new JTextField();
				  		   txtnom.setText("");
				  		   txtnom.setColumns(10);
				  		   txtnom.setBounds(165, 50, 191, 26);
				  		   layeredPane.add(txtnom);
				  		   
				  		   JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   scrollPane.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		
				  		   		}
				  		   });
				  		   scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   scrollPane.setBounds(48, 268, 630, 266);
				  		   add(scrollPane);
				  		   
				  		   TableCharges = new JTable();
				  		   TableCharges.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent e) {
				  		   		
				  		   	txtCode.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 0).toString());
				  		   		txtnom.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 1).toString());
				  		   		txtadresse.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 2).toString());
				  		   		btnAjouter.setEnabled(false);
				  		   		btnSupprimer.setEnabled(true);
				  		   		btnModifier.setEnabled(true);
				  		   		
				  		   		}
				  		   });
				  		   TableCharges.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"Type", "Code", "Libelle"
				  		   	}
				  		   ));
				  		   scrollPane.setViewportView(TableCharges);
				  		   
				  		   JLabel lblListeDesFournisseurs = new JLabel("Liste Des Fournisseurs");
				  		   lblListeDesFournisseurs.setForeground(Color.RED);
				  		   lblListeDesFournisseurs.setFont(new Font("Tahoma", Font.BOLD, 14));
				  		   lblListeDesFournisseurs.setBounds(48, 243, 206, 24);
				  		   add(lblListeDesFournisseurs);
				  		
				  			charger_les_Fournisseurs();
				  		   	    afficher_table(listeFournisseuradAdhesives);
				  		   		intialiser();
	}
	void charger_les_Fournisseurs()
	{
		
		listeFournisseuradAdhesives.clear();
		listeFournisseuradAdhesives.addAll(fournisseurAdhesiveDAO.findAll());
		
	}
	
	void afficher_table(List <FournisseurAdhesive> listeFournisseurAdhesive)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code","Nom","Adresse"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		TableCharges.setModel(modele);
		  int i=0;
			while(i<listeFournisseurAdhesive.size())
			{	
				FournisseurAdhesive fournisseurAdhesive=listeFournisseurAdhesive.get(i);
				Object []ligne={fournisseurAdhesive.getCodeFournisseur(),fournisseurAdhesive.getNom(), fournisseurAdhesive.getAdresse()};
				modele.addRow(ligne);
				i++;
			}
}
	
	void intialiser()
	{
		txtCode.setText("");
		txtnom.setText("");
		txtadresse.setText("");
		btnAjouter.setEnabled(true);
		btnSupprimer.setEnabled(false);
	   		btnModifier.setEnabled(false);
	}
}

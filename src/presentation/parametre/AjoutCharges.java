package presentation.parametre;

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
import dao.daoManager.ChargesDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.ParametreDAO;
import dao.entity.Charges;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.Parametre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutCharges extends JLayeredPane implements Constantes  {
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
	private JComboBox<String> TypeCombo;
	private List <Charges> listeCharges = new ArrayList<Charges>();
    private JTextField txtLibelle;
	
	private ChargesDAO chargesDAO =new ChargesDAOImpl();
	private JTable TableCharges;
	private JButton btnNewButton;
	private JButton btnSupprimer;
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutCharges() {
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
				  		 txtCode.setEnabled(false);
				  		 txtCode.setForeground(Color.BLUE);
				  		 txtCode.setText("<dynamic>");
				  		 txtCode.setColumns(10);
				  		 txtCode.setBounds(165, 44, 191, 26);
				  		 layeredPane.add(txtCode);
				  		 
				  		 JLabel lblValeur = new JLabel("Libelle:");
				  		 lblValeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblValeur.setBounds(54, 73, 102, 26);
				  		 layeredPane.add(lblValeur);
				  		 
				  		 JLabel lblLibelle = new JLabel("Code :");
				  		 lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblLibelle.setBounds(54, 42, 130, 26);
				  		 layeredPane.add(lblLibelle);
				  		 
				  		 JLabel lblCode = new JLabel("Type :");
				  		 lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblCode.setBounds(54, 13, 114, 26);
				  		 layeredPane.add(lblCode);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(75, 138, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   
				  		   		if(TypeCombo.getSelectedIndex()==-1){
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le Type!", "Attention", JOptionPane.INFORMATION_MESSAGE);}
				  		   		else if (txtLibelle.equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le Libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else {
				  		   		
				  		   		Charges charges = new Charges();
				  		   	
				  		   		charges.setType(TypeCombo.getSelectedItem().toString());
				  		   		charges.setCode(txtCode.getText());
				  		   		charges.setLiblle(txtLibelle.getText());
				  		 		chargesDAO.add(charges);
				  		 		listeCharges.add(charges);
				  		   	    JOptionPane.showMessageDialog(null, "Charges Validé avec succée", "Validation", JOptionPane.INFORMATION_MESSAGE);
				  		   	 charger_les_Charges();
				  		   	    afficher_table(listeCharges);
				  		   		intialiser();
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
				  		   
				  		   txtLibelle = new JTextField();
				  		 util.Utils.copycoller(txtLibelle);
				  		   txtLibelle.setColumns(10);
				  		   txtLibelle.setBounds(165, 73, 191, 26);
				  		   layeredPane.add(txtLibelle);
				  		   
				  		 TypeCombo = new JComboBox<String>();
				  		 TypeCombo.addActionListener(new ActionListener() {
				  		 	public void actionPerformed(ActionEvent arg0) {
				  		 		
				  		 		if(TypeCombo.getSelectedIndex()==1) 
				  		 		{
				  		 			String code =Utils.genererCode(Constantes.CHARGEST_FIX,Constantes.CHARGES);
					  		  	txtCode.setText(code);
				  		 		}
				  		 		if(TypeCombo.getSelectedIndex()==2) 
				  		 		{
				  		 			String code =Utils.genererCode(Constantes.CHARGEST_VARIABLE,Constantes.CHARGES);
					  		  	txtCode.setText(code);
				  		 		}
				  		 	}
				  		 });
				  		
				  		 TypeCombo.setBounds(165, 11, 191, 22);
				  		   layeredPane.add(TypeCombo);
				  		   
				  		   btnNewButton = new JButton("Modifier");
				  		   btnNewButton.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		Integer row=TableCharges.getSelectedRow();
				  		   		if(row>=0)
				  		   		{
				  		   			if(txtLibelle.getText().equals(""))
				  		   			{
				  		   				JOptionPane.showMessageDialog(null, "Veuillez saisir le libelle SVp !!!");
				  		   			}else
				  		   			{
				  		   				Charges charge=chargesDAO.findById(listeCharges.get(row).getId());
				  		   				charge.setLiblle(txtLibelle.getText());
				  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment modifier cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
				  		   				{
				  		   			chargesDAO.edit(charge);
				  		   			JOptionPane.showMessageDialog(null, "Charge Modifier avec succée ");
				  		   		    charger_les_Charges();
					  		   	    afficher_table(listeCharges);
					  		   		intialiser();
				  		   				}
				  		   								  		   				
				  		   			}
				  		   		}else
				  		   		{
				  		   		JOptionPane.showMessageDialog(null, "Veuillez selectier un enregistrement  SVp !!!");
				  		   		}
				  		   	}
				  		   });
				  		   btnNewButton.setBounds(190, 137, 107, 26);
				  		   layeredPane.add(btnNewButton);
				  		   
				  		   btnSupprimer = new JButton("Supprimer");
				  		   btnSupprimer.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   	Integer row=TableCharges.getSelectedRow();
			  		   		if(row>=0)
			  		   		{
			  		   			
			  		   				Charges charge =chargesDAO.findById(listeCharges.get(row).getId());
			  		   			
			  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
			  		   				{
			  		   			 chargesDAO.delete(charge.getId());
			  		   			 JOptionPane.showMessageDialog(null, "Charge Supprimer avec succée ");
			  		   		    charger_les_Charges();
				  		   	    afficher_table(listeCharges);
				  		   		intialiser();
			  		   				}
			  		   			
			  		   		}else
			  		   		{
			  		   		JOptionPane.showMessageDialog(null, "Veuillez selectier un enregistrement  SVp !!!");
			  		   		}	}
				  		   });
				  		   btnSupprimer.setBounds(307, 137, 107, 26);
				  		   layeredPane.add(btnSupprimer);
				  		   
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
				  		   		
				  		   		TypeCombo.setSelectedItem(TableCharges.getValueAt(TableCharges.getSelectedRow(), 0));
				  		   		txtCode.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 1).toString());
				  		   		txtLibelle.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 2).toString());
				  		   		TypeCombo.setEnabled(false);
				  		   		
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
				  		   
				  		   JLabel lblListeDesCharges = new JLabel("Liste Des Charges");
				  		   lblListeDesCharges.setForeground(Color.RED);
				  		   lblListeDesCharges.setFont(new Font("Tahoma", Font.BOLD, 14));
				  		   lblListeDesCharges.setBounds(48, 243, 206, 24);
				  		   add(lblListeDesCharges);
				  		       TypeCombo.addItem("");
					  		  TypeCombo.addItem("Fixe");
					  		  TypeCombo.addItem("Variable");
					  		 charger_les_Charges();
				  		   	    afficher_table(listeCharges);
				  		   		intialiser();
	}
	void charger_les_Charges()
	{
		
		listeCharges.clear();
		listeCharges.addAll(chargesDAO.findAll());
		
	}
	
	void afficher_table(List <Charges> listeCharges)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Type","Code","Libelle"
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
			while(i<listeCharges.size())
			{	
				Charges charges=listeCharges.get(i);
				Object []ligne={charges.getType(),charges.getCode(),charges.getLiblle()};
				modele.addRow(ligne);
				i++;
			}
}
	
	void intialiser()
	{
		txtCode.setText("");
		txtLibelle.setText("");
		TypeCombo.setSelectedIndex(-1);
		TypeCombo.setEnabled(true);
		}
}

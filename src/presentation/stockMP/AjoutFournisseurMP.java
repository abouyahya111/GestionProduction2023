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
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ChargesDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Charges;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FournisseurMP;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SubCategorieMp;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutFournisseurMP extends JLayeredPane implements Constantes  {
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
	private List <FournisseurMP> listeFournisseurMP = new ArrayList<FournisseurMP>();
    private JTextField txtadresse;
    private List <SubCategorieMp> listeSubCategorieMP = new ArrayList<SubCategorieMp>();
	private FournisseurMPDAO fournisseurmpDAO =new FournisseurMPDAOImpl();
	private Map< String, SubCategorieMp> mapSubCategorieMP = new HashMap<>();
	private SubCategorieMPDAO subCategorieMPDAO=new SubCategorieMPAOImpl();
	private JTable TableCharges;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTextField txtnom;
	 JComboBox comboCategorie = new JComboBox();
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutFournisseurMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 685);
 	
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(48, 43, 630, 220);
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
				  		  btnAjouter.setBounds(74, 171, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		SubCategorieMp categorieMp=mapSubCategorieMP.get(comboCategorie.getSelectedItem());
				  		   if(txtCode.getText().equals(""))
				  		   {
				  			 JOptionPane.showMessageDialog(null, "Il faut saisir le code de Fournisseur!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
				  		   }
				  		   else if (txtnom.getText().equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le Nom de Fournisseur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  
				  		   		else {
				  		   			
				  		   			FournisseurMP fournisseurMPTmp=fournisseurmpDAO.findByCode(txtCode.getText().toUpperCase());
				  		   			if(fournisseurMPTmp!=null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "le Fournisseur deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   			}
				  		   		
				  		   		FournisseurMP fournisseurMP=new FournisseurMP();
				  		   	
				  		     	fournisseurMP.setCodeFournisseur(txtCode.getText().toUpperCase());
				  		        fournisseurMP.setNom(txtnom.getText());
				  		       fournisseurMP.setAdresse(txtadresse.getText());
				  		       if(categorieMp!=null)
				  		       {
				  		    	 fournisseurMP.setSubCategorieMp(categorieMp);
				  		       }
				  		       
				  		       
				  		       
				  		 		fournisseurmpDAO.add(fournisseurMP);
				  		 		
				  		   	    JOptionPane.showMessageDialog(null, "Fournisseur MP Ajouté avec succée", "Validation", JOptionPane.INFORMATION_MESSAGE);
				  		   	    charger_les_Fournisseurs();
				  		   	    
							// listeMatierePremiere = new ArrayList<MatierePremier>();
							 //listeMatierePremiere=dao.findAll();
				  		     intialiser();
				  		  
					  			afficher_table(listeFournisseurMP);
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(423, 171, 102, 24);
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
				  		   	SubCategorieMp categorieMp=mapSubCategorieMP.get(comboCategorie.getSelectedItem());
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
				  		   			FournisseurMP fournisseur=fournisseurmpDAO.findById(listeFournisseurMP.get(row).getId());
				  		   			FournisseurMP fournisseurMPTmp=fournisseurmpDAO.findByCode(txtCode.getText().toUpperCase());
				  		   			if(fournisseurMPTmp!=null)
				  		   			{
				  		   				if(fournisseur.getId()!=fournisseurMPTmp.getId())
				  		   				{
				  		   				JOptionPane.showMessageDialog(null, "le Fournisseur deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   			return;
				  		   				}
				  		   		
				  		   			}
				  		   				
				  		   			fournisseur.setNom(txtnom.getText());
				  		   		    fournisseur.setAdresse(txtadresse.getText());
				  		   		    fournisseur.setCodeFournisseur(txtCode.getText().toUpperCase());
				  		   		    if(categorieMp!=null)
				  		   		    {
				  		   		   fournisseur.setSubCategorieMp(categorieMp);
				  		   		    }else
				  		   		    {
				  		   		   fournisseur.setSubCategorieMp(null);
				  		   		    }
				  		   		    
				  		   		    
				  		   		    
				  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment modifier cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
				  		   				{
				  		   			fournisseurmpDAO.edit(fournisseur);
				  		   			JOptionPane.showMessageDialog(null, "Fournisseur MP Modifier avec succée ");
				  		   		    charger_les_Fournisseurs();
					  		   	    afficher_table(listeFournisseurMP);
					  		   		intialiser();
				  		   				}
				  		   								  		   				
				  		   			}
				  		   		}else
				  		   		{
				  		   			
				  		   		JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement  SVp !!!");
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		   btnModifier.setBounds(189, 170, 107, 26);
				  		   layeredPane.add(btnModifier);
				  		   
				  		   btnSupprimer = new JButton("Supprimer");
				  		   btnSupprimer.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   	Integer row=TableCharges.getSelectedRow();
			  		   		if(row>=0)
			  		   		{
			  		   			
			  		   				FournisseurMP fournisseurmp =fournisseurmpDAO.findById(listeFournisseurMP.get(row).getId());
			  		   			
			  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
			  		   				{
			  		   				fournisseurmpDAO.delete(fournisseurmp.getId());
			  		   			 JOptionPane.showMessageDialog(null, "Fournisseur MP Supprimer avec succée ");
			  		   		    charger_les_Fournisseurs();;
				  		   	    afficher_table(listeFournisseurMP);
				  		   		intialiser();
			  		   				}
			  		   			
			  		   		}else
			  		   		{
			  		   		JOptionPane.showMessageDialog(null, "Veuillez selectier un enregistrement  SVp !!!");
			  		   		}	}
				  		   });
				  		   btnSupprimer.setBounds(306, 170, 107, 26);
				  		   layeredPane.add(btnSupprimer);
				  		   
				  		   txtnom = new JTextField();
				  		   txtnom.setText("");
				  		   txtnom.setColumns(10);
				  		   txtnom.setBounds(165, 50, 191, 26);
				  		   layeredPane.add(txtnom);
				  		   
				  		   JLabel lblCategorie = new JLabel("Categorie :");
				  		   lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCategorie.setBounds(54, 122, 102, 26);
				  		   layeredPane.add(lblCategorie);
				  		   
				  		    comboCategorie = new JComboBox();
				  		   comboCategorie.setBounds(165, 122, 191, 26);
				  		   layeredPane.add(comboCategorie);
				  		   
				  		   JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   scrollPane.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		
				  		   		}
				  		   });
				  		   scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   scrollPane.setBounds(48, 299, 630, 266);
				  		   add(scrollPane);
				  		   
				  		   TableCharges = new JTable();
				  		   TableCharges.setFillsViewportHeight(true);
				  		   TableCharges.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent e) {
				  		   		
				  		   	txtCode.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 0).toString());
				  		   		txtnom.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 1).toString());
				  		   		txtadresse.setText(TableCharges.getValueAt(TableCharges.getSelectedRow(), 2).toString());
				  		   		if(!TableCharges.getValueAt(TableCharges.getSelectedRow(), 3).toString().equals(""))
				  		   		{
				  		   			SubCategorieMp subCategorieMp=mapSubCategorieMP.get(TableCharges.getValueAt(TableCharges.getSelectedRow(), 3).toString());
				  		   			if(subCategorieMp!=null)
				  		   			{
				  		   				comboCategorie.setSelectedItem(subCategorieMp.getNom());
				  		   			}
				  		   			
				  		   		}
				  		   		
				  		   		btnAjouter.setEnabled(false);
				  		   		btnSupprimer.setEnabled(true);
				  		   		btnModifier.setEnabled(true);
				  		   		
				  		   		}
				  		   });
				  		   TableCharges.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"Type", "Code", "Libelle","Categorie"
				  		   	}
				  		   ));
				  		   scrollPane.setViewportView(TableCharges);
				  		   
				  		   JLabel lblListeDesFournisseurs = new JLabel("Liste Des Fournisseurs");
				  		   lblListeDesFournisseurs.setForeground(Color.RED);
				  		   lblListeDesFournisseurs.setFont(new Font("Tahoma", Font.BOLD, 14));
				  		   lblListeDesFournisseurs.setBounds(48, 274, 206, 24);
				  		   add(lblListeDesFournisseurs);
				  		
				  			charger_les_Fournisseurs();
				  		   	    afficher_table(listeFournisseurMP);
				  		   		intialiser();
				  		   		
		listeSubCategorieMP=subCategorieMPDAO.findAll();
		comboCategorie.addItem("");
		for(int i=0;i<listeSubCategorieMP.size();i++)
		{
			
		SubCategorieMp categorieMp=	listeSubCategorieMP.get(i);
		comboCategorie.addItem(categorieMp.getNom());
			
			mapSubCategorieMP.put(categorieMp.getNom(), categorieMp);
			
			
			
		}
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
	}
	void charger_les_Fournisseurs()
	{
		
		listeFournisseurMP.clear();
		listeFournisseurMP.addAll(fournisseurmpDAO.findAll());
		
	}
	
	void afficher_table(List <FournisseurMP> listeFournisseurMP)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code","Nom","Adresse", "Categorie"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		TableCharges.setModel(modele);
		  int i=0;
			while(i<listeFournisseurMP.size())
			{	
				FournisseurMP fournisseurMP=listeFournisseurMP.get(i);
			if(fournisseurMP.getSubCategorieMp()!=null)
			{
				Object []ligne={fournisseurMP.getCodeFournisseur(),fournisseurMP.getNom(), fournisseurMP.getAdresse(),fournisseurMP.getSubCategorieMp().getNom()};
				modele.addRow(ligne);
			}else
			{
				
				Object []ligne={fournisseurMP.getCodeFournisseur(),fournisseurMP.getNom(), fournisseurMP.getAdresse(),""};
				modele.addRow(ligne);
			}
				
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
	   		comboCategorie.setSelectedItem("");
	}
}

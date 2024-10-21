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
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Charges;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutCompteMagasinier extends JLayeredPane implements Constantes  {
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
	private List <CompteMagasinier> listeCompteMagasinier = new ArrayList<CompteMagasinier>();
  
	private CompteMagasinierDAO compteMagasinierDAO ;
	private Map< String, SubCategorieMp> mapSubCategorieMP = new HashMap<>();
	private SubCategorieMPDAO subCategorieMPDAO=new SubCategorieMPAOImpl();
	private JTable TableCharges;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTextField txtnom;
	JComboBox comboDepot = new JComboBox();
	private JLabel label_1;
	private JComboBox comboMagasin = new JComboBox();;
	private DepotDAO depotDAO;
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Utilisateur utilisateur;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private SequenceurDAO sequenceurDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutCompteMagasinier() {
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
              compteMagasinierDAO =new CompteMagasinierDAOImpl();
              depotDAO=new DepotDAOImpl();
              utilisateur= AuthentificationView.utilisateur;
              sequenceurDAO=new SequenceurDAOImpl();
             
          } catch (Exception exp){exp.printStackTrace();}
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(48, 43, 630, 272);
				  		 add(layeredPane);
				  		 
				  		 txtCode = new JTextField();
				  		 txtCode.setEditable(false);
				  		 txtCode.setForeground(Color.BLUE);
				  		 txtCode.setColumns(10);
				  		 txtCode.setBounds(233, 65, 191, 26);
				  		 layeredPane.add(txtCode);
				  		 
				  		 JLabel lblLibelle = new JLabel("Code Magasinier");
				  		 lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblLibelle.setBounds(122, 63, 130, 26);
				  		 layeredPane.add(lblLibelle);
				  		 
				  		 JLabel lblCode = new JLabel("Nom :");
				  		 lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblCode.setBounds(122, 100, 114, 26);
				  		 layeredPane.add(lblCode);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(74, 218, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   if(txtCode.getText().equals(""))
				  		   {
				  			 JOptionPane.showMessageDialog(null, "Il faut saisir le code de Compte Magasinier!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
				  			 return;
				  		   }
				  		   else if (txtnom.getText().equals(""))
				  		   {
				  				JOptionPane.showMessageDialog(null, "Il faut saisir le Nom de Magasinier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		  return; 
				  		   }
				  		   	
				  		   		else {
				  		   			
				  		   			
				  		   		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		   		Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				  		   			
				  		   			if(depot==null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
							  		  return; 
				  		   				
				  		   			}
				  		   			
				  		   			
				  		   		if(magasin==null)
			  		   			{
			  		   			JOptionPane.showMessageDialog(null, "Il faut Selectionner le Magasin SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		  return; 
			  		   				
			  		   			}
				  		   			
				  		   			
				  		  	CompteMagasinier compteMagasinierTmp=compteMagasinierDAO.findByCode(txtCode.getText().toUpperCase());
		  		   			if(compteMagasinierTmp!=null)
		  		   			{
		  		   			JOptionPane.showMessageDialog(null, "le Compte Magasinier deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		   			return;
		  		   			}
				  		   		
				  		   		
				  		   			
				  		   			
				  		   			CompteMagasinier compteMagasinier=compteMagasinierDAO.findByCodeByMagasin(txtCode.getText().toUpperCase(),magasin);
				  		   			if(compteMagasinier!=null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "le Compte Magasinier deja existant !", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   			}
				  		   		
				  		   		CompteMagasinier compteMagasinierTMP=new CompteMagasinier();
				  		   	
				  		   	compteMagasinierTMP.setCode(txtCode.getText().toUpperCase());
				  		  compteMagasinierTMP.setNom(txtnom.getText());
				  		compteMagasinierTMP.setMontant(BigDecimal.ZERO);
				  		compteMagasinierTMP.setDepot(depot);
				  		compteMagasinierTMP.setMagasin(magasin); 
				  		       
				  		compteMagasinierDAO.add(compteMagasinierTMP);
				  		 		
				  		   	    JOptionPane.showMessageDialog(null, "Compte Magasinier Ajouté avec succée", "Validation", JOptionPane.INFORMATION_MESSAGE);
				  		   	charger_les_Comptes();
				  		  Utils.incrementerValeurSequenceurCompteMagasinier (Constantes.CODE_COMPTE_MAGASINIER);
							// listeMatierePremiere = new ArrayList<MatierePremier>();
							 //listeMatierePremiere=dao.findAll();
				  		     intialiser();
				  		  
					  			afficher_table(listeCompteMagasinier);
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(423, 218, 102, 24);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   btnModifier = new JButton("Modifier");
				  		   btnModifier.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		Integer row=TableCharges.getSelectedRow();
				  		  
				  		   		if(row>=0)
				  		   		{
				  		   			if(txtCode.getText().equals(""))
				  		   			{
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Veuillez saisir le code de Compte Magasinier SVp !!!");
				  		   			
				  		   			}
				  		   			else if(txtnom.getText().equals(""))
				  		   			{
				  		   				JOptionPane.showMessageDialog(null, "Veuillez saisir le Nom de Magasinier SVp !!!");
				  		   			}else
				  		   			{
				  		   				
				  		   				
				  		   			Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		   		Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					  		   			
					  		   			if(depot==null)
					  		   			{
					  		   			JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								  		  return; 
					  		   				
					  		   			}
					  		   			
					  		   			
					  		   		if(magasin==null)
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Il faut Selectionner le Magasin SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
							  		  return; 
				  		   				
				  		   			}	
				  		   				
				  		   				
				  		   			CompteMagasinier compteMagasinier=compteMagasinierDAO.findById(listeCompteMagasinier.get(row).getId());
				  		   			
				  		   			if(compteMagasinier==null)
				  		   			{
				  		   				
				  		   				JOptionPane.showMessageDialog(null, "le Compte Magasinier introuvable !", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   			return;
				  		   				
				  		   		
				  		   			}
				  		   				
				  		   		compteMagasinier.setNom(txtnom.getText());
				  		   	compteMagasinier.setDepot(depot);
				  		  compteMagasinier.setMagasin(magasin);    
				  		   		    
				  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment modifier cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
				  		   				{
				  		   				compteMagasinierDAO.edit(compteMagasinier);
				  		   			JOptionPane.showMessageDialog(null, "Compte Magasinier Modifier avec succée ");
				  		   		charger_les_Comptes();
					  		   	    afficher_table(listeCompteMagasinier);
					  		   		intialiser();
				  		   				}
				  		   								  		   				
				  		   			}
				  		   		}else
				  		   		{
				  		   			
				  		   		JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement  SVp !!!");
				  		   		
				  		   		}
				  		   	}
				  		   });
				  		   btnModifier.setBounds(189, 217, 107, 26);
				  		   layeredPane.add(btnModifier);
				  		   
				  		   btnSupprimer = new JButton("Supprimer");
				  		   btnSupprimer.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   	Integer row=TableCharges.getSelectedRow();
			  		   		if(row>=0)
			  		   		{
			  		   			
			  		   				CompteMagasinier compteMagasinier =compteMagasinierDAO.findById(listeCompteMagasinier.get(row).getId());
			  		   			
			  		   				if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer cet enregistrement ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
			  		   				{
			  		   				compteMagasinierDAO.delete(compteMagasinier.getId());
			  		   			 JOptionPane.showMessageDialog(null, "Compte Magasinier Supprimer avec succée ");
			  		   		    charger_les_Comptes();
				  		   	    afficher_table(listeCompteMagasinier);
				  		   		intialiser();
			  		   				}
			  		   			
			  		   		}else
			  		   		{
			  		   		JOptionPane.showMessageDialog(null, "Veuillez selectier un enregistrement  SVp !!!");
			  		   		}	}
				  		   });
				  		   btnSupprimer.setBounds(306, 217, 107, 26);
				  		   layeredPane.add(btnSupprimer);
				  		   
				  		   txtnom = new JTextField();
				  		   txtnom.setText("");
				  		   txtnom.setColumns(10);
				  		   txtnom.setBounds(233, 102, 191, 26);
				  		   layeredPane.add(txtnom);
				  		   
				  		   JLabel label = new JLabel("D\u00E9pot :");
				  		   label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   label.setBounds(122, 137, 96, 24);
				  		   layeredPane.add(label);
				  		   
				  		   
				  			
				  		 comboDepot.addItem("");

				  		      if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
					  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
					  	    		comboDepot.addItem(depot.getLibelle());
					  	    		
					  	    		
					  	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasin.addItem(magasin.getLibelle());
							  		      			mapMagasin.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
					  	    }else {
					  	    	
					  	    	listDepot = depotDAO.findAll();	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      		    comboDepot.addItem(depot.getLibelle());
					  		      			mapDepot.put(depot.getLibelle(), depot);
					  		      			
					  		      			i++;
					  		      		}
					  		  
					  	    	
					  	    }	
				  		   
				  		   
				  		   
				  		   
				  		   
				  		    comboDepot = new JComboBox();
				  		    comboDepot.addItemListener(new ItemListener() {
				  		    	public void itemStateChanged(ItemEvent e) {
				  			 		
				  			 		

						     	 	
						     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
						     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				 		     	  	 // comboGroupe = new JComboBox();
						     	 	comboMagasin.removeAllItems();
						     	 Depot depot=new Depot();
						     	 	//comboGroupe.addItem("");
						     	 	if(!comboDepot.getSelectedItem().equals(""))
				 		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
						  		       if(depot!=null)
						  		       {
						  		    	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);   
						  		       }
				 		     	  		
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasin.addItem(magasin.getLibelle());
							  		      			mapMagasin.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
						     	 	 }
						     	 	
						 		
						 		
						 	}
				  		    });
				  		   comboDepot.setBounds(233, 137, 191, 24);
				  		   layeredPane.add(comboDepot);
				  		   
				  		   label_1 = new JLabel("Magasin :");
				  		   label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   label_1.setBounds(122, 172, 102, 24);
				  		   layeredPane.add(label_1);
				  		   
				  			comboDepot.addItem("");
						    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
					    		Depot depot = depotDAO.findByCode(utilisateur.getCodeDepot());
						     		comboDepot.addItem(depot.getLibelle());
						     		mapDepot.put(depot.getLibelle(), depot);
					    }else {
					    	
					    	listDepot = depotDAO.findAll();	
						      int i=0;
						      	while(i<listDepot.size())
						      		{	
						      		Depot depot=listDepot.get(i);
						      			mapDepot.put(depot.getLibelle(), depot);
						      			comboDepot.addItem(depot.getLibelle());
						      			i++;
						      		}
					    	
					    }
						    comboMagasin = new JComboBox();
				  		   comboMagasin.setBounds(233, 173, 191, 24);
				  		   layeredPane.add(comboMagasin);
				  		   
				  		   JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   scrollPane.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		
				  		   		}
				  		   });
				  		   scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   scrollPane.setBounds(48, 351, 630, 266);
				  		   add(scrollPane);
				  		   
				  		   TableCharges = new JTable();
				  		   TableCharges.setFillsViewportHeight(true);
				  		   TableCharges.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent e) {
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	txtCode.setText(listeCompteMagasinier.get(TableCharges.getSelectedRow()).getCode());
				  		   		txtnom.setText(listeCompteMagasinier.get(TableCharges.getSelectedRow()).getNom());
				  		   		comboDepot.setSelectedItem(listeCompteMagasinier.get(TableCharges.getSelectedRow()).getDepot().getLibelle());
				  		   		comboMagasin.setSelectedItem(listeCompteMagasinier.get(TableCharges.getSelectedRow()).getMagasin().getLibelle());
				  		   	txtCode.setEnabled(false);
				  		   	
				  		   		
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
				  		   
				  		   JLabel lblListeDesFournisseurs = new JLabel("Liste Des Magasinier");
				  		   lblListeDesFournisseurs.setForeground(Color.RED);
				  		   lblListeDesFournisseurs.setFont(new Font("Tahoma", Font.BOLD, 14));
				  		   lblListeDesFournisseurs.setBounds(48, 326, 206, 24);
				  		   add(lblListeDesFournisseurs);
				  		
				  			charger_les_Comptes();
				  		   	    afficher_table(listeCompteMagasinier);
				  		   	 
				  		   		
	
				  		   	 Sequenceur sequenceur=sequenceurDAO.findByCode(Constantes.CODE_COMPTE_MAGASINIER); 
			  		   		 if(sequenceur!=null)
			  		   		 {
			  		   		txtCode.setText(Utils.genererCodeCompteMagasinier(Constantes.CODE_COMPTE_MAGASINIER));
			  		   			 
			  		   		 }else
			  		   		 {
			  		   			 Sequenceur sequenceurTmp=new Sequenceur();
			  		   		sequenceurTmp.setCode(Constantes.CODE_COMPTE_MAGASINIER);
			  		   	sequenceurTmp.setLibelle(Constantes.CODE_COMPTE_MAGASINIER);
			  		  sequenceurTmp.setValeur(0);
			  		  sequenceurDAO.add(sequenceurTmp);
			  		  
			  		txtCode.setText(Utils.genererCodeCompteMagasinier (Constantes.CODE_COMPTE_MAGASINIER));
			  		   		 }	  		   		
				  		   		
				  		   		
				  		   		
				  		   		
	}
	void charger_les_Comptes()
	{
		
		listeCompteMagasinier.clear();
		listeCompteMagasinier.addAll(compteMagasinierDAO.findAll());
		
	}
	
	void afficher_table(List <CompteMagasinier> listeCompteMagasinier)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code","Nom","Magasin"
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
		 
			while(i<listeCompteMagasinier.size())
			{	
				CompteMagasinier compteMagasinier=listeCompteMagasinier.get(i);
		
				
				Object []ligne={compteMagasinier.getCode(),compteMagasinier.getNom(),compteMagasinier.getMagasin().getLibelle()};
				modele.addRow(ligne);
			
				
				i++;
			}
}
	
	void intialiser()
	{
		txtCode.setText(Utils.genererCodeCompteMagasinier (Constantes.CODE_COMPTE_MAGASINIER));		 
		txtnom.setText("");
	 	txtCode.setEnabled(true);
		btnAjouter.setEnabled(true);
		btnSupprimer.setEnabled(false);
	   		btnModifier.setEnabled(false);
	   		comboMagasin.removeAllItems();
	   comboDepot.setSelectedItem("");
	   		
	}
}

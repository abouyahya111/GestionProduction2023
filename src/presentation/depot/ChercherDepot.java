package presentation.depot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.FournisseurMP;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import javax.swing.JCheckBox;


public class ChercherDepot extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	private JTextField code;
	
	private StockMPDAO stockMPDAO;
	private  DepotDAO depotDAO;
	private MachineDAO machineDAO; 
	private ClientDAO clientDAO;
	
	private JTextField NomLigne;
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private List<String> listcodeMagasin = new ArrayList<String>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private Map< String, String> libelleDepotMap = new HashMap<>();
	private Map< String, String> codeDepotMap = new HashMap<>();
	private Map< String, String> mapTypeMagasin = new HashMap<>();
	private Map< String, String> mapMachine = new HashMap<>();
	private Map< String, String> mapCategorieMagasin = new HashMap<>();
	private Map< String, String> mapClient = new HashMap<>();
	
	private Depot depot=new Depot();
	private JTextField txtCodeDepot;
	private JTextField txtLibelleDepot;
	private MatierePremiereDAO matierePremiereDAO;
	private JComboBox comboDepot ;
	private JComboBox comboTypeMagasin = new JComboBox();
	private JComboBox comboMachine = new JComboBox();
	private JComboBox comboCatMagasin = new JComboBox();
	 
	private static  FournisseurMPDAO fournisseurMPDAO;
	JCheckBox checkclient = new JCheckBox("CLIENT");
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public ChercherDepot() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	depotDAO= new DepotDAOImpl();
        	machineDAO = new MachineDAOImpl();
        	clientDAO= new ClientDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
         
        	comboMachine = new JComboBox();
        	comboCatMagasin = new JComboBox();
        	matierePremiereDAO=new MatierePremierDAOImpl(); 
        	
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_MP, Constantes.MAGASIN_CODE_TYPE_MP);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_DECHET_MP, Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_PF, Constantes.MAGASIN_CODE_TYPE_PF);
        	
        	
        	comboTypeMagasin.addItem("");
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_MP);
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_DECHET_MP);
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_PF);
        	
        	comboCatMagasin.addItem("");
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE);
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION);
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION_CARTON);
	  		
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE, MAGASIN_CODE_CATEGORIE_STOCKAGE);
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION, MAGASIN_CODE_CATEGORIE_PRODUCTION);
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION_CARTON, MAGASIN_CODE_CATEGORIE_PRODUCTION_CARTON);

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        
        /*action lié aux listes dérolantes */
        
        comboCatMagasin.addItemListener(new ItemListener() {
	     	 	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		 
	     	 		 if(comboCatMagasin!=null && !comboCatMagasin.getSelectedItem().equals("") && comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE)){
	     	 			comboMachine.setSelectedItem("");
	     	 			comboMachine.setEnabled(false);
	     	 			 
	     	 			//comboMachine.setVisible(false);
	     	 			//comboClient.setVisible(true);
	     	 			
	     	 		 }else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION )) {
	     	 			comboMachine.setEnabled(true);
	     	 			 
	     	 			//comboMachine.setVisible(true);
	     	 			//comboClient.setVisible(false);
	     	 		 }else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION_CARTON ))
	     	 		 
	     	 		 {
	     	 			comboMachine.setSelectedItem("");
	     	 			comboMachine.setEnabled(false);
	     	 			 
	     	 		 }
	     	 		 
	     	 		 else   {
	     	 			 
	     	 			 
	     	 			 
	     	 			comboMachine.setSelectedItem("");
	     	 			 
	     	 			comboMachine.setEnabled(true);
	     	 		 
	     	 		 }
	  		    
	     	 	 }
	     	 	}
	     	 });
        
        comboTypeMagasin.addItemListener(new ItemListener() {
     	 	public void itemStateChanged(ItemEvent e) {
     	 	
     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
     	 		 
     	 		 if(!comboTypeMagasin.getSelectedItem().equals("") && comboTypeMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_TYPE_PF)){
     	 			comboCatMagasin.setSelectedItem(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE);
     	 			comboCatMagasin.setEnabled(false);
     	 		 }else {
     	 			comboCatMagasin.setSelectedItem("");
     	 			comboCatMagasin.setEnabled(true);
     	 		 }
     	 	
  		    
     	 	 }
     	 	}
     	 });
		
   /* REMPLIR LA LISTE DES MACHINES */     
        String Codedepot = AuthentificationView.utilisateur.getCodeDepot();
        code = new JTextField();	     
        util.Utils.copycoller(code);
  		
  		
  		
  		
  		
  		
  /*		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
  			listMachine= machineDAO.findListMachineByCodeDepot(code.getText());
		   	} else {
		   		listMachine= machineDAO.findListMachineByCodeDepot(Codedepot);
		   	} 
  		
  		comboMachine.addItem("");
		      	for(int i=0;i<listMachine.size();i++)
		      		{	
		      		Machine  machine=listMachine.get(i);
		      			mapMachine.put(machine.getNom() ,machine.getMatricule());
		      			comboMachine.addItem(machine.getNom());
		      			
		      		}*/
		      	
  /* REMPLIR LA LISTE DES CLIENTS */     
		      	  
		 
		      	
		 
/*		 	if(Codedepot.equals(CODE_DEPOT_SIEGE)){
		 		listClient= clientDAO.findAll();
  		   	} else {
  		   	 listClient= clientDAO.findListClientByCodeDepot(Codedepot);
  		   	}
		
		  for(int i=0;i<listClient.size();i++)
		 		{	
			  Client  client=listClient.get(i);
	      			mapClient.put(client.getNom() ,client.getCode());
	      			comboClient.addItem(client.getNom());
	      			
	      		}	  */			
	
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
      
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(depot.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher la dépot à modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  		  		//Utils.genererCodeMagasin(listMagasin,depot.getLibelle());
				  		  		
				  		  		depot.setListMagasin(listMagasin);
				  		  		
				  		  		depotDAO.edit(depot);
				  		  	 listMatierePremiere=matierePremiereDAO.findAll();
				  		  		
				  		  	GenererStockMp(listcodeMagasin , listMatierePremiere );
				  		  		
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Le dépot a été modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		  		
				  		  		listcodeMagasin.clear();
				  		  		
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(264, 528, 112, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiserDepot ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(386, 528, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		txtCodeDepot = new JTextField();
				  		 util.Utils.copycoller(txtCodeDepot);
				  		txtCodeDepot.setForeground(Color.BLUE);
				  		txtCodeDepot.setBackground(Color.WHITE);
				  		txtCodeDepot.setEditable(false);
	  		   			txtCodeDepot.setColumns(10);
	  		   			txtCodeDepot.setBounds(99, 11, 191, 26);
	  		   			layeredPane.add(txtCodeDepot);
	  		   			
	  		   			JLabel lblLibelle_1 = new JLabel("Libelle  :");
	  		   			lblLibelle_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		   			lblLibelle_1.setBounds(7, 45, 130, 26);
	  		   			layeredPane.add(lblLibelle_1);
	  		   			
	  		   			txtLibelleDepot = new JTextField();
	  		   		util.Utils.copycoller(txtLibelleDepot);
	  		   			txtLibelleDepot.setColumns(10);
	  		   			txtLibelleDepot.setBounds(99, 45, 191, 26);
	  		   			layeredPane.add(txtLibelleDepot);
				  		   
				  		   JLabel lblCode = new JLabel("Code D\u00E9pot: ");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		  
				  		   code.setBounds(107, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle:");
				  		   lblLibelle.setBounds(348, 26, 130, 26);
				  		   add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 825, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Magasin");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 106, 826, 411);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom Magasin :");
				  		   lblNom.setBounds(7, 113, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  		 util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(119, 114, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(679, 225, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   JLabel lblCatMagasin = new JLabel("Cat\u00E9gorieMagasin : ");
				  		   lblCatMagasin.setBounds(7, 150, 114, 26);
				  		   layeredPane.add(lblCatMagasin);
				  		   lblCatMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(7, 82, 822, 24);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("List des Magasins");
				  		   
				  		       
				  		   
				  		   
				  		   		table = new JXTable();
				  		   		table.setSelectionBackground(new Color(51, 204, 255));
				  		   		table.setRowHeightEnabled(true);
				  		   		table.setBackground(new Color(255, 255, 255));
				  		   		table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		   		table.setColumnControlVisible(true);
				  		   		table.setForeground(Color.BLACK);
				  		   		table.setGridColor(new Color(0, 0, 255));
				  		   		table.setAutoCreateRowSorter(true);
				  		   		

				  		   		table.setModel(new DefaultTableModel(
				  		   			new Object[][] {
				  		   			},
				  		   			new String[] {
				  		   					"id","Code","Libelle"
				  		   			}
				  		   		) {
				  		   			boolean[] columnEditables = new boolean[] {
				  		   					false,false,false
				  		   			};
				  		   			public boolean isCellEditable(int row, int column) {
				  		   				return columnEditables[column];
				  		   			}
				  		   		});
				  		   		

				  		   			table.setBounds(2, 27, 411, 198);
				  		   			table.setRowHeight(20);
				  		   			
 
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane(table);
				  		   			scrollPane.setBounds(10, 223, 666, 177);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			JLabel label = new JLabel("Code: ");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(4, 10, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			
				  		   			comboTypeMagasin.setBounds(511, 114, 140, 26);
				  		   			layeredPane.add(comboTypeMagasin);
				  		   			
				  		   			JLabel lblTypeMagasin = new JLabel("Type Magasin : ");
				  		   			lblTypeMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblTypeMagasin.setBounds(391, 113, 114, 26);
				  		   			layeredPane.add(lblTypeMagasin);
				  		   			
				  		   			
				  		   			comboMachine.setBounds(511, 147, 140, 26);
				  		   			layeredPane.add(comboMachine);
				  		   			
				  		   			JLabel lblDpot = new JLabel("Machine :");
				  		   			lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDpot.setBounds(391, 146, 130, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			
				  		   			comboCatMagasin.setBounds(119, 150, 191, 26);
				  		   			layeredPane.add(comboCatMagasin);
				  		   			
				  		   		 
				  		   			
				  		   			JLabel lblClient = new JLabel("Client: ");
				  		   			lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblClient.setBounds(7, 185, 114, 26);
				  		   			layeredPane.add(lblClient);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 824, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherMachine = new JButton();
				  		   			btnChercherMachine.setBounds(706, 15, 31, 31);
				  		   			layeredPane_1.add(btnChercherMachine);
				  		   			btnChercherMachine.setIcon(imgRechercher);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			 comboDepot.addItem("");
				  		   		 listDepot =depotDAO.findAll();	
					  		     	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot  depot=listDepot.get(i);
					  		      			libelleDepotMap.put(depot.getLibelle(), depot.getCode());
					  		      			codeDepotMap.put(depot.getCode(),depot.getLibelle());
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {

					  		     	 		 code.setText(libelleDepotMap.get(comboDepot.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
					  		      
					  		    code.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboDepot.setSelectedItem(codeDepotMap.get(code.getText()));
								  		}}});
							
							
					  		    	AutoCompleteDecorator.decorate(comboDepot);
				  		   			comboDepot.setBounds(437, 15, 188, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			btnChercherMachine.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(code.getText().equals("") && comboDepot.getSelectedItem().equals(""))
				  		   					JOptionPane.showMessageDialog(null, "Il faut remplir au critère de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			listcodeMagasin.clear();
		  		     			 
		  		     			depot = depotDAO.findByCode(code.getText());
		  		     			List<Machine> listMachine=new ArrayList<Machine>();
		  		     	  		 
		  		     	  		//comboCatMagasin = new JComboBox();
		  		     	  		 
		  		     	  		comboMachine.removeAllItems();
		  		     	  		
		  		     	  		 
		  		     	  		comboMachine.addItem("");
		  		     	  		
		  		     	  		mapClient = new HashMap<>();
		  		     	  		mapMachine = new HashMap<>();
		  		     	  
		  		     			listMachine= machineDAO.findListMachineByCodeDepot(code.getText());
	  		     	 			 
	  		     	 			for(int i=0;i<listMachine.size();i++)
	  		     	 				{	
	  		     	 					Machine  machine=listMachine.get(i);
	  		     	 					mapMachine.put(machine.getNom() ,machine.getMatricule());
	  		     	 					comboMachine.addItem(machine.getNom());
		  		  			
	  		     	 				} 
		  		  		
	  		     	 		   
		  		     			  
		  		     			  if(depot!=null){
		  		     			 
		  		     				  		listMagasin=depot.getListMagasin();
		  		     				  		txtCodeDepot.setText(depot.getCode());
		  		     				  		txtLibelleDepot.setText(depot.getLibelle());
		  		     				  		afficher_Magasin(listMagasin);
		  		     			  }else {
		  		     				  		JOptionPane.showMessageDialog(null, "Aucun dépot existe pour ces critères de recherche. Merci de vérifier votre critère !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   		
					  		 
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		
				  		     		if(txtCodeDepot.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut Chercher le dépot!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     		}else {
				  		     			
				  		     		 String codeMagasin =Utils.genererCodeMagasin(Constantes.MAGASIN_LIBELLE,depot.getLibelle());
				  		     			
				  		     		

					  		     	
					  		     	 //CodeLigne.setText(codeMagasin);
					  		     	if (NomLigne.getText().equals(""))
					  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboTypeMagasin.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir le Type Magasin!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboCatMagasin.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir la catégorie Magasin!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	 
					  		     	else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION) && comboMachine.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir la Machine!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     		 else {
					  		     			 String code="";
					  		     			  
					  		     			 if(!comboMachine.getSelectedItem().equals(""))
						  		     			code= mapMachine.get(comboMachine.getSelectedItem());
					  		     			 
					  		     			 Magasin magasin = new Magasin();
					  		     			 magasin.setLibelle(NomLigne.getText());
					  		     			 magasin.setCode(codeMagasin);
					  		     			 magasin.setTypeMagasin(mapTypeMagasin.get(comboTypeMagasin.getSelectedItem()));
					  		     			 magasin.setCatMagasin(mapCategorieMagasin.get(comboCatMagasin.getSelectedItem()));
					  		     			 magasin.setDepot(depot);
					  		     			 magasin.setCodeMachine(code);
					  		     		     magasin.setType(Constantes.MP_INTERNE);
												 
					  		     			 
					  		     			listcodeMagasin.add(codeMagasin);
					  		     			 listMagasin.add(magasin);
					  		     			 Utils.incrementerValeurSequenceur(Constantes.MAGASIN_LIBELLE);
					  		     			 afficher_Magasin(listMagasin);
					  		     			 intialiserMagasin ();
					  		     		 }
					  		     	
				  		     		}
					  		     	
				  		     		
				  		     	}
				  		     });
				  		 
				  		
				  		 
	}
	
	
	
	void afficher_Magasin(List<Magasin> listMagasin)
	{

		modeleLigneMachine =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"id","Code","Libelle"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		  int i=0;
			while(i<listMagasin.size())
			{	
				Magasin magasin=listMagasin.get(i);
				Object []ligne={magasin.getId(),magasin.getCode(),magasin.getLibelle()};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}

	
void intialiserMagasin (){
	
	NomLigne.setText("");
	comboTypeMagasin.setSelectedItem("");
	comboCatMagasin.setSelectedItem("");	 
	comboMachine.setSelectedItem("");
	
}
	
void intialiserDepot (){
		
	intialiserMagasin();
	txtCodeDepot.setText("");
	txtLibelleDepot.setText("");
	comboDepot.setSelectedItem("");
	code.setText("");
	listMagasin= new ArrayList<Magasin>();
	listcodeMagasin.clear();
	afficher_Magasin(listMagasin);
	listcodeMagasin.clear();
		
	}


public void  GenererStockMp(List<String> listCodeMagasin , List<MatierePremier> listmatierePremier )
{





	 
	StockMP stockMP = new StockMP();
    try{
    	
    	
    	fournisseurMPDAO=new FournisseurMPDAOImpl();
    	
    	

   }catch(Exception exp){exp.printStackTrace();		
   JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
	if( listMagasin!=null){
	
		for(int j=0;j<listMagasin.size();j++){
			
		for(int d=0;d<listmatierePremier.size();d++)	
		{
			
		MatierePremier matierePremier=listmatierePremier.get(d);	
		
		 if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  {
			 
			 
			 List<FournisseurMP> listFournisseurMP=fournisseurMPDAO.findByCategorie(matierePremier.getCategorieMp().getSubCategorieMp());  
			 
				
			 for(int k=0;k<listFournisseurMP.size() ; k++)
			 {
				 FournisseurMP fournisseurMP=listFournisseurMP.get(k);
				
					Magasin magasin =listMagasin.get(j);
					
					if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_MP) || magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
					{
						
						if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
							System.out.println("MP : "+matierePremier.getId() +" : "+magasin.getId());
							
							stockMP =stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
						if(stockMP!=null)	
						{
							stockMP.setFournisseurMP(fournisseurMP);
							stockMPDAO.edit(stockMP);
						}else
						{
							stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
						}
							
							
							
						
						if(stockMP==null){
							stockMP = new StockMP();
							stockMP.setMagasin(magasin);
							stockMP.setMatierePremier(matierePremier);
							stockMP.setQuantiteCommande(BigDecimal.ZERO);
							stockMP.setStock(BigDecimal.ZERO);
							stockMP.setStockMin(BigDecimal.ZERO);
							stockMP.setFournisseurMP(fournisseurMP);
							if(stockMP.getPrixUnitaire()!=null)
							{
								stockMP.setPrixUnitaire(stockMP.getPrixUnitaire());	
							}else
							{
								stockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
							stockMPDAO.add(stockMP);
						} 
					}
					
			
					
					
					
					
			 }
			 
			 
			 
		  }else
		  {
			  
			  

				
				Magasin magasin =listMagasin.get(j);
				
				
				if(magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_MP) || magasin.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_DECHET_MP))
				{
					
					if(!matierePremier.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
						//	stockMP =stockMPDAO.findStockByMagasinMPByFournisseurMP (matierePremier.getId(), magasin.getId(),fournisseurMP.getId());
							
							System.out.println("MP : "+matierePremier.getId() +" : "+magasin.getId());
							stockMP =stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
						
						
						
						if(stockMP==null){
							stockMP = new StockMP();
							stockMP.setMagasin(magasin);
							stockMP.setMatierePremier(matierePremier);
							stockMP.setQuantiteCommande(BigDecimal.ZERO);
							stockMP.setStock(BigDecimal.ZERO);
							stockMP.setStockMin(BigDecimal.ZERO);
							//stockMP.setFournisseurMP(fournisseurMP);
							if(stockMP.getPrixUnitaire()!=null)
							{
								stockMP.setPrixUnitaire(stockMP.getPrixUnitaire());	
							}else
							{
								stockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
							
							stockMPDAO.add(stockMP);
						} 
					
				}
				
			
				
				
			  
			  
			  
		  }
			
			
			
		}
			
		
	
		}
	}
	
	







}
}

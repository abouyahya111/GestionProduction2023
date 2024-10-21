package UniteFabrication;

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
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.MachineDAO;
import dao.entity.Depot;
import dao.entity.LigneMachine;
import dao.entity.Machine;


public class ChercherMachine extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	private JTextField code;
	
	
	private  MachineDAO machineDAO;
	private DepotDAO depotDAO; 
	private JTextField CodeLigne;
	private JTextField NomLigne;
	private List<LigneMachine> listLigneMachine = new ArrayList<LigneMachine>();
	private List<Machine> listMachine =new ArrayList<Machine>();
	private Map< String, String> machineMap = new HashMap<>();
	private Map< String, String> codeMachineMap = new HashMap<>();
	private Map< String, String> libelleMachineMap = new HashMap<>();
	private Map< String, String> mapCodeDepot = new HashMap<>();
	
	private Machine machine=new Machine();
	private JTextField txtCodeMachine;
	private JTextField txtLibelleMachine;
	private JTextField txtStockSolofane;
	
	private JComboBox comboMachine ;
	JComboBox comboDepot;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherMachine() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	machineDAO=new MachineDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	comboDepot = new JComboBox();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        final String Codedepot = AuthentificationView.utilisateur.getCodeDepot(); 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(machine.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher la machine ‡ modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  		
				  		  			if(comboDepot.getSelectedItem()==null || comboDepot.getSelectedItem().equals(""))
				  		  			JOptionPane.showMessageDialog(null, "Il faut Choisir le dÈpot!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			else {
				  		  		machine.setListLigneMachine(listLigneMachine);
				  		  		machine.setNom(txtLibelleMachine.getText());
				  		  		machine.setCodeDepot(mapCodeDepot.get(comboDepot.getSelectedItem()));
				  		  		machineDAO.edit(machine);
				  		  		JOptionPane.showMessageDialog(null, "La machine a ÈtÈ modifiÈe avec succËs!", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
				  		  		intialiserMachine ();
				  		  			}
				  		  		
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(264, 471, 112, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiserMachine ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(409, 471, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		txtCodeMachine = new JTextField();
				  		util.Utils.copycoller(txtCodeMachine);
	  		   			txtCodeMachine.setColumns(10);
	  		   			txtCodeMachine.setBounds(99, 11, 191, 26);
	  		   			layeredPane.add(txtCodeMachine);
	  		   			
	  		   			JLabel label_1 = new JLabel("Libelle Machine :");
	  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		   			label_1.setBounds(7, 45, 130, 26);
	  		   			layeredPane.add(label_1);
	  		   			
	  		   			txtLibelleMachine = new JTextField();
	  		   		util.Utils.copycoller(txtLibelleMachine);
	  		   			txtLibelleMachine.setColumns(10);
	  		   			txtLibelleMachine.setBounds(99, 45, 191, 26);
	  		   			layeredPane.add(txtLibelleMachine);
	  		   			
	  		   			JLabel lblStockSolofane = new JLabel("D\u00E9pot :");
	  		   			lblStockSolofane.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		   			lblStockSolofane.setBounds(4, 81, 130, 26);
	  		   			layeredPane.add(lblStockSolofane);
	  		   			
	  		   			txtStockSolofane = new JTextField();
	  		   		util.Utils.copycoller(txtStockSolofane);
	  		   			txtStockSolofane.setVisible(false);
	  		   			txtStockSolofane.setColumns(10);
	  		   			txtStockSolofane.setBounds(301, 82, 191, 26);
	  		   			layeredPane.add(txtStockSolofane);
				  		   
				  		   JLabel lblCode = new JLabel("Code Machine: ");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   code = new JTextField();
				  		 util.Utils.copycoller(code);
				  		   code.setBounds(107, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle Machine :");
				  		   lblLibelle.setBounds(348, 26, 130, 26);
				  		   add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 825, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Machine");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 106, 826, 348);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom Ligne");
				  		   lblNom.setBounds(317, 149, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  		 util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(386, 150, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(680, 207, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   CodeLigne = new JTextField();
				  		 util.Utils.copycoller(CodeLigne);
				  		   CodeLigne.setBounds(87, 150, 191, 26);
				  		   layeredPane.add(CodeLigne);
				  		   CodeLigne.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code Ligne : ");
				  		   lblCodeLigne.setBounds(10, 149, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(10, 119, 822, 24);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("List des Lignes Machine");
				  		   
				  		       
				  		   
				  		   
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
				  		   			scrollPane.setBounds(10, 183, 666, 154);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			JLabel label = new JLabel("Code Machine: ");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(4, 10, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			
				  		   			comboDepot.setBounds(99, 82, 188, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 824, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherMachine = new JButton("Chercher Machine");
				  		   			btnChercherMachine.setBounds(638, 15, 157, 26);
				  		   			layeredPane_1.add(btnChercherMachine);
				  		   			btnChercherMachine.setIcon(imgChercher);
				  		   			
				  		   			 comboMachine = new JComboBox();
				  		   			 comboMachine.addItem("");
				  		   			 
				  		   		
				  		   	if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				  		   		listMachine = machineDAO.findAll();
				  		   	} else {
				  		   		listMachine = machineDAO.findListMachineByCodeDepot(Codedepot);	
				  		   	}
				  		   		 
					  		     	
					  		      int i=0;
					  		      	while(i<listMachine.size())
					  		      		{	
					  		      			Machine machine=listMachine.get(i);
					  		      			machineMap.put(machine.getNom(), machine.getMatricule());
					  		      			codeMachineMap.put( machine.getMatricule(),machine.getNom());
					  		      			comboMachine.addItem(machine.getNom());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboMachine.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	code.setText(machineMap.get(comboMachine.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
					  		      
					  		    code.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboMachine.setSelectedItem(codeMachineMap.get(code.getText()));
								  		}}});
							
					  		    
					  		    
							
							AutoCompleteDecorator.decorate(comboMachine);
				  		   			comboMachine.setBounds(437, 15, 188, 26);
				  		   			layeredPane_1.add(comboMachine);
				  		   			btnChercherMachine.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(code.getText().equals("") && comboMachine.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critËre de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			  machine = machineDAO.findByCodeNom(code.getText());
		  		     			  comboDepot.removeAllItems();
		  		     			  
		  		     			  if(machine!=null){
		  		     				  
		  		     				  /* traitement pour affecter magesin ‡ dÈpot */
		  					  		   
		  					  		   
		  					  		   
		  					  		   if(Codedepot.equals(CODE_DEPOT_SIEGE)){
		  					  			   
		  					  			 List<Depot> listDepot = depotDAO.findDepotByCodeSaufEnParametre(CODE_DEPOT_SIEGE);
		  					  			 
		  					  			int i=0;
		  				  		      	while(i<listDepot.size())
		  				  		      		{	
		  				  		      			Depot  depot=listDepot.get(i);
		  				  		      			mapCodeDepot.put(depot.getLibelle() ,depot.getCode());
		  				  		      			libelleMachineMap.put(depot.getCode(), depot.getLibelle());
		  				  		      			comboDepot.addItem(depot.getLibelle());
		  				  		      			i++;
		  				  		      		}
		  					  		   }else {
		  					  			 Depot  depot=depotDAO.findByCode(Codedepot);
		  					  			 comboDepot.addItem(depot.getLibelle());
		  					  			 mapCodeDepot.put(depot.getLibelle() ,depot.getCode());
		  					  		   }
		  		     			 
		  		     			listLigneMachine=machine.getListLigneMachine();
		  		     			txtCodeMachine.setText(machine.getMatricule());
		  		     			txtLibelleMachine.setText(machine.getNom());
		  		     			comboDepot.setSelectedItem(libelleMachineMap.get(machine.getCodeDepot()));;
		  		     		//	txtStockSolofane.setText(machine.getStockSolofane()+"");
		  		     			afficher_LigneMachine(listLigneMachine);
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Aucune machine existe pour ces critËres de recherche. Merci de vÈrifier votre critËre !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   			
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		if(CodeLigne.getText().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if (NomLigne.getText().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		 else {
				  		     	LigneMachine ligneMachine = new LigneMachine();
				  		     	ligneMachine.setNom(NomLigne.getText());
				  		     	ligneMachine.setMatricule(CodeLigne.getText());
				  		     	ligneMachine.setMachine(machine);
				  		     	listLigneMachine.add(ligneMachine);
				  		     		 }
				  		     	
				  		    afficher_LigneMachine(listLigneMachine);
				  		    intialiserLigneMachine();
				  		     	
				  		     	}
				  		     });
				  		 
				  		
				  		 
	}
	
	
	
	void afficher_LigneMachine(List<LigneMachine> listLigneMachine)
	{

		modeleLigneMachine =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"id","Code","Nom Ligne","Stock Solofane","Stock Centure","Stock Skoutch"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		  int i=0;
			while(i<listLigneMachine.size())
			{	
				LigneMachine ligneMachine=listLigneMachine.get(i);
				Object []ligne={ligneMachine.getId(),ligneMachine.getMatricule(),ligneMachine.getNom(),ligneMachine.getStockSolofane(),ligneMachine.getStockCenture(),ligneMachine.getStockStoutch()};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}

	
void intialiserLigneMachine (){
		
		NomLigne.setText("");
		CodeLigne.setText("");
		
	}
	
void intialiserMachine (){
		
	intialiserLigneMachine();
	txtCodeMachine.setText("");
	txtLibelleMachine.setText("");
	txtStockSolofane.setText("");
	comboMachine.setSelectedItem("");
	code.setText("");
	listLigneMachine= new ArrayList<LigneMachine>();
	afficher_LigneMachine(listLigneMachine);
	
		
	}
}

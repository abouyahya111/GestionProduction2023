package UniteFabrication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JPanel;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.SequenceurDAO;
import dao.entity.Depot;
import dao.entity.LigneMachine;
import dao.entity.Machine;
import dao.entity.Sequenceur;


public class AjoutMachine extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private JTextField textTotal;
	private JTextField total;
	private JPanel top;
	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private JTextField code;
	private JTextField nom;
	
	private Map< String, String> mapCodeDepot = new HashMap<>();
	
	private  MachineDAO machineDAO;
	private DepotDAO depotDAO;
	private JComboBox comboCatModif;
	JComboBox comboDepot ;
	
	private int id_mp;
	private JTextField CodeLigne;
	private JTextField NomLigne;
	private List<LigneMachine> listLigneMachine = new ArrayList<LigneMachine>();
	Machine machine=new Machine();
 
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutMachine() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	machineDAO=new MachineDAOImpl();
        	depotDAO=new DepotDAOImpl();
       

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	intialiserMachine();
				  		  		
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(377, 408, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane_1 = new JLayeredPane();
				  		 
				  		  JLabel lblDpot = new JLabel("D\u00E9pot: ");
				  		   lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblDpot.setBounds(10, 51, 114, 24);
				  		   layeredPane_1.add(lblDpot);
				  		 comboDepot = new JComboBox();
				  		   
				  		   comboDepot.setBounds(92, 51, 187, 24);
				  		   layeredPane_1.add(comboDepot);
				  		   
				  		   code = new JTextField();
				  		   code.setBackground(Color.WHITE);
				  		   code.setEditable(false);
				  		   code.setBounds(98, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle");
				  		   lblLibelle.setBounds(374, 25, 130, 26);
				  		   add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   nom = new JTextField();
				  		 util.Utils.copycoller(nom);
				  		   nom.setBounds(422, 26, 191, 26);
				  		   add(nom);
				  		   nom.setColumns(10);
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(7, 104, 766, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Ligne Machine");
				  		   
				  		   JButton btnValiderAjoutMachine = new JButton("Valider Ajout Machine");
				  		   btnValiderAjoutMachine.setIcon(imgValider);
				  		   
				  		   /* traitement pour affecter magesin à dépot */
				  		   
				  		   String Codedepot = AuthentificationView.utilisateur.getCodeDepot();
				  		 comboDepot.addItem("");
				  		   if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				  			   
				  			 List<Depot> listDepot = depotDAO.findDepotByCodeSaufEnParametre(CODE_DEPOT_SIEGE);
				  			 
				  			int i=0;
			  		      	while(i<listDepot.size())
			  		      		{	
			  		      			Depot  depot=listDepot.get(i);
			  		      			mapCodeDepot.put(depot.getLibelle() ,depot.getCode());
			  		      			comboDepot.addItem(depot.getLibelle());
			  		      			i++;
			  		      		}
				  		   }else {
				  			 Depot  depot=depotDAO.findByCode(Codedepot);
				  			 comboDepot.addItem(depot.getLibelle());
				  			mapCodeDepot.put(depot.getLibelle() ,depot.getCode());
				  		   }
				  		   
				  		 comboDepot.addItemListener(new ItemListener() {
				      	 	public void itemStateChanged(ItemEvent e) {
				      	 	
				      	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				      	 		 
				      	 		 if(!comboDepot.getSelectedItem().equals("") ){
				      	 			 
				      	 			String codeMachine =Utils.genererCodeMachine(Constantes.MACHINE_LIBELLE,comboDepot.getSelectedItem().toString());
							  		  code.setText(codeMachine);
				      	 			
				      	 		 }else {
				      	 			 code.setText("");
				      	 		 }
				      	 	
				   		    
				      	 	 }
				      	 	}
				      	 });
				  		   
				  		   
				  		   
				  			
				  		   
				  		   btnValiderAjoutMachine.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   
				  		   	if(code.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		else if (nom.getText().equals(""))
		  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		else if (comboDepot.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le dépot!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		else if (listLigneMachine.size()<1){
		  		     		 JOptionPane.showMessageDialog(null, "Il faut Ajouter au moins une ligne de machine!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		}
		  		     		 else {
		  		     //	Machine machine = new Machine();
		  		     			
		  		     	machine.setNom(nom.getText());
		  		     	machine.setMatricule(code.getText());
		  		     	machine.setListLigneMachine(listLigneMachine);
		  		     	machine.setCodeDepot(mapCodeDepot.get(comboDepot.getSelectedItem()));
		  		     	machineDAO.add(machine);
		  		     	JOptionPane.showMessageDialog(null, "La machine a été ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		     	Utils.incrementerValeurSequenceur(Constantes.MACHINE_LIBELLE);
		  		     	intialiserMachine ();
		  		     		 }
				  		   		
				  		   	}
				  		   });
				  		   btnValiderAjoutMachine.setBounds(189, 409, 170, 26);
				  		   add(btnValiderAjoutMachine);
				  		   
				  		   JLayeredPane layeredPane = new JLayeredPane();
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(6, 117, 767, 280);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom Ligne");
				  		   lblNom.setBounds(315, 23, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  		 util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(390, 24, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(686, 132, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   CodeLigne = new JTextField();
				  		 util.Utils.copycoller(CodeLigne);
				  		   CodeLigne.setBounds(90, 24, 191, 26);
				  		   layeredPane.add(CodeLigne);
				  		   CodeLigne.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code Ligne : ");
				  		   lblCodeLigne.setBounds(10, 23, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(3, 83, 764, 24);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("List des Lignes Machine");
				  		   
				  		   JButton btnSupprimerLigne = new JButton("");
				  		   btnSupprimerLigne.setIcon(imgSupprimer);
				  		   btnSupprimerLigne.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	 try{
								   int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut sélectionner une Ligne à supprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {
									   
									   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez Supprimer  cette Ligne?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION ){
									   
									row = table.getSelectedRow();
								  
									listLigneMachine.remove(row);
								   afficher_LigneMachine(listLigneMachine);
			                        table.setRowSorter(null);
								     modele.removeRow(row);
										}
								   }
					                }catch (Exception e1){
					                	}
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnSupprimerLigne.setBounds(686, 165, 60, 26);
				  		   layeredPane.add(btnSupprimerLigne);
				  		   
				  		       
				  		   
				  		   
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
				  		   			scrollPane.setBounds(10, 115, 666, 154);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   
				  		  
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   layeredPane_1.setBounds(6, 11, 767, 82);
				  		   add(layeredPane_1);
				  		   
				  		   JLabel lblCode = new JLabel("Code Machine: ");
				  		   lblCode.setBounds(10, 14, 114, 26);
				  		   layeredPane_1.add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		 
				  		   
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
				  		     	afficher_LigneMachine(listLigneMachine);
						  		  intialiserLigneMachine ();
				  		     		 }
				  		     	
				  		    
				  		     	
				  		     	}
				  		     });
				  		 
		
				  		  	  		     
				  		   comboDepot.setSelectedItem("");		  		   
					  		   
				  		     
	}
	
	
	
	
	void afficher_LigneMachine(List<LigneMachine> listLigneMachine)
	{

		modeleLigneMachine =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"id","Code","Nom Ligne"
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
			while(i<listLigneMachine.size())
			{	
				LigneMachine ligneMachine=listLigneMachine.get(i);
				Object []ligne={ligneMachine.getId(),ligneMachine.getMatricule(),ligneMachine.getNom()};

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
		
	nom.setText("");
	code.setText("");
	listLigneMachine= new ArrayList<LigneMachine>();
	afficher_LigneMachine(listLigneMachine);
	
	comboDepot.setSelectedItem("");	
	}
}

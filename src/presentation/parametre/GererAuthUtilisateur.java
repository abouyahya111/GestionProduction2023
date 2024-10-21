package presentation.parametre;

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

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.DetailEstimation;
import dao.entity.Habilitation;
import dao.entity.MatierePremier;
import dao.entity.Utilisateur;


public class GererAuthUtilisateur extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private JButton btnIntialiserOF;
	private	JComboBox comboUtilisateur = new JComboBox();
	
	private Map< Integer, Habilitation> mapHabilitation = new HashMap<>();
	private Map< Integer, Habilitation> mapHabilitationTmp = new HashMap<>();
	private Map< String, Utilisateur> mapUtilisateur = new HashMap<>();
	private Map< Integer, Boolean> mapAuthorisation = new HashMap<>();
	
	private List<Utilisateur> listUtilisateur =new ArrayList<Utilisateur>();
	private List<Habilitation> listHabilitation =new ArrayList<Habilitation>();
	private Utilisateur utilisateur= new Utilisateur();

	private UtilisateurDAO 	utilisateurDAO;
	private HabilitationDAO habilitationDAO;
	
	private JTextField txtDepot = new JTextField();
	private JTextField txtIdentifiant;
	private JTextField txtLogin;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public GererAuthUtilisateur() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 725);
        try{
        	
        	util.Utils.copycoller(txtDepot);
        	
        	utilisateurDAO= new UtilisateurDAOImpl();
        	habilitationDAO= new HabilitationDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
            imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(324, 658, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		 comboUtilisateur.addItem(""); 
				  		listUtilisateur=utilisateurDAO.findAllSaufAdmin();
				  	        int i=0;
				  		      	while(i<listUtilisateur.size())
				  		      		{	
				  		      		Utilisateur utilisateur=listUtilisateur.get(i);
				  		      			mapUtilisateur.put(utilisateur.getNom(), utilisateur);
				  		      			comboUtilisateur.addItem(utilisateur.getNom());
				  		      			i++;
				  		      		}
				  			
				  		      comboUtilisateur.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 		 utilisateur=mapUtilisateur.get(comboUtilisateur.getSelectedItem());
				  		     	 		txtDepot.setText(utilisateur.getCodeDepot());
				  		     	 		txtIdentifiant.setText(utilisateur.getId()+"");
				  		     	 		txtLogin.setText(utilisateur.getLogin());
				  		     	 		listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId())	;	  		     
				  		     	 		afficher_tableMP(listHabilitation)	;	  
				  	                  
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		      
				  		   
				  		    
				  		
				  		    
				  		/*""""""""""""""""""""""""""""""""""""""""*/    
				  		    
				  		 
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
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
				  		     intialiserTableau();	
				  		     
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 167, 782, 461);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 720, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 781, 121);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(294, 88, 144, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  txtDepot.setBackground(Color.WHITE);
				  		  txtDepot.setEditable(false);
				  		  
				  		  
				  		  txtDepot.setBounds(348, 88, 144, 24);
				  		  layeredPane.add(txtDepot);
				  		  txtDepot.setColumns(10);
				  		  
				  		  JLabel lblArticle = new JLabel("Utilisateur:");
				  		  lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblArticle.setBounds(10, 11, 144, 24);
				  		  layeredPane.add(lblArticle);
				  		
				  		  comboUtilisateur.setBounds(89, 11, 166, 24);
				  		  layeredPane.add(comboUtilisateur);
				  		  
				  		  JLabel lblIdentifiant = new JLabel("Identifiant :");
				  		  lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblIdentifiant.setBounds(10, 52, 144, 24);
				  		  layeredPane.add(lblIdentifiant);
				  		  
				  		  txtIdentifiant = new JTextField();
				  		util.Utils.copycoller(txtIdentifiant);
				  		  txtIdentifiant.setBackground(Color.WHITE);
				  		  txtIdentifiant.setEditable(false);
				  		  txtIdentifiant.setColumns(10);
				  		  txtIdentifiant.setBounds(89, 52, 144, 24);
				  		  layeredPane.add(txtIdentifiant);
				  		  
				  		  JLabel lblLogin = new JLabel("Login :");
				  		  lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblLogin.setBounds(10, 88, 144, 24);
				  		  layeredPane.add(lblLogin);
				  		  
				  		  txtLogin = new JTextField();
				  		util.Utils.copycoller(txtLogin);
				  		  txtLogin.setBackground(Color.WHITE);
				  		  txtLogin.setEditable(false);
				  		  txtLogin.setColumns(10);
				  		  txtLogin.setBounds(89, 88, 144, 24);
				  		  layeredPane.add(txtLogin);
		
		JButton btnValiderTransfer = new JButton("Valider Authorisation");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapAuthorisation())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir les authorisations", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
					validerAuthorisation(listHabilitation);
					JOptionPane.showMessageDialog(null, "Authorisation a ÈtÈ validÈe avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(156, 658, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnSelectionnertout = new JButton();
		btnSelectionnertout.setIcon(imgSelectAll);
		btnSelectionnertout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionnerTout(listHabilitation);
			}
		});
		btnSelectionnertout.setBounds(764, 139, 26, 26);
		add(btnSelectionnertout);
		
		JButton btnDeslectionnerTout = new JButton();
		btnDeslectionnerTout.setIcon(imgDeselectAll);
		btnDeslectionnerTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deselectionnerTout(listHabilitation);
			}
		});
		btnDeslectionnerTout.setBounds(735, 139, 26, 26);
		add(btnDeslectionnerTout);
		
				 
	}
	
	
	void intialiser()
	{
		
		
	}
	
	void afficher_tableMP(List<Habilitation> listHabilitation)
	{
		intialiserTableau();
		  int i=0;
			while(i<listHabilitation.size())
			{	
				
				Habilitation habilitation=listHabilitation.get(i);
				Object []ligne={habilitation.getId(),habilitation.getMenu().getLibelle(),habilitation.isAutorise()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	


boolean remplirMapAuthorisation(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
			mapAuthorisation.put(Integer.parseInt(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 2).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


void  validerAuthorisation(List<Habilitation> listDHabilitation){
	boolean authorise;
	

	for(int i=0;i<listDHabilitation.size();i++){
		
		Habilitation habilitation=listDHabilitation.get(i);
		authorise=mapAuthorisation.get(habilitation.getId());
		habilitation.setAutorise(authorise);
		
		habilitationDAO.edit(habilitation);
	}
	
	

	
}
void selectionnerTout(List<Habilitation> listDHabilitation){
	
for(int i=0;i<listDHabilitation.size();i++){
		
		Habilitation habilitation=listDHabilitation.get(i);
		
		habilitation.setAutorise(true);
		
		listDHabilitation.set(i, habilitation);
	}
afficher_tableMP(listDHabilitation);
}

void deselectionnerTout(List<Habilitation> listDHabilitation){
	
for(int i=0;i<listDHabilitation.size();i++){
		
		Habilitation habilitation=listDHabilitation.get(i);
		
		habilitation.setAutorise(false);
		
		listDHabilitation.set(i, habilitation);
	}
afficher_tableMP(listDHabilitation);
	
}

void intialiserTableau(){
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Identifiant Authorisation","Nom Menu ","AuthorisÈ"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true 
		     	};
		     	Class[] columnTypes = new Class[] {
		     			Integer.class,String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(10);
		 //table.getColumnModel().getColumn(3).setPreferredWidth(60);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}

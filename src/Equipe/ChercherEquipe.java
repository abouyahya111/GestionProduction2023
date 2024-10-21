package Equipe;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.TypeEquipeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeBloqueDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.TypeEquipeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.EmployeBloque;
import dao.entity.Equipe;
import dao.entity.TypeEquipe;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;


public class ChercherEquipe extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgSupprimer;
	
	private JButton btnInitialiser;
	private JButton btnModifier;

	private JDateChooser dateNaissanceChooser;
	private JDateChooser dateEntreChooser;
	
	
	private JTextField CodeLigne;
	private JTextField NomLigne;
	private JTextField txtCodeEquipe;
	private JTextField txtLibelleEquipe;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JTextField txtNumDoss;
	private JTextField txtNumCNSS;
	private JTextField txtLieuNaiss;
	private JTextField code;
	
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<Equipe> listEquipe =new ArrayList<Equipe>();
	private List<TypeEquipe> listTypeEquipe = new ArrayList<TypeEquipe>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, TypeEquipe> mapTypeEquipe = new HashMap<>();
	private Map< String, TypeResEmploye> mapTypeEmploye = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	
	private Equipe equipe=new Equipe();
	private DepotDAO depotDAO;
	private Utilisateur utilisateur;
	
	private JComboBox comboEquipe = new JComboBox();
	private JComboBox comboTypeEquipe = new JComboBox();
	private JComboBox comboRespon= new JComboBox();
	private JTextField txtCoutHoraire;
	private List<TypeResEmploye> listTypeResEmploye = new ArrayList<TypeResEmploye>();
	private JComboBox comboDepot = new JComboBox();
	
	private TypeResEmployeDAO typeResEmployeDAO;
	private TypeEquipeDAO typeEquipeDAO;
	private  EmployeBloqueDAO employeBloqueDAO;
	private  EquipeDAO equipeDAO;
	private  ParametreDAO parametreDAO= new ParametreDAOImpl();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherEquipe() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 585);
        try{
        	
        	equipeDAO=new EquipeDAOImpl();
        	typeResEmployeDAO=new TypeResEmployeDAOImpl();
         	typeEquipeDAO=new TypeEquipeDAOImpl();
         	employeBloqueDAO=new EmployeBloqueDAOImpl();
         	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;


       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
     listTypeResEmploye = typeResEmployeDAO.findAll();	
     listTypeEquipe=typeEquipeDAO.findAll();
   	 comboRespon.addItem("");	 
   	comboTypeEquipe.addItem("");
   	mapParametre=Utils.listeParametre();
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		  btnModifier = new JButton("Modifier Equipe");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(equipe.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher l'équipe à modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  		  		equipe.setNomEquipe(txtLibelleEquipe.getText());
				  		  		equipe.setTypeEquipe(mapTypeEquipe.get(comboTypeEquipe.getSelectedItem()));
				  		  		equipe.setListEmploye(listEmploye);
				  		  		
				  		  		equipeDAO.edit(equipe);
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "L'équipe a été modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		  		intialiserTableau();
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(365, 549, 139, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiserAll ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(509, 550, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		txtCodeEquipe = new JTextField();
				  		 util.Utils.copycoller(txtCodeEquipe);
				  		txtCodeEquipe.setBackground(Color.WHITE);
				  		txtCodeEquipe.setDisabledTextColor(Color.BLACK);
				  		txtCodeEquipe.setEditable(false);
	  		   			txtCodeEquipe.setColumns(10);
	  		   			txtCodeEquipe.setBounds(120, 4, 191, 26);
	  		   			layeredPane.add(txtCodeEquipe);
	  		   			
	  		   			JLabel lblLibelle_1 = new JLabel("Libelle  :");
	  		   			lblLibelle_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		   			lblLibelle_1.setBounds(22, 33, 130, 26);
	  		   			layeredPane.add(lblLibelle_1);
	  		   			
	  		   			txtLibelleEquipe = new JTextField();
	  		   		 util.Utils.copycoller(txtLibelleEquipe);
	  		   			txtLibelleEquipe.setColumns(10);
	  		   			txtLibelleEquipe.setBounds(119, 34, 191, 26);
	  		   			layeredPane.add(txtLibelleEquipe);
				  		   
				  		   JLabel lblCode = new JLabel("Code Equipe: ");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   code = new JTextField();
				  		 util.Utils.copycoller(code);
				  		   code.setBackground(Color.WHITE);
				  		   code.setBounds(107, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 65, 998, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Equipe");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 84, 999, 461);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom ");
				  		   lblNom.setBounds(8, 81, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  		 util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(92, 82, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(929, 265, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   CodeLigne = new JTextField();
				  		 util.Utils.copycoller(CodeLigne);
				  		   CodeLigne.setBounds(92, 110, 191, 26);
				  		   layeredPane.add(CodeLigne);
				  		   CodeLigne.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Matricule :");
				  		   lblCodeLigne.setBounds(8, 109, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(10, 63, 999, 18);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("List des Employ\u00E9s");
				  		   
				  		   		table = new JXTable();
				  		   		table.setSelectionBackground(new Color(51, 204, 255));
				  		   		table.setRowHeightEnabled(true);
				  		   		table.setBackground(new Color(255, 255, 255));
				  		   		table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		   		table.setColumnControlVisible(true);
				  		   		table.setForeground(Color.BLACK);
				  		   		table.setGridColor(new Color(0, 0, 255));
				  		   		table.setAutoCreateRowSorter(true);
				  		   		intialiserTableau ();
				  		   		table.setModel(modeleLigneMachine);
				  		   		

				  		   			table.setBounds(2, 27, 411, 198);
				  		   			table.setRowHeight(20);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane(table);
				  		   			scrollPane.setBounds(7, 240, 912, 221);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			JLabel lblCode_1 = new JLabel("Code :");
				  		   			lblCode_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCode_1.setBounds(22, 4, 114, 26);
				  		   			layeredPane.add(lblCode_1);
				  		   			
				  		   			JLabel label = new JLabel("Responsabilti\u00E9 :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(8, 202, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 
				  		   			 comboRespon.setBounds(92, 203, 191, 26);
				  		   			layeredPane.add(comboRespon);
				  		   			
				  		   			JLabel label_1 = new JLabel("Cout Horaire");
				  		   			label_1.setVisible(false);
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(691, 109, 130, 26);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			txtCoutHoraire = new JTextField();
				  		   		util.Utils.copycoller(txtCoutHoraire);
				  		   			txtCoutHoraire.setVisible(false);
				  		   			txtCoutHoraire.setColumns(10);
				  		   			txtCoutHoraire.setBounds(785, 110, 181, 26);
				  		   			layeredPane.add(txtCoutHoraire);
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 Tel :");
				  		   			lblNTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNTel.setBounds(313, 203, 130, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   			txtTel = new JTextField();
				  		   		util.Utils.copycoller(txtTel);
				  		   			txtTel.setColumns(10);
				  		   			txtTel.setBounds(409, 203, 191, 26);
				  		   			layeredPane.add(txtTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse:");
				  		   			lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblAdresse.setBounds(313, 138, 130, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(409, 139, 273, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			 dateNaissanceChooser = new JDateChooser();
				  		   			dateNaissanceChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateNaissanceChooser.setBounds(409, 110, 181, 26);
				  		   			layeredPane.add(dateNaissanceChooser);
				  		   			
				  		   			JLabel lblDateNaissance = new JLabel("Date Naissance :");
				  		   			lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateNaissance.setBounds(313, 109, 130, 26);
				  		   			layeredPane.add(lblDateNaissance);
				  		   			
				  		   			
				  		   			comboTypeEquipe.setBounds(452, 3, 188, 26);
				  		   			layeredPane.add(comboTypeEquipe);
				  		   			
				  		   			JLabel lblTypeEquipe = new JLabel("Type Equipe:");
				  		   			lblTypeEquipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblTypeEquipe.setBounds(354, 3, 130, 26);
				  		   			layeredPane.add(lblTypeEquipe);
				  		   			
				  		   			txtNumDoss = new JTextField();
				  		   			txtNumDoss.setCaretColor(Color.RED);
				  		   			txtNumDoss.setBackground(Color.WHITE);
				  		   			txtNumDoss.setEditable(false);
				  		   			txtNumDoss.setColumns(10);
				  		   			txtNumDoss.setBounds(410, 82, 181, 26);
				  		   			layeredPane.add(txtNumDoss);
				  		   			
				  		   			JLabel lblNDossier = new JLabel("N\u00B0 Dossier : ");
				  		   			lblNDossier.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier.setBounds(313, 82, 114, 26);
				  		   			layeredPane.add(lblNDossier);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 999, 55);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherMachine = new JButton("Chercher");
				  		   			btnChercherMachine.setBounds(854, 15, 135, 26);
				  		   			layeredPane_1.add(btnChercherMachine);
				  		   			btnChercherMachine.setIcon(imgChercher);
				  		   			
				  		   			 comboEquipe = new JComboBox();
				  		   			 comboEquipe.addItem("");
				  		   			 listEquipe = equipeDAO.findAll();	
					  		     	
					  		      int i=0;
					  		      	while(i<listEquipe.size())
					  		      		{	
					  		      			Equipe equipe=listEquipe.get(i);
					  		      			mapLibelle.put(equipe.getNomEquipe(), equipe.getCodeEquipe());
					  		      			mapCode.put(equipe.getCodeEquipe(),equipe.getNomEquipe());
					  		      			comboEquipe.addItem(equipe.getNomEquipe());
					  		      			i++;
					  		      		}
					  		       i=0;
					  		      	while(i<listTypeResEmploye.size())
					  		      		{	
					  		      			TypeResEmploye typeResEmploye=listTypeResEmploye.get(i);
					  		      			mapTypeEmploye.put(typeResEmploye.getLibelle(),typeResEmploye);
					  		      			comboRespon.addItem(typeResEmploye.getLibelle());
					  		      			i++;
					  		      		}
					  		      i=0;
					  		      	while(i<listTypeEquipe.size())
					  		      		{	
					  		      		TypeEquipe typeEquipe=listTypeEquipe.get(i);
					  		      		mapTypeEquipe.put(typeEquipe.getLibelle(),typeEquipe);
					  		      		comboTypeEquipe.addItem(typeEquipe.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      	comboEquipe.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	code.setText(mapLibelle.get(comboEquipe.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
					  		      
					  		    code.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboEquipe.setSelectedItem(mapCode.get(code.getText()));
								  		}}});
							
							
							AutoCompleteDecorator.decorate(comboEquipe);
				  		   			comboEquipe.setBounds(389, 15, 188, 26);
				  		   			layeredPane_1.add(comboEquipe);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			comboDepot.setBounds(653, 15, 191, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			
				  		   		int k=0;
				  		      
				  		      	if(utilisateur.getNom().equals("admin"))
				  		      	{
				  		      		
				  		      		listDepot=depotDAO.findAll();
				  		      		
				  		      		while(k<listDepot.size())
				  		      		{
				  		      			
				  		      			Depot depot=listDepot.get(k);
				  		      			mapDepot.put(depot.getLibelle(), depot);
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      			k++;
				  		      		}
				  		      		
				  		      		
				  		      	}else
				  		      	{
				  		      		
				  		      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
				  		      		
				  		      		if(depot!=null)
				  		      		{
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      	    	mapDepot.put(depot.getLibelle(), depot);
				  		      		}
				  		      	}
				  				
				  		   			
				  		   			
				  		   			JLabel label_2 = new JLabel("Depot :");
				  		   			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_2.setBounds(600, 14, 64, 26);
				  		   			layeredPane_1.add(label_2);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Libelle Equipe:");
				  		   			lblLibelle.setBounds(308, 14, 84, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			btnChercherMachine.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(code.getText().equals("") && comboEquipe.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critère de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			 
		  		     			  Depot depot=mapDepot.get(comboDepot.getSelectedItem());
		  		     			
		  		     			  equipe = equipeDAO.findByCodeNomDepot(code.getText(),(String) comboEquipe.getSelectedItem(),depot.getId());
		  		     			  
		  		     			  if(equipe!=null){
		  		     			 
		  		     				  listEmploye=equipe.getListEmploye();
		  		     				  txtCodeEquipe.setText(equipe.getCodeEquipe());
		  		     				  txtLibelleEquipe.setText(equipe.getNomEquipe());
		  		     				  comboTypeEquipe.setSelectedItem(equipe.getTypeEquipe().getLibelle());
		  		     				  afficherList(listEmploye);
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun résultat pour ces critères de recherche. Merci de vérifier votre critère !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     				intialiserTableau();
		  		     				intialiser();
		  		     			  }
		  		     		 }
				  		   				}
				  		   			});
				  		   			
				  		   			
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {

				  		     		if(CodeLigne.getText().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else {
				  		     			EmployeBloque employeBloque =employeBloqueDAO.findByCode(CodeLigne.getText());
				  		     			
				  		     			if(employeBloque!=null){
				  		     				
				  		     				JOptionPane.showMessageDialog(null, "Cet empployé est bloqué par le système!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     			}else {
				  		     				if (NomLigne.getText().equals(""))
						  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		     		else if (comboRespon.getSelectedItem().equals(""))
						  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir la Responsabilité!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		     		else  {
						  		     	Employe employe = new Employe();
                                        Depot depot=mapDepot.get(comboDepot.getSelectedItem());
						  		     	employe.setActif(true);
						  		     	employe.setAdresse(txtAdresse.getText());
				  		     			employe.setBlocageEmploye(Constantes.CODE_NON);
				  		     			employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
				  		     			employe.setDateCreation(new Date());
				  		     			employe.setMatricule(CodeLigne.getText());
				  		     			employe.setNom(NomLigne.getText());
				  		     			employe.setNumTel(txtTel.getText());
				  		     			employe.setResponsabilite(comboRespon.getSelectedItem().toString());
				  		     			employe.setTypeResEmploye(mapTypeEmploye.get(comboRespon.getSelectedItem()));
				  		     			employe.setNumDossier(txtNumDoss.getText());
				  		     			employe.setUtilCreation(AuthentificationView.utilisateur);
				  		     			employe.setDateCreation(new Date());
				  		     			employe.setDateNaissance(dateNaissanceChooser.getDate());
						  		     	employe.setEquipe(equipe);
						  		     	employe.setDateEntre(dateEntreChooser.getDate());
						  		     	employe.setNumCNSS(txtNumCNSS.getText());
						  		     	employe.setLieuNaissance(txtLieuNaiss.getText());
						  		     	employe.setService(SERVICE_EMPLOYE);
						  		     	employe.setDepot(depot);
						  		     	listEmploye.add(employe);
						  		     	/*manque champs*/
						  		     	afficherList(listEmploye);
						  		     	intialiser ();
						  		     	txtNumDoss.setText(Utils.genererNumDossierEmploye());
				  		     			}
				  		     			
				  		     		}
				  		     			
				  		     	
				  		     		 }
				  		     	
				  		    
				  		     	
				  		     	
				  		     	}
				  		     });
				  		 
	txtNumDoss.setText(Utils.retournerNumDossierEmploye());		  		
	
	 dateEntreChooser = new JDateChooser();
	dateEntreChooser.setDateFormatString("dd/MM/yyyy");
	dateEntreChooser.setBounds(409, 167, 181, 26);
	layeredPane.add(dateEntreChooser);
	
	JLabel lblDateEntre = new JLabel("Date Entr\u00E9e:");
	lblDateEntre.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblDateEntre.setBounds(313, 167, 130, 26);
	layeredPane.add(lblDateEntre);
	
	txtNumCNSS = new JTextField();
	 util.Utils.copycoller(txtNumCNSS);
	txtNumCNSS.setColumns(10);
	txtNumCNSS.setBounds(92, 168, 191, 26);
	layeredPane.add(txtNumCNSS);
	
	JLabel lblNCnss = new JLabel("N\u00B0 CNSS :");
	lblNCnss.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblNCnss.setBounds(8, 167, 130, 26);
	layeredPane.add(lblNCnss);
	
	txtLieuNaiss = new JTextField();
	 util.Utils.copycoller(txtLieuNaiss);
	txtLieuNaiss.setColumns(10);
	txtLieuNaiss.setBounds(92, 139, 191, 26);
	layeredPane.add(txtLieuNaiss);
	
	JLabel lblServic = new JLabel("Lieu Naissance");
	lblServic.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblServic.setBounds(8, 138, 130, 26);
	layeredPane.add(lblServic);
				  		 
	}
	
	
	
	void afficherList(List<Employe> listEmploye)
	{

		intialiserTableau ();
		  int i=0;
			while(i<listEmploye.size())
			{	
				String dateNaissance="";
				String dateEntre="";
				Employe employe=listEmploye.get(i);
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  				Date dateNaiss = employe.getDateNaissance();	
  				Date dateEnt = employe.getDateEntre();
  				if(dateNaiss!=null)
  				 dateNaissance=dateFormat.format(dateNaiss);
  				if(dateEnt!=null)
  				 dateEntre=dateFormat.format(dateEnt);
				Object []ligne={employe.getId(),employe.getMatricule(),employe.getNom(),employe.getNumDossier(),employe.getResponsabilite(),dateNaissance,dateEntre,employe.getNumTel()};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}
	
void intialiser (){
		
		NomLigne.setText("");
		CodeLigne.setText("");
		txtCoutHoraire.setText("");
		comboRespon.setSelectedItem("");
		txtAdresse.setText("");
		txtTel.setText("");
		dateNaissanceChooser.setDate(null);
		dateEntreChooser.setDate(null);
		txtNumCNSS.setText("");
		txtLieuNaiss.setText("");
		
		
	}
	
void intialiserAll (){
		
	intialiser();
	txtCodeEquipe.setText("");
	txtLibelleEquipe.setText("");
	comboEquipe.setSelectedItem("");
	code.setText("");
	listEmploye= new ArrayList<Employe>();
	afficherList(listEmploye);
	
		
	}
void intialiserTableau (){
	
	modeleLigneMachine =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Id","Matricule","Nom","N° Dossier","Responsablité","Date Naissance","Date Entrée","N° TEL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     table.setModel(modeleLigneMachine); 
		     table.getColumnModel().getColumn(0).setPreferredWidth(10);
	         table.getColumnModel().getColumn(1).setPreferredWidth(60);
	         table.getColumnModel().getColumn(2).setPreferredWidth(160);
	         table.getColumnModel().getColumn(3).setPreferredWidth(60);
	         table.getColumnModel().getColumn(4).setPreferredWidth(90);
	         table.getColumnModel().getColumn(5).setPreferredWidth(90);
	         table.getColumnModel().getColumn(6).setPreferredWidth(90);
	        table.getColumnModel().getColumn(7).setPreferredWidth(90);
	         
	
}
}

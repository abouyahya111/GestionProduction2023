package Equipe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
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

import javax.swing.JCheckBox;


public class AjoutEquipe extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JButton btnInitialiser;

	private JComboBox  comboRespon = new JComboBox() ;
	private JComboBox comboTypeEquipe = new JComboBox();
	private JCheckBox checkSalarie ;
	private Utilisateur utilisateur;
	
	private JTextField code;
	private JTextField nom;
	private JTextField txtMatricule;
	private JTextField txtNom;
	private JTextField txtNumTel;
	private JTextField txtAdresse;
	private JTextField txtNumDoss;
	private JTextField txtNumCNSS;
	private JTextField txtLieuNaiss;
	private JTextField txtService;
	
	private JDateChooser dateNaissanceChooser;
	private JDateChooser dateEntreChooser ;
	private int sequence;
	private JComboBox comboDepot = new JComboBox();
	
	private Map< String, TypeEquipe> mapTypeEquipe = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private Map< String, TypeResEmploye> mapTypeEmploye = new HashMap<>();
	private List<TypeResEmploye> listTypeResEmploye = new ArrayList<TypeResEmploye>();
	private List<TypeEquipe> listTypeEquipe = new ArrayList<TypeEquipe>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Equipe equipe=new Equipe();
	
	
	private EquipeDAO equipeDAO;
	private TypeResEmployeDAO typeResEmployeDAO;
	private TypeEquipeDAO typeEquipeDAO;
	private  EmployeBloqueDAO employeBloqueDAO;
	private DepotDAO depotDAO ;
	
	
	/**
	 * Launch the application.k
	 */


	/**
	 * Create the application.
	 */
	public AjoutEquipe() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 618);
        try{
        	
        		equipeDAO=new EquipeDAOImpl();
        		typeResEmployeDAO=new TypeResEmployeDAOImpl();
        		typeEquipeDAO=new TypeEquipeDAOImpl();
        		employeBloqueDAO=new EmployeBloqueDAOImpl();
        		depotDAO=new DepotDAOImpl();
        		
        		listTypeResEmploye = typeResEmployeDAO.findAll();	
        		listTypeEquipe=typeEquipeDAO.findAll();
        		
        	
        		utilisateur=AuthentificationView.utilisateur;
        	 
        	 comboTypeEquipe.addItem("");
        	 comboRespon.addItem("");

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        checkSalarie = new JCheckBox("");
		 	
        mapParametre=Utils.listeParametre();
        final Depot depot=depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());        
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
             
          } catch (Exception exp){exp.printStackTrace();}
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	intialiserAll();
				  		  		
				  		  	}
				  		  });
				  		intialiserTableau ();
				  		
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(452, 567, 112, 26);
				  		 add(btnInitialiser);
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(7, 65, 863, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Employ\u00E9 ");
				  		   
				  		   JButton btnValiderAjoutMachine = new JButton("Valider Ajout");
				  		   btnValiderAjoutMachine.setIcon(imgValider);
				  		   
				  		   btnValiderAjoutMachine.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	if(code.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		else if (nom.getText().equals(""))
		  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     //	Machine machine = new Machine();
		  		     			TypeEquipe typeEquipe = new TypeEquipe();
		  		     			typeEquipe=mapTypeEquipe.get(comboTypeEquipe.getSelectedItem());
		  		     			equipe.setNomEquipe(nom.getText());
		  		     			equipe.setCodeEquipe(code.getText());
		  		     			equipe.setTypeEquipe(typeEquipe);
		  		     			equipe.setListEmploye(listEmploye);
		  		     			equipe.setDepot(depot);
		  		     			typeEquipe.setSequence(sequence);
		  		     			equipeDAO.add(equipe);
		  		     			typeEquipeDAO.edit(typeEquipe);
		  		     	JOptionPane.showMessageDialog(null, "L'Equipe a été ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		     	intialiser ();
		  		     		 }
				  		   		
				  		   	}
				  		   });
				  		   btnValiderAjoutMachine.setBounds(291, 567, 148, 26);
				  		   add(btnValiderAjoutMachine);
				  		   
				  		   JLayeredPane layeredPane = new JLayeredPane();
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(7, 78, 863, 478);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(10, 24, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(100, 24, 236, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(796, 257, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   txtMatricule = new JTextField();
				  		 util.Utils.copycoller(txtMatricule);
				  		   txtMatricule.setBounds(100, 61, 236, 26);
				  		   layeredPane.add(txtMatricule);
				  		   txtMatricule.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Matricule : ");
				  		   lblCodeLigne.setBounds(10, 61, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
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
								  
									listEmploye.remove(row);
								   afficherList(listEmploye);
			                        table.setRowSorter(null);
								     modele.removeRow(row);
										}
								   }
					                }catch (Exception e1){
					                	}
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnSupprimerLigne.setBounds(796, 286, 60, 26);
				  		   layeredPane.add(btnSupprimerLigne);
				  		   
				  		   JLabel lblResponsabilite = new JLabel("Responsabilti\u00E9 :");
				  		   lblResponsabilite.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblResponsabilite.setBounds(10, 130, 114, 26);
				  		   layeredPane.add(lblResponsabilite);
				  		   
				  		  
				  		   comboRespon.setBounds(100, 130, 236, 26);
				  		   layeredPane.add(comboRespon);
				  		   
				  		
				  		   int i=0;
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
			  		      comboTypeEquipe.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 		code.setText(creerCodeEquipe());
			  		     	 		}
				  		     	 });
				  		   
				  		   		table = new JXTable();
				  		   		table.setSelectionBackground(new Color(51, 204, 255));
				  		   		table.setRowHeightEnabled(true);
				  		   		table.setBackground(new Color(255, 255, 255));
				  		   		table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		   		table.setColumnControlVisible(true);
				  		   		table.setForeground(Color.BLACK);
				  		   		table.setGridColor(new Color(0, 0, 255));
				  		   		table.setAutoCreateRowSorter(true);
				  		   		table.setModel(modeleLigneMachine);
				  		   		table.setBounds(2, 27, 411, 198);
				  		   		table.setRowHeight(20);
				  		   			JScrollPane scrollPane = new JScrollPane(table);
				  		   			scrollPane.setBounds(10, 240, 776, 227);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			txtNumTel = new JTextField();
				  		   		util.Utils.copycoller(txtNumTel);
				  		   			txtNumTel.setColumns(10);
				  		   			txtNumTel.setBounds(473, 160, 236, 26);
				  		   			layeredPane.add(txtNumTel);
				  		   			
				  		   			JLabel label = new JLabel("N\u00B0 Tel :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(378, 159, 130, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(474, 127, 380, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			JLabel label_2 = new JLabel("Adresse:");
				  		   			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_2.setBounds(378, 127, 130, 26);
				  		   			layeredPane.add(label_2);
				  		   			
				  		   			JLabel lblDateNaissance = new JLabel("Date Naissance :");
				  		   			lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateNaissance.setBounds(378, 60, 130, 26);
				  		   			layeredPane.add(lblDateNaissance);
				  		   			
				  		   			 dateNaissanceChooser = new JDateChooser();
				  		   			 dateNaissanceChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateNaissanceChooser.setBounds(474, 60, 191, 26);
				  		   			layeredPane.add(dateNaissanceChooser);
				  		   			
				  		   			JLabel lblNDossier = new JLabel("N\u00B0 Dossier  :");
				  		   			lblNDossier.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier.setBounds(378, 24, 130, 26);
				  		   			layeredPane.add(lblNDossier);
				  		   			
				  		   			txtNumDoss = new JTextField();
				  		   			txtNumDoss.setForeground(Color.RED);
				  		   			txtNumDoss.setBackground(Color.WHITE);
				  		   			txtNumDoss.setEditable(false);
				  		   			txtNumDoss.setColumns(10);
				  		   			txtNumDoss.setBounds(474, 27, 191, 26);
				  		   			layeredPane.add(txtNumDoss);
				  		   
				  		   JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   layeredPane_1.setBounds(6, 11, 864, 56);
				  		   add(layeredPane_1);
				  		   
				  		   JLabel lblCode = new JLabel("Code : ");
				  		   lblCode.setBounds(265, 14, 54, 26);
				  		   layeredPane_1.add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   nom = new JTextField();
				  		 util.Utils.copycoller(nom);
				  		   nom.setBounds(566, 15, 191, 26);
				  		   layeredPane_1.add(nom);
				  		   nom.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle :");
				  		   lblLibelle.setBounds(513, 14, 63, 26);
				  		   layeredPane_1.add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   code = new JTextField();
				  		 util.Utils.copycoller(code);
				  		   code.setBackground(Color.WHITE);
				  		   code.setEditable(false);
				  		   code.setBounds(312, 15, 191, 26);
				  		   layeredPane_1.add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblTypeEquipe = new JLabel("Type Equipe");
				  		   lblTypeEquipe.setBounds(10, 16, 76, 24);
				  		   layeredPane_1.add(lblTypeEquipe);
				  		 
				  		   
				  		
				  		   comboTypeEquipe.setBounds(78, 16, 147, 24);
				  		   layeredPane_1.add(comboTypeEquipe);
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		if(txtMatricule.getText().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboDepot.getSelectedItem().equals(""))
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le depot!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     		else {
				  		     			EmployeBloque employeBloque =employeBloqueDAO.findByCode(txtMatricule.getText());
				  		     			
				  		     			if(employeBloque!=null){
				  		     				
				  		     				JOptionPane.showMessageDialog(null, "Cet empployé est bloqué par le système!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     			}else {
				  		     				if (txtNom.getText().equals(""))
						  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		     		else if (comboRespon.getSelectedItem().equals(""))
						  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir la Responsabilité!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		     		 else {
						  		     			 
						  		     			Employe employe = new Employe();
								  		     	employe.setDateEntre(dateEntreChooser.getDate());
								  		     	employe.setService(SERVICE_EMPLOYE);
								  		     	employe.setNumCNSS(txtNumCNSS.getText());
								  		     	employe.setActif(true);
								  		     	employe.setAdresse(txtAdresse.getText());
						  		     			employe.setBlocageEmploye(Constantes.CODE_NON);
						  		     			
						  		     			employe.setDateCreation(new Date());
						  		     			employe.setMatricule(txtMatricule.getText());
						  		     			employe.setNom(txtNom.getText());
						  		     			employe.setNumTel(txtNumTel.getText());
						  		     			employe.setResponsabilite(comboRespon.getSelectedItem().toString());
						  		     			employe.setTypeResEmploye(mapTypeEmploye.get(comboRespon.getSelectedItem()));
						  		     			employe.setNumDossier(txtNumDoss.getText());
						  		     			employe.setUtilCreation(AuthentificationView.utilisateur);
						  		     			employe.setDateCreation(new Date());
						  		     			employe.setDateNaissance(dateNaissanceChooser.getDate());
								  		     	employe.setEquipe(equipe);
								  		     	employe.setLieuNaissance(txtLieuNaiss.getText());
								  		     	employe.setSalarie(checkSalarie.isSelected());
								  		    	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
								  		    	employe.setDepot(depot);
								  		     	
								  		     	if(checkSalarie.isSelected()){
								  		     		employe.setCoutHoraire(BigDecimal.ZERO);
								  		     	}else{
								  		     		employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
								  		     	}
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
		
		txtNumCNSS = new JTextField();
		 util.Utils.copycoller(txtNumCNSS);
		txtNumCNSS.setColumns(10);
		txtNumCNSS.setBounds(100, 160, 236, 26);
		layeredPane.add(txtNumCNSS);
		
		JLabel lblNCnss = new JLabel("N\u00B0 CNSS :");
		lblNCnss.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNCnss.setBounds(10, 159, 130, 26);
		layeredPane.add(lblNCnss);
		
		 dateEntreChooser = new JDateChooser();
		dateEntreChooser.setDateFormatString("dd/MM/yyyy");
		dateEntreChooser.setBounds(474, 97, 191, 26);
		layeredPane.add(dateEntreChooser);
		
		JLabel lblDateEntr = new JLabel("Date Entr\u00E9 :");
		lblDateEntr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateEntr.setBounds(378, 97, 130, 26);
		layeredPane.add(lblDateEntr);
		
		txtLieuNaiss = new JTextField();
		 util.Utils.copycoller(txtLieuNaiss);
		txtLieuNaiss.setColumns(10);
		txtLieuNaiss.setBounds(99, 94, 236, 26);
		layeredPane.add(txtLieuNaiss);
		
		JLabel lblLieuNaissance = new JLabel("Lieu Naissance:");
		lblLieuNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLieuNaissance.setBounds(9, 94, 130, 26);
		layeredPane.add(lblLieuNaissance);
		
		txtService = new JTextField();
		util.Utils.copycoller(txtService);
		txtService.setVisible(false);
		txtService.setColumns(10);
		txtService.setBounds(473, 193, 236, 26);
		layeredPane.add(txtService);
		
		JLabel lblService = new JLabel("Service:");
		lblService.setVisible(false);
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblService.setBounds(373, 191, 130, 26);
		layeredPane.add(lblService);
		
		
		checkSalarie.setBounds(826, 32, 18, 18);
		layeredPane.add(checkSalarie);
		
		JLabel lblSalari = new JLabel("Salari\u00E9  :");
		lblSalari.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSalari.setBounds(748, 32, 96, 18);
		layeredPane.add(lblSalari);
		
		JLabel label_1 = new JLabel("Depot :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 196, 80, 26);
		layeredPane.add(label_1);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(100, 197, 236, 26);
		layeredPane.add(comboDepot);
				  		 
		
		int k=0;
	      
      	if(utilisateur.getNom().equals("admin"))
      	{
      		
      		listDepot=depotDAO.findAll();
      		
      		while(k<listDepot.size())
      		{
      			
      			Depot depottmp=listDepot.get(k);
      			mapDepot.put(depottmp.getLibelle(), depottmp);
      			comboDepot.addItem(depottmp.getLibelle());
      			k++;
      		}
      		
      		
      	}else
      	{
      		
      	
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      			mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
	
		
		
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
				
				Object []ligne={employe.getMatricule(),employe.getNom(),employe.getNumDossier(),employe.getResponsabilite(),dateNaissance,dateEntre,employe.getNumTel()};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}
	void intialiser (){
		
		txtNom.setText("");
		txtMatricule.setText("");
		comboRespon.setSelectedItem("");
		txtAdresse.setText("");
		txtNumTel.setText("");
		dateNaissanceChooser.setDate(null);
		dateEntreChooser.setDate(null);
		txtNumCNSS.setText("");
		txtLieuNaiss.setText("");
		comboDepot.setSelectedIndex(-1);
		
		
		
	}
	
void intialiserAll (){
		
	nom.setText("");
	code.setText("");
	comboTypeEquipe.setSelectedItem("");
	listEmploye= new ArrayList<Employe>();
	afficherList(listEmploye);
	intialiser ();
	intialiserTableau();
	
		
	}
void intialiserTableau (){
	
	modeleLigneMachine =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matricule","Nom","N° Dossier","Responsablité","Date Naissance","Date Entré","N° TEL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
	
	
}
String creerCodeEquipe(){
	String codeEquipe="";
	TypeEquipe typeEquipe = new TypeEquipe();
	typeEquipe=mapTypeEquipe.get(comboTypeEquipe.getSelectedItem());
	sequence =typeEquipe.getSequence();
	
	codeEquipe=typeEquipe.getCode()+"_"+sequence;
	sequence=sequence+1;
	 return codeEquipe;
	
}
}

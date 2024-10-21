package presentation.client;

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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import util.Constantes;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.Utilisateur;


public class ChercherClient extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private ImageIcon imgModifier;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	
	private  ClientDAO clientDAO;
	
	
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField txtcodeClient;
	
	private Map< String, Client> mapClient = new HashMap<>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	
	private Map< String, CategorieMp> mapCategorieMp = new HashMap<>();
	private List < Client> listeClient = new ArrayList<Client>();
	
	private JComboBox comboClient = new JComboBox();
	private JComboBox comboDepot = new JComboBox();
	private JComboBox comboTypeClient = new JComboBox();

	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNumTel;
	
	private Client client = new Client();
	private DepotDAO depotDAO;

	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherClient() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	comboTypeClient = new JComboBox();
        	clientDAO= new ClientDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	
        	

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        comboClient.addItem("");
        //comboDepot.addItem("");
       final Utilisateur utilisateurMAJ=AuthentificationView.utilisateur;
       Depot depot = depotDAO.findByCode(utilisateurMAJ.getCodeDepot());
       
       comboDepot.addItem(depot.getLibelle());
        
        listeClient = clientDAO.findAll();	
	     	
	      int i=0;
	      	while(i<listeClient.size())
	      		{	
	      			Client client=listeClient.get(i);
	      			mapClient.put(client.getNom(), client);
	      			mapLibelle.put(client.getNom(), client.getCode());
		      		mapCode.put(client.getCode(),client.getNom());
	      			
	      			comboClient.addItem(client.getNom());
	      			i++;
	      		}
	      	
	     comboTypeClient.addItem("");
	     comboTypeClient.addItem(CLIENT_CLIENT_INTERNE);
	     comboTypeClient.addItem(CLIENT_LIBELLE_EXTERNE);
	      
        
				  		  btnModifier = new JButton("Modifier ");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(client.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher le client ‡ modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  		
				  		  			client.setNom(txtNom.getText());
				  		  			client.setDateModification(new Date());
				  		  			client.setEmail(txtEmail.getText());
				  		  			client.setNumTel(txtNumTel.getText());
				  		  			client.setTypeClient(comboTypeClient.getSelectedItem().toString());
				  		  			client.setUtilisateurMAJ(utilisateurMAJ);
				  		  		
				  		  			clientDAO.edit(client);
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Le client a ÈtÈ modifiÈe avec succËs!", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(272, 310, 126, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(407, 310, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Client :");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 105, 903, 194);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(20, 29, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(113, 30, 191, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		   txtCode.setBackground(Color.WHITE);
				  		   txtCode.setDisabledTextColor(Color.BLACK);
				  		   txtCode.setEditable(false);
				  		   txtCode.setBounds(551, 30, 191, 26);
				  		   layeredPane.add(txtCode);
				  		   txtCode.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code :");
				  		   lblCodeLigne.setBounds(452, 29, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		  
				  		   			JLabel lblCatgorie = new JLabel("D\u00E9pot  :");
				  		   			lblCatgorie.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblCatgorie.setBounds(20, 139, 114, 26);
				  		   			layeredPane.add(lblCatgorie);
				  		   			
				  		   			 
				  		   			comboDepot.setBounds(113, 140, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(113, 66, 428, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			txtEmail = new JTextField();
				  		   		 util.Utils.copycoller(txtEmail);
				  		   			txtEmail.setColumns(10);
				  		   			txtEmail.setBounds(113, 103, 191, 26);
				  		   			layeredPane.add(txtEmail);
				  		   			
				  		   			txtNumTel = new JTextField();
				  		   		 util.Utils.copycoller(txtNumTel);
				  		   			txtNumTel.setColumns(10);
				  		   			txtNumTel.setBounds(551, 103, 191, 26);
				  		   			layeredPane.add(txtNumTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse :");
				  		   			lblAdresse.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblAdresse.setBounds(20, 66, 114, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			JLabel lblEmail = new JLabel("Email  :");
				  		   			lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblEmail.setBounds(20, 103, 114, 26);
				  		   			layeredPane.add(lblEmail);
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 TEL  :");
				  		   			lblNTel.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblNTel.setBounds(452, 103, 114, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   		
				  		   			comboTypeClient.setBounds(551, 143, 191, 26);
				  		   			layeredPane.add(comboTypeClient);
				  		   			
				  		   			JLabel lblTypeClient = new JLabel("Type Client  :");
				  		   			lblTypeClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblTypeClient.setBounds(452, 142, 114, 26);
				  		   			layeredPane.add(lblTypeClient);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(775, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   		
					  		      comboClient.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	txtcodeClient.setText(mapLibelle.get(comboClient.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
							AutoCompleteDecorator.decorate(comboClient);
				  		   			comboClient.setBounds(471, 15, 263, 26);
				  		   			layeredPane_1.add(comboClient);
				  		   			
				  		   			txtcodeClient = new JTextField();
				  		   		util.Utils.copycoller(txtcodeClient);
				  		   			txtcodeClient.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(txtcodeClient);
				  		   			txtcodeClient.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom :");
				  		   			lblLibelle.setBounds(419, 14, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel lblCode = new JLabel("Code :");
				  		   			lblCode.setBounds(60, 14, 114, 26);
				  		   			layeredPane_1.add(lblCode);
				  		   			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
					  		    txtcodeClient.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboClient.setSelectedItem(mapCode.get(txtcodeClient.getText()));
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(txtcodeClient.getText().equals("") && comboClient.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critËre de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			client = clientDAO.findClientByCodeClient(txtcodeClient.getText());
		  		     			  
		  		     			  if(txtcodeClient!=null){
		  		     				  
		  		     				 Depot depot = depotDAO.findByCode(client.getCodeDepot());
		  		     			 
		  		     			txtCode.setText(client.getCode());
		  		     			txtNom.setText(client.getNom());
		  		     			comboDepot.setSelectedItem(depot.getLibelle());
		  		     			txtAdresse.setText(client.getAdresse());
		  		     			txtEmail.setText(client.getEmail());
		  		     			comboTypeClient.setSelectedItem(client.getTypeClient());
		  		     			txtNumTel.setText(client.getNumTel());
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun rÈsultat pour ces critËres de recherche. Merci de vÈrifier votre critËre !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		
				  		 
	}
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtNom.setText("");
		comboDepot.setSelectedItem("");
		txtAdresse.setText("");
		txtEmail.setText("");
		comboTypeClient.setSelectedItem("");
		txtNumTel.setText("");
		
	}
}

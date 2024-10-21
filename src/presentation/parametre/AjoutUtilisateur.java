package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;

import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Depot;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;


public class AjoutUtilisateur extends JLayeredPane {
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  UtilisateurDAO utilisateurDAO;
	private DepotDAO depotDAO;
	
	private JTextField txtNom;
	private JTextField txtLogin;
	private JComboBox comboDepot = new JComboBox();
	private Map< String, String> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JPasswordField txtMdp;
	private JPasswordField txtConfirmMdP;
	private JLabel lblConfirmerMotDe;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutUtilisateur() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 565);
        try{
        	
        	utilisateurDAO= new UtilisateurDAOImpl();
        	depotDAO= new DepotDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot.getCode());
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
       
	     
        
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		

				  		   		
					  		   	if(txtNom.getText().equals(""))
			  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		else if (txtLogin.getText().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le login!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		else if (txtMdp.getText().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le mot de passe!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		else if (comboDepot.getSelectedItem().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut choisir un dÈpot!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		else {
			  		     			if (!txtMdp.getText().equals(txtConfirmMdP.getText())){
			  		     				JOptionPane.showMessageDialog(null, "Les mots ne sont pas identiques!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			}else{

						  		  		Utilisateur utilisateur = new Utilisateur();
						  		  		utilisateur.setNom(txtNom.getText());
						  		  		utilisateur.setLogin(txtLogin.getText());
						  		  		utilisateur.setPassword(txtMdp.getText());
						  		  		utilisateur.setDateCreation(new Date());
						  		  		utilisateur.setCodeDepot(mapDepot.get(comboDepot.getSelectedItem()));
						  		  		utilisateurDAO.add(utilisateur);
						  		  		
						  		  		JOptionPane.showMessageDialog(null, "L'utilisateur a ÈtÈ ajoutÈ avec succËs!", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
						  		  		Utils.genererMenuUtilisateur();
						  		  		intialiser ();
			  		     			}
			  		     		 }
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(140, 250, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(264, 250, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(29, 11, 566, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Nouveau utilisateur");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(30, 23, 565, 216);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblLogin = new JLabel("Login : ");
				  		   lblLogin.setBounds(9, 62, 130, 26);
				  		   layeredPane.add(lblLogin);
				  		   lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtLogin = new JTextField();
				  		 util.Utils.copycoller(txtLogin);
				  		   txtLogin.setBounds(152, 63, 191, 26);
				  		   layeredPane.add(txtLogin);
				  		   txtLogin.setColumns(10);
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(152, 26, 191, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   
				  		   JLabel lblNomUtilisateur = new JLabel("Nom Utilisateur :");
				  		   lblNomUtilisateur.setBounds(7, 25, 114, 26);
				  		   layeredPane.add(lblNomUtilisateur);
				  		   lblNomUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Mot de passe :");
				  		   			lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblMdp.setBounds(9, 99, 130, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			
				  		   			comboDepot.setBounds(152, 173, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			JLabel lblDpot = new JLabel("D\u00E9pot :");
				  		   			lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDpot.setBounds(9, 172, 130, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			txtMdp = new JPasswordField();
				  		   		util.Utils.copycoller(txtMdp);
				  		   			txtMdp.setBounds(152, 100, 191, 26);
				  		   			layeredPane.add(txtMdp);
				  		   			
				  		   			txtConfirmMdP = new JPasswordField();
				  		   		util.Utils.copycoller(txtConfirmMdP);
				  		   			txtConfirmMdP.setBounds(152, 137, 191, 26);
				  		   			layeredPane.add(txtConfirmMdP);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Confirmer Mot de passe :");
				  		   			lblConfirmerMotDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblConfirmerMotDe.setBounds(9, 136, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			JButton btnGnrerMenu = new JButton("G\u00E9n\u00E9rer Menu");
				  		   			btnGnrerMenu.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   				Utils.genererMenuUtilisateur();
				  		   				JOptionPane.showMessageDialog(null, "Menu Genere Avec SuccÈe");
				  		   				}
				  		   			});
				  		   			btnGnrerMenu.setBounds(397, 252, 114, 23);
				  		   			add(btnGnrerMenu);
	}

	
void intialiser (){
		
		txtNom.setText("");
		txtLogin.setText("");
		txtMdp.setText("");
		
	}
}

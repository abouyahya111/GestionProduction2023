/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import main.java.ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import util.Constantes;
import ch.swingfx.twinkle.NotificationBuilder;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.UtilisateurDAO;

import dao.entity.Utilisateur;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author SDRIDI(16/05/2014)
 * 
 */


@SuppressWarnings("serial")
public class AuthentificationView extends javax.swing.JFrame {
	
	private ImageIcon imgBest;
	public static String login;
	

	
	public static Utilisateur utilisateur;
	public UtilisateurDAO utiliseurDAO;
	
	

	public AuthentificationView() {
		try {
				Properties props = new Properties();
			  props.put("logoString", "------");
			  McWinLookAndFeel.setCurrentTheme(props);
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			/*
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		*/
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		initComponents();
		// setSize(getToolkit().getScreenSize());
		setLocationRelativeTo(null);
		connect.setEnabled(true);
		panelAuthentification.setLayout(null);
		panelAuthentification.add(panelConnexion);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 445, 475);
		lblNewLabel.setIcon(imgBest);
		panelAuthentification.add(lblNewLabel);
		nbrAttempt = 0;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		utiliseurDAO=new UtilisateurDAOImpl();
		panelAuthentification = new javax.swing.JPanel();
		panelConnexion = new javax.swing.JPanel();
		panelConnexion.setBounds(0, 0, 867, 487);
		connexionLabel = new javax.swing.JLabel();
		connexionLabel.setForeground(Color.WHITE);
		orLabel = new javax.swing.JLabel();
		createAccount = new javax.swing.JLabel();
		userLabel = new javax.swing.JLabel();
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		userLabel.setForeground(Color.BLACK);
		userName = new javax.swing.JTextField();
		util.Utils.copycoller(userName);
		passwordLabel = new javax.swing.JLabel();
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordLabel.setForeground(Color.BLACK);
		password = new javax.swing.JPasswordField();
		util.Utils.copycoller(password);
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				
				if(evt.getKeyCode()==evt.VK_ENTER)
				{
					
					try {
						connectkeypressed(evt);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
					
					
				}
				
			}
		});
		connect = new javax.swing.JButton();
		connect.setFont(new Font("Tahoma", Font.BOLD, 16));
		userNameControl = new javax.swing.JLabel();
		passwordControl = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bienvenue dans Application de Gestion Production 2023 V1.0"); // NOI18N
		setResizable(false);

		panelAuthentification.setBackground(new java.awt.Color(255, 255, 255));
		panelAuthentification.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
		panelAuthentification.setMaximumSize(getToolkit().getScreenSize());
		panelAuthentification.setName("");
	

		panelConnexion.setBackground(new java.awt.Color(204, 204, 204));
		panelConnexion.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		panelConnexion.setOpaque(false);

		connexionLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		connexionLabel.setText("Connexion");

		//orLabel.setText("ou");

		createAccount.setForeground(new java.awt.Color(0, 153, 204));
		/*createAccount.setText(" Créer un compte");
		createAccount.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				createAccountMouseClicked(evt);
			}
		});*/

		userLabel.setText("Nom d'utilisateur");

		userName.setToolTipText("saisissez le nom d'utilisateur avec lequel vous vous êtes enregistrés");
		userName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				userNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				userNameFocusLost(evt);
			}
		});
		userName.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				userNamePropertyChange(evt);
			}
		});

		passwordLabel.setText("Mot de passe");
		password.setToolTipText("saisisser un mot de passe valide");

		password.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				passwordActionPerformed(evt);
			}
		});

		/*savePasswordForNextEntry.setBackground(new java.awt.Color(255, 255, 255));
		savePasswordForNextEntry.setText(" Mémoriser pendant une semaine");*/

		connect.setBackground(Color.WHITE);
		connect.setForeground(Color.DARK_GRAY);
		connect.setText("Connexion");
		connect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					connectActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		userNameControl.setBackground(new java.awt.Color(255, 255, 255));
		userNameControl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		userNameControl.setForeground(new java.awt.Color(255, 0, 0));

		passwordControl.setBackground(new java.awt.Color(255, 255, 255));
		passwordControl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		passwordControl.setForeground(new java.awt.Color(255, 0, 0));
		
		lblSociete = new JLabel();
		lblSociete.setText("Societe");
		lblSociete.setForeground(Color.WHITE);
		lblSociete.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		errolabel = new JLabel();
		errolabel.setForeground(Color.RED);
		errolabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblDepot = new JLabel();
		lblDepot.setText("Region");
		lblDepot.setForeground(Color.WHITE);
		lblDepot.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnClear = new JButton();
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			userName.setText("");
			password.setText("");
			userName.requestFocus(true);
			}
		});
		btnClear.setText("Clear");
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClear.setEnabled(true);
		btnClear.setBackground(Color.WHITE);

		javax.swing.GroupLayout panelConnexionLayout = new javax.swing.GroupLayout(panelConnexion);
		panelConnexionLayout.setHorizontalGroup(
			panelConnexionLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelConnexionLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(panelConnexionLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(panelConnexionLayout.createSequentialGroup()
							.addComponent(connect, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDepot, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
						.addGroup(panelConnexionLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(panelConnexionLayout.createSequentialGroup()
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(panelConnexionLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelConnexionLayout.createSequentialGroup()
									.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(panelConnexionLayout.createSequentialGroup()
									.addComponent(lblSociete, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addComponent(errolabel, GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
								.addGroup(panelConnexionLayout.createSequentialGroup()
									.addComponent(connexionLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 840, Short.MAX_VALUE)
									.addComponent(orLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(createAccount, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(21))
								.addGroup(panelConnexionLayout.createSequentialGroup()
									.addGroup(panelConnexionLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(panelConnexionLayout.createSequentialGroup()
											.addGap(365)
											.addComponent(passwordControl, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
										.addGroup(panelConnexionLayout.createSequentialGroup()
											.addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(userNameControl, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
										.addComponent(userName, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
		);
		panelConnexionLayout.setVerticalGroup(
			panelConnexionLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelConnexionLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(panelConnexionLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(connexionLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(orLabel)
						.addComponent(createAccount, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(panelConnexionLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(userLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(userNameControl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGroup(panelConnexionLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(panelConnexionLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(passwordControl))
						.addGroup(panelConnexionLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(password, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(panelConnexionLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(panelConnexionLayout.createSequentialGroup()
							.addComponent(lblDepot, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(47))
						.addGroup(panelConnexionLayout.createSequentialGroup()
							.addGroup(panelConnexionLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(connect, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(lblSociete)
					.addGap(120)
					.addComponent(errolabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		panelConnexion.setLayout(panelConnexionLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelAuthentification, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(panelAuthentification, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);
		
	
		pack();
		
		
	
		
	}// </editor-fold>

	private void createAccountMouseClicked(java.awt.event.MouseEvent evt) {

		//CreateAccount newAccount = new CreateAccount();
		// this.setVisible(false);
	//	newAccount.setVisible(true);

	}

	private void passwordActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void connectActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {

		 login =userName.getText();
		String pw =password.getText();
	  //  query="SELECT * from UTILISATEUR  where Login='"+login+"' and password='"+pw+"'" ;
	  // rset=stx.executeQuery(query);
	   
	   utilisateur = utiliseurDAO.findUtilisateurByLoginMotPasse(login, pw);
	  
	   ImageIcon okIcon = new ImageIcon(this.getClass().getResource("/img/strive.png"));
		if(utilisateur !=null && utilisateur.getId()>0) {
			try{
			Main window = new Main();
			window.setVisible(true);
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception 1  "+e1);
			 		}
			
			
			//MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			//entityFlatView.setVisible(true);
			try{
			new NotificationBuilder()
			.withStyle(new DarkDefaultNotification())
			.withTitle("Bienvenue " + userName.getText())
			.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
			.withDisplayTime(4000).withIcon(okIcon).showNotification();
			this.dispose();
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception   2"+e1);
			 		}
		} else {
}

	/*//AuthentificationController controller = new AuthentificationController();
	boolean response = controller.evaluateFields(this);
	if (response) {
		//SecurityImplUtil authentificationUtil = new SecurityImplUtil();

	//	current_User = authentificationUtil.checkUser(getUserName(), getPassword());

		
		if (current_User == null) {
			Utils.setCURRENT_NBR_ATTEMPT(nbrAttempt++);
			if (Utils.getCURRENT_NBR_ATTEMPT() < Utils.getMAX_NBR_ATTEMPT()) {
				setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
								} else {
				connect.setEnabled(false);
				setErrorLabel("vous avez dépassé le nombre de tentative autorisé");
			}
		} else {

			new NotificationBuilder()
					.withStyle(new DarkDefaultNotification())
					.withTitle("Bienvenue " + current_User.getUserName())
					.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
					.withDisplayTime(4000).withIcon(okIcon).showNotification();

			MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			entityFlatView.setVisible(true);
		}

	}*/


	}
	
	
	// connection key pressed
	
	private void connectkeypressed(java.awt.event.KeyEvent evt) throws SQLException {


		
		

		 login =userName.getText();
		String pw =password.getText();
	  //  query="SELECT * from UTILISATEUR  where Login='"+login+"' and password='"+pw+"'" ;
	  // rset=stx.executeQuery(query);
	   
	   utilisateur = utiliseurDAO.findUtilisateurByLoginMotPasse(login, pw);
	  
	   ImageIcon okIcon = new ImageIcon(this.getClass().getResource("/img/strive.png"));
		if(utilisateur !=null && utilisateur.getId()>0) {
			try{
			Main window = new Main();
			window.setVisible(true);
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception 1  "+e1);
			 		}
			
			
			//MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			//entityFlatView.setVisible(true);
			try{
			new NotificationBuilder()
			.withStyle(new DarkDefaultNotification())
			.withTitle("Bienvenue " + userName.getText())
			.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
			.withDisplayTime(4000).withIcon(okIcon).showNotification();
			this.dispose();
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception   2"+e1);
			 		}
		} else {

		}

	/*//AuthentificationController controller = new AuthentificationController();
	boolean response = controller.evaluateFields(this);
	if (response) {
		//SecurityImplUtil authentificationUtil = new SecurityImplUtil();

	//	current_User = authentificationUtil.checkUser(getUserName(), getPassword());

		
		if (current_User == null) {
			Utils.setCURRENT_NBR_ATTEMPT(nbrAttempt++);
			if (Utils.getCURRENT_NBR_ATTEMPT() < Utils.getMAX_NBR_ATTEMPT()) {
				setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
								} else {
				connect.setEnabled(false);
				setErrorLabel("vous avez dépassé le nombre de tentative autorisé");
			}
		} else {

			new NotificationBuilder()
					.withStyle(new DarkDefaultNotification())
					.withTitle("Bienvenue " + current_User.getUserName())
					.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
					.withDisplayTime(4000).withIcon(okIcon).showNotification();

			MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			entityFlatView.setVisible(true);
		}

	}*/


		
		
	}

	private void userNameFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void userNameFocusLost(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void userNamePropertyChange(java.beans.PropertyChangeEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AuthentificationView().setVisible(true);
			}
		});
	}
	
	
	// Variables declaration - do not modify
	
	private javax.swing.JButton connect;
	private javax.swing.JLabel connexionLabel;
	private javax.swing.JLabel createAccount;
	private javax.swing.JLabel orLabel;
	private javax.swing.JPanel panelAuthentification;
	private javax.swing.JPanel panelConnexion;
	private javax.swing.JPasswordField password;
	private javax.swing.JLabel passwordControl;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JLabel userLabel;
	private javax.swing.JTextField userName;
	private javax.swing.JLabel userNameControl;
//	public static User current_User;
	private int nbrAttempt;
	private JLabel lblNewLabel;
	private JLabel lblSociete;
	private JLabel errolabel = new JLabel();
	 private JLabel lblDepot;
	 
	 private JComboBox comboRegion= new JComboBox<>();
}

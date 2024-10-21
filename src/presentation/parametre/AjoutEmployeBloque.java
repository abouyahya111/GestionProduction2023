
package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoManager.EmployeBloqueDAO;
import dao.entity.EmployeBloque;


public class AjoutEmployeBloque extends JLayeredPane {
	public JLayeredPane contentPane;	
	
	
	private ImageIcon imgBloquer;
	private ImageIcon imgInit;

	
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	
	private  EmployeBloqueDAO employeBloqueDAO;
	
	private JTextField txtCode;
	private JTextField txtNomEmploye;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JDateChooser dateNaissanceChooser ;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutEmployeBloque() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	employeBloqueDAO=new EmployeBloqueDAOImpl();
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgBloquer = new ImageIcon(this.getClass().getResource("/img/bloquer.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
       
	     
        
				  		  btnAjouter = new JButton("Bloquer ");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		

				  		   		
					  		   	if(txtCode.getText().equals(""))
			  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		else if (txtNomEmploye.getText().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		 else {

						  		  		EmployeBloque employeBloque = new EmployeBloque();
						  		  		employeBloque.setMatricule(txtCode.getText());
						  		  		employeBloque.setNom(txtNomEmploye.getText());
						  		  		employeBloque.setAdresse(txtAdresse.getText());
						  		  		employeBloque.setNumTel(txtTel.getText());
						  		  		employeBloque.setDateNaissance(dateNaissanceChooser.getDate());
						  		  		employeBloque.setDateCreation(new Date());
						  		  		employeBloque.setUtilCreation(AuthentificationView.utilisateur);
						  		  		
						  		  		employeBloqueDAO.add(employeBloque);
						  		  		
						  		  		JOptionPane.showMessageDialog(null, "L'employé a été bloqué avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  		
						  		  		
			  		     	intialiser ();
			  		     		 }
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgBloquer);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(250, 208, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(374, 208, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 11, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Employ\u00E9 Bloqu\u00E9s");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(9, 35, 903, 150);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(351, 25, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNomEmploye = new JTextField();
				  		 util.Utils.copycoller(txtNomEmploye);
				  		   txtNomEmploye.setBounds(450, 26, 191, 26);
				  		   layeredPane.add(txtNomEmploye);
				  		   txtNomEmploye.setColumns(10);
				  		   txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		   txtCode.setBounds(100, 26, 191, 26);
				  		   layeredPane.add(txtCode);
				  		   txtCode.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("N\u00B0 CNI");
				  		   lblCodeLigne.setBounds(7, 25, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   	
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 Tel :");
				  		   			lblNTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNTel.setBounds(7, 63, 130, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   			txtTel = new JTextField();
				  		   		 util.Utils.copycoller(txtTel);
				  		   			txtTel.setColumns(10);
				  		   			txtTel.setBounds(100, 63, 191, 26);
				  		   			layeredPane.add(txtTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse:");
				  		   			lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblAdresse.setBounds(7, 100, 130, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(100, 100, 273, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			 dateNaissanceChooser = new JDateChooser();
				  		   			dateNaissanceChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateNaissanceChooser.setBounds(450, 62, 181, 26);
				  		   			layeredPane.add(dateNaissanceChooser);
				  		   			
				  		   			JLabel lblDateNaissance = new JLabel("Date Naissance :");
				  		   			lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateNaissance.setBounds(351, 62, 130, 26);
				  		   			layeredPane.add(lblDateNaissance);
				  		   			
				  		   			
				  		   		
				  		
				  		 
	}
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtAdresse.setText("");
		txtNomEmploye.setText("");
		txtTel.setText("");
		dateNaissanceChooser.setDate(null);
		
	}
}

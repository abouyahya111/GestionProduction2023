package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
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
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeBloqueDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.EmployeBloque;
import dao.entity.Equipe;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;
import javax.swing.JCheckBox;


public class ChercherEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgSupprimer;
	private ImageIcon imgBloquer;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	private JTextField matricule;
	
	private JComboBox comboDepot = new JComboBox();
	private  EmployeDAO employeDAO;
	private  EquipeDAO equipeDAO;
	private TypeResEmployeDAO typeResEmployeDAO;
	private  EmployeBloqueDAO employeBloqueDAO;
	
	private JTextField txtCode;
	private JTextField txtNomEmploye;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JTextField txtNumDoss;
	private JTextField txtCoutHoraire;
	
	
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private Map< String, Equipe> mapEquipe = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, TypeResEmploye> mapTypeResEmploye = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private JComboBox comboEmploye = new JComboBox();
	private JComboBox comboEquipe = new JComboBox();
	private JComboBox comboRespon = new JComboBox();
	private JDateChooser dateNaissanceChooser ;
	private JDateChooser dateEntreChooser;
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<Equipe> listEquipe =new ArrayList<Equipe>();
	private List<TypeResEmploye> listTypeResEmploye=new ArrayList<TypeResEmploye>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	JCheckBox checkSalarie = new JCheckBox("");
	private Employe employe = new Employe();
	private JTextField txtNumCNSS;
	private JTextField txtService;
	private JTextField txtNumDossier;
	private JTextField txtLieuNaiss;
	 JCheckBox chckbxActif = new JCheckBox("");
	 private JTextField txtprenom;
	 private JTextField txtcin;
	 private JTextField txtnbrenfant;
	 private JTextField txtsociete;
	 JComboBox combosituation = new JComboBox();
	 JComboBox combosex = new JComboBox();
	 JDateChooser dateSortieChooser = new JDateChooser();
	 JDateChooser datePremiereProduction = new JDateChooser();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 723);
        try{
        	
        	employeDAO= new EmployeDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	typeResEmployeDAO= new TypeResEmployeDAOImpl();
        
        	depotDAO= new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
       
        mapParametre=Utils.listeParametre();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            imgBloquer = new ImageIcon(this.getClass().getResource("/img/bloquer.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        comboEmploye.addItem("");
        comboEquipe.addItem("");
        comboRespon.addItem("");
        
        listEquipe = equipeDAO.findAll();	
	     	
	      int i=0;
	      	while(i<listEquipe.size())
	      		{	
	      			Equipe equipe=listEquipe.get(i);
	      			mapEquipe.put(equipe.getNomEquipe(), equipe);
	      			
	      			comboEquipe.addItem(equipe.getNomEquipe());
	      			i++;
	      		}
	      	
	      	listTypeResEmploye = typeResEmployeDAO.findAll();	
	      	i=0;
	      	while(i<listTypeResEmploye.size())
	      		{	
	      		TypeResEmploye typeResEmploye=listTypeResEmploye.get(i);
	      			mapTypeResEmploye.put(typeResEmploye.getLibelle(), typeResEmploye);
	      			comboRespon.addItem(typeResEmploye.getLibelle());
	      			i++;
	      		}
        
				  		  btnModifier = new JButton("Modifier Employe");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(employe.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher l'employé à modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  					if (txtNomEmploye.getText().equals(""))
		  		     				{
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			 return;
		  		     				}
		  		     				else if(txtprenom.getText().equals(""))
		  		     				{
		  		     				 JOptionPane.showMessageDialog(null, "Il faut remplir le Prénom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			 return;
		  		     					
		  		     				}else if (comboRespon.getSelectedItem().equals(""))
		  		     				{
		  		     				 JOptionPane.showMessageDialog(null, "Il faut remplir la Responsabilité!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     				 return;
		  		     					
		  		     				}else if(combosex.getSelectedIndex()==-1)
		  		     				{
		  		     				 JOptionPane.showMessageDialog(null, "Il faut selectionner le sex employé !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     				 return;
		  		     				}else if(combosex.getSelectedItem().equals(""))
		  		     				{
		  		     					JOptionPane.showMessageDialog(null, "Il faut selectionner le sex employé !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     				 return;
		  		     				}else if(combosituation.getSelectedIndex()==-1)
		  		     				{
		  		     					JOptionPane.showMessageDialog(null, "Il faut selectionner la Situation employé !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     				 return;
		  		     				}	else if(txtcin.getText().equals(""))
			  		     			{
			  		     				JOptionPane.showMessageDialog(null, "Il faut remplir la CIN Employe(é) SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     				return;
			  		     			}
		  		     				
		  		     				else if(combosituation.getSelectedItem().equals(""))
		  		     				{
		  		     					JOptionPane.showMessageDialog(null, "Il faut selectionner la Situation employé !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     				 return;
		  		     				}else if(!combosituation.getSelectedItem().equals(Constantes.SITUATION_CELIBATAIRE))
		  		     				{
		  		     					
		  		     					if(txtnbrenfant.getText().equals(""))
		  		     					{
		  		     						JOptionPane.showMessageDialog(null, "Il faut remplir le Nombre d'Enfant !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     				 return;
		  		     					}else
		  		     					{
		  		     						
		  		     						Employe employeTmp =employeDAO.findByCINE(txtcin.getText().toUpperCase().trim(), employe.getDepot().getId());
		  		     						if(employeTmp!=null)
		  		     						{
		  		     							
		  		     							if(employeTmp.getId()!=employe.getId())
		  		     							{
		  		     								JOptionPane.showMessageDialog(null, "CINE déja Existant  !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		     				 return;
		  		     							}
		  		     							
		  		     						}
			  		     			  		Equipe equipeSource=employe.getEquipe();
							  		  		//equipeSource.getListEmploye().remove(employe);
							  		  		//equipeDAO.edit(equipeSource);
							  		  		Equipe equipe =mapEquipe.get(comboEquipe.getSelectedItem());
							  		  		employe.setNumCNSS(txtNumCNSS.getText());
							  		  		employe.setDateEntre(dateEntreChooser.getDate());
							  		  		employe.setService(txtService.getText());
							  		  		employe.setNom(txtNomEmploye.getText());
							  		  	    employe.setActif(chckbxActif.isSelected());
							  		        employe.setSalarie(checkSalarie.isSelected());
							  		  		employe.setAdresse(txtAdresse.getText());
							  		  		employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
							  		  		employe.setNumTel(txtTel.getText());
							  		  		employe.setEquipe(mapEquipe.get(comboEquipe.getSelectedItem()));
							  		  		employe.setTypeResEmploye(mapTypeResEmploye.get(comboRespon.getSelectedItem()));
							  		  		employe.setDateModification(new Date());
							  		  		employe.setUtilisateurMAJ(AuthentificationView.utilisateur);
							  		  		employe.setDateNaissance(dateNaissanceChooser.getDate());
							  		  		employe.setLieuNaissance(txtLieuNaiss.getText());
							  		  		
							  		  	employe.setPrenom(txtprenom.getText());
				  		     			employe.setSex(combosex.getSelectedItem().toString());
				  		     			employe.setSituation(combosituation.getSelectedItem().toString());
				  		     			if(!combosituation.getSelectedItem().equals(Constantes.SITUATION_CELIBATAIRE))
				  		     			{
				  		     				employe.setNombreEnfant(new BigDecimal(txtnbrenfant.getText()));
				  		     				
				  		     			}else
				  		     			{
				  		     				employe.setNombreEnfant(BigDecimal.ZERO);
				  		     			}
				  		     			employe.setCin(txtcin.getText());
				  		     			employe.setSociete(txtsociete.getText());
							  		  		
				  		     			if(chckbxActif.isSelected()==false)
				  		     			{
				  		     				
				  		     				if(dateSortieChooser.getDate()==null)
				  		     				{
				  		     					
				  		     					JOptionPane.showMessageDialog(null, "Veuillez entrer la date sortie pour un employé inactif SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							  		  			return;	
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     			}
				  		     			
				  		     			
				  		     			
				  		     			employe.setDateSortie(dateSortieChooser.getDate());	
							  		  		if(dateSortieChooser.getDate()!=null)
							  		  		{
							  		  			if(chckbxActif.isSelected()==true)
							  		  			{
							  		  			JOptionPane.showMessageDialog(null, "Veuillez decoché l'actif pour un employé sortie SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							  		  			return;
							  		  			}
							  		  			
							  		  		
							  		  		}
							  		  		
							  		  		
							  		  		
							  		  		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
							  		    	employe.setDepot(depot);
							  		  		employeDAO.edit(employe);
							  		  		
							  		  		
							  		  		
							  		  		
							  		  		//equipe.addEmploye(employe);
							  		  		//equipeDAO.edit(equipe);
							  		  /*	if(equipeSource !=null){
							  		  		List<Employe> listeEmployeSource=equipeDAO.findListeEmployeByEquipe(equipeSource.getId());
							  		  		equipeSource.setListEmploye(listeEmployeSource);
							  		  		equipeDAO.edit(equipeSource) ;
							  		  	}
							  		  		
							  		  	if(equipe !=null){
							  		  	List<Employe> listeEmploye=equipeDAO.findListeEmployeByEquipe(equipe.getId());
							  		  		equipe.setListEmploye(listeEmploye);
							  		  		equipeDAO.edit(equipe) ;
							  		  		
							  		  	}*/
							  		  		
							  		  		
							  		  		
							  		  		
							  		  		
							  		  		
							  		  	
							  		  		JOptionPane.showMessageDialog(null, "L'employé a été modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
							  		  		
				///////////////////////////////////////////////////////////////////////////////////////////// Impression /////////////////////////////////////////////////////////////////////////			  		  		
							  		  		
								   			Map parameters = new HashMap();
							  		   		
							  		   		 parameters.put("depot", depot.getLibelle());
							  		   		parameters.put("matricule", employe.getMatricule());
							  		   	parameters.put("numdossier", employe.getNumDossier());
							  		  parameters.put("nom", employe.getNom());
							  		 parameters.put("prenom", employe.getPrenom());
							  		 parameters.put("cin", employe.getCin());
							  		 parameters.put("sex", employe.getSex());
							  		 parameters.put("datenaissance", employe.getDateNaissance());
							  		 parameters.put("adresse", employe.getAdresse());
							  		 parameters.put("service", employe.getService());
							  		 if(employe.getEquipe()!=null)
							  		 {
							  			 if(employe.getEquipe().getNomEquipe()!=null)
							  			 {
							  				 parameters.put("equipe", employe.getEquipe().getNomEquipe()); 
							  			 }else
							  			 {
							  				 parameters.put("equipe", "");
							  			 }
							  			 
							  		 }else
							  		 {
							  			 parameters.put("equipe", ""); 
							  		 }
							  		
							  		 parameters.put("numtel", employe.getNumTel());
							  		 parameters.put("responsabilite", employe.getTypeResEmploye().getLibelle());
							  		 parameters.put("cout", employe.getCoutHoraire());
							  		 parameters.put("dateentrer", employe.getDateEntre());
							  		 parameters.put("situation", employe.getSituation());
							  		 parameters.put("nbrenfant", employe.getNombreEnfant());
							  		 parameters.put("cnss", employe.getNumCNSS());
							  		 parameters.put("datesortie", employe.getDateSortie());
							  		 parameters.put("societe", employe.getSociete());
							  		 if(employe.isSalarie()==true)
							  		 {
							  			 parameters.put("salarie", "Salarie"); 
							  		 }else
							  		 {
							  			 parameters.put("salarie", "Employé"); 
							  		 }
							  		 
							  		 if(employe.getDateSortie()!=null)
							  		 {
							  			 parameters.put("titre", "FICHE EMPLOYEE SORTIE");  
							  		 }else
							  		 {
							  			 parameters.put("titre", "FICHE EMPLOYEE");  
							  		 }
							  		 
							  		
							  		 
							  		JasperUtils.imprimerFicheEmployeBloque(listEmploye, parameters);		  		  		
							  		  		
							  		  		
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  		  		
							  		  		
							  		  		
							  		  		intialiser ();
							  		  		
			  		     				
		  		     					}
		  		     					
		  		     					
		  		     				}else
		  		     				{
		  		     					Employe employeTmp =employeDAO.findByCINE(txtcin.getText().toUpperCase().trim(), employe.getDepot().getId());
	  		     						if(employeTmp!=null)
	  		     						{
	  		     							
	  		     							if(employeTmp.getId()!=employe.getId())
	  		     							{
	  		     								JOptionPane.showMessageDialog(null, "CINE déja Existant  !!!!!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		     				 return;
	  		     							}
	  		     							
	  		     						}
		  		     					
		  		     			  		Equipe equipeSource=employe.getEquipe();
						  		  		//equipeSource.getListEmploye().remove(employe);
						  		  		//equipeDAO.edit(equipeSource);
						  		  		Equipe equipe =mapEquipe.get(comboEquipe.getSelectedItem());
						  		  		employe.setNumCNSS(txtNumCNSS.getText());
						  		  		employe.setDateEntre(dateEntreChooser.getDate());
						  		  		employe.setService(txtService.getText());
						  		  		employe.setNom(txtNomEmploye.getText());
						  		  	    employe.setActif(chckbxActif.isSelected());
						  		        employe.setSalarie(checkSalarie.isSelected());
						  		  		employe.setAdresse(txtAdresse.getText());
						  		  		employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
						  		  		employe.setNumTel(txtTel.getText());
						  		  		employe.setEquipe(mapEquipe.get(comboEquipe.getSelectedItem()));
						  		  		employe.setTypeResEmploye(mapTypeResEmploye.get(comboRespon.getSelectedItem()));
						  		  		employe.setDateModification(new Date());
						  		  		employe.setUtilisateurMAJ(AuthentificationView.utilisateur);
						  		  		employe.setDateNaissance(dateNaissanceChooser.getDate());
						  		  		employe.setLieuNaissance(txtLieuNaiss.getText());
						  		  		
						  		  	employe.setPrenom(txtprenom.getText());
			  		     			employe.setSex(combosex.getSelectedItem().toString());
			  		     			employe.setSituation(combosituation.getSelectedItem().toString());
			  		     			if(!combosituation.getSelectedItem().equals(Constantes.SITUATION_CELIBATAIRE))
			  		     			{
			  		     				employe.setNombreEnfant(new BigDecimal(txtnbrenfant.getText()));
			  		     				
			  		     			}else
			  		     			{
			  		     				employe.setNombreEnfant(BigDecimal.ZERO);
			  		     			}
			  		     			employe.setCin(txtcin.getText());
			  		     			employe.setSociete(txtsociete.getText());
						  		  		
			  		     			if(chckbxActif.isSelected()==false)
			  		     			{
			  		     				
			  		     				if(dateSortieChooser.getDate()==null)
			  		     				{
			  		     					
			  		     					JOptionPane.showMessageDialog(null, "Veuillez entrer la date sortie pour un employé inactif SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						  		  			return;	
			  		     					
			  		     					
			  		     				}
			  		     				
			  		     				
			  		     			}
			  		     			
			  		     			
			  		     			
			  		     			employe.setDateSortie(dateSortieChooser.getDate());	
						  		  		if(dateSortieChooser.getDate()!=null)
						  		  		{
						  		  			if(chckbxActif.isSelected()==true)
						  		  			{
						  		  			JOptionPane.showMessageDialog(null, "Veuillez decoché l'actif pour un employé sortie SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						  		  			return;
						  		  			}
						  		  			
						  		  		
						  		  		}
						  		  		
						  		  		
						  		  		
						  		  		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
						  		    	employe.setDepot(depot);
						  		  		employeDAO.edit(employe);
						  		  		
						  		  		
						  		  		
						  		  		
						  		  		//equipe.addEmploye(employe);
						  		  		//equipeDAO.edit(equipe);
						  		  /*	if(equipeSource !=null){
						  		  		List<Employe> listeEmployeSource=equipeDAO.findListeEmployeByEquipe(equipeSource.getId());
						  		  		equipeSource.setListEmploye(listeEmployeSource);
						  		  		equipeDAO.edit(equipeSource) ;
						  		  	}
						  		  		
						  		  	if(equipe !=null){
						  		  	List<Employe> listeEmploye=equipeDAO.findListeEmployeByEquipe(equipe.getId());
						  		  		equipe.setListEmploye(listeEmploye);
						  		  		equipeDAO.edit(equipe) ;
						  		  		
						  		  	}*/
						  		  		
						  		  		
						  		  		
						  		  		
						  		  		
						  		  		
						  		  	
						  		  		JOptionPane.showMessageDialog(null, "L'employé a été modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  		
			///////////////////////////////////////////////////////////////////////////////////////////// Impression /////////////////////////////////////////////////////////////////////////			  		  		
						  		  		
							   			Map parameters = new HashMap();
						  		   		
						  		   		 parameters.put("depot", depot.getLibelle());
						  		   		parameters.put("matricule", employe.getMatricule());
						  		   	parameters.put("numdossier", employe.getNumDossier());
						  		  parameters.put("nom", employe.getNom());
						  		 parameters.put("prenom", employe.getPrenom());
						  		 parameters.put("cin", employe.getCin());
						  		 parameters.put("sex", employe.getSex());
						  		 parameters.put("datenaissance", employe.getDateNaissance());
						  		 parameters.put("adresse", employe.getAdresse());
						  		 parameters.put("service", employe.getService());
						  		 if(employe.getEquipe()!=null)
						  		 {
						  			 if(employe.getEquipe().getNomEquipe()!=null)
						  			 {
						  				 parameters.put("equipe", employe.getEquipe().getNomEquipe()); 
						  			 }else
						  			 {
						  				 parameters.put("equipe", "");
						  			 }
						  			 
						  		 }else
						  		 {
						  			 parameters.put("equipe", ""); 
						  		 }
						  		
						  		 parameters.put("numtel", employe.getNumTel());
						  		 parameters.put("responsabilite", employe.getTypeResEmploye().getLibelle());
						  		 parameters.put("cout", employe.getCoutHoraire());
						  		 parameters.put("dateentrer", employe.getDateEntre());
						  		 parameters.put("situation", employe.getSituation());
						  		 parameters.put("nbrenfant", employe.getNombreEnfant());
						  		 parameters.put("cnss", employe.getNumCNSS());
						  		 parameters.put("datesortie", employe.getDateSortie());
						  		 parameters.put("societe", employe.getSociete());
						  		 if(employe.isSalarie()==true)
						  		 {
						  			 parameters.put("salarie", "Salarie"); 
						  		 }else
						  		 {
						  			 parameters.put("salarie", "Employé"); 
						  		 }
						  		 
						  		 if(employe.getDateSortie()!=null)
						  		 {
						  			 parameters.put("titre", "FICHE EMPLOYEE SORTIE");  
						  		 }else
						  		 {
						  			 parameters.put("titre", "FICHE EMPLOYEE");  
						  		 }
						  		 
						  		
						  		 
						  		JasperUtils.imprimerFicheEmployeBloque(listEmploye, parameters);		  		  		
						  		  		
						  		  		
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  		  		
						  		  		
						  		  		
						  		  		intialiser ();
						  		  		
		  		     				}
				  		  			
				  		  			
				  		  			
				  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(243, 645, 139, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(392, 645, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JLabel lblCode = new JLabel("Matricule Employ\u00E9 :");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Employ\u00E9");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 105, 903, 529);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(7, 25, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNomEmploye = new JTextField();
				  		 util.Utils.copycoller(txtNomEmploye);
				  		   txtNomEmploye.setBounds(99, 26, 191, 26);
				  		   layeredPane.add(txtNomEmploye);
				  		   txtNomEmploye.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Matricule :");
				  		   lblCodeLigne.setBounds(6, 169, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   			
				  		   			JLabel label = new JLabel("Responsabilti\u00E9 :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(6, 243, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 
				  		   			comboRespon.setBounds(99, 244, 191, 26);
				  		   			layeredPane.add(comboRespon);
				  		   			
				  		   			JLabel lblCoutHoraire = new JLabel("Cout Horaire :");
				  		   			lblCoutHoraire.setVisible(false);
				  		   			lblCoutHoraire.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCoutHoraire.setBounds(352, 237, 130, 26);
				  		   			layeredPane.add(lblCoutHoraire);
				  		   			
				  		   			txtCoutHoraire = new JTextField();
				  		   		util.Utils.copycoller(txtCoutHoraire);
				  		   			txtCoutHoraire.setVisible(false);
				  		   			txtCoutHoraire.setColumns(10);
				  		   			txtCoutHoraire.setBounds(450, 237, 191, 26);
				  		   			layeredPane.add(txtCoutHoraire);
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 Tel :");
				  		   			lblNTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNTel.setBounds(6, 207, 130, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   			txtTel = new JTextField();
				  		   		 util.Utils.copycoller(txtTel);
				  		   			txtTel.setColumns(10);
				  		   			txtTel.setBounds(99, 207, 191, 26);
				  		   			layeredPane.add(txtTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse:");
				  		   			lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblAdresse.setBounds(352, 169, 130, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(450, 170, 273, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			
				  		   			comboEquipe.setBounds(450, 100, 191, 26);
				  		   			layeredPane.add(comboEquipe);
				  		   			
				  		   			JLabel lblEquipe = new JLabel("Equipe :");
				  		   			lblEquipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblEquipe.setBounds(350, 99, 130, 26);
				  		   			layeredPane.add(lblEquipe);
				  		   			
				  		   			txtNumDoss = new JTextField();
				  		   		 util.Utils.copycoller(txtNumDoss);
				  		   			txtNumDoss.setForeground(Color.RED);
				  		   			txtNumDoss.setBackground(Color.WHITE);
				  		   			txtNumDoss.setEditable(false);
				  		   			txtNumDoss.setColumns(10);
				  		   			txtNumDoss.setBounds(450, 26, 191, 26);
				  		   			layeredPane.add(txtNumDoss);
				  		   			
				  		   			JLabel lblDateNaissance = new JLabel("Date Naissance :");
				  		   			lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateNaissance.setBounds(350, 137, 130, 26);
				  		   			layeredPane.add(lblDateNaissance);
				  		   			
				  		   			 dateNaissanceChooser = new JDateChooser();
				  		   			dateNaissanceChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateNaissanceChooser.setBounds(450, 136, 181, 26);
				  		   			layeredPane.add(dateNaissanceChooser);
				  		   			
				  		   			JLabel lblNDossier = new JLabel("N\u00B0 Dossier  :");
				  		   			lblNDossier.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier.setBounds(350, 26, 130, 26);
				  		   			layeredPane.add(lblNDossier);
				  		   			
				  		   			 dateEntreChooser = new JDateChooser();
				  		   			dateEntreChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateEntreChooser.setBounds(100, 276, 181, 26);
				  		   			layeredPane.add(dateEntreChooser);
				  		   			
				  		   			JLabel lblDateEntre = new JLabel("Date Entr\u00E9e:");
				  		   			lblDateEntre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateEntre.setBounds(5, 276, 130, 26);
				  		   			layeredPane.add(lblDateEntre);
				  		   			
				  		   			txtNumCNSS = new JTextField();
				  		   		 util.Utils.copycoller(txtNumCNSS);
				  		   			txtNumCNSS.setColumns(10);
				  		   			txtNumCNSS.setBounds(98, 311, 191, 26);
				  		   			layeredPane.add(txtNumCNSS);
				  		   			
				  		   			JLabel lblNCnss = new JLabel("N\u00B0 CNSS:");
				  		   			lblNCnss.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNCnss.setBounds(5, 311, 130, 26);
				  		   			layeredPane.add(lblNCnss);
				  		   			
				  		   			JLabel lblService = new JLabel("Service :");
				  		   			lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblService.setBounds(347, 62, 130, 26);
				  		   			layeredPane.add(lblService);
				  		   			txtCode = new JTextField();
				  		   		 util.Utils.copycoller(txtCode);
				  		   			txtCode.setBounds(98, 170, 191, 26);
				  		   			layeredPane.add(txtCode);
				  		   			txtCode.setForeground(Color.RED);
				  		   			txtCode.setBackground(Color.WHITE);
				  		   			txtCode.setDisabledTextColor(Color.BLACK);
				  		   			txtCode.setEditable(false);
				  		   			txtCode.setColumns(10);
				  		   			
				  		   			txtService = new JTextField();
				  		   		 util.Utils.copycoller(txtService);
				  		   			txtService.setBounds(450, 63, 191, 26);
				  		   			layeredPane.add(txtService);
				  		   			txtService.setColumns(10);
				  		   			
				  		   			txtLieuNaiss = new JTextField();
				  		   		 util.Utils.copycoller(txtLieuNaiss);
				  		   			txtLieuNaiss.setColumns(10);
				  		   			txtLieuNaiss.setBounds(451, 203, 191, 26);
				  		   			layeredPane.add(txtLieuNaiss);
				  		   			
				  		   			JLabel lblLieuNaissance = new JLabel("Lieu Naissance:");
				  		   			lblLieuNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblLieuNaissance.setBounds(348, 203, 130, 26);
				  		   			layeredPane.add(lblLieuNaissance);
				  		   			
				  		   			JLabel label_2 = new JLabel("Salari\u00E9");
				  		   			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_2.setBounds(712, 25, 87, 18);
				  		   			layeredPane.add(label_2);
				  		   			
				  		   		 checkSalarie = new JCheckBox("");
				  		   			checkSalarie.setBounds(810, 25, 28, 18);
				  		   			layeredPane.add(checkSalarie);
				  		   			
				  		   			  chckbxActif = new JCheckBox("");
				  		   			chckbxActif.setBounds(810, 58, 28, 18);
				  		   			layeredPane.add(chckbxActif);
				  		   			
				  		   			JLabel label_3 = new JLabel("Actif :");
				  		   			label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_3.setBounds(712, 57, 87, 19);
				  		   			layeredPane.add(label_3);
				  		   			
				  		   			JLabel label_4 = new JLabel("Pr\u00E9nom   :");
				  		   			label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_4.setBounds(7, 62, 130, 26);
				  		   			layeredPane.add(label_4);
				  		   			
				  		   			txtprenom = new JTextField();
				  		   			txtprenom.setColumns(10);
				  		   			txtprenom.setBounds(99, 63, 191, 26);
				  		   			layeredPane.add(txtprenom);
				  		   			
				  		   			JLabel label_5 = new JLabel("Sex :");
				  		   			label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_5.setBounds(7, 100, 64, 26);
				  		   			layeredPane.add(label_5);
				  		   			
				  		   			 combosex = new JComboBox();
				  		   			combosex.setBounds(99, 101, 191, 26);
				  		   			layeredPane.add(combosex);
				  		   			
				  		   			JLabel label_6 = new JLabel("CIN :");
				  		   			label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_6.setBounds(7, 136, 75, 26);
				  		   			layeredPane.add(label_6);
				  		   			
				  		   			txtcin = new JTextField();
				  		   			txtcin.setColumns(10);
				  		   			txtcin.setBounds(99, 137, 191, 26);
				  		   			layeredPane.add(txtcin);
				  		   			
				  		   			JLabel label_7 = new JLabel("Situation :");
				  		   			label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_7.setBounds(358, 274, 64, 26);
				  		   			layeredPane.add(label_7);
				  		   			
				  		   			 combosituation = new JComboBox();
				  		   			combosituation.addItemListener(new ItemListener() {
				  		   				public void itemStateChanged(ItemEvent arg0) {
				  		   					

				  					 		
				  					 		if(combosituation.getSelectedIndex()!=-1)
				  					 		{
				  					 			if(combosituation.getSelectedItem()!="")
				  					 			{
				  					 				
				  					 				if(combosituation.getSelectedItem().toString().equals(Constantes.SITUATION_CELIBATAIRE))
				  					 				{
				  					 					txtnbrenfant.setText("");
				  							 			txtnbrenfant.setEditable(false);
				  					 					
				  					 				}else
				  					 				{
				  					 					txtnbrenfant.setText("0");
				  							 			txtnbrenfant.setEditable(true);
				  					 				}
				  					 				
				  					 			}else
				  					 			{
				  					 				txtnbrenfant.setText("");
				  						 			txtnbrenfant.setEditable(false);
				  					 				
				  					 			}
				  					 				
				  					 		}else
				  					 		{
				  					 			txtnbrenfant.setText("");
				  					 			txtnbrenfant.setEditable(false);
				  					 		}
				  					 		
				  					 		
				  					 		
				  					 	
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			combosituation.setBounds(450, 275, 191, 26);
				  		   			layeredPane.add(combosituation);
				  		   			
				  		   			JLabel label_8 = new JLabel("Nbr Enfants   :");
				  		   			label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_8.setBounds(352, 315, 87, 26);
				  		   			layeredPane.add(label_8);
				  		   			
				  		   			txtnbrenfant = new JTextField();
				  		   			txtnbrenfant.setColumns(10);
				  		   			txtnbrenfant.setBounds(450, 315, 191, 26);
				  		   			layeredPane.add(txtnbrenfant);
				  		   			
				  		   			JLabel label_9 = new JLabel("Soci\u00E9t\u00E9 :");
				  		   			label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_9.setBounds(352, 350, 64, 26);
				  		   			layeredPane.add(label_9);
				  		   			
				  		   			txtsociete = new JTextField();
				  		   			txtsociete.setColumns(10);
				  		   			txtsociete.setBounds(450, 350, 191, 26);
				  		   			layeredPane.add(txtsociete);
				  		   			
				  		   			JLabel lblDateSortie = new JLabel("Date Sortie:");
				  		   			lblDateSortie.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateSortie.setBounds(7, 385, 130, 26);
				  		   			layeredPane.add(lblDateSortie);
				  		   			
				  		   			 dateSortieChooser = new JDateChooser();
				  		   			dateSortieChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateSortieChooser.setBounds(102, 385, 181, 26);
				  		   			layeredPane.add(dateSortieChooser);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 83);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(725, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   			
				  		   			
				  		   			 listEmploye = employeDAO.findAllEmploye();	
					  		     	
					  		       i=0;
					  		      	while(i<listEmploye.size())
					  		      		{	
					  		      			Employe employe=listEmploye.get(i);
					  		      			
					  		      			mapLibelle.put(employe.getNomafficher(), employe.getMatricule());
					  		      		mapCode.put(employe.getMatricule(),employe.getNomafficher());
				  		      			comboEmploye.addItem(employe.getNomafficher());
					  		      			
					  		      			
					  		      			i++;
					  		      		}
					  		      	
					  		      comboEmploye.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	matricule.setText(mapLibelle.get(comboEmploye.getSelectedItem()));
						  		     	   	txtNumDossier.setText("");
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
							AutoCompleteDecorator.decorate(comboEmploye);
				  		   			comboEmploye.setBounds(471, 15, 188, 26);
				  		   			layeredPane_1.add(comboEmploye);
				  		   			
				  		   			matricule = new JTextField();
				  		   		util.Utils.copycoller(matricule);
				  		   			matricule.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(matricule);
				  		   			matricule.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom Employ\u00E9 :");
				  		   			lblLibelle.setBounds(359, 14, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			txtNumDossier = new JTextField();
				  		   		util.Utils.copycoller(txtNumDossier);
				  		   			txtNumDossier.addFocusListener(new FocusAdapter() {
				  		   				@Override
				  		   				public void focusGained(FocusEvent e) {
				  		   				matricule.setText("");
			  		   					comboEmploye.setSelectedItem("");
				  		   				}
				  		   			});
				  		   		
				  		   			txtNumDossier.setColumns(10);
				  		   			txtNumDossier.setBounds(124, 46, 191, 26);
				  		   			layeredPane_1.add(txtNumDossier);
				  		   			
				  		   			JLabel lblNDossier_1 = new JLabel("N\u00B0 Dossier :");
				  		   			lblNDossier_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier_1.setBounds(10, 46, 114, 26);
				  		   			layeredPane_1.add(lblNDossier_1);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			comboDepot.setBounds(471, 46, 188, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			
				  		   			JLabel label_1 = new JLabel("Depot :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(369, 45, 80, 26);
				  		   			layeredPane_1.add(label_1);
				  		   			
				  		   			JButton btnBloquerEmploy = new JButton("Bloquer Employ\u00E9");
				  		   			btnBloquerEmploy.setIcon(imgBloquer);
				  		   			
				  		   			btnBloquerEmploy.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   				if(employe ==null || employe.getId()<=0){
				  		   				JOptionPane.showMessageDialog(null, "Il faut chercher l'employé à bloquer!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		   				
				  		   				}else {
				  		   			/*	EmployeBloque employeBolque =new EmployeBloque();
				  		   				employeBolque.setDateCreation(new Date());
				  		   				employeBolque.setAdresse(employe.getAdresse());
				  		   				employeBolque.setDateNaissance(employe.getDateNaissance());
				  		   				employeBolque.setMatricule(employe.getMatricule());
				  		   				employeBolque.setNom(employe.getNom());
				  		   				employeBolque.setNumTel(employe.getNumTel());
				  		   				employeBolque.setUtilCreation(AuthentificationView.utilisateur);*/
				  		   					if(dateSortieChooser.getDate()==null)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Il faut saisir la date sortie SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		   					return;
				  		   					}
				  		   					employe.setActif(false);
				  		   				    employe.setDateSortie(dateSortieChooser.getDate());
				  		   				    employe.setBlocageEmploye(Constantes.CODE_OUI);
				  		   					employe.setEquipe(null);
				  		   					employeDAO.edit(employe);
				  		   					
				  		   				
				  		   					
				  		   				//	employeBloqueDAO.add(employeBolque);
				  		   				JOptionPane.showMessageDialog(null, "L'employé a été bloqué avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		   			
				  		   			Map parameters = new HashMap();
				  		   		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		   		 parameters.put("depot", depot.getLibelle());
				  		   		parameters.put("matricule", employe.getMatricule());
				  		   	parameters.put("numdossier", employe.getNumDossier());
				  		  parameters.put("nom", employe.getNom());
				  		 parameters.put("prenom", employe.getPrenom());
				  		 parameters.put("cin", employe.getCin());
				  		 parameters.put("sex", employe.getSex());
				  		 parameters.put("datenaissance", employe.getDateNaissance());
				  		 parameters.put("adresse", employe.getAdresse());
				  		 parameters.put("service", employe.getService());
				  		 if(employe.getEquipe()!=null)
				  		 {
				  			 if(employe.getEquipe().getNomEquipe()!=null)
				  			 {
				  				 parameters.put("equipe", employe.getEquipe().getNomEquipe()); 
				  			 }else
				  			 {
				  				 parameters.put("equipe", "");
				  			 }
				  			 
				  		 }else
				  		 {
				  			 parameters.put("equipe", ""); 
				  		 }
				  		
				  		 parameters.put("numtel", employe.getNumTel());
				  		 parameters.put("responsabilite", employe.getResponsabilite());
				  		 parameters.put("cout", employe.getCoutHoraire());
				  		 parameters.put("dateentrer", employe.getDateEntre());
				  		 parameters.put("situation", employe.getSituation());
				  		 parameters.put("nbrenfant", employe.getNombreEnfant());
				  		 parameters.put("cnss", employe.getNumCNSS());
				  		 parameters.put("datesortie", employe.getDateSortie());
				  		 parameters.put("societe", employe.getSociete());
				  		 if(employe.isSalarie()==true)
				  		 {
				  			 parameters.put("salarie", "Salarie"); 
				  		 }else
				  		 {
				  			 parameters.put("salarie", "Employé"); 
				  		 }
				  		 
				  		 parameters.put("titre", "FICHE EMPLOYEE BLOQUE"); 
				  		 
				  		JasperUtils.imprimerFicheEmployeBloque(listEmploye, parameters);
				  		 
				  		 
						  		  		intialiser ();
				  		   				}
				  		   				}
				  		   			});
				  		   			btnBloquerEmploy.setBounds(514, 645, 139, 26);
				  		   			add(btnBloquerEmploy);
				  		   			
					  		    matricule.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboEmploye.setSelectedItem(mapCode.get(matricule.getText()));
								  			txtNumDossier.setText("");
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   			/*	if(matricule.getText().equals("") || comboEmploye.getSelectedItem().equals("") || txtNumDossier.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critère de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {*/
				  		   					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		   					
		  		     			  employe = employeDAO.findByCodeEmploye(matricule.getText(),txtNumDossier.getText(),depot.getId());
		  		     			
		  		     			  
		  		     			  if(employe!=null){
		  		     				  
		  		     				  txtNumCNSS.setText(employe.getNumCNSS());
		  		     				  txtCode.setText(employe.getMatricule());
		  		     				  txtAdresse.setText(employe.getAdresse());
		  		     				  txtNomEmploye.setText(employe.getNom());
		  		     				  txtTel.setText(employe.getNumTel());
		  		     				  txtNumDoss.setText(employe.getNumDossier());
		  		     				  comboRespon.setSelectedItem(employe.getTypeResEmploye().getLibelle());
		  		     				 checkSalarie.setSelected(employe.isSalarie());
		  		     				 chckbxActif.setSelected(employe.isActif());
		  		     				  dateNaissanceChooser.setDate(employe.getDateNaissance());
		  		     				  dateEntreChooser.setDate(employe.getDateEntre());
		  		     				  txtService.setText(employe.getService());
		  		     				  txtLieuNaiss.setText(employe.getLieuNaissance());
		  		     				  
		  		     				if(employe.getPrenom()!=null)
		  		     				{
		  		     					txtprenom.setText(employe.getPrenom());
		  		     				}
		  		     				if(employe.getSex()!=null)
		  		     				{
		  		     					combosex.setSelectedItem(employe.getSex());
		  		     				}
		  		     				  	  		     			
		  		     				if(employe.getSituation()!=null)
		  		     				{
		  		     					combosituation.setSelectedItem(employe.getSituation());
		  		     				}
		  		     				if(employe.getNombreEnfant()!=null)
		  		     				{
		  		     					txtnbrenfant.setText(employe.getNombreEnfant()+"");
		  		     				} 
		  		     				
		  		     				if(employe.getCin()!=null)
		  		     				{
		  		     					txtcin.setText(employe.getCin());
		  		     				}
		  		     				if(employe.getSociete()!=null)
		  		     				{
		  		     					txtsociete.setText(employe.getSociete());
		  		     				} 
		  		     				
		  		     				
		  		     				if(employe.getDateSortie()!=null)
		  		     				{
		  		     					dateSortieChooser.setDate(employe.getDateSortie());
		  		     				}
		  		     				
		  		     				if(employe.getDatePremiereProduction()!=null)
		  		     				{
		  		     					datePremiereProduction.setDate(employe.getDatePremiereProduction());
		  		     				}
		  		     				  
		  		     				  if(employe.getEquipe()!=null)
		  		     				 comboEquipe.setSelectedItem(employe.getEquipe().getNomEquipe());
		  		     				  else 
		  		     					 comboEquipe.setSelectedItem("");
		  		     			  }else {
		  		     				
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun résultat pour ces critères de recherche. Merci de vérifier votre critère !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     				intialiser ();
		  		     			  }
		  		     		// }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   			
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
						      	combosex.addItem("");	
								combosex.addItem(Constantes.SEX_FEMININ);	
								combosex.addItem(Constantes.SEX_MASCULIN);	
								
								combosituation.addItem("");	
								combosituation.addItem(Constantes.SITUATION_CELIBATAIRE);	
								combosituation.addItem(Constantes.SITUATION_MARIE);	
								combosituation.addItem(Constantes.SITUATION_DIVORCER);	
								combosituation.addItem(Constantes.SITUATION_VEUF);	
								
								JLabel lblDateereProduction = new JLabel("Date 1ere Production:");
								lblDateereProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
								lblDateereProduction.setBounds(6, 348, 130, 26);
								layeredPane.add(lblDateereProduction);
								
								  datePremiereProduction = new JDateChooser();
								datePremiereProduction.setDateFormatString("dd/MM/yyyy");
								datePremiereProduction.setBounds(140, 348, 173, 26);
								layeredPane.add(datePremiereProduction);
								
				  		
				  		 
	}
	
	
	

	
void intialiser (){
		
		txtNumCNSS.setText("");
		txtNumDossier.setText("");
		txtNumDoss.setText("");
		txtCode.setText("");
		txtAdresse.setText("");
		txtCoutHoraire.setText("");
		txtNomEmploye.setText("");
		txtTel.setText("");
		txtLieuNaiss.setText("");
		comboEquipe.setSelectedItem("");
		comboRespon.setSelectedItem("");
		txtService.setText("");
		dateEntreChooser.setDate(null);
		dateNaissanceChooser.setDate(null);
		comboEmploye.setSelectedIndex(-1);;
		matricule.setText("");
		chckbxActif.setSelected(false);
		checkSalarie.setSelected(false);
		txtprenom.setText("");
		combosex.setSelectedItem("");
		txtnbrenfant.setText("");
		combosituation.setSelectedItem("");
		txtcin.setText("");
		txtsociete.setText("");
		dateSortieChooser.setDate(null);
		
		
		
	}
}

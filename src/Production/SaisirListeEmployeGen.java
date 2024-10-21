package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

 

import util.Constantes;
import util.Utils;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Utilisateur;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.JTextArea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.jdesktop.swingx.JXTitledSeparator;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class SaisirListeEmployeGen extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	private JXTable tableEmployeFiltrer=new JXTable();;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	

	
	private Production production = new Production();
	private List<Employe> listEmployer =new ArrayList<Employe>();
	private List<DetailResponsableProd> listDetailResponsableProd=new ArrayList<DetailResponsableProd>();
	
	
	private Map< String, BigDecimal> mapDelai = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp25 = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp50 = new HashMap<>();
	private Map< String,Boolean> mapEmployeAbsent = new HashMap<>();
	private Map< String,Boolean> mapEmployeSortie = new HashMap<>();
	
	private Map< Integer, Employe> mapEmployeDelai = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp25 = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp50 = new HashMap<>();
	
	private Map< String, Employe> mapEmployeGlobal = new HashMap<>();
	private List <Integer> lsiteInt=new ArrayList<Integer>();
	
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private EmployeDAO employeDAO;
	private ProductionDAO productionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapNomEmploye = new HashMap<>();
	private int selectedRow ;
	
	private int compteur=0;
	
	String quantite;
	BigDecimal nbreHeure;
	private DetailProdResDAO detailProdResDAO;
	private JTextField txtcodeemployegenerique;
	private JTextField txtdelaigenerique;
	private JTextField txthsupp50generique;
	private JTextField txthsupp25generique;
	private JXTable tableEmployeGen =new JXTable();
	private DefaultTableModel	 modeleEquipeGen;
	Utilisateur utilisateur;
	 JCheckBox checksortiegenerique = new JCheckBox("Sortie");
	 JCheckBox checkretardemballage = new JCheckBox("Retard");
	 private JCheckBox checkretardgenerique;
	 JComboBox comboemployegenerique = new JComboBox();
	 JCheckBox checkabsentgenerique = new JCheckBox("Absent");
	 JButton btnAjouter = new JButton("");
	 JButton btnSupprimer = new JButton("");
	 private Map< String, BigDecimal> mapParametre = new HashMap<>();
	 List<DetailProdRes> listDetailResponsableProdTmp=new ArrayList<DetailProdRes>();
	 private JTextField txtNombreProduction = new JTextField();
	 JDateChooser dateProduction = new JDateChooser();
	 JComboBox comboPeriode = new JComboBox();
	 JComboBox comboDepot = new JComboBox();
	 private DepotDAO depotDAO;
	 private Map< String, Depot> depotMap = new HashMap<>();
	 private List<Depot> listDepot =new ArrayList<Depot>();
	 private ParametreDAO parametreDAO;
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	 
	public SaisirListeEmployeGen() {
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1335, 662);
        try {
        	javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
		         
        	compteur=0;
        	
        	
        	listEmployer =new ArrayList<Employe>();
        	
        	tableEmploye=new JXTable();
        	depotDAO= new DepotDAOImpl();
        	imgAjouter= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	imgSupp= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
        	employeDAO= new EmployeDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	compteurEmployeProdDAO= new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	  parametreDAO=new ParametreDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

       
        
		
             listEmployer=employeDAO.findByDepot(utilisateur.getCodeDepot());
             
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
              
				  		     
				  		     JButton btnValider = new JButton();
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		
				  		     		try {
				  		     			
				  		       		
					  		     		if(listDetailResponsableProdTmp.size()!=0)
					  		     		{
					  		     		if(comboDepot.getSelectedItem().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner Le depot SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		
					  		     		if(comboPeriode.getSelectedItem().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner La Période De Production SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     			
					  		     		if(dateProduction.getDate()==null)	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner La Date De Production SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}	
					  		     			
					  		     		if(txtNombreProduction.getText().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre De Production  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		
					  		     		
					  		     		if(Integer.valueOf(txtNombreProduction.getText())-0==0)
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre De Production Supérieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		
					  		     		
					  		     		
					  		     		Depot depot=depotMap.get(comboDepot.getSelectedItem().toString());
					  		     		
					  		     		List<DetailProdRes>	listDetailResponsableProdExistant=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateProduction.getDate(), dateProduction.getDate(), depot.getId(), "");
						  		  		if(listDetailResponsableProdExistant.size()!=0)
						  		  		{
						  		  			
						  		  		JOptionPane.showMessageDialog(null, "Une Liste d'equipe Generique Déja existant Dans cette Date de Production","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     			return;
				  		     			
						  		  		}
					  		     		
					  		     		
					  		     		
					  		     		for(int i=0;i<listDetailResponsableProdTmp.size();i++)
					  		     		{
					  		     			
					  		     			DetailProdRes detailProdRes=listDetailResponsableProdTmp.get(i);
					  		     			
					  		     			detailProdRes.setDateProduction(dateProduction.getDate());
					  		     			detailProdRes.setNbrProduction(Integer.valueOf(txtNombreProduction.getText()));
					  		     			detailProdRes.setCodeDepot(depot.getCode());
					  		     			
					  		     			CompteurEmployeProd compteurEmployeProd =compteurEmployeProdDAO.findByDateProdPeriode(dateProduction.getDate(),comboPeriode.getSelectedItem().toString(), detailProdRes.getEmploye().getId());
				  		  		   			 if(compteurEmployeProd !=null){
				  		  		   				 compteur=compteurEmployeProd.getCompteur()+1;
				  		  		   				 compteurEmployeProd.setCompteur(compteur);	
				  		  		   				 compteurEmployeProdDAO.edit(compteurEmployeProd);
				  		  		   				 
				  		  		   			 }else{
				  		  		   				 compteurEmployeProd= new CompteurEmployeProd();
				  		  		   				 compteurEmployeProd.setDateProd(dateProduction.getDate());
				  		  		   				 compteurEmployeProd.setPeriode(comboPeriode.getSelectedItem().toString());
				  		  		   				 compteurEmployeProd.setEmploye(detailProdRes.getEmploye());
				  		  		   				 compteurEmployeProd.setCompteur(1);
				  		  		   				 compteurEmployeProdDAO.add(compteurEmployeProd);
				  		  		   				 
				  		  		   			 	}
					  		     			
				  		  		   		detailProdResDAO.add(detailProdRes);
				  		  		   		
					  		     		}
					  		     		
					  		     		
					  		     		
					  		     		JOptionPane.showMessageDialog(null, "Opération Valider Avec Succée","Information",JOptionPane.INFORMATION_MESSAGE);
					  		     		
					  		     		
					  		     		listDetailResponsableProdTmp.clear();
					  		     		afficher_tableEmployeGen(listDetailResponsableProdTmp);
					  		     		Initialiser();
					  		     		ViderEmployeGenerique();
					  		     		
					  		     			
					  		     		}
					  		     		
										
				  		     		} catch (NumberFormatException e) {
										JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Nombre de Production et les heures  En Chiffre SVP ", "Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}

				  		     		
				  		     		
				  			
				  		     			
				  		     			
				  		     			
				  		     	
				  		     	
				  		     	
				  		     	}
				  		     });
				  		     btnValider.setText("Valider");
				  		   btnValider.setIcon(null);
				  		   btnValider.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     btnValider.setBounds(506, 517, 114, 40);
				  		   add(btnValider);
				  		     
				  		     JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator.getLayout();
				  		     gridBagLayout.rowWeights = new double[]{0.0};
				  		     gridBagLayout.rowHeights = new int[]{0};
				  		     gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		     titledSeparator.setTitle("Saisir D\u00E9lai Equipe Generique");
				  		     titledSeparator.setBounds(33, 22, 1092, 17);
				  		     add(titledSeparator);
				  		     
				  		     JLabel label = new JLabel("Code :");
				  		     label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label.setBounds(33, 175, 37, 26);
				  		     add(label);
				  		     
				  		     txtcodeemployegenerique = new JTextField();
				  		     txtcodeemployegenerique.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		

				  		     		
				  		     		if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
				  						if(employe!=null)
				  						{
				  							comboemployegenerique.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  		     		
				  		     		
				  		     		
				  		     	
				  		     	}
				  		     });
				  		     txtcodeemployegenerique.setColumns(10);
				  		     txtcodeemployegenerique.setBounds(70, 175, 77, 26);
				  		     add(txtcodeemployegenerique);
				  		     
				  		     JLabel label_1 = new JLabel("Employe :");
				  		     label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_1.setBounds(157, 176, 61, 26);
				  		     add(label_1);
				  		     
				  		      comboemployegenerique = new JComboBox();
				  		      comboemployegenerique.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		      		

				  		   	 		if(!comboemployegenerique.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployegenerique.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployegenerique.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 						  		      	
				  		      		
				  		      	}
				  		      });
				  		     comboemployegenerique.setSelectedIndex(-1);
				  		     comboemployegenerique.setBounds(208, 176, 274, 24);
				  		     add(comboemployegenerique);
				  		     
				  		     JLabel label_2 = new JLabel("Delai H :");
				  		     label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_2.setBounds(506, 175, 61, 26);
				  		     add(label_2);
				  		     
				  		     txtdelaigenerique = new JTextField();
				  		     txtdelaigenerique.setColumns(10);
				  		     txtdelaigenerique.setBounds(555, 178, 58, 26);
				  		     add(txtdelaigenerique);
				  		     
				  		     JLabel label_3 = new JLabel("H Supp 50% :");
				  		     label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_3.setBounds(619, 175, 77, 26);
				  		     add(label_3);
				  		     
				  		     txthsupp50generique = new JTextField();
				  		     txthsupp50generique.setColumns(10);
				  		     txthsupp50generique.setBounds(692, 175, 58, 26);
				  		     add(txthsupp50generique);
				  		     
				  		     JLabel label_4 = new JLabel("H Supp 25% :");
				  		     label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_4.setBounds(760, 176, 77, 26);
				  		     add(label_4);
				  		     
				  		     txthsupp25generique = new JTextField();
				  		     txthsupp25generique.setColumns(10);
				  		     txthsupp25generique.setBounds(833, 178, 58, 26);
				  		     add(txthsupp25generique);
				  		     
				  		      checkabsentgenerique = new JCheckBox("Absent");
				  		     checkabsentgenerique.setBounds(896, 181, 71, 23);
				  		     add(checkabsentgenerique);
				  		     
				  		      checksortiegenerique = new JCheckBox("Sortie");
				  		     checksortiegenerique.setBounds(968, 181, 71, 23);
				  		     add(checksortiegenerique);
				  		     
				  		      checkretardgenerique = new JCheckBox("Retard");
				  		     checkretardgenerique.setBounds(1047, 180, 78, 24);
				  		     add(checkretardgenerique);
				  		     
				  		     JScrollPane scrollPane = new JScrollPane((Component) null);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(33, 219, 1104, 282);
				  		     add(scrollPane);
				  		     
				  		     tableEmployeGen = new JXTable();
				  		     tableEmployeGen.setModel(new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     		"ID", "Matricule", "Nom", "D\u00E9lai Travaill\u00E9", "H Supp 25%", "H Supp 50%", "Absent", "Sortie", "Retard"
				  		     	}
				  		     ));
				  		     scrollPane.setViewportView(tableEmployeGen);
				  		     tableEmployeGen.setFillsViewportHeight(true);
				  		     
				  		     JButton button = new JButton("Vider");
				  		     button.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		ViderEmployeGenerique();
				  		     	}
				  		     });
				  		     button.setBounds(1147, 233, 58, 23);
				  		     add(button);
				  		     
				  		      btnAjouter = new JButton("");
				  		      btnAjouter.addActionListener(new ActionListener() {
				  		      	public void actionPerformed(ActionEvent arg0) {
				  		      		
				  		      		try {
				  		      			
				  		      			
				  		      			
				  		      			
				  		      			
				  		      			
				  		            	if(comboDepot.getSelectedItem().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner Le depot SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		
					  		     		if(comboPeriode.getSelectedItem().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner La Période De Production SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     			
					  		     		if(dateProduction.getDate()==null)	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "veuillez Selectionner La Date De Production SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}	
					  		     			
					  		     		if(txtNombreProduction.getText().equals(""))	
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre De Production  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		
					  		     		
					  		     		if(Integer.valueOf(txtNombreProduction.getText())-0==0)
					  		     		{
					  		     			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Nombre De Production Supérieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
					  		     		}
					  		     		Depot depot=depotMap.get(comboDepot.getSelectedItem().toString());
					  		     		
					  		     	List<DetailProdRes>	listDetailResponsableProdExistant=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateProduction.getDate(), dateProduction.getDate(), depot.getId(), "");
							  		  		if(listDetailResponsableProdExistant.size()!=0)
							  		  		{
							  		  		JOptionPane.showMessageDialog(null, "Une Liste d'equipe Generique Déja existant Dans cette Date de Production","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     			return;
							  		  		}
							  		  		
							  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
							  		  		boolean absent=false;
							  		  		boolean sortie=false;
							  		  	boolean retard=false;
							  		     		int idEmploye;
							  		     		BigDecimal delaiComplet = BigDecimal.ZERO,detailheurComplet25=BigDecimal.ZERO,detailheurComplet50=BigDecimal.ZERO;
							  		  		 

							  		     		BigDecimal coutSupp25=BigDecimal.ZERO;
							  		  		BigDecimal coutSupp50=BigDecimal.ZERO;
							  		  	//	BigDecimal coutHoraire=0;
							  		  		BigDecimal coutHoraireComplet=BigDecimal.ZERO;
							  		  		BigDecimal coutHoraireTotal=BigDecimal.ZERO;
							  		  	Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
							  		  		boolean existe=false;   
							  		  	if(employe!=null)
							  		  	{
							  		  		
							  		  		
							  		  		for(int i=0;i<listDetailResponsableProdTmp.size();i++)
							  		  		{
							  		  			
							  		  			if(listDetailResponsableProdTmp.get(i).getEmploye().getId()==employe.getId())
							  		  			{
							  		  			existe=true;
							  		  			}
							  		  		}
							  		  		
							  		  	}
							  		  	
							  		  	
							  		  	if(existe==true)
							  		  	{
							  		  		
							  		  		JOptionPane.showMessageDialog(null, "Emplye déja existant","Erreur",JOptionPane.ERROR_MESSAGE);
							  		  		return;
							  		  	}
							  		  	
							  		  	
							  		  	
							  		  	
							  		  	
							  		  	
							  		  	
							  		  	
							  		  		    	DetailProdRes detailResponsableProd=new DetailProdRes();
							  		  		    	detaildelai=BigDecimal.ZERO;
							  		  		    	detailheur25=BigDecimal.ZERO;
							  		  		    	detailheur50=BigDecimal.ZERO;
							  		  		    	absent=false;
							  		  		    	sortie=false;
							  		  		    retard=false;
							  		  		    //	int key=lsiteInt.get(i);
							  		  		    	
							  		  		    	
							  		  		    	if(employe!=null)
							  		  		    	{
							  		  		    		idEmploye=employe.getId();
							  		  		    	
							  		  		   
							  		  		    	if(checkabsentgenerique.isSelected()==true)
							  		  		    	{
							  		  		    		
							  		  		    	
							  		  		    	
							  		  		    	if(checkabsentgenerique.isSelected()==true)
							  		  		    	absent=true;
							  		  		    	
							  		  		    	detailResponsableProd.setCoutSupp25(BigDecimal.ZERO);
							  		  	    		detailResponsableProd.setCoutSupp50(BigDecimal.ZERO);
							  		  	    		detailResponsableProd.setCoutTotal(BigDecimal.ZERO);
							  		  		    	detailResponsableProd.setDelaiEmploye(BigDecimal.ZERO);
							  		  		    	detailResponsableProd.setHeureSupp25(BigDecimal.ZERO);
							  		  		    	detailResponsableProd.setHeureSupp50(BigDecimal.ZERO);
							  		  		    	
							  		  		        detailResponsableProd.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS));
				  		  		    		        detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		  		    	            detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
							  		  		    	
							  		  		    	detailResponsableProd.setEmploye(employe);
							  		  		        detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
							  		  		    	detailResponsableProd.setAbsent(absent);
							  		  		        detailResponsableProd.setSortie(false);
							  		  		        detailResponsableProd.setRetard(false);
							  		  		        detailResponsableProd.setAutorisation(false);
							  		  		        detailResponsableProd.setRemise(BigDecimal.ZERO);
							  		  		        detailResponsableProd.setValider(Constantes.ETAT_INVALIDER);
							  		  		    	detailResponsableProd.setDateProduction(dateProduction.getDate());
							  		  		       detailResponsableProd.setNbrProduction(Integer.valueOf(txtNombreProduction.getText()));
							  		  		    	//detailResponsableProd.setProduction(production);
							  		  		    	//listDetailResponsableProd.add(detailResponsableProd);
							  		  		    	//detailProdResDAO.add(detailResponsableProd);


							  		  		    		
							  		  		    	}else if(checkabsentgenerique.isSelected()==false){
							  		  		    	
							  		  		    		
							  		  		    	
							  		  		    		if(!txtdelaigenerique.getText().equals(""))
							  		  		    		{
							  		  		    			
							  		  		    			if(new BigDecimal(txtdelaigenerique.getText()).compareTo(BigDecimal.ZERO)  >0)
							  		  		    			{
							  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
							  		  		    				delaiComplet=detaildelai;///compteurProd;
							  		  		    				if(!employe.isSalarie()){
							  		  		    				
							  		  		    					
							  		  		    					if(checksortiegenerique.isSelected()==true)
							  		  		    					{
							  		  		    					sortie=true;
							  		  		    					}
							  		  			    				
							  		  		    				if(checkretardgenerique.isSelected()==true)
						  		  		    					{
						  		  		    					retard=true;
						  		  		    					}
							  		  		    					
							  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
							  		  		    				
							  		  		    			
							  		  		    				
							  		  		    				
							  		  				    		coutHoraireComplet=mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS).multiply(detaildelai);
							  		  				    		coutHoraireTotal=coutHoraireTotal.add(coutHoraireComplet);
							  		  		    				
							  		  		    				
							  		  		    				
							  		  		    				if(!txthsupp25generique.getText().equals(""))
							  		  		    					
							  		  		    				{
							  		  		    					if(new BigDecimal(txthsupp25generique.getText()).compareTo(BigDecimal.ZERO)  >0)
							  		  		    					{
							  		  		    					detailheur25=new BigDecimal(txthsupp25generique.getText());
							  		  		    					detailheurComplet25=detailheur25;///compteurProd;
							  		  		    		    		coutSupp25=detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
							  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp25);
							  		  		    					}
							  		  		    					
							  		  		    		    		
							  		  		    				}
							  		  		    				
							  		  		    		    	if(!txthsupp50generique.getText().equals(""))
							  		  		    		    		
							  		  		    		    	{
							  		  		    		    	if(new BigDecimal(txthsupp50generique.getText()).compareTo(BigDecimal.ZERO)  >0)
						  		  		    					{
							  		  		    		    	detailheur50=new BigDecimal(txthsupp50generique.getText());
						  		  		    		    		detailheurComplet50=detailheur50;//compteurProd;
						  		  		    		    		coutSupp50=detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
						  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp50);
						  		  		    					}
							  		  		    		    		
							  		  		    		    	}
							  		  		    		    	
							  		  		    			}
							  		  		    		    	/*
							  		  		    		    	 * */
							  		  		    			
							  		  		    				
							  		  		    				
							  		  		    		    	detailResponsableProd.setCoutSupp25(coutSupp25);
							  		  				    		detailResponsableProd.setCoutSupp50(coutSupp50);
							  		  				    		detailResponsableProd.setCoutTotal(coutHoraireComplet);
							  		  		    		    	detailResponsableProd.setDelaiEmploye(delaiComplet);
							  		  		    		    	detailResponsableProd.setHeureSupp25(detailheurComplet25);
							  		  		    		    	detailResponsableProd.setHeureSupp50(detailheurComplet50);
							  		  		    		    	
							  		  		    		    detailResponsableProd.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS));
							  		  		    		    detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
							  		  		    	        detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
							  		  		    	        detailResponsableProd.setRemise(BigDecimal.ZERO);
							  		  		    		    	detailResponsableProd.setEmploye(employe);
							  		  		    		       detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
							  		  		    		    	detailResponsableProd.setAbsent(absent);
							  		  		    		    	detailResponsableProd.setSortie(sortie);
							  		  		    		        detailResponsableProd.setRetard(retard);
							  		  		    		    detailResponsableProd.setAutorisation(false);
							  		  		    		    	//detailResponsableProd.setProduction(production);
							  		  		    		    	detailResponsableProd.setDateProduction(dateProduction.getDate());
							  		  		    		    detailResponsableProd.setNbrProduction(Integer.valueOf(txtNombreProduction.getText()));
							  		  		    		    //	detailProdResDAO.add(detailResponsableProd);
							  		  		    		
							  		  		    			 }
							  		  		    			
							  		  		    			
							  		  		    		
							  		  		    			
							  		  		    			
							  		  		    			
							  		  		    		   }
							  		  		    		
							  		  		    		  
							  		  		    		}
							  		  		    	
									  				
							  		  		    	listDetailResponsableProdTmp.add(detailResponsableProd);

									  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
							  		  		    	
							  		  		    	ViderEmployeGenerique();
							  		  		    	
							  		  		    	
							  		  		    	}
							  		  		    
				  		      			
				  		      			
				  		      			
										
									} catch (NumberFormatException e) {
										JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Nombre de Production et les heures  En Chiffre SVP ", "Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}

				  		
					  		  		      
					  		  	
					  		     		
					  		     		
					  		     		
					  		     		
					  		     	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     btnAjouter.setBounds(1147, 285, 58, 23);
				  		   btnAjouter.setIcon(imgAjouter);
				  		     add(btnAjouter);
				  		
				  		     
				  		      btnSupprimer = new JButton("");
				  		      btnSupprimer.addActionListener(new ActionListener() {
				  		      	public void actionPerformed(ActionEvent e) {
				  		      		

				  		     		

				  		     		
				  		     		
				  		     		if(listDetailResponsableProdTmp.size()!=0)
				  		     		{
				  		     			
				  		     			
				  		     			//int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
				  		     	 
						  				 
						  				
						  					if(tableEmployeGen.getSelectedRow()!=-1)
						  		     		{
						  		     			DetailProdRes detailResponsableProd=listDetailResponsableProdTmp.get(tableEmployeGen.getSelectedRow());
					  		     				
						  		     			
						  		     		
						  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
															"Satisfaction", JOptionPane.YES_NO_OPTION);
													 
													if(reponse == JOptionPane.YES_OPTION )
													{
														
														
													  
													  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
													 // detailProdResDAO.delete(detailResponsableProd.getId());
													  listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow());
													 // productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp);
													 // productionDAO.edit(productionTmp);
														
													  afficher_tableEmployeGen(listDetailResponsableProdTmp);
														
													}
						  		     				
						  		     		}
						  					
						  			 
				  		     			
				  		     			
				       			
				  		     		
					 
				  		     				  		     			
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     	
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     btnSupprimer.setBounds(1147, 317, 58, 23);
				  		   btnSupprimer.setIcon(imgSupp);
				  		     add(btnSupprimer);
	
				  		   comboemployegenerique.addItem(""); 
				  		   
				  		   JLabel lblDateProduction = new JLabel("Date Production :");
				  		   lblDateProduction.setBounds(489, 77, 95, 26);
				  		   add(lblDateProduction);
				  		   
				  		    dateProduction = new JDateChooser();
				  		   dateProduction.setLocale(Locale.FRANCE);
				  		   dateProduction.setDateFormatString("dd/MM/yyyy");
				  		   dateProduction.setBounds(594, 77, 172, 26);
				  		   add(dateProduction);
				  		   
				  		   JLabel lblNombreProduction = new JLabel("Nombre Production:");
				  		   lblNombreProduction.setBounds(776, 78, 145, 24);
				  		   add(lblNombreProduction);
				  		   
				  		   txtNombreProduction = new JTextField();
				  		   txtNombreProduction.setColumns(10);
				  		   txtNombreProduction.setBounds(918, 77, 130, 26);
				  		   add(txtNombreProduction);
				  		   
				  		   JLabel lblDpot = new JLabel("D\u00E9pot :");
				  		   lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblDpot.setBounds(26, 77, 58, 24);
				  		   add(lblDpot);
				  		   
				  		    comboDepot = new JComboBox();
				  		   comboDepot.setBounds(86, 78, 166, 24);
				  		   add(comboDepot);
				  		   
				  		   JLabel label_6 = new JLabel("P\u00E9riode :");
				  		   label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   label_6.setBounds(262, 78, 77, 24);
				  		   add(label_6);
				  		   
				  		    comboPeriode = new JComboBox();
				  		   comboPeriode.setBounds(322, 79, 157, 24);
				  		   add(comboPeriode);
				  		   for(int i=0;i<listEmployer.size();i++)
							  {
								  
								Employe employe=listEmployer.get(i);  
								
								comboemployegenerique.addItem(employe.getNomafficher());
								  mapMatriculeEmploye.put(employe.getMatricule(), employe);
							 mapNomEmploye.put(employe.getNomafficher(), employe);
							  
							  
							  }  
				  		
				  		  mapParametre=Utils.listeParametre();
				  		  
				  		 comboPeriode.addItem("");
				     	comboPeriode.addItem(PRPDUCTION_PERIODE_MATIN);
				     	comboPeriode.addItem(PRPDUCTION_PERIODE_SOIR);
				     	 
				   		comboDepot.addItem("");	  
				   		
				   		JButton btnInitialiser = new JButton("Initialiser");
				   		btnInitialiser.addActionListener(new ActionListener() {
				   			public void actionPerformed(ActionEvent e) {
				   				Initialiser();
				   			}
				   		});
				   		btnInitialiser.setBounds(1108, 79, 97, 24);
				   		add(btnInitialiser);
				   	int i=0;
						  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
					  			Depot	 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
						    		depotMap.put(depot.getLibelle(), depot);
			  		      			comboDepot.addItem(depot.getLibelle());
			  		      			 
						    }else {
					  		  listDepot = depotDAO.findAll();	
					  		       i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			depotMap.put(depot.getLibelle(), depot);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			 
					  		      			i++;
					  		      		}
						    }	 
						  
						  
						  initialiserTableauEmployeGen();
				  		  
				  		  
	}
	
	public void ViderEmployeGenerique()
	{
		
		txtcodeemployegenerique.setText("");
		comboemployegenerique.setSelectedItem("");
		txtdelaigenerique.setText("");
		txthsupp25generique.setText("");
		txthsupp50generique.setText("");
		checkabsentgenerique.setSelected(false);
		checksortiegenerique.setSelected(false);
		checkretardgenerique.setSelected(false);
		txtcodeemployegenerique.requestFocus();
		
	}
	
public	void Initialiser()
	{
		comboDepot.setSelectedItem("");
		dateProduction.setDate(null);
		comboPeriode.setSelectedItem("");
		txtNombreProduction.setText("");
		
	}
		     	
	void afficher_tableEmployeGen(List<DetailProdRes> listDetailResponsableProd)
	{
	initialiserTableauEmployeGen();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
		  int i=0;
			while(i<listDetailResponsableProd.size())
			{	
				DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
				delai=detailResponsableProd.getDelaiEmploye();
				heureSupp25=detailResponsableProd.getHeureSupp25();
				heureSupp50=detailResponsableProd.getHeureSupp50();
				absent=detailResponsableProd.isAbsent();
				sortie=detailResponsableProd.isSortie();
				retard=detailResponsableProd.isRetard();
				Object []ligne={detailResponsableProd.getEmploye().getId(),detailResponsableProd.getEmploye().getMatricule(),detailResponsableProd.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard};

				modeleEquipeGen.addRow(ligne);
				i++;
			}
			tableEmployeGen.setModel(modeleEquipeGen);
	}  
	

	void initialiserTableauEmployeGen(){
		modeleEquipeGen =new DefaultTableModel(
			     	new Object[][] {
			     	},
			     	new String[] {
			     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard"
			     	}
			     ) {
	     	boolean[] columnEditables = new boolean[] {
	     			false,false,false,true,true,true,true,true,true
	     	};
	    
	     	Class[] columnTypes = new Class[] {
	     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class,Boolean.class
				};
	      	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     };
			   tableEmployeGen.setModel(modeleEquipeGen); 
			   tableEmployeGen.getColumnModel().getColumn(0).setPreferredWidth(1);
			   tableEmployeGen.getColumnModel().getColumn(1).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(2).setPreferredWidth(160);
			   tableEmployeGen.getColumnModel().getColumn(3).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(4).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(5).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(6).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(7).setPreferredWidth(60);
			   tableEmployeGen.getColumnModel().getColumn(8).setPreferredWidth(60);
			   tableEmployeGen.getTableHeader().setReorderingAllowed(false);
	}




void remplirMapEmployeGlobal(){
	

	for(int i=0;i<listEmployer.size();i++)
	{	
		Employe employer=listEmployer.get(i);
		
		mapEmployeGlobal.put(employer.getNumDossier(), employer);
		
	}


}







}

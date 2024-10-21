package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.ExcelUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.entity.CompteurEmployeProd;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;
import dao.entity.Parametre;
import dao.entity.SituationDesEmployeesAbsents;
import dao.entity.SituationDesEmployeesAbsentsParJour;
import dao.entity.Utilisateur;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class SituationDesAbsents extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapEmploye = new HashMap<>();
	private List<SituationDesEmployeesAbsents> listFicheEmploye=new ArrayList<SituationDesEmployeesAbsents>();
	private List<FicheEmployeGlobale> listFicheEmployeGlobale=new ArrayList<FicheEmployeGlobale>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private List<SituationDesEmployeesAbsents> listFicheEmployeAbsentTmp=new ArrayList<SituationDesEmployeesAbsents>();
	private List<SituationDesEmployeesAbsentsParJour> listFicheEmployeAbsentParJour=new ArrayList<SituationDesEmployeesAbsentsParJour>();
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private ParametreDAO parametreDAO;
	JLabel labelmatricule = new JLabel("Matricule :");
	JLabel labelemploye = new JLabel("Employe :");
	JComboBox comboemploye = new JComboBox();
	JCheckBox chckbxAbsentsParJour = new JCheckBox("Absents Par Jour");
	JComboBox comboequipe = new JComboBox();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";
	private JTextField txtmatricule=new JTextField();
	public SituationDesAbsents() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 689);
        try{
        	
        	
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	detailProductionDAO=new DetailProductionDAOImpl();
        	detailResponsableDAO=new DetailResponsableProdDAOImpl();
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	detailProductionMPDAO=new DetailProductionMPDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	listEmploye=employeDAO.findAllEmploye();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
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
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
							     			"Matricule","Nom Employer", "MOIS 1","MOIS 2", "MOIS 3", "MOIS 4", "MOIS 5","MOIS 6" , "MOIS 7","MOIS 8","MOIS 9","MOIS 10", "MOIS 11","MOIS 12","TOTAL"
					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false, false, false, false, false, false, false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
						     			"Matricule","Nom Employer", "MOIS 1","MOIS 2", "MOIS 3", "MOIS 4", "MOIS 5","MOIS 6" , "MOIS 7","MOIS 8","MOIS 9","MOIS 10", "MOIS 11","MOIS 12","TOTAL"
					  		 			
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false,false, false, false, false, false, false, false, false,false
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 110, 1427, 497);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1082, 90);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 12, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(227, 11, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1041, 12, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listFicheEmploye.clear();
				listFicheEmployeGlobale.clear();
				listFicheEmployeAbsentParJour.clear();
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
		
			}
			
			
			
			/*else if(!verifierdate())
					{
				JOptionPane.showMessageDialog(null,message, "Erreur", JOptionPane.ERROR_MESSAGE);
					
						return;	
			
				}*/else {
					

					 SimpleDateFormat simpleFormatMonth = new SimpleDateFormat("MM");
						
					
					
				if(chckbxAbsentsParJour.isSelected()==true)
				{
					
					
				if(!simpleFormatMonth.format(dateDebutChooser.getDate()).equals(simpleFormatMonth.format(dateFinChooser.getDate())))	
				{
					
					JOptionPane.showMessageDialog(null, "Le Mois de date debut doit etre le meme mois de date fin SVP !!!!");
					return;
					
				}else
				{
					
					listFicheEmployeAbsentParJour=calculeCoutEmployeParJour();
					if(listFicheEmployeAbsentParJour.size()!=0)
					{
						afficher_AbsentParJour(listFicheEmployeAbsentParJour);
					}
					
					
					
				}
					
					
					
				}else
				{
	
					
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					listFicheEmploye=calculeCoutEmploye();
			
					
					
					Collections.sort(listFicheEmploye, new Comparator<SituationDesEmployeesAbsents>() {
						  @Override
						  public int compare(SituationDesEmployeesAbsents u1, SituationDesEmployeesAbsents u2) {
						    return u1.getEquipe().compareTo(u2.getEquipe());
						  }
						});
					
					
					//AjouterDetailProdResponsable();
					
					//calculerTotaux(listFicheEmploye);
					if(listFicheEmploye==null ||  listFicheEmploye.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						
					}else {
					
						afficher_tableMP(listFicheEmploye);
					}
					
				}
					
				
					
					
				}
			
			}
		  }
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 12, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(289, 12, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(460, 10, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(519, 9, 191, 26);
		layeredPane.add(comboDepot);
		
		 chckbxAbsentsParJour = new JCheckBox("Absents Par Jour");
		chckbxAbsentsParJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxAbsentsParJour.isSelected()==true)
				{
					labelemploye.setVisible(true);
					labelmatricule.setVisible(true);
					txtmatricule.setVisible(true);
					comboemploye.setVisible(true);
					
					txtmatricule.setText("");
					comboemploye.setSelectedItem("");
					
				}else
				{
					labelemploye.setVisible(false);
					labelmatricule.setVisible(false);
					txtmatricule.setVisible(false);
					comboemploye.setVisible(false);
					txtmatricule.setText("");
					comboemploye.setSelectedItem("");
				}
				
				
			}
		});
		chckbxAbsentsParJour.setBounds(20, 51, 161, 23);
		layeredPane.add(chckbxAbsentsParJour);
		
		 labelmatricule = new JLabel("Matricule :");
		labelmatricule.setBounds(248, 47, 70, 26);
		layeredPane.add(labelmatricule);
		labelmatricule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelmatricule.setVisible(false);
		
		txtmatricule = new JTextField();
		txtmatricule.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				

				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					if(!txtmatricule.getText().equals(""))
					{
						Employe employe=	mapMatriculeEmploye.get(txtmatricule.getText());
						if(employe!=null)
						{
							comboemploye.setSelectedItem(employe.getNomafficher());
						}else
						{
							JOptionPane.showMessageDialog(null, "Matricule Employe Introuvable");
							comboemploye.setSelectedItem("");
							return;
						}
					}else
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le Matricule Employe SVP");
						comboemploye.setSelectedItem("");
						return;
					}
					
			
					
					
	      		}
				
				
				
				
				
			
				
				
				
			}
		});
		txtmatricule.setBounds(316, 48, 124, 26);
		layeredPane.add(txtmatricule);
		txtmatricule.setColumns(10);
		txtmatricule.setVisible(false);
		 labelemploye = new JLabel("Employe :");
		labelemploye.setBounds(460, 47, 84, 26);
		layeredPane.add(labelemploye);
		labelemploye.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelemploye.setVisible(false);
		 comboemploye = new JComboBox();
		 comboemploye.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {

		 		if(comboemploye.getSelectedIndex()!=-1)
		 		{
		 			if(!comboemploye.getSelectedItem().equals(""))
			 		{
			 			Employe employe=mapEmploye.get(comboemploye.getSelectedItem());
			 			txtmatricule.setText(employe.getMatricule());
			 				
			 		}else
			 		{
			 			txtmatricule.setText("");
			 		}
			 		
		 		}
		 	
		 		
		 	
		 		
		 		
		 		
		 	}
		 });
		comboemploye.setBounds(527, 47, 218, 26);
		layeredPane.add(comboemploye);
		comboemploye.setVisible(false);
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
	
		JButton btnImprimer = new JButton("Fiche Des Absents");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
				if(listFicheEmployeAbsentTmp.size()!=0)
				{
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
		  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
					// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					// FicheEmploye ficheEmploye=listFicheEmployeGlobale.get(0);
					Map parameters = new HashMap();
					parameters.put("dateDu", "Du : "+dateDu);
					parameters.put("dateAu", "Au : "+dateAu);
					parameters.put("depot", comboDepot.getSelectedItem().toString());
						
					JasperUtils.imprimerSituationEmployeAbsent(listFicheEmployeAbsentTmp,parameters);
					
					
					
				}else if(listFicheEmployeAbsentParJour.size()!=0)
				{
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
		  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
					// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					// FicheEmploye ficheEmploye=listFicheEmployeGlobale.get(0);
					Map parameters = new HashMap();
					parameters.put("dateDu", "Du : "+dateDu);
					parameters.put("dateAu", "Au : "+dateAu);
					parameters.put("depot", comboDepot.getSelectedItem().toString());
						
					JasperUtils.imprimerSituationEmployeAbsentParJours(listFicheEmployeAbsentParJour,parameters);
					
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La list des employés est vide !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);	
				}
					
				
	  		  	
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(439, 635, 155, 24);
		add(btnImprimer);
		comboemploye.addItem("");
		
		JLabel lblEquipe = new JLabel("Equipe :");
		lblEquipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEquipe.setBounds(734, 11, 73, 26);
		layeredPane.add(lblEquipe);
		
		 comboequipe = new JComboBox();
		comboequipe.setBounds(793, 10, 191, 26);
		layeredPane.add(comboequipe);
	for(int i=0;i<listEmploye.size();i++)
	{
		
		Employe employe=listEmploye.get(i);
		comboemploye.addItem(employe.getNomafficher());
		mapEmploye.put(employe.getNomafficher(), employe);
		mapMatriculeEmploye.put(employe.getMatricule(), employe);
		
	}
		
		
		
	comboequipe.addItem("");

	comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
	comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
	comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
	comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
		
		
		
			  		 
	}
	
void afficher_tableMP(List<SituationDesEmployeesAbsents> listFicheEmploye )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listFicheEmploye.size())
		{
			SituationDesEmployeesAbsents situationDesEmployeesAbsents=listFicheEmploye.get(j);
			
			
			
			situationDesEmployeesAbsents.setTotal(String.valueOf( new BigDecimal(situationDesEmployeesAbsents.getMois1()).add(new BigDecimal(situationDesEmployeesAbsents.getMois2()).add(new BigDecimal(situationDesEmployeesAbsents.getMois3()).add(new BigDecimal(situationDesEmployeesAbsents.getMois4()).add(new BigDecimal(situationDesEmployeesAbsents.getMois5()).add(new BigDecimal(situationDesEmployeesAbsents.getMois6()).add(new BigDecimal(situationDesEmployeesAbsents.getMois7()).add(new BigDecimal(situationDesEmployeesAbsents.getMois8()).add(new BigDecimal(situationDesEmployeesAbsents.getMois9()).add(new BigDecimal(situationDesEmployeesAbsents.getMois10()).add(new BigDecimal(situationDesEmployeesAbsents.getMois11()).add(new BigDecimal(situationDesEmployeesAbsents.getMois12()))))))))))))));
			
			if(new BigDecimal(situationDesEmployeesAbsents.getMois1()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois1("-");
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getMois2()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois2("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois3()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois3("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois4()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois4("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois5()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois5("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois6()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois6("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois7()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois7("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois8()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois8("-");
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getMois9()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois9("-");
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getMois10()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois10("-");
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getMois11()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois11("-");
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getMois12()).compareTo(BigDecimal.ZERO)==0)
			{
				situationDesEmployeesAbsents.setMois12("-");
			}
			
			
				Object []ligne={situationDesEmployeesAbsents.getEmploye().getMatricule(),situationDesEmployeesAbsents.getEmploye().getNom(), situationDesEmployeesAbsents.getEquipe(),situationDesEmployeesAbsents.getMois1(),situationDesEmployeesAbsents.getMois2(),situationDesEmployeesAbsents.getMois3(),situationDesEmployeesAbsents.getMois4(),situationDesEmployeesAbsents.getMois5(),situationDesEmployeesAbsents.getMois6(),situationDesEmployeesAbsents.getMois7(),situationDesEmployeesAbsents.getMois8(),situationDesEmployeesAbsents.getMois9(),situationDesEmployeesAbsents.getMois10(),situationDesEmployeesAbsents.getMois11(),situationDesEmployeesAbsents.getMois12(),situationDesEmployeesAbsents.getTotal()};

				modeleMP.addRow( ligne);
				j++;
			}
			
			
	}



void afficher_AbsentParJour(List<SituationDesEmployeesAbsentsParJour> listFicheEmploye )
{
	intialiserTableauParJours();
	
	String J_1,J_2,J_3,J_4 =null;
	String J_5,J_6,J_7,J_8= null;
	String J_9,J_10,J_11,J_12= null;
	String J_13,J_14,J_15,J_16= null;
	String J_17,J_18,J_19,J_20= null;
	String J_21,J_22,J_23,J_24= null;
	String J_25,J_26,J_27,J_28= null;
	String J_29,J_30,J_31,Total=null;
	
	int j=0;
	while(j<listFicheEmploye.size())
	{
		SituationDesEmployeesAbsentsParJour situationDesEmployeesAbsentsparJour=listFicheEmploye.get(j);
		
		situationDesEmployeesAbsentsparJour.setTotal(String.valueOf( new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_1()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_2()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_3()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_4()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_5()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_6()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_7()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_8()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_9()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_10()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_11()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_12()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_13()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_14()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_15()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_16()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_17()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_18()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_19()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_20()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_21()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_22())).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_23()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_24()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_25()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_26()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_27()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_28()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_29()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_30()).add(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_31()))))))))))))))))))))))))))))))));		
			
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_1()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_1("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_2()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_2("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_3()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_3("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_4()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_4("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_5()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_5("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_6()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_6("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_7()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_7("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_8()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_8("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_9()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_9("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_10()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_10("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_11()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_11("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_12()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_12("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_13()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_13("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_14()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_14("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_15()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_15("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_16()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_16("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_17()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_17("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_18()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_18("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_19()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_19("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_20()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_20("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_21()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_21("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_22()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_22("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_23()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_23("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_24()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_24("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_25()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_25("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_26()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_26("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_27()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_27("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_28()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_28("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_29()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_29("-");
		}
		
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_30()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_30("-");
		}
		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getJ_31()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setJ_31("-");
		}

		if(new BigDecimal(situationDesEmployeesAbsentsparJour.getTotal()).compareTo(BigDecimal.ZERO)==0)
		{
			situationDesEmployeesAbsentsparJour.setTotal("-");
		}
		Object []ligne={situationDesEmployeesAbsentsparJour.getEmploye().getMatricule(),situationDesEmployeesAbsentsparJour.getEmploye().getNom(), situationDesEmployeesAbsentsparJour.getEquipe(),situationDesEmployeesAbsentsparJour.getJ_1(),situationDesEmployeesAbsentsparJour.getJ_2(),situationDesEmployeesAbsentsparJour.getJ_3(),situationDesEmployeesAbsentsparJour.getJ_4(),situationDesEmployeesAbsentsparJour.getJ_5(),situationDesEmployeesAbsentsparJour.getJ_6(),situationDesEmployeesAbsentsparJour.getJ_7(),situationDesEmployeesAbsentsparJour.getJ_8(),situationDesEmployeesAbsentsparJour.getJ_9(),situationDesEmployeesAbsentsparJour.getJ_10(),situationDesEmployeesAbsentsparJour.getJ_11(),situationDesEmployeesAbsentsparJour.getJ_12(),situationDesEmployeesAbsentsparJour.getJ_13(),situationDesEmployeesAbsentsparJour.getJ_14(),situationDesEmployeesAbsentsparJour.getJ_15(),situationDesEmployeesAbsentsparJour.getJ_16(),situationDesEmployeesAbsentsparJour.getJ_17(),situationDesEmployeesAbsentsparJour.getJ_18(),situationDesEmployeesAbsentsparJour.getJ_19(),situationDesEmployeesAbsentsparJour.getJ_20(),situationDesEmployeesAbsentsparJour.getJ_21(),situationDesEmployeesAbsentsparJour.getJ_22(),situationDesEmployeesAbsentsparJour.getJ_23(),situationDesEmployeesAbsentsparJour.getJ_24(),situationDesEmployeesAbsentsparJour.getJ_25(),situationDesEmployeesAbsentsparJour.getJ_26(),situationDesEmployeesAbsentsparJour.getJ_27(),situationDesEmployeesAbsentsparJour.getJ_28(),situationDesEmployeesAbsentsparJour.getJ_29(),situationDesEmployeesAbsentsparJour.getJ_30(),situationDesEmployeesAbsentsparJour.getJ_31(),situationDesEmployeesAbsentsparJour.getTotal()};

			modeleMP.addRow( ligne);
			j++;
	}
		
		
}







//void validerAvance(List<FicheEmploye> listFicheEmploye){
//	BigDecimal avance=0;
//	BigDecimal totalAvance=0;
//	BigDecimal totalPrime=0;
//	BigDecimal totalCout=0;
//	BigDecimal totalDu=0;
//	for(int i=0;i<listFicheEmploye.size();i++){	
//		
//		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		
//		ficheEmploye.setAvance(avance);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		totalAvance=totalAvance+avance;
//		totalPrime=totalPrime+ficheEmploye.getRemise();
//		totalCout=totalCout+ficheEmploye.getCoutTotal();
//		
//		ficheEmployeDAO.edit(ficheEmploye);
//		
//	}
//	
//	
//	totalDu=(totalPrime+totalCout)-totalAvance;
//	 DecimalFormat format = new DecimalFormat("#.00");
//	
//	
//	
//}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matricule","Nom Employer","Equipe", "MOIS 1","MOIS 2", "MOIS 3", "MOIS 4", "MOIS 5","MOIS 6" , "MOIS 7","MOIS 8","MOIS 9","MOIS 10", "MOIS 11","MOIS 12","TOTAL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false,false, false, false, false, false, false, false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(90);
      table.getColumnModel().getColumn(5).setPreferredWidth(90);
      table.getTableHeader().setReorderingAllowed(false);
      //table.getColumnModel().getColumn(6).setPreferredWidth(90);
}

void intialiserTableauParJours(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matricule","Nom Employer","Equipe", "J 1","J 2","J 3","J 4","J 5","J 6","J 7","J 8","J 9","J 10","J 10","J 12","J 13","J 14","J 15","J 16","J 17","J 18","J 19","J 20","J 21","J 22","J 23","J 24","J 25","J 26","J 27","J 28","J 29","J 30","J 31","Total"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false, false, false,false, false, false, false, false,false,false,false,false,false,false,false,false, false, false, false, false, false, false, false,false,false,false,false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
     table.getColumnModel().getColumn(1).setPreferredWidth(60);
     table.getColumnModel().getColumn(2).setPreferredWidth(20);
     table.getColumnModel().getColumn(3).setPreferredWidth(20);
     table.getColumnModel().getColumn(4).setPreferredWidth(20);
     table.getColumnModel().getColumn(5).setPreferredWidth(20);
     table.getColumnModel().getColumn(6).setPreferredWidth(20);
     table.getColumnModel().getColumn(7).setPreferredWidth(20);
     table.getColumnModel().getColumn(8).setPreferredWidth(20);
     table.getColumnModel().getColumn(9).setPreferredWidth(20);
     table.getColumnModel().getColumn(10).setPreferredWidth(20);
     table.getColumnModel().getColumn(11).setPreferredWidth(20);
     table.getColumnModel().getColumn(12).setPreferredWidth(20);
     table.getColumnModel().getColumn(13).setPreferredWidth(20);
     table.getColumnModel().getColumn(14).setPreferredWidth(20);
     table.getColumnModel().getColumn(15).setPreferredWidth(20);
     table.getColumnModel().getColumn(16).setPreferredWidth(20);
     table.getColumnModel().getColumn(17).setPreferredWidth(20);
     table.getColumnModel().getColumn(18).setPreferredWidth(20);
     table.getColumnModel().getColumn(19).setPreferredWidth(20);
     table.getColumnModel().getColumn(20).setPreferredWidth(20);
     table.getColumnModel().getColumn(21).setPreferredWidth(20);
     table.getColumnModel().getColumn(22).setPreferredWidth(20);
     table.getColumnModel().getColumn(23).setPreferredWidth(20);
     table.getColumnModel().getColumn(24).setPreferredWidth(20);
     table.getColumnModel().getColumn(25).setPreferredWidth(20);
     table.getColumnModel().getColumn(26).setPreferredWidth(20);
     table.getColumnModel().getColumn(27).setPreferredWidth(20);
     table.getColumnModel().getColumn(28).setPreferredWidth(20);
     table.getColumnModel().getColumn(29).setPreferredWidth(20);
     table.getColumnModel().getColumn(30).setPreferredWidth(20);
     table.getColumnModel().getColumn(31).setPreferredWidth(20);
     table.getColumnModel().getColumn(32).setPreferredWidth(20);
     table.getColumnModel().getColumn(33).setPreferredWidth(20);
     table.getColumnModel().getColumn(34).setPreferredWidth(20);
     table.getTableHeader().setReorderingAllowed(false);
     //table.getColumnModel().getColumn(6).setPreferredWidth(90);
}




boolean verifierdate()
{
	    SimpleDateFormat simpleFormatyear = new SimpleDateFormat("yyyy");
	    SimpleDateFormat simpleFormatMonth = new SimpleDateFormat("MM");
	    SimpleDateFormat simpleFormatDay = new SimpleDateFormat("dd");
	    
	boolean valider=true;
	if(Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate())) < Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate())))
	{
		if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))==Integer.valueOf(simpleFormatMonth.format(dateFinChooser.getDate())))
		{
			if(Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())).equals(Integer.valueOf(simpleFormatyear.format(dateFinChooser.getDate()))))
			{
				if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))!=2)
				{
					if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==30))
					{
						
						
					}else
					{
						message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/01/2018 et Date Fin : 15/01/2018 ou Date debut : 16/01/2018 et Date Fin : 30/01/2018 ";
						
						valider=false;
						
					}
					
				}else if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))==2)
				{
					
					 if ((Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 400 == 0) || ((Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 4 == 0) && (Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 100 != 0)))
					 { 
						 if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==29))
							 
							{
								
							}else
							{
								message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/02/2018 et Date Fin : 15/02/2018 ou Date debut : 16/02/2018 et Date Fin : 29/02/2018 ";

								valider=false;
							}
						 
					 }else
					 {
						 if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==28))
							 
							{
								
								
							}else
							{
								message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/02/2018 et Date Fin : 15/02/2018 ou Date debut : 16/02/2018 et Date Fin : 28/02/2018 ";
								valider=false;
							
							}
						
					 }
					
				}
				
				
			}else
			{
				message="L'année debut doit etre le meme année de la date de fin";
				valider=false;
			}
			
		}else
		{
			message= "Le mois de debut doit etre le meme mois de la date de fin";
			valider=false;
		}
		
	}else
	{
		message= "Le jour de date debut doit etre inferieur au jour de date de fin";
		valider=false;
	}
	
	return valider;
}


@SuppressWarnings("deprecation")
List<SituationDesEmployeesAbsents> calculeCoutEmploye(){
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	String requet="";
	listFicheEmployeAbsentTmp.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	
	List<DetailProduction> listDetailProduction=new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenerique=new ArrayList<DetailProdGen>();
	List<DetailProdRes> listDetailResponsableProd=new ArrayList<DetailProdRes>();
	List<DetailProductionMP> listDetailProductionMP=new ArrayList<DetailProductionMP>();
	
	if(comboequipe.getSelectedItem().equals(""))
	{

		 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);
		 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);
		 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),requet);
		 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);
	}else
	{
		
		if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION))
		{
			
			 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE))
		{
			 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE))
		{
			 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),requet);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP))
		{
			 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requet);

		}
		
		
		
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////// Detail production /////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listDetailProduction.size();i++){
		

		DetailProduction detailProduction =listDetailProduction.get(i);
		

	
		
		
		if(!detailProduction.getEmploye().isSalarie()){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
			{
				
				SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
				
				if(detailProduction.getEmploye().getId()==ficheEmploye.getEmploye().getId()  )
				{
					Trouve=true;
					
					if( detailProduction.isAbsent()==true)
					{
						if(detailProduction.getProduction().getDate().getMonth()+1==1)	
						{
							ficheEmploye.setMois1(String.valueOf(new BigDecimal(ficheEmploye.getMois1()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==2)
						{
							ficheEmploye.setMois2(String.valueOf(new BigDecimal(ficheEmploye.getMois2()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==3)
						{
							ficheEmploye.setMois3(String.valueOf(new BigDecimal(ficheEmploye.getMois3()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==4)
						{
							ficheEmploye.setMois4(String.valueOf(new BigDecimal(ficheEmploye.getMois4()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==5)
						{
							ficheEmploye.setMois5(String.valueOf(new BigDecimal(ficheEmploye.getMois5()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==6)
						{
							ficheEmploye.setMois6(String.valueOf(new BigDecimal(ficheEmploye.getMois6()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==7)
						{
							ficheEmploye.setMois7(String.valueOf(new BigDecimal(ficheEmploye.getMois7()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==8)
						{
							ficheEmploye.setMois8(String.valueOf(new BigDecimal(ficheEmploye.getMois8()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==9)
						{
							ficheEmploye.setMois9(String.valueOf(new BigDecimal(ficheEmploye.getMois9()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==10)
						{
							ficheEmploye.setMois10(String.valueOf(new BigDecimal(ficheEmploye.getMois10()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==11)
						{
							ficheEmploye.setMois11(String.valueOf(new BigDecimal(ficheEmploye.getMois11()).add(BigDecimal.ONE)));
							
						}else if(detailProduction.getProduction().getDate().getMonth()+1==12)
						{
							ficheEmploye.setMois12(String.valueOf(new BigDecimal(ficheEmploye.getMois12()).add(BigDecimal.ONE)));
							
						}
						


						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);	 
							
							
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
					}
					
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
			
			

		
			


if(detailProduction.isAbsent()==true)
{
	SituationDesEmployeesAbsents ficheEmployeTmp =new SituationDesEmployeesAbsents();
	
	ficheEmployeTmp.setEmploye(detailProduction.getEmploye());
	
	if(detailProduction.getProduction().getDate().getMonth()+1==1)	
	{
		ficheEmployeTmp.setMois1(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois1(String.valueOf(BigDecimal.ZERO));
	}

	if(detailProduction.getProduction().getDate().getMonth()+1==2)
	{
		ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==3)
	{
		ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ZERO));
	}
	 
	 
	 if(detailProduction.getProduction().getDate().getMonth()+1==4)
	{
		ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==5)
	{
		ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==6)
	{
		ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==7)
	{
		ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==8)
	{
		ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==9)
	{
		ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ZERO));
	}

	 if(detailProduction.getProduction().getDate().getMonth()+1==10)
	{
		ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ZERO));
	}
	 if(detailProduction.getProduction().getDate().getMonth()+1==11)
	{
		ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ZERO));
	}
	 
	 if(detailProduction.getProduction().getDate().getMonth()+1==12)
	{
		ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ONE));
		
	}else
	{
		ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ZERO));
	}

	 ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);		 
				
				 listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
}


			
		}
		}
		
		
	}
		
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// Detail ProductionMP //////////////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailProductionMP.size();i++){
		
	  
			
			DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
			
			
				
		
			
			
			
			
			if(!detailProductionMP.getEmploye().isSalarie()){
				
				Boolean Trouve=false;
				
				for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
				{
					
					SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
					
					if(detailProductionMP.getEmploye().getId()==ficheEmploye.getEmploye().getId()   )
					{
						Trouve=true;
if(detailProductionMP.isAbsent()==true)
{
	
	if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==1)	
	{
		ficheEmploye.setMois1(String.valueOf(new BigDecimal( ficheEmploye.getMois1()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==2)
	{
		ficheEmploye.setMois2(String.valueOf(new BigDecimal(ficheEmploye.getMois2()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==3)
	{
		ficheEmploye.setMois3(String.valueOf(new BigDecimal(ficheEmploye.getMois3()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==4)
	{
		ficheEmploye.setMois4(String.valueOf(new BigDecimal(ficheEmploye.getMois4()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==5)
	{
		ficheEmploye.setMois5(String.valueOf(new BigDecimal(ficheEmploye.getMois5()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==6)
	{
		ficheEmploye.setMois6(String.valueOf(new BigDecimal(ficheEmploye.getMois6()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==7)
	{
		ficheEmploye.setMois7(String.valueOf(new BigDecimal(ficheEmploye.getMois7()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==8)
	{
		ficheEmploye.setMois8(String.valueOf(new BigDecimal(ficheEmploye.getMois8()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==9)
	{
		ficheEmploye.setMois9(String.valueOf(new BigDecimal(ficheEmploye.getMois9()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==10)
	{
		ficheEmploye.setMois10(String.valueOf(new BigDecimal(ficheEmploye.getMois10()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==11)
	{
		ficheEmploye.setMois11(String.valueOf(new BigDecimal(ficheEmploye.getMois11()).add(BigDecimal.ONE)));
		
	}else if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==12)
	{
		ficheEmploye.setMois12(String.valueOf(new BigDecimal(ficheEmploye.getMois12()).add(BigDecimal.ONE)));
		
	}
 
	ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);	

	listFicheEmployeAbsentTmp.set(j, ficheEmploye);
	
	
}

					
						
						
					}
				
				}
			
			if(Trouve==false){
		
				
			
				

				if(detailProductionMP.isAbsent()==true)
				{
					SituationDesEmployeesAbsents	ficheEmploye =new SituationDesEmployeesAbsents();
					ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
					
					if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==1)	
					{
						ficheEmploye.setMois1(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois1(String.valueOf(BigDecimal.ZERO));
					}
					
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==2)
					{
						ficheEmploye.setMois2(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois2(String.valueOf(BigDecimal.ZERO));
					}
	 
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==3)
					{
						ficheEmploye.setMois3(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois3(String.valueOf(BigDecimal.ZERO));
					}
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==4)
					{
						ficheEmploye.setMois4(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois4(String.valueOf(BigDecimal.ZERO));
					}
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==5)
					{
						ficheEmploye.setMois5(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois5(String.valueOf(BigDecimal.ZERO));
					}
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==6)
					{
						ficheEmploye.setMois6(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois6(String.valueOf(BigDecimal.ZERO));
					}
	 
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==7)
					{
						ficheEmploye.setMois7(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois7(String.valueOf(BigDecimal.ZERO));
					}
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==8)
					{
						ficheEmploye.setMois8(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois8(String.valueOf(BigDecimal.ZERO));
					}
	 
	 
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==9)
					{
						ficheEmploye.setMois9(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois9(String.valueOf(BigDecimal.ZERO));
					}
	 

	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==10)
					{
						ficheEmploye.setMois10(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois10(String.valueOf(BigDecimal.ZERO));
					}
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==11)
					{
						ficheEmploye.setMois11(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois11(String.valueOf(BigDecimal.ZERO));
					}
	 if(detailProductionMP.getProductionMP().getDateProduction().getMonth()+1==12)
					{
						ficheEmploye.setMois12(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmploye.setMois12(String.valueOf(BigDecimal.ZERO));
					}
				 
	 ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);	
					listFicheEmployeAbsentTmp.add(ficheEmploye);
					
				}
				
			}
		
			
			
		}
		}
		
		
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// DetailProdGen ///////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailProdGenerique.size();i++){
		
		DetailProdGen detailProdGen =listDetailProdGenerique.get(i);
		

		
		if(!detailProdGen.getEmploye().isSalarie()){
			
	Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
			{
				
	SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
				
				if(detailProdGen.getEmploye().getId()==ficheEmploye.getEmploye().getId())
				{
					Trouve=true;
					if(detailProdGen.isAbsent()==true)
					{
						
						if(detailProdGen.getProductionGen().getDate().getMonth()+1==1)	
						{
							ficheEmploye.setMois1(String.valueOf(new BigDecimal( ficheEmploye.getMois1()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==2)
						{
							ficheEmploye.setMois2(String.valueOf(new BigDecimal(ficheEmploye.getMois2()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==3)
						{
							ficheEmploye.setMois3(String.valueOf(new BigDecimal(ficheEmploye.getMois3()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==4)
						{
							ficheEmploye.setMois4(String.valueOf(new BigDecimal(ficheEmploye.getMois4()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==5)
						{
							ficheEmploye.setMois5(String.valueOf(new BigDecimal(ficheEmploye.getMois5()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==6)
						{
							ficheEmploye.setMois6(String.valueOf(new BigDecimal(ficheEmploye.getMois6()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==7)
						{
							ficheEmploye.setMois7(String.valueOf(new BigDecimal(ficheEmploye.getMois7()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==8)
						{
							ficheEmploye.setMois8(String.valueOf(new BigDecimal(ficheEmploye.getMois8()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==9)
						{
							ficheEmploye.setMois9(String.valueOf(new BigDecimal(ficheEmploye.getMois9()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==10)
						{
							ficheEmploye.setMois10(String.valueOf(new BigDecimal(ficheEmploye.getMois10()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==11)
						{
							ficheEmploye.setMois11(String.valueOf(new BigDecimal(ficheEmploye.getMois11()).add(BigDecimal.ONE)));
							
						}else if(detailProdGen.getProductionGen().getDate().getMonth()+1==12)
						{
							ficheEmploye.setMois12(String.valueOf(new BigDecimal(ficheEmploye.getMois12()).add(BigDecimal.ONE)));
							
						}
						
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);	
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
						
					}
				
					
					
				
					
					
				}
				
				
				
				
			}
		
		if(Trouve==false) {
			
		
		

		if(detailProdGen.isAbsent()==true)
		{
			SituationDesEmployeesAbsents	ficheEmployeTmp =new SituationDesEmployeesAbsents();
			ficheEmployeTmp.setEmploye(detailProdGen.getEmploye());;
			
			if(detailProdGen.getProductionGen().getDate().getMonth()+1==1)	
			{
				ficheEmployeTmp.setMois1(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois1(String.valueOf(BigDecimal.ZERO));
			}
			
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==2)
			{
				ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==3)
			{
				ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==4)
			{
				ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==5)
			{
				ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==6)
			{
				ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==7)
			{
				ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==8)
			{
				ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==9)
			{
				ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ZERO));
			}


	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==10)
			{
				ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ZERO));
			}
	 
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==11)
			{
				ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ZERO));
			}
	 if(detailProdGen.getProductionGen().getDate().getMonth()+1==12)
			{
				ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ONE));
				
			}else
			{
				ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ZERO));
			}

	 ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);	
			
			listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
			
		}


			
		}
		
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////////////// DetailResponsableProd //////////////////////////////////////////////////////////////////////////////////
	
	
	 for(int i=0;i<listDetailResponsableProd.size();i++){

		 DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
		 
		
		
	Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
			{
				
	SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
				
				if(detailResponsableProd.getEmploye().getId()==ficheEmploye.getEmploye().getId())
				{
					Trouve=true;
					if(detailResponsableProd.isAbsent()==true || detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0)
					{
						
						if(detailResponsableProd.getDateProduction().getMonth()+1==1)	
						{
							ficheEmploye.setMois1( String.valueOf(new BigDecimal(ficheEmploye.getMois1()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==2)
						{
							ficheEmploye.setMois2(String.valueOf(new BigDecimal(ficheEmploye.getMois2()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==3)
						{
							ficheEmploye.setMois3(String.valueOf(new BigDecimal(ficheEmploye.getMois3()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==4)
						{
							ficheEmploye.setMois4(String.valueOf(new BigDecimal(ficheEmploye.getMois4()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==5)
						{
							ficheEmploye.setMois5(String.valueOf(new BigDecimal(ficheEmploye.getMois5()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==6)
						{
							ficheEmploye.setMois6(String.valueOf(new BigDecimal(ficheEmploye.getMois6()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==7)
						{
							ficheEmploye.setMois7(String.valueOf(new BigDecimal(ficheEmploye.getMois7()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==8)
						{
							ficheEmploye.setMois8(String.valueOf(new BigDecimal(ficheEmploye.getMois8()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==9)
						{
							ficheEmploye.setMois9(String.valueOf(new BigDecimal(ficheEmploye.getMois9()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==10)
						{
							ficheEmploye.setMois10(String.valueOf(new BigDecimal(ficheEmploye.getMois10()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==11)
						{
							ficheEmploye.setMois11(String.valueOf(new BigDecimal(ficheEmploye.getMois11()).add(BigDecimal.ONE)));
							
						}else if(detailResponsableProd.getDateProduction().getMonth()+1==12)
						{
							ficheEmploye.setMois12(String.valueOf(new BigDecimal(ficheEmploye.getMois12()).add(BigDecimal.ONE)));
							
						}
						
						
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
						
					}
				
					
					
				
					
					
				}
				
				
				
				
			}
		 
		 
			if(Trouve==false)
			{

				if(detailResponsableProd.isAbsent()==true || detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0)
				{
					 SituationDesEmployeesAbsents ficheEmployeTmp=new SituationDesEmployeesAbsents();
					 
					ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
					
					if(detailResponsableProd.getDateProduction().getMonth()+1==1)	
					{
						ficheEmployeTmp.setMois1(String.valueOf( BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois1(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==2)
					{
						ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois2(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==3)
					{
						ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois3(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==4)
					{
						ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois4(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==5)
					{
						ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois5(String.valueOf(BigDecimal.ZERO));
					}

	if(detailResponsableProd.getDateProduction().getMonth()+1==6)
					{
						ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois6(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==7)
					{
						ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois7(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==8)
					{
						ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois8(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==9)
					{
						ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois9(String.valueOf(BigDecimal.ZERO));
					}

	if(detailResponsableProd.getDateProduction().getMonth()+1==10)
					{
						ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois10(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==11)
					{
						ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois11(String.valueOf(BigDecimal.ZERO));
					}
	if(detailResponsableProd.getDateProduction().getMonth()+1==12)
					{
						ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ONE));
						
					}else
					{
						ficheEmployeTmp.setMois12(String.valueOf(BigDecimal.ZERO));
					}
	ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
					
					listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
						 
					
					
				}
			}
		 
		 
							
							
		}
	
	
	
	
	
	
	
	return listFicheEmployeAbsentTmp;
	
}



List<SituationDesEmployeesAbsentsParJour> calculeCoutEmployeParJour(){
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	
	listFicheEmployeAbsentParJour.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	String matricule="";
	if(!comboemploye.getSelectedItem().equals(""))
	{
		
		Employe employe=mapEmploye.get(comboemploye.getSelectedItem());
		matricule=employe.getMatricule();
		
	}
	
	
	 SimpleDateFormat simpleFormatDay = new SimpleDateFormat("dd");
		 

		List<DetailProduction> listDetailProduction=new ArrayList<DetailProduction>();
		List<DetailProdGen> listDetailProdGenerique=new ArrayList<DetailProdGen>();
		List<DetailProdRes> listDetailResponsableProd=new ArrayList<DetailProdRes>();
		List<DetailProductionMP> listDetailProductionMP=new ArrayList<DetailProductionMP>();
	
	
		if(comboequipe.getSelectedItem().equals(""))
		{

			 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepotparJour(dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);
			 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);
			 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),matricule);
			 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

		
		}else
		{
			
			if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION))
			{
				
				 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepotparJour(dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE))
			{
				 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE))
			{
				 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),matricule);

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP))
			{
				 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

			}
			
			
			
		}
	
	
	
	
	
	
		///////////////////////////////////////////////////////////////////////////////////////////// Detail production /////////////////////////////////////////////////////////////////////
	
	
///////////////////////////////////////////////////////////////////////////////////////////// Remplire les jour absent par zero //////////////////////////////////////////////////////////////////////////
	
	
///////////////////////////////////////////////////////////////////////////////////////////// 	DetailProduction ////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listDetailProduction.size();i++)
	{
		DetailProduction detailProduction =listDetailProduction.get(i);	
		
		if(!detailProduction.getEmploye().isSalarie())
		{
			
		boolean trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				if(listFicheEmployeAbsentParJour.get(j).getEmploye().getId()==detailProduction.getEmploye().getId())
				{
					trouve=true;
				}
				
				
				
			}
			
			
			if(trouve==false)
			{
				
				SituationDesEmployeesAbsentsParJour absentsParJour=new SituationDesEmployeesAbsentsParJour();
				
				absentsParJour.setEmploye(detailProduction.getEmploye());
			
				absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_2(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_3(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_4(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_5(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_6(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_7(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_8(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_9(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_10(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_11(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_12(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_13(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_14(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_15(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_16(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_17(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_18(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_19(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_20(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_21(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_22(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_23(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_24(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_25(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_26(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_27(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_28(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_29(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_30(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_31(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
					listFicheEmployeAbsentParJour.add(absentsParJour);
			}
			
			
			
			
			
			
		}
		
		
	}
	
///////////////////////////////////////////////////////////////////////////// 	DetailProdGenerique ///////////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listDetailProdGenerique.size();i++)
	{
		DetailProdGen detailProductionGen =listDetailProdGenerique.get(i);
		
		if(!detailProductionGen.getEmploye().isSalarie())
		{
			
		boolean trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				if(listFicheEmployeAbsentParJour.get(j).getEmploye().getId()==detailProductionGen.getEmploye().getId())
				{
					trouve=true;
				}
				
				
				
			}
			
			
			if(trouve==false)
			{
				
				SituationDesEmployeesAbsentsParJour absentsParJour=new SituationDesEmployeesAbsentsParJour();
				
				absentsParJour.setEmploye(detailProductionGen.getEmploye());
			
				absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_2(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_3(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_4(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_5(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_6(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_7(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_8(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_9(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_10(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_11(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_12(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_13(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_14(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_15(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_16(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_17(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_18(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_19(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_20(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_21(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_22(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_23(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_24(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_25(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_26(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_27(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_28(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_29(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_30(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_31(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
					listFicheEmployeAbsentParJour.add(absentsParJour);
			}
			
			
			
			
			
			
		}
		
		
	}
	
//////////////////////////////////////////////////////////////////////////// 	ResponsableProd ///////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailResponsableProd.size();i++)
	{
		 DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
		
		if(!detailResponsableProd.getEmploye().isSalarie())
		{
			
		boolean trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				if(listFicheEmployeAbsentParJour.get(j).getEmploye().getId()==detailResponsableProd.getEmploye().getId())
				{
					trouve=true;
				}
				
				
				
			}
			
			
			if(trouve==false)
			{
				
				SituationDesEmployeesAbsentsParJour absentsParJour=new SituationDesEmployeesAbsentsParJour();
				
				absentsParJour.setEmploye(detailResponsableProd.getEmploye());
			
				absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_2(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_3(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_4(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_5(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_6(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_7(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_8(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_9(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_10(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_11(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_12(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_13(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_14(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_15(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_16(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_17(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_18(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_19(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_20(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_21(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_22(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_23(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_24(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_25(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_26(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_27(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_28(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_29(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_30(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_31(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
					listFicheEmployeAbsentParJour.add(absentsParJour);
			}
			
			
			
			
			
			
		}
		
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////              DetailProductionMP                        ///////////////////////////////////////////////////////	
	
	for(int i=0;i<listDetailProductionMP.size();i++)
	{
		DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
		
		if(!detailProductionMP.getEmploye().isSalarie())
		{
			
		boolean trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				if(listFicheEmployeAbsentParJour.get(j).getEmploye().getId()==detailProductionMP.getEmploye().getId())
				{
					trouve=true;
				}
				
				
				
			}
			
			
			if(trouve==false)
			{
				
				SituationDesEmployeesAbsentsParJour absentsParJour=new SituationDesEmployeesAbsentsParJour();
				
				absentsParJour.setEmploye(detailProductionMP.getEmploye());
			
				absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_1(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_2(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_3(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_4(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_5(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_6(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_7(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_8(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_9(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_10(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_11(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_12(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_13(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_14(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_15(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_16(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_17(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_18(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_19(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_20(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_21(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_22(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_23(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_24(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_25(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_26(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_27(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_28(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_29(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_30(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setJ_31(String.valueOf(BigDecimal.ZERO) );
					absentsParJour.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
					listFicheEmployeAbsentParJour.add(absentsParJour);
			}
			
			
			
		}
		
		
	}	
	
	
	
	
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	for(int i=0;i<listDetailProduction.size();i++){
		

		DetailProduction detailProduction =listDetailProduction.get(i);
		

	
		
		
		if(!detailProduction.getEmploye().isSalarie()){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				
				SituationDesEmployeesAbsentsParJour ficheEmploye=listFicheEmployeAbsentParJour.get(j);
				
				if(detailProduction.getEmploye().getId()==ficheEmploye.getEmploye().getId()  )
				{
					Trouve=true;
					
					if( detailProduction.isAbsent()==true)
					{
						if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("01"))	
						{
							ficheEmploye.setJ_1(String.valueOf(new BigDecimal(ficheEmploye.getJ_1()).add(BigDecimal.ONE)) );
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("02"))
						{
							ficheEmploye.setJ_2(String.valueOf(new BigDecimal(ficheEmploye.getJ_2()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("03"))
						{
							ficheEmploye.setJ_3(String.valueOf(new BigDecimal(ficheEmploye.getJ_3()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("05"))
						{
							ficheEmploye.setJ_5(String.valueOf(new BigDecimal(ficheEmploye.getJ_5()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("06"))
						{
							ficheEmploye.setJ_6(String.valueOf(new BigDecimal(ficheEmploye.getJ_6()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("07"))
						{
							ficheEmploye.setJ_7(String.valueOf(new BigDecimal(ficheEmploye.getJ_7()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("08"))
						{
							ficheEmploye.setJ_8(String.valueOf(new BigDecimal(ficheEmploye.getJ_8()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("09"))
						{
							ficheEmploye.setJ_9(String.valueOf(new BigDecimal(ficheEmploye.getJ_9()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("10"))
						{
							ficheEmploye.setJ_10(String.valueOf(new BigDecimal(ficheEmploye.getJ_10()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("11"))
						{
							ficheEmploye.setJ_11(String.valueOf(new BigDecimal(ficheEmploye.getJ_11()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("12"))
						{
							ficheEmploye.setJ_12(String.valueOf(new BigDecimal(ficheEmploye.getJ_12()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("13"))
						{
							ficheEmploye.setJ_13(String.valueOf(new BigDecimal(ficheEmploye.getJ_13()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("14"))
						{
							ficheEmploye.setJ_14(String.valueOf(new BigDecimal(ficheEmploye.getJ_14()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("15"))
						{
							ficheEmploye.setJ_15(String.valueOf(new BigDecimal(ficheEmploye.getJ_15()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("16"))
						{
							ficheEmploye.setJ_16(String.valueOf(new BigDecimal(ficheEmploye.getJ_16()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("17"))
						{
							ficheEmploye.setJ_17(String.valueOf(new BigDecimal(ficheEmploye.getJ_17()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("18"))
						{
							ficheEmploye.setJ_18(String.valueOf(new BigDecimal(ficheEmploye.getJ_18()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("19"))
						{
							ficheEmploye.setJ_19(String.valueOf(new BigDecimal(ficheEmploye.getJ_19()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("20"))
						{
							ficheEmploye.setJ_20(String.valueOf(new BigDecimal(ficheEmploye.getJ_20()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("21"))
						{
							ficheEmploye.setJ_21(String.valueOf(new BigDecimal(ficheEmploye.getJ_21()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("22"))
						{
							ficheEmploye.setJ_22(String.valueOf(new BigDecimal(ficheEmploye.getJ_22()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("23"))
						{
							ficheEmploye.setJ_23(String.valueOf(new BigDecimal(ficheEmploye.getJ_23()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("24"))
						{
							ficheEmploye.setJ_24(String.valueOf(new BigDecimal(ficheEmploye.getJ_24()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("25"))
						{
							ficheEmploye.setJ_25(String.valueOf(new BigDecimal(ficheEmploye.getJ_25()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("26"))
						{
							ficheEmploye.setJ_26(String.valueOf(new BigDecimal(ficheEmploye.getJ_26()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("27"))
						{
							ficheEmploye.setJ_27(String.valueOf(new BigDecimal(ficheEmploye.getJ_27()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("28"))
						{
							ficheEmploye.setJ_28(String.valueOf(new BigDecimal(ficheEmploye.getJ_28()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("29"))
						{
							ficheEmploye.setJ_29(String.valueOf(new BigDecimal(ficheEmploye.getJ_29()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("30"))
						{
							ficheEmploye.setJ_30(String.valueOf(new BigDecimal(ficheEmploye.getJ_30()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProduction.getProduction().getDate()).equals("31"))
						{
							ficheEmploye.setJ_31(String.valueOf(new BigDecimal(ficheEmploye.getJ_31()).add(BigDecimal.ONE)));
							
						}
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
						
							 
							
							
						listFicheEmployeAbsentParJour.set(j, ficheEmploye);
					}
					
					
					
				}
				
				
				
			}
			
		
		}
		
		
	}
		
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// Detail ProductionMP //////////////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailProductionMP.size();i++){
		
	  
			
			DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
			
			
				
		
			
			
			
			
			if(!detailProductionMP.getEmploye().isSalarie()){
				
				Boolean Trouve=false;
				
				for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
				{
					
					SituationDesEmployeesAbsentsParJour ficheEmploye=listFicheEmployeAbsentParJour.get(j);
					
					if(detailProductionMP.getEmploye().getId()==ficheEmploye.getEmploye().getId()   )
					{
						Trouve=true;
if(detailProductionMP.isAbsent()==true)
{


	if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("01"))	
	{
		ficheEmploye.setJ_1(String.valueOf(new BigDecimal(ficheEmploye.getJ_1()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("02"))
	{
		ficheEmploye.setJ_2(String.valueOf(new BigDecimal(ficheEmploye.getJ_2()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("03"))
	{
		ficheEmploye.setJ_3(String.valueOf(new BigDecimal(ficheEmploye.getJ_3()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("04"))
	{
		ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("04"))
	{
		ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("05"))
	{
		ficheEmploye.setJ_5(String.valueOf(new BigDecimal(ficheEmploye.getJ_5()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("06"))
	{
		ficheEmploye.setJ_6(String.valueOf(new BigDecimal(ficheEmploye.getJ_6()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("07"))
	{
		ficheEmploye.setJ_7(String.valueOf(new BigDecimal(ficheEmploye.getJ_7()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("08"))
	{
		ficheEmploye.setJ_8(String.valueOf(new BigDecimal(ficheEmploye.getJ_8()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("09"))
	{
		ficheEmploye.setJ_9(String.valueOf(new BigDecimal(ficheEmploye.getJ_9()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("10"))
	{
		ficheEmploye.setJ_10(String.valueOf(new BigDecimal(ficheEmploye.getJ_10()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("11"))
	{
		ficheEmploye.setJ_11(String.valueOf(new BigDecimal(ficheEmploye.getJ_11()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("12"))
	{
		ficheEmploye.setJ_12(String.valueOf(new BigDecimal(ficheEmploye.getJ_12()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("13"))
	{
		ficheEmploye.setJ_13(String.valueOf(new BigDecimal(ficheEmploye.getJ_13()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("14"))
	{
		ficheEmploye.setJ_14(String.valueOf(new BigDecimal(ficheEmploye.getJ_14()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("15"))
	{
		ficheEmploye.setJ_15(String.valueOf(new BigDecimal(ficheEmploye.getJ_15()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("16"))
	{
		ficheEmploye.setJ_16(String.valueOf(new BigDecimal(ficheEmploye.getJ_16()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("17"))
	{
		ficheEmploye.setJ_17(String.valueOf(new BigDecimal(ficheEmploye.getJ_17()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("18"))
	{
		ficheEmploye.setJ_18(String.valueOf(new BigDecimal(ficheEmploye.getJ_18()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("19"))
	{
		ficheEmploye.setJ_19(String.valueOf(new BigDecimal(ficheEmploye.getJ_19()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("20"))
	{
		ficheEmploye.setJ_20(String.valueOf(new BigDecimal(ficheEmploye.getJ_20()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("21"))
	{
		ficheEmploye.setJ_21(String.valueOf(new BigDecimal(ficheEmploye.getJ_21()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("22"))
	{
		ficheEmploye.setJ_22(String.valueOf(new BigDecimal(ficheEmploye.getJ_22()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("23"))
	{
		ficheEmploye.setJ_23(String.valueOf(new BigDecimal(ficheEmploye.getJ_23()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("24"))
	{
		ficheEmploye.setJ_24(String.valueOf(new BigDecimal(ficheEmploye.getJ_24()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("25"))
	{
		ficheEmploye.setJ_25(String.valueOf(new BigDecimal(ficheEmploye.getJ_25()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("26"))
	{
		ficheEmploye.setJ_26(String.valueOf(new BigDecimal(ficheEmploye.getJ_26()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("27"))
	{
		ficheEmploye.setJ_27(String.valueOf(new BigDecimal(ficheEmploye.getJ_27()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("28"))
	{
		ficheEmploye.setJ_28(String.valueOf(new BigDecimal(ficheEmploye.getJ_28()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("29"))
	{
		ficheEmploye.setJ_29(String.valueOf(new BigDecimal(ficheEmploye.getJ_29()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("30"))
	{
		ficheEmploye.setJ_30(String.valueOf(new BigDecimal(ficheEmploye.getJ_30()).add(BigDecimal.ONE)));
		
	}else if(simpleFormatDay.format(detailProductionMP.getProductionMP().getDateProduction()).equals("31"))
	{
		ficheEmploye.setJ_31(String.valueOf(new BigDecimal(ficheEmploye.getJ_31()).add(BigDecimal.ONE)));
		
	}

	ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
		 
		
		
	listFicheEmployeAbsentParJour.set(j, ficheEmploye);



}

					
						
						
					}
				
				}
			
			
			
			
		}
		}
		
		
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// DetailProdGen ///////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailProdGenerique.size();i++){
		
		DetailProdGen detailProdGen =listDetailProdGenerique.get(i);
		

		
		if(!detailProdGen.getEmploye().isSalarie()){
			
	Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				
	SituationDesEmployeesAbsentsParJour ficheEmploye=listFicheEmployeAbsentParJour.get(j);
				
				if(detailProdGen.getEmploye().getId()==ficheEmploye.getEmploye().getId())
				{
					Trouve=true;
					if(detailProdGen.isAbsent()==true)
					{
						

						if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("01"))	
						{
							ficheEmploye.setJ_1(String.valueOf(new BigDecimal(ficheEmploye.getJ_1()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("02"))
						{
							ficheEmploye.setJ_2(String.valueOf(new BigDecimal(ficheEmploye.getJ_2()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("03"))
						{
							ficheEmploye.setJ_3(String.valueOf(new BigDecimal(ficheEmploye.getJ_3()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("05"))
						{
							ficheEmploye.setJ_5(String.valueOf(new BigDecimal(ficheEmploye.getJ_5()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("06"))
						{
							ficheEmploye.setJ_6(String.valueOf(new BigDecimal(ficheEmploye.getJ_6()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("07"))
						{
							ficheEmploye.setJ_7(String.valueOf(new BigDecimal(ficheEmploye.getJ_7()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("08"))
						{
							ficheEmploye.setJ_8(String.valueOf(new BigDecimal(ficheEmploye.getJ_8()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("09"))
						{
							ficheEmploye.setJ_9(String.valueOf(new BigDecimal(ficheEmploye.getJ_9()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("10"))
						{
							ficheEmploye.setJ_10(String.valueOf(new BigDecimal(ficheEmploye.getJ_10()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("11"))
						{
							ficheEmploye.setJ_11(String.valueOf(new BigDecimal(ficheEmploye.getJ_11()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("12"))
						{
							ficheEmploye.setJ_12(String.valueOf(new BigDecimal(ficheEmploye.getJ_12()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("13"))
						{
							ficheEmploye.setJ_13(String.valueOf(new BigDecimal(ficheEmploye.getJ_13()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("14"))
						{
							ficheEmploye.setJ_14(String.valueOf(new BigDecimal(ficheEmploye.getJ_14()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("15"))
						{
							ficheEmploye.setJ_15(String.valueOf(new BigDecimal(ficheEmploye.getJ_15()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("16"))
						{
							ficheEmploye.setJ_16(String.valueOf(new BigDecimal(ficheEmploye.getJ_16()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("17"))
						{
							ficheEmploye.setJ_17(String.valueOf(new BigDecimal(ficheEmploye.getJ_17()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("18"))
						{
							ficheEmploye.setJ_18(String.valueOf(new BigDecimal(ficheEmploye.getJ_18()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("19"))
						{
							ficheEmploye.setJ_19(String.valueOf(new BigDecimal(ficheEmploye.getJ_19()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("20"))
						{
							ficheEmploye.setJ_20(String.valueOf(new BigDecimal(ficheEmploye.getJ_20()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("21"))
						{
							ficheEmploye.setJ_21(String.valueOf(new BigDecimal(ficheEmploye.getJ_21()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("22"))
						{
							ficheEmploye.setJ_22(String.valueOf(new BigDecimal(ficheEmploye.getJ_22()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("23"))
						{
							ficheEmploye.setJ_23(String.valueOf(new BigDecimal(ficheEmploye.getJ_23()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("24"))
						{
							ficheEmploye.setJ_24(String.valueOf(new BigDecimal(ficheEmploye.getJ_24()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("25"))
						{
							ficheEmploye.setJ_25(String.valueOf(new BigDecimal(ficheEmploye.getJ_25()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("26"))
						{
							ficheEmploye.setJ_26(String.valueOf(new BigDecimal(ficheEmploye.getJ_26()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("27"))
						{
							ficheEmploye.setJ_27(String.valueOf(new BigDecimal(ficheEmploye.getJ_27()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("28"))
						{
							ficheEmploye.setJ_28(String.valueOf(new BigDecimal(ficheEmploye.getJ_28()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("29"))
						{
							ficheEmploye.setJ_29(String.valueOf(new BigDecimal(ficheEmploye.getJ_29()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("30"))
						{
							ficheEmploye.setJ_30(String.valueOf(new BigDecimal(ficheEmploye.getJ_30()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailProdGen.getProductionGen().getDate()).equals("31"))
						{
							ficheEmploye.setJ_31(String.valueOf(new BigDecimal(ficheEmploye.getJ_31()).add(BigDecimal.ONE)));
							
						}

						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);	
						listFicheEmployeAbsentParJour.set(j, ficheEmploye);
					
						
					}
				
				}
				
				
			}
		
		
		
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////////////// DetailResponsableProd //////////////////////////////////////////////////////////////////////////////////
	
	
	 for(int i=0;i<listDetailResponsableProd.size();i++){

		 DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
		 
		
		
	Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeAbsentParJour.size();j++)
			{
				
	SituationDesEmployeesAbsentsParJour ficheEmploye=listFicheEmployeAbsentParJour.get(j);
				
				if(detailResponsableProd.getEmploye().getId()==ficheEmploye.getEmploye().getId())
				{
					Trouve=true;
					if(detailResponsableProd.isAbsent()==true || detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0)
					{


						

						if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("01"))	
						{
							ficheEmploye.setJ_1(String.valueOf(new BigDecimal(ficheEmploye.getJ_1()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("02"))
						{
							ficheEmploye.setJ_2(String.valueOf(new BigDecimal(ficheEmploye.getJ_2()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("03"))
						{
							ficheEmploye.setJ_3(String.valueOf(new BigDecimal(ficheEmploye.getJ_3()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("04"))
						{
							ficheEmploye.setJ_4(String.valueOf(new BigDecimal(ficheEmploye.getJ_4()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("05"))
						{
							ficheEmploye.setJ_5(String.valueOf(new BigDecimal(ficheEmploye.getJ_5()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("06"))
						{
							ficheEmploye.setJ_6(String.valueOf(new BigDecimal(ficheEmploye.getJ_6()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("07"))
						{
							ficheEmploye.setJ_7(String.valueOf(new BigDecimal(ficheEmploye.getJ_7()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("08"))
						{
							ficheEmploye.setJ_8(String.valueOf(new BigDecimal(ficheEmploye.getJ_8()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("09"))
						{
							ficheEmploye.setJ_9(String.valueOf(new BigDecimal(ficheEmploye.getJ_9()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("10"))
						{
							ficheEmploye.setJ_10(String.valueOf(new BigDecimal(ficheEmploye.getJ_10()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("11"))
						{
							ficheEmploye.setJ_11(String.valueOf(new BigDecimal(ficheEmploye.getJ_11()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("12"))
						{
							ficheEmploye.setJ_12(String.valueOf(new BigDecimal(ficheEmploye.getJ_12()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("13"))
						{
							ficheEmploye.setJ_13(String.valueOf(new BigDecimal(ficheEmploye.getJ_13()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("14"))
						{
							ficheEmploye.setJ_14(String.valueOf(new BigDecimal(ficheEmploye.getJ_14()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("15"))
						{
							ficheEmploye.setJ_15(String.valueOf(new BigDecimal(ficheEmploye.getJ_15()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("16"))
						{
							ficheEmploye.setJ_16(String.valueOf(new BigDecimal(ficheEmploye.getJ_16()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("17"))
						{
							ficheEmploye.setJ_17(String.valueOf(new BigDecimal(ficheEmploye.getJ_17()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("18"))
						{
							ficheEmploye.setJ_18(String.valueOf(new BigDecimal(ficheEmploye.getJ_18()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("19"))
						{
							ficheEmploye.setJ_19(String.valueOf(new BigDecimal(ficheEmploye.getJ_19()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("20"))
						{
							ficheEmploye.setJ_20(String.valueOf(new BigDecimal(ficheEmploye.getJ_20()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("21"))
						{
							ficheEmploye.setJ_21(String.valueOf(new BigDecimal(ficheEmploye.getJ_21()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("22"))
						{
							ficheEmploye.setJ_22(String.valueOf(new BigDecimal(ficheEmploye.getJ_22()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("23"))
						{
							ficheEmploye.setJ_23(String.valueOf(new BigDecimal(ficheEmploye.getJ_23()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("24"))
						{
							ficheEmploye.setJ_24(String.valueOf(new BigDecimal(ficheEmploye.getJ_24()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("25"))
						{
							ficheEmploye.setJ_25(String.valueOf(new BigDecimal(ficheEmploye.getJ_25()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("26"))
						{
							ficheEmploye.setJ_26(String.valueOf(new BigDecimal(ficheEmploye.getJ_26()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("27"))
						{
							ficheEmploye.setJ_27(String.valueOf(new BigDecimal(ficheEmploye.getJ_27()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("28"))
						{
							ficheEmploye.setJ_28(String.valueOf(new BigDecimal(ficheEmploye.getJ_28()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("29"))
						{
							ficheEmploye.setJ_29(String.valueOf(new BigDecimal(ficheEmploye.getJ_29()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("30"))
						{
							ficheEmploye.setJ_30(String.valueOf(new BigDecimal(ficheEmploye.getJ_30()).add(BigDecimal.ONE)));
							
						}else if(simpleFormatDay.format(detailResponsableProd.getDateProduction()).equals("31"))
						{
							ficheEmploye.setJ_31(String.valueOf(new BigDecimal(ficheEmploye.getJ_31()).add(BigDecimal.ONE)));
							
						}

						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
						listFicheEmployeAbsentParJour.set(j, ficheEmploye);
					
						
					
					}
				
					
				}
				
				
			}
		 
		 
	 
		 
							
							
		}
	
	
	
	
	
	
	
	return listFicheEmployeAbsentParJour;
	
}
}

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
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


public class ConsulterAutorisationDesAbsents extends JLayeredPane implements Constantes{
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
	private JComboBox comboequipe;
	JCheckBox chckbxAbsentsParJour = new JCheckBox("Absents Par Jour");
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";

	private JTextField txtmatricule;
	public ConsulterAutorisationDesAbsents() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 734);
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
					  		     			"Matricule","Nom Employer", "Equipe","Motif", "Autoriser"					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false, false, false
					  		     	};
					  		     	
					  		     	Class[] columnTypes = new Class[] {
											String.class,String.class,String.class,String.class, Boolean.class
										};
									  public Class getColumnClass(int columnIndex) {
											return columnTypes[columnIndex];
										}
					  		     	
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  			 table.setModel(new DefaultTableModel(
								  		 	new Object[][] {
								  		 	},
								  		 	new String[] {
								  		 			"Date","Matricule","Nom Employer", "Equipe","Motif", "Autoriser"								  		 			
								  		 	}
								  		 ) {
								  		 	boolean[] columnEditables = new boolean[] {
								  		 		 false,false,false, false, false, false
								  		 	};
								  		 	Class[] columnTypes = new Class[] {
								  		 			Date.class,String.class,String.class,String.class,String.class, Boolean.class
												};
											  public Class getColumnClass(int columnIndex) {
													return columnTypes[columnIndex];
												}
											  
								  		 	public boolean isCellEditable(int row, int column) {
								  		 		return columnEditables[column];
								  		 	}
								  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 185, 1427, 474);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1427, 138);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 36, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(216, 39, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1028, 57, 31, 31);
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
					

			
	
					
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					listFicheEmploye=calculeCoutEmploye();
			
					
					/*
					Collections.sort(listFicheEmploye, new Comparator<SituationDesEmployeesAbsents>() {
						  @Override
						  public int compare(SituationDesEmployeesAbsents u1, SituationDesEmployeesAbsents u2) {
						    return u1.getEmploye().getNom().compareTo(u2.getEmploye().getNom());
						  }
						});
					
					*/
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
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 36, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(278, 40, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(428, 37, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(487, 36, 191, 26);
		layeredPane.add(comboDepot);
		 labelemploye = new JLabel("Equipe :");
		labelemploye.setBounds(688, 34, 84, 26);
		layeredPane.add(labelemploye);
		labelemploye.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelemploye.setVisible(true);
		 comboequipe = new JComboBox();
		 comboequipe.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
}
		 });
		comboequipe.setBounds(755, 34, 218, 26);
		layeredPane.add(comboequipe);
		comboequipe.setVisible(true);
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
	
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(new ImageIcon(ConsulterAutorisationDesAbsents.class.getResource("/img/imprimer.png")));
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				String dateDebut=simpleDateFormat.format(dateDebutChooser.getDate()) ;
				String dateFin=simpleDateFormat.format(dateFinChooser.getDate());

				if(listFicheEmploye.size()!=0)
				{
					Map parameters = new HashMap();
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					parameters.put("depot", depot.getLibelle());
					parameters.put("periode", "DU : "+ dateDebut  +" AU :"+dateFin);
					JasperUtils.imprimerEtatAutorisationEmployesAbsent(listFicheEmploye,parameters);
					
				}
			
			
			
			
			
			}
		});
		btnImprimer.setBounds(439, 687, 155, 24);
		add(btnImprimer);
		comboequipe.addItem("");

		comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
		comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
		comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
		comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
		
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
		txtmatricule.setBounds(76, 96, 130, 24);
		layeredPane.add(txtmatricule);
		txtmatricule.setColumns(10);
		
		JLabel lblMatricule = new JLabel("Matricule :");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMatricule.setBounds(10, 96, 73, 24);
		layeredPane.add(lblMatricule);
		
		JLabel lblEmployee = new JLabel("Employee :");
		lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployee.setBounds(229, 94, 73, 26);
		layeredPane.add(lblEmployee);
		
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
		comboemploye.setBounds(295, 96, 249, 26);
		 AutoCompleteDecorator.decorate(comboemploye);
		layeredPane.add(comboemploye);
		
		comboemploye.addItem("");
		
		for(int i=0;i<listEmploye.size();i++)
		{
			
			Employe employe=listEmploye.get(i);
			comboemploye.addItem(employe.getNomafficher());
			mapEmploye.put(employe.getNomafficher(), employe);
			mapMatriculeEmploye.put(employe.getMatricule(), employe);
			
		}
		
		
			  		 
	}
	
void afficher_tableMP(List<SituationDesEmployeesAbsents> listFicheEmploye )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listFicheEmploye.size())
		{
			SituationDesEmployeesAbsents situationDesEmployeesAbsents=listFicheEmploye.get(j);
			 SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
			 boolean autoriser=false;
			 String motif="";
			if(situationDesEmployeesAbsents.getMotif()!=null)
			{
				if(!situationDesEmployeesAbsents.getMotif().equals(""))
				{
					motif=situationDesEmployeesAbsents.getMotif();
				}
				
				
			}
			
			autoriser=situationDesEmployeesAbsents.isAutoriser();
			
				Object []ligne={simpledate.format(situationDesEmployeesAbsents.getDateSituation()) , situationDesEmployeesAbsents.getEmploye().getMatricule(),situationDesEmployeesAbsents.getEmploye().getNomafficher(),situationDesEmployeesAbsents.getEquipe(),motif,autoriser};

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
		     			"Date","Matricule","Nom Employer", "Equipe","Motif", "Autoriser"		
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false, false, false, false
		     	};
		    	Class[] columnTypes = new Class[] {
		    			Date.class,String.class,String.class,String.class,String.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(80);
		 table.getColumnModel().getColumn(1).setPreferredWidth(80);
      table.getColumnModel().getColumn(2).setPreferredWidth(80);
      table.getColumnModel().getColumn(3).setPreferredWidth(80);
      table.getColumnModel().getColumn(4).setPreferredWidth(150);
      table.getColumnModel().getColumn(5).setPreferredWidth(80);
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
	String matricule="";
	listFicheEmployeAbsentTmp.clear();
	List<DetailProduction> listDetailProduction=new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenerique=new ArrayList<DetailProdGen>();
	List<DetailProdRes> listDetailResponsableProd=new ArrayList<DetailProdRes>();
	List<DetailProductionMP> listDetailProductionMP=new ArrayList<DetailProductionMP>();
	if(!txtmatricule.getText().equals(""))
	{
		matricule=txtmatricule.getText();
	}
	
	
	
	if(comboequipe.getSelectedItem().equals(""))
	{
		 listDetailProduction =detailProductionDAO.ListHeursDetailProductionParDepotparJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);
	     listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(), matricule,depot.getId(),Constantes.ETAT_OF_TERMINER);
		 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId(), matricule);
		 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(), matricule,depot.getId(),Constantes.ETAT_OF_TERMINER);
	}else
	{
		
		if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION))
		{
			
			 listDetailProduction =detailProductionDAO.ListHeursDetailProductionParDepotparJour(dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE))
		{
		     listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepotParJour (dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE))
		{
			 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepotParJour(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),matricule);

		}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP))
		{
			 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepotParJour(dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule, depot.getId(),Constantes.ETAT_OF_TERMINER);

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
				
				if(detailProduction.getEmploye().getId()==ficheEmploye.getEmploye().getId() && detailProduction.getProduction().getDate().equals(ficheEmploye.getDateSituation()) )
				{
					Trouve=true;
					
					if(detailProduction.isAbsent()==true)
					{
						
						if(detailProduction.getValider().equals(Constantes.ETAT_VALIDER))
						{
							
							ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
							if(detailProduction.getMotif()!=null)
							{
								
								
								if(!detailProduction.getMotif().equals(""))
								{
									ficheEmploye.setMotif(detailProduction.getMotif());
									
								}
								
							}
							ficheEmploye.setAutoriser(detailProduction.isAutorisation());
						
							
							

							listFicheEmployeAbsentTmp.set(j, ficheEmploye);	
							
						}

						
					}
					
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
			
			

		
			


if(detailProduction.isAbsent()==true )
{
	
	
	if(detailProduction.getValider().equals(Constantes.ETAT_VALIDER))
	{
		SituationDesEmployeesAbsents ficheEmployeTmp =new SituationDesEmployeesAbsents();
		
		ficheEmployeTmp.setEmploye(detailProduction.getEmploye());
		ficheEmployeTmp.setDateSituation(detailProduction.getProduction().getDate());
		ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
		
		if(detailProduction.getMotif()!=null)
		{
			
			
			if(!detailProduction.getMotif().equals(""))
			{
				ficheEmployeTmp.setMotif(detailProduction.getMotif());
				
			}
			
		}
		ficheEmployeTmp.setAutoriser(detailProduction.isAutorisation());
		
		
	    listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
		
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
				
				for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
				{
					
					SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
					
					if(detailProductionMP.getEmploye().getId()==ficheEmploye.getEmploye().getId()  &&  detailProductionMP.getProductionMP().getDateProduction().equals(ficheEmploye.getDateSituation()))
					{
						Trouve=true;
if(detailProductionMP.isAbsent()==true )
{
	if(detailProductionMP.getValider().equals(Constantes.ETAT_VALIDER))
	{
		ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
		
		if(detailProductionMP.getMotif()!=null)
		{
			
			
			if(!detailProductionMP.getMotif().equals(""))
			{
				ficheEmploye.setMotif(detailProductionMP.getMotif());
				
			}
			
		}
		ficheEmploye.setAutoriser(detailProductionMP.isAutorisation());
		
		
		listFicheEmployeAbsentTmp.set(j, ficheEmploye);
		
	}
	
	
	
}

					
						
						
					}
				
				}
			
			if(Trouve==false){
		
				
			
				

				if(detailProductionMP.isAbsent()==true )
				{
					if(detailProductionMP.getValider().equals(Constantes.ETAT_VALIDER))
					{
						SituationDesEmployeesAbsents	ficheEmploye =new SituationDesEmployeesAbsents();
						ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
						ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
						
						if(detailProductionMP.getMotif()!=null)
						{
							
							
							if(!detailProductionMP.getMotif().equals(""))
							{
								ficheEmploye.setMotif(detailProductionMP.getMotif());
								
							}
							
						}
						ficheEmploye.setAutoriser(detailProductionMP.isAutorisation());
						
						listFicheEmployeAbsentTmp.add(ficheEmploye);
						
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
			
			for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)
			{
				
	SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
				
				if(detailProdGen.getEmploye().getId()==ficheEmploye.getEmploye().getId() && detailProdGen.getProductionGen().getDate().equals(ficheEmploye.getDateSituation()))
				{
					Trouve=true;
					if(detailProdGen.isAbsent()==true )
					{
						if(detailProdGen.getValider().equals(Constantes.ETAT_VALIDER))
						{
							ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
							
							if(detailProdGen.getMotif()!=null)
							{
								
								
								if(!detailProdGen.getMotif().equals(""))
								{
									ficheEmploye.setMotif(detailProdGen.getMotif());
									
								}
								
							}
							ficheEmploye.setAutoriser(detailProdGen.isAutorisation());
							
							listFicheEmployeAbsentTmp.set(j, ficheEmploye);
							
						}
						
						
					}
				
					
					
				
					
					
				}
				
				
				
				
			}
		
		if(Trouve==false) {
			
		
		

		if(detailProdGen.isAbsent()==true )
		{
			
			if(detailProdGen.getValider().equals(Constantes.ETAT_VALIDER))
			{
				SituationDesEmployeesAbsents	ficheEmployeTmp =new SituationDesEmployeesAbsents();
				ficheEmployeTmp.setEmploye(detailProdGen.getEmploye());;
				ficheEmployeTmp.setDateSituation(detailProdGen.getProductionGen().getDate());
				ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
				if(detailProdGen.getMotif()!=null)
				{
					
					
					if(!detailProdGen.getMotif().equals(""))
					{
						ficheEmployeTmp.setMotif(detailProdGen.getMotif());
						
					}
					
				}
				ficheEmployeTmp.setAutoriser(detailProdGen.isAutorisation());
				listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
				
			}
			
			
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
				
				if(detailResponsableProd.getEmploye().getId()==ficheEmploye.getEmploye().getId() && detailResponsableProd.getDateProduction().equals(ficheEmploye.getDateSituation()))
				{
					Trouve=true;
					if((detailResponsableProd.isAbsent()==true || detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 ) )
					{
						
						if(detailResponsableProd.getValider().equals(Constantes.ETAT_VALIDER))
						{
							
							ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
							if(detailResponsableProd.getMotif()!=null)
							{
								
								
								if(!detailResponsableProd.getMotif().equals(""))
								{
									ficheEmploye.setMotif(detailResponsableProd.getMotif());
									
								}
								
							}
							ficheEmploye.setAutoriser(detailResponsableProd.isAutorisation());
							
							listFicheEmployeAbsentTmp.set(j, ficheEmploye);
							
						}
						
					
						
					}
				
						
					
				}
				
				
				
				
			}
		 
		 
			if(Trouve==false)
			{

				if((detailResponsableProd.isAbsent()==true || detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 ) )
				{
					if(detailResponsableProd.getValider().equals(Constantes.ETAT_VALIDER))
					{
						
						 SituationDesEmployeesAbsents ficheEmployeTmp=new SituationDesEmployeesAbsents();
						 
							ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
							ficheEmployeTmp.setDateSituation(detailResponsableProd.getDateProduction());
							ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
							if(detailResponsableProd.getMotif()!=null)
							{
								
								
								if(!detailResponsableProd.getMotif().equals(""))
								{
									ficheEmployeTmp.setMotif(detailResponsableProd.getMotif());
									
								}
								
							}
							ficheEmployeTmp.setAutoriser(detailResponsableProd.isAutorisation());
							listFicheEmployeAbsentTmp.add(ficheEmployeTmp);	
						
					}
					
					
						 
					
					
				}
			}
		 
		 
							
							
		}
	
	
	
	
	return listFicheEmployeAbsentTmp;
	
}
}

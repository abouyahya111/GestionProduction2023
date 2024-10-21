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
import dao.daoImplManager.EmployeReposDAOImpl;
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
import dao.daoManager.EmployeReposDAO;
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
import dao.entity.EmployeRepos;
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


public class ConsulterDesEmployeesRepos extends JLayeredPane implements Constantes{
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
	private List<EmployeRepos> listEmployeRepos=new ArrayList<EmployeRepos>();
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
	private EmployeReposDAO employeReposDAO;
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
	public ConsulterDesEmployeesRepos() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 734);
        try{
        	
        	employeReposDAO=new EmployeReposDAOImpl();
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
					  		     			"Matricule","Nom Employer", "Equipe"					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false
					  		     	};
					  		     	
					  		     	Class[] columnTypes = new Class[] {
											String.class,String.class,String.class
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
								  		 			"Date","Matricule","Nom Employer", "Equipe"								  		 			
								  		 	}
								  		 ) {
								  		 	boolean[] columnEditables = new boolean[] {
								  		 		 false,false,false, false
								  		 	};
								  		 	Class[] columnTypes = new Class[] {
								  		 			Date.class,String.class,String.class,String.class
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
				listEmployeRepos.clear();
				
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
					
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());	
					
					if(depot==null)
					{
						JOptionPane.showMessageDialog(null, "Il faut choisir le depot", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
String requete="";


	if(comboequipe.getSelectedIndex()!=-1)
	{
		
		if(!comboequipe.getSelectedItem().equals(""))
		{
			
			requete=requete+" and equipe='"+comboequipe.getSelectedItem()+"' ";
			
			
		}
		
		
		
		
	}
	
	Employe employe=mapEmploye.get(comboemploye.getSelectedItem());
	
	if(employe!=null)
	{
		
		requete=requete+" and employe.id='"+employe.getId()+"' ";
		
	}
	
	requete=requete+" order by dateSituation , employe.id";
	
					
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					
					
					
					
					
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					listEmployeRepos=employeReposDAO.findByRequete(dateDebutChooser.getDate(),dateFinChooser.getDate(), depot, requete);
			
					
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
					if(listEmployeRepos==null ||  listEmployeRepos.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						
					}else {
					
						afficher_tableMP(listEmployeRepos);
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
		btnImprimer.setIcon(new ImageIcon(ConsulterDesEmployeesRepos.class.getResource("/img/imprimer.png")));
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				String dateDu=simpleDateFormat.format(dateDebutChooser.getDate()) ;
				String dateAu=simpleDateFormat.format(dateFinChooser.getDate());

				if(listEmployeRepos.size()!=0)
				{
					Map parameters = new HashMap();
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					parameters.put("depot", depot.getLibelle());
					parameters.put("periode", "DU : "+ dateDu  +" AU :"+dateAu);
					JasperUtils.imprimerSituationEmployeRepos(listEmployeRepos,parameters);
					
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
	
void afficher_tableMP(List<EmployeRepos> listEmployerepos )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listEmployerepos.size())
		{
			EmployeRepos employeRepos=listEmployerepos.get(j);
	
			
			
			
				Object []ligne={employeRepos.getDateSituation(),employeRepos.getEmploye().getMatricule(),employeRepos.getEmploye().getNomafficher(),employeRepos.getEquipe()};

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
		     			"Date","Matricule","Nom Employer", "Equipe"		
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false, false
		     	};
		    	Class[] columnTypes = new Class[] {
		    			Date.class,String.class,String.class,String.class
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




}

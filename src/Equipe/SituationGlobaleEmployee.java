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
import dao.daoImplManager.DetailEmployeMenageDAOImpl;
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
import dao.daoManager.DetailEmployeMenageDAO;
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
import dao.entity.DetailEmployeMenage;
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


public class SituationGlobaleEmployee extends JLayeredPane implements Constantes{
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
	private List<FicheEmploye> listFicheEmployeTmp=new ArrayList<FicheEmploye>();
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
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
	private DetailEmployeMenageDAO detailEmployeMenageDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";

	private JTextField txtmatricule;
	public SituationGlobaleEmployee() {
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
        	listEmploye=employeDAO.findAll();
        	detailEmployeMenageDAO=new DetailEmployeMenageDAOImpl();
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
								  		 			"Matricule","Nom Employer","Equipe","NBR Jours","Repos","Absent"								  		 			
								  		 	}
								  		 ) {
								  		 	boolean[] columnEditables = new boolean[] {
								  		 		 false,false,false, false, false, false
								  		 	};
								  		 	Class[] columnTypes = new Class[] {
								  		 			String.class,String.class,String.class,String.class,String.class,String.class
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
				listFicheEmployeAbsentTmp.clear();
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
	
	
	listFicheEmploye=calculeCoutEmploye();
	
	
	boolean existe=false;
	for(int i=0;i<listFicheEmploye.size();i++)
	{
		
		existe=false;
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		
		System.out.println(ficheEmploye.getEmploye().getMatricule());
		
	for(int j=0;j<listFicheEmployeAbsentTmp.size() ;j++ )	
	{
		
	SituationDesEmployeesAbsents situationDesEmployeesAbsents=listFicheEmployeAbsentTmp.get(j);
		
	if(situationDesEmployeesAbsents.getEmploye().getId()==ficheEmploye.getEmploye().getId())
	{
		
		existe=true;
		
		if(ficheEmploye.getDelaiEmploye().compareTo(BigDecimal.ZERO)!=0)
		{
			
			situationDesEmployeesAbsents.setNbrJours(String.valueOf(new BigDecimal( situationDesEmployeesAbsents.getNbrJours()).add(BigDecimal.ONE)) );
			
		}else
		{
			situationDesEmployeesAbsents.setNbrAbsent(String.valueOf(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsent()).add(BigDecimal.ONE)));
			
			if(situationDesEmployeesAbsents.getAutorisation().equals(Constantes.CODE_OUI))
			{
				situationDesEmployeesAbsents.setNbrAbsentAutoriser(String.valueOf(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsentAutoriser()).add(BigDecimal.ONE)));
				
			}else
			{
				situationDesEmployeesAbsents.setNbrAbsentNonAutoriser(String.valueOf(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsentNonAutoriser()).add(BigDecimal.ONE)));

				
			}
			
			
			
		}
		
		
		
		listFicheEmployeAbsentTmp.set(j, situationDesEmployeesAbsents);
		
	}
	
	
		
		
		
		
	}
	
	
	if(existe==false)
	{
		
		SituationDesEmployeesAbsents situationDesEmployeesAbsents=new SituationDesEmployeesAbsents();
		situationDesEmployeesAbsents.setEmploye(ficheEmploye.getEmploye());
		situationDesEmployeesAbsents.setNbrRepos(String.valueOf(BigDecimal.ZERO));
		
		if(ficheEmploye.getDelaiEmploye().compareTo(BigDecimal.ZERO)!=0)
		{
			
			situationDesEmployeesAbsents.setNbrJours(String.valueOf(BigDecimal.ONE));
			
		}else
		{
			situationDesEmployeesAbsents.setNbrJours(String.valueOf(BigDecimal.ZERO));
		}
		
		if(ficheEmploye.isAbsent()==true)
		{
			
			situationDesEmployeesAbsents.setNbrAbsent(String.valueOf(BigDecimal.ONE));
			
			if(situationDesEmployeesAbsents.getAutorisation().equals(Constantes.CODE_OUI))
			{
				situationDesEmployeesAbsents.setNbrAbsentAutoriser(String.valueOf(BigDecimal.ONE));
				situationDesEmployeesAbsents.setNbrAbsentNonAutoriser (String.valueOf(BigDecimal.ZERO));
			}else
			{
				situationDesEmployeesAbsents.setNbrAbsentNonAutoriser (String.valueOf(BigDecimal.ONE));
				situationDesEmployeesAbsents.setNbrAbsentAutoriser(String.valueOf(BigDecimal.ZERO));
			}
			
		}else
		{
			situationDesEmployeesAbsents.setNbrAbsent(String.valueOf(BigDecimal.ZERO));
			situationDesEmployeesAbsents.setNbrAbsentNonAutoriser (String.valueOf(BigDecimal.ZERO));
			situationDesEmployeesAbsents.setNbrAbsentAutoriser(String.valueOf(BigDecimal.ZERO));
		}
		
		
		
		situationDesEmployeesAbsents.setNbrRepos(String.valueOf(BigDecimal.ZERO));
		situationDesEmployeesAbsents.setEquipe(ficheEmploye.getEquipe());
		listFicheEmployeAbsentTmp.add(situationDesEmployeesAbsents);
		
	}
	
	
		
		
		
	}
	
	
	
					
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					
					
					
					
					
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					listEmployeRepos=employeReposDAO.findByRequete(dateDebutChooser.getDate(),dateFinChooser.getDate(), depot, requete);
			boolean trouve=false;
					
					for(int i=0;i<listEmployeRepos.size();i++)
					{
						
						trouve=false;	
						
						EmployeRepos employeRepos=listEmployeRepos.get(i);
						
					for(int j=0;j<listFicheEmployeAbsentTmp.size();j++)	
					{
						SituationDesEmployeesAbsents situationDesEmployeesAbsents=listFicheEmployeAbsentTmp.get(j);
						
						if(employeRepos.getEmploye().getId()==situationDesEmployeesAbsents.getEmploye().getId())
						{
							trouve=true;
							situationDesEmployeesAbsents.setNbrRepos(String.valueOf(new BigDecimal( situationDesEmployeesAbsents.getNbrRepos()).add(BigDecimal.ONE)));
							listFicheEmployeAbsentTmp.set(j, situationDesEmployeesAbsents);
							
						}
						
						
					}
					
					
					
					if(trouve==false)
					{
						
						SituationDesEmployeesAbsents situationDesEmployeesAbsents=new SituationDesEmployeesAbsents();
						situationDesEmployeesAbsents.setEmploye(employeRepos.getEmploye());
						
						situationDesEmployeesAbsents.setNbrJours(String.valueOf(BigDecimal.ZERO));			

						situationDesEmployeesAbsents.setNbrAbsent (String.valueOf(BigDecimal.ZERO));
						
						situationDesEmployeesAbsents.setNbrRepos(String.valueOf(BigDecimal.ONE));
						situationDesEmployeesAbsents.setEquipe(employeRepos.getEquipe());
						listFicheEmployeAbsentTmp.add(situationDesEmployeesAbsents);
						
					}
						
						
						
						
					}
					
					
					
					
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
					if(listFicheEmployeAbsentTmp==null ||  listFicheEmployeAbsentTmp.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						
					}else {
					
						afficher_tableMP(listFicheEmployeAbsentTmp);
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
		 comboDepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		if(!comboDepot.getSelectedItem().equals(""))
		 		{
		 			
		 			mapEmploye.clear();;
 					mapMatriculeEmploye.clear();
 					
		 			
		 			if(dateDebutChooser.getDate()==null && dateFinChooser.getDate()==null)
		 			{
		 				comboemploye.removeAllItems();
		 				listEmploye.clear();
		 				comboemploye.addItem("");
		 				
		 				for(int i=0;i<listEmploye.size();i++)
		 				{
		 					
		 					Employe employe=listEmploye.get(i);
		 					comboemploye.addItem(employe.getNomafficher());
		 					mapEmploye.put(employe.getNomafficher(), employe);
		 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 					
		 				}
		 			}else
		 			{
		 				comboemploye.removeAllItems();
		 				listEmploye.clear();
		 				comboemploye.addItem("");
		 				listEmploye=employeDAO.findAllEmploye();
		 				
		 				for(int i=0;i<listEmploye.size();i++)
		 				{
		 					
		 					Employe employe=listEmploye.get(i);
		 					
		 					if(dateDebutChooser.getDate()!=null &&  dateFinChooser.getDate()!=null)
		 					{
		 						if(employe.getBlocageEmploye().equals(Constantes.CODE_OUI))
		 						{
		 							if(!employe.getDateSortie().before(dateDebutChooser.getDate()) )
		 							{
		 								
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 								
		 								
		 							}
		 							
		 							
		 						}else
		 						{
		 							
		 							if(employe.isActif()==true && employe.getDateSortie()==null)
		 							{
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 							}else if(employe.isActif()==false && employe.getDateSortie()!=null)
		 							{
		 								
		 								if(!employe.getDateSortie().before(dateDebutChooser.getDate()) )
			 							{
			 								
			 								comboemploye.addItem(employe.getNomafficher());
						 					mapEmploye.put(employe.getNomafficher(), employe);
						 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
			 								
			 								
			 							}
		 								
		 							}
		 							
		 							
		 							
		 						}
		 							
		 						
		 						
		 					}else if(dateDebutChooser.getDate()!=null &&  dateFinChooser.getDate()==null)
		 					{

		 						if(employe.getBlocageEmploye().equals(Constantes.CODE_OUI))
		 						{
		 							if(!employe.getDateSortie().before(dateDebutChooser.getDate()))
		 							{
		 								
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 								
		 								
		 							}
		 							
		 							
		 						}else
		 						{
		 							
		 							if(employe.isActif()==true)
		 							{
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 							}else if(employe.isActif()==false && employe.getDateSortie()!=null)
		 							{
		 								
		 								if(!employe.getDateSortie().before(dateDebutChooser.getDate()) )
			 							{
			 								
			 								comboemploye.addItem(employe.getNomafficher());
						 					mapEmploye.put(employe.getNomafficher(), employe);
						 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
			 								
			 								
			 							}
		 								
		 							}
		 							
		 							
		 							
		 						}
		 						
		 						
		 					
		 						
		 						
		 					}else if(dateDebutChooser.getDate()==null &&  dateFinChooser.getDate()!=null)
		 					{
		 						


		 						if(employe.getBlocageEmploye().equals(Constantes.CODE_OUI))
		 						{
		 							if(!employe.getDateSortie().before(dateFinChooser.getDate()))
		 							{
		 								
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 								
		 								
		 							}
		 							
		 							
		 						}else
		 						{
		 							
		 							if(employe.isActif()==true)
		 							{
		 								comboemploye.addItem(employe.getNomafficher());
					 					mapEmploye.put(employe.getNomafficher(), employe);
					 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 							}else if(employe.isActif()==false && employe.getDateSortie()!=null)
		 							{
		 								
		 								if(!employe.getDateSortie().before(dateDebutChooser.getDate()) )
			 							{
			 								
			 								comboemploye.addItem(employe.getNomafficher());
						 					mapEmploye.put(employe.getNomafficher(), employe);
						 					mapMatriculeEmploye.put(employe.getMatricule(), employe);
			 								
			 								
			 							}
		 								
		 							}
		 							
		 							
		 							
		 						}
		 						
		 						
		 					
		 						
		 						
		 						
		 						
		 						
		 					}
		 				
		 					
		 				}
		 				
		 				
		 				
		 				
		 				
		 			}
		 			
		 			
		 			
		 		}
		 		
		 		
		 		
		 		
		 	}
		 });
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
		comboDepot.addItem("");
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
		btnImprimer.setIcon(new ImageIcon(SituationGlobaleEmployee.class.getResource("/img/imprimer.png")));
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				String dateDu=simpleDateFormat.format(dateDebutChooser.getDate()) ;
				String dateAu=simpleDateFormat.format(dateFinChooser.getDate());

				if(listFicheEmployeAbsentTmp.size()!=0)
				{
					Map parameters = new HashMap();
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					parameters.put("depot", depot.getLibelle());
					parameters.put("periode", "DU : "+ dateDu  +" AU : "+dateAu);
					JasperUtils.imprimerSituationEmployeGlobal(listFicheEmployeAbsentTmp,parameters);
					
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
		comboequipe.addItem(Constantes.TYPE_EQUIPE_CODE_MENAGE);
		
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
		

		
		
			  		 
	}
	
void afficher_tableMP(List<SituationDesEmployeesAbsents> listSituationDesEmployeesAbsents )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listSituationDesEmployeesAbsents.size())
		{
			SituationDesEmployeesAbsents situationDesEmployeesAbsents=listSituationDesEmployeesAbsents.get(j);
	
			if(new BigDecimal(situationDesEmployeesAbsents.getNbrJours().toString()).compareTo(BigDecimal.ZERO) ==0 )
			{
				situationDesEmployeesAbsents.setNbrJours("-");
				
			}
			if(new BigDecimal(situationDesEmployeesAbsents.getNbrRepos().toString()).compareTo(BigDecimal.ZERO) ==0 )
			{
				situationDesEmployeesAbsents.setNbrRepos("-");
				
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsent().toString()).compareTo(BigDecimal.ZERO) ==0 )
			{
				situationDesEmployeesAbsents.setNbrAbsent("-");
				
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsentAutoriser().toString()).compareTo(BigDecimal.ZERO) ==0 )
			{
				situationDesEmployeesAbsents.setNbrAbsentAutoriser ("-");
				
			}
			
			if(new BigDecimal(situationDesEmployeesAbsents.getNbrAbsentNonAutoriser().toString()).compareTo(BigDecimal.ZERO) ==0 )
			{
				situationDesEmployeesAbsents.setNbrAbsentNonAutoriser ("-");
				
			}
			
				Object []ligne={situationDesEmployeesAbsents.getEmploye().getMatricule(),situationDesEmployeesAbsents.getEmploye().getNomafficher() , situationDesEmployeesAbsents.getEquipe(),situationDesEmployeesAbsents.getNbrJours(),situationDesEmployeesAbsents.getNbrRepos(),situationDesEmployeesAbsents.getNbrAbsent(),situationDesEmployeesAbsents.getNbrAbsentAutoriser(),situationDesEmployeesAbsents.getNbrAbsentNonAutoriser()};

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
		     			"Matricule","Nom Employer","Equipe","Total travail","T.Repos","T.Absent","T.Absent Autoriser","T.Absent Non Autoriser"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false, false,false, false,false
		     	};
		    	Class[] columnTypes = new Class[] {
		    			String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class
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
      table.getColumnModel().getColumn(4).setPreferredWidth(90);
      table.getColumnModel().getColumn(5).setPreferredWidth(90);
      table.getColumnModel().getColumn(6).setPreferredWidth(90);
      table.getColumnModel().getColumn(7).setPreferredWidth(90);
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
List<FicheEmploye> calculeCoutEmploye(){
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	String requet="";
	listFicheEmployeTmp.clear();
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
	 List<DetailEmployeMenage> listDetailEmployeMenage=new ArrayList<DetailEmployeMenage>();
	 Employe employe=mapEmploye.get(comboemploye.getSelectedItem());
	if(comboequipe.getSelectedItem().equals(""))
	{
if(employe==null)
{
	 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by production.date,employe.id");
	 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by productionGen.date,employe.id");
	 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," group by dateProduction,employe.id");
	 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by productionMP.dateProduction,employe.id");
     listDetailEmployeMenage= detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," group by dateTravail,employe.id");

}else
{
	 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"'  group by production.date,employe.id");
	 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"' group by productionGen.date,employe.id");
	 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," and employe.id='"+employe.getId()+"' group by dateProduction,employe.id");
	 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"' group by productionMP.dateProduction,employe.id");
     listDetailEmployeMenage= detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," and employe.id='"+employe.getId()+"' group by dateTravail,employe.id");

	
	
}
		
	}else
	{
		if(employe==null)
		{
			if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION))
			{
				
				 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by production.date,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE))
			{
				 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by productionGen.date,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE))
			{
				 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," group by dateProduction,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP))
			{
				 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," group by productionMP.dateProduction,employe.id");

			}
			else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_MENAGE))
			{
				listDetailEmployeMenage= detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," group by dateTravail,employe.id");

			}
		}else
		{
			
			if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION))
			{
				
				 listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"' group by production.date,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE))
			{
				 listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"' group by productionGen.date,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE))
			{
				 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," and employe.id='"+employe.getId()+"' group by dateProduction,employe.id");

			}else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP))
			{
				 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER," and employe.id='"+employe.getId()+"' group by productionMP.dateProduction,employe.id");

			}
			else if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_MENAGE))
			{
				listDetailEmployeMenage= detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId()," and employe.id='"+employe.getId()+"' group by dateTravail,employe.id");

			}
			
		}
		
	
		
		
		
	}
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////// Detail production /////////////////////////////////////////////////////////////////////
	
for(int i=0;i<listDetailProduction.size();i++){

remise=BigDecimal.ZERO;
delai=BigDecimal.ZERO;
coutHoraire=BigDecimal.ZERO;
heureSupp25=BigDecimal.ZERO;
heureSupp50=BigDecimal.ZERO;
coutSupp25=BigDecimal.ZERO;
coutSupp50=BigDecimal.ZERO;
BigDecimal delaiProd=BigDecimal.ZERO;
DetailProduction detailProduction =listDetailProduction.get(i);

if(!detailProduction.getEmploye().isSalarie()){

delai=detailProduction.getDelaiEmploye() ;
heureSupp25=detailProduction.getHeureSupp25();
heureSupp50=detailProduction.getHeureSupp50();

if(detailProduction.isAbsent()==true){

// String code=Utils.genereCodeDateMoisAnnee(production.getDate());

// Utils.compterAbsenceEmploye(code, detailProduction.getEmploye(), production.getDate());

delai=BigDecimal.ZERO;
heureSupp25=BigDecimal.ZERO;
heureSupp50=BigDecimal.ZERO;



}



if(!detailProduction.getEmploye().isSalarie()){

Boolean Trouve=false;

for(int j=0;j<listFicheEmployeTmp.size();j++)
{

FicheEmploye ficheEmploye=listFicheEmployeTmp.get(j);

if(detailProduction.getEmploye().getId()==ficheEmploye.getEmploye().getId() &&  detailProduction.getProduction().getDate().equals(ficheEmploye.getDateSituation()))
{
Trouve=true;



/*Remplir fiche programme*/


if(delai.compareTo(detailProduction.getProduction().getNbreHeure())<0){
if(detailProduction.isSortie())
delaiProd=ficheEmploye.getDelaiProd().add(detailProduction.getProduction().getNbreHeure());
else 
delaiProd=ficheEmploye.getDelaiProd().add(delai);
}else {
delaiProd=ficheEmploye.getDelaiProd().add(delai);
}

//	coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
delai=delai.add(ficheEmploye.getDelaiEmploye()) ;
String numOF=ficheEmploye.getNumOF()+"-"+detailProduction.getProduction().getNumOF();
heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());


/*	ficheEmploye.setDateSituation(production.getDate());

ficheEmploye.setEmploye(detailProdGen.getEmploye());;

ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setCoutSupp25(coutSupp25);
ficheEmploye.setCoutSupp50(coutSupp50);*/


ficheEmploye.setNumOF(numOF);
//	ficheEmploye.setCoutTotal(coutHoraire);
ficheEmploye.setDelaiProd(delaiProd);
ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setDelaiEmploye(delai);
ficheEmploye.setHeurProduction(detailProduction.getProduction().getNbreHeure().add(ficheEmploye.getHeurProduction()));
if(detailProduction.isSortie()==true)
{
ficheEmploye.setSortie(true);	

}

if(detailProduction.isAbsent()==true)
{
ficheEmploye.setAbsent(true);
}

if(detailProduction.isRetard()==true)
{
ficheEmploye.setRetard(true);
}

if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){



if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

}else {
remise=BigDecimal.ZERO;
}


ficheEmploye.setRemise(remise);


ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);


listFicheEmployeTmp.set(j, ficheEmploye);



}



}

if(Trouve==false){

FicheEmploye ficheEmployeTmp =new FicheEmploye();

ficheEmployeTmp.setNumOF(detailProduction.getProduction().getNumOF());
ficheEmployeTmp.setDateSituation(detailProduction.getProduction().getDate());
ficheEmployeTmp.setDelaiEmploye(delai);
ficheEmployeTmp.setEmploye(detailProduction.getEmploye());
ficheEmployeTmp.setHeureSupp25(heureSupp25);
ficheEmployeTmp.setHeureSupp50(heureSupp50);
ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
ficheEmployeTmp.setHeurProduction(detailProduction.getProduction().getNbreHeure());
if(delai.compareTo(detailProduction.getProduction().getNbreHeure())<0){
if(detailProduction.isSortie())
delaiProd=detailProduction.getProduction().getNbreHeure();
else 
delaiProd=delai;
}else {
delaiProd=delai;
}
if(detailProduction.isSortie()==true)
{
ficheEmployeTmp.setSortie(true);

}else
{
ficheEmployeTmp.setSortie(false);	
}

if(detailProduction.isAbsent()==true)
{
ficheEmployeTmp.setAbsent(true);

}else
{
ficheEmployeTmp.setAbsent(false);	
}

if(detailProduction.isRetard()==true)
{
ficheEmployeTmp.setRetard(true);

}else
{
ficheEmployeTmp.setRetard(false);	
}






if(detailProduction.isAbsent()==false && detailProduction.isSortie()==false && detailProduction.isRetard()==false){



if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

}else {
remise=BigDecimal.ZERO;
}




ficheEmployeTmp.setRemise(remise);
ficheEmployeTmp.setDelaiProd(delaiProd);
listFicheEmployeTmp.add(ficheEmployeTmp);



}
}


}
}


////////////////////////////////////////////////////////////////////////////////Detail ProductionMP /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

for(int i=0;i<listDetailProductionMP.size();i++){

delai=BigDecimal.ZERO;

remise=BigDecimal.ZERO;
coutHoraire=BigDecimal.ZERO;


coutSupp25=BigDecimal.ZERO;
coutSupp50=BigDecimal.ZERO;

DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);

if(!detailProductionMP.getEmploye().isSalarie()){

delai=detailProductionMP.getDelaiEmploye();
heureSupp25=detailProductionMP.getHeureSupp25();
heureSupp50=detailProductionMP.getHeureSupp50();

if(detailProductionMP.isAbsent()==true){

// String code=Utils.genereCodeDateMoisAnnee(productionMP.getDateProduction());

// Utils.compterAbsenceEmploye(code, detailProductionMP.getEmploye(), productionMP.getDateProduction());

delai=BigDecimal.ZERO;
heureSupp25=BigDecimal.ZERO;
heureSupp50=BigDecimal.ZERO;

}




if(!detailProductionMP.getEmploye().isSalarie()){

Boolean Trouve=false;

for(int j=0;j<listFicheEmployeTmp.size();j++)
{

FicheEmploye ficheEmploye=listFicheEmployeTmp.get(j);

if(detailProductionMP.getEmploye().getId()==ficheEmploye.getEmploye().getId() &&  detailProductionMP.getProductionMP().getDateProduction().equals(ficheEmploye.getDateSituation()))
{
Trouve=true;
/*Remplir fiche programme*/
//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal()) ;
delai=delai.add(ficheEmploye.getDelaiEmploye());
heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());
String numOF=ficheEmploye.getNumOF()+"-"+detailProductionMP.getProductionMP().getNumOFMP();
BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(detailProductionMP.getProductionMP().getNbreHeure()) ;
/*	ficheEmploye.setDateSituation(production.getDate());

ficheEmploye.setEmploye(detailProdGen.getEmploye());;

ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setCoutSupp25(coutSupp25);
ficheEmploye.setCoutSupp50(coutSupp50);*/




ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setNumOF(numOF);
//ficheEmploye.setCoutTotal(coutHoraire);
ficheEmploye.setDelaiProd(delaiProd);
ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());
ficheEmploye.setDelaiEmploye(delai);
ficheEmploye.setHeurProduction(detailProductionMP.getProductionMP().getNbreHeure().add(ficheEmploye.getHeurProduction()));
ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);

if(detailProductionMP.isSortie()==true )
{
ficheEmploye.setSortie(true);

}

if(detailProductionMP.isAbsent()==true )
{
ficheEmploye.setAbsent(true);

}
if(detailProductionMP.isRetard()==true )
{
ficheEmploye.setRetard(true);

}


if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){

Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=parametre_remise_ouvrier.getValeur();
if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=parametre_remise_ouvrier_vrac.getValeur();

}else {
remise=BigDecimal.ZERO;
}








ficheEmploye.setRemise(remise);




listFicheEmployeTmp.set(j, ficheEmploye);


}

}

if(Trouve==false){

FicheEmploye	ficheEmploye =new FicheEmploye();
//ficheEmploye.setCoutTotal(coutHoraire);
//ficheEmploye.setNumOF(productionMP.getNumOFMP());
//ficheEmploye.setDateSituation(productionMP.getDateProduction());
ficheEmploye.setDelaiEmploye(delai);
ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setHeurProduction(detailProductionMP.getProductionMP().getNbreHeure());

//ficheEmploye.setCoutSupp25(coutSupp25);
//ficheEmploye.setCoutSupp50(coutSupp50);
ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());

if(detailProductionMP.isSortie()==true)
{
ficheEmploye.setSortie(true);	

}else
{
ficheEmploye.setSortie(false);
}

if(detailProductionMP.isAbsent()==true)
{
ficheEmploye.setAbsent(true);	

}else
{
ficheEmploye.setAbsent(false);
}


if(detailProductionMP.isRetard()==true)
{
ficheEmploye.setRetard(true);	

}else
{
ficheEmploye.setRetard(false);
}




if(detailProductionMP.isAbsent()==false && detailProductionMP.isSortie()==false   && detailProductionMP.isRetard()==false){

Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=parametre_remise_ouvrier.getValeur();
if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=parametre_remise_ouvrier_vrac.getValeur();

}else {
remise=BigDecimal.ZERO;
}




ficheEmploye.setRemise(remise);
ficheEmploye.setDelaiProd(detailProductionMP.getProductionMP().getNbreHeure());
listFicheEmployeTmp.add(ficheEmploye);

}
}


}
}






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

////////////////////////////////////////////////////////////////////////////////////////////////////////////// DetailProdGen ///////////////////////////////////////////////////////////////////////////////


for(int i=0;i<listDetailProdGenerique.size();i++){

DetailProdGen detailProdGen =listDetailProdGenerique.get(i);

remise=BigDecimal.ZERO;
delai=BigDecimal.ZERO;
coutHoraire=BigDecimal.ZERO;
heureSupp25=BigDecimal.ZERO;
heureSupp50=BigDecimal.ZERO;
coutSupp25=BigDecimal.ZERO;
coutSupp50=BigDecimal.ZERO;

if(!detailProdGen.getEmploye().isSalarie()){

delai=detailProdGen.getDelaiEmploye();
heureSupp25=detailProdGen.getHeureSupp25();
heureSupp50=detailProdGen.getHeureSupp50();

if(detailProdGen.isAbsent()==true){

// String code=Utils.genereCodeDateMoisAnnee(detailProdGen.getProductionGen().getDate());
// Utils.compterAbsenceEmploye(code, detailProdGen.getEmploye(), detailProdGen.getProductionGen().getDate());
delai=BigDecimal.ZERO;
heureSupp25=BigDecimal.ZERO;
heureSupp50=BigDecimal.ZERO;
} 



coutHoraire=detailProdGen.getEmploye().getCoutHoraire().multiply(delai);
coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);

if(!detailProdGen.getEmploye().isSalarie()){

Boolean Trouve=false;

for(int j=0;j<listFicheEmployeTmp.size();j++)
{

FicheEmploye ficheEmploye=listFicheEmployeTmp.get(j);

if(detailProdGen.getEmploye().getId()==ficheEmploye.getEmploye().getId() &&  detailProdGen.getProductionGen().getDate().equals(ficheEmploye.getDateSituation()))
{
Trouve=true;


/*Remplir fiche programme*/
//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
delai=delai.add(ficheEmploye.getDelaiEmploye());
heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());
String numOF=ficheEmploye.getNumOF()+"-"+detailProdGen.getProductionGen().getNumOF();
BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(detailProdGen.getProductionGen().getNbreHeure()) ;

/*	ficheEmploye.setDateSituation(production.getDate());

ficheEmploye.setEmploye(detailProdGen.getEmploye());;

ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setCoutSupp25(coutSupp25);
ficheEmploye.setCoutSupp50(coutSupp50);*/


ficheEmploye.setHeureSupp25(heureSupp25);
ficheEmploye.setHeureSupp50(heureSupp50);
ficheEmploye.setNumOF(numOF);
//	ficheEmploye.setCoutTotal(coutHoraire);
ficheEmploye.setDelaiProd(delaiProd);
ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
ficheEmploye.setDelaiEmploye(delai);
if(detailProdGen.isSortie()==true)
{
ficheEmploye.setSortie(true);	

}

if(detailProdGen.isAbsent()==true)
{
ficheEmploye.setAbsent(true);	


}

if(detailProdGen.isRetard()==true)
{
ficheEmploye.setRetard(true);	

}

ficheEmploye.setHeurProduction(ficheEmploye.getHeurProduction().add(detailProdGen.getProductionGen().getNbreHeure()));


if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){


if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

}else {
remise=BigDecimal.ZERO;
}





ficheEmploye.setRemise(remise);


listFicheEmployeTmp.set(j, ficheEmploye);



}




}

if(Trouve==false) {
FicheEmploye	ficheEmployeTmp =new FicheEmploye();
//	ficheEmploye.setCoutTotal(coutHoraire);
ficheEmployeTmp.setNumOF(detailProdGen.getProductionGen().getNumOF());
ficheEmployeTmp.setDateSituation(detailProdGen.getProductionGen().getDate());
ficheEmployeTmp.setDelaiEmploye(delai);
ficheEmployeTmp.setEmploye(detailProdGen.getEmploye());;

ficheEmployeTmp.setHeureSupp25(heureSupp25);
ficheEmployeTmp.setHeureSupp50(heureSupp50);
ficheEmployeTmp.setHeurProduction(detailProdGen.getProductionGen().getNbreHeure());
ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
//	ficheEmploye.setCoutSupp25(coutSupp25);
//	ficheEmploye.setCoutSupp50(coutSupp50);



if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){


if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

}else {
remise=BigDecimal.ZERO;
}

if(detailProdGen.isSortie()==true )
{
ficheEmployeTmp.setSortie(true);

}else
{
ficheEmployeTmp.setSortie(false);	
}

if(detailProdGen.isAbsent()==true )
{
ficheEmployeTmp.setAbsent(true);

}else
{
ficheEmployeTmp.setAbsent(false);	
}

if(detailProdGen.isRetard()==true )
{
ficheEmployeTmp.setRetard(true);

}else
{
ficheEmployeTmp.setRetard(false);	
}


ficheEmployeTmp.setRemise(remise);
ficheEmployeTmp.setDelaiProd(detailProdGen.getProductionGen().getNbreHeure());
listFicheEmployeTmp.add(ficheEmployeTmp);

}

}

}
}

///////////////////////////////////////////////////////////////////////// DetailResponsableProd //////////////////////////////////////////////////////////////////////////////////


for(int i=0;i<listDetailResponsableProd.size();i++){

DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);

FicheEmploye ficheEmployeTmp=new FicheEmploye();

//	ficheEmploye.setCoutTotal(coutTotal);
//ficheEmployeTmp.setNumOF(detailResponsableProd.getProduction().getNumOF());
ficheEmployeTmp.setDateSituation(detailResponsableProd.getDateProduction());
ficheEmployeTmp.setDelaiEmploye(detailResponsableProd.getDelaiEmploye() );
ficheEmployeTmp.setHeureSupp25(detailResponsableProd.getHeureSupp25() );
ficheEmployeTmp.setHeureSupp50(detailResponsableProd.getHeureSupp50() );
ficheEmployeTmp.setSortie(detailResponsableProd.isSortie());
ficheEmployeTmp.setAbsent(detailResponsableProd.isAbsent());
ficheEmployeTmp.setRetard(detailResponsableProd.isRetard());
ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());



if(detailResponsableProd.getRemise()!=null)
{
ficheEmployeTmp.setRemise(detailResponsableProd.getRemise());
}else
{
ficheEmployeTmp.setRemise(BigDecimal.ZERO);
}



if(ficheEmployeTmp.isSortie()==true || ficheEmployeTmp.isAbsent()==true || ficheEmployeTmp.isRetard()==true)
{
ficheEmployeTmp.setRemise(BigDecimal.ZERO);
}

ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());

listFicheEmployeTmp.add(ficheEmployeTmp);

}


///////////////////////////////////////////////////////////////////////// DetailemployeMenage //////////////////////////////////////////////////////////////////////////////////


for(int i=0;i<listDetailEmployeMenage.size();i++){

DetailEmployeMenage detailEmployeMenage=listDetailEmployeMenage.get(i);

FicheEmploye ficheEmployeTmp=new FicheEmploye();

//	ficheEmploye.setCoutTotal(coutTotal);
//ficheEmployeTmp.setNumOF(detailResponsableProd.getProduction().getNumOF());
ficheEmployeTmp.setDateSituation(detailEmployeMenage.getDateTravail());
ficheEmployeTmp.setDelaiEmploye(detailEmployeMenage.getDelaiEmploye());
ficheEmployeTmp.setHeureSupp25(BigDecimal.ZERO);
ficheEmployeTmp.setHeureSupp50(BigDecimal.ZERO);
ficheEmployeTmp.setSortie(false);
ficheEmployeTmp.setAbsent(detailEmployeMenage.isAbsent());
ficheEmployeTmp.setRetard(false);
ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_MENAGE);
//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());



ficheEmployeTmp.setRemise(BigDecimal.ZERO);



ficheEmployeTmp.setEmploye(detailEmployeMenage.getEmploye());

listFicheEmployeTmp.add(ficheEmployeTmp);

}	 






return listFicheEmployeTmp;

	
}




}

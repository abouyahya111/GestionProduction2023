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


public class SituationEmployeProduction extends JLayeredPane implements Constantes{
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
	public SituationEmployeProduction() {
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
        	listEmploye=employeDAO.findAll();
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
					  		     		 false,false, false, false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 			"Matricule","Nom Employer", "Equipe"					  		 			
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false,false
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
				  		     	lblDateDebut.setBounds(10, 32, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(227, 31, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1041, 32, 31, 31);
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
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 32, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(289, 32, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(460, 30, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(519, 29, 191, 26);
		layeredPane.add(comboDepot);
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
	
		JButton btnImprimer = new JButton("Fiche Des Employ\u00E9es Production");
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
						
					JasperUtils.imprimerSituationEmployeProduction(listFicheEmployeAbsentTmp,parameters);
					
					
					
				}
					
				
	  		  	
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(639, 626, 256, 41);
		add(btnImprimer);
		
		JLabel lblEquipe = new JLabel("Equipe :");
		lblEquipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEquipe.setBounds(734, 31, 73, 26);
		layeredPane.add(lblEquipe);
		
		 comboequipe = new JComboBox();
		comboequipe.setBounds(793, 30, 191, 26);
		layeredPane.add(comboequipe);
	for(int i=0;i<listEmploye.size();i++)
	{
		
		Employe employe=listEmploye.get(i);
		comboemploye.addItem(employe.getNom());
		mapEmploye.put(employe.getNom(), employe);
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
			
			
				Object []ligne={situationDesEmployeesAbsents.getEmploye().getMatricule(),situationDesEmployeesAbsents.getEmploye().getNomafficher(), situationDesEmployeesAbsents.getEquipe()};

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
		     			"Matricule","Nom Employer","Equipe"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
     
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
					
				


						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);	 
							
							
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
					
					
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	

	SituationDesEmployeesAbsents ficheEmployeTmp =new SituationDesEmployeesAbsents();
	
	ficheEmployeTmp.setEmploye(detailProduction.getEmploye());
	
	 ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);		 
				
				 listFicheEmployeAbsentTmp.add(ficheEmployeTmp);



			
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

	ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);	

	listFicheEmployeAbsentTmp.set(j, ficheEmploye);
	
	
						
					}
				
				}
			
			if(Trouve==false){
		
		
					SituationDesEmployeesAbsents	ficheEmploye =new SituationDesEmployeesAbsents();
					ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
	                ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);	
					listFicheEmployeAbsentTmp.add(ficheEmploye);
					
			
				
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
		
						
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);	
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
						
				
				}
				
				
				
				
			}
		
		if(Trouve==false) {

			SituationDesEmployeesAbsents	ficheEmployeTmp =new SituationDesEmployeesAbsents();
			ficheEmployeTmp.setEmploye(detailProdGen.getEmploye());;

	        ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);	
			
			listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
			
		


			
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
			
						
						ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
						listFicheEmployeAbsentTmp.set(j, ficheEmploye);
						
				}
				
				
				
				
			}
		 
		 
			if(Trouve==false)
			{

					 SituationDesEmployeesAbsents ficheEmployeTmp=new SituationDesEmployeesAbsents();
					 
					ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
					
		
	ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
					
					listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
						 
					
					
				
			}
		 
		 
							
							
		}
		
	return listFicheEmployeAbsentTmp;
	
}



}

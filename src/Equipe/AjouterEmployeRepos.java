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


public class AjouterEmployeRepos extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<Employe> listEmployeAfficher = new ArrayList<Employe>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapMatriculeEmployeCombo = new HashMap<>();
	private Map< String, Employe> mapEmploye = new HashMap<>();
	
	private List<EmployeRepos> listEmployeReposOld=new ArrayList<EmployeRepos>();
	
	///////////////////////////////////////////// liste des equipes des productions /////////////////////////////////////
	
	private List<DetailProduction> listEquipeProduction=new ArrayList<DetailProduction>();
	private List<DetailProdGen> listEquipeProdGen=new ArrayList<DetailProdGen>();
	private List<DetailProdRes> listEquipeProdRes=new ArrayList<DetailProdRes>();
	private List<DetailProductionMP> listEquipeProductionMP=new ArrayList<DetailProductionMP>();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private List< Depot> listDepot = new ArrayList<Depot>();
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	private EmployeReposDAO employeReposDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private List<SituationDesEmployeesAbsents> listFicheEmployeAbsentTmp=new ArrayList<SituationDesEmployeesAbsents>();
	private List<SituationDesEmployeesAbsentsParJour> listFicheEmployeAbsentParJour=new ArrayList<SituationDesEmployeesAbsentsParJour>();
	private DetailProductionDAO detailProductionDAO;
	
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private ParametreDAO parametreDAO;
	JLabel labelmatricule = new JLabel("Matricule :");
	JLabel labelemploye = new JLabel("Employe :");
	JCheckBox chckbxAbsentsParJour = new JCheckBox("Absents Par Jour");
	JComboBox comboemploye = new JComboBox();
	JComboBox comboequipe = new JComboBox();
	private JTextField txtmatricule = new JTextField();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";
	
	public AjouterEmployeRepos() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 794);
        try{
        	
        	employeReposDAO=new EmployeReposDAOImpl();
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	detailProductionDAO=new DetailProductionDAOImpl();
        	
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	detailProductionMPDAO=new DetailProductionMPDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	listEmploye=employeDAO.findAllEmploye();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					  		     			"Matricule","Nom Employer", "Repos","E_PROD", "E_EMB","E_GEN", "E_PROD_MP"						  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, true, true, true, true, true
					  		     	};
					  		     	
					  		     	Class[] columnTypes = new Class[] {
					  		     			String.class,String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
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
								  		 			"Matricule","Nom Employer", "Repos","E_PROD", "E_EMB","E_GEN", "E_PROD_MP"									  		 			
								  		 	}
								  		 ) {
								  		 	boolean[] columnEditables = new boolean[] {
								  		 		 false,false, true, true, true, true, true
								  		 	};
								  		 	Class[] columnTypes = new Class[] {
								  		 			String.class,String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
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
				  		     	scrollPane.setBounds(9, 156, 1427, 497);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1395, 92);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 36, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1079, 33, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Depot depot=mapDepot.get(comboDepot.getSelectedItem());
			if(dateDebutChooser.getDate()==null)
			{
				
				JOptionPane.showMessageDialog(null, "Veuillez selectionner la date de situation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
					
			}else if(depot==null)
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner le Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}else
			{
				
				String requete="";


				if(comboequipe.getSelectedIndex()!=-1)
				{
					
					if(!comboequipe.getSelectedItem().equals(""))
					{
						
						requete=requete+" and equipe='"+comboequipe.getSelectedItem()+"' ";
						
						
					}
					
					
					
					
				}
				
				Employe employeTmp=mapEmploye.get(comboemploye.getSelectedItem());
				
				if(employeTmp!=null)
				{
					
					requete=requete+" and employe.id='"+employeTmp.getId()+"' ";
					
				}
				
				 
	
				
				
				
				
				listEmployeAfficher.clear();
				mapMatriculeEmploye.clear();
			boolean existe=false;	
			
				
			listEquipeProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(), Constantes.ETAT_OF_TERMINER, requete)	;
			listEquipeProdGen=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(), Constantes.ETAT_OF_TERMINER, requete);
			listEquipeProdRes=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(), requete);	
			listEquipeProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(), Constantes.ETAT_OF_TERMINER, requete)	;
			listEmployeReposOld=employeReposDAO.findByDepotByDate(dateDebutChooser.getDate(), depot.getCode())	;
				for(int i=0;i<listEmploye.size();i++)
				{
					
					existe=false;
					
				Employe employe=listEmploye.get(i)	;
					
				for(int j=0;j<listEquipeProduction.size();j++)	
				{
					
					if(employe.getId()==listEquipeProduction.get(j).getEmploye().getId())
					{
						existe=true;
					}
					
					
				}
					
				for(int f=0;f<listEquipeProdGen.size();f++)	
				{
					
					if(employe.getId()==listEquipeProdGen.get(f).getEmploye().getId())
					{
						existe=true;
					}
					
					
				}	
					
				for(int d=0;d<listEquipeProdRes.size();d++)	
				{
					
					if(employe.getId()==listEquipeProdRes.get(d).getEmploye().getId())
					{
						existe=true;
					}
					
					
				}		
					
					
				for(int v=0;v<listEquipeProductionMP.size();v++)	
				{
					
					if(employe.getId()==listEquipeProductionMP.get(v).getEmploye().getId())
					{
						existe=true;
					}
					
					
				}	
				
				
				for(int l=0;l<listEmployeReposOld.size();l++)	
				{
					
					if(employe.getId()==listEmployeReposOld.get(l).getEmploye().getId())
					{
						existe=true;
					}
					
					
				}
				
				
				
				if(existe==false)
				{
					
					if(employeTmp!=null)
					{
						if(employeTmp.getId()==employe.getId())
						{
							listEmployeAfficher.add(employe);	
							mapMatriculeEmploye.put(employe.getMatricule(), employe);
						}
						
						
					}else
					{
						listEmployeAfficher.add(employe);	
						mapMatriculeEmploye.put(employe.getMatricule(), employe);
					}
					
			
						
				}
				
				}
				
				if(listEmployeAfficher.size()!=0)
				{
					
					afficher_tableEmploye(listEmployeAfficher);
					
					
				}else
				{
					intialiserTableau();
				}
				
				
			}
			
			
			
			
			
			}
		  }
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 36, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(247, 37, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(306, 36, 191, 26);
		layeredPane.add(comboDepot);
		
		JLabel label_1 = new JLabel("Matricule :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(528, 36, 73, 24);
		layeredPane.add(label_1);
		
		txtmatricule = new JTextField();
		txtmatricule.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

				


				

				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					if(!txtmatricule.getText().equals(""))
					{
						Employe employe=	mapMatriculeEmployeCombo.get(txtmatricule.getText());
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
		txtmatricule.setColumns(10);
		txtmatricule.setBounds(594, 36, 130, 24);
		layeredPane.add(txtmatricule);
		
		JLabel label_2 = new JLabel("Employee :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(734, 36, 73, 26);
		layeredPane.add(label_2);
		
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
		comboemploye.setBounds(800, 38, 249, 26);
		AutoCompleteDecorator.decorate(comboemploye);
		layeredPane.add(comboemploye);
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
	
		JButton btnImprimer = new JButton("Valider");
		btnImprimer.setIcon(new ImageIcon(AjouterEmployeRepos.class.getResource("/img/valider.png")));
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				if(dateDebutChooser.getDate()==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez selectionner la date de situation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
						
				}else if(depot==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				if(listEmployeAfficher.size()!=0)
				{
					
					boolean erreur=false;
					
				for(int i=0;i<table.getRowCount();i++)	
				{
					//"Matricule","Nom Employer", "Repos","E_PROD", "E_EMB","E_GEN", "E_PROD_MP"	
					
			int j=0;		
					boolean repos=(boolean) table.getValueAt(i, 2);	
					
					boolean E_PROD=(boolean) table.getValueAt(i, 3);
					boolean E_EMB=(boolean) table.getValueAt(i, 4);
					boolean E_GEN=(boolean) table.getValueAt(i, 5);
					boolean E_PROD_MP=(boolean) table.getValueAt(i, 6);
					
					if(repos==true)
					{
						
						if(E_PROD==true)
						{
							j=j+1;
						}
						if(E_EMB==true)
						{
							j=j+1;
						}
						if(E_GEN==true)
						{
							j=j+1;
						} 
						if(E_PROD_MP==true)
						{
							j=j+1;
						}
						
						if(j>1 || j==0) 
						{
							
							erreur=true;
							
						}
						
						
						
					}
					
					
				}
				
				
				if(erreur==true)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez selectionner juste une equipe pour un employe repos  !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
				}else
				{
					
					boolean valider=false;
					
					for(int i=0;i<table.getRowCount();i++)	
					{
						//"Matricule","Nom Employer", "Repos","E_PROD", "E_EMB","E_GEN", "E_PROD_MP"	
						
					
						boolean repos=(boolean) table.getValueAt(i, 2);	
						
						boolean E_PROD=(boolean) table.getValueAt(i, 3);
						boolean E_EMB=(boolean) table.getValueAt(i, 4);
						boolean E_GEN=(boolean) table.getValueAt(i, 5);
						boolean E_PROD_MP=(boolean) table.getValueAt(i, 6);
						
						String equipe="";
					
						
						if(repos==true)
						{
							
							if(E_PROD==true)
							{
								equipe=Constantes.TYPE_EQUIPE_CODE_PRPDUCTION;
							}
							if(E_EMB==true)
							{
								equipe=Constantes.TYPE_EQUIPE_CODE_GENERIQUE;
							}
							if(E_GEN==true)
							{
								equipe=Constantes.TYPE_EQUIPE_CODE_RESPONSABLE;
							}
							if(E_PROD_MP==true)
							{
								equipe=Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP;
							}
							
							Employe employe=mapMatriculeEmploye.get(table.getValueAt(i, 0).toString());
							
							EmployeRepos employeRepos=new EmployeRepos();
							employeRepos.setEmploye(employe);
							employeRepos.setDateSituation(dateDebutChooser.getDate());
							employeRepos.setDepot(depot);
							employeRepos.setEquipe(equipe);
							employeRepos.setUtilCreation(utilisateur);
							employeReposDAO.add(employeRepos);
							valider=true;
						}
						
						
					}
					
					
					
					if(valider==true)
					{
						
						JOptionPane.showMessageDialog(null, "Les Employees Repos sont ajoutÈ avec succÈes  !!!!","Information",JOptionPane.INFORMATION_MESSAGE);
						
						listEmployeAfficher.clear();
						afficher_tableEmploye(listEmployeAfficher);
						
						return;
						
					}else
					{
						
                      JOptionPane.showMessageDialog(null, "Veuillez cochÈ les employÈes Repos SVP  !!!!","Erreur",JOptionPane.ERROR_MESSAGE);

						
						return;
						
						
					}
					
					
					
					
					
				}
				
					
					

				}else
				{
					JOptionPane.showMessageDialog(null, "Liste employe est vide","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
					
				}
					
					
					
					
					
				}
			
			
			
			}
		});
		btnImprimer.setBounds(439, 681, 155, 24);
		add(btnImprimer);
		
		Depot depot=mapDepot.get(utilisateur.getCodeDepot());
		 
		listEmploye=employeDAO.findByDepot(depot.getCode())	;
		
		comboemploye.addItem("");
			for(int i=0;i<listEmploye.size();i++)
			{
				
				Employe employe=listEmploye.get(i);
				comboemploye.addItem(employe.getNomafficher());
				mapEmploye.put(employe.getNomafficher(), employe);
				mapMatriculeEmployeCombo.put(employe.getMatricule(), employe);
				
			} 
		 
			comboemploye.setSelectedItem("");
			
			  		 
	}
	
void afficher_tableEmploye(List<Employe> listEmployeAfficher )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listEmployeAfficher.size())
		{
			Employe employe=listEmployeAfficher.get(j);
		
				Object []ligne={employe.getMatricule(),employe.getNomafficher(),false,false,false,false,false};

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
		     			"Matricule","Nom Employer", "Repos","E_PROD", "E_EMB","E_GEN", "E_PROD_MP"			
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true, true, true, true,true
		     	};
		    	Class[] columnTypes = new Class[] {
		    			String.class,String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
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
}

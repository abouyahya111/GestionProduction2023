package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.codehaus.groovy.syntax.Reduction;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Comparateur;
import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.jaspersoft.ireport.designer.menu.preview.TXTPreviewAction;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEmployeMenageDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrimeAnciennteDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEmployeMenageDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrimeAnciennteDAO;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.CompteurEmployeProd;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailEmployeMenage;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Parametre;
import dao.entity.PrimeAnciennte;
import dao.entity.RecapFicheEmploye;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;


public class AfficherFicheEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	


	private JTextField txtCNI;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
	private List<FicheEmploye> listFicheEmployeTmp=new ArrayList<FicheEmploye>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Utilisateur utilisateur;
	private JTextField txtTotalDelai;
	private JTextField txtTotalPrime;
	private JTextField txtTotalCout;
	private JTextField txtTotalDu;
	private JComboBox comboDepot = new JComboBox();
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private JTextField txtTotalCout25;
	private JTextField txtTotalCout50;
	private JTextField txtNbrAbsence;
	private JTextField txtreduction;
	private DepotDAO depotDAO;
	private JTextField textPrimeAPayer;
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private ParametreDAO parametreDAO;
private DetailEmployeMenageDAO detailEmployeMenageDAO;
private CoutHorsProdEnAttentDAO coutHorsProdEnAttentDAO;
BigDecimal coutHeurs=BigDecimal.ZERO;
private PrimeAnciennteDAO primeAnciennteDAO;
private JTextField txtPrimeAnciennete;
private JTextField txtTauxAnciennete;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherFicheEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1321, 680);
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
        	detailEmployeMenageDAO=new DetailEmployeMenageDAOImpl();
        	coutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	primeAnciennteDAO=new PrimeAnciennteDAOImpl();
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
					  		     			"Code","Date","Nom Employé", "Délai", "Cout Horaire", "Prime Productivité", "Cout Supp 25", "Cout Supp 50","Avance","Heur Supp 25", "Heur Supp 50"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false, false, false, false, false, false, false, false, false, false, false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 		"Code", "Date", "Nom Employ\u00E9", "D\u00E9lai", "Cout Horaire", "Prime Productivit\u00E9", "Cout Supp 25", "Cout Supp 50", "Avance", "Heur Supp 25", "Heur Supp 50"
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false, false, false, false, false, false, false, false, false, false
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(2).setPreferredWidth(160);
					  		 table.getColumnModel().getColumn(3).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(4).setPreferredWidth(90);
					  		 table.getColumnModel().getColumn(5).setPreferredWidth(90);
					  		 table.getColumnModel().getColumn(6).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(7).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(8).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(9).setPreferredWidth(76);
					  		table.getColumnModel().getColumn(10).setPreferredWidth(76);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1302, 383);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1053, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(262, 10, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1012, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(txtCNI.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir le N° CNI", "Erreur", JOptionPane.ERROR_MESSAGE);
		//	}else if((dateDebutChooser.getDate().getDay()!=1 && dateFinChooser.getDate().getDay()!=15) ||(dateDebutChooser.getDate().getDay()!=16 && dateFinChooser.getDate().getDay()!=30))
					//{
				//JOptionPane.showMessageDialog(null, "la periode de travail doit etre saisir comme ça : date debut : 01/mois/année date fin 01/mois/année ou date debut : 16/mois/année date fin 30/mois/année ", "Erreur", JOptionPane.ERROR_MESSAGE);
					
						//	return;	
			
			}else if(comboDepot.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le depot ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				Employe employe =employeDAO.findByCodeEmploye(txtCNI.getText(),"",depot.getId());
				if(employe==null){
					JOptionPane.showMessageDialog(null, "Employé n'existe pas !!", "Erreur", JOptionPane.ERROR_MESSAGE);
					intialiserTableau();
					vider();
					listFicheEmploye.clear();
				}else {
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					
					listFicheEmploye=calculeCoutEmploye();
					
					
					
					//calculerTotaux(listFicheEmploye);
					if(listFicheEmploye==null ||  listFicheEmploye.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						vider();
						listFicheEmploye.clear();
					}else {
						remplirMapAvance();
						Collections.sort(listFicheEmploye, new Comparateur());

						 afficher_tableMP(listFicheEmploye);
					}
					
					
				}
			
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 11, 140, 24);
		layeredPane.add(dateFinChooser);
		
		txtCNI = new JTextField();
		txtCNI.setBounds(555, 12, 130, 22);
		layeredPane.add(txtCNI);
		txtCNI.setColumns(10);
		util.Utils.copycoller(txtCNI);
		JLabel lblMatricule = new JLabel("Matricule:");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMatricule.setBounds(495, 11, 102, 24);
		layeredPane.add(lblMatricule);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(695, 8, 130, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(764, 10, 191, 26);
		layeredPane.add(comboDepot);
		
		JButton btnValiderAvance = new JButton("Valider Avance");
		btnValiderAvance.setIcon(imgValider);
		btnValiderAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!remplirMapAvance())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir au moins une avance ", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {

				//	validerAvance(listFicheEmploye);
					
					JOptionPane.showMessageDialog(null, "L'avance a été validée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValiderAvance.setBounds(290, 574, 129, 24);
		add(btnValiderAvance);
		
		JLabel lblTotal = new JLabel("Total D\u00E9lai");
		lblTotal.setBounds(265, 459, 76, 23);
		add(lblTotal);
		
		txtTotalDelai = new JTextField();
		txtTotalDelai.setBackground(Color.WHITE);
		txtTotalDelai.setEditable(false);
		txtTotalDelai.setBounds(352, 457, 104, 26);
		add(txtTotalDelai);
		txtTotalDelai.setColumns(10);
		
		txtTotalPrime = new JTextField();
		txtTotalPrime.setBackground(Color.WHITE);
		txtTotalPrime.setEditable(false);
		txtTotalPrime.setColumns(10);
		txtTotalPrime.setBounds(699, 457, 97, 26);
		add(txtTotalPrime);
		
		txtTotalCout = new JTextField();
		txtTotalCout.setBackground(Color.WHITE);
		txtTotalCout.setEditable(false);
		txtTotalCout.setColumns(10);
		txtTotalCout.setBounds(520, 459, 104, 26);
		add(txtTotalCout);
		
		JButton btnImprimer = new JButton("Fiche Employé");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(listFicheEmploye.size()!=0)
				 {
					 Parametre parametreNbrHeuresTotal=parametreDAO.findByLibelle(Constantes.NBR_HEURES_TOTAL);
				      String sommetowords = "";
	                    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    final String dateDu = dateFormat.format(dateDebutChooser.getDate());
	                    final String dateAu = dateFormat.format(dateFinChooser.getDate());
	                    final List<FicheEmploye> listFicheEmploye = calculeCoutEmploye();
	                    final BigDecimal coutHoraire =coutHeurs;
	                    final FicheEmploye ficheEmploye = listFicheEmploye.get(0);
	                    Collections.sort(listFicheEmploye, new Comparateur());
	                    
	                    BigDecimal totalheurtravauiller=new BigDecimal(txtTotalDelai.getText().replace(",", "."));
	                    BigDecimal totalcout=new BigDecimal(txtTotalCout.getText().replace(",", "."));
	                    BigDecimal totaldu=new BigDecimal(txtTotalDu.getText().replace(",", "."));
	                    if(ficheEmploye.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_MENAGE))
	                    {
	                    	  if(totalheurtravauiller.compareTo(parametreNbrHeuresTotal.getValeur())>0)
	  	                    {
	                    		  totalheurtravauiller=parametreNbrHeuresTotal.getValeur();
	                    		  totalcout=totalheurtravauiller.multiply(coutHoraire);
	                    		  totaldu=totalcout;
	  	                    }
	                    }
	                  
	                    
	                    
	                    final Map parameters = new HashMap();
	                    parameters.put("dateDu", dateDu);
	                    parameters.put("dateAu", dateAu);
	                    parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
	                    parameters.put("nom", ficheEmploye.getEmploye().getNomafficher());
	                    parameters.put("totalCout",totalcout.toString());
	                    parameters.put("totalAvance", totalheurtravauiller.toString());
	                    parameters.put("totalPrime", txtTotalPrime.getText());
	                    parameters.put("totalDu", totaldu.toString());
	                    parameters.put("COUT_HORAIRE", coutHoraire);
	                    parameters.put("NBR_ABSENCE", txtNbrAbsence.getText());
	                    parameters.put("PRIME_APAYER", textPrimeAPayer.getText());
	                    sommetowords = ConverterNumberToWords.converter(String.valueOf(AfficherFicheEmploye.this.txtTotalDu.getText()));
	                    parameters.put("somme", sommetowords);
	                    JasperUtils.imprimerFicheEmploye(listFicheEmploye, parameters, ficheEmploye.getEmploye().getNomafficher());
	                
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!! ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(615, 574, 136, 24);
		add(btnImprimer);
		
		txtTotalDu = new JTextField();
		txtTotalDu.setBackground(Color.WHITE);
		txtTotalDu.setEditable(false);
		txtTotalDu.setColumns(10);
		txtTotalDu.setBounds(352, 529, 104, 26);
		add(txtTotalDu);
		
		JLabel lblTotalDu = new JLabel("Total A payer");
		lblTotalDu.setBounds(275, 529, 97, 26);
		add(lblTotalDu);
		
		JButton btnImprimerBulletinPaie = new JButton("Bulletin Paie");
		btnImprimerBulletinPaie.setIcon(imgImprimer);
		btnImprimerBulletinPaie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Parametre parametreNbrHeuresTotal=parametreDAO.findByLibelle(Constantes.NBR_HEURES_TOTAL);
				Parametre parametreCoutPrimePanier=parametreDAO.findByLibelle(Constantes.COUT_PRIME_PANIER);
				Parametre parametreCoutPrimetransport=parametreDAO.findByLibelle(Constantes.COUT_PRIME_TRANSPORT);
				Parametre parametrePrimePlafonne=parametreDAO.findByLibelle(Constantes.PRIME_PLAFONNE);
				
				
				if(parametreNbrHeuresTotal==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Nombre des heures total dans le Menu parametre");
					
					return;
				}
				
				if(parametreCoutPrimePanier==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Cout de Prime Panier dans le Menu parametre");
					
					return;
				}
				
				if(parametreCoutPrimetransport==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Cout de Prime de Transport dans le Menu parametre");
					
					return;
				}
				
				if(parametrePrimePlafonne==null)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Prime Plafonne dans le Menu parametre SVP : PRIME_PLAFONNE");
					
					return;
				}
				
				
				BigDecimal PrimeTotal=BigDecimal.ZERO;
				BigDecimal PrimePanier=BigDecimal.ZERO;
				BigDecimal PrimeTransport=BigDecimal.ZERO;
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
	  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
	  		  	BigDecimal Primesupplementaire=BigDecimal.ZERO;
	  		  	
				// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
				 
				 
				 BigDecimal netApayerSupplementaire=BigDecimal.ZERO;
				 BigDecimal PrimeSupplementaire=BigDecimal.ZERO;
				 BigDecimal ladifferencePrime=BigDecimal.ZERO;
				 
				
				 totalHoraire=new BigDecimal(txtTotalDelai.getText());
				 
				 
				 if(totalHoraire.compareTo(parametreNbrHeuresTotal.getValeur())>0)
				 {
					
					 
					 BigDecimal salaireBrutSupplementaire= coutHeurs.multiply(totalHoraire);
					 BigDecimal retenu226Supplementaire=salaireBrutSupplementaire.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448Supplementaire=salaireBrutSupplementaire.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenuSupplementaire=retenu448Supplementaire.add(retenu226Supplementaire);
					
					 
					 netApayerSupplementaire=salaireBrutSupplementaire.subtract(totalRetenuSupplementaire);
					 
					 
					 totalHoraire=parametreNbrHeuresTotal.getValeur();
					 
					 
					 
				 }
				 
				 BigDecimal salaireDebase= coutHeurs.multiply(totalHoraire);
				 BigDecimal PrimeAnciente=new BigDecimal(txtPrimeAnciennete.getText()) ;
				 BigDecimal TauxAnciente=new BigDecimal(txtTauxAnciennete.getText()) ;
				 BigDecimal salaireBrut=salaireDebase.add(PrimeAnciente);
				 BigDecimal retenu226=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
				 BigDecimal retenu448=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
				 BigDecimal totalRetenu=retenu448.add(retenu226);
				 BigDecimal netApayer=BigDecimal.ZERO; 
				 			
				 
				 if(netApayerSupplementaire.compareTo(BigDecimal.ZERO)!=0)
				 {
					
					 Primesupplementaire=netApayerSupplementaire.subtract(salaireBrut.subtract(totalRetenu));
					 
				 }
				 if(new BigDecimal(textPrimeAPayer.getText()).compareTo(BigDecimal.ZERO)!=0)
					{
					 
					 PrimeTotal=new BigDecimal(textPrimeAPayer.getText()).add(Primesupplementaire);
					 
					}
				 
				 if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())>0)
				 {
					 PrimeTotal= parametrePrimePlafonne.getValeur();
				 }
				 
				
				String dateEntre="";
				if(ficheEmploye.getEmploye().getDatePremiereProduction()!=null)
				{
					dateEntre=dateFormat.format(ficheEmploye.getEmploye().getDatePremiereProduction());
				}
				
				PrimePanier=PrimeTotal.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
				PrimeTransport=PrimeTotal.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
								if(PrimePanier.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)>0   &&  PrimePanier.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) <= 0 )
								{
									
									PrimePanier=PrimePanier.subtract(PrimePanier.remainder(BigDecimal.ONE)).add(new BigDecimal(0.50));
									
									
								}else if(PrimePanier.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) > 0)
								{
									
									PrimePanier=PrimePanier.subtract(PrimePanier.remainder(BigDecimal.ONE)).add(BigDecimal.ONE);

									
								}
				
				
				if(PrimeTransport.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)>0   &&  PrimeTransport.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) <= 0 )
				{
					
					PrimeTransport=PrimeTransport.subtract(PrimeTransport.remainder(BigDecimal.ONE)).add(new BigDecimal(0.50));
					
					
				}else if(PrimeTransport.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) > 0)
				{
					
					PrimeTransport=PrimeTransport.subtract(PrimeTransport.remainder(BigDecimal.ONE)).add(BigDecimal.ONE);

					
				}
				
			
				netApayer= salaireBrut.subtract(totalRetenu);

				
				Map parameters = new HashMap();
				if(new BigDecimal(textPrimeAPayer.getText()).compareTo(BigDecimal.ZERO)!=0)
				{
					
					
					
					if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())<=0)
					{
						
						parameters.put("primepanier", "Cout Prime Panier");
						parameters.put("primetransport", "Cout Prime Transport");
						parameters.put("coutprimepanier",PrimePanier.setScale(2, RoundingMode.FLOOR) );
						parameters.put("coutprimetransport", PrimeTransport.setScale(2, RoundingMode.FLOOR));
						
						netApayer= salaireBrut.subtract(totalRetenu).add(PrimePanier).add(PrimeTransport);
						
						
					}else {
						
						parameters.put("primepanier", "Cout Prime Panier");
						parameters.put("primetransport", "Cout Prime Transport");
						parameters.put("coutprimepanier", parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP));
						parameters.put("coutprimetransport", parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP));
						
						ladifferencePrime=PrimeTotal.subtract(parametrePrimePlafonne.getValeur());
						
						netApayer= salaireBrut.subtract(totalRetenu).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP)).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP));

						
					}
						
					
				}
				parameters.put("dateDu", dateDu);
				parameters.put("dateAu", dateAu);
				parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
				parameters.put("nom", ficheEmploye.getEmploye().getNom());
				parameters.put("qualif", ficheEmploye.getTypeResEmploye().getLibelle());
				parameters.put("service", ficheEmploye.getEmploye().getService());
				parameters.put("dateEntre",dateEntre );
				
				parameters.put("nbreHoraire", totalHoraire+"");
				parameters.put("tauxHoraire", coutHeurs+"");
				parameters.put("salairedebase", salaireDebase.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("primeanciennete", PrimeAnciente.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("tauxanciennete", TauxAnciente.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("salaireBrut", salaireBrut.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("taux448", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)+"");
				parameters.put("taux226", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)+"");
				
				parameters.put("retenu226", retenu226.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("retenu448", retenu448.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("totalRetenu", totalRetenu.setScale(2, RoundingMode.FLOOR)+"");
				parameters.put("netApayer", netApayer.setScale(2, RoundingMode.FLOOR)+"");
				
				JasperUtils.imprimerBulletinPaieEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
				
				//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimerBulletinPaie.setBounds(758, 574, 114, 24);
		add(btnImprimerBulletinPaie);
		
		txtTotalCout25 = new JTextField();
		txtTotalCout25.setEditable(false);
		txtTotalCout25.setColumns(10);
		txtTotalCout25.setBackground(Color.WHITE);
		txtTotalCout25.setBounds(907, 457, 107, 26);
		add(txtTotalCout25);
		
		txtTotalCout50 = new JTextField();
		txtTotalCout50.setEditable(false);
		txtTotalCout50.setColumns(10);
		txtTotalCout50.setBackground(Color.WHITE);
		txtTotalCout50.setBounds(1115, 459, 107, 26);
		add(txtTotalCout50);
		
		txtNbrAbsence = new JTextField();
		txtNbrAbsence.setEditable(false);
		txtNbrAbsence.setColumns(10);
		txtNbrAbsence.setBackground(Color.WHITE);
		txtNbrAbsence.setBounds(352, 493, 104, 26);
		add(txtNbrAbsence);
		
		JLabel lblNombreAbsence = new JLabel("Nombre Absence");
		lblNombreAbsence.setBounds(265, 493, 97, 26);
		add(lblNombreAbsence);
		
		txtreduction = new JTextField();
		txtreduction.setEditable(false);
		txtreduction.setColumns(10);
		txtreduction.setBackground(Color.WHITE);
		txtreduction.setBounds(520, 496, 104, 26);
		add(txtreduction);
		
		JLabel lblTotalCout = new JLabel("Total Cout");
		lblTotalCout.setBounds(466, 459, 114, 23);
		add(lblTotalCout);
		
		JLabel lblTotalPrime = new JLabel("Total Prime");
		lblTotalPrime.setBounds(628, 459, 114, 23);
		add(lblTotalPrime);
		
		JLabel lblTotalCout_1 = new JLabel("Total Cout 25");
		lblTotalCout_1.setBounds(806, 459, 66, 23);
		add(lblTotalCout_1);
		
		JLabel lblTotalCout_2 = new JLabel("Total Cout 50");
		lblTotalCout_2.setBounds(1025, 461, 114, 23);
		add(lblTotalCout_2);
		
		JLabel lblTotalRduction = new JLabel("R\u00E9duction");
		lblTotalRduction.setBounds(466, 496, 86, 24);
		add(lblTotalRduction);
		
		textPrimeAPayer = new JTextField();
		textPrimeAPayer.setEditable(false);
		textPrimeAPayer.setColumns(10);
		textPrimeAPayer.setBackground(Color.WHITE);
		textPrimeAPayer.setBounds(699, 493, 97, 26);
		add(textPrimeAPayer);
		
		JLabel lblPrimeAPayer = new JLabel("Prime A Payer");
		lblPrimeAPayer.setBounds(631, 495, 114, 23);
		add(lblPrimeAPayer);
		
		JLabel lblPrimeAnciennete = new JLabel("Taux Anciennete");
		lblPrimeAnciennete.setBounds(807, 496, 97, 23);
		add(lblPrimeAnciennete);
		
		txtPrimeAnciennete = new JTextField();
		txtPrimeAnciennete.setEditable(false);
		txtPrimeAnciennete.setColumns(10);
		txtPrimeAnciennete.setBackground(Color.WHITE);
		txtPrimeAnciennete.setBounds(1115, 496, 107, 26);
		add(txtPrimeAnciennete);
		
		JLabel label_1 = new JLabel("Prime Anciennete");
		label_1.setBounds(1023, 498, 97, 23);
		add(label_1);
		
		txtTauxAnciennete = new JTextField();
		txtTauxAnciennete.setEditable(false);
		txtTauxAnciennete.setColumns(10);
		txtTauxAnciennete.setBackground(Color.WHITE);
		txtTauxAnciennete.setBounds(907, 493, 107, 26);
		add(txtTauxAnciennete);
		
		JButton btnFicheEmployAdmin = new JButton("Fiche Employ\u00E9 Admin");
		btnFicheEmployAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				 if(listFicheEmploye.size()!=0)
				 {
					 Parametre parametreNbrHeuresTotal=parametreDAO.findByLibelle(Constantes.NBR_HEURES_TOTAL);
				      String sommetowords = "";
	                    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    final String dateDu = dateFormat.format(dateDebutChooser.getDate());
	                    final String dateAu = dateFormat.format(dateFinChooser.getDate());
	                    final List<FicheEmploye> listFicheEmploye = calculeCoutEmploye();
	                    
	                    final BigDecimal coutHoraire =coutHeurs;
	                    final FicheEmploye ficheEmploye = listFicheEmploye.get(0);
	                    Collections.sort(listFicheEmploye, new Comparateur());
	                    
	                    LocalDate from = LocalDate.of(DateUtils.getAnnee(listFicheEmploye.get(0).getEmploye().getDatePremiereProduction()), DateUtils.getMois(listFicheEmploye.get(0).getEmploye().getDatePremiereProduction()), DateUtils.getJour(listFicheEmploye.get(0).getEmploye().getDatePremiereProduction()));
	  			        LocalDate to = LocalDate.of(DateUtils.getAnnee(dateFinChooser.getDate()), DateUtils.getMois(dateFinChooser.getDate()), DateUtils.getJour(dateFinChooser.getDate()));

	  			        Period period = Period.between(from, to);
	                    
	                    PrimeAnciennte primeAnciennte=  primeAnciennteDAO.PrimeByMinByMaxByDatePrim(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ), dateFinChooser.getDate());	 
	                    if(primeAnciennte!=null)
	                    {
for(int j=0;j<listFicheEmploye.size();j++)
{
	
	FicheEmploye ficheEmployeTmp=listFicheEmploye.get(j);
	
	ficheEmployeTmp.setCoutHoraire((ficheEmployeTmp.getCoutHoraire().multiply(primeAnciennte.getTaux().divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(ficheEmployeTmp.getCoutHoraire()));
}
	                    }
	                    BigDecimal totalheurtravauiller=new BigDecimal(txtTotalDelai.getText().replace(",", "."));
	                    BigDecimal totalcout=new BigDecimal(txtTotalCout.getText().replace(",", "."));
	                    BigDecimal totaldu=new BigDecimal(txtTotalDu.getText().replace(",", "."));
	                    if(ficheEmploye.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_MENAGE))
	                    {
	                    	  if(totalheurtravauiller.compareTo(parametreNbrHeuresTotal.getValeur())>0)
	  	                    {
	                    		  totalheurtravauiller=parametreNbrHeuresTotal.getValeur();
	                    		  totalcout=totalheurtravauiller.multiply(coutHoraire);
	                    		  totaldu=totalcout;
	  	                    }
	                    }
	                  
	                    
	                    
	                    final Map parameters = new HashMap();
	                    parameters.put("dateDu", dateDu);
	                    parameters.put("dateAu", dateAu);
	                    parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
	                    parameters.put("nom", ficheEmploye.getEmploye().getNomafficher());
	                    parameters.put("totalCout",totalcout.toString());
	                    parameters.put("totalAvance", totalheurtravauiller.toString());
	                    parameters.put("totalPrime", txtTotalPrime.getText());
	                    parameters.put("totalDu", totaldu.toString());
	                    parameters.put("COUT_HORAIRE", coutHoraire);
	                    parameters.put("NBR_ABSENCE", txtNbrAbsence.getText());
	                    parameters.put("PRIME_APAYER", textPrimeAPayer.getText());
	                    sommetowords = ConverterNumberToWords.converter(String.valueOf(AfficherFicheEmploye.this.txtTotalDu.getText()));
	                    parameters.put("somme", sommetowords);
	                    JasperUtils.imprimerFicheEmployeAdmin (listFicheEmploye, parameters, ficheEmploye.getEmploye().getNomafficher());
	                
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!! ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
			}
		});
		btnFicheEmployAdmin.setBounds(429, 575, 136, 24);
		add(btnFicheEmployAdmin);
	
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
	
	   		     
				  		 
	}
	
void afficher_tableMP(List<FicheEmploye> listFicheEmploye)
	{
		intialiserTableau();
		  int i=0;
		 
			BigDecimal totalPrime=BigDecimal.ZERO;
			BigDecimal totalCout=BigDecimal.ZERO;
			BigDecimal totalDu=BigDecimal.ZERO;
			BigDecimal totalCout25=BigDecimal.ZERO;
			BigDecimal totalcout50=BigDecimal.ZERO;
			Employe employe=null;
			
			BigDecimal totalDelai=BigDecimal.ZERO;
			
			BigDecimal coutHoraire=BigDecimal.ZERO;
			
			BigDecimal coutHeureSupp25=BigDecimal.ZERO;
			BigDecimal coutHeureSupp50=BigDecimal.ZERO;
			
			int compteurbsenseEmployernbr=0;
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		  totalHoraire=BigDecimal.ZERO;
		  String date="";
			
			
			BigDecimal avance=BigDecimal.ZERO;
			NumberFormat nf = new DecimalFormat("0.###");
			while(i<listFicheEmploye.size())
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				FicheEmploye ficheEmploye=listFicheEmploye.get(i);
				coutHoraire=coutHeurs.multiply(ficheEmploye.getDelaiEmploye());
				coutHeureSupp25=mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25).multiply(ficheEmploye.getHeureSupp25());
				coutHeureSupp50=mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50).multiply(ficheEmploye.getHeureSupp50());
				if(ficheEmploye.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0)
					compteurbsenseEmployernbr++;
				
				
				if(ficheEmploye.isSortie()==true || ficheEmploye.isRetard()==true)
				{
					
					compteurbsenseEmployernbr++;
					
				}
					
				
			
				
				 date=dateFormat.format(ficheEmploye.getDateSituation());
				
					totalPrime=totalPrime.add(ficheEmploye.getRemise());
					totalCout=totalCout.add(coutHoraire);
					totalDelai=totalDelai.add(ficheEmploye.getDelaiEmploye());
					totalHoraire=totalHoraire.add(ficheEmploye.getDelaiEmploye());
					totalCout25=totalCout25.add(coutHeureSupp25);
					totalcout50=totalcout50.add(coutHeureSupp50);
					
					employe=ficheEmploye.getEmploye();
					
					
				Object []ligne={ficheEmploye.getId(),date,ficheEmploye.getEmploye().getNomafficher(),ficheEmploye.getDelaiEmploye(),coutHoraire,ficheEmploye.getRemise(),coutHeureSupp25,coutHeureSupp50,ficheEmploye.getHeureSupp25(),ficheEmploye.getHeureSupp50()};

				modeleMP.addRow( ligne);
				i++;
			}
			
			String dateabsent=Utils.genereCodeDateMoisAnnee(dateDebutChooser.getDate());
			
			
			
			
			
		/*	CompteurAbsenceEmploye	compteurbsenseEmployer=compteurabsenceemployedao.findByDateAbsencePeriode(dateabsent, listFicheEmploye.get(0).getEmploye().getId());
			if(compteurbsenseEmployer==null)
			{
				compteurbsenseEmployernbr=0;
			}else
			{
				compteurbsenseEmployernbr=compteurbsenseEmployer.getCompteur();
			}*/
			totalDu=totalCout.add(totalCout25).add(totalcout50);
			 DecimalFormat format = new DecimalFormat("#.00");
			//totalDu=(totalPrime+totalCout)-totalAvance;
		BigDecimal Totalprimeanciennte=BigDecimal.ZERO;
		BigDecimal TauxAnciennete=BigDecimal.ZERO;
			 if(employe!=null)
				{
					  
	  					 LocalDate from = LocalDate.of(DateUtils.getAnnee(employe.getDatePremiereProduction()), DateUtils.getMois(employe.getDatePremiereProduction()), DateUtils.getJour(employe.getDatePremiereProduction()));
	  			        LocalDate to = LocalDate.of(DateUtils.getAnnee(dateFinChooser.getDate()), DateUtils.getMois(dateFinChooser.getDate()), DateUtils.getJour(dateFinChooser.getDate()));

	  			        Period period = Period.between(from, to);

	  			        System.out.println(period.getYears() + " years,");
	  			        System.out.println(period.getMonths() + " months,");
	  			        System.out.println(period.getDays() + " days");
	  			         System.out.println(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ));
	  			         
	  			     PrimeAnciennte primeAnciennte=  primeAnciennteDAO.PrimeByMinByMaxByDatePrim(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ), dateFinChooser.getDate());	 
if(primeAnciennte!=null)
{
	Totalprimeanciennte=(primeAnciennte.getTaux().divide(new BigDecimal(100) , 6, RoundingMode.HALF_UP)).multiply(totalDu);
	
	TauxAnciennete=primeAnciennte.getTaux();
	
}
	  			   
			 
				}
			 
			 
			 
			
			txtTotalCout.setText(format.format(totalCout));
			txtTotalPrime.setText(totalPrime+""); 
			 txtPrimeAnciennete.setText(Totalprimeanciennte+"");
			 txtTauxAnciennete.setText(TauxAnciennete+"");
			//txtTotalDu.setText(totalDu+"");
			txtTotalCout25.setText(totalCout25+"");
			txtTotalCout50.setText(totalcout50+"");
			txtNbrAbsence.setText(compteurbsenseEmployernbr+"");
			txtTotalDelai.setText(totalDelai+"");
			

			calculertotalApayer(totalDu, compteurbsenseEmployernbr, totalPrime);
			
				
			
	}

boolean remplirMapAvance(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("")){
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 9).toString());
			trouve=true;
		}else {
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}

void validerAvance(List<FicheEmploye> listFicheEmploye){/*
	BigDecimal avance=BigDecimal.ZERO;
	BigDecimal totalAvance=BigDecimal.ZERO;
	BigDecimal totalPrime=BigDecimal.ZERO;
	BigDecimal totalCout=BigDecimal.ZERO;
	BigDecimal totalDu=BigDecimal.ZERO;
	for(int i=0;i<listFicheEmploye.size();i++){	
		
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		
		ficheEmploye.setAvance(avance);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		totalAvance=totalAvance.add(avance);
		totalPrime=totalPrime.add(ficheEmploye.getRemise());
		totalCout=totalCout.add(ficheEmploye.getCoutTotal());
		
		ficheEmployeDAO.edit(ficheEmploye);
		
	}
	
	
	totalDu=(totalPrime.add(totalCout)).subtract(totalAvance);
	
	  DecimalFormat format = new DecimalFormat("#0.00");
	txtTotalDelai.setText(totalAvance+"");
	txtTotalCout.setText(totalCout+"");
	txtTotalPrime.setText(totalPrime+""); 
	txtTotalDu.setText(format.format(totalDu));
	
*/}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Date","Nom Employé", "Délai", "Cout Horaire", "Prime Productivité", "Cout Supp 25", "Cout Supp 50","Heur Supp 25", "Heur Supp 50"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false, false, false, false, false, false, false, false, false, false, false
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


void calculertotalApayer(BigDecimal total,int nbabsence,BigDecimal prime)
{
	
	BigDecimal netapayer=BigDecimal.ZERO;
	BigDecimal primeAPayer=BigDecimal.ZERO;
	BigDecimal primeTmp=prime;
	BigDecimal nbabsencetmp=new BigDecimal(nbabsence);
	BigDecimal val=new BigDecimal(0.25);
	BigDecimal reduction=primeTmp.multiply(nbabsencetmp).multiply(val);
		
	if(nbabsence>=4)
	{
		
		primeAPayer=prime.subtract(primeTmp);
		
	}else if(nbabsence<4 && nbabsence>0)
	{
		
		primeAPayer=prime.subtract(reduction);
	}
	else if(nbabsence==0)
	{
		primeAPayer=prime;
	}
//	primeAPayer=primeAPayer-(primeAPayer.remainder(BigDecimal.ONE));
		/*
		 * double resteDivision=Math.IEEEremainder(primeAPayer.doubleValue(), 5l);
		 * 
		 * 
		 * // IEEERemainder = dividend - (divisor * Math.Round(dividend / divisor))
		 * 
		 * System.out.println("primeAPayer : "+primeAPayer.doubleValue());
		 * System.out.println("le Nombre Double  : "+5l);
		 * System.out.println("Le Rest est : "+resteDivision);
		 * primeAPayer=primeAPayer.subtract(new BigDecimal(resteDivision));
		 * 
		 *
		 */
	int primefinale =primeAPayer.divide(new BigDecimal(50)).intValue() * 50;
	 netapayer=total.add(new BigDecimal(primefinale));
	 
	if(netapayer.compareTo(BigDecimal.ZERO)<0) { netapayer=BigDecimal.ZERO; }
	
	
	textPrimeAPayer.setText(primefinale+"");
	txtreduction.setText((reduction.setScale(6)+""));
	 DecimalFormat format = new DecimalFormat("#.00");
	txtTotalDu.setText(format.format(netapayer));
	
	
}

void vider()
{
	txtNbrAbsence.setText("");
	txtreduction.setText("");
	txtTotalDelai.setText("");
	txtTotalCout.setText("");
	txtTotalCout25.setText("");
	txtTotalCout50.setText("");
	txtTotalDu.setText("");
	txtTotalPrime.setText("");
	
	
}



List<FicheEmploye> calculeCoutEmploye(){
	listFicheEmployeTmp.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	
	
	
	
	List<DetailProduction> listDetailProduction=detailProductionDAO.ListHeursDetailProduction(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText(),Constantes.ETAT_OF_TERMINER);
	List<DetailProdGen> listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGen(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText(),Constantes.ETAT_OF_TERMINER);
	List<DetailProdRes> listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProd(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
	List<DetailProductionMP> listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMP(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText(),Constantes.ETAT_OF_TERMINER);
	List<DetailEmployeMenage> listDetailEmployeMenage= detailEmployeMenageDAO.ListHeursDetailEmployeMenage(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
	List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent=coutHorsProdEnAttentDAO.ListHeursCoutHorsProdEnAttent (dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText(),Constantes.ETAT_OF_TERMINER);

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
			
			if(detailProduction.getCoutHoraire()!=null)
			{
				
			if(detailProduction.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
			{
				
				if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
				{
					coutHeurs=detailProduction.getCoutHoraire();
				}
				
			}
				
				
				
			}
		
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
				
				ficheEmploye.setCoutHoraire(detailProduction.getCoutHoraire());
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
						 
						  	if(detailProduction.getEmploye().isPrime()==true)
						   	{
						   		if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
									remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
								if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
									remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
						   	}else
						   	{
						   		remise=BigDecimal.ZERO;
						   	}
					   	 
			 
						
						
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 
					 ficheEmploye.setRemise(remise);
					 
				
					 ficheEmploye.setTypeResEmploye(detailProduction.getTypeResEmploye());
				 
				 
				listFicheEmployeTmp.set(j, ficheEmploye);
				
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
		FicheEmploye ficheEmployeTmp =new FicheEmploye();
		ficheEmployeTmp.setCoutHoraire(detailProduction.getCoutHoraire());
		ficheEmployeTmp.setNumOF(detailProduction.getProduction().getNumOF());
		ficheEmployeTmp.setDateSituation(detailProduction.getProduction().getDate());
		ficheEmployeTmp.setDelaiEmploye(delai);
		ficheEmployeTmp.setEmploye(detailProduction.getEmploye());;
		ficheEmployeTmp.setHeureSupp25(heureSupp25);
		ficheEmployeTmp.setHeureSupp50(heureSupp50);
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
		   			
		   		if(detailProduction.getEmploye().isPrime()==true)
		   		{
		   			if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else
		   		{
		   			remise=BigDecimal.ZERO;
		   		}
					
				
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 
			 
			 ficheEmployeTmp.setTypeResEmploye(detailProduction.getTypeResEmploye());
			 
			 ficheEmployeTmp.setRemise(remise);
			 ficheEmployeTmp.setDelaiProd(delaiProd);
			 listFicheEmployeTmp.add(ficheEmployeTmp);
			
		}
		}
		
		
	}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////// Cout Hors production En Attent /////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listCoutHorsProdEnAttent.size();i++){
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		BigDecimal delaiProd=BigDecimal.ZERO;
		CoutHorsProdEnAttent coutHorsProdEnAttent =listCoutHorsProdEnAttent.get(i);
		
		if(!coutHorsProdEnAttent.getEmploye().isSalarie()){
			
			delai=coutHorsProdEnAttent.getDelaiEmploye() ;
			heureSupp25=coutHorsProdEnAttent.getHeure25();
			heureSupp50=coutHorsProdEnAttent.getHeure50();
		
			if(coutHorsProdEnAttent.getCoutHoraire()!=null)
			{
				
			if(coutHorsProdEnAttent.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
			{
				
				if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
				{
					coutHeurs=coutHorsProdEnAttent.getCoutHoraire();
				}
				
			}
				
				
				
			}
	
		
		
		if(coutHorsProdEnAttent.getEtat().equals(ETAT_VALIDER)){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listFicheEmployeTmp.size();j++)
			{
				
				FicheEmploye ficheEmploye=listFicheEmployeTmp.get(j);
				
				if(coutHorsProdEnAttent.getEmploye().getId()==ficheEmploye.getEmploye().getId() &&  coutHorsProdEnAttent.getProduction().getDate().equals(ficheEmploye.getDateSituation()))
				{
					Trouve=true;
					
					

					/*Remplir fiche programme*/
					
					
					if(delai.compareTo(coutHorsProdEnAttent.getProduction().getNbreHeure())<0){
						
							delaiProd=ficheEmploye.getDelaiProd().add(delai);
					}else {
						delaiProd=ficheEmploye.getDelaiProd().add(delai);
					}
					
				//	coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
					delai=delai.add(ficheEmploye.getDelaiEmploye()) ;
					String numOF=ficheEmploye.getNumOF()+"-"+coutHorsProdEnAttent.getProduction().getNumOF();
					heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
					heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());
					
					
			/*	ficheEmploye.setDateSituation(production.getDate());
				
				ficheEmploye.setEmploye(detailProdGen.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setCoutSupp25(coutSupp25);
				ficheEmploye.setCoutSupp50(coutSupp50);*/
				
					ficheEmploye.setCoutHoraire(coutHorsProdEnAttent.getCoutHoraire());
				ficheEmploye.setNumOF(numOF);
			//	ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setDelaiProd(delaiProd);
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setDelaiEmploye(delai);
				 ficheEmploye.setHeurProduction(coutHorsProdEnAttent.getProduction().getNbreHeure().add(ficheEmploye.getHeurProduction()));

				 
				 if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){
			   			
			   	if(ficheEmploye.getEmploye().isPrime()==true)
			   	{
			   		if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			   	}else
			   	{
			   		remise=BigDecimal.ZERO;
			   	}
						
						
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 
					 ficheEmploye.setRemise(remise);
					 
				
				ficheEmploye.setTypeResEmploye(coutHorsProdEnAttent.getTypeResEmploye());
				 
				 
				listFicheEmployeTmp.set(j, ficheEmploye);
				
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
		FicheEmploye ficheEmployeTmp =new FicheEmploye();
			
		ficheEmployeTmp.setCoutHoraire(coutHorsProdEnAttent.getCoutHoraire());
		ficheEmployeTmp.setNumOF(coutHorsProdEnAttent.getProduction().getNumOF());
		ficheEmployeTmp.setDateSituation(coutHorsProdEnAttent.getProduction().getDate());
		ficheEmployeTmp.setDelaiEmploye(delai);
		ficheEmployeTmp.setEmploye(coutHorsProdEnAttent.getEmploye());
		ficheEmployeTmp.setHeureSupp25(heureSupp25);
		ficheEmployeTmp.setHeureSupp50(heureSupp50);
		ficheEmployeTmp.setHeurProduction(coutHorsProdEnAttent.getProduction().getNbreHeure());
			if(delai.compareTo(coutHorsProdEnAttent.getProduction().getNbreHeure())<0){
			
					delaiProd=delai;
			}else {
				delaiProd=delai;
			}
		
				ficheEmployeTmp.setSortie(false);	
			
			
			
				ficheEmployeTmp.setAbsent(false);	
		

				ficheEmployeTmp.setRetard(false);	
		
	
		   		if(ficheEmployeTmp.getEmploye().isPrime()==true)
		   		{
		   			if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   		}else
		   		{
		   			remise=BigDecimal.ZERO;
		   		}
					
					
		   			
		   	
			 
			 
			
			 
			 ficheEmployeTmp.setRemise(remise);
			 ficheEmployeTmp.setDelaiProd(delaiProd);
			 ficheEmployeTmp.setTypeResEmploye(coutHorsProdEnAttent.getTypeResEmploye());
			 listFicheEmployeTmp.add(ficheEmployeTmp);
			
		}
		}
		
		
	}
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////// Detail ProductionMP /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
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
			if(detailProductionMP.getCoutHoraire()!=null)
			{
				
			if(detailProductionMP.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
			{
				
				if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
				{
					coutHeurs=detailProductionMP.getCoutHoraire();
				}
				
			}
				
				
				
			}
		
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
					
				
					
					ficheEmploye.setCoutHoraire(detailProductionMP.getCoutHoraire());
					ficheEmploye.setHeureSupp25(heureSupp25);
					ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setNumOF(numOF);
				//ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setDelaiProd(delaiProd);
				ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setHeurProduction(detailProductionMP.getProductionMP().getNbreHeure().add(ficheEmploye.getHeurProduction()));
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
			   			
						if(ficheEmploye.getEmploye().isPrime()==true)
				   		{
							
							 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
								
								if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
									remise=parametre_remise_ouvrier.getValeur();
								if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
									remise=parametre_remise_ouvrier_vrac.getValeur();
							
				   		}else
				   		{
				   			remise=BigDecimal.ZERO;
				   			
				   			
				   		}
					 
			   		
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
			
				 
				
				 
				 
				 
				 
				 ficheEmploye.setRemise(remise);
				 
				 ficheEmploye.setTypeResEmploye(detailProductionMP.getTypeResEmploye());
					
				
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
			ficheEmploye.setCoutHoraire(detailProductionMP.getCoutHoraire());
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
				 if(ficheEmploye.getEmploye().isPrime()==true)
			   		{
					 
					 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
						
						if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=parametre_remise_ouvrier.getValeur();
						if(detailProductionMP.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=parametre_remise_ouvrier_vrac.getValeur();
			   		}else
			   		{
			   			remise=BigDecimal.ZERO;
			   		}
		   		
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 
		
			 
			 ficheEmploye.setTypeResEmploye(detailProductionMP.getTypeResEmploye());
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
			if(detailProdGen.getCoutHoraire()!=null)
			{
				
			if(detailProdGen.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
			{
				
				if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
				{
					coutHeurs=detailProdGen.getCoutHoraire();
				}
				
			}
				
				
				
			}
		
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
				ficheEmploye.setCoutHoraire(detailProdGen.getCoutHoraire());
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
			   			if(ficheEmploye.getEmploye().isPrime()==true)
			   			{
			   				if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			   				
			   			}else
			   			{
			   				remise=BigDecimal.ZERO;
			   			}
						
						
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				
				 
				 
				
					 ficheEmploye.setRemise(remise);
					 ficheEmploye.setTypeResEmploye(detailProdGen.getTypeResEmploye());
				
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
		ficheEmployeTmp.setCoutHoraire(detailProdGen.getCoutHoraire());
		ficheEmployeTmp.setHeureSupp25(heureSupp25);
		ficheEmployeTmp.setHeureSupp50(heureSupp50);
		ficheEmployeTmp.setHeurProduction(detailProdGen.getProductionGen().getNbreHeure());
		
		//	ficheEmploye.setCoutSupp25(coutSupp25);
		//	ficheEmploye.setCoutSupp50(coutSupp50);
			
	
			
			 if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){
		   			
				 if(ficheEmployeTmp.getEmploye().isPrime()==true)
		   			{
					 
					 if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			}else
		   			{
		   				
		   				remise=BigDecimal.ZERO;
		   			}
					
		   			
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
				
				ficheEmployeTmp.setTypeResEmploye(detailProdGen.getTypeResEmploye());
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
							
						//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
						//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
							
							if(detailResponsableProd.getCoutHoraire()!=null)
							{
								
							if(detailResponsableProd.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
							{
								
								if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
								{
									coutHeurs=detailResponsableProd.getCoutHoraire();
								}
								
							}
								
								
								
							}
							ficheEmployeTmp.setCoutHoraire(detailResponsableProd.getCoutHoraire());
							
							 if(detailResponsableProd.isAbsent()==false && detailResponsableProd.isSortie()==false && detailResponsableProd.isRetard()==false){
						   			
								 if(ficheEmployeTmp.getEmploye().isPrime()==true)
						   			{
									 
									 
									 if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
											remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
										if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
											remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
										
						   			}else
						   			{
						   				
						   				remise=BigDecimal.ZERO;
						   				
						   			}
									
						   			
						   		}else {
						   			remise=BigDecimal.ZERO;
						   		}
							 
							 
							
								if(detailResponsableProd.getRemise()!=null)
								{
									if(detailResponsableProd.getRemise().compareTo(remise)==0)
									{
										ficheEmployeTmp.setRemise(detailResponsableProd.getRemise());
										
									}else
									{
										ficheEmployeTmp.setRemise(remise);
									}
									
									
									
								}else
								{
									if(BigDecimal.ZERO.compareTo(remise)!=0)
									{
										ficheEmployeTmp.setRemise(remise);
									}else
									{
										ficheEmployeTmp.setRemise(BigDecimal.ZERO);
									}
									
									
								}
								
								
								
								if(ficheEmployeTmp.isSortie()==true || ficheEmployeTmp.isAbsent()==true || ficheEmployeTmp.isRetard()==true)
								{
									ficheEmployeTmp.setRemise(BigDecimal.ZERO);
								}
								
								
							 
							
							
							
							ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
							ficheEmployeTmp.setTypeResEmploye(detailResponsableProd.getTypeResEmploye());
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
							
						//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
						//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
							
							
						
								ficheEmployeTmp.setRemise(BigDecimal.ZERO);
								if(detailEmployeMenage.getCoutHoraire()!=null)
								{
									
								if(detailEmployeMenage.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
								{
									
									if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
									{
										coutHeurs=detailEmployeMenage.getCoutHoraire();
									}
									
								}
									
									
									
								}
								ficheEmployeTmp.setCoutHoraire(detailEmployeMenage.getCoutHoraire());
							
							ficheEmployeTmp.setEmploye(detailEmployeMenage.getEmploye());
							ficheEmployeTmp.setTypeResEmploye(detailEmployeMenage.getTypeResEmploye());
							listFicheEmployeTmp.add(ficheEmployeTmp);
								 
		}	 
	 
	 
	 
	 
	 
	
	return listFicheEmployeTmp;
	
}
}

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
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
import util.DateUtils;
import util.ExcelUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

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
import dao.daoImplManager.TypeResEmployeDAOImpl;
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
import dao.daoManager.TypeResEmployeDAO;
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
import dao.entity.FicheEmployeGlobale;
import dao.entity.Parametre;
import dao.entity.PrimeAnciennte;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;


public class AfficherFicheGlobaleEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgExcel;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< String, TypeResEmploye> mapTypeResEmploye = new HashMap<>();

	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
	private List<FicheEmployeGlobale> listFicheEmployeGlobale=new ArrayList<FicheEmployeGlobale>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private List<FicheEmploye> listFicheEmployeTmp=new ArrayList<FicheEmploye>();
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private DetailEmployeMenageDAO detailEmployeMenageDAO;
	private ParametreDAO parametreDAO;
	JComboBox comboBox = new JComboBox();
	private JComboBox comboResponsabilité;
	private TypeResEmployeDAO typeResEmployeDAO;
	private CoutHorsProdEnAttentDAO coutHorsProdEnAttentDAO;
	BigDecimal coutHeurs=BigDecimal.ZERO;
	private PrimeAnciennteDAO primeAnciennteDAO;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";
	private JTextField txtmatricule;
	public AfficherFicheGlobaleEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 609);
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
        	typeResEmployeDAO=new TypeResEmployeDAOImpl();
        	detailEmployeMenageDAO=new DetailEmployeMenageDAOImpl();
        	coutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	primeAnciennteDAO=new PrimeAnciennteDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgExcel= new ImageIcon(this.getClass().getResource("/img/excel.png"));
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
					  		     			"Nom Employer", " Horaire ","H Supp 25", "H Supp 50", "Total Cout Delai", "Total Cout Supp 25","Total Cout Supp 50" , "Remise","Nb Absence","Reduction", "Total A Payer","Net A Payer", "Reste"
					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false, false, false, false, false, false, false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 			"Nom Employer", " Horaire ","H Supp 25", "H Supp 50", "Total Cout Delai", "Total Cout Supp 25","Total Cout Supp 50" , "Remise","Nb Absence","Reduction", "Total A Payer","Net A Payer", "Reste"
					  		 			
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false,false, false, false, false, false, false, false, false,false,false,false
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1427, 461);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1427, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 12, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(237, 12, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1365, 14, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listFicheEmploye.clear();
				listFicheEmployeGlobale.clear();
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
		
			}/*else if(!verifierdate())
					{
				JOptionPane.showMessageDialog(null,message, "Erreur", JOptionPane.ERROR_MESSAGE);
					
						return;	
			
				}*/else {
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
						listFicheEmploye=calculeCoutEmploye();
					
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
		
		 
		dateDebutChooser.setBounds(76, 12, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(299, 13, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(735, 12, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(794, 11, 191, 26);
		layeredPane.add(comboDepot);
		
		JLabel lblResponsabilit = new JLabel("Responsabilit\u00E9 :");
		lblResponsabilit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResponsabilit.setBounds(1020, 13, 116, 26);
		layeredPane.add(lblResponsabilit);
		
		 comboResponsabilité = new JComboBox();
		comboResponsabilité.setBounds(1128, 12, 216, 26);
		layeredPane.add(comboResponsabilité);
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
	
		JButton btnImprimer = new JButton("Fiche Globale");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  DecimalFormat format = new DecimalFormat("#0.00");
				  String responsabilite="";
				BigDecimal somme=BigDecimal.ZERO;
				String sommetowords="";
				if(listFicheEmployeGlobale.size()!=0)
				{
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
		  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
					// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					// FicheEmploye ficheEmploye=listFicheEmployeGlobale.get(0);
					Map parameters = new HashMap();
					parameters.put("dateDu", dateDu);
					parameters.put("dateAu", dateAu);
					
					if(comboResponsabilité.getSelectedItem().equals(""))
					{
						
						responsabilite="Tous";
						
					}else if(comboResponsabilité.getSelectedItem().equals(Constantes.TYPE_EMPLOYE_MAIN_SALARIE))
					{
						responsabilite=Constantes.TYPE_EMPLOYE_MAIN_SALARIE;
					}else
					{	
						
						TypeResEmploye typeResEmployeTmp=mapTypeResEmploye.get(comboResponsabilité.getSelectedItem())	;
						if(typeResEmployeTmp!=null)
						{
							
							if(typeResEmployeTmp.getCode().equals(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							{
								
								TypeResEmploye typeResEmploye=mapTypeResEmploye.get(comboResponsabilité.getSelectedItem());
								responsabilite=typeResEmploye.getLibelle();
								
							}else if(typeResEmployeTmp.getCode().equals(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_MENAGE))
							{
								
								TypeResEmploye typeResEmploye=mapTypeResEmploye.get(comboResponsabilité.getSelectedItem());
								responsabilite=typeResEmploye.getLibelle();
								
							}
							
							
						}
						
			
					
					}
				
					for(int i=0;i<listFicheEmployeGlobale.size();i++)
					{
						
						somme= somme.add(listFicheEmployeGlobale.get(i).getNetapayer()) ; 
						
					}
					
					parameters.put("responsabilite", responsabilite);
					
					
					sommetowords=ConverterNumberToWords.converter(format.format(somme));
					parameters.put("somme",sommetowords);
					
					try {
						ExcelUtils.CreerExcelFicheGlobal(listFicheEmployeGlobale);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					JasperUtils.imprimerFicheEmployeGlobale(listFicheEmployeGlobale,parameters);
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La list des employés est vide !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				}
					
				
	  		  	
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(432, 574, 136, 24);
		add(btnImprimer);
		
		JButton btnImprimerBulletinPaie = new JButton("Bulletin Paie");
		btnImprimerBulletinPaie.setIcon(imgImprimer);
		btnImprimerBulletinPaie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnImprimerBulletinPaie.setBounds(575, 574, 114, 24);
		add(btnImprimerBulletinPaie);
		
		
		comboResponsabilité.addItem("");
		
		comboResponsabilité.addItem(Constantes.TYPE_EMPLOYE_MAIN_SALARIE);
		
		TypeResEmploye typeResEmployeOuvrier =typeResEmployeDAO.findByCode(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION);
		comboResponsabilité.addItem(typeResEmployeOuvrier.getLibelle());
		mapTypeResEmploye.put(typeResEmployeOuvrier.getLibelle(), typeResEmployeOuvrier);
		
		TypeResEmploye typeResEmployeMenage =typeResEmployeDAO.findByCode(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_MENAGE);
		comboResponsabilité.addItem(typeResEmployeMenage.getLibelle());
		
		JLabel label_1 = new JLabel("Matricule:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(477, 12, 102, 24);
		layeredPane.add(label_1);
		
		txtmatricule = new JTextField();
		txtmatricule.setColumns(10);
		txtmatricule.setBounds(537, 13, 152, 22);
		layeredPane.add(txtmatricule);
		
		JButton btnExporterExcel = new JButton("Exporter Excel");
		btnExporterExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getRowCount()!=0)
    			{
    				
    				String titre="Fiche Globale Des Versements ";
		    		String titrefeuilleexcel="Fiche Globale Des Versements ";
		    		ExporterTableVersExcel.tabletoexcelFicheGlobaleDesVersements (table, titre,titrefeuilleexcel);
    				
    				
    			}else
    			{
    				
    				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
    				
    				
    			}
				
			}
		});
		btnExporterExcel.setBounds(699, 575, 136, 24);
		btnExporterExcel.setIcon(imgExcel);
		add(btnExporterExcel);
		mapTypeResEmploye.put(typeResEmployeMenage.getLibelle(), typeResEmployeMenage);
			  		 
	}
	
void afficher_tableMP(List<FicheEmploye> listFicheEmploye )
	{
	
	
	
		intialiserTableau();
		totalHoraire=BigDecimal.ZERO;
		  int i=0;
		  int j=0;
		  int k=0;
		    BigDecimal totalAvance=BigDecimal.ZERO;
			BigDecimal totalPrime=BigDecimal.ZERO;
			BigDecimal totalCout=BigDecimal.ZERO;
			BigDecimal totalDu=BigDecimal.ZERO;
			BigDecimal totalCout25=BigDecimal.ZERO;
			BigDecimal totalcout50=BigDecimal.ZERO;
			BigDecimal supp25=BigDecimal.ZERO;
			BigDecimal supp50=BigDecimal.ZERO;
			BigDecimal netapayer=BigDecimal.ZERO;
			boolean trouve=false;
			
			BigDecimal reduction=BigDecimal.ZERO;
			int compteurbsenseEmployernbr=0;
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  DecimalFormat format = new DecimalFormat("#0.00");
			String date="";
			BigDecimal avance=BigDecimal.ZERO;
			NumberFormat nf = new DecimalFormat("0.###");
			
			ParametreDAO parametreDAO= new ParametreDAOImpl();
			 
			BigDecimal coutHoraire= coutHeurs;
			BigDecimal coutHeureSupp25=mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25);
			BigDecimal coutHeureSupp50=mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50);
			BigDecimal coutTotal=BigDecimal.ZERO;
			BigDecimal coutTotal25=BigDecimal.ZERO;
			BigDecimal coutTotal50=BigDecimal.ZERO;
			while(i<listFicheEmploye.size())
			{	
				
				trouve=false;
				k=0;
				  totalAvance=BigDecimal.ZERO;totalPrime=BigDecimal.ZERO; totalCout=BigDecimal.ZERO; totalCout25=BigDecimal.ZERO;totalcout50=BigDecimal.ZERO;
					int sizelist=listFicheEmployeGlobale.size();
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				FicheEmploye fiche = listFicheEmploye.get(i);
				
			
				coutTotal=fiche.getDelaiEmploye().multiply(fiche.getCoutHoraire());
				
				 coutTotal25=fiche.getHeureSupp25().multiply(coutHeureSupp25);
				 coutTotal50=fiche.getHeureSupp50().multiply(coutHeureSupp50);
				 
				 coutTotal=coutTotal.add(coutTotal25).add(coutTotal50);
				
			  
				
				 
				if(sizelist==0)
				{
					FicheEmployeGlobale ficheglobale=new FicheEmployeGlobale();
				 
					
					ficheglobale.setCoutSupp25(coutTotal25);
					ficheglobale.setCoutSupp50(coutTotal50);
					ficheglobale.setHeureSupp25(fiche.getHeureSupp25());
					ficheglobale.setHeureSupp50(fiche.getHeureSupp50());
					ficheglobale.setCoutTotal(coutTotal);
					ficheglobale.setEmploye(fiche.getEmploye());
					ficheglobale.setDelaiEmploye(fiche.getDelaiEmploye());
					ficheglobale.setDateSituation(fiche.getDateSituation());
					ficheglobale.setRemise(fiche.getRemise());
					
					if(fiche.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0)
						ficheglobale.setCompteur(1);
					
					listFicheEmployeGlobale.add(ficheglobale);
				 	
					
					
				}
				else{
					
					
					
				while(k<sizelist)
					{
				FicheEmployeGlobale FicheEmployeGlobale=listFicheEmployeGlobale.get(k);
					
						if (fiche.getEmploye().getId()==FicheEmployeGlobale.getEmploye().getId())
						{
							
							if(fiche.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 || fiche.isSortie()==true || fiche.isRetard()==true ){
								int compteur1=FicheEmployeGlobale.getCompteur()+1;
								FicheEmployeGlobale.setCompteur(compteur1);
							}
							
							 coutTotal=fiche.getDelaiEmploye().multiply(fiche.getCoutHoraire());
								 
								
								 coutTotal25=fiche.getHeureSupp25().multiply(coutHeureSupp25);
								 coutTotal50=fiche.getHeureSupp50().multiply(coutHeureSupp50);
								 
								 coutTotal=coutTotal.add(coutTotal25).add(coutTotal50);
	 
							 
							 
							
							 
								totalPrime=fiche.getRemise().add(FicheEmployeGlobale.getRemise());
								totalCout=coutTotal.add(FicheEmployeGlobale.getCoutTotal());
								totalHoraire=fiche.getDelaiEmploye().add(FicheEmployeGlobale.getDelaiEmploye());
								totalCout25=coutTotal25.add(FicheEmployeGlobale.getCoutSupp25());
								totalcout50=coutTotal50.add(FicheEmployeGlobale.getCoutSupp50());
								supp25=fiche.getHeureSupp25().add(FicheEmployeGlobale.getHeureSupp25());
								supp50=fiche.getHeureSupp50().add(FicheEmployeGlobale.getHeureSupp50());
								FicheEmployeGlobale.setAvance(totalAvance);								
								FicheEmployeGlobale.setRemise(totalPrime);
								FicheEmployeGlobale.setCoutTotal(totalCout);
								FicheEmployeGlobale.setDelaiEmploye(totalHoraire);
								FicheEmployeGlobale.setCoutSupp25(totalCout25);
								FicheEmployeGlobale.setCoutSupp50(totalcout50);
								FicheEmployeGlobale.setHeureSupp25(supp25);
								FicheEmployeGlobale.setHeureSupp50(supp50);
								
							listFicheEmployeGlobale.set(k, FicheEmployeGlobale);
							
						 
							
						trouve=true;
							
						}
						k++;
					}
				if(trouve==false)
				{
					// coutTotal=fiche.getDelaiEmploye().multiply(valeur);
					FicheEmployeGlobale ficheglobale=new FicheEmployeGlobale();
					
					 
					
						if(fiche.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 || fiche.isSortie()==true || fiche.isRetard()==true ){
							int compteur1=ficheglobale.getCompteur()+1;
							ficheglobale.setCompteur(compteur1);
						}
				//	ficheglobale.setAvance(fiche.getAvance());
					ficheglobale.setCoutSupp25(coutTotal25);
					ficheglobale.setCoutSupp50(coutTotal50);
					ficheglobale.setHeureSupp25(fiche.getHeureSupp25());
					ficheglobale.setHeureSupp50(fiche.getHeureSupp50());
					ficheglobale.setCoutTotal(coutTotal);
					ficheglobale.setEmploye(fiche.getEmploye());
					ficheglobale.setDelaiEmploye(fiche.getDelaiEmploye());
					ficheglobale.setDateSituation(fiche.getDateSituation());
					ficheglobale.setRemise(fiche.getRemise());
					listFicheEmployeGlobale.add(ficheglobale);
				  
				}
					
				}
				
				
				/* date=dateFormat.format(ficheEmploye.getDateSituation());
				/*date=dateFormat.format(ficheEmploye[1]);
				String delai1=nf.format(ficheEmploye[2]);
				String cout1=nf.format(ficheEmploye[3]);
				String remise1=nf.format(ficheEmploye[4]);
				String avance1=nf.format(ficheEmploye[5]);*/
					//avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
				/*	totalAvance=totalAvance+ficheEmploye.getAvance();
					totalPrime=totalPrime+ficheEmploye.getRemise();
					totalCout=totalCout+ficheEmploye.getCoutTotal();
					totalHoraire=totalHoraire+ficheEmploye.getDelaiEmploye();
					totalCout25=totalCout25+ficheEmploye.getCoutSupp25();
					totalcout50=totalcout50+ficheEmploye.getCoutSupp50();
				Object []ligne={ficheEmploye.getId(),date,ficheEmploye.getEmploye().getNom(),ficheEmploye.getDelaiEmploye(),ficheEmploye.getCoutTotal(),ficheEmploye.getRemise(),ficheEmploye.getCoutSupp25(),ficheEmploye.getCoutSupp50(),ficheEmploye.getAvance(),ficheEmploye.getHeureSupp25(),ficheEmploye.getHeureSupp50()};

				modeleMP.addRow( ligne);*/
				
				
		 
				
				i++;
				
				
				
				
				
				
			}
			
			
			
			
			
			Parametre parametreNbrHeuresTotal=parametreDAO.findByLibelle(Constantes.NBR_HEURES_TOTAL);
		 
			 String Nomprenom="";
			
			while(j<listFicheEmployeGlobale.size())
			{	
				 
				
				
				
				Nomprenom="";
				
				FicheEmployeGlobale ficheEmployeglobel=listFicheEmployeGlobale.get(j);
				if(ficheEmployeglobel.getEmploye().getMatricule().toString().trim().equals("7"))
				{
					System.out.println("yes 7");
				}
				
				totalDu= ficheEmployeglobel.getCoutTotal().add(ficheEmployeglobel.getCoutSupp25().add(ficheEmployeglobel.getCoutSupp50()));
				 
				 
					BigDecimal TotalprimeanciennteTmp=BigDecimal.ZERO;
					 if(ficheEmployeglobel.getEmploye()!=null)
						{
							  
			  					 LocalDate from = LocalDate.of(DateUtils.getAnnee(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getMois(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getJour(ficheEmployeglobel.getEmploye().getDatePremiereProduction()));
			  			        LocalDate to = LocalDate.of(DateUtils.getAnnee(dateFinChooser.getDate()), DateUtils.getMois(dateFinChooser.getDate()), DateUtils.getJour(dateFinChooser.getDate()));

			  			        Period period = Period.between(from, to);
/*
			  			        System.out.println(period.getYears() + " years,");
			  			        System.out.println(period.getMonths() + " months,");
			  			        System.out.println(period.getDays() + " days");
			  			         System.out.println(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ));
			  			         */
			  			     PrimeAnciennte primeAnciennte=  primeAnciennteDAO.PrimeByMinByMaxByDatePrim(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ), dateFinChooser.getDate());	 
		if(primeAnciennte!=null)
		{
			
			TotalprimeanciennteTmp=(primeAnciennte.getTaux().divide(new BigDecimal(100) , 6, RoundingMode.HALF_UP)).multiply(totalDu);
			
			
			
		}
			  			   
					 
						} 
				 
					 totalDu=totalDu.add(TotalprimeanciennteTmp);
				
					 BigDecimal retenu226Tmp=totalDu.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448Tmp=totalDu.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenuTmp=retenu448Tmp.add(retenu226Tmp);
					 totalDu=totalDu.subtract(totalRetenuTmp);
				
				String dateabsent=Utils.genereCodeDateMoisAnnee(dateDebutChooser.getDate());
				date=dateFormat.format(ficheEmployeglobel.getDateSituation());
				BigDecimal primeAPayer=BigDecimal.ZERO;
			/*	CompteurAbsenceEmploye	compteurbsenseEmployer=compteurabsenceemployedao.findByDateAbsencePeriode(dateabsent, listFicheEmployeGlobale.get(j).getEmploye().getId());
				if(compteurbsenseEmployer==null)
				{
					compteurbsenseEmployernbr=0;
				}else
				{
					compteurbsenseEmployernbr=compteurbsenseEmployer.getCompteur();
				}*/
				
				 
				compteurbsenseEmployernbr=ficheEmployeglobel.getCompteur();
				 
				BigDecimal compteurbsenseEmployernbrtmp=new BigDecimal(compteurbsenseEmployernbr);
				BigDecimal val=new BigDecimal(0.25);
				
				
				if(compteurbsenseEmployernbrtmp.compareTo(new BigDecimal(4))<1)
					reduction=ficheEmployeglobel.getRemise().multiply(compteurbsenseEmployernbrtmp).multiply(val) ;
				else 
					reduction=ficheEmployeglobel.getRemise();
				
				if(compteurbsenseEmployernbr>=4)
				{
					primeAPayer=BigDecimal.ZERO;
					//netapayer=totalDu.subtract(ficheEmployeglobel.getRemise());
					
				}else if(compteurbsenseEmployernbr<4 && compteurbsenseEmployernbr>0)
				{
					
					primeAPayer=ficheEmployeglobel.getRemise().subtract(reduction);
				}
				else if(compteurbsenseEmployernbr==0)
				{
					primeAPayer=ficheEmployeglobel.getRemise();
				}
				
				double resteDivision=primeAPayer.doubleValue()%50;
				
				primeAPayer=primeAPayer.subtract(new BigDecimal(resteDivision));
				
				if(netapayer.compareTo(BigDecimal.ZERO)<0)
				{
					netapayer=BigDecimal.ZERO;
				}
				
				netapayer=totalDu.add(primeAPayer);
				
				if(netapayer.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)>0   &&  netapayer.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) <= 0 )
				{
					
					netapayer=netapayer.subtract(netapayer.remainder(BigDecimal.ONE)).add(new BigDecimal(0.50));
					
					
				}else if(netapayer.remainder(BigDecimal.ONE).compareTo(new BigDecimal(0.50)) > 0)
				{
					
					netapayer=netapayer.subtract(netapayer.remainder(BigDecimal.ONE)).add(BigDecimal.ONE);

					
				}
				
				
			
			//	ficheEmployeglobel.setCompteur(compteurbsenseEmployernbr);
				ficheEmployeglobel.setReduction(reduction);
				ficheEmployeglobel.setRemiseApayer(primeAPayer);
				ficheEmployeglobel.setNetapayer(netapayer);
				BigDecimal PrimeTotal=BigDecimal.ZERO;
				BigDecimal PrimePanier=BigDecimal.ZERO;
				BigDecimal PrimeTransport=BigDecimal.ZERO;
				 BigDecimal netApayerSupplementaire=BigDecimal.ZERO;
				 BigDecimal Primesupplementaire=BigDecimal.ZERO;
				BigDecimal prime=BigDecimal.ZERO;
				Parametre parametrePrimePlafonne=parametreDAO.findByLibelle(Constantes.PRIME_PLAFONNE);
				if(ficheEmployeglobel.getDelaiEmploye().compareTo(parametreNbrHeuresTotal.getValeur())>0)
				{/*
					
					
					Parametre parametreCoutPrimePanier=parametreDAO.findByLibelle(Constantes.COUT_PRIME_PANIER);
					Parametre parametreCoutPrimetransport=parametreDAO.findByLibelle(Constantes.COUT_PRIME_TRANSPORT);
					BigDecimal couttotalreste=parametreNbrHeuresTotal.getValeur().multiply(parametreCoutHeureCNSS.getValeur());
					BigDecimal retenu226=couttotalreste.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448=couttotalreste.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenu=retenu448.add(retenu226);
					 BigDecimal netApayer=couttotalreste.subtract(totalRetenu);
					BigDecimal CoutPrimePanier=parametreNbrHeuresTotal.getValeur().divide(new BigDecimal(8), 2, RoundingMode.HALF_UP).multiply(parametreCoutPrimePanier.getValeur());
					BigDecimal CoutPrimetransport=parametreNbrHeuresTotal.getValeur().divide(new BigDecimal(8), 2, RoundingMode.HALF_UP).multiply(parametreCoutPrimetransport.getValeur());

					if(ficheEmployeglobel.getRemiseApayer().compareTo(BigDecimal.ZERO)!=0)
{
	if((CoutPrimePanier.add(CoutPrimetransport)).compareTo(ficheEmployeglobel.getRemiseApayer()) <0)
	{
prime=(CoutPrimePanier.add(CoutPrimetransport));

	}else
	{
		prime=ficheEmployeglobel.getRemiseApayer();
	}
	
}
		*/
					
				
					
					 BigDecimal salaireBrutSupplementaire= coutHeurs.multiply(ficheEmployeglobel.getDelaiEmploye());
					 BigDecimal retenu226Supplementaire=salaireBrutSupplementaire.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448Supplementaire=salaireBrutSupplementaire.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenuSupplementaire=retenu448Supplementaire.add(retenu226Supplementaire);					 
					 netApayerSupplementaire=salaireBrutSupplementaire.subtract(totalRetenuSupplementaire);

					 BigDecimal salaireBrutTmp= coutHeurs.multiply(parametreNbrHeuresTotal.getValeur());
					 
					 coutTotal25=ficheEmployeglobel.getHeureSupp25().multiply(coutHeureSupp25);
					 coutTotal50=ficheEmployeglobel.getHeureSupp50().multiply(coutHeureSupp50);
					 salaireBrutTmp=salaireBrutTmp.add(coutTotal25).add(coutTotal50);
					 ficheEmployeglobel.setCoutTotal(salaireBrutTmp);
						BigDecimal Totalprimeanciennte=BigDecimal.ZERO;
						 if(ficheEmployeglobel.getEmploye()!=null)
							{
								  
				  					 LocalDate from = LocalDate.of(DateUtils.getAnnee(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getMois(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getJour(ficheEmployeglobel.getEmploye().getDatePremiereProduction()));
				  			        LocalDate to = LocalDate.of(DateUtils.getAnnee(dateFinChooser.getDate()), DateUtils.getMois(dateFinChooser.getDate()), DateUtils.getJour(dateFinChooser.getDate()));

				  			        Period period = Period.between(from, to);
/*
				  			        System.out.println(period.getYears() + " years,");
				  			        System.out.println(period.getMonths() + " months,");
				  			        System.out.println(period.getDays() + " days");
				  			         System.out.println(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ));
				  			         */
				  			     PrimeAnciennte primeAnciennte=  primeAnciennteDAO.PrimeByMinByMaxByDatePrim(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ), dateFinChooser.getDate());	 
			if(primeAnciennte!=null)
			{
				Totalprimeanciennte=(primeAnciennte.getTaux().divide(new BigDecimal(100) , 6, RoundingMode.HALF_UP)).multiply(salaireBrutTmp);
				
				
				
			}
				  			   
						 
							} 
						 
					 
						 salaireBrutTmp=salaireBrutTmp.add(Totalprimeanciennte);
						
						 ficheEmployeglobel.setCoutTotalBrut(salaireBrutTmp);
					 
					 BigDecimal retenu226=salaireBrutTmp.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448=salaireBrutTmp.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenu=retenu448.add(retenu226);
					 BigDecimal netApayer=BigDecimal.ZERO;  
					 
					 ficheEmployeglobel.setCnss(totalRetenu);
					 				 					 
					 ficheEmployeglobel.setCoutTotalNet(salaireBrutTmp.subtract(totalRetenu));
					 
					 if(netApayerSupplementaire.compareTo(BigDecimal.ZERO)!=0)
					 {
						
						 Primesupplementaire=netApayerSupplementaire.subtract(salaireBrutTmp.subtract(totalRetenu));
						 
					 }
					 if(ficheEmployeglobel.getRemiseApayer().compareTo(BigDecimal.ZERO)!=0)
						{
						 
						 PrimeTotal=ficheEmployeglobel.getRemiseApayer().add(Primesupplementaire);
						 
						}
					 
					 if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())>0)
					 {
						 PrimeTotal= parametrePrimePlafonne.getValeur();
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
						
					
						netApayer= salaireBrutTmp.subtract(totalRetenu);
					 
						if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())<=0)
						{
						
							
							netApayer= salaireBrutTmp.subtract(totalRetenu).add(PrimePanier).add(PrimeTransport);
							
							
						}else {
							

							
							netApayer= salaireBrutTmp.subtract(totalRetenu).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP)).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP));

							
						}
					 
					
					ficheEmployeglobel.setNetapayerbulletin(netApayer);
					
					ficheEmployeglobel.setTotalReste(ficheEmployeglobel.getNetapayer().subtract(ficheEmployeglobel.getNetapayerbulletin()));
					
					
					
				}else
				{
					/*
					
					Parametre parametreCoutPrimePanier=parametreDAO.findByLibelle(Constantes.COUT_PRIME_PANIER);
					Parametre parametreCoutPrimetransport=parametreDAO.findByLibelle(Constantes.COUT_PRIME_TRANSPORT);
					BigDecimal CoutPrimePanier=(ficheEmployeglobel.getDelaiEmploye()).divide(new BigDecimal(8), 2, RoundingMode.HALF_UP).multiply(parametreCoutPrimePanier.getValeur());
					BigDecimal CoutPrimetransport=(ficheEmployeglobel.getDelaiEmploye()).divide(new BigDecimal(8), 2, RoundingMode.HALF_UP).multiply(parametreCoutPrimetransport.getValeur());
					
					BigDecimal couttotalreste=ficheEmployeglobel.getDelaiEmploye().multiply(parametreCoutHeureCNSS.getValeur());
					BigDecimal retenu226=couttotalreste.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448=couttotalreste.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenu=retenu448.add(retenu226);
					 BigDecimal netApayer=couttotalreste.subtract(totalRetenu);
					
					
					
					if(ficheEmployeglobel.getRemiseApayer().compareTo(BigDecimal.ZERO)!=0)
					{
						if((CoutPrimePanier.add(CoutPrimetransport)).compareTo(ficheEmployeglobel.getRemiseApayer()) <0)
						{
					prime=(CoutPrimePanier.add(CoutPrimetransport));

						}else
						{
							prime=ficheEmployeglobel.getRemiseApayer();
						}
						
					}
					
					
                     ficheEmployeglobel.setNetapayerbulletin(netApayer.add(prime));
					
					ficheEmployeglobel.setTotalReste(ficheEmployeglobel.getNetapayer().subtract(ficheEmployeglobel.getNetapayerbulletin()));
					
					*/
					
					 BigDecimal salaireBrut= ficheEmployeglobel.getCoutTotal().add(ficheEmployeglobel.getCoutSupp25().add(ficheEmployeglobel.getCoutSupp50()));
					 
					 ficheEmployeglobel.setCoutTotal(salaireBrut);
						BigDecimal Totalprimeanciennte=BigDecimal.ZERO;
						 if(ficheEmployeglobel.getEmploye()!=null)
							{
								  
				  					 LocalDate from = LocalDate.of(DateUtils.getAnnee(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getMois(ficheEmployeglobel.getEmploye().getDatePremiereProduction()), DateUtils.getJour(ficheEmployeglobel.getEmploye().getDatePremiereProduction()));
				  			        LocalDate to = LocalDate.of(DateUtils.getAnnee(dateFinChooser.getDate()), DateUtils.getMois(dateFinChooser.getDate()), DateUtils.getJour(dateFinChooser.getDate()));

				  			        Period period = Period.between(from, to);
/*
				  			        System.out.println(period.getYears() + " years,");
				  			        System.out.println(period.getMonths() + " months,");
				  			        System.out.println(period.getDays() + " days");
				  			         System.out.println(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ));
				  			         */
				  			        
				  			        
				  			     PrimeAnciennte primeAnciennte=  primeAnciennteDAO.PrimeByMinByMaxByDatePrim(new BigDecimal(String.valueOf(period.getYears()+"."+ period.getDays()) ), dateFinChooser.getDate());	 
			if(primeAnciennte!=null)
			{
				Totalprimeanciennte=(primeAnciennte.getTaux().divide(new BigDecimal(100) , 6, RoundingMode.HALF_UP)).multiply(salaireBrut);
				
				
				
			}
				  			   
						 
							} 
						 
						 
						 ficheEmployeglobel.setAnciennete(Totalprimeanciennte);
						 
					 
						 salaireBrut=ficheEmployeglobel.getCoutTotal().add(Totalprimeanciennte);
						 
						 ficheEmployeglobel.setCoutTotalBrut(salaireBrut);
						 
					 
					 BigDecimal retenu226=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal retenu448=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
					 BigDecimal totalRetenu=retenu448.add(retenu226);
					 BigDecimal netApayer=BigDecimal.ZERO;  
					 
					 ficheEmployeglobel.setCnss(totalRetenu);
					 ficheEmployeglobel.setCoutTotalNet(salaireBrut.subtract(totalRetenu));
					 
					  
					 
					 if(netApayerSupplementaire.compareTo(BigDecimal.ZERO)!=0)
					 {
						
						 Primesupplementaire=netApayerSupplementaire.subtract(salaireBrut.subtract(totalRetenu));
						 
					 }
					 if(ficheEmployeglobel.getRemiseApayer().compareTo(BigDecimal.ZERO)!=0)
						{
						 
						 PrimeTotal=ficheEmployeglobel.getRemiseApayer().add(Primesupplementaire);
						 
						}
					 
					 if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())>0)
					 {
						 PrimeTotal= parametrePrimePlafonne.getValeur();
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
					 
						if(PrimeTotal.compareTo(parametrePrimePlafonne.getValeur())<=0)
						{
						
							
							netApayer= salaireBrut.subtract(totalRetenu).add(PrimePanier).add(PrimeTransport);
							
							
						}else {
							

							
							netApayer= salaireBrut.subtract(totalRetenu).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP)).add(parametrePrimePlafonne.getValeur().divide(new BigDecimal(2), 2, RoundingMode.HALF_UP));

							
						}
					 
					
					ficheEmployeglobel.setNetapayerbulletin(netApayer);
					
					ficheEmployeglobel.setTotalReste(ficheEmployeglobel.getNetapayer().subtract(ficheEmployeglobel.getNetapayerbulletin()));
					
					
					
					
					
				}
				
				
				
				if(ficheEmployeglobel.getEmploye().getPrenom()!=null)
				{
					Nomprenom=ficheEmployeglobel.getEmploye().getNom() +" "+ficheEmployeglobel.getEmploye().getPrenom();
				}else
				{
					Nomprenom=ficheEmployeglobel.getEmploye().getNom();
				}
				
				if(ficheEmployeglobel.getAnciennete()==null)
				{
					ficheEmployeglobel.setAnciennete(BigDecimal.ZERO);
				}
				
				 
				
				
				
				listFicheEmployeGlobale.set(j, ficheEmployeglobel);
			
				Object []ligne={ficheEmployeglobel.getEmploye().getMatricule(),Nomprenom,format.format(ficheEmployeglobel.getDelaiEmploye()),ficheEmployeglobel.getHeureSupp25(),ficheEmployeglobel.getHeureSupp50(),format.format(ficheEmployeglobel.getCoutSupp25()),format.format(ficheEmployeglobel.getCoutSupp50()),format.format(ficheEmployeglobel.getCoutTotal()),format.format(ficheEmployeglobel.getAnciennete()),format.format(ficheEmployeglobel.getCoutTotalBrut()),format.format(ficheEmployeglobel.getCnss()),format.format(ficheEmployeglobel.getCoutTotalNet()),format.format(ficheEmployeglobel.getRemise()),ficheEmployeglobel.getCompteur(),format.format(ficheEmployeglobel.getReduction()),format.format(ficheEmployeglobel.getRemiseApayer()),format.format(ficheEmployeglobel.getNetapayer()),format.format(ficheEmployeglobel.getNetapayerbulletin()),format.format(ficheEmployeglobel.getTotalReste())};

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
		     			"Matricule","Nom Employer", "Heures Normale","H Supp 25", "H Supp 50","C Supp 25", "C Supp 50", "Cout Total","Prime Anciennete", "Cout Total Brut","CNSS" ,"Cout Total Net" , "Remise","Nb Absence","Reduction","Remise A Payer", "Total A Payer","Net A Payer", "Reste"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false, false, false, false, false, false, false,false,false,false,false,false,false,false,false,false,false
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


List<FicheEmploye> calculeCoutEmploye(){
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	
	listFicheEmployeTmp.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	
	String requete ="";
	
	if(!comboResponsabilité.getSelectedItem().equals(""))
	{
		
		if(comboResponsabilité.getSelectedItem().equals(Constantes.TYPE_EMPLOYE_MAIN_SALARIE))
		{
			
			
			
			requete=" and c.employe.salarie='"+1+"'";
			
			
			
			
			
		}else if(!comboResponsabilité.getSelectedItem().equals(Constantes.TYPE_EMPLOYE_MAIN_SALARIE))
		{
			
		TypeResEmploye typeResEmploye=mapTypeResEmploye.get(comboResponsabilité.getSelectedItem())	;
		if(typeResEmploye!=null)
		{
			
			if(typeResEmploye.getCode().equals(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION)) ////// Ouvrier
			{
				
				
				
				TypeResEmploye typeResEmployeOuvrierEnVrac=typeResEmployeDAO.findByCode(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC);
				
				requete=" and c.employe.salarie='"+0+"' and (c.typeResEmploye.id='"+typeResEmploye.getId()+"' or c.typeResEmploye.id='"+typeResEmployeOuvrierEnVrac.getId()+"')";
				
				
			}else if(typeResEmploye.getCode().equals(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_MENAGE)) ////////// Menage
			{
				
				requete=" and c.employe.salarie='"+0+"' and c.typeResEmploye.id='"+typeResEmploye.getId()+"'";
				
			}
			
			
			
		}
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	if(!txtmatricule.getText().equals(""))
	{
		requete=" and c.employe.matricule='"+txtmatricule.getText()+"'";
	}
	
	List<DetailProduction> listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requete);
	List<DetailProdGen> listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requete);
	List<DetailProdRes> listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),requete);
	List<DetailProductionMP> listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requete);
	List<DetailEmployeMenage> listDetailEmployeMenage=detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),requete);
	List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent=coutHorsProdEnAttentDAO.ListHeursCoutHorsProdEnAttentParDepot (dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,requete);

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
					heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
					heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());
					String numOF=ficheEmploye.getNumOF()+"-"+detailProduction.getProduction().getNumOF();
					ficheEmploye.setHeureSupp25(heureSupp25);
					ficheEmploye.setHeureSupp50(heureSupp50);
					
					
					 if(detailProduction.getCoutHoraire()!=null)
						{
							
 
								 
							ficheEmploye.setCoutHoraire(detailProduction.getCoutHoraire());
				 
							
						}
					
					
					
			/*	ficheEmploye.setDateSituation(production.getDate());
				
				ficheEmploye.setEmploye(detailProdGen.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setCoutSupp25(coutSupp25);
				ficheEmploye.setCoutSupp50(coutSupp50);*/
				
				ficheEmploye.setNumOF(numOF);
			//	ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setDelaiProd(delaiProd);
				
				ficheEmploye.setDelaiEmploye(delai);
				if(detailProduction.isSortie()==true )
				{
					ficheEmploye.setSortie(true);	
					
				}
				
				if(detailProduction.isAbsent()==true )
				{
					ficheEmploye.setAbsent(true);	
					
				}
				
				if(detailProduction.isRetard()==true )
				{
					ficheEmploye.setRetard(true);	
					
				}
				
				
				ficheEmploye.setHeurProduction(ficheEmploye.getHeurProduction().add(detailProduction.getProduction().getNbreHeure()));
				
				 if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){
			   			
					 if(detailProduction.getEmploye().isPrime()==true)
					   	{
						 
							if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
				   			
				   		}else {
				   			remise=BigDecimal.ZERO;
				   		}
						 
						 
					   	}else
					   	{
					   		remise=BigDecimal.ZERO;
					   	}
						
					
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setTypeResEmploye(detailProduction.getTypeResEmploye());
				listFicheEmployeTmp.set(j, ficheEmploye);
				
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
		FicheEmploye ficheEmployeTmp =new FicheEmploye();
			
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
			 
			 
			 if(detailProduction.getCoutHoraire()!=null)
				{
					
			 
					
					 
						 
						ficheEmployeTmp.setCoutHoraire(detailProduction.getCoutHoraire());
		 
					
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
					
				
					if(coutHorsProdEnAttent.getCoutHoraire()!=null)
					{
						
					 
						
						 
							 
						ficheEmploye.setCoutHoraire(coutHorsProdEnAttent.getCoutHoraire());
						 
				 
						
					}
				
					
					
					delai=delai.add(ficheEmploye.getDelaiEmploye()) ;
					heureSupp25=heureSupp25.add(ficheEmploye.getHeureSupp25());
					heureSupp50=heureSupp50.add(ficheEmploye.getHeureSupp50());
					String numOF=ficheEmploye.getNumOF()+"-"+coutHorsProdEnAttent.getProduction().getNumOF();
					ficheEmploye.setHeureSupp25(heureSupp25);
					ficheEmploye.setHeureSupp50(heureSupp50);
					
					
			/*	ficheEmploye.setDateSituation(production.getDate());
				
				ficheEmploye.setEmploye(detailProdGen.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setCoutSupp25(coutSupp25);
				ficheEmploye.setCoutSupp50(coutSupp50);*/
				
				ficheEmploye.setNumOF(numOF);
			//	ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setDelaiProd(delaiProd);
				
				ficheEmploye.setDelaiEmploye(delai);
			
				
			
				
			
				
				
				ficheEmploye.setHeurProduction(ficheEmploye.getHeurProduction().add(coutHorsProdEnAttent.getProduction().getNbreHeure()));
				
				 if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){
			   			
					 if(coutHorsProdEnAttent.getEmploye().isPrime()==true)
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
			
		ficheEmployeTmp.setNumOF(coutHorsProdEnAttent.getProduction().getNumOF());
		ficheEmployeTmp.setDateSituation(coutHorsProdEnAttent.getProduction().getDate());
		ficheEmployeTmp.setDelaiEmploye(delai);
		ficheEmployeTmp.setEmploye(coutHorsProdEnAttent.getEmploye());;
			
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
			
				 if(coutHorsProdEnAttent.getEmploye().isPrime()==true)
				   	{
					 
						if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			   			
				   	}else
				   	{
				   		remise=BigDecimal.ZERO;
				   	}
					
			
					if(coutHorsProdEnAttent.getCoutHoraire()!=null)
					{
						
				 
						
						 
							 
						ficheEmployeTmp.setCoutHoraire(coutHorsProdEnAttent.getCoutHoraire());
						 
				 	
						
					}
			 

					ficheEmployeTmp.setTypeResEmploye(coutHorsProdEnAttent.getTypeResEmploye());
			 
			 ficheEmployeTmp.setRemise(remise);
			 ficheEmployeTmp.setDelaiProd(delaiProd);
			 listFicheEmployeTmp.add(ficheEmployeTmp);
			
		}
		}
		
		
	}
	}	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// Detail ProductionMP //////////////////////////////////////////////////////////////////////////////////////
	
	
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
						ficheEmploye.setHeureSupp25(heureSupp25);
						ficheEmploye.setHeureSupp50(heureSupp50);
					ficheEmploye.setNumOF(numOF);
					//ficheEmploye.setCoutTotal(coutHoraire);
					ficheEmploye.setDelaiProd(delaiProd);
					
					ficheEmploye.setDelaiEmploye(delai);
					
					ficheEmploye.setHeurProduction(ficheEmploye.getHeurProduction().add(detailProductionMP.getProductionMP().getNbreHeure()));
					
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
				   			
						 if(detailProductionMP.getEmploye().isPrime()==true)
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
					 
					 
					 
						if(detailProductionMP.getCoutHoraire()!=null)
						{
							
					 
							
							 
								 
							ficheEmploye.setCoutHoraire(detailProductionMP.getCoutHoraire());
							 
					 
							
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
				ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				//ficheEmploye.setCoutSupp25(coutSupp25);
				//ficheEmploye.setCoutSupp50(coutSupp50);
				
				ficheEmploye.setHeurProduction(detailProductionMP.getProductionMP().getNbreHeure());
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
				
				
				 if(detailProductionMP.isAbsent()==false && detailProductionMP.isSortie()==false && detailProductionMP.isRetard()==false){
					 
					 if(detailProductionMP.getEmploye().isPrime()==true)
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
				 
				 
					if(detailProductionMP.getCoutHoraire()!=null)
					{
						
					 
						
						 
							 
						ficheEmploye.setCoutHoraire(detailProductionMP.getCoutHoraire());
				 
						
					}
				 
				 
				 ficheEmploye.setTypeResEmploye(detailProductionMP.getTypeResEmploye());
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(detailProductionMP.getProductionMP().getNbreHeure());
				 listFicheEmployeTmp.add(ficheEmploye);
				
			}
			}
			
			
		}
		}
		
		
	
	
	
	
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
					
					ficheEmploye.setHeureSupp25(heureSupp25);
					ficheEmploye.setHeureSupp50(heureSupp50);
					
			/*	ficheEmploye.setDateSituation(production.getDate());
				
				ficheEmploye.setEmploye(detailProdGen.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setCoutSupp25(coutSupp25);
				ficheEmploye.setCoutSupp50(coutSupp50);*/
				
				ficheEmploye.setNumOF(numOF);
			//	ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setDelaiProd(delaiProd);
				
				ficheEmploye.setDelaiEmploye(delai);
				
				ficheEmploye.setHeurProduction(ficheEmploye.getHeurProduction().add(detailProdGen.getProductionGen().getNbreHeure()));
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
				
				
				
				 if(ficheEmploye.isAbsent()==false && ficheEmploye.isSortie()==false && ficheEmploye.isRetard()==false){
			   			
					 if(detailProdGen.getEmploye().isPrime()==true)
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
				 
				 
					if(detailProdGen.getCoutHoraire()!=null)
					{
						
					 
						
						 
							 
						ficheEmploye.setCoutHoraire(detailProdGen.getCoutHoraire());
						 
						
					 	
						
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
			
		ficheEmployeTmp.setHeureSupp25(heureSupp25);
		ficheEmployeTmp.setHeureSupp50(heureSupp50);
		ficheEmployeTmp.setHeurProduction(detailProdGen.getProductionGen().getNbreHeure());
		//	ficheEmploye.setCoutSupp25(coutSupp25);
		//	ficheEmploye.setCoutSupp50(coutSupp50);
		if(detailProdGen.isSortie()==true)
		{
			ficheEmployeTmp.setSortie(true);	
			
		}else
		{
			ficheEmployeTmp.setSortie(false);	
		}
	
		if(detailProdGen.isAbsent()==true)
		{
			ficheEmployeTmp.setAbsent(true);	
			
		}else
		{
			ficheEmployeTmp.setAbsent(false);	
		}
		
		if(detailProdGen.isRetard()==true)
		{
			ficheEmployeTmp.setRetard(true);	
			
		}else
		{
			ficheEmployeTmp.setRetard(false);	
		}
		
			 if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){
		   			
				 if(detailProdGen.getEmploye().isPrime()==true)
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
			 
			 
				if(detailProdGen.getCoutHoraire()!=null)
				{
					
			 
					
					 
						 
					ficheEmployeTmp.setCoutHoraire(detailProdGen.getCoutHoraire());
					 
					
				 	
					
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
							ficheEmployeTmp.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
							ficheEmployeTmp.setHeureSupp25(detailResponsableProd.getHeureSupp25());
							ficheEmployeTmp.setHeureSupp50(detailResponsableProd.getHeureSupp50());
							ficheEmployeTmp.setSortie(detailResponsableProd.isSortie());
							ficheEmployeTmp.setAbsent(detailResponsableProd.isAbsent());
							ficheEmployeTmp.setRetard(detailResponsableProd.isRetard());
						//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
						//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
							
							 if(detailResponsableProd.isAbsent()==false && detailResponsableProd.isSortie()==false && detailResponsableProd.isRetard()==false){
						   			
								 if(detailResponsableProd.getEmploye().isPrime()==true)
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
							
							if(detailResponsableProd.getCoutHoraire()!=null)
							{
								
							if(detailResponsableProd.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
							{
								
								if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
								{
									coutHeurs=detailResponsableProd.getCoutHoraire();
								}
								
								
								
							}
								
							ficheEmployeTmp.setCoutHoraire(detailResponsableProd.getCoutHoraire());	
								
							}
							
							
						 
							
							
							ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
							 ficheEmployeTmp.setTypeResEmploye(detailResponsableProd.getTypeResEmploye());
							listFicheEmployeTmp.add(ficheEmployeTmp);
								 
		}
	
	
	 
		///////////////////////////////////////////////////////////////////////// DetailEmployeMenage //////////////////////////////////////////////////////////////////////////////////
	BigDecimal Heurstotal=BigDecimal.ZERO;	
	Parametre parametreNbrHeuresTotal=parametreDAO.findByLibelle(Constantes.NBR_HEURES_TOTAL);
		
	 for(int i=0;i<listDetailEmployeMenage.size();i++){

		 DetailEmployeMenage detailemployeMenage=listDetailEmployeMenage.get(i);
		 Heurstotal=BigDecimal.ZERO;	
		
for(int t=0;t<listFicheEmployeTmp.size();t++)
{
	
	if(detailemployeMenage.getEmploye().getId()==listFicheEmployeTmp.get(t).getEmploye().getId())
	{
		
		
		Heurstotal=Heurstotal.add(listFicheEmployeTmp.get(t).getDelaiEmploye());
		
		
		
	}
	
	
	
}

if((Heurstotal.add(detailemployeMenage.getDelaiEmploye())).compareTo(parametreNbrHeuresTotal.getValeur())<=0)
		{

	FicheEmploye ficheEmployeTmp=new FicheEmploye();
	
	
	
	

//	ficheEmploye.setCoutTotal(coutTotal);
	//ficheEmployeTmp.setNumOF(detailResponsableProd.getProduction().getNumOF());
	ficheEmployeTmp.setDateSituation(detailemployeMenage.getDateTravail());
	ficheEmployeTmp.setDelaiEmploye(detailemployeMenage.getDelaiEmploye());
	ficheEmployeTmp.setHeureSupp25(BigDecimal.ZERO);
	ficheEmployeTmp.setHeureSupp50(BigDecimal.ZERO);
	ficheEmployeTmp.setSortie(false);
	ficheEmployeTmp.setAbsent(detailemployeMenage.isAbsent());
	ficheEmployeTmp.setRetard(false);
//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
     ficheEmployeTmp.setRemise(BigDecimal.ZERO);

 	if(detailemployeMenage.getCoutHoraire()!=null)
	{
		
	if(detailemployeMenage.getCoutHoraire().compareTo(BigDecimal.ZERO)!=0)	
	{
		
		if(coutHeurs.compareTo(BigDecimal.ZERO)==0)
		{
			coutHeurs=detailemployeMenage.getCoutHoraire();
		}
		
	}
		
		
		
	}
 	
 	
 	
	if(detailemployeMenage.getCoutHoraire()!=null)
	{
		
	 
		
		 
			 
		ficheEmployeTmp.setCoutHoraire(detailemployeMenage.getCoutHoraire());
		 
		
	 	
		
	}
	
     ficheEmployeTmp.setTypeResEmploye(detailemployeMenage.getTypeResEmploye());
	ficheEmployeTmp.setEmploye(detailemployeMenage.getEmploye());
	
	listFicheEmployeTmp.add(ficheEmployeTmp);
	
		}
									
								 
		}
	 
	 
	 
	 
	 
	 
	 
	
	
	
	
	
	return listFicheEmployeTmp;
	
}


public void AjouterDetailProdResponsable()
{
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	
	listFicheEmployeTmp.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	Parametre parametre_cout_horaire=parametreDAO.findByCode(PARAMETRE_CODE_COUT_HORAIRE_CNSS);
	List<DetailResponsableProd> listDetailResponsableProd=detailResponsableDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId());
	
	JOptionPane.showMessageDialog(null, listDetailResponsableProd.size());
	
	/*
	
	
	
	remise=BigDecimal.ZERO;
	delai=BigDecimal.ZERO;
	coutHoraire=BigDecimal.ZERO;
	heureSupp25=BigDecimal.ZERO;
	heureSupp50=BigDecimal.ZERO;
	coutSupp25=BigDecimal.ZERO;
	coutSupp50=BigDecimal.ZERO;
	BigDecimal delaiProd=BigDecimal.ZERO;
	
	
	 for(int i=0;i<listDetailResponsableProd.size();i++){

		 DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
		 
		 Employe employe=detailResponsableProd.getEmploye();
		 remise=BigDecimal.ZERO;
		 
		

		 if(!employe.isSalarie()){
			 
			 if(detailResponsableProd.isAbsent()==true){
		    		
		   		// String code=Utils.genereCodeDateMoisAnnee(detailResponsableProd.getProduction().getDate());
					 
		   		// Utils.compterAbsenceEmploye(code, detailResponsableProd.getEmploye(), detailResponsableProd.getProduction().getDate());
		   		}else if(detailResponsableProd.getProduction().getNbreHeure()!=null){
		   			
		   			if(detailResponsableProd.getDelaiEmploye().compareTo(detailResponsableProd.getProduction().getNbreHeure())  >=0 )
		   			{
		   				if(detailResponsableProd.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(detailResponsableProd.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			}
					
				
		   			
		   		}
			 
			
				
			//	coutTotalAutreEmploye=coutTotalAutreEmploye+detailResponsableProd.getCoutTotal()+detailResponsableProd.getCoutSupp25()+detailResponsableProd.getCoutSupp50();
				
				
				
			 Boolean Trouve=false;
				
				for(int j=0;j<listFicheEmployeTmp.size();j++)
				{
					
		FicheEmploye ficheEmploye=listFicheEmployeTmp.get(j);
					
					if(ficheEmploye.getEmploye().getId()==detailResponsableProd.getEmploye().getId() &&  detailResponsableProd.getProduction().getDate().equals(ficheEmploye.getDateSituation()))
					{
						 int compteur=1;
						Trouve=true;
						CompteurEmployeProd compteurEmployeProd=compteurEmployeProdDAO.findByDateProdPeriode(detailResponsableProd.getProduction().getDate(),detailResponsableProd.getProduction().getPeriode(), detailResponsableProd.getEmploye().getId());
//						ficheEmploye.setCoutTotal(coutTotal);
						if(compteurEmployeProd!=null)
						{
							compteur=compteurEmployeProd.getCompteur();
						}else
						{
							compteur=1;	
						}
						ficheEmploye.setNumOF(detailResponsableProd.getProduction().getNumOF());
						ficheEmploye.setDateSituation(detailResponsableProd.getProduction().getDate());
						ficheEmploye.setDelaiEmploye(detailResponsableProd.getDelaiEmploye().multiply(new BigDecimal(compteur) ));
						ficheEmploye.setHeureSupp25(detailResponsableProd.getHeureSupp25().multiply(new BigDecimal(compteur) ));
						ficheEmploye.setHeureSupp50(detailResponsableProd.getHeureSupp50().multiply(new BigDecimal(compteur) ));
					//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
					//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
						ficheEmploye.setRemise(remise);
						ficheEmploye.setEmploye(detailResponsableProd.getEmploye());
						
				
						listFicheEmployeTmp.set(j, ficheEmploye);
						
						
						
					}
					
				}
					
					if(Trouve==false){
						 int compteur=1;
							FicheEmploye ficheEmployeTmp=new FicheEmploye();
							CompteurEmployeProd compteurEmployeProd=compteurEmployeProdDAO.findByDateProdPeriode(detailResponsableProd.getProduction().getDate(),detailResponsableProd.getProduction().getPeriode(), detailResponsableProd.getEmploye().getId());
							if(compteurEmployeProd!=null)
							{
								compteur=compteurEmployeProd.getCompteur();
							}else
							{
								compteur=1;	
							}
						//	ficheEmploye.setCoutTotal(coutTotal);
							ficheEmployeTmp.setNumOF(detailResponsableProd.getProduction().getNumOF());
							ficheEmployeTmp.setDateSituation(detailResponsableProd.getProduction().getDate());
							ficheEmployeTmp.setDelaiEmploye(detailResponsableProd.getDelaiEmploye().multiply(new BigDecimal(compteur) ));
							ficheEmployeTmp.setHeureSupp25(detailResponsableProd.getHeureSupp25().multiply(new BigDecimal(compteur) ));
							ficheEmployeTmp.setHeureSupp50(detailResponsableProd.getHeureSupp50().multiply(new BigDecimal(compteur) ));
						//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
						//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
							ficheEmployeTmp.setRemise(remise);
							ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
							
							listFicheEmployeTmp.add(ficheEmployeTmp);
							
						}
				
				
		 }
		 
		}
	 
	  */
	
	 for(int k=0;k<listDetailResponsableProd.size();k++)
	 {
		
		DetailResponsableProd detailResponsableProd= listDetailResponsableProd.get(k);
		
		DetailProdRes detailProdRes=new DetailProdRes(); 
		
		detailProdRes.setDateProduction(detailResponsableProd.getProduction().getDate());
		detailProdRes.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
		detailProdRes.setEmploye(detailResponsableProd.getEmploye());
		detailProdRes.setHeureSupp25(detailResponsableProd.getHeureSupp25());
		detailProdRes.setHeureSupp50(detailResponsableProd.getHeureSupp50());
		detailProdRes.setRemise(detailResponsableProd.getRemise());
		detailProdRes.setCoutTotal(detailResponsableProd.getDelaiEmploye().multiply(parametre_cout_horaire.getValeur()));
		detailProdRes.setCoutSupp25(detailResponsableProd.getHeureSupp25().multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
		detailProdRes.setCoutSupp50(detailResponsableProd.getHeureSupp50().multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
		detailProdResDAO.add(detailProdRes);
		
		
	 }
	 
	 JOptionPane.showMessageDialog(null, "Opération Effectué ");
	 
	
	
	
}
}

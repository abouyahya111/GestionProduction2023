package presentation.stastiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Production;


public class CoutDechetProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	
	private JTextField txtTotalAvance;
	private JTextField txtTotalPrime;
	private JTextField txtTotalCout;
	private JTextField txtTotalDu;
	
	private ProductionDAO productionDAO;
	
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CoutDechetProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	productionDAO= new ProductionDAOImpl();

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
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"N° OF ","Date OF ","Cout Déchet"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(modeleMP); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(60);
					         table.getColumnModel().getColumn(2).setPreferredWidth(100);
					        /* table.getColumnModel().getColumn(3).setPreferredWidth(60);
					         table.getColumnModel().getColumn(4).setPreferredWidth(90);
					         table.getColumnModel().getColumn(5).setPreferredWidth(90);
					         table.getColumnModel().getColumn(6).setPreferredWidth(90);*/
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 536, 383);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 536, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 536, 54);
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
		btnAfficherStock.setBounds(484, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else {
				List<Production> listeProduction=productionDAO.listeProductionEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate());
					
					//calculerTotaux(listFicheEmploye);
					if(listeProduction==null ||  listeProduction.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					}else {
						afficher_tableMP(listeProduction);
					}
					
					
				}
			
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 11, 140, 24);
		layeredPane.add(dateFinChooser);
		
		/*JButton btnValiderAvance = new JButton("Valider Avance");
		btnValiderAvance.setIcon(imgValider);
		btnValiderAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!remplirMapAvance())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir au moins une avance ", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {

					validerAvance(listFicheEmploye);
					
					JOptionPane.showMessageDialog(null, "L'avance a été validée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValiderAvance.setBounds(293, 539, 129, 24);
		add(btnValiderAvance);
		
		JLabel lblTotal = new JLabel("Total ");
		lblTotal.setBounds(369, 459, 114, 23);
		add(lblTotal);*/
		
		txtTotalAvance = new JTextField();
		txtTotalAvance.setBackground(Color.WHITE);
		txtTotalAvance.setEditable(false);
		txtTotalAvance.setBounds(668, 457, 104, 26);
		add(txtTotalAvance);
		txtTotalAvance.setColumns(10);
		
		txtTotalPrime = new JTextField();
		txtTotalPrime.setBackground(Color.WHITE);
		txtTotalPrime.setEditable(false);
		txtTotalPrime.setColumns(10);
		txtTotalPrime.setBounds(552, 457, 114, 26);
		add(txtTotalPrime);
		
		txtTotalCout = new JTextField();
		txtTotalCout.setBackground(Color.WHITE);
		txtTotalCout.setEditable(false);
		txtTotalCout.setColumns(10);
		txtTotalCout.setBounds(445, 457, 104, 26);
		add(txtTotalCout);
		
		JButton btnImprimer = new JButton("Fiche Employé");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/*
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
	  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
				 List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
				Map parameters = new HashMap();
				parameters.put("dateDu", dateDu);
				parameters.put("dateAu", dateAu);
				parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
				parameters.put("nom", ficheEmploye.getEmploye().getNom());
				
				parameters.put("totalCout", txtTotalCout.getText());
				parameters.put("totalAvance", txtTotalAvance.getText());
				parameters.put("totalPrime", txtTotalPrime.getText());
				parameters.put("totalDu", txtTotalDu.getText());
				
				JasperUtils.imprimerFicheEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
				
				JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	*/}
		});
		btnImprimer.setBounds(426, 538, 136, 24);
		add(btnImprimer);
		
		txtTotalDu = new JTextField();
		txtTotalDu.setBackground(Color.WHITE);
		txtTotalDu.setEditable(false);
		txtTotalDu.setColumns(10);
		txtTotalDu.setBounds(445, 487, 104, 26);
		add(txtTotalDu);
		
		JLabel lblTotalDu = new JLabel("Total A payer");
		lblTotalDu.setBounds(370, 487, 97, 26);
		add(lblTotalDu);
		
		JButton btnImprimerBulletinPaie = new JButton("Bulletin Paie");
		btnImprimerBulletinPaie.setIcon(imgImprimer);
		btnImprimerBulletinPaie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/*
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
	  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
				 List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
				 BigDecimal salaireBrut=mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS)*totalHoraire;
				 BigDecimal retenu226=salaireBrut*mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)/100;
				 BigDecimal retenu448=salaireBrut*mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)/100;
				 BigDecimal totalRetenu=retenu448+retenu226;
				 BigDecimal netApayer=salaireBrut-totalRetenu;
				Map parameters = new HashMap();
				parameters.put("dateDu", dateDu);
				parameters.put("dateAu", dateAu);
				parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
				parameters.put("nom", ficheEmploye.getEmploye().getNom());
				parameters.put("qualif", ficheEmploye.getEmploye().getTypeResEmploye().getLibelle());
				parameters.put("service", ficheEmploye.getEmploye().getService());
				parameters.put("dateEntre", ficheEmploye.getEmploye().getDateEntre());
				
				parameters.put("nbreHoraire", totalHoraire+"");
				parameters.put("tauxHoraire", mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS)+"");
				parameters.put("salaireBrut", salaireBrut+"");
				parameters.put("taux226", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)+"");
				parameters.put("taux448", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)+"");
				
				parameters.put("retenu226", retenu226+"");
				parameters.put("retenu448", retenu448+"");
				parameters.put("totalRetenu", totalRetenu+"");
				parameters.put("netApayer", netApayer+"");
				
				JasperUtils.imprimerBulletinPaieEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
				
				JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	*/}
		});
		btnImprimerBulletinPaie.setBounds(563, 539, 114, 24);
		add(btnImprimerBulletinPaie);
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<Production> listeProduction)
	{
		intialiserTableau();
		  int i=0;
		  BigDecimal totalAvance=BigDecimal.ZERO;
			BigDecimal totalPrime=BigDecimal.ZERO;
			BigDecimal totalCout=BigDecimal.ZERO;
			BigDecimal totalDu=BigDecimal.ZERO;
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
			String date="";
			BigDecimal avance=BigDecimal.ZERO;
			while(i<listeProduction.size())
			{	
				
				Production production=listeProduction.get(i);
				date=dateFormat.format(production.getDate());
				 
					//avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
					
				Object []ligne={production.getNumOF(),date,NumberFormat.getNumberInstance(Locale.FRANCE).format(production.getCoutDechet())};

				modeleMP.addRow(ligne);
				i++;
			}
			
		
	}



void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"N° OF","Date Production","Cout Total"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(160);
     /* table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(90);
      table.getColumnModel().getColumn(5).setPreferredWidth(90);
      table.getColumnModel().getColumn(6).setPreferredWidth(90);*/
}
}

package Equipe;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ChargeFixeDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PrimeAnciennteDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.PrimeAnciennteDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.PrimeAnciennte;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutPrimeAnciennte extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tablePrimeAnciennte = new JXTable();
	private List<PrimeAnciennte> listPrimeAnciennte =new ArrayList<PrimeAnciennte>();
 
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	 
	private JTextField txtcode;
	private JTextField txtAnneeMin;
	private JTextField txtlibelle=new JTextField();

	 private JDateChooser datePrime= new JDateChooser();
	private JTextField txtAnneeMax=new JTextField();
	private JTextField txtTaux=new JTextField();
	JButton btnModifier = new JButton();
	JButton btnSupprimer = new JButton();
	
	PrimeAnciennteDAO primeAnciennteDAO;
	private Integer CodeTranche=0;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutPrimeAnciennte() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 724);
      
	
        try{ 
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             primeAnciennteDAO=new PrimeAnciennteDAOImpl();
             listPrimeAnciennte=primeAnciennteDAO.findAll();
            
          } catch (Exception exp){exp.printStackTrace();}
        tablePrimeAnciennte.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		 
       		if(tablePrimeAnciennte.getSelectedRow()!=-1)
       		{
       		
       			PrimeAnciennte primeAnciennte=listPrimeAnciennte.get(tablePrimeAnciennte.getSelectedRow());
       			
       			txtcode.setText(primeAnciennte.getTranche());
       			txtAnneeMin.setText(primeAnciennte.getAnneMin().toString());
       			txtAnneeMax.setText(primeAnciennte.getAnneMax().toString());
       			txtTaux.setText(primeAnciennte.getTaux().toString());
       			datePrime.setDate(primeAnciennte.getDatePrime());
       			btnAjouter.setEnabled(false);
       		 btnModifier.setEnabled(true);
       		btnSupprimer.setEnabled(true);
       		}
       		
       		
       		
       		
       		
       		
       		 	}
       });
        
       tablePrimeAnciennte.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			
       		"Tranche", "Annees Min", "Annees Max", "Taux","Date Prime"
       	}
       ));
       tablePrimeAnciennte.getColumnModel().getColumn(0).setPreferredWidth(198);
       tablePrimeAnciennte.getColumnModel().getColumn(1).setPreferredWidth(87);
       tablePrimeAnciennte.getColumnModel().getColumn(2).setPreferredWidth(94);
				  		
       tablePrimeAnciennte.setShowVerticalLines(false);
       tablePrimeAnciennte.setSelectionBackground(new Color(51, 204, 255));
       tablePrimeAnciennte.setRowHeightEnabled(true);
       tablePrimeAnciennte.setBackground(new Color(255, 255, 255));
       tablePrimeAnciennte.setHighlighters(HighlighterFactory.createSimpleStriping());
       tablePrimeAnciennte.setColumnControlVisible(true);
       tablePrimeAnciennte.setForeground(Color.BLACK);
       tablePrimeAnciennte.setGridColor(new Color(0, 0, 255));
       tablePrimeAnciennte.setAutoCreateRowSorter(true);
       tablePrimeAnciennte.setBounds(2, 27, 411, 198);
       tablePrimeAnciennte.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tablePrimeAnciennte);
				  		     	scrollPane.setBounds(10, 372, 1031, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Primes Anciennete");
				  		     	titledSeparator.setBounds(10, 331, 1027, 30);
				  		     	add(titledSeparator);
		      
		
		
		  btnModifier = new JButton();
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

   			 
	     		
   			try {
   				
   				
   				
 				if(txtcode.getText().equals(""))
     			{
     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code Prime  SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
     				return;
     			}
     			else if(datePrime.getDate()==null)
     			{
     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date de Prime SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
     				return;
     			}
     			
     			else if(txtAnneeMin.getText().equals(""))
     			{
     				JOptionPane.showMessageDialog(null, "Veuillez entrer l'annee Min SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
     				return;
     			}
     			else if(txtAnneeMin.getText().equals(""))
     			{
     				JOptionPane.showMessageDialog(null, "Veuillez  entrer l'annee Min SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
     				return;
     			}
 				
     			else if(txtTaux.getText().equals(""))
     			{
     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Taux SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
     				return;
     			}
     		 
     		
     			
     			else
     			{
	     			
			    	PrimeAnciennte primeAnciennte=listPrimeAnciennte.get(tablePrimeAnciennte.getSelectedRow());	
			    	primeAnciennte.setAnneMin(new BigDecimal(txtAnneeMin.getText()) );
			    	primeAnciennte.setAnneMax(new BigDecimal(txtAnneeMax.getText()) );  
			    	primeAnciennte.setDatePrime(datePrime.getDate());
			    	primeAnciennte.setModifierPar(utilisateur);		
			    	primeAnciennte.setDateModification(new Date());	
			    	primeAnciennte.setTaux(new BigDecimal(txtTaux.getText()));
			    	 primeAnciennteDAO.edit(primeAnciennte);
			    	 listPrimeAnciennte.set(tablePrimeAnciennte.getSelectedRow(), primeAnciennte);
			    	 JOptionPane.showMessageDialog(null, "prime Anciennte Modifier Avec Succee ","Bravo",JOptionPane.INFORMATION_MESSAGE);	
		     			initialiser();		     			 
		     			afficher_tablePrimeAnciennte(listPrimeAnciennte);
	     				
	     				
	     			}
	     			
   				
   				
				
			} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "la Quantité , le Prix Unitaire et le Montant doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
				
			}
   			
   			
   		
			}
		});
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1051, 449, 73, 24);
		add(btnModifier);
		
		  btnSupprimer = new JButton();
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePrimeAnciennte.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'enregistrement  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
						{
							
							PrimeAnciennte primeAnciennte=listPrimeAnciennte.get(tablePrimeAnciennte.getSelectedRow());
							primeAnciennteDAO.delete(primeAnciennte.getId());
							listPrimeAnciennte.remove(tablePrimeAnciennte.getSelectedRow());
							afficher_tablePrimeAnciennte(listPrimeAnciennte);
							initialiser();
							
							
							
							
						}
	     	 	
		  		}
			}
		});
		btnSupprimer.setIcon(imgSupprimer);
		btnSupprimer.setBounds(1051, 496, 73, 24);
		add(btnSupprimer);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1031, 232);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(345, 22, 89, 24);
		layeredPane_1.add(label);
		
		txtcode = new JTextField();
		txtcode.setEditable(false);
		txtcode.setColumns(10);
		txtcode.setBounds(417, 21, 256, 26);
		layeredPane_1.add(txtcode);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(345, 71, 62, 24);
		layeredPane_1.add(label_1);
		
		 datePrime = new JDateChooser();
		datePrime.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				

			 
			
			
				
				
				
			}
		});
		datePrime.setLocale(Locale.FRANCE);
		datePrime.setDateFormatString("dd/MM/yyyy");
		datePrime.setBounds(417, 69, 256, 26);
		layeredPane_1.add(datePrime);
		
		JLabel lblDesignation = new JLabel("Annees Min :");
		lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDesignation.setBounds(345, 106, 82, 26);
		layeredPane_1.add(lblDesignation);
		
		txtAnneeMin = new JTextField();
		txtAnneeMin.setColumns(10);
		txtAnneeMin.setBounds(419, 106, 254, 26);
		layeredPane_1.add(txtAnneeMin);
		
		JLabel lblAnneesMax = new JLabel("Annees Max :");
		lblAnneesMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnneesMax.setBounds(345, 147, 82, 26);
		layeredPane_1.add(lblAnneesMax);
		
		txtAnneeMax = new JTextField();
		txtAnneeMax.setColumns(10);
		txtAnneeMax.setBounds(419, 147, 254, 26);
		layeredPane_1.add(txtAnneeMax);
		
		JLabel lblTaux = new JLabel("Taux :");
		lblTaux.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTaux.setBounds(345, 184, 82, 26);
		layeredPane_1.add(lblTaux);
		
		txtTaux = new JTextField();
		txtTaux.setColumns(10);
		txtTaux.setBounds(419, 184, 254, 26);
		layeredPane_1.add(txtTaux);
	 
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Primes Anciennte");
		titledSeparator_2.setBounds(10, 11, 1031, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(391, 296, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	     			   
	     			try {
	     				
	     				
	     				if(txtcode.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code Prime  SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(datePrime.getDate()==null)
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date de Prime SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			
		     			else if(txtAnneeMin.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer l'annee Min SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(txtAnneeMin.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez  entrer l'annee Min SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
	     				
		     			else if(txtTaux.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Taux SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     		 
		     		
		     			
		     			else
		     			{
		     				
		     			PrimeAnciennte primeAnciennte=new PrimeAnciennte();
		     			
		     			primeAnciennte.setAnneMin(new BigDecimal(txtAnneeMin.getText()));	
		     			primeAnciennte.setAnneMax(new BigDecimal(txtAnneeMax.getText()));		
		     			primeAnciennte.setDatePrime(datePrime.getDate());	
		     			primeAnciennte.setTaux(new BigDecimal(txtTaux.getText())); 
		     			primeAnciennte.setTranche(txtcode.getText());	
		     			primeAnciennte.setDateCreation(new Date());
		     			primeAnciennte.setCreerPar(utilisateur);
		     			primeAnciennteDAO.add(primeAnciennte);
		     			JOptionPane.showMessageDialog(null, "prime Anciennte Ajouter Avec Succee ","Bravo",JOptionPane.INFORMATION_MESSAGE);	
		     			initialiser();
		     			listPrimeAnciennte.add(primeAnciennte);
		     			afficher_tablePrimeAnciennte(listPrimeAnciennte);
		     			
		     			
		     				
		     			}
		     			
	     				
	     				
					
				} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "l'Annee Min , l'Annee Max et le Taux doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
					
				}
	     			
	     			
	     		}
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(522, 295, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  		initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  btnAjouter.setEnabled(true);
			 btnModifier.setEnabled(false);
			btnSupprimer.setEnabled(false);
	maxId();
	
	afficher_tablePrimeAnciennte(listPrimeAnciennte);
			
		}
 
	void maxId()
	{
		 
		long max=primeAnciennteDAO.maxId() ;
		System.out.print(max);
		CodeTranche= new Integer((int) max)+1;
 
		txtcode.setText(String.valueOf(CodeTranche));    
		
	}
	
	
	  void initialiser()
	{
		
	txtcode.setText("");
	txtAnneeMin.setText("");
	txtAnneeMax.setText("");
	txtTaux.setText("");
	datePrime.setDate(null);
	maxId();
	
	btnAjouter.setEnabled(true);
	 btnModifier.setEnabled(false);
	btnSupprimer.setEnabled(false);
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matire Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tablePrimeAnciennte.setModel(modeleChargefixe);
		 tablePrimeAnciennte.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tablePrimeAnciennte.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tablePrimeAnciennte.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tablePrimeAnciennte(List<PrimeAnciennte> listPrimeAnciennete)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Tranche", "Annees Min", "Annees Max", "Taux","Date Prime"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tablePrimeAnciennte.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listPrimeAnciennete.size())
		{	
			 PrimeAnciennte primeAnciennte=listPrimeAnciennete.get(i);
			
			Object []ligne={primeAnciennte.getTranche(),primeAnciennte.getAnneMin(),primeAnciennte.getAnneMax(),primeAnciennte.getTaux(),primeAnciennte.getDatePrime()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	}



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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.RecapFicheEmploye;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;


public class AfficherListeEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private EmployeDAO employeDAO;
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapEmploye = new HashMap<>();
	private List<Employe> listEmployeCombo=new ArrayList<Employe>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Utilisateur utilisateur;
	private JComboBox comboDepot = new JComboBox();
	private FicheEmployeDAO ficheEmployeDAO;

	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private DepotDAO depotDAO;
	JComboBox comboemploye = new JComboBox();
	private JTextField txtmatricule =new JTextField();
	JComboBox comboresponsabilite = new JComboBox();
	JCheckBox chckbxActif = new JCheckBox("Actif");
	private List<TypeResEmploye> listTypeResEmploye=new ArrayList<TypeResEmploye>();
	private Map< String, TypeResEmploye> mapTypeResEmploye = new HashMap<>();
	private TypeResEmployeDAO typeResEmployeDAO;
	JCheckBox chckbxInactif = new JCheckBox("InActif");
	 JCheckBox chckbxBloquant = new JCheckBox("Bloquant");
	 JLabel labeltotalactif = new JLabel("");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherListeEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1451, 707);
        try{
        	
        	
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	employeDAO=new EmployeDAOImpl();
        	typeResEmployeDAO=new TypeResEmployeDAOImpl();
        	
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
					  		     			"Matricule","Num Dossier","Nom","Actif","Salarie"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false, false, false,false,false
					  		     	};
					  		  	Class[] columnTypes = new Class[] {
										String.class,String.class,String.class,String.class, String.class
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
					  		 			"Matricule","Num Dossier","Nom","Actif","Salarie","Responsabiltié ","Bloque","Date Entrer","Date sortie"	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false, false,false,false, false, false,false,false
					  		 	};
					  		 	
					  			Class[] columnTypes = new Class[] {
										String.class,String.class,String.class,String.class, String.class,String.class,String.class,String.class,String.class
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
					  		 table.getColumnModel().getColumn(2).setPreferredWidth(160);
					  		table.getColumnModel().getColumn(3).setPreferredWidth(60);
					  		table.getColumnModel().getColumn(4).setPreferredWidth(60);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 110, 1431, 383);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(10, 94, 1431, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 45, 1431, 54);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1390, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				intialiserTableau();
		          Depot depot=mapDepot.get(comboDepot.getSelectedItem());
		          
		          String requete="";
		         
		          
		          
		          
		  		if(depot!=null)
		  		{
		  			
		  			
		  			
		  			
		  			
		  			 if(comboemploye.getSelectedIndex()!=-1)
			          {
			        	  
			        	  if(!comboemploye.getSelectedItem().equals(""))
			        	  {
			        		  requete=requete+" and nom='"+ comboemploye.getSelectedItem().toString()+"'";
			        	  }
			        	  
			          }
			          	
		  			
		  			 if(comboresponsabilite.getSelectedIndex()!=-1)
			          {
			        	  
			        	  if(!comboresponsabilite.getSelectedItem().equals(""))
			        	  {
			        		  requete=requete+" and typeResEmploye.libelle='"+ comboresponsabilite.getSelectedItem().toString()+"'";
			        	  }
			        	  
			          }
		  			
		  			if(chckbxActif.isSelected()==true)
		  			{
		  				if(chckbxInactif.isSelected()==true)
		  				{
		  					 requete=requete+" and actif='"+ 1+"' or  actif='"+ 0+"'";
		  				}else
		  				{
		  					requete=requete+" and actif='"+ 1+"'";	
		  				}
		  				
		  				
		  				
		  			}
		  			
		  			if(chckbxInactif.isSelected()==true)
		  			{
		  				if(chckbxActif.isSelected()==true)
		  				{
		  					
		  					requete=requete+" and actif='"+ 1+"' or  actif='"+ 0+"'";
		  				}else
		  				{
		  					 requete=requete+" and actif='"+ 0+"'";
		  				}
		  				
		  				
		  			}
		  			
		  			if(chckbxBloquant.isSelected()==true)
		  			{
		  				
		  				 requete=requete+" and blocageEmploye='"+ Constantes.CODE_OUI +"'";
		  				 
		  				
		  			}
		  			
		  			
		  		 listEmploye=employeDAO.findByDepotByNomByResponsabilteByActif(depot.getCode() , requete);
		            
			     afficher_Employe(listEmploye);
		  		}else
		  		{
		  			
		          JOptionPane.showMessageDialog(null, "Veuillez selectionner le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE );  
		     		
		     	}
		     	
}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(24, 11, 84, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		 comboDepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		if(!comboDepot.getSelectedItem().equals(""))
		 		{
		 			Depot depot=mapDepot.get(comboDepot.getSelectedItem());
		 			listEmployeCombo=employeDAO.findByDepotActifEtSalarie(depot.getCode());
		 			comboemploye.removeAllItems();
		 			comboemploye.addItem("");
		 			for(int i=0;i<listEmployeCombo.size();i++)
		 			{
		 				
		 				Employe employe=listEmployeCombo.get(i);
		 				comboemploye.addItem(employe.getNom());
		 				mapMatriculeEmploye.put(employe.getMatricule(), employe);
		 				mapEmploye.put(employe.getNom(), employe);
		 				
		 				
		 				
		 			}
		 			
		 			
		 		}else
		 		{
		 			comboemploye.removeAllItems();
		 			comboemploye.addItem("");
		 		}
		 		
		 		
		 		
		 	}
		 });
		comboDepot.setBounds(74, 11, 191, 26);
		layeredPane.add(comboDepot);
		
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
		comboemploye.setBounds(554, 11, 218, 26);
		layeredPane.add(comboemploye);
		 AutoCompleteDecorator.decorate(comboemploye);
		 comboemploye.addItem("");
		JLabel lblEmploye = new JLabel("Employe :");
		lblEmploye.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmploye.setBounds(487, 11, 84, 26);
		layeredPane.add(lblEmploye);
		
		JLabel lblMatricule = new JLabel("Matricule :");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMatricule.setBounds(275, 11, 70, 26);
		layeredPane.add(lblMatricule);
		
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
		txtmatricule.setColumns(10);
		txtmatricule.setBounds(343, 12, 124, 26);
		layeredPane.add(txtmatricule);
		
		JButton btnValiderAvance = new JButton("Imprimer");
		btnValiderAvance.setIcon(imgImprimer);
		btnValiderAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(listEmploye.size()!=0)
			{
				Map parameters = new HashMap();
				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				parameters.put("depot", depot.getLibelle());
				parameters.put("totalactif", labeltotalactif.getText());
				JasperUtils.imprimerEtatEmployes(listEmploye,parameters);
				
			}
			
			
			
			
			}
		});
		btnValiderAvance.setBounds(310, 504, 129, 24);
		
		add(btnValiderAvance);
		
		int k=0;
		comboDepot.addItem("");
		
		JLabel lblResponsabilite = new JLabel("Responsabilite :");
		lblResponsabilite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResponsabilite.setBounds(783, 11, 84, 26);
		layeredPane.add(lblResponsabilite);
		
		 comboresponsabilite = new JComboBox();
		comboresponsabilite.setBounds(869, 11, 218, 26);
		layeredPane.add(comboresponsabilite);
		
		 chckbxActif = new JCheckBox("Actif");
		chckbxActif.setBounds(1118, 11, 70, 23);
		layeredPane.add(chckbxActif);
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
      	
      	
      	 comboresponsabilite.addItem("");
      	 
      	  chckbxInactif = new JCheckBox("InActif");
      	 chckbxInactif.setBounds(1190, 11, 70, 23);
      	 layeredPane.add(chckbxInactif);
      	 
      	  chckbxBloquant = new JCheckBox("Bloquant");
      	 chckbxBloquant.setBounds(1266, 11, 70, 23);
      	 layeredPane.add(chckbxBloquant);
      	 
      	 JLabel lblTotalEmployeesActif = new JLabel("Total Employees Actif :");
      	 lblTotalEmployeesActif.setForeground(Color.RED);
      	 lblTotalEmployeesActif.setFont(new Font("Tahoma", Font.BOLD, 16));
      	 lblTotalEmployeesActif.setBounds(534, 504, 195, 35);
      	 add(lblTotalEmployeesActif);
      	 
      	  labeltotalactif = new JLabel("0");
      	 labeltotalactif.setForeground(Color.RED);
      	 labeltotalactif.setFont(new Font("Tahoma", Font.BOLD, 16));
      	 labeltotalactif.setBounds(728, 504, 258, 40);
      	 add(labeltotalactif);
      	 
     	listTypeResEmploye = typeResEmployeDAO.findAll();	
      int	i=0;
      	while(i<listTypeResEmploye.size())
      		{	
      		TypeResEmploye typeResEmploye=listTypeResEmploye.get(i);
      			mapTypeResEmploye.put(typeResEmploye.getLibelle(), typeResEmploye);
      			comboresponsabilite.addItem(typeResEmploye.getLibelle());
      			i++;
      		}
	
	   		     
				  		 
	}
	
void afficher_Employe(List<Employe> listEmploye)
	{
		intialiserTableau();
		  int i=0;
		 int totalactif=0;
		 
			while(i<listEmploye.size())
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Employe Employe=listEmploye.get(i);
				
				if(Employe.isActif()==true)
				{
					
					totalactif=totalactif+1;	
					
				}
				
			
				Object []ligne={Employe.getMatricule(),Employe.getNumDossier(),Employe.getNomafficher() , Employe.getActive(),Employe.getSalarier(),Employe.getTypeResEmploye().getLibelle(),Employe.getBlocageEmploye(),Employe.getDateEntre(),Employe.getDateSortie()};

				modeleMP.addRow( ligne);
				i++;
			}
			
		labeltotalactif.setText(totalactif+"");	
			
			
				
	}


void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matricule","Num Dossier","Nom","Actif","Salarie","Responsabiltié ","Bloque","Date Entrer","Date sortie"}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false, false, false,false, false,false,false, false,false
		     	};
		     	
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class,String.class,String.class, String.class,String.class,String.class,String.class,String.class
					};
	  			public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(60);
      table.getTableHeader().setReorderingAllowed(false);
}
}

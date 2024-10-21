package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

 

import util.Constantes;
import util.DateUtils;
import util.Utils;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DetailEmployeMenageDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DetailEmployeMenageDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;
import dao.entity.DetailEmployeMenage;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.JTextArea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class SaisirListeMenage extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	private JXTable tableEmployeFiltrer=new JXTable();
	private JXTable tableEmployeFiltrer_1;;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	

	
	private Production production = new Production();
	private List<Employe> listEmployer =new ArrayList<Employe>();
	private List<DetailResponsableProd> listDetailResponsableProd=new ArrayList<DetailResponsableProd>();
	
	
	private Map< String, BigDecimal> mapDelai = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp25 = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp50 = new HashMap<>();
	private Map< String,Boolean> mapEmployeAbsent = new HashMap<>();
	private Map< String,Boolean> mapEmployeSortie = new HashMap<>();
	
	private Map< Integer, Employe> mapEmployeDelai = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp25 = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp50 = new HashMap<>();
	
	private Map< String, Employe> mapEmployeGlobal = new HashMap<>();
	private List <Integer> lsiteInt=new ArrayList<Integer>();
	
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private DetailEmployeMenageDAO detailEmployeMenageDAO;
	private TypeResEmployeDAO typeResEmployeDAO;
	private EmployeDAO employeDAO;
	private ProductionDAO productionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private ParametreDAO parametreDAO;
	private int selectedRow ;
	
	private int compteur=0;
	
	String quantite;
	BigDecimal nbreHeure;
	private JLabel lblEmployesProductionGenerique;
	private DetailProdResDAO detailProdResDAO;
	 JDateChooser datetravail = new JDateChooser();
	 
	 private List <Object[]> listeObjetMaxDate=new ArrayList<Object[]>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */

	public SaisirListeMenage() {
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1000, 445);
        try {
        	javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
        	
        	tableEmployeFiltrer_1=new JXTable();
        	 DefaultCellEditor ce = (DefaultCellEditor) tableEmployeFiltrer_1.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	compteur=0;
        	
        	
        	listEmployer =new ArrayList<Employe>();
        	
        	tableEmploye=new JXTable();
        	

        	
        	
        	detailEmployeMenageDAO=new DetailEmployeMenageDAOImpl();
        	employeDAO= new EmployeDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	compteurEmployeProdDAO= new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	typeResEmployeDAO=new TypeResEmployeDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

        initialiserTableauEmploye();
        TypeResEmploye typeresemploye=typeResEmployeDAO.findByCode(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_MENAGE);
		
             listEmployer=employeDAO.findByDepotByResponsabilite(AuthentificationView.utilisateur.getCodeDepot(), typeresemploye);
             remplirMapEmployeGlobal();
		     afficher_tableEmploye(listEmployer);
		   
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		   setLayout(null);
				  		 
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableEmployeFiltrer_1);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 945, 232);
				  		    add(scrollPane);
				  		     
				  		     JButton btnValider = new JButton();
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		afficher_detailproduction();

				  		     		
				  		     	
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnValider.setText("Valider");
				  		   btnValider.setIcon(null);
				  		   btnValider.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     btnValider.setBounds(447, 356, 114, 40);
				  		     add(btnValider);
				  		     
				  		     lblEmployesProductionGenerique = new JLabel("Employ\u00E9es M\u00E9nage");
				  		     lblEmployesProductionGenerique.setForeground(Color.BLUE);
				  		     lblEmployesProductionGenerique.setFont(new Font("Tahoma", Font.BOLD, 15));
				  		     lblEmployesProductionGenerique.setBounds(10, 11, 281, 24);
				  		     add(lblEmployesProductionGenerique);
				  		     
				  		     JLabel lblDateDeTravail = new JLabel("Date De travail  :");
				  		     lblDateDeTravail.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		     lblDateDeTravail.setBounds(10, 61, 110, 26);
				  		     add(lblDateDeTravail);
				  		     
				  		      datetravail = new JDateChooser();
				  		     datetravail.setLocale(Locale.FRANCE);
				  		     datetravail.setDateFormatString("dd/MM/yyyy");
				  		     datetravail.setBounds(130, 61, 161, 26);
				  		     add(datetravail);
	
				  		  
	
				  		
	
	}
	
	void afficher_detailproduction()
	{

	Boolean travailler=false;	
	Boolean valider=false;
	Date MaxDate=null;
		if(datetravail.getDate()!=null)
		{
			

			listeObjetMaxDate=detailEmployeMenageDAO.MaxDateTravailMenege();
			
			for(int j=0;j<listeObjetMaxDate.size() ; j++)
			{
				
				 Object[] object=listeObjetMaxDate.get(j);
				 
				 if(object[1]!=null)
					{
					 MaxDate=(Date) object[1] ;
					}
				 
			}
			
			if(MaxDate!=null)
			{
				if(MaxDate.compareTo(datetravail.getDate())>0)
				{
					JOptionPane.showMessageDialog(null, "La Date Selectionne Est Inferieur Au Derniere Date Entre : "+ MaxDate,"Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			Parametre parametre=parametreDAO.findByDateByLibelle(datetravail.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS) ;

			
			if(tableEmployeFiltrer_1.getRowCount()!=0)
			{
				
				for(int i=0;i<tableEmployeFiltrer_1.getRowCount() ; i++)
				{
					
					travailler=Boolean.valueOf(tableEmployeFiltrer_1.getValueAt(i, 3).toString());
					 
					 Employe employe=mapEmployeGlobal.get(tableEmployeFiltrer_1.getValueAt(i, 0).toString());
					
					DetailEmployeMenage detailEmployeMenage=detailEmployeMenageDAO.findByDateByEmploye(datetravail.getDate(), employe);
					
					if(detailEmployeMenage==null)
					{
						
						DetailEmployeMenage detailEmployeMenageTmp=new DetailEmployeMenage();
						
						detailEmployeMenageTmp.setCoutHoraire(parametre.getValeur());
					
						if(travailler==false)
						{
							detailEmployeMenageTmp.setAbsent(true);
							detailEmployeMenageTmp.setDelaiEmploye(BigDecimal.ZERO);
							detailEmployeMenageTmp.setCoutTotal(BigDecimal.ZERO);
							
						}else
						{
							detailEmployeMenageTmp.setAbsent(false);
							detailEmployeMenageTmp.setDelaiEmploye(new BigDecimal(8));
							detailEmployeMenageTmp.setCoutTotal(new BigDecimal(8).multiply(parametre.getValeur()));
						}
						
						detailEmployeMenageTmp.setDateTravail(datetravail.getDate());
						detailEmployeMenageTmp.setEmploye(employe);
						detailEmployeMenageTmp.setTypeResEmploye(employe.getTypeResEmploye());
						detailEmployeMenageDAO.add(detailEmployeMenageTmp);
						valider=true;
					}
					
					
				}
				
				
				if(valider==true)
				{
					JOptionPane.showMessageDialog(null, "La Liste des Menages est  Valider Avec succÈe","Information",JOptionPane.INFORMATION_MESSAGE);
					
					afficher_tableEmploye(listEmployer);
					
					
					return;
					
					
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "La Liste des Menages est dÈja Saisir dans cette date SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "La Liste des Menages est vide");
				return;
				
			}
	
			
			
			
			
		}else
		{
		JOptionPane.showMessageDialog(null, "veuillez entrer la date de travail SVP");
		return;
			
		}

	
	
	
	
	
	
	
	}
		     	
		  
	


void afficher_tableEmploye(List<Employe> listEmployer)
	{
	initialiserTableauEmploye();
	
		  int i=0;
			while(i<listEmployer.size())
			{	
				Employe employer=listEmployer.get(i);
				
			
				
				Object []ligne={employer.getNumDossier(),employer.getMatricule(),employer.getNomafficher(),false};

				modeleEmploye.addRow(ligne);
				i++;
			}
				
		
	
	}


void initialiserTableauEmploye(){
	

	 modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Dossier","Matricule", "Nom", "Travailer"
		     	}
		     ) {

	     	boolean[] columnEditables = new boolean[] {
	     			false,false,false,true,true
	     	};
	    
	     	Class[] columnTypes = new Class[] {
	     			String.class,String.class,String.class, Boolean.class
				};
	      	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     
	 };
		     
		    
		     tableEmployeFiltrer_1.setModel(modeleEmploye);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(2).setPreferredWidth(160);		    
		    
}
void remplirMapEmployeGlobal(){
	

	for(int i=0;i<listEmployer.size();i++)
	{	
		Employe employer=listEmployer.get(i);
		
		mapEmployeGlobal.put(employer.getNumDossier(), employer);
		
	}


}
}

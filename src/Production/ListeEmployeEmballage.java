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

import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

 

import util.Constantes;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;
import dao.entity.DetailProdGen;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;

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


public class ListeEmployeEmballage extends JFrame implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	private JXTable tableEmployeFiltrer=new JXTable();
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
	private List<DetailProdGen> listeDetailProdGen=new ArrayList<DetailProdGen>();
	
	
	private Map< String, BigDecimal> mapDelai = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp25 = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp50 = new HashMap<>();
	
	private Map< Integer, Employe> mapEmployeDelai = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp25 = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp50 = new HashMap<>();
	private Map< String,Boolean> mapEmployeAbsent = new HashMap<>();
	private Map< String,Boolean> mapEmployeSortie = new HashMap<>();
	
	private Map< String, Employe> mapEmployeGlobal = new HashMap<>();
	private List <Integer> lsiteInt=new ArrayList<Integer>();
	
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private EmployeDAO employeDAO;
	private ProductionDAO productionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private JLabel lblMatricule;
	private JLabel lblNom;
	private JLabel lblNumDossier;
	
	private JTextField matricule;
	private JTextField nom;
	private JTextField numdossier;
	
	private int selectedRow ;
	private int compteur=0;
	
	String quantite;
	BigDecimal nbreHeure;
	private JLabel lblEmployesProductionEmballage;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public ListeEmployeEmballage(final Production productionParame, final String quantiteParam, final String nbreHeureParam) {
		
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
        	
        	 DefaultCellEditor ce = (DefaultCellEditor) tableEmployeFiltrer.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	
        	listEmployer =new ArrayList<Employe>();
        	
        	tableEmploye=new JXTable();
        	

        	production=productionParame;
        	quantite=quantiteParam;
        	nbreHeure=new BigDecimal(nbreHeureParam);
        	employeDAO= new EmployeDAOImpl();
        	productionDAO= new ProductionDAOImpl();
      
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

        initialiserTableauEmploye();
        
		
        listEmployer=employeDAO.findByDepot(productionParame.getCodeDepot());
      
             remplirMapEmployeGlobal();
		     afficher_tableEmploye(listEmployer);
				  	
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		     getContentPane().setLayout(null);
				  		  
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableEmployeFiltrer);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 945, 232);
				  		     getContentPane().add(scrollPane);
				  		     
				  		     JButton btnValider = new JButton();
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		afficher_detailproduction();
				  		     	//	JOptionPane.showMessageDialog(null,"map employer delai size : "+ mapEmployeDelai.size());
				  		     
				  		     		
				  		     		Main.contentPane=new TerminerOrdreFabrication(production,quantite,nbreHeureParam);
				  		     		Main.generalScrollPane.setViewportView(Main.contentPane);
				  		     		Main.contentPane.setOpaque(true);		
				  		     		//terminerorderfabrication.productioon
				  		     		//terminerorderfabrication.txtNumOF.setText("ghghghg");
				  		     		dispose();
				  		     	
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnValider.setText("Valider");
				  		   btnValider.setIcon(null);
				  		   btnValider.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     btnValider.setBounds(447, 356, 114, 40);
				  		     getContentPane().add(btnValider);
				  		     
				  		     lblMatricule = new JLabel("Matricule");
				  		     lblMatricule.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     lblMatricule.setBounds(324, 55, 68, 14);
				  		     getContentPane().add(lblMatricule);
				  		     
				  		     lblNom = new JLabel("Nom");
				  		     lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     lblNom.setBounds(622, 55, 46, 14);
				  		     getContentPane().add(lblNom);
				  		     
				  		     lblNumDossier = new JLabel("Num Dossier");
				  		     lblNumDossier.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		     lblNumDossier.setBounds(26, 51, 106, 24);
				  		     getContentPane().add(lblNumDossier);
				  		     
				  		   numdossier=new JTextField();
				  		  util.Utils.copycoller(numdossier);
				  		   numdossier.addKeyListener(new KeyAdapter() {
				  		   	@Override
				  		   	public void keyPressed(KeyEvent arg0) {
				  		     
				  		  listEmployer=employeDAO.findByNUmDossier_Matricule_Nom(numdossier.getText(),matricule.getText(),nom.getText());
		  		     	  afficher_tableEmploye(listEmployer);
		  		     			
				  		   	}
				  		   });
				  		     numdossier.setBounds(119, 48, 172, 27);
				  		     getContentPane().add(numdossier);
				  		     numdossier.setColumns(10);
				  		     
				  		     matricule = new JTextField();
				  		   util.Utils.copycoller(matricule);
				  		     matricule.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     	
				  		    
				  		     	  listEmployer=employeDAO.findByNUmDossier_Matricule_Nom(numdossier.getText(),matricule.getText(),nom.getText());
				  		     		 afficher_tableEmploye(listEmployer);
				  		     			
				  		     	
				  		     	}
				  		     });
				  		     matricule.setBounds(402, 50, 172, 27);
				  		     getContentPane().add(matricule);
				  		     matricule.setColumns(10);
				  		     
				  		     
				  		   
				  		     nom = new JTextField();
				  		   util.Utils.copycoller(nom);
				  		     nom.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		listEmployer=employeDAO.findByNUmDossier_Matricule_Nom(numdossier.getText(),matricule.getText(),nom.getText());
				  		     	
				  		     	    afficher_tableEmploye(listEmployer);
				  		     	    
				  		     		
				  		     	
				  		     	}
				  		     });
				  		     nom.setBounds(678, 51, 172, 24);
				  		     getContentPane().add(nom);
				  		     nom.setColumns(10);
				  		    
				  		   
				  		     
				  		     numdossier.setBounds(114, 51, 189, 24);
				  		     getContentPane().add(numdossier);
				  		     numdossier.setColumns(10);
				  		     
				  		     lblEmployesProductionEmballage = new JLabel("Employ\u00E9es Production Emballage");
				  		     lblEmployesProductionEmballage.setForeground(Color.BLUE);
				  		     lblEmployesProductionEmballage.setFont(new Font("Tahoma", Font.BOLD, 15));
				  		     lblEmployesProductionEmballage.setBounds(10, 11, 293, 24);
				  		     getContentPane().add(lblEmployesProductionEmballage);
				  		
	
	}
	
	void afficher_detailproduction()
	{
		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
		boolean absent=false;
		boolean sortie=false;
   		int idEmploye;
   		
    	
		    for(int i=0;i<mapEmployeDelai.size();i++)
		    {
		    	DetailProdGen detailprodGen=new DetailProdGen();
		    	detaildelai=BigDecimal.ZERO;
		    	detailheur25=BigDecimal.ZERO;
		    	detailheur50=BigDecimal.ZERO;
		    	absent=false;
		    	sortie=false;
		    //	int key=lsiteInt.get(i);
		    	Employe employe=mapEmployeDelai.get(i);
		    	
		    	if(employe!=null)
		    	{
		    		idEmploye=employe.getId();
		    	
		   
		    	if(mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	{
		    		
		    	/*	if(mapDelai.containsKey(employe.getNumDossier()))
		    		detaildelai=mapDelai.get(employe.getNumDossier());
		    	
		    	if(mapHeureSupp25.containsKey(employe.getNumDossier()))
		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
		    	
		    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
		    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());*/
		    	if(mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	absent=mapEmployeAbsent.get(employe.getNumDossier());
		    	
		    	
		    	detailprodGen.setEmploye(employe);
		    	detailprodGen.setDelaiEmploye(detaildelai);
		    	detailprodGen.setHeureSupp25(detailheur25);
		    	detailprodGen.setHeureSupp50(detailheur50);
		    	detailprodGen.setAbsent(absent);
		    	detailprodGen.setProductionGen(production);
		    	listeDetailProdGen.add(detailprodGen);
		    		
		    	}
		    	
		    	else if(!mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	{
		    	
		    		if(mapDelai.containsKey(employe.getNumDossier()))
		    		{
		    			
		    			if( mapDelai.get(employe.getNumDossier()).compareTo(BigDecimal.ZERO) >0)
		    			{
		    				
		    				if(mapEmployeSortie.containsKey(employe.getNumDossier()))
		    					sortie=mapEmployeSortie.get(employe.getNumDossier());
		    				detaildelai=mapDelai.get(employe.getNumDossier());
		    				if(mapHeureSupp25.containsKey(employe.getNumDossier()))
		    		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
		    		    	
		    		    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
		    		    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());
		    		    	
		    		    	detailprodGen.setEmploye(employe);
		    		    	detailprodGen.setDelaiEmploye(detaildelai);
		    		    	detailprodGen.setHeureSupp25(detailheur25);
		    		    	detailprodGen.setHeureSupp50(detailheur50);
		    		    	detailprodGen.setAbsent(absent);
		    		    	detailprodGen.setSortie(sortie);
		    		    	detailprodGen.setProductionGen(production);
					    	listeDetailProdGen.add(detailprodGen);
		    		
		    			}
		    			
		    			}
		    		}
		    	
		    	}
		     }
		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
		     		
		     		production.setListDetailProdGen(listeDetailProdGen);
		     		productionDAO.edit(production);
	}

void afficher_tableEmploye(List<Employe> listEmployer)
	{
initialiserTableauEmploye();
        boolean absent,sortie;
		String delai; 
		String heureSupp25;
		String heureSupp50;
		delai="";
		heureSupp25="";
		heureSupp50="";
		absent=false;
		sortie=false;
		
		  int i=0;
			while(i<listEmployer.size())
			{	
				Employe employer=listEmployer.get(i);
				
			//	mapEmployeGlobal.put(employer.getNumDossier(), employer);
				
				if(mapDelai!=null && mapDelai.size()>0 && mapDelai.containsKey(employer.getNumDossier()))
				{
					delai=String.valueOf(mapDelai.get(employer.getNumDossier())) ;
				}
				else{ 
					delai="";
				}
		
				if(mapHeureSupp25!=null && mapHeureSupp25.size()>0 && mapHeureSupp25.containsKey(employer.getNumDossier()))
				{
					heureSupp25=String.valueOf(mapHeureSupp25.get(employer.getNumDossier()));
				}
				else {
					heureSupp25="";
				}
				if(mapHeureSupp50!=null && mapHeureSupp50.size()>0 && mapHeureSupp50.containsKey(employer.getNumDossier()))
				{
					heureSupp50=String.valueOf(mapHeureSupp50.get(employer.getNumDossier()));
				}
				else {
					heureSupp50="";
				}
				if(mapEmployeAbsent!=null && mapEmployeAbsent.size()>0 && mapEmployeAbsent.containsKey(employer.getNumDossier()))
				{
					absent=mapEmployeAbsent.get(employer.getNumDossier());
				}
				else {
					absent=false;
				}
				
				if(mapEmployeSortie!=null && mapEmployeSortie.size()>0 && mapEmployeSortie.containsKey(employer.getNumDossier()))
				{
					sortie=mapEmployeSortie.get(employer.getNumDossier());
				}
				else {
					sortie=false;
				}
				
				Object []ligne={employer.getNumDossier(),employer.getMatricule(),employer.getNom(),delai,heureSupp25,heureSupp50,absent,sortie};

				modeleEmploye.addRow(ligne);
				i++;
			}
				
	
	}


void initialiserTableauEmploye(){
	

	 modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Dossier","Matricule", "Nom", "D�lai Travaill�", "H SuP 25%", "H SuP 50%","Absent","Sortie"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true,true,true,true
		     	};
		    
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class,String.class,String.class,String.class,String.class, Boolean.class, Boolean.class
					};
		      	
			       public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		     /*
		     tableEmployeFiltrer.setModel(new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     		"Num Dossier", "Matricule", "Nom", "D\u00E9lai Travaill\u00E9", "H SuP 25%", "H SuP 50%", "Absent"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     		false, false, false, true, true, true, true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     });
		     */
		     tableEmployeFiltrer.setModel(modeleEmploye);
		     tableEmployeFiltrer.getColumnModel().getColumn(0).setPreferredWidth(60);
		     tableEmployeFiltrer.getColumnModel().getColumn(1).setPreferredWidth(160);
		     tableEmployeFiltrer.getColumnModel().getColumn(2).setPreferredWidth(60);
		     tableEmployeFiltrer.getColumnModel().getColumn(3).setPreferredWidth(77);
		     tableEmployeFiltrer.getColumnModel().getColumn(4).setPreferredWidth(77);
		     tableEmployeFiltrer.getColumnModel().getColumn(5).setPreferredWidth(77);
		     tableEmployeFiltrer.getColumnModel().getColumn(6).setPreferredWidth(77);
		     tableEmployeFiltrer.getColumnModel().getColumn(7).setPreferredWidth(77);
		     tableEmployeFiltrer.getModel().addTableModelListener(new TableModelListener() {
		 
					@Override
					public void tableChanged(TableModelEvent e) {
						BigDecimal delai=BigDecimal.ZERO,heursup25=BigDecimal.ZERO,heursup50=BigDecimal.ZERO;
						boolean absent=false;
						boolean sortie=false;
						String numDossier= tableEmployeFiltrer.getValueAt(e.getFirstRow(),0).toString();
						if(!tableEmployeFiltrer.getValueAt(e.getFirstRow(),3).toString().equals(""))
						{
							 delai=new BigDecimal(tableEmployeFiltrer.getValueAt(e.getFirstRow(),3).toString());  
						}
						 if(!tableEmployeFiltrer.getValueAt(e.getFirstRow(), 4).toString().equals(""))
						 {
							 heursup25=new BigDecimal(tableEmployeFiltrer.getValueAt(e.getFirstRow(), 4).toString()); 
						 }
						  if(!tableEmployeFiltrer.getValueAt(e.getFirstRow(), 5).toString().equals(""))
						  {
							  heursup50=new BigDecimal(tableEmployeFiltrer.getValueAt(e.getFirstRow(), 5).toString());  
						  }
						
						  absent=Boolean.valueOf(tableEmployeFiltrer.getValueAt(e.getFirstRow(), 6).toString());
						  sortie=Boolean.valueOf(tableEmployeFiltrer.getValueAt(e.getFirstRow(), 7).toString());
						  if(absent==true)
							 {
								 mapEmployeAbsent.put(numDossier, absent);
							 	delai=BigDecimal.ZERO;heursup25=BigDecimal.ZERO;heursup50=BigDecimal.ZERO;
							 	
							 	if(!mapEmployeDelai.containsValue(mapEmployeGlobal.get(numDossier))){
							 		mapEmployeDelai.put(compteur, mapEmployeGlobal.get(numDossier));
							 		compteur++;
							 	}
					
								 
							 } else if(absent==false)
							 {
								 if(mapEmployeAbsent.containsKey(numDossier))
								 {
									 mapEmployeAbsent.remove(numDossier);
									
																	 
									}
								 	 if(delai.compareTo(BigDecimal.ZERO) >0 && !mapDelai.containsKey(numDossier)){
								 mapDelai.put(numDossier, delai); 
								 if(!mapEmployeDelai.containsValue(mapEmployeGlobal.get(numDossier))){
								 		mapEmployeDelai.put(compteur, mapEmployeGlobal.get(numDossier));
								 		compteur++;
								 		
								 		if(delai.compareTo(nbreHeure) <0)
											 mapEmployeSortie.put(numDossier, sortie);
										 else
											 mapEmployeSortie.remove(numDossier);
								 	}
							 } else {
								 
								 mapDelai.replace(numDossier, delai); 
								 
								 if(delai!=null ){
									 if(delai.compareTo(nbreHeure) <0)
										 mapEmployeSortie.replace(numDossier, sortie);
									 else
										 mapEmployeSortie.remove(numDossier);
									 }
								
								 if(heursup25.compareTo(BigDecimal.ZERO) >0 || heursup50.compareTo(BigDecimal.ZERO) >0){
									/* Employe employe =mapEmployeGlobal.get(e.getFirstRow());
									 String numDoss=employe.getNumDossier();*/
								 if(!mapHeureSupp25.containsKey(numDossier) ){
									 mapHeureSupp25.put(numDossier, heursup25);
									
								 }else {
									 mapHeureSupp25.replace(numDossier, heursup25);
								 }
								 
								 if(! mapHeureSupp50.containsKey(numDossier)) {
									 
									 mapHeureSupp50.put(numDossier, heursup50);
									 
								 } else {
									 
									 mapHeureSupp50.replace(numDossier, heursup50);
									 
								 }
								
							 }
								 
							 }
								 
							 }
							  
						
							  
						}
					});
			  				
}

void remplirMapEmployeGlobal(){
	

		for(int i=0;i<listEmployer.size();i++)
		{	
			Employe employer=listEmployer.get(i);
			
			mapEmployeGlobal.put(employer.getNumDossier(), employer);
			
		}
	
	
}
}

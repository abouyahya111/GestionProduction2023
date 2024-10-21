package ProductionCarton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.Employe;
import dao.entity.Production;
import dao.entity.ProductionMP;


public class ListeEmployeProdCarton extends JFrame implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	
	private JXTable tableEmployeFiltrer_1;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	

	
	private ProductionMP productionMP = new ProductionMP();
	private List<Employe> listEmployer =new ArrayList<Employe>();
	private List<DetailProductionMP> listDetailProduction =new ArrayList<DetailProductionMP>();
	
	
	private Map< String, BigDecimal> mapDelai = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp25 = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp50 = new HashMap<>();
	private Map< String,Boolean> mapEmployeAbsent = new HashMap<>();
	private Map< String,Boolean> mapEmployeSortie = new HashMap<>();
	private Map< String,Boolean> mapEmployeRetard = new HashMap<>();
	
	private Map< Integer, Employe> mapEmployeDelai = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp25 = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp50 = new HashMap<>();
	
	
	private Map< String, Employe> mapEmployeGlobal = new HashMap<>();
	private List <Integer> lsiteInt=new ArrayList<Integer>();
	
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private EmployeDAO employeDAO;
	private ProductionMPDAO productionMPDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private CompteurAbsenceEmployeDAO compteurAbsenceEmployeDAO;
	
	private JLabel lblMatricule;
	private JLabel lblNom;
	private JLabel lblNumDossier;
	
	private JTextField matricule;
	private JTextField nom;
	private JTextField numdossier;
	
	private int selectedRow ;
	
	private int compteur=0;
	String quantite;
	String nbreHeure;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public ListeEmployeProdCarton(final ProductionMP productionParame, final String quantiteParam, final String nbreHeureParam) {
		
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
        	tableEmployeFiltrer_1.setSortable(false);
        	compteur=0;
        	
        	listEmployer =new ArrayList<Employe>();
        	
        	tableEmploye=new JXTable();
        	
        	 DefaultCellEditor ce = (DefaultCellEditor) tableEmployeFiltrer_1.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	productionMP=productionParame;
        	quantite=quantiteParam;
        	nbreHeure=nbreHeureParam;
        	employeDAO=new EmployeDAOImpl();
        	productionMPDAO=new ProductionMPDAOImpl();
        	compteurAbsenceEmployeDAO=new CompteurAbsenceEmployeDAOImpl();
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

        initialiserTableauEmploye();
        
		
             listEmployer=employeDAO.findByDepot(productionParame.getCodeDepot());
             
             remplirMapEmployeGlobal();
		     afficher_tableEmploye(listEmployer);
				  	
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		     getContentPane().setLayout(null);
				  		 
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableEmployeFiltrer_1);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 945, 232);
				  		     getContentPane().add(scrollPane);
				  		     
				  		     JButton btnValider = new JButton();
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		afficher_detailproduction();

				  		     		Main.contentPane=new TerminerOrdreFabricationMP(productionMP,quantite,nbreHeure);
				  		     		
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
				  		     
				  		     JLabel lblNewLabel = new JLabel("Employ\u00E9es Production Carton");
				  		     lblNewLabel.setForeground(Color.BLUE);
				  		     lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				  		     lblNewLabel.setBounds(10, 11, 241, 24);
				  		     getContentPane().add(lblNewLabel);
	//	listDetailProdGen=detailProdGenDAO.findByDateProdPeriode(production.getDate(), production.getPeriode());
				  		   
				  		     
				  		    /*
				  		   ListSelectionModel cellSelectionModel = tableEmployeFiltrer.getSelectionModel();
				  		    cellSelectionModel.setSelectionMode(ListDataEvent.CONTENTS_CHANGED);
				  		  final int k=0;
				  		  
				  		tableEmployeFiltrer.addMouseListener(new MouseAdapter() {
			        		@Override
			        		public void mouseClicked(MouseEvent arg0) {
			        			
			        			  selectedRow = tableEmployeFiltrer.getSelectedRow();
			        			  
			        			  
			        		
			        			
			        			
			        		}
			        	});
				  		 
				  		  cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				  		
							@Override
							public void valueChanged(ListSelectionEvent arg0) {
								 BigDecimal selectedData ;
								 
								 
								 tableEmployeFiltrer.addKeyListener(new java.awt.event.KeyAdapter() {

									 public void keyReleased(java.awt.event.KeyEvent ke){
									 if(ke. ()==KeyEvent.VK_ENTER){
										 
										 if(selectedRow>=0){
											 String numDossier= tableEmployeFiltrer.getValueAt(selectedRow,0).toString();
											 BigDecimal delai=BigDecimal.valueOf(tableEmployeFiltrer.getValueAt(selectedRow,3).toString());  
											 BigDecimal heursup25=BigDecimal.valueOf(tableEmployeFiltrer.getValueAt(selectedRow, 4).toString());
											 BigDecimal heursup50=BigDecimal.valueOf(tableEmployeFiltrer.getValueAt(selectedRow, 5).toString());
																			 mapDelai.put(numDossier, delai); 
											 mapHeureSupp25.put(numDossier, heursup25);
											 mapHeureSupp50.put(numDossier, heursup50);
											 
											 mapEmployeDelai.put(selectedRow, mapEmployeGlobal.get(numDossier));
											 lsiteInt.add(selectedRow);
											// k++;
										 }

									 
									 
									 }
									 }
								 });
								 
							}
						});
				  		  
				  	
				  	 */
				  		
				  		 //  modeleEmploye=(DefaultTableModel) tableEmployeFiltrer.getModel();
				}
	
	void afficher_detailproduction()
	{
		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
		boolean absent=false;
   		int idEmploye;
   		boolean sortie=false;
   		boolean retard=false;
    	
		    for(int i=0;i<mapEmployeDelai.size();i++)
		    {
		    	DetailProductionMP detailproductionMP=new DetailProductionMP();
		    	detaildelai=BigDecimal.ZERO;
		    	detailheur25=BigDecimal.ZERO;
		    	detailheur50=BigDecimal.ZERO;
		    	absent=false;
		    	sortie=false;
		    	retard=false;
		    //	int key=lsiteInt.get(i);
		    	Employe employe=mapEmployeDelai.get(i);
		    	
		    	if(employe!=null)
		    	{
		    		idEmploye=employe.getId();
		    	
		   
		    	if(mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	{
		    		
		    		/*
		    		if(mapDelai.containsKey(employe.getNumDossier()))
		    		detaildelai=mapDelai.get(employe.getNumDossier());
		    	
		    	if(mapHeureSupp25.containsKey(employe.getNumDossier()))
		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
		    	
		    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
		    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());
		    		*/
		    	
		    	if(mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	absent=mapEmployeAbsent.get(employe.getNumDossier());
		    	
		    	
		    	detailproductionMP.setEmploye(employe);
		    	detailproductionMP.setDelaiEmploye(detaildelai);
		    	detailproductionMP.setHeureSupp25(detailheur25);
		    	detailproductionMP.setHeureSupp50(detailheur50);
		    	detailproductionMP.setAbsent(absent);
		    	detailproductionMP.setProductionMP(productionMP);
		    	listDetailProduction.add(detailproductionMP);
		    		
		    	}
		    	
		    	else if(!mapEmployeAbsent.containsKey(employe.getNumDossier()))
		    	{
		    	
		    		if(mapDelai.containsKey(employe.getNumDossier()))
		    		{
		    			
		    			if(mapDelai.get(employe.getNumDossier()).compareTo(BigDecimal.ZERO) >0)
		    			{
		    				
		    				if(mapEmployeSortie.containsKey(employe.getNumDossier()))
		    					sortie=mapEmployeSortie.get(employe.getNumDossier());
		    				detaildelai=mapDelai.get(employe.getNumDossier());
		    				if(mapHeureSupp25.containsKey(employe.getNumDossier()))
		    		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
		    		    	
		    		    	if(mapHeureSupp50.containsKey(employe.getNumDossier()))
		    		    		detailheur50=mapHeureSupp50.get(employe.getNumDossier());
		    		    	
		    		    	detailproductionMP.setEmploye(employe);
		    		    	detailproductionMP.setDelaiEmploye(detaildelai);
		    		    	detailproductionMP.setHeureSupp25(detailheur25);
		    		    	detailproductionMP.setHeureSupp50(detailheur50);
		    		    	detailproductionMP.setAbsent(absent);
		    		    	detailproductionMP.setSortie(sortie);
		    		    	detailproductionMP.setProductionMP(productionMP);
					    	listDetailProduction.add(detailproductionMP);
		    		
		    			}
		    			
		    			}else {
		    				
		    				if(mapHeureSupp25.containsKey(employe.getNumDossier())){
		    		    		detailheur25=mapHeureSupp25.get(employe.getNumDossier());
		    		    		
		    		    		detailproductionMP.setEmploye(employe);
		    		    		detailproductionMP.setDelaiEmploye(detaildelai);
		    		    		detailproductionMP.setHeureSupp25(detailheur25);
		    		    		detailproductionMP.setHeureSupp50(detailheur50);
		    		    		detailproductionMP.setAbsent(absent);
		    		    		detailproductionMP.setProductionMP(productionMP);
						    	listDetailProduction.add(detailproductionMP);
		    		    		
		    				}
		    				
		    				if(mapHeureSupp50.containsKey(employe.getNumDossier())){
		    					detailheur50=mapHeureSupp50.get(employe.getNumDossier());
		    					detailproductionMP.setEmploye(employe);
		    					detailproductionMP.setDelaiEmploye(detaildelai);
		    					detailproductionMP.setHeureSupp25(detailheur25);
		    					detailproductionMP.setHeureSupp50(detailheur50);
		    					detailproductionMP.setAbsent(absent);
		    					detailproductionMP.setProductionMP(productionMP);
						    	listDetailProduction.add(detailproductionMP);
		    					
		    				}
		    		    		
		    				
		    				
		    				
		    			}
		    		}
		    	
		    	}
		     }
		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
		     		
		     		productionMP.setDetailProductionsMP(listDetailProduction);
		     		productionMPDAO.edit(productionMP);
		  
	}

void afficher_tableEmploye(List<Employe> listEmployer)
	{
	initialiserTableauEmploye();	
	boolean absent,sortie;
		BigDecimal delai; 
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;
		delai=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		absent=false;
		sortie=false;
		
		  int i=0;
			while(i<listEmployer.size())
			{	
				Employe employer=listEmployer.get(i);
				
				//mapEmployeGlobal.put(employer.getNumDossier(), employer);
				
				if(mapDelai!=null && mapDelai.size()>0 && mapDelai.containsKey(employer.getNumDossier()))
				{
					delai=mapDelai.get(employer.getNumDossier());
				}
				else{ 
					delai=BigDecimal.ZERO;
				}
								
				if(mapHeureSupp25!=null && mapHeureSupp25.size()>0 && mapHeureSupp25.containsKey(employer.getNumDossier()))
				{
					heureSupp25=mapHeureSupp25.get(employer.getNumDossier());
				}
				else {
					heureSupp25=BigDecimal.ZERO;
				}
				if(mapHeureSupp50!=null && mapHeureSupp50.size()>0 && mapHeureSupp50.containsKey(employer.getNumDossier()))
				{
					heureSupp50=mapHeureSupp50.get(employer.getNumDossier());
				}
				else {
					heureSupp50=BigDecimal.ZERO;
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
		     			"Num Dossier","Matricule", "Nom", "Délai Travaillé", "H SuP 25%", "H SuP 50%","Absent","Sortie","Retard"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true,true,true,true,true
		     	};
		    
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class, Boolean.class
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
		     tableEmployeFiltrer_1.getColumnModel().getColumn(1).setPreferredWidth(160);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(2).setPreferredWidth(60);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(3).setPreferredWidth(77);
		     tableEmployeFiltrer_1.getColumnModel().getColumn(4).setPreferredWidth(77);
		     tableEmployeFiltrer_1.getModel().addTableModelListener(new TableModelListener() {
					
					@Override
					public void tableChanged(TableModelEvent e) {
						BigDecimal delai=BigDecimal.ZERO,heursup25=BigDecimal.ZERO,heursup50=BigDecimal.ZERO;
						boolean absent=false;
						boolean sortie=false;
						boolean retard=false;
						String numDossier= tableEmployeFiltrer_1.getValueAt(e.getFirstRow(),0).toString();
						  delai=new BigDecimal(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(),3).toString());  
						  heursup25=new BigDecimal(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(), 4).toString());
						  heursup50=new BigDecimal(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(), 5).toString());
						  absent=Boolean.valueOf(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(), 6).toString());
						  sortie=Boolean.valueOf(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(), 7).toString());
						  retard=Boolean.valueOf(tableEmployeFiltrer_1.getValueAt(e.getFirstRow(), 8).toString());
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
							 		
							 		if(delai.compareTo(new BigDecimal(nbreHeure))<0)
										 mapEmployeSortie.put(numDossier, sortie);
									 else
										 mapEmployeSortie.remove(numDossier);
							 		
							 		
							 	}
						 } else if(delai.compareTo(BigDecimal.ZERO)>0) {
							 
							 mapDelai.replace(numDossier, delai); 
							 
							 if(delai!=null ){
								 if(delai.compareTo(new BigDecimal(nbreHeure)) <0)
									 mapEmployeSortie.replace(numDossier, sortie);
								 else
									 mapEmployeSortie.remove(numDossier);
								 }
							 
							
							 if(heursup25.compareTo(BigDecimal.ZERO) >0 || heursup50.compareTo(BigDecimal.ZERO) > 0){
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
							 
						 }else if(!mapDelai.containsKey(numDossier)){
							 
							 if(heursup25.compareTo(BigDecimal.ZERO)>0){
								 
								 mapHeureSupp25.put(numDossier, heursup25);
								 
								 if(!mapEmployeDelai.containsValue(mapEmployeGlobal.get(numDossier))){
								 		mapEmployeDelai.put(compteur, mapEmployeGlobal.get(numDossier));
								 		compteur++;
								 	}
								 
							 }
							 
							 	if(heursup50.compareTo(BigDecimal.ZERO)>0){
								 
								 mapHeureSupp50.put(numDossier, heursup50);
								 
								 if(!mapEmployeDelai.containsValue(mapEmployeGlobal.get(numDossier))){
								 		mapEmployeDelai.put(compteur, mapEmployeGlobal.get(numDossier));
								 		compteur++;
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

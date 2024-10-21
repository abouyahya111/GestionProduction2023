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

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
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
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;


public class SaisieDelaiEquipeGen extends JFrame implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	private JButton btnAjoutChefProd;
	private JButton btnSuppchefprod;
	
	private JComboBox<String> comboMachine;
	private  JComboBox<String> comboLigneMachine;
	private JComboBox comboChefProduction ;
	private JComboBox comboAuteOuvrier;
	private	JComboBox comboAideTechnicien ;
	private	JComboBox comboTechnicien ;
	private	 JComboBox comboChefEquipe ;
	
	private Production production = new Production();
	private List<Employe> listEmploye =new ArrayList<Employe>();
	private List<DetailResponsableProd> listDetailResponsableProd=new ArrayList<DetailResponsableProd>();
	
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapNote = new HashMap<>();
	private Map< Integer, String> mapMotifEmploye = new HashMap<>();
	private Map< Integer, String> mapHeureSupp25 = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50 = new HashMap<>();
	private Map< String, DetailResponsableProd> mapDetailResponProd = new HashMap<>();
	
	private Map< String, Employe> mapEmployeTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeAideTechnicien = new HashMap<>();
	private Map< String, Employe> mapEmployeChefProd = new HashMap<>();
	private Map< String, Employe> mapEmployeChefEquipe = new HashMap<>();
	private Map< String, Employe> mapListEmployeResponsable = new HashMap<>();
	private Map< String, Employe> mapAutreEmploye = new HashMap<>();
	
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private EmployeDAO employeDAO;
	private ProductionDAO productionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public SaisieDelaiEquipeGen(final Production productionParame) {
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1000, 423);
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
        	
        	tableEmploye=new JXTable();
        	 DefaultCellEditor ce = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	comboChefProduction = new JComboBox();
        	comboAuteOuvrier = new JComboBox();
        	comboChefEquipe = new JComboBox();
        	comboTechnicien = new JComboBox();
        	comboAideTechnicien = new JComboBox();
        	mapEmployeChefProd = new HashMap<>();
        	listEmploye =new ArrayList<Employe>();
        	mapListEmployeResponsable = new HashMap<>();
        	listDetailResponsableProd=new ArrayList<DetailResponsableProd>();

        	production=productionParame;
        	employeDAO=new EmployeDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	compteurResponsableProdDAO= new CompteurResponsableProdDAOImpl();
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	compteurEmployeProdDAO= new CompteurEmployeProdDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupp1= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
        comboChefProduction.addItem("");
        comboAideTechnicien.addItem("");
  		comboTechnicien.addItem("");
  		comboChefEquipe.addItem("");
  		comboAuteOuvrier.addItem("");
        initialiserTableauEmploye();
		
				  		  btnImprimer = new JButton("Imprimer");
				  		  btnImprimer.setVisible(false);
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		
				  		  	}
				  		  });
				  		 listEmploye = employeDAO.findEmployeByType(EMPLOYE_RESPONSABILITE_TECHNITIEN,production.getCodeDepot());	
				  		   
			  		   int   i=0;
			  		      	while(i<listEmploye.size())
			  		      		{	
			  		      			Employe employe=listEmploye.get(i);
			  		      			mapEmployeTechnicien.put(employe.getNom(), employe);
			  		      			comboTechnicien.addItem(employe.getNom());
			  		      			i++;
			  		      		}
			  		      listEmploye = employeDAO.findEmployeByType(EMPLOYE_RESPONSABILITE_AIDE_TECHNITIEN,production.getCodeDepot());	
				  		   
			  		      i=0;
			  		      	while(i<listEmploye.size())
			  		      		{	
			  		      			Employe employe=listEmploye.get(i);
			  		      			mapEmployeAideTechnicien.put(employe.getNom(), employe);
			  		      			comboAideTechnicien.addItem(employe.getNom());
			  		      			i++;
			  		      		}
			  		      listEmploye = employeDAO.findEmployeByType(TYPE_EMPLOYE_RESPONSABLE_EQUIPE,production.getCodeDepot());	
				  		   
			  		      i=0;
			  		      	while(i<listEmploye.size())
			  		      		{	
			  		      			Employe employe=listEmploye.get(i);
			  		      			mapEmployeChefEquipe.put(employe.getNom(), employe);
			  		      			comboChefEquipe.addItem(employe.getNom());
			  		      			i++;
			  		      		}
			  		      	
			  		      listEmploye = employeDAO.findEmployeByType(TYPE_EMPLOYE_CHEF_PRODUCTION,production.getCodeDepot());	
				  		   
			  		      i=0;
			  		      	while(i<listEmploye.size())
			  		      		{	
			  		      			Employe employe=listEmploye.get(i);
			  		      			mapEmployeChefProd.put(employe.getNom(), employe);
			  		      			comboChefProduction.addItem(employe.getNom());
			  		      			i++;
			  		      		}
			  		      	
			  		      listEmploye = employeDAO.findAutreEmployeByType(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION);	
				  		   
			  		      i=0;
			  		      	while(i<listEmploye.size())
			  		      		{	
			  		      			Employe employe=listEmploye.get(i);
			  		      			mapAutreEmploye.put(employe.getNom(), employe);
			  		      			comboAuteOuvrier.addItem(employe.getNom());
			  		      			i++;
			  		      		}
				  		  
				  		modeleEmploye =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"ID","Matricule","Nom", "Responsabilité", "Délai travillé"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false,false, true
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
					  		getContentPane().setLayout(null);
					  		btnImprimer.setIcon(imgModifier);
					  		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
					  		btnImprimer.setBounds(506, 350, 112, 24);
					  		getContentPane().add(btnImprimer);
				  		 
				  	
				  		    
				  		    btnValiderDelai = new JButton("Valider ");
				  		    btnValiderDelai.setBounds(257, 350, 112, 24);
				  		    getContentPane().add(btnValiderDelai);
				  		    btnValiderDelai.setIcon(imgAjouter);
				  		    btnValiderDelai.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		int compteur =0;
				  		     		/* délai des employés */
				  		     		for(int j=0;j<tableEmploye.getRowCount();j++){
				  		     			if(tableEmploye.getValueAt(j, 4).toString().equals(""))
				  		     				compteur++;
				  		     			else 	
				  		     			mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 4).toString());
				  		     			if(tableEmploye.getValueAt(j, 5).toString().equals(""))
				  		     				compteur++;
				  		     			else 	
				  		     			mapNote.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 5).toString());

				  		     			if(tableEmploye.getValueAt(j, 6)==null)
				  		     				mapMotifEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), "");
				  		     			else 	
				  		     				mapMotifEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 6).toString());
				  		     			
				  		     			if(tableEmploye.getValueAt(j, 7)==null)
				  		     				mapHeureSupp25.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), "");
				  		     			else 	
				  		     				mapHeureSupp25.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 7).toString());
				  		     			
				  		     			if(tableEmploye.getValueAt(j, 8)==null)
				  		     				mapHeureSupp50.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), "");
				  		     			else 	
				  		     				mapHeureSupp50.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 8).toString());
				  		     		}
				  		     		if(compteur!=0)
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir tous les "+compteur+ "délais restants!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else {
				  		     			
				  		     			calculeCoutEmployeGenerique(listDetailResponsableProd,mapDelaiEmploye);
				  		     			JOptionPane.showMessageDialog(null, "Délais saisies avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     			
				  		     			dispose();
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     	}
				  		     });
				  		    
				  		    /*##################################################################
				  		     * POUR AJOUTER LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		    
				  		  listDetailResponsableProd=production.getListDetailResponsableProd();
				  		  
				  		  for( i=0;i<listDetailResponsableProd.size();i++){
				  			DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
				  			  mapListEmployeResponsable.put(detailResponsableProd.getEmploye().getNom(),detailResponsableProd.getEmploye());
				  		  }
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		    btnValiderDelai.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnAnnulerOF = new JButton("Annuler OF");
				  		     btnAnnulerOF.setBounds(374, 350, 122, 24);
				  		     getContentPane().add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableEmploye);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 945, 232);
				  		     getContentPane().add(scrollPane);
				  		     
				  		     
				  		     comboChefProduction.setBounds(94, 10, 153, 24);
				  		     getContentPane().add(comboChefProduction);
				  		     
				  		     JLabel lblChefProduction = new JLabel("Chef Production");
				  		     lblChefProduction.setBounds(10, 11, 200, 22);
				  		     getContentPane().add(lblChefProduction);
				  		     
				  		     btnAjoutChefProd = new JButton();
				  		     btnAjoutChefProd.setIcon(imgAjouter);
				  		     btnAjoutChefProd.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {

				  		     		ajoutChefProduction();
						  		   
				  		     		afficher_tableEmploye(listDetailResponsableProd);
					  		   	
				  		     	}
				  		     });
				  		     btnAjoutChefProd.setBounds(248, 10, 54, 24);
				  		     getContentPane().add(btnAjoutChefProd);
				  		     
				  		     btnSuppchefprod = new JButton();
				  		   btnSuppchefprod.setIcon(imgSupp1);
				  		     btnSuppchefprod.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		mapListEmployeResponsable.remove(comboChefProduction.getSelectedItem());
						  		   	listDetailResponsableProd.remove(mapDetailResponProd.get(comboChefProduction.getSelectedItem()));
						  		   	afficher_tableEmploye(listDetailResponsableProd);
						  		//   	JOptionPane.showMessageDialog(null, "Employé Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE); 
						  		  
				  		     	}
				  		     });
				  		     btnSuppchefprod.setBounds(302, 10, 54, 24);
				  		     getContentPane().add(btnSuppchefprod);
				  		     
				  		     JLabel lblChefEquipe = new JLabel("Chef Equipe");
				  		     lblChefEquipe.setBounds(10, 52, 200, 22);
				  		     getContentPane().add(lblChefEquipe);
				  		     
				  		    
				  		     comboChefEquipe.setBounds(91, 51, 153, 24);
				  		     getContentPane().add(comboChefEquipe);
				  		     
				  		     JButton btnAjoutChefEquipe = new JButton();
				  		   btnAjoutChefEquipe.setIcon(imgAjouter);
				  		   btnAjoutChefEquipe.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		ajoutChefEquipe();
				  		   		afficher_tableEmploye(listDetailResponsableProd);
				  		   	}
				  		   });
				  		     btnAjoutChefEquipe.setBounds(248, 50, 54, 24);
				  		     getContentPane().add(btnAjoutChefEquipe);
				  		     
				  		     JButton btnSuppChefEquipe = new JButton();
				  		   btnSuppChefEquipe.setIcon(imgSupp1); 
				  		   btnSuppChefEquipe.addActionListener(new ActionListener() {
					  		   	public void actionPerformed(ActionEvent e) {
					  		   	mapListEmployeResponsable.remove(comboChefEquipe.getSelectedItem());
					  		   	listDetailResponsableProd.remove(comboChefEquipe.getSelectedItem());
					  		   	afficher_tableEmploye(listDetailResponsableProd);
					  		   	}
					  		   });
				  		     
				  		     
				  		     
				  		     btnSuppChefEquipe.setBounds(302, 50, 54, 24);
				  		     getContentPane().add(btnSuppChefEquipe);
				  		     
				  		     JLabel lblTechnicien = new JLabel("Technicien");
				  		     lblTechnicien.setBounds(386, 11, 160, 22);
				  		     getContentPane().add(lblTechnicien);
				  		     
				  		    
				  		     comboTechnicien.setBounds(460, 10, 154, 24);
				  		     getContentPane().add(comboTechnicien);
				  		     
				  		   
				  		     comboAideTechnicien.setBounds(460, 51, 154, 24);
				  		     getContentPane().add(comboAideTechnicien);
				  		     
				  		     JLabel lblAideTechnicien = new JLabel("Aide Technicien");
				  		     lblAideTechnicien.setBounds(374, 52, 200, 22);
				  		     getContentPane().add(lblAideTechnicien);
				  		     
				  		     JButton btnSuppTec = new JButton();
				  		     btnSuppTec.setIcon(imgSupp1); 
				  		     btnSuppTec.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	mapListEmployeResponsable.remove(comboTechnicien.getSelectedItem());
				  		   	listDetailResponsableProd.remove(comboTechnicien.getSelectedItem());
				  		   	afficher_tableEmploye(listDetailResponsableProd);
				  		   	}
				  		   });
				  		     btnSuppTec.setBounds(670, 10, 54, 24);
				  		     getContentPane().add(btnSuppTec);
				  		     
				  		     JButton btnAjoutTec = new JButton();
				  		     btnAjoutTec.setIcon(imgAjouter);
				  		     btnAjoutTec.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	ajoutTechnicien();
				  		   	afficher_tableEmploye(listDetailResponsableProd);
				  		   	}
				  		   });
				  		     
				  		     btnAjoutTec.setBounds(616, 10, 54, 24);
				  		     getContentPane().add(btnAjoutTec);
				  		     
				  		     JButton btnSuppAideTech = new JButton();
				  		   btnSuppAideTech.setIcon(imgSupp1); 
				  		   btnSuppAideTech.addActionListener(new ActionListener() {
					  		   	public void actionPerformed(ActionEvent e) {
					  		   	mapListEmployeResponsable.remove(comboAideTechnicien.getSelectedItem());
					  		   	listDetailResponsableProd.remove(comboAideTechnicien.getSelectedItem());
					  		  afficher_tableEmploye(listDetailResponsableProd);
					  		   	}
					  		   });
				  		     btnSuppAideTech.setBounds(670, 50, 54, 24);
				  		     getContentPane().add(btnSuppAideTech);
				  		     
				  		     JButton btnAjoutAideTech = new JButton();
				  		   btnAjoutAideTech.setIcon(imgAjouter);
				  		   btnAjoutAideTech.addActionListener(new ActionListener() {
					  		   	public void actionPerformed(ActionEvent e) {
					  		   		ajoutAideTechnicien();
					  		   	afficher_tableEmploye(listDetailResponsableProd);
					  		   	}
					  		   });
				  		     
				  		     btnAjoutAideTech.setBounds(616, 50, 54, 24);
				  		     getContentPane().add(btnAjoutAideTech);
				  		     
				  		     JLabel lblAutreOuvrier = new JLabel("Autre Ouvrier");
				  		     lblAutreOuvrier.setBounds(734, 10, 200, 22);
				  		     getContentPane().add(lblAutreOuvrier);
				  		     
				  		  
				  		     comboAuteOuvrier.setBounds(802, 10, 153, 24);
				  		     getContentPane().add(comboAuteOuvrier);
				  		     
				  		     JButton btnSuppAutreOuv = new JButton();
				  		   btnSuppAutreOuv.setIcon(imgSupp1);
				  		     btnSuppAutreOuv.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		mapListEmployeResponsable.remove(comboAuteOuvrier.getSelectedItem());
						  		   	listDetailResponsableProd.remove(comboAuteOuvrier.getSelectedItem());
						  		   	afficher_tableEmploye(listDetailResponsableProd);
				  		     		
				  		     	}
				  		     });
				  		     btnSuppAutreOuv.setBounds(876, 43, 54, 24);
				  		     getContentPane().add(btnSuppAutreOuv);
				  		     
				  		     JButton btnAjoutAutreOuv = new JButton();
				  		   btnAjoutAutreOuv.setIcon(imgAjouter);
				  		     btnAjoutAutreOuv.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		ajoutAutreOuvrier();
				  		     		afficher_tableEmploye(listDetailResponsableProd);
				  		     	}
				  		     });
				  		     btnAjoutAutreOuv.setBounds(812, 43, 54, 24);
				  		     getContentPane().add(btnAjoutAutreOuv);
	//	listDetailProdGen=detailProdGenDAO.findByDateProdPeriode(production.getDate(), production.getPeriode());
				  		   afficher_tableEmploye(listDetailResponsableProd);
				  		
	}
	
	

	

	

void afficher_tableEmploye(List<DetailResponsableProd> listDetailResponsableProd)
	{
	initialiserTableauEmploye();
		BigDecimal delai; 
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;
		int note;
		String motif;
		  int i=0;
			while(i<listDetailResponsableProd.size())
			{	
				DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
				delai=detailResponsableProd.getDelaiEmploye();
				note=detailResponsableProd.getNote();
				motif=detailResponsableProd.getMotif();
				heureSupp25=detailResponsableProd.getHeureSupp25();
				heureSupp50=detailResponsableProd.getHeureSupp50();
				Object []ligne={detailResponsableProd.getEmploye().getId(),detailResponsableProd.getEmploye().getMatricule(),detailResponsableProd.getEmploye().getNom(),detailResponsableProd.getEmploye().getResponsabilite(),delai,note,motif,heureSupp25,heureSupp50};

				modeleEmploye.addRow(ligne);
				i++;
			}
	}

void ajoutTechnicien(){
	 

		if(mapListEmployeResponsable.get(comboTechnicien.getSelectedItem())==null){
				DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
				detailResponsableProd.setEmploye(mapEmployeTechnicien.get(comboTechnicien.getSelectedItem()));
				detailResponsableProd.setProduction(production);
				detailResponsableProd.setNote(1);
				listDetailResponsableProd.add(detailResponsableProd);
				mapListEmployeResponsable.put(comboTechnicien.getSelectedItem().toString(), mapEmployeTechnicien.get(comboTechnicien.getSelectedItem()));
	//	JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else 	if((mapListEmployeResponsable.get(comboTechnicien.getSelectedItem())).equals(mapEmployeTechnicien.get(comboTechnicien.getSelectedItem()))){
		JOptionPane.showMessageDialog(null, "Cet Employé est déjà ajouté ", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else {
		mapListEmployeResponsable.put(comboTechnicien.getSelectedItem().toString(), mapEmployeTechnicien.get(comboTechnicien.getSelectedItem()));
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeTechnicien.get(comboTechnicien.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		
		//	JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
}		
void ajoutAutreOuvrier(){
			 

			if(mapListEmployeResponsable.get(comboAuteOuvrier.getSelectedItem())==null){
			DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
				detailResponsableProd.setEmploye(mapAutreEmploye.get(comboAuteOuvrier.getSelectedItem()));
				detailResponsableProd.setProduction(production);
				detailResponsableProd.setNote(1);
				listDetailResponsableProd.add(detailResponsableProd);
				mapListEmployeResponsable.put(comboAuteOuvrier.getSelectedItem().toString(), mapAutreEmploye.get(comboAuteOuvrier.getSelectedItem()));
		//	JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			else 	if((mapListEmployeResponsable.get(comboAuteOuvrier.getSelectedItem())).equals(mapAutreEmploye.get(comboAuteOuvrier.getSelectedItem()))){
			JOptionPane.showMessageDialog(null, "Cet Employé est déjà ajouté ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
			mapListEmployeResponsable.put(comboAuteOuvrier.getSelectedItem().toString(), mapAutreEmploye.get(comboAuteOuvrier.getSelectedItem()));
				DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
				detailResponsableProd.setEmploye(mapAutreEmploye.get(comboAuteOuvrier.getSelectedItem()));
				detailResponsableProd.setProduction(production);
				detailResponsableProd.setNote(1);
				listDetailResponsableProd.add(detailResponsableProd);
			//	JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
	
 
}


void ajoutAideTechnicien(){
 

		if(mapListEmployeResponsable.get(comboAideTechnicien.getSelectedItem())==null){
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeAideTechnicien.get(comboAideTechnicien.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		mapListEmployeResponsable.put(comboAideTechnicien.getSelectedItem().toString(), mapEmployeAideTechnicien.get(comboAideTechnicien.getSelectedItem()));
	//		JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
		else 	if((mapListEmployeResponsable.get(comboAideTechnicien.getSelectedItem())).equals(mapEmployeAideTechnicien.get(comboAideTechnicien.getSelectedItem()))){
		JOptionPane.showMessageDialog(null, "Cet Employé est déjà ajouté ", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else {
		mapListEmployeResponsable.put(comboAideTechnicien.getSelectedItem().toString(), mapEmployeAideTechnicien.get(comboAideTechnicien.getSelectedItem()));
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeAideTechnicien.get(comboAideTechnicien.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		//		JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
	
 
}


void ajoutChefProduction(){
 

		if(mapListEmployeResponsable.get(comboChefProduction.getSelectedItem())==null){
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeChefProd.get(comboChefProduction.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
			mapListEmployeResponsable.put(comboChefProduction.getSelectedItem().toString(), mapEmployeChefProd.get(comboChefProduction.getSelectedItem()));
		//JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
		else 	if((mapListEmployeResponsable.get(comboChefProduction.getSelectedItem())).equals(mapEmployeChefProd.get(comboChefProduction.getSelectedItem()))){
		JOptionPane.showMessageDialog(null, "Cet Employé est déjà ajouté ", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else {
		mapListEmployeResponsable.put(comboChefProduction.getSelectedItem().toString(), mapEmployeChefProd.get(comboChefProduction.getSelectedItem()));
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeChefProd.get(comboChefProduction.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		//	JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
	
 
}


void ajoutChefEquipe(){
 

		if(mapListEmployeResponsable.get(comboChefEquipe.getSelectedItem())==null){
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeChefEquipe.get(comboChefEquipe.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		mapListEmployeResponsable.put(comboChefEquipe.getSelectedItem().toString(), mapEmployeChefEquipe.get(comboChefEquipe.getSelectedItem()));
	//		JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
		else 	if((mapListEmployeResponsable.get(comboChefEquipe.getSelectedItem())).equals(mapEmployeChefEquipe.get(comboChefEquipe.getSelectedItem()))){
		JOptionPane.showMessageDialog(null, "Cet Employé est déjà ajouté ", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else {
		mapListEmployeResponsable.put(comboChefEquipe.getSelectedItem().toString(), mapEmployeChefEquipe.get(comboChefEquipe.getSelectedItem()));
		DetailResponsableProd detailResponsableProd=new DetailResponsableProd();
			detailResponsableProd.setEmploye(mapEmployeChefEquipe.get(comboChefEquipe.getSelectedItem()));
			detailResponsableProd.setProduction(production);
			detailResponsableProd.setNote(1);
			listDetailResponsableProd.add(detailResponsableProd);
		//		JOptionPane.showMessageDialog(null, "Employé ajouté ", "Succès", JOptionPane.INFORMATION_MESSAGE);
		}
	
 
}
	
void calculeCoutEmployeGenerique(List<DetailResponsableProd> listDetailResponsableProd,Map< Integer, String> mapDelaiEmploye){
	
	 CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
	 int compteurProd=compteurProduction.getCompteur();
	 int compteur=0;
	//	BigDecimal delai=0;
		BigDecimal delaiComplet=BigDecimal.ZERO;
		int note;
		String motif;
		BigDecimal heureSupp25=BigDecimal.ZERO;
		BigDecimal heureSupp50=BigDecimal.ZERO;
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
	//	BigDecimal coutHoraire=0;
		BigDecimal coutHoraireComplet=BigDecimal.ZERO;
		BigDecimal coutHoraireTotal=BigDecimal.ZERO;
		//List<DetailResponsableProd> listDetailResponsableProdTmp = new ArrayList<DetailResponsableProd>();
		for(int i=0;i<listDetailResponsableProd.size();i++){
			DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
			delaiComplet=new BigDecimal(mapDelaiEmploye.get(detailResponsableProd.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25.get(detailResponsableProd.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50.get(detailResponsableProd.getEmploye().getId()));
	//		delai=delaiComplet/compteurProd;
			note=Integer.parseInt(mapNote.get(detailResponsableProd.getEmploye().getId()));
			motif=mapMotifEmploye.get(detailResponsableProd.getEmploye().getId());
			//coutHoraire=detailResponsableProd.getEmploye().getCoutHoraire()*delai;
			coutHoraireComplet=detailResponsableProd.getEmploye().getCoutHoraire().multiply(delaiComplet)  ;
			
			coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
			
			coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
			
			//detailResponsableProd.setRemise(detailProdGen.getEmploye().getRemise());
			if(!detailResponsableProd.getEmploye().isSalarie()){
				detailResponsableProd.setCoutTotal(coutHoraireComplet);
				detailResponsableProd.setDelaiEmploye(delaiComplet);
				detailResponsableProd.setNote(note);
				detailResponsableProd.setMotif(motif);
				detailResponsableProd.setHeureSupp25(heureSupp25);
				detailResponsableProd.setHeureSupp50(heureSupp50);
				detailResponsableProd.setCoutSupp25(coutSupp25);
				detailResponsableProd.setCoutSupp50(coutSupp50);
				listDetailResponsableProd.set(i,detailResponsableProd);
				
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				//ficheEmploye.setCoutTotal(BigDecimal);
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
			//	ficheEmploye.setCoutSupp25(coutSupp25);
			//	ficheEmploye.setCoutSupp50(coutSupp50);
				ficheEmploye.setNumOF(production.getPeriode());
				ficheEmploye.setDateSituation(production.getDate());
				ficheEmploye.setDelaiEmploye(delaiComplet);
				ficheEmploye.setEmploye(detailResponsableProd.getEmploye());;
				
				ficheEmployeDAO.edit(ficheEmploye);
				}else {
					ficheEmploye =new FicheEmploye();
					ficheEmploye.setHeureSupp25(heureSupp25);
					ficheEmploye.setHeureSupp50(coutSupp50);
				//	ficheEmploye.setCoutSupp25(coutSupp25);
				//	ficheEmploye.setCoutSupp50(coutSupp50);
				//	ficheEmploye.setCoutTotal(coutHoraireComplet);
					ficheEmploye.setNumOF(production.getPeriode());
					ficheEmploye.setDateSituation(production.getDate());
					ficheEmploye.setDelaiEmploye(delaiComplet);
					ficheEmploye.setEmploye(detailResponsableProd.getEmploye());;
					
					ficheEmployeDAO.add(ficheEmploye);
					
				}
			
			
			CompteurEmployeProd compteurEmployeProd =compteurEmployeProdDAO.findByDateProdPeriode(production.getDate(),production.getPeriode(), detailResponsableProd.getEmploye().getId());
			 if(compteurEmployeProd !=null){
				 compteur=compteurEmployeProd.getCompteur()+1;
				 compteurEmployeProd.setCompteur(compteur);	
				 compteurEmployeProdDAO.edit(compteurEmployeProd);
				 
			 }else{
				 compteurEmployeProd= new CompteurEmployeProd();
				 compteurEmployeProd.setDateProd(production.getDate());
				 compteurEmployeProd.setPeriode(production.getPeriode());
				 compteurEmployeProd.setEmploye(detailResponsableProd.getEmploye());
				 compteurEmployeProd.setCompteur(1);
				 compteurEmployeProdDAO.add(compteurEmployeProd);
				 
			 	}
			 } else {
				 	detailResponsableProd.setHeureSupp25(BigDecimal.ZERO);
				 	detailResponsableProd.setCoutSupp50(BigDecimal.ZERO);
					detailResponsableProd.setCoutSupp25(BigDecimal.ZERO);
					detailResponsableProd.setCoutSupp50(BigDecimal.ZERO);
				 	detailResponsableProd.setCoutTotal(BigDecimal.ZERO);
					detailResponsableProd.setDelaiEmploye(delaiComplet);
					detailResponsableProd.setNote(note);
					detailResponsableProd.setMotif(motif);
					listDetailResponsableProd.set(i,detailResponsableProd);
				 
				 FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
					if(ficheEmploye!=null){
						
						/*Remplir fiche programme*/
					//	ficheEmploye.setCoutTotal(BigDecimal.ZERO);
						ficheEmploye.setNumOF(production.getPeriode());
						ficheEmploye.setDateSituation(production.getDate());
						ficheEmploye.setDelaiEmploye(delaiComplet);
						ficheEmploye.setHeureSupp25(BigDecimal.ZERO);
						//ficheEmploye.setCoutSupp50(BigDecimal.ZERO);
					//	ficheEmploye.setCoutSupp25(BigDecimal.ZERO);
					//	ficheEmploye.setCoutSupp50(BigDecimal.ZERO);
						ficheEmploye.setEmploye(detailResponsableProd.getEmploye());;
						
						ficheEmployeDAO.edit(ficheEmploye);
						}else {
							ficheEmploye =new FicheEmploye();
							ficheEmploye.setHeureSupp25(BigDecimal.ZERO);
						//	ficheEmploye.setCoutSupp50(BigDecimal.ZERO);
						//	ficheEmploye.setCoutSupp25(BigDecimal.ZERO);
						//	ficheEmploye.setCoutSupp50(BigDecimal.ZERO);
						//	ficheEmploye.setCoutTotal(BigDecimal.ZERO);
							ficheEmploye.setNumOF(production.getPeriode());
							ficheEmploye.setDateSituation(production.getDate());
							ficheEmploye.setDelaiEmploye(delaiComplet);
							ficheEmploye.setEmploye(detailResponsableProd.getEmploye());;
							
							ficheEmployeDAO.add(ficheEmploye);
							
						}
			 }
			
			
		}
		 List<DetailResponsableProd>  listDetailResponsableProdTmp2= remplirTypeResponsabilite(listDetailResponsableProd);
		production.setListDetailResponsableProd(listDetailResponsableProdTmp2);
		productionDAO.edit(production);
		compterProductionByResponsable(production.getDate(),production.getPeriode(),listDetailResponsableProd);
		
	}

void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Responsabilité", "Délai travaillé","Note","Motif","H Supp 25%","H Supp 50%"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false, true, true,true, true, true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   tableEmploye.setModel(modeleEmploye); 
		   tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(100);
		   tableEmploye.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(6).setPreferredWidth(260);
		   tableEmploye.getColumnModel().getColumn(7).setPreferredWidth(100);
		   tableEmploye.getColumnModel().getColumn(8).setPreferredWidth(100);
}


void compterProductionByResponsable(Date dateProd,String periode,List<DetailResponsableProd> listDetailResponsableProd){
	 
	 int compteur=0;
	 for(int i=0;i<listDetailResponsableProd.size();i++){
	 Employe employe=listDetailResponsableProd.get(i).getEmploye();
	 CompteurResponsableProd compteurResponsableProduction=compteurResponsableProdDAO.findByDateProdPeriode(dateProd, periode, employe.getId());
	 
	 if(compteurResponsableProduction !=null){
		 compteur=compteurResponsableProduction.getCompteur()+1;
		 compteurResponsableProduction.setCompteur(compteur);	
		 compteurResponsableProdDAO.edit(compteurResponsableProduction);
		 
	 }else{
		 compteurResponsableProduction= new CompteurResponsableProd();
		 compteurResponsableProduction.setDateProd(dateProd);
		 compteurResponsableProduction.setPeriode(periode);
		 compteurResponsableProduction.setEmploye(employe);
		 compteurResponsableProduction.setCompteur(1);
		 compteurResponsableProdDAO.add(compteurResponsableProduction);
		 
	   }
	 }
	 
}


List<DetailResponsableProd> remplirTypeResponsabilite( List<DetailResponsableProd> listDetailResponsableProd){
	 BigDecimal compteurTec=BigDecimal.ZERO;
	 BigDecimal compteurAideTec=BigDecimal.ZERO;
	 BigDecimal compteurChefProd=BigDecimal.ZERO;
	 BigDecimal compteurChefEqu=BigDecimal.ZERO;
	 BigDecimal compteurOuvrier=BigDecimal.ZERO;
//	 List<DetailResponsableProd> listDetailResponsableProdTmp=new ArrayList<DetailResponsableProd>();
	 for(int i=0;i<listDetailResponsableProd.size();i++){
		 DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
		 Employe employe=detailResponsableProd.getEmploye();
		 
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_CHEF_PRODUCTION)){
			 	compteurChefProd.add(BigDecimal.ONE);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_RESPONSABLE_EQUIPE)){
			 	compteurChefEqu.add(BigDecimal.ONE);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(EMPLOYE_RESPONSABILITE_TECHNITIEN)){
			 compteurTec.add(BigDecimal.ONE);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(EMPLOYE_RESPONSABILITE_AIDE_TECHNITIEN)){
			 compteurAideTec.add(BigDecimal.ONE);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION)){
			 compteurOuvrier.add(BigDecimal.ONE);
		 }
	 }
	 
	 for(int i=0;i<listDetailResponsableProd.size();i++){
		 DetailResponsableProd detailResponsableProd=listDetailResponsableProd.get(i);
		 Employe employe=detailResponsableProd.getEmploye();
		 
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_CHEF_PRODUCTION)){
			 detailResponsableProd.setNbreTypeEmploye(compteurChefProd);
			 listDetailResponsableProd.set(i,detailResponsableProd);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_RESPONSABLE_EQUIPE)){
		 		detailResponsableProd.setNbreTypeEmploye(compteurChefEqu);
		 		listDetailResponsableProd.set(i,detailResponsableProd);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(EMPLOYE_RESPONSABILITE_TECHNITIEN)){
		 		detailResponsableProd.setNbreTypeEmploye(compteurTec);
		 		listDetailResponsableProd.set(i,detailResponsableProd);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(EMPLOYE_RESPONSABILITE_AIDE_TECHNITIEN)){
		 		detailResponsableProd.setNbreTypeEmploye(compteurAideTec);
		 		listDetailResponsableProd.set(i,detailResponsableProd);
		 }
		 if(employe.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION)){
		 		detailResponsableProd.setNbreTypeEmploye(compteurOuvrier);
		 		listDetailResponsableProd.set(i,detailResponsableProd);
		 }
	 }
	 return listDetailResponsableProd;	 
}
}

package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Production;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ConsulterManquePlusDechetEmballage extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable tableCoutMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableDechetFournisseur;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ProductionDAO productionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private List<Production> listProduction=new ArrayList<Production>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseur>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurImprimer=new ArrayList<DetailManqueDechetFournisseur>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private  JComboBox combomp = new JComboBox();
	private JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur;
	private JTextField txtCodeMP;
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private DepotDAO depotDAO;
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 JComboBox comboMagasinDechet = new JComboBox();
	 
	 
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterManquePlusDechetEmballage() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 825);
        try{
        	
        	
        	
        	productionDAO=new ProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	depotDAO= new DepotDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
        	imgAjouter= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
       	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     tableCoutMP = new JXTable();
				  		     tableCoutMP.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {


				  		    		if(tableCoutMP.getSelectedRow()!=-1)
				  		     		{
				  		    			
				  		    			listDetailManqueDechetFournisseurImprimer.clear();
				  		    			MatierePremier mp=mapMatierePremiere.get(tableCoutMP.getValueAt(tableCoutMP.getSelectedRow(), 1));
				  		     			FournisseurMP fournisseur=mapfournisseur.get(tableCoutMP.getValueAt(tableCoutMP.getSelectedRow(), 2));
				  		     			Magasin magasin=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				  						if(magasin==null)
				  						{
				  							JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!");
				  							return;
				  						}
				  		    			listDetailManqueDechetFournisseurImprimer=detailManqueDechetFournisseurDAO.listeDetailManqueDechetFournisseurByMPByFournisseur(mp, fournisseur, Constantes.ETAT_VALIDER,Constantes.TYPE_DECHET_FOURNISSEUR,magasin);
				  		     afficher_tableMP_Total(listDetailManqueDechetFournisseurImprimer);
				  		  		  		     	
				  		     						  		     				


				  		     	
				  		     	}
				  		     	
				  		     	
				  		     	}
				  		     });
				  		     tableCoutMP.setShowVerticalLines(false);
				  		     tableCoutMP.setSelectionBackground(new Color(51, 204, 255));
				  		     tableCoutMP.setRowHeightEnabled(true);
				  		     tableCoutMP.setBackground(new Color(255, 255, 255));
				  		     tableCoutMP.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     tableCoutMP.setColumnControlVisible(true);
				  		     tableCoutMP.setForeground(Color.BLACK);
				  		     tableCoutMP.setGridColor(new Color(0, 0, 255));
				  		     tableCoutMP.setAutoCreateRowSorter(true);
				  		     tableCoutMP.setBounds(2, 27, 411, 198);
				  		     tableCoutMP.setRowHeight(20);
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code MP","Matiere Premiere","Fournisseur","Quantite Total Manque"," Quantite Total Dechet"," Quantite Total Plus"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 tableCoutMP.setModel(modeleMP); 
					  		 tableCoutMP.getColumnModel().getColumn(0).setPreferredWidth(60);
					         tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(160);
					         tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(60);
					         tableCoutMP.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableCoutMP);
				  		     	scrollPane.setBounds(9, 108, 1125, 200);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	

				  		     	
				  		     		tableDechetFournisseur = new JXTable();
				  		     		tableDechetFournisseur.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableDechetFournisseur.getSelectedRow()!=-1)
				  		     				{

				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     		});
				  		     		tableDechetFournisseur.setShowVerticalLines(false);
				  		     		tableDechetFournisseur.setSelectionBackground(new Color(51, 204, 255));
				  		     		tableDechetFournisseur.setRowHeightEnabled(true);
				  		     		tableDechetFournisseur.setBackground(new Color(255, 255, 255));
				  		     		tableDechetFournisseur.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		tableDechetFournisseur.setColumnControlVisible(true);
				  		     		tableDechetFournisseur.setForeground(Color.BLACK);
				  		     		tableDechetFournisseur.setGridColor(new Color(0, 0, 255));
				  		     		tableDechetFournisseur.setAutoCreateRowSorter(true);
				  		     		//    table.setBounds(2, 27, 411, 198);
				  		     		tableDechetFournisseur.setRowHeight(20);
				  		      modeleMP1 =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"OF","Code MP","Matiere Premiere","Fournisseur", "Quantité Manque","Quantite Dechet","Quantite Plus"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableDechetFournisseur.setModel(modeleMP1); 
				  			 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableDechetFournisseur);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(9, 404, 1125, 355);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 77, 1125, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1125, 54);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1084, 7, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(combomp.getSelectedItem().equals("") && combofournisseur.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP ou Fournisseur SVP !!!");
			}else
			{
				MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
				FournisseurMP fournisseur=mapfournisseur.get(combofournisseur.getSelectedItem());
				Magasin magasin=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!");
					return;
				}
				
				listeObject.clear();
				listDetailManqueDechetFournisseurImprimer.clear();
				listeObject=detailManqueDechetFournisseurDAO.listeDetailManqueDechetFournisseurGroupByMPByFournisseur(mp, fournisseur , Constantes.ETAT_VALIDER,Constantes.TYPE_DECHET_FOURNISSEUR,magasin)	;
				intialiserTableau();
				afficher_tableMP(listeObject);
				afficher_tableMP_Total(listDetailManqueDechetFournisseurImprimer);
				
				
			}

			
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Code MP:");
		label.setBounds(10, 12, 66, 24);
		layeredPane.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtCodeMP = new JTextField();
		txtCodeMP.setBounds(70, 11, 106, 26);
		layeredPane.add(txtCodeMP);
		txtCodeMP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

		  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
			      		{
					MatierePremier mp=mapCodeMatierePremiere.get(txtCodeMP.getText());
					if(mp!=null)
					{
						combomp.setSelectedItem(mp.getNom());
					}else
					{
						JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
						return;
					}
					
			      		}
			
				
		  		  		
		  		  	
				
				
				
				
			}
		});
		txtCodeMP.setColumns(10);
		
		JLabel lblMp = new JLabel("  MP :");
		lblMp.setBounds(187, 12, 44, 24);
		layeredPane.add(lblMp);
		
		 combomp = new JComboBox();
		 combomp.setBounds(222, 12, 211, 24);
		 combomp.addItem("");
		 layeredPane.add(combomp);
		 AutoCompleteDecorator.decorate(combomp);
		 combomp.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

  		   		

		  	 		
		  	 		if(!combomp.getSelectedItem().equals(""))
		  	 		{
		  	 			
		  	 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
		  	 			if(mp!=null)
		  	 			{
		  	 				txtCodeMP.setText(mp.getCode());
		  	 			}else
		  	 			{
		  	 				JOptionPane.showMessageDialog(null, "MP Introuvable");
		  	 				txtCodeMP.setText(Constantes.CODE_MP);	
		  	 				return;
		  	 			}
		  	 			
		  	 			
		  	 			
		  	 		}else
		  	 		{
		  	 		txtCodeMP.setText(Constantes.CODE_MP);
		  	 		}
		  	 		
		  	 	
  		   		
  		   		
  		   		
  		   		
  		   	
		 		
		 		
		 	}
		 });
	
		
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setBounds(458, 12, 74, 24);
		 layeredPane.add(lblFournisseur);
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  combofournisseur = new JComboBox();
		  combofournisseur.setBounds(525, 14, 211, 24);
		  layeredPane.add(combofournisseur);
		  combofournisseur.addItem("");
		  
		  JLabel label_1 = new JLabel("Magasin Dechet :");
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_1.setBounds(757, 11, 97, 24);
		  layeredPane.add(label_1);
		  
		   comboMagasinDechet = new JComboBox();
		  comboMagasinDechet.setBounds(864, 14, 208, 24);
		  layeredPane.add(comboMagasinDechet);
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

		 	if(listDetailManqueDechetFournisseurImprimer.size()!=0)
		 	{
		 		
		 		
		 		Map parameters = new HashMap();
				
				JasperUtils.imprimerDetailManqueDechetFournisseurMP(listDetailManqueDechetFournisseurImprimer,parameters);
		 	
		 		
		 	}		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(456, 770, 104, 31);
		 add(button_1);
				listMatierePremiere=matierePremiereDAO.findAll();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					
					if(!matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						combomp.addItem(matierePremier.getNom());
						mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
						mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
					}
					
				}
				
				listFournisseur=fournisseurMPDAO.findAll();
				for(int j=0;j<listFournisseur.size();j++)
				{
					FournisseurMP fournisseurMP=listFournisseur.get(j);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				
				
				Depot depot=depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());
				
				 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
	  		
	  		      
	  		      
					comboMagasinDechet.addItem(""); 		      
if(listMagasinDechetMP!=null){
	  		    	  
	  		    	  int j=0;
		  		      	while(j<listMagasinDechetMP.size())
		  		      		{	
		  		      			Magasin magasin=listMagasinDechetMP.get(j);
		  		      			comboMagasinDechet.addItem(magasin.getLibelle());
		  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
		  		      			j++;
		  		      		}
	  		      }
				
				
txtCodeMP.setText(Constantes.CODE_MP);		
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			 
				 
				Object []ligne={detailManqueDechetFournisseur.getManquedechetfournisseur().getNumOF(),detailManqueDechetFournisseur.getMatierePremier().getCode(),detailManqueDechetFournisseur.getMatierePremier().getNom(),detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantiteManque()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantiteDechet()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantitePlus())};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	
void afficher_tableMP(List<Object[]> listEtatMontantPF)
	{
		intialiserTableau();
		 
		int i=0;
		 
		while(i<listEtatMontantPF.size())
		{	
			 Object[] object=listEtatMontantPF.get(i);
		
		MatierePremier mp=mapCodeMatierePremiere.get(object[0].toString());
		if(!mp.getCategorieMp().getSubCategorieMp().getCategorieMps().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
		 
			
			 Object []ligne={object[0],object[1],object[2],NumberFormat.getNumberInstance(Locale.FRANCE).format(object[3]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[4]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[5])};
			 modeleMP.addRow(ligne);
			
		}
				
					
				 
					
			
			
			
			i++;
		}
		
		
			
		
	}






void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matiere Premiere","Fournisseur","Quantite Total Manque"," Quantite Total Dechet"," Quantite Total Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableCoutMP.setModel(modeleMP); 
		 tableCoutMP.getColumnModel().getColumn(0).setPreferredWidth(60);
      tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(160);
      tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(60);
      tableCoutMP.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}

void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"OF","Code MP","Matiere Premiere","Fournisseur", "Quantité Manque","Quantite Dechet","Quantite Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableDechetFournisseur.setModel(modeleMP1); 
		 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(160);
		 tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
  


}






public void Vider()
{
	combofournisseur.setSelectedItem("");
	combomp.setSelectedItem("");
}
}

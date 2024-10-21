package ProductionCarton;

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

import dao.daoImplManager.DetailManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DetailManqueDechetFournisseurCartonDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurCartonDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.ManqueDechetFournisseurCarton;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.ProductionMP;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ConsulterManqueDechetFournisseurCarton extends JLayeredPane implements Constantes{
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
	private ProductionMPDAO productionMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	private List<ProductionMP> listProduction=new ArrayList<ProductionMP>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurAdhesive> listFournisseur=new ArrayList<FournisseurAdhesive>();
	private List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseurCarton>();
	private List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseurImprimer=new ArrayList<DetailManqueDechetFournisseurCarton>();
	private List<ManqueDechetFournisseurCarton> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseurCarton>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurAdhesive> mapfournisseur = new HashMap<>();
	
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private  JComboBox combomp = new JComboBox();
	private JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurCartonDAO manqueDechetFournisseurCartonDAO;
	private DetailManqueDechetFournisseurCartonDAO detailManqueDechetFournisseurcartonDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur;
	private JTextField txtCodeMP;
	JDateChooser dateChooserDu = new JDateChooser();
	JDateChooser dateChooserAu = new JDateChooser();
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterManqueDechetFournisseurCarton() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1371, 825);
        try{
        	
        	
        	
        	productionMPDAO=new ProductionMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
        	manqueDechetFournisseurCartonDAO=new ManqueDechetFournisseurCartonDAOImpl();
        	detailManqueDechetFournisseurcartonDAO=new DetailManqueDechetFournisseurCartonDAOImpl();
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
				  		     			FournisseurAdhesive fournisseur=mapfournisseur.get(tableCoutMP.getValueAt(tableCoutMP.getSelectedRow(), 2));
				  		    			listDetailManqueDechetFournisseurImprimer=detailManqueDechetFournisseurcartonDAO.listeDetailManqueDechetFournisseurByMPByFournisseur(mp, fournisseur, Constantes.ETAT_VALIDER);
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
					  		     			"Code MP","Matiere Premiere","Fournisseur","Quantite Total Manque"," Quantite Total Dechet"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
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
				  		     	scrollPane.setBounds(9, 149, 1293, 200);
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
				  			     			"OF","Code MP","Matiere Premiere","Fournisseur", "Quantité Manque","Quantite Dechet"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false
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
				  				scrollPane_1.setBounds(9, 404, 1293, 355);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 118, 1293, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1293, 96);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1252, 30, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(combomp.getSelectedItem().equals("") && combofournisseur.getSelectedItem().equals("") && dateChooserDu.getDate()==null && dateChooserAu.getDate()==null)
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP ou Fournisseur ou Entre Deux dates SVP !!!");
			}else
			{
				
				
				if(dateChooserDu.getDate()==null && dateChooserAu.getDate()!=null)
				{
					dateChooserDu.setDate(dateChooserAu.getDate());
				}else if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()==null)
				{
					dateChooserAu.setDate(dateChooserDu.getDate());
				}else if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()!=null)
				{
					
					if(dateChooserDu.getDate().compareTo(dateChooserAu.getDate()) > 0)
					{
						JOptionPane.showMessageDialog(null, "La Date debut doit etre inferieur au date fin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						
						return ;
					}
					
					
					
					
				}
				if(dateChooserDu.getDate()!=null)
				{
					dateChooserDu.setDateFormatString("yyyy-MM-dd");
				}
				if(dateChooserAu.getDate()!=null)
				{
					dateChooserAu.setDateFormatString("yyyy-MM-dd");
				}	
				
             String dateDu=((JTextField)dateChooserDu.getDateEditor().getUiComponent()).getText();
				
				String dateAu=((JTextField)dateChooserAu.getDateEditor().getUiComponent()).getText();
					
					if(!dateDu.equals("") && dateAu.equals(""))
					{
						dateAu=dateDu;
					}else if(dateDu.equals("") && !dateAu.equals(""))
					{
						dateDu=dateAu;
					}
				
				MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
				FournisseurAdhesive fournisseur=mapfournisseur.get(combofournisseur.getSelectedItem());
				listeObject.clear();
				listDetailManqueDechetFournisseurImprimer.clear();
				 
				String req="";
				if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()!=null)
				{
					req=req+" and  d.manquedechetfournisseurcarton.dateDechet between '"+dateDu+"' and '"+dateAu+"' ";
				}
				
				if(mp!=null)
				{
					req=req+" and d.matierePremier.id='"+mp.getId()+"' ";
				}
				
				if(fournisseur!=null)
				{
					req=req+" and  d.fourniseurAdhesive.id='"+fournisseur.getId()+"' ";
				}
				
				
				if(!req.equals(""))
				{
					
					req=req+" and d.manquedechetfournisseurcarton.etat='"+Constantes.ETAT_VALIDER+"'" ;
					
					listeObject=detailManqueDechetFournisseurcartonDAO.listeDetailManqueDechetFournisseurGroupByMPByFournisseur(req)	;
				}
				
				
				
					

				  
				intialiserTableau();
				afficher_tableMP(listeObject);
				afficher_tableMP_Total(listDetailManqueDechetFournisseurImprimer);
				
				
			}

			
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Code MP:");
		label.setBounds(433, 30, 66, 24);
		layeredPane.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtCodeMP = new JTextField();
		txtCodeMP.setBounds(493, 29, 106, 26);
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
		lblMp.setBounds(610, 30, 44, 24);
		layeredPane.add(lblMp);
		
		 combomp = new JComboBox();
		 combomp.setBounds(645, 30, 274, 24);
		 combomp.addItem("");
		 layeredPane.add(combomp);
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
		  	 				return;
		  	 			}
		  	 			
		  	 			
		  	 			
		  	 		}else
		  	 		{
		  	 		txtCodeMP.setText(Constantes.CODE_MP);
		  	 		}
		  	 		
		  	 	
  		   		
  		   		
  		   		
  		   		
  		   	
		 		
		 		
		 	}
		 });
	
		
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setBounds(949, 30, 74, 24);
		 layeredPane.add(lblFournisseur);
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  combofournisseur = new JComboBox();
		  combofournisseur.setBounds(1016, 32, 211, 24);
		  layeredPane.add(combofournisseur);
		  combofournisseur.addItem("");
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

		 	if(listDetailManqueDechetFournisseurImprimer.size()!=0)
		 	{
		 		
		 		
		 		Map parameters = new HashMap();
				
				JasperUtils.imprimerDetailManqueDechetFournisseurCarton(listDetailManqueDechetFournisseurImprimer,parameters);
		 	
		 		
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
					combomp.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				
				listFournisseur=fournisseurAdhesiveDAO.findAll();
				for(int j=0;j<listFournisseur.size();j++)
				{
					FournisseurAdhesive fournisseurMP=listFournisseur.get(j);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				
				
				txtCodeMP.setText("MP_");	
				
				JLabel label_1 = new JLabel("Du :");
				label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				label_1.setBounds(17, 32, 45, 24);
				layeredPane.add(label_1);
				
				  dateChooserDu = new JDateChooser();
				dateChooserDu.setLocale(Locale.FRANCE);
				dateChooserDu.setDateFormatString("dd/MM/yyyy");
				dateChooserDu.setBounds(51, 30, 163, 26);
				layeredPane.add(dateChooserDu);
				
				JLabel label_2 = new JLabel("Au :");
				label_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				label_2.setBounds(224, 32, 36, 24);
				layeredPane.add(label_2);
				
				  dateChooserAu = new JDateChooser();
				dateChooserAu.setLocale(Locale.FRANCE);
				dateChooserAu.setDateFormatString("dd/MM/yyyy");
				dateChooserAu.setBounds(254, 30, 169, 26);
				layeredPane.add(dateChooserAu);
				  		 
	}
	
	

	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			 
				 
				Object []ligne={detailManqueDechetFournisseur.getManquedechetfournisseurcarton().getNumOF(),detailManqueDechetFournisseur.getMatierePremier().getCode(),detailManqueDechetFournisseur.getMatierePremier().getNom(),detailManqueDechetFournisseur.getFourniseurAdhesive().getCodeFournisseur(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantiteManque()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantiteDechet())};

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
		
		
				
					 Object []ligne={object[0],object[1],object[2],NumberFormat.getNumberInstance(Locale.FRANCE).format(object[3]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[4])};
					 modeleMP.addRow(ligne);
				 
					
			
			
			
			i++;
		}
		
		
			
		
	}






void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matiere Premiere","Fournisseur","Quantite Total Manque"," Quantite Total Dechet"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
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
		     			"OF","Code MP","Matiere Premiere","Fournisseur", "Quantité Manque","Quantite Dechet"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false
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
	txtCodeMP.setText("MP_");	
}
}

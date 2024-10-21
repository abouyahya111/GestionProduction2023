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
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SubCategorieMPDAO;
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
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;


public class SituationDechetManqueParJour extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
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
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private  JComboBox combomp = new JComboBox();
	JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur;
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	JComboBox soucategoriempcombo = new JComboBox();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	JComboBox combodepot = new JComboBox();
	JDateChooser dateChooser = new JDateChooser();
	private JDateChooser dateChooserDu;
	JDateChooser dateChooser_1 = new JDateChooser();
	private JDateChooser dateChooserAu;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMagasin = new JComboBox();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	JLabel totalmoins = new JLabel("0.00");
	JLabel totalplus = new JLabel("0.00");
	JLabel totaldechetfournisseur = new JLabel("0.00");
	JLabel totaldechetusine = new JLabel("0.00");
	JLabel difference = new JLabel("0.00");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationDechetManqueParJour() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1437, 825);
        try{
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Date Production","Code MP","Matiere Premiere","Fournisseur","Dechet usine","Dechet fournisseur","Manque Plus","Manque Moins"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	

				  		     	
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
				  		      modeleMP =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Date Production","Code MP","Matiere Premiere","Fournisseur","Dechet usine","Dechet fournisseur","Manque Plus","Manque Moins"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableDechetFournisseur.setModel(modeleMP); 
				  			 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableDechetFournisseur);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(10, 166, 1417, 470);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1418, 106);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1309, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(combodepot.getSelectedItem().equals("") )
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner depot SVP !!!");
				return;
			}else if(comboMagasin.getSelectedItem().equals("") )
			{

				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!");
				return;
			
			
			}
			
			else if(dateChooserDu.getDate()==null && dateChooserAu.getDate()==null)
			{

				JOptionPane.showMessageDialog(null, "Veuillez Selectionner la periode SVP !!!");
				return;
			
			
			}else if(soucategoriempcombo.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner la categorie SVP !!!");
				return;
			}else
			{
				
				Depot depot=mapDepotSource.get(combodepot.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				
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
				
				
				
				String requete=" where prodcutionCM.codeDepot='"+depot.getCode()+"'";
				requete=requete+" and prodcutionCM.magasinStockage.id= '"+magasin.getId()+"'";
				
				 requete=requete+" and prodcutionCM.date between '"+dateDu+"' and '"+dateAu+"'";
				
				 requete=requete+" and matierePremier.categorieMp.subCategorieMp.id= '"+subCategorieMp.getId()+"' ";
				 
				 if(!combofournisseur.getSelectedItem().equals(""))
				 {
					 
					 requete=requete+" and (codeFournisseur like  '%, "+ combofournisseur.getSelectedItem().toString()+"%' or codeFournisseur like  '%"+combofournisseur.getSelectedItem().toString()+",  %' or codeFournisseur='"+combofournisseur.getSelectedItem().toString()+"')"; 
				 }
				
				 listeObject=productionDAO.listeSituationDechetManque(requete, Constantes.ETAT_OF_TERMINER);
				afficher_tableMP(listeObject);
				
				
			}

			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Du :");
		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(20, 14, 45, 24);
		layeredPane.add(label);
		
		 dateChooserDu = new JDateChooser();
		dateChooserDu.setLocale(Locale.FRANCE);
		dateChooserDu.setDateFormatString("dd/MM/yyyy");
		dateChooserDu.setBounds(54, 12, 163, 26);
		layeredPane.add(dateChooserDu);
		
		JLabel label_1 = new JLabel("Au :");
		label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label_1.setBounds(227, 14, 36, 24);
		layeredPane.add(label_1);
		
		 dateChooserAu = new JDateChooser();
		dateChooserAu.setLocale(Locale.FRANCE);
		dateChooserAu.setDateFormatString("dd/MM/yyyy");
		dateChooserAu.setBounds(257, 12, 169, 26);
		layeredPane.add(dateChooserAu);
		
		JLabel label_2 = new JLabel("Depot :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(436, 11, 55, 26);
		layeredPane.add(label_2);
		
		 combodepot = new JComboBox();
		 combodepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		
		 		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
 		     	  	 // comboGroupe = new JComboBox();
		     	 	comboMagasin.removeAllItems();
		     	 Depot depot=new Depot();
		     	 	//comboGroupe.addItem("");
		     	 	if(!combodepot.getSelectedItem().equals(""))
 		     	  	   	 depot =mapDepotSource.get(combodepot.getSelectedItem());
		  		       if(depot!=null)
		  		       {
		  		    	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);   
		  		       }
 		     	  		
		  		      if(listMagasin!=null){
		  		    	  
		  		    	  int j=0;
			  		      	while(j<listMagasin.size())
			  		      		{	
			  		      			Magasin magasin=listMagasin.get(j);
			  		      			comboMagasin.addItem(magasin.getLibelle());
			  		      			mapMagasin.put(magasin.getLibelle(), magasin);
			  		      			j++;
			  		      		}
		  		      }
		     	 	 }
		     	 	
		 		
		 		
		 	}
		 });
		combodepot.setBounds(491, 11, 202, 27);
		layeredPane.add(combodepot);
		
		JLabel label_3 = new JLabel("Sous-Categorie Mp");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(1008, 11, 104, 24);
		layeredPane.add(label_3);
		
		 soucategoriempcombo = new JComboBox();
		soucategoriempcombo.setBounds(1115, 11, 184, 24);
		layeredPane.add(soucategoriempcombo);
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

		 	if(tableDechetFournisseur.getRowCount()!=0)
		 	{
				if(dateChooserDu.getDate()!=null)
				{
					dateChooserDu.setDateFormatString("dd/MM/yyyy");
				}
				if(dateChooserAu.getDate()!=null)
				{
					dateChooserAu.setDateFormatString("dd/MM/yyyy");
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
		 		
		 		Map parameters = new HashMap();
		 		parameters.put("magasin", comboMagasin.getSelectedItem().toString());
		 		String souscategorie="";
		 		souscategorie=soucategoriempcombo.getSelectedItem().toString();
		 		if(souscategorie.equals("THE"))
		 		{
		 			souscategorie="EN VRAC";
		 		}
		 		
		 			
		 		parameters.put("titre", "SITUATION DECHET MANQUE ET MANQUE PLUS "+souscategorie);
		 		parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
				JasperUtils.imprimerSituationDechetManque(parameters, tableDechetFournisseur.getModel()); 
		 	
		 		
		 	}		 	
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(213, 660, 104, 31);
		 add(button_1);
				listMatierePremiere=matierePremiereDAO.findAll();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					combomp.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				
		
				
				
				combodepot.addItem("");
			    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
		    		Depot depot = depotDAO.findByCode(utilisateur.getCodeDepot());
			     		combodepot.addItem(depot.getLibelle());
			     		mapDepotSource.put(depot.getLibelle(), depot);
		    }else {
		    	
		    	listDepot = depotDAO.findAll();	
			      int i=0;
			      	while(i<listDepot.size())
			      		{	
			      		Depot depot=listDepot.get(i);
			      			mapDepotSource.put(depot.getLibelle(), depot);
			      			combodepot.addItem(depot.getLibelle());
			      			i++;
			      		}
		    	
		    }		
				
				soucategoriempcombo.removeAllItems();
		  		soucategoriempcombo.addItem("");
		  		
		  		 comboMagasin = new JComboBox();
		  		comboMagasin.setBounds(768, 11, 202, 27);
		  		layeredPane.add(comboMagasin);
		  		
		  		JLabel lblMagasin = new JLabel("Magasin :");
		  		lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblMagasin.setBounds(713, 11, 55, 26);
		  		layeredPane.add(lblMagasin);
		  		
		  		JLabel lblFournisseur = new JLabel("Fournisseur :");
		  		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblFournisseur.setBounds(20, 68, 67, 26);
		  		layeredPane.add(lblFournisseur);
		  		
		  		 combofournisseur = new JComboBox();
		  		combofournisseur.setBounds(97, 68, 214, 27);
		  		layeredPane.add(combofournisseur);
		  		
		  		JButton button = new JButton();
		  		button.setText("Initialiser");
		  		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		button.setBounds(1290, 63, 93, 31);
		  		layeredPane.add(button);
				
				  int j=0;
		  		  while(j<listsubcategoriemp.size())
		  		  {
		  			  subcatMap.put(listsubcategoriemp.get(j).getNom(), listsubcategoriemp.get(j));
		  			  soucategoriempcombo.addItem(listsubcategoriemp.get(j).getNom());
		  			  j++;
		  		  }	
		  		  
		  		  
		  		  
		  		listFournisseur=fournisseurMPDAO.findAll();
		  		combofournisseur.addItem("");
		  		
		  		 totalmoins = new JLabel("0.00");
		  		totalmoins.setHorizontalAlignment(SwingConstants.CENTER);
		  		totalmoins.setForeground(Color.RED);
		  		totalmoins.setFont(new Font("Tahoma", Font.BOLD, 14));
		  		totalmoins.setBounds(1244, 649, 171, 39);
		  		add(totalmoins);
		  		
		  		 totalplus = new JLabel("0.00");
		  		totalplus.setHorizontalAlignment(SwingConstants.CENTER);
		  		totalplus.setForeground(Color.RED);
		  		totalplus.setFont(new Font("Tahoma", Font.BOLD, 14));
		  		totalplus.setBounds(1054, 649, 179, 39);
		  		add(totalplus);
		  		
		  		 totaldechetfournisseur = new JLabel("0.00");
		  		totaldechetfournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		  		totaldechetfournisseur.setForeground(Color.RED);
		  		totaldechetfournisseur.setFont(new Font("Tahoma", Font.BOLD, 14));
		  		totaldechetfournisseur.setBounds(765, 649, 294, 39);
		  		add(totaldechetfournisseur);
		  		
		  		 totaldechetusine = new JLabel("0.00");
		  		totaldechetusine.setHorizontalAlignment(SwingConstants.CENTER);
		  		totaldechetusine.setForeground(Color.RED);
		  		totaldechetusine.setFont(new Font("Tahoma", Font.BOLD, 14));
		  		totaldechetusine.setBounds(497, 649, 247, 39);
		  		add(totaldechetusine);
		  		
		  		 difference = new JLabel("0.00");
		  		difference.setHorizontalAlignment(SwingConstants.CENTER);
		  		difference.setForeground(Color.RED);
		  		difference.setFont(new Font("Tahoma", Font.BOLD, 14));
		  		difference.setBounds(1054, 700, 319, 39);
		  		add(difference);
				for(int k=0;k<listFournisseur.size();k++)
				{
					FournisseurMP fournisseurMP=listFournisseur.get(k);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			 
				 
				Object []ligne={detailManqueDechetFournisseur.getManquedechetfournisseur().getNumOF(),detailManqueDechetFournisseur.getMatierePremier().getCode(),detailManqueDechetFournisseur.getMatierePremier().getNom(),detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur(),detailManqueDechetFournisseur.getQuantiteManque(),detailManqueDechetFournisseur.getQuantiteDechet()};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	
void afficher_tableMP(List<Object[]> listEtatMontantPF)
	{
	intialiserTableau2();
		 
		int i=0;
		BigDecimal totalmoins=BigDecimal.ZERO ;
		BigDecimal totalPlus=BigDecimal.ZERO ;
		BigDecimal totalDechet=BigDecimal.ZERO ;
		BigDecimal totalDechetFournisseur=BigDecimal.ZERO ;
		BigDecimal diffenceplusmoins=BigDecimal.ZERO ;
		 
		while(i<listEtatMontantPF.size())
		{	
			 Object[] object=listEtatMontantPF.get(i);
		
		
				
					 Object []ligne={object[0],object[1],object[2],object[3],NumberFormat.getNumberInstance(Locale.FRANCE).format(object[4]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[5]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[6]),NumberFormat.getNumberInstance(Locale.FRANCE).format(object[7])};
					 modeleMP.addRow(ligne);
					 totalmoins=totalmoins.add(new BigDecimal(object[7].toString()) );
					 totalPlus=totalPlus.add(new BigDecimal(object[6].toString()) );
					 totalDechetFournisseur=totalDechetFournisseur.add(new BigDecimal(object[5].toString()));
					 totalDechet=totalDechet.add(new BigDecimal(object[4].toString()) );
							 
			
			
			
			i++;
		}
		
		
		this.totalmoins.setText("Total Moins : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalmoins));
		this.totalplus.setText("Total Plus : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlus));
		this.totaldechetusine.setText("Total Dechet : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechet));
		this.totaldechetfournisseur.setText("Total Dechet Fournisseur : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalDechetFournisseur));
		this.difference.setText("Difference : "+NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPlus.subtract(totalmoins)));
		
			
		
	}









void intialiserTableau2(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date Production","Code MP","Matiere Premiere","Fournisseur","Dechet usine","Dechet fournisseur","Manque Plus","Manque Moins"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableDechetFournisseur.setModel(modeleMP); 
		 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(6).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(7).setPreferredWidth(60);

}






public void Vider()
{
	combofournisseur.setSelectedItem("");
	soucategoriempcombo.setSelectedItem("");
	combodepot.setSelectedItem("");
	comboMagasin.setSelectedItem("");
	dateChooserDu.setDate(null);
	dateChooserAu.setDate(null);
}
}

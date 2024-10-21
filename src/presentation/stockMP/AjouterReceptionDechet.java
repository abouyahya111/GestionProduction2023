package presentation.stockMP;

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
import presentation.stockMP.TransfererStockMP;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailTransferStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
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


public class AjouterReceptionDechet extends JLayeredPane implements Constantes{
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
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private JTextField txtdechet;
	
	private JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur =new ManqueDechetFournisseur();
	private TransferStockMP transferStockMPTmp=new TransferStockMP() ;
	JComboBox comboMagasinDechet = new JComboBox();
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private DepotDAO depotDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private JTextField txtCodeMP;
	 JComboBox comboMP = new JComboBox();
	 JComboBox soucategoriempcombo = new JComboBox();
	 JComboBox categoriempcombo = new JComboBox();
	 JComboBox combodepot = new JComboBox();
	 private List<Depot> listDepot = new ArrayList<Depot>();
	 private DepotDAO depotdao;
	 private Map<String, Depot> mapDepot = new HashMap<>();
	 List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
		List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
		private SubCategorieMPDAO subcategoriempdao;
		private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
		private Map< String, CategorieMp> catMap = new HashMap<>();
		private CategorieMpDAO categoriempdao;
		private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
		
		private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
		 JButton bttnAjouter = new JButton();
		 JButton btnModifier = new JButton();
		 JButton btnSupprimer = new JButton("Supprimer");
			private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
			private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
		 StockMPDAO stockMPDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterReceptionDechet() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1427, 825);
        try{
        	
        	
        	
        	stockMPDAO=new StockMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	detailTransferMPDAO= new DetailTransferMPDAOImpl();
        	depotdao = new DepotDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl() ;
        	listsubcategoriemp=subcategoriempdao.findAll();
        	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();

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
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code MP","Matiere Premiere","Manque","Dechet"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false
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
				  		     					MatierePremier matierePremier=mapMatierePremiere.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0));
				  		     					soucategoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
				  		     					categoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getNom());
				  		     					comboMP.setSelectedItem(matierePremier.getNom());
				  		     					combofournisseur.setSelectedItem(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),1));				  		     							  		     					
				  		     					txtdechet.setText(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),2).toString());
				  		     					
				  		     					bttnAjouter.setEnabled(false);
				  		     					btnModifier.setEnabled(true);
				  		     					btnSupprimer.setEnabled(true);
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
				  			     			"Matière Première","Fournisseur", "Quantite Dechet"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false
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
				  				scrollPane_1.setBounds(14, 251, 1246, 355);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1246, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date Dechet:");
				  		     	lblDateDebut.setBounds(645, 12, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		 
		  dateChooserdechet = new JDateChooser();
		 dateChooserdechet.setLocale(Locale.FRANCE);
		 dateChooserdechet.setDateFormatString("dd/MM/yyyy");
		 dateChooserdechet.setBounds(718, 10, 155, 26);
		 layeredPane.add(dateChooserdechet);
		 
		 JLabel lblMagasinDechet = new JLabel("Magasin Dechet :");
		 lblMagasinDechet.setBounds(307, 12, 97, 24);
		 layeredPane.add(lblMagasinDechet);
		 lblMagasinDechet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		  comboMagasinDechet = new JComboBox();
		  comboMagasinDechet.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		
		  		
		  		
		  	}
		  });
		  comboMagasinDechet.setBounds(414, 15, 208, 24);
		  layeredPane.add(comboMagasinDechet);
		  
		        
		        
					comboMagasinDechet.addItem(""); 		      
					
					JLabel label_4 = new JLabel("Depot  :");
					label_4.setFont(new Font("Verdana", Font.BOLD, 12));
					label_4.setBounds(0, 13, 85, 26);
					layeredPane.add(label_4);
					
					 combodepot = new JComboBox();
					 combodepot.addActionListener(new ActionListener() {
					 	public void actionPerformed(ActionEvent arg0) {
					 		

			 		 		
			 		 		if(!combodepot.getSelectedItem().equals(""))
			 		 		{
			 		 			
			 		 		comboMagasinDechet.removeAllItems();
			 		 		
			 		 		

			 			
			 					Depot depot = mapDepot.get(combodepot.getSelectedItem());
			 					
			 					if (depot != null) {
			 					
			 						
			 						listMagasinDechetMP = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
			 						int k = 0;
			 						comboMagasinDechet.addItem("");
			 						while (k < listMagasinDechetMP.size()) {
			 							Magasin magasin = listMagasinDechetMP.get(k);

			 							comboMagasinDechet.addItem(magasin.getLibelle());

			 							MapmagasinDechetMP.put(magasin.getLibelle(), magasin);

			 							k++;

			 						}

			 					}
			 				
			 		 		
			 		 		}else
			 		 		{
			 		 			comboMagasinDechet.removeAllItems();
			 		 			comboMagasinDechet.addItem("");
			 		 			
			 		 		}
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 	
					 		
					 		
					 	}
					 });
					 combodepot.addItemListener(new ItemListener() {
					 	public void itemStateChanged(ItemEvent arg0) {
}
					 	
					 	
					 	
					 	
					 });
					combodepot.setBounds(73, 15, 202, 27);
					layeredPane.add(combodepot);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_1.setBounds(9, 98, 1251, 108);
		 add(layeredPane_1);
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblFournisseur.setBounds(22, 67, 74, 24);
		 layeredPane_1.add(lblFournisseur);
		 
		  combofournisseur = new JComboBox();
		 combofournisseur.setSelectedIndex(-1);
		 combofournisseur.setBounds(89, 69, 184, 24);
		 layeredPane_1.add(combofournisseur);
		 
		 txtdechet = new JTextField();
		 txtdechet.setColumns(10);
		 txtdechet.setBounds(350, 65, 110, 26);
		 layeredPane_1.add(txtdechet);
		 
		 JLabel lblDechet = new JLabel("Dechet :");
		 lblDechet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblDechet.setBounds(294, 66, 74, 24);
		 layeredPane_1.add(lblDechet);
		 
		 JLabel label = new JLabel("Sous-Categorie Mp");
		 label.setBounds(10, 13, 144, 24);
		 layeredPane_1.add(label);
		 label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  soucategoriempcombo = new JComboBox();
		  soucategoriempcombo.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		

		 		

		 		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  			categoriempcombo.addItem("");
  		  				while(i<listecategoriemp.size())
  		  				{
  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  				
  		  			}else
  		  			{
  		  			listecategoriemp.clear();
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			comboMP.removeAllItems();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  comboMP.removeAllItems();
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		 	
		  		
		  		
		  		
		  	}
		  });
		 soucategoriempcombo.setBounds(117, 13, 184, 24);
		 layeredPane_1.add(soucategoriempcombo);
		 
		 JLabel label_1 = new JLabel("Categorie Mp");
		 label_1.setBounds(330, 13, 80, 26);
		 layeredPane_1.add(label_1);
		 
		  categoriempcombo = new JComboBox();
		  categoriempcombo.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		


		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  				if(matierePremier.getCode().contains(Constantes.CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE))
  		  				{
  		  				comboMP.addItem(matierePremier.getNom());
  		  		  		mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  		  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				}
  		  		
  		  				
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			
  		  			listeMatierePremiereCombo.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listeMatierePremiereCombo.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		  		
		  		
		  		
		  	}
		  });
		 categoriempcombo.setBounds(405, 14, 208, 24);
		 layeredPane_1.add(categoriempcombo);
		 
		 JLabel lblCodeDechet = new JLabel("Code Dechet");
		 lblCodeDechet.setBounds(623, 13, 83, 24);
		 layeredPane_1.add(lblCodeDechet);
		 lblCodeDechet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 txtCodeMP = new JTextField();
		 txtCodeMP.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		

				

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					MatierePremier mp=MapCodeMatierPremiere.get(txtCodeMP.getText().toUpperCase());
					if(mp!=null)
					{
						comboMP.setSelectedItem(mp.getNom());
					}else
					{
						JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
						return;
					}
					
	      		}
			
							
		 		
		 		
		 	}
		 });
		 txtCodeMP.setBounds(716, 13, 99, 26);
		 layeredPane_1.add(txtCodeMP);
		 txtCodeMP.setText("");
		 txtCodeMP.setColumns(10);
		 
		 JLabel lblNomDechet = new JLabel("Nom Dechet :");
		 lblNomDechet.setBounds(825, 13, 74, 24);
		 layeredPane_1.add(lblNomDechet);
		 lblNomDechet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  comboMP = new JComboBox();
		  comboMP.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		


	  		 	
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
  		 				if(matierePremier!=null)
  		 				{
  		 					txtCodeMP.setText(matierePremier.getCode());
  		 				}
  		 			
  		 				
  		 					
  		 				
  		 			}else
  		 			{
  		 				txtCodeMP.setText(Constantes.CODE_MP);
  		 			}
  		 			
  		 				
  		 			
  		 		}else
  		 		{
  		 			txtCodeMP.setText(Constantes.CODE_MP);
  		 		}
  		 		
  		 		
  		 		
  		 		
  		 		
  		 	
		 		
		 		
		 		
		 	
		  		
		  	}
		  });
		 comboMP.setBounds(898, 13, 314, 24);
		 layeredPane_1.add(comboMP);
		 comboMP.setSelectedIndex(-1);
		 
		  bttnAjouter = new JButton();
		 bttnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

					
					try {
			 			
			 	 		MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
			 	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
			 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				 		
			 	 		if(magasinDechet==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
				 			return;
			 	 		}
			 	 		
			 	 		if(matierePremier==null)
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Mp Dans la Liste des couts MP !!!!");
				 			return;
				 		}else if(txtdechet.getText().equals("") )
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité dechet SVP !!!!");
				 			return;
				 		}
				 		
				 		else 
				 		{
				 			
				 			boolean existe=false;


							for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
				 			{
				 				
				 				if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() )
				 				{
				 					if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null)
				 					{
				 						
				 						if(fournisseurMP!=null)
				 						{
				 							
				 							if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
				 							{
				 								existe=true;
				 							}
				 							
				 							
				 						}
				 						
				 						
				 						
				 					}else
				 					{
				 						
				 						if(fournisseurMP==null)
				 						{
				 							
				 							existe=true;
				 							
				 						}
				 						
				 					
				 						
				 					}
				 					
				 				
				 					
				 					
				 					
				 				}
				 					
				 				
				 				
				 			}
				 			
							if(existe==true)
				 			{
				 				JOptionPane.showMessageDialog(null, "MP Desja entrer  Veuillez le Modifier SVP");
				 				return;
				 				
				 			}else
				 			{
				 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=new DetailManqueDechetFournisseur();
				 				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();

				 				detailManqueDechetFournisseur.setPrix(new BigDecimal(0));
				 				
				 				detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
				 				detailTransferStockMP.setQuantite(new BigDecimal(txtdechet.getText()));
				 				
				 				detailTransferStockMP.setMagasinDestination(magasinDechet);
				 				if(fournisseurMP!=null)
				 				{
				 					detailTransferStockMP.setFournisseur(fournisseurMP);
				 				}
				 			
				 				detailTransferStockMP.setMatierePremier(matierePremier);
				 				detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
				 				detailTransferStockMP.setPrixUnitaire(new BigDecimal(0));
				 				listDetailTransfertStockMP.add(detailTransferStockMP);
				 				
				 				
				 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
				 				if(fournisseurMP!=null)
				 				{
				 					detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
				 				}
				 				
				 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
				 				detailManqueDechetFournisseur.setManquedechetfournisseur(newmanqueDechetFournisseur);
	                            listDetailManqueDechetFournisseur.add(detailManqueDechetFournisseur)	;	
	                        	
	                         afficher_tableMP_Total(listDetailManqueDechetFournisseur);
				 				Vider();

				 			}
				 			
				 			
				 			


				 		
				 		
				 		
				 		
				 		
				 		
				 		
				 		}
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Quantite Dechet ou Prix Dechet doit etre en chiffre SVP !!!!!");
					}
			
					
					
				
				
			
		 		
		 		
}
		 });
		 bttnAjouter.setIcon(imgAjouter);
		 bttnAjouter.setBounds(1279, 213, 113, 31);
		 add(bttnAjouter);
		 bttnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		if(listDetailManqueDechetFournisseur.size()!=0)
		 		{
		 			
		 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());




			 		String codeTransfert=Utils.genererCodeTransfer(magasinDechet.getDepot().getCode(), Constantes.TYPE_DECHET);
			 	
			 			
			 			transferStockMPTmp.setCodeTransfer(codeTransfert);
			 			transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
			 			transferStockMPTmp.setDate(new Date());
			 			transferStockMPTmp.setDateTransfer(dateChooserdechet.getDate());
			 			transferStockMPTmp.setDepot(magasinDechet.getDepot());
			 			//transferStockMPTmp.setListDetailTransferMP(listDetailTransfertStockMP);
			 			transferStockMPTmp.setStatut(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 			transferStockMPDAO.add(transferStockMPTmp);
			 			 BigDecimal montantTotal=BigDecimal.ZERO;
                         BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                         BigDecimal montantTotalEmballage=BigDecimal.ZERO;
			 			for(int i=0;i<listDetailTransfertStockMP.size();i++)
						{
			 				listDetailTransfertStockMP.get(i).setMagasinDestination(magasinDechet);
							detailTransferMPDAO.add(listDetailTransfertStockMP.get(i));
							if(listDetailTransfertStockMP.get(i).getFournisseur()!=null)
							{
								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(listDetailTransfertStockMP.get(i).getMatierePremier().getId(), magasinDechet.getId(), listDetailTransfertStockMP.get(i).getFournisseur().getId());
							if(stockMP!=null)
							{
								
								stockMP.setStock(stockMP.getStock().add(listDetailTransfertStockMP.get(i).getQuantite()));
								stockMPDAO.edit(stockMP);
							
							}else
							{
								StockMP stockMPTmp=new StockMP();
								stockMPTmp.setFournisseurMP(listDetailTransfertStockMP.get(i).getFournisseur());
								stockMPTmp.setStock(listDetailTransfertStockMP.get(i).getQuantite());
								stockMPTmp.setMatierePremier(listDetailTransfertStockMP.get(i).getMatierePremier());
								stockMPTmp.setMagasin(magasinDechet);
								stockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
								stockMPDAO.add(stockMPTmp);
							}
							
							
							
							}else
							{
								StockMP stockMP=stockMPDAO.findStockByMagasinMP(listDetailTransfertStockMP.get(i).getMatierePremier().getId(), magasinDechet.getId());
								
								if(stockMP!=null)
								{
									
									stockMP.setStock(stockMP.getStock().add(listDetailTransfertStockMP.get(i).getQuantite()));
									stockMPDAO.edit(stockMP);
								
								}else
								{
									StockMP stockMPTmp=new StockMP();
									
									stockMPTmp.setStock(listDetailTransfertStockMP.get(i).getQuantite());
									stockMPTmp.setMatierePremier(listDetailTransfertStockMP.get(i).getMatierePremier());
									stockMPTmp.setMagasin(magasinDechet);
									stockMPTmp.setPrixUnitaire(BigDecimal.ZERO);
									stockMPDAO.add(stockMPTmp);
								}
								
								
								
							}
							
							
							
							if(listDetailTransfertStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		     			{
	  		     				montantTotalEnvrac=montantTotalEnvrac.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdechet.getDate()))));
	  		     				
	  		     			}else
	  		     			{
	  		     				montantTotalEmballage=montantTotalEmballage.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdechet.getDate()))));
	  		     			}
	  		     			montantTotal=montantTotal.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdechet.getDate()))));
							
							
						}
			 			
			 			listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
	  		     		
	  		     		if(statistiqueFinanciaireMPTmp!=null)
	  		     		{
	  		     			
	  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
	  		     			
	  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
	  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().add(montantTotalEmballage));
	  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().add(montantTotalEnvrac));
	  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
	  		     			statistiqueFinanciaireMP.setCodeTransfer(codeTransfert);
	  		     			statistiqueFinanciaireMP.setDate(new Date());
	  		     			statistiqueFinanciaireMP.setDateOperation(dateChooserdechet.getDate());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
	  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
	  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
	  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
	  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
	  		     			statistiqueFinanciaireMP.setCoutReception(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutReception()) );
	  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
	  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF() );
	  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
	  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
	  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
	  		     		 
	  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
	  		     			 
	  		     			
	  		     		}
			 		
			 			newmanqueDechetFournisseur.setDateCreation(new Date());
			 			newmanqueDechetFournisseur.setDateDechet(dateChooserdechet.getDate());
			 			newmanqueDechetFournisseur.setNumOF(codeTransfert);
			 			newmanqueDechetFournisseur.setDetailManqueDechetFournisseur(listDetailManqueDechetFournisseur);
			 			
			 			newmanqueDechetFournisseur.setEtat(Constantes.ETAT_VALIDER);
			 						 			
			 			newmanqueDechetFournisseur.setType(TYPE_DECHET);
			 			manqueDechetFournisseurDAO.add(newmanqueDechetFournisseur);
			 			
			 			JOptionPane.showMessageDialog(null, "Dechet  Valider");
			 			listDetailManqueDechetFournisseur.clear();
			 			listDetailTransfertStockMP.clear();
			 			afficher_tableMP_Total(listDetailManqueDechetFournisseur);

			 			newmanqueDechetFournisseur=new ManqueDechetFournisseur();
			 			transferStockMPTmp=new TransferStockMP();
			 			
			 		Vider();
		 			
		 			
		 		}else
		 		{
		 			
		 			JOptionPane.showMessageDialog(null, "Veuillez entrer les quantités dechet SVP");
		 			return;
		 			
		 			
		 		}
		 		       
		 		
		 		
		
		 		
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgValider);
		 button_1.setBounds(475, 622, 104, 31);
		 add(button_1);
				listMatierePremiere=matierePremiereDAO.findAllDechetMP();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					comboMP.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				
				 btnModifier = new JButton();
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
if(tableDechetFournisseur.getSelectedRow()!=-1)
{
	

		

	
	try {
			
	 		MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
			Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
	 		
 	 		if(magasinDechet==null)
 	 		{
 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
	 			return;
 	 		}
 	 		
 		if(matierePremier==null)
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez Selection  MP !!!!");
 			return;
 		}else if(txtdechet.getText().equals("") )
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez entrer la manque ou dechet SVP !!!!");
 			return;
 		}
 		
 		else 
 		{


 			DetailManqueDechetFournisseur detailManqueDechetFournisseurTmp=listDetailManqueDechetFournisseur.get(tableDechetFournisseur.getSelectedRow());
 			
 			DetailTransferStockMP detailTransferStockMPTmp=listDetailTransfertStockMP.get(tableDechetFournisseur.getSelectedRow());


 			boolean existe=false;
 			BigDecimal quantitedechet=BigDecimal.ZERO;
	 		BigDecimal quantitemanque=BigDecimal.ZERO;
			for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() )
 	 				{
 						if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null)
 						{
 							
 							
 							if(fournisseurMP!=null)
 							{
 								
 								if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
 								{
 									existe=true;
 									
 								}
 								
 								
 							}
 							
 							
 						}else
 						{
 							
 							if(fournisseurMP==null)
 							{
 								
 								existe=true;
 								
 							}
 							
 							
 							
 							
 						}
 	 					
 	 					
 	 				}

 						
 					
 				}
 			
 					
 				
 			}
 			
			if(existe==true)
 			{
 				JOptionPane.showMessageDialog(null, "MP Desja entrer  Veuillez le Modifier SVP");
 				return;
 				
 			}else
 			{
 			
 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(tableDechetFournisseur.getSelectedRow());
 				
 		DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(tableDechetFournisseur.getSelectedRow());
 				
 		detailManqueDechetFournisseur.setPrix(new BigDecimal(0));
 						
 						detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
 						detailTransferStockMP.setQuantite(new BigDecimal(txtdechet.getText()));
 						
 			
 				


 		
 				if(fournisseurMP!=null)
 				{
 					detailTransferStockMP.setFournisseur(fournisseurMP);
 				}
 				
 				detailTransferStockMP.setMagasinDestination(magasinDechet);
 				detailTransferStockMP.setMatierePremier(matierePremier);
 				detailTransferStockMP.setPrixUnitaire(new BigDecimal(0));
 				
 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
 				
 				if(fournisseurMP!=null)
 				{
 					detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
 				}
 			
 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
 				
                listDetailManqueDechetFournisseur.set(tableDechetFournisseur.getSelectedRow(),detailManqueDechetFournisseur)	;	
                listDetailTransfertStockMP.set(tableDechetFournisseur.getSelectedRow(),detailTransferStockMP)	;	
              
            afficher_tableMP_Total(listDetailManqueDechetFournisseur);
 				Vider();

 			}
 			
 			
 			
 	
 		
 		
 		}
		
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "dechet ou manque doit etre en chiffre SVP !!!!!");
	}

	
	



	
	

	
	
	
}
						
							
					}
				});
				btnModifier.setText("Modifier");
				btnModifier.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnModifier.setBounds(1279, 270, 113, 31);
				btnModifier.setEnabled(false);
				add(btnModifier);
				
				 btnSupprimer = new JButton("Supprimer");
				btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableDechetFournisseur.getSelectedRow()!=-1)
						{
														
						DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseur.get(tableDechetFournisseur.getSelectedRow());
						
						DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(tableDechetFournisseur.getSelectedRow());
						
						
							listDetailTransfertStockMP.remove(tableDechetFournisseur.getSelectedRow());
							listDetailManqueDechetFournisseur.remove(tableDechetFournisseur.getSelectedRow());
							afficher_tableMP_Total(listDetailManqueDechetFournisseur);
						
							Vider();
							
						
								
						}
						
						
						
					}
				});
				btnSupprimer.setBounds(1279, 330, 113, 36);
				btnSupprimer.setEnabled(false);
				add(btnSupprimer);
				
				JButton btnVider = new JButton();
				btnVider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Vider();
						
					}
				});
				btnVider.setText("Vider");
				btnVider.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnVider.setBounds(496, 213, 113, 31);
				add(btnVider);
				
				listFournisseur=fournisseurMPDAO.findAll();
				combofournisseur.addItem("");
				for(int j=0;j<listFournisseur.size();j++)
				{
					FournisseurMP fournisseurMP=listFournisseur.get(j);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				
			
				
			
				
				
if (AuthentificationView.utilisateur.getLogin().equals("admin")) {
	
	listDepot =depotdao.findAll();
	combodepot.removeAllItems();
	combodepot.addItem("");
	
	for(int d=0;d<listDepot.size();d++)
	{
		
	Depot depot=listDepot.get(d);
	combodepot.addItem(depot.getLibelle());
	mapDepot.put(depot.getLibelle(), depot);
		
		
		
		
	}
	


} else {
	Depot depot = depotdao.findByCode(AuthentificationView.utilisateur.getCodeDepot());
	
	if (depot != null) {
		combodepot.removeAllItems();
		combodepot.addItem("");
		combodepot.addItem(depot.getLibelle());
		mapDepot.put(depot.getLibelle(), depot);


	}
}	
				
int i=0;
  while(i<listsubcategoriemp.size())
  {
	  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
	  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
	  i++;
  }		
				
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			String fournisseur=""; 
				 
			if(detailManqueDechetFournisseur.getFourniseur()!=null)
			{
				fournisseur=detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur();
			}
			
				Object []ligne={detailManqueDechetFournisseur.getMatierePremier().getNom(),fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseur.getQuantiteDechet())};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	








void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première","Fournisseur", "Quantite Dechet"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableDechetFournisseur.setModel(modeleMP1); 
		 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(160);
		 tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableDechetFournisseur.getColumnModel().getColumn(2).setPreferredWidth(60);


}






public void Vider()
{
	txtdechet.setText("");
	
	soucategoriempcombo.setSelectedIndex(-1);
	categoriempcombo.setSelectedIndex(-1);
	
	combofournisseur.setSelectedIndex(-1);
	
	bttnAjouter.setEnabled(true);
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
}







}

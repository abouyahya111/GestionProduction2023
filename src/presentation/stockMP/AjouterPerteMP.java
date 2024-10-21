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
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.PerteMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
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
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.PerteMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTransferStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.PerteMP;
import dao.entity.Production;
import dao.entity.Sequenceur;
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


public class AjouterPerteMP extends JLayeredPane implements Constantes{
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
	private List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	private List<DetailPerteMP> listDetailPerteMPImprimer=new ArrayList<DetailPerteMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	private Map< String, MotifPerteMP> mapMotifPerteMP = new HashMap<>();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 List<Magasin> listMagasinMP=new ArrayList<Magasin>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<MotifPerteMP> listMotifMP =new ArrayList<MotifPerteMP>();
	private JTextField txtdechet;
	
	private JComboBox combofournisseur = new JComboBox();
	private PerteMPDAO PerteMPDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private PerteMP newPerteMP =new PerteMP();
	private TransferStockMP transferStockMPTmp=new TransferStockMP() ;
	JComboBox comboMagasinDechet = new JComboBox();
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private Map< String, Magasin> MapmagasinMP = new HashMap<>();
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
		private MotifPerteMPDAO motifPerteMPDAO;
		private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
		 JButton bttnAjouter = new JButton();
		 JButton btnModifier = new JButton();
		 JButton btnSupprimer = new JButton("Supprimer");
		 JComboBox comboMotif = new JComboBox();
		 JComboBox comboMagasin = new JComboBox();
		 StockMPDAO stockMPDAO;
		 private JTextField txtnumbon;
		 
		 private SequenceurDAO sequenceurDAO;
		 JButton buttonImprimer = new JButton();
		 
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterPerteMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1427, 825);
        try{
        	
        	
        	
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	PerteMPDAO=new PerteMPDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	detailTransferMPDAO= new DetailTransferMPDAOImpl();
        	depotdao = new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	motifPerteMPDAO=new MotifPerteMPDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl() ;
        	listsubcategoriemp=subcategoriempdao.findAll();
        	listMotifMP=motifPerteMPDAO.findAll();
        	sequenceurDAO=new SequenceurDAOImpl();
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
				  		     			MotifPerteMP motifPerteMP=	mapMotifPerteMP.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),3).toString());
				  		     					if(motifPerteMP!=null)
				  		     					{
				  		     						
                                                 comboMotif.setSelectedItem(motifPerteMP.getMotif());
                                                 
				  		     					}
				  		     					
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
				  			     			"Matière Première","Fournisseur", "Quantite Dechet","Motif"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false
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
				  		     	layeredPane.setBounds(9, 11, 1367, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date Dechet:");
				  		     	lblDateDebut.setBounds(920, 19, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		 
		  dateChooserdechet = new JDateChooser();
		 dateChooserdechet.setLocale(Locale.FRANCE);
		 dateChooserdechet.setDateFormatString("dd/MM/yyyy");
		 dateChooserdechet.setBounds(993, 17, 155, 26);
		 layeredPane.add(dateChooserdechet);
		 
		 JLabel lblMagasinDechet = new JLabel("Magasin Dechet :");
		 lblMagasinDechet.setBounds(595, 15, 97, 24);
		 layeredPane.add(lblMagasinDechet);
		 lblMagasinDechet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		  comboMagasinDechet = new JComboBox();
		  comboMagasinDechet.setBounds(702, 18, 208, 24);
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
			 						listMagasinMP = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
			 						int k = 0;
			 						int d = 0;
			 						comboMagasinDechet.addItem("");
			 						comboMagasin.addItem("");
			 						
			 						while (k < listMagasinDechetMP.size()) {
			 							Magasin magasin = listMagasinDechetMP.get(k);

			 							comboMagasinDechet.addItem(magasin.getLibelle());

			 							MapmagasinDechetMP.put(magasin.getLibelle(), magasin);

			 							k++;

			 						}
			 						
			 						
			 						
			 						while (d < listMagasinMP.size()) {
			 							Magasin magasinTmp = listMagasinMP.get(d);

			 							comboMagasin.addItem(magasinTmp.getLibelle());

			 							MapmagasinMP.put(magasinTmp.getLibelle(), magasinTmp);

			 							d++;

			 						}
			 						
			 						
			 						

			 					}
			 				
			 		 		
			 		 		}else
			 		 		{
			 		 			comboMagasinDechet.removeAllItems();
			 		 			comboMagasinDechet.addItem("");
			 		 			
			 		 			comboMagasin.removeAllItems();
			 		 			comboMagasin.addItem("");
			 		 			
			 		 		}
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 		
			 		 	
					 		
					 		
					 	}
					 });
					 combodepot.addItemListener(new ItemListener() {
					 	public void itemStateChanged(ItemEvent arg0) {
}
					 	
					 	
					 	
					 	
					 });
					combodepot.setBounds(58, 11, 202, 27);
					layeredPane.add(combodepot);
					
					JLabel lblMagasin = new JLabel("Magasin :");
					lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblMagasin.setBounds(270, 12, 64, 24);
					layeredPane.add(lblMagasin);
					
					 comboMagasin = new JComboBox();
					comboMagasin.setBounds(336, 15, 249, 24);
					layeredPane.add(comboMagasin);
					
					JLabel lblBonN = new JLabel("Bon N\u00B0 :");
					lblBonN.setFont(new Font("Tahoma", Font.PLAIN, 11));
					lblBonN.setBounds(1158, 20, 74, 24);
					layeredPane.add(lblBonN);
					
					txtnumbon = new JTextField();
					txtnumbon.setColumns(10);
					txtnumbon.setBounds(1202, 19, 155, 26);
					layeredPane.add(txtnumbon);
					txtnumbon.setEnabled(false);
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_1.setBounds(9, 98, 1367, 108);
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
		 
		 JLabel lblDechet = new JLabel("Quantite :");
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
  		  			comboMP.addItem(matierePremier.getNom());
  		  		mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
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
		 
		 JLabel label_2 = new JLabel("Code MP:");
		 label_2.setBounds(637, 13, 67, 24);
		 layeredPane_1.add(label_2);
		 label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
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
		 txtCodeMP.setBounds(697, 13, 118, 26);
		 layeredPane_1.add(txtCodeMP);
		 txtCodeMP.setText("");
		 txtCodeMP.setColumns(10);
		 
		 JLabel label_3 = new JLabel("MP :");
		 label_3.setBounds(843, 13, 37, 24);
		 layeredPane_1.add(label_3);
		 label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
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
		 comboMP.setBounds(877, 13, 335, 24);
		 layeredPane_1.add(comboMP);
		 comboMP.setSelectedIndex(-1);
		 
		 JLabel lblPrix = new JLabel("Motif :");
		 lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblPrix.setBounds(473, 68, 47, 24);
		 layeredPane_1.add(lblPrix);
		 
		  bttnAjouter = new JButton();
		 bttnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

					
					try {
			 			
			 	 		MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
			 	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
			 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
			 	 		Magasin magasinMP=MapmagasinMP.get(comboMagasin.getSelectedItem());
				 		
			 	 		if(magasinDechet==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
				 			return;
			 	 		}
			 	 		
			 	 		if(magasinMP==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin MP SVP !!!!");
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
				 		}else if(comboMotif.getSelectedItem().equals("") )
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez selectionner le Motif de Perte SVP !!!!");
				 			return;
				 		}
				 		
				 		else 
				 		{
				 			
				 			boolean existe=false;
                         MotifPerteMP motifPerteMP=mapMotifPerteMP.get(comboMotif.getSelectedItem());

							for(int i=0;i<listDetailPerteMP.size();i++)
				 			{
				 				
				 				if(listDetailPerteMP.get(i).getMatierePremier().getId()==matierePremier.getId() )
				 				{
				 					if(listDetailPerteMP.get(i).getFournisseurMP()!=null)
				 					{
				 						
				 						if(fournisseurMP!=null)
				 						{
				 							
				 							if(listDetailPerteMP.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
				 							{
				 								if(listDetailPerteMP.get(i).getMotifPerteMP().getId()==motifPerteMP.getId())
					 							{
				 									
				 									existe=true;
				 									
					 							}
				 								
				 								
				 								
				 								
				 							}
				 							
				 							
				 						}
				 						
				 						
				 						
				 					}else
				 					{
				 						
				 						if(fournisseurMP==null)
				 						{
				 							if(listDetailPerteMP.get(i).getMotifPerteMP().getId()==motifPerteMP.getId())
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
				 				DetailPerteMP detailPerteMP=new DetailPerteMP();
				 				
				 				detailPerteMP.setQuantite (new BigDecimal(txtdechet.getText()));
				 				
				 				detailPerteMP.setMotifPerteMP(motifPerteMP);
				 				
				 				if(matierePremier.getPrix()!=null)
				 				{
				 					
				 					if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)>0)
				 					{
				 						detailPerteMP.setPrix(matierePremier.getPrix());
				 						
				 					}else
				 					{
				 						
				 						if(fournisseurMP==null)
					 					{
					 						
						 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
						 					if(stockMP!=null)
						 					{
						 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
						 					}else
						 					{
						 						detailPerteMP.setPrix(BigDecimal.ZERO);
						 					}
						 					
						 					
					 						
					 					}else
					 					{
					 						
					 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
					 						if(stockMP!=null)
						 					{
						 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
						 					}else
						 					{
						 						detailPerteMP.setPrix(BigDecimal.ZERO);
						 					}
					 						
					 					}
				 						
				 						
				 						
				 					}
				 					
				 					
				 					
				 				}else
				 				{
				 					if(fournisseurMP==null)
				 					{
				 						
					 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
					 					if(stockMP!=null)
					 					{
					 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
					 					}else
					 					{
					 						detailPerteMP.setPrix(BigDecimal.ZERO);
					 					}
					 					
				 						
				 					}else
				 					{
				 						
				 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
				 						if(stockMP!=null)
					 					{
					 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
					 					}else
					 					{
					 						detailPerteMP.setPrix(BigDecimal.ZERO);
					 					}
				 						
				 					}
				 					
				 					
				 					
				 				}
				 				
				 				if(fournisseurMP!=null)
				 				{
				 					detailPerteMP.setFournisseurMP(fournisseurMP);
				 				}
				 				
				 				detailPerteMP.setMatierePremier(matierePremier);
				 				detailPerteMP.setPerteMP(newPerteMP);
	                            listDetailPerteMP.add(detailPerteMP)	;	
	                        	
	                         afficher_tableMP_Total(listDetailPerteMP);
				 				Vider();

				 			}
				 			
				 			
				 			


				 		
				 		
				 		
				 		
				 		
				 		
				 		
				 		}
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Quantite de perte doit etre en chiffre SVP !!!!!");
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
		 		
		 		
		 		if(listDetailPerteMP.size()!=0)
		 		{
		 			
		 		
		 			
		 			
		 			
		 			
		 			
		 			
		 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		 	 		Magasin magasinSource=MapmagasinMP.get(comboMagasin.getSelectedItem());
		 	 		
		 	 		if(magasinDechet==null || magasinSource==null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Veuillez le magasin SVP");
			 			return;
		 	 		}
		 	 		
		 	 		if(dateChooserdechet.getDate()==null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP");
			 			return;
		 	 		}
		 	 		
		 	 		
		 	 		PerteMP perteMP=PerteMPDAO.findByDateByMagasin(dateChooserdechet.getDate(), magasinDechet, ETAT_INVALIDER);
		 	 		
		 	 		if(perteMP!=null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Le Perte MP  déja Existant Veuillez le Modifier");
			 			return;
		 	 		}
		 	 		
		 	 		
	PerteMP perteMPTmp=PerteMPDAO.findByDateByMagasin(dateChooserdechet.getDate(), magasinDechet, ETAT_VALIDER);
		 	 		
		 	 		if(perteMPTmp!=null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Le Perte MP déja Valider dans l'Inventaire Veuillez Contacter l'Administration");
			 			return;
		 	 		}
		 	 		
		 	 		

newPerteMP.setDateoperation(dateChooserdechet.getDate());
newPerteMP.setNumPerte(txtnumbon.getText());
newPerteMP.setDepot(magasinDechet.getDepot());
newPerteMP.setDetailPerteMP(listDetailPerteMP);
newPerteMP.setEtat(ETAT_INVALIDER);
newPerteMP.setMagasin(magasinDechet);
newPerteMP.setMagasinSource(magasinSource);
PerteMPDAO.add(newPerteMP);
			 			
			 			JOptionPane.showMessageDialog(null, "Perte MP  Ajouter Avec Succée");
			 			
			 			
			 			
/////////////////////////////////////////////////////////////////////// Impression  ///////////////////////////////////////////////////////////////////////////////			 			

			 			PerteMP perteMPTmpTmp=listDetailPerteMP.get(0).getPerteMP();
			   			
			   			Map parameters = new HashMap();
						parameters.put("numBon", perteMPTmpTmp.getNumPerte());
								
						parameters.put("dateOperation", perteMPTmpTmp.getDateoperation());
					
						parameters.put("magasin", perteMPTmpTmp.getMagasinSource().getLibelle());
						parameters.put("depot", perteMPTmpTmp.getMagasinSource().getDepot().getLibelle());
						JasperUtils.imprimerBonSortieMPSaisir(listDetailPerteMP,parameters);
			   			
			 			
			 			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			 			
					
						listDetailPerteMPImprimer.clear();
						listDetailPerteMPImprimer.addAll(listDetailPerteMP);
						
						
						listDetailPerteMP.clear();
			 			afficher_tableMP_Total(listDetailPerteMP);
			 			newPerteMP=new PerteMP();
			 			
			 			 Sequenceur sequenceur=sequenceurDAO.findByCode(Constantes.CODE_PERTE);
			 			  
			 			  if(sequenceur!=null)
			 			  {
			 				  txtnumbon.setText(Constantes.CODE_PERTE+(sequenceur.getValeur()+1)); 
			 				 sequenceur.setValeur(sequenceur.getValeur()+1);
			 				sequenceurDAO.edit(sequenceur);
			 				  
			 			  }else
			 			  {
			 				  
			 				  
			 				 Sequenceur sequenceurTmp=new Sequenceur(); 
			 				sequenceurTmp.setCode(Constantes.CODE_PERTE); 
			 				sequenceurTmp.setLibelle(Constantes.TYPE_PERTE);
			 				sequenceurTmp.setValeur(1);
			 				sequenceurDAO.add(sequenceurTmp);
			 				  txtnumbon.setText(Constantes.CODE_PERTE+1); 
			 				  
			 			  }
			 			  
			 			  
			 			  
			 			  
			 			  
			 			 
			 			
			 			
			 			
			 		Vider();
			 		
			 		
			 		 Sequenceur newsequenceur=sequenceurDAO.findByCode(Constantes.CODE_PERTE);
		 			  
		 			  if(newsequenceur!=null)
		 			  {
		 				  txtnumbon.setText(Constantes.CODE_PERTE+(newsequenceur.getValeur()+1)); 
		 				
		 				  
		 			  }else
		 			  {
		 				  
		 			
		 				  txtnumbon.setText(Constantes.CODE_PERTE+1); 
		 				  
		 			  }
			 		
			 		buttonImprimer.setEnabled(true);
			 		dateChooserdechet.setDate(null);
		 			comboMagasinDechet.setSelectedItem("");;
		 			comboMagasin.setSelectedItem("");;
		 			combodepot.setSelectedItem("");;
		 		}else
		 		{
		 			
		 			JOptionPane.showMessageDialog(null, "Veuillez entrer les quantités dechet SVP");
		 			return;
		 			
		 			
		 		}
		 		       
		 		
		 		
		
		 		
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgValider);
		 button_1.setBounds(690, 617, 104, 31);
		 add(button_1);
				listMatierePremiere=matierePremiereDAO.findAll();
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
			Magasin magasinMP=MapmagasinMP.get(comboMagasin.getSelectedItem());
	 		
 	 	
 	 		
			if(magasinMP==null)
 	 		{
 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin MP SVP !!!!");
	 			return;
 	 		}
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
 		}else if(comboMotif.getSelectedItem().equals("") )
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez selectionner le Motif SVP !!!!");
 			return;
 		}
 		
 		else 
 		{


 			
 			
MotifPerteMP motifPerteMP=mapMotifPerteMP.get(comboMotif.getSelectedItem());

 			boolean existe=false;
 			BigDecimal quantitedechet=BigDecimal.ZERO;
	 		BigDecimal quantitemanque=BigDecimal.ZERO;
			for(int i=0;i<listDetailPerteMP.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailPerteMP.get(i).getMatierePremier().getId()==matierePremier.getId() )
 	 				{
 						if(listDetailPerteMP.get(i).getFournisseurMP()!=null)
 						{
 							
 							
 							if(fournisseurMP!=null)
 							{
 								
 								if(listDetailPerteMP.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
 								{
 									if(listDetailPerteMP.get(i).getMotifPerteMP().getId()==motifPerteMP.getId())
 									{
 										existe=true;
 									}
 									
 									
 								}
 								
 								
 							}
 							
 							
 						}else
 						{
 							
 							if(fournisseurMP==null)
 							{
 								
 								if(listDetailPerteMP.get(i).getMotifPerteMP().getId()==motifPerteMP.getId())
									{
										existe=true;
									}
 								
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
 			
 				DetailPerteMP detailPerteMP=listDetailPerteMP.get(tableDechetFournisseur.getSelectedRow()); 				
 				
 						
 				detailPerteMP.setQuantite(new BigDecimal(txtdechet.getText()));
 				detailPerteMP.setMotifPerteMP(motifPerteMP);
 				detailPerteMP.setMatierePremier(matierePremier);
 				if(fournisseurMP!=null)
 				{
 					detailPerteMP.setFournisseurMP (fournisseurMP);
 				}
 				
 				
 				if(matierePremier.getPrix()!=null)
 				{
 					
 					if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)>0)
 					{
 						detailPerteMP.setPrix(matierePremier.getPrix());
 						
 					}else
 					{
 						
 						
 						if(fournisseurMP==null)
 	 					{
 	 						
 		 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
 		 					if(stockMP!=null)
 		 					{
 		 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
 		 					}else
 		 					{
 		 						detailPerteMP.setPrix(BigDecimal.ZERO);
 		 					}
 		 					
 	 						
 	 					}else
 	 					{
 	 						
 	 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
 	 						if(stockMP!=null)
 		 					{
 		 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
 		 					}else
 		 					{
 		 						detailPerteMP.setPrix(BigDecimal.ZERO);
 		 					}
 	 						
 	 					}
 						
 						
 						
 						
 					}
 					
 					
 					
 				}else
 				{
 					if(fournisseurMP==null)
 					{
 						
	 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
	 					if(stockMP!=null)
	 					{
	 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
	 					}else
	 					{
	 						detailPerteMP.setPrix(BigDecimal.ZERO);
	 					}
	 					
 						
 					}else
 					{
 						
 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
 						if(stockMP!=null)
	 					{
	 						detailPerteMP.setPrix(stockMP.getPrixUnitaire());
	 					}else
	 					{
	 						detailPerteMP.setPrix(BigDecimal.ZERO);
	 					}
 						
 					}
 					
 					
 					
 				}
 			
 				
 				
                listDetailPerteMP.set(tableDechetFournisseur.getSelectedRow(),detailPerteMP)	;	
              
            afficher_tableMP_Total(listDetailPerteMP);
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
														
						DetailPerteMP detailPerteMP=	listDetailPerteMP.get(tableDechetFournisseur.getSelectedRow());
						
						
						
							listDetailPerteMP.remove(tableDechetFournisseur.getSelectedRow());
							afficher_tableMP_Total(listDetailPerteMP);
						
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
				
				 comboMotif = new JComboBox();
				comboMotif.setSelectedIndex(-1);
				comboMotif.setBounds(510, 67, 255, 24);
				layeredPane_1.add(comboMotif);
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
  
  comboMotif.addItem("");
  
   buttonImprimer = new JButton();
   buttonImprimer.addActionListener(new ActionListener() {
   	public void actionPerformed(ActionEvent arg0) {
   		
   		if(listDetailPerteMPImprimer.size()!=0)
 		{
   			PerteMP perteMPTmp=listDetailPerteMPImprimer.get(0).getPerteMP();
   			
   			Map parameters = new HashMap();
			parameters.put("numBon", perteMPTmp.getNumPerte());
					
			parameters.put("dateOperation", perteMPTmp.getDateoperation());
		
			parameters.put("magasin", perteMPTmp.getMagasinSource().getLibelle());
			parameters.put("depot", perteMPTmp.getMagasinSource().getDepot().getLibelle());
			JasperUtils.imprimerBonSortieMPSaisir(listDetailPerteMPImprimer,parameters);
   			
   			
			
   			
 		}
 			
   			
   		
   		
   		
   	}
   });
  buttonImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
  buttonImprimer.setBounds(555, 617, 104, 31);
  buttonImprimer.setIcon(imgImprimer);
  add(buttonImprimer);
  for(int j=0;j<listMotifMP.size();j++)
  {
	  
	MotifPerteMP motifPerteMP=  listMotifMP.get(j);
	  
	comboMotif.addItem(motifPerteMP.getMotif()); 
	  mapMotifPerteMP.put(motifPerteMP.getMotif(), motifPerteMP);
	  
  }
  
  
  
  Sequenceur sequenceur=sequenceurDAO.findByCode(Constantes.CODE_PERTE);
  
  if(sequenceur!=null)
  {
	  txtnumbon.setText(Constantes.CODE_PERTE+(sequenceur.getValeur()+1)); 
	  
	  
  }else
  {
	 
	  txtnumbon.setText(Constantes.CODE_PERTE+1); 
	  
	  
	  
	  
  }
  
  
  
  buttonImprimer.setEnabled(false);			
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailPerteMP> listDetailPerteMP)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailPerteMP.size();i++)
			{	
				
	DetailPerteMP detailPerteMotifMP=	listDetailPerteMP.get(i);
			String fournisseur=""; 
				 
			if(detailPerteMotifMP.getFournisseurMP() !=null)
			{
				fournisseur=detailPerteMotifMP.getFournisseurMP().getCodeFournisseur();
			}
			
				Object []ligne={detailPerteMotifMP.getMatierePremier().getNom(),fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailPerteMotifMP.getQuantite()),detailPerteMotifMP.getMotifPerteMP().getMotif()};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	








void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première","Fournisseur", "Quantite Dechet","Motif"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
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
	comboMotif.setSelectedItem("");
	soucategoriempcombo.setSelectedIndex(-1);
	categoriempcombo.setSelectedIndex(-1);
	
	combofournisseur.setSelectedIndex(-1);
	
	bttnAjouter.setEnabled(true);
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
}
}

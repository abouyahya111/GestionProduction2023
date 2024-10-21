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

import dao.daoImplManager.ActionMPDAOImpl;
import dao.daoImplManager.ActionPerteMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailActionPerteMPDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailPerteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MotifPerteMPDAOImpl;
import dao.daoImplManager.PerteMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ActionPerteMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailActionPerteMPDAO;
import dao.daoManager.DetailCompteMagasinierDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.PerteMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.ActionMP;
import dao.entity.ActionPerteMP;
import dao.entity.BonDePerte;
import dao.entity.CategorieMp;
import dao.entity.CompteMagasinier;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailActionPerteMP;
import dao.entity.DetailCompteMagasinier;
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
import javax.swing.JTable;


public class AjouterActionPerteMP extends JLayeredPane implements Constantes{
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
	private List<DetailActionPerteMP> listDetailAcionPerteMP=new ArrayList<DetailActionPerteMP>();
	
	private List<DetailActionPerteMP> listDetailAcionPerteMPRetourProduction=new ArrayList<DetailActionPerteMP>();
	private List<DetailActionPerteMP> listDetailAcionPerteMPTransfertMagasinDechet=new ArrayList<DetailActionPerteMP>();
	private List<DetailActionPerteMP> listDetailAcionPerteMPAvanceSurMagasinier=new ArrayList<DetailActionPerteMP>();
	
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	private Map< String, ActionMP> mapActionMP = new HashMap<>();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 List<Magasin> listMagasinMP=new ArrayList<Magasin>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<ActionMP> listActionMP =new ArrayList<ActionMP>();
	private JTextField txtdechet;
	private List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	private JComboBox combofournisseur = new JComboBox();
	private ActionPerteMPDAO ActionPerteMPDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ActionPerteMP newActionPerteMP =new ActionPerteMP();
	private ActionPerteMP oldActionPerteMP=null;
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
	 List<PerteMP> listePerteMP =new ArrayList<PerteMP>();
		List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
		private SubCategorieMPDAO subcategoriempdao;
		private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
		private Map< String, PerteMP> PerteMPMap = new HashMap<>();
		private Map< String, CategorieMp> catMap = new HashMap<>();
		private CategorieMpDAO categoriempdao;
		private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
		private ActionMPDAO actionMPDAO;
		private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
		private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
		 JButton bttnAjouter = new JButton();
		 private JButton btnAjouter;
		 JButton btnModifier = new JButton();
		 JButton btnSupprimer = new JButton("Supprimer");
		 private JComboBox comboAction= new JComboBox();
		 JComboBox comboMagasin = new JComboBox();
		 StockMPDAO stockMPDAO;
		 private PerteMPDAO PerteMPDAO;
		 private DetailPerteMPDAO detailPerteMPDAO;
		 JXTable table = new JXTable();
		 JComboBox comboCompteMagasinier = new JComboBox();
		 private Map< String, CompteMagasinier> MapCompteMagasinier = new HashMap<>();
			private List<CompteMagasinier> listComptaMagasinier =new ArrayList<CompteMagasinier>();
			private CompteMagasinierDAO compteMagasinierDAO;
			private DetailCompteMagasinierDAO detailCompteMagasinierDAO;
			private DetailActionPerteMPDAO detailActionPerteMPDAO;
			JButton btnValider = new JButton();
			 JButton btnRechercher = new JButton();
			 JComboBox comboNumBon = new JComboBox();
			 Utilisateur utilisateur;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterActionPerteMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1565, 826);
        try{
        	
        	
        	
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	ActionPerteMPDAO=new ActionPerteMPDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	depotdao = new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	actionMPDAO=new ActionMPDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl() ;
        	PerteMPDAO=new PerteMPDAOImpl();
        	detailPerteMPDAO=new DetailPerteMPDAOImpl();
        	detailActionPerteMPDAO=new DetailActionPerteMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	listActionMP=actionMPDAO.findAll();
        	compteMagasinierDAO=new CompteMagasinierDAOImpl();
         transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	
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
				  		     			ActionMP actionMP=	mapActionMP.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),4).toString());
				  		     					if(actionMP!=null)
				  		     					{
				  		     						
                                                 comboAction.setSelectedItem(actionMP.getAction());
                                                 
				  		     					}else
				  		     					{
				  		     					 comboAction.setSelectedItem("");
				  		     					 
				  		     					}
				  		     					
				  		     					
				  		     					CompteMagasinier compteMagasinier=MapCompteMagasinier.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),5).toString());
				  		     					
				  		     							if(compteMagasinier!=null)
				  		     							{
				  		     								comboCompteMagasinier.setSelectedItem(compteMagasinier.getNom());
				  		     							}else
				  		     							{
				  		     								comboCompteMagasinier.setSelectedItem("");
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
				  			     			"Matière Première","Fournisseur", "Quantite","Motif","Action","Compte Magasinier"
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
				  				scrollPane_1.setBounds(22, 423, 1410, 355);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1423, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date Dechet:");
				  		     	lblDateDebut.setBounds(1185, 16, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		 
		  dateChooserdechet = new JDateChooser();
		 dateChooserdechet.setLocale(Locale.FRANCE);
		 dateChooserdechet.setDateFormatString("dd/MM/yyyy");
		 dateChooserdechet.setBounds(1258, 14, 155, 26);
		 layeredPane.add(dateChooserdechet);
		 
		 JLabel lblMagasinDechet = new JLabel("Magasin Dechet :");
		 lblMagasinDechet.setBounds(860, 13, 97, 24);
		 layeredPane.add(lblMagasinDechet);
		 lblMagasinDechet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		  comboMagasinDechet = new JComboBox();
		  comboMagasinDechet.setBounds(967, 16, 208, 24);
		  layeredPane.add(comboMagasinDechet);
		  
		        
		        
					comboMagasinDechet.addItem(""); 		      
					
					JLabel label_4 = new JLabel("Depot  :");
					label_4.setFont(new Font("Verdana", Font.BOLD, 12));
					label_4.setBounds(277, 11, 85, 26);
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
			 							Magasin magasin = listMagasinMP.get(d);

			 							comboMagasin.addItem(magasin.getLibelle());

			 							MapmagasinMP.put(magasin.getLibelle(), magasin);

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
					combodepot.setBounds(350, 13, 202, 27);
					layeredPane.add(combodepot);
					
					JLabel lblMagasin = new JLabel("Magasin :");
					lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblMagasin.setBounds(562, 14, 64, 24);
					layeredPane.add(lblMagasin);
					
					 comboMagasin = new JComboBox();
					comboMagasin.setBounds(628, 17, 222, 24);
					layeredPane.add(comboMagasin);
					
					JLabel lblBonNum = new JLabel(" Bon Num  :");
					lblBonNum.setFont(new Font("Verdana", Font.BOLD, 12));
					lblBonNum.setBounds(0, 13, 85, 26);
					layeredPane.add(lblBonNum);
					
					 comboNumBon = new JComboBox();
					comboNumBon.setBounds(84, 15, 168, 27);
					layeredPane.add(comboNumBon);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_1.setBounds(4, 266, 1428, 108);
		 add(layeredPane_1);
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblFournisseur.setBounds(519, 26, 74, 24);
		 layeredPane_1.add(lblFournisseur);
		 
		  combofournisseur = new JComboBox();
		  combofournisseur.setEnabled(false);
		 combofournisseur.setSelectedIndex(-1);
		 combofournisseur.setBounds(586, 28, 140, 24);
		 layeredPane_1.add(combofournisseur);
		 
		 txtdechet = new JTextField();
		 txtdechet.setColumns(10);
		 txtdechet.setBounds(792, 26, 110, 26);
		 layeredPane_1.add(txtdechet);
		 
		 JLabel lblDechet = new JLabel("Quantite :");
		 lblDechet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblDechet.setBounds(736, 27, 74, 24);
		 layeredPane_1.add(lblDechet);
		 
		 JLabel label_2 = new JLabel("Code MP:");
		 label_2.setBounds(10, 28, 67, 24);
		 layeredPane_1.add(label_2);
		 label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 txtCodeMP = new JTextField();
		 txtCodeMP.setEditable(false);
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
		 txtCodeMP.setBounds(70, 28, 74, 26);
		 layeredPane_1.add(txtCodeMP);
		 txtCodeMP.setText("");
		 txtCodeMP.setColumns(10);
		 
		 JLabel label_3 = new JLabel("MP :");
		 label_3.setBounds(154, 28, 31, 24);
		 layeredPane_1.add(label_3);
		 label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  comboMP = new JComboBox();
		  comboMP.setEnabled(false);
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
		 comboMP.setBounds(188, 28, 304, 24);
		 layeredPane_1.add(comboMP);
		 comboMP.setSelectedIndex(-1);
		 
		 JLabel lblPrix = new JLabel("Action :");
		 lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblPrix.setBounds(915, 29, 47, 24);
		 layeredPane_1.add(lblPrix);
		 
		  btnAjouter = new JButton();
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

					
					try {
			 			
			 	 		MatierePremier matierePremier=mapMatierePremiere.get(comboMP.getSelectedItem());
			 	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
			 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
			 	 		Magasin magasinMP=MapmagasinMP.get(comboMagasin.getSelectedItem());
			 	 		
			 	 		DetailPerteMP detailPerteMP=listDetailPerteMP.get(table.getSelectedRow());
			 	 		
			 	 		if(detailPerteMP==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Perte SVP !!!!");
				 			return;
			 	 		}
			 	 		
				 		
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
				 		}else if(comboAction.getSelectedItem().equals("") )
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Action de Perte SVP !!!!");
				 			return;
				 		}
				 		
				 		else 
				 		{
				 			
				 			BigDecimal quantitedechet=BigDecimal.ZERO;
				 			
				 			boolean existe=false;
                         ActionMP actionMP=mapActionMP.get(comboAction.getSelectedItem());
                         CompteMagasinier compteMagasinier=null;
                         
                         if(actionMP.getAction().equals(Constantes.AVANCE_SUR_MAGASINIER))
                         {
                        	 if(comboCompteMagasinier.getSelectedItem().equals(""))
                        	 {
                        		 
                        		 JOptionPane.showMessageDialog(null, "Veuillez selectionner le Compte magasinier Pour l'action Avance Sur Magasinier SVP !!!!");
     				 			return;
                        		 
                        	 }else
                        	 {
                        		 compteMagasinier=MapCompteMagasinier.get(comboCompteMagasinier.getSelectedItem());
                        		 
                        	 }
                        	 
                        	 
                        	 
                        	 
                         }
                         
                         
                         

							for(int i=0;i<listDetailAcionPerteMP.size();i++)
				 			{
				 				
				 				if(listDetailAcionPerteMP.get(i).getMatierePremier().getId()==matierePremier.getId() )
				 				{
				 					if(listDetailAcionPerteMP.get(i).getFournisseurMP()!=null)
				 					{
				 						
				 						if(fournisseurMP!=null)
				 						{
				 							
				 							if(listDetailAcionPerteMP.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
				 							{
				 								
				 									
				 									if(detailPerteMP.getMotifPerteMP().getId()==listDetailAcionPerteMP.get(i).getDetailPerteMP().getMotifPerteMP().getId())
				 									{
				 										
				 										quantitedechet=quantitedechet.add(listDetailAcionPerteMP.get(i).getQuantite());
				 										
				 										
				 										if(listDetailAcionPerteMP.get(i).getActionMP().getId()==actionMP.getId())
							 							{
				 											existe=true;
							 							}
				 											
				 									
				 									}
				 									
				 								
				 									
					 							}
				 								
				 								
				 								
				 								
				 							}
				 							
				 							
				 						}else
				 						{

					 						
					 						if(fournisseurMP==null)
					 						{
					 							
					 								
					 								if(detailPerteMP.getMotifPerteMP().getId()==listDetailAcionPerteMP.get(i).getDetailPerteMP().getMotifPerteMP().getId())
				 									{
					 									quantitedechet=quantitedechet.add(listDetailAcionPerteMP.get(i).getQuantite());
					 									
					 									if(listDetailAcionPerteMP.get(i).getActionMP().getId()==actionMP.getId())
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
				 				DetailActionPerteMP detailActionPerteMP=new DetailActionPerteMP();
				 				
				 				if(!txtdechet.getText().equals(""))
				 				{
				 					
				 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(detailPerteMP.getQuantite())<=0)
				 					{
				 						
				 						detailActionPerteMP.setQuantite (new BigDecimal(txtdechet.getText()));
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite est supperieur au sommes des Quantite Pertes !!!!");
				 						return;
				 					}
				 					
				 					
				 					
				 					
				 				}else
				 				{
				 					JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité dechet SVP !!!!");
						 			return;
				 				}
				 				
				 				
				 				
				 				
				 				
				 				
				 				
				 				detailActionPerteMP.setActionMP(actionMP);
				 				
				 				if(matierePremier.getPrix()!=null)
				 				{
				 					
				 					if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)>0)
				 					{
				 						detailActionPerteMP.setPrix(matierePremier.getPrix());
				 						
				 					}else
				 					{
				 						
				 						if(fournisseurMP==null)
					 					{
					 						
						 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
						 					if(stockMP!=null)
						 					{
						 						detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
						 					}else
						 					{
						 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
						 					}
						 					
						 					
					 						
					 					}else
					 					{
					 						
					 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
					 						if(stockMP!=null)
						 					{
					 							detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
						 					}else
						 					{
						 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
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
					 						detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
					 					}else
					 					{
					 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
					 					}
					 					
				 						
				 					}else
				 					{
				 						
				 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
				 						if(stockMP!=null)
					 					{
				 							detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
					 					}else
					 					{
					 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
					 					}
				 						
				 					}
				 					
				 					
				 					
				 				}
				 				
				 				
				 				
				 				if(compteMagasinier!=null)
				 				{
				 					detailActionPerteMP.setCompteMagasinier(compteMagasinier);
				 				}
				 				
				 				if(fournisseurMP!=null)
				 				{
				 					detailActionPerteMP.setFournisseurMP(fournisseurMP);
				 				}
				 				detailActionPerteMP.setDetailPerteMP (detailPerteMP);				 				
				 				detailActionPerteMP.setMatierePremier(matierePremier);
				 				
				 				
				 				oldActionPerteMP=ActionPerteMPDAO.findByDateByMagasin(dateChooserdechet.getDate(), magasinDechet, ETAT_INVALIDER);
				 				
				 				if(oldActionPerteMP!=null)
				 				{
				 					detailActionPerteMP.setActionperteMP (oldActionPerteMP);
				 				}else
				 				{
				 					
				 					
				 					
				 					detailActionPerteMP.setActionperteMP (newActionPerteMP);
				 				}
				 				
				 	
				 				
				 				
	                            listDetailAcionPerteMP.add(detailActionPerteMP)	;	
	                            
	                            
	                            if(oldActionPerteMP!=null)
				 				{
	                            	
	                            	
	                            	
	                            	detailActionPerteMPDAO.add(detailActionPerteMP);
				 					
				 					
				 					
				 				}else
				 				{
				 					
				 					//newActionPerteMP.setDetailActionPerteMP(listDetailAcionPerteMP);
				 					  newActionPerteMP.setDateoperation(dateChooserdechet.getDate());
			                        	newActionPerteMP.setDepot(magasinDechet.getDepot());
			                        	newActionPerteMP.setEtat(ETAT_INVALIDER);
			                        	
			                        	newActionPerteMP.setMagasin(magasinDechet);
			                        	ActionPerteMPDAO.add(newActionPerteMP);
			                        	
			                        	detailActionPerteMPDAO.add(detailActionPerteMP);
			                        	
				 				}
	                            
	                            
	                          
	                            
	                            
	                        	
	                         afficher_tableMP_Total(listDetailAcionPerteMP);
				 				Vider();

				 			}
				 			
				 			
				 			


				 		
				 		
				 		
				 		
				 		
				 		
				 		
				 		}
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Quantite de perte doit etre en chiffre SVP !!!!!");
					}
			
						
		 		
}
		 });
		 btnAjouter.setIcon(imgAjouter);
		 btnAjouter.setBounds(1442, 450, 113, 31);
		 add(btnAjouter);
		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  btnValider = new JButton();
		 btnValider.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		if(listDetailAcionPerteMP.size()!=0)
		 		{
		 			
		 		
		 			
		 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		 	 		Magasin magasin=MapmagasinMP.get(comboMagasin.getSelectedItem());
		 	 		
		 	 		boolean QuantiteNonValider=false;
		 	 		BigDecimal quantite=BigDecimal.ZERO;
		 	 		
		 	 		if(magasinDechet==null || magasin==null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Veuillez le magasin SVP");
			 			return;
		 	 		}
		 	 		
		 	 		if(dateChooserdechet.getDate()==null)
		 	 		{
		 	 			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP");
			 			return;
		 	 		}
		 	 		
		 	 		
		 	 		listDetailAcionPerteMPRetourProduction.clear();
		 	 		listDetailAcionPerteMPTransfertMagasinDechet.clear();
		 	 		listDetailAcionPerteMPAvanceSurMagasinier.clear();
		 	 		
		 	 		for(int i=0;i<listDetailPerteMP.size();i++)
		 	 		{
		 	 			quantite=BigDecimal.ZERO;
		 	 			listDetailAcionPerteMP.clear();
		 	 			DetailPerteMP detailPerteMP=listDetailPerteMP.get(i);
		 	 			
		 	 			listDetailAcionPerteMP=detailActionPerteMPDAO.listeDetailActionPerteMPByDetailPerteMP(detailPerteMP);	
		 	 			
		 	 			if(listDetailAcionPerteMP.size()==0)
		 	 			{
		 	 				QuantiteNonValider=true;
		 	 			}
		 	 			
		 	 			for(int j=0;j<listDetailAcionPerteMP.size();j++)
		 	 			{
		 	 				
		 	 				
		 	 				DetailActionPerteMP detailActionPerteMP=listDetailAcionPerteMP.get(j);
		 	 				
		 	 				if(detailActionPerteMP.getActionMP().getAction().equals(Constantes.AVANCE_SUR_MAGASINIER))
		 	 				{
		 	 					listDetailAcionPerteMPAvanceSurMagasinier.add(detailActionPerteMP);
		 	 					
		 	 					
		 	 				}else if(detailActionPerteMP.getActionMP().getAction().equals(TRANSFERT_MAGASIN_DECHET))
		 	 				{
		 	 					listDetailAcionPerteMPTransfertMagasinDechet.add(detailActionPerteMP);
		 	 					
		 	 				}else if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
		 	 				{
		 	 					listDetailAcionPerteMPRetourProduction.add(detailActionPerteMP);
		 	 					
		 	 					
		 	 				}
		 	 				
		 	 				
		 	 				
		 	 				
		 	 				quantite=quantite.add(detailActionPerteMP.getQuantite());
		 	 				
		 	 				
		 	 				
		 	 				
		 	 			}
		 	 			
		 	 			
		 	 			if(detailPerteMP.getQuantite().compareTo(quantite)!=0)
		 	 			{
		 	 				QuantiteNonValider=true;
		 	 			}
		 	 			
		 	 			
		 	 			
		 	 			
		 	 		}
		 	 		
		 	 		
		 	 		if(QuantiteNonValider==true)
		 	 		{
		 	 			
		 	 			JOptionPane.showMessageDialog(null, "Il Vous Rester les Quantites Sans Action de Perte Veuillez Spécifier leurs Actions SVP !!!!! ");
		 	 			return;
		 	 			
		 	 		}else
		 	 		{
		 	 			
		 	 			
		 	 			TransferStockMP transfererStockMP=new TransferStockMP();
		 	 			
		 	 			Magasin magasindechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		 	 	  		
		 	 	  		String numbon="";	
		 	 	  		numbon=comboNumBon.getSelectedItem().toString();	
		 	 			PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat (numbon,dateChooserdechet.getDate(), magasindechet, ETAT_INVALIDER);
		 	 			perteMP.setEtat(ETAT_VALIDER);
		 	 		    PerteMPDAO.edit(perteMP);
		 	 			
		 	 		ActionPerteMP  ActionPerteMP=ActionPerteMPDAO.findByDateByMagasin(dateChooserdechet.getDate(), magasinDechet, ETAT_INVALIDER);
		 	 		ActionPerteMP.setEtat(ETAT_VALIDER);
		 	 	   ActionPerteMPDAO.edit(ActionPerteMP);
		 	 	   

		 	 		

		 	 	   
		 	 	   
		 	 	   
		 	 	   
		 	 	   
		 	 	   
		 	 	   
		 	 	
		 	 	JOptionPane.showMessageDialog(null, "L'Action de Perte MP  Ajouter Avec Succée");
		 	 	
		 	 	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Impression   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 	 	
		 	 	
		 	 	if(listDetailAcionPerteMPAvanceSurMagasinier.size()!=0)
		 	 	{
		 	 		
		 	 		Map parametersTmp = new HashMap();					             
					parametersTmp.put("magasinDest",  magasin.getLibelle());
					parametersTmp.put("depDest", magasin.getDepot().getLibelle());
					parametersTmp.put("dateTransfer", dateChooserdechet.getDate());
					parametersTmp.put("numBon", listDetailAcionPerteMPAvanceSurMagasinier.get(0).getDetailPerteMP().getPerteMP().getNumPerte());
					JasperUtils.imprimerBonPerteAvanceSurMagasinier(listDetailAcionPerteMPAvanceSurMagasinier, parametersTmp);
		 	 		
		 	
		 	 		
		 	 		
		 	 	}
		 	 	
		 		if(listDetailAcionPerteMPTransfertMagasinDechet.size()!=0)
		 	 	{
		 	 		
		 	 		
		 			Map parametersTmp = new HashMap();					             
					parametersTmp.put("magasinDest",  magasin.getLibelle());
					parametersTmp.put("depDest", magasin.getDepot().getLibelle());
					parametersTmp.put("dateTransfer", dateChooserdechet.getDate());
					parametersTmp.put("numBon", listDetailAcionPerteMPTransfertMagasinDechet.get(0).getDetailPerteMP().getPerteMP().getNumPerte());
					JasperUtils.imprimerBonPerteTransfertMagasinDechet(listDetailAcionPerteMPTransfertMagasinDechet, parametersTmp);
		 	 		
		 	
		 	 		
		 	 		
		 	 		
		 	 	}
		 	 	
		 		if(listDetailAcionPerteMPRetourProduction.size()!=0)
		 	 	{
		 	 		
		 			
		 	 		
		 			Map parametersTmp = new HashMap();					             
					parametersTmp.put("magasinDest",  magasin.getLibelle());
					parametersTmp.put("depDest", magasin.getDepot().getLibelle());
					parametersTmp.put("dateTransfer", dateChooserdechet.getDate());
					parametersTmp.put("numBon", listDetailAcionPerteMPRetourProduction.get(0).getDetailPerteMP().getPerteMP().getNumPerte());
					JasperUtils.imprimerBonPerteRetourProduction(listDetailAcionPerteMPRetourProduction, parametersTmp);
		 	 		
		 	
		 	 		
		 	 		
		 	 	}
		 	 	
		 	 	
		 	 	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 	 	
		 	 	
		 	 	
		 	 	
		 	 	
		 	 	
		 	 	
	 			listDetailAcionPerteMP.clear();
	 			afficher_tableMP_Total(listDetailAcionPerteMP);
	 			listDetailPerteMP.clear();
	 			afficher_tableMP_Perte(listDetailPerteMP);
	 			
	 			Vider();
	 			combodepot.setSelectedItem("");
		 		comboMagasin.setSelectedItem("");
		 		comboMagasinDechet.setSelectedItem("");
		 		comboNumBon.setSelectedIndex(-1);
		 		dateChooserdechet.setDate(null);
		 		listDetailTransfertStockMP.clear();
		 	
		 		
		 		ChargerNumBonPerteMPValider();
		 		
		 		newActionPerteMP=new ActionPerteMP();
		 		
		 		
		 	 		
		 	 		}

			 			
			 			

			 			
			 			
			 			
			 		
		 			
		 		}else
		 		{
		 			
		 			JOptionPane.showMessageDialog(null, "Veuillez entrer les quantités dechet SVP");
		 			return;
		 			
		 			
		 		}
		 		       
		 		
		 		
		
		 		
		 	
		 	}
		 });
		 btnValider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 btnValider.setIcon(imgValider);
		 btnValider.setBounds(597, 794, 104, 31);
		 add(btnValider);
		 comboMP.addItem("");
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
 		}else if(comboAction.getSelectedItem().equals("") )
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Action SVP !!!!");
 			return;
 		}
 		
 		else 
 		{

 			
 			
 			

 			
 			
           ActionMP actionMP=mapActionMP.get(comboAction.getSelectedItem());

 			boolean existe=false;
 			BigDecimal quantitedechet=BigDecimal.ZERO;
	 		
	 		
	 		
	 		DetailPerteMP detailPerteMP=null;
	 		DetailActionPerteMP detailActionPerteMPTmp=listDetailAcionPerteMP.get(tableDechetFournisseur.getSelectedRow());
	 		
	 		
	 		for(int j=0;j<listDetailPerteMP.size();j++)
	 		{
	 			
	 			if(detailActionPerteMPTmp.getMatierePremier().getId()==listDetailPerteMP.get(j).getMatierePremier().getId())
	 			{
	 				
	 				if(detailActionPerteMPTmp.getFournisseurMP()!=null)
	 				{
	 					if(listDetailPerteMP.get(j).getFournisseurMP()!=null)
	 					{
	 						if(detailActionPerteMPTmp.getFournisseurMP().getId()==listDetailPerteMP.get(j).getFournisseurMP().getId())
		 					{
		 						
		 						if(detailActionPerteMPTmp.getDetailPerteMP().getMotifPerteMP().getId()==listDetailPerteMP.get(j).getMotifPerteMP().getId())
		 						{
		 							
		 							detailPerteMP=listDetailPerteMP.get(j);
		 							
		 						}
	 							
	 							
		 						
		 					}
	 					}
	 					
	 					
	 				}else
	 				{
	 					
	 					
	 					if(listDetailPerteMP.get(j).getFournisseurMP()==null)
	 					{
	 						
		 						if(detailActionPerteMPTmp.getDetailPerteMP().getMotifPerteMP().getId()==listDetailPerteMP.get(j).getMotifPerteMP().getId())
		 						{
		 							
		 							detailPerteMP=listDetailPerteMP.get(j);
		 							
		 						}
	 							
	 							
	 					}
	 					
	 					
	 					
	 				}
	 				
	 					
	 				
	 			}
	 			
	 			
	 		}
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
			for(int i=0;i<listDetailAcionPerteMP.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailAcionPerteMP.get(i).getMatierePremier().getId()==matierePremier.getId() )
 	 				{
 						if(listDetailAcionPerteMP.get(i).getFournisseurMP()!=null)
 						{
 							
 							
 							if(fournisseurMP!=null)
 							{
 								
 								if(listDetailAcionPerteMP.get(i).getFournisseurMP().getId()==fournisseurMP.getId())
 								{
 									
 									if(detailPerteMP.getMotifPerteMP().getId()==listDetailAcionPerteMP.get(i).getDetailPerteMP().getMotifPerteMP().getId())
 									{
 										
 										quantitedechet=quantitedechet.add(listDetailAcionPerteMP.get(i).getQuantite());
 									
 									if(listDetailAcionPerteMP.get(i).getActionMP().getId()==actionMP.getId())
 									{
 										existe=true;
 									}
 									
 									
 									}
 									
 									
 								}
 								
 								
 							}
 							
 							
 						}else
 						{
 							
 							if(fournisseurMP==null)
 							{
 								
 								if(detailPerteMP.getMotifPerteMP().getId()==listDetailAcionPerteMP.get(i).getDetailPerteMP().getMotifPerteMP().getId())
									{
										
										quantitedechet=quantitedechet.add(listDetailAcionPerteMP.get(i).getQuantite());
									
									if(listDetailAcionPerteMP.get(i).getActionMP().getId()==actionMP.getId())
									{
										existe=true;
									}
									
									
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
 				
 				
 			   CompteMagasinier compteMagasinier=null;
               
               if(actionMP.getAction().equals(Constantes.AVANCE_SUR_MAGASINIER))
               {
              	 if(comboCompteMagasinier.getSelectedItem().equals(""))
              	 {
              		 
              		 JOptionPane.showMessageDialog(null, "Veuillez selectionner le Compte magasinier Pour l'action Avance Sur Magasinier SVP !!!!");
			 			return;
              		 
              	 }else
              	 {
              		 compteMagasinier=MapCompteMagasinier.get(comboCompteMagasinier.getSelectedItem());
              		 
              	 }
              	 
              	 
              	 
              	 
               }
 				
 				
 			
 				DetailActionPerteMP detailActionPerteMP=listDetailAcionPerteMP.get(tableDechetFournisseur.getSelectedRow()); 				
 				
 					
 				if(!txtdechet.getText().equals(""))
 				{
 					
 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(detailPerteMP.getQuantite())<=0)
 					{
 						
 						detailActionPerteMP.setQuantite (new BigDecimal(txtdechet.getText()));
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite est supperieur au sommes des Quantite Pertes !!!!");
 						return;
 					}
 					
 					
 					
 					
 				}else
 				{
 					JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité dechet SVP !!!!");
		 			return;
 				}
 				
 				
 				
 				detailActionPerteMP.setActionMP(actionMP);
 				detailActionPerteMP.setMatierePremier(matierePremier);
 				if(fournisseurMP!=null)
 				{
 					detailActionPerteMP.setFournisseurMP (fournisseurMP);
 				}
 				
 				
 				if(matierePremier.getPrix()!=null)
 				{
 					
 					if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)>0)
 					{
 						detailActionPerteMP.setPrix(matierePremier.getPrix());
 						
 					}else
 					{
 						
 						
 						if(fournisseurMP==null)
 	 					{
 	 						
 		 					StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
 		 					if(stockMP!=null)
 		 					{
 		 						detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
 		 					}else
 		 					{
 		 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
 		 					}
 		 					
 	 						
 	 					}else
 	 					{
 	 						
 	 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
 	 						if(stockMP!=null)
 		 					{
 	 							detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
 		 					}else
 		 					{
 		 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
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
	 						detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
	 					}else
	 					{
	 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
	 					}
	 					
 						
 					}else
 					{
 						
 						StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinMP.getId(),fournisseurMP.getId());
 						if(stockMP!=null)
	 					{
 							detailActionPerteMP.setPrix(stockMP.getPrixUnitaire());
	 					}else
	 					{
	 						detailActionPerteMP.setPrix(BigDecimal.ZERO);
	 					}
 						
 					}
 					
 					
 					
 				}
 			
 				
 				detailActionPerteMP.setCompteMagasinier(compteMagasinier);
                listDetailAcionPerteMP.set(tableDechetFournisseur.getSelectedRow(),detailActionPerteMP)	;	
                
               detailActionPerteMPDAO.edit(detailActionPerteMP);
                
            afficher_tableMP_Total(listDetailAcionPerteMP);
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
				btnModifier.setBounds(1442, 507, 113, 31);
				btnModifier.setEnabled(false);
				add(btnModifier);
				
				 btnSupprimer = new JButton("Supprimer");
				btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableDechetFournisseur.getSelectedRow()!=-1)
						{
														
							DetailActionPerteMP detailActionPerteMP=	listDetailAcionPerteMP.get(tableDechetFournisseur.getSelectedRow());
						
							DetailActionPerteMP detailActionPerteMPTmp=detailActionPerteMPDAO.findById(detailActionPerteMP.getId());
							if(detailActionPerteMPTmp!=null)
							{
								detailActionPerteMPDAO.delete(detailActionPerteMP.getId());
							}
						
							listDetailAcionPerteMP.remove(tableDechetFournisseur.getSelectedRow());
							afficher_tableMP_Total(listDetailAcionPerteMP);
						
							Vider();
							
						
								
						}
						
						
						
					}
				});
				btnSupprimer.setBounds(1442, 567, 113, 36);
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
				btnVider.setBounds(504, 385, 113, 31);
				add(btnVider);
				
				listFournisseur=fournisseurMPDAO.findAll();
				combofournisseur.addItem("");
				
				 comboAction = new JComboBox();
				 comboAction.addActionListener(new ActionListener() {
				 	public void actionPerformed(ActionEvent arg0) {
				 		if(comboAction.getSelectedItem()!=null)
				 		{
				 			if(!comboAction.getSelectedItem().equals(""))
			 		 		{
					 			
					 		if(comboAction.getSelectedItem().equals(AVANCE_SUR_MAGASINIER)	)
					 		{
					 			
					 			
					 			comboCompteMagasinier.setSelectedItem("");
					 			comboCompteMagasinier.setEnabled(true);
					 			
					 			
					 		}else
					 		{
					 			
					 			comboCompteMagasinier.setSelectedItem("");
					 			comboCompteMagasinier.setEnabled(false);
					 		}
					 			
					 			
					 			
			 		 		}else
			 		 		{
			 		 			comboCompteMagasinier.setSelectedItem("");
					 			comboCompteMagasinier.setEnabled(false);
			 		 		}
				 			
				 		}
				 
				 		
				 	}
				 });
				comboAction.setSelectedIndex(-1);
				comboAction.setBounds(952, 28, 278, 24);
				layeredPane_1.add(comboAction);
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
  
  comboAction.addItem("");
  
  JLabel lblCompteMagasinier = new JLabel("Compte Magasinier:");
  lblCompteMagasinier.setFont(new Font("Tahoma", Font.PLAIN, 11));
  lblCompteMagasinier.setBounds(852, 73, 110, 24);
  layeredPane_1.add(lblCompteMagasinier);
  
   comboCompteMagasinier = new JComboBox();
  comboCompteMagasinier.setBounds(952, 72, 278, 24);
  layeredPane_1.add(comboCompteMagasinier);
  
   btnRechercher = new JButton();
  btnRechercher.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent arg0) {
  		
  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
  		Magasin magasindechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
  		Magasin magasin=MapmagasinMP.get(comboMagasin.getSelectedItem());
  		
  		String numbon="";	
  		numbon=comboNumBon.getSelectedItem().toString();
  			
  		
  		if(numbon.equals("") && dateChooserdechet.getDate()==null && magasindechet==null)
  		{
  			
  			JOptionPane.showMessageDialog(null, "Veuillez entrer un ou plusieurs champs SVP");
  			return;
  			
  		}
  		
  		if(magasindechet!=null && dateChooserdechet.getDate()==null && numbon.equals(""))
  		{
  			
  			JOptionPane.showMessageDialog(null, "Veuillez entrer la date ou le Num de Bon SVP");
  			return;
  			
  			
  			
  		}
  		
  		
  		if(magasindechet==null && dateChooserdechet.getDate()!=null && numbon.equals(""))
  		{
  			
  			JOptionPane.showMessageDialog(null, "Veuillez entrer le magasin dechet ou le Num de Bon SVP");
  			return;
  			
  			
  			
  		}
  		
  		
  		if(magasin==null)
  		{
  			
  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin MP SVP !!!!");
  			return;
  		}
  		
  		listComptaMagasinier.clear();
  		listComptaMagasinier=compteMagasinierDAO.findCompteMagasinierByMagasin(magasin);
  		comboCompteMagasinier.removeAllItems();
  		ChargerComboCompteMagasinier();
  		
  			btnAjouter.setEnabled(true);
  			btnModifier.setEnabled(true);
  			btnSupprimer.setEnabled(true);
  			btnValider.setEnabled(true);
  			
  			
  			
  			PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat (numbon,dateChooserdechet.getDate(), magasindechet, ETAT_INVALIDER);
  		 			
 	 			listDetailPerteMP.clear();
 	 			if(perteMP!=null)
 	 			{
 	 				dateChooserdechet.setDate(perteMP.getDateoperation());
 	 				
 	 				listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMP);
 	 			}
 	 			
 	 			
 	 			
 	 			afficher_tableMP_Perte(listDetailPerteMP);
 	 			
 	 		
  	}
  });
  btnRechercher.setText("Rechercher");
  btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 12));
  btnRechercher.setBounds(1442, 25, 113, 31);
  add(btnRechercher);
  
  JScrollPane scrollPane = new JScrollPane();
  scrollPane.setBounds(9, 80, 1423, 175);
  add(scrollPane);
  
   table = new JXTable();
   table.addMouseListener(new MouseAdapter() {
   	@Override
   	public void mouseClicked(MouseEvent arg0) {
   		
   		if(table.getSelectedRow()!=-1)
   		{
   			detailActionPerteMPDAO.ViderSession();
   			
   			DetailPerteMP detailPerteMP=listDetailPerteMP.get(table.getSelectedRow());
   			
   			Magasin magasindechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
   	   		if(magasindechet!=null)
   	   		{
   	   			oldActionPerteMP=ActionPerteMPDAO.findByDateByMagasin(dateChooserdechet.getDate(), magasindechet, ETAT_INVALIDER);
   	   	
   	   			
   	   		listDetailAcionPerteMP=detailActionPerteMPDAO.listeDetailActionPerteMPByDetailPerteMP(detailPerteMP);
   	   			
   	   		
   	   		}else
   	   		{
   	   			JOptionPane.showMessageDialog(null, "Veuillez slectionner le Magasin de dechet SVP");
   	  			return;
   	  			
   	   			
   	   			
   	   		}
   			
   			
   			
   	
   			
   		txtCodeMP.setText(detailPerteMP.getMatierePremier().getCode());
   		comboMP.setSelectedItem(detailPerteMP.getMatierePremier().getNom());
   		if(detailPerteMP.getFournisseurMP()!=null)
   		{
   			combofournisseur.setSelectedItem(detailPerteMP.getFournisseurMP().getCodeFournisseur());
   		}else
   		{
   			combofournisseur.setSelectedItem("");
   		}
   		
   
   	  afficher_tableMP_Total(listDetailAcionPerteMP);
   		
   			
   			
   		}
   		
   		
   		
   	}
   });
  table.setModel(new DefaultTableModel(
  	new Object[][] {
  	},
  	new String[] {
  		"Code MP", "MP", "Fournisseur", "Quantite", "Motif"
  	}
  ));
  scrollPane.setViewportView(table);
  table.setShowVerticalLines(false);
  table.setSelectionBackground(new Color(51, 204, 255));
  table.setRowHeightEnabled(true);
  table.setRowHeight(20);
  table.setGridColor(Color.BLUE);
  table.setForeground(Color.BLACK);
  table.setColumnControlVisible(true);
  table.setBackground(Color.WHITE);
  table.setAutoCreateRowSorter(true);
  for(int j=0;j<listActionMP.size();j++)
  {
	  
	ActionMP actionMP=  listActionMP.get(j);
	  
	comboAction.addItem(actionMP.getAction()); 
	  mapActionMP.put(actionMP.getAction(), actionMP);
	  
  }
  
  
  
  

		
	  comboCompteMagasinier.setSelectedItem("");
		comboCompteMagasinier.setEnabled(false);
		
		comboNumBon.removeAllItems();
		ChargerNumBonPerteMPValider();
		
		
	}
	
	
	
	public void ChargerComboCompteMagasinier()
	{
		
		 comboCompteMagasinier.addItem("");
		  for(int d=0;d<listComptaMagasinier.size();d++) {
			  
			  CompteMagasinier compteMagasinier= listComptaMagasinier.get(d);
			  
			  comboCompteMagasinier.addItem(compteMagasinier.getNom());
			  MapCompteMagasinier.put(compteMagasinier.getNom(), compteMagasinier);
			  
			  }
		
		
	}
	
	
	public void ChargerNumBonPerteMPValider()
	{
		
		
		comboNumBon.addItem("");
		listePerteMP=PerteMPDAO.listDesPertesValiderParDepot(AuthentificationView.utilisateur.getCodeDepot(), Constantes.ETAT_INVALIDER);
		for(int d=0;d<listePerteMP.size();d++)	
		{
			
			PerteMP perteMP=listePerteMP.get(d);
			PerteMPMap.put(perteMP.getNumPerte(), perteMP);
			comboNumBon.addItem(perteMP.getNumPerte());
			
			
		}
		
		
	}
	
	
	
	void afficher_tableMP_Total(List<DetailActionPerteMP> listDetailActionPerteMP)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailActionPerteMP.size();i++)
			{	
				
	DetailActionPerteMP detailActionPerteMP=	listDetailActionPerteMP.get(i);
			String fournisseur=""; 
			String CompteMagasinier=""; 
				 
			if(detailActionPerteMP.getFournisseurMP() !=null)
			{
				fournisseur=detailActionPerteMP.getFournisseurMP().getCodeFournisseur();
			}
			
			if(detailActionPerteMP.getCompteMagasinier() !=null)
			{
				CompteMagasinier=detailActionPerteMP.getCompteMagasinier().getNom();
			}
			
			
			
				Object []ligne={detailActionPerteMP.getMatierePremier().getNom(),fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailActionPerteMP.getQuantite()) ,detailActionPerteMP.getDetailPerteMP().getMotifPerteMP().getMotif(),detailActionPerteMP.getActionMP().getAction(),CompteMagasinier};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	

	void afficher_tableMP_Perte(List<DetailPerteMP> listDetailPerteMP)
	{
		intialiserTableau();
		
		
		 
			for (int i=0;i<listDetailPerteMP.size();i++)
			{	
				
	DetailPerteMP detailPerteMP=	listDetailPerteMP.get(i);
			String fournisseur=""; 
				 
			if(detailPerteMP.getFournisseurMP() !=null)
			{
				fournisseur=detailPerteMP.getFournisseurMP().getCodeFournisseur();
			}
			
				Object []ligne={detailPerteMP.getMatierePremier().getCode(), detailPerteMP.getMatierePremier().getNom(),fournisseur,NumberFormat.getNumberInstance(Locale.FRANCE).format(detailPerteMP.getQuantite()),detailPerteMP.getMotifPerteMP().getMotif()};

				modeleMP.addRow( ligne);
			
			}
			
		
	}






void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première","Fournisseur", "Quantite","Motif","Action","Compte Magasinier"
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
		 tableDechetFournisseur.getColumnModel().getColumn(2).setPreferredWidth(60);


}


void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matière Première","Fournisseur", "Quantite","Motif"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(160);
		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
		 table.getColumnModel().getColumn(2).setPreferredWidth(60);


}



public void Vider()
{
	txtdechet.setText("");
	comboAction.setSelectedItem("");
	soucategoriempcombo.setSelectedIndex(-1);
	categoriempcombo.setSelectedIndex(-1);
	 comboMP.setSelectedIndex(-1);
	 txtCodeMP.setText("");
	combofournisseur.setSelectedIndex(-1);
	
	btnAjouter.setEnabled(true);
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
}
}

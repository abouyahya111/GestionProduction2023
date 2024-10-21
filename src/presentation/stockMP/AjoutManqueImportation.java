package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueImportationDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueImportationDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeFixe;
import dao.entity.ChargeProduction;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueImportation;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class AjoutManqueImportation extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;

	private JXTable  tableMP = new JXTable();
	
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	 
	 
	 
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	//private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	
	
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiere= new HashMap<>();
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private	List<ManqueImportation> listManqueImportationNonValider= new ArrayList<ManqueImportation>();
	


	private TransferStockMPDAO transferStockMPDAO;
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	
	 private JComboBox combomp=new JComboBox<>();

	


	
private StockMPDAO stockMPDAO;
	private JTextField txtcodemp;
	
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	private MatierePremiereDAO matierepremieredao;
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private   JDateChooser dateChooserMP ;
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private Map< String, FournisseurAdhesive> mapFournisseurAdhesif = new HashMap<>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private List<FournisseurAdhesive> listFournisseurAdhesif =new ArrayList<FournisseurAdhesive>();
	private FournisseurMPDAO fournisseurMPDAO;
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	 private SubCategorieMPDAO categorieMPDAO;
	 private List<ManqueImportation> listManqueImportation =new ArrayList<ManqueImportation>();
	 private ManqueImportationDAO manqueImportationDAO;
	 JComboBox soucategoriempcombo = new JComboBox();
	 List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	 List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
		private SubCategorieMPDAO subcategoriempdao;
		private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
		private Map< String, CategorieMp> catMap = new HashMap<>();
		private CategorieMpDAO categoriempdao;
		JComboBox categoriempcombo = new JComboBox();
		 JComboBox comboNumReception = new JComboBox();
		 JButton btnDeslectionnerTout = new JButton();
		  JButton btnSelectionnertout = new JButton();
		  private ImageIcon imgSelectAll;
			private ImageIcon imgDeselectAll;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutManqueImportation() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 795);
      
	
        try{ 
        	
        	 imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	matierepremieredao=new MatierePremierDAOImpl();
         	stockMPDAO= new StockMPDAOImpl();
         	
        	
        
         transferStockMPDAO= new TransferStockMPDAOImpl();
         detailTransferStockMPDAO= new DetailTransferMPDAOImpl();
         fournisseurMPDAO=new FournisseurMPDAOImpl();
         fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
         
         manqueImportationDAO=new ManqueImportationDAOImpl();
         categoriempdao=new CategorieMpDAOImpl();
     	subcategoriempdao=new SubCategorieMPAOImpl() ;
     	listsubcategoriemp=subcategoriempdao.findAll();
     	//listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
    	listFournisseur=fournisseurMPDAO.findAll();
         	listFournisseurAdhesif=fournisseurAdhesiveDAO.findAll();
          } catch (Exception exp){exp.printStackTrace();}
        tableMP.setSortable(false);
        tableMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		MatierePremier matierePremier=mapMatierePremiere.get(tableMP.getValueAt(tableMP.getSelectedRow(), 1));
				soucategoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
				categoriempcombo.setSelectedItem(matierePremier.getCategorieMp().getNom());
				 
				combomp.setSelectedItem(matierePremier.getNom());
       		
       		
       		
       		txtcodemp.setText(matierePremier.getCode());
       		
       		
       		 
       		 
       		comboFournisseur.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 2).toString());
       		txtquantite.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 3).toString());
       		 
       		
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Code MP","Matière Première","Fournisseur", "Quantite Manque" ,"Valider"
       	}
       ));
				  		
       tableMP.setShowVerticalLines(false);
       tableMP.setSelectionBackground(new Color(51, 204, 255));
       tableMP.setRowHeightEnabled(true);
       tableMP.setBackground(new Color(255, 255, 255));
       tableMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMP.setColumnControlVisible(true);
       tableMP.setForeground(Color.BLACK);
       tableMP.setGridColor(new Color(0, 0, 255));
       tableMP.setAutoCreateRowSorter(true);
       tableMP.setBounds(2, 27, 411, 198);
       tableMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMP);
				  		     	scrollPane.setBounds(24, 221, 1117, 320);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des MP");
				  		     	titledSeparator.setBounds(24, 180, 983, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(-60, 655, 1200, 140);
				  		     	layeredPane.setVisible(false);
				  		     	add(layeredPane);
				  		     	
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(635, 34, 64, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(806, 34, 57, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodemp = new JTextField();
		      util.Utils.copycoller(txtcodemp);
		      txtcodemp.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     					
			      			if(!txtcodemp.getText().equals(""))
			      			{

			      			MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
			      			if(mp!=null)
			      			{
			      				
			      				combomp.setSelectedItem(mp.getNom());
			      				
			      			}else
			      			{
			      				 JOptionPane.showMessageDialog(null, " Code MP  incorrecte ", "Erreur", JOptionPane.ERROR_MESSAGE);
			      				
			      			}
			      			
			      			
			      			}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(714, 35, 82, 26);
		      layeredPane.add(txtcodemp);
		    
		   
		       combomp = new JComboBox();
		     
		       combomp.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		       		if(combomp.getSelectedIndex()!=-1)
	  		 		{
		       			
		       			if(!combomp.getSelectedItem().equals(""))
  				 		{
  				 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
  				 			txtcodemp.setText(mp.getCode());
  				 		if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  				 		{
  				 			
  				 			comboFournisseur.removeAllItems();
  				 			comboFournisseur.addItem("");
  				 			 
  				 			for(int k=0;k<listFournisseur.size();k++)
  				 			{
  				 				FournisseurMP fournisseurMP=listFournisseur.get(k);
  				 				comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
  				 				mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
  				 				
  				 			}
  				 			
  				 			
  				 			
  				 		}else
  				 		{
  				 			
  				 			comboFournisseur.removeAllItems();
  				 			comboFournisseur.addItem("");
  				 			 
  				 			for(int k=0;k<listFournisseurAdhesif.size();k++)
  				 			{
  				 				FournisseurAdhesive fournisseurAdhesive=listFournisseurAdhesif.get(k);
  				 				comboFournisseur.addItem(fournisseurAdhesive.getCodeFournisseur());
  				 				mapFournisseurAdhesif.put(fournisseurAdhesive.getCodeFournisseur(), fournisseurAdhesive);
  				 				
  				 			}
  				 			
  				 			
  				 		}
  				 			
  				 		
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodemp.setText(Constantes.CODE_MP);
  				 			
  				 		}
  				 	
		       			
	  		 		}else
	  		 		{
	  		 			txtcodemp.setText(Constantes.CODE_MP);
	  		 		}
  		     	 	
  			 			
  			 		
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      combomp.setBounds(846, 35, 344, 27);
		      layeredPane.add(combomp);
		      combomp.setSelectedIndex(-1);
		     
		     	int i=0;
		     	while(i<listMP.size())
		     	{
		     		
		     		MatierePremier mp=listMP.get(i);
		     		mapCodeMP.put(mp.getCode(), mp);
		     		mapMatierePremiere.put(mp.getNom(), mp);
		     		combomp.addItem(mp.getNom());
		     		
		     		
		     		i++;
		     	}
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(286, 84, 57, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(345, 84, 112, 26);
		      layeredPane.add(txtquantite);
		      
		 
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean existe=false;
			//	MouvementStockGlobal mouvementStockGlobal=null;
				
				 if(dateChooserMP.getDate()==null)
					{

						JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combodepot.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combomagasin.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
						
					}else if(listManqueImportation.size()==0)
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer les matieres premieres  SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;		
						
						
					}else if(comboNumReception.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Numéro De Reception  SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}
					
					
					
					else
					{
						
						boolean trouve=false;
					for(int i=0;i<listManqueImportation.size();i++)
					{
						
						ManqueImportation manqueImportation=listManqueImportation.get(i);
						boolean selected=(boolean) tableMP.getValueAt(i, 4);
						if(selected==true)
						{
							trouve=true;
							manqueImportation.setEtat(Constantes.ETAT_VALIDER);
							
							manqueImportationDAO.edit(manqueImportation);
							
						}
						
						
						
						
						
					}
						if(trouve==true)
						{
							JOptionPane.showMessageDialog(null,
									  "Manque Importation Valider Avec succée","Bravo",JOptionPane.
									  INFORMATION_MESSAGE);
							ChargerComboNumReceptionNonValider();
							listManqueImportation.clear();
							afficher_tableMP(listManqueImportation);
						}else
						{
							
							JOptionPane.showMessageDialog(null,
									  "Veuillez Selectionne Un Ou Plusieurs Enregistrement","Erreyrr",JOptionPane.
									  ERROR_MESSAGE);
							
							
						}
					
						
						
						
						
						
					/*
					 * List<ManqueImportation> ListeManqueImportationExiste=new ArrayList<>();
					 * String req="";
					 * req=req+" where m.numReception='"+txtNumReception.getText().toUpperCase().
					 * trim()+"' ";
					 * 
					 * ListeManqueImportationExiste=manqueImportationDAO.findByRequete(req);
					 * 
					 * if(ListeManqueImportationExiste.size()!=0) {
					 * JOptionPane.showMessageDialog(null,
					 * "le Numéro De Reception déja existant SVP !!!!!","Erreur",JOptionPane.
					 * ERROR_MESSAGE); return; }
					 * 
					 * Depot depot=mapDepot.get(combodepot.getSelectedItem()); Magasin magasin
					 * =mapMagasin.get(combomagasin.getSelectedItem());
					 * 
					 * 
					 * for(int i=0;i<listManqueImportation.size();i++) {
					 * 
					 * ManqueImportation manqueImportation= listManqueImportation.get(i);
					 * 
					 * manqueImportation.setMagasin(magasin);
					 * manqueImportation.setDate(dateChooserMP.getDate());
					 * manqueImportation.setAjouterPar(utilisateur);
					 * manqueImportation.setNumReception(txtNumReception.getText().toUpperCase().
					 * trim()); manqueImportation.setDateCreer(new Date());
					 * listManqueImportation.set(i, manqueImportation);
					 * 
					 * 
					 * 
					 * 
					 * } boolean valider=false; for(int i=0;i<listManqueImportation.size();i++) {
					 * 
					 * ManqueImportation manqueImportation= listManqueImportation.get(i);
					 * 
					 * manqueImportationDAO.add(manqueImportation);
					 * 
					 * valider=true;
					 * 
					 * 
					 * }
					 * 
					 * if(valider==true) { JOptionPane.showMessageDialog(null,
					 * "Manque Importation Valider Avec succée","Bravo",JOptionPane.
					 * INFORMATION_MESSAGE);
					 * 
					 * 
					 * if(listManqueImportation.size()!=0) {
					 * 
					 * Map parameters = new HashMap(); parameters.put("depot",
					 * listManqueImportation.get(0).getMagasin().getDepot().getLibelle());
					 * 
					 * parameters.put("magasin",listManqueImportation.get(0).getMagasin().getLibelle
					 * ());
					 * 
					 * parameters.put("numreception",txtNumReception.getText().toUpperCase().trim())
					 * ; parameters.put("datesituation",dateChooserMP.getDate());
					 * parameters.put("datesituationtext","Date Situation:");
					 * parameters.put("numreceptiontext","Num Réception :");
					 * JasperUtils.imprimerManqueImportation (listManqueImportation, parameters);
					 * 
					 * //JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès",
					 * JOptionPane.INFORMATION_MESSAGE);
					 * 
					 * }
					 * 
					 * 
					 * 
					 * 
					 * listManqueImportation.clear(); afficher_tableMP(listManqueImportation);
					 * initialiserMP(); initialiserdepot(); }else {
					 * JOptionPane.showMessageDialog(null,
					 * "Erreur","Erreur",JOptionPane.INFORMATION_MESSAGE); }
					 */
					 
						
					}
				
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(501, 567, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations MP");
		titledSeparator_1.setBounds(-84, 627, 1200, 30);
		add(titledSeparator_1);
		titledSeparator_1.setVisible(false);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1200, 97);
		add(layeredPane_1);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(294, 31, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(360, 32, 209, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
     				if(depot!=null)
     				{
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
     					 listMagasinDechetMP= depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
		     				if(listMagasin.size()!=0 || listMagasinDechetMP.size()!=0 )
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
  		     				{
  		     					Magasin magasin=listMagasin.get(i);
  		     					combomagasin.addItem(magasin.getLibelle());
  		     					mapMagasin.put(magasin.getLibelle(), magasin);
  		     					i++;
  		     				}
		     					
		     					  int j=0;
					  		      	while(j<listMagasinDechetMP.size())
					  		      		{	
					  		      			Magasin magasin=listMagasinDechetMP.get(j);
					  		      		combomagasin.addItem(magasin.getLibelle());
		  		     					mapMagasin.put(magasin.getLibelle(), magasin);
					  		      			j++;
					  		      		}
		     					
		     					
		     					
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
     					
     				}else
     				{
     					combomagasin.removeAllItems();
     				}
     				
     				
    	 		 }
    	 	
 	}
		  });
		  
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     	Depot depot=listDepot.get(k);
		     	combodepot.addItem(depot.getLibelle());
			     		
			     mapDepot.put(depot.getLibelle(), depot);
			     	
		     		
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(589, 30, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(669, 31, 183, 24);
		  layeredPane_1.add(combomagasin);
		  
		  JLabel label = new JLabel("Date  :");
		  label.setBounds(58, 33, 62, 24);
		  layeredPane_1.add(label);
		  
		   dateChooserMP = new JDateChooser();
		  dateChooserMP.setLocale(Locale.FRANCE);
		  dateChooserMP.setDateFormatString("dd/MM/yyyy");
		  dateChooserMP.setBounds(103, 30, 181, 26);
		  layeredPane_1.add(dateChooserMP);
		  
		  JLabel lblNumReception = new JLabel("Num reception  :");
		  lblNumReception.setBounds(862, 29, 106, 26);
		  layeredPane_1.add(lblNumReception);
		  
		   comboNumReception = new JComboBox();
		   comboNumReception.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		
		   	 if(e.getStateChange() == ItemEvent.SELECTED)
	 		 {
		   		 
		   		 
		   		 if(!comboNumReception.getSelectedItem().equals(""))
		   		 {
		   			 
		   			 listManqueImportation=manqueImportationDAO.findByEtatByNumReception(Constantes.ETAT_INVALIDER, comboNumReception.getSelectedItem().toString());
		   			 
		   			 afficher_tableMP(listManqueImportation);
		   			 
		   		 }else
		   		 {
		   			 
		   			listManqueImportation.clear();
		   			afficher_tableMP(listManqueImportation);
		   			 
		   		 }
		   		 
	 		 }
		   	}
		   });
		  comboNumReception.setBounds(962, 31, 228, 24);
		  layeredPane_1.add(comboNumReception);
		 
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1200, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(1150, 652, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					boolean existe=false;
					
					
					
					 if(dateChooserMP.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combodepot.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combomagasin.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
							
						}else if(combomp.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodemp.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							} else if(comboFournisseur.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} 
							
							
							
							
							
							else 	{
								
								
								
								
								
								
								
								
								
								
								  Depot depot=mapDepot.get(combodepot.getSelectedItem());
								Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
								MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
								
						 
								
								
								
								
								
								
								existe=false;
								for(int i=0;i<listManqueImportation.size();i++)
								{
								
									ManqueImportation manqueImportation=listManqueImportation.get(i);
									
									if(manqueImportation.getMatierePremier().getId()==mp.getId())
									{
										
									 if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									 {
										 
										if(manqueImportation.getFournisseurMP().getId()==mapFournisseur.get(comboFournisseur.getSelectedItem()).getId()) 
										{
											existe=true;	
											
											
											
											
										} 
										 
									 }else
									 {
										 if(manqueImportation.getFournisseurAdhesif().getId()==mapFournisseurAdhesif.get(comboFournisseur.getSelectedItem()).getId()) 
											{
												existe=true;	
												
												
												
												
											} 
										 
									 }
										
										
										
										
										
										
										
										
										
									}
									
									
									
									
								}
								
								if(existe==false)
								{
									
									ManqueImportation manqueImportation=new ManqueImportation();
									manqueImportation.setMatierePremier(mp);
									manqueImportation.setQuantite(new BigDecimal(txtquantite.getText()));
									
									 if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									 {
										 
										 manqueImportation.setFournisseurMP(mapFournisseur.get(comboFournisseur.getSelectedItem()));
										 
									 }else
									 {
										 manqueImportation.setFournisseurAdhesif(mapFournisseurAdhesif.get(comboFournisseur.getSelectedItem())); 
										 
									 }
									 manqueImportation.setPrixUnitaire(mp.getPrix());
								
								listManqueImportation.add(manqueImportation);
								afficher_tableMP(listManqueImportation);
								
								initialiserMP();
								
								}else
								{
									
									JOptionPane.showMessageDialog(null, "Matiere Premiere Déja Ajouter Avec Le meme Fournisseur","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
									
								}
								
								
								
							
								
								
 

								
								
							
								
								
							
								
								
								
								
								
							
									
									
								
								
							}
						        
							
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "la Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
		
				}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		btnAjouter.setVisible(false);
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(1160, 761, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiserMP();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser.setVisible(false);
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserdepot();;
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(531, 146, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		
		   		try {
		   			
		   			
		   			boolean existe=false;
			   		
			   		if(tableMP.getSelectedRow()!=-1)
			   		{
			   		

			   			 
			   			
			   					if(combomp.getSelectedItem().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtcodemp.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtquantite.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
			   					{
			   						JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					
			   					}else if(comboFournisseur.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Fournisseur SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								} 

			   					
			   					
			   					else {


									
									
									
									
									
									
									
									
									
									
									  Depot depot=mapDepot.get(combodepot.getSelectedItem());
									Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
									MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
									
							  
									
									existe=false;
									for(int i=0;i<listManqueImportation.size();i++)
									{
									
										ManqueImportation manqueImportation=listManqueImportation.get(i);
										
										if(manqueImportation.getMatierePremier().getId()==mp.getId() && i!=tableMP.getSelectedRow())
										{
											
										 if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
										 {
											 
											if(manqueImportation.getFournisseurMP().getId()==mapFournisseur.get(comboFournisseur.getSelectedItem()).getId()) 
											{
												existe=true;	
												
												
												
												
											} 
											 
										 }else
										 {
											 if(manqueImportation.getFournisseurAdhesif().getId()==mapFournisseurAdhesif.get(comboFournisseur.getSelectedItem()).getId()) 
												{
													existe=true;	
													
													
													
													
												} 
											 
										 }
											
											
											
											
											
											
											
											
											
										}
										
										
										
										
									}
									
									if(existe==false)
									{
										
										ManqueImportation manqueImportation=listManqueImportation.get(tableMP.getSelectedRow());
										manqueImportation.setMatierePremier(mp);
										manqueImportation.setQuantite(new BigDecimal(txtquantite.getText()));
										
										 if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
										 {
											 
											 manqueImportation.setFournisseurMP(mapFournisseur.get(comboFournisseur.getSelectedItem()));
											 
										 }else
										 {
											 manqueImportation.setFournisseurAdhesif(mapFournisseurAdhesif.get(comboFournisseur.getSelectedItem())); 
											 
										 }
										 manqueImportation.setPrixUnitaire(mp.getPrix());
									
									    listManqueImportation.set(tableMP.getSelectedRow(), manqueImportation) ;
									
									    afficher_tableMP(listManqueImportation);
									    initialiserMP();
									
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Matiere Premiere Déja Ajouter Avec Le meme Fournisseur","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
									
								
									
									
	 

									
									
								
									
									
								
									
									
									
									
									
								
										
										
									
									
								
			   						
			   					
			   					}
			   				
			   		}
			   		
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showConfirmDialog(null, "la Quantité et le Prix doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnModifier.setVisible(false);
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal oldPrix=BigDecimal.ZERO;
				BigDecimal oldQuantite=BigDecimal.ZERO;
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());

		  		if(tableMP.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la matiere premiere SVP  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{

							listManqueImportation.remove(tableMP.getSelectedRow());
						
							afficher_tableMP(listManqueImportation);
							initialiserMP();
						}
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  		}
		  		
		  		
		  		
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1150, 717, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  btnSupprimer.setVisible(false);
		  add(btnSupprimer);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1150, 687, 73, 24);
		add(btnModifier);
		
		txtcodemp.setText(Constantes.CODE_MP);
		
		JLabel label_1 = new JLabel("Fournisseur : ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(22, 84, 73, 24);
		layeredPane.add(label_1);
		
		comboFournisseur = new JComboBox();
		comboFournisseur.setSelectedIndex(-1);
		comboFournisseur.setBounds(104, 86, 172, 24);
		layeredPane.add(comboFournisseur);
		
		comboFournisseur.addItem("");
		
		JLabel label_2 = new JLabel("Sous-Categorie Mp");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(22, 35, 144, 24);
		layeredPane.add(label_2);
		
		 soucategoriempcombo = new JComboBox();
		 soucategoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

		  		

		 		

		 		

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
  		  	 categoriempcombo.setSelectedItem("");
  		  	combomp.removeAllItems();
  			combomp.addItem("");
  	  		 combomp.setSelectedItem("");
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  categoriempcombo.setSelectedItem("");
  		  combomp.removeAllItems();
  		combomp.addItem("");
  		 combomp.setSelectedItem("");
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		 	
		  		
		  		
		  		
		  	
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(129, 35, 184, 24);
		layeredPane.add(soucategoriempcombo);
		
		JLabel label_5 = new JLabel("Categorie Mp");
		label_5.setBounds(342, 35, 80, 26);
		layeredPane.add(label_5);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

		  		


		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listMP.clear();
  		  			txtcodemp.setText("");
  		  		combomp.removeAllItems();
  		  	combomp.addItem("");
		  			
  		  listMP=matierepremieredao.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listMP.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listMP.get(i);
  		  			combomp.addItem(matierePremier.getNom());
  		  		mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
  		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			
  		  			listMP.clear();
  		  				txtcodemp.setText("");
  		  				combomp.removeAllItems();
  		  			combomp.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listMP.clear();
  		  	txtcodemp.setText("");
  		  combomp.removeAllItems();
  		combomp.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		  		
		  		
		  		
		  	
		 		
		 		
		 	}
		 });
		categoriempcombo.setBounds(417, 36, 208, 24);
		layeredPane.add(categoriempcombo);
		 
		 
		soucategoriempcombo.addItem("");
		int j=0;
		  while(j<listsubcategoriemp.size())
		  {
			  subcatMap.put(listsubcategoriemp.get(j).getNom(), listsubcategoriemp.get(j));
			  soucategoriempcombo.addItem(listsubcategoriemp.get(j).getNom());
			  j++;
		  }	
		  
		  soucategoriempcombo.setSelectedItem("");
		  
		   btnDeslectionnerTout = new JButton();
		   btnDeslectionnerTout.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {

	    		for(int i=0;i<tableMP.getRowCount();i++)
	     		{
	     			tableMP.setValueAt(false, i, 4);
	     		}
	    	
		   	}
		   });
		  btnDeslectionnerTout.setToolTipText("deselectionner Tout");
		  btnDeslectionnerTout.setBounds(1068, 180, 26, 26);
		  btnDeslectionnerTout.setIcon(imgDeselectAll);
		  add(btnDeslectionnerTout);
		  
		   btnSelectionnertout = new JButton();
		   btnSelectionnertout.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		

	    		
	    		for(int i=0;i<tableMP.getRowCount();i++)
	     		{
	     			tableMP.setValueAt(true, i, 4);
	     		}
	    		
	    	
		   		
		   	}
		   });
		  btnSelectionnertout.setToolTipText("Selectionner Tout");
		  btnSelectionnertout.setBounds(1097, 180, 26, 26);
		  btnSelectionnertout.setIcon(imgSelectAll);
		  add(btnSelectionnertout);
		  
		  ChargerComboNumReceptionNonValider();
		  
		}
	
	
	
	void ChargerComboNumReceptionNonValider()
	{
		comboNumReception.removeAllItems();
		comboNumReception.addItem("");
		listManqueImportationNonValider.clear();
		listManqueImportationNonValider=manqueImportationDAO.findByEtatGroupByNumReception(Constantes.ETAT_INVALIDER);
		for(int j=0;j<listManqueImportationNonValider.size();j++)
		{
			
			comboNumReception.addItem(listManqueImportationNonValider.get(j).getNumReception());
			
			
		}
		
		
		
		comboNumReception.setSelectedItem("");
		
	}
	

	
	void initialiserdepot()
	{
		dateChooserMP.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		 
		}

	void initialiserMP()
	{
		soucategoriempcombo.setSelectedItem("");
		categoriempcombo.removeAllItems();
		categoriempcombo.addItem("");
		categoriempcombo.setSelectedItem("");
		combomp.removeAllItems();
		combomp.addItem("");
		txtcodemp.setText("");
		combomp.setSelectedItem("");
	 
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
      
		 
		 txtcodemp.setText(Constantes.CODE_MP);
		 comboFournisseur.setSelectedItem("");
		
	}
	
	void InitialiseTableau()
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première","Fournisseur",  "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableMP.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableMP.getColumnModel().getColumn(2).setPreferredWidth(94);
	       tableMP.getColumnModel().getColumn(3).setPreferredWidth(94);
	    
		
	
}
	
	
	void afficher_tableMP(List<ManqueImportation> listManqueImportation)
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première","Fournisseur","Quantite","Valider"
				}
			)
		{
			boolean[] columnEditables = new boolean[] {
					false,false,false,false,true
			};
			
			Class[] columnTypes = new Class[] {
					String.class,String.class,String.class,BigDecimal.class, Boolean.class
				};
			  	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableMP.setModel(modeleMP);
		int i=0;
		 
		while(i<listManqueImportation.size())
		{	
		ManqueImportation manqueImportation=listManqueImportation.get(i);
		
		
		
		
		String fournisseur="";
		
	 if(manqueImportation.getFournisseurMP()!=null)
	 {
		 fournisseur= manqueImportation.getFournisseurMP().getCodeFournisseur();
	 }
			
			Object []ligne={manqueImportation.getMatierePremier().getCode(),manqueImportation.getMatierePremier().getNom(),fournisseur, manqueImportation.getQuantite(),false};

			modeleMP.addRow(ligne);
			i++;
		}
}
	}



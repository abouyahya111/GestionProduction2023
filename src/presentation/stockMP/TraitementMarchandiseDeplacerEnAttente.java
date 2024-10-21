package presentation.stockMP;

import static org.hamcrest.CoreMatchers.describedAs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.event.AbortProcessingException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import main.AuthentificationView;
import main.ProdLauncher;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CheckableItem;
import util.ComparateurStockDechetMP;
import util.ComparateurStockMP;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.NumberUtils;
import util.Variables;

import com.toedter.calendar.JDateChooser;

import Production.AjouterEstimationOffre;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailMarchandiseDeplacer;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockDechetMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Inventaire;
import dao.entity.Magasin;
import dao.entity.MarchandiseDeplacer;
import dao.entity.MatierePremier;
import dao.entity.Sequenceur;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TraitementMarchandiseDeplacerEnAttente extends JLayeredPane implements Constantes {
	public   JLayeredPane contentPane;

	private   DefaultTableModel modeleEtatStock;
	private   DefaultTableModel modeleDetailEtatStock;

	private   JXTable tableEtatStock = new JXTable();

	private List<Depot> listDepotSource = new ArrayList<Depot>();
	
	private List<Depot> listparDepot = new ArrayList<Depot>();
	private List<Magasin> listMagasinSource = new ArrayList<Magasin>();
	private List<Magasin> listMagasinDestination = new ArrayList<Magasin>();
	

	// ******************************************* Listes Pour Mouvement de Stock Mp
	// **********************************************//

	private   List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();
	private   List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTmp = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortieTmp = new ArrayList<DetailTransferStockMP>();
	private   List<MarchandiseDeplacer> listMarchandiseDeplacer = new ArrayList<MarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacer = new ArrayList<DetailMarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerFrais = new ArrayList<DetailMarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerPaie = new ArrayList<DetailMarchandiseDeplacer>();
	private   List<DetailMarchandiseDeplacer> listDetailMarchandiseDeplacerImprimerProduction = new ArrayList<DetailMarchandiseDeplacer>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer = new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp = new ArrayList<DetailMouvementStock>();

	// *******************************************************************************************************************************//

	// ************************************************

	private List<DetailTransferStockMP> listDetailTransferStockMPReception = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPReceptionGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPCharge = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeGroupebyMP = new ArrayList<DetailTransferStockMP>();

	private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEnter = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEntrerGroupebyMP = new ArrayList<DetailTransferStockMP>();

	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP = new ArrayList<DetailTransferStockMP>();
	

	// ***************************************************

	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp = new ArrayList<DetailMouvementStock>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private List<MatierePremier> listMP = new ArrayList<MatierePremier>();

	private   Map<String, Depot> mapDepotSource = new HashMap<>();
	private   Map<String, Depot> mapDepotDestination = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private   Map<String, Magasin> mapMagasinSource = new HashMap<>();
	private   Map<String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;


	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private   DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;

	private JTextField txtlibelle = new JTextField();

	private DepotDAO depotdao;
	
	
	private JDateChooser dateChooser = new JDateChooser();

	JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;

	private MatierePremiereDAO MatierPremiereDAO;
	String titre = "";
	Workbook workbook = new HSSFWorkbook();
	private static JTextField txtCodTransfert;
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox comboMP = new JComboBox();
	
	private static JComboBox combomagasinSource= new JComboBox();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private CategorieMpDAO categoriempdao;
	static JDateChooser dateChooserdebut = new JDateChooser();
	static JDateChooser dateChooserfin = new JDateChooser();
	
	private static JComboBox combodepotSource= new JComboBox();
	private SubCategorieMPDAO subcategoriempdao;
	private MatierePremiereDAO matierePremiereDAO;
	JComboBox comboFournisseur = new JComboBox();
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	private static  JTable table = new JXTable();
	JButton ImprimerDetailEtatStockDechetMP = new JButton("Imprimer");
	static JComboBox combodepotDestination = new JComboBox();
	static JComboBox combomagasinDestination = new JComboBox();
	JFrame frame;
	 private SequenceurDAO sequenceurDAO;
	private List<DetailTransferStockMP> listDetailTransfertMpTmp =new ArrayList<DetailTransferStockMP>();
	
	static JComboBox jcomboboxActionsPlus=new JComboBox();
	static JComboBox jcomboboxActionsPerte=new JComboBox();
	  static JComboBox jcomboboxServiceFrais=new JComboBox();
	  static JComboBox jcomboboxServiceProduction=new JComboBox();
	  static JComboBox jcomboboxServicePaie=new JComboBox();
	  static JTextField jText=new JTextField();
	  static JScrollPane scrollPane_1 = new JScrollPane();
	  JButton btnAfficher = new JButton("Consulter");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public TraitementMarchandiseDeplacerEnAttente() {
		 
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1485, 1029);

		try {

			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgModifierr = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgValider = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgChercher = new ImageIcon(this.getClass().getResource("/img/chercher.png"));
			imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
			utilisateur = AuthentificationView.utilisateur;
			depotdao =  new DepotDAOImpl();
			mouvementStockGlobaleDAO =  new MouvementStockGlobalDAOImpl();
			MatierPremiereDAO =  new MatierePremierDAOImpl();
			detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
			listMP = MatierPremiereDAO.findAll();
			categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	 fournisseurMPDAO=new FournisseurMPDAOImpl();
        	 transferStockMPDAO=new TransferStockMPDAOImpl();
        	 sequenceurDAO=new SequenceurDAOImpl();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		tableEtatStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tableEtatStock.getSelectedRow()!=-1)
				{
					
					

					detailTransferStockMPDAO.ViderSession();
					
					
					
					listDetailMarchandiseDeplacer.clear();
					
					
					listDetailTransferStockMPTmp=detailTransferStockMPDAO.findDetailTransferMPByCodeByStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT);
					listDetailTransferStockMPSortieTmp=detailTransferStockMPDAO.findDetailTransferMPByCodeByStatut(tableEtatStock.getValueAt(tableEtatStock.getSelectedRow(), 0).toString(), Constantes.ETAT_SORTIE_ENATTENT);
					boolean  existe=false;
					BigDecimal QuantiteSortie=BigDecimal.ZERO;
				for(int t=0;t<listDetailTransferStockMPTmp.size();t++)
				{
					
					existe=false;
					DetailTransferStockMP detailTransferStockMPEntrer=listDetailTransferStockMPTmp.get(t);
					
				for(int d=0;d<listDetailTransferStockMPSortieTmp.size();d++)
				{
					
					
					DetailTransferStockMP detailTransferStockMPSortie=listDetailTransferStockMPSortieTmp.get(d);
					
					if(detailTransferStockMPEntrer.getMatierePremier().getId()==detailTransferStockMPSortie.getMatierePremier().getId())
					{
						
						if(detailTransferStockMPEntrer.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							
							if(detailTransferStockMPEntrer.getFournisseur()!=null && detailTransferStockMPSortie.getFournisseur()!=null)
							{
								
								if(detailTransferStockMPEntrer.getFournisseur().getId()== detailTransferStockMPSortie.getFournisseur().getId())
								{
									/*
									if(detailTransferStockMPEntrer.getQuantite().compareTo(detailTransferStockMPSortie.getQuantite())!=0)
									{*/
										existe=true;
										QuantiteSortie=detailTransferStockMPSortie.getQuantite();
									/*}*/
									
									
								}
								
								
								
							}
							
						}else
						{
							/*if(detailTransferStockMPEntrer.getQuantite().compareTo(detailTransferStockMPSortie.getQuantite())!=0)
							{*/
								existe=true;
								QuantiteSortie=detailTransferStockMPSortie.getQuantite();
							/*}*/
						}
						
						
						
						
						
					}
					
					
					
					
					
				}
					
				if(existe==true)
				{
					
					DetailMarchandiseDeplacer detailMarchandiseDeplacer=new DetailMarchandiseDeplacer();
					detailMarchandiseDeplacer.setDetailTransferStockMP(detailTransferStockMPEntrer);
					if(detailTransferStockMPEntrer.getFournisseur()!=null)
					{
						detailMarchandiseDeplacer.setFournisseur(detailTransferStockMPEntrer.getFournisseur());
						
					}
					detailMarchandiseDeplacer.setMatierePremier(detailTransferStockMPEntrer.getMatierePremier());
					detailMarchandiseDeplacer.setQuantiteEntrer(detailTransferStockMPEntrer.getQuantite());
					detailMarchandiseDeplacer.setQuantiteSortie(QuantiteSortie);
					detailMarchandiseDeplacer.setQuantiteValider(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
					listDetailMarchandiseDeplacer.add(detailMarchandiseDeplacer);
				}
					
					
					
					
				}
					
				afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);	
					
					
					

					
					
					
				}
				
				
			}
		});

		tableEtatStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {  "Code Transfert","Depot Source","Magasin Source", "Depot Destination","Magasin Destination"}));
		
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		
		
		tableEtatStock.setShowVerticalLines(false);
		tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
		tableEtatStock.setRowHeightEnabled(true);
		tableEtatStock.setBackground(new Color(255, 255, 255));
		tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEtatStock.setColumnControlVisible(true);
		tableEtatStock.setForeground(Color.BLACK);
		tableEtatStock.setGridColor(new Color(0, 0, 255));
		tableEtatStock.setAutoCreateRowSorter(true);
		tableEtatStock.setBounds(2, 27, 411, 198);
		tableEtatStock.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(tableEtatStock);
		scrollPane.setBounds(10, 250, 1465, 260);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Marchandises Deplacer En Attente");
		titledSeparator.setBounds(10, 209, 1465, 30);
		add(titledSeparator);
		
		 jcomboboxActionsPlus=new JComboBox();
		 jcomboboxActionsPerte=new JComboBox();
		   jcomboboxServiceFrais=new JComboBox();
		   jcomboboxServiceProduction=new JComboBox();
		   jcomboboxServicePaie=new JComboBox();
		
		jcomboboxActionsPerte.addItem("");

		  
		jcomboboxActionsPerte.addItem(Constantes.ACTION_PERTE_DE_QUANTITE);
		jcomboboxActionsPerte.addItem(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR);
		jcomboboxActionsPerte.addItem(Constantes.ACTION_ERREUR_MAGASINIER);
		jcomboboxActionsPerte.addItem(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR);
		jcomboboxActionsPerte.setEditable(false);
		
		jcomboboxActionsPlus.addItem("");
		jcomboboxActionsPlus.addItem(Constantes.ACTION_QUANTITE_A_STOCKER);
		jcomboboxActionsPlus.addItem(Constantes.ACTION_ERREUR_MAGASINIER);
		jcomboboxActionsPlus.addItem(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR);
		jcomboboxActionsPlus.setEditable(false);
		
		

		
jcomboboxServiceFrais.addItem("");
jcomboboxServiceProduction.addItem("");
jcomboboxServicePaie.addItem("");

		  
jcomboboxServiceProduction.addItem(Constantes.SERVICE_PRODUCTION);
jcomboboxServicePaie.addItem(Constantes.SERVICE_PAIE);
jcomboboxServiceFrais.addItem(Constantes.SERVICE_FRAIS);
		

jcomboboxServiceProduction.setEditable(false);
jcomboboxServicePaie.setEditable(false);
jcomboboxServiceFrais.setEditable(false);





 
modeleDetailEtatStock =new DefaultTableModel(new Object[][] {},
		new String[] {
				"Code","Matiere Premiere","Fournisseur",  "Quantite Transfert", "Quantite Transfert Reçu","Difference","Quantite Valider", "Actions"	,"Service","Commentaire"	}) {
	boolean[] columnEditables = new boolean[] {  false, false,false, false, false, false, true, true, true,true};

	public boolean isCellEditable(int row, int column) {
		
		
		return columnEditables[column];
	}
};

table = new JTable(modeleDetailEtatStock)
{
    //  Determine editor to be used by row
    public TableCellEditor getCellEditor(int row, int column)
    {
        int modelColumn = convertColumnIndexToModel( column );

        if (modelColumn == 7 && new BigDecimal(table.getValueAt(row, 4).toString()).compareTo(new BigDecimal(table.getValueAt(row, 3).toString()))<0)
        {
        	return new DefaultCellEditor(jcomboboxActionsPerte);
        }else if(modelColumn == 7 && new BigDecimal(table.getValueAt(row, 4).toString()).compareTo(new BigDecimal(table.getValueAt(row, 3).toString()))>0)
        {
        	return new DefaultCellEditor(jcomboboxActionsPlus);
        }
        
        /*
        else if(modelColumn == 7 &&  table.getValueAt(row, 6).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE))
        {
        	return new DefaultCellEditor(jcomboboxServiceFrais);
        }
        
        
        else if(modelColumn == 7 &&  table.getValueAt(row, 6).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER))
        {
        	return new DefaultCellEditor(jcomboboxServiceProduction);
        }else if(modelColumn == 7 &&  table.getValueAt(row, 6).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR))
        {
        	return new DefaultCellEditor(jcomboboxServicePaie);
        }else if(modelColumn == 7 &&  !table.getValueAt(row, 6).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR)   &&  !table.getValueAt(row, 6).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER) &&  !table.getValueAt(row, 6).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE))
        {
        	
        	modeleDetailEtatStock.setValueAt("", row, modelColumn);
        	modeleDetailEtatStock.fireTableCellUpdated(row, modelColumn);
        	
        	return super.getCellEditor(row, column);
        }  else if(modelColumn == 7 &&   table.getValueAt(row, 6).toString().equals("") )
        {
        	
        	modeleDetailEtatStock.setValueAt("", row, modelColumn);
        	modeleDetailEtatStock.fireTableCellUpdated(row, modelColumn);
        	
        	 return new DefaultCellEditor(jText);
        }  */
        
        else 
        	
        	
        	
            return  super.getCellEditor(row, column);
    }
    
    
    
    
    
    
    
    
};
table.addPropertyChangeListener(new PropertyChangeListener() {
	public void propertyChange(PropertyChangeEvent arg0) {
		

		if(table.getSelectedRow()!=-1)
		{
			
			if(table.getValueAt(table.getSelectedRow(), 7).equals(Constantes.ACTION_PERTE_DE_QUANTITE))
			{
				modeleDetailEtatStock.setValueAt(Constantes.SERVICE_FRAIS, table.getSelectedRow(),8);
			 

				
			}
			else if (table.getValueAt(table.getSelectedRow(), 7).equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR))
			{
				modeleDetailEtatStock.setValueAt(Constantes.SERVICE_PAIE, table.getSelectedRow(), 8);
				 

				
			}else if (table.getValueAt(table.getSelectedRow(), 7).equals(Constantes.ACTION_QUANTITE_A_STOCKER))
			{
				modeleDetailEtatStock.setValueAt(Constantes.SERVICE_PRODUCTION, table.getSelectedRow(), 8);
				 

				
			}
			
			else if (table.getValueAt(table.getSelectedRow(), 7).equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(table.getSelectedRow(), 7).equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR) )
			{
				modeleDetailEtatStock.setValueAt("", table.getSelectedRow(), 8);
				 
				
			}else if(table.getValueAt(table.getSelectedRow(), 7).equals(""))
			{
				modeleDetailEtatStock.setValueAt("", table.getSelectedRow(), 8);
				 

			}
		}
		
	
		
		
		
		
		
		
		
	
		
		
	}
});
table.addInputMethodListener(new InputMethodListener() {
	public void caretPositionChanged(InputMethodEvent arg0) {
	}
	public void inputMethodTextChanged(InputMethodEvent event) {}
});

 

table.getDefaultEditor(BigDecimal.class).addCellEditorListener(
          new CellEditorListener() {
          	
          	
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				  System.out.println("editingCanceled");
				
			}

			@Override
			public void editingStopped(ChangeEvent e) {


  		 	
  		 		
  		 			if(!modeleDetailEtatStock.getValueAt(table.getSelectedRow(), 6).toString().equals(""))
  		 			{
  		 				
  		 				
  		 				try {
  		 					
  		 					if(new BigDecimal(modeleDetailEtatStock.getValueAt(table.getSelectedRow(), 6).toString()).compareTo(BigDecimal.ZERO)==0)
	  		 				{
	  		 					
	  		 					if(new BigDecimal(modeleDetailEtatStock.getValueAt(table.getSelectedRow(), 6).toString()).compareTo(new BigDecimal(modeleDetailEtatStock.getValueAt(table.getSelectedRow(), 4).toString()))!=0)
	  		 					{
	  		 						
	  		 						modeleDetailEtatStock.setValueAt("", table.getSelectedRow(), 7);
	  		 						modeleDetailEtatStock.setValueAt("", table.getSelectedRow(), 8);
	  		 						modeleDetailEtatStock.setValueAt("", table.getSelectedRow(), 9);
	  		 					}
	  		 					
	  		 				 
	  		 					
	  		 					
	  		 					
	  		 					
	  		 					
	  		 				} 
							
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "Veuillez Entre Quantité Reçu en Chiffres SVP !!!","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
  		 				
  		 				
  		 			
  		 				
  		 				
		 					
  		 				
  		 				
  		 			}
  		 			
  		 			
			
			
			}
          });





 
table.addKeyListener(new KeyAdapter() {
	@Override
	public void keyReleased(KeyEvent e) { 
	
		
		 
		
		
		 
	}
});

scrollPane_1 = new JScrollPane(table);
scrollPane_1.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		
		
		
	}
});
scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
scrollPane_1.setBounds(10, 539, 1465, 260);
add(scrollPane_1);
 
		
				
				
				
	
		
				

		JLabel lblConslterLesFactures = new JLabel("           TRFS Ecart MP D\u00E9placer");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(277, 11, 1082, 35);
		add(lblConslterLesFactures);
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		 btnAfficher = new JButton("Consulter");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Consulter();
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAfficher.setBounds(590, 174, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 57, 1349, 106);
		add(layeredPane);
		
		JLabel lblDu = new JLabel("Du  :");
		lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblDu.setBounds(10, 11, 67, 24);
		layeredPane.add(lblDu);
		
		JLabel lblCodeTransfert = new JLabel("Code Transfert :");
		lblCodeTransfert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodeTransfert.setBounds(931, 66, 111, 24);
		layeredPane.add(lblCodeTransfert);
		
		txtCodTransfert = new JTextField();
		txtCodTransfert.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					MatierePremier mp=MapCodeMatierPremiere.get(txtCodTransfert.getText().toUpperCase());
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
		txtCodTransfert.setText("");
		txtCodTransfert.setColumns(10);
		txtCodTransfert.setBounds(1035, 66, 215, 26);
		layeredPane.add(txtCodTransfert);
		
		 dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(48, 9, 163, 26);
		layeredPane.add(dateChooserdebut);
		JLabel lblMagasinSource = new JLabel("Magasin Source  :");
		lblMagasinSource.setFont(new Font("Verdana", Font.BOLD, 12));
		lblMagasinSource.setBounds(828, 11, 125, 26);
		layeredPane.add(lblMagasinSource);
		
		 combomagasinSource = new JComboBox();
		combomagasinSource.setBounds(963, 11, 263, 27);
		layeredPane.add(combomagasinSource);
		combomagasinSource.addItem("");
		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) util.DateUtils.getCurrentYear() + "-01-01");

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton button = new JButton("Initialiser");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				combodepotDestination.setSelectedItem("");
				combomagasinDestination.setSelectedItem("");
				combodepotSource.setSelectedItem("");
				combomagasinSource.setSelectedItem("");
				dateChooserdebut.setCalendar(null);
			dateChooserfin.setCalendar(null);
			txtCodTransfert.setText("");

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(724, 174, 107, 24);
		add(button);
 		
 		JLabel lblAu = new JLabel("Au  :");
 		lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
 		lblAu.setBounds(227, 13, 67, 24);
 		layeredPane.add(lblAu);
 		
 		 dateChooserfin = new JDateChooser();
 		dateChooserfin.setLocale(Locale.FRANCE);
 		dateChooserfin.setDateFormatString("dd/MM/yyyy");
 		dateChooserfin.setBounds(265, 11, 163, 26);
 		layeredPane.add(dateChooserfin);
 		
 		JLabel lblDepot = new JLabel("Depot Source  :");
 		lblDepot.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepot.setBounds(457, 11, 117, 26);
 		layeredPane.add(lblDepot);
 		
 		 combodepotSource = new JComboBox();
 		 combodepotSource.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		
 		 		
 		 		if(combodepotSource.getSelectedItem()==null)
 		 		{
 		 			return;
 		 		}
 		 		
 		 		
 		 		if(!combodepotSource.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasinSource.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepotSource.get(combodepotSource.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasinSource = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasinSource.addItem("");
 						while (k < listMagasinSource.size()) {
 							Magasin magasin = listMagasinSource.get(k);

 							combomagasinSource.addItem(magasin.getLibelle());

 							mapMagasinSource.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasinSource.removeAllItems();
 		 			combomagasinSource.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 	}
 		 });
 		combodepotSource.setBounds(584, 11, 215, 27);
 		layeredPane.add(combodepotSource);
 		combodepotSource.addItem("");
 		
 		JLabel lblDepotDestination = new JLabel("Depot Destination  :");
 		lblDepotDestination.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepotDestination.setBounds(10, 66, 149, 26);
 		layeredPane.add(lblDepotDestination);
 		
 		 combodepotDestination = new JComboBox();
 		 combodepotDestination.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		if(combodepotDestination.getSelectedItem()==null)
 		 		{
 		 			return;
 		 		}

 		 		
 		 		if(!combodepotDestination.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasinDestination.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepotSource.get(combodepotDestination.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasinDestination = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
 						int k = 0;
 						combomagasinDestination.addItem("");
 						while (k < listMagasinDestination.size()) {
 							Magasin magasin = listMagasinDestination.get(k);

 							combomagasinDestination.addItem(magasin.getLibelle());

 							mapMagasinDestination.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasinDestination.removeAllItems();
 		 			combomagasinDestination.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 	
 		 		
 		 		
 		 	}
 		 });
 		combodepotDestination.setBounds(169, 66, 202, 27);
 		layeredPane.add(combodepotDestination);
 		combodepotDestination.addItem("");
 		JLabel lblMagasinDestination = new JLabel("Magasin Destination  :");
 		lblMagasinDestination.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblMagasinDestination.setBounds(381, 66, 155, 26);
 		layeredPane.add(lblMagasinDestination);
 		
 		 combomagasinDestination = new JComboBox();
 		combomagasinDestination.setBounds(561, 66, 263, 27);
 		layeredPane.add(combomagasinDestination);
 		combomagasinDestination.addItem("");
  		  
  		  
		if (utilisateur.getLogin().equals("admin")) {
			
			listDepotSource =depotdao.findAll();
			
			combodepotSource.removeAllItems();
			combodepotSource.addItem("");
			combodepotDestination.removeAllItems();
			combodepotDestination.addItem("");
			
			for(int d=0;d<listDepotSource.size();d++)
			{
				
			Depot depot=listDepotSource.get(d);
			combodepotSource.addItem(depot.getLibelle());
			combodepotDestination.addItem(depot.getLibelle());
			mapDepotSource.put(depot.getLibelle(), depot);
			mapDepotDestination.put(depot.getLibelle(), depot);	
				
				
				
			}
			
	

		}
		
		
		
		
		combodepotDestination.setSelectedItem("");
		combomagasinDestination.setSelectedItem("");
		combodepotSource.setSelectedItem("");
		combomagasinSource.setSelectedItem("");
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				boolean actionecartnegatif=false;
				boolean actionecartpositif=false;
				boolean tablevide=true;
				boolean ManqueExiste=false;
				boolean ActionExiste=false;
				boolean ActionNonsaisir=false;
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Valider l'opération  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						boolean MarchandiseAnnule=false;
						boolean ErreurPrix=false;
						boolean MarchandiseRetourner=false;
						String msg="";
						
						for(int c=0;c<listDetailMarchandiseDeplacer.size();c++)		
						{
						 
							if(listDetailMarchandiseDeplacer.get(c).getMatierePremier().getPrix().compareTo(BigDecimal.ZERO)==0)
							{
								ErreurPrix=true;
								
								msg=msg+ listDetailMarchandiseDeplacer.get(c).getMatierePremier().getNom()+"\n";
							}
						}
						
						
						
						for(int i=0;i<table.getRowCount();i++)
						{
							 if( table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
							 {
								 MarchandiseAnnule=true;
							 } 
							 
							 if( !table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) && !table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR) && !table.getValueAt(i, 7).toString().equals("") )
							 {
								 MarchandiseRetourner=true;
							 }
							
						}
						
						
						if(MarchandiseAnnule==false && MarchandiseRetourner==true)
						{
							if(ErreurPrix==true)
							{
								JOptionPane.showMessageDialog(null, " Veuillez Entrer le Prix des MP Suivante : "+msg);
								return;
								
							}
						}
						
						
						
						
						
						listDetailMarchandiseDeplacerImprimerFrais.clear();
						listDetailMarchandiseDeplacerImprimerPaie.clear();
						listDetailMarchandiseDeplacerImprimerProduction.clear();
						
						
						
						
						for(int i=0;i<table.getRowCount();i++)
						{
							
							
							tablevide=false; 
							
					for(int d=0;d<listDetailMarchandiseDeplacer.size();d++)		
					{
						DetailMarchandiseDeplacer detailMarchandiseDeplacer=listDetailMarchandiseDeplacer.get(d);
						
						if(detailMarchandiseDeplacer.getMatierePremier().getCode().equals(table.getValueAt(i, 0).toString()))
						{
							
							if(detailMarchandiseDeplacer.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								if(detailMarchandiseDeplacer.getFournisseur().getCodeFournisseur().equals(table.getValueAt(i, 2).toString()))
								{
									
									
									if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))!=0 && new BigDecimal(table.getValueAt(i, 6).toString()).compareTo(new BigDecimal(table.getValueAt(i, 4).toString()))!=0)
									{
										
										ManqueExiste=true;
										detailMarchandiseDeplacer.setManqueExiste(Constantes.CODE_OUI);
										
										if( !table.getValueAt(i, 6).toString().equals(""))
										{
											
											detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 6).toString()));
											
										}else
										{
											detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(0));
										}
										
										
									}else if((new BigDecimal(table.getValueAt(i, 6).toString()).compareTo(new BigDecimal(table.getValueAt(i, 4).toString()))==0 && new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(BigDecimal.ZERO)!=0) || new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))==0)
									{
										
										ActionExiste=true;
										
										detailMarchandiseDeplacer.setManqueExiste(Constantes.CODE_NON);
										
										
										if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))==0)
										{
											
											 
												
											detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 4).toString()));
												
											 
											
										}else
										{
											if( !table.getValueAt(i, 6).toString().equals(""))
											{
												
												detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 6).toString()));
												
											}else
											{
												detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(0));
											}
										}
										
									
										
										
										if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))<0)
										{
											
											if( !table.getValueAt(i, 7).toString().equals(""))
											{
												
												
												 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
												 {
													
													 
													 detailMarchandiseDeplacer.setAction(table.getValueAt(i, 7).toString());  
													 detailMarchandiseDeplacer.setService(table.getValueAt(i, 8).toString());
													 detailMarchandiseDeplacer.setCommentaire(table.getValueAt(i, 9).toString());
													 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR) )
													 {
														 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
														 
														
														
														 
													 }else if( table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
													 {
														 
														 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
													 }
													 
													 detailMarchandiseDeplacer.setEtatManque(ETAT_MANQUE_NEGATIF);
													 
													 if(detailMarchandiseDeplacer.getQuantiteValider().compareTo (detailMarchandiseDeplacer.getQuantiteSortie())<0)
													 {
														 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie())).multiply(new BigDecimal(-1)));
														 
														 
														 
														 
														 
													 }else
													 {
														 
														 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie()))); 
													 } 
													 
													 
													 
												 }else
												 {
													 actionecartnegatif=true;
												 }
												
												
												
												
											}else
											{
												ActionNonsaisir=true;
											}
											
										}else if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))>0)
										{
											
											
											if( !table.getValueAt(i, 7).toString().equals(""))
											{
												
												
												 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER)  || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
												 {
													
													 
													 detailMarchandiseDeplacer.setAction(table.getValueAt(i, 7).toString());  
													 detailMarchandiseDeplacer.setService(table.getValueAt(i, 8).toString());
													 detailMarchandiseDeplacer.setCommentaire(table.getValueAt(i, 9).toString());
													  
													 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER)  )
													 {
														 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
														 
													 }else if( table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
													 {
														 
														 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
													 }
													 
													 detailMarchandiseDeplacer.setEtatManque(ETAT_MANQUE_POSITIF);
													 
													 if(detailMarchandiseDeplacer.getQuantiteValider().compareTo (detailMarchandiseDeplacer.getQuantiteSortie())<0)
													 {
														 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie())).multiply(new BigDecimal(-1)));
														 
														 
														 
														 
														 
													 }else
													 {
														 
														 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie()))); 
													 }
													 
													 
													 
												 }else
												 {
													 actionecartpositif=true;
												 }
												
												
												
												
											}else
											{
												ActionNonsaisir=true;
											}
											
											
											
										}
										
										
										
										
										
									}
									
									
									
								}
								
								
							}else
							{
								
								
								if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))!=0 && new BigDecimal(table.getValueAt(i, 6).toString()).compareTo(new BigDecimal(table.getValueAt(i, 4).toString()))!=0)
								{
									
									ManqueExiste=true;
									detailMarchandiseDeplacer.setManqueExiste(Constantes.CODE_OUI);
									
									if( !table.getValueAt(i, 6).toString().equals(""))
									{
										
										detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 6).toString()));
										
									}else
									{
										detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(0));
									}
									
									
								}else if((new BigDecimal(table.getValueAt(i, 6).toString()).compareTo(new BigDecimal(table.getValueAt(i, 4).toString()))==0 && new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(BigDecimal.ZERO)!=0) || new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))==0)
								{
									
									ActionExiste=true;
									
									detailMarchandiseDeplacer.setManqueExiste(Constantes.CODE_NON);
									
									
									if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))==0)
									{
										
										 
											
										detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 4).toString()));
											
										 
										
									}else
									{
										if( !table.getValueAt(i, 6).toString().equals(""))
										{
											
											detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(table.getValueAt(i, 6).toString()));
											
										}else
										{
											detailMarchandiseDeplacer.setQuantiteValider(new BigDecimal(0));
										}
									}
									
								
									
									
									if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))<0)
									{
										
										if( !table.getValueAt(i, 7).toString().equals(""))
										{
											
											
											 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
											 {
												
												 
												 detailMarchandiseDeplacer.setAction(table.getValueAt(i, 7).toString());  
												 detailMarchandiseDeplacer.setService(table.getValueAt(i, 8).toString());
												 detailMarchandiseDeplacer.setCommentaire(table.getValueAt(i, 9).toString());
												 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_PERTE_DE_QUANTITE) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR) )
												 {
													 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
													 
													
													
													 
												 }else if( table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
												 {
													 
													 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
												 }
												 
												 detailMarchandiseDeplacer.setEtatManque(ETAT_MANQUE_NEGATIF);
												 
												 if(detailMarchandiseDeplacer.getQuantiteValider().compareTo (detailMarchandiseDeplacer.getQuantiteSortie())<0)
												 {
													 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie())).multiply(new BigDecimal(-1)));
													 
													 
													 
													 
													 
												 }else
												 {
													 
													 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie()))); 
												 } 
												 
												 
												 
											 }else
											 {
												 actionecartnegatif=true;
											 }
											
											
											
											
										}else
										{
											ActionNonsaisir=true;
										}
										
									}else if(new BigDecimal(table.getValueAt(i, 4).toString()).compareTo(new BigDecimal(table.getValueAt(i, 3).toString()))>0)
									{
										
										
										if( !table.getValueAt(i, 7).toString().equals(""))
										{
											
											
											 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER)  || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
											 {
												
												 
												 detailMarchandiseDeplacer.setAction(table.getValueAt(i, 7).toString());  
												 detailMarchandiseDeplacer.setService(table.getValueAt(i, 8).toString());
												 detailMarchandiseDeplacer.setCommentaire(table.getValueAt(i, 9).toString());
												  
												 if(table.getValueAt(i, 7).toString().equals(Constantes.ACTION_QUANTITE_A_STOCKER)  )
												 {
													 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
													 
												 }else if( table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_MAGASINIER) || table.getValueAt(i, 7).toString().equals(Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR))
												 {
													 
													 detailMarchandiseDeplacer.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
												 }
												 
												 detailMarchandiseDeplacer.setEtatManque(ETAT_MANQUE_POSITIF);
												 
												 if(detailMarchandiseDeplacer.getQuantiteValider().compareTo (detailMarchandiseDeplacer.getQuantiteSortie())<0)
												 {
													 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie())).multiply(new BigDecimal(-1)));
													 
													 
													 
													 
													 
												 }else
												 {
													 
													 detailMarchandiseDeplacer.setQuantiteManque((detailMarchandiseDeplacer.getQuantiteValider().subtract(detailMarchandiseDeplacer.getQuantiteSortie()))); 
												 }
												 
												 
												 
											 }else
											 {
												 actionecartpositif=true;
											 }
											
											
											
											
										}else
										{
											ActionNonsaisir=true;
										}
										
										
										
									}
									
									
									
									
									
								}
								
								
								
								
							}
							
							
							
							
							listDetailMarchandiseDeplacer.set(d, detailMarchandiseDeplacer);
							
							
						}
						
						
						
						
						
					}
							
							
							
							
							
				
							
							
							
							
							
							
							
						}
						
						
						
						if(actionecartnegatif==true)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner Pour Ecart Négatif une parmis les actions Suivant :"+Constantes.ACTION_PERTE_DE_QUANTITE +" ; "+Constantes.ACTION_AVANCE_SUR_CHAUFFEUR+" ; "+Constantes.ACTION_ERREUR_MAGASINIER+" Ou "+Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR +" SVP !!!");
							return;
						}
						
						if(actionecartpositif==true)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner Pour Ecart Positive une Parmis les actions Suivant :"+Constantes.ACTION_QUANTITE_A_STOCKER +" ; "+Constantes.ACTION_ERREUR_MAGASINIER+" Ou "+Constantes.ACTION_ERREUR_DE_SAISIE_OPERATEUR +" SVP !!!");
							return;
						}
						
						
						if(ActionNonsaisir==true && ManqueExiste==false)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner les actions Pour Les MP Qui Contient Des Manques SVP !!!");
							return;
						}
						
						if(tablevide==false)
						{
							
							
						TransferStockMP transfererStockMP=null;	
						boolean annuler=false;	
							
						for(int t=0;t<listDetailMarchandiseDeplacer.size();t++)
						{
							
							
							DetailTransferStockMP detailTransferStockMP=listDetailMarchandiseDeplacer.get(t).getDetailTransferStockMP();
							
							
						if(ManqueExiste==true)	
						{
							 if(listDetailMarchandiseDeplacer.get(t).getManqueExiste()!=null)
							 {
								 if(listDetailMarchandiseDeplacer.get(t).getManqueExiste().equals(Constantes.CODE_OUI))
								 {
									 detailTransferStockMP.setQuantiteAncien(listDetailMarchandiseDeplacer.get(t).getQuantiteEntrer());
										detailTransferStockMP.setQuantite(listDetailMarchandiseDeplacer.get(t).getQuantiteValider());
										 					
										transfererStockMP=detailTransferStockMP.getTransferStockMP();
										 
									  
											detailTransferStockMPDAO.edit(detailTransferStockMP);
								 }
							 }
						
							
							 
					 
							
							
						}else
						{
							
							detailTransferStockMP.setAction(listDetailMarchandiseDeplacer.get(t).getAction());
							detailTransferStockMP.setCommentaire(listDetailMarchandiseDeplacer.get(t).getCommentaire());
							detailTransferStockMP.setQuantiteAncien(listDetailMarchandiseDeplacer.get(t).getQuantiteEntrer());
							detailTransferStockMP.setQuantite(listDetailMarchandiseDeplacer.get(t).getQuantiteValider());
							detailTransferStockMP.setEtat(listDetailMarchandiseDeplacer.get(t).getEtat());							
							transfererStockMP=detailTransferStockMP.getTransferStockMP();
							detailTransferStockMP.setService(listDetailMarchandiseDeplacer.get(t).getService());
							if(detailTransferStockMP.getEtat()!=null)
							{
								if(detailTransferStockMP.getEtat().equals(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER))
								{
									annuler=true;	
								}
							}
						
							
							
							 
								detailTransferStockMPDAO.edit(detailTransferStockMP);
							 
							
							
							if(detailTransferStockMP.getAction()!=null)
							{
								
								if(detailTransferStockMP.getAction().equals(Constantes.ACTION_PERTE_DE_QUANTITE))
								{
									if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
									{
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

									}else
									{
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

									}
									
									listDetailMarchandiseDeplacerImprimerFrais.add(listDetailMarchandiseDeplacer.get(t));
								}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_QUANTITE_A_STOCKER))
								{
									if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
									{
										
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

										
									}else
									{
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

									}
									
									listDetailMarchandiseDeplacerImprimerProduction.add(listDetailMarchandiseDeplacer.get(t));
								}else if(detailTransferStockMP.getAction().equals(Constantes.ACTION_AVANCE_SUR_CHAUFFEUR))
								{
									if(DateUtils.getAnnee(dateChooserdebut.getDate())==2023)
									{
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrix().multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

									}else
									{
										listDetailMarchandiseDeplacer.get(t).setMontant(listDetailMarchandiseDeplacer.get(t).getMatierePremier().getPrixByYear(DateUtils.getAnnee(dateChooserdebut.getDate())).multiply(listDetailMarchandiseDeplacer.get(t).getQuantiteManque()).setScale(2, RoundingMode.HALF_UP));

										
									}
									
									listDetailMarchandiseDeplacerImprimerPaie.add(listDetailMarchandiseDeplacer.get(t));
								}
							}
							
							
						}
							
							
						
							
							
							
							
							
						}
		
						
						if(ManqueExiste==true)
						{
							if(transfererStockMP!=null)
							{
								transfererStockMP.setVuPar(AFFICHER_TRANSFERT_ENATTENTE_USER);
								transferStockMPDAO.edit(transfererStockMP);
							}
							
						}

							
							
						if(annuler==true)
						{
							
							if(ManqueExiste==false)
							{
								if(transfererStockMP!=null)
								{
									
									transfererStockMP.setStatut(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
									transfererStockMP.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
									transfererStockMP.setVuPar(AFFICHER_TRANSFERT_ENATTENTE_ADMIN);
									transferStockMPDAO.edit(transfererStockMP);
									
									TransferStockMP transfererStockMPTmp=transferStockMPDAO.findTransferByCodeStatut(transfererStockMP.getCodeTransfer(), Constantes.ETAT_SORTIE_ENATTENT);
									transfererStockMPTmp.setStatut(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_SORTIE_ANNULER);
									transfererStockMPTmp.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_ANNULER);
									transferStockMPDAO.edit(transfererStockMPTmp);
									Consulter();
									 
									listDetailMarchandiseDeplacer.clear();
									afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);
									
									JOptionPane.showMessageDialog(null, "Marchandise Déplacé Anullé","Information",JOptionPane.INFORMATION_MESSAGE);
									
									return;
									
								}
							}
							
							
							
							
							
							
							
						}else
						{
							
							
							
							
							///////////////////////////////////////////////////////////////////////////// BON FRAIS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
											
											
											 String date =""; 
											 String NumBon="";
											
											
											
											if(listDetailMarchandiseDeplacerImprimerFrais.size()!=0)
											{
												
												date= dcn.format(listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												
												
											 
												
												
												  String code="PROD "; 
													 
												  
												  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
												  if(sequenceur!=null) { 
													  
													  
													  
													  int  valeur=sequenceur.getValeur()+1;
													  if(valeur<100 && valeur>=10) {
												  code=code+"0"+valeur+"-"+date; 
												  }else if(valeur<10) 
												  { code=code+"00"+valeur+"/"+date;
												  }else if(valeur>=100) 
												  { code=code+valeur+"-"+date; } 
													 
													  sequenceur.setValeur(valeur);
													  sequenceurDAO.edit(sequenceur);
												  
												  }else
													  {
														  
															Sequenceur sequenceurTmp=new Sequenceur();
															
															sequenceurTmp.setCode(date);
															sequenceurTmp.setLibelle(Constantes.CODE_BON);
															sequenceurTmp.setValeur(1);
															
															sequenceurDAO.add(sequenceurTmp);
												  
												  code=code+"00"+1+"-"+date;
												  
												  } 
												
												
												
												
												Map parameters = new HashMap();
												parameters.put("servicetitre","BON FRAIS" );
												
												parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
												parameters.put("bonnum",code );
												parameters.put("date", listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												parameters.put("depot", listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
												parameters.put("magasin",listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
												
												JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerFrais,parameters);	
										
												TransferStockMP transferStockMP=listDetailMarchandiseDeplacerImprimerFrais.get(0).getDetailTransferStockMP().getTransferStockMP();
												transferStockMP.setNumbonFrais(code);
												transferStockMPDAO.edit(transferStockMP);
												
												
												
											}
											
											///////////////////////////////////////////////////////////////////////////// BON PRODUCTION /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
											
											
											  date =""; 
											  NumBon="";
											
											
											
											if(listDetailMarchandiseDeplacerImprimerProduction.size()!=0)
											{
												
												date= dcn.format(listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												
												
											 
												
												
												  String code="PROD "; 
													 
												  
												  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
												  if(sequenceur!=null) { 
													  int  valeur=sequenceur.getValeur()+1;
													  if(valeur<100 && valeur>=10) {
												  code=code+"0"+valeur+"-"+date; 
												  }else if(valeur<10) 
												  { code=code+"00"+valeur+"/"+date;
												  }else if(valeur>=100) 
												  { code=code+valeur+"-"+date; } 
													  
													  sequenceur.setValeur(valeur);
													  sequenceurDAO.edit(sequenceur); 
													  
													  
													  }else
													  {
														  
															Sequenceur sequenceurTmp=new Sequenceur();
															
															sequenceurTmp.setCode(date);
															sequenceurTmp.setLibelle(Constantes.CODE_BON);
															sequenceurTmp.setValeur(1);
															
															sequenceurDAO.add(sequenceurTmp);
												  
												  code=code+"00"+1+"-"+date;
												  
												  } 
												
												
												
												
												Map parameters = new HashMap();
												parameters.put("servicetitre","BON PRODUCTION" );
												parameters.put("bonnum",code );
												parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
												parameters.put("date", listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												parameters.put("depot", listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
												parameters.put("magasin",listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
												
												JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerProduction,parameters);	
										
												TransferStockMP transferStockMP=listDetailMarchandiseDeplacerImprimerProduction.get(0).getDetailTransferStockMP().getTransferStockMP();
												transferStockMP.setNumbonProduction (code);
												transferStockMPDAO.edit(transferStockMP);
												
												
												
												
												
											}
											
											///////////////////////////////////////////////////////////////////////////// BON PAIE /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
											
											
											  date =""; 
											  NumBon="";
											
											
											
											if(listDetailMarchandiseDeplacerImprimerPaie.size()!=0)
											{
												
												date= dcn.format(listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												
												
											 
												
												
												  String code="PROD "; 
													 
												  
												  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_BON); 
												  if(sequenceur!=null) { 
													  int  valeur=sequenceur.getValeur()+1;
													  if(valeur<100 && valeur>=10) {
												  code=code+"0"+valeur+"-"+date; 
												  }else if(valeur<10) 
												  { code=code+"00"+valeur+"/"+date;
												  }else if(valeur>=100) 
												  { code=code+valeur+"-"+date; } 
													  
													  sequenceur.setValeur(valeur);
													  sequenceurDAO.edit(sequenceur);
												  
												  
												  }else
													  {
														  
															Sequenceur sequenceurTmp=new Sequenceur();
															
															sequenceurTmp.setCode(date);
															sequenceurTmp.setLibelle(Constantes.CODE_BON);
															sequenceurTmp.setValeur(1);
															
															sequenceurDAO.add(sequenceurTmp);
												  
												  code=code+"00"+1+"-"+date;
												  
												  } 
												
												
												
												
												Map parameters = new HashMap();
												parameters.put("servicetitre","BON PAIE" );
												parameters.put("bonnum",code );
												parameters.put("codetransfert",listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getCodeTransfer() );
												parameters.put("date", listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP().getDateTransfer());
												parameters.put("depot", listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getMagasinDestination().getDepot().getLibelle());
												parameters.put("magasin",listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getMagasinDestination().getLibelle());
												
												JasperUtils.imprimerBonFrais(listDetailMarchandiseDeplacerImprimerPaie,parameters);	
										
												TransferStockMP transferStockMP=listDetailMarchandiseDeplacerImprimerPaie.get(0).getDetailTransferStockMP().getTransferStockMP();
												transferStockMP.setNumbonPaie(code);
												transferStockMPDAO.edit(transferStockMP);
												
												
												
												
												
											}	
							
							
							
							
							
							
							
							
							
							
							
							
							
							if(transfererStockMP!=null && ManqueExiste==false)
							{
								
								
								listDetailTransfertMpTmp.clear();
								listDetailTransfertMpTmp=detailTransferStockMPDAO.findByTransferStockMP(transfererStockMP.getId());
								
								
								transfererStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
								transfererStockMP.setEtat (ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
								transfererStockMP.setVuPar(AFFICHER_TRANSFERT_ENATTENTE_ADMIN);
								transferStockMPDAO.edit(transfererStockMP);

								
								TransferStockMP transferStockMPTMP = transferStockMPDAO.findTransferByCodeStatut(transfererStockMP.getCodeTransfer(), Constantes.ETAT_SORTIE_ENATTENT);
								transferStockMPTMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
								transferStockMPTMP.setEtat(ETAT_MARCHANDISE_DESPLACER_EN_ATTENTE_VALIDER);
								transferStockMPDAO.edit(transferStockMPTMP);
								
								
								TransferStockMP transferStockMP=new TransferStockMP();
									transferStockMP.setCodeTransfer(listDetailTransfertMpTmp.get(0).getTransferStockMP().getCodeTransfer());
									transferStockMP.setCreerPar(utilisateur);
									transferStockMP.setDate(new Date());
									transferStockMP.setDateTransfer(transferStockMPTMP.getDateTransfer());
									transferStockMP.setDepot(listDetailTransfertMpTmp.get(0).getTransferStockMP().getDepot());
									transferStockMP.setSoustype(listDetailTransfertMpTmp.get(0).getTransferStockMP().getSoustype());
									transferStockMP.setType(listDetailTransfertMpTmp.get(0).getTransferStockMP().getType());
									transferStockMP.setStatut(Constantes.ETAT_SORTIE_ENATTENT_VALIDER);
									transferStockMPDAO.add(transferStockMP);
									
									for(int d=0;d<listDetailTransfertMpTmp.size();d++)
									{
										
									DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();	
									detailTransferStockMP.setFournisseur(listDetailTransfertMpTmp.get(d).getFournisseur());
									detailTransferStockMP.setMagasinDestination(listDetailTransfertMpTmp.get(d).getMagasinDestination());
									detailTransferStockMP.setMagasinSouce(listDetailTransfertMpTmp.get(d).getMagasinSouce());
									detailTransferStockMP.setMatierePremier(listDetailTransfertMpTmp.get(d).getMatierePremier());
									detailTransferStockMP.setPrixUnitaire(listDetailTransfertMpTmp.get(d).getPrixUnitaire());
									detailTransferStockMP.setQuantite(listDetailTransfertMpTmp.get(d).getQuantite());
									detailTransferStockMP.setQuantiteDechet(listDetailTransfertMpTmp.get(d).getQuantiteDechet());
									detailTransferStockMP.setQuantiteExistante(listDetailTransfertMpTmp.get(d).getQuantiteExistante());
									detailTransferStockMP.setQuantiteManque(listDetailTransfertMpTmp.get(d).getQuantiteManque());
									detailTransferStockMP.setQuantiteOffre(listDetailTransfertMpTmp.get(d).getQuantiteOffre());
									detailTransferStockMP.setQuantiteRetour(listDetailTransfertMpTmp.get(d).getQuantiteRetour());
									detailTransferStockMP.setStockSource(listDetailTransfertMpTmp.get(d).getStockSource());
									detailTransferStockMP.setQuantiteAncien(listDetailTransfertMpTmp.get(d).getQuantiteAncien());
									detailTransferStockMP.setAction(listDetailTransfertMpTmp.get(d).getAction());
									detailTransferStockMP.setEtat(listDetailTransfertMpTmp.get(d).getEtat());
									detailTransferStockMP.setService(listDetailTransfertMpTmp.get(d).getService());
									detailTransferStockMP.setCommentaire(listDetailTransfertMpTmp.get(d).getCommentaire());
									detailTransferStockMP.setTransferStockMP(transferStockMP);
									detailTransferStockMPDAO.add(detailTransferStockMP);
									
									
									}
									
									
									
									
								
								
									Consulter();
									 
									
JOptionPane.showMessageDialog(null, "Marchandise Déplacé Validé","Information",JOptionPane.INFORMATION_MESSAGE);
listDetailMarchandiseDeplacer.clear();
afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);
									return;	
									
								
							}
							
							
							
							
						}
							
						listDetailMarchandiseDeplacer.clear();
						afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);			
						Consulter();	
							
							
						}
						
						
					 
						
						
					}
				
				
				
			
				
				
				
				
				
			
				
				 
				
				
				
			}
		});
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValider.setBounds(707, 810, 137, 30);
		add(btnValider);
		
		/*
		   frame.addWindowListener(new WindowAdapter() {

			   @Override
			   public void windowClosing(WindowEvent e) {
			       // handle closing the window
			         AjouterActionsMarchandiseDeplacerEnAttenteIsOpne = false;
			       frame.setVisible(false);
			       frame.dispose();
			   }
			   });
		
		*/
		
		 consulterSansFiltrer();

	}

	void InitialiseTableauDetailMouvementStock() {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Code","Matiere Premiere","Quantite Initial", "Quantite Reception","Transfert Entre", "Quantite Charger", "Quantite Charge Supp",
						 "Quantite Retour","Quantite Sortie", "Quantite Finale", }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
		tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);

	}

	/*
	 * void InitialiseTableauMouvementStock() { modeleMouvementStock =new
	 * DefaultTableModel( new Object[][] { }, new String[] { "Date Mouvement",
	 * "Depot", "Magasin" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false }; public boolean isCellEditable(int row, int column) {
	 * return columnEditables[column]; } }; table.setModel(modeleMouvementStock);
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * 
	 * 
	 * 
	 * }
	 */

	  void afficher_tableEtatStock(List<MarchandiseDeplacer> listMarchandise) {
		InitialiserTable();
	
		int i = 0;

		while (i < listMarchandise.size()) {
			MarchandiseDeplacer marchandiseDeplacer = listMarchandise.get(i);

	Object[] ligne = {marchandiseDeplacer.getCodeTransfert(),marchandiseDeplacer.getMagasinSouce().getDepot().getLibelle(),marchandiseDeplacer.getMagasinSouce().getLibelle(),marchandiseDeplacer.getMagasinDestination().getDepot().getLibelle(),marchandiseDeplacer.getMagasinDestination().getLibelle() };
	
	

	modeleEtatStock.addRow(ligne);

		

			i++;
		}
	}
	
	
	
	
public void InitialiserTable()
{
	modeleEtatStock = new DefaultTableModel(new Object[][] {},
			new String[] {   "Code Transfert","Depot Source","Magasin Source", "Depot Destination","Magasin Destination" }) {
		boolean[] columnEditables = new boolean[] {  false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	tableEtatStock.setModel(modeleEtatStock);
}
	
	
	  void afficher_tableDetailEtatStock(List<DetailMarchandiseDeplacer> listDetailMarchandise) {
		
				
	
	//final ListModel<CheckableItem> model=jcomboboxService.getModel();

 
		
		
		
		
		modeleDetailEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {
						"Code","Matiere Premiere","Fournisseur",  "Quantite Transfert", "Quantite Transfert Reçu","Difference","Quantite Valider", "Actions"	,"Service","Commentaire"	}) {
			boolean[] columnEditables = new boolean[] {
					false, false,false, false, false,true, true, true, true, true}
			;

			public boolean isCellEditable(int row, int column) {
				
				
				
				 
				if( (new BigDecimal(modeleDetailEtatStock.getValueAt(row, 3).toString()).compareTo(new BigDecimal(modeleDetailEtatStock.getValueAt(row, 4).toString())))==0 &&  (column==5 ||column==6 ||column==7 ||column==8 ||column==9) )
	     			{
					return false;
	     			}else if((new BigDecimal(modeleDetailEtatStock.getValueAt(row, 4).toString()).compareTo(new BigDecimal(modeleDetailEtatStock.getValueAt(row, 6).toString())))!=0 && ( column==7 ||column==8 ||column==9))
{
	     				
	     				table.setValueAt("", row, column);
	return false;
	
	
	
	
	
	
}
				
				else
	     				
				return columnEditables[column];
			}
		};
		
		/*
		table.setModel(modeleDetailEtatStock);
		// table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcomboboxActionsPlus));
		 table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jcomboboxService));
		*/
		
		int i = 0;
String fournisseur="";
		while (i < listDetailMarchandise.size()) {
			DetailMarchandiseDeplacer detailMarchandiseDeplacer = listDetailMarchandise.get(i);
			if(detailMarchandiseDeplacer.getFournisseur()!=null)
			{
				fournisseur=detailMarchandiseDeplacer.getFournisseur().getCodeFournisseur();
				
			}else
			{
				fournisseur="";
			}
			

	Object[] ligne = {detailMarchandiseDeplacer.getMatierePremier().getCode(),detailMarchandiseDeplacer.getMatierePremier().getNom(),fournisseur,detailMarchandiseDeplacer.getQuantiteSortie(),detailMarchandiseDeplacer.getQuantiteEntrer(),detailMarchandiseDeplacer.getQuantiteSortie().subtract(detailMarchandiseDeplacer.getQuantiteEntrer()), detailMarchandiseDeplacer.getQuantiteValider(),"","",""};
	
	 
	
	modeleDetailEtatStock.addRow(ligne);
	

	

		

			i++;
		}
		
		table.setModel(modeleDetailEtatStock);
	
		
		
		/*
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(102);
		table.getColumnModel().getColumn(3).setPreferredWidth(102);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		table.getColumnModel().getColumn(5).setPreferredWidth(102);
		*/
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	public   void Consulter()
	{
		detailTransferStockMPDAO.ViderSession();
		
		listMarchandiseDeplacer.clear();
		afficher_tableEtatStock(listMarchandiseDeplacer);

		listDetailMarchandiseDeplacer.clear();
	afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);
		
		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && combodepotSource.getSelectedItem().equals("") && combomagasinSource.getSelectedItem().equals("") && combodepotDestination.getSelectedItem().equals("") && combomagasinDestination.getSelectedItem().equals("") && txtCodTransfert.getText().equals(""))
		{
			
			consulterSansFiltrer();
			 
		}else
		{
			transferStockMPDAO.ViderSession();
			
			
			
			String req="";
			
			req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"' and vuPar='"+Constantes.AFFICHER_TRANSFERT_ENATTENTE_ADMIN+"' ";
			
			
			
			
			
			dateChooserdebut.setDateFormatString("yyyy-MM-dd");
			String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			dateChooserfin.setDateFormatString("yyyy-MM-dd");
			String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();

			if(!dateDu.equals("") && dateAu.equals(""))
			{
				dateAu=dateDu;
			}else if(dateDu.equals("") && !dateAu.equals(""))
			{
				dateDu=dateAu;
			}
			
			
			
			if(!dateDu.equals("") && !dateAu.equals(""))
			{
				req=req+" and  d.transferStockMP.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
				
			}
			
			if(!txtCodTransfert.getText().equals(""))
			{
				req=req+" and d.transferStockMP.CodeTransfer='"+txtCodTransfert.getText()+"' ";
			}
			
			if(!combodepotDestination.getSelectedItem().equals(""))
			{
				req=req+" and d.transferStockMP.depot.id='"+mapDepotDestination.get(combodepotDestination.getSelectedItem()).getId()+"' ";
			}
			
			if(!combomagasinDestination.getSelectedItem().equals(""))
			{
				req=req+" and d.magasinDestination.id='"+mapMagasinDestination.get(combomagasinDestination.getSelectedItem()).getId()+"' ";
			}
			
			listDetailTransferStockMP=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
		
			
			req="";
			
			req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_SORTIE_ENATTENT+"'  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"') ";
			
			
			if(!combodepotSource.getSelectedItem().equals(""))
			{
				req=req+" and d.transferStockMP.depot.id='"+mapDepotSource.get(combodepotSource.getSelectedItem()).getId()+"' ";
			}
			
			if(!combomagasinSource.getSelectedItem().equals(""))
			{
				req=req+" and d.magasinSouce.id='"+mapMagasinSource.get(combomagasinSource.getSelectedItem()).getId()+"' ";
			}
			
			listDetailTransferStockMPSortie=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
			
			boolean existe=false;
			for(int i=0;i<listDetailTransferStockMP.size();i++)
			{
				
				DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(i);
				existe=false;	
				
				Magasin magasinSource=null;
				for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
				{
					
					DetailTransferStockMP detailTransferStockMPTmp=listDetailTransferStockMPSortie.get(j);
				if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(detailTransferStockMPTmp.getTransferStockMP().getCodeTransfer()))
				{
					 
					
					existe=true;
					magasinSource=detailTransferStockMPTmp.getMagasinSouce();
				}
					
					
					
				}
				
				if(existe==true)
				{
					
					MarchandiseDeplacer marchandiseDeplacer=new MarchandiseDeplacer();
					marchandiseDeplacer.setCodeTransfert(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
					marchandiseDeplacer.setMagasinDestination(detailTransferStockMP.getMagasinDestination());
					marchandiseDeplacer.setMagasinSouce(magasinSource);
					listMarchandiseDeplacer.add(marchandiseDeplacer);
				}
				
				
				
				
				
				
			}
			
			
			
			afficher_tableEtatStock(listMarchandiseDeplacer);
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	void consulterSansFiltrer()
	{
	
			detailTransferStockMPDAO.ViderSession();
			
			listMarchandiseDeplacer.clear();
			afficher_tableEtatStock(listMarchandiseDeplacer);

			listDetailMarchandiseDeplacer.clear();
		afficher_tableDetailEtatStock(listDetailMarchandiseDeplacer);
			
		 
				transferStockMPDAO.ViderSession();
				
				
				
				String req="";
				
				req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"' and vuPar='"+Constantes.AFFICHER_TRANSFERT_ENATTENTE_ADMIN+"' ";
				
			 
				
				listDetailTransferStockMP=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
			
				
				req="";
				
				req=req+" where  d.transferStockMP.statut ='"+Constantes.ETAT_SORTIE_ENATTENT+"'  and d.transferStockMP.CodeTransfer in (select t.transferStockMP.CodeTransfer from DetailTransferStockMP t where t.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT+"') ";
				
				
				 
				
				listDetailTransferStockMPSortie=detailTransferStockMPDAO.findDetailTransferMPByCodeTransferByRequete(req);
				
				boolean existe=false;
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					
					DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(i);
					existe=false;	
					
					Magasin magasinSource=null;
					for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
					{
						
						DetailTransferStockMP detailTransferStockMPTmp=listDetailTransferStockMPSortie.get(j);
					if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(detailTransferStockMPTmp.getTransferStockMP().getCodeTransfer()))
					{
						 
						
						existe=true;
						magasinSource=detailTransferStockMPTmp.getMagasinSouce();
					}
						
						
						
					}
					
					if(existe==true)
					{
						
						MarchandiseDeplacer marchandiseDeplacer=new MarchandiseDeplacer();
						marchandiseDeplacer.setCodeTransfert(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
						marchandiseDeplacer.setMagasinDestination(detailTransferStockMP.getMagasinDestination());
						marchandiseDeplacer.setMagasinSouce(magasinSource);
						listMarchandiseDeplacer.add(marchandiseDeplacer);
					}
					
					
					
					
					
					
				}
				
				
				
				afficher_tableEtatStock(listMarchandiseDeplacer);
				
			  
	}
	
	
	
}

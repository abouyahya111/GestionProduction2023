package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;
 

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Comparateur;
import util.ComparateurSituationEnvracParRegion;
import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SituationEnVrac;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class SituationStockEmballage extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	private ImageIcon imgExcel;

	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	private Map<String, BigDecimal> mapPrixMP = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	
	private Map<String, MatierePremier> mapNomMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<CategorieMp> listecategorieBox_Carton =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	
	private Map< String, Integer> mapDepot = new HashMap<>();
	private List<SituationEnVrac> listSituationEnVrac =new ArrayList<SituationEnVrac>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<StockMP> listStockMPGroupByChaara=new ArrayList<StockMP>();
	private List<StockMP> listStockMPGroupByMkarkeb=new ArrayList<StockMP>();
	private List<TransferStockMP> listTransfertStockMPSortieEnAttente=new ArrayList<TransferStockMP>();
	private List<TransferStockMP> listTransfertStockMPSortieEnAttenteValider=new ArrayList<TransferStockMP>();
	private List<TransferStockMP> listTransfertStockMP=new ArrayList<TransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMPSortieEnAttente=new ArrayList<DetailTransferStockMP>();
	
	
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private DepotDAO depotDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private Utilisateur utilisateur;
	private MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categoriempdao;
	private FournisseurMPDAO fournisseurMPDAO;
	private SubCategorieMPDAO subcategoriempdao;
	private TransferStockMPDAO transferStockMPDAO;
	
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox combofournisseur = new JComboBox();
	JDateChooser datedebut = new JDateChooser();
	JDateChooser datefin = new JDateChooser();
	
	private List <Object[]> listeObjectChaara=new ArrayList<Object[]>();
	private List <Object[]> listeObjectMkarkeb=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEnVrac=new ArrayList<Object[]>();
	
	JComboBox comboType = new JComboBox();
	JLabel totalenattente = new JLabel("0");
	JLabel totaltantan = new JLabel("0");
	JLabel totallaayoun = new JLabel("0");
	JLabel totalessmara = new JLabel("0");
	JLabel totalagadir = new JLabel("0");
	JLabel global = new JLabel("0");
	JLabel totalReceptionEnAttente = new JLabel("0");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationStockEmballage() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1453, 820);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	transferStockMPDAO = new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	
        	
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
             imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 239, 1287, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 198, 1287, 30);
				  		     	add(titledSeparator);
		
		JButton btnImprimer = new JButton("Imprimer ");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Map parameters = new HashMap();
				
				parameters.put("datedu", datedebut.getDate() );
				parameters.put("dateau", datefin.getDate() );
				
				parameters.put("titre","SITUATION STOCK EMBALLAGE" );
				Collections.sort(listSituationEnVrac, new ComparateurSituationEnvracParRegion());
				JasperUtils.imprimerSitutationStockEnVrac (listSituationEnVrac, parameters);
				
				//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
				
				
			}
		});
		btnImprimer.setBounds(533, 649, 133, 30);
		add(btnImprimer);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 44, 1327, 92);
		add(layeredPane);
		
		JLabel label_2 = new JLabel("Du  :");
		label_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(10, 40, 67, 24);
		layeredPane.add(label_2);
		
		 datedebut = new JDateChooser();
		datedebut.setLocale(Locale.FRANCE);
		datedebut.setDateFormatString("dd/MM/yyyy");
		datedebut.setBounds(48, 38, 163, 26);
		layeredPane.add(datedebut);
		
		JLabel label_8 = new JLabel("Sous-Categorie Mp");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(438, 40, 144, 24);
		layeredPane.add(label_8);
		
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
  		  		
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		 	
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(545, 40, 184, 24);
		layeredPane.add(soucategoriempcombo);
		
		JLabel label_9 = new JLabel("Categorie Mp");
		label_9.setBounds(739, 38, 80, 26);
		layeredPane.add(label_9);
		
		 categoriempcombo = new JComboBox();
		categoriempcombo.setBounds(814, 39, 208, 24);
		layeredPane.add(categoriempcombo);
		
		JLabel label_10 = new JLabel("Au  :");
		label_10.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label_10.setBounds(227, 42, 67, 24);
		layeredPane.add(label_10);
		
		 datefin = new JDateChooser();
		datefin.setLocale(Locale.FRANCE);
		datefin.setDateFormatString("dd/MM/yyyy");
		datefin.setBounds(265, 40, 163, 26);
		layeredPane.add(datefin);
		
		JButton button = new JButton("Consulter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listTransfertStockMPSortieEnAttente.clear();
				listDetailTransfertStockMPSortieEnAttente.clear();
				listDetailTransfertStockMP.clear();
					
				listSituationEnVrac.clear();	
				
				listeObjectEnVrac.clear();
				
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				
				MatierePremier mp=null;
				 Date date=null;
					try {
						date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					
					if(datedebut.getDate()==null && datefin.getDate()==null)
					{
						
						datefin.setDate(new Date());
						
					}else if(datedebut.getDate()!=null && datefin.getDate()==null)
					{
						
						//date=datedebut.getDate();
						datefin.setDate(datedebut.getDate());
					}else if(datedebut.getDate()==null && datefin.getDate()!=null)
					{
						//date=datefin.getDate();
						
					}else if(datedebut.getDate()!=null && datefin.getDate()!=null)
					{
						//date=datedebut.getDate();
						
					}
				
				
listeObjectEnVrac=detailTransferMPDAO.listeSituationEnVrac(date, datefin.getDate(), subCategorieMp, categorieMp, mp);




//////////////////////////////////////////////// Declaration Total Global /////////////////////////////////////////////////

BigDecimal GlobaleEnattentChaara=BigDecimal.ZERO;	
BigDecimal GlobaleReceptionEnattentChaara=BigDecimal.ZERO;	
BigDecimal GlobaleTantanChaara=BigDecimal.ZERO;	
BigDecimal GlobaleLaayounChaara=BigDecimal.ZERO;	
BigDecimal GlobaleEssmaraChaara=BigDecimal.ZERO;	
BigDecimal GlobaleAgadirChaara=BigDecimal.ZERO;	
BigDecimal TotalGlobale=BigDecimal.ZERO;



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

for(int k=0; k<listecategorieBox_Carton.size();k++)
{
	
CategorieMp categoriempBox_carton=listecategorieBox_Carton.get(k);
	
	BigDecimal totalEnattentChaara=BigDecimal.ZERO;		
	BigDecimal totalReceptionEnattentChaara=BigDecimal.ZERO;	
	BigDecimal totalEnattentMkarakb=BigDecimal.ZERO;
	BigDecimal totalReceptionEnattentMkarakb=BigDecimal.ZERO;	
	BigDecimal totalTantanChaara=BigDecimal.ZERO;				
	BigDecimal totalTantanMkarkab=BigDecimal.ZERO;	
	BigDecimal totalLaayounChaara=BigDecimal.ZERO;	
	BigDecimal totalLaayounMkarkab=BigDecimal.ZERO;	
	BigDecimal totalEssmaraChaara=BigDecimal.ZERO;	
	BigDecimal totalEssmaraMkarkab=BigDecimal.ZERO;	
	BigDecimal totalAgadirChaara=BigDecimal.ZERO;	
	BigDecimal totalAgadirMkarkab=BigDecimal.ZERO;	
	BigDecimal totalChaara=BigDecimal.ZERO;
	BigDecimal totalMkarkab=BigDecimal.ZERO;
	BigDecimal QuantiteInitialTantan=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionTantan=BigDecimal.ZERO;
	BigDecimal TransferEntrerTantan=BigDecimal.ZERO;
	BigDecimal TransferSortieTantan=BigDecimal.ZERO;
	BigDecimal QuantiteChargerTantan=BigDecimal.ZERO;
	BigDecimal QuantiteChargerSuppTantan=BigDecimal.ZERO;
	BigDecimal QuantiteRetourTantan=BigDecimal.ZERO;
	BigDecimal QuantiteAutreSortieTantan=BigDecimal.ZERO;
	BigDecimal QuantiteResterMachineTantan=BigDecimal.ZERO;
	BigDecimal QuantiteFabriquerTantan=BigDecimal.ZERO;
	BigDecimal QuantiteExistanteTantan=BigDecimal.ZERO;
	
	BigDecimal QuantiteInitialLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionLaayoun=BigDecimal.ZERO;
	BigDecimal TransferEntrerLaayoun=BigDecimal.ZERO;
	BigDecimal TransferSortieLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteChargerLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteChargerSuppLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteRetourLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteAutreSortieLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteResterMachineLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteFabriquerLaayoun=BigDecimal.ZERO;
	BigDecimal QuantiteExistanteLaayoun=BigDecimal.ZERO;
	
	BigDecimal QuantiteInitialEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionEssmara=BigDecimal.ZERO;
	BigDecimal TransferEntrerEssmara=BigDecimal.ZERO;
	BigDecimal TransferSortieEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteChargerEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteChargerSuppEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteRetourEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteAutreSortieEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteResterMachineEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteFabriquerEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteExistanteEssmara=BigDecimal.ZERO;
	
	BigDecimal QuantiteInitialAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionAgadir=BigDecimal.ZERO;
	BigDecimal TransferEntrerAgadir=BigDecimal.ZERO;
	BigDecimal TransferSortieAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteChargerAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteChargerSuppAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteRetourAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteAutreSortieAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteResterMachineAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteFabriquerAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteExistanteAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteEnAttentAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteEnAttentTantan=BigDecimal.ZERO;
	BigDecimal QuantiteEnAttentLaayoune=BigDecimal.ZERO;
	BigDecimal QuantiteEnAttentEssmara=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionEnAttentAgadir=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionEnAttentTantan=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionEnAttentLaayoune=BigDecimal.ZERO;
	BigDecimal QuantiteReceptionEnAttentEssmara=BigDecimal.ZERO;
	
	boolean find=false;

for(int i=0;i<listeObjectEnVrac.size();i++)
{
	
	
	 QuantiteInitialTantan=BigDecimal.ZERO;
	 QuantiteReceptionTantan=BigDecimal.ZERO;
	 TransferEntrerTantan=BigDecimal.ZERO;
	 TransferSortieTantan=BigDecimal.ZERO;
	 QuantiteChargerTantan=BigDecimal.ZERO;
	 QuantiteChargerSuppTantan=BigDecimal.ZERO;
	 QuantiteRetourTantan=BigDecimal.ZERO;
	 QuantiteAutreSortieTantan=BigDecimal.ZERO;
	 QuantiteResterMachineTantan=BigDecimal.ZERO;
	 QuantiteFabriquerTantan=BigDecimal.ZERO;
	 QuantiteExistanteTantan=BigDecimal.ZERO;
	 
	 
	 QuantiteInitialLaayoun=BigDecimal.ZERO;
	 QuantiteReceptionLaayoun=BigDecimal.ZERO;
	 TransferEntrerLaayoun=BigDecimal.ZERO;
	 TransferSortieLaayoun=BigDecimal.ZERO;
	 QuantiteChargerLaayoun=BigDecimal.ZERO;
	 QuantiteChargerSuppLaayoun=BigDecimal.ZERO;
	 QuantiteRetourLaayoun=BigDecimal.ZERO;
	 QuantiteAutreSortieLaayoun=BigDecimal.ZERO;
	 QuantiteResterMachineLaayoun=BigDecimal.ZERO;
	 QuantiteFabriquerLaayoun=BigDecimal.ZERO;
	 QuantiteExistanteLaayoun=BigDecimal.ZERO;
	 
	 QuantiteInitialEssmara=BigDecimal.ZERO;
	 QuantiteReceptionEssmara=BigDecimal.ZERO;
	 TransferEntrerEssmara=BigDecimal.ZERO;
	 TransferSortieEssmara=BigDecimal.ZERO;
	 QuantiteChargerEssmara=BigDecimal.ZERO;
	 QuantiteChargerSuppEssmara=BigDecimal.ZERO;
	 QuantiteRetourEssmara=BigDecimal.ZERO;
	 QuantiteAutreSortieEssmara=BigDecimal.ZERO;
	 QuantiteResterMachineEssmara=BigDecimal.ZERO;
	 QuantiteFabriquerEssmara=BigDecimal.ZERO;
	 QuantiteExistanteEssmara=BigDecimal.ZERO;

	 QuantiteInitialAgadir=BigDecimal.ZERO;
	 QuantiteReceptionAgadir=BigDecimal.ZERO;
	 TransferEntrerAgadir=BigDecimal.ZERO;
	 TransferSortieAgadir=BigDecimal.ZERO;
	 QuantiteChargerAgadir=BigDecimal.ZERO;
	 QuantiteChargerSuppAgadir=BigDecimal.ZERO;
	 QuantiteRetourAgadir=BigDecimal.ZERO;
	 QuantiteAutreSortieAgadir=BigDecimal.ZERO;
	 QuantiteResterMachineAgadir=BigDecimal.ZERO;
	 QuantiteFabriquerAgadir=BigDecimal.ZERO;
	 QuantiteExistanteAgadir=BigDecimal.ZERO;
	 
	     QuantiteEnAttentAgadir=BigDecimal.ZERO;
		 QuantiteEnAttentTantan=BigDecimal.ZERO;
		 QuantiteEnAttentLaayoune=BigDecimal.ZERO;
		 QuantiteEnAttentEssmara=BigDecimal.ZERO;
	
		 QuantiteReceptionEnAttentAgadir=BigDecimal.ZERO;
		 QuantiteReceptionEnAttentTantan=BigDecimal.ZERO;
		 QuantiteReceptionEnAttentLaayoune=BigDecimal.ZERO;
		 QuantiteReceptionEnAttentEssmara=BigDecimal.ZERO;
	
	
	
	Object[] object=listeObjectEnVrac.get(i);
	
	
	MatierePremier matierePremier=(MatierePremier)object[0];
	if(matierePremier!=null)
	{
		
		if(!comboType.getSelectedItem().equals(""))
		{
			
			if(matierePremier.getType().equals(comboType.getSelectedItem()))
			{
				

				
				if(matierePremier.getCategorieMp().getCode().equals(categoriempBox_carton.getCode()))
				{
					find=true;
					SituationEnVrac situationStockEnVrac=new SituationEnVrac();
					
					
		/////////////////////////////////////////////////////////////////////////////////////////////////  TANTAN    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
					
		if((BigDecimal)object[1] != null)
		{
		QuantiteEnAttentTantan=((BigDecimal)object[1]);
		}else
		{
		QuantiteEnAttentTantan=(BigDecimal.ZERO);
		}


		if((BigDecimal)object[2] != null)
		{
		QuantiteInitialTantan=((BigDecimal)object[2]);
		}else
		{
		QuantiteInitialTantan=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[3] != null)
		{
		QuantiteReceptionTantan=((BigDecimal)object[3]);
		}else
		{
		QuantiteReceptionTantan=(BigDecimal.ZERO);
		}
		if(((BigDecimal)object[4]).add((BigDecimal)object[5]) != null)
		{
		TransferEntrerTantan=(((BigDecimal)object[4]).add((BigDecimal)object[5]));
		}else
		{
		TransferEntrerTantan=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[8]).add((BigDecimal)object[9])!=null)
		{
		TransferSortieTantan=(((BigDecimal)object[8]).add((BigDecimal)object[9]));
		}else
		{
		TransferSortieTantan=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[6]!=null)
		{
		QuantiteChargerTantan=((BigDecimal)object[6]);
		}else
		{
		QuantiteChargerTantan=(BigDecimal.ZERO); 
		}

		if((BigDecimal)object[7]!=null)
		{
		QuantiteChargerSuppTantan=((BigDecimal)object[7]); 
		}else
		{
		QuantiteChargerSuppTantan=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[10]!=null)
		{
		QuantiteRetourTantan=((BigDecimal)object[10]);
		}else
		{
		QuantiteRetourTantan=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[11]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]).add((BigDecimal)object[18]).add((BigDecimal)object[19])!=null)
		{
		QuantiteAutreSortieTantan=(((BigDecimal)object[11]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]).add((BigDecimal)object[18]).add((BigDecimal)object[19]));

		}else
		{
		QuantiteAutreSortieTantan=(BigDecimal.ZERO);

		}
		if(((BigDecimal)object[12])!=null)
		{
		QuantiteResterMachineTantan=(((BigDecimal)object[12]));
		}else
		{
		QuantiteResterMachineTantan=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[13]!=null)
		{
		QuantiteFabriquerTantan=((BigDecimal)object[13]);
		}else
		{
		QuantiteFabriquerTantan=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[14]!=null)
		{
		QuantiteExistanteTantan=((BigDecimal)object[14]);
		}else
		{
		QuantiteExistanteTantan=(BigDecimal.ZERO);
		}
		
		if((BigDecimal)object[77]!=null)
		{
		QuantiteReceptionEnAttentTantan=((BigDecimal)object[77]);
		}else
		{
			QuantiteReceptionEnAttentTantan=(BigDecimal.ZERO);
		}

		/////////////////////////////////////////////////////////////////////////////////////  LAAYOUNE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		
		if((BigDecimal)object[20] != null)
		{
		QuantiteEnAttentLaayoune=((BigDecimal)object[20]);
		}else
		{
		QuantiteEnAttentLaayoune=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[21] != null)
		{
		QuantiteInitialLaayoun=((BigDecimal)object[21]);
		}else
		{
		QuantiteInitialLaayoun=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[22] != null)
		{
		QuantiteReceptionLaayoun=((BigDecimal)object[22]);
		}else
		{
		QuantiteReceptionLaayoun=(BigDecimal.ZERO);
		}
		if(((BigDecimal)object[23]).add((BigDecimal)object[24]) != null)
		{
		TransferEntrerLaayoun=(((BigDecimal)object[23]).add((BigDecimal)object[24]));
		}else
		{
		TransferEntrerLaayoun=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[27]).add((BigDecimal)object[28])!=null)
		{
		TransferSortieLaayoun=(((BigDecimal)object[27]).add((BigDecimal)object[28]));
		}else
		{
		TransferSortieLaayoun=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[25]!=null)
		{
		QuantiteChargerLaayoun=((BigDecimal)object[25]);
		}else
		{
		QuantiteChargerLaayoun=(BigDecimal.ZERO); 
		}

		if((BigDecimal)object[26]!=null)
		{
		QuantiteChargerSuppLaayoun=((BigDecimal)object[26]); 
		}else
		{
		QuantiteChargerSuppLaayoun=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[29]!=null)
		{
		QuantiteRetourLaayoun=((BigDecimal)object[29]);
		}else
		{
		QuantiteRetourLaayoun=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[30]).add((BigDecimal)object[34]).add((BigDecimal)object[35]).add((BigDecimal)object[36]).add((BigDecimal)object[37]).add((BigDecimal)object[38])!=null)
		{
		QuantiteAutreSortieLaayoun=(((BigDecimal)object[30]).add((BigDecimal)object[34]).add((BigDecimal)object[35]).add((BigDecimal)object[36]).add((BigDecimal)object[37]).add((BigDecimal)object[38]));

		}else
		{
		QuantiteAutreSortieLaayoun=(BigDecimal.ZERO);

		}
		if(((BigDecimal)object[31])!=null)
		{
		QuantiteResterMachineLaayoun=(((BigDecimal)object[31]));
		}else
		{
		QuantiteResterMachineLaayoun=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[32]!=null)
		{
		QuantiteFabriquerLaayoun=((BigDecimal)object[32]);
		}else
		{
		QuantiteFabriquerLaayoun=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[33]!=null)
		{
		QuantiteExistanteLaayoun=((BigDecimal)object[33]);
		}else
		{
		QuantiteExistanteLaayoun=(BigDecimal.ZERO);
		}
		
		
		if((BigDecimal)object[78]!=null)
		{
		QuantiteReceptionEnAttentLaayoune=((BigDecimal)object[78]);
		}else
		{
			QuantiteReceptionEnAttentLaayoune=(BigDecimal.ZERO);
		}
	

		/////////////////////////////////////////////////////////////////////////////////////  ESSMARA   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		
		if((BigDecimal)object[39] != null)
		{
		QuantiteEnAttentEssmara=((BigDecimal)object[39]);
		}else
		{
		QuantiteEnAttentEssmara=(BigDecimal.ZERO);
		}
		

		if((BigDecimal)object[40] != null)
		{
		QuantiteInitialEssmara=((BigDecimal)object[40]);
		}else
		{
		QuantiteInitialEssmara=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[41] != null)
		{
		QuantiteReceptionEssmara=((BigDecimal)object[41]);
		}else
		{
		QuantiteReceptionEssmara=(BigDecimal.ZERO);
		}
		if(((BigDecimal)object[42]).add((BigDecimal)object[43]) != null)
		{
		TransferEntrerEssmara=(((BigDecimal)object[42]).add((BigDecimal)object[43]));
		}else
		{
		TransferEntrerEssmara=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[46]).add((BigDecimal)object[47])!=null)
		{
		TransferSortieEssmara=(((BigDecimal)object[46]).add((BigDecimal)object[47]));
		}else
		{
		TransferSortieEssmara=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[44]!=null)
		{
		QuantiteChargerEssmara=((BigDecimal)object[44]);
		}else
		{
		QuantiteChargerEssmara=(BigDecimal.ZERO); 
		}

		if((BigDecimal)object[45]!=null)
		{
		QuantiteChargerSuppEssmara=((BigDecimal)object[45]); 
		}else
		{
		QuantiteChargerSuppEssmara=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[48]!=null)
		{
		QuantiteRetourEssmara=((BigDecimal)object[48]);
		}else
		{
		QuantiteRetourEssmara=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[49]).add((BigDecimal)object[53]).add((BigDecimal)object[54]).add((BigDecimal)object[55]).add((BigDecimal)object[56]).add((BigDecimal)object[57])!=null)
		{
		QuantiteAutreSortieEssmara=(((BigDecimal)object[49]).add((BigDecimal)object[53]).add((BigDecimal)object[54]).add((BigDecimal)object[55]).add((BigDecimal)object[56]).add((BigDecimal)object[57]));

		}else
		{
		QuantiteAutreSortieEssmara=(BigDecimal.ZERO);

		}
		if(((BigDecimal)object[50])!=null)
		{
		QuantiteResterMachineEssmara=(((BigDecimal)object[50]));
		}else
		{
		QuantiteResterMachineEssmara=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[51]!=null)
		{
		QuantiteFabriquerEssmara=((BigDecimal)object[51]);
		}else
		{
		QuantiteFabriquerEssmara=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[52]!=null)
		{
		QuantiteExistanteEssmara=((BigDecimal)object[52]);
		}else
		{
		QuantiteExistanteEssmara=(BigDecimal.ZERO);
		}
		
		if((BigDecimal)object[79]!=null)
		{
		QuantiteReceptionEnAttentEssmara=((BigDecimal)object[79]);
		}else
		{
			QuantiteReceptionEnAttentEssmara=(BigDecimal.ZERO);
		}
		

		/////////////////////////////////////////////////////////////////////////////////////  AGADIR   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		
		if((BigDecimal)object[58] != null)
		{
		QuantiteEnAttentAgadir=((BigDecimal)object[58]);
		}else
		{
		QuantiteEnAttentAgadir=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[59] != null)
		{
		QuantiteInitialAgadir=((BigDecimal)object[59]);
		}else
		{
		QuantiteInitialAgadir=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[60] != null)
		{
		QuantiteReceptionAgadir=((BigDecimal)object[60]);
		}else
		{
		QuantiteReceptionAgadir=(BigDecimal.ZERO);
		}
		if(((BigDecimal)object[61]).add((BigDecimal)object[62]) != null)
		{
		TransferEntrerAgadir=(((BigDecimal)object[61]).add((BigDecimal)object[62]));
		}else
		{
		TransferEntrerAgadir=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[65]).add((BigDecimal)object[66])!=null)
		{
		TransferSortieAgadir=(((BigDecimal)object[65]).add((BigDecimal)object[66]));
		}else
		{
		TransferSortieAgadir=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[63]!=null)
		{
		QuantiteChargerAgadir=((BigDecimal)object[63]);
		}else
		{
		QuantiteChargerAgadir=(BigDecimal.ZERO); 
		}

		if((BigDecimal)object[64]!=null)
		{
		QuantiteChargerSuppAgadir=((BigDecimal)object[64]); 
		}else
		{
		QuantiteChargerSuppAgadir=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[67]!=null)
		{
		QuantiteRetourAgadir=((BigDecimal)object[67]);
		}else
		{
		QuantiteRetourAgadir=(BigDecimal.ZERO);
		}

		if(((BigDecimal)object[68]).add((BigDecimal)object[72]).add((BigDecimal)object[73]).add((BigDecimal)object[74]).add((BigDecimal)object[75]).add((BigDecimal)object[76])!=null)
		{
		QuantiteAutreSortieAgadir=(((BigDecimal)object[68]).add((BigDecimal)object[72]).add((BigDecimal)object[73]).add((BigDecimal)object[74]).add((BigDecimal)object[75]).add((BigDecimal)object[76]));

		}else
		{
		QuantiteAutreSortieAgadir=(BigDecimal.ZERO);

		}
		if(((BigDecimal)object[69])!=null)
		{
		QuantiteResterMachineAgadir=(((BigDecimal)object[69]));
		}else
		{
		QuantiteResterMachineAgadir=(BigDecimal.ZERO);
		}

		if((BigDecimal)object[70]!=null)
		{
		QuantiteFabriquerAgadir=((BigDecimal)object[70]);
		}else
		{
		QuantiteFabriquerAgadir=(BigDecimal.ZERO);
		}
		if((BigDecimal)object[71]!=null)
		{
		QuantiteExistanteAgadir=((BigDecimal)object[71]);
		}else
		{
		QuantiteExistanteAgadir=(BigDecimal.ZERO);
		}	
			
		
		if((BigDecimal)object[80]!=null)
		{
		QuantiteReceptionEnAttentAgadir=((BigDecimal)object[80]);
		}else
		{
			QuantiteReceptionEnAttentAgadir=(BigDecimal.ZERO);
		}
					
					
					
		            situationStockEnVrac.setMp(matierePremier);
					situationStockEnVrac.setMatierepremiere(matierePremier.getNom());
					situationStockEnVrac.setCodematierepremiere(matierePremier.getCode());
					
					situationStockEnVrac.setReceptionenattent(QuantiteReceptionEnAttentTantan.add(QuantiteReceptionEnAttentLaayoune).add(QuantiteReceptionEnAttentEssmara).add(QuantiteReceptionEnAttentAgadir));
					totalReceptionEnattentChaara=totalReceptionEnattentChaara.add(QuantiteReceptionEnAttentTantan.add(QuantiteReceptionEnAttentLaayoune).add(QuantiteReceptionEnAttentEssmara).add(QuantiteReceptionEnAttentAgadir));
					
					situationStockEnVrac.setEnattent(QuantiteEnAttentTantan.add(QuantiteEnAttentLaayoune).add(QuantiteEnAttentEssmara).add(QuantiteEnAttentAgadir));
					totalEnattentChaara=totalEnattentChaara.add(QuantiteEnAttentTantan.add(QuantiteEnAttentLaayoune).add(QuantiteEnAttentEssmara).add(QuantiteEnAttentAgadir));
					
					situationStockEnVrac.setTantan((QuantiteInitialTantan.add( QuantiteReceptionTantan.add(QuantiteRetourTantan).add(TransferEntrerTantan.add(QuantiteResterMachineTantan.add(QuantiteFabriquerTantan))))).subtract(QuantiteChargerTantan.add(QuantiteChargerSuppTantan.add(QuantiteAutreSortieTantan).add(TransferSortieTantan).add(QuantiteExistanteTantan))));
					totalTantanChaara=totalTantanChaara.add((QuantiteInitialTantan.add( QuantiteReceptionTantan.add(QuantiteRetourTantan).add(TransferEntrerTantan.add(QuantiteResterMachineTantan.add(QuantiteFabriquerTantan))))).subtract(QuantiteChargerTantan.add(QuantiteChargerSuppTantan.add(QuantiteAutreSortieTantan).add(TransferSortieTantan).add(QuantiteExistanteTantan))));
					
					situationStockEnVrac.setLaayoun((QuantiteInitialLaayoun.add( QuantiteReceptionLaayoun.add(QuantiteRetourLaayoun).add(TransferEntrerLaayoun.add(QuantiteResterMachineLaayoun.add(QuantiteFabriquerLaayoun))))).subtract(QuantiteChargerLaayoun.add(QuantiteChargerSuppLaayoun.add(QuantiteAutreSortieLaayoun).add(TransferSortieLaayoun).add(QuantiteExistanteLaayoun))));
					totalLaayounChaara=totalLaayounChaara.add((QuantiteInitialLaayoun.add( QuantiteReceptionLaayoun.add(QuantiteRetourLaayoun).add(TransferEntrerLaayoun.add(QuantiteResterMachineLaayoun.add(QuantiteFabriquerLaayoun))))).subtract(QuantiteChargerLaayoun.add(QuantiteChargerSuppLaayoun.add(QuantiteAutreSortieLaayoun).add(TransferSortieLaayoun).add(QuantiteExistanteLaayoun))));
					
					situationStockEnVrac.setEssmara((QuantiteInitialEssmara.add( QuantiteReceptionEssmara.add(QuantiteRetourEssmara).add(TransferEntrerEssmara.add(QuantiteResterMachineEssmara.add(QuantiteFabriquerEssmara))))).subtract(QuantiteChargerEssmara.add(QuantiteChargerSuppEssmara.add(QuantiteAutreSortieEssmara).add(TransferSortieEssmara).add(QuantiteExistanteEssmara))));
					totalEssmaraChaara=totalEssmaraChaara.add((QuantiteInitialEssmara.add( QuantiteReceptionEssmara.add(QuantiteRetourEssmara).add(TransferEntrerEssmara.add(QuantiteResterMachineEssmara.add(QuantiteFabriquerEssmara))))).subtract(QuantiteChargerEssmara.add(QuantiteChargerSuppEssmara.add(QuantiteAutreSortieEssmara).add(TransferSortieEssmara).add(QuantiteExistanteEssmara))));
					
					situationStockEnVrac.setAgadir((QuantiteInitialAgadir.add( QuantiteReceptionAgadir.add(QuantiteRetourAgadir).add(TransferEntrerAgadir.add(QuantiteResterMachineAgadir.add(QuantiteFabriquerAgadir))))).subtract(QuantiteChargerAgadir.add(QuantiteChargerSuppAgadir.add(QuantiteAutreSortieAgadir).add(TransferSortieAgadir).add(QuantiteExistanteAgadir))));
					totalAgadirChaara=totalAgadirChaara.add((QuantiteInitialAgadir.add( QuantiteReceptionAgadir.add(QuantiteRetourAgadir).add(TransferEntrerAgadir.add(QuantiteResterMachineAgadir.add(QuantiteFabriquerAgadir))))).subtract(QuantiteChargerAgadir.add(QuantiteChargerSuppAgadir.add(QuantiteAutreSortieAgadir).add(TransferSortieAgadir).add(QuantiteExistanteAgadir))));
					
					situationStockEnVrac.setTotal(situationStockEnVrac.getEnattent().add(situationStockEnVrac.getTantan()).add(situationStockEnVrac.getLaayoun()).add(situationStockEnVrac.getEssmara()).add(situationStockEnVrac.getAgadir()));
					
					totalChaara=totalChaara.add(situationStockEnVrac.getTotal());
					
					listSituationEnVrac.add(situationStockEnVrac);
					
					
					
				}
				
				
				
			
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
		}else
		{
			
			

			
			if(matierePremier.getCategorieMp().getCode().equals(categoriempBox_carton.getCode()))
			{
				find=true;
				SituationEnVrac situationStockEnVrac=new SituationEnVrac();
				
				
	/////////////////////////////////////////////////////////////////////////////////////////////////  TANTAN    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
	if((BigDecimal)object[1] != null)
	{
	QuantiteEnAttentTantan=((BigDecimal)object[1]);
	}else
	{
	QuantiteEnAttentTantan=(BigDecimal.ZERO);
	}


	if((BigDecimal)object[2] != null)
	{
	QuantiteInitialTantan=((BigDecimal)object[2]);
	}else
	{
	QuantiteInitialTantan=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[3] != null)
	{
	QuantiteReceptionTantan=((BigDecimal)object[3]);
	}else
	{
	QuantiteReceptionTantan=(BigDecimal.ZERO);
	}
	if(((BigDecimal)object[4]).add((BigDecimal)object[5]) != null)
	{
	TransferEntrerTantan=(((BigDecimal)object[4]).add((BigDecimal)object[5]));
	}else
	{
	TransferEntrerTantan=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[8]).add((BigDecimal)object[9])!=null)
	{
	TransferSortieTantan=(((BigDecimal)object[8]).add((BigDecimal)object[9]));
	}else
	{
	TransferSortieTantan=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[6]!=null)
	{
	QuantiteChargerTantan=((BigDecimal)object[6]);
	}else
	{
	QuantiteChargerTantan=(BigDecimal.ZERO); 
	}

	if((BigDecimal)object[7]!=null)
	{
	QuantiteChargerSuppTantan=((BigDecimal)object[7]); 
	}else
	{
	QuantiteChargerSuppTantan=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[10]!=null)
	{
	QuantiteRetourTantan=((BigDecimal)object[10]);
	}else
	{
	QuantiteRetourTantan=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[11]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]).add((BigDecimal)object[18]).add((BigDecimal)object[19])!=null)
	{
	QuantiteAutreSortieTantan=(((BigDecimal)object[11]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]).add((BigDecimal)object[18]).add((BigDecimal)object[19]));

	}else
	{
	QuantiteAutreSortieTantan=(BigDecimal.ZERO);

	}
	if(((BigDecimal)object[12])!=null)
	{
	QuantiteResterMachineTantan=(((BigDecimal)object[12]));
	}else
	{
	QuantiteResterMachineTantan=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[13]!=null)
	{
	QuantiteFabriquerTantan=((BigDecimal)object[13]);
	}else
	{
	QuantiteFabriquerTantan=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[14]!=null)
	{
	QuantiteExistanteTantan=((BigDecimal)object[14]);
	}else
	{
	QuantiteExistanteTantan=(BigDecimal.ZERO);
	}
	
	if((BigDecimal)object[77]!=null)
	{
	QuantiteReceptionEnAttentTantan=((BigDecimal)object[77]);
	}else
	{
		QuantiteReceptionEnAttentTantan=(BigDecimal.ZERO);
	}
	

	/////////////////////////////////////////////////////////////////////////////////////  LAAYOUNE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

	if((BigDecimal)object[20] != null)
	{
	QuantiteEnAttentLaayoune=((BigDecimal)object[20]);
	}else
	{
	QuantiteEnAttentLaayoune=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[21] != null)
	{
	QuantiteInitialLaayoun=((BigDecimal)object[21]);
	}else
	{
	QuantiteInitialLaayoun=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[22] != null)
	{
	QuantiteReceptionLaayoun=((BigDecimal)object[22]);
	}else
	{
	QuantiteReceptionLaayoun=(BigDecimal.ZERO);
	}
	if(((BigDecimal)object[23]).add((BigDecimal)object[24]) != null)
	{
	TransferEntrerLaayoun=(((BigDecimal)object[23]).add((BigDecimal)object[24]));
	}else
	{
	TransferEntrerLaayoun=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[27]).add((BigDecimal)object[28])!=null)
	{
	TransferSortieLaayoun=(((BigDecimal)object[27]).add((BigDecimal)object[28]));
	}else
	{
	TransferSortieLaayoun=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[25]!=null)
	{
	QuantiteChargerLaayoun=((BigDecimal)object[25]);
	}else
	{
	QuantiteChargerLaayoun=(BigDecimal.ZERO); 
	}

	if((BigDecimal)object[26]!=null)
	{
	QuantiteChargerSuppLaayoun=((BigDecimal)object[26]); 
	}else
	{
	QuantiteChargerSuppLaayoun=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[29]!=null)
	{
	QuantiteRetourLaayoun=((BigDecimal)object[29]);
	}else
	{
	QuantiteRetourLaayoun=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[30]).add((BigDecimal)object[34]).add((BigDecimal)object[35]).add((BigDecimal)object[36]).add((BigDecimal)object[37]).add((BigDecimal)object[38])!=null)
	{
	QuantiteAutreSortieLaayoun=(((BigDecimal)object[30]).add((BigDecimal)object[34]).add((BigDecimal)object[35]).add((BigDecimal)object[36]).add((BigDecimal)object[37]).add((BigDecimal)object[38]));

	}else
	{
	QuantiteAutreSortieLaayoun=(BigDecimal.ZERO);

	}
	if(((BigDecimal)object[31])!=null)
	{
	QuantiteResterMachineLaayoun=(((BigDecimal)object[31]));
	}else
	{
	QuantiteResterMachineLaayoun=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[32]!=null)
	{
	QuantiteFabriquerLaayoun=((BigDecimal)object[32]);
	}else
	{
	QuantiteFabriquerLaayoun=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[33]!=null)
	{
	QuantiteExistanteLaayoun=((BigDecimal)object[33]);
	}else
	{
	QuantiteExistanteLaayoun=(BigDecimal.ZERO);
	}
	
	if((BigDecimal)object[78]!=null)
	{
	QuantiteReceptionEnAttentLaayoune=((BigDecimal)object[78]);
	}else
	{
		QuantiteReceptionEnAttentLaayoune=(BigDecimal.ZERO);
	}

	/////////////////////////////////////////////////////////////////////////////////////  ESSMARA   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

	if((BigDecimal)object[39] != null)
	{
	QuantiteEnAttentEssmara=((BigDecimal)object[39]);
	}else
	{
	QuantiteEnAttentEssmara=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[40] != null)
	{
	QuantiteInitialEssmara=((BigDecimal)object[40]);
	}else
	{
	QuantiteInitialEssmara=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[41] != null)
	{
	QuantiteReceptionEssmara=((BigDecimal)object[41]);
	}else
	{
	QuantiteReceptionEssmara=(BigDecimal.ZERO);
	}
	if(((BigDecimal)object[42]).add((BigDecimal)object[43]) != null)
	{
	TransferEntrerEssmara=(((BigDecimal)object[42]).add((BigDecimal)object[43]));
	}else
	{
	TransferEntrerEssmara=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[46]).add((BigDecimal)object[47])!=null)
	{
	TransferSortieEssmara=(((BigDecimal)object[46]).add((BigDecimal)object[47]));
	}else
	{
	TransferSortieEssmara=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[44]!=null)
	{
	QuantiteChargerEssmara=((BigDecimal)object[44]);
	}else
	{
	QuantiteChargerEssmara=(BigDecimal.ZERO); 
	}

	if((BigDecimal)object[45]!=null)
	{
	QuantiteChargerSuppEssmara=((BigDecimal)object[45]); 
	}else
	{
	QuantiteChargerSuppEssmara=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[48]!=null)
	{
	QuantiteRetourEssmara=((BigDecimal)object[48]);
	}else
	{
	QuantiteRetourEssmara=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[49]).add((BigDecimal)object[53]).add((BigDecimal)object[54]).add((BigDecimal)object[55]).add((BigDecimal)object[56]).add((BigDecimal)object[57])!=null)
	{
	QuantiteAutreSortieEssmara=(((BigDecimal)object[49]).add((BigDecimal)object[53]).add((BigDecimal)object[54]).add((BigDecimal)object[55]).add((BigDecimal)object[56]).add((BigDecimal)object[57]));

	}else
	{
	QuantiteAutreSortieEssmara=(BigDecimal.ZERO);

	}
	if(((BigDecimal)object[50])!=null)
	{
	QuantiteResterMachineEssmara=(((BigDecimal)object[50]));
	}else
	{
	QuantiteResterMachineEssmara=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[51]!=null)
	{
	QuantiteFabriquerEssmara=((BigDecimal)object[51]);
	}else
	{
	QuantiteFabriquerEssmara=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[52]!=null)
	{
	QuantiteExistanteEssmara=((BigDecimal)object[52]);
	}else
	{
	QuantiteExistanteEssmara=(BigDecimal.ZERO);
	}
	
	if((BigDecimal)object[79]!=null)
	{
	QuantiteReceptionEnAttentEssmara=((BigDecimal)object[79]);
	}else
	{
		QuantiteReceptionEnAttentEssmara=(BigDecimal.ZERO);
	}

	/////////////////////////////////////////////////////////////////////////////////////  AGADIR   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

	if((BigDecimal)object[58] != null)
	{
	QuantiteEnAttentAgadir=((BigDecimal)object[58]);
	}else
	{
	QuantiteEnAttentAgadir=(BigDecimal.ZERO);
	}
	


	if((BigDecimal)object[59] != null)
	{
	QuantiteInitialAgadir=((BigDecimal)object[59]);
	}else
	{
	QuantiteInitialAgadir=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[60] != null)
	{
	QuantiteReceptionAgadir=((BigDecimal)object[60]);
	}else
	{
	QuantiteReceptionAgadir=(BigDecimal.ZERO);
	}
	if(((BigDecimal)object[61]).add((BigDecimal)object[62]) != null)
	{
	TransferEntrerAgadir=(((BigDecimal)object[61]).add((BigDecimal)object[62]));
	}else
	{
	TransferEntrerAgadir=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[65]).add((BigDecimal)object[66])!=null)
	{
	TransferSortieAgadir=(((BigDecimal)object[65]).add((BigDecimal)object[66]));
	}else
	{
	TransferSortieAgadir=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[63]!=null)
	{
	QuantiteChargerAgadir=((BigDecimal)object[63]);
	}else
	{
	QuantiteChargerAgadir=(BigDecimal.ZERO); 
	}

	if((BigDecimal)object[64]!=null)
	{
	QuantiteChargerSuppAgadir=((BigDecimal)object[64]); 
	}else
	{
	QuantiteChargerSuppAgadir=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[67]!=null)
	{
	QuantiteRetourAgadir=((BigDecimal)object[67]);
	}else
	{
	QuantiteRetourAgadir=(BigDecimal.ZERO);
	}

	if(((BigDecimal)object[68]).add((BigDecimal)object[72]).add((BigDecimal)object[73]).add((BigDecimal)object[74]).add((BigDecimal)object[75]).add((BigDecimal)object[76])!=null)
	{
	QuantiteAutreSortieAgadir=(((BigDecimal)object[68]).add((BigDecimal)object[72]).add((BigDecimal)object[73]).add((BigDecimal)object[74]).add((BigDecimal)object[75]).add((BigDecimal)object[76]));

	}else
	{
	QuantiteAutreSortieAgadir=(BigDecimal.ZERO);

	}
	if(((BigDecimal)object[69])!=null)
	{
	QuantiteResterMachineAgadir=(((BigDecimal)object[69]));
	}else
	{
	QuantiteResterMachineAgadir=(BigDecimal.ZERO);
	}

	if((BigDecimal)object[70]!=null)
	{
	QuantiteFabriquerAgadir=((BigDecimal)object[70]);
	}else
	{
	QuantiteFabriquerAgadir=(BigDecimal.ZERO);
	}
	if((BigDecimal)object[71]!=null)
	{
	QuantiteExistanteAgadir=((BigDecimal)object[71]);
	}else
	{
	QuantiteExistanteAgadir=(BigDecimal.ZERO);
	}	
	if((BigDecimal)object[80]!=null)
	{
	QuantiteReceptionEnAttentAgadir=((BigDecimal)object[80]);
	}else
	{
		QuantiteReceptionEnAttentAgadir=(BigDecimal.ZERO);
	}		
				
		
	if(matierePremier.getCode().equals("MP_13"))
	{
		System.out.println("");
	}
	
	
	
				
	                  situationStockEnVrac.setMp(matierePremier);
				situationStockEnVrac.setMatierepremiere(matierePremier.getNom());
				situationStockEnVrac.setCodematierepremiere(matierePremier.getCode());
				
				
				situationStockEnVrac.setReceptionenattent (QuantiteReceptionEnAttentTantan.add(QuantiteReceptionEnAttentLaayoune).add(QuantiteReceptionEnAttentEssmara).add(QuantiteReceptionEnAttentAgadir));
				totalReceptionEnattentChaara=totalReceptionEnattentChaara.add(QuantiteReceptionEnAttentTantan.add(QuantiteReceptionEnAttentLaayoune).add(QuantiteReceptionEnAttentEssmara).add(QuantiteReceptionEnAttentAgadir));
				
				
				
				
				situationStockEnVrac.setEnattent(QuantiteEnAttentTantan.add(QuantiteEnAttentLaayoune).add(QuantiteEnAttentEssmara).add(QuantiteEnAttentAgadir));
				totalEnattentChaara=totalEnattentChaara.add(QuantiteEnAttentTantan.add(QuantiteEnAttentLaayoune).add(QuantiteEnAttentEssmara).add(QuantiteEnAttentAgadir));
				
				situationStockEnVrac.setTantan((QuantiteInitialTantan.add( QuantiteReceptionTantan.add(QuantiteRetourTantan).add(TransferEntrerTantan.add(QuantiteResterMachineTantan.add(QuantiteFabriquerTantan))))).subtract(QuantiteChargerTantan.add(QuantiteChargerSuppTantan.add(QuantiteAutreSortieTantan).add(TransferSortieTantan).add(QuantiteExistanteTantan))));
				totalTantanChaara=totalTantanChaara.add((QuantiteInitialTantan.add( QuantiteReceptionTantan.add(QuantiteRetourTantan).add(TransferEntrerTantan.add(QuantiteResterMachineTantan.add(QuantiteFabriquerTantan))))).subtract(QuantiteChargerTantan.add(QuantiteChargerSuppTantan.add(QuantiteAutreSortieTantan).add(TransferSortieTantan).add(QuantiteExistanteTantan))));
				
				situationStockEnVrac.setLaayoun((QuantiteInitialLaayoun.add( QuantiteReceptionLaayoun.add(QuantiteRetourLaayoun).add(TransferEntrerLaayoun.add(QuantiteResterMachineLaayoun.add(QuantiteFabriquerLaayoun))))).subtract(QuantiteChargerLaayoun.add(QuantiteChargerSuppLaayoun.add(QuantiteAutreSortieLaayoun).add(TransferSortieLaayoun).add(QuantiteExistanteLaayoun))));
				totalLaayounChaara=totalLaayounChaara.add((QuantiteInitialLaayoun.add( QuantiteReceptionLaayoun.add(QuantiteRetourLaayoun).add(TransferEntrerLaayoun.add(QuantiteResterMachineLaayoun.add(QuantiteFabriquerLaayoun))))).subtract(QuantiteChargerLaayoun.add(QuantiteChargerSuppLaayoun.add(QuantiteAutreSortieLaayoun).add(TransferSortieLaayoun).add(QuantiteExistanteLaayoun))));
				
				situationStockEnVrac.setEssmara((QuantiteInitialEssmara.add( QuantiteReceptionEssmara.add(QuantiteRetourEssmara).add(TransferEntrerEssmara.add(QuantiteResterMachineEssmara.add(QuantiteFabriquerEssmara))))).subtract(QuantiteChargerEssmara.add(QuantiteChargerSuppEssmara.add(QuantiteAutreSortieEssmara).add(TransferSortieEssmara).add(QuantiteExistanteEssmara))));
				totalEssmaraChaara=totalEssmaraChaara.add((QuantiteInitialEssmara.add( QuantiteReceptionEssmara.add(QuantiteRetourEssmara).add(TransferEntrerEssmara.add(QuantiteResterMachineEssmara.add(QuantiteFabriquerEssmara))))).subtract(QuantiteChargerEssmara.add(QuantiteChargerSuppEssmara.add(QuantiteAutreSortieEssmara).add(TransferSortieEssmara).add(QuantiteExistanteEssmara))));
				
				situationStockEnVrac.setAgadir((QuantiteInitialAgadir.add( QuantiteReceptionAgadir.add(QuantiteRetourAgadir).add(TransferEntrerAgadir.add(QuantiteResterMachineAgadir.add(QuantiteFabriquerAgadir))))).subtract(QuantiteChargerAgadir.add(QuantiteChargerSuppAgadir.add(QuantiteAutreSortieAgadir).add(TransferSortieAgadir).add(QuantiteExistanteAgadir))));
				totalAgadirChaara=totalAgadirChaara.add((QuantiteInitialAgadir.add( QuantiteReceptionAgadir.add(QuantiteRetourAgadir).add(TransferEntrerAgadir.add(QuantiteResterMachineAgadir.add(QuantiteFabriquerAgadir))))).subtract(QuantiteChargerAgadir.add(QuantiteChargerSuppAgadir.add(QuantiteAutreSortieAgadir).add(TransferSortieAgadir).add(QuantiteExistanteAgadir))));
				
				situationStockEnVrac.setTotal(situationStockEnVrac.getEnattent().add(situationStockEnVrac.getTantan()).add(situationStockEnVrac.getLaayoun()).add(situationStockEnVrac.getEssmara()).add(situationStockEnVrac.getAgadir()).add(situationStockEnVrac.getReceptionenattent()));
				
				totalChaara=totalChaara.add(situationStockEnVrac.getTotal());
				
				listSituationEnVrac.add(situationStockEnVrac);
				
				
				
			}
			
			
			
		
			
			
		}
		
		
		
		
		
	}
	
	
}



if(find==true)
{
	
	GlobaleReceptionEnattentChaara=GlobaleReceptionEnattentChaara.add(totalReceptionEnattentChaara);
	GlobaleEnattentChaara=GlobaleEnattentChaara.add(totalEnattentChaara);
	GlobaleTantanChaara=GlobaleTantanChaara.add(totalTantanChaara);
	GlobaleLaayounChaara=GlobaleLaayounChaara.add(totalLaayounChaara);
	GlobaleEssmaraChaara=GlobaleEssmaraChaara.add(totalEssmaraChaara);
	GlobaleAgadirChaara=GlobaleAgadirChaara.add(totalAgadirChaara);
	TotalGlobale=TotalGlobale.add(totalChaara);
	
	
	

}
	
	
	
	
	
	
	
}









/////////////////////////////////////////////////////////////// Global ////////////////////////////////////////////////////////////


//	SituationEnVrac situationStockEnVracGlobal=new SituationEnVrac();

//	situationStockEnVracGlobal.setMatierepremiere("GLOBAL");

	//situationStockEnVracGlobal.setEnattent(GlobaleEnattentChaara);
//	situationStockEnVracGlobal.setTantan(GlobaleTantanChaara);
	//situationStockEnVracGlobal.setLaayoun(GlobaleLaayounChaara);
	//situationStockEnVracGlobal.setEssmara(GlobaleEssmaraChaara);
	//situationStockEnVracGlobal.setAgadir(GlobaleAgadirChaara);
	//situationStockEnVracGlobal.setTotal(TotalGlobale);
	//listSituationEnVrac.add(situationStockEnVracGlobal);



///////////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////

listTransfertStockMPSortieEnAttente.clear();
listTransfertStockMPSortieEnAttenteValider.clear();
listTransfertStockMPSortieEnAttenteValider=transferStockMPDAO.findTransferByStatutSaufTransferAllRegion(Constantes.ETAT_SORTIE_ENATTENT_VALIDER, date, datefin.getDate());


for(int d=0;d<listTransfertStockMPSortieEnAttenteValider.size();d++)
{

TransferStockMP transferStockMP=	listTransfertStockMPSortieEnAttenteValider.get(d);
	
	listTransfertStockMP=transferStockMPDAO.listTransferByCode(transferStockMP.getCodeTransfer(), date, datefin.getDate());
	
	if(listTransfertStockMP.size()==2)
	{
		TransferStockMP transferStockMPSortie= transferStockMPDAO.findTransferByCodeStatut(transferStockMP.getCodeTransfer(), Constantes.ETAT_TRANSFER_STOCK_SORTIE);
		if(transferStockMPSortie!=null)
		{
			listTransfertStockMPSortieEnAttente.add(transferStockMPSortie);
		}
		
		
		
	}
	
	
}




for(int t=0;t<listTransfertStockMPSortieEnAttente.size();t++)
{
	
	TransferStockMP transferStockMP=	listTransfertStockMPSortieEnAttente.get(t);
	listDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
	
	for(int j=0;j<listDetailTransfertStockMP.size();j++)
	{
		
	DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(j);
	
	if(detailTransferStockMP.getMagasinSouce()!=null)
	{
		
		if((detailTransferStockMP.getMagasinSouce().getId()==1  &&  detailTransferStockMP.getMagasinDestination().getId()==18)|| (detailTransferStockMP.getMagasinSouce().getId()==12  &&  detailTransferStockMP.getMagasinDestination().getId()==18 ) || (detailTransferStockMP.getMagasinSouce().getId()==16  &&  detailTransferStockMP.getMagasinDestination().getId()==18) || (detailTransferStockMP.getMagasinSouce().getId()==20  && detailTransferStockMP.getMagasinDestination().getId()==18) ) 
		
		{
			
			if(subCategorieMp!=null && categorieMp!=null && !comboType.getSelectedItem().equals(""))
			{
				
				if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && detailTransferStockMP.getMatierePremier().getCategorieMp().getId()==categorieMp.getId())
				{
					if(detailTransferStockMP.getMatierePremier().getType()!=null)
					{
						if(detailTransferStockMP.getMatierePremier().getType().equals(comboType.getSelectedItem()))
						{
							
						listDetailTransfertStockMPSortieEnAttente.add(detailTransferStockMP)	;
							
						}
						
						
					}
					
					
				}
				
				
				
				
			}else if(subCategorieMp!=null && categorieMp!=null && comboType.getSelectedItem().equals(""))
			{
				
				
				if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && detailTransferStockMP.getMatierePremier().getCategorieMp().getId()==categorieMp.getId())
				{
					
					
							
						listDetailTransfertStockMPSortieEnAttente.add(detailTransferStockMP)	;
							
						
						
						
					
					
					
				}
				
				
				
			}else if(subCategorieMp!=null && categorieMp==null && comboType.getSelectedItem().equals(""))
			{
				
				if(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
				{
					
					
							
						listDetailTransfertStockMPSortieEnAttente.add(detailTransferStockMP)	;
							
						
						
						
					
					
					
				}
				
				
			}
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
	}
	
		
	}
	
	
	
	
	
	
	
}






boolean trouver=false;
for(int y=0;y<listDetailTransfertStockMPSortieEnAttente.size();y++)
{

DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMPSortieEnAttente.get(y);


System.out.println(detailTransferStockMP.getMatierePremier().getCode() +" : "+ detailTransferStockMP.getQuantite());



trouver=false;
for(int r=0;r<listSituationEnVrac.size();r++)
{
	
SituationEnVrac situationEnVrac=listSituationEnVrac.get(r);	
	
	if(situationEnVrac.getCodematierepremiere().equals(detailTransferStockMP.getMatierePremier().getCode()))
	{
		
		trouver=true;
		
		
		if(detailTransferStockMP.getMagasinSouce().getId()==1)
		{
			situationEnVrac.setEnattent(situationEnVrac.getEnattent().add(detailTransferStockMP.getQuantite()));
			//situationEnVrac.setTantan(situationEnVrac.getTantan().subtract(detailTransferStockMP.getQuantite()));
			
		}else if(detailTransferStockMP.getMagasinSouce().getId()==12)
		{
			situationEnVrac.setEnattent(situationEnVrac.getEnattent().add(detailTransferStockMP.getQuantite()));
			//situationEnVrac.setLaayoun(situationEnVrac.getLaayoun().subtract(detailTransferStockMP.getQuantite()));
			
		}else if(detailTransferStockMP.getMagasinSouce().getId()==16)
		{
			situationEnVrac.setEnattent(situationEnVrac.getEnattent().add(detailTransferStockMP.getQuantite()));
			//situationEnVrac.setEssmara(situationEnVrac.getEssmara().subtract(detailTransferStockMP.getQuantite()));
			
		}else if(detailTransferStockMP.getMagasinSouce().getId()==20)
		{
			situationEnVrac.setEnattent(situationEnVrac.getEnattent().add(detailTransferStockMP.getQuantite()));
			//situationEnVrac.setAgadir (situationEnVrac.getAgadir().subtract(detailTransferStockMP.getQuantite()));
		}
		
		situationEnVrac.setTotal(situationEnVrac.getEnattent().add(situationEnVrac.getTantan()).add(situationEnVrac.getLaayoun()).add(situationEnVrac.getEssmara()).add(situationEnVrac.getAgadir()).add(situationEnVrac.getReceptionenattent()));
		
		GlobaleEnattentChaara=GlobaleEnattentChaara.add(detailTransferStockMP.getQuantite());
		TotalGlobale=TotalGlobale.add(detailTransferStockMP.getQuantite());
		listSituationEnVrac.set(r, situationEnVrac);
		
		
	}
	
	
	
	
	
	
	
	
}

if(trouver==false)
{
	
	SituationEnVrac situationStockEnVrac= new SituationEnVrac();		
	
    situationStockEnVrac.setMp(detailTransferStockMP.getMatierePremier());
			situationStockEnVrac.setMatierepremiere(detailTransferStockMP.getMatierePremier().getNom());
			situationStockEnVrac.setCodematierepremiere(detailTransferStockMP.getMatierePremier().getCode());
			
			
			situationStockEnVrac.setReceptionenattent (BigDecimal.ZERO);
			situationStockEnVrac.setEnattent(detailTransferStockMP.getQuantite());
			
			situationStockEnVrac.setTantan(BigDecimal.ZERO);
			
			situationStockEnVrac.setLaayoun(BigDecimal.ZERO);
			
			situationStockEnVrac.setEssmara(BigDecimal.ZERO);
			
			situationStockEnVrac.setAgadir(BigDecimal.ZERO);
			
			situationStockEnVrac.setTotal(situationStockEnVrac.getEnattent().add(situationStockEnVrac.getTantan()).add(situationStockEnVrac.getLaayoun()).add(situationStockEnVrac.getEssmara()).add(situationStockEnVrac.getAgadir()).add(situationStockEnVrac.getReceptionenattent()));
			
			GlobaleEnattentChaara=GlobaleEnattentChaara.add(situationStockEnVrac.getTotal());
			TotalGlobale=TotalGlobale.add(situationStockEnVrac.getTotal());
			
			listSituationEnVrac.add(situationStockEnVrac);
	
	
	
	
}






}


DecimalFormatSymbols symbols = new DecimalFormatSymbols();
symbols.setGroupingSeparator(' ');
DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
dfDecimal.setDecimalFormatSymbols(symbols);
dfDecimal.setGroupingSize(3);
dfDecimal.setGroupingUsed(true);



///////////////////////////////////////////////////////////////////////////////////////////////////////////// Afficher Table ////////////////////////////////////////////
totalenattente.setText(dfDecimal.format(GlobaleEnattentChaara.setScale(2, RoundingMode.HALF_UP)) +"");
totaltantan.setText(dfDecimal.format(GlobaleTantanChaara.setScale(2, RoundingMode.HALF_UP))+"");
totallaayoun.setText(dfDecimal.format(GlobaleLaayounChaara.setScale(2, RoundingMode.HALF_UP))+"");
totalessmara.setText(dfDecimal.format(GlobaleEssmaraChaara.setScale(2, RoundingMode.HALF_UP))+"");
totalagadir.setText(dfDecimal.format(GlobaleAgadirChaara.setScale(2, RoundingMode.HALF_UP))+"");
totalReceptionEnAttente.setText(dfDecimal.format(GlobaleReceptionEnattentChaara.setScale(2, RoundingMode.HALF_UP))+"");
global.setText(dfDecimal.format(TotalGlobale.setScale(2, RoundingMode.HALF_UP))+"");

Collections.sort(listSituationEnVrac, new ComparateurSituationEnvracParRegion());
afficher_tableMP(listSituationEnVrac);

					
				
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(438, 147, 107, 24);
		add(button);
		
		JButton button_1 = new JButton("Initialiser");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboType.setSelectedItem("");
				
				datedebut.setDate(null);
				datefin.setDate(null);
				soucategoriempcombo.setSelectedItem("");
				categoriempcombo.setSelectedItem("");
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(572, 147, 107, 24);
		add(button_1);
		

	  		  
		  
	  		  
	 
	    soucategoriempcombo.removeAllItems();
		soucategoriempcombo.addItem("");
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(1034, 40, 80, 26);
		layeredPane.add(lblType);
		
		 comboType = new JComboBox();
		comboType.setBounds(1082, 41, 235, 24);
		layeredPane.add(comboType);
		
		 int i=0;
 		  while(i<listsubcategoriemp.size())
 		  {
 			  SubCategorieMp subCategorieMp=listsubcategoriemp.get(i);
 			 
 				  
 				  subcatMap.put(subCategorieMp.getNom(), subCategorieMp);
 	 			  soucategoriempcombo.addItem(subCategorieMp.getNom());
 	 			listecategorieBox_Carton.addAll(categoriempdao.findBySubcategorie(subCategorieMp.getId())) ;
 	 		
 	 			    
 			 
 			 i++;
 			
 		  }
 		  
 		
 		comboType.addItem("");  
 		comboType.addItem(Constantes.MP_CLIENT);  
 		comboType.addItem(Constantes.MP_INTERNE);
 		
 		JLabel lblTotalEnAttente = new JLabel("Total En Attente :");
 		lblTotalEnAttente.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblTotalEnAttente.setBounds(10, 696, 113, 24);
 		add(lblTotalEnAttente);
 		
 		 totalenattente = new JLabel("0");
 		totalenattente.setForeground(Color.RED);
 		totalenattente.setFont(new Font("Tahoma", Font.BOLD, 12));
 		totalenattente.setBounds(133, 696, 177, 24);
 		add(totalenattente);
 		
 		JLabel lblTotalTantan = new JLabel("Total TANTAN :");
 		lblTotalTantan.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblTotalTantan.setBounds(336, 696, 100, 24);
 		add(lblTotalTantan);
 		
 		 totaltantan = new JLabel("0");
 		totaltantan.setForeground(Color.RED);
 		totaltantan.setFont(new Font("Tahoma", Font.BOLD, 12));
 		totaltantan.setBounds(446, 696, 192, 24);
 		add(totaltantan);
 		
 		JLabel lblTotalLaayoun = new JLabel("Total LAAYOUN :");
 		lblTotalLaayoun.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblTotalLaayoun.setBounds(650, 696, 113, 24);
 		add(lblTotalLaayoun);
 		
 		 totallaayoun = new JLabel("0");
 		totallaayoun.setForeground(Color.RED);
 		totallaayoun.setFont(new Font("Tahoma", Font.BOLD, 12));
 		totallaayoun.setBounds(773, 696, 192, 24);
 		add(totallaayoun);
 		
 		JLabel lblTotalEssmara = new JLabel("Total ESSMARA :");
 		lblTotalEssmara.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblTotalEssmara.setBounds(315, 766, 113, 24);
 		add(lblTotalEssmara);
 		
 		 totalessmara = new JLabel("0");
 		totalessmara.setForeground(Color.RED);
 		totalessmara.setFont(new Font("Tahoma", Font.BOLD, 12));
 		totalessmara.setBounds(438, 766, 200, 24);
 		add(totalessmara);
 		
 		JLabel lblTotalAgadir = new JLabel("Total AGADIR :");
 		lblTotalAgadir.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblTotalAgadir.setBounds(10, 766, 113, 24);
 		add(lblTotalAgadir);
 		
 		 totalagadir = new JLabel("0");
 		totalagadir.setForeground(Color.RED);
 		totalagadir.setFont(new Font("Tahoma", Font.BOLD, 12));
 		totalagadir.setBounds(133, 766, 177, 24);
 		add(totalagadir);
 		
 		JLabel lblGlobal = new JLabel("GLOBAL :");
 		lblGlobal.setFont(new Font("Tahoma", Font.BOLD, 12));
 		lblGlobal.setBounds(648, 766, 113, 24);
 		add(lblGlobal);
 		
 		 global = new JLabel("0");
 		global.setForeground(Color.RED);
 		global.setFont(new Font("Tahoma", Font.BOLD, 12));
 		global.setBounds(771, 766, 214, 24);
 		add(global);
 		
 		JButton btnExporterExcel = new JButton("Exporter Excel");
 		btnExporterExcel.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {

  				
  				
	    			if(table.getRowCount()!=0)
	    			{
	    				
	    				String titre="Situation Stock MP Par Region ";
			    		String titrefeuilleexcel="Situation Stock MP Par Region ";
			    		ExporterTableVersExcel.tabletoexcelEtatStockMPParRegion (table, titre,titrefeuilleexcel);
	    				
	    				
	    			}else
	    			{
	    				
	    				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
		    			return;
	    				
	    				
	    			}
	    		
	    	
	    	
  				
  				
  				
  				
  				
  				
  			
 				
 				
 				
 				
 				
 			}
 		});
 		btnExporterExcel.setBounds(724, 646, 156, 34);
 		btnExporterExcel.setIcon(imgExcel);
 		add(btnExporterExcel);
				  		 
	}
	
void afficher_tableMP(List<SituationEnVrac> listSituationEnVrac)
	{
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
	intialiserTableau();
		  int i=0;
			while(i<listSituationEnVrac.size())
			{	
				
				SituationEnVrac situationEnVrac=listSituationEnVrac.get(i);
				
			
				Object []ligne={situationEnVrac.getCodematierepremiere(),situationEnVrac.getMatierepremiere(),dfDecimal.format(situationEnVrac.getEnattent().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(situationEnVrac.getTantan().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(situationEnVrac.getLaayoun().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(situationEnVrac.getEssmara().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(situationEnVrac.getAgadir().setScale(2, RoundingMode.HALF_UP)),dfDecimal.format(situationEnVrac.getTotal().setScale(2, RoundingMode.HALF_UP)) };

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matière Première",  "TRF EN ATTENTE", "TANTAN", "LAAYOUN", "ESSMARA", "AGADIR", "TOTAL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP);
		   table.getColumnModel().getColumn(0).setPreferredWidth(60);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
		   table.getColumnModel().getColumn(3).setPreferredWidth(80);
		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
		
		 
		   table.getTableHeader().setReorderingAllowed(false);
}


void intialiserModifier(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matière Première",  "EN ATTENT", "TANTAN", "LAAYOUN", "ESSMARA", "AGADIR", "TOTAL"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(60);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
		   
		  
		   table.getTableHeader().setReorderingAllowed(false);
}
}

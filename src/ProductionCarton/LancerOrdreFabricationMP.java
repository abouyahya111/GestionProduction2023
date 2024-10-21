package ProductionCarton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.*;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;


public class LancerOrdreFabricationMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleOF;

	private JXTable  table = new JXTable();
	private ImageIcon imgImprimer;
	private ImageIcon imgValider;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnLancerOF;
	private JButton btnRechercher;
	
	private JTextField quantite;
	private JTextField codeArticle;
	
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMPCharge= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp= new ArrayList<DetailTransferStockMP>();
	private ProductionMPDAO productionMPDAO;
	private StockMPDAO stockMPDAO;
	
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO ;
	private JComboBox<String> categorie;
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
	private List<ProductionMP> listProductionMP=new ArrayList<ProductionMP>();
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private static ProductionMP productionMP = new ProductionMP();
	private JTextField txtdateproduction;
	private JTable TableOF =new JTable();
	private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
	private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public LancerOrdreFabricationMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 841);
        try{
        	
        	
        	productionMPDAO=new ProductionMPDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	final String codeDepot=AuthentificationView.utilisateur.getCodeDepot();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
    
		
		
				  		  btnImprimer = new JButton("Fiche Calcule MP");
				  		 
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

					  		  	if(productionMP.getId()>0 && productionMP.getStatut().equals(Constantes.ETAT_OF_LANCER)){
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(productionMP.getDateProduction());
								 List<CoutProdMP> listCoutMP=productionMP.getListCoutProdMP();
								 
								Map parameters = new HashMap();
								parameters.put("numOF", productionMP.getNumOFMP());
							
								parameters.put("magasin", productionMP.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								//JasperUtils.imprimerFicheCalculeMatierePremiere(listCoutMP,parameters,productionMP.getNumOFMP());
								
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					  		  	}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
					  		  	
				  		  	}
				  		  });
				  		  
				  		modeleMP =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Code","Nom Matière Première   ", "Quantité","Quantite Existante","Quantite Charge" , "Charge Supp", "Ajouter Charge Supp"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false,false,false,false, true
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		   table.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent e) {
				  		   		
				  		   		
				  		 
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   table.setModel(modeleMP); 
				  		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
				  		   table.getColumnModel().getColumn(1).setPreferredWidth(300);
				  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
				  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
				  		 table.getColumnModel().getColumn(4).setPreferredWidth(60);
				  		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
				  		 table.getColumnModel().getColumn(6).setPreferredWidth(60);
				  		   btnImprimer.setIcon(imgImprimer);
				  		   btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnImprimer.setBounds(388, 697, 131, 24);
				  		   add(btnImprimer);
				  		    
				  		    btnLancerOF = new JButton("Lancer OF");
				  		    btnLancerOF.setBounds(11, 697, 107, 24);
				  		    add(btnLancerOF);
				  		    btnLancerOF.setIcon(imgAjouter);
				  		    btnLancerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		if(productionMP.getStatut().equals(Constantes.ETAT_OF_CREER)){
				  		     		
				  		     			productionMP.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     			List<CoutProdMP> listCoutProdMP=reglerStockMatierePremiere(productionMP.getListCoutProdMP(),productionMP.getMagasinProd().getId(),productionMP.getMagasinStockage().getId());
				  		     	
				  		     		
				  		     		productionMP.setStatut(Constantes.ETAT_OF_LANCER);
				  		     		
				  		     		
				  		     		
				  		     		// ajouter transfer stock MP type charge 
			  		     			String codeTransfert=Utils.genererCodeTransfer(productionMP.getCodeDepot(),ETAT_TRANSFER_STOCK_CHARGE);
									if(listDetailTransferStockMPCharge.size()!=0)
									{
										transferStock.setCodeTransfer(productionMP.getNumOFMP());
										transferStock.setCreerPar(AuthentificationView.utilisateur);
										transferStock.setDate(new Date());
										transferStock.setDateTransfer(productionMP.getDateProduction());
										transferStock.setDepot(productionMP.getMagasinStockage().getDepot());
										//transferStock.setListDetailTransferMP(listDetailTransferStockMPCharge);
										transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE);
										
										transferStockMPDAO.add(transferStock);
									}
									
									  BigDecimal montantTotal=BigDecimal.ZERO;
		                                 BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
		                                 BigDecimal montantTotalEmballage=BigDecimal.ZERO;

		                                 
									for(int i=0;i<listDetailTransferStockMPCharge.size();i++)
				  		     		{
				  		     			detailTransfertMPDAO.add(listDetailTransferStockMPCharge.get(i));
				  		     			
				  		     			
				  		     			if(listDetailTransferStockMPCharge.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		     			{
				  		     				montantTotalEnvrac=montantTotalEnvrac.add(listDetailTransferStockMPCharge.get(i).getQuantite().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  		     				montantTotalEnvrac=montantTotalEnvrac.add(listDetailTransferStockMPCharge.get(i).getQuantiteExistante().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  		     				
				  		     			}else
				  		     			{
				  		     				montantTotalEmballage=montantTotalEmballage.add(listDetailTransferStockMPCharge.get(i).getQuantite().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  		     				montantTotalEmballage=montantTotalEmballage.add(listDetailTransferStockMPCharge.get(i).getQuantiteExistante().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  		     			}
				  		     			montantTotal=montantTotal.add(listDetailTransferStockMPCharge.get(i).getQuantite().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  		     			montantTotal=montantTotal.add(listDetailTransferStockMPCharge.get(i).getQuantiteExistante().multiply(listDetailTransferStockMPCharge.get(i).getPrixUnitaire()));
				  
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		productionMPDAO.edit(productionMP);
				  		     		
				  		     		
				  		     		
				  		     		listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
				  		     		
				  		     		if(statistiqueFinanciaireMPTmp!=null)
				  		     		{
				  		     			
				  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
				  		     			
				  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
				  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
				  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
				  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
				  		     			statistiqueFinanciaireMP.setCodeTransfer(productionMP.getNumOFMP());
				  		     			statistiqueFinanciaireMP.setDate(new Date());
				  		     			statistiqueFinanciaireMP.setDateOperation(productionMP.getDateProduction());
				  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
				  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
				  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
				  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
				  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
				  		     			statistiqueFinanciaireMP.setCoutProductionCarton(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutProductionCarton()));
				  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
				  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
				  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
				  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
				  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
				  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_LANCER+" "+Constantes.PROD_CARTON);
				  		     		 
				  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
				  		     			 
				  		     			
				  		     		} 
				  		     		
				  		     		
				  		     		
				  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication lancé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot)); 
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		/*try {
										EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
											"OF Lancé avec succès",
											registerMailBody());
									} catch (AddressException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (MessagingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}*/
				  		     		}else{
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication est déjà lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     	}
				  		     });
				  		    btnLancerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnAnnulerOF = new JButton("Initialiser");
				  		     btnAnnulerOF.setBounds(120, 698, 106, 23);
				  		     add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 349, 908, 176);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 324, 908, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 536, 907, 149);
				  		     	add(layeredPane);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date Production :");
				  		  lblDatePrevue.setBounds(10, 48, 102, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  codeArticle = new JTextField();
				  		 util.Utils.copycoller(codeArticle);
				  		  codeArticle.setBounds(119, 10, 136, 26);
				  		  layeredPane.add(codeArticle);
				  		  categorie = new JComboBox();
				  		  categorie.setEnabled(false);
				  		  categorie.setEditable(true);
				  		  categorie.setBackground(Color.WHITE);
				  		  categorie.addItem("");
		codeArticle.setColumns(10);
		
		  JLabel lblCodeArticle = new JLabel("Code Article");
		  lblCodeArticle.setBounds(8, 10, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  categorie.setBounds(367, 13, 144, 26);
		  layeredPane.add(categorie);
		  categorie.addItem(""); 
		  
		  quantite = new JTextField();
		  util.Utils.copycoller(quantite);
		  quantite.setBounds(614, 11, 157, 26);
		  layeredPane.add(quantite);
		  quantite.setColumns(10);
		  
		  JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
		  lblQuantite.setBounds(533, 12, 68, 26);
		  layeredPane.add(lblQuantite);
		  lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		    
		    JLabel lblArticle = new JLabel("Article:");
		    lblArticle.setBounds(283, 11, 102, 26);
		    layeredPane.add(lblArticle);
		    lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    
		    txtdateproduction = new JTextField();
		    util.Utils.copycoller(txtdateproduction);
		    txtdateproduction.setColumns(10);
		    txtdateproduction.setBounds(119, 51, 136, 26);
		    layeredPane.add(txtdateproduction);
		    
		    
		   
		    
		    
		    
		
		JButton btnValiderChargeSupp = new JButton("Valider Charge Supp");
		btnValiderChargeSupp.setIcon(imgValider);
		
		btnValiderChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(productionMP.getStatut().equals(Constantes.ETAT_OF_LANCER)){	
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité à charger ", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Valider cette charge ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						{

				productionMP.setListCoutProdMP(ajouetrChargeSupp(listCoutProdMP,mapChargeSupp,productionMP.getMagasinProd().getId(),productionMP.getMagasinStockage().getId()));
				productionMPDAO.edit(productionMP);
				
				
				// ajouter transfer stock MP type charge  Supp
		     	   String codeTransfert=Utils.genererCodeTransfer(productionMP.getMagasinProd().getDepot().getCode(),ETAT_TRANSFER_STOCK_CHARGE);
		     		if(listDetailTransferStockMPChargeSupp.size()!=0)
		     		{
		     			
		     			transferStock.setCodeTransfer(productionMP.getNumOFMP());
						transferStock.setCreerPar(productionMP.getUtilisateurCreation());
						transferStock.setDate(new Date());
						transferStock.setDateTransfer(productionMP.getDateProduction());
						transferStock.setDepot(productionMP.getMagasinProd().getDepot());
						//transferStock.setListDetailTransferMP(listDetailTransferStockMPChargeSupp);
						transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE_SUPP);
						transferStockMPDAO.add(transferStock);
						
						 BigDecimal montantTotal=BigDecimal.ZERO;
                         BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
                         BigDecimal montantTotalEmballage=BigDecimal.ZERO;
                         BigDecimal QuantiteCharge=BigDecimal.ZERO;
                         
						for(int i=0;i<listDetailTransferStockMPChargeSupp.size();i++)
	  		     		{
	  		     			detailTransfertMPDAO.add(listDetailTransferStockMPChargeSupp.get(i));
	  		     			
	  		     			QuantiteCharge=new BigDecimal(mapChargeSupp.get(listDetailTransferStockMPChargeSupp.get(i).getMatierePremier().getCode()));
	  		     			if(listDetailTransferStockMPChargeSupp.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		     			{
	  		     				if(QuantiteCharge!=null)
	  		     				{
		  		     				montantTotalEnvrac=montantTotalEnvrac.add(QuantiteCharge.multiply(listDetailTransferStockMPChargeSupp.get(i).getPrixUnitaire()));

	  		     				}
 	  		     				
	  		     			}else
	  		     			{
	  		     				if(QuantiteCharge!=null)
	  		     				{
		  		     				montantTotalEmballage=montantTotalEmballage.add(QuantiteCharge.multiply(listDetailTransferStockMPChargeSupp.get(i).getPrixUnitaire()));

	  		     				}
 	  		     			}
	  		     			montantTotal=montantTotal.add(QuantiteCharge.multiply(listDetailTransferStockMPChargeSupp.get(i).getPrixUnitaire()));
 	  
	  		     			
	  		     			
	  		     		}
						
						
						
	  		     		listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
	  		     		
	  		     		if(statistiqueFinanciaireMPTmp!=null)
	  		     		{
	  		     			
	  		     			StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
	  		     			
	  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
	  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(montantTotalEmballage));
	  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac().subtract(montantTotalEnvrac));
	  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
	  		     			statistiqueFinanciaireMP.setCodeTransfer(productionMP.getNumOFMP());
	  		     			statistiqueFinanciaireMP.setDate(new Date());
	  		     			statistiqueFinanciaireMP.setDateOperation(productionMP.getDateProduction());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
	  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
	  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
	  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
	  		     			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());
	  		     			statistiqueFinanciaireMP.setCoutProductionCarton(montantTotal.add(statistiqueFinanciaireMPTmp.getCoutProductionCarton()));
	  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
	  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
	  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
	  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
	  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
	  		     			statistiqueFinanciaireMP.setEtat(ETAT_TRANSFER_STOCK_CHARGE_SUPP+" "+Constantes.PROD_CARTON);
	  		     		 
	  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
	  		     			 
	  		     			
	  		     		} 
						
						
		     		}
				
				
				JOptionPane.showMessageDialog(null, "La charge a été ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				
				afficher_tableMP(listCoutProdMP);
				
						}
				
			/*	try {
					EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
						"Charge Supplémetaire",
						registerMailBody2());
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
				}else {
					JOptionPane.showMessageDialog(null, "Il faut Lancer l'OF avant de valider la charge supplemntaire ", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
			
		  }
		});
		btnValiderChargeSupp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderChargeSupp.setBounds(228, 698, 158, 23);
		add(btnValiderChargeSupp);
		
		JButton btnImprimerSortieChargeSupp = new JButton("Bon Sortie Charge Supp");
		
		btnImprimerSortieChargeSupp.setIcon(imgImprimer);
		btnImprimerSortieChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	  		  	if(productionMP.getStatut().equals(Constantes.ETAT_OF_LANCER)){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(productionMP.getDateProduction());
				 List<CoutProdMP> listCoutMP=productionMP.getListCoutProdMP();
				 
				Map parameters = new HashMap();
				parameters.put("numOF", productionMP.getNumOFMP());
			
				parameters.put("magasin", productionMP.getMagasinProd().getLibelle());
				parameters.put("dateProd", date);
				//JasperUtils.imprimerBonSortieMPChargeSupp(listCoutMP,parameters,productionMP.getNumOFMP());
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
	  		  	
  		  	
				
			}
		});
		btnImprimerSortieChargeSupp.setBounds(521, 698, 167, 23);
		add(btnImprimerSortieChargeSupp);
		
		JButton btnAnnulerOf = new JButton("Annuler OF");
		btnAnnulerOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    		
		    		if(productionMP.getId()<=0){
		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message", JOptionPane.ERROR_MESSAGE);
		    			
		    		}else {
		    			
		    			if(!productionMP.getStatut().equals(ETAT_OF_ANNULER)){
		    				 if(productionMP.getStatut().equals(ETAT_OF_LANCER)){
		    					 
		    					
		    			annulerOF(productionMP.getListCoutProdMP(),productionMP.getMagasinProd().getId(),productionMP.getMagasinStockage().getId());
		    			
		    			productionMP.setStatut(ETAT_OF_ANNULER);
		    			productionMP.setUtilisateurAnnulation(AuthentificationView.utilisateur);
		    			
		    			productionMPDAO.edit(productionMP);
		    		
		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
		    				 }else{
		    					JOptionPane.showMessageDialog(null, "OF n'est pas encore lancé", "Message", JOptionPane.ERROR_MESSAGE); 
		    				 }
		    			
		    			}else{
		    				JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
		    			}
		    		}
		    		
		    		
		    	}
		});
		btnAnnulerOf.setBounds(697, 698, 89, 23);
		add(btnAnnulerOf);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 27, 908, 286);
		add(scrollPane_1);
		
		TableOF = new JTable();
		TableOF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				if(TableOF.getSelectedRow()!=-1)
				{
					
				productionMP=listProductionMP.get(TableOF.getSelectedRow());
if(productionMP!=null)
{
	productionMP=productionMPDAO.findById(productionMP.getId());
		if(productionMP!=null){
		listCoutProdMP=productionMPDAO.listeCoutProdMP(productionMP.getId());
		
		quantite.setText(""+productionMP.getQuantiteReel());
		codeArticle.setText(productionMP.getArticlesMP().getCodeArticle());
		categorie.addItem(productionMP.getArticlesMP().getLiblle());
		categorie.setSelectedItem(productionMP.getArticlesMP().getLiblle());
		
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		String dateproduction=dateFormat.format(productionMP.getDateProduction());
		txtdateproduction.setText(dateproduction);
	
		afficher_tableMP(listCoutProdMP);
		}else{
		  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
	
}
		  			
		  	

		  		
	  		  		
	  		  	
					
					
					
					
					
				}
				
				
			}
		});
		TableOF.setFillsViewportHeight(true);
		TableOF.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date OF", "NumOF", "Etat"
			}
		));
		scrollPane_1.setViewportView(TableOF);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Liste Des OF");
		titledSeparator_1.setBounds(11, 0, 906, 30);
		add(titledSeparator_1);
				  		     
		 // Charger La Liste Des OF en Etat Creation 
  		afficher_tableOF(ChargerOF(Constantes.ETAT_OF_CREER,Constantes.ETAT_OF_LANCER, codeDepot));		  		 
	}
	
	
	void intialiser()
	{
		quantite.setText("");
		codeArticle.setText("");
		categorie.setSelectedItem("");
		
	}
	
void 	intialiserTableau() {
		
		modeleMP =new DefaultTableModel(
  		     	new Object[][] {
  		     	},
  		     	new String[] {
  		     			"Code","Nom Matière Première   ", "Quantité","Quantite Existante","Quantite Charge" , "Charge Supp", "Ajouter Charge Supp"
  		     	}
  		     ) {
  		     	boolean[] columnEditables = new boolean[] {
  		     			false,false,false,false,false,false, true
  		     	};
  		     	public boolean isCellEditable(int row, int column) {
  		     		return columnEditables[column];
  		     	}
  		     };
  		   table.setModel(modeleMP); 
	}
	
	void afficher_tableMP(List<CoutProdMP> listCoutProdMP)
	{
	        BigDecimal chargeSupp;
		  int i=0;
		  intialiserTableau();
			while(i<listCoutProdMP.size())
			{	
				
				CoutProdMP coutProdMP=listCoutProdMP.get(i);
			
				BigDecimal quantiteTotal=coutProdMP.getQuantite();
				BigDecimal quantiteExistante=coutProdMP.getQuantExistante();
				BigDecimal quantiteACharge=coutProdMP.getQuantCharge();
				chargeSupp=coutProdMP.getQuantChargeSupp();
				
				Object []ligne={coutProdMP.getMatierePremier().getCode(),coutProdMP.getMatierePremier().getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))+" "+coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteExistante.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteACharge.setScale(6)),NumberFormat.getNumberInstance(Locale.FRANCE).format(chargeSupp.setScale(6)),BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN)};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
	List<CoutProdMP>  reglerStockMatierePremiere(List<CoutProdMP> listCoutMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteAConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	BigDecimal prixTotal=BigDecimal.ZERO;
	List<CoutProdMP> listCoutMPTmp=new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutProdMP coutMP=listCoutMP.get(i);
	/*	if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CELLOPHANE) ){
			quantiteAConsomme=coutMP.getQuantCharge();
		}else {
			quantiteAConsomme=coutMP.getQuantite()-coutMP.getQuantExistante();
		}*/
		quantiteAConsomme=coutMP.getQuantCharge();
		

		
		
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		detailTransferStockMP.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
		detailTransferStockMP.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
		detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
	
		detailTransferStockMP.setPrixUnitaire(coutMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(coutMP.getProdcutionCM().getDateProduction())));
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) ||  coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
		{
			detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, RoundingMode.CEILING));
			detailTransferStockMP.setQuantiteExistante(coutMP.getQuantExistante().setScale(0, RoundingMode.CEILING));
			
		}else
		{
			detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
			detailTransferStockMP.setQuantiteExistante(coutMP.getQuantExistante());
		}
		
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMPCharge.add(detailTransferStockMP);
		
		
		
		
		/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
		prixUnitaire=prixUnitaire/quantiteProd;
		prixTotal=prixUnitaire*quantiteConsomme;
		
		coutMP.setPrixUnitaire(prixUnitaire);
		coutMP.setPrixTotal(prixTotal);
		listCoutMPTmp.add(coutMP);*/
		 
		
	}
	return listCoutMPTmp;
  }

boolean remplirMapChargeSupp(){
	boolean trouve=false;
	BigDecimal quantite=BigDecimal.ZERO;
	for(int j=0;j<table.getRowCount();j++){
		/*
		quantite =new BigDecimal(table.getValueAt(j, 5).toString());
		
		if(!table.getValueAt(j, 5).toString().equals("") || quantite.compareTo(BigDecimal.ZERO) !=0){
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 5).toString());
			trouve=true;
		}else {
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), "0");
		}
		*/
		

		quantite =new BigDecimal(table.getValueAt(j, 6).toString());
		
		if(!table.getValueAt(j, 6).toString().equals("") && quantite.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) != 0){
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 6).toString());
			trouve=true;
		}else {
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), "0");
		}
		
	
		
		
		
		
	}
	return trouve;
}

List<CoutProdMP> ajouetrChargeSupp(List<CoutProdMP> listCoutMP,Map< String, String> mapChargeSupp,int idMagasinProd,int idMagasinStockage){
	BigDecimal QuantiteCharge=BigDecimal.ZERO;
	BigDecimal QuantiteTotal=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;
	List<CoutProdMP> listCoutMPTmp= new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutMP.size();i++){
		CoutProdMP coutMP =listCoutMP.get(i);
		QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()));
		
		 	
		
		 
		/*prixUnitaire = (QuantiteCharge*stockMPStockage.getPrixUnitaire())+(stockMPProd.getPrixUnitaire()*stockMPProd.getStock());
		
		if(quantiteProd> 0)
		prixUnitaire=prixUnitaire/quantiteProd;*/
		
		QuantiteTotal=coutMP.getQuantChargeSupp().add(QuantiteCharge);
		 
		coutMP.setQuantChargeSupp(QuantiteTotal);
		
		
		TransferStockMP transferStockMPTMP=transferStockMPDAO.findTransferByCodeStatut(coutMP.getProdcutionCM().getNumOFMP(), ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		if(transferStockMPTMP==null)
		{
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			detailTransferStockMP.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
			detailTransferStockMP.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
			detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
		
			
			
			detailTransferStockMP.setPrixUnitaire(coutMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(coutMP.getProdcutionCM().getDateProduction())));
	if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
	{
	detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));
	}else
	{
	detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp());
	}
			
			detailTransferStockMP.setTransferStockMP(transferStock);
			listDetailTransferStockMPChargeSupp.add(detailTransferStockMP);
			
		}else
		{
			
			List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppTmp= detailTransfertMPDAO.findByTransferStockMP(transferStockMPTMP.getId());
			
			Boolean trouve=false;
			
			for(int j=0;j<listDetailTransferStockMPChargeSuppTmp.size();j++)
			{
				
				if(listDetailTransferStockMPChargeSuppTmp.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
				{
					trouve=true;
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
					{
						listDetailTransferStockMPChargeSuppTmp.get(j).setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));	
						detailTransfertMPDAO.edit(listDetailTransferStockMPChargeSuppTmp.get(j));
						
					}else
					{
						listDetailTransferStockMPChargeSuppTmp.get(j).setQuantite(coutMP.getQuantChargeSupp());
						
						detailTransfertMPDAO.edit(listDetailTransferStockMPChargeSuppTmp.get(j));
					}
					
					
					
				}
				
			}
			
			if(trouve==false)
			{
				
				DetailTransferStockMP detailTransferStockMPTEM=new DetailTransferStockMP();
				detailTransferStockMPTEM.setMagasinDestination(coutMP.getProdcutionCM().getMagasinProd());
				detailTransferStockMPTEM.setMagasinSouce(coutMP.getProdcutionCM().getMagasinStockage());
				detailTransferStockMPTEM.setMatierePremier(coutMP.getMatierePremier());
			
				detailTransferStockMPTEM.setPrixUnitaire(coutMP.getMatierePremier().getPrixByYear(DateUtils.getAnnee(coutMP.getProdcutionCM().getDateProduction())));
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_SACHET) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_PIECE) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE))
		{
			detailTransferStockMPTEM.setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));
		}else
		{
			detailTransferStockMPTEM.setQuantite(coutMP.getQuantChargeSupp());
		}
				
		detailTransferStockMPTEM.setTransferStockMP(transferStockMPTMP);
				detailTransfertMPDAO.add(detailTransferStockMPTEM);
							
			}
			
			
			
			
		}
		
		
		
		 
		
		listCoutMPTmp.add(coutMP);
	}
	return listCoutMPTmp;
	
}



List<CoutProdMP>  annulerOF(List<CoutProdMP> listCoutMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteAConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;

	List<CoutProdMP> listCoutMPTmp=new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutProdMP coutMP=listCoutMP.get(i);
	/*	if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CELLOPHANE) ){
			quantiteAConsomme=coutMP.getQuantCharge();
		}else {
			quantiteAConsomme=coutMP.getQuantite()-coutMP.getQuantExistante();
		}*/
		quantiteAConsomme=coutMP.getQuantCharge();
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );
		StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
		
		quantiteProd=stockMPProd.getStock().subtract(quantiteAConsomme) ;
		quantiteStockage=stockMPStockage.getStock().add(quantiteAConsomme) ;
		
		/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
		prixUnitaire=prixUnitaire/quantiteProd;
		prixTotal=prixUnitaire*quantiteConsomme;
		
		coutMP.setPrixUnitaire(prixUnitaire);
		coutMP.setPrixTotal(prixTotal);
		listCoutMPTmp.add(coutMP);*/
		stockMPStockage.setStock(quantiteStockage);
		stockMPProd.setStock(quantiteProd);
		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		TransferStockMP transferStockMPCharge=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		
		if(transferStockMPCharge!=null)
		{

			
			transferStockMPDAO.deleteObject(transferStockMPCharge);
			
			
		}
		
		
		
	TransferStockMP transferStockMPChargeSupp=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		
		if(transferStockMPChargeSupp!=null)
		{
			
			
			
			transferStockMPDAO.deleteObject(transferStockMPChargeSupp);
			
			
		}
		
		
TransferStockMP transferStockMPFabriquer=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		
		if(transferStockMPFabriquer!=null)
		{
			
			
			
			transferStockMPDAO.deleteObject(transferStockMPFabriquer);
			
			
		}
		
		
		
	}
	return listCoutMPTmp;
  }


private static String registerMailBody() {
	return "<HTML><b>OF N°  :</b>"+productionMP.getNumOFMP()+" a été lance <br><br>"
			
			+ "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
}


private static String registerMailBody2() {
	List<CoutProdMP> listCoutMP =new ArrayList<CoutProdMP>(); 
	
	listCoutMP =productionMP.getListCoutProdMP();
	String mail;
	String mail1;
	String mail2 = "";
	String mail3;
	String MP[]= null;
	BigDecimal quantite[] = null;
	for (int i=0;i< listCoutMP.size();i++){
		CoutProdMP coutMP=listCoutMP.get(i);
		if(coutMP.getQuantChargeSupp().compareTo(BigDecimal.ZERO) >0)
		mail2=mail2+coutMP.getMatierePremier().getNom()+":"+coutMP.getQuantChargeSupp()+"<br><br>";
		
	}
		
	
	mail1= "<HTML><b>Charge Supplémentaire OF N°  :</b>"+productionMP.getNumOFMP()+"  <br><br>";
			
	mail3		= "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
		
	
	return mail1+mail2+mail3;
}


List<ProductionMP> ChargerOF(String statutCreer,String statutLancer, String depot)
{
	listProductionMP.clear();
	listProductionMP=productionMPDAO.listeProductionMPEtatCreer(statutCreer,statutLancer, depot);
		
	return listProductionMP;
}


void afficher_tableOF(List<ProductionMP> listProductionMP)
{
      
	  int i=0;
	  intialiserTableauOF();
		while(i<listProductionMP.size())
		{	
			
			ProductionMP productionMP=listProductionMP.get(i);			
			Object []ligne={productionMP.getDateProduction(), productionMP.getNumOFMP(), productionMP.getStatut()};

			modeleOF.addRow(ligne);
			i++;
		}
}	



void 	intialiserTableauOF() {
	
	modeleOF =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date OF","Num OF" , "Etat"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   TableOF.setModel(modeleOF); 
		   TableOF.getColumnModel().getColumn(0).setPreferredWidth(30);
		   TableOF.getColumnModel().getColumn(1).setPreferredWidth(300);
		   TableOF.getTableHeader().setReorderingAllowed(false);
}

List<ProductionMP> ChargerMPOF(String statutCreer,String statutLancer, String depot)
{
	listProductionMP.clear();
	listProductionMP=productionMPDAO.listeProductionMPEtatCreer(statutCreer,statutLancer, depot);
		
	return listProductionMP;
}

public void InitialzeTous()
{
	intialiserTableau();
codeArticle.setText("");
quantite.setText("");
categorie.setSelectedIndex(-1);	
txtdateproduction.setText("");

	
}




}

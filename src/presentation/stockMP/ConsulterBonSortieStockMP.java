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
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TypeSortieDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailTransferStockMP;
import dao.entity.DetailTypeSortie;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;
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
import javax.swing.JCheckBox;


public class ConsulterBonSortieStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable tabletransfertstock;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableDetailTransfertStockMP;
	
	JComboBox combotype = new JComboBox();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ProductionDAO productionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private TypeSortieDAO typeSortieDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private List<Production> listProduction=new ArrayList<Production>();
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<TransferStockMP> listTransfertStockMP=new ArrayList<TransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private Map< String, DetailTypeSortie> mapDetailTypeSortie = new HashMap<>();
	private List<DetailTypeSortie> listSousTypeSortieTmp =new ArrayList<DetailTypeSortie>();
	private List<TypeSortie> listTypeSortie =new ArrayList<TypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortie =new ArrayList<DetailTypeSortie>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	private Map< String, TypeSortie> mapTypeSortie = new HashMap<>();
	private Depot depot = new Depot();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private  JComboBox combomp = new JComboBox();
	private JComboBox combofournisseur = new JComboBox();
	private Utilisateur utilisateur;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private  JDateChooser dateChooserdechet = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotDAO;
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	JComboBox combosoustype = new JComboBox();
	private DetailTypeSortieDAO detailTypeSortieDAO;
	 JCheckBox checkBoxEntrer = new JCheckBox("Entree");
	 JDateChooser datedebut = new JDateChooser();
	 JDateChooser datefin = new JDateChooser();
	 private TransferStockMPDAO transferStockMPDAO;
	 private DetailTransferMPDAO detailTransferMPDAO;
	 JButton btnRetourDefinitif = new JButton();
	 JButton btnAnnule = new JButton();
	 private StockMPDAO stockMPDAO;
	 private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
	 private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterBonSortieStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1396, 868);
        try{
        	
        	
        	stockMPDAO=new StockMPDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotDAO= new DepotDAOImpl();
        	typeSortieDAO= new TypeSortieDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
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
				  		     tabletransfertstock = new JXTable();
				  		     tabletransfertstock.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {


				  		    		if(tabletransfertstock.getSelectedRow()!=-1)
				  		     		{
				  		    			


				  		 TransferStockMP transferStockMP=listTransfertStockMP.get(tabletransfertstock.getSelectedRow());
				  		 if(transferStockMP!=null)
				  		 {
				  			listDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());
				  			afficher_tableDetailTransfertStockMP(listDetailTransfertStockMP);
				  			if(transferStockMP.getSoustype()!=null)
				  			{
				  				
				  				if(transferStockMP.getSoustype().getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
				  				{
				  					btnAnnule.setVisible(true);
				  					btnRetourDefinitif.setVisible(true);
				  				}else
				  				{
				  					btnAnnule.setVisible(false);
				  					btnRetourDefinitif.setVisible(false);
				  				}
				  				
				  			}
				  			
				  			
				  			
				  			
				  		 }else
				  		 {
				  			listDetailTransfertStockMP.clear(); 
				  			afficher_tableDetailTransfertStockMP(listDetailTransfertStockMP);
				  		 }
				  	
				  		  		  		     	
				  		     						  		     				


				  		     	
				  		     	}
				  		     	
				  		     	
				  		     	}
				  		     });
				  		     tabletransfertstock.setShowVerticalLines(false);
				  		     tabletransfertstock.setSelectionBackground(new Color(51, 204, 255));
				  		     tabletransfertstock.setRowHeightEnabled(true);
				  		     tabletransfertstock.setBackground(new Color(255, 255, 255));
				  		     tabletransfertstock.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     tabletransfertstock.setColumnControlVisible(true);
				  		     tabletransfertstock.setForeground(Color.BLACK);
				  		     tabletransfertstock.setGridColor(new Color(0, 0, 255));
				  		     tabletransfertstock.setAutoCreateRowSorter(true);
				  		     tabletransfertstock.setBounds(2, 27, 411, 198);
				  		     tabletransfertstock.setRowHeight(20);
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code Transfert","Date Transfert","Etat","Type"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 tabletransfertstock.setModel(modeleMP); 
					  		 tabletransfertstock.getColumnModel().getColumn(0).setPreferredWidth(60);
					         tabletransfertstock.getColumnModel().getColumn(1).setPreferredWidth(160);
					         tabletransfertstock.getColumnModel().getColumn(1).setPreferredWidth(60);
					         tabletransfertstock.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tabletransfertstock);
				  		     	scrollPane.setBounds(9, 164, 1294, 259);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	

				  		     	
				  		     		tableDetailTransfertStockMP = new JXTable();
				  		     		tableDetailTransfertStockMP.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableDetailTransfertStockMP.getSelectedRow()!=-1)
				  		     				{
				  		     					
				  		     					
				  		     					

				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     		});
				  		     		tableDetailTransfertStockMP.setShowVerticalLines(false);
				  		     		tableDetailTransfertStockMP.setSelectionBackground(new Color(51, 204, 255));
				  		     		tableDetailTransfertStockMP.setRowHeightEnabled(true);
				  		     		tableDetailTransfertStockMP.setBackground(new Color(255, 255, 255));
				  		     		tableDetailTransfertStockMP.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		tableDetailTransfertStockMP.setColumnControlVisible(true);
				  		     		tableDetailTransfertStockMP.setForeground(Color.BLACK);
				  		     		tableDetailTransfertStockMP.setGridColor(new Color(0, 0, 255));
				  		     		tableDetailTransfertStockMP.setAutoCreateRowSorter(true);
				  		     		//    table.setBounds(2, 27, 411, 198);
				  		     		tableDetailTransfertStockMP.setRowHeight(20);
				  		      modeleMP1 =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Code","Nom MP","Quantité a Tranférer","Fournisseur"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableDetailTransfertStockMP.setModel(modeleMP1); 
				  			 tableDetailTransfertStockMP.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableDetailTransfertStockMP.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableDetailTransfertStockMP);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(9, 460, 1294, 303);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 133, 1294, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1294, 88);
				  		     	add(layeredPane);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1253, 38, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Rechercher();

			
				
				
				
				
				
				
				
				
				
			
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Du :");
		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(10, 38, 45, 24);
		layeredPane.add(label);
		
		 datedebut = new JDateChooser();
		datedebut.setLocale(Locale.FRANCE);
		datedebut.setDateFormatString("dd/MM/yyyy");
		datedebut.setBounds(44, 36, 163, 26);
		layeredPane.add(datedebut);
		
		JLabel label_1 = new JLabel("Au :");
		label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		label_1.setBounds(217, 38, 36, 24);
		layeredPane.add(label_1);
		
		 datefin = new JDateChooser();
		datefin.setLocale(Locale.FRANCE);
		datefin.setDateFormatString("dd/MM/yyyy");
		datefin.setBounds(247, 36, 169, 26);
		layeredPane.add(datefin);
		
		JLabel label_3 = new JLabel("Type :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(693, 38, 46, 24);
		layeredPane.add(label_3);
		
		 combotype = new JComboBox();
		 combotype.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

  		   		
  		   		if(combotype.getSelectedIndex()!=-1)
  		   		{
  		   			


  		   		
  		   		if(!combotype.getSelectedItem().equals(""))
  		   		{
  		   		
		   			combosoustype.removeAllItems();
		   		combosoustype.addItem("");
		   		combosoustype.setVisible(true);
  		   	TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(combotype.getSelectedItem().toString());	
  		   		if(typeSortieTmp!=null)	
  		   		{
  		   		listSousTypeSortieTmp=detailTypeSortieDAO.listeDetailTypeSortieByTypeSortie(typeSortieTmp);
  		   		if(listSousTypeSortieTmp.size()!=0)
  		   		{
  		   			
  		   			for(int i=0;i<listSousTypeSortieTmp.size();i++)
  		   			{
  		   				
  		   				DetailTypeSortie detailTypeSortie=listSousTypeSortieTmp.get(i);
  		   				
  		   			combosoustype.addItem(detailTypeSortie.getSousType());
  		   		mapDetailTypeSortie.put(detailTypeSortie.getSousType(), detailTypeSortie);
  		   			}
  		   			
  		   			
  		   		}
  		   		
  		   		}
  		   			
  		   			
  		   		}else
  		   		{
  		   			
  		   			combosoustype.removeAllItems();
  		   	
  		   			
  		   			
  		   		}
  		   		
  		   		
  		   		
  		   		
  		   		
  		   			
  		   		}
  		   		
  		   	
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		combotype.setBounds(732, 38, 211, 24);
		layeredPane.add(combotype);
		
		JLabel label_4 = new JLabel("Sous Type :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(953, 38, 74, 24);
		layeredPane.add(label_4);
		
		 combosoustype = new JComboBox();
		combosoustype.setBounds(1020, 38, 218, 24);
		layeredPane.add(combosoustype);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepot.setBounds(426, 35, 55, 26);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
 		     	  	 // comboGroupe = new JComboBox();
		     	 	comboMagasinSource.removeAllItems();
		     	 	//comboGroupe.addItem("");
		     	 Depot depot =new Depot();
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
			  		      			comboMagasinSource.addItem(magasin.getLibelle());
			  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
			  		      			j++;
			  		      		}
		  		      }
		     	 	 }
		     	 	
				
				
				
			}
		});
		combodepot.setBounds(481, 35, 202, 27);
		layeredPane.add(combodepot);
	
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {



				

	  		  	if(listDetailTransfertStockMP.size()!=0){
	  		  		
	  		  		
	  		  			
	  		  	
	  		 
				// List<DetailTransferStockMP> listDetailTransferStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStock.getId());
				
					
					 
					    DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(0);
					    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String date=dateFormat.format(detailTransferStockMP.getTransferStockMP().getDateTransfer());
					    
						Map parameters = new HashMap();
						parameters.put("numTransfer", detailTransferStockMP.getTransferStockMP().getCodeTransfer());
						if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
						{
							parameters.put("depot",   "Dépot Destination      :");
							parameters.put("magasin", "Magasin Destination   :");
							parameters.put("titre", "Bon d'Entrée de la matière première");
							parameters.put("machineSource", detailTransferStockMP.getMagasinDestination().getLibelle().toUpperCase());
							parameters.put("depSource", detailTransferStockMP.getMagasinDestination().getDepot().getLibelle().toUpperCase());
							
						}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
						{
							parameters.put("depot",   "Dépot Source      :");
							parameters.put("magasin", "Magasin Source   :");
							parameters.put("titre", "Bon de Sortie de la matière première");
							parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
							parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
						} else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER))
						{
							parameters.put("depot",   "Dépot Source      :");
							parameters.put("magasin", "Magasin Source   :");
							parameters.put("titre", "Bon de Retour de Fournisseur Annulé");
							parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
							parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
						}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT))
						{
							parameters.put("depot",   "Dépot Source      :");
							parameters.put("magasin", "Magasin Source   :");
							parameters.put("titre", "Bon de Sortie En Attente de la matière première");
							parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
							parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
						}  
					
						parameters.put("dateTransfer", date);
						
						if(detailTransferStockMP.getTransferStockMP().getDesignation()!=null)
						{
							parameters.put("designation", detailTransferStockMP.getTransferStockMP().getDesignation().toUpperCase());
						}else
						{
							parameters.put("designation", " ");
						}
						
						if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType()!=null)
						{
							parameters.put("type", detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().toUpperCase());
						}else
						{
							parameters.put("type", detailTransferStockMP.getTransferStockMP().getType().getLiblle().toUpperCase());
						}
						
						JasperUtils.imprimerBonSortieMP(listDetailTransfertStockMP,parameters,detailTransferStockMP.getTransferStockMP().getCodeTransfer());
	  		  		
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Liste Vide ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	return;
	  		  	}
	  		  	
			 
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgImprimer);
		 button_1.setBounds(532, 774, 104, 31);
		 add(button_1);
		 
		  btnRetourDefinitif = new JButton();
		  btnRetourDefinitif.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  	if(	listDetailTransfertStockMP.size()!=0)
		  	{
		  		if(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype()!=null)
		  		{
		  			
		  			
		  				
		  				if(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype().getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
		  				{


		  					
		  					BigDecimal montantTotal=BigDecimal.ZERO;
		  	                BigDecimal montantTotalEnvrac=BigDecimal.ZERO;
		  	                BigDecimal montantTotalEmballage=BigDecimal.ZERO;
	
	for(int i=0;i<listDetailTransfertStockMP.size();i++)
	{
		
	
			MatierePremier mp=listDetailTransfertStockMP.get(i).getMatierePremier();
			
			
			StockMP stockMP=null;
			if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					  					{
				
				stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(mp.getId(), listDetailTransfertStockMP.get(i).getMagasinSouce().getId(), listDetailTransfertStockMP.get(i).getFournisseur().getId());	
					  					
					  					}else
					  					{
					  						
				stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), listDetailTransfertStockMP.get(i).getMagasinSouce().getId());	
				
					  					}
		
			stockMP.setStock(stockMP.getStock().subtract(listDetailTransfertStockMP.get(i).getQuantite()));
			stockMPDAO.edit(stockMP);
			
			
			if(listDetailTransfertStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
   			{
   				montantTotalEnvrac=montantTotalEnvrac.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(listDetailTransfertStockMP.get(i).getTransferStockMP().getDateTransfer()))));
   				 
   				
   			}else
   			{
   				montantTotalEmballage=montantTotalEmballage.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(listDetailTransfertStockMP.get(i).getTransferStockMP().getDateTransfer()))));
   				 
   			}
   			montantTotal=montantTotal.add(listDetailTransfertStockMP.get(i).getQuantite().multiply(listDetailTransfertStockMP.get(i).getMatierePremier().getPrixByYear(DateUtils.getAnnee(listDetailTransfertStockMP.get(i).getTransferStockMP().getDateTransfer()))));
   
				
			

		
	}
	
	
	
	    TransferStockMP transferStockMP=new TransferStockMP();
		transferStockMP.setCodeTransfer(listDetailTransfertStockMP.get(0).getTransferStockMP().getCodeTransfer());
		transferStockMP.setCreerPar(utilisateur);
		transferStockMP.setDate(new Date());
		transferStockMP.setDateTransfer(listDetailTransfertStockMP.get(0).getTransferStockMP().getDateTransfer());
		transferStockMP.setDepot(listDetailTransfertStockMP.get(0).getTransferStockMP().getDepot());
		transferStockMP.setSoustype(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype());
		transferStockMP.setType(listDetailTransfertStockMP.get(0).getTransferStockMP().getType());
		transferStockMP.setStatut(Constantes.ETAT_SORTIE_ENATTENT_VALIDER);
		transferStockMPDAO.add(transferStockMP);
		
		for(int d=0;d<listDetailTransfertStockMP.size();d++)
		{
			
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();	
		detailTransferStockMP.setFournisseur(listDetailTransfertStockMP.get(d).getFournisseur());
		detailTransferStockMP.setMagasinDestination(listDetailTransfertStockMP.get(d).getMagasinDestination());
		detailTransferStockMP.setMagasinSouce(listDetailTransfertStockMP.get(d).getMagasinSouce());
		detailTransferStockMP.setMatierePremier(listDetailTransfertStockMP.get(d).getMatierePremier());
		detailTransferStockMP.setPrixUnitaire(listDetailTransfertStockMP.get(d).getPrixUnitaire());
		detailTransferStockMP.setQuantite(listDetailTransfertStockMP.get(d).getQuantite());
		detailTransferStockMP.setQuantiteDechet(listDetailTransfertStockMP.get(d).getQuantiteDechet());
		detailTransferStockMP.setQuantiteExistante(listDetailTransfertStockMP.get(d).getQuantiteExistante());
		detailTransferStockMP.setQuantiteManque(listDetailTransfertStockMP.get(d).getQuantiteManque());
		detailTransferStockMP.setQuantiteOffre(listDetailTransfertStockMP.get(d).getQuantiteOffre());
		detailTransferStockMP.setQuantiteRetour(listDetailTransfertStockMP.get(d).getQuantiteRetour());
		detailTransferStockMP.setStockSource(listDetailTransfertStockMP.get(d).getStockSource());
		detailTransferStockMP.setTransferStockMP(transferStockMP);
		detailTransferMPDAO.add(detailTransferStockMP);
		
		
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
   			  
   			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
   		  
   			statistiqueFinanciaireMP.setCodeTransfer(listDetailTransfertStockMP.get(0).getTransferStockMP().getCodeTransfer());
   			statistiqueFinanciaireMP.setDate(new Date());
   			statistiqueFinanciaireMP.setDateOperation(listDetailTransfertStockMP.get(0).getTransferStockMP().getDateTransfer());
   			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
   			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
   			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
   			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
   			statistiqueFinanciaireMP.setCOUTPF(statistiqueFinanciaireMPTmp.getCOUTPF());		  				     			
   			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
   			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie().add(montantTotal));
   			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
   			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
   			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour().add(montantTotal));
   			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
   		 
   			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
   			 
   			
   		}
	
	
	
	
	DetailTypeSortie soustypesortie=detailTypeSortieDAO.DetailTypeSortieByTypeSortie(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_DEFINITIF);
		  	
	TransferStockMP transferStockMPTmp=listDetailTransfertStockMP.get(0).getTransferStockMP();
	transferStockMPTmp.setSoustype(soustypesortie);;
	transferStockMPTmp.setStatut(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
	transferStockMPDAO.edit(transferStockMPTmp);
	
	JOptionPane.showMessageDialog(null, "Le Stock Retour En Attent à été transfert en Retour Definitif","Bravo",JOptionPane.INFORMATION_MESSAGE);
	
	listDetailTransfertStockMP=detailTransferMPDAO.findByTransferStockMP(listDetailTransfertStockMP.get(0).getTransferStockMP().getId());

	  	if(listDetailTransfertStockMP.size()!=0){
	  		
	  		
	  			
	  	
	 
	// List<DetailTransferStockMP> listDetailTransferStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStock.getId());
	
		
		 
		    DetailTransferStockMP detailTransferStockMP=listDetailTransfertStockMP.get(0);
		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  		  	String date=dateFormat.format(detailTransferStockMP.getTransferStockMP().getDateTransfer());
		    
			Map parameters = new HashMap();
			parameters.put("numTransfer", detailTransferStockMP.getTransferStockMP().getCodeTransfer());
		
	
				parameters.put("depot",   "Dépot Source      :");
				parameters.put("magasin", "Magasin Source   :");
				parameters.put("titre", "Bon de Sortie de la matière première");
				parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle().toUpperCase());
				parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle().toUpperCase());
				 
			 
		
			parameters.put("dateTransfer", date);
			if( detailTransferStockMP.getTransferStockMP().getDesignation()!=null)
			{
				parameters.put("designation", detailTransferStockMP.getTransferStockMP().getDesignation().toUpperCase());
			}else
			{
				parameters.put("designation", " ");
			}
			
			if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType()!=null)
			{
				parameters.put("type", detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().toUpperCase());
			}else
			{
				parameters.put("type", detailTransferStockMP.getTransferStockMP().getType().getLiblle().toUpperCase());
			}
			
			JasperUtils.imprimerBonSortieMP(listDetailTransfertStockMP,parameters,detailTransferStockMP.getTransferStockMP().getCodeTransfer());
			btnAnnule.setVisible(false);
				btnRetourDefinitif.setVisible(false);
	
//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  	}else {
	  	JOptionPane.showMessageDialog(null, "Liste Vide ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  	return;
	  	}
	
	
	
	
	
		  					
		  					
		  				}
		  				
		  				
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  		}
		  	}
		  		
	
		  		
		  		
		  	}
		  });
		 btnRetourDefinitif.setText("Retour D\u00E9finitif");
		 btnRetourDefinitif.setFont(new Font("Tahoma", Font.BOLD, 11));
		 btnRetourDefinitif.setBounds(233, 774, 134, 31);
		 add(btnRetourDefinitif);
		 btnRetourDefinitif.setVisible(false);
		  btnAnnule = new JButton();
		  btnAnnule.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment annuler le stock retour fournisseur  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						
				  		if(listDetailTransfertStockMP.size()!=0)
				  		{
				  			
				  		
				  			if(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype()!=null)
					  		{
					  			
					  			
					  				
					  				if(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype().getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
					  				{
					  					
					  					
					  					
					  					
					  					TransferStockMP transferStockMP=new TransferStockMP();
					  					transferStockMP.setCodeTransfer(listDetailTransfertStockMP.get(0).getTransferStockMP().getCodeTransfer());
					  					transferStockMP.setCreerPar(utilisateur);
					  					transferStockMP.setDate(new Date());
					  					transferStockMP.setDateTransfer(listDetailTransfertStockMP.get(0).getTransferStockMP().getDateTransfer());
					  					transferStockMP.setDepot(listDetailTransfertStockMP.get(0).getTransferStockMP().getDepot());
					  					transferStockMP.setSoustype(listDetailTransfertStockMP.get(0).getTransferStockMP().getSoustype());
					  					transferStockMP.setType(listDetailTransfertStockMP.get(0).getTransferStockMP().getType());
					  					transferStockMP.setStatut(Constantes.ETAT_SORTIE_ENATTENT_VALIDER);
					  					transferStockMPDAO.add(transferStockMP);
					  					
					  					for(int d=0;d<listDetailTransfertStockMP.size();d++)
					  					{
					  						
					  					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();	
					  					detailTransferStockMP.setFournisseur(listDetailTransfertStockMP.get(d).getFournisseur());
					  					detailTransferStockMP.setMagasinDestination(listDetailTransfertStockMP.get(d).getMagasinDestination());
					  					detailTransferStockMP.setMagasinSouce(listDetailTransfertStockMP.get(d).getMagasinSouce());
					  					detailTransferStockMP.setMatierePremier(listDetailTransfertStockMP.get(d).getMatierePremier());
					  					detailTransferStockMP.setPrixUnitaire(listDetailTransfertStockMP.get(d).getPrixUnitaire());
					  					detailTransferStockMP.setQuantite(listDetailTransfertStockMP.get(d).getQuantite());
					  					detailTransferStockMP.setQuantiteDechet(listDetailTransfertStockMP.get(d).getQuantiteDechet());
					  					detailTransferStockMP.setQuantiteExistante(listDetailTransfertStockMP.get(d).getQuantiteExistante());
					  					detailTransferStockMP.setQuantiteManque(listDetailTransfertStockMP.get(d).getQuantiteManque());
					  					detailTransferStockMP.setQuantiteOffre(listDetailTransfertStockMP.get(d).getQuantiteOffre());
					  					detailTransferStockMP.setQuantiteRetour(listDetailTransfertStockMP.get(d).getQuantiteRetour());
					  					detailTransferStockMP.setStockSource(listDetailTransfertStockMP.get(d).getStockSource());
					  					detailTransferStockMP.setTransferStockMP(transferStockMP);
					  					detailTransferMPDAO.add(detailTransferStockMP);
					  					
					  					
					  					}
					  					
					  					
					  					listDetailTransfertStockMP.get(0).getTransferStockMP().setStatut(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
					  					
					  					//DetailTypeSortie detailTypeSortie=detailTypeSortieDAO.DetailTypeSortieByTypeSortie(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
					  					//listDetailTransfertStockMP.get(0).getTransferStockMP().setSoustype(detailTypeSortie);
					  					transferStockMPDAO.edit(listDetailTransfertStockMP.get(0).getTransferStockMP());
					  					
					  				}
				  			
					  		}
				  			
				  		}
				  		
						JOptionPane.showMessageDialog(null, "Le Stock Retour fournisseur en attent à été annulé ","Bravo",JOptionPane.INFORMATION_MESSAGE);
					Rechercher();
					btnAnnule.setVisible(false);
  					btnRetourDefinitif.setVisible(false);
					}
		  		
	
		  		
		  		
		  		
		  		
		  	}
		  });
		 btnAnnule.setText("Annul\u00E9");
		 btnAnnule.setFont(new Font("Tahoma", Font.BOLD, 11));
		 btnAnnule.setBounds(377, 774, 134, 31);
		 add(btnAnnule);
		 btnAnnule.setVisible(false);
				listMatierePremiere=matierePremiereDAO.findAll();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					combomp.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				
				listFournisseur=fournisseurMPDAO.findAll();
				for(int j=0;j<listFournisseur.size();j++)
				{
					FournisseurMP fournisseurMP=listFournisseur.get(j);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				
			    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
		    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
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
			    
			    ChargerTypeSortie();
				
				  		 
	}
	
	
	void afficher_tableDetailTransfertStockMP(List<DetailTransferStockMP> listDetailTransferStockMP)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailTransferStockMP.size();i++)
			{	
				
	DetailTransferStockMP DetailTransferStockMP=	listDetailTransferStockMP.get(i);
	
	String fournisseur="";
			 if(DetailTransferStockMP.getFournisseur()!=null)
			 {
				 fournisseur =DetailTransferStockMP.getFournisseur().getCodeFournisseur();
				 
			 }
				 Object []ligne={DetailTransferStockMP.getMatierePremier().getCode(),DetailTransferStockMP.getMatierePremier().getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(DetailTransferStockMP.getQuantite()),fournisseur};

					modeleMP1.addRow( ligne); 
		
				 
				
			
			}
			
		
	}
	
	
void afficher_tableTransfertStockMP(List<TransferStockMP> listTransferStockMP)
	{
		intialiserTableau();
		 
		for (int i=0;i<listTransferStockMP.size();i++)
		{	
			
TransferStockMP transferStockMP=	listTransferStockMP.get(i);
		 
			 
			Object []ligne={transferStockMP.getCodeTransfer(),transferStockMP.getDateTransfer(),transferStockMP.getStatut(),transferStockMP.getSoustype().getSousType()};

			modeleMP.addRow( ligne);
		
		}
		
		
			
		
	}






void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Transfert","Date Transfert","Etat","Type"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tabletransfertstock.setModel(modeleMP); 
		 tabletransfertstock.getColumnModel().getColumn(0).setPreferredWidth(60);
      tabletransfertstock.getColumnModel().getColumn(1).setPreferredWidth(160);
      tabletransfertstock.getColumnModel().getColumn(2).setPreferredWidth(60);
      tabletransfertstock.getColumnModel().getColumn(3).setPreferredWidth(60);

 
}

void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Quantité a Tranférer","Fournisseur"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableDetailTransfertStockMP.setModel(modeleMP1); 
		 tableDetailTransfertStockMP.getColumnModel().getColumn(0).setPreferredWidth(160);
		 tableDetailTransfertStockMP.getColumnModel().getColumn(1).setPreferredWidth(60);
  


}

void ChargerTypeSortie()
{
	combotype.removeAllItems();
	listTypeSortie.clear();
	listTypeSortie=typeSortieDAO.findAll();
	for(int i=0;i<listTypeSortie.size();i++)
	{
		TypeSortie typesortie=listTypeSortie.get(i);
		combotype.addItem(typesortie.getLiblle());
		mapTypeSortie.put(typesortie.getLiblle(), typesortie);
	}
	
}




public void Rechercher()
{


	btnAnnule.setVisible(false);
	btnRetourDefinitif.setVisible(false);
	
if(combodepot.getSelectedIndex()!=-1)
{

Depot depot=mapDepotSource.get(combodepot.getSelectedItem());


String etat=Constantes.ETAT_TRANSFER_STOCK_SORTIE;



String requet="";







DetailTypeSortie detailTypeSortie=detailTypeSortieDAO.DetailTypeSortieByTypeSortie(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
if(detailTypeSortie!=null)
{
	if(combosoustype.getSelectedItem().equals(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER))
	{
		etat=Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER;
	}
		
	
}


if(combosoustype.getSelectedItem().toString().equals(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ENATTENT))
{
	etat=Constantes.ETAT_SORTIE_ENATTENT;
}




requet=requet+" and statut='"+etat+"'";

datedebut.setDateFormatString("yyyy-MM-dd");
String dateDu=((JTextField)datedebut.getDateEditor().getUiComponent()).getText();
datefin.setDateFormatString("yyyy-MM-dd");
String dateAu=((JTextField)datefin.getDateEditor().getUiComponent()).getText();

if(!dateDu.equals("") && dateAu.equals(""))
{
	dateAu=dateDu;
}else if(dateDu.equals("") && !dateAu.equals(""))
{
	dateDu=dateAu;
}

TypeSortie typeSortie=mapTypeSortie.get(combotype.getSelectedItem());
DetailTypeSortie soustype=mapDetailTypeSortie.get(combosoustype.getSelectedItem());

if(!dateDu.equals(""))
{
requet=requet+" and dateTransfer between '"+dateDu+"' and '"+dateAu+"'";
}

if(typeSortie!=null)
{
requet=requet+" and soustype.typesortie.id='"+typeSortie.getId()+"'";
}

if(soustype!=null)
{
	
requet=requet+" and soustype.id='"+soustype.getId()+"'";

}


listTransfertStockMP=transferStockMPDAO.findTransferConsultationBonSortieMP(depot, requet);
intialiserTableau2();
afficher_tableTransfertStockMP(listTransfertStockMP);


}else
{
JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot SVP !!!!");
return ;
}



}





}

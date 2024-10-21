package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
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
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SortirStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private JButton btnIntialiserOF;
	private Map< Integer, BigDecimal> mapQuantiteMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	//private List<MatierePremier> listMatierePremierTmp =new ArrayList<MatierePremier>();
	TransferStockMP transferStock = new TransferStockMP();
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	 private SubCategorieMPDAO categorieMPDAO;
	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtRefTransfere;
	private JTextField txtCodeMP;
	private JTextField txtQuantite;
	JComboBox comboMP = new JComboBox();
	JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	boolean valider=false;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	JComboBox comboFournisseur = new JComboBox();
	
	private List <Object[]> Mindate=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
	private SubCategorieMPDAO subcategoriempdao;
	private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
	private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurReception=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=new ArrayList<Object[]>();
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SortirStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 696);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	listMP=matierePremiereDAO.findAll();
        	   fournisseurMPDAO=new FournisseurMPDAOImpl();
               categorieMPDAO=new SubCategorieMPAOImpl();
               detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
           	subcategoriempdao= new SubCategorieMPAOImpl();
           	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
           	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
        	
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        comboMagasinDestination=new JComboBox();
    	comboDepotSource=new JComboBox();
    	comboMagasinSource=new JComboBox();
    	comboDepotDestination=new JComboBox();
    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	     		comboDepotSource.addItem(depot.getLibelle());
	     		mapDepotSource.put(depot.getLibelle(), depot);
    }else {
    	
    	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotSource.put(depot.getLibelle(), depot);
	      			comboDepotSource.addItem(depot.getLibelle());
	      			i++;
	      		}
    	
    }
	     	
	     	comboDepotDestination.addItem("");
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(415, 592, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(true);
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
				  		   intialiserTableau();
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 238, 989, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,MAGASIN_CODE_CATEGORIE_STOCKAGE);
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
					  		      
					  		      
					  		      
					  	    comboDepotSource.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinSource.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotSource.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotSource.get(comboDepotSource.getSelectedItem());
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
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
					  		      
				  		     
				  		     	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		    
					  		      
					  	    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP, MAGASIN_CODE_CATEGORIE_STOCKAGE);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 209, 989, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 989, 188);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 184, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 184, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(409, 74, 152, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(308, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("Dépot Destination");
				  		  lblDpotDestination.setBounds(308, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(409, 34, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(308, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTrnsafer = new JLabel("Code Transafert ");
				  		  lblCodeTrnsafer.setBounds(571, 74, 94, 24);
				  		  layeredPane.add(lblCodeTrnsafer);
				  		  
				  		  txtRefTransfere = new JTextField();
				  		util.Utils.copycoller(txtRefTransfere);
				  		  txtRefTransfere.setBounds(662, 74, 155, 24);
				  		  layeredPane.add(txtRefTransfere);
				  		  txtRefTransfere.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(662, 34, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(571, 34, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel label = new JLabel("Code MP:");
				  		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label.setBounds(10, 114, 102, 24);
				  		  layeredPane.add(label);
				  		  
				  		  txtCodeMP = new JTextField();
				  		  txtCodeMP.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		

								
								if(e.getKeyCode()==e.VK_ENTER)
					      		{
									MatierePremier mp=mapCodeMP.get(txtCodeMP.getText());
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
				  		  txtCodeMP.setText("");
				  		  txtCodeMP.setColumns(10);
				  		  txtCodeMP.setBounds(78, 114, 102, 26);
				  		  layeredPane.add(txtCodeMP);
				  		  
				  		  JLabel label_1 = new JLabel("MP :");
				  		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  label_1.setBounds(190, 114, 37, 24);
				  		  layeredPane.add(label_1);
				  		  
				  		   comboMP = new JComboBox();
				  		   comboMP.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent arg0) {
				  		   		

			  		   	 		
			  		   	 		if(!comboMP.getSelectedItem().equals(""))
			  		   	 		{
			  		   	 			
			  		   	 			MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
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
				  		  comboMP.setSelectedIndex(-1);
				  		  comboMP.setBounds(224, 114, 232, 24);
				  		  layeredPane.add(comboMP);
				  		  AutoCompleteDecorator.decorate(comboMP);
				  		  JLabel label_2 = new JLabel("Quantite :");
				  		  label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label_2.setBounds(801, 112, 102, 24);
				  		  layeredPane.add(label_2);
				  		  
				  		  txtQuantite = new JTextField();
				  		  txtQuantite.setText("");
				  		  txtQuantite.setColumns(10);
				  		  txtQuantite.setBounds(861, 112, 103, 26);
				  		  layeredPane.add(txtQuantite);
		
		 btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
			if(listStockMP.size()==0)	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				if(comboMagasinDestination.getSelectedItem()==null && comboDepotDestination.getSelectedItem().equals("") 
					&&	comboMagasinDestination.getSelectedItem()==null && comboMagasinDestination.getSelectedItem().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir la date de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				
				{
					
				Depot depot =mapDepotSource.get(comboDepotSource.getSelectedItem());
				Depot depoDestination =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
				String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_SORTIE);
				txtRefTransfere.setText(codeTransfert);	
				transferStock.setCodeTransfer(txtRefTransfere.getText());
				transferStock.setCreerPar(AuthentificationView.utilisateur);
				transferStock.setDate(new Date());
				transferStock.setDepotDestination(depoDestination);
				transferStock.setDateTransfer(dateTransfereChooser.getDate());
				transferStock.setStatut(Constantes.ETAT_SORTIE_ENATTENT);
				transferStock.setDepot(mapDepotSource.get(comboDepotSource.getSelectedItem()));
				remplirDetailTransfer();
				transferStockMPDAO.add(transferStock);
				
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(i);
					detailTransferMPDAO.add(detailTransferStockMP);
					
				}
				
				
				
				JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				 

				////////////////////////////////////////// impression /////////////////////////////////////////////////////////////
				
			 	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  	String date=dateFormat.format(transferStock.getDateTransfer());
				
					Map parameters = new HashMap();
					parameters.put("numTransfer", txtRefTransfere.getText());
					parameters.put("machineSource", comboMagasinSource.getSelectedItem());
					parameters.put("depSource", comboDepotSource.getSelectedItem());
					parameters.put("magasinDest", comboMagasinDestination.getSelectedItem());
					parameters.put("depDest", comboDepotDestination.getSelectedItem());
					parameters.put("dateTransfer",date );
					JasperUtils.imprimerBonSortieMPDeplace(listDetailTransferStockMP,parameters,txtRefTransfere.getText());
					
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
					intialiserTous();
					listDetailTransferStockMP=new ArrayList<DetailTransferStockMP>();
					listStockMP=new ArrayList<StockMP>();
					transferStock = new TransferStockMP();
					 
					
					
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(247, 592, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnImprimerBonSortie = new JButton("Bon Sortie MP D\u00E9plac\u00E9e");
		btnImprimerBonSortie.setIcon(imgImprimer);
		btnImprimerBonSortie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
  	if(transferStock.getId()>0){
	  		
	  		if(!comboDepotDestination.getSelectedItem().equals("")){
	  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  	String date=dateFormat.format(transferStock.getDateTransfer());
	 List<DetailTransferStockMP> listDetailTransferStockMP=detailTransferMPDAO.findByTransferStockMP(transferStock.getId());
	 if(listDetailTransferStockMP.size()!=0)
	 {
		 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
			Map parameters = new HashMap();
			parameters.put("numTransfer", transferStock.getCodeTransfer());
			parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
			parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
			parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
			parameters.put("depDest", mapDepotDestionation.get(comboDepotDestination.getSelectedItem()).getLibelle());
			parameters.put("dateTransfer", date);
			JasperUtils.imprimerBonSortieMPDeplace(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
			intialiserTous();
			listDetailTransferStockMP=new ArrayList<DetailTransferStockMP>();
			listStockMP=new ArrayList<StockMP>();
			transferStock = new TransferStockMP();
			btnValiderTransfer.setEnabled(true);
			
	 }else
	 {
		 JOptionPane.showMessageDialog(null, "Il faut saisir les matieres premiere entrer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	 }
	
	  		}else {
	  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		}
	
//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  	}else {
	  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  	}

	  		
	  		  	
			}
		});
		btnImprimerBonSortie.setBounds(537, 592, 178, 23);
		add(btnImprimerBonSortie);
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setBounds(1008, 27, 36, 36);
		add(btnAfficherStock);
		btnAfficherStock.setIcon(imgAjouter);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  				if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else if(comboMP.getSelectedItem().equals(""))
				  				{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					return;
				  				}else if(txtQuantite.getText().equals(""))
				  				{
				  					JOptionPane.showMessageDialog(null, "Il faut entrer la quantite SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					return;
				  				}else if(dateTransfereChooser.getDate()==null)
				  				{
				  					JOptionPane.showMessageDialog(null, "Il faut entrer la date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					return;
				  				}else if(new BigDecimal(txtQuantite.getText()).setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)) || new BigDecimal(txtQuantite.getText()).compareTo(BigDecimal.ZERO)<=0)
				  				{
				  					JOptionPane.showMessageDialog(null, "la Quantite doit etre supérieur à zero SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					return;
				  				}else{
				  					MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
				  					if(mp==null)
				  					{
				  						JOptionPane.showMessageDialog(null, "Il faut choisir un MP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  						return;
				  					}
				  					Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				  					
				  					StockMP stockMP=null;
									
									
									
				  					FournisseurMP fournisseurMP=null;
				  					  		  					
				  						  		  				
				  					fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem());
				  					
				  					if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		  					{
		  		  						if(fournisseurMP==null)
		  		  						{
		  		  							JOptionPane.showMessageDialog(null, "veuillez Selectionner le Fournisseur EN Vrac SVP");
		  		  							return;
		  		  						}
		  		  					}
				  					
				  					
				  					
				  					
				  					CalculerStockFinal();
				  					
				  					boolean erreur=false;
				  					boolean trouve=false;
				  					for(int i=0;i<listEtatStockMPAfficher.size() ;i++)
				  					{
				  										  						
				  					EtatStockMP etatStockMP=	listEtatStockMPAfficher.get(i);
				  					
				  					if(etatStockMP.getMp().getId()==mp.getId())
				  					{
				  						
				  						if(fournisseurMP!=null)
				  						{
				  							if(etatStockMP.getFournisseurMP()!=null)
				  							{
				  								
				  								if(etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId())
				  								{
				  									
				  									trouve=true;
				  									
				  									
				  									
				  									
				  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
				  									{
				  										
				  										erreur=true;
				  										
				  										
				  									}
				  									
				  									
				  								}
				  								
				  								
				  							}
				  							
				  							
				  							
				  							
				  							
				  						}else
				  						{
				  							
				  							if(etatStockMP.getFournisseurMP()==null)
				  							{
				  								trouve=true;
				  								
				  								
				  								
				  									if(etatStockMP.getQuantiteFinale().compareTo(new BigDecimal(txtQuantite.getText()))<0)
				  									{
				  										
				  										erreur=true;
				  										
				  										
				  									}
				  									
				  									
				  								
				  							}
				  							
				  							
				  							
				  							
				  							
				  						}
				  						
				  						
				  						
				  					}
				  						
				  						
				  						
				  						
				  						
				  						
				  						
				  					}
				  					
				  					
				  					
				  					if(trouve==true)
				  					{
				  						
				  						if(erreur==true)
				  						{
				  							
				  							JOptionPane.showMessageDialog(null, "Stock de : «"+mp.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);

					  						return;
				  						}
				  						
				  						
				  						
				  						
				  					}else
				  					{
				  						
				  						JOptionPane.showMessageDialog(null, " «"+mp.getNom()+"» Introuvale dans le Stock", "Erreur", JOptionPane.ERROR_MESSAGE);

				  						return;
				  					}
				  					
			  		  				
				  					if(mp!=null && fournisseurMP!=null)
				  					{
				  						
				  						
				  						stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(mp.getId(), magasin.getId(), fournisseurMP.getId());
				  						
				  						if(stockMP==null)
				  						{
				  							stockMP=new StockMP();	
				  							stockMP.setMagasin(magasin);
				  							stockMP.setMatierePremier(mp);
				  							stockMP.setFournisseurMP(fournisseurMP);
				  							stockMP.setPrixUnitaire(BigDecimal.ZERO);
				  							stockMP.setStock(BigDecimal.ZERO);
				  							stockMP.setStockMin(BigDecimal.ZERO);
				  							stockMP.setQuantiteCommande(BigDecimal.ZERO);
				  							stockMPDAO.add(stockMP);
				  							
				  						}
				  						
				  					}else if(mp!=null && fournisseurMP==null)
				  					{
				  						
				  						stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
				  						
				  						if(stockMP==null)
				  						{
				  							
				  							stockMP=new StockMP();	
				  							stockMP.setMagasin(magasin);
				  							stockMP.setMatierePremier(mp);
				  							stockMP.setPrixUnitaire(BigDecimal.ZERO);
				  							stockMP.setStock(BigDecimal.ZERO);
				  							stockMP.setStockMin(BigDecimal.ZERO);
				  							stockMP.setQuantiteCommande(BigDecimal.ZERO);
				  							stockMPDAO.add(stockMP);
				  							
				  						}
				  					}
				  					
				  					
				  					
				  					 
				  					 
				  					if(stockMP!=null)
				  					{
				  						
				  						
				  						
				  					
				  						
				  						
				  						
				  						
				  						boolean existe=false;
				  						for(int i=0;i<listStockMP.size();i++)
				  						{
				  							if(listStockMP.get(i).getMatierePremier().equals(mp))
				  							{
				  								if(fournisseurMP!=null && listStockMP.get(i).getFournisseurMP()!=null)
				  								{
				  									if(fournisseurMP.getId()==listStockMP.get(i).getFournisseurMP().getId())
				  									{
				  										
				  										existe=true;
				  										
				  									}
				  									
				  									
				  									
				  								}else if(fournisseurMP==null && listStockMP.get(i).getFournisseurMP()==null)
				  								{
				  									
				  									existe=true;
				  									
				  								}
				  								
				  								
				  								
				  								
				  								
				  							}
				  						}
				  						if(existe==false)
				  						{
				  							mapQuantiteMP.put(stockMP.getId(), new BigDecimal(txtQuantite.getText()));
				  							//listMatierePremierTmp.add(mp);
				  							listStockMP.add(stockMP);
				  						}
				  						
				  					}else
				  					{
				  						
				  						JOptionPane.showMessageDialog(null, "Stock Introuvable !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  						return;
				  						
				  					}
				  					
				  					
				  					txtRefTransfere.setText("");	
				  					
				  					
				  					afficher_tableMP(listStockMP);
				  					intialiser();
				  				}
				  			  }
		});
		comboMP.addItem("");
		for(int j=0;j<listMP.size();j++)
		{
			MatierePremier mp=listMP.get(j);
			comboMP.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
		}	
		
		
		txtCodeMP.setText(Constantes.CODE_MP);
		
		JLabel label_3 = new JLabel("Fournisseur : ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(466, 114, 91, 24);
		layeredPane.add(label_3);
		
		 comboFournisseur = new JComboBox();
		comboFournisseur.setSelectedIndex(-1);
		comboFournisseur.setBounds(544, 115, 232, 24);
		layeredPane.add(comboFournisseur);
		
		comboFournisseur.addItem("");
		for(int k=0;k<listFournisseur.size();k++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(k);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
				  		 
	}
	
	
	void intialiserTous()
	{
		comboDepotDestination.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		//txtRefTransfere.setText("");
		dateTransfereChooser.setCalendar(null);
		txtQuantite.setText("");
		txtCodeMP.setText("");
		comboMP.setSelectedItem("");
		intialiserTableau();
				
		
	}
	
	void intialiser()
	{
		
		txtRefTransfere.setText("");
		txtQuantite.setText("");
		txtCodeMP.setText("");
		comboMP.setSelectedItem("");
		txtCodeMP.setText(Constantes.CODE_MP);
		comboFournisseur.setSelectedItem("");		
		
	}
	
	
	void afficher_tableMP(List<StockMP> listStockMP)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockMP.size())
			{	
				
				StockMP stockMP=listStockMP.get(i);
				String fournisseur="";
				mapMatierePremierTmp.put(stockMP.getMatierePremier().getCode(), stockMP.getMatierePremier());
				if(stockMP.getFournisseurMP()!=null)
				{
					
					fournisseur=stockMP.getFournisseurMP().getCodeFournisseur();
					
				}
				
				
				Object []ligne={stockMP.getMatierePremier().getCode(),stockMP.getMatierePremier().getNom(), fournisseur,mapQuantiteMP.get(stockMP.getId())};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			//mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;

	
 listDetailTransferStockMP.clear(); ;
	Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	Magasin magasinDestination=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
	Magasin magasinEntrer =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
	
	
	for(int i=0;i<listStockMP.size();i++){
		StockMP stockMPSource =listStockMP.get(i);
		MatierePremier matierePremier=stockMPSource.getMatierePremier();
		quantite=mapQuantiteMP.get(stockMPSource.getId());
		
		StockMP stockMPDestination=null;
		
		if(quantite.compareTo(BigDecimal.ZERO)>0){
			
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			
			//Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
			
			//StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
			/*
				if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				{
					
					 stockMPDestination=stockMPDAO.findStockByMagasinMPByFournisseurMP(matierePremier.getId(), magasinDestination.getId(),stockMPSource.getFournisseurMP().getId());
					
				}else
				{
					 stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
				}
			
			
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		
		
		prixStockDestination=stockMPDestination.getPrixUnitaire();
		prixStockSource=stockMPSource.getPrixUnitaire();
		*/
		//BigDecimal	prixDest=prixStockDestination.multiply(stockMPDestination.getStock());
		//BigDecimal	prixStock=prixStockSource.multiply(quantite);
		
	/*	prixMoyen=prixDest.add(prixStock);
		if(sommeStock.compareTo(BigDecimal.ZERO)  >0)
		prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		else 
			prixMoyen=BigDecimal.ZERO;
		//stockMPDestination.setPrixUnitaire(prixMoyen);
*//*
			stockMPDestination.setPrixUnitaire(prixStockSource);
		stockMPDestination.setStock(stockDestination);
		stockMPSource.setStock(stockSource);
		
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		*/
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			
			detailTransferStockMP.setFournisseur(stockMPSource.getFournisseurMP());
		}
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(matierePremier.getPrix());
		detailTransferStockMP.setMagasinEntrer(magasinEntrer);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
	}
		//		else {
//		JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
//	}
		
		
	}
	
	return listDetailTransferStockMP;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Fournisseur","Quantité a Tranférer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
   table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}




public void CalculerStockFinal()
{
	

	if(comboDepotSource.getSelectedItem().equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else {
		
		
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
				Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem().toString());
				
			  	FournisseurMP fournisseurMP=mapFournisseur.get(comboFournisseur.getSelectedItem().toString());
			
			  	if(magasin==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
			  		return;
			  	}
			  	
			  	
			  	
				if(dateTransfereChooser.getDate()==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de situation SVP");
			  		return;
			  	}
				
				
				
				Date mindate=null;
				
				Mindate=detailTransferStockMPDAO.MinDate(magasin);
				
				for(int i=0;i<Mindate.size();i++)
				{
					
					 Object[] object= Mindate.get(i);
					
					
					if(Mindate.get(i)!=null)
					{
						mindate=(Date)object[0];
					}
					
				}
				
				String patternYear = "yyyy";
				String patternDate = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
				SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

				
				
				
				if(mindate!=null)
				{
					
					String date = simpleDateFormatDate.format(mindate);
					
					
					try {
					mindate=simpleDateFormatDate.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else
				{
					String year = simpleDateFormatyear.format(dateTransfereChooser.getDate());
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
					listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);

				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

   listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
   listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateTransfereChooser.getDate(), magasin, subCategorieMpthe, null, null);
	
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
			
		listEtatStockMP.clear();
		listEtatStockMPAfficher.clear();
		CalculerStockMPThe();	
		CalculerStockMPNonThe();		
			
			
		for(int j=0;j<listEtatStockMP.size();j++)
		{
			
		EtatStockMP etatStockMP=listEtatStockMP.get(j);	
			
		if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
			listEtatStockMPAfficher.add(etatStockMP);
			}
			
			
			
			
		}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
		{
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else
		{
			
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
			
			
		}
			
			
		
		
		
		
		
		
		
			
			
			
			
			
		}
		

		
		
		
		
			 
		
		
		
		
		
	}
  
	
	
}


public void CalculerStockMPNonThe()
{
	
	
	
	
	
	for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
	{
		
		 Object[] object=listeObjectStockInitialGroupByMP.get(i);
		EtatStockMP etatStockMP=new EtatStockMP();
		MatierePremier mp= (MatierePremier)object[0];
		etatStockMP.setMp(mp);
								
		etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
		
	
		 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
		
		//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
		listEtatStockMP.add(etatStockMP);
		
		
	}


boolean existe=false;

  for(int j=0;j<listeObjectEtatStockGroupByMP.size() ; j++) {
  
	  existe=false;
	  
  Object[] object=listeObjectEtatStockGroupByMP.get(j);
  MatierePremier mp=(MatierePremier)object[0];
	  
  for(int k=0;k<listEtatStockMP.size();k++) {
  
  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
	  if(listEtatStockMP.get(k).getFournisseurMP()==null)
	  {
		 
		  
		  
		  existe=true;
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		  if(etatStockMP.getMp().getCode().equals("MP_703"))
		  {
			  
			System.out.println(etatStockMP.getMp().getCode());  
			  
		  }
		 
		  if((BigDecimal)object[1] != null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
		  {
			  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
		  }else
		  {
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  }
		 
		  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
		  {
			  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
		  }else
		  {
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  }
		 if((BigDecimal)object[4]!=null)
		 {
			 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
		 }else
		 {
			 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
		 }
		  
		 if((BigDecimal)object[5]!=null)
		 {
			 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
		 }else
		 {
			 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		 }
		 
		 if((BigDecimal)object[8]!=null)
		 {
			 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
		 }else
		 {
			 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		 }
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]));

		 }else
		 {
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

		 }
		 if(((BigDecimal)object[10])!=null)
		 {
			  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
		 }else
		 {
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		 }
		
		 if((BigDecimal)object[11]!=null)
		 {
			  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
		 }else
		 {
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		 }
		if((BigDecimal)object[12]!=null)
		{
			 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
		}else
		{
			 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		}
		 
		  
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.set(k, etatStockMP);
	  }

  
  }
  

  
  
  
  
  
  }
  
	if(existe==false)
	{
		
		
		
		  EtatStockMP etatStockMP=new EtatStockMP();
		 
		  etatStockMP.setMp(mp);
		  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
		  
		  if((BigDecimal)object[1] != null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
		  {
			  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
		  }else
		  {
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  }
		 
		  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
		  {
			  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
		  }else
		  {
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  }
		 if((BigDecimal)object[4]!=null)
		 {
			 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
		 }else
		 {
			 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
		 }
		  
		 if((BigDecimal)object[5]!=null)
		 {
			 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
		 }else
		 {
			 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		 }
		 
		 if((BigDecimal)object[8]!=null)
		 {
			 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
		 }else
		 {
			 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		 }
		 
		 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16])!=null)
		 {
			  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]));

		 }else
		 {
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

		 }
		 if(((BigDecimal)object[10])!=null)
		 {
			  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
		 }else
		 {
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		 }
		
		 if((BigDecimal)object[11]!=null)
		 {
			  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
		 }else
		 {
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		 }
		if((BigDecimal)object[12]!=null)
		{
			 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
		}else
		{
			 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		}
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.add(etatStockMP);	
		
		
		
	}
  
  
  
  
  }
 
/////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  
	boolean trouver=false;
	
	  for(int j=0;j<listeEtatStockTransfertEnAttenteNonThe.size() ; j++) {
	  
		  trouver=false;
		  
	 DetailTransferStockMP  detailTransferStockMP=listeEtatStockTransfertEnAttenteNonThe.get(j);
	
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId()) {
		  if(listEtatStockMP.get(k).getFournisseurMP()==null)
		  {
			 if(detailTransferStockMP.getFournisseur()==null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							
							  trouver=true;
							  
							  EtatStockMP etatStockMP=listEtatStockMP.get(k);
							  
							
							  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
							 		  listEtatStockMP.set(k, etatStockMP);
							
							
							
							
						}
						
						
						
					}
						
						
						
						
						
					}
				 
				 
				 
			 }
			 
		  }

	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(trouver==false)
		{
			
			 if(detailTransferStockMP.getFournisseur()==null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							
							
							  EtatStockMP etatStockMP=new EtatStockMP();
								 
							  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
							  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
							  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
							  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
							  etatStockMP.setTransferSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
							  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
							  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
							  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
							  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
							  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
							  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
							 
							  listEtatStockMP.add(etatStockMP);	
							
							
							
							
							
							
							
						}
					}
					}
			 }
			
			
			
			
			
		}
	  
	  
	  
	  
	  }
  
  
  
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  
  
  
  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
	 
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  }




}

public void CalculerStockMPThe()
{
	

	
	

	
	
	
	for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
	{
		
		 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
		EtatStockMP etatStockMP=new EtatStockMP();
		MatierePremier mp= (MatierePremier)object[0];
		
		FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		
		etatStockMP.setMp(mp);
								
		etatStockMP.setQuantiteInitial((BigDecimal)object[2]);
		
		etatStockMP.setFournisseurMP(fournisseurMP);
		 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
		etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
		listEtatStockMP.add(etatStockMP);
		
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
boolean existe=false;

  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurReception.size() ; j++) {
  
	  existe=false;
	  
  Object[] object=listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
  MatierePremier mp=(MatierePremier)object[0];
  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
	  
  for(int k=0;k<listEtatStockMP.size();k++) {
  
  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
	  existe=true;
  
  EtatStockMP etatStockMP=listEtatStockMP.get(k);
  
 
  if((BigDecimal)object[2]!=null)
  {
	  etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add((BigDecimal)object[2]) ); 
  }
 

  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
  listEtatStockMP.set(k, etatStockMP);
  
  
  
  }
  

  
  
  
  
  
  }
  
	if(existe==false)
	{
		
		
		
		  EtatStockMP etatStockMP=new EtatStockMP();
		 
		  etatStockMP.setMp(mp);
		  etatStockMP.setFournisseurMP(fournisseurMP);
		  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
		  if((BigDecimal)object[2]!=null)
		  {
			  etatStockMP.setQuantiteReception((BigDecimal)object[2]);
		  }else
		  {
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
		  }
		 
		  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
		  etatStockMP.setTransferSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
		  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
		  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
		  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
		  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
		  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
		  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
		  
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
		 
		  listEtatStockMP.add(etatStockMP);	
		
		
	}
  
  
  
  
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 existe=false;
	 
	 BigDecimal enter=BigDecimal.ZERO;
	 BigDecimal transfert=BigDecimal.ZERO;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurEntrer.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMPByFournisseurEntrer.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
	  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
	  
	  enter=BigDecimal.ZERO;
	  transfert=BigDecimal.ZERO;
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  enter=BigDecimal.ZERO;
		  transfert=BigDecimal.ZERO;
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
		  existe=true;
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	  
	 
	  if(((BigDecimal)object[2])!=null)
	  {
		  enter=(BigDecimal)object[2];
		 
	  }
	  if(((BigDecimal)object[3])!=null)
	  {
		  transfert=(BigDecimal)object[3];
		 
	  }
	  
	  
	  etatStockMP.setTransferEntrer (etatStockMP.getTransferEntrer().add(enter.add(transfert)));
	 
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setFournisseurMP(fournisseurMP);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  if(((BigDecimal)object[2])!=null)
			  {
				  enter=(BigDecimal)object[2];
				 
			  }
			  if(((BigDecimal)object[3])!=null)
			  {
				  transfert=(BigDecimal)object[3];
				 
			  }  
			  
			  
			 
			etatStockMP.setTransferEntrer (enter.add(transfert));
			 
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
		}
	  
	  
	  
	  
	  }
	   
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;

for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurSortie.size() ; j++) {

existe=false;
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;
Object[] object=listeObjectEtatStockGroupByMPByFournisseurSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if(((BigDecimal)object[2])!=null)
{
sortie=(BigDecimal)object[2];
 
}
if(((BigDecimal)object[3])!=null)
{
  transfert=(BigDecimal)object[3];
 
}  
etatStockMP.setTransferSortie (etatStockMP.getTransferSortie(). add(sortie.add(transfert)));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
sortie=(BigDecimal)object[2];
 
}
if(((BigDecimal)object[3])!=null)
{
  transfert=(BigDecimal)object[3];
 
} 


etatStockMP.setTransferSortie(sortie.add(transfert));


etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal charge=BigDecimal.ZERO;
BigDecimal chargesupp=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurCharger.size() ; j++) {
 charge=BigDecimal.ZERO;
 chargesupp=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurCharger.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
charge=BigDecimal.ZERO;
 chargesupp=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if((BigDecimal)object[2]!=null)
{
charge=(BigDecimal)object[2];

}
etatStockMP.setQuantiteCharger(etatStockMP.getQuantiteCharger().add(charge));
if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteChargerSupp(etatStockMP.getQuantiteChargerSupp().add(chargesupp));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);

if((BigDecimal)object[2]!=null)
{
charge=(BigDecimal)object[2];

}

if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteCharger(charge);

etatStockMP.setQuantiteChargerSupp(chargesupp);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
retour=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retour=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if((BigDecimal)object[2]!=null)
{
retour=(BigDecimal)object[2];

}
etatStockMP.setQuantiteRetour (etatStockMP.getQuantiteRetour().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
if((BigDecimal)object[2]!=null)
{
retour=(BigDecimal)object[2];

}

etatStockMP.setQuantiteRetour(retour);


etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
sortie=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size() ; j++) {
 sortie=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
 sortie=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortie));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortie));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   SortiePF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortiePF=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size() ; j++) {

sortiePF=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {

sortiePF=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortiePF=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortiePF));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortiePF=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortiePF));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal sortieEnAttente=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size() ; j++) {
sortieEnAttente=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
sortieEnAttente=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortieEnAttente=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortieEnAttente));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortieEnAttente=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortieEnAttente));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Perte////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal perte=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size() ; j++) {
perte=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
perte=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
perte=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(perte));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
perte=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((perte));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
retour=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size() ; j++) {
retour=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retour=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
retour=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
retour=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retour));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}



////////////////////////////////////////////////////////////////////////////////////////////////////////Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal resteMachine=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurResterMachine.size() ; j++) {
resteMachine=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
resteMachine=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
resteMachine=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(resteMachine));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
resteMachine=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteResterMachine(resteMachine);


etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	  

////////////////////////////////////////////////////////////////////////////////////////////////////////Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal fabriquer=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurFabrique.size() ; j++) {
fabriquer=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
fabriquer=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
fabriquer=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteFabriquer (etatStockMP.getQuantiteFabriquer().add(fabriquer));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
fabriquer=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteFabriquer(fabriquer);


etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	

////////////////////////////////////////////////////////////////////////////////////////////////////////Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal existante=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurExistante.size() ; j++) {
existante=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurExistante.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
existante=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);

}

etatStockMP.setQuantiteExistante(etatStockMP.getQuantiteExistante().add(existante));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);

}
etatStockMP.setQuantiteExistante(existante);


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
	boolean trouver=false;
	
	  for(int j=0;j<listeEtatStockTransfertEnAttenteThe.size() ; j++) {
	  
		  trouver=false;
		  
	  DetailTransferStockMP detailTransferStockMP=listeEtatStockTransfertEnAttenteThe.get(j);
	 
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==detailTransferStockMP.getFournisseur().getId()) {
		
		if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
			
			
		{
		if(detailTransferStockMP.getMagasinSouce()!=null)	
		{
			
			if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
				
			{
				
				
				  trouver=true;
				  
				  EtatStockMP etatStockMP=listEtatStockMP.get(k);
				  
				 
				  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
				 		  listEtatStockMP.set(k, etatStockMP);
				
				
				
				
			}
			
			
			
		}
			
			
			
			
			
		}
		  
	
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(trouver==false)
		{
			
			 if(detailTransferStockMP.getFournisseur()!=null) 
			 {
				 
					if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
						
						
					{
					if(detailTransferStockMP.getMagasinSouce()!=null)	
					{
						
						if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
						{
							
							  EtatStockMP etatStockMP=new EtatStockMP();
								 
							  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
							  etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
							  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
							  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
							  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
							  etatStockMP.setTransferSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
							  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
							  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
							  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
							  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
							  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
							  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
							  
							  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
							 
							  listEtatStockMP.add(etatStockMP);	
							
						}
							
						}
					}
					}
			 
		
			
			
		}
	  
	  
	  
	  
	  }
  
  
  
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  
  
  
  
  
  
 
  
  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
	 
	  if(etatStockMP.getFournisseurMP()!=null)
	  {
		  System.out.println(etatStockMP.getMp().getCode() +" *** "+etatStockMP.getFournisseurMP().getCodeFournisseur() + "****"+etatStockMP.getQuantiteInitial()+" *** "+etatStockMP.getQuantiteReception()+" *** "+ etatStockMP.getQuantiteRetour()+" *** "+etatStockMP.getTransferEntrer()+" *** "+etatStockMP.getQuantiteResterMachine()+" *** "+etatStockMP.getQuantiteFabriquer() +" ---- "+ etatStockMP.getQuantiteCharger() +" *****" + etatStockMP.getQuantiteChargerSupp()+" ***** "+etatStockMP.getQuantiteAutreSortie() +" ***** "+ etatStockMP.getTransferSortie() +" ***** "+ etatStockMP.getQuantiteExistante());

	  }
	  
	  
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  }

	

}



}

package presentation.parametre;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
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

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTypeSortieDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ServiceDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TypeSortieDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ServiceDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.DetailTypeSortie;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;
import dao.entity.Utilisateur;
import dao.entity.service;

import java.awt.Component;
import javax.swing.JTable;


public class AjouterTypeEtSousTypeBonSortieStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleSousType;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private Map< String, BigDecimal> mapQuantiteMP = new HashMap<>();
	private Map< String, TypeSortie> mapTypeSortie = new HashMap<>();
	private Map< String, DetailTypeSortie> mapDetailTypeSortie = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	private Map< String, FournisseurMP> mapFournisseur = new HashMap<>();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur =new ArrayList<FournisseurMP>();
	private List<TypeSortie> listTypeSortie =new ArrayList<TypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortie =new ArrayList<DetailTypeSortie>();
	private List<DetailTypeSortie> listSousTypeSortieTmp =new ArrayList<DetailTypeSortie>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<MatierePremier> listMatierePremierTmp =new ArrayList<MatierePremier>();
	private List<DetailTransferStockMP> listDetailTransfertMp =new ArrayList<DetailTransferStockMP>();
	
	private TransferStockMP transferStock  = new TransferStockMP();;
	private TypeSortieDAO typeSortieDAO;
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private DetailTypeSortieDAO detailTypeSortieDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	JComboBox comboMP = new JComboBox();
	private JTextField txttype;
	 JComboBox comboType = new JComboBox();
	 JButton btnValiderTransfer = new JButton("Valider Transfer MP");
	 JButton btnmodifier = new JButton();
	 JButton btnsupprimer = new JButton();
	 JLabel lblFournisseur = new JLabel("Fournisseur : ");
	 JComboBox comboFournisseur = new JComboBox();
	
	 private JTextField txtsoustype;
	 private JTable tableSousTypeSortie;
	 private TypeSortie typeSortie=new TypeSortie();
	 JLabel labelsoustype = new JLabel("Sous Type :");
	 JComboBox combosoustype = new JComboBox();
	
	 JButton btnValider = new JButton();
	 private SubCategorieMPDAO categorieMPDAO;
	 private List<Magasin> listMagasinDechet = new ArrayList<Magasin>(); 
	 JComboBox comboService = new JComboBox();
	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 
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
		private DetailTransferMPDAO detailTransferStockMPDAO;
		ServiceDAO serviceDAO;
		private Map< String, service> mapService = new HashMap<>();
		private List<service> listService =new ArrayList<service>();
	 
	 
	 
	 
	 
	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterTypeEtSousTypeBonSortieStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1689, 696);
        try{
        	
        	serviceDAO=new ServiceDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	typeSortieDAO= new TypeSortieDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	detailTypeSortieDAO=new DetailTypeSortieDAOImpl();
        	  detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	categorieMPDAO=new SubCategorieMPAOImpl();
        	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
        	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	
        	listService=serviceDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}


	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgModifier = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		   intialiserTableau();
					       
				  		      
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
					  		      
				  		     
				  		     	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
		 
		 final JLayeredPane layertype = new JLayeredPane();
		 layertype.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layertype.setBounds(59, 11, 977, 514);
		 add(layertype);
		 
		 btnValider = new JButton();
		 btnValider.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(!txttype.getText().equals(""))
		 		{
		 			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText().toUpperCase());
		 			if(typeSortieTmp!=null)
		 			{
		 				JOptionPane.showMessageDialog(null, "Type de Sortie Excistant deja !!!","Erreur",JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}else
		 			{
		 				if(listSousTypeSortie.size()==0)
		 				{
		 					JOptionPane.showMessageDialog(null, "Veuillez entrer les sous type sortie SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
			 				return;
		 				}
		 				
		 			    	typeSortie.setLiblle(txttype.getText().toUpperCase());

		 						 				
		 				typeSortieDAO.add(typeSortie);
		 				
		 				for(int i=0;i<listSousTypeSortie.size();i++)
		 				{
		 					detailTypeSortieDAO.add(listSousTypeSortie.get(i));
		 				}
		 				
		 				JOptionPane.showMessageDialog(null, "Type Sortie Ajouter Avec Succée ","Information",JOptionPane.INFORMATION_MESSAGE);
		 				txttype.setText("");
		 				txtsoustype.setText("");
		 				intialiserTableauSousType();
		 				ChargerTypeSortie();
		 				listSousTypeSortie.clear();
		 				typeSortie=new TypeSortie();
		 			}
		 		}else
		 		{
		 			
		 			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Type Sortie SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
	 				return;
		 		}
		 	
		 	}
		 });
		 btnValider.setText("Valider");
		 btnValider.setBounds(211, 426, 122, 36);
		 layertype.add(btnValider);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBounds(25, 11, 444, 44);
		 layertype.add(layeredPane_1);
		 
		 JLabel lblType_1 = new JLabel("Type :");
		 lblType_1.setBounds(10, 12, 77, 24);
		 layeredPane_1.add(lblType_1);
		 lblType_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		 
		 txttype = new JTextField();
		 txttype.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		
		 		if(e.getKeyCode()==e.VK_ENTER)
	      		{
		 			
		 			
		 			if(!txttype.getText().equals(""))
		 			{
		 				
		 			TypeSortie typeSortie=typeSortieDAO.findByLibelle(txttype.getText());
		 				if(typeSortie!=null)
		 				{
		 					listSousTypeSortie=	detailTypeSortieDAO.listeDetailTypeSortieByTypeSortie(typeSortie);
		 					
		 					afficher_SousType(listSousTypeSortie);
		 					btnValider.setEnabled(false);
		 					
		 				}else
		 				{
		 					JOptionPane.showMessageDialog(null, "Type sortie Introuvable","Information",JOptionPane.INFORMATION_MESSAGE);
		 					listSousTypeSortie.clear();
		 					afficher_SousType(listSousTypeSortie);
		 					btnValider.setEnabled(true);
		 					return;
		 				}
		 			
		 				
		 			}
		 			
		 			
	      		}
		 		
		 			
		 		
		 	}
		 });
		 txttype.setBounds(112, 11, 285, 26);
		 layeredPane_1.add(txttype);
		 txttype.setText("");
		 txttype.setColumns(10);
		 
		 JLayeredPane layeredPane_3 = new JLayeredPane();
		 layeredPane_3.setBounds(25, 70, 444, 54);
		 layertype.add(layeredPane_3);
		 
		 txtsoustype = new JTextField();
		 txtsoustype.setText("");
		 txtsoustype.setColumns(10);
		 txtsoustype.setBounds(115, 11, 282, 26);
		 layeredPane_3.add(txtsoustype);
		 
		 JLabel lblSousType = new JLabel("Sous Type :");
		 lblSousType.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblSousType.setBounds(10, 12, 87, 24);
		 layeredPane_3.add(lblSousType);
		 
		 JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		 scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 scrollPane_1.setBounds(25, 210, 531, 197);
		 layertype.add(scrollPane_1);
		 
		 tableSousTypeSortie = new JTable();
		 tableSousTypeSortie.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		
		 		if(tableSousTypeSortie.getSelectedRow()!=-1)
		 		{
		 			
		 			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
		 			
		 			txtsoustype.setText(detailTypeSortie.getSousType());
		 			
		 			
		 		}
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		 tableSousTypeSortie.setModel(new DefaultTableModel(
		 	new Object[][] {
		 	},
		 	new String[] {
		 		"Sous Type Sortie"
		 	}
		 ));
		 tableSousTypeSortie.setFillsViewportHeight(true);
		 scrollPane_1.setViewportView(tableSousTypeSortie);
		 
		 JButton btnAjouter = new JButton();
		 btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		 btnAjouter.setBounds(25, 160, 152, 26);
		 layertype.add(btnAjouter);
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		if(txttype.getText().equals(""))
		 		{
		 			
		 		JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Type SVP !!!");	
		 			return;
		 			
		 			
		 		}else if(txtsoustype.getText().equals(""))
		 		{
		 			JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Sous Type SVP !!!");	
		 			return;
		 		}else
		 		{
		 			
		 			boolean trouve=false;
		 			for(int i=0;i<listSousTypeSortie.size();i++)
		 			{
		 				
		 				DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(i);
		 				if(detailTypeSortie.getSousType().equals(txtsoustype.getText()))
		 				{
		 					trouve=true;
		 				}
		 				
		 				
		 				
		 			}
		 		
		 			if(trouve==false)
		 			{
		 				TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
		 				
		 				
		 				DetailTypeSortie detailTypeSortieTmp=new DetailTypeSortie();	
		 				detailTypeSortieTmp.setSousType(txtsoustype.getText());
		 				if(typeSortieTmp!=null)
		 				{
		 					detailTypeSortieTmp.setTypesortie(typeSortieTmp);
		 					detailTypeSortieDAO.add(detailTypeSortieTmp);
		 					ChargerTypeSortie();
		 				}else
		 				{
		 					detailTypeSortieTmp.setTypesortie(typeSortie);
		 				}
		 				
		 				listSousTypeSortie.add(detailTypeSortieTmp);		 				
		 				afficher_SousType(listSousTypeSortie);
		 				txtsoustype.setText("");
		 				
		 				
		 				
		 				
		 				
		 			}else
		 			{
		 				JOptionPane.showMessageDialog(null, "Sous Type Sortie Déja Entrer !!!!!");
		 				return;
		 			}
		 			
		 			
		 			
		 			
		 		}
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnAjouter.setText("Ajouter");
		 
		 JButton btnModifier = new JButton();
		 btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		 btnModifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		if(tableSousTypeSortie.getSelectedRow()!=-1)
		 		{
		 			
		 			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
		 			
		 			detailTypeSortie.setSousType(txtsoustype.getText());
		 			
		 			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
		 			if(typeSortieTmp!=null)
		 			{
		 				listSousTypeSortie.set(tableSousTypeSortie.getSelectedRow(), detailTypeSortie);
		 				detailTypeSortieDAO.edit(detailTypeSortie);
		 				ChargerTypeSortie();
		 				
		 			}else
		 			{
		 				listSousTypeSortie.set(tableSousTypeSortie.getSelectedRow(), detailTypeSortie);
		 				
		 			}
		 				
		 			afficher_SousType(listSousTypeSortie);
		 			
		 			
		 		}	
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnModifier.setText("Modifier");
		 btnModifier.setBounds(197, 160, 152, 26);
		 layertype.add(btnModifier);
		 
		 JButton btnSupprimer = new JButton();
		 btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		 btnSupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
try {
	
		
		if(tableSousTypeSortie.getSelectedRow()!=-1)
		{
			
			DetailTypeSortie detailTypeSortie=listSousTypeSortie.get(tableSousTypeSortie.getSelectedRow());
			
			
			
			TypeSortie typeSortieTmp=typeSortieDAO.findByLibelle(txttype.getText());
			if(typeSortieTmp!=null)
			{
				detailTypeSortieDAO.delete(detailTypeSortie.getId());
				listSousTypeSortie.remove(tableSousTypeSortie.getSelectedRow());
				ChargerTypeSortie();
				
			}else
			{
				listSousTypeSortie.remove(tableSousTypeSortie.getSelectedRow());
				
			}
				
			afficher_SousType(listSousTypeSortie);
			
			
		}
	
	
	
} catch (Exception ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
}
			
		 		
		 		
		 		
		 		
		 	
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnSupprimer.setText("Supprimer");
		 btnSupprimer.setBounds(384, 160, 152, 26);
		 layertype.add(btnSupprimer);
		

		for(int k=0;k<listFournisseur.size();k++)
		{
			FournisseurMP fournisseurMP=listFournisseur.get(k);
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		ChargerTypeSortie();	  
		 

	
if (AuthentificationView.utilisateur.getLogin().equals("admin")) {

listDepot =depotDAO.findAll();
comboDepotSource.removeAllItems();
comboDepotSource.addItem("");

for(int d=0;d<listDepot.size();d++)
{

Depot depot=listDepot.get(d);
comboDepotSource.addItem(depot.getLibelle());
mapDepotSource.put(depot.getLibelle(), depot);




}



} else {
Depot depot = depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());

if (depot != null) {
comboDepotSource.removeAllItems();
comboDepotSource.addItem("");
comboDepotSource.addItem(depot.getLibelle());
mapDepotSource.put(depot.getLibelle(), depot);


}
}
for(int t=0;t<listService.size();t++)
{
	
	service service=listService.get(t);
	comboService.addItem(service.getLibelle());
	mapService.put(service.getLibelle(), service);
}
		
	}
	
	
	void ChargerTypeSortie()
	{
		listTypeSortie.clear();
		listTypeSortie=typeSortieDAO.findAll();
		for(int i=0;i<listTypeSortie.size();i++)
		{
			TypeSortie typesortie=listTypeSortie.get(i);
			if(!typesortie.getLiblle().equals(TYPE_SORTIE_RETOUR_FOURNISSEUR))
			{
				comboType.addItem(typesortie.getLiblle());
				mapTypeSortie.put(typesortie.getLiblle(), typesortie);
			}
			
		}
		
	}
	
	
	void intialiserTous()
	{
		
		comboDepotSource.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		//txtRefTransfere.setText("");
		dateTransfereChooser.setCalendar(null);
		
		comboMP.setSelectedItem("");
		intialiserTableau();
		btnmodifier.setEnabled(true);
		btnsupprimer.setEnabled(true);
	 	btnValiderTransfer.setEnabled(true);
	 	comboType.setSelectedIndex(-1);
	 combosoustype.removeAllItems();
		
	}
	
	void intialiser()
	{
	
		
		comboMP.setSelectedIndex(-1);		
		comboFournisseur.setSelectedItem("");		
	}

	




List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<listMatierePremierTmp.size();i++){
		MatierePremier matierePremier =listMatierePremierTmp.get(i);
		quantite=mapQuantiteMP.get(matierePremier.getCode());
		
		
		
		if(quantite.compareTo(BigDecimal.ZERO)>0){
			
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
			Magasin magasinDestination=depotDAO.magasinByCode(CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
			//Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
			
			StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
			StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
			
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		
		
		prixStockDestination=stockMPDestination.getPrixUnitaire();
		prixStockSource=stockMPSource.getPrixUnitaire();
		
		BigDecimal	prixDest=prixStockDestination.multiply(stockMPDestination.getStock());
		BigDecimal	prixStock=prixStockSource.multiply(quantite);
		
		prixMoyen=prixDest.add(prixStock);
		if(sommeStock.compareTo(BigDecimal.ZERO)  >0)
		prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		else 
			prixMoyen=BigDecimal.ZERO;
		//stockMPDestination.setPrixUnitaire(prixMoyen);
		
		stockMPDestination.setStock(stockDestination);
		stockMPSource.setStock(stockSource);
		
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixStockSource);
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
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}


void intialiserTableauSousType(){
	
	
	modeleSousType =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"SousType"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 tableSousTypeSortie.setModel(modeleSousType); 
		 tableSousTypeSortie.getColumnModel().getColumn(0).setPreferredWidth(10);
    
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}


void afficher_SousType(List<DetailTypeSortie> listDetailTypeSortieMP)
{

	
	intialiserTableauSousType();

        
	  int i=0;
		while(i<listDetailTypeSortieMP.size())
		{	
			
			DetailTypeSortie DetailTypeSortieMP=listDetailTypeSortieMP.get(i);
	
			Object []ligne={DetailTypeSortieMP.getSousType()};
			modeleSousType.addRow(ligne);
		
			

		
			
			i++;
		}
		tableSousTypeSortie.setModel(modeleSousType); 
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

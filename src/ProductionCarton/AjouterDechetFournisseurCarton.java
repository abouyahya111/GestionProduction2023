package ProductionCarton;

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
import dao.daoImplManager.DetailManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurCartonDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurCartonDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurCartonDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.DetailTransferStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.ManqueDechetFournisseurCarton;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.TransferStockMP;

import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjouterDechetFournisseurCarton extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable tableCoutMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable tableDechetFournisseur;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ProductionMPDAO productionMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	private List<ProductionMP> listProductionMP=new ArrayList<ProductionMP>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurAdhesive> listFournisseurAdhesive=new ArrayList<FournisseurAdhesive>();
	private List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseurCarton=new ArrayList<DetailManqueDechetFournisseurCarton>();
	private List<ManqueDechetFournisseurCarton> listManqueDechetFournisseurCarton=new ArrayList<ManqueDechetFournisseurCarton>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurAdhesive> mapfournisseurAdhesive = new HashMap<>();
	
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, ProductionMP> mapProduction = new HashMap<>();
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
	private JTextField txtmanque;
	private JTextField txtdechet;
	private  JComboBox combomp = new JComboBox();
	private JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurCartonDAO manqueDechetFournisseurCartonDAO;
	private DetailManqueDechetFournisseurCartonDAO detailManqueDechetFournisseurCartonDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseurCarton newmanqueDechetFournisseurCarton;
	private JTextField txtmanquetotal;
	private JTextField txtdechettotal;
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private DepotDAO depotDAO;
	JComboBox comboMagasinDechet = new JComboBox();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	 private JTextField txtplus;
	 private JTextField txtplustotal;
	 private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
		private TransferStockMPDAO transferStockMPDAO;
		private DetailTransferMPDAO detailTransferMPDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterDechetFournisseurCarton() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1421, 825);
        try{
        	
        	
        	
        	productionMPDAO=new ProductionMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
        	manqueDechetFournisseurCartonDAO=new ManqueDechetFournisseurCartonDAOImpl();
        	detailManqueDechetFournisseurCartonDAO=new DetailManqueDechetFournisseurCartonDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	 transferStockMPDAO = new TransferStockMPDAOImpl();
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
				  		     tableCoutMP = new JXTable();
				  		     tableCoutMP.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {


				  		    		if(tableCoutMP.getSelectedRow()!=-1)
				  		     		{
				  		     			
				  		     	CoutProdMP coutMP=listCoutProdMP.get(tableCoutMP.getSelectedRow());
				  		     			
				  		     	combomp.setSelectedItem(coutMP.getMatierePremier().getNom());
				  		     
				  		  		  		     	
				  		     						  		     				CalculerQuantite();
				  		     						  		     				Vider();
				  		     	
				  		     	}
				  		     	
				  		     	
				  		     	}
				  		     });
				  		     tableCoutMP.setShowVerticalLines(false);
				  		     tableCoutMP.setSelectionBackground(new Color(51, 204, 255));
				  		     tableCoutMP.setRowHeightEnabled(true);
				  		     tableCoutMP.setBackground(new Color(255, 255, 255));
				  		     tableCoutMP.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     tableCoutMP.setColumnControlVisible(true);
				  		     tableCoutMP.setForeground(Color.BLACK);
				  		     tableCoutMP.setGridColor(new Color(0, 0, 255));
				  		     tableCoutMP.setAutoCreateRowSorter(true);
				  		     tableCoutMP.setBounds(2, 27, 411, 198);
				  		     tableCoutMP.setRowHeight(20);
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code MP","Matiere Premiere","Manque","Dechet","Plus"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 tableCoutMP.setModel(modeleMP); 
					  		 tableCoutMP.getColumnModel().getColumn(0).setPreferredWidth(60);
					         tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(160);
					         tableCoutMP.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableCoutMP);
				  		     	scrollPane.setBounds(9, 108, 1367, 200);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	

				  		     	
				  		     		tableDechetFournisseur = new JXTable();
				  		     		tableDechetFournisseur.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableDechetFournisseur.getSelectedRow()!=-1)
				  		     				{
				  		     					
				  		     					combomp.setSelectedItem(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0));
				  		     					combofournisseur.setSelectedItem(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),1));
				  		     					txtmanque.setText(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),2).toString());
				  		     					
				  		     					txtdechet.setText(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),3).toString());
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
				  			     			"Matière Première","Fournisseur", "Quantité Manque","Quantite Dechet","Quantite Plus"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
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
				  				scrollPane_1.setBounds(9, 433, 1367, 326);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 77, 1279, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1279, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date Dechet:");
				  		     	lblDateDebut.setBounds(324, 18, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(631, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(txtNumOF.getSelectedIndex()!=-1)
				{
					
					ProductionMP productionMP=mapProduction.get(txtNumOF.getSelectedItem());
					
					if(productionMP!=null)
					{
						listCoutProdMP=productionMPDAO.listeCoutProdMPDechetManqueByProduction(productionMP.getId());
						
						ManqueDechetFournisseurCarton manqueDechetFournisseurTmp=manqueDechetFournisseurCartonDAO.findByCode(txtNumOF.getSelectedItem().toString());
						if(manqueDechetFournisseurTmp!=null)
						{
							newmanqueDechetFournisseurCarton=manqueDechetFournisseurTmp;
						}else
						{
							newmanqueDechetFournisseurCarton=new ManqueDechetFournisseurCarton();
						}
						
						afficher_tableMP(listCoutProdMP);
						listDetailManqueDechetFournisseurCarton.clear();
						intialiserTableau2();
						txtmanquetotal.setText("");
						txtdechettotal.setText("");
					}
					
				}

			
			
			}
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		
		
		JLabel label = new JLabel("Num\u00E9ro OF");
		label.setBounds(10, 18, 89, 24);
		layeredPane.add(label);
		
		 txtNumOF = new JComboBox();
		txtNumOF.setSelectedIndex(-1);
		txtNumOF.setBounds(76, 18, 211, 24);
		  AutoCompleteDecorator.decorate(txtNumOF); 
		layeredPane.add(txtNumOF);
	ChargerOF();
		 
		  dateChooserdechet = new JDateChooser();
		 dateChooserdechet.setLocale(Locale.FRANCE);
		 dateChooserdechet.setDateFormatString("dd/MM/yyyy");
		 dateChooserdechet.setBounds(397, 16, 155, 26);
		 layeredPane.add(dateChooserdechet);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_1.setBounds(9, 319, 1367, 54);
		 add(layeredPane_1);
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblFournisseur.setBounds(648, 13, 74, 24);
		 layeredPane_1.add(lblFournisseur);
		 
		 JLabel lblMp = new JLabel("  MP :");
		 lblMp.setBounds(315, 13, 44, 24);
		 layeredPane_1.add(lblMp);
		 
		  combomp = new JComboBox();
		  combomp.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent arg0) {
}
		  });
		 combomp.setSelectedIndex(-1);
		 combomp.setBounds(356, 13, 282, 24);
		 layeredPane_1.add(combomp);
		 
		  combofournisseur = new JComboBox();
		 combofournisseur.setSelectedIndex(-1);
		 combofournisseur.setBounds(715, 15, 153, 24);
		 layeredPane_1.add(combofournisseur);
		 
		 txtmanque = new JTextField();
		 txtmanque.setColumns(10);
		 txtmanque.setBounds(934, 13, 97, 26);
		 layeredPane_1.add(txtmanque);
		 
		 JLabel lblManque = new JLabel("Manque:");
		 lblManque.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblManque.setBounds(878, 14, 74, 24);
		 layeredPane_1.add(lblManque);
		 
		 txtdechet = new JTextField();
		 txtdechet.setColumns(10);
		 txtdechet.setBounds(1097, 11, 102, 26);
		 layeredPane_1.add(txtdechet);
		 
		 JLabel lblDechet = new JLabel("Dechet :");
		 lblDechet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblDechet.setBounds(1041, 12, 74, 24);
		 layeredPane_1.add(lblDechet);
		 
		 JButton button = new JButton();
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

					
					try {
			 			
			 	 		MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
			 	 		FournisseurAdhesive fournisseurAdhesive=mapfournisseurAdhesive.get(combofournisseur.getSelectedItem());
	                    Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				 		
			 	 		if(magasinDechet==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
				 			return;
			 	 		}
				 		if(matierePremier==null)
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez Selection Mp Dans la Liste des couts MP !!!!");
				 			return;
				 		}else if(txtdechet.getText().equals("") && txtmanque.getText().equals(""))
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez entrer la manque ou dechet SVP !!!!");
				 			return;
				 		}else if(fournisseurAdhesive==null)
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez entrer la fournisseur  SVP !!!!");
				 			return;
				 		}
				 		{

				 			CoutProdMP coutMP=null ;
				 			for(int i=0;i<listCoutProdMP.size();i++)
				 				{
	if(listCoutProdMP.get(i).getMatierePremier().getId()==matierePremier.getId())	
		{
		coutMP=listCoutProdMP.get(i);
		
		 
		
		}
		}
				 			
				 			if(!txtdechet.getText().equals("") && new BigDecimal(txtdechet.getText()).compareTo(BigDecimal.ZERO)!=0)	
					 		{
					 			
					 				
					 				if(coutMP.getCodeFournisseur()==null)
						 			{
						 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
						 				return;
						 			}
						 			
						 			if(coutMP.getCodeFournisseur().contains(","))
							 		{
							 			
							 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
							 			boolean existe=false;
							 			for(int i=0;i<listFournisseur.length;i++)
							 			{
							 				
							 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
							 				{
							 					existe=true;
							 				}
							 				
							 				
							 			}
							 			
							 			if(existe==false)
							 			{
							 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 			
							 			
							 			
							 		}else
							 		{
							 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
							 			{
							 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 		}
					 				
					 				 	
					 			
					 		}
				 			
				 			if(!txtmanque.getText().equals("") && new BigDecimal(txtmanque.getText()).compareTo(BigDecimal.ZERO)!=0)	
					 		{
					 			
					 				
					 				if(coutMP.getCodeFournisseur()==null)
						 			{
						 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
						 				return;
						 			}
						 			
						 			if(coutMP.getCodeFournisseur().contains(","))
							 		{
							 			
							 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
							 			boolean existe=false;
							 			for(int i=0;i<listFournisseur.length;i++)
							 			{
							 				
							 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
							 				{
							 					existe=true;
							 				}
							 				
							 				
							 			}
							 			
							 			if(existe==false)
							 			{
							 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 			
							 			
							 			
							 		}else
							 		{
							 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
							 			{
							 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 		}
					 				
					 				 	
					 			
					 		}
				 			
				 			if(!txtplus.getText().equals("") && new BigDecimal(txtplus.getText()).compareTo(BigDecimal.ZERO)!=0)	
					 		{
					 			
					 				
					 				if(coutMP.getCodeFournisseur()==null)
						 			{
						 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
						 				return;
						 			}
						 			
						 			if(coutMP.getCodeFournisseur().contains(","))
							 		{
							 			
							 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
							 			boolean existe=false;
							 			for(int i=0;i<listFournisseur.length;i++)
							 			{
							 				
							 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
							 				{
							 					existe=true;
							 				}
							 				
							 				
							 			}
							 			
							 			if(existe==false)
							 			{
							 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 			
							 			
							 			
							 		}else
							 		{
							 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
							 			{
							 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
							 				return;
							 			}
							 		}
					 				
					 				 	
					 			
					 		}
				 			
				 			
				 			ManqueDechetFournisseurCarton manqueDechetFournisseur=manqueDechetFournisseurCartonDAO.findByCode(txtNumOF.getSelectedItem().toString());
				 			
				 		if(manqueDechetFournisseur!=null)
				 		{
				 		boolean existe=false;	
				 		BigDecimal quantitedechet=BigDecimal.ZERO;
				 		BigDecimal quantitemanque=BigDecimal.ZERO;
				 		BigDecimal quantiteplus=BigDecimal.ZERO;
				 			listDetailManqueDechetFournisseurCarton=detailManqueDechetFournisseurCartonDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
				 			for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
				 			{
				 				
				 				if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()==fournisseurAdhesive.getId())
				 				{
				 					
				 					existe=true;
				 					
				 					
				 					
				 				}else if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()!=fournisseurAdhesive.getId())
				 				{
				 					
				 					quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
				 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
				 					quantiteplus=quantiteplus.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus());
				 				}
				 					
				 				
				 				
				 			}
				 			
				 			if(existe==true)
				 			{
				 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
				 				return;
				 				
				 			}else
				 			{
				 				DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=new DetailManqueDechetFournisseurCarton();
				 				
				 				if(!txtdechet.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(coutMP.getQuantDechetFournisseur())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Dechet est supperieur au sommes des Quantite Dechet fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);

				 				}
				 				
				 				if(!txtmanque.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtmanque.getText()).add(quantitemanque).compareTo(coutMP.getQuantiteManquante())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite manqante est supperieur au sommes des Quantite manqante fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
		
				 				}
				 				
				 				
				 				if(!txtplus.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtplus.getText()).add(quantiteplus).compareTo(coutMP.getQuantiteManquanteFrPlus())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantitePlus(new BigDecimal(txtmanque.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Plus est supperieur au sommes des Quantite manqante fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantitePlus (BigDecimal.ZERO);
		
				 				}
				 				
				 				
				 			 
				 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
				 				detailManqueDechetFournisseur.setFourniseurAdhesive(fournisseurAdhesive);
				 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
				 				detailManqueDechetFournisseur.setManquedechetfournisseurcarton (manqueDechetFournisseur);
				 				detailManqueDechetFournisseurCartonDAO.add(detailManqueDechetFournisseur);
				 		
				 				CalculerQuantite();
				 				Vider();
				 			}
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 		}else
				 		{
				 			boolean existe=false;
				 			BigDecimal quantitedechet=BigDecimal.ZERO;
					 		BigDecimal quantitemanque=BigDecimal.ZERO;
					 		BigDecimal quantitePlus=BigDecimal.ZERO;
					 		
							for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
				 			{
				 				
				 				if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()==fournisseurAdhesive.getId())
				 				{
				 					
				 					existe=true;
				 					
				 					
				 					
				 				}else if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()!=fournisseurAdhesive.getId())
				 				{
				 					
				 					quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
				 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
				 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus());
				 				}
				 					
				 				
				 				
				 			}
				 			
							if(existe==true)
				 			{
				 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
				 				return;
				 				
				 			}else
				 			{
				 				
				 				DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=new DetailManqueDechetFournisseurCarton();
				 				
				 				if(!txtdechet.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(coutMP.getQuantDechetFournisseur())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Dechet est supperieur au sommes des Quantite Dechet fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);

				 				}
				 				
				 				if(!txtmanque.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtmanque.getText()).add(quantitemanque).compareTo(coutMP.getQuantiteManquante())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite manquante est supperieur au sommes des Quantite manqante fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);

				 				}
				 				
				 				
				 				if(!txtplus.getText().equals(""))
				 				{
				 					if(new BigDecimal(txtplus.getText()).add(quantitePlus).compareTo(coutMP.getQuantiteManquanteFrPlus())<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantitePlus(new BigDecimal(txtplus.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Plus est supperieur au sommes des Quantite Plus fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantitePlus(BigDecimal.ZERO);

				 				}
				 				
				 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
				 				detailManqueDechetFournisseur.setFourniseurAdhesive(fournisseurAdhesive);
				 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
				 				detailManqueDechetFournisseur.setManquedechetfournisseurcarton(newmanqueDechetFournisseurCarton);
	                            listDetailManqueDechetFournisseurCarton.add(detailManqueDechetFournisseur)	;		 				
	                            CalculerQuantite();
				 				Vider();

				 			}
				 			
				 			
				 			
				 		}
				 		
				 		
				 		}
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "dechet ou manque doit etre en chiffre SVP !!!!!");
					}
			
					
					
				
				
			
		 		
		 		
}
		 });
		 button.setIcon(imgAjouter);
		 button.setBounds(388, 389, 113, 31);
		 add(button);
		 button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		listDetailTransfertStockMP.clear();
		 		ProductionMP productionMP=mapProduction.get(txtNumOF.getSelectedItem());
		 		ManqueDechetFournisseurCarton manqueDechetFournisseur=manqueDechetFournisseurCartonDAO.findByCode(productionMP.getNumOFMP());
		 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		 		BigDecimal quantitedechet=BigDecimal.ZERO;
		 		BigDecimal quantitemanque=BigDecimal.ZERO;
		 		BigDecimal quantiteplus=BigDecimal.ZERO;
		 		boolean trouve=false;
		 		if(manqueDechetFournisseur!=null)
		 		{
		 			manqueDechetFournisseur.setDateModification(new Date());
		 			listDetailManqueDechetFournisseurCarton=detailManqueDechetFournisseurCartonDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
		 			
		 			for(int i=0;i<listCoutProdMP.size();i++)
		 			{
		 				quantitedechet=BigDecimal.ZERO;
		 				quantitemanque=BigDecimal.ZERO;
		 				quantiteplus=BigDecimal.ZERO;
		 				
		 			CoutProdMP coutMP=	listCoutProdMP.get(i);
		 				for(int j=0;j<listDetailManqueDechetFournisseurCarton.size();j++)
		 				{
		 					if(listDetailManqueDechetFournisseurCarton.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
		 					{
		 						quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantiteDechet());
		 						quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantiteManque());
		 						quantiteplus=quantiteplus.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantitePlus());
		 						
		 						
		 					}
		 					
		 					
		 				}
		 				
		 				if(coutMP.getQuantDechetFournisseur().compareTo(quantitedechet)!=0 || coutMP.getQuantiteManquante().compareTo(quantitemanque)!=0 || coutMP.getQuantiteManquanteFrPlus().compareTo(quantiteplus)!=0)
		 				{
		 					
		 					trouve=true;
		 					break;
		 					
		 					
		 					
		 				}
		 				
		 				
		 				
		 				
		 			}
		 			
		 			if(trouve==false)
		 			{
		 				
		 				manqueDechetFournisseur.setEtat(Constantes.ETAT_VALIDER);
		 				
		 				
		 			}else
		 			{
		 				
		 				JOptionPane.showMessageDialog (null," Somme Total des Quantités Dechet , Manque et Plus entrer doit etre egale La Somme Des Quantite Dechet , manque et Plus de la Production SVP !!!! ", "Error",JOptionPane.ERROR_MESSAGE);
		 				return;
		 				
		 			}
		 			
		 			
		 			manqueDechetFournisseurCartonDAO.edit(manqueDechetFournisseur);
		 			
		 			TransferStockMP transferStockMPTmp = new TransferStockMP();
		 			transferStockMPTmp.setCodeTransfer(txtNumOF.getSelectedItem().toString());
		 			transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
		 			transferStockMPTmp.setDate(new Date());
		 			transferStockMPTmp.setDateTransfer(dateChooserdechet.getDate());
		 			transferStockMPTmp.setDepot(magasinDechet.getDepot());
		 			//transferStockMPTmp.setListDetailTransferMP(listDetailTransfertStockMP);
		 			transferStockMPTmp.setStatut(Constantes.TYPE_DECHET_FOURNISSEUR);
		 			
		 			for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
		 			{
		 				
		 				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		 				
		 				detailTransferStockMP.setMagasinDestination(magasinDechet);
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive()!=null)
		 				{
		 					detailTransferStockMP.setFournisseurAdhesive (listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive());
		 					
		 				}
		 				
		 				detailTransferStockMP.setMatierePremier(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier());
		 				detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
		 				detailTransferStockMP.setPrixUnitaire(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getPrix());
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet()!=null)
		 				{
		 					detailTransferStockMP.setQuantiteDechet(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
		 				}
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque()!=null)
		 				{
		 					detailTransferStockMP.setQuantiteManque(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
		 				}
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus()!=null)
		 				{
		 					detailTransferStockMP.setQuantitePlus (listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
		 				}
		 				
		 				
		 				listDetailTransfertStockMP.add(detailTransferStockMP);	
		 				
		 			}
		 			
		 			if(listDetailTransfertStockMP.size()!=0)
		 			{
		 				
		 		 transferStockMPDAO.add(transferStockMPTmp);
		 				
		 			for(int j=0;j<listDetailTransfertStockMP.size();j++)
		 			{
		 				detailTransferMPDAO.add(listDetailTransfertStockMP.get(j));
		 			}
		 			}
		 			
		 			JOptionPane.showMessageDialog(null, "Manque Dechet Fournisseur Valider");
		 			listDetailManqueDechetFournisseurCarton.clear();
		 			listDetailTransfertStockMP.clear();
		 			intialiserTableau();
		 			afficher_tableMP_Total(listDetailManqueDechetFournisseurCarton);
		 			ChargerOF();
		 			
		 			
		 		}else
		 		{
		 			
		 			newmanqueDechetFournisseurCarton.setDateCreation(new Date());
		 			newmanqueDechetFournisseurCarton.setDateDechet(dateChooserdechet.getDate());
		 			newmanqueDechetFournisseurCarton.setNumOF(txtNumOF.getSelectedItem().toString());
		 			newmanqueDechetFournisseurCarton.setDetailManqueDechetFournisseur(listDetailManqueDechetFournisseurCarton);
		 			
		 			for(int i=0;i<listCoutProdMP.size();i++)
		 			{
		 				quantitedechet=BigDecimal.ZERO;
		 				quantitemanque=BigDecimal.ZERO;
		 				quantiteplus=BigDecimal.ZERO;
		 				
		 			CoutProdMP coutMP=	listCoutProdMP.get(i);
		 				for(int j=0;j<listDetailManqueDechetFournisseurCarton.size();j++)
		 				{
		 					if(listDetailManqueDechetFournisseurCarton.get(j).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
		 					{
		 						quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantiteDechet());
		 						quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantiteManque());
		 						quantiteplus=quantiteplus.add(listDetailManqueDechetFournisseurCarton.get(j).getQuantitePlus());
		 						
		 						
		 					}
		 					
		 					
		 				}
		 				
		 				if(coutMP.getQuantDechetFournisseur().compareTo(quantitedechet)!=0 || coutMP.getQuantiteManquante().compareTo(quantitemanque)!=0 || coutMP.getQuantiteManquanteFrPlus().compareTo(quantiteplus)!=0)
		 				{
		 					
		 					trouve=true;
		 					break;
		 					
		 					
		 					
		 				}
		 				
		 				
		 				
		 				
		 			}
		 			
		 			if(trouve==false)
		 			{
		 				newmanqueDechetFournisseurCarton.setEtat(Constantes.ETAT_VALIDER);
		 				
		 			}else
		 			{
		 				JOptionPane.showMessageDialog (null," Somme Total des Quantités Dechet , Manque et Plus entrer doit etre egale La Somme Des Quantite Dechet , manque et Plus de la Production SVP !!!! ", "Error",JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 			
		 			 
		 			
		 			manqueDechetFournisseurCartonDAO.add(newmanqueDechetFournisseurCarton);
		 			
		 			TransferStockMP transferStockMPTmp = new TransferStockMP();
		 			transferStockMPTmp.setCodeTransfer(txtNumOF.getSelectedItem().toString());
		 			transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
		 			transferStockMPTmp.setDate(new Date());
		 			transferStockMPTmp.setDateTransfer(dateChooserdechet.getDate());
		 			transferStockMPTmp.setDepot(magasinDechet.getDepot());
		 			//transferStockMPTmp.setListDetailTransferMP(listDetailTransfertStockMP);
		 			transferStockMPTmp.setStatut(Constantes.TYPE_DECHET_FOURNISSEUR);
		 			
		 			for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
		 			{
		 				
		 				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		 				
		 				detailTransferStockMP.setMagasinDestination(magasinDechet);
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive()!=null)
		 				{
		 					detailTransferStockMP.setFournisseurAdhesive (listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive());
		 					
		 				}
		 				
		 				detailTransferStockMP.setMatierePremier(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier());
		 				detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
		 				detailTransferStockMP.setPrixUnitaire(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getPrix());
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet()!=null)
		 				{
		 					detailTransferStockMP.setQuantiteDechet(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
		 				}
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque()!=null)
		 				{
		 					detailTransferStockMP.setQuantiteManque(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
		 				}
		 				
		 				if(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus()!=null)
		 				{
		 					detailTransferStockMP.setQuantitePlus (listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus());
		 				}else
		 				{
		 					detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
		 				}
		 				
		 				
		 				listDetailTransfertStockMP.add(detailTransferStockMP);	
		 				
		 			}
		 			
		 			if(listDetailTransfertStockMP.size()!=0)
		 			{
		 				
		 		 transferStockMPDAO.add(transferStockMPTmp);
		 				
		 			for(int j=0;j<listDetailTransfertStockMP.size();j++)
		 			{
		 				detailTransferMPDAO.add(listDetailTransfertStockMP.get(j));
		 			}
		 			}
		 			
		 			  
		 			JOptionPane.showMessageDialog(null, "Manque Dechet Fournisseur Valider");
		 			listDetailManqueDechetFournisseurCarton.clear();
		 			listDetailTransfertStockMP.clear();
		 			intialiserTableau();
		 			afficher_tableMP_Total(listDetailManqueDechetFournisseurCarton);
		 			ChargerOF();
		 			
		 		}
		 		
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgValider);
		 button_1.setBounds(458, 770, 104, 31);
		 add(button_1);
		 
		 txtmanquetotal = new JTextField();
		 txtmanquetotal.setColumns(10);
		 txtmanquetotal.setBounds(827, 771, 153, 26);
		 add(txtmanquetotal);
		 
		 txtdechettotal = new JTextField();
		 txtdechettotal.setColumns(10);
		 txtdechettotal.setBounds(1014, 770, 153, 26);
		 add(txtdechettotal);
				listMatierePremiere=matierePremiereDAO.findAll();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					combomp.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				combomp.setSelectedIndex(-1);
				
				JLabel label_1 = new JLabel("Magasin Dechet :");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				label_1.setBounds(10, 13, 97, 24);
				layeredPane_1.add(label_1);
				
				 comboMagasinDechet = new JComboBox();
				comboMagasinDechet.setBounds(117, 16, 188, 24);
				layeredPane_1.add(comboMagasinDechet);
				
				JButton btnModifier = new JButton();
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
if(tableDechetFournisseur.getSelectedRow()!=-1)
{
	

		

	
	try {
			
	 		MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
	 		FournisseurAdhesive fournisseurAdhesive=mapfournisseurAdhesive.get(combofournisseur.getSelectedItem());
	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
	 		
 	 		if(magasinDechet==null)
 	 		{
 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
	 			return;
 	 		}
 	 		
 		if(matierePremier==null)
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez Selection Mp Dans la Liste des couts MP !!!!");
 			return;
 		}else if(txtdechet.getText().equals("") && txtmanque.getText().equals(""))
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez entrer la manque ou dechet SVP !!!!");
 			return;
 		}else if(fournisseurAdhesive==null)
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez entrer la fournisseur  SVP !!!!");
 			return;
 		}
 		{

 			CoutProdMP coutMP=null ;
 			for(int i=0;i<listCoutProdMP.size();i++)
 				{
if(listCoutProdMP.get(i).getMatierePremier().getId()==matierePremier.getId())	
{
coutMP=listCoutProdMP.get(i);
}
}
 			
 			
 			
 			if(!txtdechet.getText().equals("") && new BigDecimal(txtdechet.getText()).compareTo(BigDecimal.ZERO)!=0)	
	 		{
	 			
	 				
	 				if(coutMP.getCodeFournisseur()==null)
		 			{
		 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 			
		 			if(coutMP.getCodeFournisseur().contains(","))
			 		{
			 			
			 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
			 			boolean existe=false;
			 			for(int i=0;i<listFournisseur.length;i++)
			 			{
			 				
			 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
			 				{
			 					existe=true;
			 				}
			 				
			 				
			 			}
			 			
			 			if(existe==false)
			 			{
			 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 			
			 			
			 			
			 		}else
			 		{
			 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
			 			{
			 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 		}
	 				
	 				 	
	 			
	 		}
 			
 			if(!txtmanque.getText().equals("") && new BigDecimal(txtmanque.getText()).compareTo(BigDecimal.ZERO)!=0)	
	 		{
	 			
	 				
	 				if(coutMP.getCodeFournisseur()==null)
		 			{
		 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 			
		 			if(coutMP.getCodeFournisseur().contains(","))
			 		{
			 			
			 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
			 			boolean existe=false;
			 			for(int i=0;i<listFournisseur.length;i++)
			 			{
			 				
			 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
			 				{
			 					existe=true;
			 				}
			 				
			 				
			 			}
			 			
			 			if(existe==false)
			 			{
			 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 			
			 			
			 			
			 		}else
			 		{
			 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
			 			{
			 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 		}
	 				
	 				 	
	 			
	 		}
 			
 			if(!txtplus.getText().equals("") && new BigDecimal(txtplus.getText()).compareTo(BigDecimal.ZERO)!=0)	
	 		{
	 			
	 				
	 				if(coutMP.getCodeFournisseur()==null)
		 			{
		 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour cette MP !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 			
		 			if(coutMP.getCodeFournisseur().contains(","))
			 		{
			 			
			 			String [] listFournisseur=coutMP.getCodeFournisseur().split(",");
			 			boolean existe=false;
			 			for(int i=0;i<listFournisseur.length;i++)
			 			{
			 				
			 				if(listFournisseur[i].trim().toString().equals(fournisseurAdhesive.getCodeFournisseur().trim().toString()))
			 				{
			 					existe=true;
			 				}
			 				
			 				
			 			}
			 			
			 			if(existe==false)
			 			{
			 				JOptionPane.showMessageDialog(null,"Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 			
			 			
			 			
			 		}else
			 		{
			 			if(!coutMP.getCodeFournisseur().equals(fournisseurAdhesive.getCodeFournisseur()))
			 			{
			 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
			 				return;
			 			}
			 		}
	 				
	 				 	
	 			
	 		}		
 			
 			
 			
 			
 			DetailManqueDechetFournisseurCarton detailManqueDechetFournisseurTmp=listDetailManqueDechetFournisseurCarton.get(tableDechetFournisseur.getSelectedRow());
 			ManqueDechetFournisseurCarton manqueDechetFournisseur=manqueDechetFournisseurCartonDAO.findByCode(detailManqueDechetFournisseurTmp.getManquedechetfournisseurcarton().getNumOF());
 			
 		if(manqueDechetFournisseur!=null)
 		{
 		boolean existe=false;	
 		BigDecimal quantitedechet=BigDecimal.ZERO;
 		BigDecimal quantitemanque=BigDecimal.ZERO;
 		BigDecimal quantiteplus=BigDecimal.ZERO;
 		
 		
 			listDetailManqueDechetFournisseurCarton=detailManqueDechetFournisseurCartonDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
 			for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()==fournisseurAdhesive.getId())
 	 				{
 	 					
 	 					existe=true;
 	 					
 	 					
 	 					
 	 				}else if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()!=fournisseurAdhesive.getId())
 	 				{
 	 					
 	 					quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
 	 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
 	 					quantiteplus=quantiteplus.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
 	 				}
 				}
 			
 					
 				
 				
 			}
 			
 			if(existe==true)
 			{
 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
 				return;
 				
 			}else
 			{
 				DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=listDetailManqueDechetFournisseurCarton.get(tableDechetFournisseur.getSelectedRow());
 				
 				if(!txtdechet.getText().equals(""))
 				{
 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(coutMP.getQuantDechetFournisseur())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Dechet est supperieur au sommes des Quantite Dechet fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);

 				}
 				
 				if(!txtmanque.getText().equals(""))
 				{
 					if(new BigDecimal(txtmanque.getText()).add(quantitemanque).compareTo(coutMP.getQuantiteManquante())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite manqante est supperieur au sommes des Quantite manqante fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);

 				}
 				
 				if(!txtplus.getText().equals(""))
 				{
 					if(new BigDecimal(txtplus.getText()).add(quantiteplus).compareTo(coutMP.getQuantiteManquanteFrPlus())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantitePlus (new BigDecimal(txtplus.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Plus est supperieur au sommes des Quantite manqante fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantitePlus(BigDecimal.ZERO);

 				}
 				
 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
 				detailManqueDechetFournisseur.setFourniseurAdhesive (fournisseurAdhesive);
 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
 				
 				detailManqueDechetFournisseurCartonDAO.edit(detailManqueDechetFournisseur);
 		
 				CalculerQuantite();
 				Vider();
 			}
 			
 			
 			
 			
 			
 			
 			
 			
 			
 		}else
 		{
 			boolean existe=false;
 			BigDecimal quantitedechet=BigDecimal.ZERO;
	 		BigDecimal quantitemanque=BigDecimal.ZERO;
	 		BigDecimal quantitePlus=BigDecimal.ZERO;
			for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()==fournisseurAdhesive.getId())
 	 				{
 	 					
 	 					existe=true;
 	 					
 	 					
 	 					
 	 				}else if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseurCarton.get(i).getFourniseurAdhesive().getId()!=fournisseurAdhesive.getId())
 	 				{
 	 					
 	 					quantitedechet=quantitedechet.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet());
 	 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque());
 	 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus());
 	 				}
 				}
 			
 					
 				
 			}
 			
			if(existe==true)
 			{
 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
 				return;
 				
 			}else
 			{
 				DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=listDetailManqueDechetFournisseurCarton.get(tableDechetFournisseur.getSelectedRow());
 				
 				if(!txtdechet.getText().equals(""))
 				{
 					if(new BigDecimal(txtdechet.getText()).add(quantitedechet).compareTo(coutMP.getQuantDechetFournisseur())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteDechet(new BigDecimal(txtdechet.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Dechet est supperieur au sommes des Quantite Dechet fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);

 				}
 				
 				if(!txtmanque.getText().equals(""))
 				{
 					if(new BigDecimal(txtmanque.getText()).add(quantitemanque).compareTo(coutMP.getQuantiteManquante())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite manqante est supperieur au sommes des Quantite manqante fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);

 				}
 				
 				
 				if(!txtplus.getText().equals(""))
 				{
 					if(new BigDecimal(txtplus.getText()).add(quantitePlus).compareTo(coutMP.getQuantiteManquanteFrPlus())<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantitePlus(new BigDecimal(txtplus.getText()));
 						
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Plus est supperieur au sommes des Quantite manqante fournisseur !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantitePlus(BigDecimal.ZERO);

 				}
 				
 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
 				detailManqueDechetFournisseur.setFourniseurAdhesive(fournisseurAdhesive);
 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
 				
                listDetailManqueDechetFournisseurCarton.set(tableDechetFournisseur.getSelectedRow(),detailManqueDechetFournisseur)	;		 				
                CalculerQuantite();
 				Vider();

 			}
 			
 			
 			
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
				btnModifier.setBounds(540, 389, 113, 31);
			
				add(btnModifier);
				
				JButton btnSupprimer = new JButton("Supprimer");
				btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableDechetFournisseur.getSelectedRow()!=-1)
						{
							MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem().toString());
							
						DetailManqueDechetFournisseurCarton detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurCarton.get(tableDechetFournisseur.getSelectedRow());
						ManqueDechetFournisseurCarton manqueDechetFournisseur=manqueDechetFournisseurCartonDAO.findById(detailManqueDechetFournisseur.getManquedechetfournisseurcarton().getId());
						if(manqueDechetFournisseur!=null)
						{
							detailManqueDechetFournisseurCartonDAO.delete(detailManqueDechetFournisseur.getId());
							listDetailManqueDechetFournisseurCarton=detailManqueDechetFournisseurCartonDAO.findByIdMPByManqueDechetFournisseur(manqueDechetFournisseur.getId(), matierePremier.getId());
							afficher_tableMP_Total(listDetailManqueDechetFournisseurCarton);
							CalculerQuantite();
							Vider();
						}else
						{
							listDetailManqueDechetFournisseurCarton.remove(tableDechetFournisseur.getSelectedRow());
							afficher_tableMP_Total(listDetailManqueDechetFournisseurCarton);
							CalculerQuantite();
							Vider();
							
						}
							
							
							
							
							
							
						}
						
						
						
					}
				});
				btnSupprimer.setBounds(681, 390, 113, 30);
				add(btnSupprimer);
				
				listFournisseurAdhesive=fournisseurAdhesiveDAO.findAll();
				for(int j=0;j<listFournisseurAdhesive.size();j++)
				{
					FournisseurAdhesive fournisseurAdhesive=listFournisseurAdhesive.get(j);
					combofournisseur.addItem(fournisseurAdhesive.getCodeFournisseur());
					mapfournisseurAdhesive.put(fournisseurAdhesive.getCodeFournisseur(), fournisseurAdhesive);
					
				}
				Depot depot=depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());
				 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
				
				
				comboMagasinDechet.addItem(""); 		      
				
				JLabel lblPlus = new JLabel("Plus :");
				lblPlus.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblPlus.setBounds(1210, 14, 63, 24);
				layeredPane_1.add(lblPlus);
				
				txtplus = new JTextField();
				txtplus.setColumns(10);
				txtplus.setBounds(1255, 13, 102, 26);
				layeredPane_1.add(txtplus);
				
				txtplustotal = new JTextField();
				txtplustotal.setColumns(10);
				txtplustotal.setBounds(1193, 770, 153, 26);
				add(txtplustotal);
if(listMagasinDechetMP!=null){
	  		    	  
	  		    	  int j=0;
		  		      	while(j<listMagasinDechetMP.size())
		  		      		{	
		  		      			Magasin magasin=listMagasinDechetMP.get(j);
		  		      			comboMagasinDechet.addItem(magasin.getLibelle());
		  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
		  		      			j++;
		  		      		}
	  		      }
				
				
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseurCarton> listDetailManqueDechetFournisseursCarton)
	{
		intialiserTableau2();
		
		
		 
			for (int i=0;i<listDetailManqueDechetFournisseursCarton.size();i++)
			{	
				
	DetailManqueDechetFournisseurCarton detailManqueDechetFournisseurcarton=	listDetailManqueDechetFournisseursCarton.get(i);
			 
				 
				Object []ligne={detailManqueDechetFournisseurcarton.getMatierePremier().getNom(),detailManqueDechetFournisseurcarton.getFourniseurAdhesive().getCodeFournisseur(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseurcarton.getQuantiteManque()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseurcarton.getQuantiteDechet()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailManqueDechetFournisseurcarton.getQuantitePlus())};

				modeleMP1.addRow( ligne);
			
			}
			
		
	}
	
	
void afficher_tableMP(List<CoutProdMP> listCoutProdMP)
	{
		intialiserTableau();
		 
			for (int i=0;i<listCoutProdMP.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				CoutProdMP coutMP=listCoutProdMP.get(i);
				
				
				Object []ligne={coutMP.getMatierePremier().getCode() , coutMP.getMatierePremier().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(coutMP.getQuantiteManquante()) , NumberFormat.getNumberInstance(Locale.FRANCE).format(coutMP.getQuantDechetFournisseur()), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutMP.getQuantiteManquanteFrPlus())};

				modeleMP.addRow( ligne);
			
			}
			
		
	}






void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matiere Premiere","Manque","Dechet","Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableCoutMP.setModel(modeleMP); 
		 tableCoutMP.getColumnModel().getColumn(0).setPreferredWidth(60);
      tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(160);
      tableCoutMP.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}

void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première","Fournisseur", "Quantité Manque","Quantite Dechet","Quantite Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableDechetFournisseur.setModel(modeleMP1); 
		 tableDechetFournisseur.getColumnModel().getColumn(0).setPreferredWidth(160);
		 tableDechetFournisseur.getColumnModel().getColumn(1).setPreferredWidth(60);
  


}



public void CalculerQuantite()
{
	    BigDecimal quantitTotaledechet=BigDecimal.ZERO;
		BigDecimal quantitTotalemanque=BigDecimal.ZERO;
		BigDecimal quantitTotalePlus=BigDecimal.ZERO;
	MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem().toString());
	
 	ManqueDechetFournisseurCarton manqueDechetFournisseur=manqueDechetFournisseurCartonDAO.findByCode(txtNumOF.getSelectedItem().toString());
   	if(manqueDechetFournisseur!=null)
   	{
   		listDetailManqueDechetFournisseurCarton=detailManqueDechetFournisseurCartonDAO.findByIdMPByManqueDechetFournisseur(manqueDechetFournisseur.getId(), matierePremier.getId());
	     	
   		
   	}
	
	
for(int i=0;i<listDetailManqueDechetFournisseurCarton.size();i++)
{
	
	if(listDetailManqueDechetFournisseurCarton.get(i).getMatierePremier().getId()==matierePremier.getId())
	{
		
		quantitTotaledechet=quantitTotaledechet.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteDechet())	;
		quantitTotalemanque=quantitTotalemanque.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantiteManque())	;
		quantitTotalePlus=quantitTotalePlus.add(listDetailManqueDechetFournisseurCarton.get(i).getQuantitePlus())	;
	}
	
	
	
}
afficher_tableMP_Total(listDetailManqueDechetFournisseurCarton);

txtmanquetotal.setText(quantitTotalemanque+"");
txtdechettotal.setText(quantitTotaledechet+"");
txtplustotal.setText(quantitTotalePlus+"");

}


public void Vider()
{
	txtdechet.setText("");
	txtmanque.setText("");
	combofournisseur.setSelectedIndex(-1);
}



public void ChargerOF()
{
	
	 listProductionMP=productionMPDAO.listeProductionMPEtatTerminer(Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
		listManqueDechetFournisseurCarton=manqueDechetFournisseurCartonDAO.listeManqueDechetFournisseurByEtat(Constantes.ETAT_VALIDER);
		boolean existe=false;
		txtNumOF.removeAllItems();
	 for(int i=0;i<listProductionMP.size();i++)
	 {
			existe=false;
	ProductionMP productionMP= listProductionMP.get(i);
	for(int j=0;j<listManqueDechetFournisseurCarton.size();j++)
	{
		
		if(listManqueDechetFournisseurCarton.get(j).getNumOF().equals(productionMP.getNumOFMP()))
		{
			existe=true;
		}
		
	}
	if(existe==false)
	{
		txtNumOF.addItem(productionMP.getNumOFMP());
		mapProduction.put(productionMP.getNumOFMP(), productionMP);
	}
	 
			
			
	 }
	 txtNumOF.setSelectedIndex(-1);
	
	
}
}

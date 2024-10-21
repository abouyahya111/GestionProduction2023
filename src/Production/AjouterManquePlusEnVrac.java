package Production;

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
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailTransferStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StockMP;
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


public class AjouterManquePlusEnVrac extends JLayeredPane implements Constantes{
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
	private ProductionDAO productionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private List<Production> listProduction=new ArrayList<Production>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseur>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<ManqueDechetFournisseur> listManqueDechetFournisseur=new ArrayList<ManqueDechetFournisseur>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, FournisseurMP> mapfournisseur = new HashMap<>();
	 List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPTmp =new ArrayList<CoutMP>();

	private JTextField txtmanque;
	private  JComboBox combomp = new JComboBox();
	private JComboBox combofournisseur = new JComboBox();
	private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
	private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
	private  JDateChooser dateChooserdechet = new JDateChooser();
	private ManqueDechetFournisseur newmanqueDechetFournisseur;
	private TransferStockMP transferStockMPTmp;
	private JTextField txtmanquetotal;
	JComboBox comboMagasinDechet = new JComboBox();
	private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private DepotDAO depotDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private JTextField txtPlustotal;
	private JTextField txtPlus;
	boolean existeManque=false;
	boolean existeTransfert=false;
	private CoutMPDAO coutMPDAO;
	private Map< String, MatierePremier> mapMatierePremiereAfficher = new HashMap<>();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterManquePlusEnVrac() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1447, 825);
        try{
        	
        	
        	
        	productionDAO=new ProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
        	detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
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
				  		     			
				  		     	CoutMP coutMP=listCoutMPTmp.get(tableCoutMP.getSelectedRow());
				  		     			
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
					  		     			"Code MP","Matiere Premiere","Manque","Plus"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false 
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
				  		     	scrollPane.setBounds(9, 108, 1240, 200);
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
				  		     					txtPlus.setText(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(),3).toString());
				  		     					 
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
				  			     			"Matière Première","Fournisseur", "Quantité Manque","Quantite Plus"
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
				  				scrollPane_1.setBounds(9, 456, 1246, 303);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 77, 1246, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1246, 54);
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
					
					BigDecimal TotalmanqueEnvrac=BigDecimal.ZERO;
					BigDecimal TotalPlusEnvrac=BigDecimal.ZERO;
					int pos=-1;
					Production production=mapProduction.get(txtNumOF.getSelectedItem());
					
					if(production!=null)
					{
						
						listCoutMPTmp.clear();
						listCoutMP=productionDAO.listeCoutMPDechetManqueByProduction(production.getId());
						boolean existe=false;
						for(int i=0;i<listCoutMP.size();i++)
						{
							
							if(listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								for(int j=0;j<listCoutMPTmp.size();j++)
								{
									
									if(listCoutMP.get(i).getMatierePremier().getId()==listCoutMPTmp.get(j).getMatierePremier().getId())
										
									{
										if(listCoutMP.get(i).getFournisseurMP().getId()==listCoutMPTmp.get(j).getFournisseurMP().getId())
										{
											existe=true;	
											
											if(listCoutMP.get(i).getFournisseurMP()!=null)
											{
												listCoutMPTmp.get(j).setCodeFournisseur (listCoutMPTmp.get(j).getCodeFournisseur()+" , "+ listCoutMP.get(i).getFournisseurMP().getCodeFournisseur());
											}
												listCoutMPTmp.get(j).setQuantiteManquante(listCoutMPTmp.get(j).getQuantiteManquante().add(listCoutMP.get(i).getQuantiteManquante()));
												listCoutMPTmp.get(j).setQuantDechetFournisseur(listCoutMPTmp.get(j).getQuantDechetFournisseur().add(listCoutMP.get(i).getQuantDechetFournisseur()));
												listCoutMPTmp.get(j).setQuantiteManquanteFrPlus(listCoutMPTmp.get(j).getQuantiteManquanteFrPlus().add(listCoutMP.get(i).getQuantiteManquanteFrPlus()));
											TotalmanqueEnvrac=TotalmanqueEnvrac.add(listCoutMP.get(i).getQuantiteManquante());	
											TotalPlusEnvrac=TotalPlusEnvrac.add(listCoutMP.get(i).getQuantiteManquanteFrPlus());	
											
											
											listCoutMPTmp.set(j, listCoutMPTmp.get(j));
										}
									
										
										
										
									}
									
									
								}
								
								
								
								if(existe==false)
								{
									if(listCoutMP.get(i).getFournisseurMP()!=null)
									{
										listCoutMP.get(i).setCodeFournisseur(listCoutMP.get(i).getFournisseurMP().getCodeFournisseur());
									}
									
									listCoutMPTmp.add(listCoutMP.get(i));
									
									
									TotalmanqueEnvrac=TotalmanqueEnvrac.add(listCoutMP.get(i).getQuantiteManquante());	
									TotalPlusEnvrac=TotalPlusEnvrac.add(listCoutMP.get(i).getQuantiteManquanteFrPlus());
									
								}
								
								pos=i;
								
							}
							
				
							
							
						}
						
						if(production.getQuantitePlus().compareTo(TotalPlusEnvrac)<0)
						{
							
						 CoutMP coutMP=listCoutMP.get(pos);
						 coutMP.setQuantiteManquanteFrPlus(coutMP.getQuantiteManquanteFrPlus().subtract(TotalPlusEnvrac.subtract(production.getQuantitePlus())));
						 coutMPDAO.edit(coutMP);
						 listCoutMPTmp.get(listCoutMPTmp.size()-1).setQuantiteManquanteFrPlus(coutMP.getQuantiteManquanteFrPlus());
						 
						}else if(production.getQuantitePlus().compareTo(TotalPlusEnvrac)>0)
						{
							 CoutMP coutMP=listCoutMP.get(pos);
							 coutMP.setQuantiteManquanteFrPlus(coutMP.getQuantiteManquanteFrPlus().add(production.getQuantitePlus().subtract(TotalPlusEnvrac)));
							 coutMPDAO.edit(coutMP);
							 listCoutMPTmp.get(listCoutMPTmp.size()-1).setQuantiteManquanteFrPlus(coutMP.getQuantiteManquanteFrPlus());
						}
						
						if(production.getQuantiteMoins().compareTo(TotalmanqueEnvrac)<0)
						{
							 CoutMP coutMP=listCoutMP.get(pos);
							 coutMP.setQuantiteManquante(coutMP.getQuantiteManquante().subtract(TotalmanqueEnvrac.subtract(production.getQuantiteMoins())));
							 coutMPDAO.edit(coutMP);
							 listCoutMPTmp.get(listCoutMPTmp.size()-1).setQuantiteManquante(coutMP.getQuantiteManquante());
							
						}else if(production.getQuantiteMoins().compareTo(TotalmanqueEnvrac)>0)
						{
							 CoutMP coutMP=listCoutMP.get(pos);
							 coutMP.setQuantiteManquante(coutMP.getQuantiteManquante().add(production.getQuantiteMoins().subtract(TotalmanqueEnvrac)));
							 coutMPDAO.edit(coutMP);
							 listCoutMPTmp.get(listCoutMPTmp.size()-1).setQuantiteManquante(coutMP.getQuantiteManquante()); 
							 
							
						}
						
						
						
						
						ManqueDechetFournisseur manqueDechetFournisseurTmp=manqueDechetFournisseurDAO.findByCode(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
						TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
						
						if(manqueDechetFournisseurTmp!=null)
						{
							newmanqueDechetFournisseur=manqueDechetFournisseurTmp;
							existeManque=true;
						}else
						{
							newmanqueDechetFournisseur=new ManqueDechetFournisseur();
							existeManque=false;
						}
						
						if(transfererStockMP!=null)
						{
							transferStockMPTmp=transfererStockMP;
							existeTransfert=true;
							
						}else
						{
							transferStockMPTmp=new TransferStockMP();
							existeTransfert=false;
						}
						
						afficher_tableMP(listCoutMPTmp);
						listDetailManqueDechetFournisseur.clear();
						listDetailTransfertStockMP.clear();
						intialiserTableau2();
						txtmanquetotal.setText("");
						 
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
	
		 
		  dateChooserdechet = new JDateChooser();
		 dateChooserdechet.setLocale(Locale.FRANCE);
		 dateChooserdechet.setDateFormatString("dd/MM/yyyy");
		 dateChooserdechet.setBounds(397, 16, 155, 26);
		 layeredPane.add(dateChooserdechet);
		 
		 JLayeredPane layeredPane_1 = new JLayeredPane();
		 layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 layeredPane_1.setBounds(9, 319, 1240, 54);
		 add(layeredPane_1);
		 
		 JLabel lblFournisseur = new JLabel("Fournisseur :");
		 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblFournisseur.setBounds(667, 13, 74, 24);
		 layeredPane_1.add(lblFournisseur);
		 
		 JLabel lblMp = new JLabel("  MP :");
		 lblMp.setBounds(334, 13, 44, 24);
		 layeredPane_1.add(lblMp);
		 
		  combomp = new JComboBox();
		  combomp.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent arg0) {
}
		  });
		 combomp.setSelectedIndex(-1);
		 combomp.setBounds(375, 13, 282, 24);
		 layeredPane_1.add(combomp);
		 
		  combofournisseur = new JComboBox();
		 combofournisseur.setSelectedIndex(-1);
		 combofournisseur.setBounds(734, 15, 153, 24);
		 layeredPane_1.add(combofournisseur);
		 
		 txtmanque = new JTextField();
		 txtmanque.setColumns(10);
		 txtmanque.setBounds(953, 13, 86, 26);
		 layeredPane_1.add(txtmanque);
		 
		 JLabel lblManque = new JLabel("Manque:");
		 lblManque.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblManque.setBounds(897, 14, 74, 24);
		 layeredPane_1.add(lblManque);
		 
		 JButton button = new JButton();
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

					
					try {
			 			
			 	 		MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
			 	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
			 	 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
				 		BigDecimal QuantiteTotalPlusProduction=BigDecimal.ZERO;
				 		BigDecimal QuantiteTotalManqueProduction=BigDecimal.ZERO;
			 	 		
			 	 		if(magasinDechet==null)
			 	 		{
			 	 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Dechet SVP !!!!");
				 			return;
			 	 		}
			 	 		
			 	 		if(matierePremier==null)
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Mp Dans la Liste des couts MP !!!!");
				 			return;
				 		}else if(txtPlus.getText().equals("") && txtmanque.getText().equals(""))
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez entrer le manque ou Plus SVP !!!!");
				 			return;
				 		}else if(fournisseurMP==null)
				 		{
				 			JOptionPane.showMessageDialog(null, "Veuillez entrer la fournisseur  SVP !!!!");
				 			return;
				 		}
				 		{

				 			CoutMP coutMP=null ;
	 
				 			coutMP=	listCoutMPTmp.get(tableCoutMP.getSelectedRow())	 	;		
				 			
				 	
				 		
				 		if(!txtmanque.getText().equals("") && new BigDecimal(txtmanque.getText()).compareTo(BigDecimal.ZERO)!=0)	
				 		{
				 			
				 			
				 			 
				 				
				 				QuantiteTotalManqueProduction=BigDecimal.ZERO;
				 				
				 				
					 			
					 		
				 				
				 				boolean trouverMP=false , trouverFournisseur=false , ErreurFournisseur=false;
				 				for(int t=0;t<listCoutMPTmp.size();t++)
				 				{
				 					if(listCoutMPTmp.get(t).getMatierePremier().getId()==matierePremier.getId())
				 					{
				 						
				 						trouverMP=true;
				 						

				 						
				 						
				 					}
				 					
				 					if(listCoutMPTmp.get(t).getFournisseurMP().getCodeFournisseur().equals(fournisseurMP.getCodeFournisseur()))
			 						{
			 							trouverFournisseur=true;
			 							
			 							
			 							
			 						}
				 						
				 						
				 					if(listCoutMPTmp.get(t).getFournisseurMP()==null)	
				 					{
				 						ErreurFournisseur=true;
				 						
				 					}
				 						
				 						
				 						QuantiteTotalManqueProduction=QuantiteTotalManqueProduction.add(listCoutMPTmp.get(t).getQuantiteManquante());
				 					
				 				}
				 				
				 				if(ErreurFournisseur==true)
					 			{
					 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour Les  En Vrac!!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
					 				return;
					 			}
				 				
						 			if(trouverMP==false || trouverFournisseur==false)
						 			{
						 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production ou MP Selectionner est différent aux MP Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
						 				return;
						 			}
						 		
				 				
				 				
				 				
				 				
				 				
				 				
				 		 
				 		
				 			
				 			
				 		}
				 		
				 		if(!txtPlus.getText().equals("") && new BigDecimal(txtPlus.getText()).compareTo(BigDecimal.ZERO)!=0)	
				 		{
				 			
				 			
				 			
				 				
				 				QuantiteTotalPlusProduction=BigDecimal.ZERO;
				 				
				 				
				 				
					 		
				 				boolean trouverMP=false , trouverFournisseur=false , ErreurFournisseur=false;
				 				for(int t=0;t<listCoutMPTmp.size();t++)
				 				{
				 					if(listCoutMPTmp.get(t).getMatierePremier().getId()==matierePremier.getId())
				 					{
				 						trouverMP=true;
				 						
				 						
				 						
				 					}
				 						
				 					if(listCoutMPTmp.get(t).getFournisseurMP().getCodeFournisseur().equals(fournisseurMP.getCodeFournisseur()))
			 						{
			 							trouverFournisseur=true;
			 						}
				 						
				 						if(listCoutMPTmp.get(t).getFournisseurMP()==null)	
					 					{
					 						ErreurFournisseur=true;
					 						
					 					}
				 						
				 						QuantiteTotalPlusProduction=QuantiteTotalPlusProduction.add(listCoutMPTmp.get(t).getQuantiteManquanteFrPlus());
				 					
				 				}
				 				
				 				
				 				if(ErreurFournisseur==true)
					 			{
					 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour Les En Vrac !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
					 				return;
					 			}
					 			
				 				
						 			if(trouverMP==false || trouverFournisseur==false)
						 			{
						 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production ou MP Selectionner est différent aux MP Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
						 				return;
						 			}
						 		
				 				
				 				
				 				
				 				
				 				
				 		
				 			
				 		
				 			
				 			
				 		}
				 		
				 			
				 	
				 			
				 			
				 			ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
				 			TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
				 			
				 		if(manqueDechetFournisseur!=null)
				 		{
				 		boolean existe=false;	
				 		BigDecimal quantitedechet=BigDecimal.ZERO;
				 		BigDecimal quantitemanque=BigDecimal.ZERO;
				 		BigDecimal quantitePlus=BigDecimal.ZERO;
				 			listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseurByCategorieThe(manqueDechetFournisseur.getId());
				 			for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
				 			{
				 				
				 				if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
				 				{
				 					
				 					existe=true;
				 					
				 					
				 					
				 				}else if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()!=fournisseurMP.getId())
				 				{
				 					
				 					
				 					//quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
				 					//quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
				 				}
				 				
				 				
				 					
				 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
				 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
				 					
				 				
				 					
				 				
				 				
				 			}
				 			
				 			if(existe==true)
				 			{
				 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
				 				return;
				 				
				 			}else
				 			{
				 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=new DetailManqueDechetFournisseur();
				 				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
				 		 
				 				    detailManqueDechetFournisseur.setEtat(ETAT_INVALIDER);
			 						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
				 				
				 				
				 				if(!txtmanque.getText().equals(""))
				 				{
				 					if((new BigDecimal(txtmanque.getText()).add(quantitemanque)).compareTo(QuantiteTotalManqueProduction)<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						detailTransferStockMP.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite manqante est different au sommes des Quantite manqante  !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
				 				}
				 				
				 				
				 				
				 				
				 				
				 				if(!txtPlus.getText().equals(""))
				 				{
				 					if((new BigDecimal(txtPlus.getText()).add(quantitePlus)).compareTo(QuantiteTotalPlusProduction)<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantitePlus (new BigDecimal(txtPlus.getText()));
				 						detailTransferStockMP.setQuantitePlus (new BigDecimal(txtPlus.getText()));
				 						
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Plus est different au sommes des Quantite Plus  !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantitePlus(BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
				 				}
				 				
				 				detailTransferStockMP.setMagasinDestination(magasinDechet);
				 				detailTransferStockMP.setFournisseur(fournisseurMP);
				 				detailTransferStockMP.setMatierePremier(matierePremier);
				 				detailTransferStockMP.setTransferStockMP(transfererStockMP);
				 				detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
				 				
				 				
				 				detailManqueDechetFournisseur.setPrix(coutMP.getPrixUnitaire());
				 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
				 				detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
				 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
				 				detailManqueDechetFournisseur.setManquedechetfournisseur(manqueDechetFournisseur);
				 				detailManqueDechetFournisseurDAO.add(detailManqueDechetFournisseur);
				 			    detailTransferMPDAO.add(detailTransferStockMP);
				 				CalculerQuantite();
				 				Vider();
				 			}
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 			
				 		}else
				 		{
				 			
				 			
				 			
				 			boolean existe=false;
				 			BigDecimal quantitedechet=BigDecimal.ZERO;
					 		BigDecimal quantitemanque=BigDecimal.ZERO;
					 		BigDecimal quantitePlus=BigDecimal.ZERO;
							for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
				 			{
				 				
				 				if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
				 				{
				 					
				 					existe=true;
				 					
				 					
				 					
				 				}else if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()!=fournisseurMP.getId())
				 				{
				 					
				 					
				 					//quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
				 					//quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
				 				}
				 					
				 				
				 					
				 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
				 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
				 					
				 				
				 				
				 			}
				 			
							if(existe==true)
				 			{
				 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
				 				return;
				 				
				 			}else
				 			{
				 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=new DetailManqueDechetFournisseur();
				 				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
				 				
				 				detailManqueDechetFournisseur.setEtat(ETAT_INVALIDER);
			 						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
				 			
				 				
				 				if(!txtmanque.getText().equals(""))
				 				{
				 					if((new BigDecimal(txtmanque.getText()).add(quantitemanque)) .compareTo(QuantiteTotalManqueProduction)<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						detailTransferStockMP.setQuantiteManque(new BigDecimal(txtmanque.getText()));
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Manque est different au sommes des Quantite manqante fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
				 				}
				 				
				 				
				 				if(!txtPlus.getText().equals(""))
				 				{
				 					if((new BigDecimal(txtPlus.getText()).add(quantitePlus)).compareTo(QuantiteTotalPlusProduction)<=0)
				 					{
				 						
				 						detailManqueDechetFournisseur.setQuantitePlus (new BigDecimal(txtPlus.getText()));
				 						detailTransferStockMP.setQuantitePlus (new BigDecimal(txtPlus.getText()));
				 						
				 						
				 					}else
				 					{
				 						JOptionPane.showMessageDialog(null, "La Quantite Plus est different au sommes des Quantite Plus fournisseur !!!!");
				 						return;
				 					}
				 				}else
				 				{
			 						detailManqueDechetFournisseur.setQuantitePlus (BigDecimal.ZERO);
			 						detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
				 				}
				 				
				 				detailTransferStockMP.setMagasinDestination(magasinDechet);
				 				detailTransferStockMP.setFournisseur(fournisseurMP);
				 				detailTransferStockMP.setMatierePremier(matierePremier);
				 				detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
				 				detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
				 				listDetailTransfertStockMP.add(detailTransferStockMP);
				 				
				 				detailManqueDechetFournisseur.setPrix(coutMP.getPrixUnitaire());
				 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
				 				detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
				 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
				 				detailManqueDechetFournisseur.setManquedechetfournisseur(newmanqueDechetFournisseur);
	                            listDetailManqueDechetFournisseur.add(detailManqueDechetFournisseur)	;	
	                        	
	                          
	                        	
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
		 button.setBounds(412, 394, 113, 31);
		 add(button);
		 button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		 JButton button_1 = new JButton();
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		Production production=mapProduction.get(txtNumOF.getSelectedItem());            
		 		
		 		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		 		
		 		BigDecimal quantitemanque=BigDecimal.ZERO;
		 		BigDecimal quantitePlus=BigDecimal.ZERO;
		 	
		 		BigDecimal quantitemanqueTotal=BigDecimal.ZERO;
		 		BigDecimal quantitePlusTotal=BigDecimal.ZERO;
		 		
		 	
		 			
		 			for(int i=0;i<listCoutMPTmp.size();i++)
		 			{
		 				if(listCoutMPTmp.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		 				{
		 					
		 					quantitemanqueTotal=quantitemanqueTotal.add(listCoutMPTmp.get(i).getQuantiteManquante());
	 						quantitePlusTotal=quantitePlusTotal.add(listCoutMPTmp.get(i).getQuantiteManquanteFrPlus());
		 					
		 				}
		 					
		 			 
		 			 
		 			}
		 			
		 			
		 			if(existeManque==true)
		 			{
		 				
		 				listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(newmanqueDechetFournisseur.getId());
		 				for(int j=0;j<listDetailManqueDechetFournisseur.size();j++)
		 				{
		 					 		
			 				if(listDetailManqueDechetFournisseur.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			 				{
			 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(j).getQuantiteManque());
		 						quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(j).getQuantitePlus());
			 				}
		 							
		 			
		 					
		 				}
		 				
		 				
		 			}else
		 			{
		 				for(int j=0;j<listDetailManqueDechetFournisseur.size();j++)
		 				{
		 					 		
			 				if(listDetailManqueDechetFournisseur.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			 				{
			 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(j).getQuantiteManque());
		 						quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(j).getQuantitePlus());
			 				}
		 							
		 			
		 					
		 				}
		 			}
		 			
		 		
		 			
		 			
		 			
		 			if(  quantitemanqueTotal.compareTo(quantitemanque)!=0 || quantitePlusTotal.compareTo(quantitePlus)!=0)
	 				{
		 				JOptionPane.showMessageDialog(null, "Quantite Total Manquante Ou Plus est différent aux Quantités Entre !!!!!","Error",JOptionPane.ERROR_MESSAGE);
		 				return;
	 				}
		 			
		 			
		 			
		 			
		 			
		 			newmanqueDechetFournisseur.setDateCreation(new Date());
		 			newmanqueDechetFournisseur.setDateDechet(dateChooserdechet.getDate());
		 			newmanqueDechetFournisseur.setNumOF(txtNumOF.getSelectedItem().toString());
		 			 
		 	        newmanqueDechetFournisseur.setEtat(Constantes.ETAT_VALIDER);
		 				
		 			  
		 			newmanqueDechetFournisseur.setType(TYPE_DECHET_FOURNISSEUR);
		 			if(existeManque==true)
		 			{
		 				
		 				listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(newmanqueDechetFournisseur.getId());
		 				for(int j=0;j<listDetailManqueDechetFournisseur.size();j++)
		 				{
		 					 		
			 				if(listDetailManqueDechetFournisseur.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			 				{
			 					 
			 					DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(j);
			 					detailManqueDechetFournisseur.setEtat(ETAT_VALIDER);
			 					detailManqueDechetFournisseurDAO.edit(detailManqueDechetFournisseur);
			 					
			 				}
		 							
		 			
		 					
		 				}
		 				manqueDechetFournisseurDAO.edit(newmanqueDechetFournisseur);
		 				
		 			}else
		 			{
		 				manqueDechetFournisseurDAO.add(newmanqueDechetFournisseur);
		 				
		 				for(int t=0;t<listDetailManqueDechetFournisseur.size();t++)
			 			{
			 				
			 				if(listDetailManqueDechetFournisseur.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			 				{
			 					DetailManqueDechetFournisseur detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(t);
			 					detailManqueDechetFournisseur.setEtat(ETAT_VALIDER);
			 					
			 					detailManqueDechetFournisseurDAO.add(detailManqueDechetFournisseur);
			 					
			 				}
			 				
			 			}
		 				
		 			}
		 			
		 			 
		 			
		 		
		 			
		 			transferStockMPTmp.setCodeTransfer(txtNumOF.getSelectedItem().toString());
		 			transferStockMPTmp.setCreerPar(AuthentificationView.utilisateur);
		 			transferStockMPTmp.setDate(new Date());
		 			transferStockMPTmp.setDateTransfer(dateChooserdechet.getDate());
		 			transferStockMPTmp.setDepot(magasinDechet.getDepot());
		 			//transferStockMPTmp.setListDetailTransferMP(listDetailTransfertStockMP);
		 			transferStockMPTmp.setStatut(Constantes.TYPE_DECHET_FOURNISSEUR);
		 			if(existeTransfert==true)
		 			{
		 				transferStockMPDAO.edit(transferStockMPTmp);
		 				
		 			}else
		 			{
		 				transferStockMPDAO.add(transferStockMPTmp);
		 				for(int i=0;i<listDetailTransfertStockMP.size();i++)
						{
							detailTransferMPDAO.add(listDetailTransfertStockMP.get(i));
						}
				 		
		 			}
		 			
		 			
		 			
		 			
		 			
		 			
		 			JOptionPane.showMessageDialog(null, "Manque Dechet Fournisseur Valider");
		 			listDetailManqueDechetFournisseur.clear();
		 			intialiserTableau();
		 			afficher_tableMP_Total(listDetailManqueDechetFournisseur);
		 			ChargerOF();
		 			
		 		
		 		
		 	
		 	}
		 });
		 button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 button_1.setIcon(imgValider);
		 button_1.setBounds(519, 783, 104, 31);
		 add(button_1);
		 
		 txtmanquetotal = new JTextField();
		 txtmanquetotal.setColumns(10);
		 txtmanquetotal.setBounds(761, 783, 153, 26);
		 add(txtmanquetotal);
				listMatierePremiere=matierePremiereDAO.findAll();
				for(int i=0;i<listMatierePremiere.size();i++)
				{
					
					MatierePremier matierePremier=listMatierePremiere.get(i);
					combomp.addItem(matierePremier.getNom());
					mapMatierePremiere.put(matierePremier.getNom(), matierePremier);
					mapCodeMatierePremiere.put(matierePremier.getCode(), matierePremier);
				}
				combomp.setSelectedIndex(-1);
				
				JLabel lblMagasinDechet = new JLabel("Magasin Dechet :");
				lblMagasinDechet.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblMagasinDechet.setBounds(9, 13, 97, 24);
				layeredPane_1.add(lblMagasinDechet);
				
				 comboMagasinDechet = new JComboBox();
				comboMagasinDechet.setBounds(116, 16, 208, 24);
				layeredPane_1.add(comboMagasinDechet);
				
				JButton btnModifier = new JButton();
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
if(tableDechetFournisseur.getSelectedRow()!=-1)
{
	

	System.out.println("listDetailTransfertStockMP.size(): "+listDetailTransfertStockMP.size());

	
	try {
		
		BigDecimal QuantiteTotalPlusProduction=BigDecimal.ZERO;
 		BigDecimal QuantiteTotalManqueProduction=BigDecimal.ZERO;
			
	 		MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
	 		FournisseurMP fournisseurMP=mapfournisseur.get(combofournisseur.getSelectedItem());
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
 		}else if(txtPlus.getText().equals("") && txtmanque.getText().equals("")  )
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez entrer la manque ou dechet SVP !!!!");
 			return;
 		}else if(fournisseurMP==null)
 		{
 			JOptionPane.showMessageDialog(null, "Veuillez entrer la fournisseur  SVP !!!!");
 			return;
 		}
 		{

 			CoutMP coutMP=null ;
 			for(int i=0;i<listCoutMPTmp.size();i++)
 				{
if(listCoutMPTmp.get(i).getMatierePremier().getId()==matierePremier.getId())	
{
coutMP=listCoutMPTmp.get(i);
}
}
 			
 			
 			
 			if(!txtmanque.getText().equals("") && new BigDecimal(txtmanque.getText()).compareTo(BigDecimal.ZERO)!=0)	
	 		{


	 			
	 			
	 			 
 				
 				QuantiteTotalManqueProduction=BigDecimal.ZERO;
 				
 				
	 			
	 		
 				
 				boolean trouverMP=false , trouverFournisseur=false , ErreurFournisseur=false;
 				for(int t=0;t<listCoutMPTmp.size();t++)
 				{
 					if(listCoutMPTmp.get(t).getMatierePremier().getId()==matierePremier.getId())
 					{
 						
 						trouverMP=true;
 						
 						
 						
 					}
 					
 					if(listCoutMPTmp.get(t).getFournisseurMP().getCodeFournisseur().equals(fournisseurMP.getCodeFournisseur()))
						{
							trouverFournisseur=true;
						}
 						
 						
 					if(listCoutMPTmp.get(t).getFournisseurMP()==null)	
 					{
 						ErreurFournisseur=true;
 						
 					}
 						
 						
 						QuantiteTotalManqueProduction=QuantiteTotalManqueProduction.add(listCoutMPTmp.get(t).getQuantiteManquante());
 					
 				}
 				
 				if(ErreurFournisseur==true)
	 			{
	 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour Les  En Vrac!!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
	 				return;
	 			}
 				
		 			if(trouverMP==false || trouverFournisseur==false)
		 			{
		 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production ou MP Selectionner est différent aux MP Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 		
 				
 				
 				
 				
 				
 				
 		 
 		
 			
 			
 		
	 		}
 			
 			if(!txtPlus.getText().equals("") && new BigDecimal(txtPlus.getText()).compareTo(BigDecimal.ZERO)!=0)	
	 		{
	 			
	 			
	 			
 				
 				QuantiteTotalPlusProduction=BigDecimal.ZERO;
 				
 				
 				
	 		
 				boolean trouverMP=false , trouverFournisseur=false , ErreurFournisseur=false;
 				for(int t=0;t<listCoutMPTmp.size();t++)
 				{
 					if(listCoutMPTmp.get(t).getMatierePremier().getId()==matierePremier.getId())
 					{
 						trouverMP=true;
 						
 						
 						
 						
 					}
 					if(listCoutMPTmp.get(t).getFournisseurMP().getCodeFournisseur().equals(fournisseurMP.getCodeFournisseur()))
						{
							trouverFournisseur=true;
						}
 						
 						
 						if(listCoutMPTmp.get(t).getFournisseurMP()==null)	
	 					{
	 						ErreurFournisseur=true;
	 						
	 					}
 						
 						QuantiteTotalPlusProduction=QuantiteTotalPlusProduction.add(listCoutMPTmp.get(t).getQuantiteManquanteFrPlus());
 					
 				}
 				
 				
 				if(ErreurFournisseur==true)
	 			{
	 				JOptionPane.showMessageDialog(null,"Veuillez Selectionner le Fournisseur Dans la Production Pour Les En Vrac !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
	 				return;
	 			}
	 			
 				
		 			if(trouverMP==false || trouverFournisseur==false)
		 			{
		 				JOptionPane.showMessageDialog(null,  "Le fournisseur Selectionner est Différent de Fournisseur Entrer dans La production ou MP Selectionner est différent aux MP Entrer dans La production !!!","Erreur" ,JOptionPane.ERROR_MESSAGE);
		 				return;
		 			}
		 		
 				
 				
 				
 				
 				
 		
 			
 		
 			
 			
 		}
 			
 			
 			
 			
 			DetailManqueDechetFournisseur detailManqueDechetFournisseurTmp=null;;
 			for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
				{
					
					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getNom().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()))
					{
						
						if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null	)
						{
							if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getCodeFournisseur().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString()))
		 					{
								
								detailManqueDechetFournisseurTmp=listDetailManqueDechetFournisseur.get(i);
								
								
		 					}
							
							
						}
								
						
						
						
					}
					
				}
 			
 			ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(detailManqueDechetFournisseurTmp.getManquedechetfournisseur().getNumOF(),Constantes.TYPE_DECHET_FOURNISSEUR);
 			 
 			
 			
           
 			
 			// TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCodeStatut(detailTransferStockMPTmp.getTransferStockMP().getCodeTransfer(),Constantes.TYPE_DECHET_FOURNISSEUR);
 		if(manqueDechetFournisseur!=null)
 		{
 			
 			
 			
 		boolean existe=false;	
 		BigDecimal quantitedechet=BigDecimal.ZERO;
 		BigDecimal quantitemanque=BigDecimal.ZERO;
 		BigDecimal quantitePlus=BigDecimal.ZERO;
 		
 			listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseurByCategorieThe(manqueDechetFournisseur.getId());
 			
 		
 			
 			
 			for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
 	 				{
 	 					
 	 					existe=true;
 	 					
 	 					
 	 					
 	 				}else if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()!=fournisseurMP.getId())
 	 				{
 	 					
 	 					
 	 					//quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
 	 					//quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
 	 				}
 					
 					 
	 					
	 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
	 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
	 					
	 				 
 					
 					
 					
 					
 				}
 			
 					
 				
 				
 			}
 			
 			if(existe==true)
 			{
 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
 				return;
 				
 			}else
 			{
 				
 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=null;
 				
 				for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
 				{
 					
 					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getNom().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()))
 					{
 						
 					if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null)
 					{
 						
 						if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getCodeFournisseur().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString()))
 						{
 							
 							detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(i);
 							
 							
 						}
 						
 						
 						
 					}
 						
 						
 						
 						
 						
 					}
 					
 					
 				}
 				 
 				
 				TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
 	 			MatierePremier matPremierTmp=mapMatierePremiereAfficher.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()+" "+tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());
 	 			FournisseurMP fournisseurMPTmp=mapfournisseur.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());
 	 			
 	 			DetailTransferStockMP detailTransferStockMP= detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMPByFournisseur(matPremierTmp, transfererStockMP, fournisseurMPTmp);
 	 			
 				
 				 
 			
 		
 			
						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);
						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
 				
 				
 				if(!txtmanque.getText().equals(""))
 				{
 					if((new BigDecimal(txtmanque.getText()).add(quantitemanque)).compareTo(QuantiteTotalManqueProduction)<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						detailTransferStockMP.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite manqante est different au sommes des Quantite manqante fournissue !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
						detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
 				}
 				
 				
 				if(!txtPlus.getText().equals(""))
 				{
 					if((new BigDecimal(txtPlus.getText()).add(quantitePlus)).compareTo(QuantiteTotalPlusProduction)<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantitePlus(new BigDecimal(txtPlus.getText()));
 						detailTransferStockMP.setQuantitePlus(new BigDecimal(txtPlus.getText()));
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Plus est different au sommes des Quantite Plus fournissue !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantitePlus(BigDecimal.ZERO);
						detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
 				}
 				
 				
 				detailTransferStockMP.setFournisseur(fournisseurMP);
 				detailTransferStockMP.setMatierePremier(matierePremier);
 				detailTransferStockMP.setMagasinDestination(magasinDechet);
 				detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
 				
 				detailManqueDechetFournisseur.setPrix(coutMP.getPrixUnitaire());
 				detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
 				detailManqueDechetFournisseurDAO.edit(detailManqueDechetFournisseur);
 				
 				detailTransferMPDAO.edit(detailTransferStockMP);
 				
 				CalculerQuantite();
 				Vider();
 			}
 			
 			
 			
 			
 			
 			
 			
 			
 			
 		}else
 		{
 			boolean existe=false;
 			BigDecimal quantitedechet=BigDecimal.ZERO;
	 		BigDecimal quantitemanque=BigDecimal.ZERO;
	 		BigDecimal quantitePlus=BigDecimal.ZERO;
	 		
			for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
 			{
 				if(i!=tableDechetFournisseur.getSelectedRow())
 				{
 					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()==fournisseurMP.getId())
 	 				{
 	 					
 	 					existe=true;
 	 					
 	 					
 	 					
 	 				}else if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getId()==matierePremier.getId() && listDetailManqueDechetFournisseur.get(i).getFourniseur().getId()!=fournisseurMP.getId())
 	 				{
 	 					
 	 					
 	 					//quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
 	 					//quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
 	 				}
 					
 					
	 					
	 					quantitemanque=quantitemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque());
	 					quantitePlus=quantitePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
	 					
	 				
 					
 					
 				}
 			
 					
 				
 			}
 			
			if(existe==true)
 			{
 				JOptionPane.showMessageDialog(null, "MP Desja entrer avec le meme fournisseur Veuillez Modifier SVP");
 				return;
 				
 			}else
 			{
 			
 				DetailManqueDechetFournisseur detailManqueDechetFournisseur=null ;
 				
 				for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
 				{
 					
 					if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getNom().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()))
 					{
 						
 						if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null	)
 						{
 							if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getCodeFournisseur().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString()))
 		 					{
 								
 								detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(i);
 								
 								
 		 					}
 							
 							
 						}
 								
 						
 						
 						
 					}
 					
 				}
 				 
 				
 				
 				TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
 	 			MatierePremier matPremierTmp=mapMatierePremiereAfficher.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()+" "+tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());
 	 			FournisseurMP fournisseurMPTmp=mapfournisseur.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());
 	 			
 	 			DetailTransferStockMP detailTransferStockMP= detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMPByFournisseur(matPremierTmp, transfererStockMP, fournisseurMPTmp);
 	 		
 		
 		
 		
 		if(detailManqueDechetFournisseur==null)
 		{
 			
 			detailManqueDechetFournisseur=listDetailManqueDechetFournisseur.get(tableDechetFournisseur.getSelectedRow());
 			
 		}	
 			
						detailManqueDechetFournisseur.setQuantiteDechet(BigDecimal.ZERO);
						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);

 			
 				
 				if(!txtmanque.getText().equals(""))
 				{
 					if((new BigDecimal(txtmanque.getText()).add(quantitemanque)) .compareTo(QuantiteTotalManqueProduction)<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						detailTransferStockMP.setQuantiteManque(new BigDecimal(txtmanque.getText()));
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Manque est different au sommes des Quantite manqante fournissue !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantiteManque(BigDecimal.ZERO);
						detailTransferStockMP.setQuantiteManque(BigDecimal.ZERO);
 				}
 				
 				
 				if(!txtPlus.getText().equals(""))
 				{
 					if((new BigDecimal(txtPlus.getText()).add(quantitePlus)).compareTo(QuantiteTotalPlusProduction)<=0)
 					{
 						
 						detailManqueDechetFournisseur.setQuantitePlus(new BigDecimal(txtPlus.getText()));
 						detailTransferStockMP.setQuantitePlus (new BigDecimal(txtPlus.getText()));
 						
 						
 					}else
 					{
 						JOptionPane.showMessageDialog(null, "La Quantite Plus est different au sommes des Quantite Plus fournissue !!!!");
 						return;
 					}
 				}else
 				{
						detailManqueDechetFournisseur.setQuantitePlus (BigDecimal.ZERO);
						detailTransferStockMP.setQuantitePlus(BigDecimal.ZERO);
 				}
 		
 				
 				detailTransferStockMP.setFournisseur(fournisseurMP);
 				detailTransferStockMP.setMagasinDestination(magasinDechet);
 				detailTransferStockMP.setMatierePremier(matierePremier);
                detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
 				
 				detailManqueDechetFournisseur.setPrix(coutMP.getPrixUnitaire());
 				detailManqueDechetFournisseur.setMagasinDechet(magasinDechet);
 				detailManqueDechetFournisseur.setFourniseur(fournisseurMP);
 				detailManqueDechetFournisseur.setMatierePremier(matierePremier);
 				
                listDetailManqueDechetFournisseur.set(tableDechetFournisseur.getSelectedRow(),detailManqueDechetFournisseur)	;	
                listDetailTransfertStockMP.set(tableDechetFournisseur.getSelectedRow(),detailTransferStockMP)	;	
              
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
				btnModifier.setBounds(547, 394, 113, 31);
			
				add(btnModifier);
				
				JButton btnSupprimer = new JButton("Supprimer");
				btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableDechetFournisseur.getSelectedRow()!=-1)
						{
							 
							
						 
						listDetailTransfertStockMP.size();
						boolean existe=false;
						
					 	ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
					   	if(manqueDechetFournisseur!=null)
					   	{
					   		existe=true;
					   	}
					
						
						
						TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
		 	 			MatierePremier matPremierTmp=mapMatierePremiereAfficher.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()+" "+tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());
		 	 			FournisseurMP fournisseurMPTmp=mapfournisseur.get(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString());		 	 			
		 	 			DetailTransferStockMP detailTransferStockMP= detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMPByFournisseur(matPremierTmp, transfererStockMP, fournisseurMPTmp);
		 	
		 	 			detailTransferMPDAO.delete(detailTransferStockMP.getId());
						
						for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
						{
							if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getNom().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 0).toString()))
							{
								
								if(listDetailManqueDechetFournisseur.get(i).getFourniseur()!=null)
								{
									if(listDetailManqueDechetFournisseur.get(i).getFourniseur().getCodeFournisseur().equals(tableDechetFournisseur.getValueAt(tableDechetFournisseur.getSelectedRow(), 1).toString()))
									{
										if(existe==true)
										{
											detailManqueDechetFournisseurDAO.delete(listDetailManqueDechetFournisseur.get(i).getId());
										}
										listDetailManqueDechetFournisseur.remove(i);
										
										
									}
									
								}
								
								
							}
							
							
						}
						
						
					    
							afficher_tableMP_Total(listDetailManqueDechetFournisseur);
							CalculerQuantite();
							Vider();
							
						 
								
						}
						
						
						
					}
				});
				btnSupprimer.setBounds(682, 395, 113, 30);
				add(btnSupprimer);
				combofournisseur.addItem("");
				listFournisseur=fournisseurMPDAO.findAll();
				for(int j=0;j<listFournisseur.size();j++)
				{
					FournisseurMP fournisseurMP=listFournisseur.get(j);
					combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
					mapfournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
					
				}
				
				Depot depot=depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());
				
				 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
	  		
	  		      
	  		      
					comboMagasinDechet.addItem(""); 		      
					
					JLabel lblPlus = new JLabel("Plus :");
					lblPlus.setFont(new Font("Tahoma", Font.PLAIN, 11));
					lblPlus.setBounds(1059, 12, 74, 24);
					layeredPane_1.add(lblPlus);
					
					txtPlus = new JTextField();
					txtPlus.setColumns(10);
					txtPlus.setBounds(1097, 11, 97, 26);
					layeredPane_1.add(txtPlus);
					
					txtPlustotal = new JTextField();
					txtPlustotal.setColumns(10);
					txtPlustotal.setBounds(1018, 777, 153, 26);
					add(txtPlustotal);
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
				
				
				
				
				
ChargerOF();		
				  		 
	}
	
	
	void afficher_tableMP_Total(List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurs)
	{
		intialiserTableau2();
		
		mapMatierePremiereAfficher.clear();
		 
			for (int i=0;i<listDetailManqueDechetFournisseurs.size();i++)
			{	
				
	DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseurs.get(i);
			 
	if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	{
		
		
		mapMatierePremiereAfficher.put(detailManqueDechetFournisseur.getMatierePremier().getNom()+" "+detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur(), detailManqueDechetFournisseur.getMatierePremier());
		Object []ligne={detailManqueDechetFournisseur.getMatierePremier().getNom(),detailManqueDechetFournisseur.getFourniseur().getCodeFournisseur(),detailManqueDechetFournisseur.getQuantiteManque(),detailManqueDechetFournisseur.getQuantitePlus()};

		modeleMP1.addRow( ligne);
		
	}
			
			
			}
			
		
	}
	
	
void afficher_tableMP(List<CoutMP> listCoutMP)
	{
		intialiserTableau();
		 
			for (int i=0;i<listCoutMP.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				CoutMP coutMP=listCoutMP.get(i);
				
				
				Object []ligne={coutMP.getMatierePremier().getCode() , coutMP.getMatierePremier().getNom(),coutMP.getCodeFournisseur() , coutMP.getQuantiteManquante(),  coutMP.getQuantiteManquanteFrPlus()};

				modeleMP.addRow( ligne);
				
			
			}
			
		
	}






void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","Matiere Premiere","Fournisseur","Manque", "Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false ,false 
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableCoutMP.setModel(modeleMP); 
		 tableCoutMP.getColumnModel().getColumn(0).setPreferredWidth(60);
      tableCoutMP.getColumnModel().getColumn(1).setPreferredWidth(160);
      tableCoutMP.getColumnModel().getColumn(2).setPreferredWidth(60);
      tableCoutMP.getColumnModel().getColumn(3).setPreferredWidth(60);
 
}

void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première","Fournisseur", "Quantité Manque","Quantite Plus"
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
		 tableDechetFournisseur.getColumnModel().getColumn(3).setPreferredWidth(60);

}



public void CalculerQuantite()
{


		BigDecimal quantitTotalemanque=BigDecimal.ZERO;
		BigDecimal quantitTotalePlus=BigDecimal.ZERO;
	MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem().toString());
	
 	ManqueDechetFournisseur manqueDechetFournisseur=manqueDechetFournisseurDAO.findByCode(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
   	if(manqueDechetFournisseur!=null)
   	{
   		
   		listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
	     	
   		
   	}
	
   	
   	
   	
   	
	
for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
{
	

		
if(listDetailManqueDechetFournisseur.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
{

	quantitTotalemanque=quantitTotalemanque.add(listDetailManqueDechetFournisseur.get(i).getQuantiteManque())	;
	quantitTotalePlus=quantitTotalePlus.add(listDetailManqueDechetFournisseur.get(i).getQuantitePlus());
	
}

		
	
	
	 
	
}


TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCodeStatut(txtNumOF.getSelectedItem().toString(),Constantes.TYPE_DECHET_FOURNISSEUR);
if(transfererStockMP!=null)
{
	listDetailTransfertStockMP=detailTransferMPDAO.findDetailTransferStockMPByMPByTransferMPList(matierePremier, transfererStockMP);
}


listDetailTransfertStockMP.size();

afficher_tableMP_Total(listDetailManqueDechetFournisseur);

txtmanquetotal.setText(quantitTotalemanque+"");


txtPlustotal.setText(quantitTotalePlus+"");


}


public void Vider()
{

txtPlus.setText("");
	txtmanque.setText("");
	combofournisseur.setSelectedIndex(-1);
}



public void ChargerOF()
{
	  listProduction=productionDAO.listeProductionEtatTerminerEnVrac(Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
		listManqueDechetFournisseur=manqueDechetFournisseurDAO.listeManqueDechetFournisseurByEtat(Constantes.ETAT_VALIDER);
		List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseurTmp=new ArrayList<DetailManqueDechetFournisseur>();
		
		boolean existe=false;
		txtNumOF.removeAllItems();
		txtNumOF.addItem("");
		boolean EnvracInvalide=false;
		
	 for(int i=0;i<listProduction.size();i++)
	 {
			existe=false;
			EnvracInvalide=false;
	Production production= listProduction.get(i);
	for(int j=0;j<listManqueDechetFournisseur.size();j++)
	{
		
		if(listManqueDechetFournisseur.get(j).getNumOF().equals(production.getNumOF())  )
		{
			if(listManqueDechetFournisseur.get(j).getType().equals(TYPE_DECHET_FOURNISSEUR))
			{
				
				
				
				listDetailManqueDechetFournisseurTmp=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(listManqueDechetFournisseur.get(j).getId())	;
				for(int t=0;t<listDetailManqueDechetFournisseurTmp.size();t++)
				{
					
					if(listDetailManqueDechetFournisseurTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						existe=true;
						if(listDetailManqueDechetFournisseurTmp.get(t).getEtat().equals(ETAT_INVALIDER))
						{
							EnvracInvalide=true;
						}
						
					}
					
				}
			}
		
		}
		
	}
	if(existe==false || EnvracInvalide==true)
	{
		txtNumOF.addItem(production.getNumOF());
		mapProduction.put(production.getNumOF(), production);
		 
		
	}
	 
			
			
	 }
	 txtNumOF.setSelectedItem("");
	

	 
	 
	 
	 
	 
}
}

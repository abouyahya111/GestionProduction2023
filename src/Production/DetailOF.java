package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.Articles;
import dao.entity.CompteStockMP;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class DetailOF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<DetailProdRes> listEmployeGesnerique=new ArrayList<DetailProdRes>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	   private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Production> listProduction=new ArrayList<Production>();
	private List<Production> listProductionAfficher=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private JTextField txtCodeArticle;
	private JTextField txtLibelle;
	private JTextField txtDateProduction;
	private JTextField txtQuantiteDemande;
	private JTextField txtPeriode;
	private JTextField txtQuantiteReel;
	private JTextField txtCoutTotal;
	private JXTable tableMP;
	private JXTable tableEmployerGenerique;
	private JXTable tableEmployeProduction;
	private JTextField txtPrix;
	private JTextField txtCoutTotalMP;
	private JTextField txtCoutTotalEmployeGenerique;
	private JTextField txtCoutTotalEmployeProduction;
	private JXTable tableEmployeEmballage;
	private JTextField txtCoutTotalEmployeEmballage;
	private JTextField txttotalcoutsupp50employegenerique;
	private JTextField txttotalcoutsupp25employegenerique;
	private JTextField txttotalcoutsupp50employeProduction;
	private JTextField txttotalcoutsupp25employeProduction;
	private JTextField txttotalcoutsupp50employeEmballage;
	private JTextField txttotalcoutsupp25employeEmballage;
	private JTextField txtTotalCoutOffreMP;
	private JTextField txtTotalCoutDechetMP;
	private JTextField txtTotalCoutQuantiteConsommeMP;
	private JTextField txtTotalCoutDechetFournisseurMP;
	private JTextField txtTotalCoutManquanteMP;
	 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
		private DetailProdResDAO detailProdResDAO;
		private DetailProductionDAO detailProductionDAO;
		private JTextField codeArticle;
		JComboBox comboArticle = new JComboBox();
		 List<Production> listProductionGroupByArticle=new ArrayList<Production>();
		 private Map< String, Articles> mapCodeArticle= new HashMap<>();
			private Map< String, Articles> mapLibelleAricle = new HashMap<>();
			
			
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public DetailOF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO= new ProductionDAOImpl();
        	depotdao= new DepotDAOImpl();
        	  CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	  detailProdResDAO=new DetailProdResDAOImpl();
        	  detailProductionDAO=new DetailProductionDAOImpl();
        	  listProductionGroupByArticle=productionDAO.listeProductionGroupByArticle();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
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
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Num OF","Date", "Depot","Article","Statut"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 130, 573, 338);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 569, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 569, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(158, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(532, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(combodepot.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else
			
			{
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
          listProduction.clear();
          listProductionAfficher.clear();
     listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());

     
     afficher_tableProd(listProduction);
				
				
				
				
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(37, 11, 111, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(191, 11, 124, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(325, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(373, 12, 149, 24);
		layeredPane.add(combodepot);
		
		JButton btnImprimer = new JButton("Afficher Detail OF");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Map parameters = new HashMap();
				BigDecimal coutTotalEmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
				BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
				BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
				BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
				BigDecimal coutTotalPlus= BigDecimal.ZERO;
				BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
				
				intialiserTableauEmployeGenerique();
				intialiserTableauMP();
				intialiserTableauEmployeProduction();
				DecimalFormatSymbols symbols = new DecimalFormatSymbols();
				symbols.setGroupingSeparator(' ');
				DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
				dfDecimal.setDecimalFormatSymbols(symbols);
				dfDecimal.setGroupingSize(3);
				dfDecimal.setGroupingUsed(true);
				
				
				Production production =listProductionAfficher.get(table.getSelectedRow());
				txtCodeArticle.setText(production.getArticles().getCodeArticle());
				txtLibelle.setText(production.getArticles().getLiblle());
				txtDateProduction.setText(String.valueOf(production.getDate_debFabPre()) );
				txtPeriode.setText( production.getPeriode());
				txtQuantiteDemande.setText(dfDecimal.format( production.getQuantiteEstime())+"");
				txtQuantiteReel.setText(dfDecimal.format(production.getQuantiteReel())+"");
				
			
				
				listCoutMP=production.getListCoutMP();
				listEmployeGesnerique= detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
				listEmployeEmballage=production.getListDetailProdGen();
				listEmployeProduction=detailProductionDAO.ListEmployeDetailProductionByProduction(production);
				
				
				listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.findByProduction(production);
				
				for(int k=0;k<listCoutMP.size();k++)
				{
					
					coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getCoutDechet());
					coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getCoutOffre());
					coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(listCoutMP.get(k).getQuantConsomme().multiply(listCoutMP.get(k).getPrixUnitaire()).subtract(listCoutMP.get(k).getQuantiteManquanteFrPlus().multiply(listCoutMP.get(k).getPrixUnitaire())));
					coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getCoutDechetFournisseur());
					coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getCoutManquante());
				}
				
			
				for(int i=0;i<listEmployeGesnerique.size();i++)
				{
					coutTotalEmployeGenerique=coutTotalEmployeGenerique.add((listEmployeGesnerique.get(i).getDelaiEmploye().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraire()));
					coutTotalSupp25EmployeGenerique=coutTotalSupp25EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp25().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp25()));
					coutTotalSupp50EmployeGenerique=coutTotalSupp50EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp50().divide(new BigDecimal(listEmployeGesnerique.get(i).getNbrProduction()) , 6, RoundingMode.HALF_UP)).multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp50()));
				}
				
				for(int j=0;j<listEmployeProduction.size();j++)
				{
					coutTotalEmployeProduction=coutTotalEmployeProduction.add(listEmployeProduction.get(j).getCoutHoraire().multiply(listEmployeProduction.get(j).getDelaiEmploye()));
					coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listEmployeProduction.get(j).getCoutHoraireSupp25().multiply(listEmployeProduction.get(j).getHeureSupp25()));
					coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listEmployeProduction.get(j).getCoutHoraireSupp50().multiply(listEmployeProduction.get(j).getHeureSupp50()));
					
				}
				
				for(int j=0;j<listCoutHorsProductionEnAttent.size();j++)
				{
					coutTotalEmployeProduction=coutTotalEmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutHoraire().multiply(listCoutHorsProductionEnAttent.get(j).getDelaiEmploye()));
					coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutHoraire25().multiply(listCoutHorsProductionEnAttent.get(j).getHeure25()));
					coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutHoraire50().multiply(listCoutHorsProductionEnAttent.get(j).getHeure50()));
					
				}
				
				boolean existe=false;
				for(int t=0;t<listCoutHorsProductionEnAttent.size();t++)
				{
					existe=false;
					
					for(int d=0;d<listEmployeProduction.size();d++)
					{
						
						if(listEmployeProduction.get(d).getEmploye().getId()==listCoutHorsProductionEnAttent.get(t).getEmploye().getId())
						{
							
							existe=true;
							listEmployeProduction.get(d).setDelaiEmploye(listEmployeProduction.get(d).getDelaiEmploye().add(listCoutHorsProductionEnAttent.get(t).getDelaiEmploye()));	
							listEmployeProduction.get(d).setHeureSupp25(listEmployeProduction.get(d).getHeureSupp25().add(listCoutHorsProductionEnAttent.get(t).getHeure25()));
							listEmployeProduction.get(d).setHeureSupp50(listEmployeProduction.get(d).getHeureSupp50().add(listCoutHorsProductionEnAttent.get(t).getHeure50()));
							listEmployeProduction.get(d).setCoutTotal(listEmployeProduction.get(d).getCoutTotal().add(listCoutHorsProductionEnAttent.get(t).getCoutTotal()));
							listEmployeProduction.get(d).setCoutSupp25 (listEmployeProduction.get(d).getCoutSupp25().add(listCoutHorsProductionEnAttent.get(t).getCoutSupp25()));
							listEmployeProduction.get(d).setCoutSupp50 (listEmployeProduction.get(d).getCoutSupp50().add(listCoutHorsProductionEnAttent.get(t).getCoutSupp50()));
							
						}
						
						
						
					}
					
					if(existe==false)
					{
						
						DetailProduction detailProduction=new DetailProduction();	
						
						detailProduction.setEmploye(listCoutHorsProductionEnAttent.get(t).getEmploye());
						detailProduction.setDelaiEmploye(listCoutHorsProductionEnAttent.get(t).getDelaiEmploye());
						detailProduction.setHeureSupp25(listCoutHorsProductionEnAttent.get(t).getHeure25());
						detailProduction.setHeureSupp50(listCoutHorsProductionEnAttent.get(t).getHeure50());
						detailProduction.setCoutTotal(listCoutHorsProductionEnAttent.get(t).getCoutTotal());
						detailProduction.setCoutSupp25(listCoutHorsProductionEnAttent.get(t).getCoutSupp25());
						detailProduction.setCoutSupp50(listCoutHorsProductionEnAttent.get(t).getCoutSupp50());
						
						listEmployeProduction.add(detailProduction);
						
						
					}
					
				
				
				}
				
				
				
				
				
				for(int j=0;j<listEmployeEmballage.size();j++)
				{
					coutTotalEmployeEmballage=coutTotalEmployeEmballage.add(listEmployeEmballage.get(j).getCoutHoraire().multiply(listEmployeEmballage.get(j).getDelaiEmploye()));
					coutTotalSupp25EmployeEmballage=coutTotalSupp25EmployeEmballage.add(listEmployeEmballage.get(j).getHeureSupp25().multiply(listEmployeEmballage.get(j).getCoutHoraireSupp25()));
					coutTotalSupp50EmployeEmballage=coutTotalSupp50EmployeEmballage.add(listEmployeEmballage.get(j).getHeureSupp50().multiply(listEmployeEmballage.get(j).getCoutHoraireSupp50()));
				}
				
				txtCoutTotalEmployeGenerique.setText(dfDecimal.format(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP))+"");
				txtCoutTotalEmployeProduction.setText(dfDecimal.format(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP))+"");
				txtCoutTotalEmployeEmballage.setText(dfDecimal.format(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP))+"");
				txtTotalCoutDechetMP.setText(dfDecimal.format(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP))+"");
				txtTotalCoutOffreMP.setText(dfDecimal.format(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP))+"");
				txtTotalCoutDechetFournisseurMP.setText(dfDecimal.format(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP))+"");
				txtTotalCoutQuantiteConsommeMP.setText(dfDecimal.format(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp25employeEmballage.setText(dfDecimal.format(coutTotalSupp25EmployeEmballage.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp50employeEmballage.setText(dfDecimal.format(coutTotalSupp50EmployeEmballage.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp25employegenerique.setText(dfDecimal.format(coutTotalSupp25EmployeGenerique.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp50employegenerique.setText(dfDecimal.format(coutTotalSupp50EmployeGenerique.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp25employeProduction.setText(dfDecimal.format(coutTotalSupp25EmployeProduction.setScale(2, RoundingMode.HALF_UP))+"");
				txttotalcoutsupp50employeProduction.setText(dfDecimal.format(coutTotalSupp50EmployeProduction.setScale(2, RoundingMode.HALF_UP))+"");
				txtTotalCoutManquanteMP.setText(dfDecimal.format(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))+"");
				
				txtCoutTotalMP.setText(dfDecimal.format(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP)))))+"");

				
				afficher_tableMP(listCoutMP);
				afficher_tableEmployeproduction(listEmployeProduction);
				afficher_tableEmployeGenerique(listEmployeGesnerique);
				afficher_tableEmployeEmballage(listEmployeEmballage);
				
				
				
				txtCoutTotal.setText( dfDecimal.format((coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP)))+"");
				txtPrix.setText(((coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP))) .divide(production.getQuantiteReel(), 2, RoundingMode.HALF_UP)+"");
				
				
				
				
				/*listCoutMP=production.getListCoutMP();
				listEmployeGesnerique=production.getListDetailProdGen();
				listResponsableProd=production.getListDetailResponsableProd();
				JOptionPane.showConfirmDialog(null, "listEmployeGesnerique :"+listEmployeGesnerique.size());
				JOptionPane.showConfirmDialog(null,"listResponsableProd :"+ listResponsableProd.size());
				JOptionPane.showConfirmDialog(null,"listCoutMP :"+ listCoutMP.size());
				parameters.put("depot",depot.getLibelle());
				parameters.put("codearticle",production.getArticles().getCodeArticle());
				parameters.put("libellearticle",production.getArticles().getLiblle());
				parameters.put("dateproduction",production.getDate_debFabPre());
				parameters.put("quantiteestime",production.getQuantiteEstime());
				parameters.put("quantitereel",production.getQuantiteReel());
				parameters.put("periode",production.getPeriode());
				parameters.put("couttotal",production.getCoutTotal());
				parameters.put("prixunitaire",production.getCoutTotal().divide(production.getQuantiteReel(), 2, RoundingMode.HALF_UP));
				parameters.put("listEmployeGenerique",listEmployeGesnerique);
				parameters.put("listEmployeProd",listResponsableProd);
				parameters.put("listCoutMP",listCoutMP);
				JasperUtils.imprimerDetailOF(listCoutMP, parameters);
				*/
				
			}
		});
		btnImprimer.setBounds(212, 479, 174, 24);
		add(btnImprimer);
		
		
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
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		  gridBagLayout.rowWeights = new double[]{0.0};
		  gridBagLayout.rowHeights = new int[]{0};
		  gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_1.setTitle("Cout Production");
		  titledSeparator_1.setBackground(Color.RED);
		  titledSeparator_1.setBounds(9, 503, 573, 30);
		  add(titledSeparator_1);
		  
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCodeArticle.setBounds(9, 544, 122, 23);
		  add(lblCodeArticle);
		  
		  txtCodeArticle = new JTextField();
		  txtCodeArticle.setEditable(false);
		  txtCodeArticle.setColumns(10);
		  txtCodeArticle.setBounds(127, 541, 111, 30);
		  add(txtCodeArticle);
		  
		  JLabel lblLibelle = new JLabel("Libelle :");
		  lblLibelle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblLibelle.setBounds(252, 541, 122, 23);
		  add(lblLibelle);
		  
		  txtLibelle = new JTextField();
		  txtLibelle.setEditable(false);
		  txtLibelle.setColumns(10);
		  txtLibelle.setBounds(311, 534, 265, 30);
		  add(txtLibelle);
		  
		  JLabel lblDateProduction = new JLabel("Date Production :");
		  lblDateProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblDateProduction.setBounds(9, 582, 122, 23);
		  add(lblDateProduction);
		  
		  txtDateProduction = new JTextField();
		  txtDateProduction.setEditable(false);
		  txtDateProduction.setColumns(10);
		  txtDateProduction.setBounds(127, 578, 111, 30);
		  add(txtDateProduction);
		  
		  JLabel lblQuantitDemand = new JLabel("Quantit\u00E9 Demand\u00E9:");
		  lblQuantitDemand.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitDemand.setBounds(248, 582, 174, 23);
		  add(lblQuantitDemand);
		  
		  txtQuantiteDemande = new JTextField();
		  txtQuantiteDemande.setEditable(false);
		  txtQuantiteDemande.setColumns(10);
		  txtQuantiteDemande.setBounds(384, 575, 192, 30);
		  add(txtQuantiteDemande);
		  
		  JLabel lblPeriode = new JLabel("Periode :");
		  lblPeriode.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPeriode.setBounds(9, 616, 122, 23);
		  add(lblPeriode);
		  
		  txtPeriode = new JTextField();
		  txtPeriode.setEditable(false);
		  txtPeriode.setColumns(10);
		  txtPeriode.setBounds(127, 613, 111, 30);
		  add(txtPeriode);
		  
		  JLabel lblQuantitRel = new JLabel("Quantit\u00E9 R\u00E9el :");
		  lblQuantitRel.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitRel.setBounds(250, 616, 122, 23);
		  add(lblQuantitRel);
		  
		  txtQuantiteReel = new JTextField();
		  txtQuantiteReel.setEditable(false);
		  txtQuantiteReel.setColumns(10);
		  txtQuantiteReel.setBounds(384, 612, 192, 30);
		  add(txtQuantiteReel);
		  
		  JLabel lblCoutTotal = new JLabel("Cout  Total :");
		  lblCoutTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotal.setBounds(252, 660, 122, 23);
		  add(lblCoutTotal);
		  
		  txtCoutTotal = new JTextField();
		  txtCoutTotal.setEditable(false);
		  txtCoutTotal.setColumns(10);
		  txtCoutTotal.setBounds(384, 653, 192, 30);
		  add(txtCoutTotal);
		  
		  JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		  gridBagLayout_1.rowWeights = new double[]{0.0};
		  gridBagLayout_1.rowHeights = new int[]{0};
		  gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_2.setTitle("Cout MP");
		  titledSeparator_2.setBackground(Color.RED);
		  titledSeparator_2.setBounds(588, 0, 981, 16);
		  add(titledSeparator_2);
		  
		  JScrollPane scrollPane_1 = new JScrollPane();
		  scrollPane_1.setBounds(588, 16, 981, 136);
		  add(scrollPane_1);
		  
		  tableMP = new JXTable();
		  tableMP.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Code MP", "Matiere Premiere","prix unitaire", "Quantite Consomme", "Quantite Dechet", "Quantite Offre","Quantite Dechet Fournisseur", "Quantite Manquante","Quantite Plus","Cout Total", "Cout Dechet", "Cout Offre","Cout Dechet Fournisseur","Cout Manquante","Cout Plus"
		  	}
		  ));
			 tableMP.getColumnModel().getColumn(0).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
			 tableMP.getColumnModel().getColumn(2).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(3).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(4).setPreferredWidth(60); 
			 tableMP.getColumnModel().getColumn(5).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(6).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(7).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(8).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(9).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(10).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(11).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(12).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(13).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(14).setPreferredWidth(60);
		   //  tableMP.setModel(modeleMP);
		  scrollPane_1.setViewportView(tableMP);
		  
		  JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_3.getLayout();
		  gridBagLayout_2.rowWeights = new double[]{0.0};
		  gridBagLayout_2.rowHeights = new int[]{0};
		  gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_3.setTitle("Cout Employe Generique");
		  titledSeparator_3.setBackground(Color.RED);
		  titledSeparator_3.setBounds(592, 218, 977, 30);
		  add(titledSeparator_3);
		  
		  JScrollPane scrollPane_2 = new JScrollPane();
		  scrollPane_2.setBounds(592, 244, 977, 150);
		  add(scrollPane_2);
		  
		  tableEmployerGenerique = new JXTable();
		  tableEmployerGenerique.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Nom","Delai Employe" , "Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout Total"
		  	}
		  ));
		  tableEmployerGenerique.getColumnModel().getColumn(0).setPreferredWidth(200);
		  tableEmployerGenerique.getColumnModel().getColumn(1).setPreferredWidth(60);
		  tableEmployerGenerique.getColumnModel().getColumn(2).setPreferredWidth(60);
		  tableEmployerGenerique.getColumnModel().getColumn(3).setPreferredWidth(60);
		  tableEmployerGenerique.getColumnModel().getColumn(4).setPreferredWidth(60);
		  tableEmployerGenerique.getColumnModel().getColumn(5).setPreferredWidth(60);
		  tableEmployerGenerique.getColumnModel().getColumn(6).setPreferredWidth(60);
		  //tableEmployerGenerique.setModel(modeleEmployeGen);
		  scrollPane_2.setViewportView(tableEmployerGenerique);
		  
		  JXTitledSeparator titledSeparator_4 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_3 = (GridBagLayout) titledSeparator_4.getLayout();
		  gridBagLayout_3.rowWeights = new double[]{0.0};
		  gridBagLayout_3.rowHeights = new int[]{0};
		  gridBagLayout_3.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_3.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_4.setTitle("Cout Employe Production");
		  titledSeparator_4.setBackground(Color.RED);
		  titledSeparator_4.setBounds(592, 424, 977, 30);
		  add(titledSeparator_4);
		  
		  JScrollPane scrollPane_3 = new JScrollPane();
		  scrollPane_3.setBounds(592, 459, 977, 136);
		  add(scrollPane_3);
		  
		  tableEmployeProduction = new JXTable();
		  tableEmployeProduction.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Nom","Delai Employe" ,"Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout Total"
		  	}
		  ));
		  tableEmployeProduction.getColumnModel().getColumn(0).setPreferredWidth(200);
		  tableEmployeProduction.getColumnModel().getColumn(1).setPreferredWidth(60);
		  tableEmployeProduction.getColumnModel().getColumn(2).setPreferredWidth(60);
		  tableEmployeProduction.getColumnModel().getColumn(3).setPreferredWidth(60);
		  tableEmployeProduction.getColumnModel().getColumn(4).setPreferredWidth(60);
		  tableEmployeProduction.getColumnModel().getColumn(5).setPreferredWidth(60);
		  tableEmployeProduction.getColumnModel().getColumn(6).setPreferredWidth(60);
		 // tableEmployeProduction.setModel(modeleEmployeProd);
		  scrollPane_3.setViewportView(tableEmployeProduction);
		  
		  JLabel lblPrix = new JLabel("Prix  :");
		  lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrix.setBounds(9, 652, 122, 23);
		  add(lblPrix);
		  
		  txtPrix = new JTextField();
		  txtPrix.setEditable(false);
		  txtPrix.setColumns(10);
		  txtPrix.setBounds(127, 649, 111, 30);
		  add(txtPrix);
		  
		  JLabel lblCoutTotalMp = new JLabel("Cout Total MP :");
		  lblCoutTotalMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotalMp.setBounds(1007, 192, 111, 27);
		  add(lblCoutTotalMp);
		  
		  txtCoutTotalMP = new JTextField();
		  txtCoutTotalMP.setEditable(false);
		  txtCoutTotalMP.setColumns(10);
		  txtCoutTotalMP.setBounds(1167, 191, 402, 30);
		  add(txtCoutTotalMP);
		  
		  JLabel lblCoutTotalEmploye = new JLabel("Cout Total Employe Generique :");
		  lblCoutTotalEmploye.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotalEmploye.setBounds(606, 399, 228, 23);
		  add(lblCoutTotalEmploye);
		  
		  txtCoutTotalEmployeGenerique = new JTextField();
		  txtCoutTotalEmployeGenerique.setEditable(false);
		  txtCoutTotalEmployeGenerique.setColumns(10);
		  txtCoutTotalEmployeGenerique.setBounds(1458, 396, 111, 30);
		  add(txtCoutTotalEmployeGenerique);
		  
		  JLabel lblCoutTotalEmploye_1 = new JLabel("Cout Total Employe Production:");
		  lblCoutTotalEmploye_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotalEmploye_1.setBounds(588, 592, 253, 23);
		  add(lblCoutTotalEmploye_1);
		  
		  txtCoutTotalEmployeProduction = new JTextField();
		  txtCoutTotalEmployeProduction.setEditable(false);
		  txtCoutTotalEmployeProduction.setColumns(10);
		  txtCoutTotalEmployeProduction.setBounds(1464, 598, 105, 24);
		  add(txtCoutTotalEmployeProduction);
		  
		  JXTitledSeparator titledSeparator_5 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_4 = (GridBagLayout) titledSeparator_5.getLayout();
		  gridBagLayout_4.rowWeights = new double[]{0.0};
		  gridBagLayout_4.rowHeights = new int[]{0};
		  gridBagLayout_4.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_4.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_5.setTitle("Cout Employe Emballage");
		  titledSeparator_5.setBackground(Color.RED);
		  titledSeparator_5.setBounds(588, 625, 981, 30);
		  add(titledSeparator_5);
		  
		  JScrollPane scrollPane_4 = new JScrollPane();
		  scrollPane_4.setBounds(588, 654, 981, 102);
		  add(scrollPane_4);
		  
		  tableEmployeEmballage = new JXTable();
		  tableEmployeEmballage.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Nom", "Delai Employe", "Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout total"
		  	}
		  ));
		  tableEmployeEmballage.getColumnModel().getColumn(0).setPreferredWidth(200);
		  tableEmployeEmballage.getColumnModel().getColumn(1).setPreferredWidth(60);
		  tableEmployeEmballage.getColumnModel().getColumn(2).setPreferredWidth(60);
		  tableEmployeEmballage.getColumnModel().getColumn(3).setPreferredWidth(60);
		  tableEmployeEmballage.getColumnModel().getColumn(4).setPreferredWidth(60);
		  tableEmployeEmballage.getColumnModel().getColumn(5).setPreferredWidth(60);
		  tableEmployeEmballage.getColumnModel().getColumn(6).setPreferredWidth(60);
		  scrollPane_4.setViewportView(tableEmployeEmballage);
		  
		  JLabel lblCoutTotalEmploye_2 = new JLabel("Cout Total Employe Emballage");
		  lblCoutTotalEmploye_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotalEmploye_2.setBounds(588, 770, 306, 23);
		  add(lblCoutTotalEmploye_2);
		  
		  txtCoutTotalEmployeEmballage = new JTextField();
		  txtCoutTotalEmployeEmballage.setEditable(false);
		  txtCoutTotalEmployeEmballage.setColumns(10);
		  txtCoutTotalEmployeEmballage.setBounds(1458, 767, 111, 30);
		  add(txtCoutTotalEmployeEmballage);
		  
		  txttotalcoutsupp50employegenerique = new JTextField();
		  txttotalcoutsupp50employegenerique.setEditable(false);
		  txttotalcoutsupp50employegenerique.setColumns(10);
		  txttotalcoutsupp50employegenerique.setBounds(1347, 396, 105, 30);
		  add(txttotalcoutsupp50employegenerique);
		  
		  txttotalcoutsupp25employegenerique = new JTextField();
		  txttotalcoutsupp25employegenerique.setEditable(false);
		  txttotalcoutsupp25employegenerique.setColumns(10);
		  txttotalcoutsupp25employegenerique.setBounds(1232, 396, 111, 30);
		  add(txttotalcoutsupp25employegenerique);
		  
		  txttotalcoutsupp50employeProduction = new JTextField();
		  txttotalcoutsupp50employeProduction.setEditable(false);
		  txttotalcoutsupp50employeProduction.setColumns(10);
		  txttotalcoutsupp50employeProduction.setBounds(1357, 597, 105, 25);
		  add(txttotalcoutsupp50employeProduction);
		  
		  txttotalcoutsupp25employeProduction = new JTextField();
		  txttotalcoutsupp25employeProduction.setEditable(false);
		  txttotalcoutsupp25employeProduction.setColumns(10);
		  txttotalcoutsupp25employeProduction.setBounds(1232, 597, 122, 25);
		  add(txttotalcoutsupp25employeProduction);
		  
		  txttotalcoutsupp50employeEmballage = new JTextField();
		  txttotalcoutsupp50employeEmballage.setEditable(false);
		  txttotalcoutsupp50employeEmballage.setColumns(10);
		  txttotalcoutsupp50employeEmballage.setBounds(1334, 767, 118, 30);
		  add(txttotalcoutsupp50employeEmballage);
		  
		  txttotalcoutsupp25employeEmballage = new JTextField();
		  txttotalcoutsupp25employeEmballage.setEditable(false);
		  txttotalcoutsupp25employeEmballage.setColumns(10);
		  txttotalcoutsupp25employeEmballage.setBounds(1222, 767, 105, 30);
		  add(txttotalcoutsupp25employeEmballage);
		  
		  txtTotalCoutOffreMP = new JTextField();
		  txtTotalCoutOffreMP.setEditable(false);
		  txtTotalCoutOffreMP.setColumns(10);
		  txtTotalCoutOffreMP.setBounds(1336, 152, 80, 30);
		  add(txtTotalCoutOffreMP);
		  
		  txtTotalCoutDechetMP = new JTextField();
		  txtTotalCoutDechetMP.setEditable(false);
		  txtTotalCoutDechetMP.setColumns(10);
		  txtTotalCoutDechetMP.setBounds(1251, 152, 80, 30);
		  add(txtTotalCoutDechetMP);
		  
		  txtTotalCoutQuantiteConsommeMP = new JTextField();
		  txtTotalCoutQuantiteConsommeMP.setEditable(false);
		  txtTotalCoutQuantiteConsommeMP.setColumns(10);
		  txtTotalCoutQuantiteConsommeMP.setBounds(1167, 152, 80, 30);
		  add(txtTotalCoutQuantiteConsommeMP);
		  
		  txtTotalCoutDechetFournisseurMP = new JTextField();
		  txtTotalCoutDechetFournisseurMP.setEditable(false);
		  txtTotalCoutDechetFournisseurMP.setColumns(10);
		  txtTotalCoutDechetFournisseurMP.setBounds(1426, 152, 76, 30);
		  add(txtTotalCoutDechetFournisseurMP);
		  
		  txtTotalCoutManquanteMP = new JTextField();
		  txtTotalCoutManquanteMP.setEditable(false);
		  txtTotalCoutManquanteMP.setColumns(10);
		  txtTotalCoutManquanteMP.setBounds(1506, 152, 63, 30);
		  add(txtTotalCoutManquanteMP);
		  
		  JButton btnExporterExcel = new JButton("Exporter Excel");
		  btnExporterExcel.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
				

	    			
	    					String titre="Detail Ordre De Fabrication  ";
		    	    		String titrefeuille="Detail OF ";
		    	    		ExporterTableVersExcel.tabletoexcelDetailOF(tableMP, tableEmployerGenerique, tableEmployeProduction, tableEmployeEmballage, titre, titrefeuille , txtCodeArticle.getText(),txtLibelle.getText(),txtDateProduction.getText(),txtPeriode.getText(),txtPrix.getText(),txtQuantiteDemande.getText(),txtQuantiteReel.getText(),txtCoutTotal.getText());
	    			
	    		
	    	
		  		
		  		
		  		
		  	}
		  });
		  btnExporterExcel.setBounds(191, 715, 155, 30);
		  add(btnExporterExcel);
		  
		  JLabel label = new JLabel("Code Article");
		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label.setBounds(9, 91, 82, 26);
		  add(label);
		  
		  codeArticle = new JTextField();
		  codeArticle.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyPressed(KeyEvent e) {
		  		
		  		

		  		
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			if(!codeArticle.getText().equals(""))
		  			{
		  				comboArticle.setSelectedItem(mapCodeArticle.get(codeArticle.getText()).getLiblle().toString());
		  			}else
		  			{
		  				comboArticle.setSelectedItem("");
		  				
		  			}
		  			
		  		
		  			 
		  		}
		  		
		  		
		  	
		  	}
		  });
		  codeArticle.setColumns(10);
		  codeArticle.setBounds(82, 91, 111, 26);
		  add(codeArticle);
		  
		  JLabel label_1 = new JLabel("Article:");
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_1.setBounds(203, 90, 102, 26);
		  add(label_1);
		  
		   comboArticle = new JComboBox();
		   comboArticle.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		

		   		
			   	 if(e.getStateChange() == ItemEvent.SELECTED) {
			   		 if(!comboArticle.getSelectedItem().toString().equals(""))
			   		 {
			   			 Articles article=mapLibelleAricle.get(comboArticle.getSelectedItem().toString());
			   			codeArticle.setText(article.getCodeArticle());
			   		 }else
			   		 {
			   			codeArticle.setText("");
			   		 }
			   		 
			   	 }
			   		
			   	
		   	}
		   });
		  comboArticle.setBounds(251, 91, 307, 26);
		  add(comboArticle);
		
		
		  comboArticle.addItem("");
			listProductionGroupByArticle.stream().forEach(

					e->{
						comboArticle.addItem(e.getArticles().getLiblle().toString());
						mapCodeArticle.put(e.getArticles().getCodeArticle(), e.getArticles());
						mapLibelleAricle.put(e.getArticles().getLiblle(), e.getArticles());
						
					}
					);
				  		     
				  		 
	}
	
void afficher_tableProd(List<Production> listProduction)
	{
		intialiserTableau();
		
		
		listProductionAfficher.clear();
		Articles articles=mapLibelleAricle.get(comboArticle.getSelectedItem().toString());
		 
			for (int i=0;i<listProduction.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Production production=listProduction.get(i);
				if(articles!=null)
				{
					if(articles.getId()==production.getArticles().getId())
					{
						
						Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getArticles().getLiblle(),production.getStatut()};

						modeleProd.addRow( ligne);
						listProductionAfficher.add(production);
					}
					
					 
					
				}else
				{
					Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getArticles().getLiblle(),production.getStatut()};

					modeleProd.addRow( ligne);
					listProductionAfficher.add(production);
				}
				
				
			
			}
			
		
	}

void afficher_tableMP(List<CoutMP> listCoutMP)
{
	intialiserTableauMP();;
	 
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
		for (int i=0;i<listCoutMP.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			CoutMP coutmp=listCoutMP.get(i);
			
			Object []ligne={coutmp.getMatierePremier().getCode() , coutmp.getMatierePremier().getNom(),coutmp.getPrixUnitaire(),dfDecimal.format(coutmp.getQuantConsomme().subtract(coutmp.getQuantiteManquanteFrPlus())),dfDecimal.format(coutmp.getQuantDechet()),dfDecimal.format(coutmp.getQuantiteOffre()),dfDecimal.format(coutmp.getQuantDechetFournisseur()),dfDecimal.format(coutmp.getQuantiteManquante()),dfDecimal.format(coutmp.getQuantiteManquanteFrPlus()), dfDecimal.format(((coutmp.getQuantConsomme().multiply(coutmp.getPrixUnitaire())).subtract(coutmp.getQuantiteManquanteFrPlus().multiply(coutmp.getPrixUnitaire())))), dfDecimal.format(coutmp.getCoutDechet()),dfDecimal.format(coutmp.getCoutOffre()),dfDecimal.format(coutmp.getCoutDechetFournisseur()),dfDecimal.format(coutmp.getCoutManquante()),BigDecimal.ZERO};

			modeleMP.addRow( ligne);
		
		}
		
	
}

void afficher_tableEmployeGenerique(List<DetailProdRes> listDetailProdGenerique)
{
	intialiserTableauEmployeGenerique();;
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
		for (int i=0;i<listDetailProdGenerique.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			DetailProdRes detailemployegen=listDetailProdGenerique.get(i);
			
			Object []ligne={detailemployegen.getEmploye().getNom(),dfDecimal.format(detailemployegen.getDelaiEmploye().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP)), dfDecimal.format(detailemployegen.getHeureSupp25().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP)),dfDecimal.format(detailemployegen.getHeureSupp50().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP)),dfDecimal.format(detailemployegen.getHeureSupp25().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP).multiply(detailemployegen.getCoutHoraireSupp25())),dfDecimal.format(detailemployegen.getHeureSupp50().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP).multiply(detailemployegen.getCoutHoraireSupp50())),dfDecimal.format(detailemployegen.getDelaiEmploye().divide(new BigDecimal(detailemployegen.getNbrProduction()), 6, RoundingMode.HALF_UP).multiply(detailemployegen.getCoutHoraire()))};

			modeleEmployeGen.addRow( ligne);
		
		}
		
	
}

void afficher_tableEmployeproduction(List<DetailProduction> listEmployeProduction)
{
	intialiserTableauEmployeProduction();;;
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
		for (int i=0;i<listEmployeProduction.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			DetailProduction detailemployeProd=listEmployeProduction.get(i);
			
			Object []ligne={detailemployeProd.getEmploye().getNom(),detailemployeProd.getDelaiEmploye(),detailemployeProd.getHeureSupp25(),detailemployeProd.getHeureSupp50(),dfDecimal.format(detailemployeProd.getCoutSupp25()),dfDecimal.format(detailemployeProd.getCoutSupp50()),dfDecimal.format(detailemployeProd.getCoutTotal())};

			modeleEmployeProd.addRow( ligne);
		
		}
		
	
}

void afficher_tableEmployeEmballage(List<DetailProdGen> listEmployeEmballage)
{
	intialiserTableauEmployeEmballage();
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
		for (int i=0;i<listEmployeEmballage.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			DetailProdGen detailemployeEmballage=listEmployeEmballage.get(i);
			
			Object []ligne={detailemployeEmballage.getEmploye().getNom(),detailemployeEmballage.getDelaiEmploye(),detailemployeEmballage.getHeureSupp25(),detailemployeEmballage.getHeureSupp50(),dfDecimal.format(detailemployeEmballage.getCoutSupp25()),dfDecimal.format(detailemployeEmballage.getCoutSupp50()),dfDecimal.format(detailemployeEmballage.getCoutTotal())};

			modeleEmployeEmballage.addRow( ligne);
		
		}
		
	
}



void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num OF","Date", "Depot","Article","Statut"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}


void intialiserTableauMP(){
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
				  		"Code MP", "Matiere Premiere","prix unitaire", "Quantite Consomme", "Quantite Dechet", "Quantite Offre","Quantite Dechet Fournisseur", "Quantite Manquante","Quantite Plus","Cout Total", "Cout Dechet", "Cout Offre","Cout Dechet Fournisseur","Cout Manquante","Cout Plus"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableMP.setModel(modeleMP); 
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
		 tableMP.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(4).setPreferredWidth(60); 
		 tableMP.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(6).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(7).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(8).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(9).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(10).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(11).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(12).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(13).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(14).setPreferredWidth(60);
}


void intialiserTableauEmployeEmballage(){
	modeleEmployeEmballage =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Nom","Delai Employe" , "Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout Total"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableEmployeEmballage.setModel(modeleEmployeEmballage); 
		 tableEmployeEmballage.getColumnModel().getColumn(0).setPreferredWidth(200);
		 tableEmployeEmballage.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableEmployeEmballage.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableEmployeEmballage.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableEmployeEmballage.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableEmployeEmballage.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableEmployeEmballage.getColumnModel().getColumn(6).setPreferredWidth(60);
 
}




void intialiserTableauEmployeGenerique(){
	modeleEmployeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Nom","Delai Employe" , "Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout Total"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableEmployerGenerique.setModel(modeleEmployeGen); 
		 tableEmployerGenerique.getColumnModel().getColumn(0).setPreferredWidth(200);
		 tableEmployerGenerique.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableEmployerGenerique.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableEmployerGenerique.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableEmployerGenerique.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableEmployerGenerique.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableEmployerGenerique.getColumnModel().getColumn(6).setPreferredWidth(60);
 
}

void intialiserTableauEmployeProduction(){
	modeleEmployeProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Nom","Delai Employe" , "Heure Supp 25", "Heure Supp 50", "Cout Supp 25", "Cout Supp 50", "Cout Total"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableEmployeProduction.setModel(modeleEmployeProd); 
		 tableEmployeProduction.getColumnModel().getColumn(0).setPreferredWidth(200);
		 tableEmployeProduction.getColumnModel().getColumn(1).setPreferredWidth(60);
		 tableEmployeProduction.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableEmployeProduction.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableEmployeProduction.getColumnModel().getColumn(4).setPreferredWidth(60);
		 tableEmployeProduction.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableEmployeProduction.getColumnModel().getColumn(6).setPreferredWidth(60);
 
}
}

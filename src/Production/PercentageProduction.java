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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;

import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.PourcentageProductionArticle;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PercentageProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleProdTmp;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	 List<PourcentageProductionArticle> listPourcentageProductionArticle=new ArrayList<PourcentageProductionArticle>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
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
	private List<Production> listProductionTmp=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, String> mapMP= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private JTable tableMP;
	 JXTable Table_Percentage_Production = new JXTable();
	 private Map< String, Boolean> mapProductionGenerer= new HashMap<>();
	 private Map< String, Boolean> mapProductionImprimer= new HashMap<>();
	 private ParametreDAO parametreDAo;
	 private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public PercentageProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1171);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO= new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	parametreDAo=new ParametreDAOImpl();
        	
        	 imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
        
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent e) {}
				  		     });
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
					  		     			"Num OF","Date", "Depot","Statut","Article","Quantite","Generer"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,true
					  		     	};
					  		     	Class[] columnTypes = new Class[] {
											String.class,Date.class,String.class,String.class,String.class, String.class,Boolean.class
										};
					  		      public Class getColumnClass(int columnIndex) {
										return columnTypes[columnIndex];
									}
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					         table.getColumnModel().getColumn(3).setPreferredWidth(60);
					         table.getColumnModel().getColumn(4).setPreferredWidth(60);
					         table.getColumnModel().getColumn(5).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 118, 832, 390);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF","Date", "Depot","Statut","Article","Quantite","Generer"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,true
				  			     	};
				  			  	Class[] columnTypes = new Class[] {
										String.class,Date.class,String.class,String.class,String.class,String.class, Boolean.class
									};
				  		      public Class getColumnClass(int columnIndex) {
									return columnTypes[columnIndex];
								}
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Les productions Terminer");
				  		     	titledSeparator.setBounds(9, 68, 832, 24);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 832, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(183, 11, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(791, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date DÈbut", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}else if(combodepot.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else
			
			{
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				productionDAO.ViderSession();
          listProduction.clear();
     listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());
afficher_tableProd(listProduction);
				
				listCoutMPTmp.clear();
				listProductionTmp.clear();
				intialiserTableauMP();
				intialiserTableauProdGenerer();
				
				
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(37, 11, 136, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(216, 12, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(366, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(414, 12, 149, 24);
		layeredPane.add(combodepot);
		
		JButton btnImprimer = new JButton("Imprimer Pourcentage Production");
		btnImprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!remplirProductionImprimer())
				{
					
					JOptionPane.showMessageDialog (null, "Veuillez Selectionner les productions imprimer");
					return;
					
				}else
				{
					listCoutMPTmp.clear();
					
					for(int i=0;i<listProductionTmp.size();i++)
					{
						
						if(mapProductionImprimer.containsKey(listProductionTmp.get(i).getNumOF()))
						{
			     			
			     			
			     			Parametre percentageProduction=parametreDAo.findByCode(PERCENTAGE_PRODUCTION);
			     			Production production=listProductionTmp.get(i);
			     			
			     			int count=0;
			     			
			     			for(int p=0;p<production.getListCoutMP().size();p++)
			     			{
			     				
			     				if(production.getListCoutMP().get(p).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			     				{
			     					count+=1;
			     				}
			     				
			     				
			     			}
			     			
			     			
			     			for(int j=0;j<production.getListCoutMP().size();j++)
			     			{
			     				if(!production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) )
			     				{
			     				 
			     				CoutMP coutmp=new CoutMP();
			     				
			     				if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
			     				{
			     					BigDecimal estimation=BigDecimal.ZERO;
			     					for(int k=0;k<production.getArticles().getDetailEstimation().size();k++)
			     					{
			     						if(production.getArticles().getDetailEstimation().get(k).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
			     						{
			     							estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						}
			     					}
			     					BigDecimal quantite=BigDecimal.ZERO;
			     					BigDecimal rest=BigDecimal.ZERO;
			     					if(!estimation.equals(BigDecimal.ZERO))
			     					{
			     						quantite=production.getQuantiteReel().divide(estimation,6,RoundingMode.HALF_UP);
			     						
			     						
			     					}
			     							
			     				 rest=quantite.subtract(quantite.setScale(0, RoundingMode.FLOOR)).movePointRight(quantite.scale());

	         if(!rest.equals(BigDecimal.ZERO))
	             {
	        	 if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
	        	 {
	        		 if(quantite.remainder(BigDecimal.ONE).setScale(3, RoundingMode.FLOOR).equals(BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR)))
	        		 {
	        			 quantite=production.getQuantiteReel().divide(estimation).setScale(0,  RoundingMode.FLOOR); 
	        		 }else
	        		 {
	        			 quantite=(quantite.subtract(quantite.remainder(BigDecimal.ONE))).add(BigDecimal.ONE);  
	        		 }
	        		
	        	 }else
	        	 {
	        		 quantite=production.getQuantiteReel().divide(estimation).setScale(0, RoundingMode.HALF_UP);
	        	 }
	        	
	        	 
	            }
			     					coutmp.setQuantConsomme(quantite);
			     					coutmp.setQuantDechet (production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP));

			     				}else if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			     				{
				  					
			     					BigDecimal quantite=production.getListCoutMP().get(j).getQuantConsomme().setScale(3).multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP));
			     					BigDecimal quantiteDechet=production.getListCoutMP().get(j).getQuantDechet().setScale(3).multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP));
			     					BigDecimal rest=quantite.subtract(quantite.setScale(0, RoundingMode.FLOOR)).movePointRight(quantite.scale());
			     					BigDecimal restdechet=quantiteDechet.subtract(quantiteDechet.setScale(0, RoundingMode.FLOOR)).movePointRight(quantiteDechet.scale());
			     							  				
			     							  					
			     							  					BigDecimal estimation=BigDecimal.ZERO;
			     						     					for(int k=0;k<production.getArticles().getDetailEstimation().size();k++)
			     						     					{
			     						     						if(production.getArticles().getDetailEstimation().get(k).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
			     						     						{
			     						     							
			     						     							if(count==1)
			     						     							{
			     						     								if(production.getArticles().getDetailEstimation().get(k).getPriorite()==1)
			     						     								{
			     						     									estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						     								}
			     						     								
			     						     							}else
			     						     							{
			     						     								
			     						     								if(production.getArticles().getDetailEstimation().get(k).getPriorite()==2)
			     						     								{
			     						     									estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						     								}
			     						     								
			     						     							}
			     						     								
			     						     						}
			     						     					}
			     							  					
	     									  					coutmp.setQuantConsomme(production.getQuantiteReel().multiply(estimation));
			     							  					
			     							  					if(!restdechet.equals(BigDecimal.ZERO))
			     							  					{
			     							  						 String x= String.valueOf(restdechet);	
			     							  						x=x.substring(0, 3);
			     							  						
			     									  					BigDecimal b=new BigDecimal(x).divide(production.getListCoutMP().get(j).getProdcutionCM().getArticles().getCentreCout3(),0,RoundingMode.FLOOR);
			     									  					
			     									  					coutmp.setQuantDechet (quantiteDechet.setScale(0,RoundingMode.FLOOR).add((b.setScale(0,RoundingMode.FLOOR).multiply(production.getListCoutMP().get(j).getProdcutionCM().getArticles().getCentreCout3())).divide(new BigDecimal(1000),6,RoundingMode.FLOOR)));
			     							  					}else
			     							  					{
			     							  						
			     							  						coutmp.setQuantDechet(quantiteDechet);
			     							  						
			     							  					}
			     				}else if (production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE))
			     				{
			     					
			     					BigDecimal estimation=BigDecimal.ZERO;
			     					for(int k=0;k<production.getArticles().getDetailEstimation().size();k++)
			     					{
			     						if(production.getArticles().getDetailEstimation().get(k).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
			     						{
			     							estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						}
			     					}
			     					
			     					coutmp.setQuantConsomme((production.getQuantiteReel().multiply(estimation)).multiply(Constantes.CENTURE_5000));
			     					coutmp.setQuantDechet((production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP))).multiply(Constantes.CENTURE_5000));	
			     					
			     				}else if (production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH))
			     				{
			     					BigDecimal estimation=BigDecimal.ZERO;
			     					for(int k=0;k<production.getArticles().getDetailEstimation().size();k++)
			     					{
			     						if(production.getArticles().getDetailEstimation().get(k).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
			     						{
			     							estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						}
			     					}
			     					
			     					coutmp.setQuantConsomme((production.getQuantiteReel().multiply(estimation)).multiply(Constantes.SCOTCHE_MULTIPLE_1000));
			     					coutmp.setQuantDechet((production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP))).multiply(Constantes.SCOTCHE_MULTIPLE_1000));	
			     					
			     				}else
			     				{
			     					BigDecimal estimation=BigDecimal.ZERO;
			     					for(int k=0;k<production.getArticles().getDetailEstimation().size();k++)
			     					{
			     						if(production.getArticles().getDetailEstimation().get(k).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
			     						{
			     							estimation=production.getArticles().getDetailEstimation().get(k).getQuantite();
			     						}
			     					}
			     					coutmp.setQuantConsomme(production.getQuantiteReel().multiply(estimation));
			     					coutmp.setQuantDechet(production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP)));	
			     				}
			     				coutmp.setQuantCharge(coutmp.getQuantConsomme().add(coutmp.getQuantDechet()));
			     				coutmp.setMatierePremier(production.getListCoutMP().get(j).getMatierePremier());
			     				coutmp.setProdcutionCM(production);
			     				listCoutMPTmp.add(coutmp);
			     				
			     			}
			     				
			     			}
			     			
			     			
							
						}
						
					}
					
					
					for(int i=0;i<listCoutMPTmp.size();i++)
	     			{
	     				
	     				if(!listCoutMPTmp.get(i).getMatierePremier().getCategorieMp(). getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
	     				{
	     					
	     					listCoutMPTmp.get(i).setNomMP(listCoutMPTmp.get(i).getMatierePremier().getNom());	
	     					
	     				}else
	     				{
	     					listCoutMPTmp.get(i).setNomMP(Constantes.POURCENTAGE_PROMO);
	     					
	     				}
	     				
	     				
	     				
	     			}
	     			
					
					
					
					if(listCoutMPTmp.size()!=0)
					{
						
						Map parameters = new HashMap();
	    				//parameters.put("dateproduction", production.getDate_debFabPre());
	    				JasperUtils.imprimerPercentageProduction(listCoutMPTmp, parameters);
						
					}
					
					
					/*
					 * for(int k=0;k<listCoutMPTmp.size();k++) {
					 * 
					 * 
					 * if( listCoutMPTmp.get(k).getMatierePremier().getCategorieMp().
					 * getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					 * {
					 * 
					 * if(mapMP.get(listCoutMPTmp.get(k).getMatierePremier().getCode())!=null) {
					 * 
					 * listCoutMPTmp.get(k).getMatierePremier().setNom(mapMP.get(listCoutMPTmp.get(k
					 * ).getMatierePremier().getCode()));
					 * 
					 * }
					 * 
					 * }
					 * 
					 * }
					 */
					
					
				}
			
				
			}
		});
		btnImprimer.setBounds(1121, 683, 294, 39);
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
		  
		  JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		  gridBagLayout_1.rowWeights = new double[]{0.0};
		  gridBagLayout_1.rowHeights = new int[]{0};
		  gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_2.setTitle("Cout MP");
		  titledSeparator_2.setBackground(Color.RED);
		  titledSeparator_2.setBounds(851, 424, 718, 16);
		  add(titledSeparator_2);
		  
		  JScrollPane scrollPane_1 = new JScrollPane();
		  scrollPane_1.setBounds(851, 440, 718, 232);
		  add(scrollPane_1);
		  
		  tableMP = new JTable();
		  tableMP.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Code MP", "Matiere Premiere", "Quantite Consomme", "Quantite Dechet","Quantite Charger"
		  	}
		  ));
			 tableMP.getColumnModel().getColumn(0).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
			 tableMP.getColumnModel().getColumn(2).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(3).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(4).setPreferredWidth(60); 
			
		   //  tableMP.setModel(modeleMP);
		  scrollPane_1.setViewportView(tableMP);
		  
		  JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		  scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  scrollPane_2.setBounds(851, 57, 718, 356);
		  add(scrollPane_2);
		  
		   Table_Percentage_Production = new JXTable();
		  Table_Percentage_Production.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		

		     		
		     		if(Table_Percentage_Production.getSelectedRow()!=-1)
		     		{
		     			
		     			listCoutMPTmp.clear();
		     			
		     			Parametre percentageProduction=parametreDAo.findByCode(PERCENTAGE_PRODUCTION);
		     			Production production=listProductionTmp.get(Table_Percentage_Production.getSelectedRow());
		     			
		     			int count=0;
		     			
		     			for(int p=0;p<production.getListCoutMP().size();p++)
		     			{
		     				
		     				if(production.getListCoutMP().get(p).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		     				{
		     					count+=1;
		     				}
		     				
		     				
		     			}
		     			
		     			
		     			
		     			for(int j=0;j<production.getListCoutMP().size();j++)
		     			{
		     				
		     				
		     				if(!production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) )
		     				{
		     					
		     				
		     				
		     				 
		     				CoutMP coutmp=new CoutMP();
		     				
		     				if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
		     				{
		     					BigDecimal estimation=BigDecimal.ZERO;
		     					for(int i=0;i<production.getArticles().getDetailEstimation().size();i++)
		     					{
		     						if(production.getArticles().getDetailEstimation().get(i).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
		     						{
		     							estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						}
		     					}
		     					
		     					BigDecimal quantite=BigDecimal.ZERO;
		     					if(!estimation.equals(BigDecimal.ZERO))
		     					{
		     						quantite=production.getQuantiteReel().divide(estimation,6,RoundingMode.HALF_UP);
		     					}
		     					
		     					BigDecimal rest=quantite.subtract(quantite.setScale(0, RoundingMode.FLOOR)).movePointRight(quantite.scale());

         if(!rest.equals(BigDecimal.ZERO))
             {
        	 if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
        	 {
        		 if(quantite.remainder(BigDecimal.ONE).setScale(3, RoundingMode.FLOOR).equals(BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR)))
        		 {
        			 quantite=production.getQuantiteReel().divide(estimation).setScale(0,  RoundingMode.FLOOR); 
        		 }else
        		 {
        			 quantite=(quantite.subtract(quantite.remainder(BigDecimal.ONE))).add(BigDecimal.ONE);  
        		 }
        		
        	 }else
        	 {
        		 quantite=production.getQuantiteReel().divide(estimation).setScale(0, RoundingMode.HALF_UP);
        	 }
        	
        	 
            }
		     					coutmp.setQuantConsomme(quantite);
		     					coutmp.setQuantDechet (production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP));

		     				}else if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		     				{
			  					
		     					BigDecimal quantite=production.getListCoutMP().get(j).getQuantConsomme().setScale(3).multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP));
		     					BigDecimal quantiteDechet=production.getListCoutMP().get(j).getQuantDechet().setScale(3).multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP));
		     					BigDecimal rest=quantite.subtract(quantite.setScale(0, RoundingMode.FLOOR)).movePointRight(quantite.scale());
		     					BigDecimal restdechet=quantiteDechet.subtract(quantiteDechet.setScale(0, RoundingMode.FLOOR)).movePointRight(quantiteDechet.scale());
		     							  				
		     							  					
		     							  					BigDecimal estimation=BigDecimal.ZERO;
		     						     					for(int i=0;i<production.getArticles().getDetailEstimation().size();i++)
		     						     					{
		     						     						
		     						     					//	System.out.println(production.getNumOF() +" : "+ production.getArticles().getDetailEstimation().get(i).getMatierePremier().getNom() + " : "+production.getListCoutMP().get(j).getMatierePremier().getNom());
		     						     						
		     						     						if(production.getArticles().getDetailEstimation().get(i).getMatierePremier().getId()==production.getListCoutMP().get(j).getMatierePremier().getId())
		     						     						{
		     						     							
		     						     							if(count==1)
		     						     							{
		     						     								if(production.getArticles().getDetailEstimation().get(i).getPriorite()==1)
		     						     								{
		     						     									estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						     								}
		     						     								
		     						     							}else
		     						     							{
		     						     								
		     						     								if(production.getArticles().getDetailEstimation().get(i).getPriorite()==2)
		     						     								{
		     						     									estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						     								}
		     						     								
		     						     							}
		     						     								
		     						     						}
		     						     					}
		     							  					
     									  					coutmp.setQuantConsomme(production.getQuantiteReel().multiply(estimation));
		     							  					
		     							  					if(!restdechet.equals(BigDecimal.ZERO))
		     							  					{
		     							  						 String x= String.valueOf(restdechet);	
		     							  						x=x.substring(0, 3);
		     							  						
		     									  					BigDecimal b=new BigDecimal(x).divide(production.getListCoutMP().get(j).getProdcutionCM().getArticles().getCentreCout3(),0,RoundingMode.FLOOR);
		     									  					
		     									  					coutmp.setQuantDechet (quantiteDechet.setScale(0,RoundingMode.FLOOR).add((b.setScale(0,RoundingMode.FLOOR).multiply(production.getListCoutMP().get(j).getProdcutionCM().getArticles().getCentreCout3())).divide(new BigDecimal(1000),6,RoundingMode.FLOOR)));
		     							  					}else
		     							  					{
		     							  						
		     							  						coutmp.setQuantDechet(quantiteDechet);
		     							  						
		     							  					}
		     				}else if (production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE))
		     				{
		     					
		     					BigDecimal estimation=BigDecimal.ZERO;
		     					for(int i=0;i<production.getArticles().getDetailEstimation().size();i++)
		     					{
		     						if(production.getArticles().getDetailEstimation().get(i).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
		     						{
		     							estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						}
		     					}
		     					
		     					coutmp.setQuantConsomme((production.getQuantiteReel().multiply(estimation)).multiply(Constantes.CENTURE_5000));
		     					coutmp.setQuantDechet((production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP))).multiply(Constantes.CENTURE_5000));	
		     					
		     				}else if (production.getListCoutMP().get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH))
		     				{
		     					BigDecimal estimation=BigDecimal.ZERO;
		     					for(int i=0;i<production.getArticles().getDetailEstimation().size();i++)
		     					{
		     						if(production.getArticles().getDetailEstimation().get(i).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
		     						{
		     							estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						}
		     					}
		     					
		     					coutmp.setQuantConsomme((production.getQuantiteReel().multiply(estimation)).multiply(Constantes.SCOTCHE_MULTIPLE_1000));
		     					coutmp.setQuantDechet((production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP))).multiply(Constantes.SCOTCHE_MULTIPLE_1000));	
		     					
		     				}
		     				
		     				else
		     				{
		     					BigDecimal estimation=BigDecimal.ZERO;
		     					for(int i=0;i<production.getArticles().getDetailEstimation().size();i++)
		     					{
		     						if(production.getArticles().getDetailEstimation().get(i).getMatierePremier().equals(production.getListCoutMP().get(j).getMatierePremier()))
		     						{
		     							estimation=production.getArticles().getDetailEstimation().get(i).getQuantite();
		     						}
		     					}
		     					
		     					
		     					coutmp.setQuantConsomme(production.getQuantiteReel().multiply(estimation));
		     					coutmp.setQuantDechet(production.getListCoutMP().get(j).getQuantDechet().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP)));	
		     				}
							
							  if(production.getListCoutMP().get(j).getMatierePremier().getCategorieMp(). getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							  {
								  
								  mapMP.put(production.getListCoutMP().get(j).getMatierePremier().getCode(), production.getListCoutMP().get(j).getMatierePremier().getNom());
								  
								  
								  production.getListCoutMP().get(j).setNomMP(Constantes.POURCENTAGE_PROMO);
								  
								  
							  }
							 
		     				coutmp.setQuantCharge(coutmp.getQuantConsomme().add(coutmp.getQuantDechet()));
		     				coutmp.setMatierePremier(production.getListCoutMP().get(j).getMatierePremier());
		     				coutmp.setProdcutionCM(production);
		     				listCoutMPTmp.add(coutmp);
		     				
		     			}
		     			}
		     			
		     			
		     			
		     			for(int i=0;i<listCoutMPTmp.size();i++)
		     			{
		     				
		     				if(!listCoutMPTmp.get(i).getMatierePremier().getCategorieMp(). getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		     				{
		     					
		     					listCoutMPTmp.get(i).setNomMP(listCoutMPTmp.get(i).getMatierePremier().getNom());	
		     					
		     				}else
		     				{
		     					listCoutMPTmp.get(i).setNomMP(Constantes.POURCENTAGE_PROMO);
		     					
		     				}
		     				
		     				
		     				
		     			}
		     			
		     			
		     			
		     			
		     			afficher_tableMP(listCoutMPTmp);
		     			
		     			
		     		}
		     		
		     		
		  		
		  	}
		  });
		  Table_Percentage_Production.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Num OF", "Date", "Depot", "Statut", "Quantite","Imprimer"
		  	}
		  ));
		  Table_Percentage_Production.setShowVerticalLines(false);
		  Table_Percentage_Production.setSelectionBackground(new Color(51, 204, 255));
		  Table_Percentage_Production.setRowHeightEnabled(true);
		  Table_Percentage_Production.setRowHeight(20);
		  Table_Percentage_Production.setGridColor(Color.BLUE);
		  Table_Percentage_Production.setForeground(Color.BLACK);
		  Table_Percentage_Production.setColumnControlVisible(true);
		  Table_Percentage_Production.setBackground(Color.WHITE);
		  Table_Percentage_Production.setAutoCreateRowSorter(true);
		  scrollPane_2.setViewportView(Table_Percentage_Production);
		  
		  JButton btnGenerer = new JButton("Generer les Productions");
		  btnGenerer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		Parametre percentageProduction=parametreDAo.findByCode(PERCENTAGE_PRODUCTION);
		  		if(percentageProduction.equals(null))
		  		{
		  			JOptionPane.showMessageDialog(null, "Veuillez Entrer le percentage du Production dans le parametre ","Erreur",JOptionPane.ERROR_MESSAGE);
		  			return;
		  		}
		  		mapProductionGenerer.clear();
		  		listProductionTmp.clear();
		  		
		  		if(!remplirProductionAGenerer())
		  			
		  		{

					  JOptionPane.showMessageDialog(null,
					  "Il faut remplir les Productions ‡ Generer", "Erreur",
					  JOptionPane.ERROR_MESSAGE); 	
		  		}else
		  			
		  			
		  		{
		  			
		  			intialiserTableauMP();
		  			
		  			for(int i=0;i<listProduction.size();i++)
		  			{
		  				Production production=new Production();
		  				
		  				if(mapProductionGenerer.containsKey(listProduction.get(i).getNumOF()))
		  				{
		  					
BigDecimal quantite=listProduction.get(i).getQuantiteReel().setScale(3).multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP));

BigDecimal rest=quantite.subtract(quantite.setScale(0, RoundingMode.FLOOR)).movePointRight(quantite.scale());

		  					
		  					
		  					if(!rest.equals(BigDecimal.ZERO))
		  					{
		  						 String x= String.valueOf(rest);	
		  						x=x.substring(0, 3);
		  						
				  					BigDecimal b=(quantite.multiply(new BigDecimal(1000))).divide(listProduction.get(i).getArticles().getCentreCout3(), 2, RoundingMode.HALF_UP);
				  					
				  					if(!(b.subtract(b.setScale(0, RoundingMode.FLOOR)).movePointRight(b.scale())).equals(BigDecimal.ZERO))
				  							{
				  						b=(b.setScale(0, RoundingMode.FLOOR)).add(BigDecimal.ONE);
				  						
				  							}
				  					
				  					
				  					
				  					production.setQuantiteReel((b.setScale(2, RoundingMode.HALF_UP).multiply(listProduction.get(i).getArticles().getCentreCout3())).divide(new BigDecimal(1000),6,RoundingMode.FLOOR));
		  					}else
		  					{
		  						
		  						
		  						production.setQuantiteReel(quantite);
		  					}
		  					
		  					
		  					
		  					production.setArticles(listProduction.get(i).getArticles());
		  					production.setNumOF(listProduction.get(i).getNumOF());
		  					production.setDate_debFabPre(listProduction.get(i).getDate_debFabPre());
		  					
		  					production.setStatut(listProduction.get(i).getStatut());
		  					production.setMagasinPF(listProduction.get(i).getMagasinPF());
		  					production.setListCoutMP(listProduction.get(i).getListCoutMP());
		  					
		  					if(production.getArticles().getCentreCout3().setScale(6, RoundingMode.HALF_UP).equals(new BigDecimal(2000).setScale(6,  RoundingMode.HALF_UP)))
		  					{
		  						
		  						BigDecimal remainder=production.getQuantiteReel().remainder(new BigDecimal(2));
		  						if(BigDecimal.ZERO.compareTo(remainder)!=0)
		  						{
		  							production.setQuantiteReel(production.getQuantiteReel().add(BigDecimal.ONE));
		  							
		  						}
		  					}
		  					
		  					
		  					
		  					listProductionTmp.add(production);
		  				}
		  			
		  				
		  			}
		  			
		  			afficher_tableProd_Generer(listProductionTmp);
		  		}
		  			
		  	}
		  });
		  btnGenerer.setFont(new Font("Tahoma", Font.BOLD, 13));
		  btnGenerer.setBounds(330, 519, 193, 24);
		  add(btnGenerer);
		  
		  JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		  gridBagLayout.rowWeights = new double[]{0.0};
		  gridBagLayout.rowHeights = new int[]{0};
		  gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_1.setTitle("Les Productions Generer");
		  titledSeparator_1.setBounds(849, 11, 718, 24);
		  add(titledSeparator_1);
		  
		  JButton btnDeslectionnerTout = new JButton();
		  btnDeslectionnerTout.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 6);
	     		}
		  	}
		  });
		  btnDeslectionnerTout.setToolTipText("deselectionner Tout");
		  btnDeslectionnerTout.setBounds(786, 88, 26, 26);
		  btnDeslectionnerTout.setIcon(imgDeselectAll);
		  add(btnDeslectionnerTout);
		  
		  JButton btnSelectionnertout = new JButton();
		  btnSelectionnertout.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 6);
	     		}
		  	}
		  });
		  btnSelectionnertout.setToolTipText("Selectionner Tout");
		  btnSelectionnertout.setIcon(imgSelectAll);
		  btnSelectionnertout.setBounds(815, 88, 26, 26);
		  add(btnSelectionnertout);
		  
		  JButton button = new JButton();
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		

		  		
		  		for(int i=0;i<Table_Percentage_Production.getRowCount();i++)
	     		{
		  			Table_Percentage_Production.setValueAt(false, i, 5);
	     		}
		  	
		  	}
		  });
		  button.setToolTipText("deselectionner Tout");
		  button.setIcon(imgDeselectAll);
		  button.setBounds(1500, 29, 26, 26);
		  add(button);
		  
		  JButton button_1 = new JButton();
		  button_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		
		  		for(int i=0;i<Table_Percentage_Production.getRowCount();i++)
	     		{
		  			Table_Percentage_Production.setValueAt(true, i, 5);
	     		}
		  	
		  	
		  	}
		  });
		  button_1.setToolTipText("Selectionner Tout");
		  button_1.setIcon(imgSelectAll);
		  button_1.setBounds(1529, 29, 26, 26);
		  add(button_1);
		  
		  JButton btnImprimerProduction = new JButton("Imprimer Pourcentage Production Article");
		  btnImprimerProduction.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {

		  		
		  		Parametre percentageProduction=parametreDAo.findByCode(PERCENTAGE_PRODUCTION);
		  		afficher_tableProd(listProduction);

  		  		
		  		  if(listPourcentageProductionArticle.size()!=0)
		  		  {
		  			  
		  			  for(int i=0;i<listPourcentageProductionArticle.size();i++)
		  			  {
		  				  
		  				PourcentageProductionArticle pourcentageProductionArticle= listPourcentageProductionArticle.get(i);
		  				  
		  				pourcentageProductionArticle.setPourcentageQuantite(pourcentageProductionArticle.getQuantite().multiply(percentageProduction.getValeur().divide(new BigDecimal(100),RoundingMode.HALF_UP)));  
		  				listPourcentageProductionArticle.set(i, pourcentageProductionArticle);
		  				  
		  				  
		  				  
		  			  }
		  			  
		  			
		  			Map parameters = new HashMap();
					parameters.put("depot", combodepot.getSelectedItem());
					parameters.put("du", dateDebutChooser.getDate());
					parameters.put("au", dateFinChooser.getDate());
					
					JasperUtils.imprimerPourcentageProductionArticles(listPourcentageProductionArticle, parameters);
					
					//JOptionPane.showMessageDialog(null, "PDF exportÈ avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
		  			  
		  		  }else
		  		  {
		  			JOptionPane.showMessageDialog(null, "Liste Vide", "Erreur", JOptionPane.ERROR_MESSAGE);
		  			return;
		  		  }
				 
				 
				
	  		  	
				
				
				
			
		  		
		  		
		  		
		  		
		  		
		  		
		  	
		  		
		  		
		  		
		  	}
		  });
		  btnImprimerProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  btnImprimerProduction.setBounds(319, 554, 332, 39);
		  add(btnImprimerProduction);
		  
		  JButton btnImprimerProductionArticle = new JButton("Imprimer Production Article");
		  btnImprimerProductionArticle.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		

				

  		  		
		  		  if(listPourcentageProductionArticle.size()!=0)
		  		  {
		  			  
		  			
		  			Map parameters = new HashMap();
					parameters.put("depot", combodepot.getSelectedItem());
					parameters.put("du", dateDebutChooser.getDate());
					parameters.put("au", dateFinChooser.getDate());
					
					
					JasperUtils.imprimerProductionArticles(listPourcentageProductionArticle, parameters);
					
					//JOptionPane.showMessageDialog(null, "PDF exportÈ avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
		  			  
		  		  }else
		  		  {
		  			JOptionPane.showMessageDialog(null, "Liste Vide", "Erreur", JOptionPane.ERROR_MESSAGE);
		  			return;
		  		  }
				 
				 
				
	  		  	
				
				
				
			
		  		
		  		
		  		
		  		
		  		
		  		
		  	}
		  });
		  btnImprimerProductionArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  btnImprimerProductionArticle.setBounds(20, 554, 271, 39);
		  add(btnImprimerProductionArticle);
				  		 
	}
	
void afficher_tableProd(List<Production> listProduction)
	{
	
	listPourcentageProductionArticle.clear();
		intialiserTableau();
		 boolean existe=false;
			for (int i=0;i<listProduction.size();i++)
			{	
				existe=false;
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Production production=listProduction.get(i);	
				
				for(int j=0;j<listPourcentageProductionArticle.size();j++)
				{
					PourcentageProductionArticle pourcentageProductionArticle=listPourcentageProductionArticle.get(j);
					if(pourcentageProductionArticle.getArticles().getId()==production.getArticles().getId())
					{
						existe=true;
						
						pourcentageProductionArticle.setQuantite(pourcentageProductionArticle.getQuantite().add(production.getQuantiteReel()));
						listPourcentageProductionArticle.set(j, pourcentageProductionArticle);
						
					}
					
					
					
					
				}
				
				if(existe==false)
				{
					
					PourcentageProductionArticle pourcentageProductionArticle=new PourcentageProductionArticle();
					pourcentageProductionArticle.setArticles(production.getArticles());
					pourcentageProductionArticle.setQuantite(production.getQuantiteReel());
					pourcentageProductionArticle.setPourcentageQuantite(BigDecimal.ZERO);
					listPourcentageProductionArticle.add(pourcentageProductionArticle);
				}
				
				
				
				
				Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getStatut(),production.getArticles().getLiblle(), NumberFormat.getNumberInstance(Locale.FRANCE).format(production.getQuantiteReel()),false};

				modeleProd.addRow( ligne);
			
			}
			
		
	}

void afficher_tableProd_Generer(List<Production> listProduction)
{
	intialiserTableauProdGenerer();
	 
		for (int i=0;i<listProduction.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			Production production=listProduction.get(i);
			
			
			Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getStatut(),NumberFormat.getNumberInstance(Locale.FRANCE).format(production.getQuantiteReel()),false};

			modeleProdTmp.addRow( ligne);
		
		}
		
	
}


void afficher_tableMP(List<CoutMP> listCoutMP)
{
	intialiserTableauMP();;
	 
		for (int i=0;i<listCoutMP.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			CoutMP coutmp=listCoutMP.get(i);
			
			Object []ligne={coutmp.getMatierePremier().getCode() , coutmp.getMatierePremier().getNom(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutmp.getQuantConsomme()),NumberFormat.getNumberInstance(Locale.FRANCE).format(coutmp.getQuantDechet()),NumberFormat.getNumberInstance(Locale.FRANCE).format(coutmp.getQuantCharge())};

			modeleMP.addRow( ligne);
		
		}
		
	
}




void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num OF","Date", "Depot","Statut", "Article","Quantite","Generer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,true
		     	};
		     	Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class, Boolean.class
					};
  		      public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}




void intialiserTableauProdGenerer(){
	modeleProdTmp =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num OF", "Date", "Depot", "Statut", "Quantite","Imprimer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,true
		     	};
		     	Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,Boolean.class
					};
  		      public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		     Table_Percentage_Production.setModel(modeleProdTmp); 
		     Table_Percentage_Production.getColumnModel().getColumn(0).setPreferredWidth(60);
		     Table_Percentage_Production.getColumnModel().getColumn(1).setPreferredWidth(160);
		     Table_Percentage_Production.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}


void intialiserTableauMP(){
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
				  		"Code MP", "Matiere Premiere", "Quantite Consomme", "Quantite Dechet", "Quantite Charger"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
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
		
}


boolean remplirProductionAGenerer(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean generer=(boolean) table.getValueAt(j, 6);
		if(generer==true ){
			
			mapProductionGenerer.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 6).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


boolean remplirProductionImprimer(){
	boolean trouve=false;
	int i=0;
	mapProductionImprimer.clear();
	for(int j=0;j<Table_Percentage_Production.getRowCount();j++){
		
		boolean generer=(boolean) Table_Percentage_Production.getValueAt(j, 5);
		if(generer==true ){
			
			mapProductionImprimer.put(String.valueOf(Table_Percentage_Production.getValueAt(j, 0).toString()), Boolean.valueOf(Table_Percentage_Production.getValueAt(j, 5).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
}
